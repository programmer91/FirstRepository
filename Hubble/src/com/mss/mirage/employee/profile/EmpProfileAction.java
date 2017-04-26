/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :   April 1, 2008, 4:39 PM
 *
 * Author  :  Hari Krishna Kondala <hkondala@miraclesoft.com>
 *
 * File    : EmpImageAction .java
 *
 * Copyright 2008 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.employee.profile;

import com.mss.mirage.employee.general.EmployeeService;
import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.AuthorizationManager;
import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.Properties;
import com.mss.mirage.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;
import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import java.sql.Blob;
/**
 *
 * @author miracle
 */
public class EmpProfileAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
    
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private EmpProfileVTO currentEmployee;
    private File imagePath = null;
    private String skillSet = null;
    private String resultType = null;
    private int userRoleId;
    private int userId;
    private String resultMessage="";
    
    /** The firstName is used for storing firstName of employee. */
    //private String firstName;
    
    /** The lastName is used for storing lastName of employee. */
    //private String lastName;
    
    /** The middleName is used for storing middleName of employee. */
    //private String middleName;
    
    /** The aliasName is used for storing aliasName of employee. */
    private String aliasName;
       
     /** The gender is used for storing aliasName of employee. */
    private String gender;
    
    /** The maritalStatus is used for storing maritalStatus of employee. */
    private String maritalStatus;
    
    /** The country is used for storing maritalStatus of employee. */
    private String country;
           
    /** The birthDate is used for storing dateOfBirth of employee. */
    private Date birthDate;
    
    /** The offBirthDate is used for storing official date of birth of employee. */
    private Date offBirthDate;
        
    /** The anniversaryDate is used for storing anniversaryDate of employee.*/
    private Date anniversaryDate;
    
    /** The workPhoneNo is used for storing wormPhoneNo of employee. */
    private String workPhoneNo;
    
    /** The altPhoneNo is used for storing alternatePhoneNo of employee. */
    private String altPhoneNo;
    
    /** The homePhoneNo is used for storing homePhoneNo of employee. */
    private String homePhoneNo;
    
    /** The cellPhoneNo is used for storing cellPhoneNo of employee. */
    private String cellPhoneNo;   
    
    /** The hotelPhoneNo is used for storing hotelPhoneNo of employee. */
    private String hotelPhoneNo;
    
    /** The personalEmail is used for storing personal Email Of employee. */
    private String personalEmail;
    
    /** The indiaPhoneNo is used for storing indiaPhoneNo of employee. */
    private String indiaPhoneNo;
    
    /** The otherEmail is used for storing other Email of employee. */
    private String otherEmail;
    
    /** The faxNo is used for storing Fax of employee. */
    private String faxNo;
    
    private String empId; 
    
      //New Fileds For Confedential Info Date : 08/19/2014
     private String bankName;
private String accNum;
private String nameAsPerAcc;
private String ifscCode;
private String phyChallenged;
private String phyCategory;
private String aadharNum;
private String aadharName;
private String nameAsPerPan;
private String empno;
private String ssn;

private String nsrno;
private String itgBatch;
//new

