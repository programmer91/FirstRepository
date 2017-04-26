/*
 * LeaveServiceImpl.java
 *
 * Created on January 22, 2008, 4:11 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.employee.leaverequest;
import com.mss.mirage.employee.leaverequest.LeaveService;
import com.mss.mirage.services.mail.MailAttachment;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.DateUtility;
import java.sql.CallableStatement;
import java.sql.SQLException;
import com.mss.mirage.util.ServiceLocatorException;
//import java.util.Date;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Iterator;
import com.mss.mirage.util.ConnectionProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.mss.mirage.services.mail.MailAction;
import java.sql.Statement;
import java.io.File;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Properties;

/**
 *  The common base class for all contact classes; provides default implementations
 *  of the <code>com.mss.mirage.employee.leaverequest</code> methods. All method implementations are
 *  forwarded to a <code>addOrUpdateContact</code> object stored in the <code>LeaveServiceImpl</code>
 *  instance.
 *
 *  @author Arjun Sanapathi<asanapathi@miraclesoft.com>
 */
public class LeaveServiceImpl implements LeaveService  {
    
    /** Creates a new instance of LeaveServiceImpl */
    private LeaveVTO leaveVTO;
    private String queryString;
    private Connection connection = null;
    private CallableStatement callableStatement= null;
    private ResultSet resultSet = null;
    private Statement statement;
    
    
    
    
    /**
     * The <code>addorUpdateContact</code>  class is used for getting new Leave details from
     * <i>LeaveRequest.jsp</i> page.
     * <p>
     * Then it invokes setter methods in <code>doAdd,doEdit</code> class and invokes update() method
     * in <code>doAdd,doEdit</code> for inserting leave details in
     *
     * @author Arjun Sanapthi<a href="mailto:asanapathi@miraclesoft.com">Arjun</a>
     *
     * @version 1.0 Febravary jan-28-2008
     *
     * @see com.mss.mirage.employee.leaverequest.LeaveAction
     * @see com.mss.mirage.employee.leaverequest.LeaveService
     * @see com.mss.mirage.employee.leaverequest.LeaveServiceImpl
     * @see com.mss.mirage.employee.leaverequest.LeaveVTO */
    
    
    
