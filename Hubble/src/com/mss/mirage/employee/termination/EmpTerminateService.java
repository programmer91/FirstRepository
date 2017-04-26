/*
 * EmpTerminateService.java
 *
 * Created on July 7, 2008, 7:01 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.employee.termination;

import com.mss.mirage.util.ServiceLocatorException;

/**
 * Creates a new instance of EmpTerminateService
 * @author miracle
 */
public interface EmpTerminateService {
    
    /**
     * 
     * This method is used to get the Primary Details of Employee
     * @param empId 
     * @return EmpTerminateVTO
     * @throws com.mss.mirage.util.ServiceLocatorException 
     */
   public  EmpTerminateVTO getTermination(int empId) throws ServiceLocatorException;
    /**
     * 
     * This method is used to update the Primary Details of Employee
     * @param empAction 
     * @param empId 
     * @return boolean
     * @throws com.mss.mirage.util.ServiceLocatorException 
     */
   public boolean updateTermination(EmpTerminateAction empAction,int empId) throws ServiceLocatorException;
    
}
