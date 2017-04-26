/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.bms;

import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.DateUtility;
import com.opensymphony.xwork2.ActionSupport;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

/**
 *
 * @author miracle
 */
public class BridgeManagementAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
    /*@param httpServletRequest to set resultMessage and that will get in login page*/

    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private String resultType;
    private String startDate;
    private List timeList;
    private String bridgeDate;
    private String bridgeNumber;
    private String bridgeName;
    private String bridgeStatus;
    private String bmsBridgeStatus;
    private String bridgeCode;
    private Map employeeEmails = new HashMap();
    private Map taskAssignToMap = new HashMap();

    public String execute() {
        resultType = LOGIN;

        try {

            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

                setBridgeDate(DateUtility.getInstance().getCurrentMySqlDate());
                setTimeList(DataSourceDataProvider.getInstance().getTimeList());
                setTaskAssignToMap(DataSourceDataProvider.getInstance().getTaskEmpDetailsBasedOnIssueRel("3"));
                setEmployeeEmails(DataSourceDataProvider.getInstance().getEmpEmails());
                resultType = SUCCESS;

            }//Closing Session checking
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
            resultType = ERROR;
        }
        return resultType;
    }

    public String doBridgeSearch() {
        resultType = LOGIN;
        String queryString = "";
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {

                queryString = "SELECT BCode,BNumber,BName,STATUS ,CreatedBy,CreatedDate,PassCode FROM tblBridges WHERE 1=1 ";



                if (!"".equals(getBridgeCode()) && getBridgeCode() != null) {
                    queryString = queryString + " AND BCode like '%" + getBridgeCode().trim() + "%'";
                }

                if (!"".equals(getBmsBridgeStatus()) && getBmsBridgeStatus() != null) {
                    queryString = queryString + " AND STATUS like '" + getBmsBridgeStatus().trim() + "'";
                }

                queryString = queryString + " ORDER BY  STATUS ASC,CreatedDate DESC";
              //  System.out.println("queryString----" + queryString);
                httpServletRequest.setAttribute(ApplicationConstants.QUERY_STRING, queryString);
                resultType = SUCCESS;
            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                ex.printStackTrace();
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }


        }
        return resultType;


    }

    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    /**
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the timeList
     */
    public List getTimeList() {
        return timeList;
    }

    /**
     * @param timeList the timeList to set
     */
    public void setTimeList(List timeList) {
        this.timeList = timeList;
    }

    /**
     * @return the bridgeDate
     */
    public String getBridgeDate() {
        return bridgeDate;
    }

    /**
     * @param bridgeDate the bridgeDate to set
     */
    public void setBridgeDate(String bridgeDate) {
        this.bridgeDate = bridgeDate;
    }

    /**
     * @return the bridgeName
     */
    public String getBridgeName() {
        return bridgeName;
    }

    /**
     * @param bridgeName the bridgeName to set
     */
    public void setBridgeName(String bridgeName) {
        this.bridgeName = bridgeName;
    }

    /**
     * @return the bridgeStatus
     */
    public String getBridgeStatus() {
        return bridgeStatus;
    }

    /**
     * @param bridgeStatus the bridgeStatus to set
     */
    public void setBridgeStatus(String bridgeStatus) {
        this.bridgeStatus = bridgeStatus;
    }

    /**
     * @return the bmsBridgeStatus
     */
    public String getBmsBridgeStatus() {
        return bmsBridgeStatus;
    }

    /**
     * @param bmsBridgeStatus the bmsBridgeStatus to set
     */
    public void setBmsBridgeStatus(String bmsBridgeStatus) {
        this.bmsBridgeStatus = bmsBridgeStatus;
    }

    /**
     * @return the bridgeNumber
     */
    public String getBridgeNumber() {
        return bridgeNumber;
    }

    /**
     * @param bridgeNumber the bridgeNumber to set
     */
    public void setBridgeNumber(String bridgeNumber) {
        this.bridgeNumber = bridgeNumber;
    }

    /**
     * @return the bridgeCode
     */
    public String getBridgeCode() {
        return bridgeCode;
    }

    /**
     * @param bridgeCode the bridgeCode to set
     */
    public void setBridgeCode(String bridgeCode) {
        this.bridgeCode = bridgeCode;
    }

    /**
     * @return the taskAssignToMap
     */
    public Map getTaskAssignToMap() {
        return taskAssignToMap;
    }

    /**
     * @param taskAssignToMap the taskAssignToMap to set
     */
    public void setTaskAssignToMap(Map taskAssignToMap) {
        this.taskAssignToMap = taskAssignToMap;
    }

    /**
     * @return the employeeEmails
     */
    public Map getEmployeeEmails() {
        return employeeEmails;
    }

    /**
     * @param employeeEmails the employeeEmails to set
     */
    public void setEmployeeEmails(Map employeeEmails) {
        this.employeeEmails = employeeEmails;
    }
}
