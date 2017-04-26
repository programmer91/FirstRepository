/*
 * @(#)GeneralService.java 1.0 Nov 01, 2007
 *
 * Copyright 2006 Miracle Software Systems(INDIA) Pvt Ltd. All rights reserved.
 *
 */

package com.mss.mirage.cre.general;

import com.mss.mirage.cre.CreAction;
import com.mss.mirage.util.ServiceLocatorException;

/**
 *
 * @author RajaReddy.Andra <a href="mailto:randra@miraclesoft.com">randra@miraclesoft.com</a>
 *
 * @version 1.0 Nov 01, 2007
 *
 * @see com.mss.mirage.util.ServiceLocatorException
 *
 */
public interface CreGeneralService {
   
    public boolean creUserCheckExist(CreRegistrationAction creAction) throws ServiceLocatorException;
    public int addCreUser(CreRegistrationAction creAction) throws ServiceLocatorException;
}