    public  int addOrUpdateLeave(LeaveAction leaveaction,String userId,String userName,int empId,String mode)  {
        int updatedRows=0;
        int insertedRows=0;
        int result =0;
        int leaveId = 0;
        String mailval ="";
        String[] mailvals=null;
        Connection connection = null;
        CallableStatement callableStatement= null;
        ResultSet resultSet = null;
        String sqlQuery=null;
        MailAttachment  mailAttachment   = new  MailAttachment();
        StringBuilder htmlText = new StringBuilder();
        if(mode.equalsIgnoreCase("add")) {
            try{
                connection = ConnectionProvider.getInstance().getConnection();
                DateUtility dateUtil = DateUtility.getInstance();
                callableStatement = connection.prepareCall("{call spLeaveRequest(?,?,?,?,?,?,?,?,?,?,?)}");
                callableStatement.setInt(1,leaveaction.getEmpId());
                callableStatement.setTimestamp(2, dateUtil.strToTimeStampObj(leaveaction.getLeaveRequiredFrom()));
                callableStatement.setTimestamp(3, dateUtil.strToTimeStampObj(leaveaction.getLeaveRequiredTo()));
                callableStatement.setString(4,leaveaction.getReason());
                callableStatement.setString(5,leaveaction.getStatus());
                callableStatement.setInt(6,0);
                callableStatement.setString(7,leaveaction.getLeaveType());
                callableStatement.setString(8,leaveaction.getOneLevelreportsToId());
                callableStatement.setString(9,leaveaction.getDepartmentId());
                callableStatement.setString(10,leaveaction.getOrgId());
                callableStatement.registerOutParameter(11,Types.INTEGER);
                insertedRows = callableStatement.executeUpdate();
                leaveId = callableStatement.getInt(11);
                String priroty="High";
                if(priroty.equalsIgnoreCase("High")) {
                    priroty="1";
                }
                DateUtility date = null;
                date = DateUtility.getInstance();   
                String dtEnds = leaveaction.getLeaveRequiredFrom();
                String dtStarts = leaveaction.getLeaveRequiredTo();
                
                String dtEnd =  date.usTimeStampToTimeStampHyphen(leaveaction.getLeaveRequiredFrom());
                String dtStart = date.usTimeStampToTimeStampHyphen(leaveaction.getLeaveRequiredTo());
                String dtStartOnlyDate = dtStart.substring(0,10);
                String dtStartOnlyTime = dtStart.substring(11,dtStart.length()).trim();
                dtStart = dtStartOnlyDate+dtStartOnlyTime;
                
                String dtEndDate = dtEnd.substring(0,10);
                String dtEndTime = dtEnd.substring(11,dtEnd.length()).trim();
                dtEnd = dtEndDate+dtEndTime;
                
                String calDtEnd = dtEnd;
                calDtEnd = calDtEnd.substring(0,16);
                dtStart = setdtEndChars(dtStartOnlyDate,dtStartOnlyTime);
                dtEnd = setdtEndChars(dtEndDate,dtEndTime);
                
                String  name  = userName;
                String description = leaveaction.getLeaveType();
                String message =  leaveaction.getReason();
                String summery = "Mirage Leave Update for Employee";
                //makeICSFile(priroty,dtStart,dtEnd,summery,description,message,name,userId,calDtEnd);
                DataSourceDataProvider dateSourceProvider  = DataSourceDataProvider.getInstance();
               //System.out.println("insertedRows---->"+insertedRows);
               /* if((insertedRows >= 0) && (com.mss.mirage.util.Properties.getProperty("Mail.Flag").equals("1"))) {
                    mailvals = splitValues(leaveaction.getReportsTo());
                 //System.out.println("Mail values -->"+mailvals);
                    String directReportsTo = mailvals[0];
                  //System.out.println("Direct Report -->"+directReportsTo);
                    htmlText.append("Leave Requested By:"+userName+"\n");
                    htmlText.append(" Leave From:"+leaveaction.getLeaveRequiredFrom());
                    htmlText.append("\n Leave To:"+leaveaction.getLeaveRequiredTo()+
                            "\n\n\n Reason:-" + "\n "+leaveaction.getReason()+
                            "\n\n\n This Leave approval to be done by : "+dateSourceProvider.getFname_Lname(directReportsTo));
                    String empOrg = dateSourceProvider.getEmployeeOrganisation(empId);
                    
                    if(empOrg.equalsIgnoreCase("Miracle Software Systems(USA).Inc")) {
                       htmlText.append("\n\n\n Please click here http://w3.miraclesoft.com/Hubble/employee/getleaveApprovalList.action?leaveId="+leaveId+ " to Approve this Leave.");
                    }else {
                        htmlText.append("\n\n\n Please click here http://w3.miraclesoft.com/Hubble/employee/getleaveApprovalList.action?leaveId="+leaveId+ " to Approve this Leave.");
                    }
                    
                   // System.out.println(mailval+"  Main Value is....");
                    
                    for(int i=0;i<mailvals.length;i++) {
                        mailval = mailvals[i];
                        if(mailval != null && !mailval.equals("") && !mailval.equals(" ")) {
                    //        System.out.println(mailval+"  Main Value is....");
                            mailAttachment.setTo(mailval+"@miraclesoft.com");
                            mailAttachment.setSubject("Mirage Leave Request");
                            mailAttachment.setMessage(htmlText.toString());
                            mailAttachment.setCc("");
                            mailAttachment.setBcc("");
                            //String filePath = com.mss.mirage.util.Properties.getProperty("Leave.VCS.Path");
                            //filePath = filePath + com.mss.mirage.util.Properties.getProperty("OS.Compatabliliy.Download")+ userId+".vcs";
                            
                            //mailAttachment.setUpload(filePath);
                            
                            
                                mailAttachment.sendMail(userId,userName);
                            
                        }
                    }
                }*/
                
            }catch(SQLException se) {
                //throw new ServiceLocatorException(se);
                se.printStackTrace();
            }catch (ServiceLocatorException ex) {
                    ex.printStackTrace();
                }catch (Exception exception) {
                    exception.printStackTrace();
                }finally{
                try{
                    if(callableStatement!=null){
                        callableStatement.close();
                        callableStatement = null;
                    }
                    if(connection != null){
                        connection.close();
                        connection = null;
                    }
                }catch(SQLException sqle){
                   // throw new ServiceLocatorException(sqle);
                    sqle.printStackTrace();
                }
            }
            
            result =  insertedRows;
            
        }
        
        
        if(mode.equalsIgnoreCase("edit")) {
            
            try{
                
                connection = ConnectionProvider.getInstance().getConnection();
                DateUtility dateUtil = DateUtility.getInstance();
                callableStatement = connection.prepareCall("{call spLeaveRequest(?,?,?,?,?,?,?,?,?,?,?)}");
                callableStatement.setInt(1,leaveaction.getEmpId());
                callableStatement.setTimestamp(2, dateUtil.strToTimeStampObj(leaveaction.getLeaveRequiredFrom()));
                callableStatement.setTimestamp(3, dateUtil.strToTimeStampObj(leaveaction.getLeaveRequiredTo()));
                callableStatement.setString(4,leaveaction.getReason());
                callableStatement.setString(5,leaveaction.getStatus());
                callableStatement.setInt(6,leaveaction.getId());
                callableStatement.setString(7,leaveaction.getLeaveType());
                callableStatement.setString(8,leaveaction.getOneLevelreportsToId());
                callableStatement.setString(9,leaveaction.getDepartmentId());
                callableStatement.setString(10,leaveaction.getOrgId());
                callableStatement.registerOutParameter(11,Types.INTEGER);
                updatedRows = callableStatement.executeUpdate();
                
                
            }catch(SQLException se) {
                //throw new ServiceLocatorException(se);
                se.printStackTrace();
            }catch (ServiceLocatorException ex) {
                    ex.printStackTrace();
                }catch (Exception exception) {
                    exception.printStackTrace();
                }finally{
                try{
                    if(callableStatement!=null){
                        callableStatement.close();
                        callableStatement = null;
                    }
                    if(connection != null){
                        connection.close();
                        connection = null;
                    }
                }catch(SQLException sqle){
                    //throw new ServiceLocatorException(sqle);
                    sqle.printStackTrace();
                }
            }
            
            result =  updatedRows;
        }
        
        
        
        return result;
    }
    
