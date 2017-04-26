/*
 * EmployeeAparaisalVTO.java
 *
 * Created on May 15, 2008, 3:23 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.employee.appraisal;

import java.sql.Date;

/**
 *
 * @author miracle
 */
public class EmployeeAppraisalVTO {
    
    /** Creates a new instance of EmployeeAparaisalVTO */
    public EmployeeAppraisalVTO() {
    }
    private String department;
    private String designation;
    private Date dateOfJoin;
    private Date appraisalDate;
    private String teamName;
    private String techMangName;
    
    private int currentEmployeeId;
    
    
    
    private String empName;
    
    private int appraisalId;
    
    /** project details **/
    
    private String nameOfProject1;
    private String modeOfWork1;
    private String resourceType1;
    
    private Date startDate1;
    private Date endDate1;
    private String comments1;
    
    private String nameOfProject2;
    private String modeOfWork2;
    private String resourceType2;
    private Date startDate2;
    private Date endDate2;
    private String comments2;
    
    private String nameOfProject3;
    private String modeOfWork3;
    private String resourceType3;
    private Date startDate3;
    private Date endDate3;
    private String comments3;
    
    private String nameOfProject4;
    private String modeOfWork4;
    private String resourceType4;
    private Date startDate4;
    private Date endDate4;
    private String comments4;
    
    private String nameOfProject5;
    private String modeOfWork5;
    private String resourceType5;
    private Date startDate5;
    private Date endDate5;
    private String comments5;
    
    private String prjHrOrTm;
    
    private String PrjHrRating;
    
    /** Details of individuals **/
    
    
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
    
    
    /** hr or vp or pm or tl **/
    
    private String tlcomments;
    private String eName;
    private String tlRating;
    private String currentDate;
    
    
    private String pmComments;
    private String hrComment;
    private String vpComments;
    
    
    private String pName;
    private String hName;
    private String vName;
    
    
    private String pRating;
    private String hrRating;
    private String vpRating;
    
    private Date currentTMDate;
    private Date currentPMDate;
    private Date currentHRDate;
    private Date currentVPDate;
    
    
    
    
    
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
    
    public String getComments1() {
        return comments1;
    }
    
    public void setComments1(String comments1) {
        this.comments1 = comments1;
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
    
    public String getComments2() {
        return comments2;
    }
    
    public void setComments2(String comments2) {
        this.comments2 = comments2;
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
    
    public String getComments3() {
        return comments3;
    }
    
    public void setComments3(String comments3) {
        this.comments3 = comments3;
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
    
    public String getComments4() {
        return comments4;
    }
    
    public void setComments4(String comments4) {
        this.comments4 = comments4;
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
    
    public String getComments5() {
        return comments5;
    }
    
    public void setComments5(String comments5) {
        this.comments5 = comments5;
    }
    
    public int getAppraisalId() {
        return appraisalId;
    }
    
    public void setAppraisalId(int appraisalId) {
        this.appraisalId = appraisalId;
    }
    
    public int getCurrentEmployeeId() {
        return currentEmployeeId;
    }
    
    public void setCurrentEmployeeId(int currentEmployeeId) {
        this.currentEmployeeId = currentEmployeeId;
    }
    
    public String getEmpName() {
        return empName;
    }
    
    public void setEmpName(String empName) {
        this.empName = empName;
    }
    
    /** Details of individuals **/
    
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
    
    public String getTlcomments() {
        return tlcomments;
    }
    
    public void setTlcomments(String tlcomments) {
        this.tlcomments = tlcomments;
    }
    
    public String getEName() {
        return eName;
    }
    
    public void setEName(String eName) {
        this.eName = eName;
    }
    
    public String getTlRating() {
        return tlRating;
    }
    
    public void setTlRating(String tlRating) {
        this.tlRating = tlRating;
    }
    
    public String getCurrentDate() {
        return currentDate;
    }
    
    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
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

    public Date getCurrentTMDate() {
        return currentTMDate;
    }

    public void setCurrentTMDate(Date currentTMDate) {
        this.currentTMDate = currentTMDate;
    }

    public Date getCurrentPMDate() {
        return currentPMDate;
    }

    public void setCurrentPMDate(Date currentPMDate) {
        this.currentPMDate = currentPMDate;
    }

    public Date getCurrentHRDate() {
        return currentHRDate;
    }

    public void setCurrentHRDate(Date currentHRDate) {
        this.currentHRDate = currentHRDate;
    }

    public Date getCurrentVPDate() {
        return currentVPDate;
    }

    public void setCurrentVPDate(Date currentVPDate) {
        this.currentVPDate = currentVPDate;
    }
}
