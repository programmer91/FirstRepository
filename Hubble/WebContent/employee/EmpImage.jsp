<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@ page import="com.mss.mirage.util.ApplicationConstants" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.employee.profile.EmpProfileAction" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%--<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee Image</title>
    </head>
    <body>
        
        <%! 
        Connection connection = null;
        Statement statement = null;
        %>
        
        <%         
        
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select Image from tblEmployee where LoginId='"+session.getAttribute(ApplicationConstants.SESSION_USER_ID)+"'");
            rs.next();
            byte[] image = rs.getBytes("Image");
            response.setContentType("text/html");
            if(image!=null){
                response.getOutputStream().write(image);
                
                //Closing output Stream it's high priority
                response.getOutputStream().close();
            } 
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            try{
                if(statement!=null){
                    statement.close();
                    statement=null;
                }
                if(connection!=null){
                    connection.close();
                    connection=null;
                }
            }catch(SQLException sql){
                sql.printStackTrace();
            }
        }
        %>        
        
        
        
    </body>
</html>
