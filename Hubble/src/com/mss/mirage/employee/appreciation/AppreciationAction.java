/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.employee.appreciation;

import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.AuthorizationManager;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.MailManager;
import com.mss.mirage.util.Properties;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.mss.mirage.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author miracle
 */
public class AppreciationAction extends ActionSupport implements ServletRequestAware {

    private int empId;
    private String resultType;
    private HttpServletRequest httpServletRequest;
    private String createdBy;
    private String submitFrom;
    private String currentAction;
    private String resultMessage;
    private int userRoleId;
    private Map empnamesList;
    private String startDate;
    private String endDate;
    private String title;
    private String subtitle;
    private String status;
    private String empnameById;
    private String appreciationTitle;
    private String subAppreciationTitle;
    private String appreciationSubject;
    private String appreciationTo;
    private String appreciationCC;
    private String appreciationBCC;
    private String appreciationBodyContent;
    private Map employeeEmails;
    private Map AppreciationEmailMap;
    private String CCHidden;
    private String BCCHidden;
    private String ToHidden;
    private String currentDate;
    private String sendOrSave;
    private String emailsForTo;
    private int appriceationId;
    private List appreciationEmpList;
    private List appreciationToList;
    private List appreciationCCList;
    private List appreciationBCCList;
    private String bodyContent;
    private String searchflag;
    private String designation;
    private String userName;

    public String getSearchflag() {
        return searchflag;
    }

    public void setSearchflag(String searchflag) {
        this.searchflag = searchflag;
    }

public String getMyAppreciation() {
         resultType = LOGIN;
        String myteam = " ";
        String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
        int  roleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());

