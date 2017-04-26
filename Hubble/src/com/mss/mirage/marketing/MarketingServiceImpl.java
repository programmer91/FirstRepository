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
 * File    : ScreenServiceImpl.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
package com.mss.mirage.marketing;

import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.MailManager;
import com.mss.mirage.util.Properties;
import com.mss.mirage.util.RestRepository;
import com.mss.mirage.util.ServiceLocator;
import com.mss.mirage.util.ServiceLocatorException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.taglibs.standard.lang.jpath.adapter.Convert;
import org.json.JSONObject;

/**
 *
 * @author Rajanikanth Teppala<rteppala@miraclesoft.com>
 */
public class MarketingServiceImpl implements MarketingService {

    /**
     * Creates a new instance of MarketingServiceImpl
     */
    public MarketingServiceImpl() {
    }

    /**
     * The getScreenActivityVTO method is used to set the variables using the MarketingAction class.
     * @param screenAction SMarketingActionReference
     * @return The return type of this method isMarketingVTOO.
     * @throws ServiceLocatorException If a required Account value cannot be added in the database.
     */
    public MarketingVTO getScreenActivityVTO(MarketingAction screenAction) throws ServiceLocatorException {
        MarketingVTO screenVTO = new MarketingVTO();

        screenVTO.setAccountId(screenAction.getAccountActivId());
        screenVTO.setAccName(screenAction.getAccName());
        screenVTO.setContactName(screenAction.getContactName());
        screenVTO.setContactsId(screenAction.getContactActivId());
        screenVTO.setGetAccountId(screenAction.getAccountActivId());
        screenVTO.setContactActivId(screenAction.getContactActivId());
        screenVTO.setAccountActivId(screenAction.getAccountActivId());
        screenVTO.setActivityId(screenAction.getActivityId());
        return screenVTO;
    }

