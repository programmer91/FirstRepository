/*
 * EmployeeAppraisalService.java
 *
 * Created on May 15, 2008, 3:20 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.employee.appraisal;

import com.mss.mirage.util.ServiceLocatorException;

/**
 *
 * @author miracle
 */
public interface  EmployeeAppraisalService {
    
    /** Creates a new instance of EmployeeAppraisalService */
    public EmployeeAppraisalVTO getPersonalDetails(int empId,int appraisalId) throws ServiceLocatorException ;
    public  boolean addOrUpdateProjectDetails(EmployeeAppraisalAction empAppraisalAction, int empId,String operationalType)throws ServiceLocatorException;
    public boolean addOrUpdateDetailsOfIndividuals(EmployeeAppraisalAction empAppraisalAction,String operationalType ) throws ServiceLocatorException;
    public int addOrUpdateEmployeeAssessment(EmployeeAppraisalAction empAppraisalAction,int empId,String operationalType) throws ServiceLocatorException;
    public boolean addOrUpdateTLComments(EmployeeAppraisalAction action,String operationType)throws ServiceLocatorException;
    
}
