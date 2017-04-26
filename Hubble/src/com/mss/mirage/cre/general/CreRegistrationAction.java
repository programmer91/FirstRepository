/*
 * CreRegistrationAction.java
 *
 * Created on August 28, 2013, 7:54 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.cre.general;
import com.mss.mirage.util.MailManager;
import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.HibernateDataProvider;
import com.mss.mirage.util.DefaultDataProvider;
import com.mss.mirage.util.PasswordUtility;
import com.mss.mirage.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.Random;
import com.mss.mirage.util.HibernateServiceLocator;
import com.mss.mirage.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
/**
 *
 * @author miracle
 */
public class CreRegistrationAction extends ActionSupport implements ServletRequestAware{
    
      /*@param resultMessage to store message and that will display in login page*/
    private String resultMessage;
    
    /*@param httpServletRequest to set resultMessage and that will get in login page*/
    private HttpServletRequest httpServletRequest;
    
    /*@param resultType to store type of results means login or success or failure etc...*/
    private String resultType;
    
    /*These are all persional details of the employee*/
    
    /*@param firstName to store firstname of the user*/
    private String firstName;
    
    /*@param lastName to store lastname of the user*/
    private String lastName;
    
    /*@param middleName to store middlename of the user*/
    private String middleName;
    
    /*@param gender to store gender of the user*/
    private String gender;
    
    /*@param maritalStatus to store  maritalstatus of the user*/
    private String maritalStatus;
    
    /*@param ssn to store the ssn of the user*/
    private String ssn;
    
    /*@param country to store the country of the user*/
    private String country;
    
    /*@param birthDate to store the date of birth of user*/
    private Date birthDate;
    
    /*@param offBirthDate to store the official date of birth*/
    private Date offBirthDate;
    
    /*@param refferedBy to store the name of the refferenc persion*/
    private String refferedBy;
    
    /*these all are contact details*/
    
    /*@param workPhoneNo to store work phone no of the user*/
    private String workPhoneNo;
    
    /*@param cellPhoneNo to store mobile number of the user*/
    private String cellPhoneNo;
    
    /*@param homePhoneNo to store home phone no of the user*/
    private String homePhoneNo;
    
    /*@param altPhoneNo to store alternative phone number of the user*/
    private String altPhoneNo;
    
    /*@param hotelPhoneNo to store hotel phone no of the user*/
    private String hotelPhoneNo;
    
    /*@param indiaPhoneNo to store india phone no of the user*/
    private String indiaPhoneNo;
    
    /*@param faxNo to store faxno of the user*/
    private String faxNo;
    
    /*@param officeEmail to store office email of the user*/
    private String officeEmail;
    
    /*@param otherEmail to store other email of the user*/
    private String otherEmail;
    
    /*@param personalEmail to store personal email of the user*/
    private String personalEmail;
    
    /*@param prefQuestion to store prefered question of the user*/
    private String prefQuestion;
    
    /*@param prefAnswer to store prefered answer  of the user*/
    private String prefAnswer;
    
    /*@param loginId to store the login id of the user*/
    private String loginId;
    
    /*@param password to store the password of the user*/
    private String password;
    
    /*@param curStatus to store the current status of the  user*/
    private String curStatus;
    private int deletedFlag;
    
    /*@param id t store id of the row*/
    private int id;
    
    
    /*@param genderList is used to store applocation level constants of gender*/
    private List genderList = new ArrayList();
    
    /*@param maritalStatusList is used to store applicationn level constants of maritalstatus valus*/
    private List maritalStatusList = new ArrayList();
    
    /*@param countryList is used to store application level constants of Country*/
    private List coutryList = new ArrayList();
    
    /*@param prefferedQuestionMap is used to store application level constatns of prefferedQuestions */
    private Map prefferedQuestionsMap;
    
    private DefaultDataProvider defaultDataProvider;
    
    private String orgId;
    
