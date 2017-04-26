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
 * File: ExceptionMesssages.java
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
public interface ErrorMessages {

	public static final String CANNOT_READ_DATA = "Cannot Read Data. REASON: ";
	public static final String CANNOT_OPEN_CONNECTION =
		"Cannot Open Connection REASON: ";
	public static final String CANNOT_INSERT_DATA =
		"Cannot Insert Data REASON: ";
	public static final String CANNOT_UPDATE_DATA =
		"Cannot Update Data REASON: ";
	public static final String CANNOT_DELETE_DATA =
		"Cannot Delete Data REASON: ";
	public static final String CANNOT_CLOSE_CONNECTION =
		"Cannot Close Connection REASON: ";
	public static final String CANNOT_GET_DATASOURCE="Cannot get Data Source REASON:";
        public static final String CANNOT_GET_SESSIONFACTORY="Cannot get Hibernate Session Factory object REASON:";
	public static final String ID_IS_NULL = "Id is null.";
	public static final String CANNOT_FIND_LOOKUP_DATA = "Cannot get Lookup Data from Database";
        
        public static final String HN_CANNOT_CONFIGURE = "Cannot confire mirage.cfg.xml properly";
        
	
}
