/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.ajaxnew;

import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.AuthorizationManager;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.Properties;
import com.mss.mirage.util.ServiceLocator;
import com.mss.mirage.util.ServiceLocatorException;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONObject;

/**
 *
 * @author miracle
 */
public class NewAjaxHandlerAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private String resultType;
    private String responseString;
    private String mcertStartDate;
    private String mcertToDate;
    private String mcertConsultantId;
    private String mcertConsultantStatus;
    private String ExamNameIdList;
    private int questionNo;
    private int selectedAns;
    private String navigation;
    private int remainingQuestions;
    private int onClickStatus;
    private int subTopicId;
    private int specficQuestionNo;
    private String examKeyId;
    private String createdBy;
    private String title;
    private String postedDate1;
    private String postedDate2;
    private String status;
    private String country;
    private String practiceid;
    private int requirementId;
    private String state;
    private String preSalesPerson;
    private String assignedTo;
    private String assignedBy;
    private int accId;
    private String empId;
    private String projectId;
    private int accountId;
    private String empType;
    private int preAssignEmpId;
    private int pgNo = 1;
    private String customerName;
    private String pgFlag;
    private int userRoleId;
    private String startDate;
    private String orderBy;
    private int prjId;
    private String endDate;
    private String requestType;
    private int leadId;
    private File file;
    private String fileFileName;
    private String fileFileContentType;
    private String generatedPath;
    private String fileLocation;
    private String filepath;
    private String objectType;
    private String attachmentLocation;
    private int topicId;
    private String year;
    private String month;
    private String type;
    private String category;
    private String activityType;
    private String bdmId;
    private String activityId;

    
    private String teamMemberId;
    private String  startDateContacts;
    private String  endDateContacts;
    private String  customerId;
    private String  titleType;
    private int  teamMemberCheck;
    
    private String reqId;
    private String statesFromOrg;
    
    
 private String bdeLoginId;
    private String lastActivityFrom;
    private String lastActivityTo;
    private String opportunity;
    private String touched;
    private String employeeName;
    
    
    
    private int invId;
    private Timestamp reminderDate;
    private String followUpComments;
    private String nextFollowUpSteps;
    private int id;
    private int reminderFlag;
    private String operationType;

    
     private String projectName;
    private String projectStartDate;
    private String pmoLoginId;
    private String practiceId;
    private String loginId;
    private int notesId;
    private int graphId;