    private String workingCountry;
    //added newly
    private int attendingInterviewAt;
    private int category;
    private String pan;
    private String fatherName;
    private String fatherOccupation;
    private String fatherIncome;
    private String address;
    private String city;
    private String  pin;
    private String state;
    private String qualification;
    private String education;
    private String college;
    private String medium;
    private String yearOfPass;
    private String percentage;
    private String ipeCategory;
    private String ipeStream;
    private String ipeCollege;
    private String ipeMedium;
    private String ipeYearOfPass;
    private String ipePercentage;
    private String degreeQual;
    private String degreeBranch;
    private String degreeCollege;
    private String degreeMedium;
    private String degreeYearOfPass;
    private String degreePercentage;
    private String pgQual;
    private String pgStream;
    private String pgCollege;
    private String pgMedium;
    private String pgYearOfPass;
    private String pgPercentage;
    private String addInfo;
    private String skill1;
    private String scale1;
    private String skill2;
    private String scale2;
    private String skill3;
    private String scale3;
    private String skill4;
    private String scale4;
    private String skill5;
    private String scale5;
    private String skill6;
    private String scale6;
    private String company;
    private String designation;
    private Date fromDate;
    private Date toDate;
    private String location;
    private String company1;
    private String designation1;
    private Date fromDate1;
    private Date toDate1;
    private String location1;
    private String company2;
    private String designation2;
    private Date fromDate2;
    private Date toDate2;
    private String location2;
    private String refName;
    private String refEmail;
    private String refMobile;
    
    private int campusLocation;
    private String recLocation;
    private String jfairLocation;

            
      private File imagePath = null; 
    private List recLocationList;
     private Map postAppliedMap;
    /** Creates a new instance of CreRegistrationAction */
    
       public String prepare()throws Exception{
       
        setDefaultDataProvider(DefaultDataProvider.getInstance());
        setGenderList(getDefaultDataProvider().getGender(ApplicationConstants.GENDER_OPTIONS));
        setMaritalStatusList(getDefaultDataProvider().getMaritalStatus(ApplicationConstants.MARITAL_STATUS_OPTIONS));
        setCoutryList(HibernateDataProvider.getInstance().getContries(ApplicationConstants.COUNTRY_OPTIONS));
        setRecLocationList(DataSourceDataProvider.getInstance().getCollegeList());
        setPostAppliedMap(DataSourceDataProvider.getInstance().getCrePositions());
        return SUCCESS;
    }
 /*
 public String doRegister() throws Exception {
        setResultMessage(null);
        boolean isUserExist= ServiceLocator.getCreGeneralService().creUserCheckExist(this);
        prepare();
        if(isUserExist){
            setResultMessage("<font color=\"red\" size=\"1.5\">Oops! you have been registered.<br></font>");
             resultMessage = resultMessage+ "<font color=\"red\" size=\"1.5\">Please use the Registered ID to login.</font>";
           
            setResultType(LOGIN);
        }else{
           // setCurStatus("Registered");
           // System.out.println("getCampusLocation()-->"+getCampusLocation());
                         //   System.out.println("getRecLocation()-->"+getRecLocation());
                          //  System.out.println("getjFairLocation()-->"+getJfairLocation());
            int isAddUser =  ServiceLocator.getCreGeneralService().addCreUser(this);
            if(isAddUser > 0){
                setResultMessage("MSSCRE"+String.valueOf(isAddUser));
                setResultType(SUCCESS);
            }else{
                setResultMessage("<font color=\"red\" size=\"1.5\">Sorry! Registration Failed, Please Try Again! </font>");
                setResultType(INPUT);
            }
        }
        
        getHttpServletRequest().setAttribute("resultMessage", getResultMessage());
        return getResultType();
    }
*/
       public String doRegister() throws Exception {
        setResultMessage(null);
        boolean isUserExist= ServiceLocator.getCreGeneralService().creUserCheckExist(this);
        prepare();
        if(isUserExist){
            //<font color=\"red\" size=\"1.5\">Oops! your personal email existed in our Database.Please register with another email Id!</font>"
            //setResultMessage("<font color=\"red\" size=\"1.5\">Oops! you have been registered.</font>");
            //setResultMessage("<font color=\"red\" size=\"1.5\">Oops! your personal email existed in our Database.Please register with another email Id!</font>");
             //resultMessage = resultMessage+ "<font color=\"red\" size=\"1.5\">Please use the Registered ID to login.</font>";
           
            setResultType(LOGIN);
        }else{
           // setCurStatus("Registered");
           // System.out.println("getCampusLocation()-->"+getCampusLocation());
                         //   System.out.println("getRecLocation()-->"+getRecLocation());
                          //  System.out.println("getjFairLocation()-->"+getJfairLocation());
            int isAddUser =  ServiceLocator.getCreGeneralService().addCreUser(this);
          /*  if(isAddUser > 0){
                setResultMessage("MSSCRE"+String.valueOf(isAddUser));
                setResultType(SUCCESS);
            }*/    if(isAddUser > 0){
                if(getImagePath()!=null) {
                updateImage(isAddUser);
                }
                setResultMessage("MSSCRE"+String.valueOf(isAddUser));
                setResultType(SUCCESS);
            }else{
                setResultMessage("<font color=\"red\" size=\"1.5\">Sorry! Registration Failed, Please Try Again! </font>");
                setResultType(INPUT);
            }
        }
        
        getHttpServletRequest().setAttribute("resultMessage", getResultMessage());
        return getResultType();
    }

       
       
