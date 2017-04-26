/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :   April 1, 2008, 3:42 PM
 *
 * Author  :  Sagar Bandaru <sjanana@miraclesoft.com>
 *
 * File    : PartLoginAction .java
 *
 * Copyright 2008 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.partner.general;

import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.PasswordUtility;
import com.mss.mirage.util.ServiceLocatorException;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import java.util.Iterator;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author miracle
 */

    
    /*
 * CustLoginAction.java
 *
 * Created on March 13, 2008, 10:27 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */



/**
 *
 * @author miracle
 */
public class PartLoginAction extends ActionSupport implements ServletRequestAware {
    
    private String resultType;
    
    private String partnerId;
   
    
    private String  partnerPwd;
    
    private PasswordUtility passwordUtility;
    
    private HttpServletRequest httpServletRequest;
    private HttpSession httpSession;
    /** Creates a new instance of CustLoginAction */
   
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }
   
    public String execute() throws Exception {
        String resultType="";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String dbLoginId="";
        String dbPassword = "";
        String dbAccountId= "";
        String dbCustomerName="";
        String dbCustId="";
        passwordUtility = new PasswordUtility();
        String SQL_QUERY="SELECT Id ,LoginId,AccountId,Password,LastName,FirstName,MiddleName from tblCrmContact where LoginId ='"+getPartnerId().toLowerCase().trim()+"'";
        
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_QUERY);
            HttpSession session = httpServletRequest.getSession(true);
            
            
            while(resultSet.next()) {
                dbCustId=resultSet.getString("Id");
                dbPassword= resultSet.getString("Password");
                dbLoginId =resultSet.getString("LoginId");
                dbAccountId=resultSet.getString("AccountId");
                dbCustomerName = resultSet.getString("FirstName")+" "+resultSet.getString("MiddleName")+"."+resultSet.getString("LastName");
            }
            String decPassword = passwordUtility.decryptPwd(dbPassword);
            if(decPassword.equalsIgnoreCase(getPartnerPwd().trim())) {
               
                session.setAttribute(ApplicationConstants.SESSION_CUST_ACC_ID,dbAccountId);
                session.setAttribute(ApplicationConstants.SESSION_CUST_ID,dbCustId);
                session.setAttribute(ApplicationConstants.SESSION_CUST_NAME,dbCustomerName);        
                session.setAttribute(ApplicationConstants.SESSION_CUST_USER_ID,dbLoginId);
                resultType=SUCCESS;
            } else {
                httpServletRequest.setAttribute("partErrorMessage","<font color=\"red\" size=\"1.5\">Please Login with valid UserId and Password! </font>");
                resultType = INPUT;
            }
            
        } catch(Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("partErrorMessage",ex.toString());
            resultType =  ERROR;
        }finally{
            try{
                if(resultSet!=null){
                   resultSet.close();
                    resultSet = null;
                }
                if(statement!=null){
                   statement.close();
                    statement = null;
                }
                if(connection!=null){
                    connection.close();
                    connection = null;
                }
                
            }catch(SQLException sqle){
                throw new ServiceLocatorException(sqle);
            }
        }
        return resultType;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPartnerPwd() {
        return partnerPwd;
    }

    public void setPartnerPwd(String partnerPwd) {
        this.partnerPwd = partnerPwd;
    }
}

    

