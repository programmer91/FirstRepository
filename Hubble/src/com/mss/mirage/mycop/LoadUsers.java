/*
 * LoadUsers.java
 *
 * Created on March 10, 2008, 4:44 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.mycop;
import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.ServiceLocatorException;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
/**
 *
 * @author miracle
 * This class gets team members map object from session for using in MyCopReport.jsp
 */
public class LoadUsers extends ActionSupport implements ServletRequestAware{
    
    //private List users;
    private Map MyTeamMembers = new TreeMap();
    private HttpServletRequest httpServletRequest;
   
    
    
    public String execute(){
        setMyTeamMembers((Map)getHttpServletRequest().getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
        
        
        return SUCCESS;
    }    
   
    
    

    public Map getMyTeamMembers() {
        return MyTeamMembers;
    }

    public void setMyTeamMembers(Map MyTeamMembers) {
        this.MyTeamMembers = MyTeamMembers;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }   

    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    
    
}
