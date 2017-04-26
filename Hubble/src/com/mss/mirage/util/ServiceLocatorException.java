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
 * File: ServiceLocatorException.java
 * 
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
package com.mss.mirage.util;


/**
 * @author MrutyumjayaRao Chennu<mchennu@miraclesoft.com>
 *
 *This Class.... ENTER THE DESCRIPTION HERE
 */
public class ServiceLocatorException extends Exception {

	/**
	 * 
	 */
	public ServiceLocatorException() {
		super();

	}

	/**
	 * @param message
	 */
	public ServiceLocatorException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ServiceLocatorException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public ServiceLocatorException(Throwable cause) {
		super(cause);
	}

}
