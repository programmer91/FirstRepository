/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :com.mss.mirage.recruitment.consultantdetails
 *
 * Date    :   Created on October 31, 2007, 3:57 PM
 *
 * Author  : Kondaiah Adapa<kadapa@miraclesoft.com>
 *
 * File    : ConsultantSkillBean.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
package com.mss.mirage.recruitment.consultantdetails;

/**
 *
  * The <code>ConsultantSkillBean</code>class is used for getting  Consultant details from
 * <i>ConsultantDetails.jsp</i>    
 *
 * @author  Kondaiah Adapa<a href="mailto:kadapa@miraclesoft.com">kadapa@miraclesoft.com</a>
 *
 *
 *This Class.... ENTER THE DESCRIPTION HERE
 *
 */
public class ConsultantSkillBean {
    
    /**
     * Creates a new instance of ConsultantSkillBean
     */
    public ConsultantSkillBean() {
    }
 /** The id is Useful to get the consultant skills. */   
private int  id;

 /** The empId is Useful to get the emp id. */ 
private int empId;

 /** The yearsOfExperience is Useful to get the yearsOf Experience of consultant. */ 
private String yearsOfExperience;

 /** The skillSetUtilized is Useful to get a skills utilized by the Consultant */ 
private String skillsetUtilized;

 /** The skillSet is Useful to get a different technical skills of the Consultant */ 
private String skillSet;

 /** The projectInfo is Useful to get the project information. */ 
private String projectInfo;

/** The actionName is Useful to get the actionName. */ 
private String actionName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(String yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getSkillsetUtilized() {
        return skillsetUtilized;
    }

    public void setSkillsetUtilized(String skillsetUtilized) {
        this.skillsetUtilized = skillsetUtilized;
    }

    public String getSkillSet() {
        return skillSet;
    }

    public void setSkillSet(String skillSet) {
        this.skillSet = skillSet;
    }

    public String getProjectInfo() {
        return projectInfo;
    }

    public void setProjectInfo(String projectInfo) {
        this.projectInfo = projectInfo;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

}