    /**
     * The getActivity method is  used to edit the Complete Activity Details.
     * Activity Details are edited basing on the Account and Contact selected on the screen from the first two divs.
     * @return The return type of this method is ScreenVTO.
     * @param activityId 
     * @throws com.mss.mirage.util.ServiceLocatorException 
     */
    public MarketingVTO getActivity(int activityId) throws ServiceLocatorException {
        MarketingVTO screenVTO = new MarketingVTO();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        DataSourceDataProvider dataSourceDataProvider;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            dataSourceDataProvider = DataSourceDataProvider.getInstance();
            preparedStatement = connection.prepareStatement("SELECT * FROM tblCrmActivity WHERE Id=?");
            preparedStatement.setInt(1, activityId);
            resultSet = preparedStatement.executeQuery();
            screenVTO.setActivityId(activityId);
            while (resultSet.next()) {
                screenVTO.setAccountActivId(resultSet.getInt("AccountId"));
                screenVTO.setContactActivId(resultSet.getInt("ContactId"));
                screenVTO.setActivityType(resultSet.getString("ActivityType"));
                screenVTO.setCampaignId(resultSet.getInt("CampaignId"));
                screenVTO.setAssignedToId(resultSet.getString("AssignedToId"));
                screenVTO.setPriority(resultSet.getString("Priority"));
                screenVTO.setDueDate(resultSet.getTimestamp("DateDue"));
                screenVTO.setStatus(resultSet.getString("Status"));
                screenVTO.setAlarm(resultSet.getBoolean("Alarm"));
                screenVTO.setDescription(resultSet.getString("Description"));
                screenVTO.setComments(resultSet.getString("Comments"));
                screenVTO.setAssignedById(resultSet.getString("AssignedById"));
                screenVTO.setCreatedById(resultSet.getString("CreatedById"));
                screenVTO.setModifiedById(resultSet.getString("ModifiedById"));
                screenVTO.setCreatedDate(resultSet.getTimestamp("CreatedDate"));
                screenVTO.setModifiedDate(resultSet.getTimestamp("ModifiedDate"));
                screenVTO.setAccountId(resultSet.getInt("AccountId"));
                screenVTO.setGetAccountId(resultSet.getInt("AccountId"));
                screenVTO.setContactsId(resultSet.getInt("ContactId"));
                screenVTO.setAccName(dataSourceDataProvider.getAccountName(resultSet.getInt("AccountId")));
                screenVTO.setContactName(dataSourceDataProvider.getContactName(resultSet.getInt("ContactId")));
            }
        } catch (SQLException se) {
            throw new ServiceLocatorException(se);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }
        return screenVTO;
    }

    public List doJobSearch(MarketingAction marketingAction) throws ServiceLocatorException {
        List jobsList = null;
        List mainList = null;
        String createdDateFrom;
        String createdDateTo;
        String jobId;
        String title;
        String status;


        try {

            JSONObject jb = new JSONObject();

            if (marketingAction.getCreatedDateFrom() != null && !"".equals(marketingAction.getCreatedDateFrom())) {
                jb.put("createdDateFrom", marketingAction.getCreatedDateFrom());
            } else {
                jb.put("createdDateFrom", "none");
            }
            if (marketingAction.getCreatedDateTo() != null && !"".equals(marketingAction.getCreatedDateTo())) {
                jb.put("createdDateTo", marketingAction.getCreatedDateTo());
            } else {
                jb.put("createdDateTo", "none");
            }
            if (marketingAction.getJobId() != null && !"".equals(marketingAction.getJobId())) {
                jb.put("jobId", marketingAction.getJobId());
            } else {
                jb.put("jobId", "none");
            }
            if (marketingAction.getTitle() != null && !"".equals(marketingAction.getTitle())) {
                jb.put("title", marketingAction.getTitle());
            } else {
                jb.put("title", "none");
            }
            if (marketingAction.getStatus() != null && !"".equals(marketingAction.getStatus())) {
                jb.put("status", marketingAction.getStatus());
            } else {
                jb.put("status", "none");
            }

            // URL url = new URL("http://localhost:8080/CrunchifyTutorials/api/crunchifyService");
            //URL url = new URL("http://172.17.11.251:8080/WebRoot/resources/doJobPost/search");
            //  URL url = new URL("http://172.17.14.226:8080/WebRoot/resources/doJobPost/search");
            //  URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doJobPost/search");
            String serviceUrl = RestRepository.getInstance().getSrviceUrl("jobsearch");
            URL url = new URL(serviceUrl);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write(jb.toString());
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String s = null;
            String data = "";
            while ((s = in.readLine()) != null) {
                //     System.out.println(s);
                data = data + s;
            }



            //  System.out.println("Data-->"+data);
            JSONObject jObject = new JSONObject(data);

            mainList = new ArrayList();
            for (int i = 0; i < jObject.length(); i++) {
                JSONObject subJson = jObject.getJSONObject(String.valueOf(i));
                Map subMap = new TreeMap();

                Iterator<?> subkeys = subJson.keys();
                while (subkeys.hasNext()) {

                    String subkey = (String) subkeys.next();
                    String value = (String) subJson.get(subkey);
                    // subList.add(value);
                    subMap.put(subkey, value);
                }




                //////////////////----------------
        /*         
                Iterator<?> keys = jObject.keys();
                
                List subList ;
                while( keys.hasNext() ){
                String key = (String)keys.next();
                Map subMap = new TreeMap();
                // System.out.println("Sub map-->"+key);
                JSONObject subJson = jObject.getJSONObject(key);
                subList = new ArrayList();
                Iterator<?> subkeys = subJson.keys();
                while( subkeys.hasNext() ){
                String subkey = (String)subkeys.next();
                String value = (String)subJson.get(subkey); 
                // subList.add(value);
                subMap.put(subkey, value);
                }
                 */
                mainList.add(subMap);



                // map.put(key, mailJson);

            }
            //  System.out.println("\nREST Service Invoked Successfully..");
            in.close();
        } catch (Exception e) {
            System.out.println("\nError while calling REST Service");
            System.out.println(e);
        }
        return mainList;
    }

    public List getApplicantsList(MarketingAction marketingAction) throws ServiceLocatorException {
        List jobsList = null;
        List mainList = null;
        String createdDateFrom;
        String createdDateTo;
        String jobId;
        String title;
        String status;
        Map subMap = null;

        try {

            JSONObject jb = new JSONObject();
            if (marketingAction.getCreatedDateFrom() != null && !"".equals(marketingAction.getCreatedDateFrom())) {
                jb.put("createdDateFrom", marketingAction.getCreatedDateFrom());
            } else {
                jb.put("createdDateFrom", "none");
            }
            if (marketingAction.getCreatedDateTo() != null && !"".equals(marketingAction.getCreatedDateTo())) {
                jb.put("createdDateTo", marketingAction.getCreatedDateTo());
            } else {
                jb.put("createdDateTo", "none");
            }
            if (marketingAction.getName() != null && !"".equals(marketingAction.getName())) {
                jb.put("name", marketingAction.getName());
            } else {
                jb.put("name", "none");
            }
            if (marketingAction.getJobId() != null && !"".equals(marketingAction.getJobId())) {
                jb.put("jobId", marketingAction.getJobId());
            } else {
                jb.put("jobId", "none");
            }
            if (marketingAction.getEmail() != null && !"".equals(marketingAction.getEmail())) {
                jb.put("email", marketingAction.getEmail());
            } else {
                jb.put("email", "none");
            }

            // URL url = new URL("http://localhost:8080/CrunchifyTutorials/api/crunchifyService");
            //URL url = new URL("http://172.17.11.251:8080/WebRoot/resources/doJobPost/search");
            //URL url = new URL("http://172.17.14.226:8080/WebRoot/resources/doJobPost/appliedList");
            // URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doJobPost/appliedList");
            String serviceUrl = RestRepository.getInstance().getSrviceUrl("appliedList");
            URL url = new URL(serviceUrl);



            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write(jb.toString());
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String s = null;
            String data = "";
            while ((s = in.readLine()) != null) {
                //     System.out.println(s);
                data = data + s;
            }



            //  System.out.println("Data-->"+data);
            JSONObject jObject = new JSONObject(data);


            // System.out.println("jObject-->"+jObject);
            mainList = new ArrayList();
            //------------------

            for (int i = 0; i < jObject.length(); i++) {
                JSONObject subJson = jObject.getJSONObject(String.valueOf(i));
                subMap = new HashMap();

                Iterator<?> subkeys = subJson.keys();
                while (subkeys.hasNext()) {
                    String subkey = (String) subkeys.next();
                    String value = (String) subJson.get(subkey);
                    // subList.add(value);
                    subMap.put(subkey, value);

                }
                mainList.add(subMap);

                //---------------------

                /*        
                
                Iterator<?> keys = jObject.keys();
                
                List subList ;
                
                boolean isEmailExist = true;
                while( keys.hasNext() ){
                String key = (String)keys.next();
                Map subMap = new TreeMap();
                // System.out.println("Sub map-->"+key);
                JSONObject subJson = jObject.getJSONObject(key);
                subList = new ArrayList();
                Iterator<?> subkeys = subJson.keys();
                while( subkeys.hasNext() ){
                String subkey = (String)subkeys.next();
                String value = (String)subJson.get(subkey); 
                // subList.add(value);
                if(subkey.equals("EmailId")){
                
                isEmailExist = DataSourceDataProvider.getInstance().isConsultantEmailExist(value);
                
                System.out.println("isEmailExist-->"+isEmailExist);
                subMap.put("AddFlag",isEmailExist);
                isEmailExist = true;
                }
                subMap.put(subkey, value);
                
                }*/





                // map.put(key, mailJson);

            }
            //System.out.println("mainList size-->"+mainList.size()) ;

            // System.out.println("\nREST Service Invoked Successfully..");
            in.close();
        } catch (Exception e) {
            System.out.println("\nError while calling REST Service");
            System.out.println(e);
        }
        return mainList;
    }

    public List getLocationList() throws ServiceLocatorException {
        List locationsList = null;
        try {
            // URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doJobPost/locationsList");
            String serviceUrl = RestRepository.getInstance().getSrviceUrl("locationsList");
            URL url = new URL(serviceUrl);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            //System.out.println("Output from Server .... \n");
            //String s=null;
            String data = "";
            while ((output = br.readLine()) != null) {
                data = data + output;
                //System.out.println(output);
            }
            JSONObject jObject = new JSONObject(data);
            locationsList = new ArrayList();
            Iterator<?> keys = jObject.keys();


            while (keys.hasNext()) {
                String key = (String) keys.next();
                Map subMap = new TreeMap();
                // System.out.println("Sub map-->"+key);
                String locationName = jObject.getString(key);
                locationsList.add(locationName);

            }


            conn.disconnect();
        } catch (Exception e) {
            System.out.println("\nError while calling REST Service");
            System.out.println(e);
        }
        return locationsList;
    }

    /*public List websiteLatestJobApplications() throws ServiceLocatorException{
    List latestJobsList = new LinkedList();
    Map jobsMap = null;
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    connection = ConnectionProvider.getInstance().getWebConnection();
    String queryString = "SELECT tblJobDescriptions.JobId,JobTitle,tblJobDescriptions.JobDescription,tblJobDescriptions.JobStatus,tblJobConsultants.AppliedDate FROM tblJobConsultants LEFT JOIN tblJobDescriptions ON (tblJobConsultants.JobId = tblJobDescriptions.JobId ) GROUP BY tblJobDescriptions.JobId ORDER BY  tblJobConsultants.AppliedDate DESC";
    
    System.out.println("queryString-->"+queryString);
    try {
    statement = connection.createStatement();
    resultSet = statement.executeQuery(queryString);
    while(resultSet.next()){
    jobsMap = new HashMap();
    jobsMap.put("JobId", resultSet.getString("JobId"));
    jobsMap.put("JobTitle", resultSet.getString("JobTitle"));
    jobsMap.put("JobDescription", resultSet.getString("JobDescription"));
    jobsMap.put("JobStatus", resultSet.getString("JobStatus"));
    jobsMap.put("AppliedDate", resultSet.getString("AppliedDate"));
    latestJobsList.add(jobsMap);
    }            
    }catch (SQLException ex) {
    ex.printStackTrace();
    }finally {
    
    try {
    // resultSet Object Checking if it's null then close and set null
    if(resultSet != null) {
    resultSet.close();
    resultSet = null;
    }
    
    if(statement != null) {
    statement.close();
    statement = null;
    }
    
    if(connection != null) {
    connection.close();
    connection = null;
    }
    } catch (SQLException ex) {
    throw new ServiceLocatorException(ex);
    }
    }
    return latestJobsList;
    }
    
     */
    public List websiteLatestJobApplications() throws ServiceLocatorException {

        List latestJobsList = new ArrayList();
        Map jobsMap = null;
        try {
            //    URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doJobPost/latestAppliedJobs");
            String serviceUrl = RestRepository.getInstance().getSrviceUrl("jobOrderByLastApplied");
            URL url = new URL(serviceUrl);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            //System.out.println("Output from Server .... \n");
            //String s=null;
            String data = "";
            while ((output = br.readLine()) != null) {
                data = data + output;
                //System.out.println(output);
            }
            JSONObject jObject = new JSONObject(data);
            for (int i = 0; i < jObject.length(); i++) {
                JSONObject subJson = jObject.getJSONObject(String.valueOf(i));
                jobsMap = new HashMap();

                Iterator<?> subkeys = subJson.keys();
                while (subkeys.hasNext()) {

                    String subkey = (String) subkeys.next();
                    String value = (String) subJson.get(subkey);
                    // subList.add(value);
                    jobsMap.put(subkey, value);

                }
                latestJobsList.add(jobsMap);



            }



            br.close();
            conn.disconnect();
        } catch (Exception e) {
            System.out.println("\nError while calling REST Service");
            System.out.println(e);
        }
        return latestJobsList;
    }

    public List doEventSearch(MarketingAction marketingAction) throws ServiceLocatorException {
        List jobsList = null;
        List mainList = null;
        String createdDateFrom;
        String createdDateTo;

        String title;
        String status;


        try {

            JSONObject jb = new JSONObject();

            if (marketingAction.getCreatedDateFrom() != null && !"".equals(marketingAction.getCreatedDateFrom())) {
                jb.put("event_startdate", marketingAction.getCreatedDateFrom());
            } else {
                jb.put("event_startdate", "none");
            }
            if (marketingAction.getCreatedDateTo() != null && !"".equals(marketingAction.getCreatedDateTo())) {
                jb.put("evetnt_enddate", marketingAction.getCreatedDateTo());
            } else {
                jb.put("evetnt_enddate", "none");
            }
            if (marketingAction.getTitle() != null && !"".equals(marketingAction.getTitle())) {
                jb.put("title", marketingAction.getTitle());
            } else {
                jb.put("title", "none");
            }
            if (marketingAction.getStatus() != null && !"".equals(marketingAction.getStatus())) {
                jb.put("status", marketingAction.getStatus());
            } else {
                jb.put("status", "none");
            }
           // System.out.println("Event Type-->" + marketingAction.getEventSearchType());

            if (marketingAction.getEventSearchType() != null && !"".equals(marketingAction.getEventSearchType())) {
                jb.put("WebinarType", marketingAction.getEventSearchType());
            } else {
                jb.put("WebinarType", "none");
            }
           // System.out.println("In event search method");
            // URL url = new URL("http://localhost:8080/CrunchifyTutorials/api/crunchifyService");
            //URL url = new URL("http://172.17.11.251:8080/WebRoot/resources/doJobPost/search");
            //  URL url = new URL("http://172.17.14.226:8080/WebRoot/resources/doJobPost/search");
            //   URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doEventPost/eventSearch");
            String serviceUrl = RestRepository.getInstance().getSrviceUrl("eventSearch");
         //   System.out.print("serviceUrl--->" + serviceUrl);


            URL url = new URL(serviceUrl);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write(jb.toString());
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String s = null;
            String data = "";
            while ((s = in.readLine()) != null) {
                //     System.out.println(s);
                data = data + s;
            }



            //  System.out.println("Data-->"+data);
            JSONObject jObject = new JSONObject(data);

            mainList = new ArrayList();
            for (int i = 0; i < jObject.length(); i++) {
                JSONObject subJson = jObject.getJSONObject(String.valueOf(i));
                Map subMap = new TreeMap();

                Iterator<?> subkeys = subJson.keys();
                while (subkeys.hasNext()) {

                    String subkey = (String) subkeys.next();
                    String value = (String) subJson.get(subkey);
                    // subList.add(value);
                    subMap.put(subkey, value);
                }




                //////////////////----------------
        /*         
                Iterator<?> keys = jObject.keys();
                
                List subList ;
                while( keys.hasNext() ){
                String key = (String)keys.next();
                Map subMap = new TreeMap();
                // System.out.println("Sub map-->"+key);
                JSONObject subJson = jObject.getJSONObject(key);
                subList = new ArrayList();
                Iterator<?> subkeys = subJson.keys();
                while( subkeys.hasNext() ){
                String subkey = (String)subkeys.next();
                String value = (String)subJson.get(subkey); 
                // subList.add(value);
                subMap.put(subkey, value);
                }
                 */
                mainList.add(subMap);



                // map.put(key, mailJson);

            }
            //  System.out.println("\nREST Service Invoked Successfully..");
            in.close();
        } catch (Exception e) {
            System.out.println("\nError while calling REST Service");
            System.out.println(e);
        }
        return mainList;
    }

    public List getEventSpeakers(MarketingAction marketingAction) throws ServiceLocatorException {
        List jobsList = null;
        List mainList = null;
        String createdDateFrom;
        String createdDateTo;
        String jobId;
        String title;
        String status;
        Map subMap = null;
        // boolean primaryExist = false;
        int primaryFlag = 0;
        try {

            JSONObject jb = new JSONObject();

            if (marketingAction.getEventId() != null && !"".equals(marketingAction.getEventId())) {
                jb.put("eventId", marketingAction.getEventId());
            } else {
                jb.put("eventId", "none");
            }


            // URL url = new URL("http://localhost:8080/CrunchifyTutorials/api/crunchifyService");
            //URL url = new URL("http://172.17.11.251:8080/WebRoot/resources/doJobPost/search");
            //URL url = new URL("http://172.17.14.226:8080/WebRoot/resources/doJobPost/appliedList");
            // URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doJobPost/appliedList");

            String serviceUrl = RestRepository.getInstance().getSrviceUrl("eventSpeakersList");
            URL url = new URL(serviceUrl);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write(jb.toString());
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String s = null;
            String data = "";
            while ((s = in.readLine()) != null) {
                //     System.out.println(s);
                data = data + s;
            }



            //  System.out.println("Data-->"+data);
            JSONObject jObject = new JSONObject(data);


            // System.out.println("jObject-->"+jObject);
            mainList = new ArrayList();
            //------------------

            for (int i = 0; i < jObject.length(); i++) {
                JSONObject subJson = jObject.getJSONObject(String.valueOf(i));
                subMap = new HashMap();

                Iterator<?> subkeys = subJson.keys();
                while (subkeys.hasNext()) {
                    String subkey = (String) subkeys.next();
                    String value = (String) subJson.get(subkey);
                    // subList.add(value);

                    if (subkey.equals("primary_speaker")) {
                        //primaryFlag
                        if ("1".equals(value)) {
                            primaryFlag++;
                        }

                    }


                    subMap.put(subkey, value);

                }
                mainList.add(subMap);

                //---------------------

                /*        
                
                Iterator<?> keys = jObject.keys();
                
                List subList ;
                
                boolean isEmailExist = true;
                while( keys.hasNext() ){
                String key = (String)keys.next();
                Map subMap = new TreeMap();
                // System.out.println("Sub map-->"+key);
                JSONObject subJson = jObject.getJSONObject(key);
                subList = new ArrayList();
                Iterator<?> subkeys = subJson.keys();
                while( subkeys.hasNext() ){
                String subkey = (String)subkeys.next();
                String value = (String)subJson.get(subkey); 
                // subList.add(value);
                if(subkey.equals("EmailId")){
                
                isEmailExist = DataSourceDataProvider.getInstance().isConsultantEmailExist(value);
                
                System.out.println("isEmailExist-->"+isEmailExist);
                subMap.put("AddFlag",isEmailExist);
                isEmailExist = true;
                }
                subMap.put(subkey, value);
                
                }*/





                // map.put(key, mailJson);

            }

            if (primaryFlag > 0) {
                marketingAction.setPrimarySpeakerExist("YES");
            } else {
                marketingAction.setPrimarySpeakerExist("NO");
            }
            //  primarySpeakerExist
            //System.out.println("mainList size-->"+mainList.size()) ;

            // System.out.println("\nREST Service Invoked Successfully..");
            in.close();
        } catch (Exception e) {
            System.out.println("\nError while calling REST Service");
            System.out.println(e);
        }
        return mainList;
    }

    public List searchWebsiteInfo(MarketingAction marketingAction) throws ServiceLocatorException {
        List jobsList = null;
        List mainList = null;
        String createdDateFrom;
        String createdDateTo;
        String jobId;
        String title;
        String status;
        Map subMap = null;
        // boolean primaryExist = false;

        try {

            JSONObject jb = new JSONObject();

            //  if(marketingAction.getEventId()!=null&&!"".equals(marketingAction.getEventId())){
            jb.put("tableName", marketingAction.getTableName());

            if (marketingAction.getCreatedDateFrom() != null && !"".equals(marketingAction.getCreatedDateFrom())) {
                jb.put("createdDateFrom", marketingAction.getCreatedDateFrom());
            } else {
                jb.put("createdDateFrom", "none");
            }

            if (marketingAction.getCreatedDateTo() != null && !"".equals(marketingAction.getCreatedDateTo())) {
                jb.put("createdDateTo", marketingAction.getCreatedDateTo());
            } else {
                jb.put("createdDateTo", "none");
            }


            if (marketingAction.getTableName().equals("tblResourceDepotDetails")) {



                if (!"".equals(marketingAction.getTrackName()) && marketingAction.getTrackName() != null) {
                    jb.put("trackName", marketingAction.getTrackName());
                } else {
                    jb.put("trackName", "none");
                }
                if (marketingAction.getAccessType() != null && !"".equals(marketingAction.getAccessType())) {
                    jb.put("AccessType", marketingAction.getAccessType());
                } else {
                    jb.put("AccessType", "none");
                }

            }




            if (marketingAction.getTableName().equals("tblEventAttendies")) {
                if (marketingAction.getQmeetTitleId() != null && !"".equals(marketingAction.getQmeetTitleId())) {
                    jb.put("qmeetTitleId", marketingAction.getQmeetTitleId());
                } else {
                    jb.put("qmeetTitleId", "none");
                }
            }
            if (marketingAction.getTableName().equals("tblIot")) {



                if (marketingAction.getIotNameSearch() != null && !"".equals(marketingAction.getIotNameSearch())) {
                    jb.put("IotNameSearch", marketingAction.getIotNameSearch());
                } else {
                    jb.put("IotNameSearch", "none");
                }
                if (marketingAction.getIotZipCodeSearch() != null && !"".equals(marketingAction.getIotZipCodeSearch())) {
                    jb.put("IotZipCodeSearch", marketingAction.getIotZipCodeSearch());
                } else {
                    jb.put("IotZipCodeSearch", "none");
                }

            }

            if (marketingAction.getTableName().equals("tblSignature")) {



                if (marketingAction.getSignatureNameSearch() != null && !"".equals(marketingAction.getSignatureNameSearch())) {
                    jb.put("SignatureNameSearch", marketingAction.getSignatureNameSearch());
                } else {
                    jb.put("SignatureNameSearch", "none");
                }
                if (marketingAction.getSignatureEmailSearch() != null && !"".equals(marketingAction.getSignatureEmailSearch())) {
                    jb.put("SignatureEmailSearch", marketingAction.getSignatureEmailSearch());
                } else {
                    jb.put("SignatureEmailSearch", "none");
                }

            }
            // }


            // URL url = new URL("http://localhost:8080/CrunchifyTutorials/api/crunchifyService");
            //URL url = new URL("http://172.17.11.251:8080/WebRoot/resources/doJobPost/search");
            //URL url = new URL("http://172.17.14.226:8080/WebRoot/resources/doJobPost/appliedList");
            // URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doJobPost/appliedList");

            String serviceUrl = RestRepository.getInstance().getSrviceUrl("wesiteInfoSearch");
            URL url = new URL(serviceUrl);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write(jb.toString());
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String s = null;
            String data = "";
            while ((s = in.readLine()) != null) {
                //     System.out.println(s);
                data = data + s;
            }



            //  System.out.println("Data-->"+data);
            JSONObject jObject = new JSONObject(data);


            // System.out.println("jObject-->"+jObject);
            mainList = new ArrayList();
            //------------------

            for (int i = 0; i < jObject.length(); i++) {
                JSONObject subJson = jObject.getJSONObject(String.valueOf(i));
                subMap = new HashMap();

                Iterator<?> subkeys = subJson.keys();
                while (subkeys.hasNext()) {
                    String subkey = (String) subkeys.next();
                    String value = (String) subJson.get(subkey);

                    subMap.put(subkey, value);

                }
                mainList.add(subMap);

                //---------------------

                /*        
                
                Iterator<?> keys = jObject.keys();
                
                List subList ;
                
                boolean isEmailExist = true;
                while( keys.hasNext() ){
                String key = (String)keys.next();
                Map subMap = new TreeMap();
                // System.out.println("Sub map-->"+key);
                JSONObject subJson = jObject.getJSONObject(key);
                subList = new ArrayList();
                Iterator<?> subkeys = subJson.keys();
                while( subkeys.hasNext() ){
                String subkey = (String)subkeys.next();
                String value = (String)subJson.get(subkey); 
                // subList.add(value);
                if(subkey.equals("EmailId")){
                
                isEmailExist = DataSourceDataProvider.getInstance().isConsultantEmailExist(value);
                
                System.out.println("isEmailExist-->"+isEmailExist);
                subMap.put("AddFlag",isEmailExist);
                isEmailExist = true;
                }
                subMap.put(subkey, value);
                
                }*/





                // map.put(key, mailJson);

            }


            //  primarySpeakerExist
            //System.out.println("mainList size-->"+mainList.size()) ;

            // System.out.println("\nREST Service Invoked Successfully..");
            in.close();
        } catch (Exception e) {
            System.out.println("\nError while calling REST Service");
            System.out.println(e);
        }
        return mainList;
    }

    public List doCompletedEventSearch(MarketingAction marketingAction) throws ServiceLocatorException {
        List jobsList = null;
        List mainList = null;
        String createdDateFrom;
        String createdDateTo;

        String title;
        String status;


        try {

            JSONObject jb = new JSONObject();

            if (marketingAction.getCreatedDateFrom() != null && !"".equals(marketingAction.getCreatedDateFrom())) {
                jb.put("event_startdate", marketingAction.getCreatedDateFrom());
            } else {
                jb.put("event_startdate", "none");
            }
            if (marketingAction.getCreatedDateTo() != null && !"".equals(marketingAction.getCreatedDateTo())) {
                jb.put("evetnt_enddate", marketingAction.getCreatedDateTo());
            } else {
                jb.put("evetnt_enddate", "none");
            }
            if (marketingAction.getTitle() != null && !"".equals(marketingAction.getTitle())) {
                jb.put("title", marketingAction.getTitle());
            } else {
                jb.put("title", "none");
            }
            if (marketingAction.getStatus() != null && !"".equals(marketingAction.getStatus())) {
                jb.put("status", marketingAction.getStatus());
            } else {
                jb.put("status", "none");
            }
            //  System.out.println("Event Type-->"+marketingAction.getEventSearchType());

            if (marketingAction.getEventSearchType() != null && !"".equals(marketingAction.getEventSearchType())) {
                jb.put("WebinarType", marketingAction.getEventSearchType());
            } else {
                jb.put("WebinarType", "none");
            }
// System.out.println("In event search method");
            // URL url = new URL("http://localhost:8080/CrunchifyTutorials/api/crunchifyService");
            //URL url = new URL("http://172.17.11.251:8080/WebRoot/resources/doJobPost/search");
            //  URL url = new URL("http://172.17.14.226:8080/WebRoot/resources/doJobPost/search");
            //   URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doEventPost/eventSearch");
            String serviceUrl = RestRepository.getInstance().getSrviceUrl("completedEventSearch");

            //    System.out.println("serviceUrl-->"+serviceUrl);
            URL url = new URL(serviceUrl);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write(jb.toString());
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String s = null;
            String data = "";
            while ((s = in.readLine()) != null) {
                //     System.out.println(s);
                data = data + s;
            }



            //  System.out.println("Data-->"+data);
            JSONObject jObject = new JSONObject(data);

            mainList = new ArrayList();
            for (int i = 0; i < jObject.length(); i++) {
                JSONObject subJson = jObject.getJSONObject(String.valueOf(i));
                Map subMap = new TreeMap();

                Iterator<?> subkeys = subJson.keys();
                while (subkeys.hasNext()) {

                    String subkey = (String) subkeys.next();
                    String value = (String) subJson.get(subkey);
                    // subList.add(value);
                    subMap.put(subkey, value);
                }




                //////////////////----------------
        /*         
                Iterator<?> keys = jObject.keys();
                
                List subList ;
                while( keys.hasNext() ){
                String key = (String)keys.next();
                Map subMap = new TreeMap();
                // System.out.println("Sub map-->"+key);
                JSONObject subJson = jObject.getJSONObject(key);
                subList = new ArrayList();
                Iterator<?> subkeys = subJson.keys();
                while( subkeys.hasNext() ){
                String subkey = (String)subkeys.next();
                String value = (String)subJson.get(subkey); 
                // subList.add(value);
                subMap.put(subkey, value);
                }
                 */
                mainList.add(subMap);



                // map.put(key, mailJson);

            }
            //  System.out.println("\nREST Service Invoked Successfully..");
            in.close();
        } catch (Exception e) {
            System.out.println("\nError while calling REST Service");
            System.out.println(e);
        }
        return mainList;
    }

    public List getTrackNamesList() throws ServiceLocatorException {
        List trackNamesList = null;
        try {
            // URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doJobPost/locationsList");
            String serviceUrl = RestRepository.getInstance().getSrviceUrl("libraryTrackNamesList");
            URL url = new URL(serviceUrl);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            //System.out.println("Output from Server .... \n");
            //String s=null;
            String data = "";
            while ((output = br.readLine()) != null) {
                data = data + output;
                //System.out.println(output);
            }
            JSONObject jObject = new JSONObject(data);
            trackNamesList = new ArrayList();
            Iterator<?> keys = jObject.keys();


            while (keys.hasNext()) {
                String key = (String) keys.next();
                Map subMap = new TreeMap();
                // System.out.println("Sub map-->"+key);
                String trackName = jObject.getString(key);
                trackNamesList.add(trackName);

            }


            conn.disconnect();
        } catch (Exception e) {
            System.out.println("\nError while calling REST Service");
            System.out.println(e);
        }
        return trackNamesList;
    }

    public Map getQmeetMap(MarketingAction marketingAction) throws ServiceLocatorException {

        Map qmeetMap = new HashMap();
        try {

            JSONObject jb = new JSONObject();

            // System.out.println("year-->"+marketingAction.getQmeetYear());
            jb.put("year", marketingAction.getQmeetYear());

            String serviceUrl = RestRepository.getInstance().getSrviceUrl("eventQmeetMap");
            URL url = new URL(serviceUrl);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write(jb.toString());
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String s = null;
            String data = "";
            while ((s = in.readLine()) != null) {
                //     System.out.println(s);
                data = data + s;
            }



            // System.out.println("Data-->"+data);
            JSONObject jObject = new JSONObject(data);


            /***********************/
            Iterator<String> keysItr = jObject.keys();
            while (keysItr.hasNext()) {
                String key = keysItr.next();
                String value = (String) jObject.get(key);
                qmeetMap.put(key, value);
                //System.out.println("Id-->"+key+"-->"+value);
            }


            /*************************/
            in.close();
        } catch (Exception e) {
            System.out.println("\nError while calling REST Service");
            System.out.println(e);
        }
        return qmeetMap;
    }

    public List getWebinarSeriesList(MarketingAction marketingAction) throws ServiceLocatorException {

        List jobsList = null;
        List mainList = null;
        String createdDateFrom;
        String createdDateTo;

        String title;
        String status;


        try {

            JSONObject jb = new JSONObject();

            if (marketingAction.getCreatedDateFrom() != null && !"".equals(marketingAction.getCreatedDateFrom())) {
                jb.put("CreatedDateFrom", marketingAction.getCreatedDateFrom());
            } else {
                jb.put("CreatedDateFrom", "none");
            }
            if (marketingAction.getCreatedDateTo() != null && !"".equals(marketingAction.getCreatedDateTo())) {
                jb.put("CreatedDateTo", marketingAction.getCreatedDateTo());
            } else {
                jb.put("CreatedDateTo", "none");
            }
            if (marketingAction.getSearchSeriesTitle() != null && !"".equals(marketingAction.getSearchSeriesTitle())) {
                jb.put("SeriesTitle", marketingAction.getSearchSeriesTitle());
            } else {
                jb.put("SeriesTitle", "none");
            }
            if (marketingAction.getSearchSeriesStatus() != null && !"".equals(marketingAction.getSearchSeriesStatus())) {
                jb.put("SeriesStatus", marketingAction.getSearchSeriesStatus());
            } else {
                jb.put("SeriesStatus", "none");
            }

            if (marketingAction.getEventType() != null && !"".equals(marketingAction.getEventType())) {
                jb.put("SeriesType", marketingAction.getEventType());
            } else {
                jb.put("SeriesType", "none");
            }
            if (marketingAction.getSearchSeriesTrack() != null && !"".equals(marketingAction.getSearchSeriesTrack())) {
                jb.put("SeriesTrack", marketingAction.getSearchSeriesTrack());
            } else {
                jb.put("SeriesTrack", "none");
            }
         //   System.out.println("In event search method");
            // URL url = new URL("http://localhost:8080/CrunchifyTutorials/api/crunchifyService");
            //URL url = new URL("http://172.17.11.251:8080/WebRoot/resources/doJobPost/search");
            //  URL url = new URL("http://172.17.14.226:8080/WebRoot/resources/doJobPost/search");
            //   URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doEventPost/eventSearch");
            String serviceUrl = RestRepository.getInstance().getSrviceUrl("webinarSeriesList");

            //  System.out.println("serviceUrl-->"+serviceUrl);
            URL url = new URL(serviceUrl);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write(jb.toString());
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String s = null;
            String data = "";
            while ((s = in.readLine()) != null) {
                //     System.out.println(s);
                data = data + s;
            }



            //  System.out.println("Data-->"+data);
            JSONObject jObject = new JSONObject(data);

            mainList = new ArrayList();
            for (int i = 0; i < jObject.length(); i++) {
                JSONObject subJson = jObject.getJSONObject(String.valueOf(i));
                Map subMap = new TreeMap();

                Iterator<?> subkeys = subJson.keys();
                while (subkeys.hasNext()) {

                    String subkey = (String) subkeys.next();
                    String value = (String) subJson.get(subkey);
                    // subList.add(value);
                    subMap.put(subkey, value);
                }




                //////////////////----------------
        /*         
                Iterator<?> keys = jObject.keys();
                
                List subList ;
                while( keys.hasNext() ){
                String key = (String)keys.next();
                Map subMap = new TreeMap();
                // System.out.println("Sub map-->"+key);
                JSONObject subJson = jObject.getJSONObject(key);
                subList = new ArrayList();
                Iterator<?> subkeys = subJson.keys();
                while( subkeys.hasNext() ){
                String subkey = (String)subkeys.next();
                String value = (String)subJson.get(subkey); 
                // subList.add(value);
                subMap.put(subkey, value);
                }
                 */
                mainList.add(subMap);



                // map.put(key, mailJson);

            }
            //  System.out.println("\nREST Service Invoked Successfully..");
            in.close();
        } catch (Exception e) {
            System.out.println("\nError while calling REST Service");
            System.out.println(e);
        }
        return mainList;
    }

    public List getEventsListBySeries(MarketingAction marketingAction) throws ServiceLocatorException {
        List jobsList = null;
        List mainList = null;
        String createdDateFrom;
        String createdDateTo;
        String jobId;
        String title;
        String status;
        Map subMap = null;
        // boolean primaryExist = false;
        int primaryFlag = 0;
        try {

            JSONObject jb = new JSONObject();

            //  if(marketingAction.getEventId()!=null&&!"".equals(marketingAction.getEventId())){
            jb.put("SeriesId", marketingAction.getSeriesId());
            //  }else {
            //     jb.put("eventId", "none");
            // }  


            // URL url = new URL("http://localhost:8080/CrunchifyTutorials/api/crunchifyService");
            //URL url = new URL("http://172.17.11.251:8080/WebRoot/resources/doJobPost/search");
            //URL url = new URL("http://172.17.14.226:8080/WebRoot/resources/doJobPost/appliedList");
            // URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doJobPost/appliedList");

            String serviceUrl = RestRepository.getInstance().getSrviceUrl("getEventsListBySeries");
            URL url = new URL(serviceUrl);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write(jb.toString());
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String s = null;
            String data = "";
            while ((s = in.readLine()) != null) {
                //     System.out.println(s);
                data = data + s;
            }



            //  System.out.println("Data-->"+data);
            JSONObject jObject = new JSONObject(data);


            // System.out.println("jObject-->"+jObject);
            mainList = new ArrayList();
            //------------------

            for (int i = 0; i < jObject.length(); i++) {
                JSONObject subJson = jObject.getJSONObject(String.valueOf(i));
                subMap = new HashMap();

                Iterator<?> subkeys = subJson.keys();
                while (subkeys.hasNext()) {
                    String subkey = (String) subkeys.next();
                    String value = (String) subJson.get(subkey);
                    // subList.add(value);




                    subMap.put(subkey, value);

                }
                mainList.add(subMap);

                //---------------------

                /*        
                
                Iterator<?> keys = jObject.keys();
                
                List subList ;
                
                boolean isEmailExist = true;
                while( keys.hasNext() ){
                String key = (String)keys.next();
                Map subMap = new TreeMap();
                // System.out.println("Sub map-->"+key);
                JSONObject subJson = jObject.getJSONObject(key);
                subList = new ArrayList();
                Iterator<?> subkeys = subJson.keys();
                while( subkeys.hasNext() ){
                String subkey = (String)subkeys.next();
                String value = (String)subJson.get(subkey); 
                // subList.add(value);
                if(subkey.equals("EmailId")){
                
                isEmailExist = DataSourceDataProvider.getInstance().isConsultantEmailExist(value);
                
                System.out.println("isEmailExist-->"+isEmailExist);
                subMap.put("AddFlag",isEmailExist);
                isEmailExist = true;
                }
                subMap.put(subkey, value);
                
                }*/





                // map.put(key, mailJson);

            }


            //  primarySpeakerExist
            //System.out.println("mainList size-->"+mainList.size()) ;

            // System.out.println("\nREST Service Invoked Successfully..");
            in.close();
        } catch (Exception e) {
            System.out.println("\nError while calling REST Service");
            System.out.println(e);
        }
        return mainList;
    }

    public Map getUnAssociatedEvents(String seriesId) throws Exception {
        Map unAssociatedEventsMap = new HashMap();

        try {

            JSONObject jb = new JSONObject();

            // System.out.println("year-->"+marketingAction.getQmeetYear());
            jb.put("SeriesId", seriesId);


            String serviceUrl = RestRepository.getInstance().getSrviceUrl("getUnAssociatedEvents");
            URL url = new URL(serviceUrl);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write(jb.toString());
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String s = null;
            String data = "";
            while ((s = in.readLine()) != null) {
                //     System.out.println(s);
                data = data + s;
            }



            // System.out.println("Data-->"+data);
            JSONObject jObject = new JSONObject(data);


            /***********************/
            /*  qmeetXml.append("<xml version=\"1.0\">");
            qmeetXml.append("<EVENTS>");
            qmeetXml.append("<EVENT eventId=\"\">--Please Select--</EVENT>");*/
            Iterator<String> keysItr = jObject.keys();
            while (keysItr.hasNext()) {
                String key = keysItr.next();
                String value = (String) jObject.get(key);
                unAssociatedEventsMap.put(key, value);
                // System.out.println("Id-->"+key+"-->"+value);


            }


            /*************************/
            in.close();
        } catch (Exception e) {
            System.out.println("\nError while calling REST Service");
            System.out.println(e);
        }

        return unAssociatedEventsMap;
    }

    public String addEventToSeries(String seriesId, String eventId) throws Exception {
        String message = "";
        boolean isUpdated = false;

        try {


            JSONObject jb = new JSONObject();
            jb.put("SeriesId", seriesId);
            jb.put("event_id", eventId);


            //   URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doEventPost/addEvent");
            String serviceUrl = RestRepository.getInstance().getSrviceUrl("addEventToSeries");
            URL url = new URL(serviceUrl);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write(jb.toString());
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String s = null;
            String data = "";
            while ((s = in.readLine()) != null) {

                data = data + s;
            }

            JSONObject jObject = new JSONObject(data);

            message = jObject.getString("message");



            in.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return message;
    }

//------
    public String getPrimarySpeaker(int eventId, String objectType) throws ServiceLocatorException {

        // String primaryTeamMemberUserId = "";
        String primarySpeakerId = "";

        String message = "";
        boolean isUpdated = false;
        //  String queryString ="";
        // System.out.println("first call");
        String data = "";
        //queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ModifiedBy=?,ModifiedDate=?,ReviewName=?,ReviewDate=?,Status=?,TLComments=?,TLRating=?,HRRating=?,HrComments=? where Id=?";
        //if(roleName.equalsIgnoreCase("Employee"))
        // queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ReviewName=?,ReviewDate=?,Status=? where Id=?";
        try {


            JSONObject jb = new JSONObject();

            jb.put("event_id", String.valueOf(eventId));
            jb.put("ObjectType", objectType);
            //  URL url = new URL("http://172.17.11.251:8080/WebRoot/resources/doJobPost/edit");
            //  URL url = new URL("http://172.17.14.226:8080/WebRoot/resources/doJobPost/edit");
            //  URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doJobPost/edit");
            String serviceUrl = RestRepository.getInstance().getSrviceUrl("getPrimarySpeaker");
            URL url = new URL(serviceUrl);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write(jb.toString());
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String s = null;

            while ((s = in.readLine()) != null) {

                data = data + s;
            }

            JSONObject jObject = new JSONObject(data);

            // message = jObject.getString("message");
            //  System.out.println("displaymessage-->" +message);
            primarySpeakerId = jObject.getString("SpeakerId");

            in.close();
            /* connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1,ajaxHandlerAction.getOverlayReviewType());
            preparedStatement.setString(2,ajaxHandlerAction.getOverlayDescription());
            
            
            
            preparedStatement.setString(3,ajaxHandlerAction.getOverlayReviewName());
            preparedStatement.setDate(4, DateUtility.getInstance().getMysqlDate(ajaxHandlerAction.getOverlayReviewDate()));
            preparedStatement.setString(5,ajaxHandlerAction.getReviewStatusOverlay());
            
            preparedStatement.setString(6,ajaxHandlerAction.getReviewId());
            isUpdated = preparedStatement.execute();*/
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return primarySpeakerId;
    }

    public Map getSpeakersByEventId(int eventId, String primaryTeamMember, String objectType) throws ServiceLocatorException {

        Map speakerMap = new HashMap();
        speakerMap.clear();
        String primarySpeakerId = "";

        String message = "";
        boolean isUpdated = false;
        //  String queryString ="";
        // System.out.println("first call");
        String data = "";
        //queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ModifiedBy=?,ModifiedDate=?,ReviewName=?,ReviewDate=?,Status=?,TLComments=?,TLRating=?,HRRating=?,HrComments=? where Id=?";
        //if(roleName.equalsIgnoreCase("Employee"))
        // queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ReviewName=?,ReviewDate=?,Status=? where Id=?";
        try {


            JSONObject jb = new JSONObject();

            jb.put("eventId", String.valueOf(eventId));

            if (primaryTeamMember == null) {
                primaryTeamMember = "";
            }
            jb.put("primaryTeamMember", primaryTeamMember);
            jb.put("objectType", objectType);



            //  URL url = new URL("http://172.17.11.251:8080/WebRoot/resources/doJobPost/edit");
            //  URL url = new URL("http://172.17.14.226:8080/WebRoot/resources/doJobPost/edit");
            //  URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doJobPost/edit");
            String serviceUrl = RestRepository.getInstance().getSrviceUrl("getSpeakersByEventId");
            URL url = new URL(serviceUrl);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write(jb.toString());
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String s = null;

            while ((s = in.readLine()) != null) {

                data = data + s;
            }

            JSONObject jObject = new JSONObject(data);
            Iterator<?> keys = jObject.keys();


            while (keys.hasNext()) {
                String key = (String) keys.next();
                // subMap = new TreeMap();
                // System.out.println("Sub map-->"+key);
                String speakerName = jObject.getString(key);
                // trackNamesList.add(trackName);
                speakerMap.put(key, speakerName);
            }
            // message = jObject.getString("message");
            //  System.out.println("displaymessage-->" +message);
//primarySpeakerId = jObject.getString("SpeakerId");

            in.close();
            /* connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1,ajaxHandlerAction.getOverlayReviewType());
            preparedStatement.setString(2,ajaxHandlerAction.getOverlayDescription());
            
            
            
            preparedStatement.setString(3,ajaxHandlerAction.getOverlayReviewName());
            preparedStatement.setDate(4, DateUtility.getInstance().getMysqlDate(ajaxHandlerAction.getOverlayReviewDate()));
            preparedStatement.setString(5,ajaxHandlerAction.getReviewStatusOverlay());
            
            preparedStatement.setString(6,ajaxHandlerAction.getReviewId());
            isUpdated = preparedStatement.execute();*/
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        /*
        ResultSet resultSet = null;
        Connection connection = null;
        Statement statement = null;
        
        connection = ConnectionProvider.getInstance().getConnection();
        
        try {
        statement = connection.createStatement();
        
        if(!("".equals(primaryTeamMember))){
        resultSet = statement.executeQuery("SELECT DISTINCT SpeakerId,SpeakerName FROM vwSpeakerList WHERE eventId="+eventId+" AND SpeakerId="+primaryTeamMember+" AND ObjectType='"+objectType+"'");
        while(resultSet.next()) {
        speakerMap.put(resultSet.getString("SpeakerId"),resultSet.getString("SpeakerName"));
        }
        resultSet.close();
        resultSet = null;
        }
        
        resultSet = statement.executeQuery("SELECT DISTINCT SpeakerId,SpeakerName FROM vwSpeakerList WHERE eventId="+eventId+" AND ObjectType='"+objectType+"'");
        while(resultSet.next()) {
        if(!(speakerMap.containsKey(resultSet.getString("SpeakerId")))){
        speakerMap.put(resultSet.getString("SpeakerId"),resultSet.getString("SpeakerName"));
        }
        
        }
        
        
        } catch (SQLException ex) {
        throw new ServiceLocatorException(ex);
        }finally{
        try{
        if(resultSet != null){
        resultSet.close();
        resultSet = null;
        }
        if(statement != null){
        statement.close();
        statement = null;
        }
        if(connection != null){
        connection.close();
        connection = null;
        }
        }catch (SQLException ex) {
        throw new ServiceLocatorException(ex);
        }
        }*/
        return speakerMap;
    }

    public String getEventTitleByEventId(String eventId, String ObjectType) throws Exception {
        String message = "";
        boolean isUpdated = false;
        //  String queryString ="";
        // System.out.println("first call");
        String data = "";
        String eventTitle = "";
        //queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ModifiedBy=?,ModifiedDate=?,ReviewName=?,ReviewDate=?,Status=?,TLComments=?,TLRating=?,HRRating=?,HrComments=? where Id=?";
        //if(roleName.equalsIgnoreCase("Employee"))
        // queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ReviewName=?,ReviewDate=?,Status=? where Id=?";
        try {


            JSONObject jb = new JSONObject();


            // jb.put("ObjectType",ObjectType);      
            //  URL url = new URL("http://172.17.11.251:8080/WebRoot/resources/doJobPost/edit");
            //  URL url = new URL("http://172.17.14.226:8080/WebRoot/resources/doJobPost/edit");
            //  URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doJobPost/edit");
            String serviceUrl = "";
            if ("EV".equals(ObjectType)) {
                jb.put("event_id", eventId);
                serviceUrl = RestRepository.getInstance().getSrviceUrl("eventEdit");
            } else {
                jb.put("eventId", eventId);
                serviceUrl = RestRepository.getInstance().getSrviceUrl("libraryEdit");
            }

            URL url = new URL(serviceUrl);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write(jb.toString());
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String s = null;

            while ((s = in.readLine()) != null) {

                data = data + s;
            }

            JSONObject jObject = new JSONObject(data);
            if ("EV".equals(ObjectType)) {
                eventTitle = jObject.getString("event_title");
            } else {
                eventTitle = jObject.getString("DocTitle");
            }
            // message = jObject.getString("message");
            //  System.out.println("displaymessage-->" +message);


            in.close();
            /* connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1,ajaxHandlerAction.getOverlayReviewType());
            preparedStatement.setString(2,ajaxHandlerAction.getOverlayDescription());
            
            
            
            preparedStatement.setString(3,ajaxHandlerAction.getOverlayReviewName());
            preparedStatement.setDate(4, DateUtility.getInstance().getMysqlDate(ajaxHandlerAction.getOverlayReviewDate()));
            preparedStatement.setString(5,ajaxHandlerAction.getReviewStatusOverlay());
            
            preparedStatement.setString(6,ajaxHandlerAction.getReviewId());
            isUpdated = preparedStatement.execute();*/
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return eventTitle;
    }

    public Map getAllPeaopleExceptEventSpeakers(Map eventSpeakerMap) throws ServiceLocatorException {


        Map speakerMapExceptEventSpeakerMap = new LinkedHashMap();
        speakerMapExceptEventSpeakerMap.clear();
        //Map sortedMap = new LinkedHashMap();
        //sortedMap.clear();
      /*  ResultSet resultSet = null;
        Connection connection = null;
        Statement statement = null;
        
        connection = ConnectionProvider.getInstance().getConnection();
        
        try {
        statement = connection.createStatement();
        //  resultSet = statement.executeQuery("SELECT LoginId,CONCAT(TRIM(FName),'.',TRIM(LName)) AS EmployeeName FROM tblEmpRoles,tblLKRoles,tblEmployee WHERE tblEmployee.Id=tblEmpRoles.EmpId AND tblLKRoles.Id=tblEmpRoles.RoleId AND RoleId=4 AND CurStatus ='Active' AND DeletedFlag != 1 ORDER BY FName ");
        resultSet = statement.executeQuery("SELECT Id,Name FROM tblLkAuthorsAndSpeakers WHERE STATUS ='Active'  ORDER BY Name ");
        while(resultSet.next()) {
        if(!(eventSpeakerMap.containsKey(resultSet.getString("Id")))){
        speakerMapExceptEventSpeakerMap.put(resultSet.getString("Id"),resultSet.getString("Name"));
        List list = new LinkedList( speakerMapExceptEventSpeakerMap.entrySet());
        
        Collections.sort(list, new Comparator() {
        public int compare(Object o1, Object o2) {
        return ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());
        }
        });
        
        
        for (Iterator it = list.iterator(); it.hasNext();) {
        Map.Entry entry = (Map.Entry)it.next();
        speakerMapExceptEventSpeakerMap.put(entry.getKey(), entry.getValue());         
        }
        }  
        }} catch (SQLException ex) {
        
        
        throw new ServiceLocatorException(ex);
        
        } finally {
        try{
        if(resultSet != null){
        resultSet.close();
        resultSet = null;
        }
        if(statement != null){
        statement.close();
        statement = null;
        }
        if(connection != null){
        connection.close();
        connection = null;
        }
        }catch (SQLException ex) {
        throw new ServiceLocatorException(ex);
        }
        }
         */


        String message = "";
        boolean isUpdated = false;
        //  String queryString ="";
        // System.out.println("first call");
        String data = "";
        //queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ModifiedBy=?,ModifiedDate=?,ReviewName=?,ReviewDate=?,Status=?,TLComments=?,TLRating=?,HRRating=?,HrComments=? where Id=?";
        //if(roleName.equalsIgnoreCase("Employee"))
        // queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ReviewName=?,ReviewDate=?,Status=? where Id=?";
        try {


            JSONObject jb = new JSONObject();
            Iterator entries = eventSpeakerMap.entrySet().iterator();
            while (entries.hasNext()) {
                Entry thisEntry = (Entry) entries.next();
                Object key = thisEntry.getKey();
                Object value = thisEntry.getValue();
                jb.put(String.valueOf(key), String.valueOf(value));
            }


            //jb.put("eventSpeakerMap",eventSpeakerMap);



            //  URL url = new URL("http://172.17.11.251:8080/WebRoot/resources/doJobPost/edit");
            //  URL url = new URL("http://172.17.14.226:8080/WebRoot/resources/doJobPost/edit");
            //  URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doJobPost/edit");
            String serviceUrl = RestRepository.getInstance().getSrviceUrl("getAllPeaopleExceptEventSpeakers");
            URL url = new URL(serviceUrl);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write(jb.toString());
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String s = null;

            while ((s = in.readLine()) != null) {

                data = data + s;
            }

            JSONObject jObject = new JSONObject(data);
            Iterator<?> keys = jObject.keys();


            while (keys.hasNext()) {
                String key = (String) keys.next();
                // subMap = new TreeMap();
                // System.out.println("Sub map-->"+key);
                String speakerName = jObject.getString(key);
                // trackNamesList.add(trackName);
                speakerMapExceptEventSpeakerMap.put(key, speakerName);

            }
            // message = jObject.getString("message");
            //  System.out.println("displaymessage-->" +message);
//primarySpeakerId = jObject.getString("SpeakerId");

            in.close();
            /* connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1,ajaxHandlerAction.getOverlayReviewType());
            preparedStatement.setString(2,ajaxHandlerAction.getOverlayDescription());
            
            
            
            preparedStatement.setString(3,ajaxHandlerAction.getOverlayReviewName());
            preparedStatement.setDate(4, DateUtility.getInstance().getMysqlDate(ajaxHandlerAction.getOverlayReviewDate()));
            preparedStatement.setString(5,ajaxHandlerAction.getReviewStatusOverlay());
            
            preparedStatement.setString(6,ajaxHandlerAction.getReviewId());
            isUpdated = preparedStatement.execute();*/
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        speakerMapExceptEventSpeakerMap = DataSourceDataProvider.getInstance().sortMapByValues(speakerMapExceptEventSpeakerMap);

        return speakerMapExceptEventSpeakerMap;
    }

    /*
     * Update Speaker Team
     * Author : Santosh Kola
     * Date : 08/07/2015
     */
    //public int updateAccountTeamMembers(String[] accountTeamMembers,int accountId,String primaryId ,int rid,HttpSession httpsession) throws ServiceLocatorException {
    public int updateEventSpeakers(String[] eventSpeakers, int eventId, String primaryId, HttpSession httpsession, String objectType) throws ServiceLocatorException {
        int insertedRows = 0;

        String message = "";
        boolean isUpdated = false;

        try {


            JSONObject jb = new JSONObject();
            jb.put("eventId", String.valueOf(eventId));
            if (primaryId == null) {
                primaryId = "";
            }
            jb.put("primaryId", primaryId);

            jb.put("objectType", objectType);
            // System.out.println("eventSpeakers-->"+eventSpeakers);
            jb.put("eventSpeakers", eventSpeakers);




            //   URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doEventPost/addEvent");
            String serviceUrl = RestRepository.getInstance().getSrviceUrl("updateEventSpeakersList");
            URL url = new URL(serviceUrl);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write(jb.toString());
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String s = null;
            String data = "";
            while ((s = in.readLine()) != null) {

                data = data + s;
            }

            JSONObject jObject = new JSONObject(data);

            message = jObject.getString("message");
            insertedRows = 3;


            in.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        /*
        
        Connection connection = null;
        Statement statement = null;
        
        connection = ConnectionProvider.getInstance().getConnection();
        try {
        statement = connection.createStatement();
        
        boolean isDelete = statement.execute("DELETE FROM tblCrmAccountTeam WHERE AccountId="+accountId);
        statement.close();
        statement = null;
        if(!isDelete){
        statement=connection.createStatement();
        if(accountTeamMembers!=null)
        {
        for(int i=0;i<accountTeamMembers.length;i++ ){
        
        if(i==0)
        {
        statement.addBatch("INSERT INTO tblCrmAccountTeam(AccountId, TeamMemberId, SalesFlag, PrimaryFlag) values("
        +accountId+",'"+accountTeamMembers[i]+"',0,1)");
        //ApplicationConstants.AFTER_UPDATEPRIMARYTEAM = accountTeamMembers[i];
        httpsession.setAttribute(ApplicationConstants.AFTER_UPDATEPRIMARYTEAM,accountTeamMembers[i]);
        }else{
        statement.addBatch("INSERT INTO tblCrmAccountTeam(AccountId, TeamMemberId, SalesFlag, PrimaryFlag) values("
        +accountId+",'"+accountTeamMembers[i]+"',0,0)");
        }
        
        }
        }
        int[] insertedRowsArray = statement.executeBatch();
        insertedRows = insertedRowsArray.length;
        
        
        
        }
        
        } catch (SQLException ex) {
        throw new ServiceLocatorException(ex);
        }finally{
        try{
        
        if(statement != null){
        statement.close();
        statement = null;
        }
        if(connection != null){
        connection.close();
        connection = null;
        }
        }catch (SQLException ex) {
        throw new ServiceLocatorException(ex);
        }
        }*/
        return insertedRows;
    }

    public List eventSpeakerDetailsList(String eventId, String objectType) throws ServiceLocatorException {
        List jobsList = null;
        List mainList = null;
        String createdDateFrom;
        String createdDateTo;
        String jobId;
        String title;
        String status;
        Map subMap = null;
        // boolean primaryExist = false;
        int primaryFlag = 0;
        try {

            JSONObject jb = new JSONObject();


            jb.put("eventId", eventId);
            jb.put("objectType", objectType);


            // URL url = new URL("http://localhost:8080/CrunchifyTutorials/api/crunchifyService");
            //URL url = new URL("http://172.17.11.251:8080/WebRoot/resources/doJobPost/search");
            //URL url = new URL("http://172.17.14.226:8080/WebRoot/resources/doJobPost/appliedList");
            // URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doJobPost/appliedList");

            String serviceUrl = RestRepository.getInstance().getSrviceUrl("getPeopleDetailsList");
            URL url = new URL(serviceUrl);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write(jb.toString());
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String s = null;
            String data = "";
            while ((s = in.readLine()) != null) {
                //     System.out.println(s);
                data = data + s;
            }



            //  System.out.println("Data-->"+data);
            JSONObject jObject = new JSONObject(data);


            // System.out.println("jObject-->"+jObject);
            mainList = new ArrayList();
            //------------------

            for (int i = 0; i < jObject.length(); i++) {
                JSONObject subJson = jObject.getJSONObject(String.valueOf(i));
                subMap = new HashMap();

                Iterator<?> subkeys = subJson.keys();
                while (subkeys.hasNext()) {
                    String subkey = (String) subkeys.next();
                    String value = (String) subJson.get(subkey);
                    // subList.add(value);




                    subMap.put(subkey, value);

                }
                mainList.add(subMap);

                //---------------------

                /*        
                
                Iterator<?> keys = jObject.keys();
                
                List subList ;
                
                boolean isEmailExist = true;
                while( keys.hasNext() ){
                String key = (String)keys.next();
                Map subMap = new TreeMap();
                // System.out.println("Sub map-->"+key);
                JSONObject subJson = jObject.getJSONObject(key);
                subList = new ArrayList();
                Iterator<?> subkeys = subJson.keys();
                while( subkeys.hasNext() ){
                String subkey = (String)subkeys.next();
                String value = (String)subJson.get(subkey); 
                // subList.add(value);
                if(subkey.equals("EmailId")){
                
                isEmailExist = DataSourceDataProvider.getInstance().isConsultantEmailExist(value);
                
                System.out.println("isEmailExist-->"+isEmailExist);
                subMap.put("AddFlag",isEmailExist);
                isEmailExist = true;
                }
                subMap.put(subkey, value);
                
                }*/





                // map.put(key, mailJson);

            }


            //  primarySpeakerExist
            //System.out.println("mainList size-->"+mainList.size()) ;

            // System.out.println("\nREST Service Invoked Successfully..");
            in.close();
        } catch (Exception e) {
            System.out.println("\nError while calling REST Service");
            System.out.println(e);
        }
        return mainList;
    }

    public Map getLkpTrackNamesMap() throws ServiceLocatorException {

        List trackNamesList = null;
        Map trackNamesMap = new TreeMap();

        try {



            String serviceUrl = RestRepository.getInstance().getSrviceUrl("trackNamesList");
            URL url = new URL(serviceUrl);
            URLConnection connection = url.openConnection();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String s = null;
            String data = "";
            while ((s = in.readLine()) != null) {
                //     System.out.println(s);
                data = data + s;
            }


            JSONObject jObject = new JSONObject(data);
            trackNamesList = new ArrayList();
            Iterator<?> keys = jObject.keys();


            while (keys.hasNext()) {
                String key = (String) keys.next();
                // subMap = new TreeMap();
                // System.out.println("Sub map-->"+key);
                String trackName = jObject.getString(key);
                //trackNamesList.add(trackName);
                trackNamesMap.put(key, trackName);
            }

trackNamesMap=DataSourceDataProvider.getInstance().sortMapByValues(trackNamesMap);
            conn.disconnect();
        } catch (Exception e) {
            System.out.println("\nError while calling REST Service");
            System.out.println(e);
        }
        //  System.out.println("trackNamesList-->"+trackNamesList);
        return trackNamesMap;
    }

    public List searchPeopleList(MarketingAction marketingAction) throws ServiceLocatorException {
        List jobsList = null;
        List mainList = null;
        String createdDateFrom;
        String createdDateTo;
        String jobId;
        String title;
        String status;
        Map subMap = null;
        // boolean primaryExist = false;
        int primaryFlag = 0;
        try {

            JSONObject jb = new JSONObject();

            if (marketingAction.getName() != null && !"".equals(marketingAction.getName())) {
                jb.put("Name", marketingAction.getName());
            } else {
                jb.put("Name", "none");
            }
            if (marketingAction.getEmail() != null && !"".equals(marketingAction.getEmail())) {
                jb.put("EmailId", marketingAction.getEmail());
            } else {
                jb.put("EmailId", "none");
            }
            // URL url = new URL("http://localhost:8080/CrunchifyTutorials/api/crunchifyService");
            //URL url = new URL("http://172.17.11.251:8080/WebRoot/resources/doJobPost/search");
            //URL url = new URL("http://172.17.14.226:8080/WebRoot/resources/doJobPost/appliedList");
            // URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doJobPost/appliedList");

            String serviceUrl = RestRepository.getInstance().getSrviceUrl("getPeopleList");
            URL url = new URL(serviceUrl);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write(jb.toString());
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String s = null;
            String data = "";
            while ((s = in.readLine()) != null) {
                //     System.out.println(s);
                data = data + s;
            }



            //  System.out.println("Data-->"+data);
            JSONObject jObject = new JSONObject(data);


            // System.out.println("jObject-->"+jObject);
            mainList = new ArrayList();
            //------------------

            for (int i = 0; i < jObject.length(); i++) {
                JSONObject subJson = jObject.getJSONObject(String.valueOf(i));
                subMap = new HashMap();

                Iterator<?> subkeys = subJson.keys();
                while (subkeys.hasNext()) {
                    String subkey = (String) subkeys.next();
                    String value = (String) subJson.get(subkey);
                    // subList.add(value);




                    subMap.put(subkey, value);

                }
                mainList.add(subMap);

                //---------------------

                /*        
                
                Iterator<?> keys = jObject.keys();
                
                List subList ;
                
                boolean isEmailExist = true;
                while( keys.hasNext() ){
                String key = (String)keys.next();
                Map subMap = new TreeMap();
                // System.out.println("Sub map-->"+key);
                JSONObject subJson = jObject.getJSONObject(key);
                subList = new ArrayList();
                Iterator<?> subkeys = subJson.keys();
                while( subkeys.hasNext() ){
                String subkey = (String)subkeys.next();
                String value = (String)subJson.get(subkey); 
                // subList.add(value);
                if(subkey.equals("EmailId")){
                
                isEmailExist = DataSourceDataProvider.getInstance().isConsultantEmailExist(value);
                
                System.out.println("isEmailExist-->"+isEmailExist);
                subMap.put("AddFlag",isEmailExist);
                isEmailExist = true;
                }
                subMap.put(subkey, value);
                
                }*/





                // map.put(key, mailJson);

            }


            //  primarySpeakerExist
            //System.out.println("mainList size-->"+mainList.size()) ;

            // System.out.println("\nREST Service Invoked Successfully..");
            in.close();
        } catch (Exception e) {
            System.out.println("\nError while calling REST Service");
            System.out.println(e);
        }
        return mainList;
    }

    public boolean deleteInvestment(int id) throws ServiceLocatorException {
        //  System.out.println("----id----" + id);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        //  ResultSet resultSet = null;
        boolean isDeleted = false;
        String queryString = "UPDATE tblInvestments SET STATUS='InActive' WHERE Inv_Id=?";
        System.out.println("queryString for Delete-->" + queryString);
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, id);
            isDeleted = preparedStatement.execute();
        } catch (SQLException se) {
            throw new ServiceLocatorException(se);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }
        return isDeleted;
    }

    public String generateInvestmentXls(String queryString) throws ServiceLocatorException {
        String filePath = "";
        StringBuffer sb = null;

        Connection connection = null;
        /**
         * preStmt,preStmtTemp are reference variable for PreparedStatement .
         */
        PreparedStatement preStmt = null;

        /**
         * The queryString is useful to get queryString result to the particular
         * jsp page
         */
        /**
         * The statement is useful to execute the above queryString
         */
        ResultSet resultSet = null;
        HashMap map = null;
        double totalAmount = 0.0;
        double totalOpprtunity = 0.0;
        double floortotalsum = 0.0;
        String generatedPath = "";
        List finalList = new ArrayList();
        try {
            generatedPath = com.mss.mirage.util.Properties.getProperty("Marketing.Investment.Path");
            File file = new File(generatedPath);
            if (!file.exists()) {
                file.mkdirs();
            }

            FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath() + "/Investment.xls");
            connection = ConnectionProvider.getInstance().getConnection();
            String query = null;
            if (!"".equals(queryString)) {
                query = queryString;
            } else {
                query = "SELECT * from vwInvestments WHERE STATUS='Active' ORDER BY createdDate DESC";
            }
            String reportToName = "";
            List teamList = null;
            int j = 1;
            //  System.out.println("query...."+query);
            preStmt = connection.prepareStatement(query);
            resultSet = preStmt.executeQuery();
            while (resultSet.next()) {
                String InvestmentName = resultSet.getString("Inv_Name");
                String TotalExpenses = resultSet.getString("TotalExpenses");
                String StartDate = resultSet.getString("StartDate");
                String EndDate = resultSet.getString("EndDate");
                String Location = resultSet.getString("Location");
                String InvestmentType = resultSet.getString("InvestmentType");
                String TotalOpprtunity = resultSet.getString("TotalOpprtunity");
                totalAmount = totalAmount + resultSet.getDouble("TotalExpenses");
                totalOpprtunity = totalOpprtunity + resultSet.getDouble("TotalOpprtunity");
                map = new HashMap();
                map.put("SNO", String.valueOf(j));
                map.put("InvestmentName", InvestmentName);
                map.put("TotalExpenses", TotalExpenses);
                map.put("StartDate", StartDate);
                map.put("EndDate", EndDate);
                map.put("Location", Location);
                if ("S".equalsIgnoreCase(InvestmentType)) {
                    map.put("InvestmentType", "Lead Source");
                } else if ("P".equalsIgnoreCase(InvestmentType)) {
                    map.put("InvestmentType", "Lead Pass");
                }
                map.put("TotalOpprtunity", TotalOpprtunity);
                map.put("Sum", totalAmount);
                map.put("SumOpp", totalOpprtunity);

                finalList.add(map);
                j++;

            }


            if (finalList.size() > 0) {
                filePath = file.getAbsolutePath() + "/Investment.xls";
                HSSFWorkbook hssfworkbook = new HSSFWorkbook();
                HSSFSheet sheet = hssfworkbook.createSheet("Investment Sheet");

                HSSFFont timesBoldFont1 = hssfworkbook.createFont();
                timesBoldFont1.setFontHeightInPoints((short) 13);
                timesBoldFont1.setColor(HSSFColor.BLACK.index);
                timesBoldFont1.setFontName("Arial");

                HSSFCellStyle cellColor = hssfworkbook.createCellStyle();
                cellColor.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
                cellColor.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                cellColor.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellColor.setBorderTop((short) 1); // single line border
                cellColor.setBorderBottom((short) 1); // single line border
                cellColor.setFont(timesBoldFont1);

                HSSFCellStyle cellColor1 = hssfworkbook.createCellStyle();

                cellColor1.setFillForegroundColor(HSSFColor.WHITE.index);
                cellColor1.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                cellColor1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellColor1.setBorderTop((short) 1); // single line border
                cellColor1.setBorderBottom((short) 1); // single line border
                cellColor1.setFont(timesBoldFont1);


                HSSFCellStyle cs = hssfworkbook.createCellStyle();

                HSSFCellStyle headercs = hssfworkbook.createCellStyle();
                headercs.setFillForegroundColor(HSSFColor.BLUE.index);
                headercs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                headercs.setBorderTop((short) 1); // single line border
                headercs.setBorderBottom((short) 1); // single line border
                // cs.setFont(timesBoldFont1);

                HSSFFont timesBoldFont = hssfworkbook.createFont();
                timesBoldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                timesBoldFont.setFontHeightInPoints((short) 13);
                timesBoldFont.setColor(HSSFColor.WHITE.index);
                timesBoldFont.setFontName("Calibri");
                headercs.setFont(timesBoldFont);
                // cs.setFont(timesBoldFont);
                HSSFFont footerFont = hssfworkbook.createFont();
                footerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                timesBoldFont.setFontHeightInPoints((short) 13);
                footerFont.setFontName("Calibri");

                HSSFCellStyle footercs = hssfworkbook.createCellStyle();
                footercs.setFont(footerFont);


                HSSFDataFormat df = hssfworkbook.createDataFormat();
                HSSFRow row = sheet.createRow((short) 0);
                HSSFCell cell = row.createCell((short) 0);

                HSSFCell cell1 = row.createCell((short) 1);

                HSSFCell cell2 = row.createCell((short) 2);
                HSSFCell cell3 = row.createCell((short) 3);

                HSSFCell cell4 = row.createCell((short) 4);
                HSSFCell cell5 = row.createCell((short) 5);
                HSSFCell cell6 = row.createCell((short) 6);
                HSSFCell cell7 = row.createCell((short) 7);

                cell.setCellValue("SNO");
                cell1.setCellValue("InvestmentName");
                cell2.setCellValue("TotalExpenses");
                cell3.setCellValue("StartDate");
                cell4.setCellValue("EndDate");
                cell5.setCellValue("Location");
                cell6.setCellValue("InvestmentType");
                cell7.setCellValue("TotalOpprtunity");





                cell.setCellStyle(headercs);
                cell1.setCellStyle(headercs);
                cell2.setCellStyle(headercs);
                cell3.setCellStyle(headercs);
                cell4.setCellStyle(headercs);
                cell5.setCellStyle(headercs);
                cell6.setCellStyle(headercs);
                cell7.setCellStyle(headercs);

                int count = 1;

                if (finalList.size() > 0) {
                    Map stateHistorylMap = null;
                    for (int i = 0; i < finalList.size(); i++) {
                        stateHistorylMap = (Map) finalList.get(i);
                        row = sheet.createRow((short) count++);
                        cell = row.createCell((short) 0);

                        cell1 = row.createCell((short) 1);
                        cell2 = row.createCell((short) 2);
                        cell3 = row.createCell((short) 3);
                        cell4 = row.createCell((short) 4);
                        cell5 = row.createCell((short) 5);
                        cell6 = row.createCell((short) 6);
                        cell7 = row.createCell((short) 7);



                        cell.setCellValue((String) stateHistorylMap.get("SNO"));
                        cell1.setCellValue((String) stateHistorylMap.get("InvestmentName"));
                        HSSFCellStyle css1 = hssfworkbook.createCellStyle();
                        HSSFCellStyle css2 = hssfworkbook.createCellStyle();
                        css1.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
                        css1.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                        css1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                        css1.setBorderTop((short) 1); // single line border
                        css1.setBorderBottom((short) 1); // single line border
                        css1.setFont(timesBoldFont1);

                        HSSFDataFormat df1 = hssfworkbook.createDataFormat();
                        css1.setDataFormat(df1.getFormat("#,##0.0"));
                        css2.setDataFormat(df1.getFormat("#,##0.0"));
                        css2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                        css2.setFont(timesBoldFont1);
                        cell2.setCellValue(Convert.toDouble(stateHistorylMap.get("TotalExpenses")));
                        cell3.setCellValue((String) stateHistorylMap.get("StartDate"));
                        cell4.setCellValue((String) stateHistorylMap.get("EndDate"));
                        cell5.setCellValue((String) stateHistorylMap.get("Location"));
                        cell6.setCellValue((String) stateHistorylMap.get("InvestmentType"));
                        cell7.setCellValue((String) stateHistorylMap.get("TotalOpprtunity"));



                        if (count % 2 == 0) {
                            cell.setCellStyle(cellColor1);
                            cell1.setCellStyle(cellColor1);
                            cell2.setCellStyle(css2);
                            cell3.setCellStyle(cellColor1);
                            cell4.setCellStyle(cellColor1);
                            cell5.setCellStyle(cellColor1);
                            cell6.setCellStyle(cellColor1);
                            cell7.setCellStyle(cellColor1);

                        } else {
                            cell.setCellStyle(cellColor);
                            cell1.setCellStyle(cellColor);
                            cell2.setCellStyle(css1);
                            cell3.setCellStyle(cellColor);
                            cell4.setCellStyle(cellColor);
                            cell5.setCellStyle(cellColor);
                            cell6.setCellStyle(cellColor);
                            cell7.setCellStyle(cellColor);
                        }
                    }
                    row = sheet.createRow((short) count++);
                    cell = row.createCell((short) 0);

                    cell1 = row.createCell((short) 1);
                    cell2 = row.createCell((short) 2);
                    cell3 = row.createCell((short) 3);
                    cell4 = row.createCell((short) 4);
                    cell5 = row.createCell((short) 5);
                    cell6 = row.createCell((short) 6);
                    cell7 = row.createCell((short) 7);
                    cell.setCellValue("");
                    cell7.setCellValue("");

                    cell.setCellStyle(footercs);
                    cell1.setCellStyle(footercs);
                    cell2.setCellStyle(footercs);
                    cell3.setCellStyle(footercs);

                    cell4.setCellStyle(footercs);
                    cell5.setCellStyle(footercs);
                    cell6.setCellStyle(footercs);
                    cell7.setCellStyle(footercs);
                }
                HSSFCellStyle totalSum = hssfworkbook.createCellStyle();
                totalSum.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
                totalSum.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                totalSum.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                totalSum.setBorderTop((short) 1); // single line border
                totalSum.setBorderBottom((short) 1); // single line border
                totalSum.setFont(timesBoldFont1);
                HSSFCellStyle totalSum1 = hssfworkbook.createCellStyle();
                totalSum1.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
                totalSum1.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
                totalSum1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                totalSum1.setBorderTop((short) 1); // single line border
                totalSum1.setBorderBottom((short) 1); // single line border
                totalSum1.setFont(timesBoldFont1);

                HSSFDataFormat totalSumdf1 = hssfworkbook.createDataFormat();

                totalSum.setDataFormat((short) 7);
                HSSFCellStyle test = hssfworkbook.createCellStyle();
                HSSFDataFormat testdf = hssfworkbook.createDataFormat();

                sheet.autoSizeColumn((int) 0);
                sheet.autoSizeColumn((int) 3);
                sheet.autoSizeColumn((int) 4);
                sheet.setColumnWidth(1, 50 * 256);
                sheet.setColumnWidth(2, 35 * 256);
                sheet.setColumnWidth(5, 25 * 256);
                sheet.setColumnWidth(6, 25 * 256);
                sheet.setColumnWidth(7, 25 * 256);
                BigDecimal bb, bc, cc, cd;
                bb = new BigDecimal(totalAmount);
                bc = bb.setScale(2, RoundingMode.CEILING);
                cc = new BigDecimal(totalOpprtunity);
                cd = cc.setScale(2, RoundingMode.CEILING);
                totalSum.setDataFormat(testdf.getFormat("#,##0.0"));

                cell.setCellStyle(totalSum);

                cell1.setCellValue("Sum ");
                cell1.setCellStyle(totalSum1);
                cell2.setCellValue(bc.longValue());

                cell2.setCellStyle(totalSum);
                cell3.setCellStyle(totalSum);
                cell4.setCellStyle(totalSum);
                cell5.setCellStyle(totalSum);

                cell6.setCellStyle(totalSum);
                cell7.setCellValue(cd.longValue());

                cell7.setCellStyle(totalSum);

                hssfworkbook.write(fileOut);
                fileOut.flush();
                fileOut.close();


            }


        } catch (FileNotFoundException fne) {

            fne.printStackTrace();
        } catch (IOException ioe) {

            ioe.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (preStmt != null) {
                    preStmt.close();
                    preStmt = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (Exception se) {
                se.printStackTrace();
            }
        }

        return filePath;


    }

    public String getInvestmentAttachments(int investmentId) throws ServiceLocatorException {
      //  System.out.println("getInvestmentAttachments");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String filepath = "";
        String queryString = "";
        //  System.out.println("ModifiedDate------>"+createdDate);

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "select  AttachmentLocation from tblInvestments where Inv_Id=" + investmentId;
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
               // System.out.println("in while");
                //System.out.println("resultSet.getString(\"AttachmentLocation\")-->"+resultSet.getString("AttachmentLocation"));
                filepath = resultSet.getString("AttachmentLocation");
            }
        } catch (Exception ex) {
        }

        return filepath;
    }

    public int addCampaign(MarketingAction marketingAction) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int isInserted = 0;

        String queryString = "INSERT INTO tblCrmCampaign(CampaignName,CampaignDesc,CampaignStartDate,CampaignEndDate,Status,CreatedBy,CreatedDate,SendDates) VALUES(?,?,?,?,?,?,?,?)";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, marketingAction.getCampaignName());
            preparedStatement.setString(2, marketingAction.getDescription());
            preparedStatement.setDate(3, DateUtility.getInstance().getMysqlDate(DateUtility.getInstance().getCurrentMySqlDate()));
            preparedStatement.setDate(4, DateUtility.getInstance().getMysqlDate(marketingAction.getCampaignEndDate()));
            preparedStatement.setString(5, marketingAction.getCampaignStatus());
            preparedStatement.setString(6, marketingAction.getCreatedById());
            preparedStatement.setTimestamp(7, DateUtility.getInstance().getCurrentMySqlDateTime());
            preparedStatement.setString(8, marketingAction.getSendDates());
            isInserted = preparedStatement.executeUpdate();
            if (isInserted > 0) {
            }
        } catch (SQLException se) {
            throw new ServiceLocatorException(se);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }

        return isInserted;
    }

    public String getCampaignDetails(MarketingAction marketingAction) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String activityDetails = "";
        int i = 0;
        String queryString = "";
        JSONObject subJson = null;
        try {
            queryString = "SELECT * FROM tblCrmCampaign WHERE Id='" + marketingAction.getCampaignId() + "'";
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery(queryString);
            if (resultSet.next()) {
                marketingAction.setCampaignName(resultSet.getString("CampaignName"));
                marketingAction.setCampaignStartDate(DateUtility.getInstance().convertToviewFormat(resultSet.getString("CampaignStartDate")));
                marketingAction.setCampaignEndDate(DateUtility.getInstance().convertToviewFormat(resultSet.getString("CampaignEndDate")));
                marketingAction.setCampaignStatus(resultSet.getString("STATUS"));
                marketingAction.setDescription(resultSet.getString("CampaignDesc"));
                marketingAction.setSendDates(resultSet.getString("SendDates"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
            }
        }
        return null;
    }

    public int updateCampaign(MarketingAction marketingAction) throws ServiceLocatorException {
        int updatedRows = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String queryString = "UPDATE tblCrmCampaign SET CampaignName=?,CampaignDesc=?,CampaignEndDate=?,Status=?,CreatedBy=?,SendDates=? WHERE Id='" + marketingAction.getCampaignId() + "'";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);

            preparedStatement.setString(1, marketingAction.getCampaignName());
            preparedStatement.setString(2, marketingAction.getDescription());
            preparedStatement.setDate(3, DateUtility.getInstance().getMysqlDate(marketingAction.getCampaignEndDate()));
            preparedStatement.setString(4, marketingAction.getCampaignStatus());
            preparedStatement.setString(5, marketingAction.getCreatedById());
            preparedStatement.setString(6, marketingAction.getSendDates());
            updatedRows = preparedStatement.executeUpdate();

        } catch (SQLException se) {
            throw new ServiceLocatorException(se);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }
        return updatedRows;
    }

    public String getCampaignContactsExcel(HttpServletRequest httpServletRequest, MarketingAction marketingAction) throws ServiceLocatorException {

        // System.out.println("getCampaignContactsExcel");
        String filePath = "";
        StringBuffer sb = null;

        Connection connection = null;
        /**
         * preStmt,preStmtTemp are reference variable for PreparedStatement .
         */
        PreparedStatement preStmt = null;

        /**
         * The queryString is useful to get queryString result to the particular
         * jsp page
         */
        String queryString = "";
        /**
         * The statement is useful to execute the above queryString
         */
        ResultSet resultSet = null;
        HashMap map = null;

        List finalList = new ArrayList();
        String campaignTitle = "";
        try {
            PreparedStatement preparedStatement = null;
            Statement statement = null;
            ResultSet resultSet1 = null;
            String email = "";
            String phone = "";
            String CompanyName = "";
            String contactName = "";
            int z = 0;
            int n = 1;
            Map<Integer, String> contactIdMap = new TreeMap<Integer, String>();
            // queryString = "SELECT Id,ContactId FROM tblCrmActivity WHERE CampaignId=" + campaignId + " ORDER BY CreatedDate DESC";
            String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            String roleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            campaignTitle = DataSourceDataProvider.getInstance().getCampaignNameByCampaignId(marketingAction.getCampaignId());
            // queryString = "SELECT Id,Title,CreatedBy,CurrStatus FROM tblCrmLeads WHERE AccountId=" + accId;
            // queryString = "SELECT Id,ContactId FROM tblCrmActivity WHERE CampaignId="+campaignId+" ORDER BY CreatedDate DESC";
            queryString = "SELECT Id,ContactId FROM tblCrmActivity WHERE CampaignId=" + marketingAction.getCampaignId();

            if (marketingAction.getContactStartDate() != null && !"".equalsIgnoreCase(marketingAction.getContactStartDate())) {
                queryString = queryString + " AND (DATE(CreatedDate))>='" + DateUtility.getInstance().getMysqlDate(marketingAction.getContactStartDate()) + "' ";
            }
            if (marketingAction.getContactEndDate() != null && !"".equalsIgnoreCase(marketingAction.getContactEndDate())) {
                queryString = queryString + " AND (DATE(CreatedDate))<='" + DateUtility.getInstance().getMysqlDate(marketingAction.getContactEndDate()) + "' ";
            }
            if (roleName.equals("Sales")) {
                queryString = queryString + " AND CreatedById='" + loginId + "' ";
            }
            queryString = queryString + " ORDER BY CreatedDate DESC";

            // String contactQuery = "SELECT Id, CONCAT(FirstName,' ',MiddleName,'.',LastName) AS ContactName,Email1,CellPhone FROM tblCrmContact WHERE Id=?";
            String contactQuery = "SELECT tblCrmContact.id AS contactId,tblCrmAccount.Id AS accountId,"
                    + "CONCAT(tblCrmContact.FirstName,'.',tblCrmContact.LastName) AS ContactName,"
                    + " tblCrmContact.Email1 AS Email1, tblCrmAccount.NAME AS CompanyName,tblCrmContact.CellPhone AS CellPhone"
                    + "  FROM tblCrmContact LEFT JOIN  tblCrmAccount ON(tblCrmContact.AccountId=tblCrmAccount.Id) WHERE tblCrmContact.id=?";
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                z++;
                int Id = resultSet.getInt("Id");
                String ContactId = resultSet.getString("ContactId");
                contactIdMap.put(Id, ContactId);
                //totalStream=totalStream+i+"|"+createdDate+"|"+actType+"|"+description+"|"+comments+"|"+assignedToId+"|"+contactName+"|"+datedue+"|"+actid+"|"+"^";
                //  totalActivities=totalActivities+count;
            }
            preparedStatement = connection.prepareStatement(contactQuery);

            for (Map.Entry<Integer, String> entry : contactIdMap.entrySet()) {
                Integer key = entry.getKey();
                String value = entry.getValue();
                String[] parts = value.split("\\,");
                if (parts.length > 0 && !"".equals(value)) {
                    for (int j = 0; j < parts.length; j++) {
                        preparedStatement.setInt(1, Integer.parseInt(parts[j]));
                        resultSet1 = preparedStatement.executeQuery();

                        while (resultSet1.next()) {
                            CompanyName = resultSet1.getString("CompanyName");
                            contactName = resultSet1.getString("ContactName");
                            email = resultSet1.getString("Email1");
                            phone = resultSet1.getString("CellPhone");
                            map = new HashMap();
                            map.put("SNO", String.valueOf(n));
                            map.put("contactName", contactName);
                            map.put("CompanyName", CompanyName);
                            map.put("email", email);
                            // map.put("phone", phone);
                            // System.out.println(map);
                            finalList.add(map);
                            n++;
                        }

                    }
                }



            }
            //  System.out.println("map" + finalList.size());
            File file = new File(Properties.getProperty("Emp.Qmeet.Path"));

            if (!file.exists()) {
                file.mkdirs();
            }

            FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath() + File.separator + "Contacts.xls");
            filePath = file.getAbsolutePath() + File.separator + "Contacts.xls";
            HSSFRow row1;
            HSSFWorkbook workbook = new HSSFWorkbook();
            System.out.println("filePath " + filePath);
            HSSFSheet worksheet = workbook.createSheet("Campaign Contacts");
            for (int i = 0; i < 4; i++) {
                worksheet.setColumnWidth(i, 10 * 256);

            }

            HSSFFont timesBoldFont = workbook.createFont();
            timesBoldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            timesBoldFont.setColor(HSSFColor.WHITE.index);

            timesBoldFont.setFontName("Calibri");
            HSSFCellStyle headercs = workbook.createCellStyle();
            headercs.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
            headercs.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            headercs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            headercs.setBorderTop((short) 1); // single line border
            headercs.setBorderBottom((short) 1); // single line border
            headercs.setFont(timesBoldFont);

            HSSFFont footerFont = workbook.createFont();
            footerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            footerFont.setFontName("Calibri");





            // HSSFRow row1;
            // LogisticsDocBean logisticsDocBean = null;
            // index from 0,0... cell A1 is cell(0,0)

            // if(list.size()!=0){//
            //System.out.println("list size-->"+list.size());




            HSSFFont font4 = workbook.createFont();
            font4.setColor(HSSFColor.WHITE.index);
            font4.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font4.setFontHeightInPoints((short) 14);
            font4.setFontName("Calibri");

            HSSFCellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
            cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cellStyle.setFont(font4);


            HSSFFont font1 = workbook.createFont();
            //font1.setColor(HSSFColor.WHITE.index);
            font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font1.setFontHeightInPoints((short) 14);
            font1.setFontName("Calibri");
            HSSFCellStyle cs = workbook.createCellStyle();
            cs.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            cs.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cs.setBorderTop((short) 1); // single line border
            cs.setBorderBottom((short) 1); // single line border
            cs.setFont(font1);


            HSSFCellStyle cs1 = workbook.createCellStyle();
            cs1.setFillForegroundColor(HSSFColor.ROYAL_BLUE.index);
            cs1.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cs1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cs1.setFont(font4);
            cs1.setBorderTop((short) 1); // single line border
            cs1.setBorderBottom((short) 1); // single line border



            HSSFCellStyle cs2 = workbook.createCellStyle();

            cs2.setFillForegroundColor(HSSFColor.WHITE.index);
            cs2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cs2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cs2.setBorderTop((short) 1); // single line border
            cs2.setBorderBottom((short) 1); // single line border
            cs2.setFont(font1);


            HSSFFont font3 = workbook.createFont();
            //font1.setColor(HSSFColor.WHITE.index);
            //  font3.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font3.setFontHeightInPoints((short) 14);
            font3.setFontName("Calibri");

            HSSFCellStyle cs3 = workbook.createCellStyle();
            cs3.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            cs3.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cs3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cs3.setFont(font3);
            cs3.setBorderTop((short) 1); // single line border
            cs3.setBorderBottom((short) 1); // single line border



            HSSFCellStyle cs4 = workbook.createCellStyle();

            cs4.setFillForegroundColor(HSSFColor.WHITE.index);
            cs4.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cs4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cs4.setBorderTop((short) 1); // single line border
            cs4.setBorderBottom((short) 1); // single line border
            cs4.setFont(font3);



            //start	


            row1 = worksheet.createRow((short) 0);

            HSSFCell cellpo0 = row1.createCell((short) 0);
            // cellpo0.setCellValue("Purchase Order");
            HSSFCell cellpo1 = row1.createCell((short) 1);
            HSSFCell cellpo2 = row1.createCell((short) 2);
            // cellpo2.setCellValue("Created Date");
            HSSFCell cellpo3 = row1.createCell((short) 3);
            //cellpo3.setCellValue((date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+date.getDate());

            HSSFCell cellpo4 = row1.createCell((short) 4);
            HSSFCell cellpo5 = row1.createCell((short) 5);
            HSSFCell cellpo6 = row1.createCell((short) 6);
            HSSFCell cellpo7 = row1.createCell((short) 7);
            HSSFCell cellpo8 = row1.createCell((short) 8);
            HSSFCell cellpo9 = row1.createCell((short) 9);
            HSSFCell cellpo10 = row1.createCell((short) 10);
            HSSFCell cellpo11 = row1.createCell((short) 11);
            row1 = worksheet.createRow((short) 0);
            Cell cell[] = new Cell[4];
            for (int i = 0; i < 4; i++) {
                cell[i] = row1.createCell((short) i);
            }

            // cell.setCellValue("Logistics Document :-Created Date : "+(date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+date.getDate());
            cell[0].setCellValue("Contacts Details :" + campaignTitle);
            HSSFCellStyle cellStyleHead = workbook.createCellStyle();
            cellStyleHead.setFont(timesBoldFont);
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            cellStyle.setFillBackgroundColor(HSSFColor.PALE_BLUE.index);
            cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cell[0].setCellStyle(cellStyle);


            worksheet.addMergedRegion(CellRangeAddress.valueOf("A1:" + "D2"));

            //sno
            row1 = worksheet.createRow((short) 2);
            cell[0] = row1.createCell((short) 0);
            cell[0].setCellValue("SNo");
            // cellStyleHead.setFont(font4);
            cellStyleHead.setFont(timesBoldFont);
            //  cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            //cellStyleHead.setFillBackgroundColor(HSSFColor.PALE_BLUE.index);
            // cellStyleHead.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            // cell[0].setCellStyle(cellStyleHead);
            cell[0].setCellStyle(cs1);

            worksheet.addMergedRegion(CellRangeAddress.valueOf("A3:A4"));

            cell[0] = row1.createCell((short) 1);
            cell[0].setCellValue("Contact Name");
            cellStyleHead.setFont(timesBoldFont);
            cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            //cell[0].setCellStyle(cellStyleHead);
            cell[0].setCellStyle(cs1);
            worksheet.addMergedRegion(CellRangeAddress.valueOf("B3:B4"));

            cell[0] = row1.createCell((short) 2);
            cell[0].setCellValue("Company Name");
            cellStyleHead.setFont(timesBoldFont);
            cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            //cell[0].setCellStyle(cellStyleHead);
            cell[0].setCellStyle(cs1);
            worksheet.addMergedRegion(CellRangeAddress.valueOf("C3:C4"));

            cell[0] = row1.createCell((short) 3);
            cell[0].setCellValue("E-Mail");
            cellStyleHead.setFont(timesBoldFont);
            cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            // cell[0].setCellStyle(cellStyleHead);
            cell[0].setCellStyle(cs1);
            worksheet.addMergedRegion(CellRangeAddress.valueOf("D3:D4"));

