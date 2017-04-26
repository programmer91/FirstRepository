/*
 * ConnTag.java
 *
 * Created on March 31, 2007, 11:05 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.tags;

import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.ServiceLocatorException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.jsp.tagext.*;
import java.sql.*;
import javax.servlet.jsp.*;
import javax.sql.DataSource;
/**
 *
 * @author raja reddy andra
 */
public class ConnTag extends TagSupport
{
   Connection connection;
            

    public int doStartTag() throws JspException
    {
         try
        {
            
             connection = ConnectionProvider.getInstance().getConnection();
             
             if(connection != null){
             pageContext.setAttribute("connection",connection,pageContext.APPLICATION_SCOPE);
             }
            
        }
        catch(Exception e)
       {
            throw new JspException(e);
        }finally{
            try{
               
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException se){
                //throw new ServiceLocatorException(se);
                se.printStackTrace();
            }
        }
         
      return EVAL_BODY_INCLUDE; 
       }// doStartTag()
   

    public int doAfterBody() throws JspException
    {
     
        return SKIP_BODY;
    }
    
    public int doEndTag() throws JspException
   {
        return EVAL_PAGE;
   }

//    private DataSource getJndiSqlDS() throws NamingException {
//        Context c = new InitialContext();
//        return (DataSource) c.lookup("java:comp/env/jndi/mirage");
//    }
    }
   

