/*******************************************************************************

 * /*

 * @(#)GreenSheetService.java	September 26, 2007, 12:13 AM

 *

 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.

 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.

 */



package com.mss.mirage.crm.greensheets;



import com.mss.mirage.util.ServiceLocatorException;

//new
import javax.servlet.http.HttpSession;

/**

 * The <code>GreenSheetService</code>  class is used for getting new Employee details from

 * <i>GreenSheetAdd.jsp</i> page.

 * <p>

 * Then it invokes setter methods in <code>AddEmployee</code> class and invokes doEdit() method

 * in <code>GreenSheetService</code> for inserting employee details in TBLGREENSHEETS table.

 *

 * @author Suresh Nalla <a href="mailto:snalla@miraclesoft.com">snalla@miraclesoft.com</a>

 *

 * @version 1.0 November 29, 2007

 *

 * @see com.mss.mirage.crm.greensheets.GreenSheetService

 */



/**

 *

 * @author miracle

 */





public interface GreenSheetService {

    

    /**  This method can be used for adding the new GreenSheet Details.

     *

     * @param greenSheetAction A GreenSheetAction reference.

     *

     * @return boolean.

     */    

    public boolean insertGreenSheet(GreenSheetAction greenSheetAction) throws ServiceLocatorException;

    

    /**  

     * This method can be used for getting GreenSheet Details by Id.

     *

     * @param Id GreenSheet Id

     *

     * @return GreenSheetVTO

     */    

    public GreenSheetVTO getGreenSheetByID(int Id,HttpSession httpsession) throws ServiceLocatorException;

    

    /**  This method can be used for editing the GreenSheet Details.

     *

     * @param greenSheetAction A GreenSheetAction reference.

     *

     * @return boolean.

     */ 

    public boolean editGreenSheet(GreenSheetAction greenSheetAction) throws ServiceLocatorException;

    

    /**  This method can be used to seacrh the Appraisal based on the id

     *  return the GreenSheetAction object.

     *

     * @param id A String.

     *

     * @return GreenSheetAction.

     */

    /*int addNewAccount(GreenSheetAction greenSheetAction);*/

    

}

