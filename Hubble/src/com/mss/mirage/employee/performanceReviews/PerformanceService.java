/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.employee.performanceReviews;
import com.mss.mirage.util.ServiceLocatorException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @author miracle
 */
public interface PerformanceService {
   /*  public  int addMetrics(PerformanceReviewsAction performanceReviewsAction,HttpServletRequest httpServletRequest,String metricName,String minvalue,String maxValue,String status,String desc)throws ServiceLocatorException, SQLException;
          public PerformanceVTO getMetrics(int id,String mericName)throws ServiceLocatorException, SQLException ;
           public  int updateMetrics(PerformanceReviewsAction performanceReviewsAction,HttpServletRequest httpServletRequest,String metricName,String minvalue,String maxValue,String status,String desc)throws ServiceLocatorException, SQLException;
public  int addTitle(PerformanceReviewsAction performanceReviewsAction,HttpServletRequest httpServletRequest,String metricId,String status,String desc,String deptId,String titleId,String weightage)throws ServiceLocatorException, SQLException;
  public PerformanceVTO getTitleMetrics(int id)throws ServiceLocatorException, SQLException ;
       public  int updateTitles(PerformanceReviewsAction performanceReviewsAction,HttpServletRequest httpServletRequest,String metricId,String minvalue,String maxValue,String status,String desc,String deptId,String titleId,String weightage)throws ServiceLocatorException, SQLException;*/
       public  int addPerformance(PerformanceReviewsAction performanceReviewsAction,HttpServletRequest httpServletRequest,String empLoginId,String dept,String title,int callOutBound,int appointment,int conferenceCalls,int meeting,int oppurtunity,int requirement,String commentsForPerformance)throws ServiceLocatorException, SQLException;
        public  int addPerformanceLines(PerformanceReviewsAction performanceReviewsAction,HttpServletRequest httpServletRequest,String metricId,String rangeValue,String weightage)throws ServiceLocatorException, SQLException;

     /*   public PerformanceVTO getPerformanceValues(int id)throws ServiceLocatorException, SQLException ;*/
}
