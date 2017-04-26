/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  May 30, 2008, 8:29 PM
 *
 * Author  : Hari Krishna Kondala<hkondala@miraclesoft.com>
 *
 * File    : TravelAction.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.employee.travel;

import com.mss.mirage.employee.travel.TravelVTO;
import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.AuthorizationManager;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Date;
import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author miracle
 */
public class TravelAction extends ActionSupport implements ServletRequestAware{
    
    /** The resultType is used for storing the message,after performing the bussiness logic . */
    private String resultType;
    
    /** The resultMessage is used for storing the message,after performing the bussiness logic . */
    private String resultMessage;
    
    /** The httpServletRequest is used for storing the request from the Client . */
    private HttpServletRequest httpServletRequest;
    
    /** The TravelService is used for invoking travel Service methods.
     * {@link com.mss.mirage.util.ServiceLocator}
     */
    private TravelService travelService;
    
    /** The currentTravel is reference object of the TravelVTO. */
    private TravelVTO currentTravel;
    
    /** The currentEmployee is reference object of the TravelVTO. */
    private TravelVTO currentEmployee;
    
    /** The id is used for storing the primary value of the table. */
    private int id;
    
    /** The EmpId  is used for storing the Employee Id  in the table. */
    private int EmpId;
    
    /** The CorpCardType  is used for storing the Corporate Card Type  in the table. */
    private String CorpCardType;
    
    /** The CorpCardNo  is used for storing the Corporate Card No in the table. */
    private String CorpCardNo;
    
    /** The CorpExpDate  is used for storing the Corporate Card Expiry Date in the table. */
    private Date CorpExpDate;
    
    /** The CorpNameOnCard  is used for storing the Corporate Name On Card in the table. */
    private String CorpNameOnCard;
    
    /** The CorpCardCode  is used for storing the Corporate Card Code in the table. */
    private String CorpCardCode;
    
    /** The PerCardType  is used for storing the Personal Card Type  in the table. */
    private String PerCardType;
    
    /** The PerCardType  is used for storing the Personal Card Type  in the table. */
    private String PerCardNo;
    
    /** The PerExpDate  is used for storing the Personal Card Expiry Date in the table. */
    private Date PerExpDate;
    
    /** The PerNameOnCard  is used for storing the Personal Name On Card in the table. */
    private String PerNameOnCard;
    
    /** The PerCardCode  is used for storing the Personal Card Code in the table. */
    private String PerCardCode;
    
    /** The FQProg1  is used for storing the FQ Program 1 in the table. */
    private String FQProg1;
    
    /** The FQNo1  is used for storing the FQ NO.1 in the table. */
    private String FQNo1;
    
    /** The FQProg2  is used for storing the FQ Program 2 in the table. */
    private String FQProg2;
            
    /** The FQNo2  is used for storing the FQ NO.2 in the table. */
    private String FQNo2;
    
    /** The FQProg3  is used for storing the FQ Program 3 in the table. */
    private String FQProg3;
            
    /** The FQNo3  is used for storing the FQ NO.3 in the table. */
    private String FQNo3;
    
    /** The FQProg4  is used for storing the FQ Program 4 in the table. */
    private String FQProg4;
            
    /** The FQNo4  is used for storing the FQ NO.4 in the table. */
    private String FQNo4;
    
    /** The FQProg5  is used for storing the FQ Program 5 in the table. */
    private String FQProg5;
            
    /** The FQNo5  is used for storing the FQ NO.5 in the table. */
    private String FQNo5;
    
    /** The FQProg6  is used for storing the FQ Program 6 in the table. */
    private String FQProg6;
            
    /** The FQNo6  is used for storing the FQ NO.6 in the table. */
    private String FQNo6;
    
    /** The PrefSeat  is used for storing the Preffered Seat in the table. */
    private String PrefSeat;
    
    /** The PrefMeals  is used for storing the Preffered Meal in the table. */
    private String PrefMeals;
    
    /** The PrefCarCo  is used for storing the Preffered Car Co in the table. */
    private String PrefCarCo;
    
    /** The PrefAirLine  is used for storing the Preffered AirLines in the table. */
    private String PrefAirLine;
    
    /** The PrefHotel  is used for storing the Preffered Hotel in the table. */
    private String PrefHotel;
    
    /** The PrefOther  is used for storing the Preffered Other in the table. */
    private String PrefOther;
    
    /** The Comments  is used for storing the Comments in the table. */
    private String Comments;
    
    /** The ModifiedBy  is used for storing the ModifiedBy in the table. */
    private String ModifiedBy;
    
    /** The ModifiedDate  is used for storing the ModifiedDate in the table. */
    private Timestamp ModifiedDate;
    
    private String employeeName;
    
    private int userRoleId;
    
    private String formAction = "";
    
    //These variables are used for getting employee details.
    
