/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  October 9, 2007, 3:11 PM
 *
 * Author  : MrutyumjayaRao Chennu<mchennu@miraclesoft.com>
 *
 * File    : AttachmentServiceImpl.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.crm.attachments;

import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.HibernateServiceLocator;
import com.mss.mirage.util.Properties;
import com.mss.mirage.util.ServiceLocatorException;
import java.util.Date;
import java.sql.Timestamp;
import java.util.Iterator;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author MrutyumjayaRao Chennu<mchennu@miraclesoft.com>
 *
 * This Class.... ENTER THE DESCRIPTION HERE
 */
public class AttachmentServiceImpl implements AttachmentService {
    
    
    /** Creates a new instance of AttachmentServiceImpl */
    public AttachmentServiceImpl() {
    }
    
    public boolean insertAttachment(AttachmentAction attachmentPojo) throws ServiceLocatorException {
        boolean isInserted = false;
        try {
            
            Session session = HibernateServiceLocator.getInstance().getSession();
            Transaction transaction = session.beginTransaction();
            transaction.begin();
            session.save(attachmentPojo);
            session.flush();
            transaction.commit();
            isInserted = true;
            
            session.close();
            
        } catch (Exception ex) {
            
            throw new ServiceLocatorException(ex);
        }
        return isInserted;
        
    }
    
    public String generatePath(String contextPath, String objectType) throws ServiceLocatorException {
        
        Date date = new Date();
        String monthName = null;
        String weekName = null;
        
        /*The path which is created in the drive and used as a home directroy*/
        String generatedPath = contextPath;
        
        
        if(date.getMonth()==0) monthName="January";
        else if(date.getMonth()==1) monthName="February";
        else if(date.getMonth()==2) monthName="March";
        else if(date.getMonth()==3) monthName="April";
        else if(date.getMonth()==4) monthName="May";
        else if(date.getMonth()==5) monthName="June";
        else if(date.getMonth()==6) monthName="July";
        else if(date.getMonth()==7) monthName="August";
        else if(date.getMonth()==8) monthName="September";
        else if(date.getMonth()==9) monthName="October";
        else if(date.getMonth()==10) monthName="November";
        else if(date.getMonth()==11) monthName="December";
        short week = (short)(Math.round(date.getDate()/7));
        
        if(week == 0) weekName="WeekFirst";
        else if(week == 1) weekName="WeekSecond";
        else if(week == 2) weekName="WeekThird";
        else if(week == 3) weekName="WeekFourth";
        else if(week == 4) weekName="WeekFifth";
        
        /*getrequestType is used to create a directory of the object type specified in the jsp page*/
//        createPath=new File(createPath.getAbsolutePath()+"//MirageV2//"+getRequestType()+"//"+String.valueOf(date.getYear()+1900)+"//"+monthName+"//"+weekName);
        generatedPath = generatedPath+"/MirageV2/"+objectType+"/"
                +String.valueOf(date.getYear()+1900)
                +"/"+monthName+"/"+weekName;
        return generatedPath;
    }
    
    public boolean deleteAttachment(AttachmentAction attachmentPojo) throws ServiceLocatorException {
        boolean isDeleted = false;
        try {
            
            Session session = HibernateServiceLocator.getInstance().getSession();
            Transaction transaction = session.beginTransaction();
            transaction.begin();
            session.delete(attachmentPojo);
            session.flush();
            transaction.commit();
            isDeleted = true;
            session.close();
            
        } catch (Exception ex) {
            
            throw new ServiceLocatorException(ex);
        }
        return isDeleted;
    }
    
    public String getAttachmentLocation(int attachmentId) throws ServiceLocatorException {
        
        Session session = HibernateServiceLocator.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String attachmentLocation = null;
        String SQL_QUERY ="Select h.attachmentLocation from AttachmentAction as h where h.id=:attachmentId";
        
        Query query = session.createQuery(SQL_QUERY).setInteger("attachmentId",attachmentId);
        for(Iterator it=query.iterate();it.hasNext();){
            attachmentLocation = (String) it.next();
        }//end of the for loop
        
        if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        he.printStackTrace();
                    }
                }
        
        return attachmentLocation;
    }
    
}
