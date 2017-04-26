/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package : com.mss.mirage.util
 *
 * Date    :  September 18, 2007, 1:29 AM
 *
 * Author  : Praveen Kumar Ralla<pralla@miraclesoft.com>
 *
 * File    : ApplicationDataProvider.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.util;


import com.mss.mirage.util.CacheManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.Session;



/**
 *
 * @author Praveen Kumar Ralla<pralla@miraclesoft.com>
 *
 * This Class.... ENTER THE DESCRIPTION HERE
 */
public class HibernateDataProvider {
    
    private static HibernateDataProvider _instance;
    private String id;
    private String countryName;
    private String regionName;
    private String description;
    private String name;
    private String firstName;
    private String lastName;
    private String middleName;
    private int bdmID;
    private int accountId;
    private int contactId;
    private int countryId;
    //newly added on 03282013
     private String status;
    public List territoryList = new ArrayList();
    
    public HibernateDataProvider(){
        
    }
    /**
     * @return An instance of the DataServiceLocator class
     * @throws ServiceLocatorException
     */
    public static HibernateDataProvider getInstance() throws ServiceLocatorException {
        try {
            if(_instance==null) {
                _instance = new HibernateDataProvider();
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
        }
        return _instance;
    }
    
    public List getProjectAttachType(String projectAttachTypeKey) throws ServiceLocatorException{
        List projectAttachTypeList = new ArrayList();//Description
        if (CacheManager.getCache().containsKey(projectAttachTypeKey)) {
            projectAttachTypeList  = (List) CacheManager.getCache().get(projectAttachTypeKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from ProjectAttachTypeData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                String desc = (String) it.next();
                //Storing values into the TreeList.
                projectAttachTypeList.add(desc);
                
            }// closing for loop.
            CacheManager.getCache().put(projectAttachTypeKey, projectAttachTypeList);
            
            try{
                // Closing hibernate session
                session.close();
                session=null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
        }// closing if condition.
        
        return projectAttachTypeList;// returning the object.
    }//closing the method.
    
    /**
     *@ returns map for Campaigns.
     *@ throws HibernateException.
     */
    public Map getCampaignNames(String campaignKey) throws
            ServiceLocatorException{
        Map campaignsMap = new TreeMap();//key-Description
        if (CacheManager.getCache().containsKey(campaignKey)) {
            campaignsMap  = (Map) CacheManager.getCache().get(campaignKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session =  HibernateServiceLocator.getInstance().getSession();
            
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            
            String SQL_STG = "select tp.id,tp.description from CampaignNamesData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                
                Object[] row = (Object[]) it.next();
                
                //Storing values into the TreeMap.
                campaignsMap.put(row[0].toString(),row[1].toString());
                
            }// closing for loop.
            
            //Storing the rolesMap object in to the cache as singleton object.
            CacheManager.getCache().put(campaignKey, campaignsMap);
            
            // Closing hibernate session
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        he.printStackTrace();
                    }
                }
            }
        }// closing if condition.
        
