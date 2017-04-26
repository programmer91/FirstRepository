/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :   August 4, 2008, 3:15 PM
 *
 * Author  :  Rajasekhar Yenduva <ryenduva@miraclesoft.com>
 *
 * File    : CalendarAction .java
 *
 * Copyright 2008 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.crm.calendar;

import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.ServiceLocator;
import com.mss.mirage.util.ServiceLocatorException;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author Rajasekhar Yenduva <ryenduva@miraclesoft.com>
 */
public class CalendarHandlerAction extends ActionSupport implements ServletRequestAware {
    
    private HttpServletRequest httpServletRequest;
    private String userRoleId;
    private String resultType;
    private CalendarHandlerService calendarHandlerService;
    private Map salesTeamExceptAccountTeamMap = new TreeMap();
    private Map TeamCalendar=new TreeMap();
    private String currentAction;
    private String currentTeamAction;
    /**
     * Creates a new instance of CalendarHandlerAction
     */
    public CalendarHandlerAction() {
    }
    
/*    public String execute() {
        String resultType = LOGIN;
        String date = "";
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            try {
                //connection = ConnectionProvider.getInstance().getConnection();
                String queryString = "select * from tblCrmCalendar where EventDate like '"+date+"' order by EventDate desc ";
                httpServletRequest.setAttribute("queryString",queryString);
                resultType = SUCCESS;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
        }
        return resultType;
    }*/
    
    
    public String accessTeamCalendar(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            //setUserRoleId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString()));
            resultType = "accessFailed";
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString().equals("Sales")){
               String empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
                try{
                    calendarHandlerService = ServiceLocator.getCalendarService();
                    setSalesTeamExceptAccountTeamMap(calendarHandlerService.getAllSalesTeamExceptAccountTeam(empId));
                    setCurrentAction("AccessCalendar");
                    resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
                
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }
    
    public String accessTeamCalendar1(){ //this is is used for accessing team members calendar by teamlead or team Manager
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            //setUserRoleId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString()));
            resultType = "accessFailed";
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString().equals("Sales")){
               String empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                try{
                    calendarHandlerService = ServiceLocator.getCalendarService();
                    setTeamCalendar(calendarHandlerService.getTeamCalendar(empId));
                    setCurrentAction("AccessCalendar");
                    setCurrentTeamAction("AccessTeamCalendar");
                    resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
                
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType; 
    }

    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public String getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(String userRoleId) {
        this.userRoleId = userRoleId;
    }

    public Map getSalesTeamExceptAccountTeamMap() {
        return salesTeamExceptAccountTeamMap;
    }

    public void setSalesTeamExceptAccountTeamMap(Map salesTeamExceptAccountTeamMap) {
        this.salesTeamExceptAccountTeamMap = salesTeamExceptAccountTeamMap;
    }

    public String getCurrentAction() {
        return currentAction;
    }

    public void setCurrentAction(String currentAction) {
        this.currentAction = currentAction;
    }

    public Map getTeamCalendar() {
        return TeamCalendar;
    }

    public void setTeamCalendar(Map TeamCalendar) {
        this.TeamCalendar = TeamCalendar;
    }

    public String getCurrentTeamAction() {
        return currentTeamAction;
    }

    public void setCurrentTeamAction(String currentTeamAction) {
        this.currentTeamAction = currentTeamAction;
    }
    
}
