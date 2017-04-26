/*
 * BridgeConferenceService.java
 *
 * Created on April 3, 2008, 7:52 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.employee.bridgeconference;

import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.AuthorizationManager;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.ServiceLocator;
import com.mss.mirage.util.ServiceLocatorException;
/**
 *
 * @author miracle
 */
public interface BridgeConferenceService {
    
    /** Creates a new instance of BridgeConferenceService */
  public boolean addBridgeSchedule(BridgeConferenceAction conferenceAction) throws ServiceLocatorException ; 

  public boolean editBridgeSchedule(BridgeConferenceAction conferenceAction,int bridgeId) throws ServiceLocatorException;
  
  public boolean delete(int bridgeId) throws ServiceLocatorException;
  public BridgeConferenceVTO getBridge(int bridgeId) throws ServiceLocatorException;
    
}
