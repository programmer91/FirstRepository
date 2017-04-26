/*
 * ProjectAction.java
 *
 * Created on December 18, 2008, 6:00 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.employee.projects;
import com.mss.mirage.projects.ProjectService;
import com.mss.mirage.projects.ProjectServiceImpl;
import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Date;
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
 */
public class ProjectAction extends ActionSupport implements ServletRequestAware,ServletResponseAware  {
    
    /**
     * Creates a new instance of ProjectAction
     */
    public ProjectAction() {
    }
    private String prjName;
    private String prjManagerUID;
    private Date currentDate;
    private String startDate;
    private String endDate;
    private String projectDuration;
    private String projectType;
    private String accName; //
    private Map reportsToDefault;
    private Map prjTypeMap;
    private Map custNameMap=new TreeMap();
    private int accountId;
    private List list;
    private int userRoleId;
    private HttpServletRequest httpServletRequest;
    private String resultType="";
    private String empId;
    private ProjectVTO projVTO; //
    private int projId;      //
    private String currentAction;
    private HttpServletResponse httpServletResponse;
    private String statusCode;
    //private Map
    
    
    public String prepare() throws Exception {
        
        setReportsToDefault(DataSourceDataProvider.getInstance().getReportsToDefault());
        setPrjTypeMap(DataSourceDataProvider.getInstance().getPrjTypeAndId());
        setCustNameMap(DataSourceDataProvider.getInstance().getAccNameAndId());
        setCurrentDate(Date.valueOf(DateUtility.getInstance().getToDayDate()));
        return SUCCESS;
    }
    public String addProjectDetails() throws Exception {
        
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            empId=httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            try {
                boolean isInserted=false;
                String resultMessage="";
                isInserted= ServiceLocator.getProjectDetailService().addProjectDetails(this);
                if(isInserted && projId!=0){
                    resultMessage="Successfully ProjectDetails updated";
                }
                else if(isInserted){
                     resultMessage="Successfully ProjectDetails add";
                }
                else{
                    resultMessage="please Try again";
                }
                httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
                prepare();
            
            resultType=SUCCESS;
        }catch (Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            resultType =  ERROR;
        }
    }
      return resultType; 
    }
    public String   getProjectDetails() throws Exception {
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            empId=httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            try {
                boolean isInserted=false;
                String resultMessage="";
                ProjectsService prjService = ServiceLocator.getProjectDetailService();
               setProjVTO(prjService.getProjectDetails(projId));
//                if(isInserted){
//                    resultMessage="Successfully ProjectDetails updated";
//                }else{
//                    resultMessage="please Try again";
//                }
//                httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
                prepare();
                prjService.getProjStatusCode(this);
                setCurrentAction("edit");
            resultType=SUCCESS;
        }catch (Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            resultType =  ERROR;
        }
    }
      return resultType; 
        
    }
    public String getPrjName() {
        return prjName;
    }
    
    public void setPrjName(String prjName) {
        this.prjName = prjName;
    }
    
    public String getPrjManagerUID() {
        return prjManagerUID;
    }
    
    public void setPrjManagerUID(String prjManagerUID) {
        this.prjManagerUID = prjManagerUID;
    }
    
    public String getStartDate() {
        return startDate;
    }
    
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    
    public String getEndDate() {
        return endDate;
    }
    
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
    public String getProjectDuration() {
        return projectDuration;
    }
    
    public void setProjectDuration(String projectDration) {
        this.projectDuration = projectDuration;
    }
    
    public String getProjectType() {
        return projectType;
    }
    
    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }
    
    public String getAccName() {
        return accName;
    }
    
    public void setAccName(String accName) {
        this.accName = accName;
    }
    
    public Map getReportsToDefault() {
        return reportsToDefault;
    }
    
    public void setReportsToDefault(Map reportsToDefault) {
        this.reportsToDefault = reportsToDefault;
    }
    
    public int getAccountId() {
        return accountId;
    }
    
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
    
    public Map getPrjTypeMap() {
        return prjTypeMap;
    }
    
    public void setPrjTypeMap(Map prjTypeMap) {
        this.prjTypeMap = prjTypeMap;
    }
    
    public Map getCustNameMap() {
        return custNameMap;
    }
    
    public void setCustNameMap(Map custNameMap) {
        this.custNameMap = custNameMap;
    }
    
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse=httpServletResponse;
    }
    
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest=httpServletRequest;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public ProjectVTO getProjVTO() {
        return projVTO;
    }

    public void setProjVTO(ProjectVTO projVTO) {
        this.projVTO = projVTO;
    }

   

    public String getCurrentAction() {
        return currentAction;
    }

    public void setCurrentAction(String currentAction) {
        this.currentAction = currentAction;
    }

    public int getProjId() {
        return projId;
    }

    public void setProjId(int projId) {
        this.projId = projId;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
    
}
