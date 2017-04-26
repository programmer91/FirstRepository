/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.tree.team;
import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.Properties;
import com.mss.mirage.util.ServiceLocatorException;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
/**
 *
 * @author miracle
 */
public class DynamicTeamHierarchyAction extends ActionSupport implements ServletRequestAware{
    private HttpServletRequest httpServletRequest;
     private String departmentId;
     private String reportsTo;
     private Map reportsToIdMap;
    public TeamHierarchy getTeamTreeRootNode() throws ServiceLocatorException{
        HttpSession session = httpServletRequest.getSession(false);
        String userId = null;
        String userFullName = null;
       
        // if session is existed or not
        if(session!=null){
           // userId = session.getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            if(getReportsTo()==null||"".equals(getReportsTo())||"-1".equals(getReportsTo()))
             userId = Properties.getProperty("Managers.ReportingTo");
            else
                userId = getReportsTo();
            userFullName = DataSourceDataProvider.getInstance().getFullNameBYLoginId(userId);
            TeamHierarchy.setUserFullName(userFullName);
            TeamHierarchy.getMyTeamManagersHierarchy(Properties.getProperty("Managers.ReportingTo"));
        }
        return TeamHierarchy.getByUserId(userId);
    }
    
     public OperationsTeamHirarchy getTeamTreeRootNodeOfOperations() throws ServiceLocatorException{
        HttpSession session = httpServletRequest.getSession(false);
        String userId = null;
        String userFullName = null;
        
        // if session is existed or not
        if(session!=null){
             if(getReportsTo()==null||"".equals(getReportsTo())||"-1".equals(getReportsTo()))
             userId = Properties.getProperty("Operations.ReportingTo");
              else
                userId = getReportsTo();
            userFullName = DataSourceDataProvider.getInstance().getFullNameBYLoginId(userId);
            OperationsTeamHirarchy.setUserFullName(userFullName);
            OperationsTeamHirarchy.getMyTeamManagersHierarchyOfOperations(Properties.getProperty("Operations.ReportingTo"));
        }
        return OperationsTeamHirarchy.getByUserId(userId);
    }
     
     public GDCHierarchy getTeamTreeOfGDC() throws ServiceLocatorException{
        HttpSession session = httpServletRequest.getSession(false);
        String userId = null;
        String userFullName = null;
        
        // if session is existed or not
        if(session!=null){
             if(getReportsTo()==null||"".equals(getReportsTo())||"-1".equals(getReportsTo()))
             userId = Properties.getProperty("GDC.ReportingTo");
             else
                userId = getReportsTo();
            userFullName = DataSourceDataProvider.getInstance().getFullNameBYLoginId(userId);
            GDCHierarchy.setUserFullName(userFullName);
            GDCHierarchy.getMyTeamHierarchyOfGDC(Properties.getProperty("GDC.ReportingTo"));
        }
        return GDCHierarchy.getByUserId(userId);
    } 
     
    public RecruitmentHierarchy getTeamTreeOfRecruitment() throws ServiceLocatorException{
        HttpSession session = httpServletRequest.getSession(false);
        String userId = null;
        String userFullName = null;
        
        // if session is existed or not
        if(session!=null){
             if(getReportsTo()==null||"".equals(getReportsTo())||"-1".equals(getReportsTo()))
            userId = Properties.getProperty("Recruitment.ReportingTo");
              else
                userId = getReportsTo();
            userFullName = DataSourceDataProvider.getInstance().getFullNameBYLoginId(userId);
            RecruitmentHierarchy.setUserFullName(userFullName);
            RecruitmentHierarchy.getMyTeamHierarchyOfRecruitment(Properties.getProperty("Recruitment.ReportingTo"));
        }
        return RecruitmentHierarchy.getByUserId(userId);
    }
     
     
    public String execute()throws Exception{
        if(getDepartmentId()==null){
             setDepartmentId("Sales");
        }else {
            setDepartmentId(getDepartmentId());
        }
         setReportsToIdMap(DataSourceDataProvider.getInstance().getReportsToByDepartment(getDepartmentId()));
        setReportsTo(getReportsTo());
        
        //System.out.println("I am in Execute");
        
        return SUCCESS;
    }
     
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        
        //Binding Request Object to This Class Variable httpServletRequest
        this.httpServletRequest = httpServletRequest;
        
    }

    /**
     * @return the departmentId
     */
    public String getDepartmentId() {
        return departmentId;
    }

    /**
     * @param departmentId the departmentId to set
     */
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * @return the reportsTo
     */
    public String getReportsTo() {
        return reportsTo;
    }

    /**
     * @param reportsTo the reportsTo to set
     */
    public void setReportsTo(String reportsTo) {
        this.reportsTo = reportsTo;
    }

    /**
     * @return the ReportsToIdMap
     */
    public Map getReportsToIdMap() {
        return reportsToIdMap;
    }

    /**
     * @param ReportsToIdMap the ReportsToIdMap to set
     */
    public void setReportsToIdMap(Map reportsToIdMap) {
        this.reportsToIdMap = reportsToIdMap;
    }
}
