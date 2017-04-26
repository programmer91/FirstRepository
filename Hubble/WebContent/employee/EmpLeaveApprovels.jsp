 <!-- 
 *******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  November 20, 2007, 3:25 PM
 *
 * Author  : Rajasekhar Yenduva<ryenduva@miraclesoft.com>
 *
 * File    : LeaveList.jsp
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
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="javax.sql.DataSource" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hubble Organization Portal :: Employee Leave List</title>
        <sx:head cache="true"/>
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
        <s:include value="/includes/template/headerScript.html"/>
    </head>
    <body class="bodyGeneral" oncontextmenu="return false;">
        <%!
        /* Declarations */
        Connection connection;
        String queryString;
        String strTmp;
        String strSortCol = null;
        String strSortOrd = "ASC";
        String userId;
        String submittedFrom;
        String action;
        
        int role;
        int intSortOrd = 0;
        int intCurr;
        boolean blnSortAsc = true;
        %>
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
                            <td width="850px" class="cellBorder" valign="top" style="padding-left:10px;padding-top:5px;">
                                <!--//START TABBED PANNEL : -->
                                <ul id="accountTabs" class="shadetabs" >
                                    <li ><a href="#" class="selected" rel="leaveRequestsTab"  > Leave Approvals List </a></li>
                                    <li ><a href="#" class="" rel="teamLeavesSearchTab"  > Search Leave </a></li>
                                </ul>
                                
                                <%-- <sx:tabbedpanel id="attachmentPannel2" cssStyle="width: 845px; height: 520px;padding-left:10px;padding-top:5px;" doLayout="true"> --%>
                                <div  style="border:1px solid gray; width:845px;height: 520px; overflow:auto; margin-bottom: 1em;">  
                                    <!--//START TAB : -->
                                    <%-- <sx:div id="leaveRequestsTab" label="Leaves List" cssStyle="overflow:auto"> --%>
                                    <div id="leaveRequestsTab" class="tabcontent"  >
                                        
                                        <%
                                        /* Declarations */
                                        
                                        %>
                                        
                                        <%
                                        /* String Variable for storing current position of records in dbgrid*/
                                        strTmp = request.getParameter("txtCurr");
                                        
                                        intCurr = 1;
                                        
                                        if (strTmp != null)
                                            intCurr = Integer.parseInt(strTmp);
                                        
                                        /* Specifing Shorting Column*/
                                        strSortCol = request.getParameter("txtSortCol");
 
                                        if (strSortCol == null) strSortCol = "EndDate";
 
                                        strSortOrd = request.getParameter("txtSortAsc");
                                        if (strSortOrd == null) strSortOrd = "DESC";
                                        
                                        
                                      /*  strSortCol = request.getParameter("txtSortCol");
                                        strSortOrd = request.getParameter("txtSortAsc");
                                        if (strSortCol == null) strSortCol = "EmpName";
                                        if (strSortOrd == null) strSortOrd = "ASC"; */
                                        blnSortAsc = (strSortOrd.equals("ASC"));
                                        
                                        try{
                                            
                                            /* Getting DataSource using Service Locator */
                                            connection=ConnectionProvider.getInstance().getConnection();
                                            
                                            
                                            /* Sql query for retrieving resultset from DataBase */
                                            queryString  =null;
                                            String empLeaveAction = "../employee/leaveRequest.action";
                                            String userId = (String) session.getAttribute(ApplicationConstants.SESSION_USER_ID);
                                        /*String  queryString="select `tblEmpLeaves`.`Id` AS `LeaveId`,`tblEmpLeaves`.`Status` AS `Status`, `tblEmpLeaves`.`EmpId` AS `EmpId`,concat(`tblEmployee`.`FName`,'.',`tblEmployee`.`LName` ) AS `EmpName`," +
                                        "`tblEmpLeaves`.`StartDate` AS `StartDate`,`tblEmpLeaves`.`EndDate` AS `EndDate`,`tblEmpLeaves`.`leaveType` AS `leaveType`,`tblEmpLeaves`.`reportsTo` AS `reportsTo` " +
                                        "from (`tblEmployee` join `tblEmpLeaves` on(`tblEmployee`.`Id` = `tblEmpLeaves`.`EmpId`)) where `tblEmpLeaves`.`reportsTo` ='" + userId +"'" + " order by `EndDate` desc";*/
                                            String queryString=request.getAttribute(ApplicationConstants.EMP_LEAVE_APPROVAL_LIST).toString();
                                           // String queryString="select tblEmpLeaves.Id AS LeaveId,tblEmpLeaves.Status AS Status,tblEmpLeaves.EmpId AS EmpId, concat(tblEmployee.FName,'.',tblEmployee.LName ) AS EmpName,tblEmpLeaves.StartDate AS StartDate,tblEmpLeaves.EndDate AS EndDate,tblEmpLeaves.leaveType AS leaveType,tblEmpLeaves.reportsTo AS reportsTo from (tblEmployee join tblEmpLeaves on(tblEmployee.Id = tblEmpLeaves.EmpId)) where tblEmpLeaves.reportsTo ='plokam' OR tblEmpLeaves.reportsTo IN('sratnala','rravi','smutnuri','vgudla','sbongu','sgudivada','bshaw','rchandra','vkaleepalli','vchalapureddi','ranand','psattu','srokkam','sdasari','rita','rbanerjee','pdonepudi','ajayanthi','ssrivastav','vv**','bd***','syelavarthy','vatti','pbayana','rpoli','pcolluru','null','sbora','yseelam','dgaddibhukta','rganapathiraju','sogirala','skondepudi','sbasava','vyerra','pkandrakota','cguduru','rgarlanka','mrajana','samujuri','rdulam','sbonam','saddala','mbeesetti','rpeethala','rkaruku','ppattisapu','achandu','cpasumarthi','avaranasi','jvankadari','mlokam','vgandham','spolisetti','pkothapally','sdash','ajuthada','schakravarthula','akammili','nbaswani','sghanta','bmadena','matchi','vravada','npoduri','jmajji','kmamidi','mmohammed','mmukku','smantripragada','isheik','rayyalasomayajula','ksaurabh','bbalakrishnan','kpaul','sdass','sdharmendra','sveerni','cutla','rbehara','mvangury','graguthu','fnaim','kvelagaleti','kyeramala','ksubramanya','mbonthu','pnemani','spinnamaraju','kmuddana','rballa','rtheetla','rmaddugaru','svatsavayi','amarabathula','ddubey','rnelli','msirugudi','rgurumakonda','rjupalli','acherukuri','gkaranam','kmenon','kvonteddu','rmanku','vgiduthuri','skotagiri','lgudla','nsrighakollapu','fmohammed','ssanapala','rnarva','rgollapalli','rsripatnala','apasupuleti','ssunkari','varyasomayajula','gjanapareddy','rgudimetla','mbonthu1','akumar','spandey','skoyya','ars','vgandikota','ryayi','rtheethla','dammu','ynakkani','skondamuri','schintapalli','skalanatham','pdash','ssigireddy','mkodrappassery','ppalivela','bgalla','cjada','amanapuram','jkundala','ysurabathula','gsurabathula','ktatineni','karri','bsuram','amehmood','sattili','kpasha','apilla','smukkamala','svaranasi','vrao','skedarisetty','amarpu','jchapa','amente','rravuri','smalla','kkalaizhagan','vvanka','vkota','ljampana','hboddeti','spilla','kkalidindi','jmarpu','rvagvala','not avilable','stiwari1','vkunadharaju','vvennapu','sakasapu','rnatrajan','vkilani','vkonatham','nmakina','adamodara','radapa','rlokam','rsanjeev','rkota1','sarora','tvyda','vkasturi','rkondaveeti','cnallam','kpuvvala','rgidla','snagumalli','babburi','vbandaru','satya','rsahukar','amanyala','aghosal','daryasomayajula','mdommeti','richard','crao','schinta','vseera','dareti','kbondada','ssirapu','kravipati','spativada','sanala','sande','skanchdapu','bavula','mmohammed','pkumar','vkasarapu','jpandiri','rvelyudhan','smantena','psabbavarapu','vnalli','rvadiyala','bchowdhury','adola','vpenumasta','plingam','raj','rachel','sgandham','kkolachala','angelal','skadari','spalla','fmatheen','mveerapaneni','dlee','muppala','srayapureddy','sthennela','rchaganti1','vpendyala','amiriyala','pkar','ashaik','ltavva','stiwari','ntholathuty','lavala','savala','rdas','amohammad','abalan','sbalan','nerothi','vyeduru','slokam','dchunn','scott.miracle','hsamanthapudi','ukuppili','jmolli','jboddu','rgiduthuri','ckunapareddy','apenmetsa','spitchika','sgalla','lnetinti','gpalkodaty','kchagamreddy','kganti','gnemani','mbansal','mjose','grazhdanmarc','muddala','skodukula','vsuda','spilla','rmareedu','jhurd','ssheety','cbhaskarabatla','svandavasi','nnistla','sghandikota','pkumar1','tmanuel','sbonela','vsharma','rob.doucette','mgimble','adevotta','bkarri','gallada','vbattula','rvejarla','sthota','csannidhiraju','tricky','klofranco','lbehara','mchokkakula','pcartin','nshaik','nmedidi','rgarlanka','rpyreddy','bshaffe1','rshinn','spedireddy','rvyda','ashaik2','skrupa','rmutyala','gbalakrishnan','rbalireddy','upodugu','ppolumahanthi','sduvvuri','dchalla','spusapati','vmohammed','sgundu','vpeddysetty','[astockyj','kbehara','gsaripalli','apiedy','dsupriya','smurugesan','kganta','vsaladi','sbommu','rchinagandham','ksripathi','ssuragala','pajjarapu','jkamanduri','hsuvvada','ggupta','vnadipena','spulagam','rbuddhavarapu','vlingampalli','rgunda','rpullakhandam','ksteve','skovari','skopula','ssirki','spitla','rbaig','dpandava','ysanapala','vbhogaraju','mchaganti','mkakarla','adutta','gkota','skolli9','gsirasapalli','larthimalla','vsagi','rmajji','atankala','jbatakala','ndhokale','sdhole','vlad.bruk','kbathini','smagam','smente','vmunagala','sdondapati','rshaik','njain','asheik','apaluri','ashaik3','cgompa','sjilludumudi','myelleti','sakkaraboyana','myedida','drayi','aseeram','kballa','rpinnamaraju','rnaidu','gsandireddy','cbremmon','pkakani','proutaray','drautraya','lpundru','gmaddi','vvadlapudi','vattada','mtholatuty','kveluri','spotala','rwalker','bkudana','cavery','gakana','nbryant','talaparthi','narwood','sbondepalli','oawad','rbadham','pkottam','oweizman','rbhupatiraju','skandregula','sdonthneni','vbokka','ukurimineni','sbhumireddy','sbommana','skrishnamurthy','dkoneru','jbondada','vbrundavanam','rchintapalli','skanchi','kiranchandu','schintalapudi','schukka','mkopparthi','vdattini','clankalapalli','vchebolu','bshaik2','adunga','vsubr','nnainalasetti','rvenkuduswamy','sgedala1','svalavala1','rpasala','rrajapudi','rdenduluri','ncollins','pchikkam','ddenison','chakridhavala','ndogiparthi','vyerra1','vchavakula','rpinninti','semmandi','gsanjeevannagari','gadde_n','sadapa1','nnadipalli','mgawjowniczek','sdanthuluri','vbalabhadruni','samba681','ngoriparti','sgulla','jpeela','skanneganti','uavuthu','smadhupada','kjala','ntiruvaipati','vparamesh','hreed','rjayathirtha','nkonakanchi','vruthala','vijaykumarj','rjonnadula','kkareddula','smullangi','vballa','skastury','ssatuluri','bkattoju','ykondareddy','pmuddana','sambati','rlankalapalli','vgunti','jlemond','dpinapala','rmadhira','vveerla','ubandaru','jrajasimha','skumarapu','jkota','mmaka','mmotupalli','rmamidi','hgujjula','mparepalli','rpendyala','jampili','vmastoli','mmanikonda','mbokam','rbalivada','sgarapati','vputambaram','nparasher','venky','kchittineni','smaddali','vdantuluri','ryerramilli','rpoduri','rvelpula','rgiduturi','rgulapallu','mkalavapudi','dmudunuri','smatta','apalla','scherukuri','lmuppidi','gkatari','vpentapati','evankayalapati','hvedavalli','wvelpula','mvemuri','mineni','pvemuri','mbandi','slingamallu','rvinakota','kvuddharaju','psurapaneni','kyenneti','srsankara','naravelli','sreddy','pyellamanchili','pbrar','ssuryadevara','rsreeram','ssabbella','jthom','cvijjapu','grokkam','mpolisetti','vamsi_muddada','jpittu','bkovvuru','nvrajareddy','bala','nneelam','aogirala','kpeeru','spalanichamy','jpanditi','joe','vommi','syechuri','jsaini','snorway','rkommuri','vpenupala','cooneygate','rajesh_kamisetty','skasarapu','sree','sdeshpande','sdachepalli','spappala','kdharmavarapu','vgidugu','apotireddy','vkundharaju','pbibhu','rnadupuru','ppothireddy','bkilli','jpaliakkara','sgunta','smalapareddy','cpednam','bdube','rsargadam1','kjanjanam','vkoppula','rnandyala','rindukuri','snatchireddy','bkondaveti','sbhupathiraju','bpeddireddi','stamvada','sbonthula','akaki','bskillman','skotti','kmohammad','ynanday','gommi','ayadav','mohammad','ckarumuri','sparepalli','ssampathirao','skorlam_old','styagi','sanchuri','sdevaras','mdogra','jpaidi','sdulam','tnallani','snulu','sseru','twalsworth','cponnusamy','syenneti1','lnayana','skolluru','sbadugu','bbahadursha','gippili','skhan','vvani','schitre','cgovindugari','vnamburi','nchimakurthi','vvalchandani','jnukala','pgeddam','spanda','kabbireddy','rvungarala','nchinthakindi','utamilarasan','sasrar','sedara','tmolleti','cborusu','syenneti','cgorle','smaddimsetti','sseshadri','sayyagari','gbaliboyana','sdarisipudi','dbhogavalli','pdirisala','stelampudi','bgorapalli','varyasomayajula1','aravi','pshaik','bgudla','vravi','cpolaki','skondeypati','nvedula','skavala','chaitanya','bsukrisno','ugandepalli','ryepuru','smadabathula','mbondili','asahini','sali','suppala','spragada','jchandra','psethurama','sdommeti1','nbuddha','jpyna','rpenumetsa','lnama','bzaradich','sbondapalli','ibm1','ksharma','ssindiri','schintalapudi1','sbendi','ibhuri','karunakar','syerubandi','srinivas','plaveti','jlerner','tkhazra','meyyappanm','vennasravan','sandesh_y','rstoklosa','haqshaik','vuday1','asamayam','bninan','yosef.abreham','rsekar','mkillens','srajendran','nchandhana','adandothkar','avittalam','gkatzman','rdoddavenkannavar','swolff','kkarnati','wvawter','snaidu','jmanavalan','amanabala','amanuel','amarapu','asesetti','astockyj','bbinu','bdeepthimahanthi','bgude','bpillutla','bpoludasu','ckolchala','cvaranasi','cwood','dcooke','dprasad','hbikki','hlewis','hnagumalli','jputhuparambil','jreed','krispodili','lchindada','lpoduri','lschneider','lvatchavai','mfratarcangeli','mgoldstein','mmansoorahmed','mmathur','mtatiparthi','mtulala','nmetz','npalivela','nvaranasi','nyacoub','pcooney','pgajavelli','pgosh','pmalgireddy','rcoduru','rdwibhashi','rgottimukkala','rkonjerla','rkuppili','rmalini','rraghavan','rthompson','sadmin','sarmstrong','sboddeda1','sdannana','shatti','sjalakam','skatragadda','smanthena','snistala','ssamala','ssanam','ssha','svallesety','svelidanda','twendt','vgunturu','vjohn','vnakka','vtanikella','vvadlamani','vvaranasi','zhafeez','sboddeda','sraj','vishy','sri','mmanchiganti','mgoru','sgnaneswaran','sam','kvalentine','jpeddiboyina','madhu','jsaneda','vmogalla','funny','pkarri','smuttangi','sbalibani','vdoddapureddy','lyenda','bnandaluri','something','schamana','nmahareddy','lmahanti','rbhupathi','sthanneru','kmummuluri','ngantimahapathruni','rrrrrrr','sbokka','kbodhasrungi','ssubramanian','vkastury','rgopalan','preddy')";
                                           //out.print(queryString);
                                        
                                        %>
                                        
                                        
                                        <form method="post" id="frmDBGrid" name="frmDBGrid" action=""> 
                                            <table cellpadding="0" cellspacing="0" width="100%">
                                                <tr>
                                                    <td class="headerText">
                                                        
                                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                        <s:property value="#request.resultMessage"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td> 
                                                        <!-- DataGrid for list all activities -->
                                                        <grd:dbgrid id="tblEmpLeaves" name="tblEmpLeaves" width="100" pageSize="15" 
                                                                    currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                    dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                            
                                                            <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif" imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                                                           imgNext="../includes/images/DBGrid/Next.gif" imgLast="../includes/images/DBGrid/Last.gif"
                                                                           addImage="../includes/images/DBGrid/Add.png"  
                                                            />
                                                            <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>"  imageAscending="../includes/images/DBGrid/ImgAsc.gif" imageDescending="../includes/images/DBGrid/ImgDesc.gif" />
                                                            
                                                            <grd:imagecolumn  headerText="Edit" width="5"  HAlign="center" 
                                                                              imageSrc="../includes/images/DBGrid/Edit.gif" 
                                                                              linkUrl="getleaveApprovalList.action?leaveId={LeaveId}" 
                                                                              imageBorder="0" imageWidth="16" imageHeight="16" 
                                                                              alterText="Click to edit" /> 
                                                            <grd:textcolumn dataField="EmpName" headerText="Applied By"  width="16"  sortable="true"/>
                                                            
                                                            <grd:datecolumn dataField="StartDate"  headerText="StartDate" HAlign="right" dataFormat="MM-dd-yyyy" width="18" sortable="false"/>
                                                            <grd:datecolumn dataField="EndDate"  headerText="EndDate"  HAlign="right" dataFormat="MM-dd-yyyy" width="18" sortable="false"/>
                                                            <grd:textcolumn dataField="leaveType" headerText="LeaveType"  width="16"  sortable="true"/>
                                                            <grd:textcolumn dataField="Status" headerText="Status"  width="16"  sortable="true"/> 
                                                            <grd:textcolumn dataField="reportsTo" headerText="ReportsTo" width="15"/>
                                                            
                                                        </grd:dbgrid>
                                                        
                                                        <!-- these components are DBGrid Specific  -->
                                                       
                                                        <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                        <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                        <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                                        <s:hidden  name="empName" value="%{empName}"/>
                                                        <s:hidden  name="leaveStatus" value="%{leaveStatus}"/>
                                                        <input id="txtDBId" TYPE="hidden" NAME="txtDBId" VALUE="0">
                                                        <input id="txtTRId"TYPE="hidden" NAME="txtTRId" VALUE="1">  
                                                    </td>
                                                </tr>
                                            </table>
                                        </form>
                                        
                                        <%
                                        }catch(Exception ex){
                                            out.println(ex.toString());
                                        }finally{
                                            if(connection!= null){
                                                connection.close();
                                            }
                                        }
                                        %>
                                        
                                        
                                        <%-- </sx:div> --%>
                                    </div>
     
                                <script type="text/javascript">

