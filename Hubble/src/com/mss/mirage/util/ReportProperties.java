/*
 * ReportProperties.java
 *
 * Created on November 22, 2007, 8:55 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 *
 * @author miracle
 */
public class ReportProperties {
    
    private static final String BUNDLE_NAME = "com/mss/mirage/config/reports";
    private static final ResourceBundle RESOURCE_BUNDLE =
        ResourceBundle.getBundle(BUNDLE_NAME);
    
    public static String getProperty(String property) {
        try {
            return RESOURCE_BUNDLE.getString(property);
        } catch (MissingResourceException e) {
            return '!' + property + '!';
        }
    }
}