		  /*Method For Image uploading
        * Date : 08/11/2014
        * Author : Santosh Kola
        * Modified by : Triveni Nidra
        */
     public String updateImage(int creId) throws FileNotFoundException,ServletException{
       // System.out.println("in updateImage");
       // System.out.println("in getImagePath-->"+getImagePath());
        InputStream imageFile = new FileInputStream(getImagePath());
        String fileName = "";
//        Connection connection = null;
//        PreparedStatement statement = null;
        
        try {
            
//            connection = ConnectionProvider.getInstance().getConnection();
//            statement = connection.prepareStatement("update tblCreConsultentDetails set Image=? where Id=?");
//            statement.setBinaryStream(1,imageFile, (int) getImagePath().length());
//            
//            statement.setInt(2, creId);
//            
//            //statement.setString(2, (String) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID));
//            int update = statement.executeUpdate();
         //  System.out.println("getConsultantId()......"+creId);
             
             String basePath = Properties.getProperty("CreConsultantImages.path");
                    File createPath = new File(basePath);
                  //  System.out.println("createPath..." + createPath);
                    File newFile = new File(createPath.getAbsolutePath() + "//" + "MSSCRE"+creId+".png");
                  //  newFile.mkdirs();
                    String filepath = newFile.toString();
                    fileName = "MSSCRE"+creId+".png";
                //    System.out.println("filepath--->" + filepath);
                //    System.out.println("fileName--->" + fileName);
                    OutputStream fileOutputStream = new FileOutputStream(
                                        newFile);
                    int bufferSize;
                        byte[] bufffer = new byte[512];
                        while ((bufferSize = imageFile.read(bufffer)) > 0) {
                                fileOutputStream.write(bufffer, 0, bufferSize);
                        }
                      fileOutputStream.close();
            
          //  if(update==1){
                resultMessage = "Employee Image Updated Successfully!";
//            }else{
//                resultMessage = "Please Try again!";
//            }
            httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
           // resultType = SUCCESS;
          
            resultType = SUCCESS;
            
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex);
            resultType=ERROR;
        }finally {
            try{
                if(imageFile != null) {
                imageFile.close();
                imageFile = null;
                }
            }catch(IOException ioe){
                httpServletRequest.getSession(false).setAttribute("errorMessage",ioe);
            }
            
        }
        return resultType;
    }
    
       
       
       
    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getFirstName() {
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
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public String getRefferedBy() {
        return refferedBy;
    }

    public void setRefferedBy(String refferedBy) {
        this.refferedBy = refferedBy;
    }

    public String getWorkPhoneNo() {
        return workPhoneNo;
    }

    public void setWorkPhoneNo(String workPhoneNo) {
        this.workPhoneNo = workPhoneNo;
    }

    public String getCellPhoneNo() {
        return cellPhoneNo;
    }

    public void setCellPhoneNo(String cellPhoneNo) {
        this.cellPhoneNo = cellPhoneNo;
    }

    public String getHomePhoneNo() {
        return homePhoneNo;
    }

    public void setHomePhoneNo(String homePhoneNo) {
        this.homePhoneNo = homePhoneNo;
    }

    public String getAltPhoneNo() {
        return altPhoneNo;
    }

    public void setAltPhoneNo(String altPhoneNo) {
        this.altPhoneNo = altPhoneNo;
    }

    public String getHotelPhoneNo() {
        return hotelPhoneNo;
    }

    public void setHotelPhoneNo(String hotelPhoneNo) {
        this.hotelPhoneNo = hotelPhoneNo;
    }

    public String getIndiaPhoneNo() {
        return indiaPhoneNo;
    }

    public void setIndiaPhoneNo(String indiaPhoneNo) {
        this.indiaPhoneNo = indiaPhoneNo;
    }

    public String getFaxNo() {
        return faxNo;
    }

    public void setFaxNo(String faxNo) {
        this.faxNo = faxNo;
    }

    public String getOfficeEmail() {
        return officeEmail;
    }

    public void setOfficeEmail(String officeEmail) {
        this.officeEmail = officeEmail;
    }

    public String getOtherEmail() {
        return otherEmail;
    }

    public void setOtherEmail(String otherEmail) {
        this.otherEmail = otherEmail;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public String getPrefQuestion() {
        return prefQuestion;
    }

    public void setPrefQuestion(String prefQuestion) {
        this.prefQuestion = prefQuestion;
    }

    public String getPrefAnswer() {
        return prefAnswer;
    }

    public void setPrefAnswer(String prefAnswer) {
        this.prefAnswer = prefAnswer;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCurStatus() {
        return curStatus;
    }

    public void setCurStatus(String curStatus) {
        this.curStatus = curStatus;
    }

    public int getDeletedFlag() {
        return deletedFlag;
    }

    public void setDeletedFlag(int deletedFlag) {
        this.deletedFlag = deletedFlag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List getGenderList() {
        return genderList;
    }

    public void setGenderList(List genderList) {
        this.genderList = genderList;
    }

    public List getMaritalStatusList() {
        return maritalStatusList;
    }

    public void setMaritalStatusList(List maritalStatusList) {
        this.maritalStatusList = maritalStatusList;
    }

    public List getCoutryList() {
        return coutryList;
    }

    public void setCoutryList(List coutryList) {
        this.coutryList = coutryList;
    }

    public Map getPrefferedQuestionsMap() {
        return prefferedQuestionsMap;
    }

    public void setPrefferedQuestionsMap(Map prefferedQuestionsMap) {
        this.prefferedQuestionsMap = prefferedQuestionsMap;
    }

    public DefaultDataProvider getDefaultDataProvider() {
        return defaultDataProvider;
    }

    public void setDefaultDataProvider(DefaultDataProvider defaultDataProvider) {
        this.defaultDataProvider = defaultDataProvider;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getWorkingCountry() {
        return workingCountry;
    }

    public void setWorkingCountry(String workingCountry) {
        this.workingCountry = workingCountry;
    }

    public int getAttendingInterviewAt() {
        return attendingInterviewAt;
    }

    public void setAttendingInterviewAt(int attendingInterviewAt) {
        this.attendingInterviewAt = attendingInterviewAt;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getFatherOccupation() {
        return fatherOccupation;
    }

    public void setFatherOccupation(String fatherOccupation) {
        this.fatherOccupation = fatherOccupation;
    }

    public String getFatherIncome() {
        return fatherIncome;
    }

    public void setFatherIncome(String fatherIncome) {
        this.fatherIncome = fatherIncome;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getYearOfPass() {
        return yearOfPass;
    }

    public void setYearOfPass(String yearOfPass) {
        this.yearOfPass = yearOfPass;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getIpeCategory() {
        return ipeCategory;
    }

    public void setIpeCategory(String ipeCategory) {
        this.ipeCategory = ipeCategory;
    }

    public String getIpeStream() {
        return ipeStream;
    }

    public void setIpeStream(String ipeStream) {
        this.ipeStream = ipeStream;
    }

    public String getIpeCollege() {
        return ipeCollege;
    }

    public void setIpeCollege(String ipeCollege) {
        this.ipeCollege = ipeCollege;
    }

    public String getIpeMedium() {
        return ipeMedium;
    }

    public void setIpeMedium(String ipeMedium) {
        this.ipeMedium = ipeMedium;
    }

    public String getIpeYearOfPass() {
        return ipeYearOfPass;
    }

    public void setIpeYearOfPass(String ipeYearOfPass) {
        this.ipeYearOfPass = ipeYearOfPass;
    }

    public String getIpePercentage() {
        return ipePercentage;
    }

    public void setIpePercentage(String ipePercentage) {
        this.ipePercentage = ipePercentage;
    }

    public String getDegreeQual() {
        return degreeQual;
    }

    public void setDegreeQual(String degreeQual) {
        this.degreeQual = degreeQual;
    }

    public String getDegreeBranch() {
        return degreeBranch;
    }

    public void setDegreeBranch(String degreeBranch) {
        this.degreeBranch = degreeBranch;
    }

    public String getDegreeCollege() {
        return degreeCollege;
    }

    public void setDegreeCollege(String degreeCollege) {
        this.degreeCollege = degreeCollege;
    }

    public String getDegreeMedium() {
        return degreeMedium;
    }

    public void setDegreeMedium(String degreeMedium) {
        this.degreeMedium = degreeMedium;
    }

    public String getDegreeYearOfPass() {
        return degreeYearOfPass;
    }

    public void setDegreeYearOfPass(String degreeYearOfPass) {
        this.degreeYearOfPass = degreeYearOfPass;
    }

    public String getDegreePercentage() {
        return degreePercentage;
    }

    public void setDegreePercentage(String degreePercentage) {
        this.degreePercentage = degreePercentage;
    }

    public String getPgQual() {
        return pgQual;
    }

    public void setPgQual(String pgQual) {
        this.pgQual = pgQual;
    }

    public String getPgStream() {
        return pgStream;
    }

    public void setPgStream(String pgStream) {
        this.pgStream = pgStream;
    }

    public String getPgCollege() {
        return pgCollege;
    }

    public void setPgCollege(String pgCollege) {
        this.pgCollege = pgCollege;
    }

    public String getPgMedium() {
        return pgMedium;
    }

    public void setPgMedium(String pgMedium) {
        this.pgMedium = pgMedium;
    }

    public String getPgYearOfPass() {
        return pgYearOfPass;
    }

    public void setPgYearOfPass(String pgYearOfPass) {
        this.pgYearOfPass = pgYearOfPass;
    }

    public String getPgPercentage() {
        return pgPercentage;
    }

    public void setPgPercentage(String pgPercentage) {
        this.pgPercentage = pgPercentage;
    }

    public String getAddInfo() {
        return addInfo;
    }

    public void setAddInfo(String addInfo) {
        this.addInfo = addInfo;
    }

    public String getSkill1() {
        return skill1;
    }

    public void setSkill1(String skill1) {
        this.skill1 = skill1;
    }

    public String getScale1() {
        return scale1;
    }

    public void setScale1(String scale1) {
        this.scale1 = scale1;
    }

    public String getSkill2() {
        return skill2;
    }

    public void setSkill2(String skill2) {
        this.skill2 = skill2;
    }

    public String getScale2() {
        return scale2;
    }

    public void setScale2(String scale2) {
        this.scale2 = scale2;
    }

    public String getSkill3() {
        return skill3;
    }

    public void setSkill3(String skill3) {
        this.skill3 = skill3;
    }

    public String getScale3() {
        return scale3;
    }

    public void setScale3(String scale3) {
        this.scale3 = scale3;
    }

    public String getSkill4() {
        return skill4;
    }

    public void setSkill4(String skill4) {
        this.skill4 = skill4;
    }

    public String getScale4() {
        return scale4;
    }

    public void setScale4(String scale4) {
        this.scale4 = scale4;
    }

    public String getSkill5() {
        return skill5;
    }

    public void setSkill5(String skill5) {
        this.skill5 = skill5;
    }

    public String getScale5() {
        return scale5;
    }

    public void setScale5(String scale5) {
        this.scale5 = scale5;
    }

    public String getSkill6() {
        return skill6;
    }

    public void setSkill6(String skill6) {
        this.skill6 = skill6;
    }

    public String getScale6() {
        return scale6;
    }

    public void setScale6(String scale6) {
        this.scale6 = scale6;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCompany1() {
        return company1;
    }

    public void setCompany1(String company1) {
        this.company1 = company1;
    }

    public String getDesignation1() {
        return designation1;
    }

    public void setDesignation1(String designation1) {
        this.designation1 = designation1;
    }

    public Date getFromDate1() {
        return fromDate1;
    }

    public void setFromDate1(Date fromDate1) {
        this.fromDate1 = fromDate1;
    }

    public Date getToDate1() {
        return toDate1;
    }

    public void setToDate1(Date toDate1) {
        this.toDate1 = toDate1;
    }

    public String getLocation1() {
        return location1;
    }

    public void setLocation1(String location1) {
        this.location1 = location1;
    }

    public String getCompany2() {
        return company2;
    }

    public void setCompany2(String company2) {
        this.company2 = company2;
    }

    public String getDesignation2() {
        return designation2;
    }

    public void setDesignation2(String designation2) {
        this.designation2 = designation2;
    }

    public Date getFromDate2() {
        return fromDate2;
    }

    public void setFromDate2(Date fromDate2) {
        this.fromDate2 = fromDate2;
    }

    public Date getToDate2() {
        return toDate2;
    }

    public void setToDate2(Date toDate2) {
        this.toDate2 = toDate2;
    }

    public String getLocation2() {
        return location2;
    }

    public void setLocation2(String location2) {
        this.location2 = location2;
    }

    public String getRefName() {
        return refName;
    }

    public void setRefName(String refName) {
        this.refName = refName;
    }

    public String getRefEmail() {
        return refEmail;
    }

    public void setRefEmail(String refEmail) {
        this.refEmail = refEmail;
    }

    public String getRefMobile() {
        return refMobile;
    }

    public void setRefMobile(String refMobile) {
        this.refMobile = refMobile;
    }

    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    /**
     * @return the campusLocation
     */
    public int getCampusLocation() {
        return campusLocation;
    }

    /**
     * @param campusLocation the campusLocation to set
     */
    public void setCampusLocation(int campusLocation) {
        this.campusLocation = campusLocation;
    }

    /**
     * @return the recLocation
     */
    public String getRecLocation() {
        return recLocation;
    }

    /**
     * @param recLocation the recLocation to set
     */
    public void setRecLocation(String recLocation) {
        this.recLocation = recLocation;
    }

    /**
     * @return the jFairLocation
     */
    public String getJfairLocation() {
        return jfairLocation;
    }

    /**
     * @param jFairLocation the jFairLocation to set
     */
    public void setJfairLocation(String jfairLocation) {
        this.jfairLocation = jfairLocation;
    }

    /**
     * @return the imagePath
     */
    public File getImagePath() {
        return imagePath;
    }

    /**
     * @param imagePath the imagePath to set
     */
    public void setImagePath(File imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * @return the recLocationList
     */
    public List getRecLocationList() {
        return recLocationList;
    }

    /**
     * @param recLocationList the recLocationList to set
     */
    public void setRecLocationList(List recLocationList) {
        this.recLocationList = recLocationList;
    }

    /**
     * @return the postAppliedMap
     */
    public Map getPostAppliedMap() {
        return postAppliedMap;
    }

    /**
     * @param postAppliedMap the postAppliedMap to set
     */
    public void setPostAppliedMap(Map postAppliedMap) {
        this.postAppliedMap = postAppliedMap;
    }
  
    
}