var countries=new ddtabcontent("accountTabs")
countries.setpersist(false)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()

                                </script>
                                <!--//END TABBED PANNEL : -->
                                
                                
                               
                                    <div id="teamLeavesSearchTab" class="tabcontent"  >
                                       <s:form name="frmSearch" action="teamMembersLeavesSearch" theme="simple" method="post">
                                        
                                            <!--The components of searching issues -->
                                            <br><br>
                                            <table border="0" cellpadding="1" cellspacing="1" width="80%" align="center">
                                                <tr> 
                                                    <td  class="fieldLabel">Team&nbsp;Members&nbsp;:</td>
                                                    <td>
                                                    <s:select list="#session.MyTeamMapForLeaveSearch" name="empName" id="empName"  headerValue="--Please Select--" headerKey="" cssClass="inputSelect"/>
                                                    </td>
                                                    
                                                    <td  class="fieldLabel">Leave&nbsp;Status&nbsp;:</td>
                                                    <td>
                                                     <s:select list="{'Applied','Approved','Cancelled'}"
                                                                                  name="leaveStatus"
                                                                                  headerKey="" 
                                                                                  headerValue="--Please Select--"
                                                                              cssClass="inputSelect"  />
                                                    </td>
                                                      <td  align="center">
                                                     
                                                         <s:submit cssClass="buttonBg" value="Search"/>
                                                    </td>    
                                                </tr>
        
                                                
                                            </table>
                                            
                                        </s:form>
                                    </div>
                                </div>
                                
                                
                                
                                
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

