<%--/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :   November 27, 2008 7.27PM
 *
 * Author  :  Hari Krishna Kondala <hkondala@miraclesoft.com>
 *
 * File    : ResumeSearchHelp.jsp
 *
 * Copyright 2008 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
 --%>

<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hubble Organization Portal :: Resume Search Help</title>
        
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        
        <s:include value="/includes/template/headerScript.html"/>
    </head>
    <body class="bodyGeneral" oncontextmenu="return false;">
        <br>
        <table cellpadding="0" width="70%" align="center" cellspacing="0" bordercolor="#efefef" class="cellBorder">  
            
            <tr class="headerText">
                <td colspan="5" align="center"> 
                    Search Techniques
                </td>
            </tr>
            <tr>
                <td colspan="5">
                    <table align="center" cellpadding="0" cellspacing="0"  width="98%">
                        <tr class="fieldLabel">
                            <td align="left">
                                <b>1. Single Keyword Search:</b><br>
                                By this, We can search by a single complete Keyword.<br>
                                <font color="red">Ex:</font> Java<br>
                                <br>
                                <b>2. Keyword1+Keyword2 (or) Keyword1,Keyword2</b><br>
                                By this search we'll get resumes which are consist both Keywords 1 & 2.<br>
                                <font color="red">Ex:</font> Java+Banking<br><br>
                                <b>3. Keyword1+Keyword2+Keyword3 (or) Keyword1,Keyword2,Keyword3</b><br>
                                By this search we'll get resumes which are consist all Keywords 1,2 and 3.<br>
                                <font color="red">Ex:</font> Java+Banking+Swings<br><br>
                                <b>4. Keyword1 Keyword2 (SPACE)</b><br>
                                By this search we'll get resumes which are consist either Keyword1 or Keyword2.<br>
                                <font color="red">Ex:</font> Java Banking<br><br>
                                <b>5. Complete Sentence</b><br>
                                By this search we'll get resumes which are consist whole sentence.<br>
                                <font color="red">Ex:</font> "test@gmail.com" (or) "7 years of working experience"<br><br>
                                <b>6. Boolean Operator</b><br>
                                AND: If you want to search for documents that contain more than one word, use the AND operator. <br>
                                <font color="red">Ex:</font> "Java AND Lucene" returns all documents that contain both "Java" and "Lucene."<br><br>
                                <font color="red">Note*:</font> For the uploaded Resume, The activation period will be 24 Hrs. <br><br>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </body>
</html>
