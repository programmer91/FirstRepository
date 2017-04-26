/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.marketing.surveyform;

import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.AuthorizationManager;
import com.mss.mirage.util.DateUtility;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

/**
 *
 * @author miracle
 */
public class SurveyFormAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    private HttpServletRequest httpServletRequest;
    /** The resultType is used for storing type of message. */
    private String resultType;
    /** The resultMessage is used for storing resulted message. */
    private String resultMessage;
    private int userRoleId;
    private String currentAction;
    private String createdBy;
    private String surveyType;
    private String surveyTitle;
    private String expiryDate;
    private String surveyDescription;
    private String currStatus;
    private String createdDateFrom;
    private String createdDateTo;
    private String surveyId;
    private String createdDate;
    private List optionTypeList;
    private String questionTitle;
    private String optionType;
    private String querySequence;
    private String questionId;
    private int optionCount;
    private String optionLabel1;
    private String optionLabel2;
    private String optionLabel3;
    private String optionLabel4;
    private String optionLabel5;
    private String optionLabel6;
    private int optionSequence1;
    private int optionSequence2;
    private int optionSequence3;
    private int optionSequence4;
    private int optionSequence5;
    private int optionSequence6;
    public InputStream inputStream;
    public OutputStream outputStream;
    public HttpServletResponse httpServletResponse;
    private String fileName;
    private int surveyInfoId;
    private String currentDate;
    private int totalQuestions;
    private String fullName;
    private String phoneNumber;
    private String emailId;
    private Map questionMap;
    private String searchQuestionId;
    private String searchQuestion;
    private String searchQuestionId2;
    private String searchQuestion2;
    //--------------
    private int questionCount;
    private int questionId1;
    private int querySequence1;
    private int questionId2;
    private int querySequence2;
    private int questionId3;
    private int querySequence3;
    private int questionId4;
    private int querySequence4;
    private int questionId5;
    private int querySequence5;
    private int questionId6;
    private int querySequence6;
    private int questionId7;
    private int querySequence7;
    private int questionId8;
    private int querySequence8;
    private int questionId9;
    private int querySequence9;
    private String loginId;
    private Map teamMap;
    private String surveyFormCustomizedTextBox;
    private boolean customCheckBox;
    private boolean anonymousCheckBox;
    private int attachmentAvailable = 0;
    private int maxSubmissions = 10;
    private boolean allowDuplicates = true;

    public String getSearchQuestion2() {
        return searchQuestion2;
    }

    public void setSearchQuestion2(String searchQuestion2) {
        this.searchQuestion2 = searchQuestion2;
    }

    public String getSearchQuestionId2() {
        return searchQuestionId2;
    }

    public void setSearchQuestionId2(String searchQuestionId2) {
        this.searchQuestionId2 = searchQuestionId2;
    }

    public String surveyFormList() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("SURVEY_FORM_ACTIVITY", userRoleId) || AuthorizationManager.getInstance().isAuthorizedForSurveyForm("SURVEY_FORM_ACCESS", userId)) {
                try {
                    // setTrackNamesMap(ServiceLocator.getMarketingService().getLkpTrackNamesMap());
                    if (httpServletRequest.getSession(false).getAttribute("surveyFormSearchList") != null) {
                        httpServletRequest.getSession(false).removeAttribute("surveyFormSearchList");
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

    public String addSurveyForm() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("SURVEY_FORM_ACTIVITY", userRoleId) || AuthorizationManager.getInstance().isAuthorizedForSurveyForm("SURVEY_FORM_ACCESS", userId)) {
                try {
                    // setDateOfPublish(DateUtility.getInstance().getCurrentMySqlDateTime1());
                    setCurrentAction("doAddSurveyForm");
                    setResultMessage(getResultMessage());
                    setCurrentDate(DateUtility.getInstance().getCurrentMySqlDate());
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
    //doAddSurveyForm

    public String doAddSurveyForm() {
        //  System.out.println("----------doAddSurveyForm------");
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("SURVEY_FORM_ACTIVITY", userRoleId) || AuthorizationManager.getInstance().isAuthorizedForSurveyForm("SURVEY_FORM_ACCESS", userId)) {
                try {
                    setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                    setCurrentDate(DateUtility.getInstance().getCurrentMySqlDate());
                    setResultMessage(ServiceLocator.getSurveyFormService().doAddSurveyForm(this));
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
//surveyFormListSearch

    public String surveyFormListSearch() {

        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();

            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("SURVEY_FORM_ACTIVITY", userRoleId) || AuthorizationManager.getInstance().isAuthorizedForSurveyForm("SURVEY_FORM_ACCESS", userId)) {
                try {

                    List mainList = null;
                    setResultMessage(getResultMessage());

                    if (httpServletRequest.getSession(false).getAttribute("surveyFormSearchList") != null) {
                        httpServletRequest.getSession(false).removeAttribute("surveyFormSearchList");
                    }
                    setLoginId(userId);

                    teamMap = (Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP);
                    // System.out.println("teamMap-->"+teamMap);
                    // mainList = ServiceLocator.getMarketingService().doCompletedEventSearch(this);
                    mainList = ServiceLocator.getSurveyFormService().doSurveyFormSearch(this, httpServletRequest);


                    //setTeamMap(teamMap);
                    httpServletRequest.getSession(false).setAttribute("surveyFormSearchList", mainList);
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

//editSurveyForm
    public String editSurveyForm() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("SURVEY_FORM_ACTIVITY", userRoleId) || AuthorizationManager.getInstance().isAuthorizedForSurveyForm("SURVEY_FORM_ACCESS", userId)) {
                try {
                    // setDateOfPublish(DateUtility.getInstance().getCurrentMySqlDate());
                    setResultMessage(getResultMessage());
                    setCurrentDate(DateUtility.getInstance().getCurrentMySqlDate());
                    //  Map eventSpeakerMap = new TreeMap();
                    //     setSpeakersMapExceptEventSpeakerMap(ServiceLocator.getMarketingService().getAllPeaopleExceptEventSpeakers(eventSpeakerMap));
                    setCurrentAction("doUpdateSurveyForm");
                    ServiceLocator.getSurveyFormService().editSurveyForm(this);
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
    //doUpdateSurveyForm

    public String doUpdateSurveyForm() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("SURVEY_FORM_ACTIVITY", userRoleId) || AuthorizationManager.getInstance().isAuthorizedForSurveyForm("SURVEY_FORM_ACCESS", userId)) {
                try {
                    setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                    setCurrentAction("doUpdateSurveyForm");
                    setResultMessage(ServiceLocator.getSurveyFormService().doUpdateSurveyForm(this));
                    setCurrentDate(DateUtility.getInstance().getCurrentMySqlDate());
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

    //getQuestionnaireList
    public String getQuestionnaireList() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("SURVEY_FORM_ACTIVITY", userRoleId) || AuthorizationManager.getInstance().isAuthorizedForSurveyForm("SURVEY_FORM_ACCESS", userId)) {
                try {
                    // setDateOfPublish(DateUtility.getInstance().getCurrentMySqlDate());

                    if (httpServletRequest.getSession(false).getAttribute("surveyFormSearchList") != null) {
                        httpServletRequest.getSession(false).removeAttribute("surveyFormSearchList");
                    }
                    setSurveyId(getSurveyId());
                    ServiceLocator.getSurveyFormService().editSurveyForm(this);
                    //getQuestionnaireList
                    List mainList = null;
                    setOptionTypeList(ServiceLocator.getSurveyFormService().getOptionTypeList());

                    if (httpServletRequest.getSession(false).getAttribute("surveyFormQuestionnaireList") != null) {
                        httpServletRequest.getSession(false).removeAttribute("surveyFormQuestionnaireList");
                    }
                    // mainList = ServiceLocator.getMarketingService().doCompletedEventSearch(this);
                    mainList = ServiceLocator.getSurveyFormService().getQuestionnaireList(this);
                    httpServletRequest.getSession(false).setAttribute("surveyFormQuestionnaireList", mainList);
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
    //addQuestionnaire

    public String addQuestionnaire() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("SURVEY_FORM_ACTIVITY", userRoleId) || AuthorizationManager.getInstance().isAuthorizedForSurveyForm("SURVEY_FORM_ACCESS", userId)) {
                try {
                    // setDateOfPublish(DateUtility.getInstance().getCurrentMySqlDate());

                    if (httpServletRequest.getSession(false).getAttribute("surveyFormSearchList") != null) {
                        httpServletRequest.getSession(false).removeAttribute("surveyFormSearchList");
                    }
                    setCurrentAction("doAddQuestionnaire");
                    setSurveyId(getSurveyId());
                    ServiceLocator.getSurveyFormService().editSurveyForm(this);
                    setOptionTypeList(ServiceLocator.getSurveyFormService().getOptionTypeList());
                    // ServiceLocator.getSurveyFormService().editSurveyForm(this);

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
//doAddQuestionnaire

    public String doAddQuestionnaire() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("SURVEY_FORM_ACTIVITY", userRoleId) || AuthorizationManager.getInstance().isAuthorizedForSurveyForm("SURVEY_FORM_ACCESS", userId)) {
                try {

                    setSurveyId(getSurveyId());
                    setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());



                    setResultMessage(ServiceLocator.getSurveyFormService().doAddQuestionnaire(this));


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
    //editQuestionnaireDetails

    public String editQuestionnaireDetails() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("SURVEY_FORM_ACTIVITY", userRoleId) || AuthorizationManager.getInstance().isAuthorizedForSurveyForm("SURVEY_FORM_ACCESS", userId)) {
                try {
                    // setDateOfPublish(DateUtility.getInstance().getCurrentMySqlDate());
                    //setResultMessage(getResultMessage());

                    //  Map eventSpeakerMap = new TreeMap();
                    //     setSpeakersMapExceptEventSpeakerMap(ServiceLocator.getMarketingService().getAllPeaopleExceptEventSpeakers(eventSpeakerMap));
                    ServiceLocator.getSurveyFormService().editSurveyForm(this);
                    setOptionTypeList(ServiceLocator.getSurveyFormService().getOptionTypeList());
                    setCurrentAction("doUpdateQuestionnaire");
                    ServiceLocator.getSurveyFormService().editQuestionnaireDetails(this);
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
    //doUpdateQuestionnaire

    public String doUpdateQuestionnaire() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("SURVEY_FORM_ACTIVITY", userRoleId) || AuthorizationManager.getInstance().isAuthorizedForSurveyForm("SURVEY_FORM_ACCESS", userId)) {
                try {


                    setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

                    ServiceLocator.getSurveyFormService().editSurveyForm(this);
                    setOptionTypeList(ServiceLocator.getSurveyFormService().getOptionTypeList());
                    setCurrentAction("doUpdateQuestionnaire");

                    setResultMessage(ServiceLocator.getSurveyFormService().doUpdateQuestionnaire(this));


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
//getSurveyReviewList

    public String getSurveyReviewList() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("SURVEY_FORM_ACTIVITY", userRoleId) || AuthorizationManager.getInstance().isAuthorizedForSurveyForm("SURVEY_FORM_ACCESS", userId)) {
                try {
                    // setDateOfPublish(DateUtility.getInstance().getCurrentMySqlDate());


                    setSurveyId(getSurveyId());
                    ServiceLocator.getSurveyFormService().editSurveyForm(this);
                    //getQuestionnaireList
                    List mainList = null;


                    if (httpServletRequest.getSession(false).getAttribute("surveyFormReviewList") != null) {
                        httpServletRequest.getSession(false).removeAttribute("surveyFormReviewList");
                    }
                    if (httpServletRequest.getSession(false).getAttribute("surveyFormQueryList") != null) {
                        httpServletRequest.getSession(false).removeAttribute("surveyFormReviewList");
                    }



                    List mainQueryList = null;


                    if (httpServletRequest.getSession(false).getAttribute("surveyFormQuestionnaireList") != null) {
                        httpServletRequest.getSession(false).removeAttribute("surveyFormQuestionnaireList");
                    }
                    // mainList = ServiceLocator.getMarketingService().doCompletedEventSearch(this);
                    mainQueryList = ServiceLocator.getSurveyFormService().getQuestionnaireList(this);
                    httpServletRequest.getSession(false).setAttribute("surveyFormQuestionnaireList", mainQueryList);
                    setQuestionMap(ServiceLocator.getSurveyFormService().getSurveyQuestionarrieMap(mainQueryList));

                    //getSurveyQueryDetailsList
                    // mainList = ServiceLocator.getMarketingService().doCompletedEventSearch(this);
                    mainList = ServiceLocator.getSurveyFormService().getSurveyReviewList(this);
                    httpServletRequest.getSession(false).setAttribute("surveyFormReviewList", mainList);

                    int count = 0;
                    Map subList = null;
                    Map firstMap = null;
                    File filee = null;
                    for (int k = 0; k < mainList.size(); k++) {
                        subList = (Map) mainList.get(k);

                        for (int qes = 0; qes < mainQueryList.size(); qes++) {
                            firstMap = (Map) mainQueryList.get(qes);
                            if (firstMap.get("OptionType").toString().equals("Attachment")) {
                                if (subList.get(firstMap.get("Query")) != null && !"".equals(subList.get(firstMap.get("Query")))) {
                                    filee = new File((String) subList.get(firstMap.get("Query")));
                                    if (filee.exists()) {
                                        count = count + 1;
                                        setAttachmentAvailable(count);
                                    }
                                }
                            }

                            if (count > 0) {
                                break;
                            }
                        }

                    }
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

    public String doSurveyAttachmentDownload() {
        try {
            // this.setId(Integer.parseInt(httpServletRequest.getParameter("Id").toString()));

            // this.setAttachmentLocation(ServiceLocator.getProjIssuesService().getAttachmentLocation(this.getId()));
            // setResultMessage();
            String location = ServiceLocator.getSurveyFormService().doSurveyAttachmentDownload(getSurveyInfoId());

            httpServletResponse.setContentType("application/force-download");

            File file = new File(location);
            fileName = file.getName();
            inputStream = new FileInputStream(file);
            outputStream = httpServletResponse.getOutputStream();
            httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            int noOfBytesRead = 0;
            byte[] byteArray = null;
            while (true) {
                byteArray = new byte[1024];
                noOfBytesRead = inputStream.read(byteArray);
                if (noOfBytesRead == -1) {
                    break;
                }
                outputStream.write(byteArray, 0, noOfBytesRead);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public String getSurveyFormInfoDetails() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("SURVEY_FORM_ACTIVITY", userRoleId) || AuthorizationManager.getInstance().isAuthorizedForSurveyForm("SURVEY_FORM_ACCESS", userId)) {
                try {
                    // setDateOfPublish(DateUtility.getInstance().getCurrentMySqlDate());
                    //setResultMessage(getResultMessage());

                    //  Map eventSpeakerMap = new TreeMap();
                    //     setSpeakersMapExceptEventSpeakerMap(ServiceLocator.getMarketingService().getAllPeaopleExceptEventSpeakers(eventSpeakerMap));
                    ServiceLocator.getSurveyFormService().editSurveyForm(this);

                    List mainList = null;
                    setSurveyId(getSurveyId());
                    List mainQueryList = null;


                    if (httpServletRequest.getSession(false).getAttribute("surveyFormQuestionnaireList") != null) {
                        httpServletRequest.getSession(false).removeAttribute("surveyFormQuestionnaireList");
                    }
                    // mainList = ServiceLocator.getMarketingService().doCompletedEventSearch(this);
                    mainQueryList = ServiceLocator.getSurveyFormService().getQuestionnaireList(this);
                    httpServletRequest.getSession(false).setAttribute("surveyFormQuestionnaireList", mainQueryList);

                    //getSurveyQueryDetailsList
                    // mainList = ServiceLocator.getMarketingService().doCompletedEventSearch(this);
                    mainList = ServiceLocator.getSurveyFormService().getSurveyFormInfoDetails(this);
                    httpServletRequest.getSession(false).setAttribute("surveyFormInfoDetailsList", mainList);

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

    public String doPublishSurveyForm() {
        resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("SURVEY_FORM_ACTIVITY", userRoleId) || AuthorizationManager.getInstance().isAuthorizedForSurveyForm("SURVEY_FORM_ACCESS", userId)) {
                try {
                    setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

                    setResultMessage(ServiceLocator.getSurveyFormService().doPublishSurveyForm(this));

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
    //surveyFormReviewListSearch

    public String surveyFormReviewListSearch() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("SURVEY_FORM_ACTIVITY", userRoleId) || AuthorizationManager.getInstance().isAuthorizedForSurveyForm("SURVEY_FORM_ACCESS", userId)) {
                try {
                    // setDateOfPublish(DateUtility.getInstance().getCurrentMySqlDate());


                    setSurveyId(getSurveyId());
                    ServiceLocator.getSurveyFormService().editSurveyForm(this);
                    //getQuestionnaireList
                    List mainList = null;


                    if (httpServletRequest.getSession(false).getAttribute("surveyFormReviewList") != null) {
                        httpServletRequest.getSession(false).removeAttribute("surveyFormReviewList");
                    }
                    if (httpServletRequest.getSession(false).getAttribute("surveyFormQueryList") != null) {
                        httpServletRequest.getSession(false).removeAttribute("surveyFormReviewList");
                    }



                    List mainQueryList = null;


                    if (httpServletRequest.getSession(false).getAttribute("surveyFormQuestionnaireList") != null) {
                        httpServletRequest.getSession(false).removeAttribute("surveyFormQuestionnaireList");
                    }
                    // mainList = ServiceLocator.getMarketingService().doCompletedEventSearch(this);
                    mainQueryList = ServiceLocator.getSurveyFormService().getQuestionnaireList(this);
                    httpServletRequest.getSession(false).setAttribute("surveyFormQuestionnaireList", mainQueryList);
                    setQuestionMap(ServiceLocator.getSurveyFormService().getSurveyQuestionarrieMap(mainQueryList));
                    //getSurveyQueryDetailsList
                    // mainList = ServiceLocator.getMarketingService().doCompletedEventSearch(this);
                    mainList = ServiceLocator.getSurveyFormService().getSurveyReviewSearchList(this);
                    httpServletRequest.getSession(false).setAttribute("surveyFormReviewList", mainList);
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

    public String doDeleteQuestion() {
        resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("SURVEY_FORM_ACTIVITY", userRoleId) || AuthorizationManager.getInstance().isAuthorizedForSurveyForm("SURVEY_FORM_ACCESS", userId)) {
                try {
                    setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

                    setResultMessage(ServiceLocator.getSurveyFormService().doDeleteQuestion(this));

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

    public String doInactiveSurveyForm() {
        resultType = LOGIN;
        //  System.out.println("1");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("SURVEY_FORM_ACTIVITY", userRoleId) || AuthorizationManager.getInstance().isAuthorizedForSurveyForm("SURVEY_FORM_ACCESS", userId)) {
                try {
                    setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

                    setResultMessage(ServiceLocator.getSurveyFormService().doInactiveSurveyForm(this));

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

    public String doActiveSurveyForm() {
        resultType = LOGIN;
        // System.out.println("1");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("SURVEY_FORM_ACTIVITY", userRoleId) || AuthorizationManager.getInstance().isAuthorizedForSurveyForm("SURVEY_FORM_ACCESS", userId)) {
                try {
                    setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

                    setResultMessage(ServiceLocator.getSurveyFormService().doActiveSurveyForm(this));

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

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
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
     * @return the surveyType
     */
    public String getSurveyType() {
        return surveyType;
    }

    /**
     * @param surveyType the surveyType to set
     */
    public void setSurveyType(String surveyType) {
        this.surveyType = surveyType;
    }

    /**
     * @return the surveyTitle
     */
    public String getSurveyTitle() {
        return surveyTitle;
    }

    /**
     * @param surveyTitle the surveyTitle to set
     */
    public void setSurveyTitle(String surveyTitle) {
        this.surveyTitle = surveyTitle;
    }

    /**
     * @return the expiryDate
     */
    public String getExpiryDate() {
        return expiryDate;
    }

    /**
     * @param expiryDate the expiryDate to set
     */
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * @return the surveyDescription
     */
    public String getSurveyDescription() {
        return surveyDescription;
    }

    /**
     * @param surveyDescription the surveyDescription to set
     */
    public void setSurveyDescription(String surveyDescription) {
        this.surveyDescription = surveyDescription;
    }

    /**
     * @return the currStatus
     */
    public String getCurrStatus() {
        return currStatus;
    }

    /**
     * @param currStatus the currStatus to set
     */
    public void setCurrStatus(String currStatus) {
        this.currStatus = currStatus;
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
     * @return the surveyId
     */
    public String getSurveyId() {
        return surveyId;
    }

    /**
     * @param surveyId the surveyId to set
     */
    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

    /**
     * @return the createdDate
     */
    public String getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return the optionTypeList
     */
    public List getOptionTypeList() {
        return optionTypeList;
    }

    /**
     * @param optionTypeList the optionTypeList to set
     */
    public void setOptionTypeList(List optionTypeList) {
        this.optionTypeList = optionTypeList;
    }

    /**
     * @return the questionTitle
     */
    public String getQuestionTitle() {
        return questionTitle;
    }

    /**
     * @param questionTitle the questionTitle to set
     */
    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    /**
     * @return the optionType
     */
    public String getOptionType() {
        return optionType;
    }

    /**
     * @param optionType the optionType to set
     */
    public void setOptionType(String optionType) {
        this.optionType = optionType;
    }

    /**
     * @return the querySequence
     */
    public String getQuerySequence() {
        return querySequence;
    }

    /**
     * @param querySequence the querySequence to set
     */
    public void setQuerySequence(String querySequence) {
        this.querySequence = querySequence;
    }

    /**
     * @return the questionId
     */
    public String getQuestionId() {
        return questionId;
    }

    /**
     * @param questionId the questionId to set
     */
    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    /**
     * @return the optionCount
     */
    public int getOptionCount() {
        return optionCount;
    }

    /**
     * @param optionCount the optionCount to set
     */
    public void setOptionCount(int optionCount) {
        this.optionCount = optionCount;
    }

    /**
     * @return the optionLabel1
     */
    public String getOptionLabel1() {
        return optionLabel1;
    }

    /**
     * @param optionLabel1 the optionLabel1 to set
     */
    public void setOptionLabel1(String optionLabel1) {
        this.optionLabel1 = optionLabel1;
    }

    /**
     * @return the optionLabel2
     */
    public String getOptionLabel2() {
        return optionLabel2;
    }

    /**
     * @param optionLabel2 the optionLabel2 to set
     */
    public void setOptionLabel2(String optionLabel2) {
        this.optionLabel2 = optionLabel2;
    }

    /**
     * @return the optionLabel3
     */
    public String getOptionLabel3() {
        return optionLabel3;
    }

    /**
     * @param optionLabel3 the optionLabel3 to set
     */
    public void setOptionLabel3(String optionLabel3) {
        this.optionLabel3 = optionLabel3;
    }

    /**
     * @return the optionLabel4
     */
    public String getOptionLabel4() {
        return optionLabel4;
    }

    /**
     * @param optionLabel4 the optionLabel4 to set
     */
    public void setOptionLabel4(String optionLabel4) {
        this.optionLabel4 = optionLabel4;
    }

    /**
     * @return the optionLabel5
     */
    public String getOptionLabel5() {
        return optionLabel5;
    }

    /**
     * @param optionLabel5 the optionLabel5 to set
     */
    public void setOptionLabel5(String optionLabel5) {
        this.optionLabel5 = optionLabel5;
    }

    /**
     * @return the optionLabel6
     */
    public String getOptionLabel6() {
        return optionLabel6;
    }

    /**
     * @param optionLabel6 the optionLabel6 to set
     */
    public void setOptionLabel6(String optionLabel6) {
        this.optionLabel6 = optionLabel6;
    }

    /**
     * @return the optionSequence1
     */
    public int getOptionSequence1() {
        return optionSequence1;
    }

    /**
     * @param optionSequence1 the optionSequence1 to set
     */
    public void setOptionSequence1(int optionSequence1) {
        this.optionSequence1 = optionSequence1;
    }

    /**
     * @return the optionSequence2
     */
    public int getOptionSequence2() {
        return optionSequence2;
    }

    /**
     * @param optionSequence2 the optionSequence2 to set
     */
    public void setOptionSequence2(int optionSequence2) {
        this.optionSequence2 = optionSequence2;
    }

    /**
     * @return the optionSequence3
     */
    public int getOptionSequence3() {
        return optionSequence3;
    }

    /**
     * @param optionSequence3 the optionSequence3 to set
     */
    public void setOptionSequence3(int optionSequence3) {
        this.optionSequence3 = optionSequence3;
    }

    /**
     * @return the optionSequence4
     */
    public int getOptionSequence4() {
        return optionSequence4;
    }

    /**
     * @param optionSequence4 the optionSequence4 to set
     */
    public void setOptionSequence4(int optionSequence4) {
        this.optionSequence4 = optionSequence4;
    }

    /**
     * @return the optionSequence5
     */
    public int getOptionSequence5() {
        return optionSequence5;
    }

    /**
     * @param optionSequence5 the optionSequence5 to set
     */
    public void setOptionSequence5(int optionSequence5) {
        this.optionSequence5 = optionSequence5;
    }

    /**
     * @return the optionSequence6
     */
    public int getOptionSequence6() {
        return optionSequence6;
    }

    /**
     * @param optionSequence6 the optionSequence6 to set
     */
    public void setOptionSequence6(int optionSequence6) {
        this.optionSequence6 = optionSequence6;
    }

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    /**
     * @return the surveyInfoId
     */
    public int getSurveyInfoId() {
        return surveyInfoId;
    }

    /**
     * @param surveyInfoId the surveyInfoId to set
     */
    public void setSurveyInfoId(int surveyInfoId) {
        this.surveyInfoId = surveyInfoId;
    }

    /**
     * @return the currentDate
     */
    public String getCurrentDate() {
        return currentDate;
    }

    /**
     * @param currentDate the currentDate to set
     */
    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    /**
     * @return the totalQuestions
     */
    public int getTotalQuestions() {
        return totalQuestions;
    }

    /**
     * @param totalQuestions the totalQuestions to set
     */
    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return the emailId
     */
    public String getEmailId() {
        return emailId;
    }

    /**
     * @param emailId the emailId to set
     */
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    /**
     * @return the questionMap
     */
    public Map getQuestionMap() {
        return questionMap;
    }

    /**
     * @param questionMap the questionMap to set
     */
    public void setQuestionMap(Map questionMap) {
        this.questionMap = questionMap;
    }

    /**
     * @return the searchQuestionId
     */
    public String getSearchQuestionId() {
        return searchQuestionId;
    }

    /**
     * @param searchQuestionId the searchQuestionId to set
     */
    public void setSearchQuestionId(String searchQuestionId) {
        this.searchQuestionId = searchQuestionId;
    }

    /**
     * @return the searchQuestion
     */
    public String getSearchQuestion() {
        return searchQuestion;
    }

    /**
     * @param searchQuestion the searchQuestion to set
     */
    public void setSearchQuestion(String searchQuestion) {
        this.searchQuestion = searchQuestion;
    }

    /**
     * @return the questionCount
     */
    public int getQuestionCount() {
        return questionCount;
    }

    /**
     * @param questionCount the questionCount to set
     */
    public void setQuestionCount(int questionCount) {
        this.questionCount = questionCount;
    }

    /**
     * @return the questionId1
     */
    public int getQuestionId1() {
        return questionId1;
    }

    /**
     * @param questionId1 the questionId1 to set
     */
    public void setQuestionId1(int questionId1) {
        this.questionId1 = questionId1;
    }

    /**
     * @return the querySequence1
     */
    public int getQuerySequence1() {
        return querySequence1;
    }

    /**
     * @param querySequence1 the querySequence1 to set
     */
    public void setQuerySequence1(int querySequence1) {
        this.querySequence1 = querySequence1;
    }

    /**
     * @return the questionId2
     */
    public int getQuestionId2() {
        return questionId2;
    }

    /**
     * @param questionId2 the questionId2 to set
     */
    public void setQuestionId2(int questionId2) {
        this.questionId2 = questionId2;
    }

    /**
     * @return the querySequence2
     */
    public int getQuerySequence2() {
        return querySequence2;
    }

    /**
     * @param querySequence2 the querySequence2 to set
     */
    public void setQuerySequence2(int querySequence2) {
        this.querySequence2 = querySequence2;
    }

    /**
     * @return the questionId3
     */
    public int getQuestionId3() {
        return questionId3;
    }

    /**
     * @param questionId3 the questionId3 to set
     */
    public void setQuestionId3(int questionId3) {
        this.questionId3 = questionId3;
    }

    /**
     * @return the querySequence3
     */
    public int getQuerySequence3() {
        return querySequence3;
    }

    /**
     * @param querySequence3 the querySequence3 to set
     */
    public void setQuerySequence3(int querySequence3) {
        this.querySequence3 = querySequence3;
    }

    /**
     * @return the questionId4
     */
    public int getQuestionId4() {
        return questionId4;
    }

    /**
     * @param questionId4 the questionId4 to set
     */
    public void setQuestionId4(int questionId4) {
        this.questionId4 = questionId4;
    }

    /**
     * @return the querySequence4
     */
    public int getQuerySequence4() {
        return querySequence4;
    }

    /**
     * @param querySequence4 the querySequence4 to set
     */
    public void setQuerySequence4(int querySequence4) {
        this.querySequence4 = querySequence4;
    }

    /**
     * @return the questionId5
     */
    public int getQuestionId5() {
        return questionId5;
    }

    /**
     * @param questionId5 the questionId5 to set
     */
    public void setQuestionId5(int questionId5) {
        this.questionId5 = questionId5;
    }

    /**
     * @return the querySequence5
     */
    public int getQuerySequence5() {
        return querySequence5;
    }

    /**
     * @param querySequence5 the querySequence5 to set
     */
    public void setQuerySequence5(int querySequence5) {
        this.querySequence5 = querySequence5;
    }

    /**
     * @return the questionId6
     */
    public int getQuestionId6() {
        return questionId6;
    }

    /**
     * @param questionId6 the questionId6 to set
     */
    public void setQuestionId6(int questionId6) {
        this.questionId6 = questionId6;
    }

    /**
     * @return the querySequence6
     */
    public int getQuerySequence6() {
        return querySequence6;
    }

    /**
     * @param querySequence6 the querySequence6 to set
     */
    public void setQuerySequence6(int querySequence6) {
        this.querySequence6 = querySequence6;
    }

    /**
     * @return the questionId7
     */
    public int getQuestionId7() {
        return questionId7;
    }

    /**
     * @param questionId7 the questionId7 to set
     */
    public void setQuestionId7(int questionId7) {
        this.questionId7 = questionId7;
    }

    /**
     * @return the querySequence7
     */
    public int getQuerySequence7() {
        return querySequence7;
    }

    /**
     * @param querySequence7 the querySequence7 to set
     */
    public void setQuerySequence7(int querySequence7) {
        this.querySequence7 = querySequence7;
    }

    /**
     * @return the questionId8
     */
    public int getQuestionId8() {
        return questionId8;
    }

    /**
     * @param questionId8 the questionId8 to set
     */
    public void setQuestionId8(int questionId8) {
        this.questionId8 = questionId8;
    }

    /**
     * @return the querySequence8
     */
    public int getQuerySequence8() {
        return querySequence8;
    }

    /**
     * @param querySequence8 the querySequence8 to set
     */
    public void setQuerySequence8(int querySequence8) {
        this.querySequence8 = querySequence8;
    }

    /**
     * @return the questionId9
     */
    public int getQuestionId9() {
        return questionId9;
    }

    /**
     * @param questionId9 the questionId9 to set
     */
    public void setQuestionId9(int questionId9) {
        this.questionId9 = questionId9;
    }

    /**
     * @return the querySequence9
     */
    public int getQuerySequence9() {
        return querySequence9;
    }

    /**
     * @param querySequence9 the querySequence9 to set
     */
    public void setQuerySequence9(int querySequence9) {
        this.querySequence9 = querySequence9;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getSurveyFormCustomizedTextBox() {
        return surveyFormCustomizedTextBox;
    }

    public void setSurveyFormCustomizedTextBox(String surveyFormCustomizedTextBox) {
        this.surveyFormCustomizedTextBox = surveyFormCustomizedTextBox;
    }

    public Map getTeamMap() {
        return teamMap;
    }

    public void setTeamMap(Map teamMap) {
        this.teamMap = teamMap;
    }

    public boolean getAnonymousCheckBox() {
        return anonymousCheckBox;
    }

    public void setAnonymousCheckBox(boolean anonymousCheckBox) {
        this.anonymousCheckBox = anonymousCheckBox;
    }

    public boolean getCustomCheckBox() {
        return customCheckBox;
    }

    public void setCustomCheckBox(boolean customCheckBox) {
        this.customCheckBox = customCheckBox;
    }

    /**
     * @return the attachmentAvailable
     */
    public int getAttachmentAvailable() {
        return attachmentAvailable;
    }

    /**
     * @param attachmentAvailable the attachmentAvailable to set
     */
    public void setAttachmentAvailable(int attachmentAvailable) {
        this.attachmentAvailable = attachmentAvailable;
    }

    /**
     * @return the maxSubmissions
     */
    public int getMaxSubmissions() {
        return maxSubmissions;
    }

    /**
     * @param maxSubmissions the maxSubmissions to set
     */
    public void setMaxSubmissions(int maxSubmissions) {
        this.maxSubmissions = maxSubmissions;
    }

    /**
     * @return the allowDuplicates
     */
    public boolean getAllowDuplicates() {
        return allowDuplicates;
    }

    /**
     * @param allowDuplicates the allowDuplicates to set
     */
    public void setAllowDuplicates(boolean allowDuplicates) {
        this.allowDuplicates = allowDuplicates;
    }
}
