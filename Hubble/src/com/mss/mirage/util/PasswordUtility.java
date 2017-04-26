/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  September 19, 2007, 11:31 PM
 *
 * Author  : Praveen Kumar Ralla<pralla@miraclesoft.com>
 *
 * File    : EncriptDecriptPwd.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Praveen Kumar Ralla<pralla@miraclesoft.com>
 *
 * This Class.... ENTER THE DESCRIPTION HERE
 */
public class PasswordUtility {
   
private static MessageDigest digester;
    static {
        try {
            digester = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /** Creates a new instance of EncriptDecriptPwd */
    public PasswordUtility() {
    }
    public static void main(String rags[]){
     System.out.println(decryptPwd("@160@198@210@158@198@204@198@104@112@100@114@74"));
      
      System.out.println(encryptPwd("grvc@2k17"));
      //grvc@2k17
    }
  
    /** Creates a new instance of FormulaEncrypt */
    public static String encryptPwd(String src) {
        //Converting String to array
        
        char  asciiarr[] = src.toCharArray();
        
        //Finding lnegth and converting into int
        
        int encryasciiarr[]= new int[src.length()];
        
        String encrypt="";
        
        for(int i=0;i<asciiarr.length;i++) {
            // System.out.println("The origianl value are"+ (int)asciiarr[i]);
            int asciichar = (int) asciiarr[i]+2;
            int accharmul2 = asciichar*2;
            encryasciiarr[i] = accharmul2;
            
        }
        
        for(int j=0;j<encryasciiarr.length;j++) {
            
            //System.out.println("The ascii char are"+encryasciiarr[j]);
            encrypt = encrypt+"@"+encryasciiarr[j];
        }
        
        // System.out.println("The enc is"+encrypt);
        
        return encrypt;
    }//end of the encrypt method
    
   
    //this is Dycrypt Method
    public static  String decryptPwd(String enc) {
        
        String asarr[] = enc.split("@");
        //variable declaration
        
        int inval=0;
        String instr=".";
        String instr1="";
        String orval="";
        for(int lk=0;lk<asarr.length;lk++) {
            
            //System.out.println("Values are"+asarr[lk]);
            if(asarr[lk].equalsIgnoreCase(""))
                
            {
                
                instr = asarr[lk];
                //System.out.println("The value with null"+instr);
                
            } else {
                instr1 = asarr[lk];
                inval = Integer.parseInt(instr1);
                //System.out.println("Int values are"+inval);
                int divval = inval/2;
                int minusval = divval-2;
                
                orval = orval+(char) minusval;
                
                
            }
           //  System.out.println("orignal Values are"+orval.trim());
            
        }
        
        
        return orval;
        
    }//end of the dycript method
    
    public static String md5EncriptionPassword(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("String to encript cannot be null or zero length");
        }

        digester.update(str.getBytes());
        byte[] hash = digester.digest();
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            if ((0xff & hash[i]) < 0x10) {
                hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
            }
            else {
                hexString.append(Integer.toHexString(0xFF & hash[i]));
            }
        }
        return hexString.toString();
    }

 
}
