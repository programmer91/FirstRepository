/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.presales;

import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.Properties;
import com.mss.mirage.util.ServiceLocatorException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author miracle
 */
public class PreSalesServiceImpl implements PreSalesService{
    private StringBuffer stringBuffer;
     /* My opprtunity dashboard details */
    
////    public String getPreSalesOppDashBoard(String type, String state, String dueStartDate, String dueEndDate, HttpServletRequest httpServletRequest) throws ServiceLocatorException{
////        //String qsTitle = "";
////        stringBuffer = new StringBuffer();
////        String oppDashboardList = "";
////        CallableStatement callableStatement = null;
////        Connection connection=null;
////        String title = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_TITLE).toString();
////        String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
////        String newDueSDate = "";
////        String newDueEDate = "";
////        int empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
////        //Map myTeamMembersMap = (Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP);
////        //String myTeamMembers = getKeys(myTeamMembersMap,",");
////        if (type.equals("All")) {
////            type = "%";
////        }
////        if (state.equals("")) {
////            state = "%";
////        }
////       
////        /* if(curWorkCountry==null || curWorkCountry=="")
////        curWorkCountry ="%";*/
////        boolean checkDefUser = checkDefaultPeople(loginId);
////        try {
////            connection = ConnectionProvider.getInstance().getConnection();
//////            if(title.equals("Vice President") || title.equals("BDM")){
//////                 callableStatement = connection.prepareCall("{call spGetOppDashboardForBDMandVicePresident(?,?,?,?,?,?,?,?,?,?,?)}");
//////            }
//////            else{
//////                 callableStatement = connection.prepareCall("{call spGetOppDashboard(?,?,?,?,?,?,?,?,?,?,?)}");
//////            }
////             callableStatement = connection.prepareCall("{call spGetPreSalesOppDashboard(?,?,?,?,?,?,?,?,?,?)}");
////            callableStatement.setString(1, type);
////            callableStatement.setString(2, state);
////            if (dueStartDate.equals("") || dueStartDate.equals(" ") || dueStartDate == null) {
////                newDueSDate = DateUtility.getInstance().convertStringToMySQLDate("01/01/1990");
////                callableStatement.setString(3, newDueSDate);
////            } else {
////                dueStartDate = DateUtility.getInstance().convertStringToMySQLDate(dueStartDate);
////                callableStatement.setString(3, dueStartDate);
////            }
////            if (dueEndDate.equals("") || dueEndDate.equals(" ") || dueEndDate == null) {
////                newDueEDate = DateUtility.getInstance().convertStringToMySQLDate("01/01/2050");
////                callableStatement.setString(4, newDueEDate);
////            } else {
////                dueEndDate = DateUtility.getInstance().convertStringToMySQLDate(dueEndDate);
////                callableStatement.setString(4, dueEndDate);
////            }
////            
////            callableStatement.setInt(5, empId);
////            callableStatement.setString(6, title);
////            callableStatement.setBoolean(7, checkDefUser);
////           
////            // callableStatement.setString(9,curWorkCountry);
////            callableStatement.registerOutParameter(8, Types.VARCHAR);
////            callableStatement.registerOutParameter(9, Types.VARCHAR);
////            callableStatement.registerOutParameter(10, Types.VARCHAR);
////            callableStatement.executeUpdate();
////            //oppDashboardList = ;
////            //int count = ;
////
////            stringBuffer.delete(0, stringBuffer.length());
////            stringBuffer.append(callableStatement.getString(8));
////            stringBuffer.append("addto");
////            stringBuffer.append(callableStatement.getInt(9));
////            stringBuffer.append("addto");
////            stringBuffer.append(callableStatement.getInt(10));
////            /*consultantList = callableStatement.getString(5);
////            stringBuffer.append(callableStatement.getString(5));
////            stringBuffer.append("addto");
////            stringBuffer.append(callableStatement.getString(6));*/
////            
////        } catch (Exception sqe) {
////            sqe.printStackTrace();
////        } finally {
////            try {
////                if (callableStatement != null) {
////                    callableStatement.close();
////                    callableStatement = null;
////                }
////                if (connection != null) {
////                    connection.close();
////                    connection = null;
////                }
////            } catch (SQLException sqle) {
////                sqle.printStackTrace();
////            }
////        }
////       
////
////
////        return stringBuffer.toString();
////    }
////
////    public boolean checkDefaultPeople(String loginId) {
////        boolean isDefUser = false;
////        try {
////            String users = Properties.getProperty("DefaultPeople").toString();
////            String[] userArr = users.split(",");
////            for (int index = 0; index < userArr.length; index++) {
////                //System.err.println("---"+userArr[index]);
////                if ((userArr[index]).equals(loginId)) {
////
////                    isDefUser = true;
////                }
////            }
////            //System.err.println("---"+isDefUser);
////        } catch (Exception slex) {
////        }
////        return isDefUser;
////    }
////    
}
