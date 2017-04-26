/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  November 7, 2007, 6:29 PM
 *
 * Author  : Rajanikanth Teppala<rteppala@miraclesoft.com>
 *
 * File    : SoftwareAction.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.crm.accounts;

import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.HibernateDataProvider;
import com.mss.mirage.util.AuthorizationManager;
import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.DataServiceLocator;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.ServiceLocator;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;


import com.opensymphony.xwork2.ActionSupport;
import java.sql.Date;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author miracle
 */

public class SoftwareAction  extends ActionSupport implements ServletRequestAware {
    
    
    /** Creating a reference variable for <code>AccountService</code> class .
     *  { @link com.mss.mirage.crm.accounts.AccountService }
     */
    private AccountService accountService;
    
    /* The String formAction is used for storing the Form Action i.e editAction or addAction */
    private String formAction;
    
    /* The StringBuffer queryString is used for storing query to communicate with backend */
    private StringBuffer queryString;
    
    /**
     * The resultType means the type we are returing from business methods
     *  whether SUCCESS or ERROR or INPUT...
     */
    private String resultType;
    
    /**
     * The resultMessage is used to display the stored info. in the form
     */
    private String resultMessage;
    
    /** Creating a reference variable for HttpServletRequest. */
    private HttpServletRequest httpServletRequest;
    
    /* Creating id to store AccountID in the database  */
    private int id;
    
    // parameters from check box.. start
    
    /* The String applications is used for storing the applications of the particular account */
    private String applications;
    
    /* The String hardware is used for storing the hardware of the particular account */
    private String hardware;
    
    /* The String softwares is used for storing the softwares of the particular account */
    private String softwares;
    
    /* The String databases is used for storing the databases of the particular account */
    private String databases;
    
    /* The Boolean asFour is used for storing the asFour of the particular account */
    private boolean asFour;
    
    /* The Boolean aix is used for storing the url aix the particular account */
    private boolean aix;
    
    /* The Boolean baan is used for storing the baan of the particular account */
    private boolean baan;
    
    /* The Boolean beaWeblogic is used for storing the beaWeblogic of the particular account */
    private boolean beaWeblogic;
    
    /* The Boolean businessObjects is used for storing the businessObjects of the particular account */
    private boolean businessObjects;
    
    /* The Boolean coldFusion is used for storing the coldFusion of the particular account */
    private boolean coldFusion;
    
    /* The Boolean cobol is used for storing the cobol of the particular account */
    private boolean cobol;
    
    /* The Boolean cognos is used for storing the cognos of the particular account */
    private boolean cognos;
    
    /* The Boolean cyclone is used for storing the cyclone of the particular account */
    private boolean cyclone;
    
    /* The Boolean dotNet is used for storing the dotNet of the particular account */
    private boolean dotNet;
    
    /* The Boolean dbTwo is used for storing the dbTwo of the particular account */
    private boolean dbTwo;
    
    /* The Boolean fabric is used for storing the fabric of the particular account */
    private boolean fabric;
    
    /* The Boolean gentran is used for storing the gentran of the particular account */
    private boolean gentran;
    
    /* The Boolean gxs is used for storing the gxs of the particular account */
    private boolean gxs;
    
    /* The Boolean hyperion is used for storing the hyperion of the particular account */
    private boolean hyperion;
    
    /* The Boolean harbinger is used for storing the harbinger of the particular account */
    private boolean harbinger;
    
    /* The Boolean hpUx is used for storing the hpUx of the particular account */
    private boolean hpUx;
    
    /* The Boolean hats is used for storing the hats of the particular account */
    private boolean hats;
    
    /* The Boolean ics is used for storing the ics of the particular account */
    private boolean ics;
    
    /* The Boolean iConnect is used for storing the iConnect of the particular account */
    private boolean iConnect;
    
    /* The Boolean informatica is used for storing the informatica of the particular account */
    private boolean informatica;
    
    /* The Boolean jdEdwards is used for storing the jdEdwards of the particular account */
    private boolean jdEdwards;
    
    /* The Boolean jsp is used for storing the jsp of the particular account */
    private boolean jsp;
    
