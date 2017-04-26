/*
 *******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package : com.mss.mirage.employee.genaral
 *
 * Date    :  September 24, 2007, 10:18 PM
 *
 * Author  : Praveen Kumar Ralla<pralla@miraclesoft.com>
 *
 * File    : EmployeeService.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
package com.mss.mirage.employee.Reviews;


import com.mss.mirage.employee.general.StateVTO;
import com.mss.mirage.util.ServiceLocatorException;
import java.util.Collection;
import java.util.List;

public interface ReviewsService {
    
    
  
    
    public boolean getInsertReview(ReviewsAction reviewPojo) throws ServiceLocatorException;
    
   
    public ReviewVTO getReview(int reviewId) throws ServiceLocatorException;
    
  public boolean doEditReview(ReviewsAction reviewsAction) throws ServiceLocatorException;
    
    
       
  public boolean deleteReview(int reviewId) throws ServiceLocatorException;
    
      public List getCompetitionBoard(String empName,String department,String startDate,String endDate) throws ServiceLocatorException;

   
}
