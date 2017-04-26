/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.marketing.surveyform;

import com.mss.mirage.util.ServiceLocatorException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author miracle
 */
public interface SurveyFormService {
    public String doAddSurveyForm(SurveyFormAction surveyFormAction) throws ServiceLocatorException;
     public List doSurveyFormSearch(SurveyFormAction surveyFormAction,HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    public void editSurveyForm(SurveyFormAction surveyFormAction) throws ServiceLocatorException;
    public String doUpdateSurveyForm(SurveyFormAction surveyFormAction) throws ServiceLocatorException ;
    public List getOptionTypeList() throws ServiceLocatorException;
    public String doAddQuestionnaire(SurveyFormAction surveyFormAction) throws ServiceLocatorException;
     public List getQuestionnaireList(SurveyFormAction surveyFormAction) throws ServiceLocatorException;
      public void editQuestionnaireDetails(SurveyFormAction surveyFormAction) throws ServiceLocatorException;
      public String doUpdateQuestionnaire(SurveyFormAction surveyFormAction) throws ServiceLocatorException;
      public List getSurveyReviewList(SurveyFormAction surveyFormAction) throws ServiceLocatorException;
      public String doSurveyAttachmentDownload(int surveyInfoId) throws ServiceLocatorException ;
      public List getSurveyFormInfoDetails(SurveyFormAction surveyFormAction) throws ServiceLocatorException ;
       public String doPublishSurveyForm(SurveyFormAction surveyFormAction) throws ServiceLocatorException ;
       public List getSurveyReviewSearchList(SurveyFormAction surveyFormAction) throws ServiceLocatorException ;
       public Map getSurveyQuestionarrieMap(List questionList) throws ServiceLocatorException ;
        public String doDeleteQuestion(SurveyFormAction surveyFormAction) throws ServiceLocatorException ;
        public String doInactiveSurveyForm(SurveyFormAction surveyFormAction) throws ServiceLocatorException ;
public String doActiveSurveyForm(SurveyFormAction surveyFormAction) throws ServiceLocatorException ;


}
