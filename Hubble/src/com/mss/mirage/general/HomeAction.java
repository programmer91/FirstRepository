/*
 * @(#)HomeAction.java 1.0 Nov 01, 2007
 *
 * Copyright 2006 Miracle Software Systems(INDIA) Pvt Ltd. All rights reserved.
 *
 */

package com.mss.mirage.general;

import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.SecurityProperties;
import com.mss.mirage.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Collection;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 * The <code>HomeAction</code>  class is used for getting roles from
 * <i>Home.jsp</i> page.
 *
 * @author RajaReddy.Andra <a href="mailto:randra@miraclesoft.com">randra@miraclesoft.com</a>
 *
 * @version 1.0 Nov 01, 2007
 *
 * @see com.mss.mirage.util.ApplicationConstants
 * @see com.mss.mirage.util.ApplicationDataProvider
 * @see com.mss.mirage.util.ServiceLocator
 *
 */
public class HomeAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
    
    /*@param roleTypeId is used to store roleid*/
    private int roleTypeId;
    
    /*@param httpServletRequest is used to store request of type HttpServletRequest*/
    private HttpServletRequest httpServletRequest;
    
    /*@param httpServletResponse is used to store response of type httpServletResponse*/
    private HttpServletResponse httpServletResponse;
    
    /*@param currentLeftMenu is used to store left menus names */
    private Map currentLeftMenu;
    
    /*@param id is used to store id */
    private int id;
    
    /*@param description is used to store roles of employee*/
    private String description;
    
    /*@param defaultAction is used to store actions of leftmenu*/
    private String defaultAction;
    
    /*@param roleName is used to store roleName*/
    private String roleName;
    
    /*@param leftMenu is used to get leftMenu of type is collection*/
    private Collection leftMenu;
    
    /*@param resultType is used to store type of the result*/
    private String resultType;
    
    private String userId;
    private int usableTeamHours = 0;
    private int usedTeamHours = 0;
    private int naturalHolidayHours = 0;
    private int teamLeaveHours = 0;
    
    /** Creates a new instance of HomeAction */
    public HomeAction() {
    }
    
    /**execute() method return type String  it returns resultType
     *user select an perticuler role in home page and click on continue button he will get leftmenu
     *depending on the role.
     */
    public String execute() throws Exception {
        try{
            resultType = LOGIN;
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
                
                String defaultAction = null;
                if(roleTypeId == 0) {
                    roleTypeId =  Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
                }
                String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                httpServletRequest.getSession(false).setAttribute(ApplicationConstants.SESSION_ROLE_ID,String.valueOf(getRoleTypeId()));
                roleName = ServiceLocator.getGeneralService().getRoleName(getRoleTypeId());
                
                 Map rolesMap = (Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_ROLES);
                
                
                
                    String forwarded = httpServletRequest.getHeader("X-FORWARDED-FOR");
                    String incomingIpAddress = httpServletRequest.getRemoteAddr();
					
					String actualIpAddress = null;
					String actualIpAddress1 = null;
                   
					String allowUser=SecurityProperties.getProperty(userId);
                                        
                                       // System.out.println("allowUser-->"+allowUser);
                                        if(!rolesMap.containsValue(roleName)){
                                             httpServletRequest.getSession(false).setAttribute("roleId",2);
                                        httpServletRequest.getSession(false).setAttribute("roleName","Employee");
                                        setDefaultAction("/employee/tasks/getTasks.action");  
                                        }else {
                                        
                                        
					if(allowUser.contains("!")) {
                                            //System.out.println("allowUser1-->"+allowUser);
					if(forwarded!=null && incomingIpAddress!=null){
                                           // System.out.println("allowUser2-->"+allowUser);
							actualIpAddress=SecurityProperties.getProperty(forwarded);
							actualIpAddress1=SecurityProperties.getProperty(incomingIpAddress);
							if(actualIpAddress.contains("!") && actualIpAddress1.contains("!")){
                                                          //  System.out.println("allowUser3-->"+allowUser);
                                                            httpServletRequest.getSession(false).setAttribute("roleId",2);
                                                            httpServletRequest.getSession(false).setAttribute("roleName","Employee");
                                                            setDefaultAction("/employee/tasks/getTasks.action"); 
									
							}else if(actualIpAddress.contains("!") && !actualIpAddress1.contains("!")){
                                                           // System.out.println("allowUser4-->"+allowUser);
                                                            setDefaultAction(ServiceLocator.getGeneralService().getPrimaryAction(getRoleTypeId()));
                                                        }else if(!actualIpAddress.contains("!") && actualIpAddress1.contains("!")){
                                                           // System.out.println("allowUser5-->"+allowUser);
                                                            setDefaultAction(ServiceLocator.getGeneralService().getPrimaryAction(getRoleTypeId()));
                                                        }else{
								//System.out.println("allowUser6-->"+allowUser);
                                                            setDefaultAction(ServiceLocator.getGeneralService().getPrimaryAction(getRoleTypeId()));
							}
                                    } else if(forwarded!=null){
                                       // System.out.println("allowUser7-->"+allowUser);
							actualIpAddress=SecurityProperties.getProperty(forwarded);
							if(actualIpAddress.contains("!")){
                                                           // System.out.println("allowUser8-->"+allowUser);
                                                            httpServletRequest.getSession(false).setAttribute("roleId",2);
                                                            httpServletRequest.getSession(false).setAttribute("roleName","Employee");
                                                            setDefaultAction("/employee/tasks/getTasks.action"); 
									
							}else{
								//System.out.println("allowUser9-->"+allowUser);
                                                            setDefaultAction(ServiceLocator.getGeneralService().getPrimaryAction(getRoleTypeId()));
							}
                                    }else if(incomingIpAddress!=null){
                                        //System.out.println("allowUser10-->"+allowUser);
							actualIpAddress1=SecurityProperties.getProperty(incomingIpAddress);
							if(actualIpAddress1.contains("!")){
                                                          //  System.out.println("allowUser11-->"+allowUser);
                                                            httpServletRequest.getSession(false).setAttribute("roleId",2);
                                                            httpServletRequest.getSession(false).setAttribute("roleName","Employee");
                                                            setDefaultAction("/employee/tasks/getTasks.action"); 
									
							}else{
                                                           // System.out.println("allowUser12-->"+allowUser);
								setDefaultAction(ServiceLocator.getGeneralService().getPrimaryAction(getRoleTypeId())); 
							}
				  }else{
                                        //System.out.println("allowUser13-->"+allowUser);
                                        httpServletRequest.getSession(false).setAttribute("roleId",2);
                                        httpServletRequest.getSession(false).setAttribute("roleName","Employee");
                                        setDefaultAction("/employee/tasks/getTasks.action");  
				}
						
				}else{
                                            //System.out.println("allowUser14-->"+allowUser);
					setDefaultAction(ServiceLocator.getGeneralService().getPrimaryAction(getRoleTypeId()));
				}
					
            }
                
                
                
                
                
                
                
                
                
            /*
             *  Eneable below If you want to generate Left Menu Dynamically
             *  Note: If you enable below line, You should update code in
             *  /includes/template/LeftMenu.jsp to affect
             */
                //setLeftMenu(DataSourceDataProvider.getInstance().getMenu(getRoleTypeId()));
                
//            if(httpSession.getAttribute(ApplicationConstants.SESSION_LEFT_MENU) != null){
//                httpSession.removeAttribute(ApplicationConstants.SESSION_LEFT_MENU);
//            }
//
//            httpSession.setAttribute(ApplicationConstants.SESSION_LEFT_MENU,getLeftMenu());
                
                
                if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME) != null){
                    httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.SESSION_ROLE_NAME);
                }
                
                //Removing Issues SQLQuery from session
                if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_ISSUES_LIST)!=null){
                    httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_ISSUES_LIST);
                }
                //Removing Activity SQLQuery from session
                if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_ACTIVITY_LIST)!=null){
                    httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_ACTIVITY_LIST);
                }
                if(Integer.parseInt(httpServletRequest.getSession(false).getAttribute("roleId").toString())==2)
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.SESSION_ROLE_NAME,"Employee");
                else{
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.SESSION_ROLE_NAME,roleName);
                }
                httpServletRequest.getSession(false).setAttribute(ApplicationConstants.PAYROLL_AUTH_CHECK,0);

                resultType = SUCCESS;
            }//Closing Session checking
        } catch(Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            ex.printStackTrace();
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            resultType =  ERROR;
        }
        
        return resultType;
    }
    
    public String setDefaultRole() throws Exception {
        
        
        try{
            resultType=LOGIN;
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
                if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID) != null){
                    httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.SESSION_ROLE_ID);
                }
                httpServletRequest.getSession(false).setAttribute(ApplicationConstants.SESSION_ROLE_ID,
                        String.valueOf(DataSourceDataProvider.getInstance().getDefaultRoleId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString())));
                resultType = SUCCESS;
                /*if(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString().trim())==1){
                    setUserId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                    //setUsableTeamHours(ServiceLocator.getGeneralService().getUsableTeamHours());
                    setUsedTeamHours(ServiceLocator.getGeneralService().getUsedTeamHours());
                    setNaturalHolidayHours(ServiceLocator.getGeneralService().getNaturalHolidayHours());
                    setTeamLeaveHours(ServiceLocator.getGeneralService().getTeamLeaveHours());
                    resultType="manager";
                }*/
            }//Close Session Checking
        } catch(Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            resultType =  ERROR;
        }
        return resultType;
    }
    
    /*from here bean section all setter and getter methods*/
    /*getRoleTypeId() used to get role type id*/
    public int getRoleTypeId() {
        return roleTypeId;
    }
    
    
    /*setRoleTypeId(int roleTypeId) used to set role type id*/
    public void setRoleTypeId(int roleTypeId) {
        this.roleTypeId = roleTypeId;
    }
    
    /*this is abstract method*/
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }
    
    /*getCurrentLeftMenu() used to get current left menu*/
    public Map getCurrentLeftMenu() {
        return currentLeftMenu;
    }
    
    /*setCurrentLeftMenu(Map currentLeftMenu) used to set current left menu*/
    public void setCurrentLeftMenu(Map currentLeftMenu) {
        this.currentLeftMenu = currentLeftMenu;
    }
    
    /*this is abstract method*/
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }
    
    /*getId() used to get id*/
    public int getId() {
        return id;
    }
    
    /*setId(int id) used to set id*/
    public void setId(int id) {
        this.id = id;
    }
    
    /*getDescription() used to get description of the role*/
    public String getDescription() {
        return description;
    }
    
    /*setDescription(String description) used to set description of the role*/
    public void setDescription(String description) {
        this.description = description;
    }
    
    /*getDefaultAction() used to get default action*/
    public String getDefaultAction() {
        return defaultAction;
    }
    
    /*setDefaultAction(String defaultAction) used to set default action*/
    public void setDefaultAction(String defaultAction) {
        this.defaultAction = defaultAction;
    }
    
    /*getLeftMenu() used to get left menu*/
    public Collection getLeftMenu() {
        return leftMenu;
    }
    
    /*setLeftMenu(Collection leftMenu) used to set left menu*/
    public void setLeftMenu(Collection leftMenu) {
        this.leftMenu = leftMenu;
    }
    
    public int getUsableTeamHours() {
        return usableTeamHours;
    }
    
    public void setUsableTeamHours(int usableTeamHours) {
        this.usableTeamHours = usableTeamHours;
    }
    
    public int getUsedTeamHours() {
        return usedTeamHours;
    }
    
    public void setUsedTeamHours(int usedTeamHours) {
        this.usedTeamHours = usedTeamHours;
    }
    
    public int getNaturalHolidayHours() {
        return naturalHolidayHours;
    }
    
    public void setNaturalHolidayHours(int naturalHolidayHours) {
        this.naturalHolidayHours = naturalHolidayHours;
    }
    
    public int getTeamLeaveHours() {
        return teamLeaveHours;
    }
    
    public void setTeamLeaveHours(int teamLeaveHours) {
        this.teamLeaveHours = teamLeaveHours;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
}