    /* The Boolean mainFrames is used for storing the mainFrames of the particular account */
    private boolean mainFrames;
    
    /* The Boolean mq is used for storing the mq of the particular account */
    private boolean mq;
    
    /* The Boolean microStrategy is used for storing the microStrategy of the particular account */
    private boolean microStrategy;
    
    /* The Boolean msSql is used for storing the msSql of the particular account */
    private boolean msSql;
    
    /* The Boolean mySql is used for storing the mySql of the particular account */
    private boolean mySql;
    
    /* The Boolean mercator is used for storing the mercator of the particular account */
    private boolean mercator;
    
    /* The Boolean messageBroker is used for storing the messageBroker of the particular account */
    private boolean messageBroker;
    
    /* The Boolean oracleApps is used for storing the oracleApps of the particular account */
    private boolean oracleApps;
    
    /* The Boolean oracleFusion is used for storing the oracleFusion of the particular account */
    private boolean oracleFusion;
    
    /* The Boolean oracle is used for storing the oracle of the particular account */
    private boolean oracle;
    
    /* The Boolean peopleSoft is used for storing the peopleSoft of the particular account */
    private boolean peopleSoft;
    
    /* The Boolean redHat is used for storing the redHat of the particular account */
    private boolean redHat;
    
    /* The Boolean sap is used for storing the sap of the particular account */
    private boolean sap;
    
    /* The Boolean siebel is used for storing the siebel of the particular account */
    private boolean siebel;
    
    /* The Boolean sapxi is used for storing the sapxi of the particular account */
    private boolean sapxi;
    
    /* The Boolean seeBeyond is used for storing the seeBeyond of the particular account */
    private boolean seeBeyond;
    
    /* The Boolean seeBurger is used for storing the seeBurger of the particular account */
    private boolean seeBurger;
    
    /* The Boolean solaris is used for storing the solaris of the particular account */
    private boolean solaris;
    
    /* The Boolean tibco is used for storing the tibco of the particular account */
    private boolean tibco;
    
    /* The Boolean wps is used for storing the wps of the particular account */
    private boolean wps;
    
    /* The Boolean webMethods is used for storing the webMethods of the particular account */
    private boolean webMethods;
    
    /* The Boolean windows is used for storing the windows of the particular account */
    private boolean windows;
    
    /* The Boolean xtol is used for storing the xtol of the particular account */
    private boolean xtol;
    
    private boolean manuguistics;
    
    private boolean ITwo;
    
    private boolean vignette;
    
    private boolean broadvision;
    
    private boolean neon;
    
    private boolean vitria;
    
    private boolean selectica;
    
    private boolean webSphere;
    
    private boolean ariba;
    
    private boolean commerce;
    
    private boolean dataPower;
    
    private boolean ibmPortals;
	//new software parameters start
	private boolean jcaps;
    
    private boolean wtx;
    
    private boolean castIron;
    
    private boolean wodm;
    
    private boolean bpm;
    
    private boolean rational;
    
    private boolean lotus;
    
    private boolean hp;
	
	private boolean b2b;

    //new params end
    private String submitFrom;
    
    
    private int userRoleId;
    
    private int activitySummaryFlag;
     private Date dashBoardEndDate;
    private Date dashBoardStartDate;
     private String contactStatus;
	 private String activityType;
	private String empId;
 
    // parameters from check box.. end
    private boolean hybris;
    