    private String fName;
    
    private String lName;
    
    private String mName;
    
    private String organization;
    
    private String department;
    
    private String title;
    
    private String status;
    
    private String employeeType;
    
    /** Creates a new instance of TravelAction */
    public TravelAction() {
    }
    
    public String execute() throws Exception {
        return SUCCESS;
    }
    
    public String checkAction() {
        resultType = LOGIN;
        
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("CHECK_TRAVEL",userRoleId)) {
                try {
                    setEmployeeName(DataSourceDataProvider.getInstance().getEmployeeNameByEmpNo(getId()));
                    setCurrentEmployee(ServiceLocator.getTravelService().getEmpDetails(getId()));
                    if(ServiceLocator.getTravelService().checkAction(getId()) > 0) {
                        setCurrentTravel(ServiceLocator.getTravelService().getTravel(getId()));
                        setFormAction("travelEdit");
                    }else {
                        setFormAction("travelAdd");
                    }
                    resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }
        return resultType;
    }
    
    /**
     * The doAdd() is used for adding  the ancillary details to the database server.
     * @throws Exception
     * @return String variable for navigation.
     */
    
    public String doAdd() {
        resultType = LOGIN;
        
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_ADD_TRAVEL",userRoleId)){
                try {
                    int insertedRows = 0;
                    travelService = ServiceLocator.getTravelService();
                    setEmployeeName(DataSourceDataProvider.getInstance().getEmployeeNameByEmpNo(getId()));
                    insertedRows = travelService.addTravel(this);
                    if(insertedRows == 1) {
                        setCurrentTravel(ServiceLocator.getTravelService().getTravel(getId()));
                        setFormAction("travelEdit");  
                        resultType = SUCCESS;
                        resultMessage = "Travel Details has been successfully inserted!";
                    }else {
                        resultType = INPUT;
                        resultMessage = "Sorry! Please Try again!";
                    }
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }
        return resultType;
    }
    
