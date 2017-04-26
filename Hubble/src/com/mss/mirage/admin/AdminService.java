/*
 * @(#)AdminService.java	1.0 04/11/2007
 *
 * Copyright 2004 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */


package com.mss.mirage.admin;
import com.mss.mirage.util.ServiceLocatorException;
import java.util.*;

/**
 * 
 * @author Arjun Sanapathi
 * @version 1.122, 05/05/04
 * @since 1.0
 */
public interface AdminService {
    
    /**
     * 
     * This method is useful to get The Employee Details
     * @return The AdminRolesVTO returned  depends on the employeeId.
     * @see com.mss.mirage.util.ServiceLocatorException.
     * @param employeeId 
     * @throws ServiceLocatorException If a ServiceLocatorException exists and its <code>{@link
     *          com.mss.mirage.util.ServiceLocatorException}</code>
     */
    
    public AdminRolesVTO employeeDetails(int employeeId) throws ServiceLocatorException;
    
    
    /**
     * 
     * This method is useful to Transfer Roles to user
     * @param roles 
     * @param employeeId 
     * @param primaryRoles 
     * @return integer value of insertedRoles in the database.
     * @see com.mss.mirage.util.ServiceLocatorException.
     * @throws ServiceLocatorException If a ServiceLocatorException exists and its <code>{@link
     *            com.mss.mirage.util.ServiceLocatorException}</code>
     */
    
    public  int  insertRoles(String[] roles,int employeeId,int primaryRoles) throws ServiceLocatorException;
    
    /**
     * 
     * 
     * This method is useful to get Role Name
     * @return The AdminRolesVTO returned  depends on the roleId.
     * @see com.mss.mirage.util.ServiceLocatorException.
     * @param roleId 
     * @throws ServiceLocatorException If a ServiceLocatorException exists and its <code>{@link
     *          com.mss.mirage.util.ServiceLocatorException}</code>
     */
    
    public AdminRolesVTO getRoleName(int roleId) throws ServiceLocatorException;
    
    /**
     * 
     * 
     * 
     * @return integer value of insertRoleScreens in the database.
     * @see com.mss.mirage.util.ServiceLocatorException.
     * @param screens 
     * @param roleId 
     * @throws ServiceLocatorException If a ServiceLocatorException exists and its <code>{@link
     *            com.mss.mirage.util.ServiceLocatorException}</code>
     */
    public int insertRoleScreens(String[] screens,int roleId) throws ServiceLocatorException;
    
    /**
     * 
     * 
     * 
     * @return integer value of insertNewScreen in the database.
     * @see com.mss.mirage.util.ServiceLocatorException.
     * @param moduleId 
     * @param screenName 
     * @param screenAction 
     * @param screenTitle 
     * @throws ServiceLocatorException If a ServiceLocatorException exists and its <code>{@link
     *            com.mss.mirage.util.ServiceLocatorException}</code>
     */
    
    public int insertNewScreen(String moduleId,String screenName,String screenAction,String screenTitle) throws ServiceLocatorException;
    
    /**
     * This method is useful to Update the Password of User
     * @param passwordAction 
     * @return integer value of updatePassword in the database.
     * @throws com.mss.mirage.util.ServiceLocatorException 
     */
    public int updatePassword(AdminAction passwordAction) throws ServiceLocatorException;
    public int updateCustPassword(AdminAction passwordAction) throws ServiceLocatorException;
    public boolean checkSalesAgainstBdm(AdminAction adminAction) throws ServiceLocatorException;
}
