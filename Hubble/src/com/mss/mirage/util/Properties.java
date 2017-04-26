/******************************************************************************
 * 
 * Project: Mirage V2
 * 
 * Package: com.msws.mirage.services
 * 
 * Date: September 6, 2007
 * 
 * Author: MrutyumjayaRao Chennu<mchennu@miraclesoft.com>
 * 
 * File: Properties.java
 * 
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
package com.mss.mirage.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author MrutyumjayaRao Chennu<mchennu@miraclesoft.com>
 *
 *This Class.... ENTER THE DESCRIPTION HERE
 */
public class Properties {

	private static final String BUNDLE_NAME = "com/mss/mirage/config/mirage";
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
