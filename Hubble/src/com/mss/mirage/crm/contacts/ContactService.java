/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  September 29, 2007, 7:51 PM
 *
 * Author  : Jyothi Nimmagadda<jnimmagadda@miraclesoft.com>
 *
 * File    : ContactService.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.crm.contacts;

import com.mss.mirage.util.ServiceLocatorException;
//import java.sql.ResultSet;

/**
 *
 * @author Jyothi Nimmagadda<jnimmagadda@miraclesoft.com>
 *
 * This Class.... ENTER THE DESCRIPTION HERE
 */
public interface ContactService {
    
	/**
 * The <code>addorUpdateContact</code>  class is used for getting new Contact details from
 * <i>ContactAdd.jsp,ContactDetails.jsp</i> page.
 * <p>
 * Then it invokes setter methods in <code>doAdd,doEdit</code> class and invokes update() method
 * in <code>doAdd,doEdit</code> for inserting contact details in mirage.tblCrmContact table.
 *
 * @author Jyothi.Nimmagadda <a href="mailto:jnimmagadda@miraclesoft.com">jnimmagadda@miraclesoft.com</a>
 *
 * @version 1.0 November 01, 2007
 *
 * @see com.mss.mirage.crm.contacts.ContactAction
 * @see com.mss.mirage.crm.contacts.ContactService
 * @see com.mss.mirage.crm.contacts.ContactServiceImplementation
   @see com.mss.mirage.crm.contacts.ContactVTO */ 
    public int addOrUpdateContact(ContactAction contactPojo) throws ServiceLocatorException;

	/**
     *    
     *   
     *   @param   Taking contactId .
     *   @return   The ContactVTO returned  depends on the roleId.
     *   @throws  ServiceLocatorException
     *          If a ServiceLocatorException exists and its <code>{@link
     *          com.mss.mirage.util.ServiceLocatorException}</code>
	     @see com.mss.mirage.util.ServiceLocatorException.
     */
    public ContactVTO getContact(int contactId) throws ServiceLocatorException;
    
	/**
	   The method ContactVTO getContactVTO(ContactAction contactPojo) is used for getting ContactVTO object
	   which contains accessors and mutators methods
	   @return ContactVTO object 
	{@link com.mss.mirage.crm.contacts.ContactVTO}*/
	public ContactVTO getContactVTO(ContactAction contactPojo) throws ServiceLocatorException;

	 /**
     *    
     *   
     *   @param   Taking accountId as int .
     *   @return   The ContactVTO returned  depends on the accountId.
     *   @throws  ServiceLocatorException
     *          If a ServiceLocatorException exists and its <code>{@link
     *          com.mss.mirage.util.ServiceLocatorException}</code>
	     @see com.mss.mirage.util.ServiceLocatorException.
		 @ com.mss.mirage.crm.ContactService
		 {@link
     *          com.mss.mirage.crm.ContactService
     */
    public ContactVTO getOfficeAddress(int accountId) throws ServiceLocatorException;
    //New
   //public int getLastcontact()throws ServiceLocatorException;
}
