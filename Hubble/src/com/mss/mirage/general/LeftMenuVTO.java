/*
 * @(#)LeftMenuVTO.java 1.0 Nov 01, 2007
 *
 * Copyright 2006 Miracle Software Systems(INDIA) Pvt Ltd. All rights reserved.
 *
 */
package com.mss.mirage.general;


/**
 *
 * @author RajaReddy.Andra <a href="mailto:randra@miraclesoft.com">randra@miraclesoft.com</a>
 *
 * @version 1.0 Nov 01, 2007
 */
public class LeftMenuVTO {
    
    /** Creates a new instance of LeftMenuVTO */
    public LeftMenuVTO() {
    }
    
    /*@param screenAction used to store Screen Actions*/
    private String screenAction;
    
    /*@param screenTitle used to store Screen Names*/
    private String screenTitle;
    
    /*this is bean section*/
    /*getScreenAction() used to get action of the screen*/
    public String getScreenAction() {
        return screenAction;
    }
    
    /*setScreenAction(String screenAction) used to set action of the screen*/
    public void setScreenAction(String screenAction) {
        this.screenAction = screenAction;
    }
    
    /*getScreenTitle() used to get title of the screen*/
    public String getScreenTitle() {
        return screenTitle;
    }
    
    /*setScreenTitle(String screenTitle) used to set title  of the screen*/
    public void setScreenTitle(String screenTitle) {
        this.screenTitle = screenTitle;
    }
    
}