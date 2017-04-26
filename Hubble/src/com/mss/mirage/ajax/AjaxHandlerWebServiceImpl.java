/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.ajax;

import com.mss.mirage.crm.attachments.AttachmentService;
import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.FileUploadUtility;
import com.mss.mirage.util.Properties;
import com.mss.mirage.util.RestRepository;
import com.mss.mirage.util.ServiceLocator;
import com.mss.mirage.util.ServiceLocatorException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

/**
 *
 * @author miracle
 */
public class AjaxHandlerWebServiceImpl implements AjaxHandlerWebService{
    
      public String jobPosting(AjaxHandlerAction ajaxHandlerAction) throws Exception  {
        // Connection connection = null;
        //PreparedStatement preparedStatement = null;
        // ResultSet resultSet = null;
 String message ="";
        boolean isUpdated = false;
        //  String queryString ="";
          //    System.out.println("first call");

        //queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ModifiedBy=?,ModifiedDate=?,ReviewName=?,ReviewDate=?,Status=?,TLComments=?,TLRating=?,HRRating=?,HrComments=? where Id=?";
        //if(roleName.equalsIgnoreCase("Employee"))
        // queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ReviewName=?,ReviewDate=?,Status=? where Id=?";
        try {
            
              
                JSONObject jb = new JSONObject();
                // AjaxHandlerAction ac=new AjaxHandlerAction();
        jb.put("JobTitle", ajaxHandlerAction.getJobtitle());
      //  jb.put("JobTagline", ajaxHandlerAction.getJobtagline());
        //jb.put("JobLogo", " ../images/jobs/jobs1.png");
        jb.put("JobDescription", ajaxHandlerAction.getJobdescription());
      //  jb.put("JobPosition", ajaxHandlerAction.getJobposition());
        jb.put("JobQualification", ajaxHandlerAction.getJobqulification());
      //  jb.put("JobTechnical", ajaxHandlerAction.getJobtechnical());
      //  jb.put("JobSoftSkills", ajaxHandlerAction.getJobshiftskills());
      //  jb.put("JobShifts", ajaxHandlerAction.getJobshifits());
      //  jb.put("JobCountry",ajaxHandlerAction.getJobcountry());
        jb.put("JobStatus",ajaxHandlerAction.getJobstatus());
        jb.put("Location",ajaxHandlerAction.getLocation());
        jb.put("CreatedBy",ajaxHandlerAction.getCreatedBy());  
        jb.put("JobDepartment",ajaxHandlerAction.getJobDepartment()); 
        jb.put("JobHireType",ajaxHandlerAction.getJobHireType()); 
         //   URL url = new URL("http://172.17.11.251:8080/WebRoot/resources/doJobPost/post");
          // URL url = new URL("http://172.17.14.226:8080/WebRoot/resources/doJobPost/post");
           // URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doJobPost/post");
         String serviceUrl = RestRepository.getInstance().getSrviceUrl("JobPost");
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
       // System.out.println("displaymessage-->" +message);

if(!"".equals(message)){
             if(!message.contains("Oops"))
             sendJobPostingDetails(jb,"add");
         }
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
        return message;
    }
    
      
      
      public String editPosting(String jobId) throws Exception{
          String message ="";
        boolean isUpdated = false;
        //  String queryString ="";
             // System.out.println("first call");
            String data = "";
        //queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ModifiedBy=?,ModifiedDate=?,ReviewName=?,ReviewDate=?,Status=?,TLComments=?,TLRating=?,HRRating=?,HrComments=? where Id=?";
        //if(roleName.equalsIgnoreCase("Employee"))
        // queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ReviewName=?,ReviewDate=?,Status=? where Id=?";
        try {
            
              
                JSONObject jb = new JSONObject();
      
        jb.put("jobId",jobId);        
          //  URL url = new URL("http://172.17.11.251:8080/WebRoot/resources/doJobPost/edit");
        //  URL url = new URL("http://172.17.14.226:8080/WebRoot/resources/doJobPost/edit");
        // URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doJobPost/edit");
        String serviceUrl = RestRepository.getInstance().getSrviceUrl("jobedit");
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

      //  JSONObject jObject = new JSONObject(data);

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
        return data;
      }
      
      
      public String upadteJobPosting(AjaxHandlerAction ajaxHandlerAction) throws Exception{
           // Connection connection = null;
        //PreparedStatement preparedStatement = null;
        // ResultSet resultSet = null;
 String message ="";
        boolean isUpdated = false;
        //  String queryString ="";
           //   System.out.println("first call");

        //queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ModifiedBy=?,ModifiedDate=?,ReviewName=?,ReviewDate=?,Status=?,TLComments=?,TLRating=?,HRRating=?,HrComments=? where Id=?";
        //if(roleName.equalsIgnoreCase("Employee"))
        // queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ReviewName=?,ReviewDate=?,Status=? where Id=?";
        try {
            
              
                JSONObject jb = new JSONObject();
                // AjaxHandlerAction ac=new AjaxHandlerAction();
                
              //  System.out.println("Technical-->"+ ajaxHandlerAction.getJobtechnical());
        jb.put("JobTitle", ajaxHandlerAction.getJobtitle());
       // jb.put("JobTagline", ajaxHandlerAction.getJobtagline());
        //jb.put("JobLogo", " ../images/jobs/jobs1.png");
        jb.put("JobDescription", ajaxHandlerAction.getJobdescription());
      //  jb.put("JobPosition", ajaxHandlerAction.getJobposition());
        jb.put("JobQualification", ajaxHandlerAction.getJobqulification());
        //jb.put("JobTechnical", ajaxHandlerAction.getJobtechnical());
       // jb.put("JobSoftSkills", ajaxHandlerAction.getJobshiftskills());
        //jb.put("JobShifts", ajaxHandlerAction.getJobshifits());
        //jb.put("JobCountry",ajaxHandlerAction.getJobcountry());
        jb.put("JobStatus",ajaxHandlerAction.getJobstatus());
        jb.put("Location",ajaxHandlerAction.getLocation());
        jb.put("CreatedBy",ajaxHandlerAction.getCreatedBy());        
        jb.put("JobId",ajaxHandlerAction.getJobId()); 
        jb.put("JobDepartment",ajaxHandlerAction.getJobDepartment()); 
        jb.put("JobHireType",ajaxHandlerAction.getJobHireType()); 
            //URL url = new URL("http://172.17.11.251:8080/WebRoot/resources/doJobPost/update");
        //URL url = new URL("http://172.17.14.226:8080/WebRoot/resources/doJobPost/update");
      //  URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doJobPost/update");
        
  String serviceUrl = RestRepository.getInstance().getSrviceUrl("jobupdate");
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

 if(!"".equals(message)){
             if(!message.contains("Oops"))
             sendJobPostingDetails(jb,"update");
         }
           
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
        return message;
      }
      
      
      
       public String getApplicantDetails(int applicantId) throws Exception{
            String message ="";
        boolean isUpdated = false;
        //  String queryString ="";
           //   System.out.println("first call");
            String data = "";
            Connection dbConnection = null;
            Statement statement = null;
            ResultSet resultSet = null;
        //queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ModifiedBy=?,ModifiedDate=?,ReviewName=?,ReviewDate=?,Status=?,TLComments=?,TLRating=?,HRRating=?,HrComments=? where Id=?";
        //if(roleName.equalsIgnoreCase("Employee"))
        // queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ReviewName=?,ReviewDate=?,Status=? where Id=?";
        try {
            
              
                JSONObject jb = new JSONObject();
      
        jb.put("applicantId",applicantId);        
          //  URL url = new URL("http://172.17.11.251:8080/WebRoot/resources/doJobPost/edit");
         // URL url = new URL("http://172.17.14.226:8080/WebRoot/resources/doJobPost/applicantInfo");
       // URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doJobPost/applicantInfo");
        String serviceUrl = RestRepository.getInstance().getSrviceUrl("applicantInfo");
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

      //  JSONObject jObject = new JSONObject(data);

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
        return data;
       }
       
       
       
       
       
      
       public String addWebsiteConsultant(int applicantId,String loginId) throws Exception{
            String message ="";
        boolean isExisted = false;
        //  String queryString ="";
             // System.out.println("first call");
            String data = "";
     Connection dbConnection = null;
            Statement statement = null;
            ResultSet resultSet = null;
            PreparedStatement preparedStatement = null;
            int existedConsultantId = 0;
             JSONObject resultJObject  = new JSONObject();
        try {
            
              
                JSONObject jb = new JSONObject();
      
        jb.put("applicantId",String.valueOf(applicantId));        
          /*
        URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doJobPost/applicantInfo");
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

            in.close();*/
        
         data = getApplicantDetails(applicantId);
             JSONObject jObject  = new JSONObject(data);
             
             
            String Id = (String)jObject.get("Id"); 
            String JobId = (String)jObject.get("JobId"); 
            String FirstName = (String)jObject.get("FirstName"); 
            String LastName = (String)jObject.get("LastName"); 
            String MiddleName = (String)jObject.get("MiddleName"); 
            String PhoneNumber = (String)jObject.get("PhoneNumber"); 
            String EmailId = (String)jObject.get("EmailId"); 
          //  String NativePlace = (String)jObject.get("NativePlace"); 
           // String CurrentLocation = (String)jObject.get("CurrentLocation"); 
           // String NoticePeriod = (String)jObject.get("NoticePeriod"); 
            String ResumePath = (String)jObject.get("ResumePath"); 
            String ResumeName = (String)jObject.get("ResumeName"); 
            String AppliedDate = (String)jObject.get("AppliedDate"); 
           /* String NativeDistrict = (String)jObject.get("NativeDistrict"); 
            String NativeState = (String)jObject.get("NativeState"); 
            String NativeZipcode = (String)jObject.get("NativeZipcode");
             * *
             */    String CurrentOrg = (String)jObject.get("CurrentOrg"); 
            String Experience = (String)jObject.get("Experience"); 
            String EduQualification = (String)jObject.get("EduQualification"); 
            
            String CurentDistrict = (String)jObject.get("CurentDistrict"); 
            String CurrentState = (String)jObject.get("CurrentState"); 
            String CurrentZipcode = (String)jObject.get("CurrentZipcode"); 
            
          dbConnection = ConnectionProvider.getInstance().getConnection();
          statement = dbConnection.createStatement();
          resultSet = statement.executeQuery("SELECT Id FROM tblRecConsultant WHERE Email2 = '"+EmailId+"'");
          if(resultSet.next()){
            //  resultJObject.put("IsExisted", "true");
              isExisted = true;
              existedConsultantId = resultSet.getInt("Id");
          }
          resultSet.close();
         statement.close();
         
           String copiedPath = "";
          if(isExisted){
              resultJObject.put("IsExisted", "true");
              resultJObject.put("ExistedId", String.valueOf(existedConsultantId));
               File sourceFile = new File(ResumePath);
              if(sourceFile.exists()){
                 AttachmentService attachmentService = ServiceLocator.getAttachmentService();
                // String generatedPath = attachmentService.generatePath(Properties.getProperty("Attachments.Path"), "Emp Tasks");
                 copiedPath = attachResume(sourceFile);
                int inseretdRows= addConsultantAttachment(existedConsultantId,copiedPath,sourceFile.getName());
                         // addConsultantAttachment(int consultantId,String filePath,String fileName)
                if(inseretdRows>0){
                    resultJObject.put("FileAdded", "true");
                    updateMergeFlag(jb);
                }
                else{
                     resultJObject.put("FileAdded", "false"); 
                }
                   
              }
              
          }else {
              resultJObject.put("IsExisted", "false");
             
              
              File sourceFile = new File(ResumePath);
              String fileName = "";
              if(sourceFile.exists()){
                 AttachmentService attachmentService = ServiceLocator.getAttachmentService();
                // String generatedPath = attachmentService.generatePath(Properties.getProperty("Attachments.Path"), "Emp Tasks");
                 copiedPath = attachResume(sourceFile);
                 fileName = sourceFile.getName();
              }
              
               
          String query = "INSERT INTO tblConsultantMigration(FromLocation,Sourcepath,TargetPath,WebTblConsultantId,WebTblConsultantEmailId,Createdby,FileName) VALUES(?,?,?,?,?,?,?)";    
              preparedStatement = dbConnection.prepareStatement(query);
              preparedStatement.setString(1, ResumePath);
              preparedStatement.setString(2, ResumePath);
              preparedStatement.setString(3, copiedPath);
              preparedStatement.setInt(4, Integer.parseInt(Id));
              preparedStatement.setString(5, EmailId);
               preparedStatement.setString(6, loginId);
                preparedStatement.setString(7, fileName);
             int insertRows=  preparedStatement.executeUpdate();
               
               resultJObject.put("IsAdded", "true");
               
               if(insertRows>0){
                   
                    /*URL suburl = new URL(Properties.getProperty("WEB.REST.URL") +"doJobPost/updateMergeFlag");
            URLConnection subconnection = suburl.openConnection();
            subconnection.setDoOutput(true);
            subconnection.setRequestProperty("Content-Type", "application/json");
            subconnection.setConnectTimeout(5000);
            subconnection.setReadTimeout(5000);
            OutputStreamWriter subout = new OutputStreamWriter(subconnection.getOutputStream());
           subout.write(jb.toString());
            subout.close();

            BufferedReader subin = new BufferedReader(new InputStreamReader(
                    subconnection.getInputStream()));
            String subs = null;
String subdata = "";
            while ((subs = subin.readLine()) != null) {

                subdata = subdata + subs;
            }
System.out.println("subdata-->"+subdata);
            subin.close();
                   */
                   
                   String subdata = updateMergeFlag(jb);
                   //System.out.println("subdata-->"+subdata);
                   
                   
               }
               
               
               
               
               
               
               
               
               
               
               
          }
        } catch (IOException ex) {
            ex.printStackTrace();
        }catch(Exception exception){
          exception.printStackTrace();  
        }finally{
             try{
                 if(resultSet!=null){
                    resultSet.close();
                    resultSet = null;
                }
                if(preparedStatement!=null){
                    preparedStatement.close();
                    preparedStatement = null;
                } 
                if(statement!=null){
                    statement.close();
                    statement = null;
                }
                if(dbConnection!=null){
                    dbConnection.close();
                    dbConnection = null;
                }
                
            }catch(SQLException sqle){
                throw new ServiceLocatorException(sqle);
            }
        }
        return resultJObject.toString();
       }
       
       
       
      public String attachResume(File file) {
        String targetPath = null;
        try {

            /*     String basePath = Properties.getProperty("Resume.Attachments");
             File createPath = new File(basePath);
             java.util.Date dt = new java.util.Date();
             String month = "";
             if (dt.getMonth() == 0) {
             month = "Jan";
             } else if (dt.getMonth() == 1) {
             month = "Feb";
             } else if (dt.getMonth() == 2) {
             month = "Mar";
             } else if (dt.getMonth() == 3) {
             month = "Apr";
             } else if (dt.getMonth() == 4) {
             month = "May";
             } else if (dt.getMonth() == 5) {
             month = "Jun";
             } else if (dt.getMonth() == 6) {
             month = "Jul";
             } else if (dt.getMonth() == 7) {
             month = "Aug";
             } else if (dt.getMonth() == 8) {
             month = "Sep";
             } else if (dt.getMonth() == 9) {
             month = "Oct";
             } else if (dt.getMonth() == 10) {
             month = "Nov";
             } else if (dt.getMonth() == 11) {
             month = "Dec";
             }
             short week = (short) (Math.round(dt.getDate() / 7));
             createPath = new File(createPath.getAbsolutePath() + "//MirageV2//A//" + String.valueOf(dt.getYear() + 1900) + "//" + month + "//" + String.valueOf(week));
             createPath.mkdir();
             File theFile = new File(createPath.getAbsolutePath() + "//" + file.getName());
             targetPath = theFile.getAbsolutePath();
             FileUtils.copyFile(file, theFile);   */

            String basePath = Properties.getProperty("Resume.Attachments") + "//MirageV2//A";
            String theFilePath = FileUploadUtility.getInstance().filePathGeneration(basePath);
            String fileName = FileUploadUtility.getInstance().fileNameGeneration(file.getName());
            File theFile = new File(theFilePath);
            theFile.mkdir();
            theFile = new File(theFile.getAbsoluteFile() + "//" + fileName);
            targetPath = theFile.getAbsolutePath();
            FileUtils.copyFile(file, theFile);


        } catch (Exception ex) {
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            ex.printStackTrace();
            //System.err.println("In Action Class Catch"+ex.getMessage());
            //httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());

        }


        return targetPath;
    }
       
       
       
           public int addConsultantAttachment(int consultantId,String filePath,String fileName) throws ServiceLocatorException{
        int isSuccess = 0;
        Connection connection = null;
        PreparedStatement preStmt=null;
        
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            
             
           
            /* int Id = DataSourceDataProvider.getInstance().getMaxRecAttachMentId();
             Id = Id + 1;*/
             //System.out.print("Id-->"+Id);
            
             preStmt = connection.prepareStatement("INSERT INTO tblRecAttachments(ObjectId,ObjectType,AttachmentName,AttachmentFileName,AttachmentLocation,DateUploaded) values(?,?,?,?,?,?)");
             
             preStmt.setInt(1,consultantId);
             preStmt.setString(2,"A");
             preStmt.setString(3,fileName);
             preStmt.setString(4,fileName);
             preStmt.setString(5,filePath);
             preStmt.setTimestamp(6,DateUtility.getInstance().getCurrentMySqlDateTime());
             
             isSuccess = preStmt.executeUpdate();
             
        }catch (Exception ex){
            throw new ServiceLocatorException(ex);
        }finally{
            try{
                if(preStmt!=null){
                    preStmt.close();
                    preStmt = null;
                }
                if(connection!=null){
                    connection.close();
                    connection = null;
                }
                
            }catch(SQLException sqle){
                throw new ServiceLocatorException(sqle);
            }
        }
        
        return isSuccess;
    }
       
           
           
           
public String updateMergeFlag(JSONObject jb) {
    String subdata = "";
       try {
     // URL suburl = new URL(Properties.getProperty("WEB.REST.URL") +"doJobPost/updateMergeFlag");
            String serviceUrl = RestRepository.getInstance().getSrviceUrl("consultantMerge");
              URL suburl = new URL(serviceUrl);
            URLConnection subconnection = suburl.openConnection();
            subconnection.setDoOutput(true);
            subconnection.setRequestProperty("Content-Type", "application/json");
            subconnection.setConnectTimeout(5000);
            subconnection.setReadTimeout(5000);
            OutputStreamWriter subout = new OutputStreamWriter(subconnection.getOutputStream());
           subout.write(jb.toString());
            subout.close();

            BufferedReader subin = new BufferedReader(new InputStreamReader(
                    subconnection.getInputStream()));
            String subs = null;

            while ((subs = subin.readLine()) != null) {

                subdata = subdata + subs;
            }
//System.out.println("subdata-->"+subdata);
            subin.close();
       }catch(Exception exception)   {
           exception.printStackTrace();
       }
       return subdata;
}
           
           
 //public static void sendConsultantUpdatedDetailsForRequirement(String title,String requirementId,String consultantId,String rate,String startDate,String status,String comments,String modifiedBy,String modifiedDate) throws ServiceLocatorException {
public static void sendJobPostingDetails(JSONObject jb,String activityType) throws ServiceLocatorException {
        // SUBSTITUTE YOUR EMAIL ADDRESSES HERE!!!
        
        try {
            
            String jobTitle = jb.getString("JobTitle");
            String jobExperience = jb.getString("JobQualification")+" Years's";
             String JobStatus = jb.getString("JobStatus");
              String Location = jb.getString("Location");
               String JobDepartment = jb.getString("JobDepartment");
                String CreatedBy = jb.getString("CreatedBy");
            
           
         // String to = "sjanakala@miraclesoft.com,bmedasetti@miraclesoft.com,vkusampudi@miraclesoft.com,clokam@miraclesoft.com";
                //WEB.JOBPOST.EMAILS
                String to = Properties.getProperty("WEB.JOBPOST.EMAILS");
          String from = "hubbleapp@miraclesoft.com";
          String subject = "";
          if(activityType.equals("add"))
           subject = "A Position has been created!";
            else
              subject = "A Position has been updated!";
           
          StringBuilder sb = new StringBuilder("");
       sb.append("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>");
        
        sb.append("<html xmlns='http://www.w3.org/1999/xhtml'>");
sb.append("<head>");
sb.append("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />");
sb.append("<title>");
sb.append("Position Status Change");
sb.append("</title>");
sb.append("<style type='text/css'>");
sb.append("body {");
sb.append("padding-top: 0 !important;");
sb.append("padding-bottom: 0 !important;");
sb.append("padding-top: 0 !important;");
sb.append("padding-bottom: 0 !important;");
sb.append("margin:0 !important;");
sb.append("width: 100% !important;");
sb.append("-webkit-text-size-adjust: 100% !important;");
sb.append("-ms-text-size-adjust: 100% !important;");
sb.append("-webkit-font-smoothing: antialiased !important;");
sb.append("}");
sb.append(".tableContent img {");
sb.append("border: 0 !important;");
sb.append("display: block !important;");
sb.append("outline: none !important;");
sb.append("}");
sb.append("a{");
sb.append("color:#382F2E;");
sb.append("}");
sb.append("p, h1,h2,ul,ol,li,div{");
sb.append("margin:0;");
sb.append("padding:0;");
sb.append("}");
sb.append("h1,h2{");
sb.append("font-weight: normal;");
sb.append("background:transparent !important;");
sb.append("border:none !important;");
sb.append("}");
sb.append(".contentEditable h2.big,.contentEditable h1.big{");
sb.append("font-size: 26px !important;");
sb.append("}");
sb.append(".contentEditable h2.bigger,.contentEditable h1.bigger{");
sb.append("font-size: 37px !important;");
sb.append("}");
sb.append("td,table{");
sb.append("vertical-align: top;");
sb.append("}");
sb.append("td.middle{");
sb.append("vertical-align: middle;");
sb.append("}");
sb.append("a.link1{");
sb.append("font-size:13px;");
sb.append("color:#27A1E5;");
sb.append("line-height: 24px;");
sb.append("text-decoration:none;");
sb.append("}");
sb.append("a{");
sb.append("text-decoration: none;");
sb.append("}");
sb.append(".link2{");
sb.append("color:#ef4048;");
sb.append("border-top:6px solid #ef4048;");
sb.append("border-bottom:8px solid #ef4048;");
sb.append("border-left:8px solid #ef4048;");
sb.append("border-right:8px solid #ef4048;");
sb.append("border-radius:0px;");
sb.append("-moz-border-radius:3px;");
sb.append("-webkit-border-radius:3px;");
sb.append("background:#ef4048;");
sb.append("}");
sb.append(".link3{");
sb.append("color:#555555;");
sb.append("border:1px solid #cccccc;");
sb.append("padding:10px 18px;");
sb.append("border-radius:3px;");
sb.append("-moz-border-radius:3px;");
sb.append("-webkit-border-radius:3px;");
sb.append("background:#ffffff;");
sb.append("}");
sb.append(".link4{");
sb.append("color:#27A1E5;");
sb.append("line-height: 24px;");
sb.append("}");
sb.append("h2,h1{");
sb.append("line-height: 20px;");
sb.append("}");
sb.append("p{");
sb.append("font-size: 14px;");
sb.append("line-height: 21px;");
sb.append("color:#AAAAAA;");
sb.append("}");
sb.append(".contentEditable li{");
sb.append("}");
sb.append(".appart p{");
sb.append("}");
sb.append(".bgItem{");
sb.append("background:#ffffff;");
sb.append("}");
sb.append(".bgBody{");
sb.append("background: rgb(13,65,107);");
sb.append("}");
sb.append("img {");
sb.append("outline:none;");
sb.append("text-decoration:none;");
sb.append("-ms-interpolation-mode: bicubic;");
sb.append("width: auto;");
sb.append("max-width: 100%;");
sb.append("clear: both;");
sb.append("display: block;");
sb.append("float: none;");
sb.append("}");
sb.append("</style>");
sb.append("<script type='colorScheme' class='swatch active'>");
sb.append("{");
sb.append("'name':'Default',");
sb.append("'bgBody':'ffffff',");
sb.append("'link':'27A1E5',");
sb.append("'color':'AAAAAA',");
sb.append("'bgItem':'ffffff',");
sb.append("'title':'444444'");
sb.append("}");
sb.append("</script>");
sb.append("</head>");
sb.append("<body paddingwidth='0' paddingheight='0' bgcolor='#d1d3d4'  style='padding-top: 0; padding-bottom: 0; padding-top: 0; padding-bottom: 0; background-repeat: repeat; width: 100% !important; -webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; -webkit-font-smoothing: antialiased;' offset='0' toppadding='0' leftpadding='0'>");
sb.append("<table width='100%' border='0' cellspacing='0' cellpadding='0' class='tableContent bgBody' align='center'  style='font-family:Helvetica, sans-serif;'>");
sb.append("<tr>");
sb.append("<td align='center'>");
sb.append("<table width='600' border='0' cellspacing='0' cellpadding='0' align='center' >");
sb.append("<tr>");
sb.append("<td class='bgItem' align='center'>");
sb.append("<table width='600' border='0' cellspacing='0' cellpadding='0' align='center'>");
sb.append("<tr>");
sb.append("<td class='movableContentContainer' align='center'>");
  sb.append("<div class='movableContent'>");
	sb.append("<table width='100%' border='0' cellspacing='0' cellpadding='0' align='center'>");
	  sb.append("<tr>");
		sb.append("<td style='background: rgb(13,65,107); border-radius:0px;-moz-border-radius:0px;-webkit-border-radius:0px' height='30'>");
		sb.append("</td>");
	  sb.append("</tr>");
	  sb.append("<tr>");
		sb.append("<td style='background:rgb(13,65,107); border-radius:0px;-moz-border-radius:0px;-webkit-border-radius:0px'>");
		  sb.append("<table width='650' border='0' cellspacing='0' cellpadding='0' align='center'>");
			sb.append("<tr>");
			  sb.append("<td width='130'>");
				sb.append("<div class='contentEditableContainer contentImageEditable'>");
				  sb.append("<div class='contentEditable'>");
					sb.append("<a href='http://www.miraclesoft.com/index.php' target='_blank'>");
					  sb.append("<img src='http://www.miraclesoft.com/images/logo.png' alt='Logo' width='70' height='45' data-default='placeholder' data-max-width='200'>");
					sb.append("</a>");
				  sb.append("</div>");
				sb.append("</div>");
			  sb.append("</td>");
			  sb.append("<td valign='middle' style='vertical-align: middle;'>");
			  sb.append("</td>");
			  sb.append("<td valign='middle' style='vertical-align: middle;' width='150'>");
			  sb.append("</br>");
			sb.append("<table width='300' border='0' cellpadding='0' cellspacing='0' align='right' style='text-align: right; font-size: 15px; border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt;' class='fullCenter'>");
			  sb.append("<tr>");
				sb.append("<td height='55' valign='middle' width='100%' style='font-family: calibri; color:#000000;'>");
				  sb.append("<span style='font-family: calibri; font-weight: normal;'>");
					sb.append("<a href='http://www.miraclesoft.com' target='_blank' style='text-decoration: none; color:#ffffff;'class='underline' >");
					  sb.append("Company");
					sb.append("</a>");
				  sb.append("</span>");
				  sb.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				  sb.append("<span style='font-family:calibri; font-weight: normal;'>");
					sb.append("<a href='http://www.miraclesoft.com/careers/careers.php' target='_blank' style='text-decoration: none; color:#ffffff;'class='underline' >");
					  sb.append("Services");
					sb.append("</a>");
				  sb.append("</span>");
				  sb.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				  sb.append("<span style='font-family:calibri; font-weight: normal;'>");
					sb.append("<a href='http://www.miraclesoft.com/careers/careers.php' target='_blank' style='text-decoration: none; color:#ffffff;'class='underline' >");
					  sb.append("Contact");
					sb.append("</a>");
				  sb.append("</span>");
				sb.append("</td>");
			  sb.append("</tr>");
			sb.append("</table>");
		  sb.append("</td>");
		sb.append("</tr>");
	sb.append("</table>");
  sb.append("</td>");
sb.append("</tr>");
sb.append("</table>");
sb.append("</div>");
sb.append("<div class='movableContent'>");
sb.append("<table width='580' border='0' cellspacing='0' cellpadding='0' align='center'>");
sb.append("<tr>");
sb.append("<td style='border: 5px solid #000000; border-radius:0px;-moz-border-radius:0px;-webkit-border-radius:0px'>");
sb.append("<div class='movableContent'>");
  sb.append("<table width='650' border='0' cellspacing='0' cellpadding='0' align='center'>");
	sb.append("<tr>");
	  sb.append("<td style='background:#ef4048; border-radius:0px;-moz-border-radius:0px;-webkit-border-radius:0px'>");
		sb.append("<table width='600' border='0' cellspacing='0' cellpadding='0' align='center'>");
		  sb.append("<tr>");
			sb.append("<td height='15'>");
			sb.append("</td>");
		  sb.append("</tr>");
		  sb.append("<tr>");
			sb.append("<td>");
			  sb.append("<div class='contentEditableContainer contentTextEditable'>");
				sb.append("<div class='contentEditable' style='text-align: left;'>");
				  sb.append("<h1 style='font-size: 24px;'>");
					sb.append("<font color='#FFffff' face='calibri'>");
					  sb.append("<b>");
                                          if(activityType.equals("add")) {
                                              sb.append("A Position Has Been Created!");
                                          }else {
                                              //sb.append("A Position Status Change has Occurred!");
                                              sb.append("A Position Has Been Updated!");
                                          }
					  sb.append("</b>");
					sb.append("</font>");
				  sb.append("</h1>");
				  sb.append("<br>");
				sb.append("</div>");
			  sb.append("</div>");
			sb.append("</td>");
		  sb.append("</tr>");
		sb.append("</table>");
	  sb.append("</td>");
	sb.append("</tr>");
  sb.append("</table>");
sb.append("</div>");
sb.append("<table width='600' border='0' cellspacing='0' cellpadding='0' align='center'>");
  sb.append("<tr>");
	sb.append("<td>");
	  sb.append("<div class='contentEditableContainer contentTextEditable'>");
		sb.append("<div class='contentEditable' style='text-align: center;'>");
		  sb.append("<br>");
		  sb.append("<p style='text-align: justify; font-size: 15px;'>");
			sb.append("<font color='#232527' face='calibri'>");
			  sb.append("<b>");
			  sb.append("Social Media Team,");
			  sb.append("</b>");
			sb.append("</font>");
		  sb.append("</p>");
		sb.append("</br>");
		sb.append("<p style='text-align: justify; font-size: 15px;'>");
		  sb.append("<font color='#8c8c8c' face='calibri'>");
			//sb.append("A positon has been <b>[status: created/activated/edited]</b> on our website. Please do post the same with the following details to our Social Media Pages. ");
                  sb.append("A positon has been <b>"+JobStatus+"</b> on our website. Please do post the same with the following details to our Social Media Pages. ");
		  sb.append("</font>");
		sb.append("</p>");
	  sb.append("</br>");
	sb.append("</div>");
sb.append("</div>");

sb.append("<div class='movableContent'>");
sb.append("  <table width='600' border='0' cellspacing='0' cellpadding='0' align='center'>");
	
sb.append("	<tr>");
sb.append("	  <td width='600' style='line-height: 10px;'>");
sb.append("		<table width='600' border='0' cellpadding='0' cellspacing='0' align='center'>");
sb.append("		  <tr>");
sb.append("			<td>");
sb.append("			  <div class='contentEditableContainer contentTextEditable'>");
sb.append("				<div class='contentEditable' style='text-align: justify;'>");
sb.append("				  <p style='text-align: justify; font-size: 15px;'>");
sb.append("					<font color= #232527 face='calibri'>");
sb.append("					  <b>");
sb.append("						Job Title :");
sb.append("					  </b>");
sb.append("					</font>");
sb.append("					<font color= #8c8c8c face='calibri'>");
sb.append(					  jobTitle);
sb.append("					</font>");
sb.append("				  </p>");
sb.append("				  <br>");
sb.append("				  <p style='text-align: justify; font-size: 15px;'>");
sb.append("					<font color= #232527 face='calibri'>");
sb.append("					  <b>");
sb.append("						Location :");
sb.append("					  </b>");
sb.append("					</font>");
sb.append("					<font color= #8c8c8c face='calibri'>");
sb.append(					  Location);
sb.append("					</font>");
sb.append("				  </p>");
sb.append("				  <br>");
sb.append("				  <p style='text-align: justify; font-size: 15px;'>");
sb.append("					<font color= #232527 face='calibri'>");
sb.append("					  <b>");
sb.append("						Department :");
sb.append("					  </b>");
sb.append("					</font>");
sb.append("					<font color= #8c8c8c face='calibri'>");
sb.append(					  JobDepartment);
sb.append("					</font>");
sb.append("				  </p>");
sb.append("				  <br>");
sb.append("				  <p style='text-align: justify; font-size: 15px;'>");
sb.append("					<font color= #232527 face='calibri'>");
sb.append("					  <b>");
sb.append("						Experience: ");
sb.append("					  </b>");
sb.append("					</font>");
sb.append("					<font color= #8c8c8c face='calibri'>");
sb.append(					  jobExperience);
sb.append("					</font>");
sb.append("				  </p>");
sb.append("				  <br>");


if(activityType.equals("add")){
sb.append("				  <p style='text-align: justify; font-size: 15px;'>");
sb.append("					<font color= #232527 face='calibri'>");
sb.append("					  <b>");
sb.append("						CreatedBy: ");
sb.append("					  </b>");
sb.append("					</font>");
sb.append("					<font color= #8c8c8c face='calibri'>");
sb.append(					  CreatedBy);
sb.append("					</font>");
sb.append("				  </p>");
sb.append("				  <br>");
}else {
    sb.append("				  <p style='text-align: justify; font-size: 15px;'>");
sb.append("					<font color= #232527 face='calibri'>");
sb.append("					  <b>");
sb.append("						ModifiedBy: ");
sb.append("					  </b>");
sb.append("					</font>");
sb.append("					<font color= #8c8c8c face='calibri'>");
sb.append(					  CreatedBy);
sb.append("					</font>");
sb.append("				  </p>");
sb.append("				  <br>");
}

sb.append("				  <br>");
sb.append("				  <br>");
sb.append("				  <a target='_blank' href='http://www.miraclesoft.com/careers/open-positions.php' class='link2' style='color:#ffffff'>");
sb.append("					<font face='calibri'>");
sb.append("					  <b>");
sb.append("						Read more");
sb.append("					  </b>");
sb.append("					</font>");
sb.append("				  </a>");
sb.append("				  <br>");
sb.append("				  <br>");
sb.append("				  <br>");
sb.append("				  <br>");
sb.append("				  <br>");
sb.append("				  <br>");
sb.append("				</div>");
sb.append("			  </div>");
sb.append("			</td>");
sb.append("		  </tr>");
sb.append("		</table>");
sb.append("	  </td>");
  
	  
sb.append("	  <td height='40' colspan='3'>");
sb.append("	  </td>");
sb.append("	</tr>");
sb.append("  </table>");
sb.append("</div>");
sb.append("<div class='contentEditableContainer contentTextEditable'>");
sb.append("  <div class='contentEditable' style='text-align: center;'>");
sb.append("	<p style='text-align: justify; font-size: 14px;'>");
sb.append("	  <font color= #8c8c8c face='calibri'>");
sb.append("		<b>");
sb.append("		  Thanks & Regards,");
sb.append("		</b>");
sb.append("	  </br>");
sb.append("	  <b>");
sb.append("		Corporate Application Support Team,");
sb.append("	  </b>");
sb.append("	</br>");
sb.append("  Miracle Software Systems, Inc.");
sb.append("</br>");
sb.append("45625 Grand River Avenue, Novi, MI(USA)");
sb.append("</br>");
sb.append("<b>");
sb.append("Email :");
sb.append("</b>");
sb.append("hubbleapp@miraclesoft.com");
sb.append("</br>");
sb.append("<b>");
sb.append("Phone :");
sb.append("</b>");
sb.append("(+1)248-233-1814");
sb.append("</font>");
sb.append("</p>");
sb.append("</br>");
sb.append("<p style='text-align: justify; font-size: 15px;'>");
sb.append("<font color= #ef4048 face='calibri'>");
sb.append("<i>");
sb.append("<b>");
sb.append("*Note:");
sb.append("</b>");
sb.append("Please do not reply to this email as this is an automated notification.");
sb.append("</i>");
sb.append("</font>");
sb.append("</p>");
sb.append("<br>");
sb.append("</div>");
sb.append("</div>");
sb.append("</td>");
sb.append("</tr>");
sb.append("<tr>");
sb.append("<td height='10' colspan='3'>");
sb.append("</td>");
sb.append("</tr>");
sb.append("</table>");
sb.append("</td>");
sb.append("</tr>");
sb.append("</table>");
sb.append("</div>");
sb.append("<div class='movableContent'>");
sb.append("<table width='660' border='0' cellspacing='0' cellpadding='0' align='center'>");
sb.append("<tr>");
sb.append("<td style='background:rgb(13,65,107); border-radius:0px;-moz-border-radius:0px;-webkit-border-radius:0px'>");
sb.append("<table width='655' border='0' cellspacing='0' cellpadding='0' align='center'>");
sb.append("<tr>");
sb.append("<td colspan='3' height='20'>");
sb.append("</td>");
sb.append("</tr>");
sb.append("<tr>");
sb.append("<td width='90'>");
sb.append("</td>");
sb.append("<td width='660'align='center' style='text-align: center;'>");
sb.append("<table width='660' cellpadding='0' cellspacing='0' align='center'>");
sb.append("<tr>");
sb.append("<td>");
sb.append("<div class='contentEditableContainer contentTextEditable'>");
sb.append("  <div class='contentEditable' style='text-align: center;color:#AAAAAA;'>");
sb.append("	<p style='text-align: center; font-size: 14px;'>");
sb.append("	  <font color='#ffffff' face='calibri'>");
sb.append("		© Copyright 2015 Miracle Software Systems, Inc.");
sb.append("	  </br>");
sb.append("	  45625 Grand River Avenue");
sb.append("	</br>");
sb.append("  Novi, MI - USA");
sb.append("</p>");
sb.append("</div>");
sb.append("</div>");
sb.append("</td>");
sb.append("</tr>");
sb.append("</table>");
sb.append("</td>");
sb.append("<td width='90'>");
sb.append("</td>");
sb.append("</tr>");
sb.append("</table>");
sb.append("<table width='660' border='0' cellspacing='0' cellpadding='0' align='center'>");
sb.append("<tr>");
sb.append("<td colspan='3' height='10'>");
sb.append("</td>");
sb.append("</tr>");
sb.append("<tr>");
sb.append("<td width='230' align='center' style='text-align: center;'>");
sb.append("<table width='230' cellpadding='0' cellspacing='0' align='center'>");
sb.append("<tr>");
sb.append("<td width='40'>");
sb.append("<div class='contentEditableContainer contentFacebookEditable'>");
sb.append("<div class='contentEditable' style='text-align: center;color:#AAAAAA;'>");
sb.append("<a href='https://www.facebook.com/miracle45625' target='_blank'>");
sb.append("<img src='http://www.miraclesoft.com/newsletters/others/2015/social/fb.png' alt='facebook' width='30' height='30' data-max-width='40' data-customIcon='true' >");
sb.append("</a>");
sb.append("</div>");
sb.append("</div>");
sb.append("</td>");
sb.append("<td width='10'>");
sb.append("</td>");
sb.append("<td width='40'>");
sb.append("<div class='contentEditableContainer contentTwitterEditable'>");
sb.append("<div class='contentEditable' style='text-align: center;color:#AAAAAA;'>");
sb.append("<a href='https://twitter.com/team_mss' target='_blank'>");
sb.append("<img src='http://www.miraclesoft.com/newsletters/others/2015/social/twitter.png' alt='twitter' width='30' height='30' data-max-width='40' data-customIcon='true'>");
sb.append("</a>");
sb.append("</div>");
sb.append("</div>");
sb.append("</td>");
sb.append("<td width='10'>");
sb.append("</td>");
sb.append("<td width='40'>");
sb.append("<div class='contentEditableContainer contentTwitterEditable'>");
sb.append("<div class='contentEditable' style='text-align: center;color:#AAAAAA;'>");
sb.append("<a href='https://www.linkedin.com/company/miracle-software-systems-inc&quot;' target='_blank'>");
sb.append("<img src='http://www.miraclesoft.com/newsletters/others/2015/social/linkedin.png' alt='linkedin' width='30' height='30' data-max-width='40' data-customIcon='true'>");
sb.append("</a>");
sb.append("</div>");
sb.append("</div>");
sb.append("</td>");
sb.append("<td width='10'>");
sb.append("</td>");
sb.append("<td width='40'>");
sb.append("<div class='contentEditableContainer contentFacebookEditable'>");
sb.append("<div class='contentEditable' style='text-align: center;color:#AAAAAA;'>");
sb.append("<a href='https://plus.google.com/+Team_MSS' target='_blank'>");
sb.append("<img src='http://www.miraclesoft.com/newsletters/others/2015/social/googleplus.png' alt='googleplus' width='30' height='30' data-max-width='40' data-customIcon='true' >");
sb.append("</a>");
sb.append("</div>");
sb.append("</div>");
sb.append("</td>");
sb.append("<td width='10'>");
sb.append("</td>");
sb.append("<td width='40'>");
sb.append("<div class='contentEditableContainer contentTwitterEditable'>");
sb.append("<div class='contentEditable' style='text-align: center;color:#AAAAAA;'>");
sb.append("<a href='https://www.flickr.com/photos/team_miracle' target='_blank'>");
sb.append("<img src='http://www.miraclesoft.com/newsletters/others/2015/social/flicker.png' alt='flicker' width='30' height='30' data-max-width='40' data-customIcon='true'>");
sb.append("</a>");
sb.append("</div>");
sb.append("</div>");
sb.append("</td>");
sb.append("<td width='10'>");
sb.append("</td>");
sb.append("<td width='40'>");
sb.append("<div class='contentEditableContainer contentTwitterEditable'>");
sb.append("<div class='contentEditable' style='text-align: center;color:#AAAAAA;'>");
sb.append("<a href='https://www.youtube.com/c/Team_MSS' target='_blank'>");
sb.append("<img src='http://www.miraclesoft.com/newsletters/others/2015/social/youtube.png' alt='youtube' width='30' height='30' data-max-width='40' data-customIcon='true'>");
sb.append("</a>");
sb.append("</div>");
sb.append("</div>");
sb.append("</td>");
sb.append("<td width='10'>");
sb.append("</td>");
sb.append("</tr>");
sb.append("</table>");
sb.append("</td>");
sb.append("</tr>");
sb.append("<tr>");
sb.append("<td colspan='3' height='40'>");
sb.append("</td>");
sb.append("</tr>");
sb.append("</table>");
sb.append("</td>");
sb.append("</tr>");
sb.append("</table>");
sb.append("</div>");
sb.append("<div class='movableContent'>");
sb.append("<table width='100%' border='0' cellspacing='0' cellpadding='0' align='center'>");
sb.append("<tr>");
sb.append("<td style='background:rgb(13,65,107); border-radius:0px;-moz-border-radius:0px;-webkit-border-radius:0px' height='0'>");
sb.append("</td>");
sb.append("</tr>");
sb.append("<tr>");
sb.append("</table>");
sb.append("</div>");
sb.append("</td>");
sb.append("</tr>");
sb.append("</table>");
sb.append("</td>");
sb.append("</tr>");
sb.append("</table>");
sb.append("</td>");
sb.append("</tr>");
sb.append("</table>");
sb.append("</body>");
sb.append("</html>");

            
          ServiceLocator.getMailServices().doAddEmailLog(to, "", subject, sb.toString(), "", "");
          //  doAddEmailLog(String toAddress, String cCAddress, String subject,String bodyContent,String createdDate,String bCCAddress)
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
      
public String addEventposting(AjaxHandlerAction ajaxHandlerAction) throws Exception{
       
 String message ="";
        boolean isUpdated = false;
      
        try {
            
              
                JSONObject jb = new JSONObject();
              
        jb.put("event_title", ajaxHandlerAction.getEventTitle());
  
        jb.put("event_description", ajaxHandlerAction.getEventDescription());
    
        jb.put("event_startdate", ajaxHandlerAction.getStartDate());
      
        jb.put("evetnt_enddate",ajaxHandlerAction.getEndDate());
        jb.put("event_time_from",ajaxHandlerAction.getStartTime());
        jb.put("event_time_to",ajaxHandlerAction.getEndTime());  
        jb.put("midday_from",ajaxHandlerAction.getMidDayFrom()); 
        jb.put("midday_to",ajaxHandlerAction.getMidDayTo()); 
        jb.put("timezone",ajaxHandlerAction.getTimeZone()); 
        jb.put("location",ajaxHandlerAction.getEventLocation()); 
        jb.put("transport",ajaxHandlerAction.getTransportation()); 
        jb.put("createdby",ajaxHandlerAction.getCreatedBy()); 
       jb.put("status",ajaxHandlerAction.getEventStatus()); 
        jb.put("WebinarType",ajaxHandlerAction.getEventType()); 
        
        if(ajaxHandlerAction.getEventType().equalsIgnoreCase("I")|| ajaxHandlerAction.getEventType().equalsIgnoreCase("E")){
        jb.put("event_bold_Title",ajaxHandlerAction.getEventBoldtitle()); 
        jb.put("event_tagline",ajaxHandlerAction.getEventRegluarTitle()); 
        jb.put("OrganizerEmail",ajaxHandlerAction.getContactUsEmail()); 
        jb.put("RegistrationLink",ajaxHandlerAction.getEventRegistrationLink()); 
        
       // jb.put("PrimaryTrack",ajaxHandlerAction.getPrimaryTrack());
       // jb.put("SecondaryTrack",ajaxHandlerAction.getSecondaryTrack());
         if (!"".equals(ajaxHandlerAction.getPrimaryTrack()) && ajaxHandlerAction.getPrimaryTrack() != null) {
                jb.put("PrimaryTrack", ajaxHandlerAction.getPrimaryTrack());
            } else {
                jb.put("PrimaryTrack", "0");
            }
            if (!"".equals(ajaxHandlerAction.getSecondaryTrack()) && ajaxHandlerAction.getSecondaryTrack() != null) {
                jb.put("SecondaryTrack", ajaxHandlerAction.getSecondaryTrack());
            } else {
                jb.put("SecondaryTrack", "0");
            }
        jb.put("Department",ajaxHandlerAction.getEventDepartment());
        }
         if(ajaxHandlerAction.getEventType().equalsIgnoreCase("C")){
             jb.put("event_redirect",ajaxHandlerAction.getConferenceUrl()); 
         }
         jb.put("VideoLink",ajaxHandlerAction.getEventAfterVideoUrl());
     //    System.out.println("getConferenceUrl-->"+ajaxHandlerAction.getConferenceUrl());
         //   URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doEventPost/addEvent");
         String serviceUrl = RestRepository.getInstance().getSrviceUrl("addEvent");
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
      
      
      
       public String editEventposting(String eventId) throws Exception{
          String message ="";
        boolean isUpdated = false;
        //  String queryString ="";
             // System.out.println("first call");
            String data = "";
        //queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ModifiedBy=?,ModifiedDate=?,ReviewName=?,ReviewDate=?,Status=?,TLComments=?,TLRating=?,HRRating=?,HrComments=? where Id=?";
        //if(roleName.equalsIgnoreCase("Employee"))
        // queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ReviewName=?,ReviewDate=?,Status=? where Id=?";
        try {
            
              
                JSONObject jb = new JSONObject();
      
        jb.put("event_id",eventId);        
          //  URL url = new URL("http://172.17.11.251:8080/WebRoot/resources/doJobPost/edit");
        //  URL url = new URL("http://172.17.14.226:8080/WebRoot/resources/doJobPost/edit");
       //  URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doJobPost/edit");
        String serviceUrl = RestRepository.getInstance().getSrviceUrl("eventEdit");
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

      //  JSONObject jObject = new JSONObject(data);

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
        return data;
      }
     public String updateEventposting(AjaxHandlerAction ajaxHandlerAction) throws Exception{
         
 String message ="";
        boolean isUpdated = false;
      
        try {
            
              
                JSONObject jb = new JSONObject();
              jb.put("event_id", String.valueOf(ajaxHandlerAction.getEventId()));
        jb.put("event_title", ajaxHandlerAction.getEventTitle());
  
        jb.put("event_description", ajaxHandlerAction.getEventDescription());
    
        jb.put("event_startdate", ajaxHandlerAction.getStartDate());
      
        jb.put("evetnt_enddate",ajaxHandlerAction.getEndDate());
        jb.put("event_time_from",ajaxHandlerAction.getStartTime());
        jb.put("event_time_to",ajaxHandlerAction.getEndTime());  
        jb.put("midday_from",ajaxHandlerAction.getMidDayFrom()); 
        
       // System.out.println("getMidDayTo-->"+ajaxHandlerAction.getMidDayTo());
        jb.put("midday_to",ajaxHandlerAction.getMidDayTo()); 
        jb.put("timezone",ajaxHandlerAction.getTimeZone()); 
        jb.put("location",ajaxHandlerAction.getEventLocation()); 
        jb.put("transport",ajaxHandlerAction.getTransportation()); 
        jb.put("createdby",ajaxHandlerAction.getCreatedBy()); 
       jb.put("status",ajaxHandlerAction.getEventStatus()); 
        jb.put("WebinarType",ajaxHandlerAction.getEventType()); 
       if(ajaxHandlerAction.getEventType().equalsIgnoreCase("I")|| ajaxHandlerAction.getEventType().equalsIgnoreCase("E")){
        jb.put("event_bold_Title",ajaxHandlerAction.getEventBoldtitle()); 
        jb.put("event_tagline",ajaxHandlerAction.getEventRegluarTitle()); 
        jb.put("OrganizerEmail",ajaxHandlerAction.getContactUsEmail()); 
        jb.put("RegistrationLink",ajaxHandlerAction.getEventRegistrationLink()); 
        
       if (!"".equals(ajaxHandlerAction.getPrimaryTrack()) && ajaxHandlerAction.getPrimaryTrack() != null) {
                jb.put("PrimaryTrack", ajaxHandlerAction.getPrimaryTrack());
            } else {
                jb.put("PrimaryTrack", "0");
            }
            if (!"".equals(ajaxHandlerAction.getSecondaryTrack()) && ajaxHandlerAction.getSecondaryTrack() != null) {
                jb.put("SecondaryTrack", ajaxHandlerAction.getSecondaryTrack());
            } else {
                jb.put("SecondaryTrack", "0");
            }
        jb.put("Department",ajaxHandlerAction.getEventDepartment());
        
        jb.put("IsAssociated",ajaxHandlerAction.getIsAssociated());
        jb.put("AsociatedEventId",ajaxHandlerAction.getAssociatedEventId());
        
        }if(ajaxHandlerAction.getEventType().equalsIgnoreCase("C")){
             jb.put("event_redirect",ajaxHandlerAction.getConferenceUrl()); 
         }
         jb.put("VideoLink",ajaxHandlerAction.getEventAfterVideoUrl());
         jb.put("SeriesId",ajaxHandlerAction.getSeriesId());
         
        // System.out.println("VideoLink-->"+ajaxHandlerAction.getEventAfterVideoUrl());
       //  System.out.println("get VideoLink-->"+jb.getString("VideoLink"));
      //  System.out.println("getConferenceUrl-->"+ajaxHandlerAction.getConferenceUrl());
         //   URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doEventPost/addEvent");
         String serviceUrl = RestRepository.getInstance().getSrviceUrl("updateEvent");
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
     
     
     
      public String addEventSpeaker(AjaxHandlerAction ajaxHandlerAction) throws Exception{
       
 String message ="";
        boolean isUpdated = false;
      
        try {
            
              
                JSONObject jb = new JSONObject();
              
        jb.put("event_id", String.valueOf(ajaxHandlerAction.getEventId()));
  
        jb.put("event_speaker", ajaxHandlerAction.getSpeakerName());
    
        jb.put("event_designation", ajaxHandlerAction.getDesignation());
      
        jb.put("primary_speaker",ajaxHandlerAction.getSpeakerType());
        jb.put("Company",ajaxHandlerAction.getCompany());
        jb.put("Status",ajaxHandlerAction.getStatus());
       
         //   URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doEventPost/addEvent");
         String serviceUrl = RestRepository.getInstance().getSrviceUrl("addEventSpeaker");
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
       public String editEventSpeaker(String speakerId) throws Exception{
            String message ="";
        boolean isUpdated = false;
        //  String queryString ="";
             // System.out.println("first call");
            String data = "";
        //queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ModifiedBy=?,ModifiedDate=?,ReviewName=?,ReviewDate=?,Status=?,TLComments=?,TLRating=?,HRRating=?,HrComments=? where Id=?";
        //if(roleName.equalsIgnoreCase("Employee"))
        // queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ReviewName=?,ReviewDate=?,Status=? where Id=?";
        try {
            
              
                JSONObject jb = new JSONObject();
      
        jb.put("Id",speakerId);        
          //  URL url = new URL("http://172.17.11.251:8080/WebRoot/resources/doJobPost/edit");
        //  URL url = new URL("http://172.17.14.226:8080/WebRoot/resources/doJobPost/edit");
       //  URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doJobPost/edit");
        String serviceUrl = RestRepository.getInstance().getSrviceUrl("editEventSpeaker");
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

      //  JSONObject jObject = new JSONObject(data);

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
        return data;
       }
       
       
        public String updateEventSpeaker(AjaxHandlerAction ajaxHandlerAction) throws Exception{
            
 String message ="";
        boolean isUpdated = false;
      
        try {
            
              
                JSONObject jb = new JSONObject();
              
        jb.put("event_id", String.valueOf(ajaxHandlerAction.getEventId()));
  
        jb.put("event_speaker", ajaxHandlerAction.getSpeakerName());
    
        jb.put("event_designation", ajaxHandlerAction.getDesignation());
      
        jb.put("primary_speaker",ajaxHandlerAction.getSpeakerType());
        jb.put("Company",ajaxHandlerAction.getCompany());
        jb.put("Status",ajaxHandlerAction.getStatus());
        jb.put("Id",ajaxHandlerAction.getSpeakerId());
       
         //   URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doEventPost/addEvent");
         String serviceUrl = RestRepository.getInstance().getSrviceUrl("updateEventSpeaker");
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
       public String getInfoDetails(String infoId,String tableName) throws Exception {
               String message ="";
        boolean isUpdated = false;
        //  String queryString ="";
             // System.out.println("first call");
            String data = "";
        //queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ModifiedBy=?,ModifiedDate=?,ReviewName=?,ReviewDate=?,Status=?,TLComments=?,TLRating=?,HRRating=?,HrComments=? where Id=?";
        //if(roleName.equalsIgnoreCase("Employee"))
        // queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ReviewName=?,ReviewDate=?,Status=? where Id=?";
        try {
            
              
                JSONObject jb = new JSONObject();
      
        jb.put("Id",infoId);   
        jb.put("tableName",tableName);   
          //  URL url = new URL("http://172.17.11.251:8080/WebRoot/resources/doJobPost/edit");
        //  URL url = new URL("http://172.17.14.226:8080/WebRoot/resources/doJobPost/edit");
       //  URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doJobPost/edit");
        String serviceUrl = RestRepository.getInstance().getSrviceUrl("getInfoDetails");
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

      //  JSONObject jObject = new JSONObject(data);

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
        return data;
       } 
       
       public String updateAfterEvent(AjaxHandlerAction ajaxHandlerAction) throws Exception{
            
        String message ="";
        boolean isUpdated = false;
      
        try {
            
              
                JSONObject jb = new JSONObject();
              
        jb.put("eventId", String.valueOf(ajaxHandlerAction.getEventId()));
  
        jb.put("eventAfterDescription", ajaxHandlerAction.getEventAfterDescription());
    
        jb.put("eventAfterVideoUrl", ajaxHandlerAction.getEventAfterVideoUrl());
      
        jb.put("eventStatus",ajaxHandlerAction.getEventStatus());
        jb.put("primaryTrack",ajaxHandlerAction.getPrimaryTrack());
        jb.put("secondaryTrack",ajaxHandlerAction.getSecondaryTrack());
        jb.put("eventType",ajaxHandlerAction.getEventType());
       //eventType
       
         //   URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doEventPost/addEvent");
         String serviceUrl = RestRepository.getInstance().getSrviceUrl("updateAfterEvent");
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
       
       
       public String getQmeetMap(String year) throws Exception {
            Map qmeetMap = new HashMap();
            StringBuffer qmeetXml = new StringBuffer();
           try {
               
                JSONObject jb = new JSONObject();
                 
              // System.out.println("year-->"+marketingAction.getQmeetYear());
                  jb.put("year", year);
               
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
String s=null;
String data = "";
                while ((s=in.readLine()) != null) {
               //     System.out.println(s);
                    data =data+s;
                }
                
                
                
            //   System.out.println("Data-->"+data);
               JSONObject jObject  = new JSONObject(data);
            
                 
                 /***********************/
                qmeetXml.append("<xml version=\"1.0\">");
            qmeetXml.append("<QMEETS>");
            qmeetXml.append("<QMEET qmeetId=\"\">--Please Select--</QMEET>");
                 Iterator<String> keysItr = jObject.keys();
    while(keysItr.hasNext()) {
         String key = keysItr.next();
        String value = (String)jObject.get(key);
        qmeetMap.put(key, value);
         //  System.out.println("Id-->"+key+"-->"+value);
           qmeetXml.append("<QMEET qmeetId=\"" + key + "\">");
           if (value.contains("&")) {
                    value = value.replace("&", "&amp;");
                }
                qmeetXml.append(value);
                qmeetXml.append("</QMEET>");
           
    }
             qmeetXml.append("</QMEETS>");
            qmeetXml.append("</xml>");    
                 
                 /*************************/
                 
             
                in.close();
            } catch (Exception e) {
                System.out.println("\nError while calling REST Service");
                System.out.println(e);
            }
         //  System.out.println("qmeetXml-->"+qmeetXml.toString());
  return qmeetXml.toString();
       }
       
       
    /*
       public String getEventSeries(String eventId,String eventType) throws Exception {
            Map qmeetMap = new HashMap();
            StringBuffer qmeetXml = new StringBuffer();
           try {
               
                JSONObject jb = new JSONObject();
                 
              // System.out.println("year-->"+marketingAction.getQmeetYear());
                  jb.put("eventId", eventId);
                  jb.put("eventType", eventType);
               
              String serviceUrl = RestRepository.getInstance().getSrviceUrl("eventSeriesMap");
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
String s=null;
String data = "";
                while ((s=in.readLine()) != null) {
               //     System.out.println(s);
                    data =data+s;
                }
                
                
                
               System.out.println("Data-->"+data);
               JSONObject jObject  = new JSONObject(data);
            
                 
                 
                qmeetXml.append("<xml version=\"1.0\">");
            qmeetXml.append("<EVENTS>");
            qmeetXml.append("<EVENT eventId=\"\">--Please Select--</EVENT>");
                 Iterator<String> keysItr = jObject.keys();
    while(keysItr.hasNext()) {
         String key = keysItr.next();
        String value = (String)jObject.get(key);
        qmeetMap.put(key, value);
           System.out.println("Id-->"+key+"-->"+value);
           qmeetXml.append("<EVENT eventId=\"" + key + "\">");
           if (value.contains("&")) {
                    value = value.replace("&", "&amp;");
                }if (value.contains("<")) {
                    value = value.replace("<", "&lt;");
                }if (value.contains(">")) {
                    value = value.replace(">", "&gt;");
                }
                qmeetXml.append(value);
                qmeetXml.append("</EVENT>");
           
    }
             qmeetXml.append("</EVENTS>");
            qmeetXml.append("</xml>");    
                 
               
                 
             
                in.close();
            } catch (Exception e) {
                System.out.println("\nError while calling REST Service");
                System.out.println(e);
            }
           System.out.println("qmeetXml-->"+qmeetXml.toString());
  return qmeetXml.toString();
       }
       */
       
public String doCreateWebinarSeries(AjaxHandlerAction ajaxHandlerAction) throws Exception {
       
 String message ="";
        boolean isUpdated = false;
      
        try {
            
              
                JSONObject jb = new JSONObject();
              
        jb.put("SeriesTitle", ajaxHandlerAction.getSeriesTitle());
  
        jb.put("SeriesType", ajaxHandlerAction.getSeriesType());
    
      //  jb.put("SeriesTrack", ajaxHandlerAction.getSeriesTrack());
        if (!"".equals(ajaxHandlerAction.getSeriesTrack()) && ajaxHandlerAction.getSeriesTrack() != null) {
                jb.put("SeriesTrack", ajaxHandlerAction.getSeriesTrack());
            } else {
                jb.put("SeriesTrack", "0");
            }
      
        jb.put("SeriesStatus",ajaxHandlerAction.getSeriesStatus());
       
       jb.put("CreatedBy",ajaxHandlerAction.getCreatedBy());
         //   URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doEventPost/addEvent");
         String serviceUrl = RestRepository.getInstance().getSrviceUrl("createWebinarSeries");
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


public String getSeriesDetails(String seriesId) throws Exception{
            String message ="";
        boolean isUpdated = false;
        //  String queryString ="";
             // System.out.println("first call");
            String data = "";
        //queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ModifiedBy=?,ModifiedDate=?,ReviewName=?,ReviewDate=?,Status=?,TLComments=?,TLRating=?,HRRating=?,HrComments=? where Id=?";
        //if(roleName.equalsIgnoreCase("Employee"))
        // queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ReviewName=?,ReviewDate=?,Status=? where Id=?";
        try {
            
              
                JSONObject jb = new JSONObject();
      
        jb.put("Id",seriesId);        
          //  URL url = new URL("http://172.17.11.251:8080/WebRoot/resources/doJobPost/edit");
        //  URL url = new URL("http://172.17.14.226:8080/WebRoot/resources/doJobPost/edit");
       //  URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doJobPost/edit");
        String serviceUrl = RestRepository.getInstance().getSrviceUrl("getSeriesDetails");
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

      //  JSONObject jObject = new JSONObject(data);

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
        return data;
       }


public String doUpdateWebinarSeries(AjaxHandlerAction ajaxHandlerAction) throws Exception{
    String message ="";
        boolean isUpdated = false;
      
        try {
            
              
                JSONObject jb = new JSONObject();
              jb.put("Id", ajaxHandlerAction.getSeriesId());
        jb.put("SeriesTitle", ajaxHandlerAction.getSeriesTitle());
  
        jb.put("SeriesType", ajaxHandlerAction.getSeriesType());
    
   //     jb.put("SeriesTrack", ajaxHandlerAction.getSeriesTrack());
         if (!"".equals(ajaxHandlerAction.getSeriesTrack()) && ajaxHandlerAction.getSeriesTrack() != null) {
                jb.put("SeriesTrack", ajaxHandlerAction.getSeriesTrack());
            } else {
                jb.put("SeriesTrack", "0");
            }
      
        jb.put("SeriesStatus",ajaxHandlerAction.getSeriesStatus());
       
       jb.put("CreatedBy",ajaxHandlerAction.getCreatedBy());
         //   URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doEventPost/addEvent");
         String serviceUrl = RestRepository.getInstance().getSrviceUrl("updateWebinarSeries");
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


public String addTrackName(AjaxHandlerAction ajaxHandlerAction) throws Exception{
       
 String message ="";
        boolean isUpdated = false;
      
        try {
            
              
                JSONObject jb = new JSONObject();
              
        jb.put("trackName", String.valueOf(ajaxHandlerAction.getTrackName()));
      //  System.out.println("addTrackNameAHWSI"+ajaxHandlerAction.getTrackName());
  
        
       
         //   URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doEventPost/addEvent");
         String serviceUrl = RestRepository.getInstance().getSrviceUrl("addTrackName");
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

public String editTrackName(AjaxHandlerAction ajaxHandlerAction) throws Exception{
       
 String message ="";
        boolean isUpdated = false;
      
        try {
            
              
                JSONObject jb = new JSONObject();
              
        jb.put("trackName", ajaxHandlerAction.getTrackName());
        jb.put("trackId", String.valueOf(ajaxHandlerAction.getTrackId()));
        jb.put("createdBy", ajaxHandlerAction.getCreatedBy());
     //   System.out.println("addTrackNameAHWSI"+ajaxHandlerAction.getTrackId());
  
        
       
         //   URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doEventPost/addEvent");
         String serviceUrl = RestRepository.getInstance().getSrviceUrl("updateTrackName");
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
	
/*Method : People Add
 * Author : Santosh Kola
 * Date : 08/05/2015 
 */
 public String doAddPeople(AjaxHandlerAction ajaxHandlerAction) throws Exception{
       
 String message ="";
        boolean isUpdated = false;
      
        try {
            
              
                JSONObject jb = new JSONObject();
              
        jb.put("Name", String.valueOf(ajaxHandlerAction.getPeopleName()));
  
        jb.put("Designation", ajaxHandlerAction.getDesignation());
    
        jb.put("Organization", ajaxHandlerAction.getOrganization());
      
        jb.put("EmailId",ajaxHandlerAction.getEmail());
        jb.put("Status",ajaxHandlerAction.getStatus());
        jb.put("CreatedBy",ajaxHandlerAction.getCreatedBy());
       
         //   URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doEventPost/addEvent");
         String serviceUrl = RestRepository.getInstance().getSrviceUrl("addPeople");
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
public String getPeopleDetails(String peopleId) throws Exception{
     String message ="";
        boolean isUpdated = false;
        //  String queryString ="";
             // System.out.println("first call");
            String data = "";
        //queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ModifiedBy=?,ModifiedDate=?,ReviewName=?,ReviewDate=?,Status=?,TLComments=?,TLRating=?,HRRating=?,HrComments=? where Id=?";
        //if(roleName.equalsIgnoreCase("Employee"))
        // queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ReviewName=?,ReviewDate=?,Status=? where Id=?";
        try {
            
              
                JSONObject jb = new JSONObject();
      
        jb.put("Id",peopleId);        
          //  URL url = new URL("http://172.17.11.251:8080/WebRoot/resources/doJobPost/edit");
        //  URL url = new URL("http://172.17.14.226:8080/WebRoot/resources/doJobPost/edit");
       //  URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doJobPost/edit");
        String serviceUrl = RestRepository.getInstance().getSrviceUrl("getPeopleDetails");
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

      //  JSONObject jObject = new JSONObject(data);

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
        return data;
}




public String doUpdatePeopleDetails(AjaxHandlerAction ajaxHandlerAction) throws Exception{
    String message ="";
        boolean isUpdated = false;
      
        try {
            
              
                JSONObject jb = new JSONObject();
              jb.put("Id", String.valueOf(ajaxHandlerAction.getId()));
        jb.put("Name", String.valueOf(ajaxHandlerAction.getPeopleName()));
  
        jb.put("Designation", ajaxHandlerAction.getDesignation());
    
        jb.put("Organization", ajaxHandlerAction.getOrganization());
      
        jb.put("EmailId",ajaxHandlerAction.getEmail());
        jb.put("Status",ajaxHandlerAction.getStatus());
        jb.put("CreatedBy",ajaxHandlerAction.getCreatedBy());
         //   URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doEventPost/addEvent");
         String serviceUrl = RestRepository.getInstance().getSrviceUrl("updatePeopleDetails");
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
  	
public String doLibraryTitleCheck(AjaxHandlerAction ajaxHandlerAction) throws Exception{
       
 String message ="";
        boolean isUpdated = false;
      
        try {
            
              
                JSONObject jb = new JSONObject();
              
        jb.put("ResourceTitle", String.valueOf(ajaxHandlerAction.getResourceTitle()));
        //System.out.println("getResourceTitle "+ajaxHandlerAction.getResourceTitle());
  
        
       
         //   URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doEventPost/addEvent");
         String serviceUrl = RestRepository.getInstance().getSrviceUrl("libraryTitleCheck");
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

  

  public String doAddQuestionnaire(AjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException{
           
 String message ="";
    
      
        try {
                JSONObject jb = new JSONObject();
       
        jb.put("Query", ajaxHandlerAction.getQuestionTitle());
        jb.put("OptionType", ajaxHandlerAction.getOptionType());
        jb.put("SurveyId", ajaxHandlerAction.getSurveyId());
        jb.put("Sequence", ajaxHandlerAction.getQuerySequence());
         jb.put("PlaceHolder", ajaxHandlerAction.getPlaceHolder());
jb.put("questionStatus",ajaxHandlerAction.getQuestionStatus());
        if(ajaxHandlerAction.isIsRequired()){
            jb.put("IsRequired", 1);
        }else {
            jb.put("IsRequired", 0);
        }
        //CreatedBy
        jb.put("CreatedBy", ajaxHandlerAction.getCreatedBy());
        jb.put("Sequence", ajaxHandlerAction.getQuerySequence());
        if("Checkbox".equals(ajaxHandlerAction.getOptionType())||"RadioButton".equals(ajaxHandlerAction.getOptionType())){
            
            jb.put("OptionLabel1", ajaxHandlerAction.getOptionLabel1());
            jb.put("OptionSequence1", ajaxHandlerAction.getOptionSequence1());
             jb.put("OptionCount", ajaxHandlerAction.getOptionCount());
            
            if(ajaxHandlerAction.getOptionCount()>1){
                 jb.put("OptionLabel2", ajaxHandlerAction.getOptionLabel2());
            jb.put("OptionSequence2", ajaxHandlerAction.getOptionSequence2());
            } if(ajaxHandlerAction.getOptionCount()>2){
                 jb.put("OptionLabel3", ajaxHandlerAction.getOptionLabel3());
            jb.put("OptionSequence3", ajaxHandlerAction.getOptionSequence3());
            }if(ajaxHandlerAction.getOptionCount()>3){
                 jb.put("OptionLabel4", ajaxHandlerAction.getOptionLabel4());
            jb.put("OptionSequence4", ajaxHandlerAction.getOptionSequence4());
            }if(ajaxHandlerAction.getOptionCount()>4){
                 jb.put("OptionLabel5", ajaxHandlerAction.getOptionLabel5());
            jb.put("OptionSequence5", ajaxHandlerAction.getOptionSequence5());
            }if(ajaxHandlerAction.getOptionCount()>5){
                 jb.put("OptionLabel6", ajaxHandlerAction.getOptionLabel6());
            jb.put("OptionSequence6", ajaxHandlerAction.getOptionSequence6());
            }
        }
        else if("DropDown".equals(ajaxHandlerAction.getOptionType()))
        {
            jb.put("dropdownOptions", ajaxHandlerAction.getDropdownOptions());
        }
         if(ajaxHandlerAction.getAllowDocuments())
         jb.put("AllowDocuments", 1);
        else
            jb.put("AllowDocuments", 0);
        if(ajaxHandlerAction.getAllowPictures())
            jb.put("AllowPictures", 1);
        else
            jb.put("AllowPictures", 0);
     
         String serviceUrl = RestRepository.getInstance().getSrviceUrl("doAddQuestionnaire");
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
  
  public String editQuestionnaireDetails(AjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException{
     String message ="";
        boolean isUpdated = false;
    
            String data = "";
       
        try {
            
              
                JSONObject jb = new JSONObject();
      
        jb.put("SurveyId", ajaxHandlerAction.getSurveyId());
        jb.put("QuestionId", ajaxHandlerAction.getQuestionId());  
        
        String serviceUrl = RestRepository.getInstance().getSrviceUrl("editQuestionnaireDetails");
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

      //  JSONObject jObject = new JSONObject(data);

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
        }catch(Exception e){
             throw new ServiceLocatorException(e);
        }
        return data;
}


 public String doUpdateQuestionnaire(AjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException{
           
 String message ="";
    
      
        try {
                JSONObject jb = new JSONObject();
        jb.put("QuestionId", ajaxHandlerAction.getQuestionId());
        jb.put("Query", ajaxHandlerAction.getQuestionTitle());
        jb.put("OptionType", ajaxHandlerAction.getOptionType());
        jb.put("SurveyId", ajaxHandlerAction.getSurveyId());
        jb.put("Sequence", ajaxHandlerAction.getQuerySequence());
        jb.put("QuestionStatus", ajaxHandlerAction.getQuestionStatus());
        jb.put("PlaceHolder", ajaxHandlerAction.getPlaceHolder());
         if(ajaxHandlerAction.isIsRequired()){
            jb.put("IsRequired", 1);
        }else {
            jb.put("IsRequired", 0);
        }
         jb.put("CreatedBy", ajaxHandlerAction.getCreatedBy());
        if("Checkbox".equals(ajaxHandlerAction.getOptionType())||"RadioButton".equals(ajaxHandlerAction.getOptionType())){
            jb.put("OptionLabel1", ajaxHandlerAction.getOptionLabel1());
            jb.put("OptionSequence1", ajaxHandlerAction.getOptionSequence1());
             jb.put("OptionCount", ajaxHandlerAction.getOptionCount());
            
            if(ajaxHandlerAction.getOptionCount()>1){
                 jb.put("OptionLabel2", ajaxHandlerAction.getOptionLabel2());
            jb.put("OptionSequence2", ajaxHandlerAction.getOptionSequence2());
            } if(ajaxHandlerAction.getOptionCount()>2){
                 jb.put("OptionLabel3", ajaxHandlerAction.getOptionLabel3());
            jb.put("OptionSequence3", ajaxHandlerAction.getOptionSequence3());
            }if(ajaxHandlerAction.getOptionCount()>3){
                 jb.put("OptionLabel4", ajaxHandlerAction.getOptionLabel4());
            jb.put("OptionSequence4", ajaxHandlerAction.getOptionSequence4());
            }if(ajaxHandlerAction.getOptionCount()>4){
                 jb.put("OptionLabel5", ajaxHandlerAction.getOptionLabel5());
            jb.put("OptionSequence5", ajaxHandlerAction.getOptionSequence5());
            }if(ajaxHandlerAction.getOptionCount()>5){
                 jb.put("OptionLabel6", ajaxHandlerAction.getOptionLabel6());
            jb.put("OptionSequence6", ajaxHandlerAction.getOptionSequence6());
            }
        }
         else if("DropDown".equals(ajaxHandlerAction.getOptionType()))
        {
            jb.put("dropdownOptions", ajaxHandlerAction.getDropdownOptions());
        }
      if(ajaxHandlerAction.getAllowDocuments())
         jb.put("AllowDocuments", 1);
        else
            jb.put("AllowDocuments", 0);
        if(ajaxHandlerAction.getAllowPictures())
            jb.put("AllowPictures", 1);
        else
            jb.put("AllowPictures", 0);
         String serviceUrl = RestRepository.getInstance().getSrviceUrl("doUpdateQuestionnaire");
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

  public String showReviewDetails(int surveyInfoId) throws Exception{
          String message ="";
        boolean isUpdated = false;
        //  String queryString ="";
             // System.out.println("first call");
            String data = "";
        //queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ModifiedBy=?,ModifiedDate=?,ReviewName=?,ReviewDate=?,Status=?,TLComments=?,TLRating=?,HRRating=?,HrComments=? where Id=?";
        //if(roleName.equalsIgnoreCase("Employee"))
        // queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ReviewName=?,ReviewDate=?,Status=? where Id=?";
        try {
            
             JSONObject jb = new JSONObject();
    
        
          jb.put("surveyInfoId", surveyInfoId);
      String serviceUrl = RestRepository.getInstance().getSrviceUrl("getSurveyInfoDetails");
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
            
            
          //  System.out.println("data->"+data);
          

      //  JSONObject jObject = new JSONObject(data);

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
        return data;
      }
 public String getSearchQuestionInfo(int questionId,List questionList ) throws ServiceLocatorException{
          String message ="";
        boolean isUpdated = false;
         JSONObject subJson = new JSONObject();
        //  String queryString ="";
             // System.out.println("first call");
            String data = "";
        //queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ModifiedBy=?,ModifiedDate=?,ReviewName=?,ReviewDate=?,Status=?,TLComments=?,TLRating=?,HRRating=?,HrComments=? where Id=?";
        //if(roleName.equalsIgnoreCase("Employee"))
        // queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ReviewName=?,ReviewDate=?,Status=? where Id=?";
        try {
            
            
    for (Object quesObj : questionList) {
			//System.out.println(temp);
                            Map tempQmap = (Map)quesObj;
                           // if(tempQmap.get("OptionType").toString().equals("Textbox")||tempQmap.get("OptionType").toString().equals("Checkbox")||tempQmap.get("OptionType").toString().equals("RadioButton"))
                          if(Integer.parseInt(tempQmap.get("Id").toString())==questionId){
                          subJson.put("Id", tempQmap.get("Id"));
subJson.put("Query",tempQmap.get("Query"));
subJson.put("OptionType", tempQmap.get("OptionType"));
subJson.put("CurrStatus", tempQmap.get("CurrStatus"));
subJson.put("Sequence", tempQmap.get("Sequence"));
subJson.put("SurveyId", tempQmap.get("SurveyId"));
subJson.put("RequiredFlag", tempQmap.get("RequiredFlag"));
 if(subJson.getString("OptionType").equals("Checkbox")||subJson.getString("OptionType").equals("RadioButton")){
     subJson.put("OptionCount", tempQmap.get("OptionCount"));
subJson.put("OptionLabel1",tempQmap.get("OptionLabel1"));
subJson.put("OptionSequence1",tempQmap.get("OptionSequence1"));
                         
                         if(Integer.parseInt(subJson.getString("OptionCount"))>1){
                             subJson.put("OptionLabel2",tempQmap.get("OptionLabel2"));
subJson.put("OptionSequence2",tempQmap.get("OptionSequence2"));
                         } if(Integer.parseInt(subJson.getString("OptionCount"))>2){
                             subJson.put("OptionLabel3",tempQmap.get("OptionLabel3"));
subJson.put("OptionSequence3",tempQmap.get("OptionSequence3"));
                         } if(Integer.parseInt(subJson.getString("OptionCount"))>3){
                             subJson.put("OptionLabel4",tempQmap.get("OptionLabel4"));
subJson.put("OptionSequence4",tempQmap.get("OptionSequence4"));
                         } if(Integer.parseInt(subJson.getString("OptionCount"))>4){
                             subJson.put("OptionLabel5",tempQmap.get("OptionLabel5"));
subJson.put("OptionSequence5",tempQmap.get("OptionSequence5"));
                         }if(Integer.parseInt(subJson.getString("OptionCount"))>5){
                             subJson.put("OptionLabel6",tempQmap.get("OptionLabel6"));
subJson.put("OptionSequence6",tempQmap.get("OptionSequence6"));
                         }
      
 }
break;
                          }
    }
                            
                           
     
            
            
         //   System.out.println("data->"+data);
          

     
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return subJson.toString();
      }
 
  public String updateQuestionSequence(AjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException{
           
 String message ="";
    
      String data = "";
        try {
                JSONObject jb = new JSONObject();
        
                jb.put("CreatedBy", ajaxHandlerAction.getCreatedBy());
                jb.put("Data", ajaxHandlerAction.getQuestionSequanceData());
     
         String serviceUrl = RestRepository.getInstance().getSrviceUrl("updateQuestionSequence");
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

       // JSONObject jObject = new JSONObject(data);

        // message = jObject.getString("message");
     
//System.out.println("message-->"+message);

            in.close();
           
        } catch (IOException ex) {
            ex.printStackTrace();
        }catch(Exception e){
             throw new ServiceLocatorException(e);
        }
        return data;   
     }
  public String doUpdateSurveyFormExpiryDate(AjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException {

        String message = "";

        String data = "";
       try {
            JSONObject jb = new JSONObject();
            jb.put("surveyId", ajaxHandlerAction.getSurveyId());
           jb.put("ExpiryDate", ajaxHandlerAction.getExpiryDate());                              
            String serviceUrl = RestRepository.getInstance().getSrviceUrl("updateExpiry");
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

             message = jObject.getString("message");

//System.out.println("message-->"+message);

            in.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            throw new ServiceLocatorException(e);
        }
        return message;
    }
/*
   * Emeet Methods 
   * date : 01/29/2016
   * Author : Phani Kanuri
   * 
   */
    public String doAddExecitiveMeeting(AjaxHandlerAction ajaxHandlerAction) throws Exception {
        // Connection connection = null;
        //PreparedStatement preparedStatement = null;
        // ResultSet resultSet = null;
        String message = "";
        boolean isUpdated = false;

        try {

            JSONObject jb = new JSONObject();
            // AjaxHandlerAction ac=new AjaxHandlerAction();
            jb.put("ExecutiveMeetingType", ajaxHandlerAction.getExecutiveMeetingType());

            jb.put("ExecutiveMeetMonth", ajaxHandlerAction.getExecutiveMeetMonth());

            // jb.put("RegistrationTextforExeMeet", ajaxHandlerAction.getRegistrationTextforExeMeet());

            jb.put("ExeMeetcreatedDateTo", ajaxHandlerAction.getExeMeetcreatedDateTo());
            jb.put("ExecutiveMeetingStatus", ajaxHandlerAction.getExecutiveMeetingStatus());
            jb.put("ExeMeetStartTime", ajaxHandlerAction.getExeMeetStartTime());
            jb.put("ExeMeetEndTime", ajaxHandlerAction.getExeMeetEndTime());
            jb.put("ExeMeetStartmidDayFrom", ajaxHandlerAction.getExeMeetStartmidDayFrom());
            jb.put("ExeMeetEndmidDayTo", ajaxHandlerAction.getExeMeetEndmidDayTo());
            jb.put("RegistrationLinkForEMeet", ajaxHandlerAction.getRegistrationLinkForEMeet());
            jb.put("CreatedBy", ajaxHandlerAction.getCreatedBy());
            jb.put("TimeZone", ajaxHandlerAction.getTimeZone());
            jb.put("Month", ajaxHandlerAction.getExecutiveMeetMonth());

            //   URL url = new URL("http://172.17.11.251:8080/WebRoot/resources/doJobPost/post");
            // URL url = new URL("http://172.17.14.226:8080/WebRoot/resources/doJobPost/post");
            // URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doJobPost/post");
            String serviceUrl = RestRepository.getInstance().getSrviceUrl("doAddExecutiveMeeting");
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
            // System.out.println("displaymessage-->" +message);


            in.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return message;
    }

    public String editExeMeeting(int Id) throws Exception {
        String message = "";
        boolean isUpdated = false;
        //  String queryString ="";
        // System.out.println("first call");
        String data = "";

        try {


            JSONObject jb = new JSONObject();

            jb.put("Id", Id);
            //  URL url = new URL("http://172.17.11.251:8080/WebRoot/resources/doJobPost/edit");
            //  URL url = new URL("http://172.17.14.226:8080/WebRoot/resources/doJobPost/edit");
            // URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doJobPost/edit");
            String serviceUrl = RestRepository.getInstance().getSrviceUrl("eMeetEdit");
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

            //  JSONObject jObject = new JSONObject(data);

            // message = jObject.getString("message");
            //  System.out.println("displaymessage-->" +message);


            in.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return data;
    }

    public String updateExecitiveMeeting(AjaxHandlerAction ajaxHandlerAction) throws Exception {
        // Connection connection = null;
        //PreparedStatement preparedStatement = null;
        // ResultSet resultSet = null;
        String message = "";
        boolean isUpdated = false;
        //  String queryString ="";
        //   System.out.println("first call");

        //queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ModifiedBy=?,ModifiedDate=?,ReviewName=?,ReviewDate=?,Status=?,TLComments=?,TLRating=?,HRRating=?,HrComments=? where Id=?";
        //if(roleName.equalsIgnoreCase("Employee"))
        // queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ReviewName=?,ReviewDate=?,Status=? where Id=?";
        try {


            JSONObject jb = new JSONObject();
            // AjaxHandlerAction ac=new AjaxHandlerAction();

            jb.put("ExeMeetId", ajaxHandlerAction.getId());
            jb.put("ExecutiveMeetingType", ajaxHandlerAction.getExecutiveMeetingType());

            jb.put("ExecutiveMeetMonth", ajaxHandlerAction.getExecutiveMeetMonth());

            //   jb.put("RegistrationTextforExeMeet", ajaxHandlerAction.getRegistrationTextforExeMeet());

            jb.put("ExeMeetcreatedDateTo", ajaxHandlerAction.getExeMeetcreatedDateTo());
            jb.put("ExecutiveMeetingStatus", ajaxHandlerAction.getExecutiveMeetingStatus());
            jb.put("ExeMeetStartTime", ajaxHandlerAction.getExeMeetStartTime());
            jb.put("ExeMeetEndTime", ajaxHandlerAction.getExeMeetEndTime());
            jb.put("ExeMeetStartmidDayFrom", ajaxHandlerAction.getExeMeetStartmidDayFrom());
            jb.put("ExeMeetEndmidDayTo", ajaxHandlerAction.getExeMeetEndmidDayTo());
            jb.put("RegistrationLinkForEMeet", ajaxHandlerAction.getRegistrationLinkForEMeet());
            jb.put("CreatedBy", ajaxHandlerAction.getCreatedBy());
            jb.put("TimeZone", ajaxHandlerAction.getTimeZone());
            jb.put("Month", ajaxHandlerAction.getExecutiveMeetMonth());
            //URL url = new URL("http://172.17.11.251:8080/WebRoot/resources/doJobPost/update");
            //URL url = new URL("http://172.17.14.226:8080/WebRoot/resources/doJobPost/update");
            //  URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doJobPost/update");

            String serviceUrl = RestRepository.getInstance().getSrviceUrl("executiveMeetingUpdate");
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
        }
        return message;
    }

    public String updateCompletedExecitiveMeetDetails(AjaxHandlerAction ajaxHandlerAction) throws Exception {
        // Connection connection = null;
        //PreparedStatement preparedStatement = null;
        // ResultSet resultSet = null;
        String message = "";
        boolean isUpdated = false;

        try {


            JSONObject jb = new JSONObject();
            // AjaxHandlerAction ac=new AjaxHandlerAction();

            jb.put("ExeMeetId", ajaxHandlerAction.getId());
            jb.put("ExecutiveMeetingStatus", ajaxHandlerAction.getExecutiveMeetingStatus());
            jb.put("CreatedBy", ajaxHandlerAction.getCreatedBy());
            jb.put("VideoLink", ajaxHandlerAction.getVideoLinkForEMeet());
            //URL url = new URL("http://172.17.11.251:8080/WebRoot/resources/doJobPost/update");
            //URL url = new URL("http://172.17.14.226:8080/WebRoot/resources/doJobPost/update");
            //  URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doJobPost/update");

            String serviceUrl = RestRepository.getInstance().getSrviceUrl("updateCompletedExeMeetDetails");
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
        }
        return message;
    }

    public String doActiveEmmet(AjaxHandlerAction ajaxHandlerAction) throws Exception {
        // Connection connection = null;
        //PreparedStatement preparedStatement = null;
        // ResultSet resultSet = null;
        String message = "";
        boolean isUpdated = false;

        try {


            JSONObject jb = new JSONObject();
            // AjaxHandlerAction ac=new AjaxHandlerAction();

            jb.put("ExeMeetId", ajaxHandlerAction.getId());

            jb.put("CreatedBy", ajaxHandlerAction.getCreatedBy());
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
        }
        return message;
    }

    public JSONObject getUpcomingEmeets(String emeetType) throws ServiceLocatorException {
        List jobsList = null;
        List mainList = null;
        String createdDateFrom;
        String createdDateTo;
        String jobId;
        String title;
        String status;

        JSONObject jObject = null;
        try {

            JSONObject jb = new JSONObject();


            jb.put("EMeetCreatedDate", "none");
            jb.put("EmeetFromDate", "none");
            jb.put("EmeetToDate", "none");
            jb.put("EmeetStatusByDate", "U");
            jb.put("EmeetStatusForMails", "Published");
            jb.put("ExecutiveMeetMonthSearch", "none");
            if (!"Both".equals(emeetType)) {
                jb.put("EMeetType", emeetType);
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
            jObject = new JSONObject(data);


            in.close();
        } catch (Exception e) {
            System.out.println("\nError while calling REST Service");
            //System.out.println(e);
        }
        return jObject;
    }
/*Events functionality new enhancements start
     * Date : 01/23/2017
     * Author : Santosh Kola/Phani Kanuri
     */
    
public String doAddQmeetEventPost(AjaxHandlerAction ajaxHandlerAction) throws Exception{
       
 String message ="";
        boolean isUpdated = false;
      
        try {
            
              
                JSONObject jb = new JSONObject();
              
        jb.put("event_title", ajaxHandlerAction.getEventTitle());
  
        jb.put("event_description", ajaxHandlerAction.getEventDescription());
    
        jb.put("event_startdate", ajaxHandlerAction.getStartDate());
      
        //jb.put("evetnt_enddate",ajaxHandlerAction.getEndDate());
        jb.put("event_time_from",ajaxHandlerAction.getStartTime());
        jb.put("event_time_to",ajaxHandlerAction.getEndTime());  
        jb.put("midday_from",ajaxHandlerAction.getMidDayFrom()); 
        jb.put("midday_to",ajaxHandlerAction.getMidDayTo()); 
        jb.put("timezone",ajaxHandlerAction.getTimeZone()); 
        jb.put("location",ajaxHandlerAction.getEventLocation()); 
        jb.put("transport",ajaxHandlerAction.getTransportation()); 
        jb.put("createdby",ajaxHandlerAction.getCreatedBy()); 
       jb.put("status",ajaxHandlerAction.getEventStatus()); 
       
     //    System.out.println("getConferenceUrl-->"+ajaxHandlerAction.getConferenceUrl());
         //   URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doEventPost/addEvent");
         String serviceUrl = RestRepository.getInstance().getSrviceUrl("addQmeetEvent");
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
        }
        return message;   
      }     
       public String editEventpost(String eventId) throws Exception{
          String message ="";
        boolean isUpdated = false;
        //  String queryString ="";
             // System.out.println("first call");
            String data = "";
        //queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ModifiedBy=?,ModifiedDate=?,ReviewName=?,ReviewDate=?,Status=?,TLComments=?,TLRating=?,HRRating=?,HrComments=? where Id=?";
        //if(roleName.equalsIgnoreCase("Employee"))
        // queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ReviewName=?,ReviewDate=?,Status=? where Id=?";
        try {
            
              
                JSONObject jb = new JSONObject();
      
        jb.put("event_id",eventId);        
          //  URL url = new URL("http://172.17.11.251:8080/WebRoot/resources/doJobPost/edit");
        //  URL url = new URL("http://172.17.14.226:8080/WebRoot/resources/doJobPost/edit");
       //  URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doJobPost/edit");
        String serviceUrl = RestRepository.getInstance().getSrviceUrl("editEventPost");
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

            while ((s = in.readLine()) != null) {

                data = data + s;
            }

      //  JSONObject jObject = new JSONObject(data);

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
        return data;
      }
       
       
        public String updateQmeetEventPost(AjaxHandlerAction ajaxHandlerAction) throws Exception{
         
 String message ="";
        boolean isUpdated = false;
      
        try {
            
              
                JSONObject jb = new JSONObject();
              jb.put("event_id", String.valueOf(ajaxHandlerAction.getEventId()));
        jb.put("event_title", ajaxHandlerAction.getEventTitle());
  
        jb.put("event_description", ajaxHandlerAction.getEventDescription());
    
        jb.put("event_startdate", ajaxHandlerAction.getStartDate());
      
        //jb.put("evetnt_enddate",ajaxHandlerAction.getEndDate());
        jb.put("event_time_from",ajaxHandlerAction.getStartTime());
        jb.put("event_time_to",ajaxHandlerAction.getEndTime());  
        jb.put("midday_from",ajaxHandlerAction.getMidDayFrom()); 
        
       // System.out.println("getMidDayTo-->"+ajaxHandlerAction.getMidDayTo());
        jb.put("midday_to",ajaxHandlerAction.getMidDayTo()); 
        jb.put("timezone",ajaxHandlerAction.getTimeZone()); 
        jb.put("location",ajaxHandlerAction.getEventLocation()); 
        jb.put("transport",ajaxHandlerAction.getTransportation()); 
        jb.put("createdby",ajaxHandlerAction.getCreatedBy()); 
       jb.put("status",ajaxHandlerAction.getEventStatus()); 
       // jb.put("WebinarType",ajaxHandlerAction.getEventType()); 
       
         
        // System.out.println("VideoLink-->"+ajaxHandlerAction.getEventAfterVideoUrl());
       //  System.out.println("get VideoLink-->"+jb.getString("VideoLink"));
      //  System.out.println("getConferenceUrl-->"+ajaxHandlerAction.getConferenceUrl());
         //   URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doEventPost/addEvent");
         String serviceUrl = RestRepository.getInstance().getSrviceUrl("updateQmeetEventPost");
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
        }
        return message;   
     } 
        
       
public String doAddExternalWebinarEventPost(AjaxHandlerAction ajaxHandlerAction) throws Exception{
       
 String message ="";
        boolean isUpdated = false;
      
        try {
            
              
                JSONObject jb = new JSONObject();
              
        jb.put("event_title", ajaxHandlerAction.getEventTitle());
  
        jb.put("event_description", ajaxHandlerAction.getEventDescription());
    
        jb.put("event_startdate", ajaxHandlerAction.getStartDate());
      
        jb.put("evetnt_enddate",ajaxHandlerAction.getEndDate());
        jb.put("event_time_from",ajaxHandlerAction.getStartTime());
        jb.put("event_time_to",ajaxHandlerAction.getEndTime());  
        jb.put("midday_from",ajaxHandlerAction.getMidDayFrom()); 
        jb.put("midday_to",ajaxHandlerAction.getMidDayTo()); 
        jb.put("timezone",ajaxHandlerAction.getTimeZone()); 
        jb.put("location",ajaxHandlerAction.getEventLocation()); 
       // jb.put("transport",ajaxHandlerAction.getTransportation()); 
        jb.put("createdby",ajaxHandlerAction.getCreatedBy()); 
       jb.put("status",ajaxHandlerAction.getEventStatus()); 
        //jb.put("WebinarType",ajaxHandlerAction.getEventType()); 
        
       // if(ajaxHandlerAction.getEventType().equalsIgnoreCase("I")|| ajaxHandlerAction.getEventType().equalsIgnoreCase("E")){
        jb.put("event_bold_Title",ajaxHandlerAction.getEventBoldtitle()); 
        jb.put("event_tagline",ajaxHandlerAction.getEventRegluarTitle()); 
        jb.put("OrganizerEmail",ajaxHandlerAction.getContactUsEmail()); 
        jb.put("RegistrationLink",ajaxHandlerAction.getEventRegistrationLink()); 
        
       // jb.put("PrimaryTrack",ajaxHandlerAction.getPrimaryTrack());
       // jb.put("SecondaryTrack",ajaxHandlerAction.getSecondaryTrack());
         if (!"".equals(ajaxHandlerAction.getPrimaryTrack()) && ajaxHandlerAction.getPrimaryTrack() != null) {
                jb.put("PrimaryTrack", ajaxHandlerAction.getPrimaryTrack());
            } else {
                jb.put("PrimaryTrack", "0");
            }
            if (!"".equals(ajaxHandlerAction.getSecondaryTrack()) && ajaxHandlerAction.getSecondaryTrack() != null) {
                jb.put("SecondaryTrack", ajaxHandlerAction.getSecondaryTrack());
            } else {
                jb.put("SecondaryTrack", "0");
            }
       // jb.put("Department",ajaxHandlerAction.getEventDepartment());
       // }
         
         //jb.put("VideoLink",ajaxHandlerAction.getEventAfterVideoUrl());
     //    System.out.println("getConferenceUrl-->"+ajaxHandlerAction.getConferenceUrl());
         //   URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doEventPost/addEvent");
         String serviceUrl = RestRepository.getInstance().getSrviceUrl("externalWebinarEventAdd");
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
        }
        return message;   
      }     
       
        
         public String editExternalEventpost(String eventId) throws Exception{
          String message ="";
        boolean isUpdated = false;
        //  String queryString ="";
             // System.out.println("first call");
            String data = "";
        //queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ModifiedBy=?,ModifiedDate=?,ReviewName=?,ReviewDate=?,Status=?,TLComments=?,TLRating=?,HRRating=?,HrComments=? where Id=?";
        //if(roleName.equalsIgnoreCase("Employee"))
        // queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ReviewName=?,ReviewDate=?,Status=? where Id=?";
        try {
            
              
                JSONObject jb = new JSONObject();
      
        jb.put("event_id",eventId);        
          //  URL url = new URL("http://172.17.11.251:8080/WebRoot/resources/doJobPost/edit");
        //  URL url = new URL("http://172.17.14.226:8080/WebRoot/resources/doJobPost/edit");
       //  URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doJobPost/edit");
        String serviceUrl = RestRepository.getInstance().getSrviceUrl("editExternalEventPost");
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

            while ((s = in.readLine()) != null) {

                data = data + s;
            }

      //  JSONObject jObject = new JSONObject(data);

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
        return data;
      }
         
         
          public String updateExternalWebinarEventposting(AjaxHandlerAction ajaxHandlerAction) throws Exception{
         
 String message ="";
        boolean isUpdated = false;
      
        try {
            
              
                JSONObject jb = new JSONObject();
              jb.put("event_id", String.valueOf(ajaxHandlerAction.getEventId()));
        jb.put("event_title", ajaxHandlerAction.getEventTitle());
  
        jb.put("event_description", ajaxHandlerAction.getEventDescription());
    
        jb.put("event_startdate", ajaxHandlerAction.getStartDate());
      
       // jb.put("evetnt_enddate",ajaxHandlerAction.getEndDate());
        jb.put("event_time_from",ajaxHandlerAction.getStartTime());
        jb.put("event_time_to",ajaxHandlerAction.getEndTime());  
        jb.put("midday_from",ajaxHandlerAction.getMidDayFrom()); 
        
       // System.out.println("getMidDayTo-->"+ajaxHandlerAction.getMidDayTo());
        jb.put("midday_to",ajaxHandlerAction.getMidDayTo()); 
        jb.put("timezone",ajaxHandlerAction.getTimeZone()); 
        jb.put("location",ajaxHandlerAction.getEventLocation()); 
        //jb.put("transport",ajaxHandlerAction.getTransportation()); 
        jb.put("createdby",ajaxHandlerAction.getCreatedBy()); 
       jb.put("status",ajaxHandlerAction.getEventStatus()); 
        //jb.put("WebinarType",ajaxHandlerAction.getEventType()); 
      // if(ajaxHandlerAction.getEventType().equalsIgnoreCase("I")|| ajaxHandlerAction.getEventType().equalsIgnoreCase("E")){
        jb.put("event_bold_Title",ajaxHandlerAction.getEventBoldtitle()); 
        jb.put("event_tagline",ajaxHandlerAction.getEventRegluarTitle()); 
        jb.put("OrganizerEmail",ajaxHandlerAction.getContactUsEmail()); 
        jb.put("RegistrationLink",ajaxHandlerAction.getEventRegistrationLink()); 
        
       if (!"".equals(ajaxHandlerAction.getPrimaryTrack()) && ajaxHandlerAction.getPrimaryTrack() != null) {
                jb.put("PrimaryTrack", ajaxHandlerAction.getPrimaryTrack());
            } else {
                jb.put("PrimaryTrack", "0");
            }
            if (!"".equals(ajaxHandlerAction.getSecondaryTrack()) && ajaxHandlerAction.getSecondaryTrack() != null) {
                jb.put("SecondaryTrack", ajaxHandlerAction.getSecondaryTrack());
            } else {
                jb.put("SecondaryTrack", "0");
            }
       // jb.put("Department",ajaxHandlerAction.getEventDepartment());
        
       // jb.put("IsAssociated",ajaxHandlerAction.getIsAssociated());
        //jb.put("AsociatedEventId",ajaxHandlerAction.getAssociatedEventId());
        
      //  }
       
      // if(ajaxHandlerAction.getEventType().equalsIgnoreCase("C")){
        //     jb.put("event_redirect",ajaxHandlerAction.getConferenceUrl()); 
        // }
        // jb.put("VideoLink",ajaxHandlerAction.getEventAfterVideoUrl());
         jb.put("SeriesId",ajaxHandlerAction.getSeriesId());
         
        // System.out.println("VideoLink-->"+ajaxHandlerAction.getEventAfterVideoUrl());
       //  System.out.println("get VideoLink-->"+jb.getString("VideoLink"));
      //  System.out.println("getConferenceUrl-->"+ajaxHandlerAction.getConferenceUrl());
         //   URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doEventPost/addEvent");
         String serviceUrl = RestRepository.getInstance().getSrviceUrl("externalWebinarEventUpdate");
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
        }
        return message;   
     } 
     
       /*
           * 
           * 
           * phani methods
           */
          
          
    public String doAddIEEEventPost(AjaxHandlerAction ajaxHandlerAction) throws Exception {

        String message = "";
        boolean isUpdated = false;

        try {


            JSONObject jb = new JSONObject();

            jb.put("event_title", ajaxHandlerAction.getEventTitle());
            jb.put("event_startdate", ajaxHandlerAction.getStartDate());
            jb.put("evetnt_enddate",ajaxHandlerAction.getStartDate());
            jb.put("event_time_from", ajaxHandlerAction.getStartTime());
            jb.put("event_time_to", ajaxHandlerAction.getEndTime());
            jb.put("midday_from", ajaxHandlerAction.getMidDayFrom());
            jb.put("midday_to", ajaxHandlerAction.getMidDayTo());
            jb.put("timezone", ajaxHandlerAction.getTimeZone());
            jb.put("location", ajaxHandlerAction.getEventLocation());
            jb.put("createdby", ajaxHandlerAction.getCreatedBy());
            jb.put("status", ajaxHandlerAction.getEventStatus());

            //    System.out.println("getConferenceUrl-->"+ajaxHandlerAction.getConferenceUrl());
            //   URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doEventPost/addEvent");
            String serviceUrl = RestRepository.getInstance().getSrviceUrl("addIEEEvent");
            URL url = new URL(serviceUrl);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(360 * 5000);
            connection.setReadTimeout(360 * 5000);
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
    
    public String updateIEEEventPost(AjaxHandlerAction ajaxHandlerAction) throws Exception {

        String message = "";
        boolean isUpdated = false;

        try {


            JSONObject jb = new JSONObject();
            jb.put("event_id", String.valueOf(ajaxHandlerAction.getEventId()));
            jb.put("event_title", ajaxHandlerAction.getEventTitle());

         //   jb.put("event_description", ajaxHandlerAction.getEventDescription());

            jb.put("event_startdate", ajaxHandlerAction.getStartDate());

            //jb.put("evetnt_enddate",ajaxHandlerAction.getEndDate());
            jb.put("event_time_from", ajaxHandlerAction.getStartTime());
            jb.put("event_time_to", ajaxHandlerAction.getEndTime());
            jb.put("midday_from", ajaxHandlerAction.getMidDayFrom());

            // System.out.println("getMidDayTo-->"+ajaxHandlerAction.getMidDayTo());
            jb.put("midday_to", ajaxHandlerAction.getMidDayTo());
            jb.put("timezone", ajaxHandlerAction.getTimeZone());
            jb.put("location", ajaxHandlerAction.getEventLocation());
           // jb.put("transport", ajaxHandlerAction.getTransportation());
            jb.put("createdby", ajaxHandlerAction.getCreatedBy());
            jb.put("status", ajaxHandlerAction.getEventStatus());
            // jb.put("WebinarType",ajaxHandlerAction.getEventType()); 


            // System.out.println("VideoLink-->"+ajaxHandlerAction.getEventAfterVideoUrl());
            //  System.out.println("get VideoLink-->"+jb.getString("VideoLink"));
            //  System.out.println("getConferenceUrl-->"+ajaxHandlerAction.getConferenceUrl());
            //   URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doEventPost/addEvent");
            String serviceUrl = RestRepository.getInstance().getSrviceUrl("updateIEEEventPost");
            URL url = new URL(serviceUrl);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(360 * 5000);
            connection.setReadTimeout(360 * 5000);
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
    
    
    
public String doAddInternalWebinarEventPost(AjaxHandlerAction ajaxHandlerAction) throws Exception{
       
 String message ="";
        boolean isUpdated = false;
      
        try {
            
              
                JSONObject jb = new JSONObject();
              
        jb.put("event_title", ajaxHandlerAction.getEventTitle());
  
        jb.put("event_description", ajaxHandlerAction.getEventDescription());
    
        jb.put("event_startdate", ajaxHandlerAction.getStartDate());
      
       // jb.put("evetnt_enddate",ajaxHandlerAction.getEndDate());
        jb.put("event_time_from",ajaxHandlerAction.getStartTime());
        jb.put("event_time_to",ajaxHandlerAction.getEndTime());  
        jb.put("midday_from",ajaxHandlerAction.getMidDayFrom()); 
        jb.put("midday_to",ajaxHandlerAction.getMidDayTo()); 
        jb.put("timezone",ajaxHandlerAction.getTimeZone()); 
        jb.put("location",ajaxHandlerAction.getEventLocation()); 
        //jb.put("transport",ajaxHandlerAction.getTransportation()); 
        jb.put("createdby",ajaxHandlerAction.getCreatedBy()); 
       jb.put("status",ajaxHandlerAction.getEventStatus()); 
        //jb.put("WebinarType",ajaxHandlerAction.getEventType()); 
        
       // if(ajaxHandlerAction.getEventType().equalsIgnoreCase("I")|| ajaxHandlerAction.getEventType().equalsIgnoreCase("E")){
        jb.put("event_bold_Title",ajaxHandlerAction.getEventBoldtitle()); 
        jb.put("event_tagline",ajaxHandlerAction.getEventRegluarTitle()); 
        jb.put("OrganizerEmail",ajaxHandlerAction.getContactUsEmail()); 
        jb.put("RegistrationLink",ajaxHandlerAction.getEventRegistrationLink()); 
        
       // jb.put("PrimaryTrack",ajaxHandlerAction.getPrimaryTrack());
       // jb.put("SecondaryTrack",ajaxHandlerAction.getSecondaryTrack());
         if (!"".equals(ajaxHandlerAction.getPrimaryTrack()) && ajaxHandlerAction.getPrimaryTrack() != null) {
                jb.put("PrimaryTrack", ajaxHandlerAction.getPrimaryTrack());
            } else {
                jb.put("PrimaryTrack", "0");
            }
            if (!"".equals(ajaxHandlerAction.getSecondaryTrack()) && ajaxHandlerAction.getSecondaryTrack() != null) {
                jb.put("SecondaryTrack", ajaxHandlerAction.getSecondaryTrack());
            } else {
                jb.put("SecondaryTrack", "0");
            }
        jb.put("Department",ajaxHandlerAction.getEventDepartment());
       // }
         
         
     //    System.out.println("getConferenceUrl-->"+ajaxHandlerAction.getConferenceUrl());
         //   URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doEventPost/addEvent");
         String serviceUrl = RestRepository.getInstance().getSrviceUrl("internalWebinarEventAdd");
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
        }
        return message;   
      }     
       public String updateInternalWebinarEvent(AjaxHandlerAction ajaxHandlerAction) throws Exception{
         
 String message ="";
        boolean isUpdated = false;
      
        try {
            
              
                JSONObject jb = new JSONObject();
              jb.put("event_id", String.valueOf(ajaxHandlerAction.getEventId()));
        jb.put("event_title", ajaxHandlerAction.getEventTitle());
  
        jb.put("event_description", ajaxHandlerAction.getEventDescription());
   // System.out.println("event_startdate-->"+ajaxHandlerAction.getStartDate());
        jb.put("event_startdate", ajaxHandlerAction.getStartDate());
      
        //jb.put("evetnt_enddate",ajaxHandlerAction.getEndDate());
        jb.put("event_time_from",ajaxHandlerAction.getStartTime());
        jb.put("event_time_to",ajaxHandlerAction.getEndTime());  
        jb.put("midday_from",ajaxHandlerAction.getMidDayFrom()); 
        
       // System.out.println("getMidDayTo-->"+ajaxHandlerAction.getMidDayTo());
        jb.put("midday_to",ajaxHandlerAction.getMidDayTo()); 
        jb.put("timezone",ajaxHandlerAction.getTimeZone()); 
        jb.put("location",ajaxHandlerAction.getEventLocation()); 
       // jb.put("transport",ajaxHandlerAction.getTransportation()); 
        jb.put("createdby",ajaxHandlerAction.getCreatedBy()); 
       jb.put("status",ajaxHandlerAction.getEventStatus()); 
        //jb.put("WebinarType",ajaxHandlerAction.getEventType()); 
       //if(ajaxHandlerAction.getEventType().equalsIgnoreCase("I")|| ajaxHandlerAction.getEventType().equalsIgnoreCase("E")){
        jb.put("event_bold_Title",ajaxHandlerAction.getEventBoldtitle()); 
        jb.put("event_tagline",ajaxHandlerAction.getEventRegluarTitle()); 
        jb.put("OrganizerEmail",ajaxHandlerAction.getContactUsEmail()); 
        jb.put("RegistrationLink",ajaxHandlerAction.getEventRegistrationLink()); 
        
       if (!"".equals(ajaxHandlerAction.getPrimaryTrack()) && ajaxHandlerAction.getPrimaryTrack() != null) {
                jb.put("PrimaryTrack", ajaxHandlerAction.getPrimaryTrack());
            } else {
                jb.put("PrimaryTrack", "0");
            }
            if (!"".equals(ajaxHandlerAction.getSecondaryTrack()) && ajaxHandlerAction.getSecondaryTrack() != null) {
                jb.put("SecondaryTrack", ajaxHandlerAction.getSecondaryTrack());
            } else {
                jb.put("SecondaryTrack", "0");
            }
        jb.put("Department",ajaxHandlerAction.getEventDepartment());
        
       // jb.put("IsAssociated",ajaxHandlerAction.getIsAssociated());
        //jb.put("AsociatedEventId",ajaxHandlerAction.getAssociatedEventId());
        
        //}
       
       
       //  jb.put("VideoLink",ajaxHandlerAction.getEventAfterVideoUrl());
         jb.put("SeriesId",ajaxHandlerAction.getSeriesId());
         
        // System.out.println("VideoLink-->"+ajaxHandlerAction.getEventAfterVideoUrl());
       //  System.out.println("get VideoLink-->"+jb.getString("VideoLink"));
      //  System.out.println("getConferenceUrl-->"+ajaxHandlerAction.getConferenceUrl());
         //   URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doEventPost/addEvent");
         String serviceUrl = RestRepository.getInstance().getSrviceUrl("internalWebinarEventUpdate");
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
        }
        return message;   
     } 
     public String updateExternalConferenceEventposting(AjaxHandlerAction ajaxHandlerAction) throws Exception {

        String message = "";
        boolean isUpdated = false;

        try {

           // System.out.println("in updateExternalConferenceEventposting impl");
            JSONObject jb = new JSONObject();
            jb.put("event_id", String.valueOf(ajaxHandlerAction.getEventId()));
            jb.put("event_title", ajaxHandlerAction.getEventTitle());
            jb.put("event_startdate", ajaxHandlerAction.getStartDate());
            jb.put("evetnt_enddate", ajaxHandlerAction.getEndDate());
            jb.put("location", ajaxHandlerAction.getEventLocation());
            jb.put("createdby", ajaxHandlerAction.getCreatedBy());
            jb.put("status", ajaxHandlerAction.getEventStatus());
            jb.put("event_redirect", ajaxHandlerAction.getConferenceUrl());
            jb.put("WebinarType", ajaxHandlerAction.getEventType());
            String serviceUrl = RestRepository.getInstance().getSrviceUrl("externalConferenceEventUpdate");
            URL url = new URL(serviceUrl);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(360 * 5000);
            connection.setReadTimeout(360 * 5000);
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

    public String doAddExternalConferenceEventPost(AjaxHandlerAction ajaxHandlerAction) throws Exception {

        String message = "";
        boolean isUpdated = false;

        try {

            JSONObject jb = new JSONObject();

            jb.put("event_title", ajaxHandlerAction.getEventTitle());
            jb.put("event_startdate", ajaxHandlerAction.getStartDate());
            jb.put("evetnt_enddate", ajaxHandlerAction.getEndDate());
            jb.put("location", ajaxHandlerAction.getEventLocation());
            jb.put("createdby", ajaxHandlerAction.getCreatedBy());
            jb.put("status", ajaxHandlerAction.getEventStatus());
            jb.put("WebinarType",ajaxHandlerAction.getEventType()); 
            jb.put("event_redirect", ajaxHandlerAction.getConferenceUrl());

            String serviceUrl = RestRepository.getInstance().getSrviceUrl("externalConferenceEventAdd");
            URL url = new URL(serviceUrl);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(360 * 5000);
            connection.setReadTimeout(360 * 5000);
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
    
   /*  public String editWebianarEventposting(String eventId) throws Exception {
        String message = "";
        boolean isUpdated = false;
        String data = "";
        try {
            JSONObject jb = new JSONObject();
            jb.put("event_id", eventId);
          //  String serviceUrl = RestRepository.getInstance().getSrviceUrl("webinarEventEdit");
              String serviceUrl = RestRepository.getInstance().getSrviceUrl("webinarEventEdit");
            URL url = new URL(serviceUrl);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(360 * 5000);
            connection.setReadTimeout(360 * 5000);
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write(jb.toString());
            out.close();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String s = null;

            while ((s = in.readLine()) != null) {

                data = data + s;
            }
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return data;
    }
    */
    
    public String updateWebinarAfterEvent(AjaxHandlerAction ajaxHandlerAction) throws Exception {

        String message = "";
        boolean isUpdated = false;

        try {


            JSONObject jb = new JSONObject();

            jb.put("eventId", String.valueOf(ajaxHandlerAction.getEventId()));

            jb.put("eventAfterDescription", ajaxHandlerAction.getEventAfterDescription());

            jb.put("eventAfterVideoUrl", ajaxHandlerAction.getEventAfterVideoUrl());

            jb.put("eventStatus", ajaxHandlerAction.getEventStatus());
//            jb.put("primaryTrack", ajaxHandlerAction.getPrimaryTrack());
//            jb.put("secondaryTrack", ajaxHandlerAction.getSecondaryTrack());
            jb.put("eventType", ajaxHandlerAction.getEventType());
            //eventType

            //   URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doEventPost/addEvent");
            String serviceUrl = RestRepository.getInstance().getSrviceUrl("updateWebinarAfterEvent");
            URL url = new URL(serviceUrl);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(360 * 5000);
            connection.setReadTimeout(360 * 5000);
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
    
    
    
     public String getWebinarSeriesList(String seriesType) throws Exception {
        String message = "";
        boolean isUpdated = false;
        String data = "";
        try {
            JSONObject jb = new JSONObject();
            jb.put("SeriesType", seriesType);
            String serviceUrl = RestRepository.getInstance().getSrviceUrl("getWebinarSeriesList");
            URL url = new URL(serviceUrl);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(360 * 5000);
            connection.setReadTimeout(360 * 5000);
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write(jb.toString());
            out.close();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String s = null;

            while ((s = in.readLine()) != null) {

                data = data + s;
            }
            
           
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return data;
    }
     
     
     
     
     
public String doAddWebinarSeries(AjaxHandlerAction ajaxHandlerAction) throws Exception {
       
 String message ="";
        boolean isUpdated = false;
      
        try {
            
              
                JSONObject jb = new JSONObject();
              
        jb.put("SeriesTitle", ajaxHandlerAction.getSeriesTitle());
  
        jb.put("SeriesType", ajaxHandlerAction.getSeriesType());
    
      //  jb.put("SeriesTrack", ajaxHandlerAction.getSeriesTrack());
        if (!"".equals(ajaxHandlerAction.getSeriesTrack()) && ajaxHandlerAction.getSeriesTrack() != null) {
                jb.put("SeriesTrack", ajaxHandlerAction.getSeriesTrack());
            } else {
                jb.put("SeriesTrack", "0");
            }
      
        jb.put("SeriesStatus",ajaxHandlerAction.getSeriesStatus());
       
       jb.put("CreatedBy",ajaxHandlerAction.getCreatedBy());
         //   URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doEventPost/addEvent");
         String serviceUrl = RestRepository.getInstance().getSrviceUrl("addWebinarSeries");
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
        }
        return message;   
}


public String getWebinarSeriesDetails(String seriesId) throws Exception{
            String message ="";
        boolean isUpdated = false;
        //  String queryString ="";
             // System.out.println("first call");
            String data = "";
        //queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ModifiedBy=?,ModifiedDate=?,ReviewName=?,ReviewDate=?,Status=?,TLComments=?,TLRating=?,HRRating=?,HrComments=? where Id=?";
        //if(roleName.equalsIgnoreCase("Employee"))
        // queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ReviewName=?,ReviewDate=?,Status=? where Id=?";
        try {
            
              
                JSONObject jb = new JSONObject();
      
        jb.put("Id",seriesId);        
          //  URL url = new URL("http://172.17.11.251:8080/WebRoot/resources/doJobPost/edit");
        //  URL url = new URL("http://172.17.14.226:8080/WebRoot/resources/doJobPost/edit");
       //  URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doJobPost/edit");
        String serviceUrl = RestRepository.getInstance().getSrviceUrl("getWebinarSeriesDetails");
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

            while ((s = in.readLine()) != null) {

                data = data + s;
            }

      //  JSONObject jObject = new JSONObject(data);

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
        return data;
       }


public String doUpdateWebinarSeriesDetails(AjaxHandlerAction ajaxHandlerAction) throws Exception{
    String message ="";
        boolean isUpdated = false;
      
        try {
            
              
                JSONObject jb = new JSONObject();
              jb.put("Id", ajaxHandlerAction.getSeriesId());
        jb.put("SeriesTitle", ajaxHandlerAction.getSeriesTitle());
  
        jb.put("SeriesType", ajaxHandlerAction.getSeriesType());
    
   //     jb.put("SeriesTrack", ajaxHandlerAction.getSeriesTrack());
         if (!"".equals(ajaxHandlerAction.getSeriesTrack()) && ajaxHandlerAction.getSeriesTrack() != null) {
                jb.put("SeriesTrack", ajaxHandlerAction.getSeriesTrack());
            } else {
                jb.put("SeriesTrack", "0");
            }
      
        jb.put("SeriesStatus",ajaxHandlerAction.getSeriesStatus());
       
       jb.put("CreatedBy",ajaxHandlerAction.getCreatedBy());
         //   URL url = new URL(Properties.getProperty("WEB.REST.URL") +"doEventPost/addEvent");
         String serviceUrl = RestRepository.getInstance().getSrviceUrl("updateWebinarSeriesDetails");
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
        }
        return message;   
}     


public String getQmeetAttendeeDetails(HttpServletRequest httpServletRequest,String infoId) throws Exception{
    String message ="";
        boolean isUpdated = false;
      JSONObject jb = new JSONObject();
        try {
            List qmeetAttendeeList = (List)httpServletRequest.getSession(false).getAttribute("qmeetAttendeesList");
            Map attendeeDetails ;
            for (int i = 0; i < qmeetAttendeeList.size(); i++) {
                attendeeDetails = (Map)qmeetAttendeeList.get(i);
                
                if(attendeeDetails.get("Id").toString().equals(infoId)){
                    Iterator entries = attendeeDetails.entrySet().iterator();
                        while (entries.hasNext()) {
                          Entry thisEntry = (Entry) entries.next();
                          jb.put((String)thisEntry.getKey(),(String)thisEntry.getValue());

                        }
                }
            }
              
                
             
           
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return jb.toString();   
}
public JSONObject getUpcomingExecutivemeets(String emeetType) throws ServiceLocatorException {
        List jobsList = null;
        List mainList = null;
        String createdDateFrom;
        String createdDateTo;
        String jobId;
        String title;
        String status;

        JSONObject jObject = null;
        try {

            JSONObject jb = new JSONObject();


            jb.put("EMeetCreatedDate", "none");
            jb.put("EmeetFromDate", "none");
            jb.put("EmeetToDate", "none");
            jb.put("EmeetStatusByDate", "U");
            jb.put("EmeetStatusForMails", "Published");
            jb.put("ExecutiveMeetMonthSearch", "none");
            if (!"Both".equals(emeetType)) {
                jb.put("EMeetType", emeetType);
            } else {
                jb.put("EMeetType", "none");
            }
            String serviceUrl = RestRepository.getInstance().getSrviceUrl("executiveMeetSearch");
            URL url = new URL(serviceUrl);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(360 * 5000);
            connection.setReadTimeout(360 * 5000);
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
            jObject = new JSONObject(data);


            in.close();
        } catch (Exception e) {
            System.out.println("\nError while calling REST Service");
            //System.out.println(e);
        }
        return jObject;
    }




     public String getWebinarsListBySeriesId(String seriesId) throws Exception {
        String message = "";
        boolean isUpdated = false;
        String data = "";
        try {
            JSONObject jb = new JSONObject();
          //  System.out.println("seriesId-->"+seriesId);
            jb.put("SeriesId", seriesId);
            String serviceUrl = RestRepository.getInstance().getSrviceUrl("webinarsListBySeriesId");
            URL url = new URL(serviceUrl);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(360 * 5000);
            connection.setReadTimeout(360 * 5000);
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write(jb.toString());
            out.close();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String s = null;

            while ((s = in.readLine()) != null) {

                data = data + s;
            }
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        // System.out.println("web data-->"+data);
        return data;
    }
     
public String addOrRemoveWebinarToSeries(String seriesId,String eventId) throws Exception{
    String message ="";
        boolean isUpdated = false;
      
        try {
           
              
                JSONObject jb = new JSONObject();
              jb.put("SeriesId",seriesId);
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


    /*Events functionality new enhancements end
     * Date : 01/23/2017
     * Author : Santosh Kola/Phani Kanuri
     */
}

