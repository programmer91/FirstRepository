/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.employee.appreciation;

import com.mss.mirage.util.ServiceLocatorException;
import java.util.Map;

/**
 *
 * @author miracle
 */
public interface AppreciationService {
    
    public int insertAppreciationService(AppreciationAction appreciationAction) throws ServiceLocatorException;
    public String getAppreciationDetailsById(AppreciationAction appreciationAction) throws ServiceLocatorException;
    public Map getAppreciationEmailValues(AppreciationAction appreciationAction) throws ServiceLocatorException;

}
