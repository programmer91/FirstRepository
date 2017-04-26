/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  September 29, 2007, 7:51 PM
 *
 * Author  : Arjun Sanapathi<asanapathi@miraclesoft.com>
 *
 * File    : LeaveService.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.employee.leaverequest;
import com.mss.mirage.util.ServiceLocatorException;
import java.sql.SQLException;
/**
 *
 * @author miracle
 */
public interface LeaveService {
    
  public  int addOrUpdateLeave(LeaveAction leaveaction,String userId,String userName,int empId,String mode) ;  
  /**
     *    
     *   
     *   @param   Taking userId,leaveActionObject,username,mode
     *    
     *   @throws  ServiceLocatorException
     *          If a ServiceLocatorException exists and its <code>{@link
     *          com.mss.mirage.util.ServiceLocatorException}</code>
	     @see com.mss.mirage.util.ServiceLocatorException.
     */
  
  public String[] splitValues(String reportsTo) throws ServiceLocatorException, SQLException;
  
  
  /**
     *    
     *   
     *   @param   reportsTo from the leaverequest.jsp
     *    
     *   @throws  ServiceLocatorException
     *          If a ServiceLocatorException exists and its <code>{@link
     *          com.mss.mirage.util.ServiceLocatorException}</code>
	     @see com.mss.mirage.util.ServiceLocatorException.
     */
  
  
  
 public LeaveVTO getLeave(int leaveId) throws ServiceLocatorException;
  
  /**
     *    
     *   
     *   @param   LeaveId,leaveActionObject,username,mode
     *    
     *   @throws  ServiceLocatorException
     *          If a ServiceLocatorException exists and its <code>{@link
     *          com.mss.mirage.util.ServiceLocatorException}</code>
	     @see com.mss.mirage.util.ServiceLocatorException.
     */
  
    
}