        return campaignsMap; // returning the object.
    }//closing the method
    
    
    /**
     *@ returns map for Roles.
     *@ throws HibernateException.
     */
    public Map getRoles(String rolesKey) throws ServiceLocatorException{
        Map rolesMap = new TreeMap();//key-Description
        if (CacheManager.getCache().containsKey(rolesKey)) {
            
            rolesMap  = (Map) CacheManager.getCache().get(rolesKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            
          //  String SQL_STG="select tp.id,tp.description from RolesData as tp WHERE tp.description!='Pre-Sales' AND tp.description!='Sourcing'";
            String SQL_STG="select tp.id,tp.description from RolesData as tp WHERE tp.description!='Pre-Sales' AND tp.description!='Sourcing' AND tp.description!='Recruitment Admin'";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                Object[] row = (Object[]) it.next();
                
                //Storing values into the TreeMap.
                rolesMap.put(row[0].toString(),row[1].toString());
                
            }// closing for loop.
            
            //Storing the rolesMap object in to the cache as singleton object.
            CacheManager.getCache().put(rolesKey, rolesMap);
            
            // Closing hibernate session
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        he.printStackTrace();
                    }
                }
            }
            
            
        }// closing if condition.
        
        return rolesMap; // returning the object.
    }//closing the method.
    
    
    
    /**
     *@ returns List for Countries.
     *@ throws HibernateException.
     */
    public List getContries(String countriesKey) throws ServiceLocatorException{
        List countryList = new ArrayList();//Description
        if (CacheManager.getCache().containsKey(countriesKey)) {
            countryList  = (List) CacheManager.getCache().get(countriesKey); //returning sigleton object.
        } else {
            
            //getting hibernate session from the HibernateServiceLocator class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from CountryData as tp";
            Query query=session.createQuery(SQL_STG);
            
            for(Iterator it=query.iterate();it.hasNext();){
                
                String desc = (String)it.next();
                
                //Storing values into the TreeList.
                countryList.add(desc);
                
            }  // closing for loop.
            
            
            CacheManager.getCache().put(countriesKey, countryList);
            
            try{
                
                // Closing hibernate session
                session.close();
                session = null;
                query=null;
            }catch(HibernateException he){
                he.printStackTrace();
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
        }// closing if condition.
        
        return countryList; // returning the object.
    } //closing the method.
    
    public List getTerritoryList(String qsterritorylist) throws ServiceLocatorException {
        
        if (CacheManager.getCache().containsKey(qsterritorylist)) {
            territoryList  = (List) CacheManager.getCache().get(qsterritorylist); //returning sigleton object.
        } else {
            
            //getting hibernate session from the HibernateServiceLocator class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from TerritoryData as tp";
            
            Query query=session.createQuery(SQL_STG);
            
            for(Iterator it=query.iterate();it.hasNext();){
                
                String desc = (String)it.next();
                
                //Storing values into the TreeList.
                territoryList.add(desc);
                
            }  // closing for loop.
            
            
            CacheManager.getCache().put(qsterritorylist, territoryList);
            
            try{
                
                // Closing hibernate session
                session.close();
                session = null;
                query=null;
            }catch(HibernateException he){
                he.printStackTrace();
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
        }// closing if condition.
        
        
        return territoryList;
    }
    
    
    public List getDefaultStatesList(int CountryCode) throws ServiceLocatorException{
        String defaultStatesKey="defaultStates";
        List statesList = new ArrayList();
        
        if (CacheManager.getCache().containsKey(defaultStatesKey)) {
            statesList  = (List) CacheManager.getCache().get(defaultStatesKey);
        } else {
            
            //getting hibernate session from the HibernateServiceLocator class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="SELECT tp.description FROM DefaultStatesData AS tp where tp.countryId=:country";
            
            Query query=session.createQuery(SQL_STG).setInteger("country",CountryCode);
            
            for(Iterator it=query.iterate();it.hasNext();){
                
                String desc = (String)it.next();
                
                //Storing values into the TreeList.
                statesList.add(desc);
                
            }  // closing for loop.
            
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
        }
        return statesList; // returning the object.
    } //closing the method.
    
//   method for getting EmpActivities list.
    public List getEmpActivities(String empActivityKey) throws ServiceLocatorException{
        List empActivityTypesList = new ArrayList();//Description
        if (CacheManager.getCache().containsKey(empActivityKey)) {
            empActivityTypesList  = (List) CacheManager.getCache().get(empActivityKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from EmpActivityData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                String desc = (String) it.next();
                //Storing values into the TreeList.
                empActivityTypesList.add(desc);
                
            }// closing for loop.
            CacheManager.getCache().put(empActivityKey, empActivityTypesList);
            
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
        }// closing if condition.
        
        return empActivityTypesList;// returning the object.
    }
    
    //PROJECTS LOOKUPS : START
    public List getProjectType(String projectTypeKey) throws ServiceLocatorException{
        List projectTypeList = new ArrayList();//Description
        if (CacheManager.getCache().containsKey(projectTypeKey)) {
            projectTypeList  = (List) CacheManager.getCache().get(projectTypeKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from ProjectTypeData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                String desc = (String) it.next();
                //Storing values into the TreeList.
                projectTypeList.add(desc);
                
            }// closing for loop.
            CacheManager.getCache().put(projectTypeKey, projectTypeList);
            
            try{
                // Closing hibernate session
                session.close();
                session=null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
        }// closing if condition.
        Collections.sort(projectTypeList);
        return projectTypeList;// returning the object.
    }//closing the method.
    
    
    public List getProjectStatusType(String projectStatusTypeKey) throws ServiceLocatorException{
        
        List projectStatusTypeList = new ArrayList();//Description
        
        if (CacheManager.getCache().containsKey(projectStatusTypeKey)) {
            projectStatusTypeList  = (List) CacheManager.getCache().get(projectStatusTypeKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from ProjectStatusTypeData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                String desc = (String) it.next();
                //Storing values into the TreeList.
                projectStatusTypeList.add(desc);
                
            }// closing for loop.
            CacheManager.getCache().put(projectStatusTypeKey, projectStatusTypeList);
            
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
        }// closing if condition.
        
        return projectStatusTypeList;// returning the object.
    }//closing the method.
    
    //PROJECTS LOOKUPS : END
    
    /*   method for getting TimeType list.*/
    public List getTime(String timeKey) throws ServiceLocatorException{
        List timeTypeList = new ArrayList();//Description
        if (CacheManager.getCache().containsKey(timeKey)) {
            timeTypeList  = (List) CacheManager.getCache().get(timeKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from TimeData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                String desc = (String) it.next();
                //Storing values into the TreeList.
                timeTypeList.add(desc);
                
            }// closing for loop.
            
            CacheManager.getCache().put(timeKey, timeTypeList);
            
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
        }// closing if condition.
        
        return timeTypeList;// returning the object.
    }//closing the method.
    
    /*   method for getting TimeSheetStatus list.*/
    public List getTimeSheetStatus(String timeSheetStatusKey) throws ServiceLocatorException{
        
        List timeSheetStatusTypesList = new ArrayList();//Description
        
        if (CacheManager.getCache().containsKey(timeSheetStatusKey)) {
            timeSheetStatusTypesList  = (List) CacheManager.getCache().get(timeSheetStatusKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from TimeSheetStatusData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                String desc = (String) it.next();
                //Storing values into the TreeList.
                timeSheetStatusTypesList.add(desc);
                
            }// closing for loop.
            
            CacheManager.getCache().put(timeSheetStatusKey, timeSheetStatusTypesList);
            
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
        }// closing if condition.
        
        return timeSheetStatusTypesList;// returning the object.
    }//closing the method.
    
    public List getLkOrganization(String lKOrganizationKey) throws ServiceLocatorException{
        List lKOrganizationList = new ArrayList();//Description
        if (CacheManager.getCache().containsKey(lKOrganizationKey)) {
            lKOrganizationList  = (List) CacheManager.getCache().get(lKOrganizationKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from LKOrganizationData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                String desc = (String) it.next();
                //Storing values into the TreeList.
                lKOrganizationList.add(desc);
                
            }// closing for loop.
           
            CacheManager.getCache().put(lKOrganizationKey, lKOrganizationList);
            
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
        }// closing if condition.
        
        return lKOrganizationList;// returning the object.
    }//closing the method.
    
    public List getDepartment(String lKDepartmentKey) throws ServiceLocatorException{
        List lKDepartmentList = new ArrayList();
        if (CacheManager.getCache().containsKey(lKDepartmentKey)) {
            lKDepartmentList  = (List) CacheManager.getCache().get(lKDepartmentKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from LKDepartmentData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                String desc = (String) it.next();
                //Storing values into the TreeList.
                lKDepartmentList.add(desc);
                
            }// closing for loop.
            
            CacheManager.getCache().put(lKDepartmentKey, lKDepartmentList);
            
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
        }// closing if condition.
        
        return lKDepartmentList;// returning the object.
    }//closing the method.
    
    public List getPractice(String lKPracticeKey) throws ServiceLocatorException{
        
        List lKPracticeList = new ArrayList();//Description
        
        if (CacheManager.getCache().containsKey(lKPracticeKey)) {
            lKPracticeList  = (List) CacheManager.getCache().get(lKPracticeKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from LKPracticeData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                String desc = (String) it.next();
                //Storing values into the TreeList.
                lKPracticeList.add(desc);
                
            }// closing for loop.
            
            CacheManager.getCache().put(lKPracticeKey, lKPracticeList);
            
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
        }// closing if condition.
        
        return lKPracticeList;// returning the object.
    }//closing the method.
    
    
    public List getIndustry(String lKIndustryKey) throws ServiceLocatorException{
        
        List lKIndustryList = new ArrayList();//Description
        
      /*  if (CacheManager.getCache().containsKey(lKIndustryKey)) {
            lKIndustryList  = (List) CacheManager.getCache().get(lKIndustryKey);
        } else {*/
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from LKIndustryData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                String desc = (String) it.next();
                //Storing values into the TreeList.
                lKIndustryList.add(desc);
                
            }// closing for loop.
            
          //  CacheManager.getCache().put(lKIndustryKey, lKIndustryList);
            
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
       // }// closing if condition.
        
        return lKIndustryList;// returning the object.
    }//closing the method.
    
    public List getRecConsultant(String recConsultantKey) throws ServiceLocatorException{
        
        List recConsultantTypesList = new ArrayList();//Description
        
        if (CacheManager.getCache().containsKey(recConsultantKey)) {
            recConsultantTypesList  = (List) CacheManager.getCache().get(recConsultantKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from RecConsultantData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                String desc = (String) it.next();
                //Storing values into the TreeList.
                recConsultantTypesList.add(desc);
                
            }// closing for loop.
            
            CacheManager.getCache().put(recConsultantKey, recConsultantTypesList);
            
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
        }// closing if condition.
        
        return recConsultantTypesList;// returning the object.
    }//closing the method.
    
    
    public List getCrmAccountType(String crmAccountKey) throws ServiceLocatorException{
        
        List crmAccountTypesList = new ArrayList();//Description
        
        if (CacheManager.getCache().containsKey(crmAccountKey)) {
            crmAccountTypesList  = (List) CacheManager.getCache().get(crmAccountKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from CrmAccountData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                String desc = (String) it.next();
                //Storing values into the TreeList.
                crmAccountTypesList.add(desc);
                
            }// closing for loop.
            
            CacheManager.getCache().put(crmAccountKey, crmAccountTypesList);
            
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
        }// closing if condition.
        
        return crmAccountTypesList;// returning the object.
    }//closing the method.
    
    
    public List getCrmAccountStatus(String crmAccountStatusKey) throws ServiceLocatorException{
        
        List crmAccountStatusTypesList = new ArrayList();//Description
        
        if (CacheManager.getCache().containsKey(crmAccountStatusKey)) {
            crmAccountStatusTypesList  = (List) CacheManager.getCache().get(crmAccountStatusKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from CrmAccountStatusData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                String desc = (String) it.next();
                //Storing values into the TreeList.
                crmAccountStatusTypesList.add(desc.trim());
                
            }// closing for loop.
            
            CacheManager.getCache().put(crmAccountStatusKey, crmAccountStatusTypesList);
            
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
        }// closing if condition.
        //System.out.println("crmAccountStatusKey-->"+crmAccountStatusTypesList.size());
        return crmAccountStatusTypesList;// returning the object.
    }//closing the method.
    
    
    public List getCrmActivityTypes(String crmActivityTypeKey) throws ServiceLocatorException{
        
        List crmActivityTypesList = new ArrayList();//Description
        
        if (CacheManager.getCache().containsKey(crmActivityTypeKey)) {
            crmActivityTypesList  = (List) CacheManager.getCache().get(crmActivityTypeKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            //String SQL_STG="select tp.description from CrmActivityData as tp";
            //newly added on 03282013
            String SQL_STG="select tp.description from CrmActivityData as tp where tp.status = 'Active'";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                String desc = (String) it.next();
                //Storing values into the TreeList.
                crmActivityTypesList.add(desc);
                
            }// closing for loop.
            //Sorting Elements
            Collections.sort(crmActivityTypesList);
            CacheManager.getCache().put(crmActivityTypeKey, crmActivityTypesList);
            
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
        }// closing if condition.
        
        return crmActivityTypesList;// returning the object.
    }//closing the method.
    
    
    public List getCrmAddress(String crmAddressKey) throws ServiceLocatorException{
        
        List crmAddressTypesList = new ArrayList();//Description
        
        if (CacheManager.getCache().containsKey(crmAddressKey)) {
            crmAddressTypesList  = (List) CacheManager.getCache().get(crmAddressKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from CrmAddressData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                String desc = (String) it.next();
                //Storing values into the TreeList.
                crmAddressTypesList.add(desc);
                
            }// closing for loop.
            
            CacheManager.getCache().put(crmAddressKey, crmAddressTypesList);
            
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
        }// closing if condition.
        
        return crmAddressTypesList;// returning the object.
    }//closing the method.
    
    
    public List getObject(String objectKey) throws ServiceLocatorException{
        
        List objectList = new ArrayList();//Description
        
        if (CacheManager.getCache().containsKey(objectKey)) {
            objectList  = (List) CacheManager.getCache().get(objectKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from ObjectData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                String desc = (String) it.next();
                //Storing values into the TreeList.
                objectList.add(desc);
                
            }// closing for loop.
            
            CacheManager.getCache().put(objectKey, objectList);
            
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
        }// closing if condition.
        
        return objectList;// returning the object.
    }//closing the method.
    
    
    public List getEmployeeType(String employeeTypeKey) throws ServiceLocatorException{
        
        List employeeTypesList = new ArrayList();//Description
        
        if (CacheManager.getCache().containsKey(employeeTypeKey)) {
            employeeTypesList  = (List) CacheManager.getCache().get(employeeTypeKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from EmployeeTypeData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                String desc = (String) it.next();
                //Storing values into the TreeList.
                employeeTypesList.add(desc);
                
            }// closing for loop.
            
            CacheManager.getCache().put(employeeTypeKey, employeeTypesList);
            
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
        }// closing if condition.
        
        return employeeTypesList;// returning the object.
    }//closing the method.
    
    
    public List getStage(String stageKey) throws ServiceLocatorException{
        
        List stageList = new ArrayList();//Description
        
        if (CacheManager.getCache().containsKey(stageKey)) {
            stageList  = (List) CacheManager.getCache().get(stageKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from StageData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                String desc = (String) it.next();
                //Storing values into the TreeList.
                stageList.add(desc);
                
            }// closing for loop.
            
            CacheManager.getCache().put(stageKey, stageList);
            
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
        }// closing if condition.
        
        return stageList;// returning the object.
    }//closing the method.
    
    
    public List getOpportunityType(String opportunitytypeKey) throws ServiceLocatorException{
        
        List opportunitytypeList = new ArrayList(); //Description
        
        if (CacheManager.getCache().containsKey(opportunitytypeKey)) {
            opportunitytypeList  = (List) CacheManager.getCache().get(opportunitytypeKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from opportunitytypeData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                String desc = (String) it.next();
                //Storing values into the TreeList.
                opportunitytypeList.add(desc);
                
            }// closing for loop.
            
            CacheManager.getCache().put(opportunitytypeKey, opportunitytypeList);
            
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
        }// closing if condition.
        Collections.sort(opportunitytypeList);
        return opportunitytypeList;// returning the object.
    }//closing the method.
    
    
    public List getTitleType(String titleTypeKey) throws ServiceLocatorException{
        
        List titleTypeList = new ArrayList();//Description
        
        if (CacheManager.getCache().containsKey(titleTypeKey)) {
            titleTypeList  = (List) CacheManager.getCache().get(titleTypeKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from titleTypeData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                String desc = (String) it.next();
                //Storing values into the TreeList.
                titleTypeList.add(desc);
                
            }// closing for loop.
            
            CacheManager.getCache().put(titleTypeKey, titleTypeList);
            
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
        }// closing if condition.
        
        return titleTypeList;// returning the object.
    }//closing the method.
    
    
    
    
    public List getPoLineCategory(String poLineCategoryKey) throws ServiceLocatorException{
        
        List poLineCategoryTypesList = new ArrayList();//Description
        
        if (CacheManager.getCache().containsKey(poLineCategoryKey)) {
            poLineCategoryTypesList  = (List) CacheManager.getCache().get(poLineCategoryKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from PoLineCategoryData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                String desc = (String) it.next();
                //Storing values into the TreeList.
                poLineCategoryTypesList.add(desc);
                
            }// closing for loop.
            
            CacheManager.getCache().put(poLineCategoryKey, poLineCategoryTypesList);
            
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
        }// closing if condition.
        
        return poLineCategoryTypesList;// returning the object.
    }//closing the method.
    
    public List getPoLine(String poLineKey) throws ServiceLocatorException{
        
        List poLineTypesList = new ArrayList();//Description
        
        if (CacheManager.getCache().containsKey(poLineKey)) {
            poLineTypesList  = (List) CacheManager.getCache().get(poLineKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from PoLineData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                String desc = (String) it.next();
                //Storing values into the TreeList.
                poLineTypesList.add(desc);
                
            }// closing for loop.
            
            CacheManager.getCache().put(poLineKey, poLineTypesList);
            
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
        }// closing if condition.
        
        return poLineTypesList;// returning the object.
    }//closing the method.
    
    
    public List getPoStatus(String poStatusKey) throws ServiceLocatorException{
        
        List poStatusList = new ArrayList();//Description
        
        if (CacheManager.getCache().containsKey(poStatusKey)) {
            poStatusList  = (List) CacheManager.getCache().get(poStatusKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from PoStatusData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                String desc = (String) it.next();
                //Storing values into the TreeList.
                poStatusList.add(desc);
                
            }// closing for loop.
            
            CacheManager.getCache().put(poStatusKey, poStatusList);
            
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
        }// closing if condition.
        
        return poStatusList;// returning the object.
    }//closing the method.
    
    public List getInvoiceStatus(String invoiceStatusKey) throws ServiceLocatorException{
        
        List invoiceStatusTypesList = new ArrayList();//Description
        
        if (CacheManager.getCache().containsKey(invoiceStatusKey)) {
            invoiceStatusTypesList  = (List) CacheManager.getCache().get(invoiceStatusKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from InvoiceStatusData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                String desc = (String) it.next();
                //Storing values into the TreeList.
                invoiceStatusTypesList.add(desc);
                
            }// closing for loop.
            
            CacheManager.getCache().put(invoiceStatusKey, invoiceStatusTypesList);
            
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
        }// closing if condition.
        
        return invoiceStatusTypesList;// returning the object.
    }//closing the method.
    
    
    public List getCrmNote(String crmNoteKey) throws ServiceLocatorException{
        
        List crmNoteTypesList = new ArrayList();//Description
        
        if (CacheManager.getCache().containsKey(crmNoteKey)) {
            crmNoteTypesList  = (List) CacheManager.getCache().get(crmNoteKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from CrmNoteData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                String desc = (String) it.next();
                //Storing values into the TreeList.
                crmNoteTypesList.add(desc);
                
            }// closing for loop.
            
            CacheManager.getCache().put(crmNoteKey, crmNoteTypesList);
            
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
        }// closing if condition.
        
        return crmNoteTypesList;// returning the object.
    }//closing the method.
    
    
    public List getEmpCurrentState(String empCurrentStateKey) throws ServiceLocatorException{
        List empCurrentSateList = new ArrayList();//Description
        if (CacheManager.getCache().containsKey(empCurrentStateKey)) {
            empCurrentSateList  = (List) CacheManager.getCache().get(empCurrentStateKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from EmpCurrentState as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                String desc = (String) it.next();
                //Storing values into the TreeList.
                empCurrentSateList.add(desc);
                
            }// closing for loop.
            
            CacheManager.getCache().put(empCurrentStateKey, empCurrentSateList);
            
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
        }// closing if condition.
        
       // System.out.println("empCurrentSateList"+empCurrentSateList);
        return empCurrentSateList;// returning the object.
    }//closing the method.
    
    
    public List getCrmCampaignContacts(String crmCampaignContactsKey) throws ServiceLocatorException{
        
        List crmCampaignContactsList = new ArrayList();//Description
        
        if (CacheManager.getCache().containsKey(crmCampaignContactsKey)) {
            crmCampaignContactsList  = (List) CacheManager.getCache().get(crmCampaignContactsKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from CrmCampaignContactsData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                String desc = (String) it.next();
                //Storing values into the TreeList.
                crmCampaignContactsList.add(desc);
                
            }// closing for loop.
            
            CacheManager.getCache().put(crmCampaignContactsKey, crmCampaignContactsList);
            
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
        }// closing if condition.
        
        return crmCampaignContactsList;// returning the object.
    }//closing the method.
    
    
    public List getCrmCampaignStatus(String crmCampaignStatusKey) throws ServiceLocatorException{
        
        List crmCampaignStatusList = new ArrayList();//Description
        
        if (CacheManager.getCache().containsKey(crmCampaignStatusKey)) {
            crmCampaignStatusList  = (List) CacheManager.getCache().get(crmCampaignStatusKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from CrmCampaignStatusData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                String desc = (String) it.next();
                //Storing values into the TreeList.
                crmCampaignStatusList.add(desc);
                
            }// closing for loop.
            
            CacheManager.getCache().put(crmCampaignStatusKey, crmCampaignStatusList);
            
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
        }// closing if condition.
        
        return crmCampaignStatusList;// returning the object.
    }//closing the method.
    
    public List getCrmCampaignType(String crmCampaignTypeKey) throws ServiceLocatorException{
        
        List crmCampaignTypeList = new ArrayList();//Description
        
        if (CacheManager.getCache().containsKey(crmCampaignTypeKey)) {
            crmCampaignTypeList  = (List) CacheManager.getCache().get(crmCampaignTypeKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from CrmCampaignTypeData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                String desc = (String) it.next();
                //Storing values into the TreeList.
                crmCampaignTypeList.add(desc);
                
            }// closing for loop.
            
            CacheManager.getCache().put(crmCampaignTypeKey, crmCampaignTypeList);
            
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
        }// closing if condition.
        
        return crmCampaignTypeList;// returning the object.
    }//closing the method.
    
    public List getCrmContactStatus(String crmContactStatusKey) throws ServiceLocatorException{
        
        List crmContactStatusList = new ArrayList();//Description
        
        if (CacheManager.getCache().containsKey(crmContactStatusKey)) {
            crmContactStatusList  = (List) CacheManager.getCache().get(crmContactStatusKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from CrmContactStatusData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                String desc = (String) it.next();
                //Storing values into the TreeList.
                crmContactStatusList.add(desc);
                
            }// closing for loop.
            
            CacheManager.getCache().put(crmContactStatusKey, crmContactStatusList);
            
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
        }// closing if condition.
        
        return crmContactStatusList;// returning the object.
    }//closing the method.
    
    public List getCrmContactType(String crmContactTypeKey) throws ServiceLocatorException{
        
        List crmContactTypeList = new ArrayList();//Description
        
        if (CacheManager.getCache().containsKey(crmContactTypeKey)) {
            crmContactTypeList  = (List) CacheManager.getCache().get(crmContactTypeKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from CrmContactTypeData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                String desc = (String) it.next();
                //Storing values into the TreeList.
                crmContactTypeList.add(desc);
                
            }// closing for loop.
            
            CacheManager.getCache().put(crmContactTypeKey, crmContactTypeList);
            
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
        }// closing if condition.
        
        return crmContactTypeList;// returning the object.
    }//closing the method.
    
    
    
    public List getGreensheetUnits(String greensheetUnitsKey) throws ServiceLocatorException{
        
        List greensheetUnitsList = new ArrayList();//Description
        
        if (CacheManager.getCache().containsKey(greensheetUnitsKey)) {
            greensheetUnitsList  = (List) CacheManager.getCache().get(greensheetUnitsKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from GreensheetUnitsData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                String desc = (String) it.next();
                //Storing values into the TreeList.
                greensheetUnitsList.add(desc);
                
            }// closing for loop.
            
            CacheManager.getCache().put(greensheetUnitsKey, greensheetUnitsList);
            
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
        }// closing if condition.
        
        return greensheetUnitsList;// returning the object.
    }//closing the method.
    
    
    public List getGreensheetVPSales(String greensheetVPSalesKey) throws ServiceLocatorException{
        
        List greensheetVPSalesList = new ArrayList();//Description
        
        if (CacheManager.getCache().containsKey(greensheetVPSalesKey)) {
            greensheetVPSalesList  = (List) CacheManager.getCache().get(greensheetVPSalesKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from GreensheetVPSalesData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                String desc = (String) it.next();
                //Storing values into the TreeList.
                greensheetVPSalesList.add(desc);
                
            }// closing for loop.
            
            CacheManager.getCache().put(greensheetVPSalesKey, greensheetVPSalesList);
            
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
        }// closing if condition.
        
        return greensheetVPSalesList;// returning the object.
    }//closing the method.
    
    
    public List getGreenSheetExpenses(String greenSheetExpensesKey) throws ServiceLocatorException{
        
        List greenSheetExpensesList = new ArrayList();//key-Description
        
        if (CacheManager.getCache().containsKey(greenSheetExpensesKey)) {
            greenSheetExpensesList  = (List) CacheManager.getCache().get(greenSheetExpensesKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from GreenSheetExpensesData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                String desc = (String) it.next();
                //Storing values into the TreeList.
                greenSheetExpensesList.add(desc);
                
            }// closing for loop.
            
            CacheManager.getCache().put(greenSheetExpensesKey, greenSheetExpensesList);
            
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
        }// closing if condition.
        
        return greenSheetExpensesList;// returning the object.
    }//closing the method.
    
    public List getGreensheetCreationStatus(String greensheetCreationStatusKey) throws ServiceLocatorException{
        
        List greensheetCreationStatusList = new ArrayList();//Description
        
        if (CacheManager.getCache().containsKey(greensheetCreationStatusKey)) {
            greensheetCreationStatusList  = (List) CacheManager.getCache().get(greensheetCreationStatusKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from GreensheetCreationStatusData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                String desc = (String) it.next();
                //Storing values into the TreeList.
                greensheetCreationStatusList.add(desc);
                
            }// closing for loop.
            
            CacheManager.getCache().put(greensheetCreationStatusKey, greensheetCreationStatusList);
            
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
        }// closing if condition.
        
        return greensheetCreationStatusList;// returning the object.
    }//closing the method.
    
    
    public List getCrmActivityStatus(String crmActivityStatusKey) throws ServiceLocatorException{
        
        List crmActivityStatusList = new ArrayList();
        
        if (CacheManager.getCache().containsKey(crmActivityStatusKey)) {
            
            crmActivityStatusList  = (List) CacheManager.getCache().get(crmActivityStatusKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from CrmActivityStatusData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                String desc = (String) it.next();
                //Storing values into the TreeList.
                crmActivityStatusList.add(desc);
                
            }// closing for loop.
            
            CacheManager.getCache().put(crmActivityStatusKey, crmActivityStatusList);
            
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
        }// closing if condition.
        
        return crmActivityStatusList;// returning the object.
    }//closing the method.
    
    
    public List getPriority(String priorityKey) throws ServiceLocatorException{
        
        List priorityList = new ArrayList();
        
        if (CacheManager.getCache().containsKey(priorityKey)) {
            
            priorityList  = (List) CacheManager.getCache().get(priorityKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from PriorityData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                String desc = (String) it.next();
                //Storing values into the TreeList.
                priorityList.add(desc);
                
            }// closing for loop.
            
            CacheManager.getCache().put(priorityKey, priorityList);
            
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
        }// closing if condition.
        
        return priorityList;// returning the object.
    }//closing the method.
    
    public List getRegion(String regionKey) throws ServiceLocatorException{
        
        List regionList = new ArrayList();
        
        if (CacheManager.getCache().containsKey(regionKey)) {
            
            regionList  = (List) CacheManager.getCache().get(regionKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from RegionData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                String desc = (String) it.next();
                //Storing values into the TreeList.
                regionList.add(desc);
                
            }// closing for loop.
            
            CacheManager.getCache().put(regionKey, regionList);
            
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
        }// closing if condition.
        
        return regionList;// returning the object.
    }//closing the method.
    
    
    public Map getRegionMap(String regionKey) throws ServiceLocatorException{
        
        Map regionMap = null;
        
        if (CacheManager.getCache().containsKey(regionKey)) {
            
            regionMap  = (Map) CacheManager.getCache().get(regionKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.id,tp.description from RegionData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){                
                Object[] row = (Object[]) it.next();                
                //Storing values into the TreeMap.
                regionMap.put(row[0].toString(),row[1].toString());
                
            }// closing for loop.
                
            CacheManager.getCache().put(regionKey, regionMap);
            
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
        }// closing if condition.
        
        return regionMap;// returning the object.
    }//closing the method.
    
    
    public String getAccountName(int accountId) throws ServiceLocatorException {
        String accountName = null;
        Session session = HibernateServiceLocator.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        
        String SQL_QUERY ="Select tp.name from AccountData as tp where tp.id=:accountId";
        
        Query query=session.createQuery(SQL_QUERY).setInteger("accountId",accountId);
        for(Iterator it=query.iterate();it.hasNext();){
            accountName = (String) it.next();
        }//end of the for loop
        if(accountName == null) accountName="Name is Null";
        try{
            // Closing hibernate session
            session.close();
            session = null;
        }catch(HibernateException he){
            throw new ServiceLocatorException(he);
        }finally{
            if(session!=null){
                try{
                    session.close();
                    session = null;
                }catch(HibernateException he){
                    throw new ServiceLocatorException(he);
                }
            }
        }
        
        
        return accountName;
    }//closing the method
    
    public int getAccountIdByContactId(int inContactId) throws ServiceLocatorException{
        int accountId = 0;
        Object accountIdObject = null;
        
        Session session = HibernateServiceLocator.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        
        String SQL_QUERY ="Select tp.accountId from ContactDataForAccountId as tp where tp.id=:contactId";
        
        Query query=session.createQuery(SQL_QUERY).setInteger("contactId",inContactId);
        for(Iterator it=query.iterate();it.hasNext();){
            accountIdObject = (Object)it.next();
        }//end of the for loop
        if(accountIdObject == null) accountIdObject="0";
        accountId = Integer.parseInt(accountIdObject.toString());
        
        try{
            // Closing hibernate session
            session.close();
            session = null;
        }catch(HibernateException he){
            throw new ServiceLocatorException(he);
        }finally{
            if(session!=null){
                try{
                    session.close();
                    session = null;
                }catch(HibernateException he){
                    throw new ServiceLocatorException(he);
                }
            }
        }
        
        return accountId;
    }
    
    public int getAccountIdByActivityId(int inActivityId) throws ServiceLocatorException{
        int accountId = 0;
        Object accountIdObject = null;
        
        Session session = HibernateServiceLocator.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        
        String SQL_QUERY ="Select tp.accountId from AccountIdForActivityId as tp where tp.id=:activityId";
        
        Query query=session.createQuery(SQL_QUERY).setInteger("activityId",inActivityId);
        for(Iterator it=query.iterate();it.hasNext();){
            accountIdObject = (Object)it.next();
        }//end of the for loop
        if(accountIdObject == null) accountIdObject="0";
        accountId = Integer.parseInt(accountIdObject.toString());
        
        try{
            // Closing hibernate session
            session.close();
            session = null;
        }catch(HibernateException he){
            throw new ServiceLocatorException(he);
        }finally{
            if(session!=null){
                try{
                    session.close();
                    session = null;
                }catch(HibernateException he){
                    throw new ServiceLocatorException(he);
                }
            }
        }
        
        return accountId;
    }
    
    
    public String getContactName(int inContactId) throws ServiceLocatorException {
        String contactName = null;
        Object fName = null;
        Object lName = null;
        Object mName = null;
        Session session = HibernateServiceLocator.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        
        String SQL_QUERY ="Select tp.firstName, tp.lastName,tp.middleName from ContactData as tp where tp.id=:contactId";
        
        Query query=session.createQuery(SQL_QUERY).setInteger("contactId",inContactId);
        for(Iterator it=query.iterate();it.hasNext();){
            Object[] row = (Object[]) it.next();
            fName = (Object)row[0];
            lName =(Object)row[1];
            mName = (Object)row[2];
        }//end of the for loop
        if(fName == null) fName="";
        if(mName == null) mName ="";
        if(lName == null) lName ="";
        contactName = fName.toString()+" "+mName.toString()+"."+lName.toString();
        
        try{
            // Closing hibernate session
            session.close();
            session = null;
        }catch(HibernateException he){
            throw new ServiceLocatorException(he);
        }finally{
            if(session!=null){
                try{
                    session.close();
                    session = null;
                }catch(HibernateException he){
                    throw new ServiceLocatorException(he);
                }
            }
        }
        
        return contactName;
        
    }//closing the method
    
    //START: Project Navigation
    
    public int getAccountIdByProjectId(int inProjectId) throws ServiceLocatorException{
        int accountId = 0;
        Object accountIdObject = null;
        
        Session session = HibernateServiceLocator.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        
        String SQL_QUERY ="Select tp.accountId from ProjectDataForAccountId as tp where tp.id=:projectId";
        
        Query query=session.createQuery(SQL_QUERY).setInteger("projectId",inProjectId);
        for(Iterator it=query.iterate();it.hasNext();){
            accountIdObject = (Object)it.next();
        }//end of the for loop
        if(accountIdObject == null) accountIdObject="0";
        accountId = Integer.parseInt(accountIdObject.toString());
        
        try{
            // Closing hibernate session
            session.close();
            session = null;
        }catch(HibernateException he){
            throw new ServiceLocatorException(he);
        }finally{
            if(session!=null){
                try{
                    session.close();
                    session = null;
                }catch(HibernateException he){
                    throw new ServiceLocatorException(he);
                }
            }
        }
        
        return accountId;
    }
    
    public String getProjectName(int projectId) throws ServiceLocatorException {
        String projectName = null;
        Session session = HibernateServiceLocator.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        
        String SQL_QUERY ="Select tp.name from ProjectName as tp where tp.id=:projectId";
        
        Query query=session.createQuery(SQL_QUERY).setInteger("projectId",projectId);
        for(Iterator it=query.iterate();it.hasNext();){
            projectName = (String) it.next();
        }//end of the for loop
        
        if(projectName == null) projectName="Name is Null";
        try{
            // Closing hibernate session
            session.close();
            session = null;
        }catch(HibernateException he){
            throw new ServiceLocatorException(he);
        }finally{
            if(session!=null){
                try{
                    session.close();
                    session = null;
                }catch(HibernateException he){
                    throw new ServiceLocatorException(he);
                }
            }
        }
        return projectName;
    }//closing the method
    
    public String getSubProjectName(int subProjectId) throws ServiceLocatorException {
        String subProjectName = null;
        Session session = HibernateServiceLocator.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        
        String SQL_QUERY ="Select tp.name from SubProjectName as tp where tp.id=:subProjectId";
        
        Query query=session.createQuery(SQL_QUERY).setInteger("subProjectId",subProjectId);
        for(Iterator it=query.iterate();it.hasNext();){
            subProjectName = (String) it.next();
        }//end of the for loop
        
        if(subProjectName == null) subProjectName="Name is Null";
        try{
            // Closing hibernate session
            session.close();
            session = null;
        }catch(HibernateException he){
            throw new ServiceLocatorException(he);
        }finally{
            if(session!=null){
                try{
                    session.close();
                    session = null;
                }catch(HibernateException he){
                    throw new ServiceLocatorException(he);
                }
            }
        }
        return subProjectName;
    }//closing the method
    
    public String getMapName(int mapId) throws ServiceLocatorException {
        String mapName = null;
        Session session = HibernateServiceLocator.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        
        String SQL_QUERY ="Select tp.name from MapName as tp where tp.id=:mapId";
        
        Query query=session.createQuery(SQL_QUERY).setInteger("mapId",mapId);
        for(Iterator it=query.iterate();it.hasNext();){
            mapName = (String) it.next();
        }//end of the for loop
        
        if(mapName == null) mapName="Name is Null";
        try{
            // Closing hibernate session
            session.close();
            session = null;
        }catch(HibernateException he){
            throw new ServiceLocatorException(he);
        }finally{
            if(session!=null){
                try{
                    session.close();
                    session = null;
                }catch(HibernateException he){
                    throw new ServiceLocatorException(he);
                }
            }
        }
        return mapName;
    }//closing the method
    
    public String getMapIssueType(int issueId) throws ServiceLocatorException {
        String mapIssueType = null;
        Session session = HibernateServiceLocator.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        
        String SQL_QUERY ="Select tp.name from MapIssueType as tp where tp.id=:issueId";
        
        Query query=session.createQuery(SQL_QUERY).setInteger("issueId",issueId);
        for(Iterator it=query.iterate();it.hasNext();){
            mapIssueType = (String) it.next();
        }//end of the for loop
        
        if(mapIssueType == null) mapIssueType="Name is Null";
        try{
            // Closing hibernate session
            session.close();
            session = null;
        }catch(HibernateException he){
            throw new ServiceLocatorException(he);
        }finally{
            if(session!=null){
                try{
                    session.close();
                    session = null;
                }catch(HibernateException he){
                    throw new ServiceLocatorException(he);
                }
            }
        }
        return mapIssueType;
    }//closing the method
    
    //CLOSE: Project Navigation
    
    public int getContactIdByActivityId(int inActivityId) throws ServiceLocatorException{
        int inContactId = 0;
        Object contactIdObject = null;
        
        Session session = HibernateServiceLocator.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        
        String SQL_QUERY ="Select tp.contactId from CrmActivityDataForContactId as tp where tp.id=:activityId";
        
        Query query=session.createQuery(SQL_QUERY).setInteger("activityId",inActivityId);
        for(Iterator it=query.iterate();it.hasNext();){
            contactIdObject = (Object)it.next();
        }//end of the for loop
        if(contactIdObject == null) contactIdObject="0";
        inContactId = Integer.parseInt(contactIdObject.toString());
        
        try{
            // Closing hibernate session
            session.close();
            session = null;
        }catch(HibernateException he){
            throw new ServiceLocatorException(he);
        }finally{
            if(session!=null){
                try{
                    session.close();
                    session = null;
                }catch(HibernateException he){
                    throw new ServiceLocatorException(he);
                }
            }
        }
        
        return inContactId;
    }
    
    
    
 /*public static void main(String args[]){
        try {
         getInstance().getEmpActivities(ApplicationConstants.EMP_ACTIVITY_OPTIONS);
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        }
    }
  */
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getCountryName() {
        return countryName;
    }
    
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
    
    public String getRegionName() {
        return regionName;
    }
    
    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
    
    
    
    /**
     *@ returns List for Countries.
     *@ throws HibernateException.
     */
    public List getStatesList(String statesKey) throws ServiceLocatorException{
        
        List statesList = new ArrayList();
        
        if (CacheManager.getCache().containsKey(statesKey)) {
            statesList  = (List) CacheManager.getCache().get(statesKey);
            
        } else {
            
            //getting hibernate session from the HibernateServiceLocator class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="SELECT tp.description FROM StatesData AS tp ";
            
            Query query=session.createQuery(SQL_STG);
            
            for(Iterator it=query.iterate();it.hasNext();){
                
                String desc = (String)it.next();
                
                //Storing values into the TreeList.
                statesList.add(desc);
                
            }  // closing for loop.
            
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
        }
        return statesList; // returning the object.
    } //closing the method.
    
    public Map getCrmBDM(String BDMKey) throws ServiceLocatorException {
        
        Map crmBDMMap=new TreeMap();//key-Description
        
        if (CacheManager.getCache().containsKey(BDMKey)) {
            crmBDMMap  = (Map) CacheManager.getCache().get(BDMKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            
            //Creating a transaction for the session object.
            //     Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.bdmID,tp.description from CrmBDMData as tp";
            Query query=session.createQuery(SQL_STG);
            
            for(Iterator it=query.iterate();it.hasNext();){
                Object[] row = (Object[]) it.next();
                //Storing values into the TreeMap.
                crmBDMMap.put(row[0].toString(),row[1].toString());
                
            } // closing for loop.
            
            CacheManager.getCache().put(BDMKey,crmBDMMap);
            
            // Closing hibernate session
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally {
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
            
        }// closing if condition.
        
        return crmBDMMap;// returning the object.
    }//closing the method.
    
    public Map getGreenSheetUnits(String greenSheetUntiskey) throws ServiceLocatorException{
        
        Map greenSheetUntisMap = new TreeMap();
        
        if (CacheManager.getCache().containsKey(greenSheetUntiskey)) {
            greenSheetUntisMap  = (Map) CacheManager.getCache().get(greenSheetUntiskey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.bdmID,tp.description from CrmGreenSheetUnitsData as tp";
            Query query=session.createQuery(SQL_STG);
            
            for(Iterator it=query.iterate();it.hasNext();){
                Object[] row = (Object[]) it.next();
                //Storing values into the TreeMap.
                greenSheetUntisMap.put(row[0].toString(),row[1].toString());
                
            }// closing for loop.
            
            CacheManager.getCache().put(greenSheetUntiskey,greenSheetUntisMap);
            
            // Closing hibernate session
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
            
        }// closing if condition.
        
        return greenSheetUntisMap;// returning the object.
    }//closing the method.
    
    // for greensheet expences
    
    public List getGreenSheetExpences(String greenSheetExpenceskey) throws ServiceLocatorException{
        
        List greenSheetExpencesList = new ArrayList();//Description
        
        if (CacheManager.getCache().containsKey(greenSheetExpenceskey)) {
            greenSheetExpencesList  = (List) CacheManager.getCache().get(greenSheetExpenceskey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from CrmGreenSheetExpencesData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                String desc = (String)it.next();
                greenSheetExpencesList.add(desc);
                
            }// closing for loop.
            
            CacheManager.getCache().put(greenSheetExpenceskey,greenSheetExpencesList);
            
            // Closing hibernate session
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
        }// closing if condition.
        
        return greenSheetExpencesList;// returning the object.
    }//closing the method.
    
    // for greensheet client billing rate
    
    public Map getGreenSheetClientBillingRate(String greenSheetCurrencykey) throws ServiceLocatorException{
        
        Map greenSheetCurrencyMap = new TreeMap();//key-Description
        
        if (CacheManager.getCache().containsKey(greenSheetCurrencykey)) {
            greenSheetCurrencyMap  = (Map) CacheManager.getCache().get(greenSheetCurrencykey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.bdmID,tp.description from CrmGreenSheetCurrencyData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                Object[] row = (Object[]) it.next();
                //Storing values into the TreeMap.
                greenSheetCurrencyMap.put(row[0].toString(),row[1].toString());
                
            }// closing for loop.
            
            CacheManager.getCache().put(greenSheetCurrencykey,greenSheetCurrencyMap);
            session.clear();
            // Closing hibernate session
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
        }// closing if condition.
        
        return greenSheetCurrencyMap;// returning the object.
    }//closing the method.
    
    // for greensheet status type
    
    public List getGreenSheetStatus(String greenSheetStatuskey) throws ServiceLocatorException{
        
        List greenSheetStatusMap = new ArrayList();//Description
        
        if (CacheManager.getCache().containsKey(greenSheetStatuskey)) {
            greenSheetStatusMap  = (List) CacheManager.getCache().get(greenSheetStatuskey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from CrmGreenSheetStatusData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                String desc = (String)it.next();
                //Storing values into the TreeMap.
                greenSheetStatusMap.add(desc);
                
            }// closing for loop.
            
            CacheManager.getCache().put(greenSheetStatuskey,greenSheetStatusMap);
            session.clear();
            // Closing hibernate session
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
        }// closing if condition.
        
        return greenSheetStatusMap;// returning the object.
    }//closing the method.
    
    // for greensheet po status type
    
    public List getGreenSheetPOStatus(String greenSheetPOStatuskey) throws ServiceLocatorException{
        
        List greenSheetPOStautsMap = new ArrayList();//Description
        
        if (CacheManager.getCache().containsKey(greenSheetPOStatuskey)) {
            greenSheetPOStautsMap  = (List) CacheManager.getCache().get(greenSheetPOStatuskey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from CrmGreenSheetPOStatusData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                String desc = (String)it.next();
                //Storing values into the TreeMap.
                greenSheetPOStautsMap.add(desc);
                
            }// closing for loop.
            
            CacheManager.getCache().put(greenSheetPOStatuskey,greenSheetPOStautsMap);
            session.clear();
            // Closing hibernate session
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
        }// closing if condition.
        
        return greenSheetPOStautsMap;// returning the object.
    }//closing the method.
    
    // for greensheet po status type
    
    public Map getGreenSheetScopeOfWorkList(String greenSheetScopeOfWorkkey) throws ServiceLocatorException{
        
        Map greenSheetScopeOfWork = new TreeMap();//key-Description
        
        if (CacheManager.getCache().containsKey(greenSheetScopeOfWorkkey)) {
            greenSheetScopeOfWork  = (Map) CacheManager.getCache().get(greenSheetScopeOfWorkkey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.bdmID,tp.description from CrmGreenSheetScopeOfWorkData as tp";
            Query query=session.createQuery(SQL_STG);
            
            for(Iterator it=query.iterate();it.hasNext();){
                Object[] row = (Object[]) it.next();
                //Storing values into the TreeMap.
                greenSheetScopeOfWork.put(row[0].toString(),row[1].toString());
                
            }// closing for loop.
            
            CacheManager.getCache().put(greenSheetScopeOfWorkkey,greenSheetScopeOfWork);
            
            session.clear();
            // Closing hibernate session
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
        }// closing if condition.
        
        return greenSheetScopeOfWork;// returning the object.
    }//closing the method.
    
    // for greensheet VP status type
    
    public List getGreenSheetVPSalesList(String greenSheetVPSaleskey) throws ServiceLocatorException{
        
        List greenSheetVPSalesList = new ArrayList();//Description
        
        if (CacheManager.getCache().containsKey(greenSheetVPSaleskey)) {
            greenSheetVPSalesList  = (List) CacheManager.getCache().get(greenSheetVPSaleskey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from CrmGreenSheetVPSalesData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                String desc = (String)it.next();
                //Storing values into the TreeMap.
                greenSheetVPSalesList.add(desc);
            }// closing for loop.
            
            CacheManager.getCache().put(greenSheetVPSaleskey,greenSheetVPSalesList);
            
            session.clear();
            // Closing hibernate session
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
        }// closing if condition.
        
        return greenSheetVPSalesList;// returning the object.
    }//closing the method.
    
    // for greensheet paymentterms
    
    public Map getGreenSheetPaymentTerms(String greenSheetPaymentTermskey) throws ServiceLocatorException{
        
        Map greenSheetPaymentTermsMap =new TreeMap();//key-Description
        
        if (CacheManager.getCache().containsKey(greenSheetPaymentTermskey)) {
            greenSheetPaymentTermsMap  = (Map) CacheManager.getCache().get(greenSheetPaymentTermskey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.bdmID,tp.description from CrmGreenSheetPaymentTermsData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                Object[] row = (Object[]) it.next();
                //Storing values into the TreeMap.
                greenSheetPaymentTermsMap.put(row[0].toString(),row[1].toString());
                
            }// closing for loop.
            
            session.clear();
            CacheManager.getCache().put(greenSheetPaymentTermskey,greenSheetPaymentTermsMap);
            
            // Closing hibernate session
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null) {
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
        }// closing if condition.
        
        return greenSheetPaymentTermsMap;// returning the object.
    }//closing the method.
    
    
    public Map getModuleNames(String moduleKey) throws
            ServiceLocatorException{
        Map  moduleMap = new TreeMap();// Key-Description
        if (CacheManager.getCache().containsKey(moduleKey)) {
            
            moduleMap = (Map) CacheManager.getCache().get(moduleKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session =  HibernateServiceLocator.getInstance().getSession();
            
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            
            String SQL_STG = "select tp.id,tp.description from ModuleData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                
                Object[] row = (Object[]) it.next();
                
                //Storing values into the TreeMap.
                moduleMap.put(row[0].toString(),row[1].toString());
                
            }// closing for loop.
            
            //Storing the rolesMap object in to the cache as singleton object.
            CacheManager.getCache().put(moduleKey, moduleMap);
            
            // Closing hibernate session
            try{
                // Closing hibernate session
                session.close();
                session=null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
        }// closing if condition.
        
        return moduleMap; // returning the object.
    }//closing the method
    
    
    
    public boolean isSessionAlive(HttpSession httpSession){
        boolean isSession = false;
        if(httpSession.getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            isSession = true;
        }
        return isSession;
    }
    
    public String getMiddleName() {
        return middleName;
    }
    
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    
    public int getBdmID() {
        return bdmID;
    }
    
    public void setBdmID(int bdmID) {
        this.bdmID = bdmID;
    }
    
    public int getAccountId() {
        return accountId;
    }
    
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
    
    public int getContactId() {
        return contactId;
    }
    
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
    
    
    //Employee Issues
    
    public List getIssueCategories(String issueKey) throws
            ServiceLocatorException{
        List issueCategoryList = new ArrayList();
        if (CacheManager.getCache().containsKey(issueKey)) {
            
            issueCategoryList = (List) CacheManager.getCache().get(issueKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session =  HibernateServiceLocator.getInstance().getSession();
            
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            
            String SQL_STG = "select tp.description from IssuesCategoryData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                
                String row = (String) it.next();
                
                //Storing values into the TreeMap.
                issueCategoryList.add(row);
                
            }// closing for loop.
            
            
            
            //Storing the IssueCategory object in to the cache as singleton object.
            CacheManager.getCache().put(issueKey, issueCategoryList);
            
            // Closing hibernate session
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
        }// closing if condition.
        
        return issueCategoryList; // returning the object.
    }//closing the method
    
    //ISSUES:End
    
    public List getIssueStatus(String issueStatusKey) throws
            ServiceLocatorException{
        
        List issueStatusList = new ArrayList();
        if (CacheManager.getCache().containsKey(issueStatusKey)) {
            
            issueStatusList = (List) CacheManager.getCache().get(issueStatusKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session =  HibernateServiceLocator.getInstance().getSession();
            
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            
            String SQL_STG = "select tp.description from IssueStatusData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                
                String row = (String) it.next();
                
                //Storing values into the ArrayList.
                issueStatusList.add(row);
                
            }// closing for loop.
            
            //Storing the issueStatusMap object in to the cache as singleton object.
            CacheManager.getCache().put(issueStatusKey, issueStatusList);
            
            // Closing hibernate session
            try{
                // Closing hibernate session
                session.close();
                session = null;
                
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
        }// closing if condition.
        
        return issueStatusList; // returning the object.
    }//closing the method
    
    //ISSUESSTATUS:End
    
    
    
    public List getEmpReportsType(String employeReportsKey) throws ServiceLocatorException{
        List empreportsTypeList = new ArrayList();
        if (CacheManager.getCache().containsKey(employeReportsKey)) {
            empreportsTypeList  = (List) CacheManager.getCache().get(employeReportsKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from LKEmpRepType as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                String desc = (String) it.next();
                //Storing values into the TreeList.
                empreportsTypeList.add(desc);
                
            }// closing for loop.
            
            CacheManager.getCache().put(employeReportsKey, empreportsTypeList);
            
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
        }// closing if condition.
        
        return empreportsTypeList;// returning the object.
    }//closing the method.
    /*Immigration */
    public List getImmigrationStatus(String immigrationStatusKey) throws ServiceLocatorException{
        
        List immigrationStatusTypesList = new ArrayList();//Description
        
        if (CacheManager.getCache().containsKey(immigrationStatusKey)) {
            immigrationStatusTypesList  = (List) CacheManager.getCache().get(immigrationStatusKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from ImmigrationData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                String desc = (String) it.next();
                //Storing values into the TreeList.
                immigrationStatusTypesList.add(desc);
                
            }// closing for loop.
            
            CacheManager.getCache().put(immigrationStatusKey, immigrationStatusTypesList);
            
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
        }// closing if condition.
        
        return immigrationStatusTypesList;// returning the object.
    }//closing the method.
    
    // add by rajani kanth.
    
    public List getHealthInsuranceType(String empHealthInsuranceKey) throws ServiceLocatorException{
        
        List empHealthInsuranceTypesList = new ArrayList();//Description
        
        if (CacheManager.getCache().containsKey(empHealthInsuranceKey)) {
            empHealthInsuranceTypesList  = (List) CacheManager.getCache().get(empHealthInsuranceKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from EmpHealthInsuranceData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                String desc = (String) it.next();
                //Storing values into the TreeList.
                empHealthInsuranceTypesList.add(desc);
                
            }// closing for loop.
            
            CacheManager.getCache().put(empHealthInsuranceKey, empHealthInsuranceTypesList);
            
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
            
        }// closing if condition.
        
        return empHealthInsuranceTypesList;// returning the object.
    }//closing the method.
    
    
    //Project Related Module
    public String getIssueName(int issueId) throws ServiceLocatorException {
        String issuesName = null;
        Session session = HibernateServiceLocator.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        
        String SQL_QUERY ="Select tp.name from IssueData as tp where tp.id=:issueId";
        
        Query query=session.createQuery(SQL_QUERY).setInteger("issueId",issueId);
        for(Iterator it=query.iterate();it.hasNext();){
            issuesName = (String) it.next();
        }//end of the for loop
        if(issuesName == null) issuesName="Name is Null";
        try{
            // Closing hibernate session
            session.close();
            session = null;
        }catch(HibernateException he){
            he.printStackTrace();
        }finally{
            if(session!=null){
                try{
                    session.close();
                    session = null;
                }catch(HibernateException he){
                    he.printStackTrace();
                }
            }
        }
        return issuesName;
    }//closing the method
    
       public List getmarsProjects(String projectKey) throws ServiceLocatorException{
         List project = new ArrayList();
        if (CacheManager.getCache().containsKey(projectKey)) {
             project   = (List) CacheManager.getCache().get(projectKey); //returning sigleton object.
        } else {
            
            //getting hibernate session from the HibernateServiceLocator class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from ProjectData as tp";
            Query query=session.createQuery(SQL_STG);
            
            for(Iterator it=query.iterate();it.hasNext();){
                
                String desc = (String)it.next();
                
                //Storing values into the TreeList.
                project.add(desc);
                
            }  // closing for loop.
            
            
            CacheManager.getCache().put(projectKey, project);
            
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                he.printStackTrace();
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        he.printStackTrace();
                    }
                }
            }
            
        }// closing if condition.
        
        return project; // returning the object.
    } //closing the method.
    
    public List getSubProjectsMap(String SubProjectKey) throws ServiceLocatorException{
        List subProjectMap = new ArrayList();
        if (CacheManager.getCache().containsKey(SubProjectKey)) {
            subProjectMap = (List) CacheManager.getCache().get(SubProjectKey); //returning sigleton object.
        } else {
            
            //getting hibernate session from the HibernateServiceLocator class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from SubProjectData as tp";
            Query query=session.createQuery(SQL_STG);
            
            for(Iterator it=query.iterate();it.hasNext();){
                
                String desc = (String)it.next();
                
                //Storing values into the TreeList.
                subProjectMap.add(desc);
                
            }  // closing for loop.
            
            
            CacheManager.getCache().put(SubProjectKey, subProjectMap);
            
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                he.printStackTrace();
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        he.printStackTrace();
                    }
                }
            }
            
        }// closing if condition.
        
        return subProjectMap; // returning the object.
    } //closing the method.
    
      public List getToolsMap(String toolKey) throws ServiceLocatorException{
           List toolListMap = new ArrayList();
        if (CacheManager.getCache().containsKey(toolKey)) {
            toolListMap  = (List) CacheManager.getCache().get(toolKey); //returning sigleton object.
        } else {
            
            //getting hibernate session from the HibernateServiceLocator class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from ToolData as tp";
            Query query=session.createQuery(SQL_STG);
            
            for(Iterator it=query.iterate();it.hasNext();){
                
                String desc = (String)it.next();
                
                //Storing values into the TreeList.
                toolListMap.add(desc);
                
            }  // closing for loop.
            
            
            CacheManager.getCache().put(toolKey, toolListMap);
            
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                he.printStackTrace();
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        he.printStackTrace();
                    }
                }
            }
            
        }// closing if condition.
        
        return toolListMap ; // returning the object.
    } //closing the method.
   
      public List getIssueNameMap(String issueKey) throws ServiceLocatorException{
       List issueMap = new ArrayList();
        if (CacheManager.getCache().containsKey(issueKey)) {
            issueMap = (List) CacheManager.getCache().get(issueKey); //returning sigleton object.
        } else {
            
            //getting hibernate session from the HibernateServiceLocator class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from IssueNameData as tp";
            Query query=session.createQuery(SQL_STG);
            
            for(Iterator it=query.iterate();it.hasNext();){
                
                String desc = (String)it.next();
                
                //Storing values into the TreeList.
                issueMap.add(desc);
                
            }  // closing for loop.
            
            
            CacheManager.getCache().put(issueKey, issueMap);
            
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                he.printStackTrace();
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        he.printStackTrace();
                    }
                }
            }
            
        }// closing if condition.
        
        return issueMap; // returning the object.
    } //closing the method.  
    
        public List getEmployeesMap(String employeeKey) throws ServiceLocatorException{
        List empMap = new ArrayList();
        if (CacheManager.getCache().containsKey(employeeKey)) {
            empMap = (List) CacheManager.getCache().get(employeeKey); //returning sigleton object.
        } else {
            
            //getting hibernate session from the HibernateServiceLocator class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            
            //Genarating a quary for retrieving the data from the database.
            String SQL_STG="select tp.description from EmployData as tp";
            Query query=session.createQuery(SQL_STG);
            
            for(Iterator it=query.iterate();it.hasNext();){
                
                String desc = (String)it.next();
                
                //Storing values into the TreeList.
                empMap.add(desc);
                
            }  // closing for loop.
            
            
            CacheManager.getCache().put(employeeKey,empMap);
            
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                he.printStackTrace();
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        he.printStackTrace();
                    }
                }
            }
            
        }// closing if condition.
        
        return empMap; // returning the object.
    } //closing the method.   
        
     public int getAccountIdByMapId(int inProjectId) throws ServiceLocatorException{
        int accountId = 0;
        Object accountIdObject = null;
        
        Session session = HibernateServiceLocator.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        
        String SQL_QUERY ="Select tp.accountId from MapDataForAccountId as tp where tp.id=:inProjectId";
        
        Query query=session.createQuery(SQL_QUERY).setInteger("inProjectId",inProjectId);
        for(Iterator it=query.iterate();it.hasNext();){
            accountIdObject = (Object)it.next();
        }//end of the for loop
        if(accountIdObject == null) accountIdObject="0";
        accountId = Integer.parseInt(accountIdObject.toString());
        
        try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                he.printStackTrace();
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        he.printStackTrace();
                    }
                }
            }
        
        return accountId;
    }    
     
    public int getAccountIdByIssueId(int inIssueId) throws ServiceLocatorException{
        int accountId = 0;
        Object accountIdObject = null;
        
        Session session = HibernateServiceLocator.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        
        String SQL_QUERY ="Select tp.accountId from IssueDataForAccountId as tp where tp.id=:inIssueId";
        
        Query query=session.createQuery(SQL_QUERY).setInteger("inIssueId",inIssueId);
        for(Iterator it=query.iterate();it.hasNext();){
            accountIdObject = (Object)it.next();
        }//end of the for loop
        if(accountIdObject == null) accountIdObject="0";
        accountId = Integer.parseInt(accountIdObject.toString());
        
        try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                he.printStackTrace();
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        he.printStackTrace();
                    }
                }
            }
        
        return accountId;
    }
 public List getCrmActivityTypesAll(String crmAllActivityTypeKey) throws ServiceLocatorException{
        //  System.out.println("edit");
        List crmActivityTypesList = new ArrayList();//Description
        
        if (CacheManager.getCache().containsKey(crmAllActivityTypeKey)) {
            crmActivityTypesList  = (List) CacheManager.getCache().get(crmAllActivityTypeKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            //String SQL_STG="select tp.description from CrmActivityData as tp";
            //newly added on 03282013
            String SQL_STG="select tp.description from CrmActivityData as tp";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                String desc = (String) it.next();
                //Storing values into the TreeList.
                crmActivityTypesList.add(desc);
               
                
            }// closing for loop.
            //Sorting Elements
           //  System.out.println("crmActivityTypesList"+crmActivityTypesList.size());
            Collections.sort(crmActivityTypesList);
            CacheManager.getCache().put(crmAllActivityTypeKey, crmActivityTypesList);
            
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
        }// closing if condition.
        
        return crmActivityTypesList;// returning the object.
    }//closing the method.


    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
       //newly added on 03282013
     public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}//closing of the class

