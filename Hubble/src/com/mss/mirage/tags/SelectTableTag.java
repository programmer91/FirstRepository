/*
 * SelectTableTag.java
 *
 * Created on March 31, 2007, 11:18 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.tags;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.sql.*;
import javax.sql.DataSource;
/**
 *
 * @author raja reddy andra
 */
public class SelectTableTag extends BodyTagSupport {
    
    private String table;
    Connection con;
    Statement stm;
    ResultSet rs;
    Statement stm1;
    ResultSet rs1;
    String str1,str2;
    private String id;
    public int doStartTag() throws JspException {
        
        str1="SELECT primaryflag, ";
        str2=" FROM "+table+ " where EmpId=" + Integer.parseInt(id) ;
        
        return EVAL_BODY_INCLUDE;
    }
    
    
    public int doAfterBody() throws JspException {
        
        try {
            con=(Connection)pageContext.getAttribute("connection",pageContext.APPLICATION_SCOPE);
            int iprimaryflag=0;
            // System.out.println("The connection Object in  in Select Tag"+con);
            stm=con.createStatement();
            String query=str1+str2;
            
            //System.out.println("The id is ***"+ query);
            rs=stm.executeQuery(query);
            // System.out.print("Query"+query);
            JspWriter out=pageContext.getOut();
            
            out.write("<select name=\"roleTypeId\">");
            // out.println(" <option value=\"-1\">Select Role</option>");
            while(rs.next()) {
                String roleId = rs.getString("roleId");
                String primaryflag = rs.getString("primaryflag");
                iprimaryflag =Integer.parseInt(primaryflag);
                if(roleId.equalsIgnoreCase("") || "".equalsIgnoreCase(roleId) || roleId.equalsIgnoreCase(null)) {
                    roleId="0";
                }
                
                String query1="select *  from tblLKRoles where Id ="+Integer.parseInt(roleId);
                //System.out.print("Query1"+query1);
                stm1=con.createStatement();
                rs1 = stm1.executeQuery(query1);
                
                while(rs1.next()) {
                    
                    if(iprimaryflag==1) {
                        out.println(" <option value=\""+rs1.getString("Id")+  "\" selected>" +rs1.getString("Description")+"</option>");
                    } else {
                        out.println(" <option value=\""+rs1.getString("Id")+  "\">" +rs1.getString("Description")+"</option>");
                    }
                }
                rs1.close();
                stm1.close();
            }
            out.println("</select>");
          
        } catch(Exception e) {
            throw new JspException(e);
        }finally {
            try{
                if(con!=null){
                    con.close();
                    con = null;
                }
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
        
        return SKIP_BODY;
    }// doAfterTag()
    
    
    
    public String getTable() {
        return table;
    }
    
    public void setTable(String table) {
        this.table = table;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    
    
    
    
}
