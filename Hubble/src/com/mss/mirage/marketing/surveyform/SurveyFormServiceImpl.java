/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.marketing.surveyform;

import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.RestRepository;
import com.mss.mirage.util.ServiceLocatorException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONObject;

/**
 *
 * @author miracle
 */
public class SurveyFormServiceImpl implements SurveyFormService{
   public String doAddSurveyForm(SurveyFormAction surveyFormAction) throws ServiceLocatorException{
         // System.out.println("--------in SurveyFormServiceImpl-----------"); 
 String message ="";
    
      
        try {
                JSONObject jb = new JSONObject();
       
        jb.put("Title", surveyFormAction.getSurveyTitle());
        jb.put("SurveyType", surveyFormAction.getSurveyType());
        jb.put("Description", surveyFormAction.getSurveyDescription());
      
            jb.put("ExpiryDate", surveyFormAction.getExpiryDate());
            jb.put("CurrStatus", surveyFormAction.getCurrStatus());
            jb.put("CreatedBy", surveyFormAction.getCreatedBy());
            jb.put("customCheckBox", surveyFormAction.getCustomCheckBox());
            jb.put("anonymousCheckBox", surveyFormAction.getAnonymousCheckBox());
           
         //   System.out.println("customCheckBox-->" + surveyFormAction.getCustomCheckBox());
        if(surveyFormAction.getCustomCheckBox())
        {
            jb.put("customizedTextBox", surveyFormAction.getSurveyFormCustomizedTextBox());
         //   System.out.println("customizedTextBox-->" + surveyFormAction.getSurveyFormCustomizedTextBox());
        }
        else{
            jb.put("customizedTextBox", "");
        }
         jb.put("allowDuplicates", surveyFormAction.getAllowDuplicates());
        jb.put("maxSubmissions", surveyFormAction.getMaxSubmissions());
         String serviceUrl = RestRepository.getInstance().getSrviceUrl("surveyFormAdd");
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

 public List doSurveyFormSearch(SurveyFormAction surveyFormAction,HttpServletRequest httpServletRequest) throws ServiceLocatorException {
       // System.out.println("in do survey form search");
        List mainList = null;
        try {

            JSONObject jb = new JSONObject();

            if (surveyFormAction.getCreatedDateFrom() != null && !"".equals(surveyFormAction.getCreatedDateFrom())) {
                jb.put("CreatedDateFrom", surveyFormAction.getCreatedDateFrom());
            } else {
                jb.put("CreatedDateFrom", "none");
            }
            if (surveyFormAction.getCreatedDateTo() != null && !"".equals(surveyFormAction.getCreatedDateTo())) {
                jb.put("CreatedDateTo", surveyFormAction.getCreatedDateTo());
            } else {
                jb.put("CreatedDateTo", "none");
            }
            if (surveyFormAction.getSurveyTitle() != null && !"".equals(surveyFormAction.getSurveyTitle())) {
                jb.put("Title", surveyFormAction.getSurveyTitle());
            } else {
                jb.put("Title", "none");
            }
           
           //  Map myTeamMemebrs = (Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP_FOR_LEAVESEARCH);
                        String teamList = DataSourceDataProvider.getInstance().getTeamLoginIdList( surveyFormAction.getTeamMap());
                       // System.out.println("teamList-->" + teamList);
                      //  String teamMap = teamList.replaceAll("'", "");
                       // System.out.println("replace-->" + teamMap);
//             jb.put("LoginId", surveyFormAction.getLoginId());
//             jb.put("TeamMap",teamList);
                        
                int isTeamLead = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_TEAM_LEAD).toString());
            int isManager = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString());
           Map rolesMap = (Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_ROLES);
          // System.out.println("rolesMap-->"+rolesMap) ;
          // System.out.println("isTeamLead-->"+isTeamLead) ;
          // System.out.println("isManager-->"+isManager) ;
         //  System.out.println("Admin-->"+rolesMap.containsValue("Admin")) ;
           if(rolesMap.containsValue("Admin")){
                jb.put("SearchType","Admin");
            }
            else if(isTeamLead==1||isManager==1){
               teamList =  teamList + ",'"+surveyFormAction.getLoginId()+"'";
               jb.put("SearchType","Team");
               jb.put("TeamMap",teamList);
            }else {
                jb.put("SearchType","My");
                jb.put("LoginId", surveyFormAction.getLoginId());
            }
            
           //  System.out.println("teamList111-->"+teamList) ;
           // System.out.println("SearchType-->"+jb.getString("SearchType")) ;
                     
                        
          // String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            
            String serviceUrl = RestRepository.getInstance().getSrviceUrl("doSurveyFormSearch");

           // System.out.println("serviceUrl-->" + serviceUrl);
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
            System.out.println(e);
        }
        // System.out.println("mainList-->"+mainList);
        return mainList;
    }


