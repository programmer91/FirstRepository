/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.util;

/**
 *
 * @author miracle
 */

public interface MailServices {
    
    /** Creates a new instance of AjaxHandlerService */
    
    
    
  
    public String doAddEmailLog(String toAddress, String cCAddress, String subject,String bodyContent,String createdDate,String bCCAddress) throws ServiceLocatorException;
    public String doAddEmailLogNew(String toAddress, String cCAddress, String subject,String bodyContent,String createdDate,String bCCAddress,String Category) throws ServiceLocatorException;
     public String doAddEmailLogWithFromAddress(String toAddress, String cCAddress, String subject,String bodyContent,String createdDate,String bCCAddress,String Category,String fromAddress) throws ServiceLocatorException;

    /**
     *
     * This method is used to get the Contact Details
     * @param contactId
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    
    
}
