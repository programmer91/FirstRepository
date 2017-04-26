/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  September 26, 2007, 12:36 PM
 *
 * Author  : Rajanikanth Teppala<rteppala@miraclesoft.com>
 *
 * File    : AccountService.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.crm.accounts;

import com.mss.mirage.util.ServiceLocatorException;
import java.util.List;
import java.util.Map;
//new
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rajanikanth Teppala<rteppala@miraclesoft.com>
 *
 * This interface.... ENTER THE DESCRIPTION HERE
 */
public interface AccountService {
    
    public int addOrUpdateAccount(AccountAction accountPojo) throws ServiceLocatorException;
    public AccountVTO getAccount(int accountId, String userWorkCountry, String userRoleName) throws ServiceLocatorException;
    public int updateAccountTeamDetails(int accountId,String teamId) throws ServiceLocatorException;
    public AccountVTO getAccountVTO(AccountAction accountPojo) throws ServiceLocatorException;
    public List getAccountsList(String queryString) throws ServiceLocatorException;
    public int editSoftware(SoftwareAction softwarePojo) throws ServiceLocatorException;
    public int addSoftware(SoftwareAction softwarePojo) throws ServiceLocatorException;
    public SoftwareVTO getSoftwareDetails(int accountId) throws ServiceLocatorException;
    public Map getAccountTeamByAccountId(int accountId,String primaryTeamMember) throws ServiceLocatorException;
    public Map getAllSalesTeamExceptAccountTeam(Map accountTeam) throws ServiceLocatorException;
    public Map getAllVendorTeam(Map VendorTeam) throws ServiceLocatorException;
   // public int updateAccountTeamMembers(String[] accountTeamMembers,int accountId,String teamId ,int roleId , HttpSession httpsession) throws ServiceLocatorException;
    //before applying log
   //public int updateAccountTeamMembers(String[] accountTeamMembers,int accountId) throws ServiceLocatorException;
    //After applying log
    public int updateAccountTeamMembers(String[] accountTeamMembers,int accountId,String teamId ,int roleId , HttpSession httpsession) throws ServiceLocatorException;
    public String getPrimaryTeamMember(int accountId) throws ServiceLocatorException;
    public int updateAssignAccounts(String fromUserId,String toUserId) throws ServiceLocatorException;
    public String getContactEmailId(int contatId) throws ServiceLocatorException;
    public int createContactLogin(int contactId,String accountId,String emailId,String loginId) throws ServiceLocatorException;

    
    public int doClientReqEngagementAdd(AccountAction accountAction) throws ServiceLocatorException;
    public void getClientEngagementDetails(AccountAction accountAction) throws ServiceLocatorException;
    public int doClientReqEngagementUpdate(AccountAction accountAction) throws ServiceLocatorException;
    public int doClientReqEngagementUpdateByStatus(int requestId,String previousStage,String currentStage) throws ServiceLocatorException;
    public int doClientReqEngagementDeleteByStatus(int requestId) throws ServiceLocatorException;
  
    
}