public void editSurveyForm(SurveyFormAction surveyFormAction) throws ServiceLocatorException {

        try {
                JSONObject jb = new JSONObject();
    
        jb.put("surveyId", surveyFormAction.getSurveyId());
      String serviceUrl = RestRepository.getInstance().getSrviceUrl("editSurveyForm");
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

                data = data + s;
            }

        JSONObject jObject = new JSONObject(data);
        surveyFormAction.setSurveyId(jObject.getString("Id"));
        surveyFormAction.setSurveyTitle(jObject.getString("Title"));
        surveyFormAction.setSurveyType(jObject.getString("SurveyType"));
        surveyFormAction.setSurveyDescription(jObject.getString("Description"));
        surveyFormAction.setCurrStatus(jObject.getString("CurrStatus"));
        surveyFormAction.setCreatedBy(jObject.getString("CreatedBy"));
        surveyFormAction.setCreatedDate(jObject.getString("CreatedDate"));
        surveyFormAction.setExpiryDate(jObject.getString("ExpiryDate"));
       surveyFormAction.setCustomCheckBox(jObject.getBoolean("IsCustomMessage"));
surveyFormAction.setSurveyFormCustomizedTextBox(jObject.getString("CustomMessage"));
surveyFormAction.setAnonymousCheckBox(jObject.getBoolean("IsAnonymous"));
surveyFormAction.setMaxSubmissions(jObject.getInt("MaximumSubmissions"));
surveyFormAction.setAllowDuplicates(jObject.getBoolean("AllowDuplicates"));
       
            in.close();
           
        } catch (IOException ex) {
            ex.printStackTrace();
        }catch(Exception e){
             throw new ServiceLocatorException(e);
        }
   }

