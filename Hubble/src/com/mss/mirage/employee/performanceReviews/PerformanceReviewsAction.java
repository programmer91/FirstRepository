/*
 * TaskAction.java
 *
 * Created on July 2, 2013, 6:43 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.mss.mirage.employee.performanceReviews;

//import org.apache.struts2.interceptor.ServletRequestAware;
import com.mss.mirage.util.*;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
//import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import java.util.ArrayList;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.DefaultDataProvider;
import com.mss.mirage.util.HibernateDataProvider;
import com.mss.mirage.util.AuthorizationManager;
import java.sql.Timestamp;

/**
 *
 * @author miracle
 */
public class PerformanceReviewsAction extends ActionSupport implements ServletRequestAware {

    private int id;
    private HttpServletRequest httpServletRequest;
    private String resultType;
    private int userRoleId;
    private HibernateDataProvider hibernateDataProvider;
    private DataSourceDataProvider dataSourceDataProvider;
    private DefaultDataProvider defaultDataProvider;
    //private EmployeeService employeeService;
    private List departmentIdList;
    private String departmentId;
    private List titleIdList;
    private String titleId;
    private String metricName;
    private String minValue;
    private String maxValue;
    private String statusId;
    private String comments;
    private String createdBy;
    private String resM;
    private String iFlag;
    private PerformanceVTO performanceVTO;
    private String metricId;
    private String weightage;
    private String temp;
    private String empNameLoginId;
    private String empName;
    private String departmentName;
    private String empTitle;
    private Timestamp fromDate;
    private Timestamp toDate;
    private String text1;
    private String textw1;
    private String textm1;
    private String texti1;
    private String slider1;
    private String texts1;
    private String textc1;
    private String text2;
    private String textw2;
    private String textm2;
    private String texti2;
    private String slider2;
    private String texts2;
    private String textc2;
    private String text3;
    private String textw3;
    private String textm3;
    private String texti3;
    private String slider3;
    private String texts3;
    private String textc3;
    private String text4;
    private String textw4;
    private String textm4;
    private String texti4;
    private String slider4;
    private String texts4;
    private String textc4;
    private String text5;
    private String textw5;
    private String textm5;
    private String texti5;
    private String slider5;
    private String texts5;
    private String textc5;
    private String text6;
    private String textw6;
    private String textm6;
    private String texti6;
    private String slider6;
    private String texts6;
    private String textc6;
    private String text7;
    private String textw7;
    private String textm7;
    private String texti7;
    private String slider7;
    private String texts7;
    private String textc7;
    private String text8;
    private String textw8;
    private String textm8;
    private String texti8;
    private String slider8;
    private String texts8;
    private String textc8;
    private String text9;
    private String textw9;
    private String textm9;
    private String texti9;
    private String slider9;
    private String texts9;
    private String textc9;
    private String text10;
    private String textw10;
    private String textm10;
    private String texti10;
    private String slider10;
    private String texts10;
    private String textc10;
    private String text11;
    private String textw11;
    private String textm11;
    private String texti11;
    private String slider11;
    private String texts11;
    private String textc11;
    private String text12;
    private String textw12;
    private String textm12;
    private String texti12;
    private String slider12;
    private String texts12;
    private String textc12;
    private String text13;
    private String textw13;
    private String textm13;
    private String texti13;
    private String slider13;
    private String texts13;
    private String textc13;
    private String text14;
    private String textw14;
    private String textm14;
    private String texti14;
    private String slider14;
    private String texts14;
    private String textc14;
    private String text15;
    private String textw15;
    private String textm15;
    private String texti15;
    private String slider15;
    private String texts15;
    private String textc15;
    private int count;
    private int callOutBound,appointment,conferenceCalls,meeting,oppurtunity,requirement;
    private String commentsForPerformance;
    //new
    /** Creates a new instance of TaskAction */
    //Desc: prepare method for setting any list or maps 
    //Desc : For getting page for Creatng new task 
    public String givePerformanceReview() throws Exception {
        resultType = LOGIN;
       // System.out.println("in asction");
        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) || (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)) {
            // setTitleIdList(dataSourceDataProvider.getTitleTypeByDepartment(getDepartmentId()));
           // System.out.println("in if");
            hibernateDataProvider = HibernateDataProvider.getInstance();
            defaultDataProvider = DefaultDataProvider.getInstance();
            //  employeeService = ServiceLocator.getEmployeeService();
            dataSourceDataProvider = dataSourceDataProvider.getInstance();
            List tempList = new ArrayList();
            tempList.add("");
            setTitleIdList(tempList);
            setDepartmentIdList(hibernateDataProvider.getDepartment(ApplicationConstants.DEPARTMENT_OPTION));
            resultType = SUCCESS;
        }
        // setPerformanceVTO(getPerformanceVTO());
        httpServletRequest.setAttribute("metricFlag", "0");
        //END-Authorization Checking
        //Close Session Checking

        return resultType;
    }

    /*public String addMetrics() {
        resultType = LOGIN;

        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null)) {
            //      System.out.println("in doaddtask if--->");
            int isTeamLead = 0;
            int isManager = 0;
            int taskId = 0;
            String result = null;
            String loginId = "";
            String metricName = "";
            String minvalue = "";
            String maxValue = "";
            String status = "";
            String desc = "";

            int empId = 0;
            String loggedInEmpName = "";
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            //    System.out.println("userRoleId-->"+userRoleId);
            if (AuthorizationManager.getInstance().isAuthorizedUser("PREPARE_MY_ISSUES", userRoleId)) {
                //      System.out.println("in doaddtask3");
                try {


                    //  System.out.println("in doaddtask5");
                    isTeamLead = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_TEAM_LEAD).toString());
                    isManager = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString());
                    loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                    setCreatedBy(httpServletRequest.getSession(false).getAttribute("userId").toString());
                    empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
                    loggedInEmpName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString();

                    PerformanceVTO performanceVTO = new PerformanceVTO();

                    metricName = getMetricName();
                    minvalue = getMinValue();
                    maxValue = getMaxValue();
                    status = getStatusId();
                    desc = getComments();


                    performanceVTO.setMetricName(metricName);
                    performanceVTO.setMinValue(minvalue);
                    performanceVTO.setMaxValue(maxValue);
                    performanceVTO.setStatusId(status);
                    performanceVTO.setComments(desc);
                    boolean check=DataSourceDataProvider.getInstance().checkForMetricName(metricName);
              //      System.out.println(check);
                    if(!check)
                    {
                    int isInserted = ServiceLocator.getPerformanceService().addMetrics(this, httpServletRequest, metricName, minvalue, maxValue, status, desc);
                    if (isInserted > 0) {
                        resultType = SUCCESS;
                        result = "Metric has been added successfully";
                        httpServletRequest.setAttribute("resultMessage", result);
                        setResM(result);
                    } else {
                        result = "Sorry! Please Try again!";
                        httpServletRequest.setAttribute("resultMessage", result);
                        setResM(result);
                        resultType = INPUT;

                    }
                    }
                    else
                    {
                        result = "Sorry! Metric Name already exists..Please Try again!";
                        httpServletRequest.setAttribute("resultMessage", result);
                        setResM(result);
                        resultType = INPUT;

                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    result = ex.toString();
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking

        return resultType;
    }

    public String editMetrics() {
        resultType = LOGIN;
     //   System.out.println("in gettask->");
        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null)) {

            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            int empId = 0;

            if (AuthorizationManager.getInstance().isAuthorizedUser("GET_ISSUE", userRoleId)) {

                try {
                    hibernateDataProvider = HibernateDataProvider.getInstance();
                    List tempList = new ArrayList();
                    tempList.add("");
                    setTitleIdList(tempList);
                    setDepartmentIdList(hibernateDataProvider.getDepartment(ApplicationConstants.DEPARTMENT_OPTION));


                   // System.out.println("--------" + getId() + "-------------" + getMetricName());
                    setPerformanceVTO(ServiceLocator.getPerformanceService().getMetrics(getId(), getMetricName()));
                    setId(getPerformanceVTO().getId());


                    if (httpServletRequest.getSession(false).getAttribute("resultMessage") != null) {
                        httpServletRequest.getSession(false).removeAttribute("resultMessage");
                    }
                    httpServletRequest.setAttribute("metricFlag", "1");
                    resultType = SUCCESS;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking


        return resultType;
    }

    public String doEditMetrics() {
        //   System.out.println("In Do Edit Issue!!");
        resultType = LOGIN;
        //   System.out.println("in doedittask()-->");
        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null)) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            int empId = 0;
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("DO_EDIT_ISSUE", userRoleId)) {
                try {
                    int isUpdated = 0;
                    String resultMessage = "";
                    String metricName = "";
                    String minvalue = "";
                    String maxValue = "";
                    String status = "";
                    String desc = "";
                    PerformanceVTO performanceVTO = new PerformanceVTO();
                    metricName = getMetricName();
                    minvalue = getMinValue();
                    maxValue = getMaxValue();
                    status = getStatusId();
                    desc = getComments();
                    //System.out.println("in edit metrics");
                    performanceVTO.setMetricName(metricName);
                    performanceVTO.setMinValue(minvalue);
                    performanceVTO.setMaxValue(maxValue);
                    performanceVTO.setStatusId(status);
                    performanceVTO.setComments(desc);
                    isUpdated = ServiceLocator.getPerformanceService().updateMetrics(this, httpServletRequest, metricName, minvalue, maxValue, status, desc);
                    if (isUpdated > 0) {
                        //System.out.println("in edit metrics1");
                        resultMessage = "Metric has been successfully Updated!";
                        setResM(resultMessage);
                        setId(getId());
                        setMetricName(getMetricName());
                        resultType = SUCCESS;
                    } else {
                        resultMessage = "Sorry! Please Try again!";
                        resultType = INPUT;
                    }
                    httpServletRequest.getSession(false).setAttribute("resultMessage", resultMessage);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }

    public String addTitle() {
        resultType = LOGIN;
        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null)) {
            //System.out.println("in doaddtask if--->");
            int isTeamLead = 0;
            int isManager = 0;
            int taskId = 0;
            String result = null;
            String loginId = "";
            String metricId = "";
            String minvalue = "";
            String maxValue = "";
            String status = "";
            String desc = "";
            String deptId = "";
            String titleId = "";
            String weightage = "";
            int empId = 0;
            String loggedInEmpName = "";
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            //System.out.println("userRoleId-->"+userRoleId);
            if (AuthorizationManager.getInstance().isAuthorizedUser("PREPARE_MY_ISSUES", userRoleId)) {
                //      System.out.println("in doaddtask3");
                try {
                    //  System.out.println("in doaddtask5");
                    isTeamLead = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_TEAM_LEAD).toString());
                    isManager = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString());
                    loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                    setCreatedBy(httpServletRequest.getSession(false).getAttribute("userId").toString());
                    empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
                    loggedInEmpName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString();
                    PerformanceVTO performanceVTO = new PerformanceVTO();
                    metricId = getMetricId();
                    status = getStatusId();
                    desc = getComments();
                    deptId = getDepartmentId();
                    titleId = getTitleId();
                    weightage = getWeightage();
                    performanceVTO.setMetricId(metricId);
                    performanceVTO.setMinValue(minvalue);
                    performanceVTO.setMaxValue(maxValue);
                    performanceVTO.setStatusId(status);
                    performanceVTO.setComments(desc);
                    performanceVTO.setDepartmentId(deptId);
                    performanceVTO.setTitleId(titleId);
                    performanceVTO.setWeightage(weightage);
                    int isInserted = ServiceLocator.getPerformanceService().addTitle(this, httpServletRequest, metricId, status, desc, deptId, titleId, weightage);
                    if (isInserted > 0) {
                        resultType = SUCCESS;
                        result = "Title has been added successfully";
                        httpServletRequest.setAttribute("resultMessage", result);
                        setResM(result);
                    } else {
                        result = "Sorry! Please Try again!";
                        resultType = INPUT;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    result = ex.toString();
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }

    public String editTitleMetrics() {
        resultType = LOGIN;
        //System.out.println("in gettask->");
        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null)) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            int empId = 0;
            if (AuthorizationManager.getInstance().isAuthorizedUser("GET_ISSUE", userRoleId)) {
                try {
                    hibernateDataProvider = HibernateDataProvider.getInstance();
                    List tempList = new ArrayList();
                    tempList.add("");
                    setTitleIdList(tempList);
                    setDepartmentIdList(hibernateDataProvider.getDepartment(ApplicationConstants.DEPARTMENT_OPTION));
                   // System.out.println("--------" + getId() + "------------" + getTemp());
                    setPerformanceVTO(ServiceLocator.getPerformanceService().getTitleMetrics(getId()));
                    setId(getPerformanceVTO().getId());
                    setTitleIdList(DataSourceDataProvider.getInstance().getTitleTypeByDepartment(getPerformanceVTO().getDepartmentId()));
                    if (httpServletRequest.getSession(false).getAttribute("resultMessage") != null) {
                        httpServletRequest.getSession(false).removeAttribute("resultMessage");
                    }
                    // setTemp(getTemp());
                    httpServletRequest.setAttribute("metricFlag", "2");
                    resultType = SUCCESS;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }

    public String doEditTitles() {
        //   System.out.println("In Do Edit Issue!!");
        resultType = LOGIN;
        //   System.out.println("in doedittask()-->");
        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null)) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            int empId = 0;
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("DO_EDIT_ISSUE", userRoleId)) {
                try {
                    int isUpdated = 0;
                    String resultMessage = "";
                    String metricId = "";
                    String minvalue = "";
                    String maxValue = "";
                    String status = "";
                    String desc = "";
                    String deptId = "";
                    String titleId = "";
                    String weightage = "";
                    PerformanceVTO performanceVTO = new PerformanceVTO();
                    metricId = getMetricId();
                    minvalue = getMinValue();
                    maxValue = getMaxValue();
                    status = getStatusId();
                    desc = getComments();
                    deptId = getDepartmentId();
                    titleId = getTitleId();
                    weightage = getWeightage();
                    //System.out.println("in edit titles");
                    performanceVTO.setMetricId(metricId);
                    performanceVTO.setMinValue(minvalue);
                    performanceVTO.setMaxValue(maxValue);
                    performanceVTO.setStatusId(status);
                    performanceVTO.setComments(desc);
                    performanceVTO.setDepartmentId(deptId);
                    performanceVTO.setTitleId(titleId);
                    isUpdated = ServiceLocator.getPerformanceService().updateTitles(this, httpServletRequest, metricId, minvalue, maxValue, status, desc, deptId, titleId, weightage);
                    if (isUpdated > 0) {
                       // System.out.println("in edit metrics1");
                        resultMessage = "Title has been successfully Updated!";
                        setResM(resultMessage);
                        setId(getId());
                        setMetricName(getMetricName());
                        resultType = SUCCESS;
                    } else {
                        resultMessage = "Sorry! Please Try again!";
                        resultType = INPUT;
                    }
                    httpServletRequest.getSession(false).setAttribute("resultMessage", resultMessage);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }
*/
    public String addPerformanceReview() {
        resultType = LOGIN;
        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null)) {
            //System.out.println("in doaddtask if--->");
            int isTeamLead = 0;
            int isManager = 0;
            int taskId = 0;
            String result = null;
            String loginId = "";
            String metricId = "";
            String minvalue = "";
            String maxValue = "";
            String status = "";
            String desc = "";
            String deptId = "";
            String titleId = "";
            String weightage = "";
            String rating = "";
            int empId = 0;
            String loggedInEmpName = "";
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            //System.out.println("userRoleId-->"+userRoleId);
            if (AuthorizationManager.getInstance().isAuthorizedUser("PREPARE_MY_ISSUES", userRoleId)) {
                //      System.out.println("in doaddtask3");
                try {
                    //  System.out.println("in doaddtask5");

                    isTeamLead = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_TEAM_LEAD).toString());
                    isManager = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString());
                    loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                    setCreatedBy(httpServletRequest.getSession(false).getAttribute("userId").toString());
                    empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
                    loggedInEmpName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString();
                    PerformanceVTO performanceVTO = new PerformanceVTO();
                  //  System.out.println("count--->" + getCount());
                    int isInsertedLines = 0;
                   // System.out.println(getEmpName() + "-------------" + getEmpNameLoginId() + "-------" + getDepartmentName() + "-----" + getEmpTitle() + "------" + getFromDate() + "-------" + getToDate());
                    int isInserted = ServiceLocator.getPerformanceService().addPerformance(this, httpServletRequest, getEmpNameLoginId(), getDepartmentName(), getEmpTitle(),getCallOutBound(),getAppointment(),getConferenceCalls(),getMeeting(),getOppurtunity(),getRequirement(),getCommentsForPerformance());
                    if (isInserted > 0) {
                        for (int i = 1; i <= getCount(); i++) {
                            if (i == 1) {
                               // System.out.println(getText1());
                                isInsertedLines = ServiceLocator.getPerformanceService().addPerformanceLines(this, httpServletRequest, getText1(), getTexts1(), getTextc1());
                            }
                            if (i == 2) {
                               //  System.out.println(getText2());
                                isInsertedLines = ServiceLocator.getPerformanceService().addPerformanceLines(this, httpServletRequest, getText2(), getTexts2(), getTextc2());
                            }
                            if (i == 3) {
                                isInsertedLines = ServiceLocator.getPerformanceService().addPerformanceLines(this, httpServletRequest, getText3(), getTexts3(), getTextc3());
                            }
                            if (i == 4) {
                                isInsertedLines = ServiceLocator.getPerformanceService().addPerformanceLines(this, httpServletRequest, getText4(), getTexts4(), getTextc4());
                            }
                            if (i == 5) {
                                isInsertedLines = ServiceLocator.getPerformanceService().addPerformanceLines(this, httpServletRequest, getText5(), getTexts5(), getTextc5());
                            }
                            if (i == 6) {
                                isInsertedLines = ServiceLocator.getPerformanceService().addPerformanceLines(this, httpServletRequest, getText6(), getTexts6(), getTextc6());
                            }
                            if (i == 7) {
                                isInsertedLines = ServiceLocator.getPerformanceService().addPerformanceLines(this, httpServletRequest, getText7(), getTexts7(), getTextc7());
                            }
                            if (i == 8) {
                                isInsertedLines = ServiceLocator.getPerformanceService().addPerformanceLines(this, httpServletRequest, getText8(), getTexts8(), getTextc8());
                            }
                            if (i == 9) {
                                isInsertedLines = ServiceLocator.getPerformanceService().addPerformanceLines(this, httpServletRequest, getText9(), getTexts9(), getTextc9());
                            }
                            if (i == 10) {
                                isInsertedLines = ServiceLocator.getPerformanceService().addPerformanceLines(this, httpServletRequest, getText10(), getTexts10(), getTextc10());
                            }
                            if (i == 11) {
                                isInsertedLines = ServiceLocator.getPerformanceService().addPerformanceLines(this, httpServletRequest, getText11(), getTexts11(), getTextc11());
                            }
                            if (i == 12) {
                                isInsertedLines = ServiceLocator.getPerformanceService().addPerformanceLines(this, httpServletRequest, getText12(), getTexts12(), getTextc12());
                            }
                            if (i == 13) {
                                isInsertedLines = ServiceLocator.getPerformanceService().addPerformanceLines(this, httpServletRequest, getText13(), getTexts13(), getTextc13());
                            }
                            if (i == 14) {
                                isInsertedLines = ServiceLocator.getPerformanceService().addPerformanceLines(this, httpServletRequest, getText14(), getTexts14(), getTextc14());
                            }
                            if (i == 15) {
                                isInsertedLines = ServiceLocator.getPerformanceService().addPerformanceLines(this, httpServletRequest, getText15(), getTexts15(), getTextc15());
                            }
                        }
                        if(isInsertedLines>0)
                        {
                        resultType = SUCCESS;
                        result = "Performance has been reviewed successfully";
                        httpServletRequest.setAttribute("resultMessage", result);
                        setResM(result);
                        }
                        else{
                         result = "Sorry! Please Try again!";
                        resultType = INPUT;
                        }

                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    result = ex.toString();
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }
 /*    public String editPerformanceValues() {
        resultType = LOGIN;
      //  System.out.println("in gettask->");
        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null)) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            int empId = 0;
            if (AuthorizationManager.getInstance().isAuthorizedUser("GET_ISSUE", userRoleId)) {
                try {
                    hibernateDataProvider = HibernateDataProvider.getInstance();
                    List tempList = new ArrayList();
                    tempList.add("");
                    setTitleIdList(tempList);
                    setDepartmentIdList(hibernateDataProvider.getDepartment(ApplicationConstants.DEPARTMENT_OPTION));
                   // System.out.println("--------" + getId());
                    setPerformanceVTO(ServiceLocator.getPerformanceService().getPerformanceValues(getId()));
                    setId(getPerformanceVTO().getId());
                    setTitleIdList(DataSourceDataProvider.getInstance().getTitleTypeByDepartment(getPerformanceVTO().getDepartmentId()));
                    if (httpServletRequest.getSession(false).getAttribute("resultMessage") != null) {
                        httpServletRequest.getSession(false).removeAttribute("resultMessage");
                    }
                    // setTemp(getTemp());
                    httpServletRequest.setAttribute("metricFlag", "3");
                    resultType = SUCCESS;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }
*/
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public List getDepartmentIdList() {
        return departmentIdList;
    }

    public void setDepartmentIdList(List departmentIdList) {
        this.departmentIdList = departmentIdList;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public List getTitleIdList() {
        return titleIdList;
    }

    public void setTitleIdList(List titleIdList) {
        this.titleIdList = titleIdList;
    }

    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(String maxValue) {
        this.maxValue = maxValue;
    }

    public String getMetricName() {
        return metricName;
    }

    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }

    public String getMinValue() {
        return minValue;
    }

    public void setMinValue(String minValue) {
        this.minValue = minValue;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getResM() {
        return resM;
    }

    public void setResM(String resM) {
        this.resM = resM;
    }

    public PerformanceVTO getPerformanceVTO() {
        return performanceVTO;
    }

    public void setPerformanceVTO(PerformanceVTO performanceVTO) {
        this.performanceVTO = performanceVTO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getiFlag() {
        return iFlag;
    }

    public void setiFlag(String iFlag) {
        this.iFlag = iFlag;
    }

    public String getMetricId() {
        return metricId;
    }

    public void setMetricId(String metricId) {
        this.metricId = metricId;
    }

    public String getWeightage() {
        return weightage;
    }

    public void setWeightage(String weightage) {
        this.weightage = weightage;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpNameLoginId() {
        return empNameLoginId;
    }

    public void setEmpNameLoginId(String empNameLoginId) {
        this.empNameLoginId = empNameLoginId;
    }

    public String getEmpTitle() {
        return empTitle;
    }

    public void setEmpTitle(String empTitle) {
        this.empTitle = empTitle;
    }

    public Timestamp getFromDate() {
        return fromDate;
    }

    public void setFromDate(Timestamp fromDate) {
        this.fromDate = fromDate;
    }

    public Timestamp getToDate() {
        return toDate;
    }

    public void setToDate(Timestamp toDate) {
        this.toDate = toDate;
    }

    public String getSlider1() {
        return slider1;
    }

    public void setSlider1(String slider1) {
        this.slider1 = slider1;
    }

    public String getSlider10() {
        return slider10;
    }

    public void setSlider10(String slider10) {
        this.slider10 = slider10;
    }

    public String getSlider11() {
        return slider11;
    }

    public void setSlider11(String slider11) {
        this.slider11 = slider11;
    }

    public String getSlider12() {
        return slider12;
    }

    public void setSlider12(String slider12) {
        this.slider12 = slider12;
    }

    public String getSlider13() {
        return slider13;
    }

    public void setSlider13(String slider13) {
        this.slider13 = slider13;
    }

    public String getSlider14() {
        return slider14;
    }

    public void setSlider14(String slider14) {
        this.slider14 = slider14;
    }

    public String getSlider15() {
        return slider15;
    }

    public void setSlider15(String slider15) {
        this.slider15 = slider15;
    }

    public String getSlider2() {
        return slider2;
    }

    public void setSlider2(String slider2) {
        this.slider2 = slider2;
    }

    public String getSlider3() {
        return slider3;
    }

    public void setSlider3(String slider3) {
        this.slider3 = slider3;
    }

    public String getSlider4() {
        return slider4;
    }

    public void setSlider4(String slider4) {
        this.slider4 = slider4;
    }

    public String getSlider5() {
        return slider5;
    }

    public void setSlider5(String slider5) {
        this.slider5 = slider5;
    }

    public String getSlider6() {
        return slider6;
    }

    public void setSlider6(String slider6) {
        this.slider6 = slider6;
    }

    public String getSlider7() {
        return slider7;
    }

    public void setSlider7(String slider7) {
        this.slider7 = slider7;
    }

    public String getSlider8() {
        return slider8;
    }

    public void setSlider8(String slider8) {
        this.slider8 = slider8;
    }

    public String getSlider9() {
        return slider9;
    }

    public void setSlider9(String slider9) {
        this.slider9 = slider9;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText10() {
        return text10;
    }

    public void setText10(String text10) {
        this.text10 = text10;
    }

    public String getText11() {
        return text11;
    }

    public void setText11(String text11) {
        this.text11 = text11;
    }

    public String getText12() {
        return text12;
    }

    public void setText12(String text12) {
        this.text12 = text12;
    }

    public String getText13() {
        return text13;
    }

    public void setText13(String text13) {
        this.text13 = text13;
    }

    public String getText14() {
        return text14;
    }

    public void setText14(String text14) {
        this.text14 = text14;
    }

    public String getText15() {
        return text15;
    }

    public void setText15(String text15) {
        this.text15 = text15;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public String getText3() {
        return text3;
    }

    public void setText3(String text3) {
        this.text3 = text3;
    }

    public String getText4() {
        return text4;
    }

    public void setText4(String text4) {
        this.text4 = text4;
    }

    public String getText5() {
        return text5;
    }

    public void setText5(String text5) {
        this.text5 = text5;
    }

    public String getText6() {
        return text6;
    }

    public void setText6(String text6) {
        this.text6 = text6;
    }

    public String getText7() {
        return text7;
    }

    public void setText7(String text7) {
        this.text7 = text7;
    }

    public String getText8() {
        return text8;
    }

    public void setText8(String text8) {
        this.text8 = text8;
    }

    public String getText9() {
        return text9;
    }

    public void setText9(String text9) {
        this.text9 = text9;
    }

    public String getTextc1() {
        return textc1;
    }

    public void setTextc1(String textc1) {
        this.textc1 = textc1;
    }

    public String getTextc10() {
        return textc10;
    }

    public void setTextc10(String textc10) {
        this.textc10 = textc10;
    }

    public String getTextc11() {
        return textc11;
    }

    public void setTextc11(String textc11) {
        this.textc11 = textc11;
    }

    public String getTextc12() {
        return textc12;
    }

    public void setTextc12(String textc12) {
        this.textc12 = textc12;
    }

    public String getTextc13() {
        return textc13;
    }

    public void setTextc13(String textc13) {
        this.textc13 = textc13;
    }

    public String getTextc14() {
        return textc14;
    }

    public void setTextc14(String textc14) {
        this.textc14 = textc14;
    }

    public String getTextc15() {
        return textc15;
    }

    public void setTextc15(String textc15) {
        this.textc15 = textc15;
    }

    public String getTextc2() {
        return textc2;
    }

    public void setTextc2(String textc2) {
        this.textc2 = textc2;
    }

    public String getTextc3() {
        return textc3;
    }

    public void setTextc3(String textc3) {
        this.textc3 = textc3;
    }

    public String getTextc4() {
        return textc4;
    }

    public void setTextc4(String textc4) {
        this.textc4 = textc4;
    }

    public String getTextc5() {
        return textc5;
    }

    public void setTextc5(String textc5) {
        this.textc5 = textc5;
    }

    public String getTextc6() {
        return textc6;
    }

    public void setTextc6(String textc6) {
        this.textc6 = textc6;
    }

    public String getTextc7() {
        return textc7;
    }

    public void setTextc7(String textc7) {
        this.textc7 = textc7;
    }

    public String getTextc8() {
        return textc8;
    }

    public void setTextc8(String textc8) {
        this.textc8 = textc8;
    }

    public String getTextc9() {
        return textc9;
    }

    public void setTextc9(String textc9) {
        this.textc9 = textc9;
    }

    public String getTexti1() {
        return texti1;
    }

    public void setTexti1(String texti1) {
        this.texti1 = texti1;
    }

    public String getTexti10() {
        return texti10;
    }

    public void setTexti10(String texti10) {
        this.texti10 = texti10;
    }

    public String getTexti11() {
        return texti11;
    }

    public void setTexti11(String texti11) {
        this.texti11 = texti11;
    }

    public String getTexti12() {
        return texti12;
    }

    public void setTexti12(String texti12) {
        this.texti12 = texti12;
    }

    public String getTexti13() {
        return texti13;
    }

    public void setTexti13(String texti13) {
        this.texti13 = texti13;
    }

    public String getTexti14() {
        return texti14;
    }

    public void setTexti14(String texti14) {
        this.texti14 = texti14;
    }

    public String getTexti15() {
        return texti15;
    }

    public void setTexti15(String texti15) {
        this.texti15 = texti15;
    }

    public String getTexti2() {
        return texti2;
    }

    public void setTexti2(String texti2) {
        this.texti2 = texti2;
    }

    public String getTexti3() {
        return texti3;
    }

    public void setTexti3(String texti3) {
        this.texti3 = texti3;
    }

    public String getTexti4() {
        return texti4;
    }

    public void setTexti4(String texti4) {
        this.texti4 = texti4;
    }

    public String getTexti5() {
        return texti5;
    }

    public void setTexti5(String texti5) {
        this.texti5 = texti5;
    }

    public String getTexti6() {
        return texti6;
    }

    public void setTexti6(String texti6) {
        this.texti6 = texti6;
    }

    public String getTexti7() {
        return texti7;
    }

    public void setTexti7(String texti7) {
        this.texti7 = texti7;
    }

    public String getTexti8() {
        return texti8;
    }

    public void setTexti8(String texti8) {
        this.texti8 = texti8;
    }

    public String getTexti9() {
        return texti9;
    }

    public void setTexti9(String texti9) {
        this.texti9 = texti9;
    }

    public String getTextm1() {
        return textm1;
    }

    public void setTextm1(String textm1) {
        this.textm1 = textm1;
    }

    public String getTextm10() {
        return textm10;
    }

    public void setTextm10(String textm10) {
        this.textm10 = textm10;
    }

    public String getTextm11() {
        return textm11;
    }

    public void setTextm11(String textm11) {
        this.textm11 = textm11;
    }

    public String getTextm12() {
        return textm12;
    }

    public void setTextm12(String textm12) {
        this.textm12 = textm12;
    }

    public String getTextm13() {
        return textm13;
    }

    public void setTextm13(String textm13) {
        this.textm13 = textm13;
    }

    public String getTextm14() {
        return textm14;
    }

    public void setTextm14(String textm14) {
        this.textm14 = textm14;
    }

    public String getTextm15() {
        return textm15;
    }

    public void setTextm15(String textm15) {
        this.textm15 = textm15;
    }

    public String getTextm2() {
        return textm2;
    }

    public void setTextm2(String textm2) {
        this.textm2 = textm2;
    }

    public String getTextm3() {
        return textm3;
    }

    public void setTextm3(String textm3) {
        this.textm3 = textm3;
    }

    public String getTextm4() {
        return textm4;
    }

    public void setTextm4(String textm4) {
        this.textm4 = textm4;
    }

    public String getTextm5() {
        return textm5;
    }

    public void setTextm5(String textm5) {
        this.textm5 = textm5;
    }

    public String getTextm6() {
        return textm6;
    }

    public void setTextm6(String textm6) {
        this.textm6 = textm6;
    }

    public String getTextm7() {
        return textm7;
    }

    public void setTextm7(String textm7) {
        this.textm7 = textm7;
    }

    public String getTextm8() {
        return textm8;
    }

    public void setTextm8(String textm8) {
        this.textm8 = textm8;
    }

    public String getTextm9() {
        return textm9;
    }

    public void setTextm9(String textm9) {
        this.textm9 = textm9;
    }

    public String getTexts1() {
        return texts1;
    }

    public void setTexts1(String texts1) {
        this.texts1 = texts1;
    }

    public String getTexts10() {
        return texts10;
    }

    public void setTexts10(String texts10) {
        this.texts10 = texts10;
    }

    public String getTexts11() {
        return texts11;
    }

    public void setTexts11(String texts11) {
        this.texts11 = texts11;
    }

    public String getTexts12() {
        return texts12;
    }

    public void setTexts12(String texts12) {
        this.texts12 = texts12;
    }

    public String getTexts13() {
        return texts13;
    }

    public void setTexts13(String texts13) {
        this.texts13 = texts13;
    }

    public String getTexts14() {
        return texts14;
    }

    public void setTexts14(String texts14) {
        this.texts14 = texts14;
    }

    public String getTexts15() {
        return texts15;
    }

    public void setTexts15(String texts15) {
        this.texts15 = texts15;
    }

    public String getTexts2() {
        return texts2;
    }

    public void setTexts2(String texts2) {
        this.texts2 = texts2;
    }

    public String getTexts3() {
        return texts3;
    }

    public void setTexts3(String texts3) {
        this.texts3 = texts3;
    }

    public String getTexts4() {
        return texts4;
    }

    public void setTexts4(String texts4) {
        this.texts4 = texts4;
    }

    public String getTexts5() {
        return texts5;
    }

    public void setTexts5(String texts5) {
        this.texts5 = texts5;
    }

    public String getTexts6() {
        return texts6;
    }

    public void setTexts6(String texts6) {
        this.texts6 = texts6;
    }

    public String getTexts7() {
        return texts7;
    }

    public void setTexts7(String texts7) {
        this.texts7 = texts7;
    }

    public String getTexts8() {
        return texts8;
    }

    public void setTexts8(String texts8) {
        this.texts8 = texts8;
    }

    public String getTexts9() {
        return texts9;
    }

    public void setTexts9(String texts9) {
        this.texts9 = texts9;
    }

    public String getTextw1() {
        return textw1;
    }

    public void setTextw1(String textw1) {
        this.textw1 = textw1;
    }

    public String getTextw10() {
        return textw10;
    }

    public void setTextw10(String textw10) {
        this.textw10 = textw10;
    }

    public String getTextw11() {
        return textw11;
    }

    public void setTextw11(String textw11) {
        this.textw11 = textw11;
    }

    public String getTextw12() {
        return textw12;
    }

    public void setTextw12(String textw12) {
        this.textw12 = textw12;
    }

    public String getTextw13() {
        return textw13;
    }

    public void setTextw13(String textw13) {
        this.textw13 = textw13;
    }

    public String getTextw14() {
        return textw14;
    }

    public void setTextw14(String textw14) {
        this.textw14 = textw14;
    }

    public String getTextw15() {
        return textw15;
    }

    public void setTextw15(String textw15) {
        this.textw15 = textw15;
    }

    public String getTextw2() {
        return textw2;
    }

    public void setTextw2(String textw2) {
        this.textw2 = textw2;
    }

    public String getTextw3() {
        return textw3;
    }

    public void setTextw3(String textw3) {
        this.textw3 = textw3;
    }

    public String getTextw4() {
        return textw4;
    }

    public void setTextw4(String textw4) {
        this.textw4 = textw4;
    }

    public String getTextw5() {
        return textw5;
    }

    public void setTextw5(String textw5) {
        this.textw5 = textw5;
    }

    public String getTextw6() {
        return textw6;
    }

    public void setTextw6(String textw6) {
        this.textw6 = textw6;
    }

    public String getTextw7() {
        return textw7;
    }

    public void setTextw7(String textw7) {
        this.textw7 = textw7;
    }

    public String getTextw8() {
        return textw8;
    }

    public void setTextw8(String textw8) {
        this.textw8 = textw8;
    }

    public String getTextw9() {
        return textw9;
    }

    public void setTextw9(String textw9) {
        this.textw9 = textw9;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getAppointment() {
        return appointment;
    }

    public void setAppointment(int appointment) {
        this.appointment = appointment;
    }

    public int getCallOutBound() {
        return callOutBound;
    }

    public void setCallOutBound(int callOutBound) {
        this.callOutBound = callOutBound;
    }

    public int getConferenceCalls() {
        return conferenceCalls;
    }

    public void setConferenceCalls(int conferenceCalls) {
        this.conferenceCalls = conferenceCalls;
    }

    public int getMeeting() {
        return meeting;
    }

    public void setMeeting(int meeting) {
        this.meeting = meeting;
    }

    public int getOppurtunity() {
        return oppurtunity;
    }

    public void setOppurtunity(int oppurtunity) {
        this.oppurtunity = oppurtunity;
    }

    public int getRequirement() {
        return requirement;
    }

    public void setRequirement(int requirement) {
        this.requirement = requirement;
    }

    public String getCommentsForPerformance() {
        return commentsForPerformance;
    }

    public void setCommentsForPerformance(String commentsForPerformance) {
        this.commentsForPerformance = commentsForPerformance;
    }
    /**
     * @return the text
     */
    
}
