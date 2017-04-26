/*
 * PayRollStubAction.java
 *
 * Created on September 27, 2012, 1:48 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.employee.payroll;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opensymphony.xwork2.ActionSupport;
import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

/**
 *
 * @author miracle
 */
public class PayRollStubAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    

    
    public String getpayrollstub() throws FileNotFoundException,ServletException{
      return SUCCESS;
        
    }

    public void setServletRequest(HttpServletRequest httpServletRequest) {
    }

    public void setServletResponse(HttpServletResponse httpServletResponse) {
    }
}

