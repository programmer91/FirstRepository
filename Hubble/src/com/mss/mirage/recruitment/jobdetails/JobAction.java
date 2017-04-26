/*
 * JobAction.java
 *
 * Created on January 3, 2010, 6:40 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.recruitment.jobdetails;

import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.AuthorizationManager;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Date;
import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import java.sql.Time;
import com.mss.mirage.util.DateUtility;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author miracle
 */
    
    public class JobAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
    
    private int jobId;
    
    private String employeeLoginId;
    
    public HttpServletRequest httpServletRequest;
    
    private Date date;
    
    private int userRoleId;
    private String resultType = null;
    private String resultMessage="";
    private  String title;
    private  int number;
    private  String type;
    private  Float rate;
    private  String reqtravels;
    private  String length;
    private  String tele;
    private  String email;
    private  String city;
    private  String province;
    private  String country;
    private  String areacode;
    private  String postalcode;
    private  String skills;
    private  String desc;
    private  String notes;
    
    private Timestamp createdtime;
    
    private String userid;
    private  String jtitle;
    private  int jnumber;
    private String addOrUpdate;
    
    private  StringBuilder stringBuilder;
    
    private String submitFrom;
    
    private String currentSearch;
    
    private String currentAction;
    
    private JobVTO currentJobVTO;
    
   private HttpServletResponse httpServletResponse;
    
    
    public JobAction() {
    }
    
    
    public String execute()throws Exception{
        
        return SUCCESS;
    }
    
    public String addOrUpdate(){
        setCurrentAction("jobAdd");
        return SUCCESS;
    }
    
    
    public String doAdd() throws Exception {
        setResultType(LOGIN);
        
        if(getHttpServletRequest().getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            setUserRoleId(Integer.parseInt(getHttpServletRequest().getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString()));
            // setResultType("accessFailed");
            
            //Checking whether action has permission
            // if(AuthorizationManager.getInstance().isAuthorizedUser("Job_ADD", getUserRoleId())){
            try{
                boolean isInserted=false;
                String resultMessage="";
                
                setCreatedtime(DateUtility.getInstance().getCurrentMySqlDateTime());
                
                setUserid(getHttpServletRequest().getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                
               // isInserted = ServiceLocator.getJobService().addJob(this);
                if(isInserted){
                    resultMessage="Successfully Job added";
                }else{
                    resultMessage="please Try again";
                }
                getHttpServletRequest().setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
                setResultType(SUCCESS);
                
                //Exception Handling
            }catch (Exception ex){
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                getHttpServletRequest().getSession(false).setAttribute("errorMessage",ex.toString());
                setResultType(ERROR);
            }
            // }
        }
        
        // return getResultType();
        
        return SUCCESS;
    }
    
    
    public String doEdit() throws Exception {
        
        
        setResultType(LOGIN);
        if(getHttpServletRequest().getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            setUserRoleId(Integer.parseInt(getHttpServletRequest().getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString()));
            setResultType("accessFailed");
            //   if(AuthorizationManager.getInstance().isAuthorizedUser("PHONELOG_EDIT", getUserRoleId())){
            try {
                boolean isUpdated=false;
                String resultMessage="";
                
                setCreatedtime(DateUtility.getInstance().getCurrentMySqlDateTime());
                
                setUserid(getHttpServletRequest().getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                
              //  isUpdated=ServiceLocator.getJobService().editJob(this,getJobId());
                if(isUpdated){
                    resultMessage="Successfully JobDetails updated";
                }else{
                    resultMessage="please Try again";
                }
                getHttpServletRequest().setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
                setResultType(SUCCESS);
            }catch (Exception ex){
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                getHttpServletRequest().getSession(false).setAttribute("errorMessage",ex.toString());
                setResultType(ERROR);
            }
            // }
        }
        //  setCurrentAction("doEdit");
        return  getResultType();
    }
    
    
    public String doDelete() throws Exception {
        setResultType(LOGIN);
        if(getHttpServletRequest().getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            setUserRoleId(Integer.parseInt(getHttpServletRequest().getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString()));
            //   setResultType("accessFailed");
            //   if(AuthorizationManager.getInstance().isAuthorizedUser("PHONELOG_DELETE", getUserRoleId())){
            try {
                boolean isDeleted=false;
                String resultMessage="";
               // isDeleted=ServiceLocator.getJobService().deleteJob(this,getJobId());
                if(isDeleted){
                    resultMessage="Successfully JobDetails deleted";
                    
                }else{
                    resultMessage="please Try again";
                }
                getHttpServletRequest().setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
                setResultType(SUCCESS);
            }catch (Exception ex){
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                getHttpServletRequest().getSession(false).setAttribute("errorMessage",ex.toString());
                setResultType(ERROR);
            }
            //   }
        }
        
        setCurrentAction("doDelete");
        return getResultType();
        
    }
    
    public String myJobList() {
        setResultType(LOGIN);
        
        try{
            if(getHttpServletRequest().getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
                setUserRoleId(Integer.parseInt(getHttpServletRequest().getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString()));
                String userId=getHttpServletRequest().getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                setResultType("accessFailed");
                //   if(AuthorizationManager.getInstance().isAuthorizedUser("MY_Job_LIST", getUserRoleId())){
                if(getSubmitFrom()==null) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("SELECT id,JobTitle,JobNumber,PositionType,Email,JobCity,JobCountry,RequiredSkills from tblJobDetails");
                }
                
                /*if query is already existed remove and add new squery */
                if(getHttpServletRequest().getSession(false).getAttribute(ApplicationConstants.QS_JOB_LIST)!=null){
                    getHttpServletRequest().getSession(false).removeAttribute(ApplicationConstants.QS_JOB_LIST);
                }
                getHttpServletRequest().getSession(false).setAttribute(ApplicationConstants.QS_JOB_LIST,stringBuilder.toString());
                getHttpServletRequest().getSession(false).getAttribute(ApplicationConstants.QS_JOB_LIST);
                //  System.out.println("--"+getHttpServletRequest().getSession(false).getAttribute(ApplicationConstants.QS_JOB_LIST));
                // }
                setResultType(SUCCESS);
                
            }
        }catch (Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            getHttpServletRequest().getSession(false).setAttribute("errorMessage",ex.toString());
            setResultType(ERROR);
        }
        setCurrentSearch("JobList");
        return getResultType();
    }
    
    /**
     * this method is for searching the employ phonelog
     * @return
     */
    public String myJobSearch(){
        //  setCurrentSearchAction("JobSearchResult");
        setResultType(LOGIN);
        boolean andFlag = false;
        try{
            if(getHttpServletRequest().getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
                setUserRoleId(Integer.parseInt(getHttpServletRequest().getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString()));
                setResultType("accessFailed");
                //  if(AuthorizationManager.getInstance().isAuthorizedUser("MY_Job_SEARCH", getUserRoleId())){
                //   if("Search".equalsIgnoreCase(getSubmitFrom())) {
                setStringBuilder(new StringBuilder());
                //        if(!getTitle().equals("") && getJnumber()!=0){
                getStringBuilder().append("SELECT JobTitle,JobNumber,PositionType,Email,JobCity,JobCountry,RequiredSkills from tblJobDetails  ");
                getStringBuilder().append(" where ");
                getStringBuilder().append("JobNumber = "+getJnumber()+" and JobTitle = '"+getJtitle()+"' group by  id  ");
                
                //    }
                if(getHttpServletRequest().getSession(false).getAttribute(ApplicationConstants.QS_JOB_LIST)!=null){
                    getHttpServletRequest().getSession(false).removeAttribute(ApplicationConstants.QS_JOB_LIST);
                }
                getHttpServletRequest().getSession(false).setAttribute(ApplicationConstants.QS_JOB_LIST,getStringBuilder().toString());
                //  }
                // }
                String userId=getHttpServletRequest().getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                
                setResultType(SUCCESS);
            }
        }catch(Exception ex1){
            getHttpServletRequest().getSession(false).setAttribute("errorMessage",ex1.toString());
            setResultType(ERROR);
        }
        
        setCurrentSearch("Result");
        return getResultType();
    }
    
    
    
    public String getJob()throws Exception{
        
        // setResultType(LOGIN);
        if(getHttpServletRequest().getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            setUserRoleId(Integer.parseInt(getHttpServletRequest().getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString()));
            //   setResultType("accessFailed");
            //  if(AuthorizationManager.getInstance().isAuthorizedUser("GET_Job", getUserRoleId())){
            try{
              //  setCurrentJobVTO(ServiceLocator.getJobService().getJob(getJobId()));
                // return SUCCESS;
            }catch (Exception ex){
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                getHttpServletRequest().getSession(false).setAttribute("errorMessage",ex.toString());
                setResultType(ERROR);
            }
            // }
        }
        //  return getResultType();
        setCurrentAction("doEdit");
        return SUCCESS;
    }
    
    
    
    
    public String generatereport() {
        
        String reportResult;
        
        String query ="";
        
        resultType = "login";
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            
            try {
             
               // JobService jobservice = ServiceLocator.getJobService();
                //hrService.getFutureLeavesReport(httpServletResponse,empName,futureLeaves);
                query = "SELECT JobTitle from tblJobDetails";
                
           //     reportResult = jobservice.generateTitleReport(query,this, httpServletResponse);
             //   httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,reportResult);
                resultType = SUCCESS;
            }catch(Exception ex) {
                httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                resultType =  ERROR;
            }
        }
              return resultType;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public String getEmployeeLoginId() {
        return employeeLoginId;
    }
    
    public void setEmployeeLoginId(String employeeLoginId) {
        this.employeeLoginId = employeeLoginId;
    }
    
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.setHttpServletRequest(httpServletRequest);
    }
    
    public  Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    
    
    public String getSubmitFrom() {
        return submitFrom;
    }
    
    public void setSubmitFrom(String submitFrom) {
        this.submitFrom = submitFrom;
    }
    public String getCurrentSearch() {
        return currentSearch;
    }
    
    public void setCurrentSearch(String currentSearch) {
        this.currentSearch = currentSearch;
    }
    
    
    public String getCurrentAction() {
        return currentAction;
    }
    
    public void setCurrentAction(String currentAction) {
        this.currentAction = currentAction;
    }
    
    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }
    
    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }
    
    public int getUserRoleId() {
        return userRoleId;
    }
    
    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }
    
    public String getResultType() {
        return resultType;
    }
    
    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
    
    public String getResultMessage() {
        return resultMessage;
    }
    
    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public int getNumber() {
        return number;
    }
    
    public void setNumber(int number) {
        this.number = number;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public Float getRate() {
        return rate;
    }
    
    public void setRate(Float rate) {
        this.rate = rate;
    }
    
    public String getReqtravels() {
        return reqtravels;
    }
    
    public void setReqtravels(String reqtravels) {
        this.reqtravels = reqtravels;
    }
    
    public String getLength() {
        return length;
    }
    
    public void setLength(String length) {
        this.length = length;
    }
    
    public String getTele() {
        return tele;
    }
    
    public void setTele(String tele) {
        this.tele = tele;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getProvince() {
        return province;
    }
    
    public void setProvince(String province) {
        this.province = province;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public String getAreacode() {
        return areacode;
    }
    
    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }
    
    public String getPostalcode() {
        return postalcode;
    }
    
    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }
    
    public String getSkills() {
        return skills;
    }
    
    public void setSkills(String skills) {
        this.skills = skills;
    }
    
    public String getDesc() {
        return desc;
    }
    
    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    
    public String getUserid() {
        return userid;
    }
    
    public void setUserid(String userid) {
        this.userid = userid;
    }
    
    public StringBuilder getStringBuilder() {
        return stringBuilder;
    }
    
    public void setStringBuilder(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
    }
    
    public JobVTO getCurrentJobVTO() {
        return currentJobVTO;
    }
    
    public void setCurrentJobVTO(JobVTO currentJobVTO) {
        this.currentJobVTO = currentJobVTO;
    }
    
    public String getJtitle() {
        return jtitle;
    }
    
    public void setJtitle(String jtitle) {
        this.jtitle = jtitle;
    }
    
    public int getJnumber() {
        return jnumber;
    }
    
    public void setJnumber(int jnumber) {
        this.jnumber = jnumber;
    }
    
    public int getJobId() {
        return jobId;
    }
    
    public void setJobId(int jobId) {
        this.jobId = jobId;
    }
    
    public String getAddOrUpdate() {
        return addOrUpdate;
    }
    
    public void setAddOrUpdate(String addOrUpdate) {
        this.addOrUpdate = addOrUpdate;
    }
    
    public Timestamp getCreatedtime() {
        return createdtime;
    }
    
    public void setCreatedtime(Timestamp createdtime) {
        this.createdtime = createdtime;
    }

     public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

   
    
    
    
}