private Date passportExp;
private String passportNo;
private String uanNo;
private String pfno;
private String reportsTo;
private String operationContact;
private String aboutMe;
    /**
     * Creates a new instance of EmpProfileAction
     */
    public EmpProfileAction() {
    }
    
    public String execute() throws FileNotFoundException,ServletException{
        
        InputStream imageFile = new FileInputStream(getImagePath());
        Connection connection = null;
        PreparedStatement statement = null;
        String fileName="";
        try {
            
            connection = ConnectionProvider.getInstance().getConnection();
            String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();  
             String basePath = Properties.getProperty("EmpImages.path");
                    File createPath = new File(basePath);
                    System.out.println("createPath..." + createPath);
                    File newFile = new File(createPath.getAbsolutePath() + "//" + loginId + ".png");
                  //  newFile.mkdirs();
                    String filepath = newFile.toString();
                    fileName = loginId + ".png";
                   // System.out.println("filepath--->" + filepath);
                   // System.out.println("fileName--->" + fileName);
                    OutputStream fileOutputStream = new FileOutputStream(
                                        newFile);
                    int bufferSize;
                        byte[] bufffer = new byte[512];
                        while ((bufferSize = imageFile.read(bufffer)) > 0) {
                                fileOutputStream.write(bufffer, 0, bufferSize);
                        }
                      String ImageDetails = Properties.getProperty("EmpImages.path").trim() + "/" + loginId + ".png";
                         statement = connection.prepareStatement("update tblEmployee SET FileName = ? , FilePath = ? where LoginId=?");
            statement.setString(1, fileName);
             statement.setString(2, ImageDetails);
             String lid = loginId;
            statement.setString(3, lid);
             int update = statement.executeUpdate();
                        fileOutputStream.close();

            getSkills();
            if(update==1){
                resultMessage = "Employee Image Updated Successfully!";
            }else{
                resultMessage = "Please Try again!";
            }
            httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
           // resultType = SUCCESS;
          
            resultType = SUCCESS;
            
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex);
            resultType=ERROR;
        }finally {
            try{
                if(statement!=null){
                    statement.close();
                    statement=null;
                }
                if(connection!=null){
                    connection.close();
                    connection=null;
                }if(imageFile != null) {
                imageFile.close();
                imageFile = null;
                }
            }catch(SQLException sqle){
                httpServletRequest.getSession(false).setAttribute("errorMessage",sqle);
            }catch(IOException ioe){
                httpServletRequest.getSession(false).setAttribute("errorMessage",ioe);
            }
        }
        return resultType;
    }
    
    public String getSkills(){
        resultType = LOGIN;
        
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userId = Integer.parseInt(httpServletRequest.getSession().getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
            userRoleId = Integer.parseInt(httpServletRequest.getSession().getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("GET_SKILLSET",userRoleId)) {
                try{
                    setCurrentEmployee(ServiceLocator.getEmpProfileService().getSkills(userId));
                    resultType = SUCCESS;
                } catch(Exception ex) {
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }
        return resultType;
    }
    
    public String doEdit() {
        resultType = LOGIN;
        int updatedRows;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userId = Integer.parseInt(httpServletRequest.getSession().getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
            userRoleId = Integer.parseInt(httpServletRequest.getSession().getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("EDIT_SKILLSET",userRoleId)) {
                try{
                    updatedRows = ServiceLocator.getEmpProfileService().updateSkills(this,userId);
                    if(updatedRows==1){
                        resultMessage = "Employee SkillSet Updated Successfully!";
                    }else{
                        resultMessage = "Please Try again!";
                    }
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
                    getSkills();
                    resultType = SUCCESS;
                } catch(Exception ex) {
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }
        return resultType;
    }
    
    public void renderImage() {
       byte[] image = null;
     
         File imageFile = null;
        try {
             

           String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();  
         //  System.out.println("createPath..." + loginId);

String basePath = Properties.getProperty("EmpImages.path");
            File createPath = new File(basePath);
                   // System.out.println("createPath..." + createPath);
                    
          //   System.out.println("......."+imageFile);

              imageFile = new File(createPath.getAbsolutePath() + "//" + loginId + ".png");
           //  System.out.println("......."+imageFile);
            if (!imageFile.exists()) {
                       imageFile = new File(Properties.getProperty("No.Image"));
              
            }
               //   File 
                  InputStream is = new FileInputStream(imageFile);

//                Get the size of the file
                  long length = imageFile.length();

//                Create the byte array to hold the data
                  //byte[] bytes = new byte[(int)length];
                  image = new byte[(int)length];

//                Read in the bytes
                  int offset = 0;
                  int numRead = 0;
                  while (offset < image.length && (numRead=is.read(image, offset, image.length-offset)) >= 0) {
                    offset += numRead;
                  }

//                Ensure all the bytes have been read in
                  if (offset < image.length) {
                    throw new IOException("Could not completely read file ");
                  }
   httpServletResponse.getOutputStream().write(image);

//                Close the input stream and return bytes                                
                  is.close();
                  httpServletResponse.getOutputStream().close();
        //    }

        }catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex);
            resultType=ERROR;
        }
      //  return SUCCESS;
    }

    public String updateProfile() {
       
        resultType = LOGIN;
        int updatedRows;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userId = Integer.parseInt(httpServletRequest.getSession().getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
            userRoleId = Integer.parseInt(httpServletRequest.getSession().getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("EDIT_PROFILE",userRoleId)) {
                try {
                    updatedRows = ServiceLocator.getEmpProfileService().updateEmpProfile(this,userId);
                    if(updatedRows==1){
                        resultMessage = "Employee Profile Updated Successfully!";
                    }else{
                        resultMessage = "Please Try again!";
                    }
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
                    getSkills();
                    resultType = SUCCESS;
                } catch(Exception ex) {
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }
        return resultType;
    }
    
    public File getImagePath() {
        return imagePath;
    }
    
    public void setImagePath(File imagePath) {
        this.imagePath = imagePath;
    }
    
    
    public String getSkillSet() {
        return skillSet;
    }
    
    public void setSkillSet(String skillSet) {
        this.skillSet = skillSet;
    }
    
    public EmpProfileVTO getCurrentEmployee() {
        return currentEmployee;
    }
    
    public void setCurrentEmployee(EmpProfileVTO currentEmployee) {
        this.currentEmployee = currentEmployee;
    }
    
    
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    /*public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }*/

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getOffBirthDate() {
        return offBirthDate;
    }

    public void setOffBirthDate(Date offBirthDate) {
        this.offBirthDate = offBirthDate;
    }

    public Date getAnniversaryDate() {
        return anniversaryDate;
    }

    public void setAnniversaryDate(Date anniversaryDate) {
        this.anniversaryDate = anniversaryDate;
    }

    public String getWorkPhoneNo() {
        return workPhoneNo;
    }

    public void setWorkPhoneNo(String workPhoneNo) {
        this.workPhoneNo = workPhoneNo;
    }

    public String getAltPhoneNo() {
        return altPhoneNo;
    }

    public void setAltPhoneNo(String altPhoneNo) {
        this.altPhoneNo = altPhoneNo;
    }

    public String getHomePhoneNo() {
        return homePhoneNo;
    }

    public void setHomePhoneNo(String homePhoneNo) {
        this.homePhoneNo = homePhoneNo;
    }

    public String getCellPhoneNo() {
        return cellPhoneNo;
    }

    public void setCellPhoneNo(String cellPhoneNo) {
        this.cellPhoneNo = cellPhoneNo;
    }

    public String getHotelPhoneNo() {
        return hotelPhoneNo;
    }

    public void setHotelPhoneNo(String hotelPhoneNo) {
        this.hotelPhoneNo = hotelPhoneNo;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public String getIndiaPhoneNo() {
        return indiaPhoneNo;
    }

    public void setIndiaPhoneNo(String indiaPhoneNo) {
        this.indiaPhoneNo = indiaPhoneNo;
    }

    public String getOtherEmail() {
        return otherEmail;
    }

    public void setOtherEmail(String otherEmail) {
        this.otherEmail = otherEmail;
    }

    public String getFaxNo() {
        return faxNo;
    }

    public void setFaxNo(String faxNo) {
        this.faxNo = faxNo;
    }

    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    /**
     * @return the bankName
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * @param bankName the bankName to set
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /**
     * @return the accNum
     */
    public String getAccNum() {
        return accNum;
    }

    /**
     * @param accNum the accNum to set
     */
    public void setAccNum(String accNum) {
        this.accNum = accNum;
    }

    /**
     * @return the nameAsPerAcc
     */
    public String getNameAsPerAcc() {
        return nameAsPerAcc;
    }

    /**
     * @param nameAsPerAcc the nameAsPerAcc to set
     */
    public void setNameAsPerAcc(String nameAsPerAcc) {
        this.nameAsPerAcc = nameAsPerAcc;
    }

    /**
     * @return the ifscCode
     */
    public String getIfscCode() {
        return ifscCode;
    }

    /**
     * @param ifscCode the ifscCode to set
     */
    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    /**
     * @return the phyChallenged
     */
    public String getPhyChallenged() {
        return phyChallenged;
    }

    /**
     * @param phyChallenged the phyChallenged to set
     */
    public void setPhyChallenged(String phyChallenged) {
        this.phyChallenged = phyChallenged;
    }

    /**
     * @return the phyCategory
     */
    public String getPhyCategory() {
        return phyCategory;
    }

    /**
     * @param phyCategory the phyCategory to set
     */
    public void setPhyCategory(String phyCategory) {
        this.phyCategory = phyCategory;
    }

    /**
     * @return the aadharNum
     */
    public String getAadharNum() {
        return aadharNum;
    }

    /**
     * @param aadharNum the aadharNum to set
     */
    public void setAadharNum(String aadharNum) {
        this.aadharNum = aadharNum;
    }

    /**
     * @return the aadharName
     */
    public String getAadharName() {
        return aadharName;
    }

    /**
     * @param aadharName the aadharName to set
     */
    public void setAadharName(String aadharName) {
        this.aadharName = aadharName;
    }

    /**
     * @return the nameAsPerPan
     */
    public String getNameAsPerPan() {
        return nameAsPerPan;
    }

    /**
     * @param nameAsPerPan the nameAsPerPan to set
     */
    public void setNameAsPerPan(String nameAsPerPan) {
        this.nameAsPerPan = nameAsPerPan;
    }

    /**
     * @return the empno
     */
    public String getEmpno() {
        return empno;
    }

    /**
     * @param empno the empno to set
     */
    public void setEmpno(String empno) {
        this.empno = empno;
    }

    /**
     * @return the ssn
     */
    public String getSsn() {
        return ssn;
    }

    /**
     * @param ssn the ssn to set
     */
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    /**
     * @return the nsrno
     */
    public String getNsrno() {
        return nsrno;
    }

    /**
     * @param nsrno the nsrno to set
     */
    public void setNsrno(String nsrno) {
        this.nsrno = nsrno;
    }

    /**
     * @return the itgBatch
     */
    public String getItgBatch() {
        return itgBatch;
    }

    /**
     * @param itgBatch the itgBatch to set
     */
    public void setItgBatch(String itgBatch) {
        this.itgBatch = itgBatch;
    }

    public Date getPassportExp() {
        return passportExp;
    }

    public void setPassportExp(Date passportExp) {
        this.passportExp = passportExp;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    /**
     * @return the uanNo
     */
    public String getUanNo() {
        return uanNo;
    }

    /**
     * @param uanNo the uanNo to set
     */
    public void setUanNo(String uanNo) {
        this.uanNo = uanNo;
    }

    /**
     * @return the pfno
     */
    public String getPfno() {
        return pfno;
    }

    /**
     * @param pfno the pfno to set
     */
    public void setPfno(String pfno) {
        this.pfno = pfno;
    }

    /**
     * @return the reportsTo
     */
    public String getReportsTo() {
        return reportsTo;
    }

    /**
     * @param reportsTo the reportsTo to set
     */
    public void setReportsTo(String reportsTo) {
        this.reportsTo = reportsTo;
    }

    /**
     * @return the operationContact
     */
    public String getOperationContact() {
        return operationContact;
    }

    /**
     * @param operationContact the operationContact to set
     */
    public void setOperationContact(String operationContact) {
        this.operationContact = operationContact;
    }

    /**
     * @return the aboutMe
     */
    public String getAboutMe() {
        return aboutMe;
    }

    /**
     * @param aboutMe the aboutMe to set
     */
    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }
    
    
}
