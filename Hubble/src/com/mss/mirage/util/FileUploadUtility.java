/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.util;

import java.text.SimpleDateFormat;

/**
 *
 * @author miracle
 */
public class FileUploadUtility {

    private static FileUploadUtility _instance;

    private FileUploadUtility() {
    }

    /**
     * @return An instance of the DataServiceLocator class
     * @throws ServiceLocatorException
     */
    public static FileUploadUtility getInstance() throws ServiceLocatorException {
        try {
            if (_instance == null) {
                _instance = new FileUploadUtility();
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
        }
        return _instance;
    }

    public String filePathGeneration(String createPath) {

        //System.out.println("filePathGeneration.........createPath..." + createPath );
        java.util.Date date = new java.util.Date();
           String weekName = null;
        /*The month is generated from her*/
        String monthName = "";
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
        createPath =createPath  + "//" + String.valueOf(date.getYear() + 1900) + "//" + monthName + "//" + weekName;
        /*This creates a directory forcefully if the directory does not exsist*/
        //   System.err.println("absolute path...."+createPath.getAbsolutePath());
        //   System.err.println("createPath...."+createPath);
      
        return createPath;

 }
    
    
    
    
      public String fileNameGeneration(String fileName) {

         
         String datetime = new SimpleDateFormat("ddMMMyyyy_hh_mm_ss_SSSa").format(new java.util.Date());
        String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        String name = fileName.substring(0, fileName.lastIndexOf("."));
        
       //   System.out.println("output===="+fileType+"name========"+name);
        
      //  System.out.println("datetime..." + datetime);
      
        String theFileName =  name + "_" + datetime + fileType;

        return theFileName;

 }
    
    
    
    
    
    
    //END-Authorization Checking
    
    
}//Close Session Checking