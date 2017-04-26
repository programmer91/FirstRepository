/*
 * EmployeeAppraisalAction.java
 *
 * Created on May 15, 2008, 3:20 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.employee.appraisal;

import com.mss.mirage.util.ApplicationConstants;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.ParameterNameAware;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.AuthorizationManager;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Date;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import java.sql.Time;
import com.mss.mirage.util.DateUtility;
//import org.apache.tools.ant.taskdefs.Java;
/**
 *
 * @author miracle
 */
public class EmployeeAppraisalAction extends ActionSupport implements ServletRequestAware,ParameterNameAware,ParameterAware {
    
    /** Creates a new instance of EmployeeAppraisalAction */
    public EmployeeAppraisalAction() {
    }
    
    
    private int currentEmployeeId;  /**
     * is used for storeing employeee id
     */
    private String strCurrentEmployeeId;
    
    private int empId;
    private String empName;
    
    
    private Map myTeamMembers;
    private String submitedForm;
    
    private HttpServletRequest httpServletRequest;
    private EmployeeAppraisalVTO currentApparaisal;
    private String resultType = null;
    /**
     * this variable is used for stroring the department of an employee
     */
    private String department;
    /**
     * this variable is used for  stroring the designation of an employee
     */
    private String designation;
    /**
     * this variable is used for stroring the dateofJoin of an employee
     */
    private Date dateOfJoin;
    /**
     * this variable is used for appraisalDate the dateofJoin of an employee
     */
    private Date appraisalDate;
    /**
     * this variable is used for stroring the TeamName of an employee
     */
    private String teamName;
    /**
     * this variable is used for stroring the teamLead/techManager of an employee
     */
    private String techMangName;
    /**
     * this variable is used for stroring the Project one
     */
    
    private String nameOfProject1;
    /**
     * this variable is used for stroring the modeOfWork Project one
     */
    private String modeOfWork1;
    /**
     * this variable is used for stroring the resourceType Project one
     */
    private String resourceType1;
    
    /**
     * this variable is used for stroring the start date Project one
     */
    private Date startDate1;
    /**
     * this variable is used for stroring the end date Project one name
     */
    private Date endDate1;
    /**
     * this variable is used for stroring the comments Project one name
     */
    private String comments1;
    
    /**
     * this variable is used for stroring the end date Project two name
     */
    private String nameOfProject2;
    /**
     * this variable is used for stroring the mode of work Project two
     */
    private String modeOfWork2;
    /**
     * this variable is used for stroring the resource type Project two
     */
    private String resourceType2;
    /**
     * this variable is used for stroring the start date Project two
     */
    private Date startDate2;
    /**
     * this variable is used for stroring the end date Project two
     */
    private Date endDate2;
    /**
     * this variable is used for stroring the comments Project two
     */
    private String comments2;
    
    /**
     * this variable is used for stroring the  Project three name
     */
    private String nameOfProject3;
    /**
     * this variable is used for stroring the  mode of work Project three
     */
    private String modeOfWork3;
    /**
     * this variable is used for stroring the  resource type Project three
     */
    private String resourceType3;
    /**
     * this variable is used for stroring the  start Date Project three
     */
    private Date startDate3;
    /**
     * this variable is used for stroring the end date Project three
     */
    private Date endDate3;
    /**
     * this variable is used for stroring the comments Project three
     */
    private String comments3;
    
    /**
     * this variable is used for stroring the  name of the Project four
     */
    private String nameOfProject4;
    /**
     * this variable is used for stroring the  mode of work the Project four
     */
    private String modeOfWork4;
    /**
     * this variable is used for stroring the  resource type of Project four
     */
    private String resourceType4;
    /**
     * this variable is used for stroring the start date of Project four
     */
    private Date startDate4;
    /**
     * this variable is used for stroring the  end date of Project four
     */
    private Date endDate4;
    /**
     * this variable is used for stroring the  comments of Project four
     */
    private String comments4;
    
    /**
     * this variable is used for stroring the name of Project five
     */
    private String nameOfProject5;
    /**
     * this variable is used for stroring the mode of work of Project five
     */
    private String modeOfWork5;
    /**
     * this variable is used for stroring the resource type of Project five
     */
    private String resourceType5;
    /**
     * this variable is used for stroring the start date of Project five
     */
    private Date startDate5;
    /**
     * this variable is used for stroring the end date of Project five
     */
    private Date endDate5;
    /**
     * this variable is used for stroring comments of Project five
     */
    private String comments5;
    
