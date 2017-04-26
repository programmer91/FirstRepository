<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%--<script type="text/JavaScript" src="<s:url value="/includes/javascripts/openwindow.js"/>"></script>--%>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/javascript">
            function win_open(url){
            var values=document.getElementById('mailid').innerHTML; 
            url = url+"?name="+values;
            newWindow=window.open(url,'name','height=445,width=709');
            if (window.focus) {newWindow.focus();}
            return false;
        }
        </script>
        
    </head>
    <body>
        <form name="test">
            <%-- <input type="button" value="ClickHere" onclick="return win_open('MailWindow.jsp');">--%>
            <a id="mailid" href="return win_open('MailWindow.jsp');" onclick="return win_open('MailWindow.jsp');">randra@miraclesoft.com</a>        
        </form>
        
        
    </body>
</html>
