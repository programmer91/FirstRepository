/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  May 30, 2008, 10:04 PM
 *
 * Author  : Hari Krishna Kondala<hkondala@miraclesoft.com>
 *
 * File    : TravelVTO.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
package com.mss.mirage.employee.travel;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author miracle
 */
public class TravelVTO {
    
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
    
    //These variables are used for getting employee details.
    
    private String fName;
    
    private String lName;
    
    private String mName;
    
    private String organization;
    
    private String department;
    
    private String title;
    
    private String status;
    
    private String employeeType;
    
    /** Creates a new instance of TravelVTO */
    public TravelVTO() {
    }
    
    
    public int getEmpId() {
        return EmpId;
    }

    public void setEmpId(int EmpId) {
        this.EmpId = EmpId;
    }

    /**
     * The getCorpCardType() is used for accessing Corporate Card Type for Travel.
     * @ return String variable
     */
    public String getCorpCardType() {
        return CorpCardType;
    }

    public void setCorpCardType(String CorpCardType) {
        this.CorpCardType = CorpCardType;
    }

    /**
     * The getCorpCardNo() is used for accessing Corporate Card No for Travel.
     * @ return String variable
     */
    public String getCorpCardNo() {
        return CorpCardNo;
    }

    public void setCorpCardNo(String CorpCardNo) {
        this.CorpCardNo = CorpCardNo;
    }

    /**
     * The getCorpExpDate() is used for accessing Corporate Card Expiry Date for Travel.
     * @ return String variable
     */
    public Date getCorpExpDate() {
        return CorpExpDate;
    }

    public void setCorpExpDate(Date CorpExpDate) {
        this.CorpExpDate = CorpExpDate;
    }

    /**
     * The getCorpNameOnCard() is used for accessing Corporate Name On Card for Travel.
     * @ return String variable
     */
    public String getCorpNameOnCard() {
        return CorpNameOnCard;
    }

    public void setCorpNameOnCard(String CorpNameOnCard) {
        this.CorpNameOnCard = CorpNameOnCard;
    }

    /**
     * The getCorpCardCode() is used for accessing Corporate Card Code for Travel.
     * @ return String variable
     */
    public String getCorpCardCode() {
        return CorpCardCode;
    }

    public void setCorpCardCode(String CorpCardCode) {
        this.CorpCardCode = CorpCardCode;
    }

    /**
     * The getPerCardType() is used for accessing Personal Card Type for Travel.
     * @ return String variable
     */
    public String getPerCardType() {
        return PerCardType;
    }

    public void setPerCardType(String PerCardType) {
        this.PerCardType = PerCardType;
    }

    /**
     * The getPerCardNo() is used for accessing Personal Card No for Travel.
     * @ return String variable
     */
    public String getPerCardNo() {
        return PerCardNo;
    }

    public void setPerCardNo(String PerCardNo) {
        this.PerCardNo = PerCardNo;
    }

    /**
     * The getPerExpDate() is used for accessing Personal Card Expiry Date for Travel.
     * @ return String variable
     */
    public Date getPerExpDate() {
        return PerExpDate;
    }

    public void setPerExpDate(Date PerExpDate) {
        this.PerExpDate = PerExpDate;
    }

    /**
     * The getPerNameOnCard() is used for accessing Personal Name On Card for Travel.
     * @ return String variable
     */
    public String getPerNameOnCard() {
        return PerNameOnCard;
    }

    public void setPerNameOnCard(String PerNameOnCard) {
        this.PerNameOnCard = PerNameOnCard;
    }

    /**
     * The getPerCardCode() is used for accessing Personal Card Code for Travel.
     * @ return String variable
     */
    public String getPerCardCode() {
        return PerCardCode;
    }

    public void setPerCardCode(String PerCardCode) {
        this.PerCardCode = PerCardCode;
    }

    /**
     * The getFQProg1() is used for accessing FQ Program 1 for Travel.
     * @ return String variable
     */
    public String getFQProg1() {
        return FQProg1;
    }

    public void setFQProg1(String FQProg1) {
        this.FQProg1 = FQProg1;
    }

    /**
     * The getFQNo1() is used for accessing FQ No.1 for Travel.
     * @ return String variable
     */
    public String getFQNo1() {
        return FQNo1;
    }

    public void setFQNo1(String FQNo1) {
        this.FQNo1 = FQNo1;
    }

    /**
     * The getFQProg2() is used for accessing FQ Program 2 for Travel.
     * @ return String variable
     */
    public String getFQProg2() {
        return FQProg2;
    }

    public void setFQProg2(String FQProg2) {
        this.FQProg2 = FQProg2;
    }

    /**
     * The getFQNo2() is used for accessing FQ No.2 for Travel.
     * @ return String variable
     */
    public String getFQNo2() {
        return FQNo2;
    }

