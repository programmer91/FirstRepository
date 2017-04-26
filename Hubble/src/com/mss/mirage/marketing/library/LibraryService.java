/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.marketing.library;

import com.mss.mirage.util.ServiceLocatorException;
import java.util.List;

/**
 *
 * @author miracle
 */
public interface LibraryService {
   public String doAddLibrary(LibraryAction libraryAction) throws ServiceLocatorException;
   public List doLibraryManagementSearch(LibraryAction libraryAction) throws ServiceLocatorException;
    public String doEditLibrary(LibraryAction libraryAction) throws ServiceLocatorException;
    public String doUpdateLibrary(LibraryAction libraryAction) throws ServiceLocatorException;
    public String downloadLibraryAttachment(LibraryAction libraryAction) throws ServiceLocatorException;
}