public String doUpdateSurveyForm(SurveyFormAction surveyFormAction) throws ServiceLocatorException {

   // System.out.println("-----in doUpdateSurveyForm impl --------");
        String message = "";
        

        try {
            JSONObject jb = new JSONObject();
            jb.put("Id", surveyFormAction.getSurveyId());
            jb.put("Title", surveyFormAction.getSurveyTitle());
            jb.put("SurveyType", surveyFormAction.getSurveyType());
            jb.put("Description", surveyFormAction.getSurveyDescription());

            jb.put("ExpiryDate", surveyFormAction.getExpiryDate());
            jb.put("CurrStatus", surveyFormAction.getCurrStatus());
            jb.put("CreatedBy", surveyFormAction.getCreatedBy());
            jb.put("IsCustomMessage", surveyFormAction.getCustomCheckBox());
            if (surveyFormAction.getCustomCheckBox()) {
              //  System.out.println("in if check box");
               jb.put("customMessage", surveyFormAction.getSurveyFormCustomizedTextBox());
                
            } else {
                jb.put("customMessage", "");
            }
             jb.put("IsAnonymous", surveyFormAction.getAnonymousCheckBox());
               jb.put("allowDuplicates", surveyFormAction.getAllowDuplicates());
        jb.put("maxSubmissions", surveyFormAction.getMaxSubmissions());
//            if (surveyFormAction.getSurveyFormCustomizedTextBox() != null && !"".equals(surveyFormAction.getSurveyFormCustomizedTextBox())) {
//                System.out.println("in else custom text box");
//                jb.put("customMessage", surveyFormAction.getSurveyFormCustomizedTextBox());
//            } else {
//                jb.put("customMessage", "");
//            }
            
            
            
          //  jb.put("customCheckBox", surveyFormAction.getCustomCheckBox());
        //    System.out.println("customCheckBox-->" + surveyFormAction.getSurveyFormCustomizedTextBox());
     
            
            
            String serviceUrl = RestRepository.getInstance().getSrviceUrl("doUpdateSurveyForm");
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

public List getOptionTypeList() throws ServiceLocatorException {
        
        List mainList = null;
        try {

            JSONObject jb = new JSONObject();

          
            

            String serviceUrl = RestRepository.getInstance().getSrviceUrl("getOptionTypeList");

           // System.out.println("serviceUrl-->" + serviceUrl);
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

            JSONObject jObject = new JSONObject(data);

            mainList = new ArrayList();
            for (int i = 0; i < jObject.length(); i++) {
                JSONObject subJson = jObject.getJSONObject(String.valueOf(i));
                

               

                mainList.add(subJson.getString("FIELD"));


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

public String doAddQuestionnaire(SurveyFormAction surveyFormAction) throws ServiceLocatorException{
           
 String message ="";
    
      
        try {
                JSONObject jb = new JSONObject();
       
        jb.put("Query", surveyFormAction.getQuestionTitle());
        jb.put("OptionType", surveyFormAction.getOptionType());
        jb.put("SurveyId", surveyFormAction.getSurveyId());
        jb.put("Sequence", surveyFormAction.getQuerySequence());
        if("Checkbox".equals(surveyFormAction.getOptionType())||"RadioButton".equals(surveyFormAction.getOptionType())){
            jb.put("OptionLabel1", surveyFormAction.getOptionLabel1());
            jb.put("OptionSequence1", surveyFormAction.getOptionSequence1());
             jb.put("OptionCount", surveyFormAction.getOptionCount());
            
            if(surveyFormAction.getOptionCount()>1){
                 jb.put("OptionLabel2", surveyFormAction.getOptionLabel2());
            jb.put("OptionSequence2", surveyFormAction.getOptionSequence2());
            } if(surveyFormAction.getOptionCount()>2){
                 jb.put("OptionLabel3", surveyFormAction.getOptionLabel3());
            jb.put("OptionSequence3", surveyFormAction.getOptionSequence3());
            }if(surveyFormAction.getOptionCount()>3){
                 jb.put("OptionLabel4", surveyFormAction.getOptionLabel4());
            jb.put("OptionSequence4", surveyFormAction.getOptionSequence4());
            }if(surveyFormAction.getOptionCount()>4){
                 jb.put("OptionLabel5", surveyFormAction.getOptionLabel5());
            jb.put("OptionSequence5", surveyFormAction.getOptionSequence5());
            }if(surveyFormAction.getOptionCount()>5){
                 jb.put("OptionLabel6", surveyFormAction.getOptionLabel6());
            jb.put("OptionSequence6", surveyFormAction.getOptionSequence6());
            }
        }
        
     
         String serviceUrl = RestRepository.getInstance().getSrviceUrl("doAddQuestionnaire");
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
     public List getQuestionnaireList(SurveyFormAction surveyFormAction) throws ServiceLocatorException {
        
        List mainList = null;
        try {

            JSONObject jb = new JSONObject();

           
                jb.put("SurveyId", surveyFormAction.getSurveyId());
           
            

            String serviceUrl = RestRepository.getInstance().getSrviceUrl("getQuestionnaireList");

           // System.out.println("serviceUrl-->" + serviceUrl);
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

            JSONObject jObject = new JSONObject(data);

            mainList = new ArrayList();
          //  for (int i = 0; i < jObject.length(); i++) {
              for (int i = 0; i < jObject.length()-1; i++) {
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
             // System.out.println("Jobject length-->"+jObject.length());
            //  System.out.println("Question Count111-->"+jObject.getInt(String.valueOf((jObject.length()-1))));
              surveyFormAction.setTotalQuestions(jObject.getInt(String.valueOf((jObject.length()-1))));
              
            //  System.out.println("Question Count-->"+surveyFormAction.getTotalQuestions());
            //  System.out.println("\nREST Service Invoked Successfully..");
            in.close();
        } catch (Exception e) {
            System.out.println("\nError while calling REST Service");
            System.out.println(e);
        }
        // System.out.println("mainList-->"+mainList);
        return mainList;
    }
     public void editQuestionnaireDetails(SurveyFormAction surveyFormAction) throws ServiceLocatorException {

        try {
                JSONObject jb = new JSONObject();
    
        jb.put("SurveyId", surveyFormAction.getSurveyId());
        jb.put("QuestionId", surveyFormAction.getQuestionId());
      String serviceUrl = RestRepository.getInstance().getSrviceUrl("editQuestionnaireDetails");
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

                data = data + s;
            }

        JSONObject jObject = new JSONObject(data);
        surveyFormAction.setQuestionId(jObject.getString("Id"));
        surveyFormAction.setQuestionTitle(jObject.getString("Query"));
        surveyFormAction.setOptionType(jObject.getString("OptionType"));
        surveyFormAction.setCurrStatus(jObject.getString("CurrStatus"));
        surveyFormAction.setQuerySequence(jObject.getString("Sequence"));
        surveyFormAction.setSurveyId(jObject.getString("SurveyId"));
        
      if("Checkbox".equals(jObject.getString("OptionType"))||"RadioButton".equals(jObject.getString("OptionType"))){
            
            surveyFormAction.setOptionLabel1(jObject.getString("OptionLabel1"));
            surveyFormAction.setOptionSequence1(jObject.getInt("OptionSequence1"));
            surveyFormAction.setOptionCount(jObject.getInt("OptionCount"));
            if(jObject.getInt("OptionCount")>1){
             surveyFormAction.setOptionLabel2(jObject.getString("OptionLabel2"));   
             surveyFormAction.setOptionSequence2(jObject.getInt("OptionSequence2"));
             //OptionSequence1
            }if(jObject.getInt("OptionCount")>2){
             surveyFormAction.setOptionLabel3(jObject.getString("OptionLabel3"));   
             surveyFormAction.setOptionSequence3(jObject.getInt("OptionSequence3"));
            }if(jObject.getInt("OptionCount")>3){
             surveyFormAction.setOptionLabel4(jObject.getString("OptionLabel4"));  
             surveyFormAction.setOptionSequence4(jObject.getInt("OptionSequence4"));
            }if(jObject.getInt("OptionCount")>4){
             surveyFormAction.setOptionLabel5(jObject.getString("OptionLabel5"));   
             surveyFormAction.setOptionSequence5(jObject.getInt("OptionSequence5"));
            }if(jObject.getInt("OptionCount")>5){
             surveyFormAction.setOptionLabel6(jObject.getString("OptionLabel6"));   
             surveyFormAction.setOptionSequence6(jObject.getInt("OptionSequence6"));
            }
          
        }
       
            in.close();
           
        } catch (IOException ex) {
            ex.printStackTrace();
        }catch(Exception e){
             throw new ServiceLocatorException(e);
        }
   }

public String doUpdateQuestionnaire(SurveyFormAction surveyFormAction) throws ServiceLocatorException{
           
 String message ="";
    
      
        try {
                JSONObject jb = new JSONObject();
       jb.put("QuestionId", surveyFormAction.getQuestionId());
        jb.put("Query", surveyFormAction.getQuestionTitle());
        jb.put("OptionType", surveyFormAction.getOptionType());
        jb.put("SurveyId", surveyFormAction.getSurveyId());
        jb.put("Sequence", surveyFormAction.getQuerySequence());
        if("Checkbox".equals(surveyFormAction.getOptionType())||"RadioButton".equals(surveyFormAction.getOptionType())){
            jb.put("OptionLabel1", surveyFormAction.getOptionLabel1());
            jb.put("OptionSequence1", surveyFormAction.getOptionSequence1());
             jb.put("OptionCount", surveyFormAction.getOptionCount());
            
            if(surveyFormAction.getOptionCount()>1){
                 jb.put("OptionLabel2", surveyFormAction.getOptionLabel2());
            jb.put("OptionSequence2", surveyFormAction.getOptionSequence2());
            } if(surveyFormAction.getOptionCount()>2){
                 jb.put("OptionLabel3", surveyFormAction.getOptionLabel3());
            jb.put("OptionSequence3", surveyFormAction.getOptionSequence3());
            }if(surveyFormAction.getOptionCount()>3){
                 jb.put("OptionLabel4", surveyFormAction.getOptionLabel4());
            jb.put("OptionSequence4", surveyFormAction.getOptionSequence4());
            }if(surveyFormAction.getOptionCount()>4){
                 jb.put("OptionLabel5", surveyFormAction.getOptionLabel5());
            jb.put("OptionSequence5", surveyFormAction.getOptionSequence5());
            }if(surveyFormAction.getOptionCount()>5){
                 jb.put("OptionLabel6", surveyFormAction.getOptionLabel6());
            jb.put("OptionSequence6", surveyFormAction.getOptionSequence6());
            }
        }
        
     
         String serviceUrl = RestRepository.getInstance().getSrviceUrl("doUpdateQuestionnaire");
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

/*Method For Suvey form Review List
 * Author : Santosh Kola
 * Date : 08/31/2015
 */
public List getSurveyReviewList(SurveyFormAction surveyFormAction) throws ServiceLocatorException {
        
        List mainList = null;
        try {

            JSONObject jb = new JSONObject();

           
                jb.put("SurveyId", surveyFormAction.getSurveyId());
           
            

            String serviceUrl = RestRepository.getInstance().getSrviceUrl("getSurveyReviewList");

           // System.out.println("serviceUrl-->" + serviceUrl);
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
//System.out.println("data-->"+data);
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
            System.out.println(e);
        }
        // System.out.println("mainList-->"+mainList);
        return mainList;
    }

public String doSurveyAttachmentDownload(int surveyInfoId) throws ServiceLocatorException {
       
       
 String location ="";
        boolean isUpdated = false;
      
        try {
            
              
                JSONObject jb = new JSONObject();
                
               
        jb.put("SurveyInfoId", surveyInfoId);
      
  
       
     //    System.out.println("getConferenceUrl-->"+ajaxHandlerAction.getConferenceUrl());
         //   URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doEventPost/addEvent");
         String serviceUrl = RestRepository.getInstance().getSrviceUrl("getSurveyFormAttachmentLocation");
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

                data = data + s;
            }

        JSONObject jObject = new JSONObject(data);
         location =jObject.getString("Location");
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
//getSurveyFormInfoDetails
public List getSurveyFormInfoDetails(SurveyFormAction surveyFormAction) throws ServiceLocatorException {
List mainList = null;
        try {
                JSONObject jb = new JSONObject();
    
        
          jb.put("surveyInfoId", surveyFormAction.getSurveyInfoId());
      String serviceUrl = RestRepository.getInstance().getSrviceUrl("getSurveyInfoDetails");
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

                data = data + s;
            }

        JSONObject jObject = new JSONObject(data);
       

            mainList = new ArrayList();
          //  for (int i = 0; i < jObject.length(); i++) {
              //  JSONObject subJson = jObject.getJSONObject(String.valueOf(i));
              JSONObject subJson = jObject;
                Map subMap = new TreeMap();

                Iterator<?> subkeys = subJson.keys();
                while (subkeys.hasNext()) {

                    String subkey = (String) subkeys.next();
                    String value = (String) subJson.get(subkey);
                    // subList.add(value);
                    subMap.put(subkey, value);
                }
//System.out.println("subMap-->"+subMap);
                mainList.add(subMap);
            in.close();
          //  }
        } catch (IOException ex) {
            ex.printStackTrace();
        }catch(Exception e){
             throw new ServiceLocatorException(e);
        }
        return mainList;
   }
 public String doPublishSurveyForm(SurveyFormAction surveyFormAction) throws ServiceLocatorException {


        String message = "";
        

        try {
            JSONObject jb = new JSONObject();
            
            jb.put("Id", surveyFormAction.getSurveyId());
            jb.put("CurrStatus", "Published");
            jb.put("CreatedBy", surveyFormAction.getCreatedBy());
         
            String serviceUrl = RestRepository.getInstance().getSrviceUrl("doUpdateSurveyFormStatus");
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
 
 public List getSurveyReviewSearchList(SurveyFormAction surveyFormAction) throws ServiceLocatorException {
        
        List mainList = null;
        try {

            JSONObject jb = new JSONObject();

           
                jb.put("SurveyId", surveyFormAction.getSurveyId());
               
           
              if (surveyFormAction.getFullName() != null && !"".equals(surveyFormAction.getFullName())) {
                jb.put("userName", surveyFormAction.getFullName());
            } else {
                jb.put("userName", "none");
            }  if (surveyFormAction.getEmailId() != null && !"".equals(surveyFormAction.getEmailId())) {
                jb.put("emailId", surveyFormAction.getEmailId());
            } else {
                jb.put("emailId", "none");
            }if (surveyFormAction.getPhoneNumber() != null && !"".equals(surveyFormAction.getPhoneNumber())) {
                jb.put("phone", surveyFormAction.getPhoneNumber());
            } else {
                jb.put("phone", "none");
            }
              if(surveyFormAction.getCreatedDateFrom()!=null && !"".equals(surveyFormAction.getCreatedDateFrom()))
                      jb.put("createdDateFrom", surveyFormAction.getCreatedDateFrom());
                  else
                      jb.put("createdDateFrom", "none");
                  
                   if(surveyFormAction.getCreatedDateFrom()!=null && !"".equals(surveyFormAction.getCreatedDateFrom()))
                      jb.put("createdDateTo", surveyFormAction.getCreatedDateTo());
                  else
                      jb.put("createdDateTo", "none");
                   
                   
                   
                   if(!"".equals(surveyFormAction.getSearchQuestionId())){
                       jb.put("SearchQuestionId", surveyFormAction.getSearchQuestionId());
                         if(surveyFormAction.getSearchQuestion()!=null && !"".equals(surveyFormAction.getSearchQuestion()))
                      jb.put("SearchQuestion", surveyFormAction.getSearchQuestion());
                  else
                      jb.put("SearchQuestion", "none");
                   }else {
                       jb.put("SearchQuestionId", "none");
                   }
                       
                   if(!"".equals(surveyFormAction.getSearchQuestionId2())){
                       jb.put("SearchQuestionId2", surveyFormAction.getSearchQuestionId2());
                         if(surveyFormAction.getSearchQuestion2()!=null && !"".equals(surveyFormAction.getSearchQuestion2()))
                      jb.put("SearchQuestion2", surveyFormAction.getSearchQuestion2());
                  else
                      jb.put("SearchQuestion2", "none");
                   }else {
                       jb.put("SearchQuestionId2", "none");
                   }

            String serviceUrl = RestRepository.getInstance().getSrviceUrl("getSurveyReviewSearchList");

           // System.out.println("serviceUrl-->" + serviceUrl);
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
            System.out.println(e);
        }
        // System.out.println("mainList-->"+mainList);
        return mainList;
    }
 
 
 
 public Map getSurveyQuestionarrieMap(List questionList) throws ServiceLocatorException {
     Map questionarrieMap = new HashMap();
     try {
         		for (Object quesObj : questionList) {
			//System.out.println(temp);
                            Map tempQmap = (Map)quesObj;
                            if(tempQmap.get("OptionType").toString().equals("Textbox")||tempQmap.get("OptionType").toString().equals("Checkbox")||tempQmap.get("OptionType").toString().equals("RadioButton"))
                            questionarrieMap.put(tempQmap.get("Id"),tempQmap.get("Query"));
                            
		}
         
     }catch (Exception e) {
            System.out.println("\nError while calling REST Service");
            System.out.println(e);
        }
     
  //   System.out.println("questionarrieMap--->"+questionarrieMap);
     return questionarrieMap;
 }

public String doDeleteQuestion(SurveyFormAction surveyFormAction) throws ServiceLocatorException {
        String message = "";
        
        try {
            JSONObject jb = new JSONObject();
            
            jb.put("Id", surveyFormAction.getQuestionId());

         
            String serviceUrl = RestRepository.getInstance().getSrviceUrl("doDeleteQuestion");
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
public String doInactiveSurveyForm(SurveyFormAction surveyFormAction) throws ServiceLocatorException {


        String message = "";
        

        try {
            JSONObject jb = new JSONObject();
            
            jb.put("Id", surveyFormAction.getSurveyId());
            jb.put("CurrStatus", "InActive");
            jb.put("CreatedBy", surveyFormAction.getCreatedBy());
         
            String serviceUrl = RestRepository.getInstance().getSrviceUrl("doUpdateSurveyFormStatus");
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
public String doActiveSurveyForm(SurveyFormAction surveyFormAction) throws ServiceLocatorException {


        String message = "";
        

        try {
            JSONObject jb = new JSONObject();
            
            jb.put("Id", surveyFormAction.getSurveyId());
            jb.put("CurrStatus", "Active");
            jb.put("CreatedBy", surveyFormAction.getCreatedBy());
         
            String serviceUrl = RestRepository.getInstance().getSrviceUrl("doUpdateSurveyFormStatus");
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


}