    public String doAdd(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_SOFTWARE_ADD",userRoleId)){
                try{
                    
                    if(!("dbGrid".equalsIgnoreCase(getSubmitFrom()))){
                        int insertedRows = 0;
                        
                        accountService = ServiceLocator.getAccountService();
                        
                        
                        //
                        //Calling addOrUpdateAccount(AccountAction accountPojo) method to add account
                        //insertedRows = accountService.addOrUpdateSoftware(this);
                        
                        insertedRows = accountService.addSoftware(this);
                        
                        if(insertedRows == 1){
                            resultType = SUCCESS;
                            resultMessage = "<font color=\"green\" size=\"1.5\">Account has been successfully inserted!</font>";
                        }else{
                            resultType = INPUT;
                            resultMessage = "<font color=\"red\" size=\"1.5\">Sorry! Please Try again!</font>";
                        }
                        httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
                    }//close for submitFrom checking
                    if(LOGIN.equals(resultType)) resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
                
            }//END-Authorization Checking
        }//Close for session checking
        return resultType;
        
    }
    
    public String doEdit(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_SOFTWARE_EDIT",userRoleId)){
                try{
                    
                    if(!("dbGrid".equalsIgnoreCase(getSubmitFrom()))){
                        
                        int insertedRows = 0;
                        
                        accountService = ServiceLocator.getAccountService();
                        insertedRows = accountService.editSoftware(this);
                        
                        if(insertedRows == 1){
                            resultType = SUCCESS;
                            resultMessage = "<font color=\"green\" size=\"1.5\">Account has been successfully updated!</font>";
                            
                        }else{
                            resultType = INPUT;
                            resultMessage = "<font color=\"red\" size=\"1.5\">Sorry! Please Try again!</font>";
                        }
                        
                        httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
                        
                    }//close for submitFrom checking
                    
                   if(LOGIN.equals(resultType)) resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }//END-Authorization Checking
        }//Close for session checking
        
        return resultType;
    }
    
    /**
     * The method setServletRequest is used to set the HttpServletRequest
     */
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }
    
    /**
     * The method getId is used to get value from Accounts table in the database
     */
    public int getId() {
        return id;
    }
    
    /**
     * The method setId is used to set value from Accounts table in the database
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * The method getApplications is used to get the application for the account
     */
    public String getApplications() {
        return applications;
    }
    /**
     * The method setApplications is used to set the application for the account
     */
    public void setApplications(String applications) {
        this.applications = applications;
    }
    /**
     * The method getHardware is used to get the type of hardware for the account
     */
    public String getHardware() {
        return hardware;
    }
    /**
     * The method setHardware is used to set the type of hardware for the account
     */
    public void setHardware(String hardware) {
        this.hardware = hardware;
    }
    /**
     * The method getSoftwares is used to get the softwares for the account
     */
    public String getSoftwares() {
        return softwares;
    }
    /**
     * The method setSoftwares is used to set the softwares for the account
     */
    public void setSoftwares(String softwares) {
        this.softwares = softwares;
    }
    
    
    
    /**
     * The method getDatabases is used to get the type of database for the account
     */
    public String getDatabases() {
        return databases;
    }
    /**
     * The method setDatabases is used to set the type of database for the account
     */
    public void setDatabases(String databases) {
        this.databases = databases;
    }
    /**
     * The method isAsFour is used to get the AsFour for the account
     */
    public boolean isAsFour() {
        return asFour;
    }
    /**
     * The method setAsFour is used to set the AsFour for the account
     */
    public void setAsFour(boolean asFour) {
        this.asFour = asFour;
    }
    
    
    
    /**
     * The method isAix is used to get the Aix for the account
     */
    public boolean isAix() {
        return aix;
    }
    /**
     * The method setAix is used to set the Aix for the account
     */
    public void setAix(boolean aix) {
        this.aix = aix;
    }
    
    /**
     * The method isBeaWeblogic is used to get the BeaWeblogic for the account
     */
    public boolean isBeaWeblogic() {
        return beaWeblogic;
    }
    /**
     * The method setBeaWeblogic is used to set the BeaWeblogic for the account
     */
    public void setBeaWeblogic(boolean beaWeblogic) {
        this.beaWeblogic = beaWeblogic;
    }
    
    
    /**
     * The method isBusinessObjects is used to get the BusinessObjects for the account
     */
    public boolean isBusinessObjects() {
        return businessObjects;
    }
    /**
     * The method setBusinessObjects is used to set the BusinessObjects for the account
     */
    public void setBusinessObjects(boolean businessObjects) {
        this.businessObjects = businessObjects;
    }
    /**
     * The method isColdFusion is used to get the ColdFusion for the account
     */
    public boolean isColdFusion() {
        return coldFusion;
    }
    /**
     * The method setColdFusion is used to set the ColdFusion for the account
     */
    public void setColdFusion(boolean coldFusion) {
        this.coldFusion = coldFusion;
    }
    
    
    
    /**
     * The method isCobol is used to get the Cobol for the account
     */
    public boolean isCobol() {
        return cobol;
    }
    /**
     * The method setCobol is used to set the Cobol for the account
     */
    public void setCobol(boolean cobol) {
        this.cobol = cobol;
    }
    
    
    
    
    /**
     * The method isCognos is used to get the Cognos for the account
     */
    public boolean isCognos() {
        return cognos;
    }
    /**
     * The method setCognos is used to set the Cognos for the account
     */
    public void setCognos(boolean cognos) {
        this.cognos = cognos;
    }
    
    
    /**
     * The method isCyclone is used to get the Cyclone for the account
     */
    public boolean isCyclone() {
        return cyclone;
    }
    /**
     * The method setCyclone is used to set the Cyclone for the account
     */
    public void setCyclone(boolean cyclone) {
        this.cyclone = cyclone;
    }
    
    
    /**
     * The method isDotNet is used to get the DotNet for the account
     */
    public boolean isDotNet() {
        return dotNet;
    }
    /**
     * The method setDotNet is used to set the DotNet for the account
     */
    public void setDotNet(boolean dotNet) {
        this.dotNet = dotNet;
    }
    
    
    
    /**
     * The method isDbTwo is used to get the DbTwo for the account
     */
    public boolean isDbTwo() {
        return dbTwo;
    }
    /**
     * The method setDbTwo is used to set the DbTwo for the account
     */
    public void setDbTwo(boolean dbTwo) {
        this.dbTwo = dbTwo;
    }
    
    
    /**
     * The method isFabric is used to get the Fabric for the account
     */
    public boolean isFabric() {
        return fabric;
    }
    /**
     * The method setFabric is used to set the Fabric for the account
     */
    public void setFabric(boolean fabric) {
        this.fabric = fabric;
    }
    
    
    /**
     * The method isGentran is used to get the Gentran for the account
     */
    public boolean isGentran() {
        return gentran;
    }
    /**
     * The method setGentran is used to set the Gentran for the account
     */
    public void setGentran(boolean gentran) {
        this.gentran = gentran;
    }
    
    
    /**
     * The method isGxs is used to get the Gxs for the account
     */
    public boolean isGxs() {
        return gxs;
    }
    /**
     * The method setGxs is used to set the Gxs for the account
     */
    public void setGxs(boolean gxs) {
        this.gxs = gxs;
    }
    
    
    
    /**
     * The method isHyperion is used to get the Hyperion for the account
     */
    public boolean isHyperion() {
        return hyperion;
    }
    /**
     * The method setHyperion is used to set the Hyperion for the account
     */
    public void setHyperion(boolean hyperion) {
        this.hyperion = hyperion;
    }
    
    
    
    /**
     * The method isHarbinger is used to get the Harbinger for the account
     */
    public boolean isHarbinger() {
        return harbinger;
    }
    /**
     * The method setHarbinger is used to set the Harbinger for the account
     */
    public void setHarbinger(boolean harbinger) {
        this.harbinger = harbinger;
    }
    /**
     * The method isHpUx is used to get the HpUx for the account
     */
    public boolean isHpUx() {
        return hpUx;
    }
    /**
     * The method setHpUx is used to set the HpUx for the account
     */
    public void setHpUx(boolean hpUx) {
        this.hpUx = hpUx;
    }
    
    
    
    /**
     * The method isHats is used to get the Hats for the account
     */
    public boolean isHats() {
        return hats;
    }
    /**
     * The method setHats is used to set the Hats for the account
     */
    public void setHats(boolean hats) {
        this.hats = hats;
    }
    /**
     * The method isIcs is used to get the Ics for the account
     */
    public boolean isIcs() {
        return ics;
    }
    /**
     * The method isIcs is used to set the Ics for the account
     */
    public void setIcs(boolean ics) {
        this.ics = ics;
    }
    /**
     * The method isIConnect is used to get the IConnect for the account
     */
    public boolean isIConnect() {
        return iConnect;
    }
    /**
     * The method setIConnect is used to set the IConnect for the account
     */
    public void setIConnect(boolean iConnect) {
        this.iConnect = iConnect;
    }
    
    
    
    
    
    /**
     * The method isInformatica is used to get the Informatica for the account
     */
    public boolean isInformatica() {
        return informatica;
    }
    /**
     * The method setInformatica is used to set the Informatica for the account
     */
    public void setInformatica(boolean informatica) {
        this.informatica = informatica;
    }
    /**
     * The method isJdEdwards is used to get the JdEdwards for the account
     */
    public boolean isJdEdwards() {
        return jdEdwards;
    }
    /**
     * The method setJdEdwards is used to set the JdEdwards for the account
     */
    public void setJdEdwards(boolean jdEdwards) {
        this.jdEdwards = jdEdwards;
    }
    /**
     * The method isJsp is used to get the Jsp for the account
     */
    public boolean isJsp() {
        return jsp;
    }
    /**
     * The method setJsp is used to set the Jsp for the account
     */
    public void setJsp(boolean jsp) {
        this.jsp = jsp;
    }
    
    
    
    /**
     * The method isMainFrames is used to get the MainFrames for the account
     */
    public boolean isMainFrames() {
        return mainFrames;
    }
    /**
     * The method setMainFrames is used to set the MainFrames for the account
     */
    public void setMainFrames(boolean mainFrames) {
        this.mainFrames = mainFrames;
    }
    /**
     * The method isMq is used to get the Mq for the account
     */
    public boolean isMq() {
        return mq;
    }
    /**
     * The method setMq is used to set the Mq for the account
     */
    public void setMq(boolean mq) {
        this.mq = mq;
    }
    
    
    /**
     * The method isMicroStrategy is used to get the MicroStrategy for the account
     */
    public boolean isMicroStrategy() {
        return microStrategy;
    }
    /**
     * The method setMicroStrategy is used to set the MicroStrategy for the account
     */
    public void setMicroStrategy(boolean microStrategy) {
        this.microStrategy = microStrategy;
    }
    /**
     * The method isMsSql is used to get the MsSql for the account
     */
    public boolean isMsSql() {
        return msSql;
    }
    /**
     * The method setMsSql is used to set the MsSql for the account
     */
    public void setMsSql(boolean msSql) {
        this.msSql = msSql;
    }
    /**
     * The method isMySql is used to get the MySql for the account
     */
    public boolean isMySql() {
        return mySql;
    }
    /**
     * The method getMySql is used to set the MySql for the account
     */
    public void setMySql(boolean mySql) {
        this.mySql = mySql;
    }
    
    
    
    /**
     * The method isMercator is used to get the Mercator for the account
     */
    public boolean isMercator() {
        return mercator;
    }
    /**
     * The method setMercator is used to set the Mercator for the account
     */
    public void setMercator(boolean mercator) {
        this.mercator = mercator;
    }
    /**
     * The method isMessageBroker is used to get the MessageBroker for the account
     */
    public boolean isMessageBroker() {
        return messageBroker;
    }
    /**
     * The method setMessageBroker is used to set the MessageBroker for the account
     */
    public void setMessageBroker(boolean messageBroker) {
        this.messageBroker = messageBroker;
    }
    
    
    /**
     * The method isOracleApps is used to get the OracleApps for the account
     */
    public boolean isOracleApps() {
        return oracleApps;
    }
    /**
     * The method setOracleApps is used to set the OracleApps for the account
     */
    public void setOracleApps(boolean oracleApps) {
        this.oracleApps = oracleApps;
    }
    /**
     * The method isOracleFusion is used to get the OracleFusion for the account
     */
    public boolean isOracleFusion() {
        return oracleFusion;
    }
    /**
     * The method setOracleFusion is used to set the OracleFusion for the account
     */
    public void setOracleFusion(boolean oracleFusion) {
        this.oracleFusion = oracleFusion;
    }
    /**
     * The method isOracle is used to get the Oracle for the account
     */
    public boolean isOracle() {
        return oracle;
    }
    /**
     * The method setOracle is used to set the Oracle for the account
     */
    public void setOracle(boolean oracle) {
        this.oracle = oracle;
    }
    /**
     * The method isPeopleSoft is used to get the PeopleSoft for the account
     */
    public boolean isPeopleSoft() {
        return peopleSoft;
    }
    /**
     * The method setPeopleSoft is used to set the PeopleSoft for the account
     */
    public void setPeopleSoft(boolean peopleSoft) {
        this.peopleSoft = peopleSoft;
    }
    /**
     * The method isRedHat is used to get the RedHat for the account
     */
    public boolean isRedHat() {
        return redHat;
    }
    /**
     * The method setRedHat is used to set the RedHat for the account
     */
    public void setRedHat(boolean redHat) {
        this.redHat = redHat;
    }
    /**
     * The method isSap is used to get the Sap for the account
     */
    public boolean isSap() {
        return sap;
    }
    /**
     * The method setSap is used to set the Sap for the account
     */
    public void setSap(boolean sap) {
        this.sap = sap;
    }
    /**
     * The method isSiebel is used to get the Siebel for the account
     */
    public boolean isSiebel() {
        return siebel;
    }
    /**
     * The method setSiebel is used to set the Siebel for the account
     */
    public void setSiebel(boolean siebel) {
        this.siebel = siebel;
    }
    
    
    /**
     * The method isSapxi is used to get the Sapxi for the account
     */
    public boolean isSapxi() {
        return sapxi;
    }
    /**
     * The method setSapxi is used to set the Sapxi for the account
     */
    public void setSapxi(boolean sapxi) {
        this.sapxi = sapxi;
    }
    /**
     * The method isSeeBeyond is used to get the SeeBeyond for the account
     */
    public boolean isSeeBeyond() {
        return seeBeyond;
    }
    /**
     * The method setSeeBeyond is used to set the SeeBeyond for the account
     */
    public void setSeeBeyond(boolean seeBeyond) {
        this.seeBeyond = seeBeyond;
    }
    /**
     * The method isSeeBurger is used to get the SeeBurger for the account
     */
    public boolean isSeeBurger() {
        return seeBurger;
    }
    /**
     * The method setSeeBurger is used to set the SeeBurger for the account
     */
    public void setSeeBurger(boolean seeBurger) {
        this.seeBurger = seeBurger;
    }
    /**
     * The method isSolaris is used to get the Solaris for the account
     */
    public boolean isSolaris() {
        return solaris;
    }
    /**
     * The method setSolaris is used to set the Solaris for the account
     */
    public void setSolaris(boolean solaris) {
        this.solaris = solaris;
    }
    
    
    
    /**
     * The method isTibco is used to get the Tibco for the account
     */
    public boolean isTibco() {
        return tibco;
    }
    /**
     * The method setTibco is used to set the Tibco for the account
     */
    public void setTibco(boolean tibco) {
        this.tibco = tibco;
    }
    
    
    
    /**
     * The method isWps is used to get the Wps for the account
     */
    public boolean isWps() {
        return wps;
    }
    /**
     * The method setWps is used to set the Wps for the account
     */
    public void setWps(boolean wps) {
        this.wps = wps;
    }
    
    
    
    /**
     * The method isWebMethods is used to get the WebMethods for the account
     */
    public boolean isWebMethods() {
        return webMethods;
    }
    /**
     * The method setWebMethods is used to set the WebMethods for the account
     */
    public void setWebMethods(boolean webMethods) {
        this.webMethods = webMethods;
    }
    /**
     * The method isWindows is used to get the Windows for the account
     */
    public boolean isWindows() {
        return windows;
    }
    /**
     * The method setWindows is used to set the Windows for the account
     */
    public void setWindows(boolean windows) {
        this.windows = windows;
    }
    /**
     * The method isXtol is used to get the Xtol for the account
     */
    public boolean isXtol() {
        return xtol;
    }
    /**
     * The method setXtol is used to set the Xtol for the account
     */
    public void setXtol(boolean xtol) {
        this.xtol = xtol;
    }
    
    public String getFormAction() {
        return formAction;
    }
    
    public void setFormAction(String formAction) {
        this.formAction = formAction;
    }
    
    public boolean isManuguistics() {
        return manuguistics;
    }
    
    public void setManuguistics(boolean manuguistics) {
        this.manuguistics = manuguistics;
    }
    
    public boolean isITwo() {
        return ITwo;
    }
    
    public void setITwo(boolean ITwo) {
        this.ITwo = ITwo;
    }
    
    public boolean isVignette() {
        return vignette;
    }
    
    public void setVignette(boolean vignette) {
        this.vignette = vignette;
    }
    
    public boolean isBroadvision() {
        return broadvision;
    }
    
    public void setBroadvision(boolean broadvision) {
        this.broadvision = broadvision;
    }
    
    public boolean isNeon() {
        return neon;
    }
    
    public void setNeon(boolean neon) {
        this.neon = neon;
    }
    
    public boolean isVitria() {
        return vitria;
    }
    
    public void setVitria(boolean vitria) {
        this.vitria = vitria;
    }
    
    public boolean isSelectica() {
        return selectica;
    }
    
    public void setSelectica(boolean selectica) {
        this.selectica = selectica;
    }
    
    public boolean isWebSphere() {
        return webSphere;
    }
    
    public void setWebSphere(boolean webSphere) {
        this.webSphere = webSphere;
    }
    
    public boolean isAriba() {
        return ariba;
    }
    
    public void setAriba(boolean ariba) {
        this.ariba = ariba;
    }
    
    public boolean isBaan() {
        return baan;
    }
    
    public void setBaan(boolean baan) {
        this.baan = baan;
    }
    
    public String getSubmitFrom() {
        return submitFrom;
    }
    
    public void setSubmitFrom(String submitFrom) {
        this.submitFrom = submitFrom;
    }

    public boolean isCommerce() {
        return commerce;
    }

    public void setCommerce(boolean commerce) {
        this.commerce = commerce;
    }

    public boolean isDataPower() {
        return dataPower;
    }

    public void setDataPower(boolean dataPower) {
        this.dataPower = dataPower;
    }

    public boolean isIbmPortals() {
        return ibmPortals;
    }

    public void setIbmPortals(boolean ibmportals) {
        this.ibmPortals = ibmportals;
    }
    
	public boolean isJcaps() {
        return jcaps;
    }

    public void setJcaps(boolean jcaps) {
        this.jcaps = jcaps;
    }

    public boolean isWtx() {
        return wtx;
    }

    public void setWtx(boolean wtx) {
        this.wtx = wtx;
    }

    public boolean isBpm() {
        return bpm;
    }

    public void setBpm(boolean bpm) {
        this.bpm = bpm;
    }

    public boolean isCastIron() {
        return castIron;
    }

    public void setCastIron(boolean castIron) {
        this.castIron = castIron;
    }

    public boolean isHp() {
        return hp;
    }

    public void setHp(boolean hp) {
        this.hp = hp;
    }

    public boolean isLotus() {
        return lotus;
    }

    public void setLotus(boolean lotus) {
        this.lotus = lotus;
    }

    public boolean isRational() {
        return rational;
    }

    public void setRational(boolean rational) {
        this.rational = rational;
    }

    public boolean isB2b() {
        return b2b;
    }

    public void setB2b(boolean b2b) {
        this.b2b = b2b;
    }

    public boolean isWodm() {
        return wodm;
    }

    public void setWodm(boolean wodm) {
        this.wodm = wodm;
    }

    public int getActivitySummaryFlag() {
        return activitySummaryFlag;
    }

    public void setActivitySummaryFlag(int activitySummaryFlag) {
        this.activitySummaryFlag = activitySummaryFlag;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getContactStatus() {
        return contactStatus;
    }

    public void setContactStatus(String contactStatus) {
        this.contactStatus = contactStatus;
    }

    public Date getDashBoardEndDate() {
        return dashBoardEndDate;
    }

    public void setDashBoardEndDate(Date dashBoardEndDate) {
        this.dashBoardEndDate = dashBoardEndDate;
    }

    public Date getDashBoardStartDate() {
        return dashBoardStartDate;
    }

    public void setDashBoardStartDate(Date dashBoardStartDate) {
        this.dashBoardStartDate = dashBoardStartDate;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    /**
     * @return the hybris
     */
    public boolean isHybris() {
        return hybris;
    }

    /**
     * @param hybris the hybris to set
     */
    public void setHybris(boolean hybris) {
        this.hybris = hybris;
    }
    

    
}
