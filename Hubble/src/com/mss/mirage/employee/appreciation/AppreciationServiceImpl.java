/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.employee.appreciation;

import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.ServiceLocatorException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author miracle
 */
public class AppreciationServiceImpl implements AppreciationService {

    private Map appreciationMap;

    public int insertAppreciationService(AppreciationAction appreciationAction) throws ServiceLocatorException {
        Connection connection = null;
        int updatedRows;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            //   System.out.println("appreciationAction.getAppriceationId()==" + appreciationAction.getAppriceationId());
            if (appreciationAction.getAppriceationId() > 0) {
                queryString = "UPDATE tblEmpAppreciation SET EmployeesList=?,Title=?,Subtitle=?,Subject=?,BodyContent=?,ToAddresses=?,CCAddress=?,BCCAddress=?,Status=?,CreatedBy=?,CreatedDate=? where Id = " + appreciationAction.getAppriceationId();
                //     System.out.println("appreciationAction.getAppriceationId()==" + appreciationAction.getAppriceationId());

            } else {
                queryString = "insert into tblEmpAppreciation(EmployeesList,Title,Subtitle,Subject,BodyContent,ToAddresses,CCAddress,BCCAddress,Status,CreatedBy,CreatedDate) values(?,?,?,?,?,?,?,?,?,?,?)";
            }
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, appreciationAction.getToHidden());
            preparedStatement.setString(2, appreciationAction.getAppreciationTitle());
            preparedStatement.setString(3, appreciationAction.getSubAppreciationTitle());
            preparedStatement.setString(4, appreciationAction.getAppreciationSubject());
            preparedStatement.setString(5, appreciationAction.getAppreciationBodyContent());
            preparedStatement.setString(6, appreciationAction.getToHidden());
            preparedStatement.setString(7, appreciationAction.getCCHidden());
            preparedStatement.setString(8, appreciationAction.getBCCHidden());
            preparedStatement.setString(9, appreciationAction.getSendOrSave());
            preparedStatement.setString(10, appreciationAction.getCreatedBy());
            preparedStatement.setTimestamp(11, DateUtility.getInstance().getCurrentMySqlDateTime());
            updatedRows = preparedStatement.executeUpdate();

        } catch (SQLException se) {
            throw new ServiceLocatorException(se);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }
        return updatedRows;
    }

    public String getAppreciationDetailsById(AppreciationAction appreciationAction) throws ServiceLocatorException {
        List<String> list = new ArrayList<String>();
        List<String> list1 = new ArrayList<String>();
        List<String> list2 = new ArrayList<String>();
        List<String> list3 = new ArrayList<String>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "SELECT * FROM tblEmpAppreciation where Id = " + appreciationAction.getAppriceationId();
        String result = null;

        try {
            //  System.out.println("+queryString--->" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {


                StringTokenizer str = new StringTokenizer(resultSet.getString("EmployeesList"), ",");
                while (str.hasMoreTokens()) {
                    list.add(str.nextToken());
                }

                appreciationAction.setAppriceationId(resultSet.getInt("Id"));
                appreciationAction.setAppreciationTitle(resultSet.getString("Title"));
                appreciationAction.setSubAppreciationTitle(resultSet.getString("SubTitle"));
                appreciationAction.setAppreciationSubject(resultSet.getString("SUBJECT"));
                appreciationAction.setAppreciationBodyContent(resultSet.getString("BodyContent"));
                StringTokenizer str1 = new StringTokenizer(resultSet.getString("ToAddresses"), ",");
                while (str1.hasMoreTokens()) {
                    list1.add(str1.nextToken());
                }
                appreciationAction.setAppreciationToList(list1);

                StringTokenizer str2 = new StringTokenizer(resultSet.getString("CCAddress"), ",");
                while (str2.hasMoreTokens()) {
                    list2.add(str2.nextToken());
                }
                appreciationAction.setAppreciationCCList(list2);

                StringTokenizer str3 = new StringTokenizer(resultSet.getString("BCCAddress"), ",");
                while (str3.hasMoreTokens()) {
                    list3.add(str3.nextToken());
                }
                appreciationAction.setAppreciationBCCList(list3);

                appreciationAction.setStatus(resultSet.getString("STATUS"));
                appreciationAction.setCreatedBy(resultSet.getString("CreatedBy"));

            }
        } catch (SQLException se) {
            throw new ServiceLocatorException(se);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }

            return result;

        }
    }

    public Map getAppreciationEmailValues(AppreciationAction appreciationAction) throws ServiceLocatorException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;
        String toAddress = null;
        int count = 0;
        String userName = "";
        String empDesignation = "";
        String createdBy = "";
        DataSourceDataProvider dataSourceDataProvider = null;

        String loginId = null;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT Title,SubTitle,BodyContent,ToAddresses,CreatedBy FROM tblEmpAppreciation WHERE Id=" + appreciationAction.getAppriceationId();
            try {
                preparedStatement = connection.prepareStatement(queryString);
            } catch (SQLException ex) {
                Logger.getLogger(AppreciationServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            Map appreciationMap = new TreeMap();
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                appreciationAction.setTitle(resultSet.getString("Title"));
                appreciationAction.setSubtitle(resultSet.getString("SubTitle"));
                appreciationAction.setBodyContent(resultSet.getString("BodyContent").replace("\n", "<br/>"));
                StringTokenizer stk1 = new StringTokenizer(resultSet.getString("ToAddresses"), ",");
                while (stk1.hasMoreTokens()) {
                    toAddress = dataSourceDataProvider.getInstance().userName(((stk1.nextToken()))) + ",";

                    count++;

                }
                if (count == 1) {

                    userName = toAddress;

                } else if (count >= 2) {
                    userName = "Team ,";

                }
                empDesignation = dataSourceDataProvider.getInstance().getDesignation(((resultSet.getString("CreatedBy"))));


                createdBy = dataSourceDataProvider.getInstance().getFullNameBYLoginId(((resultSet.getString("CreatedBy"))));
                createdBy = createdBy.replace(".", " ");
                userName = userName.replace(".", " ");


                appreciationAction.setDesignation(empDesignation);
                appreciationAction.setCreatedBy(createdBy);
                appreciationAction.setUserName(userName);


            }
            appreciationMap.put(1, appreciationAction);
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }

                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }

                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }

        }

        return appreciationMap;
    }
}