//            cell[0] = row1.createCell((short) 4);
//            cell[0].setCellValue("Phone");
//            cellStyleHead.setFont(timesBoldFont);
//            cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
//            //  cell[0].setCellStyle(cellStyleHead);
//            cell[0].setCellStyle(cs1);
//            worksheet.addMergedRegion(CellRangeAddress.valueOf("E3:E4"));

            int count = 4;

            int len = finalList.size();
            if (finalList.size() > 0) {

                for (int k = 0; k < finalList.size(); k++) {

                    java.util.Map subList = (java.util.Map) finalList.get(k);
                    row1 = worksheet.createRow((short) count++);
                    for (int j = 0; j < 4; j++) {
                        cell[j] = row1.createCell((short) j);
                    }
                    //System.out.println("subList-->"+subList);
                    cell[0].setCellValue((String) subList.get("SNO"));
                    cell[1].setCellValue((String) subList.get("contactName"));
                    cell[2].setCellValue((String) subList.get("CompanyName"));
                    cell[3].setCellValue((String) subList.get("email").toString());
                    // cell[4].setCellValue((String) subList.get("phone").toString());


                    for (int h = 0; h < 4; h++) {
                        if (count % 2 == 0) {
                            cell[h].setCellStyle(cs1);
                        } else {
                            cell[h].setCellStyle(cs);
                        }

                    }
                }
                // 
                for (int i = 1; i < 4; i++) {

                    worksheet.setColumnWidth(i, 40 * 256);



                    //
                }
                workbook.write(fileOut);
                fileOut.flush();
                fileOut.close();


            }

        } catch (FileNotFoundException fne) {

            fne.printStackTrace();
        } catch (IOException ioe) {

            ioe.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (preStmt != null) {
                    preStmt.close();
                    preStmt = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (Exception se) {
                se.printStackTrace();
            }
        }

        return filePath;


    }

    public String doAddLeadsDetails(MarketingAction marketingAction) throws ServiceLocatorException {

        Connection connection = null;
        // PreparedStatement preparedStatement = null;
        CallableStatement callableStatement = null;
        String queryString;
        String isInserted = "";
        String resultMessage = "";

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            //  queryString = "INSERT INTO tblCrmLeads(Title,InvestmentId,Description,Contacts,CreatedBy,CreatedDate,ExpiryDate,AccountId,CurrStatus,PassedBy1,Commenst1,NextSteps1,PassedBy2,Commenst2,NextSteps2,PassedBy3,Commenst3,NextSteps3) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            callableStatement = connection.prepareCall("{call spLeadAdding(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            //preparedStatement = connection.prepareStatement(queryString,Statement.RETURN_GENERATED_KEYS);

            callableStatement.setString(1, marketingAction.getTitle());
            callableStatement.setInt(2, marketingAction.getInvestmentId());
            callableStatement.setString(3, marketingAction.getDescription());
            callableStatement.setString(4, marketingAction.getContactsHidden());
            // System.out.println("marketingAction.getContactsHidden()-->"+marketingAction.getContactsHidden());
            callableStatement.setString(5, marketingAction.getCreatedBy());

            callableStatement.setTimestamp(6, marketingAction.getCreatedDate());
            callableStatement.setTimestamp(7, marketingAction.getExpiryDate());
            callableStatement.setInt(8, marketingAction.getAccountId());
            callableStatement.setString(9, marketingAction.getStatus());
            callableStatement.setString(10, marketingAction.getPassedBy1hidden());
            callableStatement.setString(11, marketingAction.getComments1());
            callableStatement.setString(12, marketingAction.getNextSteps1());
            callableStatement.setString(13, marketingAction.getPassedBy2hidden());
            callableStatement.setString(14, marketingAction.getComments2());
            callableStatement.setString(15, marketingAction.getNextSteps2());
            callableStatement.setString(16, marketingAction.getPassedBy3hidden());
            callableStatement.setString(17, marketingAction.getComments3());
            callableStatement.setString(18, marketingAction.getNextSteps3());
            callableStatement.setString(19, marketingAction.getInvestmentType());
            callableStatement.setString(20,marketingAction.getPriority());


            callableStatement.registerOutParameter(21, java.sql.Types.VARCHAR);
            callableStatement.executeUpdate();
            // System.out.println("Before"+callableStatement.getString(3));
            isInserted = callableStatement.getString(21);
            if (isInserted.equalsIgnoreCase("success")) {

                if ("P".equalsIgnoreCase(marketingAction.getInvestmentType().trim())) {
                    if (Properties.getProperty("Mail.Flag").equals("1")) {
                        String accountName = DataSourceDataProvider.getInstance().getAccountName(marketingAction.getAccountId());
                        String contactName = "";
                        StringTokenizer str = new StringTokenizer(marketingAction.getContactsHidden(), ",");
                        while (str.hasMoreTokens()) {
                            contactName = contactName + DataSourceDataProvider.getInstance().getContactName(Integer.parseInt(str.nextToken())) + ",";
                        }
                        String To = "";
                        if (!"".equals(marketingAction.getPassedBy1hidden())) {
                            To = To + DataSourceDataProvider.getInstance().getEmailIdForLoginId(marketingAction.getPassedBy1hidden());
                        }
                        if (!"".equals(marketingAction.getPassedBy2hidden())) {
                            To = To + "," + DataSourceDataProvider.getInstance().getEmailIdForLoginId(marketingAction.getPassedBy2hidden());
                        }
                        if (!"".equals(marketingAction.getPassedBy3hidden())) {
                            To = To + "," + DataSourceDataProvider.getInstance().getEmailIdForLoginId(marketingAction.getPassedBy3hidden());
                        }
                        String CreatedByEmail = DataSourceDataProvider.getInstance().getEmailIdForLoginId(marketingAction.getCreatedBy());
                        if (marketingAction.getReportsTo() != null && !"".equals(marketingAction.getReportsTo())) {
                            CreatedByEmail = CreatedByEmail + "," + DataSourceDataProvider.getInstance().getEmailIdForLoginId(marketingAction.getReportsTo());
                            String higherReporting =DataSourceDataProvider.getInstance().getReportsTOOneLevel(marketingAction.getReportsTo());
                        if (higherReporting != null && !"".equals(higherReporting)) {
                            CreatedByEmail = CreatedByEmail + "," + DataSourceDataProvider.getInstance().getEmailIdForLoginId(higherReporting);
                        }
                        }


                        String accountTeamMemberEmails = DataSourceDataProvider.getInstance().accountTeamMemberEmails(marketingAction.getAccountId());
                        // System.out.println(contactName.substring(0, contactName.length()-1));
                        String invName = DataSourceDataProvider.getInstance().getInvestmentNamesById(marketingAction.getInvestmentId());
                        MailManager.sendAddingLeadsInfo(To, marketingAction.getTitle(), accountName, contactName.substring(0, contactName.length() - 1), CreatedByEmail, invName, marketingAction.getPassedBy1hidden(), marketingAction.getPassedBy2hidden(), marketingAction.getPassedBy3hidden(), accountTeamMemberEmails, marketingAction.getComments1(), marketingAction.getComments2(), marketingAction.getComments3(), marketingAction.getNextSteps1(), marketingAction.getNextSteps2(), marketingAction.getNextSteps3(), marketingAction.getCreatedDate().toString(), marketingAction.getCreatedBy());


                    }
                }


                resultMessage = "<font style='color:green;font-size:15px;'>Lead Details Added Successfully.</font>";
            } else {
                resultMessage = "<font style='color:red;font-size:15px;'>Please Try Again.</font>";
            }

        } catch (SQLException se) {

            throw new ServiceLocatorException(se);
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (callableStatement != null) {
                    callableStatement.close();
                    callableStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }
        // System.out.println("resultMessage"+resultMessage);
        return resultMessage;
    }

    public void doEditLeadsDetails(MarketingAction marketingAction) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString;
        int isInserted = 0;
        List<String> list = new ArrayList<String>();
        List<String> list1 = new ArrayList<String>();
        List<String> list2 = new ArrayList<String>();
        List<String> list3 = new ArrayList<String>();
        String resultMessage = "";

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT Title,InvestmentId,tblCrmLeads.Description,Contacts,CurrStatus,Inv_Name,PassedBy1,Commenst1,NextSteps1,PassedBy2,Commenst2,NextSteps2,PassedBy3,Commenst3,NextSteps3,InvestmentType,Priority FROM tblCrmLeads INNER JOIN tblInvestments ON(InvestmentId=Inv_Id) WHERE tblCrmLeads.Id=" + marketingAction.getLeadId();
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                marketingAction.setTitle(resultSet.getString("Title"));
                marketingAction.setInvestmentId(resultSet.getInt("InvestmentId"));
                marketingAction.setDescription(resultSet.getString("Description"));
                marketingAction.setInvestmentTitle(resultSet.getString("Inv_Name"));
                StringTokenizer str = new StringTokenizer(resultSet.getString("Contacts"), ",");
                while (str.hasMoreTokens()) {
                    list.add(str.nextToken());
                }
                marketingAction.setContactsIdList(list);
                marketingAction.setStatus(resultSet.getString("CurrStatus"));
                marketingAction.setPassedBy1hidden(resultSet.getString("PassedBy1"));

                marketingAction.setPassedBy2hidden(resultSet.getString("PassedBy2"));
                marketingAction.setPassedBy3hidden(resultSet.getString("PassedBy3"));
                list1.add(resultSet.getString("PassedBy1"));
                list2.add(resultSet.getString("PassedBy2"));
                list3.add(resultSet.getString("PassedBy3"));

                marketingAction.setPassedBy1List(list1);
                marketingAction.setPassedBy2List(list2);
                marketingAction.setPassedBy3List(list3);
                marketingAction.setComments1(resultSet.getString("Commenst1"));
                marketingAction.setComments2(resultSet.getString("Commenst2"));
                marketingAction.setComments3(resultSet.getString("Commenst3"));
                marketingAction.setNextSteps1(resultSet.getString("NextSteps1"));
                marketingAction.setNextSteps2(resultSet.getString("NextSteps2"));
                marketingAction.setNextSteps3(resultSet.getString("NextSteps3"));
                marketingAction.setInvestmentType(resultSet.getString("InvestmentType"));
                marketingAction.setPriority(resultSet.getString("Priority"));

            }
        } catch (SQLException se) {
            throw new ServiceLocatorException(se);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }

    }

    public String doUpdateLeads(MarketingAction marketingAction) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String queryString;
        int isInserted = 0;
        String resultMessage = "";

        try {
            connection = ConnectionProvider.getInstance().getConnection();
//System.out.println("marketingAction.getLeadId()-->"+marketingAction.getLeadId());
            queryString = "UPDATE tblCrmLeads SET Title=?,InvestmentId=?,Description=?,Contacts=?,CurrStatus=?,ModifiedBy=?,ModifiedDate=?,PassedBy1=?,Commenst1=?,NextSteps1=?,PassedBy2=?,Commenst2=?,NextSteps2=?,PassedBy3=?,Commenst3=?,NextSteps3=?,Priority=? WHERE Id=" + marketingAction.getLeadId();
            preparedStatement = connection.prepareStatement(queryString);

            preparedStatement.setString(1, marketingAction.getTitle());
            preparedStatement.setInt(2, marketingAction.getInvestmentId());
            preparedStatement.setString(3, marketingAction.getDescription());
            preparedStatement.setString(4, marketingAction.getContactsHidden());
            preparedStatement.setString(5, marketingAction.getStatus());
// System.out.println("marketingAction.getContactsHidden()-->"+marketingAction.getContactsHidden());
            preparedStatement.setString(6, marketingAction.getCreatedBy());
            preparedStatement.setTimestamp(7, marketingAction.getCreatedDate());
            //System.out.println(marketingAction.getPassedBy1hidden());
            preparedStatement.setString(8, marketingAction.getPassedBy1hidden());
            preparedStatement.setString(9, marketingAction.getComments1());
            preparedStatement.setString(10, marketingAction.getNextSteps1());
            preparedStatement.setString(11, marketingAction.getPassedBy2hidden());
            preparedStatement.setString(12, marketingAction.getComments2());
            preparedStatement.setString(13, marketingAction.getNextSteps2());
            preparedStatement.setString(14, marketingAction.getPassedBy3hidden());
            preparedStatement.setString(15, marketingAction.getComments3());
            preparedStatement.setString(16, marketingAction.getNextSteps3());
            preparedStatement.setString(17,marketingAction.getPriority());
            isInserted = preparedStatement.executeUpdate();
            if (isInserted > 0) {
                resultMessage = "<font style='color:green;font-size:15px;'>Lead Details Updated Successfully.</font>";
            } else {
                resultMessage = "<font style='color:red;font-size:15px;'>Please Try Again.</font>";
            }

        } catch (SQLException se) {
            throw new ServiceLocatorException(se);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }
        return resultMessage;
    }

    public int generateExcelInvestment(String queryString) throws ServiceLocatorException {
        Connection connection = null;
        int count = 0;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();


            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                count = 1;
            }
        } catch (SQLException se) {
            throw new ServiceLocatorException(se);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }


                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }
        return count;
    }
    /*
     * Emeet methods
     * Date : 01/29/2016
     * Author : Phani Kanuri
     */

    public List doEmeetSearch(MarketingAction marketingAction) throws ServiceLocatorException {

        List mainList = null;



        try {

            JSONObject jb = new JSONObject();

            if (marketingAction.getEmeetFromDate() != null && !"".equals(marketingAction.getEmeetFromDate())) {
                jb.put("EmeetFromDate", marketingAction.getEmeetFromDate());
            } else {
                jb.put("EmeetFromDate", "none");
            }
            if (marketingAction.getEmeetToDate() != null && !"".equals(marketingAction.getEmeetToDate())) {
                jb.put("EmeetToDate", marketingAction.getEmeetToDate());
            } else {
                jb.put("EmeetToDate", "none");
            }
            if (marketingAction.getEmeetType() != null && !"".equals(marketingAction.getEmeetType())) {
                jb.put("EMeetType", marketingAction.getEmeetType());
            } else {
                jb.put("EMeetType", "none");
            }
            if (marketingAction.getExecutiveMeetMonthSearch() != null && !"".equals(marketingAction.getExecutiveMeetMonthSearch())) {
                jb.put("ExecutiveMeetMonthSearch", marketingAction.getExecutiveMeetMonthSearch());
            } else {
                jb.put("ExecutiveMeetMonthSearch", "none");
            }
            jb.put("EmeetStatusByDate", marketingAction.getEmeetStatusByDate());
            jb.put("EmeetStatusForMails", "none");
            // System.out.println("marketingAction.geteMeetcreatedDate()---" + marketingAction.getEmeetFromDate());
            // System.out.println("marketingAction.geteMeetType()---" + marketingAction.getEmeetType());

            // URL url = new URL("http://localhost:8080/CrunchifyTutorials/api/crunchifyService");
            //URL url = new URL("http://172.17.11.251:8080/WebRoot/resources/doJobPost/search");
            //  URL url = new URL("http://172.17.14.226:8080/WebRoot/resources/doJobPost/search");
            //  URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doJobPost/search");
            //    String serviceUrl = RestRepository.getInstance().getSrviceUrl("jobsearch");
            String serviceUrl = RestRepository.getInstance().getSrviceUrl("eMeetsearch");
            URL url = new URL(serviceUrl);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write(jb.toString());
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String s = null;
            String data = "";
            while ((s = in.readLine()) != null) {
                //     System.out.println(s);
                data = data + s;
            }



            //  System.out.println("Data-->"+data);
            JSONObject jObject = new JSONObject(data);

            mainList = new ArrayList();
            for (int i = 0; i < jObject.length(); i++) {
                JSONObject subJson = jObject.getJSONObject(String.valueOf(i));
                Map subMap = new TreeMap();

                Iterator<?> subkeys = subJson.keys();
                while (subkeys.hasNext()) {

                    String subkey = (String) subkeys.next();
                    String value = (String) subJson.get(subkey);
                    // subList.add(value);
                    subMap.put(subkey, value);
                }


                mainList.add(subMap);



            }
            //  System.out.println("\nREST Service Invoked Successfully..");
            in.close();
        } catch (Exception e) {
            System.out.println("\nError while calling REST Service");
            // System.out.println(e);
        }
        return mainList;
    }

    public List doEmeetAttendeesSearch1(MarketingAction marketingAction) throws ServiceLocatorException {

        List mainList = null;


        try {

            JSONObject jb = new JSONObject();

            if (marketingAction.getEmeetFromDate() != null && !"".equals(marketingAction.getEmeetFromDate())) {
                jb.put("EmeetFromDate", marketingAction.getEmeetFromDate());
            } else {
                jb.put("EmeetFromDate", "none");
            }
            if (marketingAction.getEmeetToDate() != null && !"".equals(marketingAction.getEmeetToDate())) {
                jb.put("EmeetToDate", marketingAction.getEmeetToDate());
            } else {
                jb.put("EmeetToDate", "none");
            }
            if (marketingAction.getEmeetType() != null && !"".equals(marketingAction.getEmeetType())) {
                jb.put("EMeetType", marketingAction.getEmeetType());
            } else {
                jb.put("EMeetType", "none");
            }


            String serviceUrl = RestRepository.getInstance().getSrviceUrl("eMeetsearch");
            URL url = new URL(serviceUrl);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write(jb.toString());
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String s = null;
            String data = "";
            while ((s = in.readLine()) != null) {
                //     System.out.println(s);
                data = data + s;
            }



            //  System.out.println("Data-->"+data);
            JSONObject jObject = new JSONObject(data);

            mainList = new ArrayList();
            for (int i = 0; i < jObject.length(); i++) {
                JSONObject subJson = jObject.getJSONObject(String.valueOf(i));
                Map subMap = new TreeMap();

                Iterator<?> subkeys = subJson.keys();
                while (subkeys.hasNext()) {

                    String subkey = (String) subkeys.next();
                    String value = (String) subJson.get(subkey);
                    // subList.add(value);
                    subMap.put(subkey, value);
                }


                mainList.add(subMap);



                // map.put(key, mailJson);

            }
            //  System.out.println("\nREST Service Invoked Successfully..");
            in.close();
        } catch (Exception e) {
            System.out.println("\nError while calling REST Service");
            //  System.out.println(e);
        }
        return mainList;
    }

    public List doEmeetAttendeesSearch(MarketingAction marketingAction) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String queryString;
        int isInserted = 0;
        String resultMessage = "";

        List eMeetAttendeesListMain = new ArrayList();

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT tblEAttendees.Id as Id,CONCAT (FName,'',MName,'.',LName) AS EmpName,Email1,TitleTypeId,tblEAttendees.CurrStatus as CurrStatus,tblEAttendees.AccessType FROM tblEAttendees "
                    + "LEFT JOIN tblEmployee ON(tblEmployee.LoginId=tblEAttendees.LoginId) WHERE 1=1";
            if (marketingAction.getEmeetAccessType() != null && !"".equals(marketingAction.getEmeetAccessType())) {
                queryString = queryString + " and tblEAttendees.AccessType = '" + marketingAction.getEmeetAccessType() + "'";
            }
            if (marketingAction.getEmeetAttendeesEmail() != null && !"".equals(marketingAction.getEmeetAttendeesEmail())) {
                queryString = queryString + " and Email1 like '%" + marketingAction.getEmeetAttendeesEmail() + "%'";
            }
            if (marketingAction.getEmeetMeetingAccessStatus() != null && !"".equals(marketingAction.getEmeetMeetingAccessStatus())) {
                queryString = queryString + " and CurrStatus = '" + marketingAction.getEmeetMeetingAccessStatus() + "'";
            }
            queryString = queryString + " ORDER BY tblEAttendees.Id DESC";
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Map eMeetAttendeesMap = new HashMap();
                eMeetAttendeesMap.put("Id", resultSet.getString("Id"));
                eMeetAttendeesMap.put("EmpName", resultSet.getString("EmpName"));
                eMeetAttendeesMap.put("Email", resultSet.getString("Email1"));
                eMeetAttendeesMap.put("TitleTypeId", resultSet.getString("TitleTypeId"));
                eMeetAttendeesMap.put("CurrStatus", resultSet.getString("CurrStatus"));
                eMeetAttendeesMap.put("AccessType", resultSet.getString("AccessType"));
                eMeetAttendeesListMain.add(eMeetAttendeesMap);
            }


        } catch (SQLException se) {
            throw new ServiceLocatorException(se);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }

            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }
        return eMeetAttendeesListMain;
    }

    public String doPublishExeMeet(MarketingAction marketingAction) throws ServiceLocatorException {


        String message = "";


        try {
            JSONObject jb = new JSONObject();

            jb.put("Id", marketingAction.getId());
            jb.put("CreatedBy", marketingAction.getCreatedBy());

            String serviceUrl = RestRepository.getInstance().getSrviceUrl("doPublishEmeet");
            URL url = new URL(serviceUrl);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write(jb.toString());
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String s = null;
            String data = "";
            while ((s = in.readLine()) != null) {

                data = data + s;
            }

            JSONObject jObject = new JSONObject(data);

            int Id = jObject.getInt("Id");
            String TYPE = jObject.getString("TYPE");
            String STATUS = jObject.getString("STATUS");
            String StartTime = jObject.getString("StartTime");
            String EndTime = jObject.getString("EndTime");
            String MidDayTo = jObject.getString("MidDayTo");
            String MidDayFrom = jObject.getString("MidDayFrom");
            String EMeetDate = jObject.getString("EMeetDate");
            //  String EMeetRegistrationText = jObject.getString("EMeetRegistrationText");
            String EMeetRegistrationLink = jObject.getString("EMeetRegistrationLink");
            String TimeZone = jObject.getString("TimeZone");
            String Month = jObject.getString("Month");
            String CreatedDate = jObject.getString("CreatedDate");
            String CreatedBy = jObject.getString("CreatedBy");
            Map attendeesMap = DataSourceDataProvider.getInstance().getAttendeesForEMeetByType(TYPE);

            Iterator entries = attendeesMap.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry entry = (Map.Entry) entries.next();
                String AttendeeEmail = (String) entry.getKey();
                String AttendeeName = (String) entry.getValue();
                //    System.out.println("Key = " + AttendeeEmail + ", Value = " + AttendeeName);
                if (Properties.getProperty("Mail.Flag").equals("1")) {
                    String toAddress = "";
                    String cCAddress = "";
                    String subject = "";
                    String bodyContent = "";
                    String createdDate = "";
                    String mailDeliverDate = "";
                    String mailFlag = "0";
                    String bCCAddress = "";
                    // createdDate = DateUtility.getInstance().getCurrentMySqlDateTime1();
                    //mailDeliverDate = DateUtility.getInstance().getCurrentMySqlDateTime1();
                    StringBuilder htmlText = new StringBuilder();
                    //  bCCAddress = Properties.getProperty("bccAddress");
                    htmlText.append("<html xmlns='http://www.w3.org/1999/xhtml' class='gr__newsletters-2016-vnallamalla_c9users_io'><head>");
                    htmlText.append("  <meta http-equiv='Content-Type' content='text/html; charset=utf-8'>");
                    htmlText.append("  <title>Global Sales Meet </title>");
                    htmlText.append("  <style type='text/css'>");
                    htmlText.append("    body {");
                    htmlText.append("    padding-top: 0 !important;");
                    htmlText.append("    padding-bottom: 0 !important;");
                    htmlText.append("   padding-top: 0 !important;");
                    htmlText.append("   padding-bottom: 0 !important;");
                    htmlText.append("  margin:0 !important;");
                    htmlText.append("  width: 100% !important;");
                    htmlText.append("  -webkit-text-size-adjust: 100% !important;");
                    htmlText.append(" -ms-text-size-adjust: 100% !important;");
                    htmlText.append(" -webkit-font-smoothing: antialiased !important;");
                    htmlText.append(" }");
                    htmlText.append(" .tableContent img {");
                    htmlText.append(" border: 0 !important;");
                    htmlText.append("display: block !important;");
                    htmlText.append(" outline: none !important;");
                    htmlText.append("  }");
                    htmlText.append("a{");
                    htmlText.append("  color:#382F2E;");
                    htmlText.append(" }");
                    htmlText.append(" p, h1,h2,ul,ol,li,div{");
                    htmlText.append("  margin:0;");
                    htmlText.append("   padding:0;");
                    htmlText.append(" }");
                    htmlText.append(" h1,h2{");
                    htmlText.append("  font-weight: normal;");
                    htmlText.append(" background:transparent !important;");
                    htmlText.append(" border:none !important;");
                    htmlText.append(" }");
                    htmlText.append(" .contentEditable h2.big,.contentEditable h1.big{");
                    htmlText.append(" font-size: 26px !important;");
                    htmlText.append("  }");
                    htmlText.append("  .contentEditable h2.bigger,.contentEditable h1.bigger{");
                    htmlText.append("  font-size: 37px !important;");
                    htmlText.append("  }");
                    htmlText.append(" td,table{");
                    htmlText.append("  vertical-align: top;");
                    htmlText.append("  }");
                    htmlText.append("  td.middle{");
                    htmlText.append("  vertical-align: middle;");
                    htmlText.append("  }");
                    htmlText.append(" a.link1{");
                    htmlText.append("  font-size:13px;");
                    htmlText.append(" color:#27A1E5;");
                    htmlText.append(" line-height: 24px;");
                    htmlText.append(" text-decoration:none;");
                    htmlText.append("  }");
                    htmlText.append("  a{");
                    htmlText.append("  text-decoration: none;");
                    htmlText.append("  }");
                    htmlText.append("  .link2{");
                    htmlText.append("  color:#fc3f3f;");
                    htmlText.append("  border-top:0px solid #fc3f3f;");
                    htmlText.append(" border-bottom:0px solid #fc3f3f;");
                    htmlText.append("  border-left:10px solid #fc3f3f;");
                    htmlText.append("  border-right:10px solid #fc3f3f;");
                    htmlText.append("  border-radius:1px;");
                    htmlText.append(" -moz-border-radius:5px;");
                    htmlText.append(" -webkit-border-radius:5px;");
                    htmlText.append("  background:#fc3f3f;");
                    htmlText.append("  }");
                    htmlText.append("  .link3{");
                    htmlText.append("  color:#555555;");
                    htmlText.append("  border:1px solid #cccccc;");
                    htmlText.append("  padding:10px 18px;");
                    htmlText.append("   border-radius:3px;");
                    htmlText.append("  -moz-border-radius:3px;");
                    htmlText.append("  -webkit-border-radius:3px;");
                    htmlText.append("  background:#ffffff;");
                    htmlText.append("  }");
                    htmlText.append("  .link4{");
                    htmlText.append("  color:#27A1E5;");
                    htmlText.append("  line-height: 24px;");
                    htmlText.append("  }");
                    htmlText.append("  h2,h1{");
                    htmlText.append(" line-height: 20px;");
                    htmlText.append(" }");
                    htmlText.append(" p{");
                    htmlText.append(" font-size: 14px;");
                    htmlText.append(" line-height: 21px;");
                    htmlText.append(" color:#AAAAAA;");
                    htmlText.append(" }");
                    htmlText.append("  .contentEditable li{");
                    htmlText.append(" }");
                    htmlText.append(" .appart p{");
                    htmlText.append(" }");
                    htmlText.append("  .bgItem{");
                    htmlText.append("  background:#ffffff;");
                    htmlText.append("  }");
                    htmlText.append("  .bgBody{");
                    htmlText.append(" background: #0d416b;");
                    htmlText.append(" }");
                    htmlText.append(" img { ");
                    htmlText.append(" outline:none; ");
                    htmlText.append(" text-decoration:none; ");
                    htmlText.append("  -ms-interpolation-mode: bicubic;");
                    htmlText.append("  width: auto;");
                    htmlText.append(" max-width: 100%; ");
                    htmlText.append("  clear: both; ");
                    htmlText.append("  display: block;");
                    htmlText.append("  float: none;");
                    htmlText.append(" }");
                    htmlText.append("</style>");
                    htmlText.append("<script type='colorScheme' class='swatch active'>");
                    htmlText.append(" {");
                    htmlText.append("     'name':'Default',");
                    htmlText.append("     'bgBody':'ffffff',");
                    htmlText.append("     'link':'27A1E5',");
                    htmlText.append("     'color':'AAAAAA',");
                    htmlText.append("   'bgItem':'ffffff',");
                    htmlText.append("   'title':'444444'");
                    htmlText.append("  }");
                    htmlText.append(" </script>");
                    htmlText.append(" </head>");
                    htmlText.append(" <body paddingwidth='0' paddingheight='0' bgcolor='#d1d3d4' style='padding-top: 0; padding-bottom: 0; padding-top: 0; padding-bottom: 0; background-repeat: repeat; width: 100% !important; -webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; -webkit-font-smoothing: antialiased;' offset='0' toppadding='0' leftpadding='0'>");
                    htmlText.append("   <table width='100%' border='0' cellspacing='0' cellpadding='0' class='tableContent bgBody' align='center' style='font-family:Helvetica, sans-serif;'>");
                    htmlText.append("    <tbody>");
                    htmlText.append("       <tr>");
                    htmlText.append("    <td align='center'>");
                    htmlText.append("        <table width='600' border='0' cellspacing='0' cellpadding='0' align='center'>");
                    htmlText.append("         <tbody>");
                    htmlText.append("     <tr>");
                    htmlText.append("      <td class='bgItem' align='center'>");
                    htmlText.append("         <table width='600' border='0' cellspacing='0' cellpadding='0' align='center'>");
                    htmlText.append("          <tbody>");
                    htmlText.append("           <tr>");
                    htmlText.append("           <td class='movableContentContainer' align='center'>");
                    htmlText.append("               <div class='movableContent'>");
                    htmlText.append("           <table width='100%' border='0' cellspacing='0' cellpadding='0' align='center'>");
                    htmlText.append("              <tbody>");
                    htmlText.append("                <tr>");
                    htmlText.append("                  <td style='background:#0d416b; border-radius:0px;-moz-border-radius:0px;-webkit-border-radius:0px' height='20'></td>");
                    htmlText.append("              </tr>");
                    htmlText.append("            <tr>");
                    htmlText.append("            <td style='background:#0d416b; border-radius:0px;-moz-border-radius:0px;-webkit-border-radius:0px'>");
                    htmlText.append("               <table width='650' border='0' cellspacing='0' cellpadding='0' align='center'>");
                    htmlText.append("                <tbody>");
                    htmlText.append("                  <tr>");
                    htmlText.append("                    <td>");
                    htmlText.append("                 <div class='contentEditableContainer contentImageEditable'>");
                    htmlText.append("                   <div class='contentEditable'>");
                    htmlText.append("                  <a href='http://www.miraclesoft.com/index.php' target='_blank'><img src='http://www.miraclesoft.com/newsletters/others/invite_interconnect_2015/images/logo.png' alt='Logo' height='45' data-default='placeholder' data-max-width='200'></a>");
                    htmlText.append("          </div>");
                    htmlText.append("       </div>");
                    htmlText.append("    </td>");
                    htmlText.append("   <td valign='middle' style='vertical-align: middle;'>");
                    htmlText.append("    </td>");
                    htmlText.append("    <td valign='middle' style='vertical-align: middle;' width='150'>");
                    htmlText.append("      <br>");
                    htmlText.append("     <table width='300' border='0' cellpadding='0' cellspacing='0' align='right' style='text-align: right; font-size: 13px; border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt;' class='fullCenter'>");
                    htmlText.append("     <tbody>");
                    htmlText.append("        <tr>");
                    htmlText.append("           <td height='55' valign='middle' width='100%' style='font-family: Helvetica, Arial, sans-serif; color:#232527;'>");
                    htmlText.append("            <span style='font-family: 'proxima_nova_rgregular', Helvetica; font-weight: normal;'><a href='http://www.miraclesoft.com' target='_blank' style=' font-family:calibri; text-decoration: none; color:#ffffff;' class='underline'>Company</a></span>");
                    htmlText.append("            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style='font-family:calibri; Helvetica; font-weight: normal;'><a href='http://www.miraclesoft.com/careers/careers.php' target='_blank' style='text-decoration: none; color:#ffffff;' class='underline'>Careers</a></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
                    htmlText.append("          </td>");
                    htmlText.append("       </tr>");
                    htmlText.append("    </tbody>");
                    htmlText.append("  </table>");
                    htmlText.append("  </td>");
                    htmlText.append("     </tr>");
                    htmlText.append("  </tbody>");
                    htmlText.append("    </table>");
                    htmlText.append("  </td>");
                    htmlText.append("  </tr>");
                    htmlText.append("   </tbody>");
                    htmlText.append("  </table>");
                    htmlText.append("  </div>");
                    htmlText.append("   <div class='movableContent'>");
                    htmlText.append("      <table width='580' border='0' cellspacing='0' cellpadding='0' align='center'>");
                    htmlText.append("       <tbody>");
                    htmlText.append("         <tr>");
                    htmlText.append("           <td style='border: 5px solid #232527; border-radius:0px;-moz-border-radius:0px;-webkit-border-radius:0px'>");
                    htmlText.append("               <table width='650' border='0' cellspacing='0' cellpadding='0' align='center'>");
                    htmlText.append("              <tbody>");
                    htmlText.append("                <tr>");
                    htmlText.append("                <td style='background:#00aae7; border-radius:0px;-moz-border-radius:0px;-webkit-border-radius:px'>");
                    htmlText.append("               <table width='600' border='0' cellspacing='0' cellpadding='0' align='center'>");
                    htmlText.append("                   <tbody>");
                    htmlText.append("                     <tr>");
                    htmlText.append("                      <td height='18'></td>");
                    htmlText.append("             </tr>");
                    htmlText.append("              <tr>");
                    htmlText.append("                <td>");
                    htmlText.append("                  <div class='contentEditableContainer contentTextEditable'>");
                    htmlText.append("                   <div class='contentEditable' style='text-align: left;'>");
                    htmlText.append("                     <h2 style='font-size: 25px;'><font color='#ffffff' face='calibri'><b>" + TYPE + "</b></font></h2>");
                    htmlText.append("                     <br>");
                    htmlText.append("             </div></div></td>");
                    htmlText.append("           </tr>");
                    htmlText.append("        </tbody>");
                    htmlText.append("      </table>");
                    htmlText.append("   </td>");
                    htmlText.append(" </tr>");
                    htmlText.append("</tbody>");
                    htmlText.append("</table>");
                    htmlText.append("</div>");
                    htmlText.append("<p></p>");
                    htmlText.append("<p></p>");
                    htmlText.append("<table width='600' border='0' cellspacing='0' cellpadding='0' align='center'>");
                    htmlText.append(" <tbody>");
                    htmlText.append("   <tr>");
                    htmlText.append("     <td height='5'></td>");
                    htmlText.append("</tr>");
                    htmlText.append("<tr>");
                    htmlText.append(" <td>");
                    htmlText.append("   <div class='contentEditableContainer contentTextEditable'>");
                    htmlText.append("    <div class='contentEditable' style='text-align: center;'>");
                    htmlText.append("     <br>");
                    htmlText.append("    <p style='line-height:150%; text-align: justify; font-size: 14px;'><font color='#232527' face='calibri'><b>" + AttendeeName + ", </b>");
                    htmlText.append("    </font></p><font color='#232527' face='calibri'>");
                    htmlText.append("  </font></div><font color='#232527' face='calibri'>");
                    htmlText.append("  </font></div><font color='#232527' face='calibri'>");
                    htmlText.append("   </font></td>");
                    htmlText.append("  </tr>");
                    htmlText.append("   <tr>");
                    htmlText.append("    <td height='0'></td>");
                    htmlText.append("    </tr>");
                    htmlText.append("    <tr>");
                    htmlText.append("      <td>");
                    htmlText.append("       <div class='contentEditableContainer contentTextEditable'>");
                    htmlText.append("         <div class='contentEditable' style='text-align: center;'>");
                    htmlText.append("        <br>");
                    htmlText.append("    <p style='line-height:150%; text-align: justify; font-size: 14px;'><font color='#232527' face='calibri'>");
                    htmlText.append("   We are pleased to invite you for the <b>" + TYPE + "</b> for the month of <b>" + Month + "</b> on <b>" + DateUtility.getInstance().getDateWithMonthName(EMeetDate) + "</b> at <b>" + StartTime + " " + MidDayFrom + " " + TimeZone + "</b>. The meet will cover the progress of our teams over the last month and will help all of us to ensure that we are on the same road to success. ");
                    htmlText.append("<br><br> Please register today to book your slot for the executive meet. For past Executive Meet replays please visit <a href='http://www.miraclesoft.com/emeets' target='blank'; style= 'color :#2368a0';><b>www.miraclesoft.com/emeets</b></a> ");
                    htmlText.append("   <br><br>Please do not forward this email to anyone else, and any passing of this registration information will be considered as a corporate violation. For adding (or) removing members from the meet, please contact <a href=mailto:marketing@miraclesoft.com><b>marketing@miraclesoft.com</b></a><br><br><span style='font-family: calibri; font-size: 14px; color:#ef4048; font-weight: normal;'>");
                    htmlText.append("  <b>");
                    htmlText.append("  *Note: ");
                    htmlText.append("    </b>");
                    htmlText.append("   </span>");
                    htmlText.append("   <span style='font-family: calibri; font-size: 14px; color:#232527; font-weight: normal;'>");
                    if ("Global Sales Meet".equals(TYPE)) {
                        htmlText.append("    <i>The Global Sales Meet will be held on the 3rd working day after the 2nd of every month</i></span>");
                    } else if ("Global Practice Meet".equals(TYPE)) {
                        htmlText.append("    <i> The Global Practice Meet will be held on the First Tuesday after the 8th of every month</i></span>");
                    }

                    htmlText.append("   <br>");

                    htmlText.append("  </span></font>");
                    htmlText.append("  </p>");
                    htmlText.append("  </div>");
                    htmlText.append(" </div>");
                    htmlText.append(" </td>");
                    htmlText.append(" </tr>");
                    htmlText.append(" <tr>");
                    htmlText.append("   <td colspan='3' class='center' align='left' valign='top' style='padding-top: 20px;'>");
                    htmlText.append("   <table border='0' align='center' cellpadding='0' cellspacing='0' style='margin: 0;'>");
                    htmlText.append(" <tbody>");
                    htmlText.append("        <tr>");
                    htmlText.append("      <td bgcolor='#ef4048' align='left' valign='middle' style='display: inline-block; color:#ffffff; border-radius:5px; padding: 6px 15px; font-weight: normal; font-size: 14px; line-height: 100%; font-family: 'calibri'; color:#ffffff; margin: 0 !important; mso-line-height-rule: exactly;'>");
                    htmlText.append("       <span>");
                    htmlText.append("      <a href='" + EMeetRegistrationLink + "' target='blank' style='text-decoration: none; font-style: normal; font-weight: normal; font-family:calibri; color:#ffffff;'>");
                    htmlText.append("     <b> Register Today</b>");
                    htmlText.append("       </a>");
                    htmlText.append("      </span>");
                    htmlText.append("     </td>");
                    htmlText.append("    </tr>");
                    htmlText.append("  </tbody>");
                    htmlText.append("  </table>");
                    htmlText.append("   </td>");
                    htmlText.append("   </tr>");
                    htmlText.append("  <tr><td height='10' style='padding:0; line-height: 0;'>");
                    htmlText.append("   &nbsp;");
                    htmlText.append(" </td>");
                    htmlText.append(" </tr>");
                    htmlText.append("  <tr>");
                    htmlText.append("  <td align='justify' valign='top' style='margin: 0; padding-top: 5px; font-size:14px ; font-weight: normal; color:#000000; font-family:calibri;line-height:150%;mso-line-height-rule:exactly;'>");
                    htmlText.append("  <multi>");
                    htmlText.append("    <span>");
                    htmlText.append("    <b>");
                    htmlText.append("   Thanks &amp; Regards,");
                    htmlText.append("    </b>");
                    htmlText.append("   <br>");
                    htmlText.append("   <b>");
                    htmlText.append("  Marketing Team");
                    htmlText.append("  </b>");
                    htmlText.append("  <br>");
                    htmlText.append("  Miracle Software Systems, Inc.");
                    htmlText.append(" <br>");
                    htmlText.append(" 45625 Grand River Avenue, Novi, MI(USA)");
                    htmlText.append(" <br>");
                    htmlText.append("  <b>");
                    htmlText.append("  Email :");
                    htmlText.append(" </b>");
                    htmlText.append(" marketing@miraclesoft.com ");
                    htmlText.append(" <br>");
                    htmlText.append(" <b>");
                    htmlText.append(" Phone :");
                    htmlText.append(" </b>");
                    htmlText.append(" (+1)248-232-0428");
                    htmlText.append("  </span>");
                    htmlText.append("   <br>");
                    htmlText.append("   <br>");
                    htmlText.append(" </multi>");
                    htmlText.append("  </td>");
                    htmlText.append("  </tr>");
                    htmlText.append(" </tbody>");
                    htmlText.append(" </table>");
                    htmlText.append(" </td>");
                    htmlText.append(" </tr>");
                    htmlText.append("<tr>");

                    htmlText.append(" </tr>");
                    htmlText.append("  <tr>");

                    htmlText.append("  </tr>");
                    htmlText.append("  </tbody>");
                    htmlText.append("</table>");
                    htmlText.append(" <table width='600' border='0' cellspacing='0' cellpadding='0' align='center'>");
                    htmlText.append("   <tbody>");
                    htmlText.append("    <tr>");
                    htmlText.append("     <td>");
                    htmlText.append("  <div class='contentEditableContainer contentTextEditable'>");
                    htmlText.append("    <div class='contentEditable' style='text-align: center;'>");
                    htmlText.append("       <p></p>");
                    htmlText.append("    </div>");
                    htmlText.append(" </div>");
                    htmlText.append(" </td>");
                    htmlText.append(" </tr>");

                    htmlText.append(" </tbody>");
                    htmlText.append(" </table>");
                    htmlText.append(" </div></td>");
                    htmlText.append(" </tr>");
                    htmlText.append("</tbody>");
                    htmlText.append("</table>");

                    htmlText.append("<div class='movableContent'>");
                    htmlText.append("  <table width='660' border='0' cellspacing='0' cellpadding='0' align='center'>");
                    htmlText.append("   <tbody>");
                    htmlText.append(" <tr>");
                    htmlText.append("   <td style='background:#0d416b; border-radius:0px;-moz-border-radius:0px;-webkit-border-radius:0px'>");
                    htmlText.append("     <table width='655' border='0' cellspacing='0' cellpadding='0' align='center'>");
                    htmlText.append("      <tbody>");
                    htmlText.append("         <tr>");
                    htmlText.append("     <td colspan='3' height='20'></td>");
                    htmlText.append("    </tr>");
                    htmlText.append(" <tr>");
                    htmlText.append("  <td width='90'></td>");
                    htmlText.append("  <td width='660' align='center' style='text-align: center;'>");
                    htmlText.append("   <table width='660' cellpadding='0' cellspacing='0' align='center'>");
                    htmlText.append("    <tbody>");
                    htmlText.append("      <tr>");
                    htmlText.append("        <td>");
                    htmlText.append("    <div class='contentEditableContainer contentTextEditable'>");
                    htmlText.append("      <div class='contentEditable' style='text-align: center;color:#AAAAAA;'>");
                    htmlText.append("      <p style='text-align: center; font-size: 14px;'><font color='#ffffff' face='calibri'>© Copyright 2016 Miracle Software Systems, Inc.<br>");
                    htmlText.append("        45625 Grand River Avenue<br> Novi, MI - USA");
                    htmlText.append("       </font>");
                    htmlText.append("    </p>");
                    htmlText.append("   <font color='#ffffff' face='calibri'>");
                    htmlText.append("  </font>");
                    htmlText.append("</div>");
                    htmlText.append("  <font color='#ffffff' face='calibri'>");
                    htmlText.append("   </font>");
                    htmlText.append(" </div>");
                    htmlText.append("  <font color='#ffffff' face='calibri'>");
                    htmlText.append("  </font>");
                    htmlText.append("   </td>");
                    htmlText.append("  </tr>");
                    htmlText.append("  </tbody>");
                    htmlText.append("  </table>");
                    htmlText.append("  </td>");
                    htmlText.append("  <td width='90'></td>");
                    htmlText.append(" </tr>");
                    htmlText.append(" </tbody>");
                    htmlText.append("</table>");
                    htmlText.append(" <table width='650' border='0' cellspacing='0' cellpadding='0' align='center'>");
                    htmlText.append("  <tbody>");
                    htmlText.append("     <tr>");
                    htmlText.append("        <td colspan='3' height='20'></td>");
                    htmlText.append("   </tr>");
                    htmlText.append("   <tr>");
                    htmlText.append("   <td width='195'></td>");
                    htmlText.append("  <td width='190' align='center' style='text-align: center;'>");
                    htmlText.append("     <table width='190' cellpadding='0' cellspacing='0' align='center'>");
                    htmlText.append("        <tbody>");
                    htmlText.append("      <tr>");
                    htmlText.append("       <td width='40'>");
                    htmlText.append("         <div class='contentEditableContainer contentFacebookEditable'>");
                    htmlText.append("        <div class='contentEditable' style='text-align: center;color:#AAAAAA;'>");
                    htmlText.append("         <a href='https://www.facebook.com/miracle45625' target='_blank'><img src='http://www.miraclesoft.com/newsletters/others/invite_interconnect_2015/images/fb.png' alt='facebook' width='32' height='32' data-max-width='40' data-customicon='true'></a>");
                    htmlText.append("     </div>");
                    htmlText.append("   </div>");
                    htmlText.append("  </td>");
                    htmlText.append("  <td width='10'></td>");
                    htmlText.append("   <td width='40'>");
                    htmlText.append("   <div class='contentEditableContainer contentTwitterEditable'>");
                    htmlText.append("      <div class='contentEditable' style='text-align: center;color:#AAAAAA;'>");
                    htmlText.append("         <a href='https://twitter.com/team_mss' target='_blank'><img src='http://www.miraclesoft.com/newsletters/others/invite_interconnect_2015/images/tweet.png' alt='twitter' width='32' height='32' data-max-width='40' data-customicon='true'></a>");
                    htmlText.append("      </div>");
                    htmlText.append("    </div>");
                    htmlText.append(" </td>");
                    htmlText.append(" <td width='10'></td>");
                    htmlText.append(" <td width='40'>");
                    htmlText.append("  <div class='contentEditableContainer contentImageEditable'>");
                    htmlText.append("    <div class='contentEditable' style='text-align: center;color:#AAAAAA;'>");
                    htmlText.append("     <a href='https://plus.google.com/+Team_MSS/posts' target='_blank'><img src='http://www.miraclesoft.com/newsletters/others/invite_interconnect_2015/images/googleplus.png' alt='Pinterest' width='32' height='32' data-max-width='40'></a>");
                    htmlText.append("    </div>");
                    htmlText.append("  </div>");
                    htmlText.append("  </td>");
                    htmlText.append("   <td width='10'></td>");
                    htmlText.append("  <td width='40'>");
                    htmlText.append("     <div class='contentEditableContainer contentImageEditable'>");
                    htmlText.append("       <div class='contentEditable' style='text-align: center;color:#AAAAAA;'>");
                    htmlText.append("         <a href='https://www.linkedin.com/company/miracle-software-systems-inc' target='_blank'><img src='http://www.miraclesoft.com/newsletters/others/invite_interconnect_2015/images/linkedin.png' alt='Social media' width='32' height='32' data-max-width='40'></a>");
                    htmlText.append("      </div>");
                    htmlText.append("   </div>");
                    htmlText.append("  </td>");
                    htmlText.append("  </tr>");
                    htmlText.append(" </tbody>");
                    htmlText.append("  </table>");
                    htmlText.append(" </td>");
                    htmlText.append("  <td width='195'></td>");
                    htmlText.append(" </tr>");
                    htmlText.append("  <tr>");
                    htmlText.append("  <td colspan='3' height='40'></td>");
                    htmlText.append(" </tr>");
                    htmlText.append("</tbody>");
                    htmlText.append(" </table>");
                    htmlText.append(" </td>");
                    htmlText.append("</tr>");
                    htmlText.append("</tbody>");
                    htmlText.append("  </table>");
                    htmlText.append("</div>");
                    htmlText.append("  <div class='movableContent'>");
                    htmlText.append("    <table width='100%' border='0' cellspacing='0' cellpadding='0' align='center'>");
                    htmlText.append("    <tbody>");
                    htmlText.append("     <tr>");
                    htmlText.append("          <td style='background:#0d416b; border-radius:0px;-moz-border-radius:0px;-webkit-border-radius:0px' height='0'></td>");
                    htmlText.append("     </tr>");
                    htmlText.append("     <tr>");
                    htmlText.append("    </tr>");
                    htmlText.append("  </tbody>");
                    htmlText.append("  </table>");
                    htmlText.append("   </div>");
                    htmlText.append(" </td>");
                    htmlText.append(" </tr>");
                    htmlText.append(" </tbody>");
                    htmlText.append(" </table>");
                    htmlText.append(" </td>");
                    htmlText.append("</tr>");
                    htmlText.append(" </tbody>");
                    htmlText.append("  </table>  ");
                    htmlText.append("</body><span class='gr__tooltip'><span class='gr__tooltip-content'></span><i class='gr__tooltip-logo'></i><span class='gr__triangle'></span></span></html>");

                    bodyContent = htmlText.toString();
                    toAddress = AttendeeEmail;

                    //ServiceLocator.getMailServices().doAddEmailLog(toAddress, cCAddress, issueTitle, bodyContent, createdDate, bCCAddress);
                    ServiceLocator.getMailServices().doAddEmailLogNew(toAddress, cCAddress, "Reg:" + TYPE, bodyContent, createdDate, bCCAddress, "Executive Meet");

                    message = "<font color=\"green\" size=\"2px\">Status Updated Succesfully and Mail had been sent to Attendees!</font>";

                }
            }
            in.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            throw new ServiceLocatorException(e);
        }
        return message;
    }

    public String doActiveExeMeet(MarketingAction marketingAction) throws ServiceLocatorException {


        String message = "";


        try {
            JSONObject jb = new JSONObject();
            // AjaxHandlerAction ac=new AjaxHandlerAction();

            jb.put("ExeMeetId", marketingAction.getId());

            jb.put("CreatedBy", marketingAction.getCreatedBy());
            //URL url = new URL("http://172.17.11.251:8080/WebRoot/resources/doJobPost/update");
            //URL url = new URL("http://172.17.14.226:8080/WebRoot/resources/doJobPost/update");
            //  URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doJobPost/update");

            String serviceUrl = RestRepository.getInstance().getSrviceUrl("doActiveEmmet");
            URL url = new URL(serviceUrl);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write(jb.toString());
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String s = null;
            String data = "";
            while ((s = in.readLine()) != null) {

                data = data + s;
            }

            JSONObject jObject = new JSONObject(data);

            message = jObject.getString("message");
            //   System.out.println("displaymessage-->" +message);



            in.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            throw new ServiceLocatorException(e);
        }
        return message;
    }

    public Map getAllBDMsExceptInvestmentBDMs(Map investmentBDMMap) throws ServiceLocatorException {


        Map bdmMapExceptInvestmentBdmMap = new LinkedHashMap();
        bdmMapExceptInvestmentBdmMap.clear();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String message = "";
        boolean isUpdated = false;
        String queryString = "";
        //  String queryString ="";
        // System.out.println("first call");
        String data = "";
        //queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ModifiedBy=?,ModifiedDate=?,ReviewName=?,ReviewDate=?,Status=?,TLComments=?,TLRating=?,HRRating=?,HrComments=? where Id=?";
        //if(roleName.equalsIgnoreCase("Employee"))
        // queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ReviewName=?,ReviewDate=?,Status=? where Id=?";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT Id,CONCAT(TRIM(FName),'.',TRIM(LName)) AS EmployeeName FROM"
                    + " tblEmployee WHERE CurStatus='Active' AND DeletedFlag != 1 AND TitleTypeId='BDM' ORDER BY EmployeeName";
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            //subJson.put(resultSet.getString("SpeakerId"), resultSet.getString("SpeakerName"));
            while (resultSet.next()) {
                if (!investmentBDMMap.containsKey(resultSet.getString("Id"))) {
                    bdmMapExceptInvestmentBdmMap.put(resultSet.getString("Id"), resultSet.getString("EmployeeName"));
                }
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;

                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;

                }
                if (connection != null) {
                    connection.close();
                    connection = null;

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        bdmMapExceptInvestmentBdmMap = DataSourceDataProvider.getInstance().sortMapByValues(bdmMapExceptInvestmentBdmMap);

        return bdmMapExceptInvestmentBdmMap;
    }

    public Map getBDMsByInvestmentId(int investmentId) throws ServiceLocatorException {

        Map bdmsMap = new LinkedHashMap();
        bdmsMap.clear();
        String primarySpeakerId = "";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String message = "";
        boolean isUpdated = false;
        //  String queryString ="";
        // System.out.println("first call");
        String data = "";
        //queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ModifiedBy=?,ModifiedDate=?,ReviewName=?,ReviewDate=?,Status=?,TLComments=?,TLRating=?,HRRating=?,HrComments=? where Id=?";
        //if(roleName.equalsIgnoreCase("Employee"))
        // queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ReviewName=?,ReviewDate=?,Status=? where Id=?";
        try {


            connection = ConnectionProvider.getInstance().getConnection();

            preparedStatement = connection.prepareStatement("SELECT Id,CONCAT(TRIM(FName),'.',TRIM(LName)) AS EmployeeName FROM tblInvestmentBDMTeam LEFT OUTER JOIN tblEmployee ON (tblInvestmentBDMTeam.EmpId=tblEmployee.Id) WHERE InvestmentId=" + investmentId + " AND CurStatus='Active' AND TitleTypeId='BDM' ORDER BY EmployeeName");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bdmsMap.put(resultSet.getString("Id"), resultSet.getString("EmployeeName"));
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;

                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;

                }
                if (connection != null) {
                    connection.close();
                    connection = null;

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        /*
        ResultSet resultSet = null;
        Connection connection = null;
        Statement statement = null;
        
        connection = ConnectionProvider.getInstance().getConnection();
        
        try {
        statement = connection.createStatement();
        
        if(!("".equals(primaryTeamMember))){
        resultSet = statement.executeQuery("SELECT DISTINCT SpeakerId,SpeakerName FROM vwSpeakerList WHERE eventId="+eventId+" AND SpeakerId="+primaryTeamMember+" AND ObjectType='"+objectType+"'");
        while(resultSet.next()) {
        speakerMap.put(resultSet.getString("SpeakerId"),resultSet.getString("SpeakerName"));
        }
        resultSet.close();
        resultSet = null;
        }
        
        resultSet = statement.executeQuery("SELECT DISTINCT SpeakerId,SpeakerName FROM vwSpeakerList WHERE eventId="+eventId+" AND ObjectType='"+objectType+"'");
        while(resultSet.next()) {
        if(!(speakerMap.containsKey(resultSet.getString("SpeakerId")))){
        speakerMap.put(resultSet.getString("SpeakerId"),resultSet.getString("SpeakerName"));
        }
        
        }
        
        
        } catch (SQLException ex) {
        throw new ServiceLocatorException(ex);
        }finally{
        try{
        if(resultSet != null){
        resultSet.close();
        resultSet = null;
        }
        if(statement != null){
        statement.close();
        statement = null;
        }
        if(connection != null){
        connection.close();
        connection = null;
        }
        }catch (SQLException ex) {
        throw new ServiceLocatorException(ex);
        }
        }*/
        return bdmsMap;
    }

    public int updateInvestmentBdms(String[] investmentBdms, int investmentId) throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        int insertedRows = 0;
        try {


            connection = ConnectionProvider.getInstance().getConnection();

            statement = connection.createStatement();

            boolean isDelete = statement.execute("DELETE FROM tblInvestmentBDMTeam WHERE InvestmentId=" + investmentId);
            statement.close();
            statement = null;
            if (!isDelete) {
                statement = connection.createStatement();
                if (investmentBdms.length > 0) {
                    for (int i = 0; i < investmentBdms.length; i++) {

                      //  System.out.println("BDM ID-->" + investmentBdms[i].trim());
                       // System.out.println("investmentId-->" + investmentId);
                        statement.addBatch("INSERT INTO tblInvestmentBDMTeam(EmpId, InvestmentId) values(" + investmentBdms[i].trim() + "," + investmentId + ")");
                    }
                }
                int[] insertedRowsArray = statement.executeBatch();
                insertedRows = insertedRowsArray.length;



            }

        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            try {

                if (statement != null) {
                    statement.close();
                    statement = null;

                }
                if (connection != null) {
                    connection.close();
                    connection = null;

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return insertedRows;
    }

    public List investmentBdmDetailsList(int investmentId) throws ServiceLocatorException {
        List bdmsList = new LinkedList();
        Map bdmMap = null;
        bdmsList.clear();
        String primarySpeakerId = "";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String message = "";
        boolean isUpdated = false;
        //  String queryString ="";
        // System.out.println("first call");
        String data = "";
        //queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ModifiedBy=?,ModifiedDate=?,ReviewName=?,ReviewDate=?,Status=?,TLComments=?,TLRating=?,HRRating=?,HrComments=? where Id=?";
        //if(roleName.equalsIgnoreCase("Employee"))
        // queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ReviewName=?,ReviewDate=?,Status=? where Id=?";
        try {


            connection = ConnectionProvider.getInstance().getConnection();

            preparedStatement = connection.prepareStatement("SELECT Id,CONCAT(TRIM(FName),'.',TRIM(LName)) AS EmployeeName,Email1,WorkPhoneNo FROM tblInvestmentBDMTeam LEFT OUTER JOIN tblEmployee ON (tblInvestmentBDMTeam.EmpId=tblEmployee.Id) WHERE InvestmentId=" + investmentId + "  ORDER BY EmployeeName");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bdmMap = new HashMap();
                bdmMap.put("EmployeeName", resultSet.getString("EmployeeName"));
                bdmMap.put("Email1", resultSet.getString("Email1"));
                bdmMap.put("WorkPhoneNo", resultSet.getString("WorkPhoneNo"));
                bdmsList.add(bdmMap);
            }


        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;

                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;

                }
                if (connection != null) {
                    connection.close();
                    connection = null;

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bdmsList;
    }

    public Map getLeadLinkedAccountsMap() throws ServiceLocatorException {

        Map accountsMap = new HashMap();;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionProvider.getInstance().getConnection();

            preparedStatement = connection.prepareStatement("SELECT tblCrmAccount.Id,NAME FROM tblCrmAccount WHERE Id IN(SELECT AccountId FROM tblCrmLeads) ORDER BY NAME");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                accountsMap.put(resultSet.getInt("Id"), resultSet.getString("NAME"));
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;

                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;

                }
                if (connection != null) {
                    connection.close();
                    connection = null;

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return accountsMap;
    }
    
public Map getConferencesList(String status) throws ServiceLocatorException {
       // System.out.println("getUpcommingConferences  markserimpl");

        List upcommingConferencesList = null;
        Map upcommingConferencesMap = new TreeMap();

        try {
            JSONObject jb = new JSONObject();
            // AjaxHandlerAction ac=new AjaxHandlerAction();

            jb.put("status", status);
            String serviceUrl = RestRepository.getInstance().getSrviceUrl("getConferencesList");


            URL url = new URL(serviceUrl);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(360*5000);
            connection.setReadTimeout(360*5000);
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write(jb.toString());
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String s = null;
            String data = "";
            while ((s = in.readLine()) != null) {
                //     System.out.println(s);
                data = data + s;
            }



            //  System.out.println("Data-->"+data);
            JSONObject jObject = new JSONObject(data);
            upcommingConferencesList = new ArrayList();
              upcommingConferencesMap = new TreeMap();
            for (int i = 0; i < jObject.length(); i++) {
                JSONObject subJson = jObject.getJSONObject(String.valueOf(i));
              

                Iterator<?> subkeys = subJson.keys();
                while (subkeys.hasNext()) {

                    String subkey = (String) subkeys.next();
                    String value = (String) subJson.get(subkey);
                    // subList.add(value);
                    upcommingConferencesMap.put(subkey, value);
                }

               // mainList.add(subMap);
upcommingConferencesMap = DataSourceDataProvider.sortByValue(upcommingConferencesMap);
            }
            //  System.out.println("\nREST Service Invoked Successfully..");
            in.close();
        } catch (Exception e) {
            System.out.println("\nError while calling REST Service");
           // System.out.println(e);
            e.printStackTrace();
        }
        return upcommingConferencesMap;
    }
}