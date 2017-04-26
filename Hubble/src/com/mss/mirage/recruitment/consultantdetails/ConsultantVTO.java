/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :com.mss.mirage.recruitment.consultantdetails
 *
 * Date    :   Created on October 31, 2007, 10:14 PM
 *
 * Author  : Kondaiah Adapa<kadapa@miraclesoft.com>
 *
 * File    : ConsultantVTO.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
package com.mss.mirage.recruitment.consultantdetails;

/**
 * The <code>ConsultantVTO </code>  class is used for getting  Consultant details from
 * <i>ConsultantAdd.jsp</i>    
 * <p>
 *  
 *
 * @author  Kondaiah Adapa<a href="mailto:kadapa@miraclesoft.com">kadapa@miraclesoft.com</a>
 *
 *  
   @version 1.122, 05/05/04
   @since   1.0
  */
public class ConsultantVTO {
    
    /** Creates a new instance of ConsultantVTO */
    public ConsultantVTO() {
        }
    
/** The firstName is Useful to get the first name of the consultant. */
    private String firstName;
    
/** The lastName is Useful to get the last name of the consultant. */    
    private String lastName;
    
/** The email is Useful to get the office E-mail of the consultant. */      
    private String email;
    
/** The middleName is Useful to get the middle of the consultant. */      
    private String middleName;
    
/** The phoneNo is Useful to get the phoneNo of the consultant. */      
    private String phoneNo;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
