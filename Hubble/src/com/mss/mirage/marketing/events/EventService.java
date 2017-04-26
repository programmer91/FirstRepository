/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.marketing.events;

import com.mss.mirage.util.ServiceLocatorException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author miracle
 */
public interface EventService {
    public List doInternalEventSearch(EventAction eventAction) throws ServiceLocatorException;
    public List doExternalEventSearch(EventAction eventAction) throws ServiceLocatorException;
    public List doInternalEmeetSearch(EventAction eventAction) throws ServiceLocatorException;
    public String doPublishInternalExeMeet(EventAction eventAction) throws ServiceLocatorException ;
    public String doActiveInternalExeMeet(EventAction eventAction) throws ServiceLocatorException;
    
    /*public List getWebinarsListBySeriesId(EventAction eventAction) throws ServiceLocatorException;
    public Map getUnAssociatedWebinars(String seriesId) throws Exception;
    public String addWebinarToSeries(String seriesId,String eventId) throws Exception;*/
    public List getQmeetAttendees(EventAction eventAction) throws ServiceLocatorException;
    public List doExecuteMeetAttendeesSearch(EventAction eventAction) throws ServiceLocatorException;
    
}
