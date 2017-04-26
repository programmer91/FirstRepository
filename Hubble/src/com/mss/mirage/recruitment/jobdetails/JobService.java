/*
 * JobService.java
 *
 * Created on January 3, 2010, 6:42 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.recruitment.jobdetails;

import com.mss.mirage.util.ServiceLocatorException;

import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author miracle
 */
public interface JobService {
 
    /**
     * this method is for adding phonelog
     * @param action 
     * @throws com.mss.mirage.util.ServiceLocatorException 
     * @return 
     */    
public boolean addJob(JobAction action)throws ServiceLocatorException;    

    /**
     * this method is for editing the phonelog
     * @param action 
     * @param PhoneLogId 
     * @throws com.mss.mirage.util.ServiceLocatorException 
     * @return 
     */
public boolean editJob(JobAction action,int JobId)throws ServiceLocatorException;

    /**
     * this method is for deleting the phonelog record
     * @param action 
     * @param PhoneLogId 
     * @throws com.mss.mirage.util.ServiceLocatorException 
     * @return 
     */
public boolean deleteJob(JobAction action,int JobId) throws ServiceLocatorException;

    /**
     * this method is for editing the particular phonelog record
     * @param PhoneLogId 
     * @throws com.mss.mirage.util.ServiceLocatorException 
     * @return 
     */
public JobVTO getJob(int JobId)throws ServiceLocatorException;


public String generateTitleReport(String query,JobAction jobaction,HttpServletResponse httpServletResponse) throws ServiceLocatorException;



}




