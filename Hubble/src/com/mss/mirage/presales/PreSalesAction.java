/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.presales;

import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.AuthorizationManager;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.DataUtility;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.HibernateDataProvider;
import com.mss.mirage.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author miracle
 */
public class PreSalesAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private String resultType;
    private String responseString;
    private String type;
    private int userRoleId;
    /**
     * Creating a String stage used to store in Database
     */
    private String state;
    /**
     * Creating a String dueStartDate used to store in Database
     */
    private String dueStartDate;
    /**
     * Creating a String dueEndDate used to store in Database
     */
    private String dueEndDate;
    private List opportunityStateList;
    private Map myTeamMembersList;
    private Map myTeamMembers;
    private List typeList = new ArrayList();
    private List practiceIdOppList = new ArrayList();
    private List assignedMembers;
    private List techLeadList;
    private Map createdMemebers;
    private Map assignedByMap;

    public String doGetPreSalesDashBoard() {

        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            try {

                // test 
                resultType = SUCCESS;

            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }
        }//Close Session Checking
        return resultType;
    }

    /* get presales oopportunity details */
    public String myOpportunities() {

        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            resultType = "accessFailed";
            Map map = new HashMap();
            if (AuthorizationManager.getInstance().isAuthorizedUser("PRESALES_DASHBOARD_ACCESS", userRoleId)) {
                try {

                    String departmentId = DataSourceDataProvider.getInstance().getDepartmentName(loginId);
                    setOpportunityStateList(DataSourceDataProvider.getInstance().getOpportunityStateList());
                    setMyTeamMembers(DataUtility.getInstance().getMapSortByValue(DataSourceDataProvider.getInstance().getMyTeamList(loginId)));
                    map = DataUtility.getInstance().getMapSortByValue(DataSourceDataProvider.getInstance().getMyTeamMembers(loginId, departmentId));
                    map.put(loginId, DataSourceDataProvider.getInstance().getemployeenamebyloginId(loginId));
                    setTypeList(HibernateDataProvider.getInstance().getOpportunityType(ApplicationConstants.CRM_OPS_TYPE_OPTIONS));
                    setMyTeamMembersList(map);
                    setPracticeIdOppList(DataSourceDataProvider.getInstance().getPracticeByDepartment("GDC"));
                    
                    
 Map rolesMap=(Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_ROLES);
                    
                    //isAuthorizedForSurveyForm
                    
                     if(rolesMap.containsValue("Admin")|| AuthorizationManager.getInstance().isAuthorizedForSurveyForm("PRESALES_REQUIREMENT_ACCESS", httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString()))
                     {
                              //   setAnalystMap(DataSourceDataProvider.getInstance().getMarketingAnalystMap());
                                 setTechLeadList(DataSourceDataProvider.getInstance().getTechLead());
                     }else {
                          Map teamMap=(Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP);
                         List TechLeadList=DataSourceDataProvider.getInstance().getTechLead();
                         List tempTechLeadList = new ArrayList();
                          		for (int i = 0; i < TechLeadList.size(); i++) {
                                            if(teamMap.containsValue(TechLeadList.get(i))){
                                                tempTechLeadList.add(TechLeadList.get(i));
                                            }
                                        }
                         tempTechLeadList.add(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME));
                          
                          setTechLeadList(tempTechLeadList);
                     }
setCreatedMemebers(DataSourceDataProvider.getInstance().getEmployeeNamesBySalesRoleId());
                    
                    
                    resultType = SUCCESS;
                } catch (Exception ex) {
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }

            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }

    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the dueStartDate
     */
    public String getDueStartDate() {
        return dueStartDate;
    }

    /**
     * @return the dueEndDate
     */
    public String getDueEndDate() {
        return dueEndDate;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the responseString
     */
    public String getResponseString() {
        return responseString;
    }

    /**
     * @param responseString the responseString to set
     */
    public void setResponseString(String responseString) {
        this.responseString = responseString;
    }

    /**
     * @return the userRoleId
     */
    public int getUserRoleId() {
        return userRoleId;
    }

    /**
     * @param userRoleId the userRoleId to set
     */
    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    /**
     * @return the opportunityStateList
     */
    public List getOpportunityStateList() {
        return opportunityStateList;
    }

    /**
     * @param opportunityStateList the opportunityStateList to set
     */
    public void setOpportunityStateList(List opportunityStateList) {
        this.opportunityStateList = opportunityStateList;
    }

    /**
     * @return the myTeamMembers
     */
    public Map getMyTeamMembers() {
        return myTeamMembers;
    }

    /**
     * @param myTeamMembers the myTeamMembers to set
     */
    public void setMyTeamMembers(Map myTeamMembers) {
        this.myTeamMembers = myTeamMembers;
    }

    /**
     * @return the myTeamMembersList
     */
    public Map getMyTeamMembersList() {
        return myTeamMembersList;
    }

    /**
     * @param myTeamMembersList the myTeamMembersList to set
     */
    public void setMyTeamMembersList(Map myTeamMembersList) {
        this.myTeamMembersList = myTeamMembersList;
    }

    /**
     * @return the typeList
     */
    public List getTypeList() {
        return typeList;
    }

    /**
     * @param typeList the typeList to set
     */
    public void setTypeList(List typeList) {
        this.typeList = typeList;
    }

    /**
     * @return the practiceIdOppList
     */
    public List getPracticeIdOppList() {
        return practiceIdOppList;
    }

    /**
     * @param practiceIdOppList the practiceIdOppList to set
     */
    public void setPracticeIdOppList(List practiceIdOppList) {
        this.practiceIdOppList = practiceIdOppList;
    }

    /**
     * @return the assignedMembers
     */
    public List getAssignedMembers() {
        return assignedMembers;
    }

    /**
     * @param assignedMembers the assignedMembers to set
     */
    public void setAssignedMembers(List assignedMembers) {
        this.assignedMembers = assignedMembers;
    }

    /**
     * @return the techLeadList
     */
    public List getTechLeadList() {
        return techLeadList;
    }

    /**
     * @param techLeadList the techLeadList to set
     */
    public void setTechLeadList(List techLeadList) {
        this.techLeadList = techLeadList;
    }

    /**
     * @return the createdMemebers
     */
    public Map getCreatedMemebers() {
        return createdMemebers;
    }

    /**
     * @param createdMemebers the createdMemebers to set
     */
    public void setCreatedMemebers(Map createdMemebers) {
        this.createdMemebers = createdMemebers;
    }

    /**
     * @return the assignedByMap
     */
    public Map getAssignedByMap() {
        return assignedByMap;
    }

    /**
     * @param assignedByMap the assignedByMap to set
     */
    public void setAssignedByMap(Map assignedByMap) {
        this.assignedByMap = assignedByMap;
    }
}
