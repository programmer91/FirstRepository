/*
 * BridgeConferenceAction.java
 *
 * Created on April 3, 2008, 7:14 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.employee.bridgeconference;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.ServiceLocatorException;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Date;
import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.AuthorizationManager;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.ServiceLocator;
import java.util.LinkedList;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import java.sql.*;
import com.mss.mirage.util.ServiceLocator;
import com.mss.mirage.util.ConnectionProvider;
import java.util.List;


/**
 *
 * @author miracle
 */
public class BridgeConferenceAction extends  ActionSupport implements ServletRequestAware {
    
    private String contactNo;
    private String  bridgeNumber;
    private String engagedBy;
    private Date date;
    private String startTime;
    private String endTime;
    private String title;
    private String listOfEmailAdd;
    private String status;
    private String currentAction;
    private HttpServletRequest httpServletRequest;
    private String resultType;
    private int userRoleId;
    private BridgeConferenceVTO bridgeConferenceVTO;
    private int bridgeId;
    private List bridgeNumbers;
    private String senderMail;
    
    /** Creates a new instance of BridgeConferenceAction */
    public BridgeConferenceAction() {
    }
    
    public String getContactNo() {
        return contactNo;
    }
    
    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
    
    public String getBridgeNumber() {
        return bridgeNumber;
    }
    
    public void setBridgeNumber(String bridgeNumber) {
        this.bridgeNumber = bridgeNumber;
    }
    
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public String getStartTime() {
        return startTime;
    }
    
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    
    public String getEndTime() {
        return endTime;
    }
    
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getListOfEmailAdd() {
        return listOfEmailAdd;
    }
    
    public void setListOfEmailAdd(String listOfEmailAdd) {
        this.listOfEmailAdd = listOfEmailAdd;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.setHttpServletRequest(httpServletRequest);
    }
    
    public String prepare() throws Exception {
        setBridgeNumbers(DataSourceDataProvider.getInstance().getBridgeNos());
        setCurrentAction("AddBridgeSchedule");
        return SUCCESS;
    }
    
    public String doAdd(){ // adding of phonelog
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            
            try{
                boolean isInserted=false;
                String resultMessage="";
                isInserted = ServiceLocator.getBridgeConferenceService().addBridgeSchedule(this);
                
                if(isInserted){
                    
                    SendMail sMail=new SendMail();
                    sMail.sendMails(contactNo,bridgeNumber,engagedBy,date,DateUtility.getInstance().strToTimeStampObj(startTime),DateUtility.getInstance().strToTimeStampObj(endTime),title,listOfEmailAdd,status,senderMail);
                    resultMessage="Successfully PhoneLog added";
                }else{
                    resultMessage="please Try again";
                    resultType = INPUT;
                }
                getHttpServletRequest().setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
                resultType=SUCCESS;
                //Exception Handling
            }catch (Exception ex){
                getHttpServletRequest().getSession(false).setAttribute("errorMessage",ex.toString());
                resultType =  ERROR;
            }
            
        }
        return resultType;
        
    }
    
    public String doEdit(){ // adding of phonelog
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            
            try{
                boolean isInserted=false;
                String resultMessage="";
                isInserted = ServiceLocator.getBridgeConferenceService().editBridgeSchedule(this,getBridgeId());
                if(isInserted){
                    resultMessage="Successfully bridge updated";
                }else{
                    resultMessage="please Try again";
                    resultType = INPUT;
                }
                setBridgeConferenceVTO(ServiceLocator.getBridgeConferenceService().getBridge(getBridgeId()));
                setCurrentAction("EditBridgeSchedule");
                setBridgeNumbers(DataSourceDataProvider.getInstance().getBridgeNos());
                getHttpServletRequest().setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
                resultType=SUCCESS;
                //Exception Handling
            }catch (Exception ex){
                getHttpServletRequest().getSession(false).setAttribute("errorMessage",ex.toString());
                resultType =  ERROR;
            }
            
        }
        return resultType;
        
    }
    
    public String doDelete(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            
       /* if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("BRIDGECONFERENCE_DELETE",userRoleId)){*/
            try {
                boolean isDeleted=false;
                String resultMessage="";
                isDeleted=ServiceLocator.getBridgeConferenceService().delete(getBridgeId());
                if(isDeleted){
                    resultMessage="Successfully Bridgeshedule deleted";
                    
                }else{
                    resultMessage="please Try again";
                    resultType = INPUT;
                }
                getHttpServletRequest().setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
                resultType=SUCCESS;
            }catch (Exception ex){
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                getHttpServletRequest().getSession(false).setAttribute("errorMessage",ex.toString());
                resultType=ERROR;
            }
        }
        return resultType;
    }
    
    public String execute() throws Exception {
        
        return SUCCESS;
    }
    
    public String getBridgeConference(){
        
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            
            if(getHttpServletRequest().getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
                userRoleId = Integer.parseInt(getHttpServletRequest().getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
                
                try{
                    setBridgeConferenceVTO(ServiceLocator.getBridgeConferenceService().getBridge(getBridgeId()));
                    setCurrentAction("EditBridgeSchedule");
                    setBridgeNumbers(DataSourceDataProvider.getInstance().getBridgeNos());
                    return SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    getHttpServletRequest().getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
                
            }
        }
        return resultType;
    }
    
    
    public BridgeConferenceVTO getBridgeConferenceVTO() {
        return bridgeConferenceVTO;
    }
    
    public void setBridgeConferenceVTO(BridgeConferenceVTO bridgeConferenceVTO) {
        this.bridgeConferenceVTO = bridgeConferenceVTO;
    }
    
    public String getCurrentAction() {
        return currentAction;
    }
    
    public void setCurrentAction(String currentAction) {
        this.currentAction = currentAction;
    }
    
    public List getBridgeNumbers() {
        return bridgeNumbers;
    }
    
    public void setBridgeNumbers(List bridgeNumbers) {
        this.bridgeNumbers = bridgeNumbers;
    }
    
    private HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }
    
    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }
    
    public String getSenderMail() {
        return senderMail;
    }
    
    public void setSenderMail(String senderMail) {
        this.senderMail = senderMail;
    }
    
    public String getEngagedBy() {
        return engagedBy;
    }
    
    public void setEngagedBy(String engagedBy) {
        this.engagedBy = engagedBy;
    }
    
    public int getBridgeId() {
        return bridgeId;
    }
    
    public void setBridgeId(int bridgeId) {
        this.bridgeId = bridgeId;
    }
    
}