    /**
     * The doEdit() is used for editing  the ancillary details to the database server.
     * @throws Exception
     * @return String variable for navigation.
     */
    public String doEdit() {
        resultType = LOGIN;
     
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_EDIT_TRAVEL",userRoleId)){
                try{
                    int updatedRows = 0;
                    travelService = ServiceLocator.getTravelService();
                    setEmployeeName(DataSourceDataProvider.getInstance().getEmployeeNameByEmpNo(getId()));
                    setModifiedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                    setModifiedDate(DateUtility.getInstance().getCurrentMySqlDateTime());
                    updatedRows = travelService.UpdateTravel(this);
                    setCurrentTravel(ServiceLocator.getTravelService().getTravel(getId()));
                    setCurrentEmployee(ServiceLocator.getTravelService().getEmpDetails(getId()));
                    if(updatedRows == 1){
                         setFormAction("travelEdit");
                        resultType = SUCCESS;
                        resultMessage = "Travel Details has been successfully updated!";
                    }else{
                        resultType = INPUT;
                        resultMessage = "Sorry! Please Try again!";
                    }
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }
    
    /**
     * The getTravel() is used for retrieving the travel details from database server.
     * @throws Exception
     * @return String variable for navigation.
     */
    public String getTravel() {
        resultType = LOGIN;
        
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("GET_TRAVEL",userRoleId)) {
                try {
                    setCurrentTravel(ServiceLocator.getTravelService().getTravel(getId()));
                    setFormAction("travelEdit");
                    resultType =SUCCESS;
                }catch (Exception ex){
                    ex.printStackTrace();
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }
        return resultType;
    }
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public TravelVTO getCurrentTravel() {
        return currentTravel;
    }

    public void setCurrentTravel(TravelVTO currentTravel) {
        this.currentTravel = currentTravel;
    }

    public int getEmpId() {
        return EmpId;
    }

    public void setEmpId(int EmpId) {
        this.EmpId = EmpId;
    }

    public String getCorpCardType() {
        return CorpCardType;
    }

    public void setCorpCardType(String CorpCardType) {
        this.CorpCardType = CorpCardType;
    }

    public String getCorpCardNo() {
        return CorpCardNo;
    }

    public void setCorpCardNo(String CorpCardNo) {
        this.CorpCardNo = CorpCardNo;
    }

    public Date getCorpExpDate() {
        return CorpExpDate;
    }

    public void setCorpExpDate(Date CorpExpDate) {
        this.CorpExpDate = CorpExpDate;
    }

    public String getCorpNameOnCard() {
        return CorpNameOnCard;
    }

    public void setCorpNameOnCard(String CorpNameOnCard) {
        this.CorpNameOnCard = CorpNameOnCard;
    }

    public String getCorpCardCode() {
        return CorpCardCode;
    }

    public void setCorpCardCode(String CorpCardCode) {
        this.CorpCardCode = CorpCardCode;
    }

    public String getPerCardType() {
        return PerCardType;
    }

    public void setPerCardType(String PerCardType) {
        this.PerCardType = PerCardType;
    }

    public String getPerCardNo() {
        return PerCardNo;
    }

    public void setPerCardNo(String PerCardNo) {
        this.PerCardNo = PerCardNo;
    }

    public Date getPerExpDate() {
        return PerExpDate;
    }

    public void setPerExpDate(Date PerExpDate) {
        this.PerExpDate = PerExpDate;
    }

    public String getPerNameOnCard() {
        return PerNameOnCard;
    }

    public void setPerNameOnCard(String PerNameOnCard) {
        this.PerNameOnCard = PerNameOnCard;
    }

    public String getPerCardCode() {
        return PerCardCode;
    }

    public void setPerCardCode(String PerCardCode) {
        this.PerCardCode = PerCardCode;
    }

    public String getFQProg1() {
        return FQProg1;
    }

    public void setFQProg1(String FQProg1) {
        this.FQProg1 = FQProg1;
    }

    public String getFQNo1() {
        return FQNo1;
    }

    public void setFQNo1(String FQNo1) {
        this.FQNo1 = FQNo1;
    }

    public String getFQProg2() {
        return FQProg2;
    }

    public void setFQProg2(String FQProg2) {
        this.FQProg2 = FQProg2;
    }

    public String getFQNo2() {
        return FQNo2;
    }

    public void setFQNo2(String FQNo2) {
        this.FQNo2 = FQNo2;
    }

    public String getFQProg3() {
        return FQProg3;
    }

    public void setFQProg3(String FQProg3) {
        this.FQProg3 = FQProg3;
    }

    public String getFQNo3() {
        return FQNo3;
    }

    public void setFQNo3(String FQNo3) {
        this.FQNo3 = FQNo3;
    }

    public String getFQProg4() {
        return FQProg4;
    }

    public void setFQProg4(String FQProg4) {
        this.FQProg4 = FQProg4;
    }

    public String getFQNo4() {
        return FQNo4;
    }

    public void setFQNo4(String FQNo4) {
        this.FQNo4 = FQNo4;
    }

    public String getFQProg5() {
        return FQProg5;
    }

    public void setFQProg5(String FQProg5) {
        this.FQProg5 = FQProg5;
    }

    public String getFQNo5() {
        return FQNo5;
    }

    public void setFQNo5(String FQNo5) {
        this.FQNo5 = FQNo5;
    }

    public String getFQProg6() {
        return FQProg6;
    }

    public void setFQProg6(String FQProg6) {
        this.FQProg6 = FQProg6;
    }

    public String getFQNo6() {
        return FQNo6;
    }

    public void setFQNo6(String FQNo6) {
        this.FQNo6 = FQNo6;
    }

    public String getPrefSeat() {
        return PrefSeat;
    }

    public void setPrefSeat(String PrefSeat) {
        this.PrefSeat = PrefSeat;
    }

    public String getPrefMeals() {
        return PrefMeals;
    }

    public void setPrefMeals(String PrefMeals) {
        this.PrefMeals = PrefMeals;
    }

    public String getPrefCarCo() {
        return PrefCarCo;
    }

    public void setPrefCarCo(String PrefCarCo) {
        this.PrefCarCo = PrefCarCo;
    }

    public String getPrefAirLine() {
        return PrefAirLine;
    }

    public void setPrefAirLine(String PrefAirLine) {
        this.PrefAirLine = PrefAirLine;
    }

    public String getPrefHotel() {
        return PrefHotel;
    }

    public void setPrefHotel(String PrefHotel) {
        this.PrefHotel = PrefHotel;
    }

    public String getPrefOther() {
        return PrefOther;
    }

    public void setPrefOther(String PrefOther) {
        this.PrefOther = PrefOther;
    }

    public String getComments() {
        return Comments;
    }

    public void setComments(String Comments) {
        this.Comments = Comments;
    }

    public String getModifiedBy() {
        return ModifiedBy;
    }

    public void setModifiedBy(String ModifiedBy) {
        this.ModifiedBy = ModifiedBy;
    }

    public Timestamp getModifiedDate() {
        return ModifiedDate;
    }

    public void setModifiedDate(Timestamp ModifiedDate) {
        this.ModifiedDate = ModifiedDate;
    }

    public String getFormAction() {
        return formAction;
    }

    public void setFormAction(String formAction) {
        this.formAction = formAction;
    }

    public TravelVTO getCurrentEmployee() {
        return currentEmployee;
    }

    public void setCurrentEmployee(TravelVTO currentEmployee) {
        this.currentEmployee = currentEmployee;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public String getMName() {
        return mName;
    }

    public void setMName(String mName) {
        this.mName = mName;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }
    
}