    /**
     * this variable is used for stroring operation type
     */
    private String operationalType;
    
    /**
     * this variable is used for switching the current action
     */
    
    private String currentAction;
    
    private String prjHrOrTm;
    
    private String PrjHrRating;
    
    
    /**  variables are used for Details of individuals  **/
    
    
    private String monthsInPayroll;
    private String leaveHistory;
    private String onsiteProjectDuration;
    private String offshoreProjectDuration;
    private String benchDuration;
    private String corporateTrainingDuration;
    private String interviewsAttended;
    private String currentSal;
    private String previousHike;
    private String visaAvailable;
    private String skills;
    private String hrComments;
    
    
    /************************ Self Assessment Details **********************/
    
    
    
    
    /** PAttendence is used for Storing the Punctuality and attendence Comments**/
    private String pAttendence ;
    
    /** pAttendHrComments is used for Storing the HR/TM comments for Punctuality and attendence **/
    
    private String pAttendHrComments;
    
    /** pAttendHrRating is used for Storing the HR/TM Rating for Punctuality and attendence **/
    
    private String pAttendHrRating;
    
    /** workAttitude is used for Storing the Work Attitude Comments***/
    private String workAttitude;
    
    /** wAttHrComment is used for Storing the HR/TM comments for Work Attitude **/
    
    private String wAttHrComment;
    
    /** wAttHrRating is used for Storing the HR/TM Rating for Work Attitude **/
    
    private String wAttHrRating;
    
    /** communication is used for Storing the Communication Comments***/
    private String communication;
    
    /** commHrComment is used for Storing the HR/TM comments for Communication**/
    
    private String commHrComments;
    
    /** commHrRating is used for Storing the HR/TM Rating for Communication**/
    
    private String commHrRating;
    
    /** workObjectives is used for Storing the workObjectives Comments***/
    
    private String workObjectives;
    
    /** workObjHrComm is used for Storing the HR/TM Comments for workObjectives **/
    
    private String workObjHrComm;
    
    /** workObjHrRating is used for Storing the HR/TM Rating for workObjectives **/
    
    private String workObjHrRating;
    
    /** collaborationTW is used for Storing the collaboration and Team work  Comments***/
    
    private String collaborationTW;
    
    /** collTWHrComm is used for Storing the HR/TM Comments for collaboration and Team work  ***/
    
    private String collTWHrComm;
    
    /** collTWHrRating is used for Storing the HR/TM Rating for collaboration and Team work  ***/
    private String collTWHrRating;
    
    /** planOrganization is used for Storing the Planning and Oganization Comments***/
    
    private String planOrganization;
    
    /** planOrgHrComm is used for Storing the HR/TM Comments for Planning and Oganization ***/
    
    private String planOrgHrComm;
    
    /** planOrgHrRating is used for Storing the HR/TM Rating for Planning and Oganization ***/
    
    private String planOrgHrRating;
    
    /** adaptVersatility is used for Storing the Adaptability and Versatility Comments***/
    
    private String adaptVersatility;
    
    /** adaptVersatilityHrComm is used for Storing the HR/TM Comments for Adaptability and Versatility ***/
    
    private String adaptVersatilityHrComm;
    
    /** adaptVersatilityHrRating is used for Storing the HR/TM Rating for Adaptability and Versatility ***/
    
    private String adaptVersatilityHrRating;
    
    /** achievAppreciations is used for Storing the Achievements and Appreciations Comments***/
    
    private String achievAppreciations;
    
    /** achievApprecHrComm is used for Storing the HR/TM Comments for Achievements and Appreciations ***/
    private String achievApprecHrComm;
    
    /** achievApprecHrRating is used for Storing the HR/TM Rating for Achievements and Appreciations ***/
    
    private String achievApprecHrRating;
    
    
    /** team lead or hr comments **/
    private String tlcomments;
    private String pmComments;
    private String hrComment;
    private String vpComments;
    
    private String eName;
    private String pName;
    private String hName;
    private String vName;
    
    private String tlRating;
    private String pRating;
    private String hrRating;
    private String vpRating;
    
