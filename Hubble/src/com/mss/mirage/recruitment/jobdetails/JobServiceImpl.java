/*
 * JobServiceImpl.java
 *
 * Created on January 3, 2010, 6:43 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.recruitment.jobdetails;

import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.ServiceLocatorException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


/**
 *
 * @author miracle
 */
public class JobServiceImpl implements JobService{
    
    
    private String reportResult;
    
    JasperPrint jasperPrint = null;
    JasperReport jasperReport = null;
    ServletOutputStream outStream =null;
    List finalList = null;
    String query =  null;
    Connection connection=null;
    Map RBMap = null;
    
    /** Creates a new instance of PhoneLogServiceImpl */
    public JobServiceImpl() {
    }
    
    /**
     *
     * @param action
     * @throws com.mss.mirage.util.ServiceLocatorException
     * @return
     */
    public boolean addJob(JobAction action) throws ServiceLocatorException{
        
        
        
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        boolean isInserted=false;
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            
            
            preparedStatement=connection.prepareStatement("insert into tblJobDetails (JobTitle,JobNumber ,PositionType, Payrate," +
                    "RequiredTravels,JobLength,Telecommunication,Email,JobCity,JobState,JobCountry,JobAreaCode,JobPostalCode,RequiredSkills," +
                    "JobDescription,JobNotes,CreatedBy,CreatedDate) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            
            preparedStatement.setString(1, action.getTitle());
            preparedStatement.setInt(2, action.getNumber());
            preparedStatement.setString(3, action.getType());
            preparedStatement.setFloat(4, action.getRate());
            preparedStatement.setString(5, action.getReqtravels());
            preparedStatement.setString(6, action.getLength());
            preparedStatement.setString(7, action.getTele());
            preparedStatement.setString(8, action.getEmail());
            preparedStatement.setString(9, action.getCity());
            preparedStatement.setString(10,action.getProvince());
            preparedStatement.setString(11,action.getCountry());
            preparedStatement.setString(12,action.getAreacode());
            preparedStatement.setString(13,action.getPostalcode());
            preparedStatement.setString(14,action.getSkills());
            preparedStatement.setString(15,action.getDesc());
            preparedStatement.setString(16,action.getNotes());
            preparedStatement.setString(17, action.getUserid());
            preparedStatement.setTimestamp(18,action.getCreatedtime());
            
            
            
            int x = preparedStatement.executeUpdate();
            preparedStatement.close();
            if(x>0){
                isInserted=true;
            }
            
        }catch(SQLException sql){
            throw new ServiceLocatorException(sql);
        }finally{
            try{
                if(preparedStatement!=null){
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(connection!=null){
                    connection.close();
                    connection = null;
                    
                }
                
            }catch(SQLException sqle){
                throw new ServiceLocatorException(sqle);
            }
        }
        
        return isInserted;
    }
    
    /**
     * this is for editing employee phonelog
     * @param action
     * @param PhoneLogId
     * @throws com.mss.mirage.util.ServiceLocatorException
     * @return
     */
    public boolean editJob(JobAction action,int JobId) throws ServiceLocatorException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        boolean isUpdated=false;
        
        
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement=connection.prepareStatement("update tblJobDetails set JobTitle=?,JobNumber=?,PositionType=? ,Payrate=? ," +
                    "RequiredTravels=? ,JobLength=?  ,Telecommunication=? ,Email=?,JobCity =?,JobState=?,JobCountry=?,JobAreaCode=?," +
                    "JobPostalCode=?,RequiredSkills=?,JobDescription=?,JobNotes=?,ModifiedBy=? where Id='"+JobId+"'" );
            
            preparedStatement.setString(1, action.getTitle());
            preparedStatement.setInt(2, action.getNumber());
            preparedStatement.setString(3, action.getType());
            preparedStatement.setFloat(4, action.getRate());
            preparedStatement.setString(5, action.getReqtravels());
            preparedStatement.setString(6, action.getLength());
            preparedStatement.setString(7, action.getTele());
            preparedStatement.setString(8, action.getEmail());
            preparedStatement.setString(9, action.getCity());
            preparedStatement.setString(10,action.getProvince());
            preparedStatement.setString(11,action.getCountry());
            preparedStatement.setString(12,action.getAreacode());
            preparedStatement.setString(13,action.getPostalcode());
            preparedStatement.setString(14,action.getSkills());
            preparedStatement.setString(15,action.getDesc());
            preparedStatement.setString(16,action.getNotes());
            preparedStatement.setString(17, action.getUserid());
            
            
            int x = preparedStatement.executeUpdate();
            preparedStatement.close();
            
            
            if(x>0){
                isUpdated=true;
            }
            
        }catch(SQLException sql){
            throw new ServiceLocatorException(sql);
        }finally{
            try{
                if(preparedStatement!=null){
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(connection!=null){
                    connection.close();
                    connection = null;
                }
                
            }catch(SQLException sqle){
                throw new ServiceLocatorException(sqle);
            }
        }
        
        return isUpdated;
    }
    
