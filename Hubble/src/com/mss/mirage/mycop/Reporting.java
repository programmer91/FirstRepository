
package com.mss.mirage.mycop;
import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.ReportProperties;
import com.mss.mirage.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
/**
 * Reporting.java
 * @author Venumadhav Yelamanchili<vyelamanchili@miraclesoft.com>
 * Created on March 10, 2008, 4:55 PM
 *
 * This class will get username ,startdate and enddate as user input. It then generates mycop report for
 *that particular user by using mycop database. The report is in pdf format.
 *
 */

public class Reporting extends ActionSupport implements ServletRequestAware{
    
    
    /**
     *  username is the name of the user for which report is to be generated
     */
    private String username;
    /**
     *  startDate is the date from which report will be generated
     */
    private String startDate;
    /**
     *  endDate is the date upto which report will be generated starting from startDate.
     */
    private String endDate;
    /**
     *  dbLoginId contains name of the team manager who is going to generate report.
     */
    private String dbLoginId;
    /**
     *This variable contains a names of all the team members as a single String seperated by ','.
     */
    private String keysOfTeamMembers;
    /**
     *  mycopList contains report data in the form of List object.
     */    
    private List mycopList;
    
    private String departmentId;
    
    private List abcentiesList;
    
    private String startDate1;
    
    private String endDate1;
    
    /**
     *  Variable all will be true if user wants to generate report for all team memebers in his team.
     *  It will be true if the user picks the check box on the MyCopReport.jsp
     */
    private boolean all;
    /**
     *  Variable all_emp will be true if user is HR and the checkbox  to generate report for all employees is selected.
     *  It will be true if the user picks the check box with label All Users Report on the MyCopReport.jsp which will
     *  be displayed for HR role only.
     */
    private boolean all_emp;
    /**
     *  parsedStartDate and parsedEndDate are the dates got after parsing the date entered on MyCopReport.jsp to
     *requiredformat.
     */
    String parsedStartDate;
    String parsedEndDate;
    String resultString=null;
    private HttpServletRequest request;
    /**
     *  MyTeamMembers contains Map object of all team members . This will taken from session .
     */
    private Map MyTeamMembers = new TreeMap();
    
    public String execute()throws Exception{
        
        MyTeamMembers=(Map)getServletRequest().getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP);
        setMyTeamMembers(MyTeamMembers);
        setDbLoginId((String) getServletRequest().getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID));
        keysOfTeamMembers=getKeys(MyTeamMembers,",");
        
        parsedStartDate=DateUtility.getInstance().convertStringToMySQLDate(startDate);
        parsedEndDate=DateUtility.getInstance().convertStringToMySQLDate(endDate);
        MycopService mycopService = ServiceLocator.getMyCopService();
        if(isAll()==true && username.length()!=0){
            mycopList=mycopService.getUserData(username,parsedStartDate,parsedEndDate,keysOfTeamMembers);
        } else if(isAll()==true && username.length()==0){
            mycopList=mycopService.getUserData("all",parsedStartDate,parsedEndDate,keysOfTeamMembers);
        }else if(isAll()==false && username.length()!=0){
            mycopList=mycopService.getUserData(username,parsedStartDate,parsedEndDate,keysOfTeamMembers);
        }else if(isAll_emp()==true){
            mycopList=mycopService.getUserData("all_emp",parsedStartDate,parsedEndDate,keysOfTeamMembers);
        }
        else{
            mycopList=mycopService.getUserData(username,parsedStartDate,parsedEndDate,keysOfTeamMembers);
        }
        
        
        setMycopList(mycopList);
        
        if(getMycopList().size()>0){
            try {
                //ReportProperties.getProperty("JsperTempleteFile")
                JasperCompileManager.compileReportToFile(ReportProperties.getProperty("MyCopReportsJRXML"),"./template.jasper");
                resultString=SUCCESS;
                
            } catch (JRException ex) {
                ex.printStackTrace();
                request.setAttribute(ApplicationConstants.RESULT_MSG,"Sorry! Server is down. Please try later.");
                return "empty";
            }
        } else{
            request.setAttribute(ApplicationConstants.RESULT_MSG,"No report is available between selected dates.");
            resultString="empty";
        }
        return resultString;
    }
    
    public String prepare()throws Exception {
        
        MycopService mycopService = ServiceLocator.getMyCopService();
        //setAbcentiesList(mycopService.getAbcentiesData(startDate1,endDate1, departmentId));
        
        if(getAbcentiesList().size()>0){
            try {
                //ReportProperties.getProperty("JsperTempleteFile")
                JasperCompileManager.compileReportToFile(ReportProperties.getProperty("MyCopReportsJRXML"),"./template.jasper");
                resultString=SUCCESS;
                
            } catch (JRException ex) {
                ex.printStackTrace();
                request.setAttribute(ApplicationConstants.RESULT_MSG,"Sorry! Server is down. Please try later.");
                return "empty";
            }
        } else{
            request.setAttribute(ApplicationConstants.RESULT_MSG,"No report is available between selected dates.");
            resultString="empty";
        }
        return resultString;
    }
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public List getMycopList() {
        return mycopList;
    }
    
    public void setMycopList(List mycopList) {
        this.mycopList = mycopList;
    }
    
    
    
   
    
    public String getStartDate() {
        return startDate;
    }
    
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    
    public String getEndDate() {
        return endDate;
    }
    
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
    public String getDbLoginId() {
        return dbLoginId;
    }
    
    public void setDbLoginId(String dbLoginId) {
        this.dbLoginId = dbLoginId;
    }
    
    public HttpServletRequest getServletRequest() {
        return request;
    }
    
    public void setServletRequest(HttpServletRequest servletRequest) {
        this.request = servletRequest;
    }
    
    public Map getMyTeamMembers() {
        return MyTeamMembers;
    }
    
    public void setMyTeamMembers(Map MyTeamMembers) {
        this.MyTeamMembers = MyTeamMembers;
    }
    
//=====================================================================================
//  This method is used to loop through all the keys present in a Map Data Structure
//  and formulate them into a String of Keys seperated by a delimiter and return that
//  string of delimited values back to the caller
//=====================================================================================
    private String getKeys(Map map, String delimiter) {
        Iterator tempIterator = map.entrySet().iterator();
        String keys = "";
        String key="";
        int cnt = 0;
        
        while(tempIterator.hasNext()) {
            Map.Entry entry = (Map.Entry) tempIterator.next();
            key = entry.getKey().toString();
            entry = null;
            
            //  Add the Delimiter to the Keys Field for the Second Key onwards
            if (cnt > 0) keys = keys + delimiter;
            
            keys = keys + "'" + key +"'";
            cnt++;
        }
        tempIterator = null;
        return(keys);
    }

    public boolean isAll() {
        return all;
    }

    public void setAll(boolean all) {
        this.all = all;
    }

    public boolean isAll_emp() {
        return all_emp;
    }

    public void setAll_emp(boolean all_emp) {
        this.all_emp = all_emp;
    }

    
    public String getStartDate1() {
        return startDate1;
    }

    public void setStartDate1(String startDate1) {
        this.startDate1 = startDate1;
    }

    public String getEndDate1() {
        return endDate1;
    }

    public void setEndDate1(String endDate1) {
        this.endDate1 = endDate1;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public List getAbcentiesList() {
        return abcentiesList;
    }

    public void setAbcentiesList(List abcentiesList) {
        this.abcentiesList = abcentiesList;
    }
}
