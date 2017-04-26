 /*
 * DefaultDataProvider.java
 *
 * Created on January 5, 2008, 5:56 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author miracle
 */
public class DefaultDataProvider {
    private static DefaultDataProvider _instance;
    
    
    /** Creates a new instance of DefaultDataProvider */
    public DefaultDataProvider() {
    }
    
    /**
     * @return An instance of the DataServiceLocator class
     * @throws ServiceLocatorException
     */
    public static DefaultDataProvider getInstance() throws ServiceLocatorException {
        try {
            if(_instance==null) {
                _instance = new DefaultDataProvider();
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
        }
        return _instance;
    }
    
    /**
     *@ returns List for salutation.
     *@ throws
     */
    public List getSalutationList(String salutationKey) throws ServiceLocatorException {
        List salutationList = new ArrayList();//Description
        if (CacheManager.getCache().containsKey(salutationKey)) {
            salutationList  = (List) CacheManager.getCache().get(salutationKey); //returning sigleton object.
        } else {
            salutationList.add("Dr.");
            salutationList.add("Jr.");
            salutationList.add("Mr.");
            salutationList.add("Ms.");
            salutationList.add("Mrs.");
            salutationList.add("Mss.");
            salutationList.add("Sir.");
            salutationList.add("Sr.");
            CacheManager.getCache().put(salutationKey, salutationList);
        }// closing if condition.
        
        return salutationList; // returning the object.
    }//closing this method
    
    /**
     *@ returns Map for preffered Question.
     *@ throws
     */
    public Map getPrefferedQuestions(String prefferedQuestionKey)  throws ServiceLocatorException{
        Map prefferedQuestionsMap = new TreeMap();//key-Description
        if (CacheManager.getCache().containsKey(prefferedQuestionKey)){
            prefferedQuestionsMap  = (Map) CacheManager.getCache().get(prefferedQuestionKey); //returning sigleton object.
        } else {
            
            prefferedQuestionsMap.put("1","Who was your childhood hero?");
            prefferedQuestionsMap.put("2","What is your favourite pass time?");
            prefferedQuestionsMap.put("3","What is your pets name?");
            prefferedQuestionsMap.put("4","What was your high school mascot?");
            prefferedQuestionsMap.put("5","What is your favourite dish?");
            CacheManager.getCache().put(prefferedQuestionKey, prefferedQuestionsMap);
        }// closing if condition.
        
        return prefferedQuestionsMap; // returning the object.
    } //closing the method.
    
    /**
     *@ returns List for gender.
     *@ throws
     */
    public List getGender(String genderKey)  throws ServiceLocatorException{
        List genderList = new ArrayList(); //Description
        if (CacheManager.getCache().containsKey(genderKey)){
            genderList  = (List) CacheManager.getCache().get(genderKey); //returning sigleton object.
        } else {
            
            genderList.add("Male");
            genderList.add("Female");
            
            CacheManager.getCache().put(genderKey, genderList);
        }// closing if condition.
        
        return genderList; // returning the object.
    } //closing the method.
    
    /**
     *@ returns List for Marital Status.
     *@ throws
     */
    public List getMaritalStatus(String maritalStatusKey)  throws ServiceLocatorException{
        List maritalStatusList = new ArrayList();//Description
        if (CacheManager.getCache().containsKey(maritalStatusKey)) {
            maritalStatusList  = (List) CacheManager.getCache().get(maritalStatusKey); //returning sigleton object.
        } else {
            
            maritalStatusList.add("Single");
            maritalStatusList.add("Married");
            maritalStatusList.add("Divorced");
            
            CacheManager.getCache().put(maritalStatusKey, maritalStatusList);
        }// closing if condition.
        
        return maritalStatusList; // returning the object.
    } //closing the method.
    
    /**
     *@ returns List for Current Status.
     *@ throws
     */
    public List getCurrentStatus(String currStatusKey) throws ServiceLocatorException{
        List currStatusList = new ArrayList();//Description
        if (CacheManager.getCache().containsKey(currStatusKey)) {
            currStatusList  = (List) CacheManager.getCache().get(currStatusKey); //returning sigleton object.
        } else {
            
            currStatusList.add("Registered");
            currStatusList.add("Training");
            currStatusList.add("Active");
            currStatusList.add("All");
            currStatusList.add("Inactive");
            currStatusList.add("Transfer");
            currStatusList.add("Long-Term Leave");
           
            CacheManager.getCache().put(currStatusKey, currStatusList);
        }// closing if condition.
        
        return currStatusList; // returning the object.
    } //closing the method.
    
    
    public Map<String, String> getRoleStatusMap() throws ServiceLocatorException{
        Map<String, String> roleStatusMap = new TreeMap<String, String>();//Description
      /*  if (CacheManager.getCache().containsKey(currStatusKey)) {
            roleStatusMap  = (Map) CacheManager.getCache().get(currStatusKey); //returning sigleton object.
        } else { */
        roleStatusMap.put("0"," ");
        roleStatusMap.put("1","Admin");
        roleStatusMap.put("2","Employee");
        roleStatusMap.put("3","Operations");
        roleStatusMap.put("4","Sales");
        roleStatusMap.put("5","Marketing");
        roleStatusMap.put("6","Recruitment");
        roleStatusMap.put("7","HR");
        roleStatusMap.put("8","Vendor");
        roleStatusMap.put("9","Customer Manager");
          /*  CacheManager.getCache().put(currStatusKey, roleStatusMap);
        }// closing if condition.
           */
        return roleStatusMap; // returning the object.
    }
    
    /**
     *@ returns List for activity priority.
     *@ throws
     */
    public List getCrmActivityPriority(String activityPriorityKey) throws ServiceLocatorException{
        List crmActivityPriorityList = new ArrayList();//Description
        if (CacheManager.getCache().containsKey(activityPriorityKey)) {
            crmActivityPriorityList  = (List) CacheManager.getCache().get(activityPriorityKey); //returning sigleton object.
        } else {
            
            crmActivityPriorityList.add("Low");
            crmActivityPriorityList.add("Medium");
            crmActivityPriorityList.add("High");
            CacheManager.getCache().put(activityPriorityKey, crmActivityPriorityList);
        }// closing if condition.
        
        return crmActivityPriorityList; // returning the object.
    }//closing this method
    
    /**
     *@ returns List for Tech Leads In REquirement.
     *@ throws
     */
    public List getTechLead(String techLeadKey) throws ServiceLocatorException{
        List techLeadList = new ArrayList();//Description
        if (CacheManager.getCache().containsKey(techLeadKey)) {
            techLeadList  = (List) CacheManager.getCache().get(techLeadKey); //returning sigleton object.
        } else {
            
            //techLeadList.add("Bhagavan Veda Vyas.Avula");
            techLeadList.add("Chandhana .Nagineni");
            techLeadList.add("Jithendranath .Bondada");
            
            //techLeadList.add("Sai Venkata Rama Sesha.Kastury");
            techLeadList.add("Sai Venkata Rama Sesha .Kastury");       
            techLeadList.add("Sudhakar .Ghandikota");
            techLeadList.add("Manga Devi.Motupalli");
         
            techLeadList.add("Maruthi Prasad .Kampli");
            techLeadList.add("Kiran Venkata Rama Mani.Mandila");
            techLeadList.add("Giridhar .Dittakavi");
            techLeadList.add("Srinivas .Ganti");
            CacheManager.getCache().put(techLeadKey, techLeadList);
        }// closing if condition.
        
        return techLeadList; // returning the object.
    } //closing the method.
    
    public List getPracticeManager() throws ServiceLocatorException{
        List practiceManagersList=new ArrayList();
        practiceManagersList.add("sghandikota");
        return practiceManagersList;
    }
    
    
    /**
     * Author : Nag
     * Date   : 07 Dec 2011
     *
     * @ returns Map for Organization.
     * @ throws
     *
     */
    
    public Map getOrgMap(String orgKey)  throws ServiceLocatorException{
        Map orgMap = new TreeMap();//key-Description
        if (CacheManager.getCache().containsKey(orgKey)){
            orgMap  = (Map) CacheManager.getCache().get(orgKey); //returning sigleton object.
        } else {
            
            orgMap.put("1INDIA","MSS India");
            orgMap.put("2USA","MSS USA");            
            orgMap.put("3UK","MSS UK");
            orgMap.put("4EU","MSS EU");
            orgMap.put("5AUS","MSS AUS");
            CacheManager.getCache().put(orgKey, orgMap);
        }// closing if condition.
        
        return orgMap; // returning the object.
    } //closing the method.

    public Map getExpMap(String expKey)  throws ServiceLocatorException{
        Map expMap = new TreeMap();//key-Description
        if (CacheManager.getCache().containsKey(expKey)){
            expMap  = (Map) CacheManager.getCache().get(expKey); //returning sigleton object.
        } else {
            
            expMap.put("Below 3yrs","Below 3yrs");
            expMap.put("3-5 yrs","3-5 yrs");            
            expMap.put("5-7 yrs","5-7 yrs");
            expMap.put("Above 10yrs","Above 10yrs");
            //expMap.put("5AUS","MSS AUS");
            CacheManager.getCache().put(expKey, expMap);
        }// closing if condition.
        
        return expMap; // returning the object.
    } //closing the method. 
       
   
}
  
 
