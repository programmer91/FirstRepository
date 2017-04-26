/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  October 18, 2007, 8:47 PM
 *
 * Author  : MrutyumjayaRao Chennu<mchennu@miraclesoft.com>
 *
 * File    : ExceptionToListUtility.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MrutyumjayaRao Chennu<mchennu@miraclesoft.com>
 *
 * This Class.... ENTER THE DESCRIPTION HERE
 */
public class ExceptionToListUtility {
    
    /** Creates a new instance of ExceptionToListUtility */
    public ExceptionToListUtility() {
    }
    
    public static List errorMessages(Throwable ex) {
        
        List errorMsgList= new ArrayList();
        
        String errorMsg=ex.getMessage();
        
        // for knowing the error message
        int index=errorMsg.indexOf(":",0);
        String msg=errorMsg.substring((index+1),errorMsg.length());
        String excepType=errorMsg.substring(0,index);
        
        // for knowing the Line number and location
        StackTraceElement[] stackTraceElement = ex.getStackTrace();
        String fileFind=stackTraceElement[0].toString();
        int fileIndex=fileFind.indexOf("(",0);
        String pakagePart=fileFind.substring(0,fileIndex);
        String lineNopart=fileFind.substring((fileIndex+1),(fileFind.length()-1));
        int lineNoIndex=lineNopart.indexOf(":");
        String lineNo=lineNopart.substring((lineNoIndex+1),lineNopart.length());
        int methodIndex=pakagePart.lastIndexOf(".");
        String className=pakagePart.substring(0,methodIndex);
        errorMsgList.add(msg);
        errorMsgList.add(excepType);
        errorMsgList.add(lineNo);
        errorMsgList.add(className);
        return errorMsgList;
    }
}
