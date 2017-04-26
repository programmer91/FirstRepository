 /*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  November 27, 2007, 12:35 PM
 *
 * Author  : Rajanikanth Teppala<rteppala@miraclesoft.com>
 *
 * File    : EmpInsuranceService.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.employee.insurance;
import com.mss.mirage.util.ServiceLocatorException;

/**
 *
 * @author Rajanikanth Teppala<rteppala@miraclesoft.com>
 *  This INTERFACE.... ENTER THE DESCRIPTION HERE
 */

public interface EmpInsuranceService {
    
    
      public int checkAction(int empid)throws ServiceLocatorException;
     /**  This method can be used to add the new Insurance.
	 *
	 * 
	 *
     * @return is a boolean
     */
    public boolean doInsuranceAdd(EmpInsuranceAction empInsuranceAction);
    
     /**  This method can be used to Edit the new Insurance.
	 *
	 * 
	 *
     * @return is a boolean
     */
    public boolean doInsuranceEdit(EmpInsuranceAction empInsuranceAction) throws ServiceLocatorException;
    
    /**  This method used to Edit the Insurance Details.
	 *
	 * 
	 *
     * @return is a boolean
     */
    public EmpInsuranceVTO getInsurance(int empId) throws ServiceLocatorException; 
    
}
