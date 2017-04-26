/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.marketing.library;

import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.AuthorizationManager;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.Properties;
import com.mss.mirage.util.ServiceLocator;
import com.mss.mirage.util.ServiceLocatorException;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

/**
 *
 * @author miracle
 */
public class LibraryAction extends ActionSupport implements ServletRequestAware ,ServletResponseAware {

    private HttpServletRequest httpServletRequest;
    /** The resultType is used for storing type of message. */
    private String resultType;
    /** The resultMessage is used for storing resulted message. */
    private String resultMessage;
    private int userRoleId;
    private Map trackNamesMap;
    private String resourceTitle;
    private String resourceType;
    private String gateContent;
    private String resourcePrimaryTrack;
    private String resourceSecondaryTrack;
    private String resourceIndustry;
    private String resourceDescription;
    private String breadCrumbHeading;
    private String webPageCreationdetails;
    private String searchTrack;
    private String searchIndustry;
    private String searchType;
    private String createdDateTo;
    private String createdDateFrom;
    private String DateOfPublish;

    /*
     * File upload fileds
     */
    private File libraryFile;
    private String libraryFileContentType;
    private String libraryFileFileName;
    private String bodyTitle;
    private String filePath;
    private String fileName;
    private int libraryId;
    private Map speakersMapExceptEventSpeakerMap = new TreeMap();
    private String primaryAuthor;
    private String author2;
    private String author3;
    private String bodyContent;
    private String libraryFlag;
    private String currentAction;
    private String phpFileName;
    private String resourceStatus;
    private String createdBy;
    private int downloadFlag;
    private boolean gatedContent;
    private boolean isResourceDownloadable;

     public InputStream inputStream;
    public OutputStream outputStream;
    public HttpServletResponse httpServletResponse;
    
    private String searchTitle;
    private String searchFileName;
    private String customerName;
    public String getResources() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY", userRoleId)) {
                try {
                    setTrackNamesMap(ServiceLocator.getMarketingService().getLkpTrackNamesMap());
                    if(httpServletRequest.getSession(false).getAttribute("libraryManagementlist")!=null){
                    httpServletRequest.getSession(false).removeAttribute("libraryManagementlist");
                }
                    //setDateOfPublish(DateUtility.getInstance().getCurrentMySqlDate());
                    //setQmeetYear(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
                    //  setQmeetMap(ServiceLocator.getMarketingService().getQmeetMap(this));
                    resultType = SUCCESS;

                } catch (Exception ex) {
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
    }
    /*
     * Libary add page
     * Author : Santosh Kola
     * Date : 08/11/2015
     */

    public String addLibrary() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY", userRoleId)) {
                try {
                    setDateOfPublish(DateUtility.getInstance().getCurrentMySqlDateTime1());
                    setCurrentAction("doAddLibrary");
                    setIsResourceDownloadable(true);
                    setGatedContent(true);
                    setResultMessage(getResultMessage());
                    setTrackNamesMap(ServiceLocator.getMarketingService().getLkpTrackNamesMap());
                  //  Map eventSpeakerMap = new TreeMap();
                  //  setSpeakersMapExceptEventSpeakerMap(ServiceLocator.getMarketingService().getAllPeaopleExceptEventSpeakers(eventSpeakerMap));
                    resultType = SUCCESS;
                } catch (Exception ex) {
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
    }
    //doAddLibrary

