<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.*,java.util.Date" %>
<%@ page import="com.freeware.gridtag.*" %> 
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hubble Organization Portal :: Team Employees Appraisal</title>
        <sx:head cache="true"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link REL="StyleSheet" HREF="<s:url value="/includes/css/GridStyle.css"/>" type="text/css">
        <link REL="StyleSheet" HREF="<s:url value="/includes/css/leftMenu.css"/>" type="text/css">
        <script language="javascript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <s:include value="/includes/template/headerScript.html"/>
    </head>
    
    <%!
    Connection connection = null;
    String appraisalAction=null;
    int userId = 0;
    String loginId = null;
    //Logger log=null;
    %>
    <body class="bodyGeneral"  oncontextmenu="return false;">
        <table class="templateTable1000x580" align="center" cellpadding="0" cellspacing="0">
            
            <tr class="headerBg">
                <td valign="top">
                    <s:include value="/includes/template/Header.jsp"/>                    
                </td>
            </tr>
            <tr>
                <td>
                    <table class="innerTable1000x515" cellpadding="0" cellspacing="0" border="0">
                        <tr>
                            <td width="150px;" class="leftMenuBgColor" valign="top">
                                <s:include value="/includes/template/LeftMenu.jsp"/>                    
                            </td>
                            <td valign="top">
                                <table cellpadding="0" width="100%" cellspacing="0" border="0">
                                    
                                    <tr>
                                        <td>
                                            <sx:tabbedpanel id="teamEmployeeAppraisalList" name="teamEmployeeAppraisalList" cssStyle="width: 845px; height: 500px;padding: 5px 5px 5px 5px;" doLayout="true">
                                                <s:if test="%{currentAction =='empTeamAppraisal' }" >                                              
                                                    <sx:div id="Appraisal" label="Team Appraisals" cssStyle="overflow:auto;">
                                                        
                                                        <table cellpadding="0" width="100%" cellspacing="0" bordercolor="#efefef">
                                                            <tr class="headerText">
                                                                <td class="headerText">
                                                                    <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td>
                                                                    <table align="center" cellpadding="0" cellspacing="0" border="0"  width="100%">
                                                                        <tr>
                                                                            <td>
                                                                                
                                                                                <% 
                                                                                
                                                                                userId = Integer.parseInt(request.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
                                                                                String queryString = (String)request.getSession(false).getAttribute(ApplicationConstants.QS_EMP_APPRAISAL_LIST);
                                                                                // out.println(queryString);
                                                                                //String  queryString="select Id,LoginId,WorkPhoneNo,Email1 from tblEmployee where ReportsTo='ryenduva' ";
                                                                                try {
                                                                                    int intCurr = 1;
                                                                                    int intSortOrd = 0;
                                                                                    String strTmp = null;
                                                                                    boolean blnSortAsc = true;
                                                                                    String strSortCol = null;
                                                                                    String strSortOrd = "DSC";
                                                                                    
                                                                                    strTmp = request.getParameter("txtCurr");
                                                                                    try {
                                                                                        if (strTmp != null)
                                                                                            intCurr = Integer.parseInt(strTmp);
                                                                                    } catch (NumberFormatException NFEx) {
                                                                                        NFEx.printStackTrace();
                                                                                    } //catch
                                                                                    
                                                                                    // for lookup connection
                                                                                    connection = ConnectionProvider.getInstance().getConnection();
                                                                                    
                                                                                    strSortCol = request.getParameter("Colname");
                                                                                    strSortOrd = request.getParameter("txtSortAsc");
                                                                                    if (strSortCol == null) strSortCol = "DateStart";
                                                                                    if (strSortOrd == null) strSortOrd = "DSC";
                                                                                    blnSortAsc = (strSortOrd.equals("ASC"));
                                                                                
                                                                                %>
                                                                                <%     appraisalAction = "../../employee/appraisal/project.action?currentAction=addProject.action";%>
                                                                                <form  id="frmDBGrid" name="frmDBGrid"  action="" method="post">           
                                                                                    <s:if test="%{currentAction =='empAppraisal'}" >
                                                                                        <grd:dbgrid id="tblStat" name="tblStat" width="100" pageSize="19" 
                                                                                                    currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                                                    dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                                            
                                                                                            <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                                           imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                                                           addImage="../../includes/images/DBGrid/Add.png"  addAction="<%=appraisalAction%>" /> 
                                                                                            <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="false"/>
                                                                                            <grd:rownumcolumn headerText="SNO" width="2"/>
                                                                                            <grd:imagecolumn  headerText="Edit" width="5"  HAlign="center"  
                                                                                                              imageSrc="../../includes/images/DBGrid/newEdit_17x18.png" 
                                                                                                              linkUrl="editEmployeeAppraisal.action?currentEmployeeId={Id}&empId={EmpId}" imageBorder="0" 
                                                                                                              imageWidth="16" imageHeight="16" alterText="Click to edit" />
                                                                                            <grd:numbercolumn dataField="EmpId" headerText="EmpId" width="8" dataFormat=" "/>
                                                                                            <grd:datecolumn dataField="AppraisalDate" headerText="AppraisalDate" width="10" dataFormat="MM-dd-yyyy"/>
                                                                                            <grd:textcolumn dataField="CurrnetSalary" headerText="CurrnetSalary" width="15" dataFormat=" "/>
                                                                                            <grd:textcolumn dataField="onSiteDuration" headerText="onSiteDuration" width="15" dataFormat=" "/>
                                                                                            <grd:textcolumn dataField="OffShoreDuration" headerText="OffShoreDuration" width="15" dataFormat=" "/>
                                                                                        </grd:dbgrid>
                                                                                        
                                                                                    </s:if>
                                                                                    
                                                                                    <s:if test="%{currentAction =='empAppraisal1'}">
                                                                                        <grd:dbgrid id="frmDBGrid" name="frmDBGrid" width="100" pageSize="19" 
                                                                                                    currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                                                    dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                                            
                                                                                            <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                                           imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                                                           addImage="../../includes/images/DBGrid/Add.png"  /> 
                                                                                            <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="false"/>
                                                                                            <grd:rownumcolumn headerText="SNO" width="2"/>
                                                                                            <grd:imagecolumn  headerText="Edit" width="5"  HAlign="center"  
                                                                                                              imageSrc="../../includes/images/DBGrid/newEdit_17x18.png" 
                                                                                                              linkUrl="editEmployeeAppraisal.action?currentEmployeeId={Id}&empId={EmpId}" imageBorder="0" 
                                                                                                              imageWidth="16" imageHeight="16" alterText="Click to edit" />
                                                                                            <grd:numbercolumn dataField="EmpId" headerText="EmpId" width="8" dataFormat=" "/>
                                                                                            <grd:datecolumn dataField="AppraisalDate" headerText="AppraisalDate" width="10" dataFormat="MM-dd-yyyy"/>
                                                                                            <grd:textcolumn dataField="CurrnetSalary" headerText="CurrnetSalary" width="15" dataFormat=" "/>
                                                                                            <grd:textcolumn dataField="onSiteDuration" headerText="onSiteDuration" width="15" dataFormat=" "/>
                                                                                            <grd:textcolumn dataField="OffShoreDuration" headerText="OffShoreDuration" width="15" dataFormat=" "/>
                                                                                        </grd:dbgrid>
                                                                                    </s:if>
                                                                                    
                                                                                    
                                                                                    <s:if test="%{currentAction =='empTeamAppraisal'}">
                                                                                        <grd:dbgrid id="frmDBGrid" name="frmDBGrid" width="100" pageSize="19" 
                                                                                                    currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                                                    dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable" >
                                                                                            <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                                           imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                                                           addImage="../../includes/images/DBGrid/Add.png"    />
                                                                                            <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="false"/>
                                                                                            <grd:rownumcolumn headerText="SNO" width="2"/>
                                                                                            <grd:imagecolumn  headerText="Edit" width="5"  HAlign="center"  
                                                                                                              imageSrc="../../includes/images/DBGrid/newEdit_17x18.png" 
                                                                                                              linkUrl="editTeamAppraisal.action?currentEmployeeId={Id}" imageBorder="0" 
                                                                                                              imageWidth="16" imageHeight="16" alterText="Click to edit" />
                                                                                            <grd:numbercolumn dataField="Id" headerText="EmpId" width="8" dataFormat=" "/>
                                                                                            <grd:textcolumn dataField="LoginId" headerText="LoginId" width="10" dataFormat=" "/>
                                                                                            <grd:textcolumn dataField="FullName" headerText="EmpName" width="15"/>
                                                                                            <grd:numbercolumn dataField="WorkPhoneNo" headerText="Phone NO" width="10" dataFormat=" " />
                                                                                            <grd:textcolumn dataField="Email1" headerText="EMailAddress" width="15" />
                                                                                        </grd:dbgrid>
                                                                                    </s:if>
                                                                                    <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                                                    <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                                                    <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                                                                    <input type="hidden" name="submitedForm" value="dbGrid">
                                                                                    <s:hidden name="strCurrentEmployeeId" id="strCurrentEmployeeId" value="%{currentEmployeeId}"/>
                                                                                    <s:hidden name="currentAction" id="currentAction"  value="%{currentAction}" />
                                                                                    
                                                                                </form>
                                                                                <%
                                                                                connection.close();
                                                                                connection = null;
                                                                                }catch(Exception ex) {
                                                                                    ex.printStackTrace();
                                                                                    // log.setLevel((Level)Level.ERROR);
                                                                                    //log.error("The Error @addTimeSheet()",ex);
                                                                                }finally {
                                                                                    if(connection!=null)
                                                                                        connection.close();
                                                                                    connection = null;
                                                                                } // finally
                                                                                
                                                                                
                                                                                %>
                                                                            </td>
                                                                        </tr>
                                                                    </table>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </sx:div>
                                                </s:if>
                                                <s:else>
                                                    <sx:div id="Appraisal" label="Appraisals">
                                                        
                                                        <table cellpadding="0" width="100%" cellspacing="0" bordercolor="#efefef">
                                                            <tr class="headerText">
                                                                <td class="headerText">
                                                                    <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td>
                                                                    <table align="center" cellpadding="0" cellspacing="0" border="0"  width="100%">
                                                                        <tr>
                                                                            <td>
                                                                                
                                                                                <% 
                                                                                
                                                                                userId = Integer.parseInt(request.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
                                                                                String queryString = (String)request.getSession(false).getAttribute(ApplicationConstants.QS_EMP_APPRAISAL_LIST);
                                                                                // out.println(queryString);
                                                                                //String  queryString="select Id,LoginId,WorkPhoneNo,Email1 from tblEmployee where ReportsTo='ryenduva' ";
                                                                                try {
                                                                                int intCurr = 1;
                                                                                int intSortOrd = 0;
                                                                                String strTmp = null;
                                                                                boolean blnSortAsc = true;
                                                                                String strSortCol = null;
                                                                                String strSortOrd = "DSC";
                                                                                
                                                                                strTmp = request.getParameter("txtCurr");
                                                                                try {
                                                                                if (strTmp != null)
                                                                                intCurr = Integer.parseInt(strTmp);
                                                                                } catch (NumberFormatException NFEx) {
                                                                                NFEx.printStackTrace();
                                                                                } //catch
                                                                                
                                                                                // for lookup connection
                                                                                connection = ConnectionProvider.getInstance().getConnection();
                                                                                
                                                                                strSortCol = request.getParameter("Colname");
                                                                                strSortOrd = request.getParameter("txtSortAsc");
                                                                                if (strSortCol == null) strSortCol = "DateStart";
                                                                                if (strSortOrd == null) strSortOrd = "DSC";
                                                                                blnSortAsc = (strSortOrd.equals("ASC"));
                                                                                
                                                                                %>
                                                                                <%     appraisalAction = "../../employee/appraisal/project.action?currentAction=addProject.action";%>
                                                                                <form  id="frmDBGrid" name="frmDBGrid"  action="" method="post">           
                                                                                    <s:if test="%{currentAction =='empAppraisal'}" >
                                                                                        <grd:dbgrid id="tblStat" name="tblStat" width="100" pageSize="19" 
                                                                                                    currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                                                    dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                                            
                                                                                            <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                                           imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                                                           addImage="../../includes/images/DBGrid/Add.png"  addAction="<%=appraisalAction%>" /> 
                                                                                            <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="false"/>
                                                                                            <grd:rownumcolumn headerText="SNO" width="2"/>
                                                                                            <grd:imagecolumn  headerText="Edit" width="5"  HAlign="center"  
                                                                                                              imageSrc="../../includes/images/DBGrid/newEdit_17x18.png" 
                                                                                                              linkUrl="editEmployeeAppraisal.action?currentEmployeeId={Id}&empId={EmpId}" imageBorder="0" 
                                                                                                              imageWidth="16" imageHeight="16" alterText="Click to edit" />
                                                                                            <grd:numbercolumn dataField="EmpId" headerText="EmpId" width="8" dataFormat=" "/>
                                                                                            <grd:datecolumn dataField="AppraisalDate" headerText="AppraisalDate" width="10" dataFormat="MM-dd-yyyy"/>
                                                                                            <grd:textcolumn dataField="CurrnetSalary" headerText="CurrnetSalary" width="15" dataFormat=" "/>
                                                                                            <grd:textcolumn dataField="onSiteDuration" headerText="onSiteDuration" width="15" dataFormat=" "/>
                                                                                            <grd:textcolumn dataField="OffShoreDuration" headerText="OffShoreDuration" width="15" dataFormat=" "/>
                                                                                        </grd:dbgrid>
                                                                                        
                                                                                    </s:if>
                                                                                    
                                                                                    <s:if test="%{currentAction =='empAppraisal1'}">
                                                                                        <grd:dbgrid id="frmDBGrid" name="frmDBGrid" width="100" pageSize="19" 
                                                                                                    currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                                                    dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                                            
                                                                                            <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                                           imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                                                           addImage="../../includes/images/DBGrid/Add.png"  /> 
                                                                                            <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="false"/>
                                                                                            <grd:rownumcolumn headerText="SNO" width="2"/>
                                                                                            <grd:imagecolumn  headerText="Edit" width="5"  HAlign="center"  
                                                                                                              imageSrc="../../includes/images/DBGrid/newEdit_17x18.png" 
                                                                                                              linkUrl="editEmployeeAppraisal.action?currentEmployeeId={Id}&empId={EmpId}" imageBorder="0" 
                                                                                                              imageWidth="16" imageHeight="16" alterText="Click to edit" />
                                                                                            <grd:numbercolumn dataField="EmpId" headerText="EmpId" width="8" dataFormat=" "/>
                                                                                            <grd:datecolumn dataField="AppraisalDate" headerText="AppraisalDate" width="10" dataFormat="MM-dd-yyyy"/>
                                                                                            <grd:textcolumn dataField="CurrnetSalary" headerText="CurrnetSalary" width="15" dataFormat=" "/>
                                                                                            <grd:textcolumn dataField="onSiteDuration" headerText="onSiteDuration" width="15" dataFormat=" "/>
                                                                                            <grd:textcolumn dataField="OffShoreDuration" headerText="OffShoreDuration" width="15" dataFormat=" "/>
                                                                                        </grd:dbgrid>
                                                                                    </s:if>
                                                                                    
                                                                                    
                                                                                    <s:if test="%{currentAction =='empTeamAppraisal'}">
                                                                                        <grd:dbgrid id="frmDBGrid" name="frmDBGrid" width="100" pageSize="19" 
                                                                                                    currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                                                    dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable" >
                                                                                            <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                                           imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                                                           addImage="../../includes/images/DBGrid/Add.png"    />
                                                                                            <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="false"/>
                                                                                            <grd:rownumcolumn headerText="SNO" width="2"/>
                                                                                            <grd:imagecolumn  headerText="Edit" width="5"  HAlign="center"  
                                                                                                              imageSrc="../../includes/images/DBGrid/newEdit_17x18.png" 
                                                                                                              linkUrl="editTeamAppraisal.action?currentEmployeeId={Id}" imageBorder="0" 
                                                                                                              imageWidth="16" imageHeight="16" alterText="Click to edit" />
                                                                                            <grd:numbercolumn dataField="Id" headerText="EmpId" width="8" dataFormat=" "/>
                                                                                            <grd:textcolumn dataField="LoginId" headerText="LoginId" width="10" dataFormat=" "/>
                                                                                            <grd:textcolumn dataField="FullName" headerText="EmpName" width="15"/>
                                                                                            <grd:numbercolumn dataField="WorkPhoneNo" headerText="Phone NO" width="10" dataFormat=" " />
                                                                                            <grd:textcolumn dataField="Email1" headerText="EMailAddress" width="15" />
                                                                                        </grd:dbgrid>
                                                                                    </s:if>
                                                                                    <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                                                    <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                                                    <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                                                                    <input type="hidden" name="submitedForm" value="dbGrid">
                                                                                    <s:hidden name="strCurrentEmployeeId" id="strCurrentEmployeeId" value="%{currentEmployeeId}"/>
                                                                                    <s:hidden name="currentAction" id="currentAction"  value="%{currentAction}" />
                                                                                    
                                                                                </form>
                                                                                <%
                                                                                connection.close();
                                                                                connection = null;
                                                                                }catch(Exception ex) {
                                                                                ex.printStackTrace();
                                                                                // log.setLevel((Level)Level.ERROR);
                                                                                //log.error("The Error @addTimeSheet()",ex);
                                                                                }finally {
                                                                                if(connection!=null)
                                                                                connection.close();
                                                                                connection = null;
                                                                                } // finally
                                                                                
                                                                                
                                                                                %>
                                                                            </td>
                                                                        </tr>
                                                                    </table>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </sx:div>
                                                </s:else>
                                            </sx:tabbedpanel>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr class="footerBg">
                <td align="center"><s:include value="/includes/template/Footer.jsp"/></td>
            </tr>
        </table>
        
        
    </body>
</html>
