/*
 * CreLoginService.java
 *
 * Created on August 27, 2013, 3:34 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.cre.general;

import com.mss.mirage.util.ServiceLocatorException;



/**
 *
 * @author miracle
 */
public interface CreLoginService {
    
    public String[] creLoginCheck(String consultantId) throws ServiceLocatorException;
  
}
