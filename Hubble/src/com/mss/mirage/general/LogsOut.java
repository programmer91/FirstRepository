/*
 * LogsOut.java
 *
 * Created on January 21, 2010, 2:57 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.general;

import com.opensymphony.xwork2.ActionSupport;

/**
 *
 * @author miracle
 */
public class LogsOut extends ActionSupport {
    
    private String resultType;
    
    /** Creates a new instance of LogsOut */
        public String test() throws Exception {
        try{
          
            resultType =SUCCESS;
        }catch(Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            resultType =  ERROR;
        }
        return resultType;
    }
    
}
