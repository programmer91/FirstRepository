/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  October 23, 2007, 5:40 PM
 *
 * Author  : MrutyumjayaRao Chennu<mchennu@miraclesoft.com>
 *
 * File    : AccountWrapper.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.crm.accounts;

import org.displaytag.decorator.TableDecorator;

/**
 *
 * @author MrutyumjayaRao Chennu<mchennu@miraclesoft.com>
 *
 * This Class.... ENTER THE DESCRIPTION HERE
 */
public class AccountWrapper extends TableDecorator {
    
    /** Creates a new instance of AccountWrapper */
    public AccountWrapper() {
        super();
    }
    
    /**
     * Test method which always returns a null value.
     * @return <code>null</code>
     */
    public String getNullValue() {
        return null;
    }
    
    /**
     * Returns the AccountVTO's ID as a hyperlink that the person can click on
     * @return String
     */
    public String getNameLink(){
        AccountVTO object = (AccountVTO) getCurrentRowObject();
        
        return "<a href=\"getAccount.action?id=" //$NON-NLS-1$
                + object.getId()
                + "\">" //$NON-NLS-1$
                + object.getAccountName()
                + "</a>"; //$NON-NLS-1$
    }
    
}
