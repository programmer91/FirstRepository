 /*
  * AddressAction.java
  *
  * Created on November 26, 2007, 3:25 PM
  *
  * To change this template, choose Tools | Template Manager
  * and open the template in the editor.
  */

package com.mss.mirage.employee.address;

import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.HibernateDataProvider;
import com.mss.mirage.util.AuthorizationManager;
import com.mss.mirage.util.ServiceLocator;
import com.mss.mirage.util.ServiceLocatorException;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.ParameterNameAware;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author __RajaReddy.Andra
 *
 */
public class AddressAction extends ActionSupport implements ServletRequestAware,ParameterNameAware{
    
    /** id is used for storing id of address*/
    private int id;
    /** offShoreAddLine1 is used for storing offShoreAddressLine1. */
    private String offShoreAddLine1;
    
    /** offShoreAddLine2 is used for storing offShoreAddressLine2. */
    private String offShoreAddLine2;
    /** offShoreCity is used for storing offShoreCity. */
    private String offShoreCity;
    
    /** offShoreState is used for storing offShoreState. */
    private String offShoreState;
    
    /** offShoreCountry is used for storing offShoreCountry. */
    private String offShoreCountry;
    
    /** offShoreZip is used for storing offShoreZip. */
    private String offShoreZip;
    
    /** payrollAddLine1 is used for storing payrollAddLine1. */
    private String payrollAddLine1;
    
    /** payrollAddLine2 is used for storing payrollAddLine2. */
    private String payrollAddLine2;
    
    /** payrollCity is used for storing payrollCity. */
    private String payrollCity;
    
    /** payrollState is used for storing payrollState. */
    private String payrollState;
    
    /** payrollCountry is used for storing payrollCountry. */
    private String payrollCountry;
    
    /** payrollZip is used for storing payrollZip. */
    private String payrollZip;
    
    /** cprojectAddLine1 is used for storing cprojectAddLine1. */
    private String cprojectAddLine1;
    
    /** cprojectAddLine2 is used for storing cprojectAddLine2. */
    private String cprojectAddLine2;
    
    /** cprojectCity is used for storing cprojectCity. */
    private String cprojectCity;
    
    /** cprojectState is used for storing cprojectState. */
    private String cprojectState;
    
    /** cprojectCountry is used for storing cprojectCountry. */
    private String cprojectCountry;
    
    /** cprojectZip is used for storing cprojectZip. */
    private String cprojectZip;
    
    /** homeAddLine1 is used for storing homeAddLine1. */
    private String homeAddLine1;
    
    /** homeAddLine2 is used for storing homeAddLine2. */
    private String homeAddLine2;
    
    /** homeCity is used for storing homeCity. */
    private String homeCity;
    
    /** homeState is used for storing homeState. */
    private String homeState;
    
    /** homeCountry is used for storing homeCountry. */
    private String homeCountry;
    
    /** homeZip is used for storinghomeZip. */
    private String homeZip;
    
    /** hotelAddLine1 is used for hotelAddLine1. */
    private String hotelAddLine1;
    
    /** hotelAddLine2 is used for hotelAddLine2. */
    private String hotelAddLine2;
    
    /** hotelCity is used for hotelCity. */
    private String hotelCity;
    
    /** hotelState is used for hotelState. */
    private String hotelState;
    
    /** hotelCountry is used for hotelCountry. */
    private String hotelCountry;
    
    /** hotelZip is used for hotelZip. */
    private String hotelZip;
    
    /** otherAddLine1 is used for otherAddLine1. */
    private String otherAddLine1;
    
    /** otherAddLine2 is used for otherAddLine2. */
    private String otherAddLine2;
    
    /** ohterCity is used for ohterCity. */
    private String otherCity;
    
    /** ohterState is used for ohterState. */
    private String otherState;
    
    /** otherCountry is used for otherCountry. */
    private String otherCountry;
    
    /** otherZip is used for otherZip. */
    private String otherZip;
    
    private String operation;
    
    private String employeeName;
    
    private int empId;
    private HttpServletRequest httpServletRequest;
    private String resultType;
    private String resultMessage;
    //For prepare Select Components
    private List countryList;
    private List statesList;
    
    private AddressVTO currentAddress;
    
