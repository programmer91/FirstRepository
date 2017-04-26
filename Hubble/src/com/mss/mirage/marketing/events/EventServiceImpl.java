/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.marketing.events;

import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.Properties;
import com.mss.mirage.util.RestRepository;
import com.mss.mirage.util.ServiceLocator;
import com.mss.mirage.util.ServiceLocatorException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONObject;

/**
 *
 * @author miracle
 */
public class EventServiceImpl implements EventService{
    
    
    
    
    
public List doInternalEventSearch(EventAction eventAction) throws ServiceLocatorException{
             List jobsList = null;
         List mainList =null;
              String createdDateFrom;
     String createdDateTo;
    
     String title;
     String status;
     
     
           try {
               
                JSONObject jb = new JSONObject();
                
              if(eventAction.getCreatedDateFrom()!=null&&!"".equals(eventAction.getCreatedDateFrom())){
                  jb.put("event_startdate", eventAction.getCreatedDateFrom());
              }else {
                  jb.put("event_startdate", "none");
              }  if(eventAction.getCreatedDateTo()!=null&&!"".equals(eventAction.getCreatedDateTo())){
                  jb.put("evetnt_enddate", eventAction.getCreatedDateTo());
              }else {
                  jb.put("evetnt_enddate", "none");
              }   if(eventAction.getTitle()!=null&&!"".equals(eventAction.getTitle())){
                  jb.put("title", eventAction.getTitle());
              }else {
                  jb.put("title", "none");
              }if(eventAction.getStatus()!=null&&!"".equals(eventAction.getStatus())){
                  jb.put("status", eventAction.getStatus());
              }else {
                  jb.put("status", "Active");
              }
             // System.out.println("Event Type-->"+eventAction.getEventSearchType());
              
              if(eventAction.getEventSearchType()!=null&&!"".equals(eventAction.getEventSearchType())){
                  jb.put("WebinarType", eventAction.getEventSearchType());
              }else {
                  jb.put("WebinarType", "none");
              }
 //System.out.println("In event search method");
               // URL url = new URL("http://localhost:8080/CrunchifyTutorials/api/crunchifyService");
                 //URL url = new URL("http://172.17.11.251:8080/WebRoot/resources/doJobPost/search");
            //  URL url = new URL("http://172.17.14.226:8080/WebRoot/resources/doJobPost/search");
           //   URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doEventPost/eventSearch");
                String serviceUrl = RestRepository.getInstance().getSrviceUrl("internalEventSearch");
               // System.out.print("serviceUrl--->"+serviceUrl);
                
                
              URL url = new URL(serviceUrl);
                URLConnection connection = url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setConnectTimeout(300*5000);
                connection.setReadTimeout(300*5000);
                OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
                out.write(jb.toString());
                out.close();
 
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
String s=null;
String data = "";
                while ((s=in.readLine()) != null) {
               //     System.out.println(s);
                    data =data+s;
                }
                
                
                
              //  System.out.println("Data-->"+data);
               JSONObject jObject  = new JSONObject(data);
               
               mainList = new ArrayList();
               for(int i=0;i<jObject.length();i++){
    JSONObject subJson  = jObject.getJSONObject(String.valueOf(i));
    Map subMap = new TreeMap();
    
    Iterator<?> subkeys = subJson.keys();
             while( subkeys.hasNext() ){
                 
            String subkey = (String)subkeys.next();
            String value = (String)subJson.get(subkey); 
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
            mainList.add( subMap);
            
            
            
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





    
    
public List doExternalEventSearch(EventAction eventAction) throws ServiceLocatorException{
             List jobsList = null;
         List mainList =null;
              String createdDateFrom;
     String createdDateTo;
    
     String title;
     String status;
     
     
           try {
               
                JSONObject jb = new JSONObject();
                
              if(eventAction.getCreatedDateFrom()!=null&&!"".equals(eventAction.getCreatedDateFrom())){
                  jb.put("event_startdate", eventAction.getCreatedDateFrom());
              }else {
                  jb.put("event_startdate", "none");
              }  if(eventAction.getCreatedDateTo()!=null&&!"".equals(eventAction.getCreatedDateTo())){
                  jb.put("evetnt_enddate", eventAction.getCreatedDateTo());
              }else {
                  jb.put("evetnt_enddate", "none");
              }   if(eventAction.getTitle()!=null&&!"".equals(eventAction.getTitle())){
                  jb.put("title", eventAction.getTitle());
              }else {
                  jb.put("title", "none");
              }if(eventAction.getStatus()!=null&&!"".equals(eventAction.getStatus())){
                  jb.put("status", eventAction.getStatus());
              }else {
                  jb.put("status", "none");
              }
             // System.out.println("Event Type-->"+eventAction.getEventSearchType());
              
              if(eventAction.getEventSearchType()!=null&&!"".equals(eventAction.getEventSearchType())){
                  jb.put("WebinarType", eventAction.getEventSearchType());
              }else {
                  jb.put("WebinarType", "none");
              }
 //System.out.println("In event search method");
               // URL url = new URL("http://localhost:8080/CrunchifyTutorials/api/crunchifyService");
                 //URL url = new URL("http://172.17.11.251:8080/WebRoot/resources/doJobPost/search");
            //  URL url = new URL("http://172.17.14.226:8080/WebRoot/resources/doJobPost/search");
           //   URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doEventPost/eventSearch");
                String serviceUrl = RestRepository.getInstance().getSrviceUrl("externalEventSearch");
               // System.out.print("serviceUrl--->"+serviceUrl);
                
                
              URL url = new URL(serviceUrl);
                URLConnection connection = url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setConnectTimeout(300*5000);
                connection.setReadTimeout(300*5000);
                OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
                out.write(jb.toString());
                out.close();
 
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
String s=null;
String data = "";
                while ((s=in.readLine()) != null) {
               //     System.out.println(s);
                    data =data+s;
                }
                
                
                
              //  System.out.println("Data-->"+data);
               JSONObject jObject  = new JSONObject(data);
               
               mainList = new ArrayList();
               for(int i=0;i<jObject.length();i++){
    JSONObject subJson  = jObject.getJSONObject(String.valueOf(i));
    Map subMap = new TreeMap();
    
    Iterator<?> subkeys = subJson.keys();
             while( subkeys.hasNext() ){
                 
            String subkey = (String)subkeys.next();
            String value = (String)subJson.get(subkey); 
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
            mainList.add( subMap);
            
            
            
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




/*
 * Emeet methods
 * Date : 01/29/2016
 * Author : Phani Kanuri
 */

  public List doInternalEmeetSearch(EventAction eventAction) throws ServiceLocatorException {
       
        List mainList = null;
       


        try {

            JSONObject jb = new JSONObject();

            if (eventAction.getEmeetFromDate() != null && !"".equals(eventAction.getEmeetFromDate())) {
                jb.put("EmeetFromDate", eventAction.getEmeetFromDate());
            } else {
                jb.put("EmeetFromDate", "none");
            }
            if (eventAction.getEmeetToDate() != null && !"".equals(eventAction.getEmeetToDate())) {
                jb.put("EmeetToDate", eventAction.getEmeetToDate());
            } else {
                jb.put("EmeetToDate", "none");
            }
            if (eventAction.getEmeetType() != null && !"".equals(eventAction.getEmeetType())) {
                jb.put("EMeetType", eventAction.getEmeetType());
            } else {
                jb.put("EMeetType", "none");
            }
            if(eventAction.getExecutiveMeetMonthSearch() != null&&!"".equals(eventAction.getExecutiveMeetMonthSearch())) {
                jb.put("ExecutiveMeetMonthSearch", eventAction.getExecutiveMeetMonthSearch());
            } else {
                jb.put("ExecutiveMeetMonthSearch", "none");
            }
                jb.put("EmeetStatusByDate",eventAction.getEmeetStatusByDate());
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
            connection.setConnectTimeout(300*5000);
            connection.setReadTimeout(300*5000);
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



          //   System.out.println("Data-->"+data);
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

    
    /*

    public String doPublishInternalExeMeet(EventAction eventAction) throws ServiceLocatorException {


        String message = "";


        try {
            JSONObject jb = new JSONObject();

            jb.put("Id", eventAction.getId());
            jb.put("CreatedBy", eventAction.getCreatedBy());

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
                    htmlText.append("                     <h2 style='font-size: 25px;'><font color='#ffffff' face='calibri'><b>"+ TYPE +"</b></font></h2>");
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
                    htmlText.append("   We are pleased to invite you for the <b>" + TYPE + "</b> for the month of <b>" + Month + "</b> on <b>"+DateUtility.getInstance().getDateWithMonthName(EMeetDate)+"</b> at <b>" + StartTime + " " + MidDayFrom + " " + TimeZone + "</b>. The meet will cover the progress of our teams over the last month and will help all of us to ensure that we are on the same road to success. ");
                    htmlText.append("<br><br> Please register today to book your slot for the executive meet. For past Executive Meet replays please visit <a href='http://www.miraclesoft.com/emeets' target='blank'; style= 'color :#2368a0';><b>www.miraclesoft.com/emeets</b></a> ");
                    htmlText.append("   <br><br>Please do not forward this email to anyone else, and any passing of this registration information will be considered as a corporate violation. For adding (or) removing members from the meet, please contact <a href=mailto:marketing@miraclesoft.com><b>marketing@miraclesoft.com</b></a><br><br><span style='font-family: calibri; font-size: 14px; color:#ef4048; font-weight: normal;'>");
                    htmlText.append("  <b>");
                    htmlText.append("  *Note: ");
                    htmlText.append("    </b>");
                    htmlText.append("   </span>");
                    htmlText.append("   <span style='font-family: calibri; font-size: 14px; color:#232527; font-weight: normal;'>");
                    if("Global Sales Meet".equals(TYPE)){
                         htmlText.append("    <i>The Global Sales Meet will be held on the 3rd working day after the 2nd of every month</i></span>");
                    }else if("Global Practice Meet".equals(TYPE)){
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
                    htmlText.append("      <a href='"+EMeetRegistrationLink+"' target='blank' style='text-decoration: none; font-style: normal; font-weight: normal; font-family:calibri; color:#ffffff;'>");
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
                    ServiceLocator.getMailServices().doAddEmailLogNew(toAddress, cCAddress, "Reg:"+TYPE, bodyContent, createdDate, bCCAddress, "Executive Meet");

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
    }*/
public String doPublishInternalExeMeet(EventAction eventAction) throws ServiceLocatorException {


        String message = "";
 Calendar cal = Calendar.getInstance();
    int year = cal.get(cal.YEAR);

        try {
            JSONObject jb = new JSONObject();

            jb.put("Id", eventAction.getId());
            jb.put("CreatedBy", eventAction.getCreatedBy());

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
                    htmlText.append("            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style='font-family:calibri; Helvetica; font-weight: normal;'><a href='http://www.miraclesoft.com/careers' target='_blank' style='text-decoration: none; color:#ffffff;' class='underline'>Careers</a></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
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
                       // htmlText.append("    <i>The global sales meet will be held on the First Thursday after the 9th of every month</i></span>");
                     htmlText.append("    <i>The Global Sales Meet will be held on the 5th working day after the 2nd of every month</i></span>");
                    } else if ("Global Practice Meet".equals(TYPE)) {
                        htmlText.append("    <i> The global practice ceet will be held on the First Tuesday after the 8th of every month</i></span>");
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
                   // htmlText.append(" (+1)248-232-0426");
                         htmlText.append(" +1(248)412-0428");    
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


                    htmlText.append("  <div class='movableContent'>");
                    htmlText.append("  <table width='660' border='0' cellspacing='0' cellpadding='0' align='center'>");
                    htmlText.append("   <tbody>");
                    htmlText.append("     <tr>");
                    htmlText.append("     <td style='background:#0d416b; border-radius:0px;-moz-border-radius:0px;-webkit-border-radius:0px'>");
                    htmlText.append("    <table width='655' border='0' cellspacing='0' cellpadding='0' align='center'>");
                    htmlText.append("   <tbody>");
                    htmlText.append("     <tr>");
                    htmlText.append("     <td colspan='3' height='20'></td>");
                    htmlText.append("   </tr>");
                    htmlText.append("     <tr>");
                    htmlText.append("    <td width='90'></td>");
                    htmlText.append("    <td width='660' align='center' style='text-align: center;'>");
                    htmlText.append("   <table width='660' cellpadding='0' cellspacing='0' align='center'>");
                    htmlText.append("   <tbody>");
                    htmlText.append("     <tr>");
                    htmlText.append("    <td>");
                    htmlText.append("       <div class='contentEditableContainer contentTextEditable'>");
                    htmlText.append("      <div class='contentEditable' style='text-align: center;color:#AAAAAA;'>");
                    htmlText.append("     <p style='text-align: center; font-size: 14px;'><font color='#ffffff' face='calibri'>Â© Copyright "+year+" Miracle Software Systems, Inc.<br>        45625 Grand River Avenue<br> Novi, MI - USA       </font>    </p>");
                    htmlText.append("    <font color='#ffffff' face='calibri'>  </font>");
                    htmlText.append("   </div>");
                    htmlText.append("                                                                         <font color='#ffffff' face='calibri'>   </font> ");
                    htmlText.append("                                                                      </div>");
                    htmlText.append("                                                                      <font color='#ffffff' face='calibri'>  </font>   ");
                    htmlText.append("                                                                     </td>");
                    htmlText.append("                                                                </tr>");
                    htmlText.append("                                                             </tbody>");
                    htmlText.append("                                                          </table>");
                    htmlText.append("                                                       </td>");
                    htmlText.append("                                                       <td width='90'></td>");
                    htmlText.append("                                                    </tr>");
                    htmlText.append("                                                 </tbody>");
                    htmlText.append("                                              </table>");
                    htmlText.append("                                              <table width='650' border='0' cellspacing='0' cellpadding='0' align='center'>");
                    htmlText.append("                                                 <tbody>");
                    htmlText.append("                                                    <tr>");
                    htmlText.append("                                                       <td colspan='3' height='20'></td>");
                    htmlText.append("                                                    </tr>");
                    htmlText.append("                                                    <tr>");
                    htmlText.append("                                                       <td width='160'></td>");
                    htmlText.append("                                                       <td width='260' align='center' style='text-align: center;'>");
                    htmlText.append("                                                          <table width='260' cellpadding='0' cellspacing='0' align='center'>");
                    htmlText.append("                                                             <tbody>");
                    htmlText.append("                                                                <tr>");
                    htmlText.append("                                                                   <td width='40'>");
                    htmlText.append("                                                                      <div class='contentEditableContainer contentFacebookEditable'>");
                    htmlText.append("                                                                         <div class='contentEditable' style='text-align: center;color:#AAAAAA;'>         <a href='https://www.facebook.com/miracle45625' target='_blank'><img src='http://www.miraclesoft.com/newsletters/others/invite_interconnect_2015/images/fb.png' alt='facebook' width='32' height='32' data-max-width='40' data-customicon='true'></a>     </div>");
                    htmlText.append("                                                                      </div>");
                    htmlText.append("                                                                   </td>");
                    htmlText.append("                                                                   <td width='10'></td>");
                    htmlText.append("                                                                   <td width='40'>");
                    htmlText.append("                                                                      <div class='contentEditableContainer contentTwitterEditable'>");
                    htmlText.append("                                                                         <div class='contentEditable' style='text-align: center;color:#AAAAAA;'>         <a href='https://twitter.com/team_mss' target='_blank'><img src='http://www.miraclesoft.com/newsletters/others/invite_interconnect_2015/images/tweet.png' alt='twitter' width='32' height='32' data-max-width='40' data-customicon='true'></a>      </div>");
                    htmlText.append("                                                                      </div>");
                    htmlText.append("                                                                   </td>                                                                  <td width='10'></td>");
                    htmlText.append("                                                                   <td width='40'>");
                    htmlText.append("                                                                      <div class='contentEditableContainer contentTwitterEditable'>");
                    htmlText.append("                                                                         <div class='contentEditable' style='text-align: center;color:#AAAAAA;'>         <a href='https://twitter.com/team_mss' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/ye.png' alt='twitter' width='32' height='32' data-max-width='40' data-customicon='true'></a>      </div>");
                    htmlText.append("                                                                      </div>");
                    htmlText.append("                                                                   </td>");
                    htmlText.append("                                                                                                                                    <td width='10'></td>");
                    htmlText.append("                                                                   <td width='40'>");
                    htmlText.append("                                                                      <div class='contentEditableContainer contentTwitterEditable'>");
                    htmlText.append("                                                                         <div class='contentEditable' style='text-align: center;color:#AAAAAA;'>         <a href='https://twitter.com/team_mss' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/fr.png' alt='twitter' width='32' height='32' data-max-width='40' data-customicon='true'></a>      </div>");
                    htmlText.append("                                                                      </div>");
                    htmlText.append("                                                                   </td>");
                    htmlText.append("                                                                  ");
                    htmlText.append("                                                                   <td width='10'></td>");
                    htmlText.append("                                                                   <td width='40'>");
                    htmlText.append("                                                                      <div class='contentEditableContainer contentImageEditable'>");
                    htmlText.append("                                                                         <div class='contentEditable' style='text-align: center;color:#AAAAAA;'>     <a href='https://plus.google.com/+Team_MSS/posts' target='_blank'><img src='http://www.miraclesoft.com/newsletters/others/invite_interconnect_2015/images/googleplus.png' alt='Pinterest' width='32' height='32' data-max-width='40'></a>    </div>");
                    htmlText.append("                                                                      </div>");
                    htmlText.append("                                                                   </td>");
                    htmlText.append("                                                                   <td width='10'></td>");
                    htmlText.append("                                                                   <td width='40'>");
                    htmlText.append("                                                                      <div class='contentEditableContainer contentImageEditable'>");
                    htmlText.append("                                                                         <div class='contentEditable' style='text-align: center;color:#AAAAAA;'>         <a href='https://www.linkedin.com/company/miracle-software-systems-inc' target='_blank'><img src='http://www.miraclesoft.com/newsletters/others/invite_interconnect_2015/images/linkedin.png' alt='Social media' width='32' height='32' data-max-width='40'></a>      </div>");
                    htmlText.append("                                                                      </div>");
                    htmlText.append("                                                                   </td>");
                    htmlText.append("                                                                </tr>");
                    htmlText.append("                                                             </tbody>");
                    htmlText.append("                                                          </table>");
                    htmlText.append("                                                       </td>");
                    htmlText.append("                                                       <td width='140'></td>");
                    htmlText.append("                                                    </tr>");
                    htmlText.append("                                                    <tr>");
                    htmlText.append("                                                       <td colspan='3' height='40'></td>");
                    htmlText.append("                                                    </tr>");
                    htmlText.append("                                                 </tbody>");
                    htmlText.append("                                              </table>");
                    htmlText.append("                                           </td>");
                    htmlText.append("                                        </tr>");
                    htmlText.append("                                     </tbody>");
                    htmlText.append("                                  </table>");
                    htmlText.append("                               </div>");

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
    public String doActiveInternalExeMeet(EventAction eventAction) throws ServiceLocatorException {


        String message = "";


        try {
            JSONObject jb = new JSONObject();
            // AjaxHandlerAction ac=new AjaxHandlerAction();

            jb.put("ExeMeetId", eventAction.getId());

            jb.put("CreatedBy", eventAction.getCreatedBy());
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



    
    public List getQmeetAttendees(EventAction eventAction) throws ServiceLocatorException{
         List jobsList = null;
         List mainList =null;
              String createdDateFrom;
     String createdDateTo;
     String jobId;
     String title;
     String status;
      Map subMap =null;
    // boolean primaryExist = false;
    
           try {
               
                JSONObject jb = new JSONObject();
                 
              //  if(marketingAction.getEventId()!=null&&!"".equals(marketingAction.getEventId())){
                  
                  
                  if(eventAction.getCreatedDateFrom()!=null && !"".equals(eventAction.getCreatedDateFrom()))
                      jb.put("createdDateFrom", eventAction.getCreatedDateFrom());
                  else
                      jb.put("createdDateFrom", "none");
                  
                   if(eventAction.getCreatedDateTo()!=null && !"".equals(eventAction.getCreatedDateTo()))
                      jb.put("createdDateTo", eventAction.getCreatedDateTo());
                  else
                      jb.put("createdDateTo", "none");
                  
                   
                      jb.put("eventId", eventAction.getEventId());
                  
                   
             
                   
                   
                   
                   

            
                
 
               // URL url = new URL("http://localhost:8080/CrunchifyTutorials/api/crunchifyService");
                 //URL url = new URL("http://172.17.11.251:8080/WebRoot/resources/doJobPost/search");
              //URL url = new URL("http://172.17.14.226:8080/WebRoot/resources/doJobPost/appliedList");
               // URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doJobPost/appliedList");
              
              String serviceUrl = RestRepository.getInstance().getSrviceUrl("internalQmeetAttendeeSearch");
              URL url = new URL(serviceUrl);
                URLConnection connection = url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setConnectTimeout(300*5000);
                connection.setReadTimeout(300*5000);
                OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
                out.write(jb.toString());
                out.close();
 
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
String s=null;
String data = "";
                while ((s=in.readLine()) != null) {
               //     System.out.println(s);
                    data =data+s;
                }
                
                
                
              //  System.out.println("Data-->"+data);
               JSONObject jObject  = new JSONObject(data);
               
               
              // System.out.println("jObject-->"+jObject);
                 mainList = new ArrayList();
                 //------------------
                 
                 for(int i=0;i<jObject.length();i++){
    JSONObject subJson  = jObject.getJSONObject(String.valueOf(i));
     subMap = new HashMap();
    
    Iterator<?> subkeys = subJson.keys();
             while( subkeys.hasNext() ){
            String subkey = (String)subkeys.next();
            String value = (String)subJson.get(subkey); 
            
             subMap.put(subkey, value);
             
             }
                   mainList.add( subMap);
                 
               

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
    
    public List doExecuteMeetAttendeesSearch(EventAction eventAction) throws ServiceLocatorException {

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
                    + "LEFT JOIN tblEmployee ON(tblEmployee.LoginId=tblEAttendees.LoginId) WHERE tblEmployee.CurStatus = 'Active' ";
            if (eventAction.getEmeetAccessType() != null && !"".equals(eventAction.getEmeetAccessType())) {
                queryString = queryString + " and tblEAttendees.AccessType = '" + eventAction.getEmeetAccessType() + "'";
            }
            if (eventAction.getEmeetAttendeesEmail() != null && !"".equals(eventAction.getEmeetAttendeesEmail())) {
                queryString = queryString + " and Email1 like '%" + eventAction.getEmeetAttendeesEmail() + "%'";
            }
            if (eventAction.getEmeetMeetingAccessStatus() != null && !"".equals(eventAction.getEmeetMeetingAccessStatus())) {
                queryString = queryString + " and CurrStatus = '" + eventAction.getEmeetMeetingAccessStatus() + "'";
            }
            queryString = queryString + " ORDER BY tblEAttendees.Id DESC";
           // System.out.println("queryString--"+queryString);
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
    
}
