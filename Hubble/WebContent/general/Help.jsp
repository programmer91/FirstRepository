<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>

<%--
  author @Raja Reddy Andra
  
  Email @randra@miraclesoft.com
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%--Start The Page --%>
<html>

<%--Start The Head Section --%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>Hubble Organization Portal :: Help</title>
<style type="text/css">
    
</style>

<script src="../includes/javascripts/SpryCollapsiblePanel.js" type="text/javascript"></script>
<script src="../includes/javascripts/HelpLeftTextAnimation.js" type="text/javascript"></script>
<script src="../includes/javascripts/HelpRightTextAnimation.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
<link href="../includes/css/SpryCollapsiblePanel.css" rel="stylesheet" type="text/css" />
<link href="../includes/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
  <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script>  
</head>
<%--End The Head Section --%>

<%--Start The Body Section --%>
<!-- <body class="bodyGeneral" onLoad="initVars()" oncontextmenu="return false;"> -->
<body class="bodyGeneral" oncontextmenu="return false;">



<%--//START MAIN TABLE : Table for template Structure--%>

<table class="templateTableHelp" align="center" cellpadding="0" cellspacing="0" border="1">

<%--//START HEADER : Record for Header Background and Mirage Logo--%>
<tr class="headerBg">
    <td valign="top">
        <s:include value="/includes/template/Header.jsp"/>                    
    </td>
</tr>
<%--//END HEADER : Record for Header Background and Mirage Logo--%>