    private String currentAction;
    
    private int offAddressId;
    private int payAddressId;
    private int cProAddressId;
    private int homeAddressId;
    private int hotelAddressId;
    private int otherAddressId;
    
    private int userRoleId;
    
     
    /** Creates a new instance of AddressAction */
    public AddressAction() {
        
    }//end of the constructor
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }
    
    
    public String prepare(){
        resultType = LOGIN;
       
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("PREPARE_EMP_ADDRESS",userRoleId)){
                try{
                    HibernateDataProvider dataProvider = HibernateDataProvider.getInstance();
                    DataSourceDataProvider dataSourceProvider = DataSourceDataProvider.getInstance();
                    
                    setEmployeeName(dataSourceProvider.getEmployeeNameByEmpNo(getId()));
                    setCountryList(dataProvider.getContries(ApplicationConstants.COUNTRY_OPTIONS));
                    setStatesList(dataProvider.getStatesList(ApplicationConstants.STATES_OPTIONS));
                    resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }
    
    public String execute() throws Exception{
        
        
        return SUCCESS;
    }
    public String getEmpAddress() throws Exception{
        
        
        return null;
    }
    
    public String checkAction(){
        resultType = LOGIN;
      
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("CHECK_EMP_ADDRESS",userRoleId)){
                try{
                    prepare();
                    if(ServiceLocator.getAddressService().checkAction(getId()) > 0){
                        setCurrentAddress(ServiceLocator.getAddressService().getAddress(getId()));
                        //  httpServletRequest.setAttribute(ApplicationConstants.REQUEST_EMP_ID,Integer.valueOf(getEmpId()));
                        currentAction="editEmpAddress";
                    }else{
                        currentAction="addEmpAddress";
                    }
                    resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }
    
    public String addEmpAddress(){
        //setEmpId(Integer.parseInt((String)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID)));
        // setEmpId(Integer.parseInt(httpServletRequest.getAttribute(ApplicationConstants.REQUEST_EMP_ID).toString()));
        resultType = LOGIN;
     
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("ADD_EMP_ADDRESS",userRoleId)){
                try{
                    
                    //setOperation(ApplicationConstants.SP_ADD);
                    if(ServiceLocator.getAddressService().addOrUpdateAddress(this) == 0 ){
                        //change
                      //   setCurrentAddress(ServiceLocator.getAddressService().getAddress(getId()));
                         currentAction="editEmpAddress";
                        setResultMessage("Address has been successfully inserted");
                        resultType = SUCCESS;
                    }else{
                        setResultMessage("Sorry! Please Try again!");
                        resultType = INPUT;
                    }
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, getResultMessage());
                    prepare();
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
        
        
    }
    public String editEmpAddress() {
        resultType = LOGIN;
      
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("EDIT_EMP_ADDRESS",userRoleId)){
                try{
                    setEmpId(Integer.parseInt((String)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID)));
                    //setOperation(ApplicationConstants.SP_EDIT);
                    if(ServiceLocator.getAddressService().addOrUpdateAddress(this) >= 0){
                        currentAction="editEmpAddress";
                     //   setCurrentAddress(ServiceLocator.getAddressService().getAddress(getId()));
                        setResultMessage("Address has been successfully updated");
                        resultType = SUCCESS;
                    }else{
                        
                        setResultMessage("Sorry! Please Try again!");
                        resultType= INPUT;
                    }
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, getResultMessage());
                    prepare();
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
     *This is bean section
     */
    
    public String getOffShoreAddLine1() {
        return offShoreAddLine1;
    }
    
    public void setOffShoreAddLine1(String offShoreAddLine1) {
        this.offShoreAddLine1 = offShoreAddLine1;
    }
    
    public String getOffShoreAddLine2() {
        return offShoreAddLine2;
    }
    
    public void setOffShoreAddLine2(String offShoreAddLine2) {
        this.offShoreAddLine2 = offShoreAddLine2;
    }
    
    public String getOffShoreCity() {
        return offShoreCity;
    }
    
    public void setOffShoreCity(String offShoreCity) {
        this.offShoreCity = offShoreCity;
    }
    
    public String getOffShoreState() {
        return offShoreState;
    }
    
    public void setOffShoreState(String offShoreState) {
        this.offShoreState = offShoreState;
    }
    
    public String getOffShoreCountry() {
        return offShoreCountry;
    }
    
    public void setOffShoreCountry(String offShoreCountry) {
        this.offShoreCountry = offShoreCountry;
    }
    
    public String getOffShoreZip() {
        return offShoreZip;
    }
    
    public void setOffShoreZip(String offShoreZip) {
        this.offShoreZip = offShoreZip;
    }
    
    public String getPayrollAddLine1() {
        return payrollAddLine1;
    }
    
    public void setPayrollAddLine1(String payrollAddLine1) {
        this.payrollAddLine1 = payrollAddLine1;
    }
    
    public String getPayrollAddLine2() {
        return payrollAddLine2;
    }
    
    public void setPayrollAddLine2(String payrollAddLine2) {
        this.payrollAddLine2 = payrollAddLine2;
    }
    
    public String getPayrollCity() {
        return payrollCity;
    }
    
    public void setPayrollCity(String payrollCity) {
        this.payrollCity = payrollCity;
    }
    
    public String getPayrollState() {
        return payrollState;
    }
    
    public void setPayrollState(String payrollState) {
        this.payrollState = payrollState;
    }
    
    public String getPayrollCountry() {
        return payrollCountry;
    }
    
    public void setPayrollCountry(String payrollCountry) {
        this.payrollCountry = payrollCountry;
    }
    
    public String getPayrollZip() {
        return payrollZip;
    }
    
    public void setPayrollZip(String payrollZip) {
        this.payrollZip = payrollZip;
    }
    
    public String getCprojectAddLine1() {
        return cprojectAddLine1;
    }
    
    public void setCprojectAddLine1(String cprojectAddLine1) {
        this.cprojectAddLine1 = cprojectAddLine1;
    }
    
    public String getCprojectAddLine2() {
        return cprojectAddLine2;
    }
    
    public void setCprojectAddLine2(String cprojectAddLine2) {
        this.cprojectAddLine2 = cprojectAddLine2;
    }
    
    public String getCprojectCity() {
        return cprojectCity;
    }
    
    public void setCprojectCity(String cprojectCity) {
        this.cprojectCity = cprojectCity;
    }
    
    public String getCprojectState() {
        return cprojectState;
    }
    
    public void setCprojectState(String cprojectState) {
        this.cprojectState = cprojectState;
    }
    
    public String getCprojectCountry() {
        return cprojectCountry;
    }
    
    public void setCprojectCountry(String cprojectCountry) {
        this.cprojectCountry = cprojectCountry;
    }
    
    public String getCprojectZip() {
        return cprojectZip;
    }
    
    public void setCprojectZip(String cprojectZip) {
        this.cprojectZip = cprojectZip;
    }
    
    public String getHomeAddLine1() {
        return homeAddLine1;
    }
    
    public void setHomeAddLine1(String homeAddLine1) {
        this.homeAddLine1 = homeAddLine1;
    }
    
    public String getHomeAddLine2() {
        return homeAddLine2;
    }
    
    public void setHomeAddLine2(String homeAddLine2) {
        this.homeAddLine2 = homeAddLine2;
    }
    
    public String getHomeCity() {
        return homeCity;
    }
    
    public void setHomeCity(String homeCity) {
        this.homeCity = homeCity;
    }
    
    public String getHomeState() {
        return homeState;
    }
    
    public void setHomeState(String homeState) {
        this.homeState = homeState;
    }
    
    public String getHomeCountry() {
        return homeCountry;
    }
    
    public void setHomeCountry(String homeCountry) {
        this.homeCountry = homeCountry;
    }
    
    public String getHomeZip() {
        return homeZip;
    }
    
    public void setHomeZip(String homeZip) {
        this.homeZip = homeZip;
    }
    
    public String getHotelAddLine1() {
        return hotelAddLine1;
    }
    
    public void setHotelAddLine1(String hotelAddLine1) {
        this.hotelAddLine1 = hotelAddLine1;
    }
    
    public String getHotelAddLine2() {
        return hotelAddLine2;
    }
    
    public void setHotelAddLine2(String hotelAddLine2) {
        this.hotelAddLine2 = hotelAddLine2;
    }
    
    public String getHotelCity() {
        return hotelCity;
    }
    
    public void setHotelCity(String hotelCity) {
        this.hotelCity = hotelCity;
    }
    
    public String getHotelState() {
        return hotelState;
    }
    
    public void setHotelState(String hotelState) {
        this.hotelState = hotelState;
    }
    
    public String getHotelCountry() {
        return hotelCountry;
    }
    
    public void setHotelCountry(String hotelCountry) {
        this.hotelCountry = hotelCountry;
    }
    
    public String getHotelZip() {
        return hotelZip;
    }
    
    public void setHotelZip(String hotelZip) {
        this.hotelZip = hotelZip;
    }
    
    public String getOtherAddLine1() {
        return otherAddLine1;
    }
    
    public void setOtherAddLine1(String otherAddLine1) {
        this.otherAddLine1 = otherAddLine1;
    }
    
    public String getOtherAddLine2() {
        return otherAddLine2;
    }
    
    public void setOtherAddLine2(String otherAddLine2) {
        this.otherAddLine2 = otherAddLine2;
    }
    
    public String getOtherCity() {
        return otherCity;
    }
    
    public void setOtherCity(String otherCity) {
        this.otherCity = otherCity;
    }
    
    public String getOtherState() {
        return otherState;
    }
    
    public void setOtherState(String otherState) {
        this.otherState = otherState;
    }
    
    public String getOtherCountry() {
        return otherCountry;
    }
    
    public void setOtherCountry(String otherCountry) {
        this.otherCountry = otherCountry;
    }
    
    public String getOtherZip() {
        return otherZip;
    }
    
    public void setOtherZip(String otherZip) {
        this.otherZip = otherZip;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public List getCountryList() {
        return countryList;
    }
    
    public void setCountryList(List countryList) {
        this.countryList = countryList;
    }
    
    public int getEmpId() {
        return empId;
    }
    
    public void setEmpId(int empId) {
        this.empId = empId;
    }
    
    public String getOperation() {
        return operation;
    }
    
    public void setOperation(String operation) {
        this.operation = operation;
    }
    
    public String getCurrentAction() {
        return currentAction;
    }
    
    public void setCurrentAction(String currentAction) {
        this.currentAction = currentAction;
    }
    
    
    public AddressVTO getCurrentAddress() {
        return currentAddress;
    }
    
    public void setCurrentAddress(AddressVTO currentAddress) {
        this.currentAddress = currentAddress;
    }
    
    public List getStatesList() {
        return statesList;
    }
    
    public void setStatesList(List statesList) {
        this.statesList = statesList;
    }
    
    public int getOffAddressId() {
        return offAddressId;
    }
    
    public void setOffAddressId(int offAddressId) {
        this.offAddressId = offAddressId;
    }
    
    public int getPayAddressId() {
        return payAddressId;
    }
    
    public void setPayAddressId(int payAddressId) {
        this.payAddressId = payAddressId;
    }
    
    public int getCProAddressId() {
        return cProAddressId;
    }
    
    public void setCProAddressId(int cProAddressId) {
        this.cProAddressId = cProAddressId;
    }
    
    public int getHomeAddressId() {
        return homeAddressId;
    }
    
    public void setHomeAddressId(int homeAddressId) {
        this.homeAddressId = homeAddressId;
    }
    
    public int getHotelAddressId() {
        return hotelAddressId;
    }
    
    public void setHotelAddressId(int hotelAddressId) {
        this.hotelAddressId = hotelAddressId;
    }
    
    public int getOtherAddressId() {
        return otherAddressId;
    }
    
    public void setOtherAddressId(int otherAddressId) {
        this.otherAddressId = otherAddressId;
    }
    
    public String getEmployeeName() {
        return employeeName;
    }
    
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    
    public boolean acceptableParameterName(String parameter) {
        boolean isParameterAcceptable=true;
        if(parameter.equals("submit")){
            isParameterAcceptable=false;
        }
        return isParameterAcceptable;
    }

    /**
     * @return the resultMessage
     */
    public String getResultMessage() {
        return resultMessage;
    }

    /**
     * @param resultMessage the resultMessage to set
     */
    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
    
    
}
