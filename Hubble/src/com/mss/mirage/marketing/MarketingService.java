/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  January 25, 2008, 6:13 PM
 *
 * Author  : Rajanikanth Teppala<rteppala@miraclesoft.com>
 *
 * File    : ScreenService.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
package com.mss.mirage.marketing;

import com.mss.mirage.util.ServiceLocatorException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rajanikanth Teppala<rteppala@miraclesoft.com>
 */
public interface MarketingService {

    /**
     * 
     * This method can be used to get the Activities of Account
     * @param activityId 
     * @throws com.mss.mirage.util.ServiceLocatorException 
     * @return MarketingVTO
     */
    public MarketingVTO getActivity(int activityId) throws ServiceLocatorException;

    /**
     * 
     * This method can be used to get the values of form field values
     * @param screenAction 
     * @return MarketingVTO
     * @throws com.mss.mirage.util.ServiceLocatorException 
     */
    public MarketingVTO getScreenActivityVTO(MarketingAction screenAction) throws ServiceLocatorException;

    public List doJobSearch(MarketingAction marketingAction) throws ServiceLocatorException;

    public List getApplicantsList(MarketingAction marketingAction) throws ServiceLocatorException;

    public List getLocationList() throws ServiceLocatorException;

    public List websiteLatestJobApplications() throws ServiceLocatorException;

    public List doEventSearch(MarketingAction marketingAction) throws ServiceLocatorException;

    public List getEventSpeakers(MarketingAction marketingAction) throws ServiceLocatorException;

    public List searchWebsiteInfo(MarketingAction marketingAction) throws ServiceLocatorException;

    public List doCompletedEventSearch(MarketingAction marketingAction) throws ServiceLocatorException;

    public List getTrackNamesList() throws ServiceLocatorException;

    public Map getQmeetMap(MarketingAction marketingAction) throws ServiceLocatorException;

    public List getWebinarSeriesList(MarketingAction marketingAction) throws ServiceLocatorException;

    public List getEventsListBySeries(MarketingAction marketingAction) throws ServiceLocatorException;

    public Map getUnAssociatedEvents(String seriesId) throws Exception;

    public String addEventToSeries(String seriesId, String eventId) throws Exception;
//------

    public String getPrimarySpeaker(int eventId, String objectType) throws ServiceLocatorException;

    public Map getSpeakersByEventId(int eventId, String primaryTeamMember, String objectType) throws ServiceLocatorException;

    public Map getAllPeaopleExceptEventSpeakers(Map eventSpeakerMap) throws ServiceLocatorException;

    public int updateEventSpeakers(String[] eventSpeakers, int eventId, String primaryId, HttpSession httpsession, String objectType) throws ServiceLocatorException;

    public String getEventTitleByEventId(String eventId, String ObjectType) throws Exception;

    public List eventSpeakerDetailsList(String eventId, String objectType) throws ServiceLocatorException;

    public Map getLkpTrackNamesMap() throws ServiceLocatorException;

    public List searchPeopleList(MarketingAction marketingAction) throws ServiceLocatorException;
    
    
 public boolean deleteInvestment(int id) throws ServiceLocatorException;
   public String generateInvestmentXls(String queryString)throws ServiceLocatorException;
   public String getInvestmentAttachments(int investmentId) throws ServiceLocatorException;
   
    public int addCampaign(MarketingAction marketingAction) throws ServiceLocatorException;
   public String getCampaignDetails(MarketingAction marketingAction) throws ServiceLocatorException;
   public int updateCampaign(MarketingAction marketingAction) throws ServiceLocatorException;
   //public String getCampaignContactsExcel(HttpServletRequest httpServletRequest,int campaignId)throws ServiceLocatorException;
   public String getCampaignContactsExcel(HttpServletRequest httpServletRequest,MarketingAction marketingAction)throws ServiceLocatorException;
   
   public String doAddLeadsDetails( MarketingAction marketingAction) throws ServiceLocatorException;
   public void doEditLeadsDetails( MarketingAction marketingAction) throws ServiceLocatorException;
   public String doUpdateLeads( MarketingAction marketingAction) throws ServiceLocatorException;
    public int generateExcelInvestment(String queryString) throws ServiceLocatorException;
    
public List doEmeetSearch(MarketingAction marketingAction) throws ServiceLocatorException;
   public List doEmeetAttendeesSearch(MarketingAction marketingAction) throws ServiceLocatorException;
   public String doPublishExeMeet(MarketingAction marketingAction) throws ServiceLocatorException ;
   public String doActiveExeMeet(MarketingAction marketingAction) throws ServiceLocatorException ;

	public Map getAllBDMsExceptInvestmentBDMs(Map investmentBDMMap) throws ServiceLocatorException;
public Map getBDMsByInvestmentId(int investmentId) throws ServiceLocatorException;
public int updateInvestmentBdms(String[] investmentBdms,int investmentId) throws ServiceLocatorException ;
public List investmentBdmDetailsList(int investmentId) throws ServiceLocatorException;
public Map getLeadLinkedAccountsMap() throws ServiceLocatorException;
public Map getConferencesList(String status) throws ServiceLocatorException;
}