    public void setFQNo2(String FQNo2) {
        this.FQNo2 = FQNo2;
    }

    /**
     * The getFQProg3() is used for accessing FQ Program 3 for Travel.
     * @ return String variable
     */
    public String getFQProg3() {
        return FQProg3;
    }

    public void setFQProg3(String FQProg3) {
        this.FQProg3 = FQProg3;
    }

    /**
     * The getFQNo3() is used for accessing FQ No.3 for Travel.
     * @ return String variable
     */
    public String getFQNo3() {
        return FQNo3;
    }

    public void setFQNo3(String FQNo3) {
        this.FQNo3 = FQNo3;
    }

    /**
     * The getFQProg4() is used for accessing FQ Program 4 for Travel.
     * @ return String variable
     */
    public String getFQProg4() {
        return FQProg4;
    }

    public void setFQProg4(String FQProg4) {
        this.FQProg4 = FQProg4;
    }

    /**
     * The getFQNo4() is used for accessing FQ No.4 for Travel.
     * @ return String variable
     */
    public String getFQNo4() {
        return FQNo4;
    }

    public void setFQNo4(String FQNo4) {
        this.FQNo4 = FQNo4;
    }

    /**
     * The getFQProg5() is used for accessing FQ Program 5 for Travel.
     * @ return String variable
     */
    public String getFQProg5() {
        return FQProg5;
    }

    public void setFQProg5(String FQProg5) {
        this.FQProg5 = FQProg5;
    }

    /**
     * The getFQNo5() is used for accessing FQ No.5 for Travel.
     * @ return String variable
     */
    public String getFQNo5() {
        return FQNo5;
    }

    public void setFQNo5(String FQNo5) {
        this.FQNo5 = FQNo5;
    }

    /**
     * The getFQProg6() is used for accessing FQ Program 6 for Travel.
     * @ return String variable
     */
    public String getFQProg6() {
        return FQProg6;
    }

    public void setFQProg6(String FQProg6) {
        this.FQProg6 = FQProg6;
    }

    /**
     * The getFQNo6() is used for accessing FQ No.6 for Travel.
     * @ return String variable
     */
    public String getFQNo6() {
        return FQNo6;
    }

    public void setFQNo6(String FQNo6) {
        this.FQNo6 = FQNo6;
    }

    /**
     * The getPrefSeat() is used for accessing Preffered Seat for Travel.
     * @ return String variable
     */
    public String getPrefSeat() {
        return PrefSeat;
    }

    public void setPrefSeat(String PrefSeat) {
        this.PrefSeat = PrefSeat;
    }

    /**
     * The getPrefMeals() is used for accessing Preffered Meals for Travel.
     * @ return String variable
     */
    public String getPrefMeals() {
        return PrefMeals;
    }

    public void setPrefMeals(String PrefMeals) {
        this.PrefMeals = PrefMeals;
    }

    /**
     * The getPrefCarCo() is used for accessing Preffered Car Co for Travel.
     * @ return String variable
     */
    public String getPrefCarCo() {
        return PrefCarCo;
    }

    public void setPrefCarCo(String PrefCarCo) {
        this.PrefCarCo = PrefCarCo;
    }

    /**
     * The getPrefAirLine() is used for accessing Preffered AirLine for Travel.
     * @ return String variable
     */
    public String getPrefAirLine() {
        return PrefAirLine;
    }

    public void setPrefAirLine(String PrefAirLine) {
        this.PrefAirLine = PrefAirLine;
    }

    /**
     * The getPrefHotel() is used for accessing Preffered Hotel for Travel.
     * @ return String variable
     */
    public String getPrefHotel() {
        return PrefHotel;
    }

    public void setPrefHotel(String PrefHotel) {
        this.PrefHotel = PrefHotel;
    }

    /**
     * The getPrefOther() is used for accessing Preffered Other for Travel.
     * @ return String variable
     */
    public String getPrefOther() {
        return PrefOther;
    }

    public void setPrefOther(String PrefOther) {
        this.PrefOther = PrefOther;
    }

    /**
     * The getComments() is used for accessing Comments for Travel.
     * @ return String variable
     */
    public String getComments() {
        return Comments;
    }

    public void setComments(String Comments) {
        this.Comments = Comments;
    }

    /**
     * The getModifiedBy() is used for accessing ModifiedBy for Travel.
     * @ return String variable
     */
    public String getModifiedBy() {
        return ModifiedBy;
    }

    public void setModifiedBy(String ModifiedBy) {
        this.ModifiedBy = ModifiedBy;
    }

    /**
     * The getModifiedDate() is used for accessing ModifiedDate for Travel.
     * @ return String variable
     */
    public Timestamp getModifiedDate() {
        return ModifiedDate;
    }

    public void setModifiedDate(Timestamp ModifiedDate) {
        this.ModifiedDate = ModifiedDate;
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