    public String setdtEndChars(String str,String str1) {
        StringBuilder sb = new StringBuilder(str);
        int length = sb.length();
        
        for(int i=0;i<sb.length();i++) {
            if(sb.charAt(i)== '-') {
                sb.deleteCharAt(i);
            }
            
        }
        
        String strDate = sb.toString();
        
        
        
        StringBuilder sb1 = new StringBuilder(str1);
        int length1 = sb1.length();
        
        for(int i=0;i<sb1.length();i++) {
            if(sb1.charAt(i)== ':') {
                sb1.deleteCharAt(i);
            }
            
        }
        String strTime = sb1.toString();
        String strDateTime = strDate+"T"+strTime+"Z";
        return strDateTime;
    }
    
    
    
    
    
    public String makeICSFile(String priroty, String dtStart,String dtEnd,String summery,String description,String desMessage,String name,String userId,String dueTime) {
        
        
        
        StringBuffer buf = new StringBuffer("BEGIN:VCALENDAR");
        
        buf.append("\n");
        buf.append("VERSION:5.0");
        buf.append("\n");
        
        String prodId="//Microsoft Corporation//Works 2000//EN";
        buf.append("PRODID:-"+prodId);
        buf.append("\n");
        buf.append("BEGIN:VEVENT");
        buf.append("\n");
        buf.append("SUMMARY;ENCODING=QUOTED-PRINTABLE:"+"userName:-"+name + ":" +"EndDate"+dueTime);
        buf.append("\n");
        buf.append("ORGANIZER:MAILTO:"+userId+"@miraclesoft.com");
        buf.append("\n");
        buf.append("SUBJECT; ENCODING=QUOTED-PRINTABLE:files");
        buf.append("\n");
        buf.append("DESCRIPTION; ENCODING=QUOTED-PRINTABLE:"+desMessage);
        buf.append("\n");
        buf.append("LOCATION; ENCODING=QUOTED-PRINTABLE:"+summery);
        buf.append("\n");
        
        
        
//        java.sql.Date   dateStart =  null;
//        String currDate ="";
//                            DateUtility date = null;
//
//                            try {
//                                date = DateUtility.getInstance();
//                                currDate = date.getCurrentDate();
//                                dateStart = date.getMysqlDate(currDate);
//
//                            } catch (ServiceLocatorException ex) {
//                                ex.printStackTrace();
//                            }
//
//        buf.append("CURRENT DATE:"+dateStart);
        
        
        buf.append("CATEGORIES;ENCODING=QUOTED-PRINTABLE:Gifts, Holiday");
        buf.append("\n");
        buf.append("DTSTART:"+dtStart);
        buf.append("\n");
        buf.append("DTEND:"+dtEnd);
        buf.append("\n");
        buf.append("PRIORITY:"+priroty);
        buf.append("\n");
        buf.append("END:VEVENT");
        buf.append("\n");
        buf.append("END:VCALENDAR");
        
        
        
        //String message = makeFile(buf.toString());
        String message = makeFile(buf.toString(),userId);
        
        
        return buf.toString();
    }
    
    
    
    
    