    /**
     * this is for deleting the employee phonelog
     * @param action
     * @param PhoneLogId
     * @throws com.mss.mirage.util.ServiceLocatorException
     * @return
     */
    public boolean deleteJob(JobAction action,int JobId)  throws ServiceLocatorException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        boolean isDeleted=false;
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement=connection.prepareStatement("delete from tblJobDetails where Id='"+JobId+"' ");
            int x = preparedStatement.executeUpdate();
            preparedStatement.close();
            if(x>0){
                isDeleted=true;
            }
        } catch(SQLException sql){
            throw new ServiceLocatorException(sql);
        }finally{
            try{
                if(preparedStatement!=null){
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(connection!=null){
                    connection.close();
                    connection = null;
                }
                
            }catch(SQLException sqle){
                throw new ServiceLocatorException(sqle);
            }
            
        }
        return isDeleted;
    }
    
    /**
     * this is for editing the particular record of phonelog
     * @param PhoneLogId
     * @throws com.mss.mirage.util.ServiceLocatorException
     * @return
     */
    public JobVTO getJob(int JobId) throws ServiceLocatorException{
        
        JobVTO jobVTO=new JobVTO();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Statement statement=null;
        ResultSet resultSet= null;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement=connection.createStatement();
            resultSet=statement.executeQuery("select JobTitle,JobNumber ,PositionType, Payrate," +
                    "RequiredTravels,JobLength,Telecommunication,Email,JobCity,JobState,JobCountry,JobAreaCode,JobPostalCode,RequiredSkills," +
                    "JobDescription,JobNotes,CreatedBy,CreatedDate from tblJobDetails where Id='"+JobId+"'");
            while(resultSet.next()){
                jobVTO.setTitle(resultSet.getString(1));
                jobVTO.setNumber(resultSet.getInt(2));
                jobVTO.setType(resultSet.getString(3));
                jobVTO.setRate(resultSet.getFloat(4));
                jobVTO.setReqtravels(resultSet.getString(5));
                jobVTO.setLength(resultSet.getString(6));
                jobVTO.setTele(resultSet.getString(7));
                jobVTO.setEmail(resultSet.getString(8));
                jobVTO.setCity(resultSet.getString(9));
                jobVTO.setProvince(resultSet.getString(10));
                jobVTO.setCountry(resultSet.getString(11));
                jobVTO.setAreacode(resultSet.getString(12));
                jobVTO.setPostalcode(resultSet.getString(13));
                jobVTO.setSkills(resultSet.getString(14));
                jobVTO.setDesc(resultSet.getString(15));
                jobVTO.setNotes(resultSet.getString(16));
                jobVTO.setTime(resultSet.getString(17));
                
            }
        } catch(SQLException sqle){
            throw new ServiceLocatorException(sqle);
        } finally{
            try{
                if(preparedStatement!=null){
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(connection!=null){
                    connection.close();
                    connection = null;
                }
                
            }catch(SQLException sqle){
                throw new ServiceLocatorException(sqle);
            }
        }
        return jobVTO;
    }
    
    
    public String generateTitleReport(String query,JobAction jobaction,HttpServletResponse httpServletResponse) throws ServiceLocatorException {
             
       String jrxmlpath="/home/miracle/Desktop/Reports_jrxml/Job.jrxml";
        
        try {
            
            jasperReport = JasperCompileManager.compileReport(jrxmlpath);
            
            List dataList = getTitleReport(query);
            if(dataList.size()==0){
                //System.out.println("FutureLeaves ::"+dataList.size());
                reportResult = "No records are available between selected dates";
            }else {
                //System.out.println(usedLeaves+"%%%%%%%%"+appliedLeaves+"%%%%%%%%%%"+(avalLeaves-appliedLeaves));
                JRBeanCollectionDataSource jrxmlds = new JRBeanCollectionDataSource(getCollection(dataList));
                jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap(), jrxmlds);
                //JasperViewer.viewReport(jasperPrint);
                byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);
                
              //  System.out.println("Data is written to output");
                outStream = httpServletResponse.getOutputStream();
                httpServletResponse.setContentType("application/pdf");
                httpServletResponse.setContentLength(pdf.length);
                httpServletResponse.setHeader("Content-disposition","inline; filename=\"Report.pdf\"");
                outStream.write(pdf);
                outStream.flush();
                outStream.close();
            }
        } catch (Exception ex) {    
            throw new ServiceLocatorException(ex.getMessage());
        }
        return reportResult;        
    }
    
    public Collection getCollection(Object obj){
        List list=(ArrayList)obj;
        return list;
    }
    
    
    public List getTitleReport(String query) throws ServiceLocatorException{
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List lt=null;
        HashMap map = null;
        finalList=new ArrayList();
        
        try {
            /* getting the connection from the ConnectionProvider */
            connection = ConnectionProvider.getInstance().getConnection();
            if(connection!=null) {
                preparedStatement = connection.prepareStatement(query);
                //System.err.println("Query---"+query);
                /* taking the info from the tblTimeSheets based on the empId and timeSheet Id */
                resultSet = preparedStatement.executeQuery();
                while(resultSet.next()) {
                    map = new HashMap();
                    
                    map.put("Title",resultSet.getString("JobTitle"));
                    
                    finalList.add(map);
                }
                
            }
        } catch(Exception ex) {
            throw new ServiceLocatorException(ex.getMessage());
        }finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                }
                if(preparedStatement != null){
                    preparedStatement.close();
                }
                if(connection!=null){
                    connection.close();
                    connection = null;
                }
            }catch(SQLException sqle){
                throw new ServiceLocatorException(sqle.getMessage());
            }
        }
        return finalList;
    }
    
    
    
}


