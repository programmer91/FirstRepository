 /*
 * AddressService.java
 *
 * Created on November 26, 2007, 3:26 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.employee.address;

import com.mss.mirage.util.ServiceLocatorException;

/**
 *
 * @author __RajaReddy.Andra
 *
 */
public interface AddressService {
     
    
        public int checkAction(int empid)throws ServiceLocatorException;
     
    /**
      * The addOrUpdateAddress(AddressAction addressPojo) is used for getting activityVTO.
      * @throws ServiceLocatorException.
      * @return boolean Variable.
      * {@link com.mss.mirage.util.ServiceLocatorException}
      * {@ling com.mss.mirage.employee.address.AddressAction}
      */
    public int addOrUpdateAddress(AddressAction addressPojo)throws ServiceLocatorException;
    
    
    /**
	* The getAddress(int addressId) is used for retrieving activity.
	* @throws ServiceLocatorException.
	* @return AddressVTO variable.
	* {@link com.mss.mirage.util.ServiceLocatorException}
	* {@link com.mss.mirage.crm.activities.ActivityVTO}
	*/
    public AddressVTO getAddress(int addressId)throws ServiceLocatorException;
    
}
