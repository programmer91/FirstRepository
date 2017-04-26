/*
 * @(#)GeneralService.java 1.0 Nov 01, 2007
 *
 * Copyright 2006 Miracle Software Systems(INDIA) Pvt Ltd. All rights reserved.
 *
 */

package com.mss.mirage.general;

import com.mss.mirage.util.ServiceLocatorException;

/**
 *
 * @author RajaReddy.Andra <a href="mailto:randra@miraclesoft.com">randra@miraclesoft.com</a>
 *
 * @version 1.0 Nov 01, 2007
 *
 * @see com.mss.mirage.util.ServiceLocatorException
 *
 */
public interface GeneralService {
    
    /**
     *addUser(RegistrationAction registrationAction) is used for to register user into MirageV2
     *this method type is boolean
     *it throws ServiceLocatorException
     */
    public boolean addUser(RegistrationAction registrationAction) throws ServiceLocatorException;
    
    /**
     *generateUserId(String mailId) is used for generate userId to login into MirageV2,it returns
     *String .this method used in GeneralServiceImpl.java class
     */
    public String generateUserId(String mailId);
    
    /**
     *generatePassword(int noOfCharacters) is used for to generate automated String,this method type is String
     *if a user registered in MirageV2 first time he will get this string as default password
     */
    public String generatePassword(int noOfCharacters);
    
    /**
     *regetPassword(PasswordAction passwordAction)is used for to get password if
     *suppose i will forget that password.retrun type is boolean this method throws
     *ServiceLocatorException Exceptions
     *
     */
    public boolean regetPassword(PasswordAction passwordAction) throws ServiceLocatorException;
    
    public String getPassword(String userId) throws ServiceLocatorException;
    
    /**
     *updatePassword(PasswordAction passwordAction) is used to reset oldPassword,the return type
     *is boolean ,it throws ServiceLocatorException exceptions
     */
    //public boolean updatePassword(PasswordAction passwordAction) throws ServiceLocatorException;
    public int updatePassword(PasswordAction passwordAction) throws ServiceLocatorException;
    /**
     *userCheckExist(RegistrationAction registrationAction) is used to check the user already exist
     * in MirageV2 or not ,if exist it wont't accept again.otherwise it will accept.
     *the return type is boolean it throws ServiceLocatorException exceptions
     */
    public boolean userCheckExist(RegistrationAction registrationAction) throws ServiceLocatorException;
    
      /**
     *userCheckExist(RegistrationAction registrationAction) is used to check the user already exist
     * in MirageV2 or not ,if exist it wont't accept again.otherwise it will accept.
     *the return type is boolean it throws ServiceLocatorException exceptions
     */
    
    public int updateCustPassword(PasswordAction passwordAction) throws ServiceLocatorException;
    /**
     *getPrimaryAction(int roleId) is used for get roleTypeId and name of the role in HomeAction class
     *the return type is String it throws ServiceLocatorException,
     *this method is used also for redirect to the screens
     */
    public String getPrimaryAction(int roleId) throws ServiceLocatorException;
    
    /**
     *getRoleName(int roleId) is used to get rolename in HomeAction class
     */
    public String getRoleName(int roleId) throws ServiceLocatorException;
    
    public int getUsableTeamHours(String usableDays, int holidays, int teamStreangth) throws  ServiceLocatorException;
    
    public int getUsedTeamHours() throws  ServiceLocatorException;
    
    public int getNaturalHolidayHours() throws  ServiceLocatorException;
    
    public int getTeamLeaveHours() throws  ServiceLocatorException;
    
    //NEW Sservice for forget password
    
    //public boolean regetPwd(String emailId,String eFlag) throws ServiceLocatorException;
    public String regetPwd(String emailId,String eFlag) throws ServiceLocatorException;
}




