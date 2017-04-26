/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.employee.expenses;

import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.AuthorizationManager;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author miracle
 */
public class ExpensesAction extends ActionSupport implements ServletRequestAware{
     private HttpServletRequest httpServletRequest;
     private String resultType;
     private List transportLocationList;
     private List accommodationList ;
     private String roomNo;
     private String accommodation;
     private String transportLocation;
     private double transportFee;
     private double cafeteriaFee;
     private double roomFee;
     private String cafeteria;
     private String transportation;
     private int empId;
     private String employeeName;
     private int id;
     private ExpensesVTO currentExpenses;
     private int userRoleId;
     private String currentAction;
     private String resultMessage;
     private String createdBy;
       private String occupancyType;
     private String dateOfOccupancy;
     private double electricalCharges;
     private Collection getOtherDetailExpenses;
    private String flag;
    private int expId;
     public String prepare(){
        resultType = LOGIN;
       
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            
           // userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
           
                try{
                    DataSourceDataProvider dataSourceProvider = DataSourceDataProvider.getInstance();
                    
                    setEmployeeName(dataSourceProvider.getEmployeeNameByEmpNo(getId()));
                     setTransportLocationList(DataSourceDataProvider.getInstance().getTransportLocationList());
                    setAccommodationList(DataSourceDataProvider.getInstance().getAccommodationList());
                  setGetOtherDetailExpenses(ServiceLocator.getExpensesService().getOtherDetailExpenses(getId(), 15));
                    resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
        }//Close Session Checking
        return resultType;
    }
    public String expensesCheck(){
          resultType = LOGIN;
         if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
              userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("EMP_EXPENSES",userRoleId)){
               try {
                   if(ServiceLocator.getExpensesService().checkAction(getId()) > 0){                                         
                        setCurrentAction("addEmpExpenses");                        
                        //System.out.println("flag--->"+ getFlag());
                         setCafeteria("No");
                        setTransportation("No");
                        if(getFlag()!=null && !"".equals(getFlag()) && "edit".equals(getFlag()))
                        {
                             setCurrentExpenses(ServiceLocator.getExpensesService().getExpenses(getId(),getExpId()));
                            setCurrentAction("editEmpExpenses");
                            setCafeteria(getCurrentExpenses().getCafeteria());
                            setTransportation(getCurrentExpenses().getTransportation());
                        }
                        else   if(getFlag()!=null && !"".equals(getFlag()) && "copy".equals(getFlag()))
                        {
                          //  System.out.println("copy block");
                            setCurrentExpenses(ServiceLocator.getExpensesService().getCopyOfLastRecord(getId()));
                            setCafeteria(getCurrentExpenses().getCafeteria());
                            setTransportation(getCurrentExpenses().getTransportation());
                        }
                      
                    }else{
                        setCurrentAction("addEmpExpenses");
                    setCafeteria("No");
                    setTransportation("No");
                    }
                      //System.out.println("setCurrentAction---->"+getCurrentAction());
                     
                   prepare();
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


    public String addEmpExpenses(){
          resultType = LOGIN;
         if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
              userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("EMP_EXPENSES",userRoleId)){
               try {
                   setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                     //setOperation(ApplicationConstants.SP_ADD);
                    if(ServiceLocator.getExpensesService().addExpenses(this) > 0 ){
                        //change
                          setCurrentExpenses(ServiceLocator.getExpensesService().getExpenses(getId(),getExpId()));
                         currentAction="addEmpExpenses";
                        resultMessage="Expenses added successfully";
                        resultType = SUCCESS;
                    }else{
                        resultMessage="Sorry! Please Try again!";
                        resultType = INPUT;
                    }
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
                     // System.out.println("setCurrentAction---->"+getCurrentAction());
                     
                   prepare();
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
 public String editEmpExpenses(){
          resultType = LOGIN;
         if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
              userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("EMP_EXPENSES",userRoleId)){
               try {
                      setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                    if(ServiceLocator.getExpensesService().editExpenses(this) > 0 ){
                        //change
                          setCurrentExpenses(ServiceLocator.getExpensesService().getExpenses(getId(),getExpId()));
                         currentAction="editEmpExpenses";
                        resultMessage="Expenses updated successfully";
                        resultType = SUCCESS;
                    }else{
                        resultMessage="Sorry! Please Try again!";
                        resultType = INPUT;
                    }
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
                      //System.out.println("setCurrentAction---->"+getCurrentAction());
                     
                   prepare();
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

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        httpServletRequest=hsr;
    }

    /**
     * @return the transportLocationList
     */
    public List getTransportLocationList() {
        return transportLocationList;
    }

    /**
     * @param transportLocationList the transportLocationList to set
     */
    public void setTransportLocationList(List transportLocationList) {
        this.transportLocationList = transportLocationList;
    }

    /**
     * @return the accommodationList
     */
    public List getAccommodationList() {
        return accommodationList;
    }

    /**
     * @param accommodationList the accommodationList to set
     */
    public void setAccommodationList(List accommodationList) {
        this.accommodationList = accommodationList;
    }

    /**
     * @return the roomNo
     */
    public String getRoomNo() {
        return roomNo;
    }

    /**
     * @param roomNo the roomNo to set
     */
    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    /**
     * @return the accommodation
     */
    public String getAccommodation() {
        return accommodation;
    }

    /**
     * @param accommodation the accommodation to set
     */
    public void setAccommodation(String accommodation) {
        this.accommodation = accommodation;
    }

    /**
     * @return the transportLocation
     */
    public String getTransportLocation() {
        return transportLocation;
    }

    /**
     * @param transportLocation the transportLocation to set
     */
    public void setTransportLocation(String transportLocation) {
        this.transportLocation = transportLocation;
    }

    /**
     * @return the transportFee
     */
    public double getTransportFee() {
        return transportFee;
    }

    /**
     * @param transportFee the transportFee to set
     */
    public void setTransportFee(double transportFee) {
        this.transportFee = transportFee;
    }

    /**
     * @return the cafeteriaFee
     */
    public double getCafeteriaFee() {
        return cafeteriaFee;
    }

    /**
     * @param cafeteriaFee the cafeteriaFee to set
     */
    public void setCafeteriaFee(double cafeteriaFee) {
        this.cafeteriaFee = cafeteriaFee;
    }

    /**
     * @return the roomFee
     */
    public double getRoomFee() {
        return roomFee;
    }

    /**
     * @param roomFee the roomFee to set
     */
    public void setRoomFee(double roomFee) {
        this.roomFee = roomFee;
    }

    /**
     * @return the cafeteria
     */
    public String getCafeteria() {
        return cafeteria;
    }

    /**
     * @param cafeteria the cafeteria to set
     */
    public void setCafeteria(String cafeteria) {
        this.cafeteria = cafeteria;
    }

    /**
     * @return the transportation
     */
    public String getTransportation() {
        return transportation;
    }

    /**
     * @param transportation the transportation to set
     */
    public void setTransportation(String transportation) {
        this.transportation = transportation;
    }

    /**
     * @return the empId
     */
    public int getEmpId() {
        return empId;
    }

    /**
     * @param empId the empId to set
     */
    public void setEmpId(int empId) {
        this.empId = empId;
    }

    /**
     * @return the employeeName
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * @param employeeName the employeeName to set
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the currentExpenses
     */
    public ExpensesVTO getCurrentExpenses() {
        return currentExpenses;
    }

    /**
     * @param currentExpenses the currentExpenses to set
     */
    public void setCurrentExpenses(ExpensesVTO currentExpenses) {
        this.currentExpenses = currentExpenses;
    }

    /**
     * @return the currentAction
     */
    public String getCurrentAction() {
        return currentAction;
    }

    /**
     * @param currentAction the currentAction to set
     */
    public void setCurrentAction(String currentAction) {
        this.currentAction = currentAction;
    }

    /**
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the occupancyType
     */
    public String getOccupancyType() {
        return occupancyType;
    }

    /**
     * @param occupancyType the occupancyType to set
     */
    public void setOccupancyType(String occupancyType) {
        this.occupancyType = occupancyType;
    }

    /**
     * @return the dateOfOccupancy
     */
    public String getDateOfOccupancy() {
        return dateOfOccupancy;
    }

    /**
     * @param dateOfOccupancy the dateOfOccupancy to set
     */
    public void setDateOfOccupancy(String dateOfOccupancy) {
        this.dateOfOccupancy = dateOfOccupancy;
    }

    /**
     * @return the electricalCharges
     */
    public double getElectricalCharges() {
        return electricalCharges;
    }

    /**
     * @param electricalCharges the electricalCharges to set
     */
    public void setElectricalCharges(double electricalCharges) {
        this.electricalCharges = electricalCharges;
    }

    /**
     * @return the getOtherDetailExpenses
     */
    public Collection getGetOtherDetailExpenses() {
        return getOtherDetailExpenses;
    }

    /**
     * @param getOtherDetailExpenses the getOtherDetailExpenses to set
     */
    public void setGetOtherDetailExpenses(Collection getOtherDetailExpenses) {
        this.getOtherDetailExpenses = getOtherDetailExpenses;
    }

    /**
     * @return the flag
     */
    public String getFlag() {
        return flag;
    }

    /**
     * @param flag the flag to set
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

    /**
     * @return the expId
     */
    public int getExpId() {
        return expId;
    }

    /**
     * @param expId the expId to set
     */
    public void setExpId(int expId) {
        this.expId = expId;
    }
}
