/*
 * FieldTag.java
 *
 * Created on March 31, 2007, 11:53 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.tags;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.sql.*;
/**
 *
 * @author raja reddy andra
 */
public class FieldTag extends BodyTagSupport
{
    private String name;
    
   public int doStartTag() throws JspException 
    {
       SelectTableTag tg=(SelectTableTag)getParent(); 
       tg.str1 += name;    
       
       return SKIP_BODY;
       }// doStartTag()

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    
    
      
    

    }
   


