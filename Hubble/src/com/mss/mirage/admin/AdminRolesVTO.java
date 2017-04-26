/*
 * @(#)AdminRolesVTO.java	1.0 04/11/2007
 *
 * Copyright 2004 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */


package com.mss.mirage.admin;

/**
 * The <code>AdminRolesVTO </code>  class is used for getting  Employee Login details,Roles,Screen details from
 * <i>AssignRoles.jsp</i>    
 * <p>
 *  
 *
 * @author  Arjuna Rao.Sanapathi<a href="mailto:asanapathi@miraclesoft.com">asanapathi@miraclesoft.com</a>
 *


 *  
   @version 1.122, 05/05/04
   @since   1.0
  */
public class AdminRolesVTO {
    
    /** Creates a new instance of AdminRolesVTO */

	 /**
     * The userName   is Useful to  get the userName
	    
     */
    
    private String userName;
   /**
     * The loginId   is Useful to  get the loginId
	    
     */

    private String loginId;

	/**
     * The primaryRole   is Useful to  get the primaryRole
	    
     */
    private String primaryRole;

  /**
     * The id   is Useful to  get the Grid id;
	    
     */
    private int id;
  /**
     * The empId   is Useful to  get the empId;
	    
     */
    private int empId;
  /**
     * The roleName  is Useful to  get the roleName;
	    
     */
    private String roleName;

 /**
     * The roleId  is Useful to  get the roleId;
	    
     */
    private int roleId;


/**
 *  Setters and Getters.
 *  
 *  
 */


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPrimaryRole() {
        return primaryRole;
    }

    public void setPrimaryRole(String primaryRole) {
        this.primaryRole = primaryRole;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

   

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

   
    
    
}