    public String doAddLibrary() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY", userRoleId)) {
                try {
                    // setTrackNamesMap(ServiceLocator.getMarketingService().getLkpTrackNamesMap());

                    System.out.println("Gated Content-->" + getGatedContent());
                    System.out.println("Is Resource Download-->" + getIsResourceDownloadable());

                    setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

                    if (getLibraryFile() != null) {
                        String destPath = Properties.getProperty("LIBRARY.BASE.PATH") + "/" + getResourceType().replaceAll(" ", "");

                        File destFileDir = new File(destPath);
                        if (!destFileDir.exists()) {
                            destFileDir.mkdirs();
                        }
                        File destFile = new File(destPath + "/"+ getLibraryFileFileName());
                        FileUtils.copyFile(getLibraryFile(), destFile);
                        setFilePath(destPath + "/"+ getLibraryFileFileName());
                        setFileName(getLibraryFileFileName());
                    } else {
                        setFilePath("");
                        setFileName("");
                    }

                    setResultMessage(ServiceLocator.getLiraryService().doAddLibrary(this));


                    resultType = SUCCESS;
                } catch (Exception ex) {
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
    }

//LIBRARY.BASE.PATH
    public String libraryManagementSearch() {

        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY", userRoleId)) {
                try {

                    List mainList = null;
                    setTrackNamesMap(ServiceLocator.getMarketingService().getLkpTrackNamesMap());

if(httpServletRequest.getSession(false).getAttribute("libraryManagementlist")!=null){
                    httpServletRequest.getSession(false).removeAttribute("libraryManagementlist");
                }
                    // mainList = ServiceLocator.getMarketingService().doCompletedEventSearch(this);
                    mainList = ServiceLocator.getLiraryService().doLibraryManagementSearch(this);

                    httpServletRequest.getSession(false).setAttribute("libraryManagementlist", mainList);
                    resultType = SUCCESS;

                } catch (Exception ex) {
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
    }

    public String editLibrary() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY", userRoleId)) {
                try {
                    // setDateOfPublish(DateUtility.getInstance().getCurrentMySqlDate());
                    setResultMessage(getResultMessage());
                    setTrackNamesMap(ServiceLocator.getMarketingService().getLkpTrackNamesMap());
                  //  Map eventSpeakerMap = new TreeMap();
               //     setSpeakersMapExceptEventSpeakerMap(ServiceLocator.getMarketingService().getAllPeaopleExceptEventSpeakers(eventSpeakerMap));
                    setCurrentAction("doUpdateLibrary");
                    ServiceLocator.getLiraryService().doEditLibrary(this);
                    setResultMessage(getResultMessage());
                   // System.out.println("editLibrary");
                    resultType = SUCCESS;
                } catch (Exception ex) {
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
    }

    public String doUpdateLibrary() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY", userRoleId)) {
                try {
                    setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                    setTrackNamesMap(ServiceLocator.getMarketingService().getLkpTrackNamesMap());
                    setResultMessage(ServiceLocator.getLiraryService().doUpdateLibrary(this));
                    setCurrentAction("doUpdateLibrary");
                    setLibraryId(getLibraryId());
                    
                   // Map eventSpeakerMap = new TreeMap();
                  //  setSpeakersMapExceptEventSpeakerMap(ServiceLocator.getMarketingService().getAllPeaopleExceptEventSpeakers(eventSpeakerMap));
                    resultType = SUCCESS;
                } catch (Exception ex) {
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
    }
public String downloadLibraryAttachment(){
    try {
           // this.setId(Integer.parseInt(httpServletRequest.getParameter("Id").toString()));
            
           // this.setAttachmentLocation(ServiceLocator.getProjIssuesService().getAttachmentLocation(this.getId()));
            // setResultMessage();
            String location=ServiceLocator.getLiraryService().downloadLibraryAttachment(this);
            
            httpServletResponse.setContentType("application/force-download");
            
            File file = new File(location);
            fileName = file.getName();
            inputStream = new FileInputStream(file);
            outputStream =  httpServletResponse.getOutputStream();
            httpServletResponse.setHeader("Content-Disposition","attachment; filename=\"" + fileName +"\"");
            int noOfBytesRead = 0;
            byte[] byteArray = null;
            while(true) {
                byteArray = new byte[1024];
                noOfBytesRead = inputStream.read(byteArray);
                if(noOfBytesRead==-1) break;
                outputStream.write(byteArray, 0, noOfBytesRead);
            }
        }catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }catch (IOException ex) {
            ex.printStackTrace();
        }catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        }finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    return null;
}

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    /**
     * @return the resultType
     */
    public String getResultType() {
        return resultType;
    }

    /**
     * @param resultType the resultType to set
     */
    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    /**
     * @return the resultMessage
     */
    public String getResultMessage() {
        return resultMessage;
    }

    /**
     * @param resultMessage the resultMessage to set
     */
    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    /**
     * @return the trackNamesMap
     */
    public Map getTrackNamesMap() {
        return trackNamesMap;
    }

    /**
     * @param trackNamesMap the trackNamesMap to set
     */
    public void setTrackNamesMap(Map trackNamesMap) {
        this.trackNamesMap = trackNamesMap;
    }

    /**
     * @return the resourceTitle
     */
    public String getResourceTitle() {
        return resourceTitle;
    }

    /**
     * @param resourceTitle the resourceTitle to set
     */
    public void setResourceTitle(String resourceTitle) {
        this.resourceTitle = resourceTitle;
    }

    /**
     * @return the resourceType
     */
    public String getResourceType() {
        return resourceType;
    }

    /**
     * @param resourceType the resourceType to set
     */
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * @return the gateContent
     */
    public String getGateContent() {
        return gateContent;
    }

    /**
     * @param gateContent the gateContent to set
     */
    public void setGateContent(String gateContent) {
        this.gateContent = gateContent;
    }

    /**
     * @return the resourcePrimaryTrack
     */
    public String getResourcePrimaryTrack() {
        return resourcePrimaryTrack;
    }

    /**
     * @param resourcePrimaryTrack the resourcePrimaryTrack to set
     */
    public void setResourcePrimaryTrack(String resourcePrimaryTrack) {
        this.resourcePrimaryTrack = resourcePrimaryTrack;
    }

    /**
     * @return the resourceSecondaryTrack
     */
    public String getResourceSecondaryTrack() {
        return resourceSecondaryTrack;
    }

    /**
     * @param resourceSecondaryTrack the resourceSecondaryTrack to set
     */
    public void setResourceSecondaryTrack(String resourceSecondaryTrack) {
        this.resourceSecondaryTrack = resourceSecondaryTrack;
    }

    /**
     * @return the resourceIndustry
     */
    public String getResourceIndustry() {
        return resourceIndustry;
    }

    /**
     * @param resourceIndustry the resourceIndustry to set
     */
    public void setResourceIndustry(String resourceIndustry) {
        this.resourceIndustry = resourceIndustry;
    }

    /**
     * @return the resourceDescription
     */
    public String getResourceDescription() {
        return resourceDescription;
    }

    /**
     * @param resourceDescription the resourceDescription to set
     */
    public void setResourceDescription(String resourceDescription) {
        this.resourceDescription = resourceDescription;
    }

    /**
     * @return the breadCrumbHeading
     */
    public String getBreadCrumbHeading() {
        return breadCrumbHeading;
    }

    /**
     * @param breadCrumbHeading the breadCrumbHeading to set
     */
    public void setBreadCrumbHeading(String breadCrumbHeading) {
        this.breadCrumbHeading = breadCrumbHeading;
    }

    /**
     * @return the webPageCreationdetails
     */
    public String getWebPageCreationdetails() {
        return webPageCreationdetails;
    }

    /**
     * @param webPageCreationdetails the webPageCreationdetails to set
     */
    public void setWebPageCreationdetails(String webPageCreationdetails) {
        this.webPageCreationdetails = webPageCreationdetails;
    }

    /**
     * @return the searchTrack
     */
    public String getSearchTrack() {
        return searchTrack;
    }

    /**
     * @param searchTrack the searchTrack to set
     */
    public void setSearchTrack(String searchTrack) {
        this.searchTrack = searchTrack;
    }

    /**
     * @return the searchIndustry
     */
    public String getSearchIndustry() {
        return searchIndustry;
    }

    /**
     * @param searchIndustry the searchIndustry to set
     */
    public void setSearchIndustry(String searchIndustry) {
        this.searchIndustry = searchIndustry;
    }

    /**
     * @return the searchType
     */
    public String getSearchType() {
        return searchType;
    }

    /**
     * @param searchType the searchType to set
     */
    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    /**
     * @return the createdDateTo
     */
    public String getCreatedDateTo() {
        return createdDateTo;
    }

    /**
     * @param createdDateTo the createdDateTo to set
     */
    public void setCreatedDateTo(String createdDateTo) {
        this.createdDateTo = createdDateTo;
    }

    /**
     * @return the createdDateFrom
     */
    public String getCreatedDateFrom() {
        return createdDateFrom;
    }

    /**
     * @param createdDateFrom the createdDateFrom to set
     */
    public void setCreatedDateFrom(String createdDateFrom) {
        this.createdDateFrom = createdDateFrom;
    }

    /**
     * @return the libraryFile
     */
    public File getLibraryFile() {
        return libraryFile;
    }

    /**
     * @param libraryFile the libraryFile to set
     */
    public void setLibraryFile(File libraryFile) {
        this.libraryFile = libraryFile;
    }

    /**
     * @return the libraryFileContentType
     */
    public String getLibraryFileContentType() {
        return libraryFileContentType;
    }

    /**
     * @param libraryFileContentType the libraryFileContentType to set
     */
    public void setLibraryFileContentType(String libraryFileContentType) {
        this.libraryFileContentType = libraryFileContentType;
    }

    /**
     * @return the libraryFileFileName
     */
    public String getLibraryFileFileName() {
        return libraryFileFileName;
    }

    /**
     * @param libraryFileFileName the libraryFileFileName to set
     */
    public void setLibraryFileFileName(String libraryFileFileName) {
        this.libraryFileFileName = libraryFileFileName;
    }

    /**
     * @return the DateOfPublish
     */
    public String getDateOfPublish() {
        return DateOfPublish;
    }

    /**
     * @param DateOfPublish the DateOfPublish to set
     */
    public void setDateOfPublish(String DateOfPublish) {
        this.DateOfPublish = DateOfPublish;
    }

    /**
     * @return the bodyTitle
     */
    public String getBodyTitle() {
        return bodyTitle;
    }

    /**
     * @param bodyTitle the bodyTitle to set
     */
    public void setBodyTitle(String bodyTitle) {
        this.bodyTitle = bodyTitle;
    }

    /**
     * @return the filePath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * @param filePath the filePath to set
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the libraryId
     */
    public int getLibraryId() {
        return libraryId;
    }

    /**
     * @param libraryId the libraryId to set
     */
    public void setLibraryId(int libraryId) {
        this.libraryId = libraryId;
    }

    /**
     * @return the speakersMapExceptEventSpeakerMap
     */
    public Map getSpeakersMapExceptEventSpeakerMap() {
        return speakersMapExceptEventSpeakerMap;
    }

    /**
     * @param speakersMapExceptEventSpeakerMap the speakersMapExceptEventSpeakerMap to set
     */
    public void setSpeakersMapExceptEventSpeakerMap(Map speakersMapExceptEventSpeakerMap) {
        this.speakersMapExceptEventSpeakerMap = speakersMapExceptEventSpeakerMap;
    }

    /**
     * @return the primaryAuthor
     */
    public String getPrimaryAuthor() {
        return primaryAuthor;
    }

    /**
     * @param primaryAuthor the primaryAuthor to set
     */
    public void setPrimaryAuthor(String primaryAuthor) {
        this.primaryAuthor = primaryAuthor;
    }

    /**
     * @return the author2
     */
    public String getAuthor2() {
        return author2;
    }

    /**
     * @param author2 the author2 to set
     */
    public void setAuthor2(String author2) {
        this.author2 = author2;
    }

    /**
     * @return the author3
     */
    public String getAuthor3() {
        return author3;
    }

    /**
     * @param author3 the author3 to set
     */
    public void setAuthor3(String author3) {
        this.author3 = author3;
    }

    /**
     * @return the bodyContent
     */
    public String getBodyContent() {
        return bodyContent;
    }

    /**
     * @param bodyContent the bodyContent to set
     */
    public void setBodyContent(String bodyContent) {
        this.bodyContent = bodyContent;
    }

    /**
     * @return the libraryFlag
     */
    public String getLibraryFlag() {
        return libraryFlag;
    }

    /**
     * @param libraryFlag the libraryFlag to set
     */
    public void setLibraryFlag(String libraryFlag) {
        this.libraryFlag = libraryFlag;
    }

    /**
     * @return the currentAction
     */
    public String getCurrentAction() {
        return currentAction;
    }

    /**
     * @param currentAction the currentAction to set
     */
    public void setCurrentAction(String currentAction) {
        this.currentAction = currentAction;
    }

    /**
     * @return the phpFileName
     */
    public String getPhpFileName() {
        return phpFileName;
    }

    /**
     * @param phpFileName the phpFileName to set
     */
    public void setPhpFileName(String phpFileName) {
        this.phpFileName = phpFileName;
    }

    /**
     * @return the resourceStatus
     */
    public String getResourceStatus() {
        return resourceStatus;
    }

    /**
     * @param resourceStatus the resourceStatus to set
     */
    public void setResourceStatus(String resourceStatus) {
        this.resourceStatus = resourceStatus;
    }

    /**
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

   
    /**
     * @return the gatedContent
     */
    public boolean getGatedContent() {
        return gatedContent;
    }

    /**
     * @param gatedContent the gatedContent to set
     */
    public void setGatedContent(boolean gatedContent) {
        this.gatedContent = gatedContent;
    }

    /**
     * @return the isResourceDownloadable
     */
    public boolean getIsResourceDownloadable() {
        return isResourceDownloadable;
    }

    /**
     * @param isResourceDownloadable the isResourceDownloadable to set
     */
    public void setIsResourceDownloadable(boolean isResourceDownloadable) {
        this.isResourceDownloadable = isResourceDownloadable;
    }

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    /**
     * @return the searchTitle
     */
    public String getSearchTitle() {
        return searchTitle;
    }

    /**
     * @param searchTitle the searchTitle to set
     */
    public void setSearchTitle(String searchTitle) {
        this.searchTitle = searchTitle;
    }

    /**
     * @return the searchFileName
     */
    public String getSearchFileName() {
        return searchFileName;
    }

    /**
     * @param searchFileName the searchFileName to set
     */
    public void setSearchFileName(String searchFileName) {
        this.searchFileName = searchFileName;
    }

    /**
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
}
