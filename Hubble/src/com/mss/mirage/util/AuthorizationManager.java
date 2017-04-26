/*
 * AuthorizationManager.java
 *
 * Created on December 15, 2007, 7:42 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.util;

/**
 *
 * @author miracle
 */
public class AuthorizationManager {
    
    public static AuthorizationManager _instance;
    
    /** Creates a new instance of AuthorizationManager */
    private AuthorizationManager() {
    }
    
    public static AuthorizationManager getInstance(){
        if(_instance == null){
            _instance = new AuthorizationManager();
        }
        return _instance;
    }
    
    public boolean isAuthorizedUser(String accessKey, int roleId){
        boolean isAuthorized = false;
       // System.err.print("roleId in  AUTH******---"+roleId);
        try{
            
            int noOfRoles = Integer.parseInt(SecurityProperties.getProperty("TOTAL_ROLES"));
            String authorizedRoleIds = SecurityProperties.getProperty(accessKey);
            String authorizedRoleIdsArray[] = new String[noOfRoles];
            authorizedRoleIdsArray = authorizedRoleIds.split(",");
            for(int counter=0;counter < authorizedRoleIdsArray.length;counter++){
                if(roleId == Integer.parseInt(authorizedRoleIdsArray[counter])) isAuthorized = true;
            }
        }catch(Exception slex){
            
        }
        return isAuthorized;
    }
    public boolean isAuthorizedUserCreateIssue(int isManager, int isTeamLead,String accessKey){
        boolean isAuthorized = false;
       // System.err.print("roleId in  AUTH******---"+roleId);
        try{
            
            int noOfRoles = Integer.parseInt(SecurityProperties.getProperty("TOTAL_LEADS"));
            String authorizedRoleIds = SecurityProperties.getProperty(accessKey);
            String authorizedRoleIdsArray[] = new String[noOfRoles];
            authorizedRoleIdsArray = authorizedRoleIds.split(",");
            for(int counter=0;counter < authorizedRoleIdsArray.length;counter++){
                if(isTeamLead == Integer.parseInt(authorizedRoleIdsArray[counter]) || isManager== Integer.parseInt(authorizedRoleIdsArray[counter])) isAuthorized = true;
            }
        }catch(Exception slex){
            
        }
        return isAuthorized;
    }
    
       public boolean isAuthorizedForSurveyForm(String accessKey, String loginId){
        boolean isAuthorized = false;
        try{
            
           // int noOfRoles = Integer.parseInt(SecurityProperties.getProperty("TOTAL_ROLES"));
            String authorizedRoleIds = SecurityProperties.getProperty(accessKey);
            String authorizedRoleIdsArray[] = authorizedRoleIds.split(",");
            
            for(int counter=0;counter < authorizedRoleIdsArray.length;counter++){
                if(loginId.equals(authorizedRoleIdsArray[counter])) isAuthorized = true;
            }
        }catch(Exception slex){
            
        }
        return isAuthorized;
    }
}