private String taskStartDate;
    private String taskEndDate;
    private String reportsTo;
    private String comments;
    private String strengthsComments;
    private String improvementsComments;
     private String shortTermGoalComments;
    private String longTermGoalComments;
    
    private int rowCount;
    private String curretRole;
    private int appraisalId;
    private int lineId;
    private String quarterly;
    private String shortTermGoal;
    private String longTermGoal;
    private String strength;
    private String improvements;
    private String rejectedComments;
    private String operationTeamStatus;
    private String managerRejectedComments;
    private String operationRejectedComments;
    private int dayCount;
    private int accessCount;
      private String preAssignSalesId;
    private String teamMemberStatus;
  private String salesName;
  private String departmentId;
  
   private String subPracticeId;
   
    private String consultantName;
    private String consultantId;
     private String file1FileName;
      private String attachmentLocation1;

    public String getMcertRecordsList() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            String loginId1 = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();

            try {

                httpServletResponse.setContentType("text/html");
                // httpServletResponse.getWriter().write(ServiceLocator.getAjaxHandlerService().getCreRecordsList(getCreConsultantId(),getCreConsultantName(),getCreStartDate(),getCreToDate(), getCreConsultantStatus(), getCategory(),getLevel(),getInterviewAt()));
                String response = ServiceLocator.getNewAjaxHandlerService().getMcertRecordsList(getMcertStartDate(), getMcertToDate(), getMcertConsultantStatus());
                httpServletResponse.getWriter().write(response);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public String mcertRecordStatusUpdate() {

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                String loginid = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();

                httpServletResponse.setContentType("text/html");

                httpServletResponse.getWriter().write(ServiceLocator.getNewAjaxHandlerService().mcertRecordStatusUpdate(getMcertConsultantId(), loginid, getMcertConsultantStatus(), getExamNameIdList()));

            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public String getMcertQuestion() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

                // responseString = ServiceLocator.getAjaxHandlerService().getQuestion(getQuestionNo(),httpServletRequest,getSelectedAns(),getNavigation(),getRemainingQuestions(),getOnClickStatus(),getSubTopicId());
                responseString = ServiceLocator.getNewAjaxHandlerService().getMcertQuestion(getQuestionNo(), httpServletRequest, getSelectedAns(), getNavigation(), getRemainingQuestions(), getOnClickStatus(), getSubTopicId(), getSpecficQuestionNo());
                httpServletResponse.setContentType("text/xml");
                //System.out.println("responseString-->"+responseString);
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String getMcertDetailExamInfo() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                String result = ServiceLocator.getNewAjaxHandlerService().getMcertDetailExamInfo(getExamKeyId());
                // System.out.println("in action-->"+result);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(result);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public String searchPreSalesRequirementList() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            //AjaxHandlerAction ajaxHandlerAction = new AjaxHandlerAction();
            try {

                // System.out.println("values :::::::");
                //createdBy,assignedTo,title,postedDate1,postedDate2
                // System.out.println("createdBy----------"+getCreatedBy()+"---assignedTo-------------"+getAssignedTo()+"---title-------------"+getTitle()+"---postedDate1-------"+getPostedDate1()+"----postedDate2----"+getPostedDate2());

                responseString = ServiceLocator.getNewAjaxHandlerService().searchPreSalesRequirementList(httpServletRequest, this);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    //getRequirementOtherDetails
    public String getRequirementOtherDetails() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            //AjaxHandlerAction ajaxHandlerAction = new AjaxHandlerAction();
            try {

                // System.out.println("values :::::::");
                //createdBy,assignedTo,title,postedDate1,postedDate2
                // System.out.println("createdBy----------"+getCreatedBy()+"---assignedTo-------------"+getAssignedTo()+"---title-------------"+getTitle()+"---postedDate1-------"+getPostedDate1()+"----postedDate2----"+getPostedDate2());

                responseString = ServiceLocator.getNewAjaxHandlerService().getRequirementOtherDetails(getRequirementId());
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public String requirementAjaxListForMyPresales() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {

                responseString = ServiceLocator.getNewAjaxHandlerService().searchPreSalesMyRequirementList(httpServletRequest, this);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public String doPopulateAccountDetails() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

                responseString = ServiceLocator.getNewAjaxHandlerService().doPopulateAccountDetails(getAccId());

                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String popupContactsWindow() {
        String str;
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {

                str = ServiceLocator.getNewAjaxHandlerService().popupContactsWindow(getEmpId());
                httpServletResponse.setContentType("text/html");
                if (str != null) {
                    httpServletResponse.getWriter().write((str));
                } else {
                    httpServletResponse.getWriter().write(("No Record Present"));
                }

            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }//Close Session Checking
        return null;
    }

    public String getEmployeeProjectDetailsBasedOnStatus() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                responseString = ServiceLocator.getNewAjaxHandlerService().getEmployeeProjectDetailsBasedOnStatus(getProjectId(), getAccountId(), getEmpType(), getPreAssignEmpId(), httpServletRequest);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;

    }

    public String getProjectStatusById() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                responseString = ServiceLocator.getNewAjaxHandlerService().getProjectStatusById(getProjectId());
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;

    }

    public String getProjectProtfolioReport() {
        //  System.out.println("getProjectProtfolioReport() in NewAjaxHandlerAction");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            if (AuthorizationManager.getInstance().isAuthorizedUser("PRESALES_DASHBOARD_ACCESS", userRoleId)) {
                try {
                    //    System.out.println("getCustomerName---->"+getCustomerName());
                    //   System.out.println("getStartDate---->"+getStartDate());
                    //  System.out.println("getStatus---->"+getStatus());
                    responseString = ServiceLocator.getNewAjaxHandlerService().getProjectProtfolioReport(getCustomerName(), getStartDate(), getOrderBy(), getStatus(), httpServletRequest);
                    httpServletResponse.setContentType("text");
                    httpServletResponse.getWriter().write(responseString);
                } catch (ServiceLocatorException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }

    public String getProjectDescriptionDetails() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                responseString = ServiceLocator.getNewAjaxHandlerService().getProjectDescriptionDetails(getPrjId());
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public String getProjectCommentDetails() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            //AjaxHandlerAction ajaxHandlerAction = new AjaxHandlerAction();
            try {

                // System.out.println("values :::::::");
                //createdBy,assignedTo,title,postedDate1,postedDate2
                // System.out.println("createdBy----------"+getCreatedBy()+"---assignedTo-------------"+getAssignedTo()+"---title-------------"+getTitle()+"---postedDate1-------"+getPostedDate1()+"----postedDate2----"+getPostedDate2());

                responseString = ServiceLocator.getNewAjaxHandlerService().getProjectCommentDetails(getPrjId());
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public String getPSCERDetailsForPresales() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            //AjaxHandlerAction ajaxHandlerAction = new AjaxHandlerAction();
            try {

                System.out.println("values :::::::");

                responseString = ServiceLocator.getNewAjaxHandlerService().getPSCERDetailsForPresales(httpServletRequest, this);

                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public String getRequestInSightDetails() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                responseString = ServiceLocator.getNewAjaxHandlerService().getRequestInSightDetails(getLeadId());
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;

    }
    /*
     * PSCER dynamic Ajax grid for Sales and Employee
     * Author : Teja Kadamanti
     * Date :12/05/2016
     */

    public String getPSCERDetailsForEmployee() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            //AjaxHandlerAction ajaxHandlerAction = new AjaxHandlerAction();
            try {

                //   System.out.println("values :::::::");

                responseString = ServiceLocator.getNewAjaxHandlerService().getPSCERDetailsForEmployee(httpServletRequest, this);

                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public String getPSCERDetailsForSales() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            //AjaxHandlerAction ajaxHandlerAction = new AjaxHandlerAction();
            try {

                // System.out.println("values :::::::");

                responseString = ServiceLocator.getNewAjaxHandlerService().getPSCERDetailsForSales(httpServletRequest, this);

                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public String getDomainByExamType() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            //AjaxHandlerAction ajaxHandlerAction = new AjaxHandlerAction();
            try {
                System.out.println("values :::::::");

                responseString = ServiceLocator.getNewAjaxHandlerService().getDomainByExamType(getPgNo());

                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public String ajaxQuestionsFileUpload() throws ServiceLocatorException, IOException {
        try {

            //System.out.println(getSubTopicId()+"......gettopicId......."+getTopicId());
            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
            responseString = ServiceLocator.getNewAjaxHandlerService().ajaxQuestionsFileUpload(this);
            //System.out.println("---->responseString"+responseString);
            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /** Created By :Tirumala Teja Kadamanti
    Purpose : Newsletters Deployment Automation
    Use : To add and search Newsletters and images in a directory
     **/
    public String getAllFilesInDirectory() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            //AjaxHandlerAction ajaxHandlerAction = new AjaxHandlerAction();
            try {

                // System.out.println("values :::::::");

                responseString = ServiceLocator.getNewAjaxHandlerService().getAllFilesInDirectory(httpServletRequest, this);

                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public String doAddNewsLetter() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            //AjaxHandlerAction ajaxHandlerAction = new AjaxHandlerAction();
            try {

                // System.out.println("values :::::::");
                String basePath = Properties.getProperty("NewsLetters.Path");
                String monthName = "";
                if (Integer.parseInt(getMonth()) == 0) {
                    monthName = "January";
                } else if (Integer.parseInt(getMonth()) == 1) {
                    monthName = "February";
                } else if (Integer.parseInt(getMonth()) == 2) {
                    monthName = "March";
                } else if (Integer.parseInt(getMonth()) == 3) {
                    monthName = "April";
                } else if (Integer.parseInt(getMonth()) == 4) {
                    monthName = "May";
                } else if (Integer.parseInt(getMonth()) == 5) {
                    monthName = "June";
                } else if (Integer.parseInt(getMonth()) == 6) {
                    monthName = "July";
                } else if (Integer.parseInt(getMonth()) == 7) {
                    monthName = "August";
                } else if (Integer.parseInt(getMonth()) == 8) {
                    monthName = "September";
                } else if (Integer.parseInt(getMonth()) == 9) {
                    monthName = "October";
                } else if (Integer.parseInt(getMonth()) == 10) {
                    monthName = "November";
                } else if (Integer.parseInt(getMonth()) == 11) {
                    monthName = "December";
                }
                String fileName = getFileFileName().substring(0, getFileFileName().lastIndexOf("."));
                basePath = basePath + "//" + getYear() + "//" + monthName + "//" + getType() + "//" + getCategory() + "//" + fileName;
                File generateFile = new File(basePath);
                generateFile.mkdir();
                File targetDirectory = new File(generateFile + File.separator + "index.html");
                FileUtils.copyFile(getFile(), targetDirectory);

                responseString = "uploaded";

                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public String getAllFilesInImagesDirectory() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            //AjaxHandlerAction ajaxHandlerAction = new AjaxHandlerAction();
            try {

                // System.out.println("values :::::::");

                responseString = ServiceLocator.getNewAjaxHandlerService().getAllFilesInImagesDirectory(httpServletRequest, this);

                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public String doAddNewsLetterImages() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            //AjaxHandlerAction ajaxHandlerAction = new AjaxHandlerAction();
            try {

                // System.out.println("values :::::::");
                String basePath = Properties.getProperty("Images.NewsLetters.Path");
                String monthName = "";
                 if (Integer.parseInt(getMonth()) == 0) {
                    monthName = "January";
                } else if (Integer.parseInt(getMonth()) == 1) {
                    monthName = "February";
                } else if (Integer.parseInt(getMonth()) == 2) {
                    monthName = "March";
                } else if (Integer.parseInt(getMonth()) == 3) {
                    monthName = "April";
                } else if (Integer.parseInt(getMonth()) == 4 && ("2016".equalsIgnoreCase(getYear()))) {
                    monthName = "may";
                } else if (Integer.parseInt(getMonth()) == 4 && (!"2016".equalsIgnoreCase(getYear()))) {
                    monthName = "May";
                } else if (Integer.parseInt(getMonth()) == 5 && ("2016".equalsIgnoreCase(getYear()))) {
                    monthName = "june";
                } else if (Integer.parseInt(getMonth()) == 5 && (!"2016".equalsIgnoreCase(getYear()))) {
                    monthName = "June";
                } else if (Integer.parseInt(getMonth()) == 6) {
                    monthName = "July";
                } else if (Integer.parseInt(getMonth()) == 7) {
                    monthName = "August";
                } else if (Integer.parseInt(getMonth()) == 8) {
                    monthName = "September";
                } else if (Integer.parseInt(getMonth()) == 9) {
                    monthName = "October";
                } else if (Integer.parseInt(getMonth()) == 10 && ("2016".equalsIgnoreCase(getYear()))) {
                    monthName = "november";
                } else if (Integer.parseInt(getMonth()) == 10 && (!"2016".equalsIgnoreCase(getYear()))) {
                    monthName = "November";
                }  else if (Integer.parseInt(getMonth()) == 11 && ("2016".equalsIgnoreCase(getYear()))) {
                    monthName = "december";
                } else if (Integer.parseInt(getMonth()) == 11 && (!"2016".equalsIgnoreCase(getYear()))) {
                    monthName = "December";
                }

                basePath = basePath + "//" + getYear() + "//" + monthName;


                File file = new File(basePath);
                File[] listFiles = file.listFiles();

                if (listFiles != null) {
                    for (File files : listFiles) {
                        if (files.getName().equalsIgnoreCase(getFileFileName())) {
                            responseString = "exists";
                        }
                    }
                }
                if (!"exists".equalsIgnoreCase(responseString)) {

                    File generateFile = new File(basePath);
                    generateFile.mkdir();
                    File targetDirectory = new File(generateFile + File.separator + getFileFileName());
                    FileUtils.copyFile(getFile(), targetDirectory);

                    responseString = "uploaded";
                }
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    /* News Letters Deployment Automation  End*/
    public String getBDMStatistics() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                responseString = ServiceLocator.getNewAjaxHandlerService().getBDMStatistics(getBdmId(), getStartDate(), getEndDate());
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;

    }

    public String getBdmStatisticsDetailsByLoginId() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                responseString = ServiceLocator.getNewAjaxHandlerService().getBdmStatisticsDetailsByLoginId(getBdmId(), getStartDate(), getEndDate(), getActivityType());
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;

    }

    public String getActivityContacts() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                responseString = ServiceLocator.getNewAjaxHandlerService().getActivityContacts(getActivityId());
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;

    }
  public String getCustomerContacts() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                 responseString = ServiceLocator.getNewAjaxHandlerService().getCustomerContacts(getCustomerName(),getTeamMemberId(),getStartDateContacts(),getEndDateContacts(),getTeamMemberCheck(),getTitleType());
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
        
    }
            public String getContactActivities() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                 responseString = ServiceLocator.getNewAjaxHandlerService().getContactActivities(getCustomerId(),getTeamMemberId(),getStartDateContacts(),getEndDateContacts(),getTeamMemberCheck(),getTitleType());
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
        
    }
            
             public String reqOverviewPieChart(){
      if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            //AjaxHandlerAction ajaxHandlerAction = new AjaxHandlerAction();
            try {
               // System.out.println("values :::::::");
                
 responseString = ServiceLocator.getNewAjaxHandlerService().reqOverviewPieChart(httpServletRequest,this);
 //System.out.println("responseString...."+responseString);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
    
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
     return null;
 }
 
 public String getRequirementOverviewDetails(){
     if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            //AjaxHandlerAction ajaxHandlerAction = new AjaxHandlerAction();
            try {
              //  System.out.println("values of table:::::::");
                
 responseString = ServiceLocator.getNewAjaxHandlerService().getRequirementOverviewDetails(httpServletRequest,this);
 //System.out.println("responseString of table...."+responseString);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
    
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
     return null;
 }


 public String getInsideSalesStatusList() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                responseString = ServiceLocator.getNewAjaxHandlerService().getInsideSalesStatusList(getBdeLoginId());
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;

    }   
         
         
           public String getInsideSalesAccountDetailsList() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                
               
                
                responseString = ServiceLocator.getNewAjaxHandlerService().getInsideSalesAccountDetailsList(httpServletRequest,this);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;

    } 
	
    public String getInvestmentType() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                String result = ServiceLocator.getNewAjaxHandlerService().getInvestmentType(getInvId());
                // System.out.println("in action-->"+result);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(result);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
     
      
      
      
 public String addLeadHystory() {        
        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null)) {
            try {                
            String loginId=httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
           
     
                responseString=ServiceLocator.getNewAjaxHandlerService().addLeadHystory(loginId,getReminderDate(),getFollowUpComments(),getNextFollowUpSteps(),getLeadId(),getOperationType(),getId(),getReminderFlag());
                           

                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
      
   public String getLeadFollowUpHistoryDetails() 
     {  
         if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                responseString = ServiceLocator.getNewAjaxHandlerService().getLeadFollowUpHistoryDetails(getId());                
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
            }

        }
         return null;
     }  
           
         public String customerProjectDetailsDashboard() 
     {  
         if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                responseString = ServiceLocator.getNewAjaxHandlerService().customerProjectDetailsDashboard(this,httpServletRequest);                
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
            }

        }
         return null;
     }  
   
  public String getResourceTypeDetailsByCustomer() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                responseString = ServiceLocator.getNewAjaxHandlerService().getResourceTypeDetailsByCustomer(getAccId(),getProjectId(), httpServletRequest);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
        
    }
  
  public String getProjectListByCustomer() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                responseString = ServiceLocator.getNewAjaxHandlerService().getProjectListByCustomer(this, httpServletRequest);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
        
    } 
  
  
   public String getTaskNotes() {
        try {
             /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                responseString = ServiceLocator.getNewAjaxHandlerService().getTaskNotes(getNotesId());
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
        
    } 
   
        
/*
 * Task DashBoards functionality
 * 
 * Author : phanindra kanuri
 * 
 */  
    public String getIssuesDashBoardByStatus() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                responseString = ServiceLocator.getNewAjaxHandlerService().getIssuesDashBoardByStatus(getTaskStartDate(),getTaskEndDate(),getReportsTo(),getGraphId(),httpServletRequest);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;

    }
    
     public String getTaskListByStatus() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                responseString = ServiceLocator.getNewAjaxHandlerService().getTaskListByStatus(getTaskStartDate(),getTaskEndDate(),getReportsTo(),getActivityType(),getGraphId(), httpServletRequest);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;

    }
    public String doPopulateTaskDetails() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

                responseString = ServiceLocator.getNewAjaxHandlerService().doPopulateTaskDetails(getId());
                //responseString = ServiceLocator.getNewAjaxHandlerService().doPopulateAccountDetails(getId());

                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    /*
     * q - reviews
     */
     public String doSetQReviewManagerComments() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

               int count = ServiceLocator.getNewAjaxHandlerService().doSetQReviewManagerComments(getId(),getComments());
             //   System.out.println("count==="+count);
               //responseString = ServiceLocator.getNewAjaxHandlerService().doPopulateAccountDetails(getId());
if(count>0){
   responseString="success";
}else{
  responseString="<font color=\"red\" size=\"1.5\">Please try again later!</font>";   
}
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
      public String doSetQReviewPersonalityComments() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

               int count = ServiceLocator.getNewAjaxHandlerService().doSetQReviewPersonalityComments(getId(),getStrengthsComments(),getImprovementsComments(),getStrength(),getImprovements());
                //System.out.println("count==="+count);
               //responseString = ServiceLocator.getNewAjaxHandlerService().doPopulateAccountDetails(getId());
if(count>0){
   responseString="success";
}else{
  responseString="<font color=\"red\" size=\"1.5\">Please try again later!</font>";   
}
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
      public String doSetQReviewGoalsComments() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

               int count = ServiceLocator.getNewAjaxHandlerService().doSetQReviewGoalsComments(getId(),getShortTermGoalComments(),getLongTermGoalComments(),getShortTermGoal(),getLongTermGoal());
              //  System.out.println("count==="+count);
               //responseString = ServiceLocator.getNewAjaxHandlerService().doPopulateAccountDetails(getId());
if(count>0){
    responseString="success";
}else{
  responseString="<font color=\"red\" size=\"1.5\">Please try again later!</font>";   
}
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
      
       public String dummySessionHit() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
responseString="dummyHit";
              //  System.out.println("count===");
               //responseString = ServiceLocator.getNewAjaxHandlerService().doPopulateAccountDetails(getId());
            httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
       public String saveQReviewDetails() {
           JSONObject taxJson = new JSONObject(); 
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
                  if (AuthorizationManager.getInstance().isAuthorizedUser("QUARTERLY_APPRAISAL", userRoleId)) {
            try {
              //  System.out.println("shortTermGoalComments===" + getShortTermGoalComments());

                String result = ServiceLocator.getNewAjaxHandlerService().empQuarterlyAppraisalSave(this,httpServletRequest);
         //       System.out.println("result===" + result);
                if(result.contains("added")){
                    taxJson.put("result","success");
                }else{
                     taxJson.put("result","failed");
                }
               List list=DataSourceDataProvider.getInstance().getAppraisalIds(Integer.parseInt(getEmpId()));
               setLineId((Integer)list.get(0));
               setAppraisalId((Integer)list.get(1));
               // System.out.println("lineId==="+getLineId());
            //    System.out.println("getAppraisalId==="+getAppraisalId());
               result = ServiceLocator.getNewAjaxHandlerService().quarterlyAppraisalEdit(Integer.parseInt(getEmpId()), getAppraisalId(), getLineId());
               
               taxJson.put("QuarterAppraisalDetails", result.split(Pattern.quote("@^$"))[0]);
               taxJson.put("LineId",getLineId());
               taxJson.put("AppraisalId",getAppraisalId());
               
                      

                            
                            
            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }
                }
responseString=taxJson.toString();
               // System.out.println("responseString==="+responseString);
               //responseString = ServiceLocator.getNewAjaxHandlerService().doPopulateAccountDetails(getId());
            httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
       
       public String setQReviewEmpDescriptions() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

               int count = ServiceLocator.getNewAjaxHandlerService().setQReviewEmpDescriptions(getId(),getComments());
              //  System.out.println("count==="+count);
               //responseString = ServiceLocator.getNewAjaxHandlerService().doPopulateAccountDetails(getId());
if(count>0){
   responseString="success";
}else{
  responseString="<font color=\"red\" size=\"1.5\">Please try again later!</font>";   
}
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
       
        public String getBdmReport() {
        try {
           // System.out.println("in action ajax");
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

                responseString = ServiceLocator.getNewAjaxHandlerService().getBdmReport(this, httpServletRequest);
                //responseString = ServiceLocator.getNewAjaxHandlerService().doPopulateAccountDetails(getId());

                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String getSalesTeamforBDMAssociate() {
        // System.out.println("in action-->");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                // String fromId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                String result = ServiceLocator.getNewAjaxHandlerService().getSalesTeamforBDMAssociate(getSalesName());
                // System.out.println("in action-->"+result);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(result);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;

    }

    public String getSalesTeamDetails() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {


            try {


                responseString = ServiceLocator.getNewAjaxHandlerService().getSalesTeamDetails(getTeamMemberId(),getBdmId());
                //     System.out.println("responseString---->"+responseString);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
            }

        }//Close Session Checking
        return null;
    }

    public String addSalesToBdm() {

        try {
            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
           // System.out.println("bdmId---->" + getBdmId());
           // System.out.println("presalesAssignId---->" + getPreAssignSalesId());
            if ((ServiceLocator.getNewAjaxHandlerService().checkSalesAgainstBdm(this))) {
              //  System.out.println("gng to add of impl");
                if((ServiceLocator.getNewAjaxHandlerService().checkBdmStatus(this))){
                     if (ServiceLocator.getNewAjaxHandlerService().addSalesToBdm(this) > 0) {
                    //System.out.println("in add of impl");
                    responseString = "<font size='2' color='green'>Resource has been successfully added</font>";
                } else {
                    responseString = "Error";
                }
                }
                else{
                   
                     responseString = "<font size='2' color='red'>Resouce alrady exists as Inactive status .Please Make the Resource as Active</font>";
                }

            } else {
                  responseString = "<font size='2' color='red'>Sorry! The Resource has been already associated</font>";
               // responseString = ServiceLocator.getNewAjaxHandlerService().checkBdmAddedName(this);
//                System.out.println("in sorry case of impl");
//                System.out.println("responseString --------" + responseString);
//                System.out.println("in sorry case of impl after");
//                System.out.println("in else case.........");

            }
            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    public String updateBdmTeam() {

        try {
            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
            if (!ServiceLocator.getNewAjaxHandlerService().updateBdmTeam(this)) {
                responseString = "updated";
            } else {
                responseString = "Error";
            }
            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }
    
    
   /*Author :Teja Kadamanti
     * Date : 04/20/2017
     * Description: Multiple Projects Employee Details 
     * start
     * 
     */
    
    public String getMultipleProjectsEmployeeList() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                responseString = ServiceLocator.getNewAjaxHandlerService().getMultipleProjectsEmployeeList(this, httpServletRequest);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
        
    }
  
  
   public String multipleProjectsEmployeeListDetails() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                responseString = ServiceLocator.getNewAjaxHandlerService().multipleProjectsEmployeeListDetails(getPreAssignEmpId(), httpServletRequest);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
        
    }
  /*Author :Teja Kadamanti
     * Date : 04/20/2017
     * Description: Multiple Projects Employee Details 
     * end
     * 
     */
  public String getConsultantNames() {
        try {
            // System.out.println("email--"+getEmail());
            String query = "SELECT CONCAT(TRIM(FName),'.',TRIM(LName)) AS FullName FROM tblRecConsultant WHERE (LName LIKE '" + getConsultantName() + "%' OR FName LIKE '" + getConsultantName() + "%' OR Id LIKE '" + getConsultantName() + "%')";
        //   System.out.println("SELECT CONCAT(TRIM(FName),'.',TRIM(LName)) AS FullName,LoginId FROM tblEmployee WHERE (LName LIKE '" + employeeName + "%' OR FName LIKE '" + employeeName + "%')");
              responseString = ServiceLocator.getNewAjaxHandlerService().getConsultantNames(query);
            httpServletResponse.setContentType("text/xml");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }
      
        

   public String fileUploadWorkAuthorizationCopy() {
if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
try {
//System.out.println("fileFileName...."+getFileFileName());

    if (getFileFileName() != null) {
//attachmentService = ServiceLocator.getAttachmentService();
String path=com.mss.mirage.util.Properties.getProperty("WorkAuthoriztionCopy.AttachmentPath");


setFileFileName(getRequirementId() + "_" +getConsultantId() + "_" + getFileFileName());

File targetDirectory = new File(path+ "/" + getFileFileName());
 
 //System.out.println("targetDirectory"+targetDirectory);

setAttachmentLocation(targetDirectory.toString());
FileUtils.copyFile(getFile(), targetDirectory);


} else {
objectType = "NoFile";
setAttachmentLocation("");
//setFilepath("");
//attachmentName = "";
}
   
      
//if (!ServiceLocator.getNewAjaxHandlerService().fileUploadWorkAuthorizationCopy(this)) {
responseString = "uploaded";
//} else {
//responseString = "Error";
//}

httpServletResponse.setContentType("text");
httpServletResponse.getWriter().write(responseString);
} catch (IOException ioe) {
System.err.println(ioe);
}
}
return null;
}
   
   public String fileUploadDlCopyAttached(){
       if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
try {


    if (getFileFileName() != null) {
//attachmentService = ServiceLocator.getAttachmentService();
String path=com.mss.mirage.util.Properties.getProperty("DLCopy.AttachmentPath");


//generatedPath = path+"/"+monthName;
setFileFileName(getRequirementId() + "_" +getConsultantId() + "_" + getFileFileName());

File targetDirectory = new File(path+ "/" + getFileFileName());
 
 

setAttachmentLocation1(targetDirectory.toString());
FileUtils.copyFile(getFile(), targetDirectory);



// setObjectType("Emp Reviews");
} else {
objectType = "NoFile";
setAttachmentLocation("");
//setFilepath("");
//attachmentName = "";
}
   
      
//if (!ServiceLocator.getNewAjaxHandlerService().fileUploadDlCopyAttached(this)) {
responseString = "uploaded";
//} else {
//responseString = "Error";
//}

httpServletResponse.setContentType("text");
httpServletResponse.getWriter().write(responseString);
}  catch (IOException ioe) {
System.err.println(ioe);
}
}
return null;
   }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    /**
     * @return the mcertStartDate
     */
    public String getMcertStartDate() {
        return mcertStartDate;
    }

    /**
     * @param mcertStartDate the mcertStartDate to set
     */
    public void setMcertStartDate(String mcertStartDate) {
        this.mcertStartDate = mcertStartDate;
    }

    /**
     * @return the mcertToDate
     */
    public String getMcertToDate() {
        return mcertToDate;
    }

    /**
     * @param mcertToDate the mcertToDate to set
     */
    public void setMcertToDate(String mcertToDate) {
        this.mcertToDate = mcertToDate;
    }

    /**
     * @return the mcertConsultantId
     */
    public String getMcertConsultantId() {
        return mcertConsultantId;
    }

    /**
     * @param mcertConsultantId the mcertConsultantId to set
     */
    public void setMcertConsultantId(String mcertConsultantId) {
        this.mcertConsultantId = mcertConsultantId;
    }

    /**
     * @return the mcertConsultantStatus
     */
    public String getMcertConsultantStatus() {
        return mcertConsultantStatus;
    }

    /**
     * @param mcertConsultantStatus the mcertConsultantStatus to set
     */
    public void setMcertConsultantStatus(String mcertConsultantStatus) {
        this.mcertConsultantStatus = mcertConsultantStatus;
    }

    /**
     * @return the ExamNameIdList
     */
    public String getExamNameIdList() {
        return ExamNameIdList;
    }

    /**
     * @param ExamNameIdList the ExamNameIdList to set
     */
    public void setExamNameIdList(String ExamNameIdList) {
        this.ExamNameIdList = ExamNameIdList;
    }

    /**
     * @return the questionNo
     */
    public int getQuestionNo() {
        return questionNo;
    }

    /**
     * @param questionNo the questionNo to set
     */
    public void setQuestionNo(int questionNo) {
        this.questionNo = questionNo;
    }

    /**
     * @return the selectedAns
     */
    public int getSelectedAns() {
        return selectedAns;
    }

    /**
     * @param selectedAns the selectedAns to set
     */
    public void setSelectedAns(int selectedAns) {
        this.selectedAns = selectedAns;
    }

    /**
     * @return the navigation
     */
    public String getNavigation() {
        return navigation;
    }

    /**
     * @param navigation the navigation to set
     */
    public void setNavigation(String navigation) {
        this.navigation = navigation;
    }

    /**
     * @return the remainingQuestions
     */
    public int getRemainingQuestions() {
        return remainingQuestions;
    }

    /**
     * @param remainingQuestions the remainingQuestions to set
     */
    public void setRemainingQuestions(int remainingQuestions) {
        this.remainingQuestions = remainingQuestions;
    }

    /**
     * @return the onClickStatus
     */
    public int getOnClickStatus() {
        return onClickStatus;
    }

    /**
     * @param onClickStatus the onClickStatus to set
     */
    public void setOnClickStatus(int onClickStatus) {
        this.onClickStatus = onClickStatus;
    }

    /**
     * @return the subTopicId
     */
    public int getSubTopicId() {
        return subTopicId;
    }

    /**
     * @param subTopicId the subTopicId to set
     */
    public void setSubTopicId(int subTopicId) {
        this.subTopicId = subTopicId;
    }

    /**
     * @return the specficQuestionNo
     */
    public int getSpecficQuestionNo() {
        return specficQuestionNo;
    }

    /**
     * @param specficQuestionNo the specficQuestionNo to set
     */
    public void setSpecficQuestionNo(int specficQuestionNo) {
        this.specficQuestionNo = specficQuestionNo;
    }

    /**
     * @return the examKeyId
     */
    public String getExamKeyId() {
        return examKeyId;
    }

    /**
     * @param examKeyId the examKeyId to set
     */
    public void setExamKeyId(String examKeyId) {
        this.examKeyId = examKeyId;
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
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the postedDate1
     */
    public String getPostedDate1() {
        return postedDate1;
    }

    /**
     * @param postedDate1 the postedDate1 to set
     */
    public void setPostedDate1(String postedDate1) {
        this.postedDate1 = postedDate1;
    }

    /**
     * @return the postedDate2
     */
    public String getPostedDate2() {
        return postedDate2;
    }

    /**
     * @param postedDate2 the postedDate2 to set
     */
    public void setPostedDate2(String postedDate2) {
        this.postedDate2 = postedDate2;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the practiceid
     */
    public String getPracticeid() {
        return practiceid;
    }

    /**
     * @param practiceid the practiceid to set
     */
    public void setPracticeid(String practiceid) {
        this.practiceid = practiceid;
    }

    /**
     * @return the requirementId
     */
    public int getRequirementId() {
        return requirementId;
    }

    /**
     * @param requirementId the requirementId to set
     */
    public void setRequirementId(int requirementId) {
        this.requirementId = requirementId;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the preSalesPerson
     */
    public String getPreSalesPerson() {
        return preSalesPerson;
    }

    /**
     * @param preSalesPerson the preSalesPerson to set
     */
    public void setPreSalesPerson(String preSalesPerson) {
        this.preSalesPerson = preSalesPerson;
    }

    /**
     * @return the assignedTo
     */
    public String getAssignedTo() {
        return assignedTo;
    }

    /**
     * @param assignedTo the assignedTo to set
     */
    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    /**
     * @return the assignedBy
     */
    public String getAssignedBy() {
        return assignedBy;
    }

    /**
     * @param assignedBy the assignedBy to set
     */
    public void setAssignedBy(String assignedBy) {
        this.assignedBy = assignedBy;
    }

    /**
     * @return the accId
     */
    public int getAccId() {
        return accId;
    }

    /**
     * @param accId the accId to set
     */
    public void setAccId(int accId) {
        this.accId = accId;
    }

    /**
     * @return the empId
     */
    public String getEmpId() {
        return empId;
    }

    /**
     * @param empId the empId to set
     */
    public void setEmpId(String empId) {
        this.empId = empId;
    }

    /**
     * @return the projectId
     */
    public String getProjectId() {
        return projectId;
    }

    /**
     * @param projectId the projectId to set
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    /**
     * @return the accountId
     */
    public int getAccountId() {
        return accountId;
    }

    /**
     * @param accountId the accountId to set
     */
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    /**
     * @return the empType
     */
    public String getEmpType() {
        return empType;
    }

    /**
     * @param empType the empType to set
     */
    public void setEmpType(String empType) {
        this.empType = empType;
    }

    /**
     * @return the preAssignEmpId
     */
    public int getPreAssignEmpId() {
        return preAssignEmpId;
    }

    /**
     * @param preAssignEmpId the preAssignEmpId to set
     */
    public void setPreAssignEmpId(int preAssignEmpId) {
        this.preAssignEmpId = preAssignEmpId;
    }

    /**
     * @return the pgNo
     */
    public int getPgNo() {
        return pgNo;
    }

    /**
     * @param pgNo the pgNo to set
     */
    public void setPgNo(int pgNo) {
        this.pgNo = pgNo;
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

    /**
     * @return the pgFlag
     */
    public String getPgFlag() {
        return pgFlag;
    }

    /**
     * @param pgFlag the pgFlag to set
     */
    public void setPgFlag(String pgFlag) {
        this.pgFlag = pgFlag;
    }

    /**
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the orderBy
     */
    public String getOrderBy() {
        return orderBy;
    }

    /**
     * @param orderBy the orderBy to set
     */
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    /**
     * @return the prjId
     */
    public int getPrjId() {
        return prjId;
    }

    /**
     * @param prjId the prjId to set
     */
    public void setPrjId(int prjId) {
        this.prjId = prjId;
    }

    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the requestType
     */
    public String getRequestType() {
        return requestType;
    }

    /**
     * @param requestType the requestType to set
     */
    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    /**
     * @return the leadId
     */
    public int getLeadId() {
        return leadId;
    }

    /**
     * @param leadId the leadId to set
     */
    public void setLeadId(int leadId) {
        this.leadId = leadId;
    }

    /**
     * @return the file
     */
    public File getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * @return the fileFileName
     */
    public String getFileFileName() {
        return fileFileName;
    }

    /**
     * @param fileFileName the fileFileName to set
     */
    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    /**
     * @return the fileFileContentType
     */
    public String getFileFileContentType() {
        return fileFileContentType;
    }

    /**
     * @param fileFileContentType the fileFileContentType to set
     */
    public void setFileFileContentType(String fileFileContentType) {
        this.fileFileContentType = fileFileContentType;
    }

    /**
     * @return the generatedPath
     */
    public String getGeneratedPath() {
        return generatedPath;
    }

    /**
     * @param generatedPath the generatedPath to set
     */
    public void setGeneratedPath(String generatedPath) {
        this.generatedPath = generatedPath;
    }

    /**
     * @return the fileLocation
     */
    public String getFileLocation() {
        return fileLocation;
    }

    /**
     * @param fileLocation the fileLocation to set
     */
    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    /**
     * @return the filepath
     */
    public String getFilepath() {
        return filepath;
    }

    /**
     * @param filepath the filepath to set
     */
    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    /**
     * @return the objectType
     */
    public String getObjectType() {
        return objectType;
    }

    /**
     * @param objectType the objectType to set
     */
    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    /**
     * @return the attachmentLocation
     */
    public String getAttachmentLocation() {
        return attachmentLocation;
    }

    /**
     * @param attachmentLocation the attachmentLocation to set
     */
    public void setAttachmentLocation(String attachmentLocation) {
        this.attachmentLocation = attachmentLocation;
    }

    /**
     * @return the topicId
     */
    public int getTopicId() {
        return topicId;
    }

    /**
     * @param topicId the topicId to set
     */
    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    /**
     * @return the year
     */
    public String getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * @return the month
     */
    public String getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the activityType
     */
    public String getActivityType() {
        return activityType;
    }

    /**
     * @param activityType the activityType to set
     */
    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    /**
     * @return the bdmId
     */
    public String getBdmId() {
        return bdmId;
    }

    /**
     * @param bdmId the bdmId to set
     */
    public void setBdmId(String bdmId) {
        this.bdmId = bdmId;
    }

    /**
     * @return the activityId
     */
    public String getActivityId() {
        return activityId;
    }

    /**
     * @param activityId the activityId to set
     */
    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    /**
     * @return the teamMemberId
     */
    public String getTeamMemberId() {
        return teamMemberId;
    }

    /**
     * @param teamMemberId the teamMemberId to set
     */
    public void setTeamMemberId(String teamMemberId) {
        this.teamMemberId = teamMemberId;
    }

    /**
     * @return the startDateContacts
     */
    public String getStartDateContacts() {
        return startDateContacts;
    }

    /**
     * @param startDateContacts the startDateContacts to set
     */
    public void setStartDateContacts(String startDateContacts) {
        this.startDateContacts = startDateContacts;
    }

    /**
     * @return the endDateContacts
     */
    public String getEndDateContacts() {
        return endDateContacts;
    }

    /**
     * @param endDateContacts the endDateContacts to set
     */
    public void setEndDateContacts(String endDateContacts) {
        this.endDateContacts = endDateContacts;
    }

    /**
     * @return the customerId
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the titleType
     */
    public String getTitleType() {
        return titleType;
    }

    /**
     * @param titleType the titleType to set
     */
    public void setTitleType(String titleType) {
        this.titleType = titleType;
    }

    /**
     * @return the teamMemberCheck
     */
    public int getTeamMemberCheck() {
        return teamMemberCheck;
    }

    /**
     * @param teamMemberCheck the teamMemberCheck to set
     */
    public void setTeamMemberCheck(int teamMemberCheck) {
        this.teamMemberCheck = teamMemberCheck;
    }

    /**
     * @return the reqId
     */
    public String getReqId() {
        return reqId;
    }

    /**
     * @param reqId the reqId to set
     */
    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    /**
     * @return the statesFromOrg
     */
    public String getStatesFromOrg() {
        return statesFromOrg;
    }

    /**
     * @param statesFromOrg the statesFromOrg to set
     */
    public void setStatesFromOrg(String statesFromOrg) {
        this.statesFromOrg = statesFromOrg;
    }

    /**
     * @return the bdeLoginId
     */
    public String getBdeLoginId() {
        return bdeLoginId;
    }

    /**
     * @param bdeLoginId the bdeLoginId to set
     */
    public void setBdeLoginId(String bdeLoginId) {
        this.bdeLoginId = bdeLoginId;
    }

    /**
     * @return the lastActivityFrom
     */
    public String getLastActivityFrom() {
        return lastActivityFrom;
    }

    /**
     * @param lastActivityFrom the lastActivityFrom to set
     */
    public void setLastActivityFrom(String lastActivityFrom) {
        this.lastActivityFrom = lastActivityFrom;
    }

    /**
     * @return the lastActivityTo
     */
    public String getLastActivityTo() {
        return lastActivityTo;
    }

    /**
     * @param lastActivityTo the lastActivityTo to set
     */
    public void setLastActivityTo(String lastActivityTo) {
        this.lastActivityTo = lastActivityTo;
    }

    /**
     * @return the opportunity
     */
    public String getOpportunity() {
        return opportunity;
    }

    /**
     * @param opportunity the opportunity to set
     */
    public void setOpportunity(String opportunity) {
        this.opportunity = opportunity;
    }

    /**
     * @return the touched
     */
    public String getTouched() {
        return touched;
    }

    /**
     * @param touched the touched to set
     */
    public void setTouched(String touched) {
        this.touched = touched;
    }

    /**
     * @return the employeeName
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * @param employeeName the employeeName to set
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    /**
     * @return the invId
     */
    public int getInvId() {
        return invId;
    }

    /**
     * @param invId the invId to set
     */
    public void setInvId(int invId) {
        this.invId = invId;
    }

    /**
     * @return the reminderDate
     */
    public Timestamp getReminderDate() {
        return reminderDate;
    }

    /**
     * @param reminderDate the reminderDate to set
     */
    public void setReminderDate(Timestamp reminderDate) {
        this.reminderDate = reminderDate;
    }

    /**
     * @return the followUpComments
     */
    public String getFollowUpComments() {
        return followUpComments;
    }

    /**
     * @param followUpComments the followUpComments to set
     */
    public void setFollowUpComments(String followUpComments) {
        this.followUpComments = followUpComments;
    }

    /**
     * @return the nextFollowUpSteps
     */
    public String getNextFollowUpSteps() {
        return nextFollowUpSteps;
    }

    /**
     * @param nextFollowUpSteps the nextFollowUpSteps to set
     */
    public void setNextFollowUpSteps(String nextFollowUpSteps) {
        this.nextFollowUpSteps = nextFollowUpSteps;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the reminderFlag
     */
    public int getReminderFlag() {
        return reminderFlag;
    }

    /**
     * @param reminderFlag the reminderFlag to set
     */
    public void setReminderFlag(int reminderFlag) {
        this.reminderFlag = reminderFlag;
    }

    /**
     * @return the operationType
     */
    public String getOperationType() {
        return operationType;
    }

    /**
     * @param operationType the operationType to set
     */
    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    /**
     * @return the projectName
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * @param projectName the projectName to set
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * @return the projectStartDate
     */
    public String getProjectStartDate() {
        return projectStartDate;
    }

    /**
     * @param projectStartDate the projectStartDate to set
     */
    public void setProjectStartDate(String projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    /**
     * @return the pmoLoginId
     */
    public String getPmoLoginId() {
        return pmoLoginId;
    }

    /**
     * @param pmoLoginId the pmoLoginId to set
     */
    public void setPmoLoginId(String pmoLoginId) {
        this.pmoLoginId = pmoLoginId;
    }

    /**
     * @return the practiceId
     */
    public String getPracticeId() {
        return practiceId;
    }

    /**
     * @param practiceId the practiceId to set
     */
    public void setPracticeId(String practiceId) {
        this.practiceId = practiceId;
    }

    /**
     * @return the loginId
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * @param loginId the loginId to set
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    /**
     * @return the notesId
     */
    public int getNotesId() {
        return notesId;
    }

    /**
     * @param notesId the notesId to set
     */
    public void setNotesId(int notesId) {
        this.notesId = notesId;
    }

    /**
     * @return the graphId
     */
    public int getGraphId() {
        return graphId;
    }

    /**
     * @param graphId the graphId to set
     */
    public void setGraphId(int graphId) {
        this.graphId = graphId;
    }

    /**
     * @return the taskStartDate
     */
    public String getTaskStartDate() {
        return taskStartDate;
    }

    /**
     * @param taskStartDate the taskStartDate to set
     */
    public void setTaskStartDate(String taskStartDate) {
        this.taskStartDate = taskStartDate;
    }

    /**
     * @return the taskEndDate
     */
    public String getTaskEndDate() {
        return taskEndDate;
    }

    /**
     * @param taskEndDate the taskEndDate to set
     */
    public void setTaskEndDate(String taskEndDate) {
        this.taskEndDate = taskEndDate;
    }

    /**
     * @return the reportsTo
     */
    public String getReportsTo() {
        return reportsTo;
    }

    /**
     * @param reportsTo the reportsTo to set
     */
    public void setReportsTo(String reportsTo) {
        this.reportsTo = reportsTo;
    }

    /**
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * @return the strengthsComments
     */
    public String getStrengthsComments() {
        return strengthsComments;
    }

    /**
     * @param strengthsComments the strengthsComments to set
     */
    public void setStrengthsComments(String strengthsComments) {
        this.strengthsComments = strengthsComments;
    }

    /**
     * @return the improvementsComments
     */
    public String getImprovementsComments() {
        return improvementsComments;
    }

    /**
     * @param improvementsComments the improvementsComments to set
     */
    public void setImprovementsComments(String improvementsComments) {
        this.improvementsComments = improvementsComments;
    }

    /**
     * @return the shortTermGoalComments
     */
    public String getShortTermGoalComments() {
        return shortTermGoalComments;
    }

    /**
     * @param shortTermGoalComments the shortTermGoalComments to set
     */
    public void setShortTermGoalComments(String shortTermGoalComments) {
        this.shortTermGoalComments = shortTermGoalComments;
    }

    /**
     * @return the longTermGoalComments
     */
    public String getLongTermGoalComments() {
        return longTermGoalComments;
    }

    /**
     * @param longTermGoalComments the longTermGoalComments to set
     */
    public void setLongTermGoalComments(String longTermGoalComments) {
        this.longTermGoalComments = longTermGoalComments;
    }

    /**
     * @return the rowCount
     */
    public int getRowCount() {
        return rowCount;
    }

    /**
     * @param rowCount the rowCount to set
     */
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    /**
     * @return the curretRole
     */
    public String getCurretRole() {
        return curretRole;
    }

    /**
     * @param curretRole the curretRole to set
     */
    public void setCurretRole(String curretRole) {
        this.curretRole = curretRole;
    }

    /**
     * @return the appraisalId
     */
    public int getAppraisalId() {
        return appraisalId;
    }

    /**
     * @param appraisalId the appraisalId to set
     */
    public void setAppraisalId(int appraisalId) {
        this.appraisalId = appraisalId;
    }

    /**
     * @return the lineId
     */
    public int getLineId() {
        return lineId;
    }

    /**
     * @param lineId the lineId to set
     */
    public void setLineId(int lineId) {
        this.lineId = lineId;
    }

    /**
     * @return the quarterly
     */
    public String getQuarterly() {
        return quarterly;
    }

    /**
     * @param quarterly the quarterly to set
     */
    public void setQuarterly(String quarterly) {
        this.quarterly = quarterly;
    }

    /**
     * @return the shortTermGoal
     */
    public String getShortTermGoal() {
        return shortTermGoal;
    }

    /**
     * @param shortTermGoal the shortTermGoal to set
     */
    public void setShortTermGoal(String shortTermGoal) {
        this.shortTermGoal = shortTermGoal;
    }

    /**
     * @return the longTermGoal
     */
    public String getLongTermGoal() {
        return longTermGoal;
    }

    /**
     * @param longTermGoal the longTermGoal to set
     */
    public void setLongTermGoal(String longTermGoal) {
        this.longTermGoal = longTermGoal;
    }

    /**
     * @return the strength
     */
    public String getStrength() {
        return strength;
    }

    /**
     * @param strength the strength to set
     */
    public void setStrength(String strength) {
        this.strength = strength;
    }

    /**
     * @return the improvements
     */
    public String getImprovements() {
        return improvements;
    }

    /**
     * @param improvements the improvements to set
     */
    public void setImprovements(String improvements) {
        this.improvements = improvements;
    }

    /**
     * @return the rejectedComments
     */
    public String getRejectedComments() {
        return rejectedComments;
    }

    /**
     * @param rejectedComments the rejectedComments to set
     */
    public void setRejectedComments(String rejectedComments) {
        this.rejectedComments = rejectedComments;
    }

    /**
     * @return the operationTeamStatus
     */
    public String getOperationTeamStatus() {
        return operationTeamStatus;
    }

    /**
     * @param operationTeamStatus the operationTeamStatus to set
     */
    public void setOperationTeamStatus(String operationTeamStatus) {
        this.operationTeamStatus = operationTeamStatus;
    }

    /**
     * @return the managerRejectedComments
     */
    public String getManagerRejectedComments() {
        return managerRejectedComments;
    }

    /**
     * @param managerRejectedComments the managerRejectedComments to set
     */
    public void setManagerRejectedComments(String managerRejectedComments) {
        this.managerRejectedComments = managerRejectedComments;
    }

    /**
     * @return the operationRejectedComments
     */
    public String getOperationRejectedComments() {
        return operationRejectedComments;
    }

    /**
     * @param operationRejectedComments the operationRejectedComments to set
     */
    public void setOperationRejectedComments(String operationRejectedComments) {
        this.operationRejectedComments = operationRejectedComments;
    }

    /**
     * @return the dayCount
     */
    public int getDayCount() {
        return dayCount;
    }

    /**
     * @param dayCount the dayCount to set
     */
    public void setDayCount(int dayCount) {
        this.dayCount = dayCount;
    }

    /**
     * @return the accessCount
     */
    public int getAccessCount() {
        return accessCount;
    }

    /**
     * @param accessCount the accessCount to set
     */
    public void setAccessCount(int accessCount) {
        this.accessCount = accessCount;
    }

    /**
     * @return the preAssignSalesId
     */
    public String getPreAssignSalesId() {
        return preAssignSalesId;
    }

    /**
     * @param preAssignSalesId the preAssignSalesId to set
     */
    public void setPreAssignSalesId(String preAssignSalesId) {
        this.preAssignSalesId = preAssignSalesId;
    }

    /**
     * @return the teamMemberStatus
     */
    public String getTeamMemberStatus() {
        return teamMemberStatus;
    }

    /**
     * @param teamMemberStatus the teamMemberStatus to set
     */
    public void setTeamMemberStatus(String teamMemberStatus) {
        this.teamMemberStatus = teamMemberStatus;
    }

    /**
     * @return the salesName
     */
    public String getSalesName() {
        return salesName;
    }

    /**
     * @param salesName the salesName to set
     */
    public void setSalesName(String salesName) {
        this.salesName = salesName;
    }

    /**
     * @return the departmentId
     */
    public String getDepartmentId() {
        return departmentId;
    }

    /**
     * @param departmentId the departmentId to set
     */
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * @return the subPracticeId
     */
    public String getSubPracticeId() {
        return subPracticeId;
    }

    /**
     * @param subPracticeId the subPracticeId to set
     */
    public void setSubPracticeId(String subPracticeId) {
        this.subPracticeId = subPracticeId;
    }

    /**
     * @return the consultantId
     */
    public String getConsultantId() {
        return consultantId;
    }

    /**
     * @param consultantId the consultantId to set
     */
    public void setConsultantId(String consultantId) {
        this.consultantId = consultantId;
    }

    /**
     * @return the file1FileName
     */
    public String getFile1FileName() {
        return file1FileName;
    }

    /**
     * @param file1FileName the file1FileName to set
     */
    public void setFile1FileName(String file1FileName) {
        this.file1FileName = file1FileName;
    }

    /**
     * @return the attachmentLocation1
     */
    public String getAttachmentLocation1() {
        return attachmentLocation1;
    }

    /**
     * @param attachmentLocation1 the attachmentLocation1 to set
     */
    public void setAttachmentLocation1(String attachmentLocation1) {
        this.attachmentLocation1 = attachmentLocation1;
    }

    /**
     * @return the consultantName
     */
    public String getConsultantName() {
        return consultantName;
    }

    /**
     * @param consultantName the consultantName to set
     */
    public void setConsultantName(String consultantName) {
        this.consultantName = consultantName;
    }
}
