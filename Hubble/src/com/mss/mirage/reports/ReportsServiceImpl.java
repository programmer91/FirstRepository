 /*
  * ReportsServiceImpl.java
  *
  * Created on November 22, 2007, 8:36 PM
  *
  * To change this template, choose Tools | Template Manager
  * and open the template in the editor.
  */

package com.mss.mirage.reports;

import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.HibernateServiceLocator;
import com.mss.mirage.util.Properties;
import com.mss.mirage.util.ServiceLocatorException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.sql.Timestamp;
import java.util.ArrayList;
import org.hibernate.Query;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
/**
 *
 * @author miracle
 */
public class ReportsServiceImpl implements ReportsService{
    private String reportName;
    
    /** Creates a new instance of ReportsServiceImpl */
    public ReportsServiceImpl() {
    }
    
    public boolean generateReportToPdfFile(String sourceJrxml, String destDirectiory) throws ServiceLocatorException {
        boolean isReportGenerated = false;
        Connection connection = null;
        Map params = new HashMap();
        try{
            JasperReport jasperReport = JasperCompileManager.compileReport(sourceJrxml);
            connection = ConnectionProvider.getInstance().getConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,params,connection);
            
            /* The Export manager will export the source file in to our required format*/
            JasperExportManager.exportReportToPdfFile(jasperPrint,destDirectiory);
            
            /* Jasper viewer for To view the result in viewer*/
            //  JasperViewer.viewReport(jasperPrint);
            isReportGenerated = true;
            
            
        }catch ( JRException jrex){
            throw new ServiceLocatorException(jrex);
        }finally {
            if(connection!=null){
                try {
                    connection.close();
                    connection = null;
                } catch (SQLException ex) {
                    throw new ServiceLocatorException(ex);
                }
                
            }
        }
        return isReportGenerated;
    }
    
    public boolean generateReportToHtmlFile(String sourceJrxml, String destDirectiory) throws ServiceLocatorException {
        Map params = new HashMap();
        boolean isReportGenerated = false;
        Connection connection = null;
        try{
            JasperReport jasperReport = JasperCompileManager.compileReport(sourceJrxml);
            connection = ConnectionProvider.getInstance().getConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,params,connection);
            
            /* The Export manager will export the source file in to our required format*/
            JasperExportManager.exportReportToHtmlFile(jasperPrint,destDirectiory);
            
            /* Jasper viewer for To view the result in viewer*/
            //  JasperViewer.viewReport(jasperPrint);
            isReportGenerated = true;
            
            
        }catch ( JRException jrex){
            throw new ServiceLocatorException(jrex);
        }finally{
            if(connection!=null){
                try {
                    connection.close();
                    connection = null;
                } catch (SQLException ex) {
                    throw new ServiceLocatorException(ex);
                }
                
            }
        }
        return isReportGenerated;
    }
    
    public boolean generateReportToXmlFile(String sourceJrxml, String destDirectiory) throws ServiceLocatorException {
        Map params = new HashMap();
        boolean isReportGenerated = false;
        Connection connection = null;
        try{
            JasperReport jasperReport = JasperCompileManager.compileReport(sourceJrxml);
            connection = ConnectionProvider.getInstance().getConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,params,connection);
            
            /* The Export manager will export the source file in to our required format*/
            JasperExportManager.exportReportToXmlFile(jasperPrint,destDirectiory,true);
            
            /* Jasper viewer for To view the result in viewer*/
            //  JasperViewer.viewReport(jasperPrint);
            isReportGenerated = true;
           
            
        }catch ( JRException jrex){
            throw new ServiceLocatorException(jrex);
        }finally {
            if(connection!=null){
                try {
                    connection.close();
                    connection = null;
                } catch (SQLException ex) {
                    throw new ServiceLocatorException(ex);
                }
                
            }
        }
        return isReportGenerated;
    }
    
    public boolean addEmpReportToDatabase(EmployeeAvailabilityAction employeeAvailabilityAction) throws ServiceLocatorException{
        boolean isInserted =false;
        Transaction transaction = null;
        Session hibernateSession = null;
        try {
            
            
            hibernateSession = HibernateServiceLocator.getInstance().getSession();
            
            transaction = hibernateSession.beginTransaction();
            
            //Generating query to inserting activity details into tblCrmActivity
            String queryString = "";
            
            /* Save Pojo object into Session Object */
            hibernateSession.save(employeeAvailabilityAction);
            
            /* Pushing Pojo Object into DataBase */
            hibernateSession.flush();
            
            /* Commit a Tranasaction */
            transaction.commit();
            
            /* Closing Session */
            hibernateSession.close();
            
            isInserted = true;
            
            hibernateSession = null;
            
        } catch(Exception e) {
            isInserted = false;
            throw new ServiceLocatorException(e);
        }
        return isInserted;
    }
    
    
    public boolean deleteReport(EmployeeAvailabilityAction employeeAvailabilityAction) throws ServiceLocatorException {
        boolean isDelete=false;
        try{
            
            //Create a seesion for connectionFactory
            Session session=HibernateServiceLocator.getInstance().getSession(); //for get session factory instance from hibernate service locator
            //Transaction Started
            Transaction trns=session.beginTransaction();
            trns.begin();
            //Save values in DataBase
            session.delete(employeeAvailabilityAction);
            session.flush();
            trns.commit();
            isDelete=true;
            session.clear();
            session.close();
            session = null;
        } catch(Exception ex) {
            throw new ServiceLocatorException(ex);
        }
        
        return isDelete;
        
    }
    
    public String getReportName(int reportId) throws ServiceLocatorException {
        Session session = HibernateServiceLocator.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        
        String SQL_QUERY ="Select h.reportName from EmployeeAvailabilityAction as h where h.id=:reportId";
        
        Query query = session.createQuery(SQL_QUERY).setInteger("reportId",reportId);
        for(Iterator it=query.iterate();it.hasNext();){
            reportName = (String) it.next();
        }//end of the for loop
        session.close();
        session = null;
        return reportName;
    }
 
    
    
      
       public String generatePFPortalReport(int contactId,String docType) throws ServiceLocatorException {       
        String filePath = "";
       // StringBuffer sb = null;

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
        String generatedPath = "";
        List finalList = new ArrayList();
        // System.out.println("the opscontactId"+contactId);
        try {         
            generatedPath = com.mss.mirage.util.Properties.getProperty("reports.PFPortal.Path");          
            //System.out.println("the generatedPath"+generatedPath);
            File file = new File(generatedPath);      
            if (!file.exists()) {
                file.mkdirs();
            }
            
            //FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath()+"/PFPortal.xls");
            FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath()+Properties.getProperty("OS.Compatabliliy.Download")+"PFPortal.xls");
            filePath = file.getAbsolutePath()+Properties.getProperty("OS.Compatabliliy.Download")+"PFPortal.xls";
            connection = ConnectionProvider.getInstance().getConnection();
            String query = null;
        
             if(docType.equals( "Aadhar no")) 
             {
            query = "SELECT DISTINCT EmpNo,UANNo,PFNo,CONCAT(FName,' ',LName) AS ContactName,IfscCode,PhyChallenged,PhyCategory,AadharNum as docNumber,Gender,MaritalStatus,IsInternationalWorker,BachDegree,PGDegree,AadharName,NameAsPerPan,NameAsPerAcc FROM tblEmployee LEFT OUTER JOIN tblEmpAncillary ON (tblEmployee.Id=tblEmpAncillary.EmpId)  WHERE Country='India' AND CurStatus='Active' ";            
           
             }
            else
             if(docType.equals("Bank Account no")) 
             {
            query = "SELECT DISTINCT EmpNo,UANNo,PFNo,CONCAT(FName,' ',LName) AS ContactName,IfscCode,PhyChallenged,PhyCategory,AccNum as docNumber,Gender,MaritalStatus,IsInternationalWorker,BachDegree,PGDegree,AadharName,NameAsPerPan,NameAsPerAcc FROM tblEmployee LEFT OUTER JOIN tblEmpAncillary ON (tblEmployee.Id=tblEmpAncillary.EmpId)  WHERE Country='India' AND CurStatus='Active' ";            
           
             }
            else if(docType.equals("SSN"))
             {
            query = "SELECT DISTINCT EmpNo,UANNo,PFNo,CONCAT(FName,' ',MName,'.',LName) AS ContactName,IfscCode,PhyChallenged,PhyCategory,SSN as docNumber,Gender,MaritalStatus,IsInternationalWorker,BachDegree,PGDegree,AadharName,NameAsPerPan,NameAsPerAcc FROM tblEmployee LEFT OUTER JOIN tblEmpAncillary ON (tblEmployee.Id=tblEmpAncillary.EmpId)  WHERE Country='India' AND CurStatus='Active' ";            
           
             }
            
               if(contactId!= -1){
               // System.out.println("contactId-->"+contactId);
               query = query + " AND Id = "+contactId; 
            }
            //System.out.println("the queryyyyyy"+query);
            int j = 1;
            preStmt = connection.prepareStatement(query);
            resultSet = preStmt.executeQuery();
            
            
            String HeaderNameAsPerDoc ="";
                   if(docType.equals( "Aadhar no")) {
                       HeaderNameAsPerDoc = "Name as per Document[AADHAR]";

                    }else if(docType.equals("Bank Account no")) {
                        HeaderNameAsPerDoc = "Name as per Document[Bank Account]";
                    }else if(docType.equals("SSN")){
                        HeaderNameAsPerDoc = "Name as per Document[PAN]";
                    }
            
            while (resultSet.next()) {
                String EmpNo = resultSet.getString("EmpNo");                
              String UANNo = resultSet.getString("UANNo");
               String PFNo = resultSet.getString("PFNo");               
                String ContactName = resultSet.getString("ContactName");            
                String IfscCode = resultSet.getString("IfscCode");                           
                String PhyChallenged = resultSet.getString("PhyChallenged");                           
                String BachDegree = resultSet.getString("BachDegree");   
                String PGDegree = resultSet.getString("PGDegree");   
                String PhyCategory = resultSet.getString("PhyCategory");                           
                String docNumber = resultSet.getString("docNumber");                           
                String Gender = resultSet.getString("Gender");                           
                String MaritalStatus = resultSet.getString("MaritalStatus");                           
                int IsInternationalWorker = resultSet.getInt("IsInternationalWorker");                           
                  String EduQualification = "-";
                  if(PGDegree!=null&&!"".equals(PGDegree)&&!"-1".equals(PGDegree))
                      EduQualification=PGDegree;
                  else if(BachDegree!=null&&!"".equals(BachDegree)&&!"-1".equals(BachDegree))
                      EduQualification=BachDegree;
                  
                  String NameAsPerDoc ="";
                   if(docType.equals( "Aadhar no")) {
                       NameAsPerDoc = resultSet.getString("AadharName"); 
                    }else if(docType.equals("Bank Account no")) {
                        NameAsPerDoc = resultSet.getString("NameAsPerAcc"); 
                    }else if(docType.equals("SSN")){
                        NameAsPerDoc = resultSet.getString("NameAsPerPan"); 
                    }
                  
                  
                 
                map = new HashMap();
                map.put("SNO", String.valueOf(j));
                map.put("EmpNo", EmpNo);
                map.put("UANNo", UANNo);
                map.put("PFNo", PFNo);
                map.put("ContactName", ContactName);
                map.put("IfscCode", IfscCode);
                map.put("NameAsPerDoc", NameAsPerDoc);
                map.put("EduQualification", EduQualification);
                map.put("PhyChallenged", PhyChallenged);
                map.put("PhyCategory", PhyCategory);
                map.put("docNumber", docNumber);
                map.put("Gender", Gender);
                map.put("MaritalStatus", MaritalStatus);
                if(IsInternationalWorker==0)
                map.put("IsInternationalWorker", "No");
                else
                    map.put("IsInternationalWorker", "Yes");
                finalList.add(map);
                j++;

            }
         

            if (finalList.size() > 0) {
                filePath = file.getAbsolutePath()+"/PFPortal.xls";
              //  System.out.println("the file path"+filePath);
//                HSSFWorkbook hssfworkbook = new HSSFWorkbook();
//                HSSFSheet sheet = hssfworkbook.createSheet("PF Portal sheet");
//                
                 HSSFWorkbook hssfworkbook = new HSSFWorkbook();
      HSSFSheet sheet = hssfworkbook.createSheet("PF Portal sheet");
//     
//      HSSFCellStyle cs = hssfworkbook.createCellStyle();
//      HSSFDataFormat df = hssfworkbook.createDataFormat();
//                
                
                

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
                HSSFCell cell8 = row.createCell((short) 8);
                HSSFCell cell9 = row.createCell((short) 9);
                HSSFCell cell10 = row.createCell((short) 10);
                HSSFCell cell11 = row.createCell((short) 11);
                HSSFCell cell12 = row.createCell((short) 12);
                HSSFCell cell13 = row.createCell((short) 13);

                cell.setCellValue("SNO");
                cell1.setCellValue("EmpNo");
                cell2.setCellValue("UANNo");
                cell3.setCellValue("PFNo");
                cell4.setCellValue("Member Name(Employee Name)");
                cell5.setCellValue("IfscCode");
                cell6.setCellValue(HeaderNameAsPerDoc);
                cell7.setCellValue("Educational Qualification");
                cell8.setCellValue("PhyChallenged");
                cell9.setCellValue("PhyCategory");
                cell10.setCellValue(docType);
                cell11.setCellValue("Gender");
                cell12.setCellValue("MaritalStatus");
                cell13.setCellValue("IsInternationalWorker");


//HeaderNameAsPerDoc


                cell.setCellStyle(headercs);
                cell1.setCellStyle(headercs);
                cell2.setCellStyle(headercs);
                cell3.setCellStyle(headercs);
                cell4.setCellStyle(headercs);
                cell5.setCellStyle(headercs);
                cell6.setCellStyle(headercs);
                cell7.setCellStyle(headercs);
                cell8.setCellStyle(headercs);
                cell9.setCellStyle(headercs);
                cell10.setCellStyle(headercs);
                cell11.setCellStyle(headercs);
                cell12.setCellStyle(headercs);
                cell13.setCellStyle(headercs);

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
                        cell8 = row.createCell((short) 8);
                        cell9 = row.createCell((short) 9);
                        cell10 = row.createCell((short) 10);
                        cell11= row.createCell((short) 11);
                        cell12= row.createCell((short) 12);
                        cell13= row.createCell((short) 13);



                        cell.setCellValue((String) stateHistorylMap.get("SNO"));
                        cell1.setCellValue((String) stateHistorylMap.get("EmpNo"));
                        cell2.setCellValue((String) stateHistorylMap.get("UANNo"));                   
                        cell3.setCellValue((String) stateHistorylMap.get("PFNo"));
                        cell4.setCellValue((String) stateHistorylMap.get("ContactName"));
                        cell5.setCellValue((String) stateHistorylMap.get("IfscCode"));
                        cell6.setCellValue((String) stateHistorylMap.get("NameAsPerDoc"));
                        cell7.setCellValue((String) stateHistorylMap.get("EduQualification"));
                        cell8.setCellValue((String) stateHistorylMap.get("PhyChallenged"));
                        cell9.setCellValue((String) stateHistorylMap.get("PhyCategory"));
                        cell10.setCellValue((String) stateHistorylMap.get("docNumber"));
                        cell11.setCellValue((String) stateHistorylMap.get("Gender"));
                        cell12.setCellValue((String) stateHistorylMap.get("MaritalStatus"));
                        cell13.setCellValue((String) stateHistorylMap.get("IsInternationalWorker"));

 cell.setCellStyle(cs);
      cell1.setCellStyle(cs);
      cell2.setCellStyle(cs);
        cell3.setCellStyle(cs);
         cell4.setCellStyle(cs);
         cell5.setCellStyle(cs);
         cell6.setCellStyle(cs);
         cell7.setCellStyle(cs);
         cell8.setCellStyle(cs);
         cell9.setCellStyle(cs);
         cell10.setCellStyle(cs);
         cell11.setCellStyle(cs);
         cell12.setCellStyle(cs);
         cell13.setCellStyle(cs);

                        if (count % 2 == 0) {
                            cell.setCellStyle(cellColor1);
                            cell1.setCellStyle(cellColor1);
                            cell2.setCellStyle(cellColor1);
                            cell3.setCellStyle(cellColor1);
                            cell4.setCellStyle(cellColor1);
                            cell5.setCellStyle(cellColor1);
                            cell6.setCellStyle(cellColor1);
                            cell7.setCellStyle(cellColor1);
                            cell8.setCellStyle(cellColor1);
                            cell9.setCellStyle(cellColor1);
                            cell10.setCellStyle(cellColor1);
                            cell11.setCellStyle(cellColor1);
                            cell12.setCellStyle(cellColor1);
                            cell13.setCellStyle(cellColor1);

                        } else {
                            cell.setCellStyle(cellColor);
                            cell1.setCellStyle(cellColor);
                            cell2.setCellStyle(cellColor);
                            cell3.setCellStyle(cellColor);
                            cell4.setCellStyle(cellColor);
                            cell5.setCellStyle(cellColor);
                            cell6.setCellStyle(cellColor);
                            cell7.setCellStyle(cellColor);
                            cell8.setCellStyle(cellColor);
                            cell9.setCellStyle(cellColor);
                            cell10.setCellStyle(cellColor);
                            cell11.setCellStyle(cellColor);
                            cell12.setCellStyle(cellColor);
                            cell13.setCellStyle(cellColor);
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
                    cell8 = row.createCell((short) 8);
                    cell9 = row.createCell((short) 9);
                    cell10 = row.createCell((short) 10);
                    cell11 = row.createCell((short) 11);
                    cell12 = row.createCell((short) 12);
                    
                    cell.setCellStyle(footercs);
                    cell1.setCellStyle(footercs);
                    cell2.setCellStyle(footercs);
                    cell3.setCellStyle(footercs);

                    cell4.setCellStyle(footercs);
                    cell5.setCellStyle(footercs);
                    cell6.setCellStyle(footercs);
                    cell7.setCellStyle(footercs);
                    cell8.setCellStyle(footercs);
                    cell9.setCellStyle(footercs);
                    cell10.setCellStyle(footercs);
                    cell11.setCellStyle(footercs);
                    cell12.setCellStyle(footercs);
                }
                HSSFCellStyle totalSum = hssfworkbook.createCellStyle();
                totalSum.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
                totalSum.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
                totalSum.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                totalSum.setBorderTop((short) 1); // single line border
                totalSum.setBorderBottom((short) 1); // single line border
                totalSum.setFont(timesBoldFont1);

                   
                sheet.autoSizeColumn((int) 0);               
                sheet.autoSizeColumn((int) 3);
                sheet.autoSizeColumn((int) 4);
                sheet.autoSizeColumn((int) 6);
                sheet.autoSizeColumn((int) 7);
                sheet.autoSizeColumn((int) 8);
                sheet.autoSizeColumn((int) 9);
                sheet.autoSizeColumn((int) 10);
                sheet.autoSizeColumn((int) 13);
               // sheet.setColumnWidth(1, 50 * 256);
                sheet.setColumnWidth(2, 35 * 256);
                sheet.setColumnWidth(5, 25 * 256);
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
        // System.out.println("the file path"+filePath);
        return filePath;


    }
    
    

}