        StringBuffer sb = new StringBuffer();
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
           String isManager=httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString();           
           String isLead=httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_TEAM_LEAD).toString();
           setSearchflag(getSearchflag());
            if (AuthorizationManager.getInstance().isAuthorizedUser("GET_EMP_APPRECIATION_SEARCH_ALL", userRoleId)) {
                try {
                    if ("my".equalsIgnoreCase(searchflag)) {
                       if(roleId==2)
                       {
                        setEndDate(DateUtility.getInstance().getCurrentMySqlDate());                                   
                        setStartDate(DateUtility.getInstance().getLastThirtyDaysDateFromCurrentDate());
                        sb.append("SELECT Id,Title,SubTitle,CreatedDate,CreatedBy FROM tblEmpAppreciation WHERE 1=1");
                        sb.append(" AND MATCH(EmployeesList) AGAINST ('" + loginId + "' IN BOOLEAN MODE)");
                        sb.append(" AND (DATE(CreatedDate) >= '" + DateUtility.getInstance().convertStringToMySQLDate(getStartDate()) + "') AND (DATE(CreatedDate) <= '" + DateUtility.getInstance().convertStringToMySQLDate(getEndDate()) + "') AND STATUS LIKE '%Send%'");
                        sb.append("ORDER BY CreatedDate DESC LIMIT 200");
                     
                        httpServletRequest.setAttribute(ApplicationConstants.APPRECIATION_SEARCH, sb);
                        resultType=SUCCESS;

                       }
                     
                    }
                    else if("team".equalsIgnoreCase(searchflag)) {
                        if (((isManager.equalsIgnoreCase("1")) || (isLead.equalsIgnoreCase("1"))) && (roleId==2)) {
                         
                       // System.out.println("in team");
                        setEmpnamesList((Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP_FOR_LEAVESEARCH));
                        setEndDate(DateUtility.getInstance().getCurrentMySqlDate());                                   
                        setStartDate(DateUtility.getInstance().getLastThirtyDaysDateFromCurrentDate());
                        sb.append("SELECT Id,Title,SubTitle,STATUS,CreatedDate,CreatedBy FROM tblEmpAppreciation WHERE 1=1 ");
                        sb.append(" AND (STATUS='Send' OR CreatedBy= '"+loginId+"') ");
                        sb.append(" AND (DATE(CreatedDate) >= '" + DateUtility.getInstance().convertStringToMySQLDate(getStartDate()) + "') AND (DATE(CreatedDate) <= '" + DateUtility.getInstance().convertStringToMySQLDate(getEndDate()) + "') ");
                        Map myTeamMemebrs = (Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP_FOR_LEAVESEARCH);
                        String teamList = DataSourceDataProvider.getInstance().getTeamLoginIdList(myTeamMemebrs);
                     
                        String team = teamList.replaceAll("'", "").replaceAll(",", " ");
         
                        sb.append(" AND MATCH(EmployeesList) AGAINST ('" + team + "' IN BOOLEAN MODE) ");
                      

                        sb.append(" ORDER BY CreatedDate DESC LIMIT 200");

                        httpServletRequest.setAttribute(ApplicationConstants.APPRECIATION_SEARCH, sb);
                        resultType=SUCCESS;
                         }
                        
                    }
                   else if ("opt".equalsIgnoreCase(searchflag)) {                         
                  
                            if(roleId==3) 
                            {
                              
                            //    System.out.println("in else since ");
                        setEndDate(DateUtility.getInstance().getCurrentMySqlDate());                                   
                        setStartDate(DateUtility.getInstance().getLastThirtyDaysDateFromCurrentDate());
                         String  workingCountry = (String)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
                         setEmpnamesList(DataSourceDataProvider.getInstance().getEmployeeNamesByLoginId(workingCountry));
                        sb.append("SELECT Id,Title,SubTitle,STATUS,CreatedDate,CreatedBy FROM tblEmpAppreciation WHERE 1=1 ");
                          sb.append(" AND (STATUS='Send' OR CreatedBy = '"+loginId+"') ");
                        sb.append(" AND (DATE(CreatedDate) >= '" + DateUtility.getInstance().convertStringToMySQLDate(getStartDate()) + "') AND (DATE(CreatedDate) <= '" + DateUtility.getInstance().convertStringToMySQLDate(getEndDate()) + "') ");
                      
                        Map myTeamMemebrs = (Map) DataSourceDataProvider.getInstance().getEmployeeNamesByLoginId(workingCountry);
                        String teamList = DataSourceDataProvider.getInstance().getTeamLoginIdList(myTeamMemebrs);
                
                        String team = teamList.replaceAll("'", "").replaceAll(",", " ");
               
                        sb.append(" AND MATCH(EmployeesList) AGAINST ('" + team + "' IN BOOLEAN MODE)");
                    
                        sb.append(" ORDER BY CreatedDate DESC LIMIT 200");
                      
                        httpServletRequest.setAttribute(ApplicationConstants.APPRECIATION_SEARCH, sb);
                        resultType=SUCCESS;
                    }
                  
                    }

                
                else{
            resultType = "accessFailed";
            
            }}
                catch (Exception ex) {
                    ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType=ERROR;
                }

            }
            
        }
        return resultType;
    }



    public String getAppreciationadd() {
      
         resultType=LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("GET_EMP_APPRECIATION_SEARCH_ALL", userRoleId)) {

                try {
                    setAppriceationId(0);
                    setCurrentAction("doAddAppreciation");
                    setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                    if ("team".equalsIgnoreCase(getSearchflag())) {
                        setEmpnamesList((Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP_FOR_LEAVESEARCH));
                    }
                   if ("opt".equalsIgnoreCase(getSearchflag())) {
                        String  workingCountry = (String)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
                        setEmpnamesList((Map) DataSourceDataProvider.getInstance().getEmployeeNamesByLoginId(workingCountry));
                   }
                    
                    DataSourceDataProvider dataSourceDataProvider = null;
                    employeeEmails = dataSourceDataProvider.getInstance().getEmpEmails();
                    resultType=SUCCESS;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType=ERROR;
                }

            }
        }
        return resultType;
    }

    public String doSendAppreciation() {
 resultType=LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
        if (AuthorizationManager.getInstance().isAuthorizedUser("GET_EMP_APPRECIATION_SEARCH_ALL", userRoleId)) {
            try {
                setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                DataSourceDataProvider dataSourceDataProvider = null;
                String responseText="";
                StringTokenizer stk2 = new StringTokenizer(getToHidden(), ",");
                StringTokenizer loginId = stk2;
                String empName = "";
                String empDesignation = "";
                String createdByName = "";
                int insertedRows = ServiceLocator.getAppreciationService().insertAppreciationService(this);
                if(insertedRows>0)
                {
                while (stk2.hasMoreTokens()) {
                    empName += dataSourceDataProvider.getInstance().userName(((stk2.nextToken()))) + ",";
                }
                empName = StringUtils.chop(empName);
                empDesignation = dataSourceDataProvider.getInstance().getDesignation(((getCreatedBy())));
                createdByName = dataSourceDataProvider.getInstance().getFullNameBYLoginId(((getCreatedBy())));
                setUserName(empName);
                if ("Send".equalsIgnoreCase(getSendOrSave())) {
                    Map emailMap = new HashMap();
                    emailMap.put("To", getToHidden());
                    emailMap.put("LoginId", getToHidden());
                    emailMap.put("CC", getCCHidden());
                    emailMap.put("BCC", getBCCHidden());
                    emailMap.put("Title", getAppreciationTitle());
                    emailMap.put("SubTitle", getSubAppreciationTitle());
                    emailMap.put("Subject", getAppreciationSubject());
                    emailMap.put("BodyContent", getAppreciationBodyContent());
                    emailMap.put("createdBy", createdByName);
                    emailMap.put("userName", getUserName());
                     emailMap.put("createdByLogin", getCreatedBy());
                    emailMap.put("Designation", empDesignation);
                    emailMap.put("Searchflag", getSearchflag());
                    
               //     System.out.println(emailMap);
                    setAppreciationEmailMap(emailMap);
                    if (Properties.getProperty("Mail.Flag").equals("1")) {
                        responseText = MailManager.sendAppreciationEmailDetails(getAppreciationEmailMap());
                    }
                    setResultMessage("Appreciation Send Successfully");
                }
                }
                if (getSendOrSave().equalsIgnoreCase("Created")) {
                    setResultMessage("Appreciation Created Successfully");
                } if (getSendOrSave().equalsIgnoreCase("Created") && this.getAppriceationId() > 0 ) {
                    setResultMessage("Appreciation Updated Successfully");
                }


  if (this.getAppriceationId() > 0) {
                    resultType = INPUT;
                } else {
                    resultType = SUCCESS;
                }
               
            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                ex.printStackTrace();
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                
                resultType=ERROR;
            }

        }}
     //   System.out.println("getResultType.." + getResultType());
        return resultType;
    }


    public String doSearchAppreciation() {
      //  System.out.println("Entered into doSearchAppreciation");
        resultType = LOGIN;
        String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            try {
                setSearchflag(getSearchflag());
         //       System.out.println("test---->" + getSearchflag());
                if ("my".equalsIgnoreCase(getSearchflag())) {
                     setEmpnamesList((Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP_FOR_LEAVESEARCH));
                    StringBuffer queryString = new StringBuffer();
                    queryString.append("SELECT Id,Title,SubTitle,CreatedDate,CreatedBy");
                    queryString.append(" FROM tblEmpAppreciation");

                    queryString.append(" WHERE 1=1");
                    if (getStartDate() != null && !"".equals(getStartDate()) && getEndDate() != null && !"".equals(getEndDate())) {
                        queryString = queryString.append(" AND (DATE(CreatedDate) >= '" + DateUtility.getInstance().convertStringToMySQLDate(getStartDate()) + "') AND (DATE(CreatedDate) <= '" + DateUtility.getInstance().convertStringToMySQLDate(getEndDate()) + "') ");
                    }

                    if (getTitle() != null && !"".equals(getTitle())) {
                        queryString.append(" AND Title LIKE '%" + getTitle().trim() + "%'");
                    }
                    if (getSubtitle() != null && !"".equals(getSubtitle())) {
                        queryString.append(" AND SubTitle LIKE '%" + getSubtitle().trim() + "%'");
                    }
                    if (loginId != null && !"".equals(loginId)) {
                        queryString.append(" AND MATCH(EmployeesList) AGAINST ('" + loginId + "' IN BOOLEAN MODE)  AND STATUS LIKE '%Send%' ORDER BY CreatedDate DESC LIMIT 200");
                    }

                    httpServletRequest.setAttribute(ApplicationConstants.APPRECIATION_SEARCH, queryString);


                }




                if ("team".equalsIgnoreCase(getSearchflag())) {
                    setEndDate(DateUtility.getInstance().getCurrentMySqlDate());                                   
                        setStartDate(DateUtility.getInstance().getLastThirtyDaysDateFromCurrentDate());

                    setEmpnamesList((Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP_FOR_LEAVESEARCH));
                    String employeeId = empnameById;
                    StringBuffer queryString = new StringBuffer();
                    queryString.append("SELECT Id,Title,SubTitle,STATUS,CreatedDate,CreatedBy");
                    queryString.append(" FROM tblEmpAppreciation");

                    queryString.append(" WHERE 1=1");
                    if (getStatus() != null && !"".equals(getStatus())  && !"-1".equalsIgnoreCase(getStatus())) {
                        queryString.append(" AND STATUS LIKE '%" + getStatus().trim() + "%'");
                    }
                    if (getStartDate() != null && !"".equals(getStartDate()) && getEndDate() != null && !"".equals(getEndDate())) {
                        queryString = queryString.append(" AND (DATE(CreatedDate) >= '" + DateUtility.getInstance().convertStringToMySQLDate(getStartDate()) + "') AND (DATE(CreatedDate) <= '" + DateUtility.getInstance().convertStringToMySQLDate(getEndDate()) + "') ");
                    }

                    if (getTitle() != null && !"".equals(getTitle())) {
                        queryString.append(" AND Title LIKE '%" + getTitle().trim() + "%'");
                    }
                    if (getSubtitle() != null && !"".equals(getSubtitle())) {
                        queryString.append(" AND SubTitle LIKE '%" + getSubtitle().trim() + "%'");
                    }

                    employeeId = " +" + employeeId;
                    if (!"".equalsIgnoreCase(getEmpnamesList().toString()) && !"-1".equalsIgnoreCase(getEmpnameById().toString())) {
                        queryString.append(" AND MATCH(EmployeesList) AGAINST ('" + employeeId + "' IN BOOLEAN MODE) ORDER BY CreatedDate DESC LIMIT 200");
                    }
                    if (!"".equalsIgnoreCase(getEmpnamesList().toString()) && "-1".equalsIgnoreCase(getEmpnameById().toString())) {
                        Map myTeamMemebrs = (Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP_FOR_LEAVESEARCH);
                        String teamList = DataSourceDataProvider.getInstance().getTeamLoginIdList(myTeamMemebrs);
                        String team = teamList.replaceAll("'", "").replaceAll(",", " ");
                        queryString.append(" AND MATCH(EmployeesList) AGAINST ('" + team + "' IN BOOLEAN MODE) ORDER BY CreatedDate DESC LIMIT 200");
                    }
                    httpServletRequest.setAttribute(ApplicationConstants.APPRECIATION_SEARCH, queryString);

                }


                if ("opt".equalsIgnoreCase(getSearchflag())) {
                    String  workingCountry = (String)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
                    setEmpnamesList(DataSourceDataProvider.getInstance().getEmployeeNamesByLoginId(workingCountry));

                     setEndDate(DateUtility.getInstance().getCurrentMySqlDate());                                   
                        setStartDate(DateUtility.getInstance().getLastThirtyDaysDateFromCurrentDate());


                    String employeeId = empnameById;
                    StringBuffer queryString = new StringBuffer();
                    queryString.append("SELECT Id,Title,SubTitle,STATUS,CreatedDate,CreatedBy");
                    queryString.append(" FROM tblEmpAppreciation");

                    queryString.append(" WHERE 1=1");
                    if (getStatus() != null && !"".equals(getStatus())  && !"-1".equalsIgnoreCase(getStatus())) {
                        queryString.append(" AND STATUS LIKE '%" + getStatus().trim() + "%'");
                    }
                    if (getStartDate() != null && !"".equals(getStartDate()) && getEndDate() != null && !"".equals(getEndDate())) {
                        queryString = queryString.append(" AND (DATE(CreatedDate) >= '" + DateUtility.getInstance().convertStringToMySQLDate(getStartDate()) + "') AND (DATE(CreatedDate) <= '" + DateUtility.getInstance().convertStringToMySQLDate(getEndDate()) + "') ");
                    }

                    if (getTitle() != null && !"".equals(getTitle())) {
                        queryString.append(" AND Title LIKE '%" + getTitle().trim() + "%'");
                    }
                    if (getSubtitle() != null && !"".equals(getSubtitle())) {
                        queryString.append(" AND SubTitle LIKE '%" + getSubtitle().trim() + "%'");
                    }

                    employeeId = " +" + employeeId;
                    if (!"".equalsIgnoreCase(getEmpnamesList().toString()) && !"-1".equalsIgnoreCase(getEmpnameById().toString())) {
                        queryString.append(" AND MATCH(EmployeesList) AGAINST ('" + employeeId + "' IN BOOLEAN MODE) ORDER BY CreatedDate DESC LIMIT 200");
                    }
                    if (!"".equalsIgnoreCase(getEmpnamesList().toString()) && "-1".equalsIgnoreCase(getEmpnameById().toString())) {
                        Map myTeamMemebrs = (Map) DataSourceDataProvider.getInstance().getEmployeeNamesByLoginId(workingCountry);
                        String teamList = DataSourceDataProvider.getInstance().getTeamLoginIdList(myTeamMemebrs);
                        String team = teamList.replaceAll("'", "").replaceAll(",", " ");
                        queryString.append(" AND MATCH(EmployeesList) AGAINST ('" + team + "' IN BOOLEAN MODE) ORDER BY CreatedDate DESC LIMIT 200");
                    }
                    httpServletRequest.setAttribute(ApplicationConstants.APPRECIATION_SEARCH, queryString);

                }

                resultType = SUCCESS;
            } catch (Exception ex) {
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
                //   }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }

    public String getAppreciationDetailsById() {

        resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());

            //  resultType = "accessFailed";
            try {
                    
//                  if ("my".equalsIgnoreCase(getSearchflag())) {
//                 setEmpnamesList((Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP_FOR_LEAVESEARCH));
//                  }
               if ("team".equalsIgnoreCase(getSearchflag())) {
                        setEmpnamesList((Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP_FOR_LEAVESEARCH));
                    }
                   if ("opt".equalsIgnoreCase(getSearchflag())) {
                        String  workingCountry = (String)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
                        setEmpnamesList((Map) DataSourceDataProvider.getInstance().getEmployeeNamesByLoginId(workingCountry));
                   }

                DataSourceDataProvider dataSourceDataProvider = null;
                employeeEmails = dataSourceDataProvider.getInstance().getEmpEmails();
                String result = ServiceLocator.getAppreciationService().getAppreciationDetailsById(this);
                resultType = SUCCESS;
                //System.out.println("resultType-->" + resultType);
            } catch (Exception ex) {
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;

            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }

    public String getAppreciationPreview() {
        //  System.out.println("entered into the dogetAppreciationPreview"+appreciationId);
       
        resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("GET_EMP_APPRECIATION_SEARCH_ALL", userRoleId)) {

                try {
                    ServiceLocator.getAppreciationService().getAppreciationEmailValues(this);
                     resultType = SUCCESS;
                   
                } catch (Exception ex) {
                    ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                     resultType = ERROR;
                }

            }
        }
        return resultType;
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    /**
     * @return the empId
     */
    public int getEmpId() {
        return empId;
    }

    /**
     * @param empId the empId to set
     */
    public void setEmpId(int empId) {
        this.empId = empId;
    }

    /**
     * @return the resultType
     */
    public String getResultType() {
        return resultType;
    }

    /**
     * @param resultType the resultType to set
     */
    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    /**
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the submitFrom
     */
    public String getSubmitFrom() {
        return submitFrom;
    }

    /**
     * @param submitFrom the submitFrom to set
     */
    public void setSubmitFrom(String submitFrom) {
        this.submitFrom = submitFrom;
    }

    /**
     * @return the currentAction
     */
    public String getCurrentAction() {
        return currentAction;
    }

    /**
     * @param currentAction the currentAction to set
     */
    public void setCurrentAction(String currentAction) {
        this.currentAction = currentAction;
    }

    /**
     * @return the resultMessage
     */
    public String getResultMessage() {
        return resultMessage;
    }

    /**
     * @param resultMessage the resultMessage to set
     */
    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    /**
     * @return the empnamesList
     */
    public Map getEmpnamesList() {
        return empnamesList;
    }

    /**
     * @param empnamesList the empnamesList to set
     */
    public void setEmpnamesList(Map empnamesList) {
        this.empnamesList = empnamesList;
    }

    /**
     * @return the appreciationTitle
     */
    public String getAppreciationTitle() {
        return appreciationTitle;
    }

    /**
     * @param appreciationTitle the appreciationTitle to set
     */
    public void setAppreciationTitle(String appreciationTitle) {
        this.appreciationTitle = appreciationTitle;
    }

    /**
     * @return the subAppreciationTitle
     */
    public String getSubAppreciationTitle() {
        return subAppreciationTitle;
    }

    /**
     * @param subAppreciationTitle the subAppreciationTitle to set
     */
    public void setSubAppreciationTitle(String subAppreciationTitle) {
        this.subAppreciationTitle = subAppreciationTitle;
    }

    /**
     * @return the appreciationSubject
     */
    public String getAppreciationSubject() {
        return appreciationSubject;
    }

    /**
     * @param appreciationSubject the appreciationSubject to set
     */
    public void setAppreciationSubject(String appreciationSubject) {
        this.appreciationSubject = appreciationSubject;
    }

    /**
     * @return the appreciationTo
     */
    public String getAppreciationTo() {
        return appreciationTo;
    }

    /**
     * @param appreciationTo the appreciationTo to set
     */
    public void setAppreciationTo(String appreciationTo) {
        this.appreciationTo = appreciationTo;
    }

    /**
     * @return the appreciationCC
     */
    public String getAppreciationCC() {
        return appreciationCC;
    }

    /**
     * @param appreciationCC the appreciationCC to set
     */
    public void setAppreciationCC(String appreciationCC) {
        this.appreciationCC = appreciationCC;
    }

    /**
     * @return the appreciationBCC
     */
    public String getAppreciationBCC() {
        return appreciationBCC;
    }

    /**
     * @param appreciationBCC the appreciationBCC to set
     */
    public void setAppreciationBCC(String appreciationBCC) {
        this.appreciationBCC = appreciationBCC;
    }

    /**
     * @return the appreciationBodyContent
     */
    public String getAppreciationBodyContent() {
        return appreciationBodyContent;
    }

    /**
     * @param appreciationBodyContent the appreciationBodyContent to set
     */
    public void setAppreciationBodyContent(String appreciationBodyContent) {
        this.appreciationBodyContent = appreciationBodyContent;
    }

    public Map getEmployeeEmails() {
        return employeeEmails;
    }

    public void setEmployeeEmails(Map employeeEmails) {
        this.employeeEmails = employeeEmails;
    }

    /**
     * @return the AppreciationEmailMap
     */
    public Map getAppreciationEmailMap() {
        return AppreciationEmailMap;
    }

    /**
     * @param AppreciationEmailMap the AppreciationEmailMap to set
     */
    public void setAppreciationEmailMap(Map AppreciationEmailMap) {
        this.AppreciationEmailMap = AppreciationEmailMap;
    }

    /**
     * @return the CCHidden
     */
    public String getCCHidden() {
        return CCHidden;
    }

    /**
     * @param CCHidden the CCHidden to set
     */
    public void setCCHidden(String CCHidden) {
        this.CCHidden = CCHidden;
    }

    /**
     * @return the BCCHidden
     */ 
    public String getBCCHidden() {
        return BCCHidden;
    }

    /**
     * @param BCCHidden the BCCHidden to set
     */
    public void setBCCHidden(String BCCHidden) {
        this.BCCHidden = BCCHidden;
    }

    /**
     * @return the ToHidden
     */
    public String getToHidden() {
        return ToHidden;
    }

    /**
     * @param ToHidden the ToHidden to set
     */
    public void setToHidden(String ToHidden) {
        this.ToHidden = ToHidden;
    }

    /**
     * @return the appriceationid
     */
    public int getAppriceationId() {
        return appriceationId;
    }

    /**
     * @param appriceationid the appriceationid to set
     */
    public void setAppriceationId(int appriceationId) {
        this.appriceationId = appriceationId;
    }

    /**
     * @return the currentDate
     */
    public String getCurrentDate() {
        return currentDate;
    }

    /**
     * @param currentDate the currentDate to set
     */
    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    /**
     * @return the sendOrSave
     */
    public String getSendOrSave() {
        return sendOrSave;
    }

    /**
     * @param sendOrSave the sendOrSave to set
     */
    public void setSendOrSave(String sendOrSave) {
        this.sendOrSave = sendOrSave;
    }

    /**
     * @return the emailsForTo
     */
    public String getEmailsForTo() {
        return emailsForTo;
    }

    /**
     * @param emailsForTo the emailsForTo to set
     */
    public void setEmailsForTo(String emailsForTo) {
        this.emailsForTo = emailsForTo;
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
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the subtitle
     */
    public String getSubtitle() {
        return subtitle;
    }

    /**
     * @param subtitle the subtitle to set
     */
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the empnameById
     */
    public String getEmpnameById() {
        return empnameById;
    }

    /**
     * @param empnameById the empnameById to set
     */
    public void setEmpnameById(String empnameById) {
        this.empnameById = empnameById;
    }

    public List getAppreciationBCCList() {
        return appreciationBCCList;
    }

    public void setAppreciationBCCList(List appreciationBCCList) {
        this.appreciationBCCList = appreciationBCCList;
    }

    public List getAppreciationCCList() {
        return appreciationCCList;
    }

    public void setAppreciationCCList(List appreciationCCList) {
        this.appreciationCCList = appreciationCCList;
    }

    public List getAppreciationEmpList() {
        return appreciationEmpList;
    }

    public void setAppreciationEmpList(List appreciationEmpList) {
        this.appreciationEmpList = appreciationEmpList;
    }

    public List getAppreciationToList() {
        return appreciationToList;
    }

    public void setAppreciationToList(List appreciationToList) {
        this.appreciationToList = appreciationToList;
    }

    /**
     * @return the bodyContent
     */
    public String getBodyContent() {
        return bodyContent;
    }

    /**
     * @param bodyContent the bodyContent to set
     */
    public void setBodyContent(String bodyContent) {
        this.bodyContent = bodyContent;
    }

    /**
     * @return the designation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * @param designation the designation to set
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
}