    private String TM;
    private String PM;
    private String HR;
    private String VP;
    private Date currentDate;
    private String message;
   
    
    
    
    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }
    
    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public String getDesignation() {
        return designation;
    }
    
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    public String getTeamName() {
        return teamName;
    }
    
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    
    public String getTechMangName() {
        return techMangName;
    }
    
    public void setTechMangName(String techMangName) {
        this.techMangName = techMangName;
    }
    
    
    public EmployeeAppraisalVTO getCurrentApparaisal() {
        return currentApparaisal;
    }
    
    public void setCurrentApparaisal(EmployeeAppraisalVTO currentApparaisal) {
        this.currentApparaisal = currentApparaisal;
    }
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest=httpServletRequest;
    }
    
    public Date getDateOfJoin() {
        return dateOfJoin;
    }
    
    public void setDateOfJoin(Date dateOfJoin) {
        this.dateOfJoin = dateOfJoin;
    }
    
    public Date getAppraisalDate() {
        return appraisalDate;
    }
    
    public void setAppraisalDate(Date appraisalDate) {
        this.appraisalDate = appraisalDate;
    }
    
    public String getNameOfProject1() {
        return nameOfProject1;
    }
    
    public void setNameOfProject1(String nameOfProject1) {
        this.nameOfProject1 = nameOfProject1;
    }
    
    public String getModeOfWork1() {
        return modeOfWork1;
    }
    
    public void setModeOfWork1(String modeOfWork1) {
        this.modeOfWork1 = modeOfWork1;
    }
    
    public String getResourceType1() {
        return resourceType1;
    }
    
    public void setResourceType1(String resourceType1) {
        this.resourceType1 = resourceType1;
    }
    
    public Date getStartDate1() {
        return startDate1;
    }
    
    public void setStartDate1(Date startDate1) {
        this.startDate1 = startDate1;
    }
    
    public Date getEndDate1() {
        return endDate1;
    }
    
    public void setEndDate1(Date endDate1) {
        this.endDate1 = endDate1;
    }
    
    public String getNameOfProject2() {
        return nameOfProject2;
    }
    
    public void setNameOfProject2(String nameOfProject2) {
        this.nameOfProject2 = nameOfProject2;
    }
    
    public String getModeOfWork2() {
        return modeOfWork2;
    }
    
    public void setModeOfWork2(String modeOfWork2) {
        this.modeOfWork2 = modeOfWork2;
    }
    
    public String getResourceType2() {
        return resourceType2;
    }
    
    public void setResourceType2(String resourceType2) {
        this.resourceType2 = resourceType2;
    }
    
    public Date getStartDate2() {
        return startDate2;
    }
    
    public void setStartDate2(Date startDate2) {
        this.startDate2 = startDate2;
    }
    
    public Date getEndDate2() {
        return endDate2;
    }
    
    public void setEndDate2(Date endDate2) {
        this.endDate2 = endDate2;
    }
    
    public String getNameOfProject3() {
        return nameOfProject3;
    }
    
    public void setNameOfProject3(String nameOfProject3) {
        this.nameOfProject3 = nameOfProject3;
    }
    
    public String getModeOfWork3() {
        return modeOfWork3;
    }
    
    public void setModeOfWork3(String modeOfWork3) {
        this.modeOfWork3 = modeOfWork3;
    }
    
    public String getResourceType3() {
        return resourceType3;
    }
    
    public void setResourceType3(String resourceType3) {
        this.resourceType3 = resourceType3;
    }
    
    public Date getStartDate3() {
        return startDate3;
    }
    
    public void setStartDate3(Date startDate3) {
        this.startDate3 = startDate3;
    }
    
    public Date getEndDate3() {
        return endDate3;
    }
    
    public void setEndDate3(Date endDate3) {
        this.endDate3 = endDate3;
    }
    
    public String getNameOfProject4() {
        return nameOfProject4;
    }
    
    public void setNameOfProject4(String nameOfProject4) {
        this.nameOfProject4 = nameOfProject4;
    }
    
    public String getModeOfWork4() {
        return modeOfWork4;
    }
    
    public void setModeOfWork4(String modeOfWork4) {
        this.modeOfWork4 = modeOfWork4;
    }
    
    public String getResourceType4() {
        return resourceType4;
    }
    
    public void setResourceType4(String resourceType4) {
        this.resourceType4 = resourceType4;
    }
    
    public Date getStartDate4() {
        return startDate4;
    }
    
    public void setStartDate4(Date startDate4) {
        this.startDate4 = startDate4;
    }
    
    public Date getEndDate4() {
        return endDate4;
    }
    
    public void setEndDate4(Date endDate4) {
        this.endDate4 = endDate4;
    }
    
    public String getNameOfProject5() {
        return nameOfProject5;
    }
    
    public void setNameOfProject5(String nameOfProject5) {
        this.nameOfProject5 = nameOfProject5;
    }
    
    public String getModeOfWork5() {
        return modeOfWork5;
    }
    
    public void setModeOfWork5(String modeOfWork5) {
        this.modeOfWork5 = modeOfWork5;
    }
    
    public String getResourceType5() {
        return resourceType5;
    }
    
    public void setResourceType5(String resourceType5) {
        this.resourceType5 = resourceType5;
    }
    
    public Date getStartDate5() {
        return startDate5;
    }
    
    public void setStartDate5(Date startDate5) {
        this.startDate5 = startDate5;
    }
    
    public Date getEndDate5() {
        return endDate5;
    }
    
    public void setEndDate5(Date endDate5) {
        this.endDate5 = endDate5;
    }
    public String getComments1() {
        return comments1;
    }
    
    public void setComments1(String comments1) {
        this.comments1 = comments1;
    }
    
    public String getComments2() {
        return comments2;
    }
    
    public void setComments2(String comments2) {
        this.comments2 = comments2;
    }
    
    public String getComments3() {
        return comments3;
    }
    
    public void setComments3(String comments3) {
        this.comments3 = comments3;
    }
    
    public String getComments4() {
        return comments4;
    }
    
    public void setComments4(String comments4) {
        this.comments4 = comments4;
    }
    
    public String getComments5() {
        return comments5;
    }
    
    public void setComments5(String comments5) {
        this.comments5 = comments5;
    }
    
    public String getCurrentAction() {
        return currentAction;
    }
    
    public void setCurrentAction(String currentAction) {
        this.currentAction = currentAction;
    }
    public Map getMyTeamMembers() {
        return myTeamMembers;
    }
    
    public void setMyTeamMembers(Map myTeamMembers) {
        this.myTeamMembers = myTeamMembers;
    }
    
    public int getCurrentEmployeeId() {
        return currentEmployeeId;
    }
    
    public void setCurrentEmployeeId(int currentEmployeeId) {
        this.currentEmployeeId = currentEmployeeId;
    }
    
    public int getEmpId() {
        return empId;
    }
    
    public void setEmpId(int empId) {
        this.empId = empId;
    }
    
    public String getEmpName() {
        return empName;
    }
    
    public void setEmpName(String empName) {
        this.empName = empName;
    }
    
    /** methods for Details of individuals **/
    
    public String getMonthsInPayroll() {
        return monthsInPayroll;
    }
    
    public void setMonthsInPayroll(String monthsInPayroll) {
        this.monthsInPayroll = monthsInPayroll;
    }
    
    public String getLeaveHistory() {
        return leaveHistory;
    }
    
    public void setLeaveHistory(String leaveHistory) {
        this.leaveHistory = leaveHistory;
    }
    
    public String getOnsiteProjectDuration() {
        return onsiteProjectDuration;
    }
    
    public void setOnsiteProjectDuration(String onsiteProjectDuration) {
        this.onsiteProjectDuration = onsiteProjectDuration;
    }
    
    public String getOffshoreProjectDuration() {
        return offshoreProjectDuration;
    }
    
    public void setOffshoreProjectDuration(String offshoreProjectDuration) {
        this.offshoreProjectDuration = offshoreProjectDuration;
    }
    
    public String getBenchDuration() {
        return benchDuration;
    }
    
    public void setBenchDuration(String benchDuration) {
        this.benchDuration = benchDuration;
    }
    
    public String getCorporateTrainingDuration() {
        return corporateTrainingDuration;
    }
    
    public void setCorporateTrainingDuration(String corporateTrainingDuration) {
        this.corporateTrainingDuration = corporateTrainingDuration;
    }
    
    public String getInterviewsAttended() {
        return interviewsAttended;
    }
    
    public void setInterviewsAttended(String interviewsAttended) {
        this.interviewsAttended = interviewsAttended;
    }
    
    public String getCurrentSal() {
        return currentSal;
    }
    
    public void setCurrentSal(String currentSal) {
        this.currentSal = currentSal;
    }
    
    public String getPreviousHike() {
        return previousHike;
    }
    
    public void setPreviousHike(String previousHike) {
        this.previousHike = previousHike;
    }
    
    public String getVisaAvailable() {
        return visaAvailable;
    }
    
    public void setVisaAvailable(String visaAvailable) {
        this.visaAvailable = visaAvailable;
    }
    
    public String getSkills() {
        return skills;
    }
    
    public void setSkills(String skills) {
        this.skills = skills;
    }
    
    public String getHrComments() {
        return hrComments;
    }
    
    public void setHrComments(String hrComments) {
        this.hrComments = hrComments;
    }
    
    /******************* Self Assessment Setter and getter methods ****************/
    public String getPAttendence() {
        return pAttendence;
    }
    
    public void setPAttendence(String pAttendence) {
        this.pAttendence = pAttendence;
    }
    
    public String getPAttendHrComments() {
        return pAttendHrComments;
    }
    
    public void setPAttendHrComments(String pAttendHrComments) {
        this.pAttendHrComments = pAttendHrComments;
    }
    
    public String getPAttendHrRating() {
        return pAttendHrRating;
    }
    
    public void setPAttendHrRating(String pAttendHrRating) {
        this.pAttendHrRating = pAttendHrRating;
    }
    
    public String getWorkAttitude() {
        return workAttitude;
    }
    
    public void setWorkAttitude(String workAttitude) {
        this.workAttitude = workAttitude;
    }
    
    public String getWAttHrComment() {
        return wAttHrComment;
    }
    
    public void setWAttHrComment(String wAttHrComment) {
        this.wAttHrComment = wAttHrComment;
    }
    
    public String getWAttHrRating() {
        return wAttHrRating;
    }
    
    public void setWAttHrRating(String wAttHrRating) {
        this.wAttHrRating = wAttHrRating;
    }
    
    public String getCommunication() {
        return communication;
    }
    
    public void setCommunication(String communication) {
        this.communication = communication;
    }
    
    public String getCommHrComments() {
        return commHrComments;
    }
    
    public void setCommHrComments(String commHrComments) {
        this.commHrComments = commHrComments;
    }
    
    public String getCommHrRating() {
        return commHrRating;
    }
    
    public void setCommHrRating(String commHrRating) {
        this.commHrRating = commHrRating;
    }
    
    public String getWorkObjectives() {
        return workObjectives;
    }
    
    public void setWorkObjectives(String workObjectives) {
        this.workObjectives = workObjectives;
    }
    
    public String getWorkObjHrComm() {
        return workObjHrComm;
    }
    
    public void setWorkObjHrComm(String workObjHrComm) {
        this.workObjHrComm = workObjHrComm;
    }
    
    public String getWorkObjHrRating() {
        return workObjHrRating;
    }
    
    public void setWorkObjHrRating(String workObjHrRating) {
        this.workObjHrRating = workObjHrRating;
    }
    
    public String getCollaborationTW() {
        return collaborationTW;
    }
    
    public void setCollaborationTW(String collaborationTW) {
        this.collaborationTW = collaborationTW;
    }
    
    public String getCollTWHrComm() {
        return collTWHrComm;
    }
    
    public void setCollTWHrComm(String collTWHrComm) {
        this.collTWHrComm = collTWHrComm;
    }
    
    public String getCollTWHrRating() {
        return collTWHrRating;
    }
    
    public void setCollTWHrRating(String collTWHrRating) {
        this.collTWHrRating = collTWHrRating;
    }
    
    public String getPlanOrganization() {
        return planOrganization;
    }
    
    public void setPlanOrganization(String planOrganization) {
        this.planOrganization = planOrganization;
    }
    
    public String getPlanOrgHrComm() {
        return planOrgHrComm;
    }
    
    public void setPlanOrgHrComm(String planOrgHrComm) {
        this.planOrgHrComm = planOrgHrComm;
    }
    
    public String getPlanOrgHrRating() {
        return planOrgHrRating;
    }
    
    public void setPlanOrgHrRating(String planOrgHrRating) {
        this.planOrgHrRating = planOrgHrRating;
    }
    
    public String getAdaptVersatility() {
        return adaptVersatility;
    }
    
    public void setAdaptVersatility(String adaptVersatility) {
        this.adaptVersatility = adaptVersatility;
    }
    
    public String getAdaptVersatilityHrComm() {
        return adaptVersatilityHrComm;
    }
    
    public void setAdaptVersatilityHrComm(String adaptVersatilityHrComm) {
        this.adaptVersatilityHrComm = adaptVersatilityHrComm;
    }
    
    public String getAdaptVersatilityHrRating() {
        return adaptVersatilityHrRating;
    }
    
    public void setAdaptVersatilityHrRating(String adaptVersatilityHrRating) {
        this.adaptVersatilityHrRating = adaptVersatilityHrRating;
    }
    
    public String getAchievAppreciations() {
        return achievAppreciations;
    }
    
    public void setAchievAppreciations(String achievAppreciations) {
        this.achievAppreciations = achievAppreciations;
    }
    
    public String getAchievApprecHrComm() {
        return achievApprecHrComm;
    }
    
    public void setAchievApprecHrComm(String achievApprecHrComm) {
        this.achievApprecHrComm = achievApprecHrComm;
    }
    
    public String getAchievApprecHrRating() {
        return achievApprecHrRating;
    }
    
    public void setAchievApprecHrRating(String achievApprecHrRating) {
        this.achievApprecHrRating = achievApprecHrRating;
    }
    
    public String getPrjHrOrTm() {
        return prjHrOrTm;
    }
    
    public void setPrjHrOrTm(String prjHrOrTm) {
        this.prjHrOrTm = prjHrOrTm;
    }
    
    public String getPrjHrRating() {
        return PrjHrRating;
    }
    
    public void setPrjHrRating(String PrjHrRating) {
        this.PrjHrRating = PrjHrRating;
    }
    
    public String getTlcomments() {
        return tlcomments;
    }
    
    public void setTlcomments(String tlcomments) {
        this.tlcomments = tlcomments;
    }
    
    public String getPmComments() {
        return pmComments;
    }
    
    public void setPmComments(String pmComments) {
        this.pmComments = pmComments;
    }
    
    public String getHrComment() {
        return hrComment;
    }
    
    public void setHrComment(String hrComment) {
        this.hrComment = hrComment;
    }
    
    public String getVpComments() {
        return vpComments;
    }
    
    public void setVpComments(String vpComments) {
        this.vpComments = vpComments;
    }
    
    public String getEName() {
        return eName;
    }
    
    public void setEName(String eName) {
        this.eName = eName;
    }
    
    public String getPName() {
        return pName;
    }
    
    public void setPName(String pName) {
        this.pName = pName;
    }
    
    public String getHName() {
        return hName;
    }
    
    public void setHName(String hName) {
        this.hName = hName;
    }
    
    public String getVName() {
        return vName;
    }
    
    public void setVName(String vName) {
        this.vName = vName;
    }
    
    public String getTlRating() {
        return tlRating;
    }
    
    public void setTlRating(String tlRating) {
        this.tlRating = tlRating;
    }
    
    public String getPRating() {
        return pRating;
    }
    
    public void setPRating(String pRating) {
        this.pRating = pRating;
    }
    
    public String getHrRating() {
        return hrRating;
    }
    
    public void setHrRating(String hrRating) {
        this.hrRating = hrRating;
    }
    
    public String getVpRating() {
        return vpRating;
    }
    
    public void setVpRating(String vpRating) {
        this.vpRating = vpRating;
    }
    
    public String getTM() {
        return TM;
    }
    
    public void setTM(String TM) {
        this.TM = TM;
    }
    
    public String getPM() {
        return PM;
    }
    
    public void setPM(String PM) {
        this.PM = PM;
    }
    
    public String getHR() {
        return HR;
    }
    
    public void setHR(String HR) {
        this.HR = HR;
    }
    
    public String getVP() {
        return VP;
    }
    
    public void setVP(String VP) {
        this.VP = VP;
    }
    
    public Date getCurrentDate() {
        return currentDate;
    }
    
    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }
    
    public String individualDetails() throws Exception {
        resultType = LOGIN;
        if(getHttpServletRequest().getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            try {
                boolean isInserted=false;
                String resultMessage="";
                String operationType = null;
                currentEmployeeId = getCurrentEmployeeId();
                if(currentEmployeeId == 0) {
                    operationType = "Ins";
                } else {
                    operationType = "Upd";
                }
                
                isInserted = ServiceLocator.getEmpAppraisalService().addOrUpdateDetailsOfIndividuals(this,operationType);
                if(isInserted && currentEmployeeId!=0){
                    resultMessage="The values are Successfully Updated.";
                } else if(currentEmployeeId==0 && isInserted) {
                    resultMessage="The values are Successfully Inserted.";
                } else{
                    resultMessage="please Try again";
                    resultType = INPUT;
                }
                httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
                prepare();
                resultType = SUCCESS;
            }catch (Exception ex){
                getHttpServletRequest().getSession(false).setAttribute("errorMessage",ex.toString());
                resultType =  ERROR;
            }
        }
        
        return resultType;
    }
    
    
    
    
    
    public String execute() throws Exception {
        return SUCCESS;
    }
    public String getGenalDetails() throws Exception  {
        if(empId==0){
            empId=empId =Integer.parseInt((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString()));
        }
        //  int empId =Integer.parseInt ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString()));
        setCurrentApparaisal(ServiceLocator.getEmpAppraisalService().getPersonalDetails(empId,currentEmployeeId));
       
        prepare();
        return SUCCESS;
    }
    public String prepare() throws Exception {
        if(empId==0){
            empId=Integer.parseInt((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString()));
        }
        //   int empId =Integer.parseInt ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString()));
        setCurrentApparaisal(ServiceLocator.getEmpAppraisalService().getPersonalDetails(empId,currentEmployeeId));
       
        //setCurrentDate(DateUtility.getInstance().getToDayDateMMDDYYYY());
        // setCurrentAction("addProject");
        return SUCCESS;
    }
    public String doAdd() throws Exception { // adding of phonelog
        prepare();
        resultType = LOGIN;
        //Checking whether user is login or not
        
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            int userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            // userId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
            resultType = "accessFailed";
            
            if(currentEmployeeId == 0) {
                operationalType = "Ins";
            } else {
                operationalType = "Upd";
            }
            int userId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
            if(empId!=0){
                userId = empId;
            }
            
            //Checking whether action has permission
            //if(AuthorizationManager.getInstance().isAuthorizedUser("PHONELOG_ADD",userRoleId)){
            try{
                boolean isInserted=false;
                String resultMessage="";
                isInserted = ServiceLocator.getEmpAppraisalService().addOrUpdateProjectDetails(this,userId,operationalType);
                if(isInserted && currentEmployeeId!=0){
                    resultMessage="Successfully ProjectDetails updated";
                } else if(isInserted && currentEmployeeId==0)
                    resultMessage="Successfully ProjectDetails added";
                else{
                    resultMessage="please Try again";
                }
                httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
                prepare();
               
                resultType=SUCCESS;
                
                //Exception Handling
            }catch (Exception ex){
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                resultType =  ERROR;
            }
            
        }
        return resultType;
    }
    
    private String getKeys(Map map, String delimiter) {
        Iterator tempIterator = map.entrySet().iterator();
        String keys = "";
        String key="";
        int cnt = 0;
        
        while(tempIterator.hasNext()) {
            Map.Entry entry = (Map.Entry) tempIterator.next();
            key = entry.getKey().toString();
            entry = null;
            
            //  Add the Delimiter to the Keys Field for the Second Key onwards
            if (cnt > 0) keys = keys + delimiter;
            
            keys = keys + "'" + key +"'";
            cnt++;
        }
        tempIterator = null;
        return(keys);
    }
    
    public String getTeamAppraisal() {
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            setMyTeamMembers((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_EMP_APPRAISAL_LIST) != null) {
                httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_EMP_APPRAISAL_LIST);
            }
            String employeeTeamAppraisalQuery = "SELECT concat(FName,' ',MName,'.',LName) as FullName,Id,LoginId,WorkPhoneNo,Email1 FROM tblEmployee where Id IN ( SELECT Id FROM tblEmployee where ReportsTo IN ("+getKeys(getMyTeamMembers(),",")+"))";
            httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_EMP_APPRAISAL_LIST,employeeTeamAppraisalQuery);
            resultType = SUCCESS;
        }
        setCurrentAction("empTeamAppraisal");
        return resultType;
    }
    
    public String getEmployeeAppraisalList()throws Exception{
        resultType = LOGIN;
        if(!("dbGrid".equalsIgnoreCase(getSubmitedForm()))){
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString() != null) {
                int empId =Integer.parseInt((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString()));
                if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_EMP_APPRAISAL_LIST) != null) {
                    httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_EMP_APPRAISAL_LIST);
                }
                if(currentEmployeeId==0){
                    String employeeAppraisalQuery = "select EmpId,AppraisalDate,CurrnetSalary,onSiteDuration,offShoreDuration,Id from tblEmpAppraisal where EmpId="+empId;
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_EMP_APPRAISAL_LIST,employeeAppraisalQuery);
                } else {
                    String employeeAppraisalQuery = "select EmpId,AppraisalDate,CurrnetSalary,onSiteDuration,offShoreDuration,Id from tblEmpAppraisal where EmpId="+currentEmployeeId;
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_EMP_APPRAISAL_LIST,employeeAppraisalQuery);
                }
                
                resultType = SUCCESS;
            }
            if(currentEmployeeId==0){
                setCurrentAction("empAppraisal");
            } else {
                setCurrentAction("empAppraisal1");
            }
            setCurrentDate(Date.valueOf(DateUtility.getInstance().getToDayDate()));
        }
        if(LOGIN.equals(resultType)) resultType = "dbGrid";
        return resultType;
    }
    /**
     *   This method is used Add and Update Employee appraisal details
     *
     *      From Action: selfAssessment
     *
     *      jsp : selfAppraisal.jsp
     * @Description: This method is used to insert and update details of selected employee.
     * @return : String
     * @param
     */
    
    public String doAddSelfAssessment() throws Exception {
        resultType = LOGIN;
        String operationalType = null;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                int userId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
                if(empId!=0){
                    userId = empId;
                }
                if(currentEmployeeId == 0) {
                    operationalType = "Ins";
                } else {
                    operationalType = "Upd";
                }
                String resultMessage =null;
                int addRows = ServiceLocator.getEmpAppraisalService().addOrUpdateEmployeeAssessment(this,userId,operationalType);
                if(currentEmployeeId!=0 && addRows==0) {
                    resultMessage="Self Assessment details has been successfully inserted";
                    // getEmployeeAppraisal(userId);
                    resultType = SUCCESS;
                } else if(addRows==1 && currentEmployeeId!=0){
                    resultMessage="Self Assessment details has been successfully Updated";
                    //getEmployeeAppraisal(userId);
                    resultType = SUCCESS;
                } else {
                    resultMessage="Sorry Please Try Again";
                    resultType = INPUT;
                }
                httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
            } catch (Exception ex){
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                resultType =  ERROR;
            }
        }
        prepare();
        return resultType;
    }
    
    /**  team lead or hr or pm or vp **/
    public String doAddTL() throws Exception{
        String resultMessage="";
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID)!= null) {
            try {
                if(currentEmployeeId== 0) {
                    operationalType = "Ins";
                } else {
                    operationalType = "Upd";
                }
                boolean commAdd = ServiceLocator.getEmpAppraisalService().addOrUpdateTLComments(this,operationalType);
                if(commAdd == true) {
                    //resultMessage = "<font color=\"green\" size=\"1.5\">'"+getMessage()+"'</font>";
                    resultMessage=getMessage();
                    httpServletRequest.setAttribute("resultMessage",resultMessage);
                    prepare();
                    resultType = SUCCESS;
                } else if(commAdd == false) {
                    //resultMessage = "<font color=\"green\" size=\"1.5\">Sorry! Please try again!</font>";
                    resultMessage=getMessage();
                    httpServletRequest.setAttribute("resultMessage",resultMessage);
                    resultType = INPUT;
                }
                 
            } catch(Exception ex) {
                httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                resultType = ERROR;
            }
        }
        
        return resultType;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public boolean acceptableParameterName(String inputParamName) {
        boolean isParamAcceptable = true;
        if(inputParamName.equals("txtCurr") ||
                inputParamName.equals("txtSortCol") ||
                inputParamName.equals("txtSortAsc") ||
                inputParamName.equals("assignedTeamMembers") ||
                inputParamName.equals("availableTeamMembers") ||
                inputParamName.equals("txtAccActCurr") ||
                inputParamName.equals("txtAllAccActCurr") ||
                inputParamName.equals("txtAllAccActSortAsc") ||
                inputParamName.equals("txtAllAccActSortCol") ||
                inputParamName.equals("txtAttachCurr") ||
                inputParamName.equals("txtContactCurr") ||
                inputParamName.equals("txtGreenSheetCurr") ||
                inputParamName.equals("txtNotesCurr") ||
                inputParamName.equals("txtAccActSortAsc") ||
                inputParamName.equals("txtAccActSortCol") ||
                inputParamName.equals("txtProjectCurr") ||
                inputParamName.equals("txtContactSortAsc") ||
                inputParamName.equals("txtContactSortCol") ||
                inputParamName.equals("txtOppCurr")||
                inputParamName.equals("searchBy")){
            
            isParamAcceptable = false;
        }
        
        
        return isParamAcceptable;
        
    }
    
    public void setParameters(Map map) {
    }
    
    public String getSubmitedForm() {
        return submitedForm;
    }
    
    public void setSubmitedForm(String submitedForm) {
        this.submitedForm = submitedForm;
    }

    public String getStrCurrentEmployeeId() {
        return strCurrentEmployeeId;
    }

    public void setStrCurrentEmployeeId(String strCurrentEmployeeId) {
        this.strCurrentEmployeeId = strCurrentEmployeeId;
        this.setCurrentEmployeeId(Integer.parseInt(strCurrentEmployeeId));
    }

}
