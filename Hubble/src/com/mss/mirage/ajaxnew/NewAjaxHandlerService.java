/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.ajaxnew;

import com.mss.mirage.util.ServiceLocatorException;
import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author miracle
 */
public interface NewAjaxHandlerService {

    public String getMcertRecordsList(String startDate, String toDate, String status) throws ServiceLocatorException;

    public String mcertRecordStatusUpdate(String consultantIds, String loginId, String status, String examNameIdList) throws ServiceLocatorException;

    public String getMcertQuestion(int questionNo, HttpServletRequest httpServletRequest, int selectedAns, String navigation, int remainingQuestions, int onClickStatus, int subTopicId, int specficQuestionNo) throws ServiceLocatorException;

    public String getMcertDetailExamInfo(String examKeyId) throws ServiceLocatorException;

    public String searchPreSalesRequirementList(HttpServletRequest httpServletRequest, NewAjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException;

    public String getRequirementOtherDetails(int reqId) throws ServiceLocatorException;

    public String searchPreSalesMyRequirementList(HttpServletRequest httpServletRequest, NewAjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException;

    public String doPopulateAccountDetails(int accId) throws ServiceLocatorException;

    public String popupContactsWindow(String empId) throws ServiceLocatorException;

    public String getEmployeeProjectDetailsBasedOnStatus(String projectId, int accountId, String empType, int empId, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    public String getProjectStatusById(String projectId) throws ServiceLocatorException;

    public String getProjectProtfolioReport(String customerName, String startDate, String orderBy, String status, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    public String getProjectDescriptionDetails(int prjId) throws ServiceLocatorException;

    public String getProjectCommentDetails(int prjId) throws ServiceLocatorException;

    public String getPSCERDetailsForPresales(HttpServletRequest httpServletRequest, NewAjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException;

    public String getRequestInSightDetails(int leadId) throws ServiceLocatorException;

    public String getPSCERDetailsForEmployee(HttpServletRequest httpServletRequest, NewAjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException;

    public String getPSCERDetailsForSales(HttpServletRequest httpServletRequest, NewAjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException;

    public String getDomainByExamType(int flag);

    public String ajaxQuestionsFileUpload(NewAjaxHandlerAction ajaxHandlerAction);

    public String getAllFilesInDirectory(HttpServletRequest httpServletRequest, NewAjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException;

    public String getAllFilesInImagesDirectory(HttpServletRequest httpServletRequest, NewAjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException;

    public String getBDMStatistics(String bdmId, String startDate, String endDate) throws ServiceLocatorException;

    public String getBdmStatisticsDetailsByLoginId(String bdmId, String startDate, String endDate, String activityType) throws ServiceLocatorException;

    public String getActivityContacts(String actitvityId) throws ServiceLocatorException;

    public String getCustomerContacts(String customerName, String teamMemberId, String startDate, String endDate, int teamMemberCheck, String titleType) throws ServiceLocatorException;

    public String getContactActivities(String customerId, String teamMemberId, String startDate, String endDate, int teamMemberCheck, String titleType) throws ServiceLocatorException;

    public String reqOverviewPieChart(HttpServletRequest httpServletRequest, NewAjaxHandlerAction aThis);

    public String getRequirementOverviewDetails(HttpServletRequest httpServletRequest, NewAjaxHandlerAction aThis);

    public String getInsideSalesStatusList(String bdeLoginId) throws ServiceLocatorException;

    public String getInsideSalesAccountDetailsList(HttpServletRequest httpServletRequest, NewAjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException;

    public String getInvestmentType(int invId) throws ServiceLocatorException;

    public String addLeadHystory(String loginId, Timestamp remainderDate, String followUpComments, String nextfollowUpComments, int leadId, String opt, int id, int reminderFlag) throws ServiceLocatorException;

    public String getLeadFollowUpHistoryDetails(int id) throws ServiceLocatorException;

    public String customerProjectDetailsDashboard(NewAjaxHandlerAction aThis, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    public String getResourceTypeDetailsByCustomer(int accId, String projectId, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    public String getProjectListByCustomer(NewAjaxHandlerAction newAjaxHandlerAction, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    public String getTaskNotes(int notesId) throws ServiceLocatorException;

    /*
     * task dashboard methods
     */
    public String getIssuesDashBoardByStatus(String taskStartDate, String taskEndDate, String reportsTo, int graphId, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    public String getTaskListByStatus(String taskStartDate, String taskEndDate, String reportsTo, String activityType, int graphId, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    public String doPopulateTaskDetails(int taskId) throws ServiceLocatorException;

    public int doSetQReviewManagerComments(int Id, String comments) throws ServiceLocatorException;

    public int doSetQReviewPersonalityComments(int Id, String strengthsComments, String improvementsComments, String strengths, String improvements) throws ServiceLocatorException;

    public int doSetQReviewGoalsComments(int Id, String shortTermGoalComments, String longTermGoalComments, String shortTermGoal, String longTermGoal) throws ServiceLocatorException;

    public String empQuarterlyAppraisalSave(NewAjaxHandlerAction newAjaxHandlerAction, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    public String quarterlyAppraisalEdit(int empId, int appraisalId, int lineId) throws ServiceLocatorException;

    public int setQReviewEmpDescriptions(int Id, String comments) throws ServiceLocatorException;

    public String getBdmReport(NewAjaxHandlerAction newAjaxHandlerAction, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    public String getSalesTeamforBDMAssociate(String salesName) throws ServiceLocatorException;

    public String getSalesTeamDetails(String teamMemberId,String bdmId) throws ServiceLocatorException;

    public int addSalesToBdm(NewAjaxHandlerAction newAjaxHandlerAction) throws ServiceLocatorException;

    public boolean checkSalesAgainstBdm(NewAjaxHandlerAction newAjaxHandlerAction) throws ServiceLocatorException;

    public String checkBdmAddedName(NewAjaxHandlerAction newAjaxHandlerAction) throws ServiceLocatorException;

    public boolean updateBdmTeam(NewAjaxHandlerAction newAjaxHandlerAction) throws ServiceLocatorException;

    public boolean checkBdmStatus(NewAjaxHandlerAction newAjaxHandlerAction) throws ServiceLocatorException;
    
    /*Author :Teja Kadamanti
     * Date : 04/20/2017
     * Description: Multiple Projects Employee Details 
     * 
     * 
     */
    public String getMultipleProjectsEmployeeList(NewAjaxHandlerAction newAjaxHandlerAction,HttpServletRequest httpServletRequest) throws ServiceLocatorException ;

public String multipleProjectsEmployeeListDetails(int preAssignedEmpId,HttpServletRequest httpServletRequest) throws ServiceLocatorException;
 public String getConsultantNames(String query) throws ServiceLocatorException;

    public boolean fileUploadWorkAuthorizationCopy(NewAjaxHandlerAction newAjaxHandlerAction)  throws ServiceLocatorException;

    public boolean fileUploadDlCopyAttached(NewAjaxHandlerAction newAjaxHandlerAction)  throws ServiceLocatorException;

}
