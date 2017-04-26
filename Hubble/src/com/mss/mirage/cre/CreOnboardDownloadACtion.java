/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.cre;


import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.ReportProperties;
import java.sql.*;



import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.Properties;
import com.opensymphony.xwork2.Action;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import org.apache.poi.hssf.record.formula.functions.Cell;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;


/**
 *
 * @author miracle
 */
public class CreOnboardDownloadACtion implements
        Action,ServletRequestAware,ServletResponseAware{
    
    
    private String contentDisposition="FileName=inline";
    public InputStream inputStream;
    public OutputStream outputStream;
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private String fileName;
    private String downloadType;
    private String sheetType;
    private int creId;
       public String execute() throws Exception {
        return null;
    }
       
       
        public void setServletResponse(HttpServletResponse httpServletResponse) {
        
        //String responseString="";
        try {
         
            String fileLocation ="";
            //For creating Excel grind from Search result Grid
            
           
           fileLocation = generateCreOnboardForm(getCreId());
             
        //    httpServletResponse.setContentType("application/force-download");
           // File file = new File(Properties.getProperty("mscvp.docCreationPath")+"SearchedExcelDocument.xls");
                    File file = new File(fileLocation); 
                   Date date = new Date();
                   
                   //fileName = (date.getYear()+1900)+"_"+(date.getMonth()+1)+"_"+date.getDate()+"_"+file.getName(); 
                   fileName = file.getName(); 
            if(file.exists()){
            inputStream = new FileInputStream(file);
            outputStream =  httpServletResponse.getOutputStream();
            httpServletResponse.setHeader("Content-Disposition","attachment;filename=\"" + fileName +"\"");
            int noOfBytesRead = 0;
            byte[] byteArray = null;
            while(true) {
                byteArray = new byte[1024];
                noOfBytesRead = inputStream.read(byteArray);
                if(noOfBytesRead==-1) break;
                outputStream.write(byteArray, 0, noOfBytesRead);
            }
            //responseString = "downLoaded!!";
                httpServletResponse.setContentType("application/pdf");
                //httpServletResponse.getWriter().write(responseString);
            }else{
                throw new FileNotFoundException("File not found");
            } 
        }catch (FileNotFoundException ex) {
            try {
                httpServletResponse.sendRedirect("../general/exception.action?exceptionMessage='No File found'");
            } catch (IOException ex1) {
                Logger.getLogger(CreOnboardDownloadACtion.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }catch (IOException ex) {
            ex.printStackTrace();
        }/*catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        }*/finally {
            try {
               
                inputStream.close();
                outputStream.flush();
                outputStream.close();
                httpServletResponse.flushBuffer();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        
        
    }
    
    
    public String generateCreOnboardForm(int creId) {
        String fileLocation = "";
        
         PreparedStatement stmt = null;
        int id = 0;
        String name = "";
        Blob image1 = null;
        byte[] im = null;
        String fullName="";
        String postAppliedFor="";
        String createdDate="";
        String icetOrEamcetRank="";
        String dob="";
        String gender="";
        String fatherName="";
        String fatherOccupation="";
        String address = "";
        String city = "";
        String phoneNumber = "";
        String email = "";
        String mobileNumber = "";
        String achievements = "";
        
        String firstName="";
        String lastName="";
        
         File imageFile = null;
        String consultentId = "";
        
        Connection connection =  null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;       
        try {
           // Class.forName("org.gjt.mm.mysql.Driver");
            //Connection con = DriverManager.getConnection("jdbc:mysql://172.17.4.79/mirage", "AppAdmin", "lokam001");
             connection = ConnectionProvider.getInstance().getConnection();
                    
            //con = DriverManager.getConnection(url, "user","password");
            preparedStatement = connection.prepareStatement("select * from tblCreConsultentDetails where ID="+creId);
             resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("Id");
                fullName = resultSet.getString("FName")+"."+resultSet.getString("LName");
                firstName = resultSet.getString("FName");
                lastName = resultSet.getString("LName");
                
                if("1".equals(resultSet.getString("Category"))){
                 postAppliedFor  = "Software Trainee" ;   
                }else if("2".equals(resultSet.getString("Category"))){
                 postAppliedFor  = "Sr.Developer" ;   
                }else if("3".equals(resultSet.getString("Category"))){
                 postAppliedFor  = "Sales" ;   
                }else if("4".equals(resultSet.getString("Category"))){
                 postAppliedFor  = "IT Recruitment" ;   
                }else if("5".equals(resultSet.getString("Category"))){
                 postAppliedFor  = "Operations" ;   
                }else if("6".equals(resultSet.getString("Category"))){
                 postAppliedFor  = "Network Engineer" ;   
                }else if("7".equals(resultSet.getString("Category"))){
                 postAppliedFor  = "Trainer" ;   
                }else if("8".equals(resultSet.getString("Category"))){
                 postAppliedFor  = "Civil" ;   
                }else if("9".equals(resultSet.getString("Category"))){
                 postAppliedFor  = "MES" ;   
                }else {
                 postAppliedFor  = "Other" ;   
                }
                
                
                
                
                
                //postAppliedFor = result.getString("Category");
                createdDate = resultSet.getString("CreatedDate");
                icetOrEamcetRank = resultSet.getString("CreatedDate");
                gender = resultSet.getString("Gender");
                fatherName = resultSet.getString("FatherName");
                
                 fatherOccupation = resultSet.getString("FatherOccupation");
                address = resultSet.getString("Address");
                dob = resultSet.getString("DOB");
                city = resultSet.getString("City");
                phoneNumber = resultSet.getString("AlterPhoneNo");
                email = resultSet.getString("Email1");
                mobileNumber = resultSet.getString("CellPhoneNo");
                achievements =  resultSet.getString("Addinfo");
               // System.out.println("achievements-->"+achievements);
                //image1=result.getBlob("Image");
                //im = resultSet.getBytes("Image");
               //  filePath = resultSet.getString("filePath");
                consultentId = resultSet.getString("ConsultentId");
                
            }
            
             String basePath = Properties.getProperty("CreConsultantImages.path");
            File createPath = new File(basePath);
                    // System.out.println("createPath..." + createPath);
                     imageFile = new File(createPath.getAbsolutePath() + "/" + consultentId + ".png");

                     
            File creOnboardFile = new File(ReportProperties.getProperty("Cre.OnboardFormLocation"));
            if(!creOnboardFile.exists()) {
                creOnboardFile.mkdirs();
            }
            
            File creFile = new File(creOnboardFile,fullName+".pdf");
            fileLocation = creFile.getAbsolutePath();
            //OutputStream file = new FileOutputStream(new File("C:\\Documents and Settings\\miracle\\Desktop\\ss\\1MSSCRE"+id+".pdf"));
            OutputStream file = new FileOutputStream(creFile);
            
          //  OutputStream file = new FileOutputStream(new File("/home/itg5/Desktop/CREOnboarding.pdf"));
            Document document = new Document();
            PdfWriter.getInstance(document, file);

            //Inserting Image in PDF
            //Image image = Image.getInstance ("C:\\Users\\miracle\\Desktop\\Miracle-Logo.jpg");
            Image image = Image.getInstance (ReportProperties.getProperty("Cre.OnboardMiracleLogo"));
             image.setAlignment(Image.LEFT | Image.TEXTWRAP);
            //image3.getAlignment();
            image.scaleAbsolute(180f, 30f);//image width,height	
            //image=null;
            //java.awt.Image image = Toolkit.getDefaultToolkit().createImage(im);
            Image image3 =null;
             if(imageFile.exists()) {
              //   image3 = Image.getInstance(im);
                // String basePath = Properties.getProperty("CreConsultantImages.path");
                 //   File createPath = new File(basePath);
                //    System.out.println("createPath..." + createPath);
                   // File newFile = new File(createPath.getAbsolutePath() + "//" + consultentId + ".JPG");
                image3 = Image.getInstance(Properties.getProperty("CreConsultantImages.path").trim() + "/" + consultentId + ".png");
            }
            else {
                //image3 = Image.getInstance ("C:\\Users\\miracle\\Desktop\\noimage.jpg");
                image3 = Image.getInstance (ReportProperties.getProperty("Cre.OnboardNoImage"));
            }
           // Image image3 = Image.getInstance(im);
            image3.setAlignment(Image.RIGHT | Image.TEXTWRAP);
            //image3.getAlignment();
            image3.scaleAbsolute(80f, 90f);//image width,height	

            //Inserting Table in PDF
        /*    PdfPTable table = new PdfPTable(3);

            PdfPCell cell = new PdfPCell(new Paragraph("Miracle Onboarding"));

            cell.setColspan(3);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPadding(10.0f);
            cell.setBackgroundColor(new BaseColor(140, 221, 8));

            table.addCell(cell);

            table.addCell("Name");
            table.addCell("Address");
            table.addCell("Country");
            table.addCell(name);
            table.addCell("Vizag");
            table.addCell("India");
            table.setSpacingBefore(30.0f);       // Space Before table starts, like margin-top in CSS
            table.setSpacingAfter(30.0f);        // Space After table starts, like margin-Bottom in CSS								          
*/
            //Inserting List in PDF
            /*List list = new List(true, 30);
            list.add(new ListItem("Java4s"));
            list.add(new ListItem("Php4s"));
            list.add(new ListItem("Some Thing..."));*/

            //Text formating in PDF
	               /* Chunk chunk=new Chunk("Welecome To Java4s Programming Blog...");
            chunk.setUnderline(+1f,-2f);//1st co-ordinate is for line width,2nd is space between
            Chunk chunk1=new Chunk("Php4s.com");
            chunk1.setUnderline(+4f,-8f);
            chunk1.setBackground(new BaseColor (17, 46, 193));*/

            
            
            
             Font font1 = new Font(Font.FontFamily.HELVETICA  , 25, Font.BOLD);
          Font font2 = new Font(Font.FontFamily.COURIER    , 18,
          Font.ITALIC | Font.UNDERLINE);
          Font font3 = new Font(Font.FontFamily.TIMES_ROMAN, 8);
           Font fontBold = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD);
           Font headingFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
            
            
            
            
            //Now Insert Every Thing Into PDF Document
            document.open();//PDF document opened........			       
 //document.add(image);
           // document.add(image3);

           // document.add(Chunk.NEWLINE);   //Something like in HTML :-)

           // document.add(new Paragraph("Cadidate Photo"));
           // document.add(Chunk.NEWLINE);
           // document.add(Chunk.NEWLINE);
           // document.add(Chunk.NEWLINE);
           //document.add(Chunk.NEWLINE);
           // document.add(new Paragraph("Document Generated On - " + new Date().toString()));
          //  document.add(new Paragraph("Cadidate Details "));
//document.add(new Paragraph("Full Name"));
           
           
           PdfPCell logoCell = new PdfPCell(image);
logoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
logoCell.setColspan(3);
 logoCell.setBorder(0);
document.add(logoCell);
           
           
           
           
           
                PdfPCell cadidateDetailsCell1 = new PdfPCell(new Paragraph("CandidateDetails ",headingFont));
    cadidateDetailsCell1.setHorizontalAlignment(Element.ALIGN_LEFT);
    cadidateDetailsCell1.setBorder(0);
    cadidateDetailsCell1.setColspan(3);
    document.add(cadidateDetailsCell1);
    
    
      PdfPCell postAppliedCell1 = new PdfPCell(new Paragraph("Post Applied For : ",font3));
    postAppliedCell1.setHorizontalAlignment(Element.ALIGN_LEFT);
    postAppliedCell1.setBorder(0);
    document.add(postAppliedCell1);
             
             
 PdfPCell postAppliedCell = new PdfPCell(new Paragraph(postAppliedFor,fontBold));
postAppliedCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//nameCell.setColspan(2);
 postAppliedCell.setBorder(0);
document.add(postAppliedCell);
    
         PdfPCell dateCell1 = new PdfPCell(new Paragraph("Date : ",font3));
    dateCell1.setHorizontalAlignment(Element.ALIGN_LEFT);
    dateCell1.setBorder(0);
    document.add(dateCell1);
             
             
 PdfPCell createdDateCell = new PdfPCell(new Paragraph(createdDate,fontBold));
createdDateCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//nameCell.setColspan(2);
 createdDateCell.setBorder(0);
document.add(createdDateCell);
            
    PdfPCell nameCell1 = new PdfPCell(new Paragraph("FullName : ",font3));
    nameCell1.setHorizontalAlignment(Element.ALIGN_LEFT);
    nameCell1.setBorder(0);
    document.add(nameCell1);
             
             
 PdfPCell nameCell = new PdfPCell(new Paragraph(fullName,fontBold));
nameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//nameCell.setColspan(2);
 nameCell.setBorder(0);
document.add(nameCell);

PdfPCell imageCell = new PdfPCell(image3);
imageCell.setHorizontalAlignment(Element.ALIGN_LEFT);
imageCell.setRowspan(12);
 imageCell.setBorder(0);
document.add(imageCell);

 /*PdfPCell rankCel1 = new PdfPCell(new Paragraph("ICET/EAMCET Rank :",font3));
rankCel1.setHorizontalAlignment(Element.ALIGN_LEFT);

 rankCel1.setBorder(0);
document.add(rankCel1);

 PdfPCell rankCel2 = new PdfPCell(new Paragraph("12345",fontBold));
rankCel2.setHorizontalAlignment(Element.ALIGN_LEFT);

 rankCel2.setBorder(0);
document.add(rankCel2);*/

//--------------

PdfPCell dobCel1 = new PdfPCell(new Paragraph("DateOdBirth:",font3));
dobCel1.setHorizontalAlignment(Element.ALIGN_LEFT);
 dobCel1.setBorder(0);
document.add(dobCel1);
PdfPCell dobCel2 = null;
if(!"".equals(dob)&&dob!=null){
    dobCel2 = new PdfPCell(new Paragraph(dob.split(" ")[0],fontBold));
}else {
    dobCel2 = new PdfPCell(new Paragraph("",fontBold));
}
//PdfPCell dobCel2 = new PdfPCell(new Paragraph(dob.split(" ")[0],fontBold));
dobCel2.setHorizontalAlignment(Element.ALIGN_LEFT);
 dobCel2.setBorder(0);
document.add(dobCel2);


PdfPCell genderCel1 = new PdfPCell(new Paragraph("Gender:",font3));
genderCel1.setHorizontalAlignment(Element.ALIGN_LEFT);
 genderCel1.setBorder(0);
document.add(genderCel1);

PdfPCell genderCel2 = new PdfPCell(new Paragraph(gender,fontBold));
genderCel2.setHorizontalAlignment(Element.ALIGN_LEFT);
 genderCel2.setBorder(0);
document.add(genderCel2);

/*PdfPCell nativeCel1 = new PdfPCell(new Paragraph("NativePlace:",font3));
nativeCel1.setHorizontalAlignment(Element.ALIGN_LEFT);
 nativeCel1.setBorder(0);
document.add(nativeCel1);

PdfPCell nativeCel2 = new PdfPCell(new Paragraph("Visakhapatnam",fontBold));
nativeCel2.setHorizontalAlignment(Element.ALIGN_LEFT);
 nativeCel2.setBorder(0);
document.add(nativeCel2);*/

//----------------------------------------

    PdfPCell fnameCell1 = new PdfPCell(new Paragraph("FatherName : ",font3));
    fnameCell1.setHorizontalAlignment(Element.ALIGN_LEFT);
    fnameCell1.setBorder(0);
    document.add(fnameCell1);
             
             
 PdfPCell fnameCell2 = new PdfPCell(new Paragraph(fatherName,fontBold));
fnameCell2.setHorizontalAlignment(Element.ALIGN_LEFT);
//fnameCell2.setColspan(2);
 fnameCell2.setBorder(0);
document.add(fnameCell2);

 PdfPCell foccuCel1 = new PdfPCell(new Paragraph("FatherOccupation :",font3));
foccuCel1.setHorizontalAlignment(Element.ALIGN_LEFT);
//foccuCel1.setColspan(2);
 foccuCel1.setBorder(0);
document.add(foccuCel1);

 PdfPCell foccuCel2 = new PdfPCell(new Paragraph(fatherOccupation,fontBold));
foccuCel2.setHorizontalAlignment(Element.ALIGN_LEFT);

 foccuCel2.setBorder(0);
document.add(foccuCel2);
//------------------------------

  PdfPCell addrCell1 = new PdfPCell(new Paragraph("Address : ",font3));
    addrCell1.setHorizontalAlignment(Element.ALIGN_LEFT);
    addrCell1.setBorder(0);
    //foccuCel1.setRowspan(2);
    //foccuCel1.setColspan(2);
    document.add(addrCell1);
    
     PdfPCell addrCell2 = new PdfPCell(new Paragraph(address,fontBold));
    addrCell2.setHorizontalAlignment(Element.ALIGN_LEFT);
    addrCell2.setBorder(0);
    //foccuCel1.setColspan(2);
    //foccuCel1.setRowspan(2);
    document.add(addrCell2); 
    
    
    
     PdfPCell cityCel1 = new PdfPCell(new Paragraph("City :",font3));
cityCel1.setHorizontalAlignment(Element.ALIGN_LEFT);
//cityCel1.setColspan(2);
 cityCel1.setBorder(0);
document.add(cityCel1);

 PdfPCell cityCel2 = new PdfPCell(new Paragraph(city,fontBold));
cityCel2.setHorizontalAlignment(Element.ALIGN_LEFT);

 cityCel2.setBorder(0);
document.add(cityCel2);
    
   
  PdfPCell phoneCel1 = new PdfPCell(new Paragraph("Phone :",font3));
phoneCel1.setHorizontalAlignment(Element.ALIGN_LEFT);
//phoneCel1.setColspan(2);
 phoneCel1.setBorder(0);
document.add(phoneCel1);

 PdfPCell phoneCel2 = new PdfPCell(new Paragraph(phoneNumber,fontBold));
phoneCel2.setHorizontalAlignment(Element.ALIGN_LEFT);

 phoneCel2.setBorder(0);
document.add(phoneCel2);

//-------------------------------------------

 PdfPCell emailCell1 = new PdfPCell(new Paragraph("Email : ",font3));
    emailCell1.setHorizontalAlignment(Element.ALIGN_LEFT);
    emailCell1.setBorder(0);
    document.add(emailCell1);
             
             
 PdfPCell emailCell2 = new PdfPCell(new Paragraph(email,fontBold));
emailCell2.setHorizontalAlignment(Element.ALIGN_LEFT);
//emailCell2.setColspan(2);
 emailCell2.setBorder(0);
document.add(emailCell2);

 PdfPCell mobileCel1 = new PdfPCell(new Paragraph("Mobile :",font3));
mobileCel1.setHorizontalAlignment(Element.ALIGN_LEFT);
//mobileCel1.setColspan(2);
 mobileCel1.setBorder(0);
document.add(mobileCel1);

 PdfPCell mobileCel2 = new PdfPCell(new Paragraph(mobileNumber,fontBold));
mobileCel2.setHorizontalAlignment(Element.ALIGN_LEFT);

 mobileCel2.setBorder(0);
document.add(mobileCel2);

PdfPTable logoTable = new PdfPTable(3);
  logoTable.addCell(logoCell);
    document.add(logoTable);
    
    document.add(Chunk.NEWLINE);
    
    
PdfPTable nameTable = new PdfPTable(3);

            
                     //nameTable.addCell(logoCell);

                    nameTable.addCell(cadidateDetailsCell1);
                    nameTable.addCell(postAppliedCell1);
nameTable.addCell(postAppliedCell);
nameTable.addCell(imageCell);
nameTable.addCell(dateCell1);
nameTable.addCell(createdDateCell);

nameTable.addCell(nameCell1);
nameTable.addCell(nameCell);


//nameTable.addCell(rankCel1);
//nameTable.addCell(rankCel2);
//nameTable.addCell(nameCelSpace);
//PdfPTable nameTable1 = new PdfPTable(6);
// nameTable1.setSpacingBefore(30.0f);       // Space Before table starts, like margin-top in CSS
  //          nameTable1.setSpacingAfter(30.0f);
nameTable.addCell(dobCel1);
nameTable.addCell(dobCel2);
nameTable.addCell(genderCel1);
nameTable.addCell(genderCel2);
//nameTable.addCell(nativeCel1);
//nameTable.addCell(nativeCel2);
nameTable.addCell(fnameCell1);
nameTable.addCell(fnameCell2);
nameTable.addCell(foccuCel1);
nameTable.addCell(foccuCel2);

nameTable.addCell(addrCell1);
nameTable.addCell(addrCell2);
nameTable.addCell(cityCel1);
nameTable.addCell(cityCel2);
nameTable.addCell(phoneCel1);
nameTable.addCell(phoneCel2);

nameTable.addCell(emailCell1);
nameTable.addCell(emailCell2);
nameTable.addCell(mobileCel1);
nameTable.addCell(mobileCel2);

 nameTable.setSpacingBefore(10.0f);       // Space Before table starts, like margin-top in CSS
            nameTable.setSpacingAfter(10.0f);
document.add(nameTable);
 //document.add(Chunk.NEWLINE); 
//document.add(nameTable1);
          //  document.add(table);

            //document.add(chunk);
            //document.add(chunk1);

          //  document.add(Chunk.NEWLINE);   //Something like in HTML :-)	
            
            
            // document.add(new Paragraph("Qualification[Recent To Past]",headingFont));
            
           // document.add(Chunk.NEWLINE);
            
             // Education TAble
             
                 PdfPTable qualificationTable = new PdfPTable(5);
                 
                  PdfPCell qualLabelHeader = new PdfPCell(new Paragraph("Qualification[Recent To Past]",headingFont));
                 qualLabelHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
                 qualLabelHeader.setBorder(0);
                 qualLabelHeader.setColspan(5);
                  document.add(qualLabelHeader);
                 qualificationTable.addCell(qualLabelHeader);
                 
                 

            PdfPCell qualHeader = new PdfPCell(new Paragraph("Qualification",fontBold));

           // cell.setColspan(3);
            qualHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
           // cell.setPadding(10.0f);
            //cell.setBackgroundColor(new BaseColor(140, 221, 8));
            qualHeader.setBackgroundColor(BaseColor.GRAY);
            
            PdfPCell collegeHeader = new PdfPCell(new Paragraph("College/University",fontBold));

           // cell.setColspan(3);
            collegeHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
           // cell.setPadding(10.0f);
            //cell.setBackgroundColor(new BaseColor(140, 221, 8));
            collegeHeader.setBackgroundColor(BaseColor.GRAY);
            
            
            
            
            PdfPCell mediumHeader = new PdfPCell(new Paragraph("Medium",fontBold));

           // cell.setColspan(3);
            mediumHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
           // cell.setPadding(10.0f);
            //cell.setBackgroundColor(new BaseColor(140, 221, 8));
            mediumHeader.setBackgroundColor(BaseColor.GRAY);
            
            PdfPCell yearOfPassHeader = new PdfPCell(new Paragraph("YrOfPass",fontBold));

           // cell.setColspan(3);
            yearOfPassHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
           // cell.setPadding(10.0f);
            //cell.setBackgroundColor(new BaseColor(140, 221, 8));
            yearOfPassHeader.setBackgroundColor(BaseColor.GRAY);
            
                PdfPCell percentageHeader = new PdfPCell(new Paragraph("%",fontBold));

           // cell.setColspan(3);
            percentageHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
           // cell.setPadding(10.0f);
            //cell.setBackgroundColor(new BaseColor(140, 221, 8));
            percentageHeader.setBackgroundColor(BaseColor.GRAY);
            
            qualificationTable.addCell(qualHeader);
            qualificationTable.addCell(collegeHeader);
             qualificationTable.addCell(mediumHeader);
             qualificationTable.addCell(yearOfPassHeader);
             qualificationTable.addCell(percentageHeader);
             
             if(resultSet!=null) {
                 resultSet.close();
                 resultSet = null;
             }
               if(preparedStatement!=null) {
                 preparedStatement.close();
                 preparedStatement = null;
             }
                 if(connection!=null) {
                 connection.close();
                 connection = null;
             }
            
             
             
            //  con = DriverManager.getConnection("jdbc:mysql://172.17.4.79/mirage", "AppAdmin", "lokam001");
             connection = ConnectionProvider.getInstance().getConnection();
              preparedStatement = connection.prepareStatement("select cource,university,medium,passyear,percent from tblCreAcademics where CreId="+creId+" ORDER BY ID DESC");
             resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
             
              PdfPCell courseCell = new PdfPCell(new Paragraph(resultSet.getString("cource"),font3));
              courseCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                document.add(courseCell);
                 qualificationTable.addCell(courseCell);
                 
                PdfPCell universityCell = new PdfPCell(new Paragraph(resultSet.getString("university"),font3));
              universityCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                document.add(universityCell);
                qualificationTable.addCell(universityCell);
                
                PdfPCell mediumCell = new PdfPCell(new Paragraph(resultSet.getString("medium"),font3));
              mediumCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                document.add(mediumCell);
                qualificationTable.addCell(mediumCell);
                
                   PdfPCell yearCell = new PdfPCell(new Paragraph(resultSet.getString("passyear"),font3));
              yearCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                document.add(yearCell);
                qualificationTable.addCell(yearCell);
                
                 PdfPCell percentCell = new PdfPCell(new Paragraph(resultSet.getString("percent"),font3));
              percentCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                document.add(percentCell);
             qualificationTable.addCell(percentCell);
            }
             
             
              //qualificationTable.setSpacingBefore(30.0f);       // Space Before table starts, like margin-top in CSS
            qualificationTable.setSpacingAfter(10.0f);
             document.add(qualificationTable);
             
             
             //Skill Set
             
              document.add(Chunk.NEWLINE);   //Something like in HTML :-)	
             
            
             //document.add(new Paragraph("Skill Set",headingFont));
            
           // document.add(Chunk.NEWLINE);
             
             
             PdfPTable skillSetTable = new PdfPTable(2);
            // skillSetTable.setWidthPercentage(50);
             //skillSetTable.setHorizontalAlignment(Element.ALIGN_LEFT);
             
               PdfPCell skillLabelHeader = new PdfPCell(new Paragraph("Skill Set",headingFont));
             skillLabelHeader.setColspan(2);
             skillLabelHeader.setBorder(0);
             skillLabelHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
             document.add(skillLabelHeader);
                 skillSetTable.addCell(skillLabelHeader);
             

            PdfPCell skillHeader = new PdfPCell(new Paragraph("Skill",fontBold));

           // cell.setColspan(3);
            skillHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
           // cell.setPadding(10.0f);
            //cell.setBackgroundColor(new BaseColor(140, 221, 8));
            skillHeader.setBackgroundColor(BaseColor.GRAY);
             
               PdfPCell skillScaleHeader = new PdfPCell(new Paragraph("Scale",fontBold));

           // cell.setColspan(3);
            skillScaleHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
           // cell.setPadding(10.0f);
            //cell.setBackgroundColor(new BaseColor(140, 221, 8));
            skillScaleHeader.setBackgroundColor(BaseColor.GRAY);
            skillSetTable.addCell(skillHeader);
            skillSetTable.addCell(skillScaleHeader);
            
                if(resultSet!=null) {
                 resultSet.close();
                 resultSet = null;
             }
               if(preparedStatement!=null) {
                 preparedStatement.close();
                 preparedStatement = null;
             }
                 if(connection!=null) {
                 connection.close();
                 connection = null;
             }
             // con = DriverManager.getConnection("jdbc:mysql://172.17.4.79/mirage", "AppAdmin", "lokam001");
             connection = ConnectionProvider.getInstance().getConnection();
              preparedStatement = connection.prepareStatement("select skill,scale from tblCreSkillset where CreId="+creId+" ORDER BY ID DESC");
             resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                 PdfPCell skillCell = new PdfPCell(new Paragraph(resultSet.getString("skill"),font3));
              skillCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                document.add(skillCell);
                 skillSetTable.addCell(skillCell);
                 
                   PdfPCell scaleCell = new PdfPCell(new Paragraph(resultSet.getString("scale"),font3));
              scaleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                document.add(scaleCell);
                 skillSetTable.addCell(scaleCell);
            }
            
            skillSetTable.setSpacingAfter(10.0f);
            
            document.add(skillSetTable);
            
            
            //Experience
            
            
            
              //Skill Set
             
              document.add(Chunk.NEWLINE);   //Something like in HTML :-)	
            
            
            // document.add(new Paragraph("Experience",headingFont));
            
            //document.add(Chunk.NEWLINE);
            
             PdfPTable experienceTable = new PdfPTable(5);
            // experienceTable.setWidthPercentage(50);

             PdfPCell expHeader = new PdfPCell(new Paragraph("Experience",headingFont));

            expHeader.setColspan(5);
            expHeader.setBorder(0);
            expHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
           // cell.setPadding(10.0f);
            //cell.setBackgroundColor(new BaseColor(140, 221, 8));
           // expHeader.setBackgroundColor(BaseColor.GRAY);
            experienceTable.addCell(expHeader);
            
            
            
            //PdfPCell desigHeader = new PdfPCell(new Paragraph("Designation",fontBold));

             
             
            PdfPCell companyHeader = new PdfPCell(new Paragraph("CompanyName",fontBold));

           // cell.setColspan(3);
            companyHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
           // cell.setPadding(10.0f);
            //cell.setBackgroundColor(new BaseColor(140, 221, 8));
            companyHeader.setBackgroundColor(BaseColor.GRAY);
            
            PdfPCell desigHeader = new PdfPCell(new Paragraph("Designation",fontBold));

           // cell.setColspan(3);
            desigHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
           // cell.setPadding(10.0f);
            //cell.setBackgroundColor(new BaseColor(140, 221, 8));
            desigHeader.setBackgroundColor(BaseColor.GRAY);
            
               PdfPCell fromHeader = new PdfPCell(new Paragraph("FromDate",fontBold));

           // cell.setColspan(3);
            fromHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
           // cell.setPadding(10.0f);
            //cell.setBackgroundColor(new BaseColor(140, 221, 8));
            fromHeader.setBackgroundColor(BaseColor.GRAY);
            
             PdfPCell toHeader = new PdfPCell(new Paragraph("ToDate",fontBold));

           // cell.setColspan(3);
            toHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
           // cell.setPadding(10.0f);
            //cell.setBackgroundColor(new BaseColor(140, 221, 8));
            toHeader.setBackgroundColor(BaseColor.GRAY);
            
            PdfPCell locationHeader = new PdfPCell(new Paragraph("Location",fontBold));

           // cell.setColspan(3);
            locationHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
           // cell.setPadding(10.0f);
            //cell.setBackgroundColor(new BaseColor(140, 221, 8));
            locationHeader.setBackgroundColor(BaseColor.GRAY);
            
            experienceTable.addCell(companyHeader);
            experienceTable.addCell(desigHeader);
            experienceTable.addCell(fromHeader);
            experienceTable.addCell(toHeader);
            experienceTable.addCell(locationHeader);
            
            
             if(resultSet!=null) {
                 resultSet.close();
                 resultSet = null;
             }
               if(preparedStatement!=null) {
                 preparedStatement.close();
                 preparedStatement = null;
             }
                 if(connection!=null) {
                 connection.close();
                 connection = null;
             }
             // con = DriverManager.getConnection("jdbc:mysql://172.17.4.79/mirage", "AppAdmin", "lokam001");
             connection = ConnectionProvider.getInstance().getConnection();
              preparedStatement = connection.prepareStatement("select CompanyName,Designation,Fromdate,ToDate,Location from tblCreExperience where CreId="+creId+" ORDER BY ID DESC");
             resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                 PdfPCell companyCell = new PdfPCell(new Paragraph(resultSet.getString("CompanyName"),font3));
              companyCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                document.add(companyCell);
                 experienceTable.addCell(companyCell);
                 
                 PdfPCell desigCell = new PdfPCell(new Paragraph(resultSet.getString("Designation"),font3));
              desigCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                document.add(desigCell);
                 experienceTable.addCell(desigCell);
                 PdfPCell fromCell = null;
                 if(!"".equals(resultSet.getString("Fromdate"))){
                    fromCell = new PdfPCell(new Paragraph(resultSet.getString("Fromdate").split(" ")[0],font3)); 
                 }else {
                      fromCell = new PdfPCell(new Paragraph("",font3)); 
                 }
                 
              fromCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                document.add(fromCell);
                 experienceTable.addCell(fromCell);
                 
                   PdfPCell toCell = null;
                   if(!"".equals(resultSet.getString("ToDate"))){
                       toCell = new PdfPCell(new Paragraph(resultSet.getString("ToDate").split(" ")[0],font3));
                   }else {
                       toCell = new PdfPCell(new Paragraph("",font3));
                   }
              toCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                document.add(toCell);
                 experienceTable.addCell(toCell);
                 
                   PdfPCell locationCell = new PdfPCell(new Paragraph(resultSet.getString("Location"),font3));
              locationCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                document.add(locationCell);
                 experienceTable.addCell(locationCell);
            }
            experienceTable.setSpacingAfter(10.0f);
 document.add(experienceTable);
                    //Opened new page
            
            
            
               document.add(Chunk.NEWLINE);
            
          
              PdfPTable acheivementTable = new PdfPTable(5);
              
               PdfPCell acheiveCell = new PdfPCell(new Paragraph("Acheivements",headingFont));
               acheiveCell.setColspan(5);
               acheiveCell.setRowspan(2);
               acheiveCell.setBorder(0);
              acheiveCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                document.add(acheiveCell);
                 acheivementTable.addCell(acheiveCell);
                 
                 
                     PdfPCell acheiveConentCell = null;
                     if(!"".equals(achievements)){
                         acheiveConentCell = new PdfPCell(new Paragraph(achievements,font3));
                     }else {
                         acheiveConentCell = new PdfPCell(new Paragraph("\n\n\n",font3));
                     }
               acheiveConentCell.setColspan(5);
               acheiveConentCell.setRowspan(2);
             //  acheiveConentCell.setBorder(0);
              acheiveCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                document.add(acheiveConentCell);
                 acheivementTable.addCell(acheiveConentCell);
                 
                 acheivementTable.setSpacingAfter(10.0f);
                  document.add(acheivementTable);
                 
            // document.newPage();   
            
            
            //document.add(list);            //In the new page we are going to add list

                  
                  //----------------------------------
                  
                    document.add(Chunk.NEWLINE);
                    
                  PdfPTable testTable = new PdfPTable(8);
            // experienceTable.setWidthPercentage(50);

             PdfPCell testHeader = new PdfPCell(new Paragraph("Test Marks",headingFont));

            testHeader.setColspan(8);
            testHeader.setBorder(0);
            testHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
           // cell.setPadding(10.0f);
            //cell.setBackgroundColor(new BaseColor(140, 221, 8));
           // expHeader.setBackgroundColor(BaseColor.GRAY);
            testTable.addCell(testHeader);
            
                  
                   PdfPCell snoHeader = new PdfPCell(new Paragraph("SL.NO",fontBold));
                    snoHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
                    snoHeader.setBackgroundColor(BaseColor.GRAY);
                    testTable.addCell(snoHeader);
                  
                    PdfPCell creNoHeader = new PdfPCell(new Paragraph("CRE NO",fontBold));
                    creNoHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
                    creNoHeader.setBackgroundColor(BaseColor.GRAY);
                    testTable.addCell(creNoHeader);
                  
                    PdfPCell firstNameHeader = new PdfPCell(new Paragraph("First Name",fontBold));
                    firstNameHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
                    firstNameHeader.setBackgroundColor(BaseColor.GRAY);
                    testTable.addCell(firstNameHeader);
                    
                      PdfPCell lastNameHeader = new PdfPCell(new Paragraph("Last Name",fontBold));
                    lastNameHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
                    lastNameHeader.setBackgroundColor(BaseColor.GRAY);
                    testTable.addCell(lastNameHeader);
                    
                     PdfPCell testDateHeader = new PdfPCell(new Paragraph("Test Date",fontBold));
                    testDateHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
                    testDateHeader.setBackgroundColor(BaseColor.GRAY);
                    testTable.addCell(testDateHeader);
                    
                      PdfPCell testNameHeader = new PdfPCell(new Paragraph("Test Name",fontBold));
                    testNameHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
                    testNameHeader.setBackgroundColor(BaseColor.GRAY);
                    testTable.addCell(testNameHeader);
                    
                        PdfPCell scoreHeader = new PdfPCell(new Paragraph("Score",fontBold));
                    scoreHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
                    scoreHeader.setBackgroundColor(BaseColor.GRAY);
                    testTable.addCell(scoreHeader);
                    
                      PdfPCell statusHeader = new PdfPCell(new Paragraph("Status",fontBold));
                    statusHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
                    statusHeader.setBackgroundColor(BaseColor.GRAY);
                    testTable.addCell(statusHeader);
                  
                  if(resultSet!=null) {
                 resultSet.close();
                 resultSet = null;
             }
               if(preparedStatement!=null) {
                 preparedStatement.close();
                 preparedStatement = null;
             }
                 if(connection!=null) {
                 connection.close();
                 connection = null;
             }
            // Connection connection = DriverManager.getConnection("jdbc:mysql://172.17.4.79/mirage", "AppAdmin", "lokam001");
             //PreparedStatement preparedStatement = connection.prepareStatement("")
           //   con = DriverManager.getConnection("jdbc:mysql://172.17.4.79/mirage", "AppAdmin", "lokam001");
             connection = ConnectionProvider.getInstance().getConnection();
              preparedStatement = connection.prepareStatement("SELECT * FROM tblEcertResult WHERE tblEcertResult.EmpId = 'MSSCRE"+creId+"' ORDER BY ID DESC");
             resultSet = preparedStatement.executeQuery();
             int i=0;
             //ExamKeyId
            while (resultSet.next()) {
                 PdfPCell snoValueCell = new PdfPCell(new Paragraph(String.valueOf(++i),font3));
              snoValueCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                document.add(snoValueCell);
                 testTable.addCell(snoValueCell);
                 
                  PdfPCell creNoCell = new PdfPCell(new Paragraph(resultSet.getString("EmpId"),font3));
              creNoCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                document.add(creNoCell);
                 testTable.addCell(creNoCell);
                 
                //  firstName = result.getString("FName");
                //lastName = result.getString("LName");
                 
                 
                  PdfPCell creFnameCell = new PdfPCell(new Paragraph(firstName,font3));
              creFnameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                document.add(creFnameCell);
                 testTable.addCell(creFnameCell);
            
                  PdfPCell crelnameCell = new PdfPCell(new Paragraph(lastName,font3));
              crelnameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                document.add(crelnameCell);
                 testTable.addCell(crelnameCell);
                 
                    PdfPCell testDateCell = null;
                    if(!"".equals(resultSet.getString("DateSubmitted"))){
                       testDateCell = new PdfPCell(new Paragraph(resultSet.getString("DateSubmitted").split(" ")[0],font3)); 
                    }else {
                        testDateCell = new PdfPCell(new Paragraph("",font3)); 
                    }
              testDateCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                document.add(testDateCell);
                 testTable.addCell(testDateCell);
                 String testName = getSubTopicNames(resultSet.getInt("ExamKeyId"));
                  // PdfPCell testNameCell = new PdfPCell(new Paragraph("TestName1",font3));
                  PdfPCell testNameCell = new PdfPCell(new Paragraph(testName,font3));
              testNameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                document.add(testNameCell);
                 testTable.addCell(testNameCell);
                 double testScore = Math.round((resultSet.getDouble("Marks")/resultSet.getDouble("TotalQuestions"))*100.0f);
                 
                 //System.out.println("testScore-->"+testScore);
                 // PdfPCell scoreCell = new PdfPCell(new Paragraph(String.valueOf((resultSet.getDouble("Marks")/resultSet.getDouble("TotalQuestions"))*100.0f)+"%",font3));
                  PdfPCell scoreCell = new PdfPCell(new Paragraph(String.valueOf(testScore)+" %",font3));
              scoreCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                document.add(scoreCell);
                 testTable.addCell(scoreCell);
                 
                  PdfPCell statusCell = new PdfPCell(new Paragraph(resultSet.getString("ExamStatus"),font3));
              statusCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                document.add(statusCell);
                 testTable.addCell(statusCell);
                 
                 
            
            }
                    testTable.setSpacingAfter(10.0f);
                      document.add(testTable);
                    
                  
                      
                      //----------------------
                      
                  //    document.add(Chunk.NEWLINE);
                    //  document.add(Chunk.NEWLINE);
                    //  document.add(Chunk.NEWLINE);
                      
                     // document.add(Chunk.NEWLINE);
            
          
              PdfPTable reviewTable = new PdfPTable(5);
              
               PdfPCell reviewHeader = new PdfPCell(new Paragraph("Tech Comments",headingFont));
               reviewHeader.setColspan(5);
               reviewHeader.setRowspan(2);
               reviewHeader.setBorder(0);
              reviewHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
                document.add(reviewHeader);
                 reviewTable.addCell(reviewHeader);
                 if(resultSet!=null) {
                 resultSet.close();
                 resultSet = null;
             }
               if(preparedStatement!=null) {
                 preparedStatement.close();
                 preparedStatement = null;
             }
                 if(connection!=null) {
                 connection.close();
                 connection = null;
             }
             // con = DriverManager.getConnection("jdbc:mysql://172.17.4.79/mirage", "AppAdmin", "lokam001");
             connection = ConnectionProvider.getInstance().getConnection();
              preparedStatement = connection.prepareStatement("SELECT * FROM tblCreTchComments WHERE CreId = "+creId+" ORDER BY ID DESC");
             resultSet = preparedStatement.executeQuery();
            // int i=0;
             String techComments = "";
            while (resultSet.next()) {
            techComments =techComments+" "+ resultSet.getString("Comments");
            }
            
            
                  if(resultSet!=null) {
                 resultSet.close();
                 resultSet = null;
             }
               if(preparedStatement!=null) {
                 preparedStatement.close();
                 preparedStatement = null;
             }
                 if(connection!=null) {
                 connection.close();
                 connection = null;
             }
             // con = DriverManager.getConnection("jdbc:mysql://172.17.4.79/mirage", "AppAdmin", "lokam001");
             connection = ConnectionProvider.getInstance().getConnection();
              preparedStatement = connection.prepareStatement("SELECT * FROM tblCreHrComments WHERE CreId = "+creId+" ORDER BY ID DESC");
             resultSet = preparedStatement.executeQuery();
            // int i=0;
             String hrComments = "";
            while (resultSet.next()) {
            hrComments =hrComments+" "+ resultSet.getString("Comments");
            }
            
            
             if(resultSet!=null) {
                 resultSet.close();
                 resultSet = null;
             }
               if(preparedStatement!=null) {
                 preparedStatement.close();
                 preparedStatement = null;
             }
                 if(connection!=null) {
                 connection.close();
                 connection = null;
             }
            //  con = DriverManager.getConnection("jdbc:mysql://172.17.4.79/mirage", "AppAdmin", "lokam001");
             connection = ConnectionProvider.getInstance().getConnection();
              preparedStatement = connection.prepareStatement("SELECT * FROM tblCreVPComments WHERE CreId = "+creId+" ORDER BY ID DESC");
             resultSet = preparedStatement.executeQuery();
            // int i=0;
             String vpComments = "";
            while (resultSet.next()) {
            vpComments =vpComments+" "+ resultSet.getString("Comments");
            }
            if(resultSet!=null) {
                 resultSet.close();
                 resultSet = null;
             }
               if(preparedStatement!=null) {
                 preparedStatement.close();
                 preparedStatement = null;
             }
                 if(connection!=null) {
                 connection.close();
                 connection = null;
             }
            
             connection = ConnectionProvider.getInstance().getConnection();
              preparedStatement = connection.prepareStatement("SELECT * FROM tblCreOnBoardComments WHERE CreId = "+creId+" ORDER BY ID DESC");
             resultSet = preparedStatement.executeQuery();
            // int i=0;
             String onboardComments = "";
            while (resultSet.next()) {
            onboardComments =onboardComments+" "+ resultSet.getString("Comments");
            }
            if(resultSet!=null) {
                 resultSet.close();
                 resultSet = null;
             }
               if(preparedStatement!=null) {
                 preparedStatement.close();
                 preparedStatement = null;
             }
                 if(connection!=null) {
                 connection.close();
                 connection = null;
             }
                 
                 
            //Paragraph techComm = new Paragraph(techComments,font3);
            PdfPCell techCell =null;
            if(!"".equals(techComments)) {
                techCell = new PdfPCell(new Paragraph(techComments,font3));
            }else {
                techCell = new PdfPCell(new Paragraph("\n\n\n",font3));
            }
            
            
                      // PdfPCell techCell = new PdfPCell(new Paragraph(techComments,font3));
                       
                       
                    techCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                   // techCell.setBackgroundColor(BaseColor.GRAY);
                    techCell.setColspan(5);
               techCell.setRowspan(2);
                document.add(techCell);
                    reviewTable.addCell(techCell);
                     //testTable.setSpacingAfter(30.0f);
                        document.add(reviewTable);
                     document.add(Chunk.NEWLINE);
                      PdfPTable hrreviewTable = new PdfPTable(5);
                    
                      
                        PdfPCell hrHeader = new PdfPCell(new Paragraph("Hr Comments",headingFont));
               hrHeader.setColspan(5);
               hrHeader.setRowspan(2);
               hrHeader.setBorder(0);
              hrHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
                document.add(hrHeader);
                 hrreviewTable.addCell(hrHeader);
                      
                      
                       PdfPCell hrCell = null;
                       
                       if(!"".equals(hrComments)) {
                           hrCell = new PdfPCell(new Paragraph(hrComments,font3));
                       }else {
                           hrCell = new PdfPCell(new Paragraph("\n\n\n",font3));
                       }
                    hrCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    //hrCell.setBackgroundColor(BaseColor.GRAY);
                     hrCell.setColspan(5);
               hrCell.setRowspan(2);
                document.add(hrCell);
                    hrreviewTable.addCell(hrCell);
                    document.add(hrreviewTable);
                     document.add(Chunk.NEWLINE);
                       PdfPTable vpreviewTable = new PdfPTable(5);
                       
                       PdfPCell vpHeader = new PdfPCell(new Paragraph("VP Comments",headingFont));
               vpHeader.setColspan(5);
               vpHeader.setRowspan(2);
               vpHeader.setBorder(0);
              vpHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
                document.add(vpHeader);
                 vpreviewTable.addCell(vpHeader);
                 
                    PdfPCell vpCell = null;
                    if(!"".equals(vpComments)){
                         vpCell = new PdfPCell(new Paragraph(vpComments,font3));
                    }else {
                        vpCell = new PdfPCell(new Paragraph("\n\n\n",font3));
                    }
                    vpCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                   // vpCell.setBackgroundColor(BaseColor.GRAY);
                     vpCell.setColspan(5);
               vpCell.setRowspan(2);
               document.add(vpCell);
                    vpreviewTable.addCell(vpCell);
                      vpreviewTable.setSpacingAfter(30.0f);
                      document.add(vpreviewTable);
                      
                      
                      //----------------------------------
                      
                       PdfPTable onBoardingCommTable = new PdfPTable(5);
              
               PdfPCell onbordHeader = new PdfPCell(new Paragraph("On Boarding Comments",headingFont));
               onbordHeader.setColspan(5);
               //reviewHeader.setRowspan(2);
               onbordHeader.setBorder(0);
              onbordHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
                document.add(onbordHeader);
                 onBoardingCommTable.addCell(onbordHeader);
                      
                      
                       PdfPCell onbordCommentsBox = null;
                       if(!"".equals(onboardComments)){
                           onbordCommentsBox = new PdfPCell(new Paragraph(onboardComments,font3));
                       }else {
                           onbordCommentsBox = new PdfPCell(new Paragraph("\n\n\n",headingFont));
                       }
                       //PdfPCell onbordCommentsBox = new PdfPCell(new Paragraph("\n\n\n",headingFont));
               onbordCommentsBox.setColspan(5);
               //reviewHeader.setRowspan(2);
               //onbordHeader.setBorder(0);
              onbordCommentsBox.setHorizontalAlignment(Element.ALIGN_LEFT);
                document.add(onbordCommentsBox);
                 onBoardingCommTable.addCell(onbordCommentsBox);
                      
                  document.add(onBoardingCommTable);
                      
                      
                      
                      
            document.close();
            
            
            
            file.flush();
            file.close();

          //  System.out.println("Pdf created successfully..");
     if(resultSet!=null) {
                 resultSet.close();
                 resultSet = null;
             }
               if(preparedStatement!=null) {
                 preparedStatement.close();
                 preparedStatement = null;
             }
                 if(connection!=null) {
                 connection.close();
                 connection = null;
             }
        } 
  
        catch (Exception e) {
            e.printStackTrace();
        }
        return fileLocation;
    }
    public  String getSubTopicNames(int examKeyId) {
        Connection connection =null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String testName = "";
        try {
           //  connection = DriverManager.getConnection("jdbc:mysql://172.17.4.79/mirage", "AppAdmin", "lokam001");
            connection = ConnectionProvider.getInstance().getConnection();
              preparedStatement = connection.prepareStatement("SELECT DISTINCT SubTopicName FROM tblEcertSummary,tblEcertSubTopics  WHERE tblEcertSummary.SubtopicId = tblEcertSubTopics.Id AND tblEcertSummary.ExamKeyId = "+examKeyId);
             resultSet = preparedStatement.executeQuery();
             
             while(resultSet.next()) {
                 testName = testName+","+resultSet.getString("SubTopicName");
             }
             if(!"".equals(testName)){
                 testName = testName.substring(1,testName.length());
             }
        }catch(Exception exception) {
            exception.printStackTrace();
        }finally {
            try {
             if(resultSet!=null) {
                 resultSet.close();
                 resultSet = null;
             }
               if(preparedStatement!=null) {
                 preparedStatement.close();
                 preparedStatement = null;
             }
                 if(connection!=null) {
                 connection.close();
                 connection = null;
             }
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
         return testName;
    }

  

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
       this.httpServletRequest = httpServletRequest;
    }

    /**
     * @return the creId
     */
    public int getCreId() {
        return creId;
    }

    /**
     * @param creId the creId to set
     */
    public void setCreId(int creId) {
        this.creId = creId;
    }

    /**
     * @return the downloadType
     */
    public String getDownloadType() {
        return downloadType;
    }

    /**
     * @param downloadType the downloadType to set
     */
    public void setDownloadType(String downloadType) {
        this.downloadType = downloadType;
    }

   
}