    public String makeFile(String output,String userId) {
        String message ="fileCreated";
        String filePath = com.mss.mirage.util.Properties.getProperty("Leave.VCS.Path");
        filePath = filePath + com.mss.mirage.util.Properties.getProperty("OS.Compatabliliy.Download")+ userId+".vcs";
        File aFile = new File(filePath);
        FileOutputStream file = null;
        try {
            file = new FileOutputStream(aFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.err);
        }
        FileChannel outChannel = file.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(output.length());
        byte[] bytes = output.getBytes();
        buf.put(bytes);
        buf.flip();
        
        try {
            outChannel.write(buf);
            file.close();
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
        
        return  filePath;
    }
    
    
    
    
    
    /**
     * The <code>addorUpdateContact</code>  class is used for getting Details of particular Id
     * <i>LeaveList.jsp  and LeaveApprovals.jsp</i> page.
     * <p>
     * Then it invokes setter methods in <code>doAdd,doEdit</code> class and invokes update() method
     * in <code>doAdd,doEdit</code> for inserting leave details in
     *
     * @author Arjun Sanapthi<a href="mailto:asanapathi@miraclesoft.com">Arjun</a>
     *
     * @version 1.0 Febravary jan-28-2008
     *
     * @see com.mss.mirage.employee.leaverequest.LeaveAction
     * @see com.mss.mirage.employee.leaverequest.LeaveService
     * @see com.mss.mirage.employee.leaverequest.LeaveServiceImpl
     * @see com.mss.mirage.employee.leaverequest.LeaveVTO */
    
    
    
    
    
    public LeaveVTO getLeave(int leaveId) throws ServiceLocatorException {
        queryString = "SELECT * FROM tblEmpLeaves WHERE Id="+leaveId;
        leaveVTO = new LeaveVTO();
        DateUtility dateUtil;
        dateUtil = DateUtility.getInstance();
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            leaveVTO.setId(leaveId);
            while(resultSet.next()){
                //leaveVTO.setStartDate(resultSet.getString("StartDate"));
                //leaveVTO.setEndDate(resultSet.getString("EndDate"));
                leaveVTO.setLeaveStartDate(dateUtil.sqlTimeStampTousTimeStamp(resultSet.getString("StartDate")));
                leaveVTO.setLeaveEndDate(dateUtil.sqlTimeStampTousTimeStamp(resultSet.getString("EndDate")));
                leaveVTO.setEmpId(resultSet.getInt("EmpId"));
                leaveVTO.setReason(resultSet.getString("Reason"));
                leaveVTO.setStatus(resultSet.getString("Status"));
                leaveVTO.setLeaveType(resultSet.getString("leaveType"));
                leaveVTO.setReportsTo(resultSet.getString("reportsTo"));
                leaveVTO.setDepartmentId(resultSet.getString("DepartmentId"));
                leaveVTO.setOrgId(resultSet.getString("OrgId"));
            }
            
            
        }catch (SQLException sqle){
            throw new ServiceLocatorException(sqle);
        }finally{
            try{
                if(resultSet!= null) {
                    resultSet.close();
                    resultSet = null;
                }
                
                if(statement!= null) {
                    statement.close();
                    statement = null;
                }
                
                if(connection != null){
                    
                    connection.close();
                    connection = null;
                }
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        return leaveVTO;
        
    }
    
    
    
    /**
     *  This method takes one parameter from the user and split values in array and send it to back
     * Then it invokes setter methods in <code>doAdd,doEdit</code> class and invokes update() method
     * in <code>doAdd,doEdit</code> for inserting leave details in
     *
     * @author Arjun Sanapthi<a href="mailto:asanapathi@miraclesoft.com">Arjun</a>
     *
     * @version 1.0 Febravary jan-28-2008
     *
     * @see com.mss.mirage.employee.leaverequest.LeaveAction
     * @see com.mss.mirage.employee.leaverequest.LeaveService
     * @see com.mss.mirage.employee.leaverequest.LeaveServiceImpl
     * @see com.mss.mirage.employee.leaverequest.LeaveVTO */
    
    
    
    
    
    public  String[] splitValues(String reportsTo) throws ServiceLocatorException, SQLException {
        String[] reportsToAll = null;
        
        try {
            reportsToAll = reportsTo.split(";");
            
        }catch(Exception sqle){
            throw new ServiceLocatorException(sqle);
        }
        
        return reportsToAll;
    }
    
    
    
}











