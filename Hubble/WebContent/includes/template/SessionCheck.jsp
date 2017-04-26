<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%
if(session.getAttribute(ApplicationConstants.SESSION_USER_ID) == null){
    response.sendRedirect("/general/login.action");
}
%>