<%--//START DATA RECORD : Record for LeftMenu and Screen Content--%>
<tr>
<td>
    <table class="innerTableHelp" cellpadding="0" cellspacing="0" border="0" background="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/miragewatermark_850X600.gif">
    <tr>
        <!--START LEFT MENU -->
        <td width="150px" valign="top" align="center">
            <br><br><br><br><br><br><br><br>
            <div id="hi" style="color: #7E2217">
                <!--   Mirage Help -->
            </div>
            <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
            <div id="welcome" style="color: #7E2217">
                <!--   Mirage Help -->
            </div> 
            <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
            <div id="message" style="color: #7E2217">
                <!--   Mirage Help -->
            </div>
            
            
        </td>   
        <!--END LEFt MENU -->
        
        <!--//START DATA COLUMN : Coloumn for Screen Content-->
        <td width="700px" class="" valign="top">
        <!--Start data table -->    
        <table border="0" width="700px" align="center">
            <tr>
            <td valign="top">
                <table width="700px" align="center" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/helpHeader_700x150.jpg" width="707" height="150" /></td>
                </tr>
                <tr>
                    <td valign="top"><div id="CollapsiblePanel1" class="CollapsiblePanel">
                            <div class="CollapsiblePanelTab" tabindex="0">
                                <table width="700" border="0" cellspacing="0" cellpadding="6">
                                    <tr>
                                        <td width="30" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/helpQues_20x19.jpg" width="20" height="19" /></td>
                                        <td valign="top" class="fieldLabelContactHelpQ">1. How do I reset my password?</td>
                                    </tr>
                                </table>
                            </div>
                            <div class="CollapsiblePanelContent">
                                <table width="700" border="0" cellspacing="0" cellpadding="6">
                                    <tr>
                                        <td width="30" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/helpAns_20x19.jpg" width="20" height="19" /></td>
                                        <td width="642" valign="top" class="fieldLabelContactHelpA"><table border="0" cellpadding="0" cellspacing="6">
                                                <tr>
                                                    <td valign="top" class="fieldLabelContactHelpA">If you would like to change your MirageV2 password, just login in to your MirageV2
                                                        with your current password ,select a role what are the roles assigned to you in home,
                                                        click on continue button,You will be get default page.There you will get leftmenu, from that
                                                    menu select resetpassword button,click on that. Then: </td>
                                                </tr>
                                                <tr>
                                                    <td valign="top" class="fieldLabelContactHelpA">1.Enter old password in first box.<br />
                                                        2.Enter new password in second box<br />
                                                        3.Confirm your new password in third box<br />
                                                        4.Click on submit button.<br />
                                                    5.Automatically you will get message on login page.</td>
                                                </tr>
                                                <tr>
                                                    <td valign="top" class="fieldLabelContactHelpA"><font size="4px">Please Note:</font> To better protect your account, make sure that your password is memorable 
                                                        for you but difficult for others to guess. Never use the same password that you 
                                                        have used in the past, and do not share your password with anyone. For security
                                                        purposes, your new password must be a minimum of four characters and max eight characters long. A good 
                                                        password contains a combination of uppercase and lowercase letters (remember
                                                        that your password is case sensitive), numbers, and special characters such 
                                                    as +, ?, and * are not allowed. </td>
                                                </tr>
                                        </table></td>
                                    </tr>
                                </table>
                            </div>
                    </div></td>
                </tr>
                <tr>
                    <td valign="top"><div id="CollapsiblePanel2" class="CollapsiblePanel">
                            <div class="CollapsiblePanelTab" tabindex="0">
                                <table width="700" border="0" cellspacing="0" cellpadding="6">
                                    <tr>
                                        <td width="30" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/helpQues_20x19.jpg" width="20" height="19" /></td>
                                        <td valign="top" class="fieldLabelContactHelpQ">2. What if I forgot my password?</td>
                                    </tr>
                                </table>
                            </div>
                            <div class="CollapsiblePanelContent">
                                <table width="700" border="0" cellspacing="0" cellpadding="6">
                                    <tr>
                                        <td width="32" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/helpAns_20x19.jpg" width="20" height="19" /></td>
                                        <td valign="top" class="fieldLabelContactHelpA"><table border="0" cellspacing="6" cellpadding="0">
                                                <tr>
                                                    <td width="646" valign="top" class="fieldLabelContactHelpA">You can get both your password and loginId. Visit the login
                                                        page and click on forgot password button.You will get forgot Password page,there
                                                    you provide some basic verification information, such as:</td>
                                                </tr>
                                                <tr>
                                                    <td valign="top" class="fieldLabelContactHelpA">1.Select Preffered Question in first box.<br />
                                                        2.Enter preffered Answer in second box.<br />
                                                    3.Enter loginId in third Box</td>
                                                </tr>
                                                <tr>
                                                    <td valign="top" class="fieldLabelContactHelpA"><font size="4px">Please Note:</font>Make sure to provide the same information you gave during registration 
                                                        or when you last updated your account. Without the correct verification
                                                    information, you will not be able to obtain your password. </td>
                                                </tr>
                                        </table></td>
                                    </tr>
                                </table>
                            </div>
                    </div></td>
                </tr>
                <tr>
                    <td valign="top"><div id="CollapsiblePanel3" class="CollapsiblePanel">
                            <div class="CollapsiblePanelTab" tabindex="0">
                                <table width="700" border="0" cellspacing="0" cellpadding="6">
                                    <tr>
                                        <td width="30" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/helpQues_20x19.jpg" width="20" height="19" /></td>
                                        <td valign="top" class="fieldLabelContactHelpQ">3. How do I register into MirageV2?</td>
                                    </tr>
                                </table>
                            </div>
                            <div class="CollapsiblePanelContent">
                                <table width="700" border="0" cellspacing="0" cellpadding="6">
                                    <tr>
                                        <td width="32" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/helpAns_20x19.jpg" width="20" height="19" /></td>
                                        <td valign="top" class="fieldLabelContactHelpA"><table border="0" cellspacing="6" cellpadding="0">
                                                <tr>
                                                    <td width="646" valign="top" class="fieldLabelContactHelpA">If you would like to register in MirageV2,just visit login page,click on registration 
                                                        button.You will get self registration page.There you should provide some persional information
                                                    about your self such as:</td>
                                                </tr>
                                                <tr>
                                                    <td valign="top" class="fieldLabelContactHelpA">1.Enter your general details like firstname,lastname,middlename,date of birth,gender,
                                                        marital status and country in General details part.<br />
                                                        <br />
                                                        2.Enter Contact details like you phone number's,mobile number,office email and other
                                                        email in contact details part.<br />
                                                        <br />
                                                        3.To provide security for your account in MirageV2 please select prefQuestion from
                                                        security details and give your answer for that question.<br />
                                                        <br />
                                                        4.Finally submit those details by clicking register button  at top of the page.<br />
                                                        <br />
                                                        5.Automatically you will come to login page and you will get MirageV2 loginId and
                                                    password through your email.</td>
                                                </tr>
                                                <tr>
                                                    <td valign="top" class="fieldLabelContactHelpA"><font size="4px">Please Note:</font>After registration completed you will get auto generated password[combination of numbers
                                                        and characters],so you can change that password.To change your password you should provide
                                                        same prefQuestion and answer you gave during registration.So please try to remember those
                                                    question and answer.</td>
                                                </tr>
                                        </table></td>
                                    </tr>
                                </table>
                            </div>
                    </div></td>
                </tr>
                <tr>
                    <td valign="top"><div id="CollapsiblePanel4" class="CollapsiblePanel">
                            <div class="CollapsiblePanelTab" tabindex="0">
                                <table width="700" border="0" cellspacing="0" cellpadding="6">
                                    <tr>
                                        <td width="30" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/helpQues_20x19.jpg" width="20" height="19" /></td>
                                        <td valign="top" class="fieldLabelContactHelpQ">4. How can I view my Account details?</td>
                                    </tr>
                                </table>
                            </div>
                            <div class="CollapsiblePanelContent">
                                <table width="700" border="0" cellspacing="0" cellpadding="6">
                                    <tr>
                                        <td width="32" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/helpAns_20x19.jpg" width="20" height="19" /></td>
                                        <td valign="top" class="fieldLabelContactHelpA"><table border="0" cellspacing="6" cellpadding="0">
                                                <tr>
                                                    <td width="646" valign="top" class="fieldLabelContactHelpA">If you want to see the Account details after login into MirageV2 select a role in home page
                                                        click on continue button.you will get default page,in left menu have two buttons like All Accounts,
                                                        My Accounts.<br />
                                                        <br />
                                                        If you want to see all accounts means who are have an account in MirageV2,just click on All Accounts
                                                        Button,You will get all accounts.<br />
                                                        <br />
                                                        You want to edit any account, please click on edit button in perticular account field,you will get all
                                                        the fields about that account.Now you will make changes on what are the fields you want to change,after 
                                                        changes finished click on save button.<br />
                                                        <br />
                                                        If you want to see only your account means currently who are enter into MirageV2,just click on My Account
                                                        Button,you will get your account details.<br />
                                                        <br />
                                                        Suppose if you want to edit your account click on edit button in your account field,you will get all the fields
                                                    about your account,just make changes whatever you want changes and click on save button.</td>
                                                </tr>
                                                
                                        </table></td>
                                    </tr>
                                </table>
                            </div>
                    </div></td>
                </tr>
                <tr>
                    <td valign="top"><div id="CollapsiblePanel5" class="CollapsiblePanel">
                            <div class="CollapsiblePanelTab" tabindex="0">
                                <table width="700" border="0" cellspacing="0" cellpadding="6">
                                    <tr>
                                        <td width="30" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/helpQues_20x19.jpg" width="20" height="19" /></td>
                                        <td valign="top" class="fieldLabelContactHelpQ">5. How to search for Accounts?</td>
                                    </tr>
                                </table>
                            </div>
                            <div class="CollapsiblePanelContent">
                                <table width="700" border="0" cellspacing="0" cellpadding="6">
                                    <tr>
                                        <td width="32" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/helpAns_20x19.jpg" width="20" height="19" /></td>
                                        <td valign="top" class="fieldLabelContactHelpA"><table border="0" cellspacing="6" cellpadding="0">
                                                <tr>
                                                    <td width="646" valign="top" class="fieldLabelContactHelpA">If you want to search the Account details, after login into MirageV2 select a role in home page
                                                        click on continue button.you will get default page,in that page left menu have two buttons like All Accounts and
                                                        My Accounts buttons.Click on All Accounts Button ,you will get account details page.<br />
                                                        <br />
                                                        In that account details page there are two buttons like Account list and Account search buttons in the tabbed panel,
                                                        just click on Account search button you will get account search page, in that page have some fields like name,status,team member,industry,reagion
                                                        ,territory etc.<br />
                                                        <br />
                                                        You can search an account name wise,status wise,team member wise etc.What are the your requirements,according to that requirement
                                                    you can search account. </td>
                                                </tr>
                                                
                                        </table></td>
                                    </tr>
                                </table>
                            </div>
                    </div></td>
                </tr>
                <tr>
                    <td valign="top"><div id="CollapsiblePanel6" class="CollapsiblePanel">
                            <div class="CollapsiblePanelTab" tabindex="0">
                                <table width="700" border="0" cellspacing="0" cellpadding="6">
                                    <tr>
                                        <td width="30" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/helpQues_20x19.jpg" width="20" height="19" /></td>
                                        <td valign="top" class="fieldLabelContactHelpQ">6. Can i change my role?</td>
                                    </tr>
                                </table>
                            </div>
                            <div class="CollapsiblePanelContent">
                                <table width="700" border="0" cellspacing="0" cellpadding="6">
                                    <tr>
                                        <td width="32" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/helpAns_20x19.jpg" width="20" height="19" /></td>
                                        <td valign="top" class="fieldLabelContactHelpA"><table border="0" cellspacing="6" cellpadding="0">
                                                <tr>
                                                    <td width="646" valign="top" class="fieldLabelContactHelpA"><font size="4px">Yes,</font>You have to get an authorization notification / mail / letter from
                                                        your superior / higher official and it has to be forwarded to the administrator,who in turn will chage your role.<br />
                                                    You can reach the admin through &quot;mirage@miraclesoft.com&quot;. </td>
                                                </tr>
                                                
                                        </table></td>
                                    </tr>
                                </table>
                            </div>
                    </div></td>
                </tr>
                <tr>
                    <td valign="top"><div id="CollapsiblePanel7" class="CollapsiblePanel">
                    <div class="CollapsiblePanelTab" tabindex="0">
                        <table width="700" border="0" cellspacing="0" cellpadding="6">
                            <tr>
                                <td width="30" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/helpQues_20x19.jpg" width="20" height="19" /></td>
                                <td valign="top" class="fieldLabelContactHelpQ">7. How to attach or upload files?</td>
                            </tr>
                        </table>
                    </div>
                    <div class="CollapsiblePanelContent">
                    <table width="700" border="0" cellspacing="0" cellpadding="6">
                        <tr>
                        <td width="32" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/helpAns_20x19.jpg" width="20" height="19" /></td>
                        <td valign="top" class="fieldLabelContactHelpA"><table border="0" cellspacing="6" cellpadding="0">
                            <tr>
                                <td width="646" valign="top" class="fieldLabelContactHelpA">After login into MirageV2 select a role in home and click on continue button.You will get default page,
                                    in the default page from the grid select edit button,then you will get two tabbed pannels in a single page.<br />
                                    <br />
                                    In the lower tabbed pannel hvae nine buttons like contact,projects,greensheets,notes,etc.From these buttons select attachment
                                    button.You will get what are the attachment files already avilable.If you want to upload a file,select add button on the grid.
                                    <br><br>
                                    You will get  attachment page,in that page click on browse button, select required file and click on open button.Give the attachment
                                    file name in the second text box,finally click on upload button.Your file will be saved in database.
                                </font> </td>
                            </tr>
                            
                    </table></td>
                </tr>
            </table>
            </div>
        </div></td>
    </tr>
    <tr>
        <td valign="top"><div id="CollapsiblePanel8" class="CollapsiblePanel">
                <div class="CollapsiblePanelTab" tabindex="0">
                    <table width="700" border="0" cellspacing="0" cellpadding="6">
                        <tr>
                            <td width="30" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/helpQues_20x19.jpg" width="20" height="19" /></td>
                            <td valign="top" class="fieldLabelContactHelpQ">8. How to access/edit my Timesheets?</td>
                        </tr>
                    </table>
                </div>
                <div class="CollapsiblePanelContent">
                    <table width="700" border="0" cellspacing="0" cellpadding="6">
                        <tr>
                            <td width="32" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/helpAns_20x19.jpg" width="20" height="19" /></td>
                            <td valign="top" class="fieldLabelContactHelpA"><table border="0" cellspacing="6" cellpadding="0">
                                    <tr>
                                        <td width="646" valign="top" class="fieldLabelContactHelpA"></td>
                                    </tr>
                                    <tr>
                                        <td valign="top" class="fieldLabelContactHelpA">After login into MirageV2 ,Select a role and click on submit button,then you will get
                                            default page,in that page from the left menu select timesheet button,you will get TimeSeet page.
                                            <br><br>
                                            In that page you can perform add/edit operation according to your requirements.
                                        </td>    
                                    </tr>
                            </table></td>
                        </tr>
                    </table>
                </div>
        </div></td>
    </tr>
    <tr>
        <td valign="top"><div id="CollapsiblePanel9" class="CollapsiblePanel">
                <div class="CollapsiblePanelTab" tabindex="0">
                    <table width="700" border="0" cellspacing="0" cellpadding="6">
                        <tr>
                            <td width="30" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/helpQues_20x19.jpg" width="20" height="19" /></td>
                            <td valign="top" class="fieldLabelContactHelpQ">9. How to access/edit my GreenSheets?</td>
                        </tr>
                    </table>
                </div>
                <div class="CollapsiblePanelContent">
                    <table width="700" border="0" cellspacing="0" cellpadding="6">
                        <tr>
                            <td width="32" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/helpAns_20x19.jpg" width="20" height="19" /></td>
                            <td valign="top" class="fieldLabelContactHelpA"><table border="0" cellspacing="6" cellpadding="0">
                                    <tr>
                                        <td width="646" valign="top" class="fieldLabelContactHelpA"></td>
                                    </tr>
                                    <tr>
                                    <td valign="top" class="fieldLabelContactHelpA">After login into MirageV2 ,Select a role and click on submit button,then you will get
                                    default page,in that page from the left menu select Greensheet button,you will get GreenSheet page.
                                    <br><br>    
                                    In that page you can perform add/edit operation according to your requirements.(OR) Another way is....
                                    <br>
                                    From the default page select any edit button from the grid,then we will get accounts page,in this page we have two tabbed panels,from  the
                                    lower tabbed panel select greensheet button ,we get all detatils about greensheets.If we want to add any greensheet,just click on add button
                                    bottom side on the grid.
                                    <br><br>
                                    We will get GreenSheet page,fill all the fields in that page finally click on save button to save in data base.
                                    
                                    
                                    
                            </table></td>
                        </tr>
                    </table>
                </div>
        </div></td>
    </tr>
    <tr>
        <td valign="top"><div id="CollapsiblePanel10" class="CollapsiblePanel">
                <div class="CollapsiblePanelTab" tabindex="0">
                    <table width="700" border="0" cellspacing="0" cellpadding="6">
                        <tr>
                            <td width="30" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/helpQues_20x19.jpg" width="20" height="19" /></td>
                            <td valign="top" class="fieldLabelContactHelpQ">10. How to access/edit Opportunities?</td>
                        </tr>
                    </table>
                </div>
                <div class="CollapsiblePanelContent">
                    <table width="700" border="0" cellspacing="0" cellpadding="6">
                        <tr>
                            <td width="32" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/helpAns_20x19.jpg" width="20" height="19" /></td>
                            <td valign="top" class="fieldLabelContactHelpA"><table border="0" cellspacing="6" cellpadding="0">
                                    <tr>
                                        <td width="646" valign="top" class="fieldLabelContactHelpA"></td>
                                    </tr>
                                    <tr>
                                        <td valign="top" class="fieldLabelContactHelpA">After login into MirageV2 ,Select a role and click on submit button,then you will get
                                            default page,in that page from the left menu select Opportunities button,you will get Opportunities page.
                                            <br><br>    
                                            n that page you can perform add/edit operation according to your requirements.(OR) Another way is....
                                            <br><br>
                                            From the default page select any edit button from the grid,then we will get accounts page,in this page we have two tabbed panels,from  the
                                            lower tabbed panel select Opportunities button ,we get all detatils about Opportunities.If we want to add any Opportunities details,just click on add button
                                            bottom side on the grid.
                                            <br><br>
                                            We will get Opportunities page,fill all the fields in that page finally click on save button to save in data base. 
                                        </td>
                                    </tr>
                                    
                            </table></td>
                        </tr>
                    </table>
                </div>
        </div></td>
    </tr>
    <tr>
        <td valign="top"><div id="CollapsiblePanel11" class="CollapsiblePanel">
                <div class="CollapsiblePanelTab" tabindex="0">
                    <table width="700" border="0" cellspacing="0" cellpadding="6">
                        <tr>
                            <td width="30" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/helpQues_20x19.jpg" width="20" height="19" /></td>
                            <td valign="top" class="fieldLabelContactHelpQ">11. How to access/edit Invoices?</td>
                        </tr>
                    </table>
                </div>
                <div class="CollapsiblePanelContent">
                    <table width="700" border="0" cellspacing="0" cellpadding="6">
                        <tr>
                            <td width="32" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/helpAns_20x19.jpg" width="20" height="19" /></td>
                            <td valign="top" class="fieldLabelContactHelpA"><table border="0" cellspacing="6" cellpadding="0">
                                    <tr>
                                        <td width="646" valign="top" class="fieldLabelContactHelpA"></td>
                                    </tr>
                                    <tr>
                                        <td valign="top" class="fieldLabelContactHelpA">
                                            Under Construction.
                                        </td>
                                    </tr>
                            </table></td>
                        </tr>
                    </table>
                </div>
        </div></td>
    </tr>
    <tr>
        <td valign="top"><div id="CollapsiblePanel12" class="CollapsiblePanel">
                <div class="CollapsiblePanelTab" tabindex="0">
                    <table width="700" border="0" cellspacing="0" cellpadding="6">
                        <tr>
                            <td width="30" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/helpQues_20x19.jpg" width="20" height="19" /></td>
                            <td valign="top" class="fieldLabelContactHelpQ">12. How to access/edit Po's?</td>
                        </tr>
                    </table>
                </div>
                <div class="CollapsiblePanelContent">
                    <table width="700" border="0" cellspacing="0" cellpadding="6">
                        <tr>
                            <td width="32" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/helpAns_20x19.jpg" width="20" height="19" /></td>
                            <td valign="top" class="fieldLabelContactHelpA"><table border="0" cellspacing="6" cellpadding="0">
                                    <tr>
                                        <td width="646" valign="top" class="fieldLabelContactHelpA"></td>
                                    </tr>
                                    <tr>
                                        <td valign="top" class="fieldLabelContactHelpA">
                                            Under Construction...
                                        </td>
                                    </tr>
                                    
                            </table></td>
                        </tr>
                    </table>
                </div>
        </div></td>
    </tr>
    <tr>
        <td valign="top"><div id="CollapsiblePanel13" class="CollapsiblePanel">
                <div class="CollapsiblePanelTab" tabindex="0">
                    <table width="700" border="0" cellspacing="0" cellpadding="6">
                        <tr>
                            <td width="30" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/helpQues_20x19.jpg" width="20" height="19" /></td>
                            <td valign="top" class="fieldLabelContactHelpQ">13. How to access/edit Activities?</td>
                        </tr>
                    </table>
                </div>
                <div class="CollapsiblePanelContent">
                    <table width="700" border="0" cellspacing="0" cellpadding="6">
                        <tr>
                            <td width="32" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/helpAns_20x19.jpg" width="20" height="19" /></td>
                            <td valign="top" class="fieldLabelContactHelpA"><table border="0" cellspacing="6" cellpadding="0">
                                    <tr>
                                        <td width="646" valign="top" class="fieldLabelContactHelpA"></td>
                                    </tr>
                                    <tr>
                                        <td valign="top" class="fieldLabelContactHelpA">After login into MirageV2 ,Select a role and click on submit button,then you will get
                                            default page,in that page from the left menu select My Activities or Team Activities button according to your requirement,You will get
                                            Activities page.
                                            <br><br>    
                                            In that page you can perform add/edit operation according to your requirements.(OR) Another way is....
                                            <br>
                                            From the default page select any edit button from the grid,then we will get accounts page,in this page we have two tabbed panels,from  the
                                            lower tabbed panel select Activities button or AllActivities button depends on your requirement ,we get all detatils about Activities(or)AllActivities.
                                            If we want to add any Activities(or)AllActivities details,just click on add button,from bottom side on the grid.
                                            <br><br>
                                            We will get Activities (or)AllActivities page,fill all the fields in that page finally click on save button to save in data base.
                                        </td>
                                    </tr>
                                    
                            </table></td>
                        </tr>
                    </table>
                </div>
        </div></td>
    </tr>
    <tr>
        <td valign="top"><div id="CollapsiblePanel14" class="CollapsiblePanel">
                <div class="CollapsiblePanelTab" tabindex="0">
                    <table width="700" border="0" cellspacing="0" cellpadding="6">
                        <tr>
                            <td width="30" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/helpQues_20x19.jpg" width="20" height="19" /></td>
                            <td valign="top" class="fieldLabelContactHelpQ">14.How to view Reports?</td>
                        </tr>
                    </table>
                </div>
                <div class="CollapsiblePanelContent">
                    <table width="700" border="0" cellspacing="0" cellpadding="6">
                        <tr>
                            <td width="32" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/helpAns_20x19.jpg" width="20" height="19" /></td>
                            <td valign="top" class="fieldLabelContactHelpA"><table border="0" cellspacing="6" cellpadding="0">
                                    <tr>
                                        <td width="646" valign="top" class="fieldLabelContactHelpA"></td>
                                    </tr>
                                    <tr>
                                        <td valign="top" class="fieldLabelContactHelpA">
                                            MirageV2 providing reports about employee.Suppose if we want to access the reports very simple.
                                            Select role in home and click on continue button,we will get default page,in that page from the left menu select EmpReports button
                                            ,will get reports page.
                                            <br><br>
                                            Here we can view what are avilable reports  on the grid.Otherwise we can generate report by provideing some informationn in that page 
                                            Here we have some fields like current status,organisation and report type.Just fill those fields and click on GenereteReport button.
                                        </td>
                                    </tr>
                                    
                            </table></td>
                        </tr>
                    </table>
                </div>
        </div></td>
    </tr>
    <tr>
        <td valign="top"><div id="CollapsiblePanel15" class="CollapsiblePanel">
                <div class="CollapsiblePanelTab" tabindex="0">
                    <table width="700" border="0" cellspacing="0" cellpadding="6">
                        <tr>
                            <td width="30" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/helpQues_20x19.jpg" width="20" height="19" /></td>
                            <td valign="top" class="fieldLabelContactHelpQ">15. How to add/edit Contacts?</td>
                        </tr>
                    </table>
                </div>
                <div class="CollapsiblePanelContent">
                    <table width="700" border="0" cellspacing="0" cellpadding="6">
                        <tr>
                            <td width="32" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/helpAns_20x19.jpg" width="20" height="19" /></td>
                            <td valign="top" class="fieldLabelContactHelpA"><table border="0" cellspacing="6" cellpadding="0">
                                    <tr>
                                        <td width="646" valign="top" class="fieldLabelContactHelpA"></td>
                                    </tr>
                                    <tr>
                                        <td valign="top" class="fieldLabelContactHelpA">After login into MirageV2 ,Select a role and click on submit button,then you will get
                                            default page,in this page select edit button from the DBGrid table,we will get Accounts page,in this page we have two tabbed panels
                                            ,from the lower pannel we have some buttons like contact,Opportunities,greensheets etc...,
                                            <br><br>
                                            Suppose you want to add any contact select add button form booton side on the grid.Then will get contact page,in that page enter information
                                            in all fields and click on save button to save in data base.
                                            <br><br>
                                            Suppose you want to edit any contact,click on edit button form the DBGrid table.Will get Contact Edit page,in that page change information what you
                                            want to change the details of perticular employee.
                                            
                                        </td>
                                    </tr>
                                    
                            </table></td>
                        </tr>
                    </table>
                </div>
        </div></td>
    </tr>
    <tr>
        <td valign="top"><div id="CollapsiblePanel16" class="CollapsiblePanel">
                <div class="CollapsiblePanelTab" tabindex="0">
                    <table width="700" border="0" cellspacing="0" cellpadding="6">
                        <tr>
                            <td width="30" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/helpQues_20x19.jpg" width="20" height="19" /></td>
                            <td valign="top" class="fieldLabelContactHelpQ">16. How can i update my details?</td>
                        </tr>
                    </table>
                </div>
                <div class="CollapsiblePanelContent">
                    <table width="700" border="0" cellspacing="0" cellpadding="6">
                        <tr>
                            <td width="30" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/helpAns_20x19.jpg" width="20" height="19" /></td>
                            <td valign="top" class="fieldLabelContactHelpA"><table border="0" cellspacing="6" cellpadding="0">
                                    <tr>
                                        <td width="646" valign="top" class="fieldLabelContactHelpA"></td>
                                    </tr>
                                    <tr>
                                        <td valign="top" class="fieldLabelContactHelpA">
                                            Select a role in the Home page and click on Continue button ,we will get default page,in this page select EmpSearch button from
                                            the left menu .Will get employeeSearch page.Here Select required employee from the DBGrid table and click on edit buton on grid.
                                            <br><br>
                                            The we will get Employee update page ,in that page change your required fields and click on update button to save in data base.
                                            Otherwise search employee in search page depending on employee name (or) phone no (or)dept etc.Will get employee details in a Grid,then 
                                            click on edit button on Grid ,you get update page,in that page just change required fields depending on your requirement.
                                            Finally click on save button to save in database.
                                            
                                            
                                        </td>
                                    </tr>
                                    
                            </table></td>
                        </tr>
                    </table>
                </div>
        </div></td>
    </tr>
    <tr>
        <td valign="top"><div id="CollapsiblePanel17" class="CollapsiblePanel">
                <div class="CollapsiblePanelTab" tabindex="0">
                    <table width="700" border="0" cellspacing="0" cellpadding="6">
                        <tr>
                            <td width="30" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/helpQues_20x19.jpg" width="20" height="19" /></td>
                            <td valign="top" class="fieldLabelContactHelpQ">17. How to search an Employee?</td>
                        </tr>
                    </table>
                </div>
                <div class="CollapsiblePanelContent">
                    <table width="700" border="0" cellspacing="0" cellpadding="6">
                        <tr>
                            <td width="32" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/helpAns_20x19.jpg" width="20" height="19" /></td>
                            <td valign="top" class="fieldLabelContactHelpA"><table border="0" cellspacing="6" cellpadding="0">
                                    <tr>
                                        <td width="646" valign="top" class="fieldLabelContactHelpA"></td>
                                    </tr>
                                    <tr>
                                        <td valign="top" class="fieldLabelContactHelpA">In MirageV2 you have Employee Search option.Select role in Home page and click on continue button.
                                            You will get default page.In that page from leftmenu select EmpSearch button,then will get search page.
                                            <br><br>
                                            In Search page have some  fields like Name,phone No,Status,Dept and Organigation.Here we can search an employee depending on
                                            your known information.
                                            <font size="4px">Please Note:</font>In this page name means firstname or lastname or middlename any one of these you can enter.
                                        </td>
                                    </tr>
                                    
                            </table></td>
                        </tr>
                    </table>
                </div>
        </div></td>
    </tr>
    <tr>
        <td valign="top"><div id="CollapsiblePanel18" class="CollapsiblePanel">
                <div class="CollapsiblePanelTab" tabindex="0">
                    <table width="700" border="0" cellspacing="0" cellpadding="6">
                        <tr>
                            <td width="30" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/helpQues_20x19.jpg" width="20" height="19" /></td>
                            <td valign="top" class="fieldLabelContactHelpQ">18. How to add/update employee Addresses?</td>
                        </tr>
                    </table>
                </div>
                <div class="CollapsiblePanelContent">
                    <table width="700" border="0" cellspacing="0" cellpadding="6">
                        <tr>
                            <td width="32" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/helpAns_20x19.jpg" width="20" height="19" /></td>
                            <td valign="top" class="fieldLabelContactHelpA"><table border="0" cellspacing="6" cellpadding="0">
                                    <tr>
                                        <td width="646" valign="top" class="fieldLabelContactHelpA"></td>
                                    </tr>
                                    <tr>
                                        <td valign="top" class="fieldLabelContactHelpA">
                                            Select Role in Home page and click on continue button then you will get default page like employee search page.
                                            In this page select your perticular employee depending on your requirement from the DBGrid and click on edit button.
                                            <br><br>
                                            We will get employee details page ,here select otherdetails buttom from tabbed pannel,there will get four buttons,select addresses
                                            button from those buttons.
                                            <br><br>
                                            Here you can add addresses and also you can update already available addresses,finally click on add button to save in data base.
                                        </td>
                                    </tr>
                                    
                            </table></td>
                        </tr>
                    </table>
                </div>
        </div></td>
    </tr>
    <tr>
        <td valign="top"><div id="CollapsiblePanel19" class="CollapsiblePanel">
                <div class="CollapsiblePanelTab" tabindex="0">
                    <table width="700" border="0" cellspacing="0" cellpadding="6">
                        <tr>
                            <td width="30" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/helpQues_20x19.jpg" width="20" height="19" /></td>
                            <td valign="top" class="fieldLabelContactHelpQ">19. How to add/update my immigration details?</td>
                        </tr>
                    </table>
                </div>
                <div class="CollapsiblePanelContent">
                    <table width="700" border="0" cellspacing="0" cellpadding="6">
                        <tr>
                            <td width="32" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/helpAns_20x19.jpg" width="20" height="19" /></td>
                            <td valign="top" class="fieldLabelContactHelpA"><table border="0" cellspacing="6" cellpadding="0">
                                    <tr>
                                        <td width="646" valign="top" class="fieldLabelContactHelpA"></td>
                                    </tr>
                                    <tr>
                                        <td valign="top" class="fieldLabelContactHelpA">
                                            Select Role in Home page and click on continue button then you will get default page like employee search page.
                                            In this page select your perticular employee depending on your requirement from the DBGrid and click on edit button.
                                            <br><br>
                                            We will get employee details page ,here select otherdetails buttom from tabbed pannel,there will get four buttons,select immigration
                                            button from those buttons.
                                            <br><br>
                                            Here you can add immigration details and also you can update already available immigration details,finally click on add button to save in data base.
                                        </td>
                                    </tr>
                                    
                            </table></td>
                        </tr>
                    </table>
                </div>
        </div></td>
    </tr>
    <tr>
        <td valign="top"><div id="CollapsiblePanel20" class="CollapsiblePanel">
                <div class="CollapsiblePanelTab" tabindex="0">
                    <table width="700" border="0" cellspacing="0" cellpadding="6">
                        <tr>
                            <td width="30" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/helpQues_20x19.jpg" width="20" height="19" /></td>
                            <td valign="top" class="fieldLabelContactHelpQ">20. How to add/update Insurance?</td>
                        </tr>
                    </table>
                </div>
                <div class="CollapsiblePanelContent">
                    <table width="700" border="0" cellspacing="0" cellpadding="6">
                        <tr>
                            <td width="32" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/helpAns_20x19.jpg" width="20" height="19" /></td>
                            <td valign="top" class="fieldLabelContactHelpA"><table border="0" cellspacing="6" cellpadding="0">
                                    <tr>
                                        <td width="646" valign="top" class="fieldLabelContactHelpA"></td>
                                    </tr>
                                    <tr>
                                        <td valign="top" class="fieldLabelContactHelpA">
                                            Select Role in Home page and click on continue button then you will get default page like employee search page.
                                            In this page select your perticular employee depending on your requirement from the DBGrid and click on edit button.
                                            <br><br>
                                            We will get employee details page ,here select otherdetails buttom from tabbed pannel,there will get four buttons,select Insurance
                                            button from those buttons.
                                            <br><br>
                                            Here you can add Insurance details and also you can update already available Insurance details,finally click on add button to save in data base.
                                        </td>
                                    </tr>
                                    
                            </table></td>
                        </tr>
                    </table>
                </div>
        </div></td>
    </tr>
    <tr>
        <td valign="top"><div id="CollapsiblePanel21" class="CollapsiblePanel">
                <div class="CollapsiblePanelTab" tabindex="0">
                    <table width="700" border="0" cellspacing="0" cellpadding="6">
                        <tr>
                            <td width="30" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/helpQues_20x19.jpg" width="20" height="19" /></td>
                            <td valign="top" class="fieldLabelContactHelpQ">21. How to add/update Ancillary?</td>
                        </tr>
                    </table>
                </div>
                <div class="CollapsiblePanelContent">
                    <table width="700" border="0" cellspacing="0" cellpadding="6">
                        <tr>
                            <td width="32" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/helpAns_20x19.jpg" width="20" height="19" /></td>
                            <td valign="top" class="fieldLabelContactHelpA"><table border="0" cellspacing="6" cellpadding="0">
                                    <tr>
                                        <td width="646" valign="top" class="fieldLabelContactHelpA"></td>
                                    </tr>
                                    <tr>
                                        <td valign="top" class="fieldLabelContactHelpA">
                                            Select Role in Home page and click on continue button then you will get default page like employee search page.
                                            In this page select your perticular employee depending on your requirement from the DBGrid and click on edit button.
                                            <br><br>
                                            We will get employee details page ,here select otherdetails buttom from tabbed pannel,there will get four buttons,select Ancillary
                                            button from those buttons.
                                            <br><br>
                                            Here you can add Ancillary details and also you can update already available Ancillary details,finally click on add button to save in data base.
                                        </td>
                                    </tr>
                                    
                            </table></td>
                        </tr>
                    </table>
                </div>
        </div></td>
    </tr>
    <%--IF you want to add extra rows you enable following rows --%>
    <%-- <tr>
        <td valign="top">&nbsp;</td>
    </tr>
    <tr>
        <td valign="top">&nbsp;</td>
    </tr>
    <tr>
        <td valign="top">&nbsp;</td>
    </tr>--%>
