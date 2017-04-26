/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.marketing.library;

import com.mss.mirage.util.RestRepository;
import com.mss.mirage.util.ServiceLocatorException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author miracle
 */
public class LibraryServiceImpl implements LibraryService{
    
    
    
    
   public String doAddLibrary(LibraryAction libraryAction) throws ServiceLocatorException {
       
       
 String message ="";
        boolean isUpdated = false;
      
        try {
            
              
                JSONObject jb = new JSONObject();
                
                /*
                
                DocType
DocTitle
DocLongDesc
SourceLink
Industry
PrimaryTrackId
SecondaryTrackId
BodyContent
         */     
        jb.put("DocTitle", libraryAction.getResourceTitle());
        jb.put("DocType", libraryAction.getResourceType());
        jb.put("Industry", libraryAction.getResourceIndustry());
       // jb.put("PrimaryTrackId", libraryAction.getResourcePrimaryTrack());
        //jb.put("SecondaryTrackId", libraryAction.getResourceSecondaryTrack());
        if (!"".equals(libraryAction.getResourcePrimaryTrack()) && libraryAction.getResourcePrimaryTrack() != null) {
                jb.put("PrimaryTrackId", libraryAction.getResourcePrimaryTrack());
            } else {
                jb.put("PrimaryTrackId", "0");
            }
            if (!"".equals(libraryAction.getResourceSecondaryTrack()) && libraryAction.getResourceSecondaryTrack() != null) {
                jb.put("SecondaryTrackId", libraryAction.getResourceSecondaryTrack());
            } else {
                jb.put("SecondaryTrackId", "0");
            }

        jb.put("DocLongDesc", libraryAction.getResourceDescription());
        jb.put("BodyContent", libraryAction.getWebPageCreationdetails());
        jb.put("BreadCrumbHeading", libraryAction.getBreadCrumbHeading());
       // jb.put("CreatedDate", libraryAction.getDateOfPublish());
       // jb.put("BodyTitle", libraryAction.getBodyTitle());
         jb.put("PhpFileName", libraryAction.getPhpFileName());
        jb.put("DocName", libraryAction.getFileName());
        jb.put("DocLocation", libraryAction.getFilePath());
        jb.put("bodyContent2", libraryAction.getBodyContent());
        jb.put("STATUS", libraryAction.getResourceStatus());
        jb.put("CreatedBy", libraryAction.getCreatedBy());
        jb.put("DateOfPublish", libraryAction.getDateOfPublish());
        if(libraryAction.getIsResourceDownloadable())
        jb.put("DownloadFlag", 1);
        else
            jb.put("DownloadFlag", 0);
        if(libraryAction.getGatedContent())
        jb.put("GatedContent", 1);
        else
            jb.put("GatedContent", 0);
         if (libraryAction.getCustomerName() != null && !"".equals(libraryAction.getCustomerName())) {
                jb.put("CustomerName", libraryAction.getCustomerName());
            } else {
                jb.put("CustomerName", "none");
            }
         /*   List authorsList = new ArrayList();
            Map authorsMap =null;
            if(!"".equals(libraryAction.getPrimaryAuthor()) && libraryAction.getPrimaryAuthor()!=null){
                authorsMap = new TreeMap();
                authorsMap.put("SpeakerId", libraryAction.getPrimaryAuthor());
                authorsMap.put("PrimarySpeaker", 1);
                authorsMap.put("ObjectType", "LB");
                authorsList.add(authorsMap);
            }
        if(!"".equals(libraryAction.getAuthor2()) && libraryAction.getAuthor2()!=null){
                authorsMap = new TreeMap();
                authorsMap.put("SpeakerId", libraryAction.getAuthor2());
                authorsMap.put("PrimarySpeaker", 0);
                authorsMap.put("ObjectType", "LB");
                 authorsList.add(authorsMap);
            }
 if(!"".equals(libraryAction.getAuthor3()) && libraryAction.getAuthor3()!=null){
                authorsMap = new TreeMap();
                authorsMap.put("SpeakerId", libraryAction.getAuthor3());
                authorsMap.put("PrimarySpeaker", 0);
                authorsMap.put("ObjectType", "LB");
                 authorsList.add(authorsMap);
            }
 jb.put("Authors", authorsList);
*/
       
     //    System.out.println("getConferenceUrl-->"+ajaxHandlerAction.getConferenceUrl());
         //   URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doEventPost/addEvent");
         String serviceUrl = RestRepository.getInstance().getSrviceUrl("libraryAdd");
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
        }catch(Exception e){
             throw new ServiceLocatorException(e);
        }
        return message;   
       
       
   } 
   public List doLibraryManagementSearch(LibraryAction libraryAction) throws ServiceLocatorException {
        
        List mainList = null;
       

      


        try {

            JSONObject jb = new JSONObject();

            if (libraryAction.getCreatedDateFrom() != null && !"".equals(libraryAction.getCreatedDateFrom())) {
                jb.put("event_startdate", libraryAction.getCreatedDateFrom());
            } else {
                jb.put("event_startdate", "none");
            }
            if (libraryAction.getCreatedDateTo() != null && !"".equals(libraryAction.getCreatedDateTo())) {
                jb.put("evetnt_enddate", libraryAction.getCreatedDateTo());
            } else {
                jb.put("evetnt_enddate", "none");
            }
            if (libraryAction.getSearchIndustry() != null && !"".equals(libraryAction.getSearchIndustry())) {
                jb.put("SearchIndustry", libraryAction.getSearchIndustry());
            } else {
                jb.put("SearchIndustry", "none");
            }
            if (libraryAction.getSearchTrack() != null && !"".equals(libraryAction.getSearchTrack())) {
                jb.put("SearchTrack", libraryAction.getSearchTrack());
            } else {
                jb.put("SearchTrack", "none");
            }
            if (libraryAction.getSearchType() != null && !"".equals(libraryAction.getSearchType())) {
                jb.put("SearchType", libraryAction.getSearchType());
            } else {
                jb.put("SearchType", "none");
            } if (libraryAction.getSearchTitle() != null && !"".equals(libraryAction.getSearchTitle())) {
                jb.put("DocTitle", libraryAction.getSearchTitle());
            } else {
                jb.put("DocTitle", "none");
            } if (libraryAction.getSearchFileName() != null && !"".equals(libraryAction.getSearchFileName())) {
                jb.put("PhpFileName", libraryAction.getSearchFileName());
            } else {
                jb.put("PhpFileName", "none");
            }
//searchFileName



            // URL url = new URL("http://localhost:8080/CrunchifyTutorials/api/crunchifyService");
            //URL url = new URL("http://172.17.11.251:8080/WebRoot/resources/doJobPost/search");
            //  URL url = new URL("http://172.17.14.226:8080/WebRoot/resources/doJobPost/search");
            //   URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doEventPost/eventSearch");
            String serviceUrl = RestRepository.getInstance().getSrviceUrl("getLibraryDetails");

            System.out.println("serviceUrl-->" + serviceUrl);
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
            System.out.println(e);
        }
        // System.out.println("mainList-->"+mainList);
        return mainList;
    }
public String doEditLibrary(LibraryAction libraryAction) throws ServiceLocatorException {
       
       
 String message ="";
        boolean isUpdated = false;
      
        try {
            
              
                JSONObject jb = new JSONObject();
                
                /*
                
                DocType
DocTitle
DocLongDesc
SourceLink
Industry
PrimaryTrackId
SecondaryTrackId
BodyContent
         */     
        jb.put("eventId", libraryAction.getLibraryId());
      
  
       
     //    System.out.println("getConferenceUrl-->"+ajaxHandlerAction.getConferenceUrl());
         //   URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doEventPost/addEvent");
         String serviceUrl = RestRepository.getInstance().getSrviceUrl("libraryEdit");
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
        libraryAction.setResourceType(jObject.getString("DocType"));
        libraryAction.setResourceTitle(jObject.getString("DocTitle"));
        
        libraryAction.setResourceDescription(jObject.getString("DocLongDesc"));
        libraryAction.setResourceIndustry(jObject.getString("Industry"));
        libraryAction.setResourcePrimaryTrack(jObject.getString("PrimaryTrackId"));
        libraryAction.setResourceSecondaryTrack(jObject.getString("SecondaryTrackId"));
        libraryAction.setWebPageCreationdetails(jObject.getString("BodyContent"));
        libraryAction.setBreadCrumbHeading(jObject.getString("BreadComLabel"));
        libraryAction.setBodyContent(jObject.getString("BodyContent2"));
       // libraryAction.setBodyTitle(jObject.getString("BodyTitle"));
        libraryAction.setDateOfPublish(jObject.getString("CreatedDate"));
        libraryAction.setPhpFileName(jObject.getString("PhpFileName"));
        libraryAction.setLibraryFileFileName(jObject.getString("DocName"));
        libraryAction.setDateOfPublish(jObject.getString("DateOfPublish"));
        libraryAction.setResourceStatus(jObject.getString("STATUS"));
         int GatedContent=jObject.getInt("GatedContent");
        if(GatedContent==1){
            libraryAction.setGatedContent(true);
        }else{
            libraryAction.setGatedContent(false);
        }
        libraryAction.setCustomerName(jObject.getString("CustomerName"));
        //PhpFileName
            /*  JSONArray AuthorsList=(JSONArray) jObject.get("Authors");
              
              
              
              if(AuthorsList.length()>0){
               
                    JSONObject authorsMap = (JSONObject) AuthorsList.get(0);
                   
                    String SpeakerId=(String) authorsMap.get("SpeakerId");
                    
            
             libraryAction.setPrimaryAuthor(SpeakerId);   
            }
               if(AuthorsList.length()>1){
               
                    JSONObject authorsMap = (JSONObject) AuthorsList.get(1);
                   
                    String SpeakerId=(String) authorsMap.get("SpeakerId");
                    
            
             libraryAction.setAuthor2(SpeakerId);   
            }
                if(AuthorsList.length()>2){
               
                    JSONObject authorsMap = (JSONObject) AuthorsList.get(2);
                   
                    String SpeakerId=(String) authorsMap.get("SpeakerId");
                    
            
             libraryAction.setAuthor3(SpeakerId);   
            }*/
               // subJson.put("SourceLink", resultSet.getString("SourceLink"));
                
        // message = jObject.getString("message");
     


            in.close();
           
        } catch (IOException ex) {
            ex.printStackTrace();
        }catch(Exception e){
             throw new ServiceLocatorException(e);
        }
        return message;   
       
       
   }
public String doUpdateLibrary(LibraryAction libraryAction) throws ServiceLocatorException {


        String message = "";
        boolean isUpdated = false;

        try {


            JSONObject jb = new JSONObject();

            /*
                
             DocType
             DocTitle
             DocLongDesc
             SourceLink
             Industry
             PrimaryTrackId
             SecondaryTrackId
             BodyContent
             */
            jb.put("DocTitle", libraryAction.getResourceTitle());
            jb.put("DocType", libraryAction.getResourceType());
            jb.put("Industry", libraryAction.getResourceIndustry());
           // jb.put("PrimaryTrackId", libraryAction.getResourcePrimaryTrack());
           // jb.put("SecondaryTrackId", libraryAction.getResourceSecondaryTrack());
             if (!"".equals(libraryAction.getResourcePrimaryTrack()) && libraryAction.getResourcePrimaryTrack() != null) {
                jb.put("PrimaryTrackId", libraryAction.getResourcePrimaryTrack());
            } else {
                jb.put("PrimaryTrackId", "0");
            }
            if (!"".equals(libraryAction.getResourceSecondaryTrack()) && libraryAction.getResourceSecondaryTrack() != null) {
                jb.put("SecondaryTrackId", libraryAction.getResourceSecondaryTrack());
            } else {
                jb.put("SecondaryTrackId", "0");
            }
            jb.put("DocLongDesc", libraryAction.getResourceDescription());
            jb.put("BodyContent", libraryAction.getWebPageCreationdetails());
            jb.put("BreadCrumbHeading", libraryAction.getBreadCrumbHeading());
            jb.put("BodyContent2", libraryAction.getBodyContent());
            jb.put("BreadComLabel", libraryAction.getBreadCrumbHeading());
            jb.put("EventId",libraryAction.getLibraryId());
             jb.put("STATUS", libraryAction.getResourceStatus());
        jb.put("CreatedBy", libraryAction.getCreatedBy());
        jb.put("PhpFileName",libraryAction.getPhpFileName());
        jb.put("DateOfPublish", libraryAction.getDateOfPublish());
        if (libraryAction.getCustomerName() != null && !"".equals(libraryAction.getCustomerName())) {
                jb.put("CustomerName", libraryAction.getCustomerName());
            } else {
                jb.put("CustomerName", "none");
            }
           /* List authorsList = new ArrayList();
            Map authorsMap =null;
            if(!"".equals(libraryAction.getPrimaryAuthor()) && libraryAction.getPrimaryAuthor()!=null){
                authorsMap = new TreeMap();
                authorsMap.put("SpeakerId", libraryAction.getPrimaryAuthor());
                authorsMap.put("PrimarySpeaker", 1);
                authorsMap.put("ObjectType", "LB");
                authorsList.add(authorsMap);
            }
        if(!"".equals(libraryAction.getAuthor2()) && libraryAction.getAuthor2()!=null){
                authorsMap = new TreeMap();
                authorsMap.put("SpeakerId", libraryAction.getAuthor2());
                authorsMap.put("PrimarySpeaker", 0);
                authorsMap.put("ObjectType", "LB");
                 authorsList.add(authorsMap);
            }
 if(!"".equals(libraryAction.getAuthor3()) && libraryAction.getAuthor3()!=null){
                authorsMap = new TreeMap();
                authorsMap.put("SpeakerId", libraryAction.getAuthor3());
                authorsMap.put("PrimarySpeaker", 0);
                authorsMap.put("ObjectType", "LB");
                 authorsList.add(authorsMap);
            }
 jb.put("Authors", authorsList);*/
            //    System.out.println("getConferenceUrl-->"+ajaxHandlerAction.getConferenceUrl());
            //   URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doEventPost/addEvent");
            String serviceUrl = RestRepository.getInstance().getSrviceUrl("libraryUpdate");
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
        } catch (Exception e) {
            throw new ServiceLocatorException(e);
        }
        return message;


    }


public String downloadLibraryAttachment(LibraryAction libraryAction) throws ServiceLocatorException {
       
       
 String location ="";
        boolean isUpdated = false;
      
        try {
            
              
                JSONObject jb = new JSONObject();
                
               
        jb.put("eventId", libraryAction.getLibraryId());
      
  
       
     //    System.out.println("getConferenceUrl-->"+ajaxHandlerAction.getConferenceUrl());
         //   URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doEventPost/addEvent");
         String serviceUrl = RestRepository.getInstance().getSrviceUrl("libraryEdit");
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
         location =jObject.getString("DocLocation");
        //    System.out.println("location--"+location);
               // subJson.put("SourceLink", resultSet.getString("SourceLink"));
                
        // message = jObject.getString("message");
     


            in.close();
           
        } catch (IOException ex) {
            ex.printStackTrace();
        }catch(Exception e){
             throw new ServiceLocatorException(e);
        }
        return location;   
       
       
   }

}