</table></td>
</tr>
</table>   
<!--End data table -->
</td> 
<!--//END DATA COLUMN : Coloumn for Screen Content-->

<!--START RIGHT MENU -->
<td width="150px" align="center" valign="top" background="">
    <br><br><br><br><br><br><br><br>
    <div id="msg2" style="color: #7E2217">
        <!--   Mirage Help -->
    </div>
    <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
    <div id="msg3" style="color: #7E2217">
        <!--   Mirage Help -->
    </div>
    <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
    <div id="msg4" style="color: #7E2217">
        <!--   Mirage Help -->
    </div>
</td>   
<!--END RIGHT MENU -->
</tr>
</table>
</td>

</tr>
<%--//END DATA RECORD : Record for LeftMenu and Screen Content--%>

<%--//START FOOTER : Record for Footer Background and Content--%>
<tr class="footerBg">
    <td align="center"><s:include value="/includes/template/Footer.jsp"/></td>
</tr>
<%--//END FOOTER : Record for Footer Background and Content--%>

</table> 
<%--//End MAIN TABLE : Table for template Structure--%>
<script type="text/javascript">
<!--
var CollapsiblePanel1 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel1", {contentIsOpen:false});
var CollapsiblePanel2 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel2", {contentIsOpen:false});
var CollapsiblePanel3 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel3", {contentIsOpen:false});
var CollapsiblePanel4 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel4", {contentIsOpen:false});
var CollapsiblePanel5 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel5", {contentIsOpen:false});
var CollapsiblePanel6 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel6", {contentIsOpen:false});
var CollapsiblePanel7 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel7", {contentIsOpen:false});
var CollapsiblePanel8 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel8", {contentIsOpen:false});
var CollapsiblePanel9 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel9", {contentIsOpen:false});
var CollapsiblePanel10 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel10", {contentIsOpen:false});
var CollapsiblePanel11 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel11", {contentIsOpen:false});
var CollapsiblePanel12 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel12", {contentIsOpen:false});
var CollapsiblePanel13 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel13", {contentIsOpen:false});
var CollapsiblePanel14 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel14", {contentIsOpen:false});
var CollapsiblePanel15 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel15", {contentIsOpen:false});
var CollapsiblePanel16 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel16", {contentIsOpen:false});
var CollapsiblePanel17 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel17", {contentIsOpen:false});
var CollapsiblePanel18 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel18", {contentIsOpen:false});
var CollapsiblePanel19 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel19", {contentIsOpen:false});
var CollapsiblePanel20 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel20", {contentIsOpen:false});
var CollapsiblePanel21 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel21", {contentIsOpen:false});
//-->
</script>        
<script type="text/javascript">
		$(window).load(function(){
		initVars();

		});
		</script>
</body>
<%--End The Body Section --%>

</html>
<%--End The Page --%>