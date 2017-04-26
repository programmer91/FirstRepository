/*
 * DataSourceDataProvider.java
 *
 * Created on January 5, 2008, 6:41 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.mss.mirage.util;

import com.mss.mirage.cre.CreReviewVTO;
import com.mss.mirage.general.LeftMenuVTO;
import com.opensymphony.xwork2.Result;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import javax.servlet.http.HttpSession;
import org.hibernate.jdbc.ConnectionManager;//new
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author miracle
 */
public class DataSourceDataProvider {

    private static DataSourceDataProvider _instance;
    //new
    private Map myTeamMembers = new TreeMap();
    /**
     * Creating a reference variable for HttpServletRequest.
     */
    private HttpServletRequest httpServletRequest;
    /**
     * Creating a reference variable for HttpServletResponse.
     */
    private HttpServletResponse httpServletResponse;

    public Map getMyTeamMembers() {
        return myTeamMembers;
    }

    public void setMyTeamMembers(Map myTeamMembers) {
        this.myTeamMembers = myTeamMembers;
    }
    ArrayList arrayTeamcalendar;
    HashMap teamCalendarMap;
    String str[] = new String[10000];
    int num2 = 0;

    /**
     * Creates a new instance of DataSourceDataProvider
     */
    private DataSourceDataProvider() {
    }

    /**
     * @return An instance of the DataServiceLocator class
     * @throws ServiceLocatorException
     */
    public static DataSourceDataProvider getInstance() throws ServiceLocatorException {
        try {
            if (_instance == null) {
                _instance = new DataSourceDataProvider();
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
        }
        return _instance;
    }

    public String getProjectType(int projectId) throws ServiceLocatorException {

        String projectType = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "Select Id,ProjectName,ProjectType from tblProjects where Id =" + projectId;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                projectType = resultSet.getString("ProjectType");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return projectType;
    }

    public String getLoginIdByEmpname(String empName) throws ServiceLocatorException {

        String projectType = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        //queryString = "SELECT concat(FirstName,'.',LastName) as Name, LoginId FROM tblCatagory";
        String queryString = "Select Id,FirstName,LastName,LoginId from tblCatagory where concat(FirstName,'.',LastName) LIKE '" + empName + "'";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                projectType = resultSet.getString("LoginId");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return projectType;
    }

    public List getCustomerProjects(int customerId) throws ServiceLocatorException {

        List customerProjects = new ArrayList();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;

        queryString = "SELECT ProjectName FROM tblProjects WHERE CustomerId= " + customerId + "  ORDER BY ProjectName";
        connection = ConnectionProvider.getInstance().getConnection();

        try {
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                customerProjects.add(resultSet.getString("ProjectName"));
            }

        } catch (SQLException sqlex) {
            throw new ServiceLocatorException(sqlex);
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
        return customerProjects;
    }//closing the method.

    public List getCustomerSubProjects(int customerId) throws ServiceLocatorException {

        List customerSubProjects = new ArrayList();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;

        queryString = "SELECT SubProjectName FROM tblSubProjects WHERE CustomerId= " + customerId + "  ORDER BY SubProjectName";
        connection = ConnectionProvider.getInstance().getConnection();

        try {
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                customerSubProjects.add(resultSet.getString("SubProjectName"));
            }

        } catch (SQLException sqlex) {
            throw new ServiceLocatorException(sqlex);
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
        return customerSubProjects;
    }//closing the method.

    public List getCustomerMaps(int customerId) throws ServiceLocatorException {

        List customerMaps = new ArrayList();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;

        queryString = "SELECT MapName FROM tblMaps WHERE CustomerId= " + customerId + "  ORDER BY MapName";
        connection = ConnectionProvider.getInstance().getConnection();

        try {
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                customerMaps.add(resultSet.getString("MapName"));
            }

        } catch (SQLException sqlex) {
            throw new ServiceLocatorException(sqlex);
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
        return customerMaps;
    }//closing the method.

    public int getProjectIdByName(String projName) throws ServiceLocatorException {
        int projectId = 0;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT * FROM tblProjects WHERE ProjectName = '" + projName + "'";


        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                projectId = resultSet.getInt("Id");
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return projectId;
    }

    public int getSubProjectIdByName(String subprojName) throws ServiceLocatorException {
        int subprojectId = 0;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT * FROM tblSubProjects WHERE SubProjectName = '" + subprojName + "'";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                subprojectId = resultSet.getInt("Id");
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return subprojectId;
    }

    public int getMapIdByName(String mapName) throws ServiceLocatorException {
        int mapId = 0;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT * FROM tblMaps WHERE MapName = '" + mapName + "'";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                mapId = resultSet.getInt("Id");
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return mapId;
    }

    public List getBridgeNos() throws ServiceLocatorException {

        List bridgeNosList = new ArrayList();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;

        queryString = "select * from tblbridgedetails";
        connection = ConnectionProvider.getInstance().getConnection();


        try {
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bridgeNosList.add(resultSet.getString("bridgenumber"));
            }

        } catch (SQLException sqlex) {
            throw new ServiceLocatorException(sqlex);
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
        return bridgeNosList;
    }//closing the method.

    public String[] getDepartmentWithOrgId(String userId) throws ServiceLocatorException {

        String[] values = new String[2];
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "select DepartmentId,OrgId  from tblEmployee where curStatus='Active' and loginId = '" + userId + "'";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {

                values[0] = resultSet.getString("DepartmentId");
                values[1] = resultSet.getString("OrgId");
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }

        }
        return values;
    }

    public List getTitleTypeByDepartment(String departmentName) throws ServiceLocatorException {

        List titleTypeListByDepartment = new ArrayList();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;

        queryString = "SELECT Description FROM tblLKTitleType WHERE DepartmentId = (SELECT Id FROM tblLKDepartment WHERE Description='" + departmentName + "')";
        connection = ConnectionProvider.getInstance().getConnection();


        try {
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                titleTypeListByDepartment.add(resultSet.getString("Description"));
            }

        } catch (SQLException sqlex) {
            throw new ServiceLocatorException(sqlex);
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
        return titleTypeListByDepartment;
    }//closing the method.

    public Map getEmployeeNamesWithoutAccount(String department) throws ServiceLocatorException {

        Map employeeMap = new TreeMap();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT Id,FName,LName,MName FROM tblEmployee WHERE curStatus='Active' and DepartmentId = ? and  PracticeId='Projects' ORDER BY FName";
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, department);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employeeMap.put(new Integer(resultSet.getInt("Id")),
                        resultSet.getString("FName") + " " + resultSet.getString("MName")
                        + "." + resultSet.getString("LName"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return employeeMap; // returning the object.
    } //closing the method.

    public Map getProjectEmployeeNames(int projectId) throws ServiceLocatorException {

        Map<String, String> employeeProjectMap = new TreeMap();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;
        StringBuilder genKey = new StringBuilder();
        StringBuilder genValue = new StringBuilder();

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = " select tblCrmContact.Id,concat(FirstName,'.',LastName) as empName from tblProjectTeam left outer join tblCrmContact on (tblProjectTeam.empId=tblCrmContact.id) where ProjectId=" + projectId;
            //queryString = "select * from vwProjectTeamEmployees where ProjectId=?";
            preparedStatement = connection.prepareStatement(queryString);
            //preparedStatement.setInt(1,projectId);
            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                genKey.append(resultSet.getInt("Id"));



                genValue.append(resultSet.getString("empName"));



                //employeeProjectMap.put(new Integer(genKey.toString()),
                employeeProjectMap.put(genKey.toString(), genValue.toString());
                genKey.setLength(0);
                genValue.setLength(0);

//                        new Integer(resultSet.getInt("ProjectId")),
//                        resultSet.getString("EmpName"));
//
//
//
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
        //System.out.println("employeeProjectMap---->"+employeeProjectMap);
        return employeeProjectMap; // returning the object.
    } //closing the method.

    /* public Map getProjectEmployeeNames(int projectId)throws ServiceLocatorException{
        
     Map employeeProjectMap = new TreeMap();//key-Description
     PreparedStatement preparedStatement = null;
     Connection connection = null;
     ResultSet resultSet = null;
     String queryString = null;
        
     try{
     connection = ConnectionProvider.getInstance().getConnection();
     queryString = "select tblEmployee.Id, tblEmployee.FName,tblEmployee.MName,tblEmployee.LName from tblProjectTeam join tblEmployee on tblProjectTeam.EmpId = tblEmployee.Id   where tblProjectTeam.ProjectId= ? ORDER BY tblEmployee.FName";
     //queryString = "select * from vwProjectTeamEmployees where ProjectId=?";
     preparedStatement = connection.prepareStatement(queryString);
     preparedStatement.setInt(1,projectId);
     resultSet = preparedStatement.executeQuery();
     while(resultSet.next()) {
     employeeProjectMap.put(new Integer(resultSet.getInt("Id")),
     resultSet.getString("FName")+" "+resultSet.getString("MName")
     +"."+resultSet.getString("LName"));
                
     //                        new Integer(resultSet.getInt("ProjectId")),
     //                        resultSet.getString("EmpName"));
     //
     //
     //
     }
            
            
     }catch (SQLException sql){
     throw new ServiceLocatorException(sql);
     }finally{
     try {
     // resultSet Object Checking if it's null then close and set null
     if(resultSet != null) {
     resultSet.close();
     resultSet = null;
     }
                
     if(preparedStatement != null) {
     preparedStatement.close();
     preparedStatement = null;
     }
                
     if(connection != null) {
     connection.close();
     connection = null;
     }
     } catch (SQLException ex) {
     throw new ServiceLocatorException(ex);
     }
            
     }
        
     return employeeProjectMap; // returning the object.
     } //closing the method.
     */
    public List getProjectEmployeeName(int projectId) throws ServiceLocatorException {

        List employeeProjectList = new ArrayList();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "select tblEmployee.Id, tblEmployee.FName,tblEmployee.MName,tblEmployee.LName from tblProjectTeam join tblEmployee on tblProjectTeam.EmpId = tblEmployee.Id  where tblProjectTeam.ProjectId= ? ORDER BY tblEmployee.FName";
            //queryString = "select * from vwProjectTeamEmployees where ProjectId=?";
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, projectId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employeeProjectList.add(resultSet.getString("FName") + " " + resultSet.getString("MName")
                        + "." + resultSet.getString("LName"));
                // titleTypeListByDepartment.add(resultSet.getString("Description"));

//                employeeProjectMap.put(new Integer(resultSet.getInt("Id")),
//                        resultSet.getString("FName")+" "+resultSet.getString("MName")
//                        +"."+resultSet.getString("LName"));

//                        new Integer(resultSet.getInt("ProjectId")),
//                        resultSet.getString("EmpName"));
//
//
//
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return employeeProjectList; // returning the object.
    } //closing the method.

    /**
     * @ author rajasekharYenduva (ryenduva@miraclesoft.com)
     * @ returns Map
     * @ throws
     */
    public String getEmployeeNameByEmpNo(int empNo) throws ServiceLocatorException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String queryString = null;
        ResultSet resultSet = null;
        String employeeName = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT Id,FName,LName,MName FROM tblEmployee WHERE curStatus='Active' and Id = ?";
            preparedStatement = connection.prepareStatement(queryString);

            preparedStatement.setInt(1, empNo);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employeeName = resultSet.getString("FName") + " " + resultSet.getString("MName")
                        + "." + resultSet.getString("LName");
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return employeeName;
    } //closing the method.

    /**
     * @ author rajasekharYenduva (ryenduva@miraclesoft.com)
     * @ returns Map
     * @ throws
     */
    public Map getEmployeeNames(String department) throws ServiceLocatorException {

        Map employeeMap = new TreeMap();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT Id,FName,LName,MName FROM tblEmployee WHERE DepartmentId = ? ORDER BY FName";
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, department);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employeeMap.put(new Integer(resultSet.getInt("Id")),
                        resultSet.getString("FName") + " " + resultSet.getString("MName")
                        + "." + resultSet.getString("LName"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return employeeMap; // returning the object.
    } //closing the method.

    public List getEmployeeNamesList(String department) throws ServiceLocatorException {

        List employeeMap = new LinkedList();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT LoginId,FName,LName,MName FROM tblEmployee WHERE curstatus='Active' and DepartmentId = ? ORDER BY FName";
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, department);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employeeMap.add(resultSet.getString("FName") + " " + resultSet.getString("MName")
                        + "." + resultSet.getString("LName"));
            }

        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return employeeMap; // returning the object.
    } //closing the method.

    /**
     * @ author rajasekharYenduva (ryenduva@miraclesoft.com)
     * @ returns Map for ActivityAssignedPersons.
     * @ throws
     */
    public Map getEmployeeNamesByUserId(String department) throws ServiceLocatorException {

        Map employeeMap = new TreeMap();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT LoginId,FName,LName,MName FROM tblEmployee WHERE curStatus='Active' and DepartmentId LIKE '" + department + "%' ORDER BY FName";
            preparedStatement = connection.prepareStatement(queryString);
            //preparedStatement = connection.prepareStatement(queryString);

            //preparedStatement.setString(1,department);

            //resultSet = preparedStatement.executeQuery();
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employeeMap.put(resultSet.getString("LoginId"),
                        resultSet.getString("FName") + " " + resultSet.getString("MName")
                        + "." + resultSet.getString("LName"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return employeeMap; // returning the object.
    } //closing the method.

    /**
     * @ author Ravi Chandu Dadi (rdadi@miraclesoft.com)
     * @ returns Map for ReportingPersons.
     * @ throws
     */
    public Map getEmployeeNamesByReportingPerson() throws ServiceLocatorException {
        // System.err.println("I am in getEmployeeNamesByReportingPerson");
        Map employeeMap = new TreeMap();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT LoginId,CONCAT(TRIM(FName),'.',TRIM(LName)) AS EmployeeName,isManager,isTeamLead FROM tblEmployee WHERE (isManager=1 OR isTeamLead=1) AND curstatus='Active'  GROUP BY EmployeeName";
            preparedStatement = connection.prepareStatement(queryString);
            //preparedStatement = connection.prepareStatement(queryString);

            //preparedStatement.setString(1,department);

            //resultSet = preparedStatement.executeQuery();
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employeeMap.put(resultSet.getString("LoginId"), resultSet.getString("EmployeeName"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return sortByValue(employeeMap); // returning the object.
    } //closing the method.

    /**
     * @ author MrutyumjayaRao Chennu(mchennu@miraclesoft.com)
     * @ returns Map for Employee Names and UserId's by Title of the Employee.
     * @ throws
     */
    public Map getEmployeeNamesUserIdByTitle(String title) throws ServiceLocatorException {

        Map employeeMap = new TreeMap();//key-Description
        Statement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT Id,FName,LName,MName FROM tblEmployee WHERE  curStatus='Active' and TitleTypeId ='" + title + "' ORDER BY FName";
            statement = connection.createStatement();
            //preparedStatement = connection.prepareStatement(queryString);

            //preparedStatement.setString(1,department);

            //resultSet = preparedStatement.executeQuery();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                employeeMap.put(resultSet.getString("Id"),
                        resultSet.getString("FName") + " " + resultSet.getString("MName")
                        + "." + resultSet.getString("LName"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }

        return employeeMap; // returning the object.
    } //closing the method.

    /**
     * @ author MrutyumjayaRao Chennu(mchennu@miraclesoft.com)
     * @ returns Map for Employee Names and UserId's by Title of the Employee.
     * @ throws
     */
    public Map getEmployeeNamesUserIdByTitle(String titleOne, String titleTwo) throws ServiceLocatorException {

        Statement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;
        Map employeeMap = new TreeMap();

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT Id,FName,LName,MName FROM tblEmployee WHERE curStatus='Active' and (TitleTypeId ='" + titleOne + "' OR  TitleTypeId ='" + titleTwo + "') ORDER BY FName";
            statement = connection.createStatement();
            //preparedStatement = connection.prepareStatement(queryString);

            //preparedStatement.setString(1,department);

            //resultSet = preparedStatement.executeQuery();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                employeeMap.put(resultSet.getString("Id"),
                        resultSet.getString("FName") + " " + resultSet.getString("MName")
                        + "." + resultSet.getString("LName"));
            }

        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }

        return employeeMap; // returning the object.
    } //closing the method.

    /**
     * @ author rajasekharYenduva (ryenduva@miraclesoft.com)
     * @ returns Map for ActivityAssignedPersons.
     * @ throws
     */
    public Map getEmployeeNames(String department, String title) throws ServiceLocatorException {

        Map employeeMap = new TreeMap();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT Id,FName,LName,MName FROM tblEmployee WHERE curStatus='Active' and DepartmentId = ? AND Title = ? ORDER BY Fname";
            preparedStatement = connection.prepareStatement(queryString);

            preparedStatement.setString(1, department);
            preparedStatement.setString(2, title);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employeeMap.put(new Integer(resultSet.getInt("Id")),
                        resultSet.getString("FName") + " " + resultSet.getString("MName")
                        + "." + resultSet.getString("LName"));
            }

        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return employeeMap; // returning the object.
    } //closing the method.

    /**
     * @ author rajasekharYenduva (ryenduva@miraclesoft.com)
     * @ returns Map for ActivityAssignedPersons.
     * @ throws
     */
    public Map getEmployeeNames(String organization, String department, String title) throws ServiceLocatorException {

        Map employeeMap = new TreeMap();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT DISTINCT Id ,LName,FName,MName FROM tblEmployee "
                    + "WHERE  curStatus='Active' and OrgId = ?  "
                    + "AND  DepartmentId = ? "
                    + "AND TitleTypeId = ?";

            preparedStatement = connection.prepareStatement(queryString);

            preparedStatement.setString(1, organization);
            preparedStatement.setString(2, department);
            preparedStatement.setString(3, title);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employeeMap.put(new Integer(resultSet.getInt("Id")),
                        resultSet.getString("FName") + " " + resultSet.getString("MName")
                        + "." + resultSet.getString("LName"));
            }

        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return employeeMap; // returning the object.
    } //closing the method.

    /**
     * @ author rajasekharYenduva (ryenduva@miraclesoft.com)
     * @ returns Map for ActivityAssignedPersons.
     * @ throws
     */
    public Map getEmployeeNames(String organization, String department, String title, int roleId) throws ServiceLocatorException {

        Map employeeMap = new TreeMap();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT DISTINCT Id ,LName,FName,MName FROM tblEmployee "
                    + "WHERE  curStatus='Active' and OrgId = ?  "
                    + "AND  DepartmentId = ? "
                    + "AND TitleTypeId=?  "
                    + "AND Id IN(SELECT EmpId FROM tblEmpRoles WHERE RoleId= ?)";

            preparedStatement = connection.prepareStatement(queryString);

            preparedStatement.setString(1, organization);
            preparedStatement.setString(2, department);
            preparedStatement.setString(3, title);
            preparedStatement.setInt(4, roleId);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employeeMap.put(new Integer(resultSet.getInt("Id")),
                        resultSet.getString("FName") + " " + resultSet.getString("MName")
                        + "." + resultSet.getString("LName"));
            }

        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return employeeMap; // returning the object.
    } //closing the method.

    /**
     * @ author MrutyumjayaRao Chennu (mchennu@miraclesoft.com)
     * @ returns Map to populate Point of contact person names by LoginId
     * @ throws
     */
    public Map getPointOfContact() throws ServiceLocatorException {

        Map employeeMap = new TreeMap();//key-Description
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT LoginId,FName,LName,MName FROM tblEmployee WHERE TitleTypeId =\"Jr. Operations Executive\"";
            queryString = queryString + " OR TitleTypeId =\"Sr. Operations Executive\" ORDER BY FName";
            statement = connection.createStatement();
            //preparedStatement = connection.prepareStatement(queryString);

            //preparedStatement.setString(1,department);

            //resultSet = preparedStatement.executeQuery();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                employeeMap.put(resultSet.getString("LoginId"),
                        resultSet.getString("FName") + " " + resultSet.getString("MName")
                        + "." + resultSet.getString("LName"));
            }

        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }

        return employeeMap; // returning the object.
    } //closing the method.

    public Collection getMenu(int roleId) throws ServiceLocatorException {

        LeftMenuVTO leftMenuVTO = null;
        Map leftMenuMap = new TreeMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        int counter = 10;

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT Action,Title,RoleId FROM vwLeftMenu WHERE RoleId=" + roleId + " ORDER BY ScreenOrder";
            statement = connection.createStatement();

            resultSet = statement.executeQuery(queryString);

            while (resultSet.next()) {
                leftMenuVTO = new LeftMenuVTO();
                leftMenuVTO.setScreenAction(resultSet.getString("Action"));
                leftMenuVTO.setScreenTitle(resultSet.getString("Title"));
                leftMenuMap.put(counter + "leftMenuVTO", leftMenuVTO);
                counter++;
            }

        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return leftMenuMap.values(); // returning the object.
    }

    public List getTeritory(String region) throws ServiceLocatorException {
        List territoryList = new ArrayList();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT TerritoryName FROM vwRegionTerritory WHERE RegionName='" + region + "' ORDER BY TerritoryName";
            statement = connection.createStatement();

            resultSet = statement.executeQuery(queryString);

            while (resultSet.next()) {
                territoryList.add(resultSet.getString("TerritoryName"));
            }

        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }


        return territoryList;// returning the object.
    }//closing the method.

    /**
     * @ returns List for Countries.
     * @ throws HibernateException.
     */
    public List getStates(String country) throws ServiceLocatorException {

        List statesList = new ArrayList();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT StateName FROM vwCountryState WHERE CountryName='" + country + "' ORDER BY StateName";
            statement = connection.createStatement();

            resultSet = statement.executeQuery(queryString);

            while (resultSet.next()) {
                statesList.add(resultSet.getString("StateName"));
            }

        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }

        return statesList; // returning the object.
    } //closing the method.

    public List getGreenSheetVendorNamesList(String greenSheetVendorNamesKey) throws Exception {

        List greenSheetVendorNamesList = new ArrayList();//Description
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;
        /*if (CacheManager.getCache().containsKey(greenSheetVendorNamesKey)) {
         
         greenSheetVendorNamesList  = (List) CacheManager.getCache().get(greenSheetVendorNamesKey);
         
         } else {*/
        try {
            String strSQL = "select Name from tblCrmAccount where Type IN('Vendor','1099')";
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(strSQL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                greenSheetVendorNamesList.add(resultSet.getString(1));
            }// closing for loop.

            Collections.sort(greenSheetVendorNamesList);
            //CacheManager.getCache().put(greenSheetVendorNamesKey,greenSheetVendorNamesList);

            //headerKey="-1" headerValue="-- Please Select --"
        } catch (Exception e) {
            throw new ServiceLocatorException(e);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
        //}// closing if condition.

        return greenSheetVendorNamesList;// returning the object.
    }//closing the method.

    public Map getGreenSheetCustomerNamesMap(String greenSheetCustomerNamesKey) throws Exception {

        Map greenSheetCustomerNamesMap = new TreeMap();//key-Description
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;

        if (CacheManager.getCache().containsKey(greenSheetCustomerNamesKey)) {

            greenSheetCustomerNamesMap = (Map) CacheManager.getCache().get(greenSheetCustomerNamesKey);
        } else {
            try {
                connection = ConnectionProvider.getInstance().getConnection();
                statement = connection.createStatement();
                String strSQL = "select Id,Name from tblCrmAccount where Type='Customer'";
                resultSet = statement.executeQuery(strSQL);
                while (resultSet.next()) {
                    greenSheetCustomerNamesMap.put(String.valueOf(resultSet.getInt(1)), resultSet.getString(2));

                }// closing for loop.


                CacheManager.getCache().put(greenSheetCustomerNamesKey, greenSheetCustomerNamesMap);
            } catch (Exception e) {
                throw new ServiceLocatorException(e);
            } finally {
                try {
                    // resultSet Object Checking if it's null then close and set null
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
                } catch (SQLException ex) {
                    throw new ServiceLocatorException(ex);
                }
            }
        }// closing if condition.

        return greenSheetCustomerNamesMap;// returning the object.
    }//closing the method.

    //Admin:Start
    public Map getAssignedRoles(int empId) throws ServiceLocatorException {

        Map assignedRoleMap = new TreeMap(); // Key-Description
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;

        connection = ConnectionProvider.getInstance().getConnection();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT RoleId,RoleName from  vwAddedRoles WHERE EmpId=" + empId);
            while (resultSet.next()) {
                assignedRoleMap.put(resultSet.getString("RoleId"), resultSet.getString("RoleName"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return assignedRoleMap; // returning the object.
    }//closing the method.

    public Map getNotAssignedRoles(int empId) throws ServiceLocatorException {
        Map notAssignedRoleMap = new TreeMap();// Key-Description
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        connection = ConnectionProvider.getInstance().getConnection();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select  Id as RoleId, tblLKRoles.description as RoleName  from tblLKRoles where tblLKRoles.id not in (select roleId from tblEmpRoles where empId=" + empId + ") ORDER BY RoleName");
            while (resultSet.next()) {
                notAssignedRoleMap.put(resultSet.getString("RoleId"), resultSet.getString("RoleName"));

            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }

        return notAssignedRoleMap; // returning the object.
    }//closing the method.

    public Map getAssignedScreens(int roleId) throws ServiceLocatorException {

        Map assignedScrensMap = new HashMap(); // Key-Description
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;

        connection = ConnectionProvider.getInstance().getConnection();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT ScreenId ,TileScreen  from  vwAssignRoleScreensList WHERE RoleId =" + roleId + " order by primaryflag DESC");
            while (resultSet.next()) {

                assignedScrensMap.put(new Integer(resultSet.getInt("ScreenId")), resultSet.getString("TileScreen"));

            }



        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }

        return assignedScrensMap; // returning the object.
    }//closing the method.

    public Map getAssignedAllScreens(int roleId) throws ServiceLocatorException {

        Map assignedAllScreensMap = new TreeMap(); // Key-Description
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String Query = "SELECT ScreenId ,TileScreen  from  vwAssignRoleScreensList WHERE RoleId <>" + roleId;

        try {
            statement = connection.createStatement();

            resultSet = statement.executeQuery("select  Id, Title from tblScreens where Id not in (select ScreenId from tblRoleScreens where RoleId=" + roleId + ")");

            while (resultSet.next()) {
                assignedAllScreensMap.put(new Integer(resultSet.getInt("Id")), resultSet.getString("Title"));
            }



        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }

        return assignedAllScreensMap;

    }

    /**
     * @ returns Map for default Reporting person Id's.
     * @ throws
     */
    public int getDefaultRoleId(String loginId) throws ServiceLocatorException {
        int roleId = Integer.parseInt(SecurityProperties.getProperty("DEFAULT_ROLE"));
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;

        queryString = "SELECT RoleId FROM tblEmpRoles WHERE EmpId=(SELECT Id FROM tblEmployee WHERE LoginId='" + loginId + "') AND PrimaryFlag=1";
        connection = ConnectionProvider.getInstance().getConnection();
        try {
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                roleId = resultSet.getInt("RoleId");
            }

        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
        return roleId;
    } //closing the method.

    public Map getMyRoles(int empId) throws ServiceLocatorException {
        //int roleId = Integer.parseInt(SecurityProperties.getProperty("DEFAULT_ROLE"));
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;
        Map roles = new TreeMap();
        // queryString = "SELECT  `tblLKRoles`.`Id`,`tblLKRoles`.`Description` FROM (`tblLKRoles` join `tblEmpRoles` on (`tblLKRoles`.`Id` = `tblEmpRoles`.`RoleId`)) WHERE `tblLKRoles`.`Description`!='Pre-Sales' AND `tblLKRoles`.`Description`!='Sourcing' AND`tblEmpRoles`.`EmpId`="+empId;
        //  queryString = "SELECT  `tblLKRoles`.`Id`,`tblLKRoles`.`Description` FROM (`tblLKRoles` join `tblEmpRoles` on (`tblLKRoles`.`Id` = `tblEmpRoles`.`RoleId`)) WHERE `tblLKRoles`.`Description`!='Pre-Sales' AND `tblLKRoles`.`Description`!='Sourcing' AND `tblLKRoles`.`Description`!='Recruitment Admin' AND `tblEmpRoles`.`EmpId`="+empId;
        queryString = "SELECT  `tblLKRoles`.`Id`,`tblLKRoles`.`Description` FROM (`tblLKRoles` join `tblEmpRoles` on (`tblLKRoles`.`Id` = `tblEmpRoles`.`RoleId`)) WHERE `tblLKRoles`.`Description`!='Sourcing' AND `tblLKRoles`.`Description`!='Recruitment Admin' AND `tblEmpRoles`.`EmpId`=" + empId;
        connection = ConnectionProvider.getInstance().getConnection();
        try {
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                //roleId = resultSet.getInt("RoleId");
                //System.err.println("role Id--"+resultSet.getString("Description"));
                roles.put(resultSet.getString("Id"), resultSet.getString("Description"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
        return roles;
    } //closing the method.

    /**
     * @ returns Map for default Reporting person Id's.
     * @ throws
     */
    public Map getReportsToDefault() throws ServiceLocatorException {

        Map reportsToDefault = new TreeMap();//Key-Description
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;

        queryString = "SELECT LoginId,FName,MName,LName FROM tblEmployee WHERE (IsManager=1 OR IsTeamLead=1) AND CurStatus='Active' ORDER BY FName";
        connection = ConnectionProvider.getInstance().getConnection();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                reportsToDefault.put(resultSet.getString("LoginId"), resultSet.getString("FName") + " " + resultSet.getString("MName") + "." + resultSet.getString("LName"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return reportsToDefault; // returning the object.
    } //closing the method.

    /**
     * @ returns Map for default Reporting person Id's.
     * @ throws
     */
    public Map getReportsToByDepartment(String departmentName) throws ServiceLocatorException {

        Map reportsToByDepartment = new TreeMap();//Key-Description
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        String loginId = null;
        String topManagementPeople[] = null;
        boolean isTopManager = false;
        if ("GDC".equals(departmentName) || "SSG".equals(departmentName) || "Marketing".equals(departmentName)) {
            queryString = "SELECT LoginId,FName,MName,LName FROM tblEmployee WHERE DepartmentId IN ('GDC','SSG','Marketing') AND (IsManager=1 OR IsTeamLead=1) AND CurStatus='Active' ORDER BY FName";
        } else {
            queryString = "SELECT LoginId,FName,MName,LName FROM tblEmployee WHERE DepartmentId='" + departmentName + "' AND (IsManager=1 OR IsTeamLead=1) AND CurStatus='Active' ORDER BY FName";
        }
        connection = ConnectionProvider.getInstance().getConnection();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);

            topManagementPeople = Properties.getProperty("Top.Management.People").split(",");

            for (String topManager : topManagementPeople) {
                reportsToByDepartment.put(topManager, Properties.getProperty(topManager));
            }


            //These Default Reporting Persons in our Organization
            /*reportsToByDepartment.put("plokam","Prasad Venkata.Lokam");
             reportsToByDepartment.put("mlokam","Madhavi Naga.Loakam");
             reportsToByDepartment.put("rsanku","Ram Nagesh.Sanku");
             reportsToByDepartment.put("sghandikota","Sudhakar.GhandiKota");
             reportsToByDepartment.put("dkancherla","Durga Prasad.Kancherla");*/
            while (resultSet.next()) {
                loginId = resultSet.getString("LoginId");
                isTopManager = false;
                for (String topManager : topManagementPeople) {
                    if (topManager.equals(loginId)) {
                        isTopManager = true;
                    }
                    break;
                }

                if (!isTopManager) {
                    reportsToByDepartment.put(loginId, resultSet.getString("FName") + " " + resultSet.getString("MName") + "." + resultSet.getString("LName"));
                }

//                if(!("plokam".equalsIgnoreCase(loginId) || "mlokam".equalsIgnoreCase(loginId)
//                || "rsanku".equalsIgnoreCase(loginId)
//                || "sghandikota".equalsIgnoreCase(loginId)
//                || "dkancherla".equalsIgnoreCase(loginId))){
//
//                    reportsToByDepartment.put(loginId,resultSet.getString("FName")+" "+resultSet.getString("MName")+"."+resultSet.getString("LName"));
//                }
            }

        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return reportsToByDepartment; // returning the object.
    } //closing the method.

    /**
     * @ returns List for Practice Names depends on Department
     * @ throws
     */
    public List getPracticeByDepartment(String departmentName) throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        List lKPracticeListByDepartment = new ArrayList();//Description

        lKPracticeListByDepartment.add("All");
        queryString = "SELECT Description FROM tblLKPractice WHERE DepartmentId = (SELECT Id FROM tblLKDepartment WHERE Description='" + departmentName + "')";

        connection = ConnectionProvider.getInstance().getConnection();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                lKPracticeListByDepartment.add(resultSet.getString("Description"));
            }
            Collections.sort(lKPracticeListByDepartment);

        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return lKPracticeListByDepartment; // returning the object.
    } //closing the method.

    /**
     * @ returns List for Practice Names depends on Department
     * @ throws
     */
    public List getSubPracticeByPractice(String practiceName) throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        List lKSubPracticeListByPractice = new ArrayList();//Description
        if ("All".equalsIgnoreCase(practiceName)) {
            queryString = "SELECT Description FROM tblLKSubPractice";
            lKSubPracticeListByPractice.add("All");
        } else {
            queryString = "SELECT Description FROM tblLKSubPractice WHERE PracticeId in (SELECT Id FROM tblLKPractice WHERE Description='" + practiceName + "')";
        }
        connection = ConnectionProvider.getInstance().getConnection();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                lKSubPracticeListByPractice.add(resultSet.getString("Description"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return lKSubPracticeListByPractice; // returning the object.
    } //closing the method.

    public List getTeamBySubPractice(String subPracticeName) throws ServiceLocatorException {

        List lKTeamList = new ArrayList();//Description

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;

       // if ("All".equalsIgnoreCase(subPracticeName)) {
            queryString = "SELECT Description FROM tblLKTeam";
            lKTeamList.add("All");
       // } else {
         //   queryString = "SELECT Description FROM tblLKTeam WHERE SubPracticeId IN (SELECT Id FROM tblLKSubPractice WHERE Description='" + subPracticeName + "')";
       // }
        connection = ConnectionProvider.getInstance().getConnection();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                lKTeamList.add(resultSet.getString("Description"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return lKTeamList; // returning the object.

    }//closing the method.

    /**
     * @ returns Map for Team Members.
     * @ throws
     */
    public Map getMyTeamMembers(String reportsTo, String departmentId) throws ServiceLocatorException {

        //System.out.println("reportsTo"+reportsTo);
        // System.out.println("departmentId"+departmentId);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;
        connection = ConnectionProvider.getInstance().getConnection();
        Map myTeamMembers = new TreeMap();

        try {
            if (CacheManager.getCache().containsKey(reportsTo + "Team")) {
                myTeamMembers = (Map) CacheManager.getCache().get(reportsTo + "Team");
            } else {
                if (reportsTo.equalsIgnoreCase(Properties.getProperty("CEO"))) {
                    queryString = "SELECT LoginId,FName,MName,LName,IsManager,IsTeamLead FROM tblEmployee WHERE  DepartmentId='Sales' and  ReportsTo= ? AND CurStatus='Active' AND DeletedFlag !=1 ORDER BY FName";
                } //new 
                else if (reportsTo.equalsIgnoreCase(Properties.getProperty("ExecutiveBoard"))) {
                    queryString = "SELECT LoginId,FName,MName,LName,IsManager,IsTeamLead FROM tblEmployee WHERE ReportsTo= ? AND CurStatus='Active' AND DeletedFlag !=1 ORDER BY FName";
                } else {
                    // queryString = "SELECT LoginId,FName,MName,LName,IsManager,IsTeamLead FROM tblEmployee WHERE  DepartmentId='" + departmentId + "' and ReportsTo= ? AND CurStatus='Active' AND DeletedFlag !=1 ORDER BY FName";
                    if (departmentId.equals("GDC") || departmentId.equals("SSG") || departmentId.equals("Marketing")) {
                        queryString = "SELECT LoginId,FName,MName,LName,IsManager,IsTeamLead FROM tblEmployee WHERE  (DepartmentId='SSG' OR DepartmentId='GDC' OR DepartmentId='Marketing') and ReportsTo= ? AND CurStatus='Active' AND DeletedFlag !=1 ORDER BY FName";
                    } else {
                        queryString = "SELECT LoginId,FName,MName,LName,IsManager,IsTeamLead FROM tblEmployee WHERE  DepartmentId='" + departmentId + "' and ReportsTo= ? AND CurStatus='Active' AND DeletedFlag !=1 ORDER BY FName";
                    }

                }

                preparedStatement = connection.prepareStatement(queryString);
                myTeamMembers = getMyTeamMembersUpTo(reportsTo, preparedStatement);

                //myTeamMembers.put(reportsTo, getFname_Lname(reportsTo));

                //Caching TeamMembers
                CacheManager.getCache().put(reportsTo + "Team", myTeamMembers);

            }//Closing Cache Checking
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }


        // System.out.println("myTeamMembers"+myTeamMembers);
        //System.out.println("I am Out of Data Source Provider"+myTeamMembers.size());
        return myTeamMembers; // returning the object.
    }

    public Map getMyTeamMembersUpTo(String reportsTo, java.sql.PreparedStatement theStatement) throws ServiceLocatorException {
        ResultSet resultSet = null;
        Map myTeamManagersMap = new TreeMap();
        Map tempMap = new TreeMap();
        String[] keys = new String[100];
        int keyCnt = 0;
        String key = null;
        String value = null;

        try {
            //System.out.println("Main ReportsTo:" + reportsTo);
            theStatement.setString(1, reportsTo);
            resultSet = theStatement.executeQuery();

            while (resultSet.next()) {
                key = resultSet.getString("LoginId");
                value = resultSet.getString("FName") + " " + resultSet.getString("MName") + "." + resultSet.getString("LName");
                myTeamManagersMap.put(key, value);
                // If the Team Member is a Manager then Get his Team Members List
                if ((resultSet.getBoolean("IsManager")) || (resultSet.getBoolean("IsTeamLead"))) {
                    keys[keyCnt] = key;
                    keyCnt++;
                    //  System.out.println("keyCnt--- Value"+keyCnt);

                }
            }

            for (int i = 0; i < keyCnt; i++) {
                key = keys[i];

                tempMap = getMyTeamMembersUpTo(key, theStatement);

                //========================================================================
                // Iterate over the List of Team Members arrived at for this Person and
                //  add them to the myTeamMembersMap
                //========================================================================
                if (tempMap.size() > 0) {
                    Iterator tempIterator = tempMap.entrySet().iterator();
                    while (tempIterator.hasNext()) {
                        Map.Entry entry = (Map.Entry) tempIterator.next();
                        key = entry.getKey().toString();
                        value = entry.getValue().toString();
                        myTeamManagersMap.put(key, value);
                        entry = null;
                    }
                }
                // System.out.println("keyCnt value"+keyCnt);
                // System.out.println("i value"+i);
            }

        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        }
        myTeamManagersMap = sortMapByValues(myTeamManagersMap);
        return myTeamManagersMap; // returning the object.
    } //closing the method

    public List getManagers(String managersKey) throws
            ServiceLocatorException {
        List managersList = new ArrayList();

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;
        try {
            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT concat(FName,'.',LName) as Name FROM tblEmployee WHERE IsManager=1 AND DeletedFlag !=1 and CurStatus='Active'";
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                managersList.add(resultSet.getString("Name"));
            }

        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
        return managersList; // returning the object.
    }//closing the method

    public int touchAccount(int accountId) throws ServiceLocatorException {
        CallableStatement callableStatement = null;
        Connection connection = null;
        String queryString = null;
        int count = 0;
        try {
            connection = ConnectionProvider.getInstance().getConnection();

            callableStatement = connection.prepareCall("{ call spTouchAccount(?)}");

            callableStatement.setInt(1, accountId);
            count = callableStatement.executeUpdate();

        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {

                if (callableStatement != null) {
                    callableStatement.close();
                    callableStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return count;
    }

    /**
     * Employee Issues utilities
     */
    // For Assigned List
    public List AddAssignedMembers(String managersKey) throws
            ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";
        List managersList = new ArrayList();

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            //queryString = "SELECT concat(FirstName,'.',LastName) as Name, LoginId FROM tblCatagory";
            queryString = "SELECT EmpName, EmpId FROM vwProjectTeamEmployees";
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                managersList.add(resultSet.getString("EmpName"));
            }

        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return managersList; // returning the object.
    }//closing the method

    // For Assigned List
    public List EditAssignedMembers(String empdept) throws
            ServiceLocatorException {

        // System.err.println("empdept--->"+empdept);
        String fName, lName, name[] = null;
        int num = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";
        List managersList = new ArrayList();
        //System.out.println("empName is"+empName);
        // name=empName.split("\\.");
        //for( num=0;num<2;num++)

        //   fName=name[num];
        //  lName=name[num+1];

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            //queryString = "SELECT concat(FirstName,'.',LastName) as Name, LoginId FROM tblCatagory";
            // queryString = "SELECT EmpName, EmpId FROM vwProjectTeamEmployees";
          /*  queryString="select concat(FirstName,'.',LastName) as EmpName from tblCatagory where CatagoryId = (select CatagoryId from " +
             "tblCatagory where FirstName='"+fName+"' and LastName='"+lName+"' )"; */

            if (empdept.equalsIgnoreCase("Sales") || empdept.equalsIgnoreCase("Recruitment")) {
                queryString = "SELECT CONCAT(TRIM(FName),'.',TRIM(LName)) as EmpName  from tblEmployee where DepartmentId='" + empdept + "'";
            } else {
                queryString = "SELECT CONCAT(TRIM(FirstName),'.',TRIM(LastName)) as EmpName from tblCatagory where CatagoryId ='" + empdept + "'";
            }

            //System.err.println("queryString-->"+queryString);
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                managersList.add(resultSet.getString("EmpName"));
            }

        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return managersList; // returning the object.
    }//closing the method

// To get DeptName
    public String getDepartmentName(String userId) throws ServiceLocatorException {
        String deptName = "";
        ResultSet resultSet = null;
        Statement statement = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "select DepartmentId from tblEmployee where loginId = '" + userId + "'";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                deptName = resultSet.getString("DepartmentId");
            }


        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }



        return deptName;
    }

    public String getTeamNameByLoginId(String userId) throws ServiceLocatorException {
        String teamName = "";
        ResultSet resultSet = null;
        Statement statement = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "select SubPractice from tblEmployee where loginId = '" + userId + "'";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                teamName = resultSet.getString("SubPractice");
            }


        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }



        return teamName;
    }
// get catagory Username

    //new on 10162012 for GET Accounts for BDM
    public String getPracticeIdByLoginId(String userId) throws ServiceLocatorException {
        String teamName = "";
        ResultSet resultSet = null;
        Statement statement = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "select PracticeId from tblEmployee where loginId = '" + userId + "'";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                teamName = resultSet.getString("PracticeId");
            }


        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }



        return teamName;
    }

    public String getFname_Lname(String userId) throws ServiceLocatorException {
        String Name = "";
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "select concat(trim(FName),'.',trim(LName)) as Name from tblEmployee where loginId = '" + userId + "'";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                Name = resultSet.getString("Name");
            }


        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }



        return Name;
    }

    public String getFname_Lname1(int empId) throws ServiceLocatorException {
        String Name = "";
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "select concat(FName,'.',LName) as Name from tblEmployee where Id =" + empId;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                Name = resultSet.getString("Name");
            }


        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }



        return Name;
    }

// get catagoryName
    public String getIssueCatagory(String userId) throws ServiceLocatorException {
        String catagoryName = "";
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "select CatagoryId from tblCatagory where LoginId = '" + userId + "'";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                catagoryName = resultSet.getString("CatagoryId");
            }


        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }



        return catagoryName;
    }
    //Recruitment Module Related Utils

    /**
     * @ returns map for LastContactBy.
     * @ throws HibernateException.
     */
    public Map getLastContactBy() throws ServiceLocatorException {
        Map contactByMap = new TreeMap();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        try {
            connection = ConnectionProvider.getInstance().getConnection();
            String queryString = "SELECT Id,concat(FName,'.',LName) as Name FROM tblEmployee WHERE DepartmentId ='Recruiting'";
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                contactByMap.put(new Integer(resultSet.getInt("Id")), resultSet.getString("Name"));
            }
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
            } catch (SQLException sql) {
                throw new ServiceLocatorException(sql);
            }
        }
        return contactByMap; // returning the object.
    } //closing the method.

    /**
     * @ returns map for RecContactId.
     * @ throws ServiceLocatorException.
     */
    public Map getRecContactId() throws ServiceLocatorException {
        Map recContactIdMap = new TreeMap();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            String queryString = "SELECT Id,concat(FName,'.',LName) as Name FROM tblEmployee WHERE DepartmentId ='Recruiting'";
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                recContactIdMap.put(new Integer(resultSet.getInt("Id")), resultSet.getString("Name"));
            }
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
            } catch (SQLException sql) {
                throw new ServiceLocatorException(sql);
            }
        }
        return recContactIdMap; // returning the object.
    } //closing the method.

    //LEAVE REQUEST MODULE RELATED
    public String getReportsTo(String userId) throws ServiceLocatorException {

        //System.out.println("userId--->"+userId);
        String reportsId = "";
        String levelReport = "";
        Connection connection = ConnectionProvider.getInstance().getConnection();
        Statement statement = null;
        String queryString = "";
        ResultSet resultSet = null;

        StringBuffer levelReportToId = new StringBuffer();

        try {

            for (int i = 1; i < 4; i++) {
                statement = connection.createStatement();
                queryString = "select ReportsTo from tblEmployee where curStatus='Active' and loginId = '" + userId + "'";
                resultSet = statement.executeQuery(queryString);

                while (resultSet.next()) {
                    reportsId = resultSet.getString("Reportsto");
                }
                //levelReportToId =  levelReportToId +  reportsId+";";
                userId = reportsId;

                if ((userId == "") || (userId.equalsIgnoreCase(null)) || (userId.equalsIgnoreCase(""))) {
                    i = 5;
                    levelReportToId.append(reportsId);
                } else {
                    levelReportToId.append(reportsId);
                    levelReportToId.append(";");
                }
                //System.out.println("reportsId----->"+reportsId);
                // resultSet  = null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
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
            } catch (SQLException sql) {
                throw new ServiceLocatorException(sql);
            }
        }

        levelReport = levelReportToId.toString();
        //System.out.println("levelReportsTo---->"+levelReport);
        levelReport = levelReport.substring(0, levelReport.length() - 1);
        //System.out.println("levelReport---111------->"+levelReport);
        return levelReport;
    }

    public String getReportsTOOneLevel(String userId) throws ServiceLocatorException {

        String reportTo = "";
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "select reportsto from tblEmployee where curStatus='Active' and  loginId = '" + userId + "'";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                reportTo = resultSet.getString("reportsto");
            }

        } catch (SQLException ex) {
            throw new ServiceLocatorException(ex);
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
            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }


        return reportTo;
    }

    public String getDownReportsToFourLevel(String userId) throws ServiceLocatorException {
        String reportsId = "";
        String levelReportToId = userId + ",";
        Connection connection = ConnectionProvider.getInstance().getConnection();
        Statement statement = null;
        String queryString = "";
        String[] reportsTo = new String[3];
        ResultSet resultSet = null;

        try {

            for (int i = 0; i < 2; i++) {
                statement = connection.createStatement();
                queryString = "select loginId  from tblEmployee where ReportsTo = '" + userId + "'";

                resultSet = statement.executeQuery(queryString);

                while (resultSet.next()) {
                    reportsId = resultSet.getString("loginId");

                    if (reportsId.equalsIgnoreCase(reportsTo[i])) {
                        userId = reportsId;
                        levelReportToId = levelReportToId + reportsId + ",";
                    }

                }
            }

        } catch (SQLException ex) {
            throw new ServiceLocatorException(ex);
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
            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }

        }

        levelReportToId = levelReportToId.substring(0, levelReportToId.length() - 1);

        return levelReportToId;
    }

    //Projects Related Module
    /**
     * @ author MrutyumjayaRao Chennu(mchennu@miraclesoft.com)
     * @ returns Map for Employee Names and UserId's by Title of the Employee.
     * @ throws
     */
    public Map getEmployeeNames1(String title) throws ServiceLocatorException {
        Map employeeMap1 = new TreeMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionProvider.getInstance().getConnection();

            String queryString = "SELECT LoginId,FName,LName,MName FROM tblEmployee WHERE TitleTypeId ='" + title + "' ORDER BY FName";
            statement = connection.createStatement();
            //preparedStatement = connection.prepareStatement(queryString);

            //preparedStatement.setString(1,department);

            //resultSet = preparedStatement.executeQuery();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                employeeMap1.put(resultSet.getString("LoginId"),
                        resultSet.getString("FName") + " " + resultSet.getString("MName")
                        + "." + resultSet.getString("LName"));
            }

        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
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

        }

        return employeeMap1; // returning the object.
    } //closing the method.

    /**
     * @ author MrutyumjayaRao Chennu(mchennu@miraclesoft.com)
     * @ returns Map for Employee Names and UserId's by Title of the Employee.
     * @ throws
     */
    public Map getEmployeeNames2(String title) throws ServiceLocatorException {
        Map employeeMap2 = new TreeMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionProvider.getInstance().getConnection();

            String queryString = "SELECT LoginId,FName,LName,MName FROM tblEmployee WHERE TitleTypeId ='" + title + "' ORDER BY FName";
            statement = connection.createStatement();
            //preparedStatement = connection.prepareStatement(queryString);

            //preparedStatement.setString(1,department);

            //resultSet = preparedStatement.executeQuery();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                employeeMap2.put(resultSet.getString("LoginId"),
                        resultSet.getString("FName") + " " + resultSet.getString("MName")
                        + "." + resultSet.getString("LName"));
            }

        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
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

        }

        return employeeMap2; // returning the object.
    } //closing the method.

    /**
     * @ author MrutyumjayaRao Chennu(mchennu@miraclesoft.com)
     * @ returns Map for Employee Names and UserId's by Title of the Employee.
     * @ throws
     */
    public Map getEmployeeNames3(String title) throws ServiceLocatorException {
        Map employeeMap3 = new TreeMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionProvider.getInstance().getConnection();

            String queryString = "SELECT LoginId,FName,LName,MName FROM tblEmployee WHERE TitleTypeId ='" + title + "' ORDER BY FName";
            statement = connection.createStatement();
            //preparedStatement = connection.prepareStatement(queryString);

            //preparedStatement.setString(1,department);

            //resultSet = preparedStatement.executeQuery();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                employeeMap3.put(resultSet.getString("LoginId"),
                        resultSet.getString("FName") + " " + resultSet.getString("MName")
                        + "." + resultSet.getString("LName"));
            }

        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
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

        }

        return employeeMap3; // returning the object.
    } //closing the method.

    public String getAccountName(int accountId) throws ServiceLocatorException {
        String accName = "";
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        String queryString = "SELECT * FROM tblCrmAccount WHERE Id=" + accountId;
        //System.out.print("QS:----->"+queryString);
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                accName = resultSet.getString("Name");
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex.getMessage());
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return accName;
    }

    public String getContactName(int contactId) throws ServiceLocatorException {
        String contactName = "";
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT CONCAT(FirstName,' ',MiddleName,' ',LastName) AS Name FROM tblCrmContact WHERE Id=" + contactId;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                contactName = resultSet.getString("Name");
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return contactName;
    }
    //The following methods are implemented for sending the reminders about TimeSheet Status.
    //Starts Here.

    public String getEmpLoginIdById(String empId) throws ServiceLocatorException {
        String loginId = "";
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        //System.out.println("Emp Full Names -->"+empName);
        String queryString = "SELECT LoginId FROM tblEmployee WHERE Id ='" + empId + "'";

        //String queryString ="SELECT LoginId FROM tblEmployee WHERE CONCAT(fName,' ',mName,'.',lName) ='"+empName+"'";
        // System.out.println("Query-->"+queryString);

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                loginId = resultSet.getString("LoginId");
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return loginId;
    }

    //New on 04242013 by vkandregula@miraclesoft.com
    //get employee email through name
    public String getEmpEmailById(String empName) throws ServiceLocatorException {
        String email = "";
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        //System.out.println("Emp Full Names -->"+empName);
        String queryString = "SELECT Email1 FROM tblEmployee WHERE CONCAT(fName,' ',mName,'.',lName) ='" + empName + "'";
        //System.out.println("Query-->"+queryString);

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                email = resultSet.getString("Email1");
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return email;
    }

    /*    public String getTimeSheetStatus(String empId, String timeSheetID,String empType,String resourceType) throws ServiceLocatorException {
     String status = "";
     Statement statement = null;
     ResultSet resultSet = null;
     //     Statement statement1 = null;
     //    ResultSet resultSet1 = null;
     Connection connection = ConnectionProvider.getInstance().getConnection();
     //      Connection connection1 = ConnectionProvider.getInstance().getConnection();
     String queryString = "";
     //         String queryString1 = "";
     if(empType.equalsIgnoreCase("e")){
     if(resourceType.equalsIgnoreCase("e")){
     queryString ="SELECT Description FROM vwTimeSheetList WHERE EmpId = '"+empId+"' AND TimeSheetId = '"+timeSheetID+"'";
     }
     else
     {
     queryString ="SELECT Description FROM vwCustTimeSheetList WHERE EmpId = '"+empId+"' AND TimeSheetId = '"+timeSheetID+"'";  
     }
     }else{
     queryString ="SELECT Description FROM vwCustTimeSheetList WHERE EmpId = '"+empId+"' AND TimeSheetId = '"+timeSheetID+"'";  
     }
        
        
     try{

            
            
     statement =connection.createStatement();
     resultSet =statement.executeQuery(queryString);
     while(resultSet.next()){
     status = resultSet.getString("Description");
     }
     }catch (Exception ex) {
     throw new ServiceLocatorException(ex);
     }finally {
            
     try{
     if(resultSet != null){
     resultSet.close();
     resultSet = null;
     }
     if(statement != null){
     statement.close();
     statement = null;
     }
     if(connection != null){
     connection.close();
     connection = null;
     }
     }catch(SQLException sqle){
     throw new ServiceLocatorException(sqle);
     }
     }
     return status;
     }
     */
    public String getTimeSheetStatus(String empId, String timeSheetID, String empType, String resourceType) throws ServiceLocatorException {
        String status = "";
        Statement statement = null;
        ResultSet resultSet = null;
        Statement statement1 = null;
        ResultSet resultSet1 = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        Connection connection1 = ConnectionProvider.getInstance().getConnection();
        String queryString = "";
        String queryString1 = "";


        try {
            if (empType.equalsIgnoreCase("e")) {
                if (resourceType.equalsIgnoreCase("e")) {
                    queryString1 = "select * from tblEmployee where Id=" + empId;

                    statement1 = connection1.createStatement();
                    resultSet1 = statement1.executeQuery(queryString1);
                    if (resultSet1.next()) {
                        queryString = "SELECT Description FROM vwTimeSheetList WHERE EmpId = '" + empId + "' AND TimeSheetId = '" + timeSheetID + "'";
                    } else {
                        queryString = "SELECT Description FROM vwCustTimeSheetList WHERE EmpId = '" + empId + "' AND TimeSheetId = '" + timeSheetID + "'";
                    }
                } else {
                    queryString = "SELECT Description FROM vwCustTimeSheetList WHERE EmpId = '" + empId + "' AND TimeSheetId = '" + timeSheetID + "'";
                }
            } else {
                queryString = "SELECT Description FROM vwCustTimeSheetList WHERE EmpId = '" + empId + "' AND TimeSheetId = '" + timeSheetID + "'";
            }
            // System.out.println(queryString);


            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                status = resultSet.getString("Description");
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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
                if (resultSet1 != null) {
                    resultSet1.close();
                    resultSet1 = null;
                }
                if (statement1 != null) {
                    statement1.close();
                    statement1 = null;
                }
                if (connection1 != null) {
                    connection1.close();
                    connection1 = null;
                }
            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return status;
    }

    public String reportsTo(String empId) throws ServiceLocatorException {
        String reportsTo = "";
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        // String queryString ="SELECT ReportsTo FROM tblEmployee WHERE CONCAT(fName,' ',mName,'.',lName) = '"+empName+"'";
        String queryString = "SELECT ReportsTo FROM tblEmployee WHERE Id ='" + empId + "'";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                reportsTo = resultSet.getString("ReportsTo");
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return reportsTo;
    }
    /*    public String getTimeSheetStartDate(String empId, String timeSheetID,String empType,String resourceType) throws ServiceLocatorException {
     String startDate = "";
     Statement statement = null;
     ResultSet resultSet = null;
     Statement statement1 = null;
     ResultSet resultSet1 = null;
     Connection connection = ConnectionProvider.getInstance().getConnection();
     Connection connection1 = ConnectionProvider.getInstance().getConnection();
     String queryString = "";
     //String queryString1 = "";
     if(empType.equalsIgnoreCase("e")){
     if(resourceType.equalsIgnoreCase("e")){
     queryString="SELECT DateStart FROM vwTimeSheetList WHERE EmpId = "+empId+" AND TimeSheetId = '"+timeSheetID+"'";
     }else
     {
     queryString="SELECT DateStart FROM vwCustTimeSheetList WHERE EmpId = "+empId+" AND TimeSheetId = '"+timeSheetID+"'";   
     }
     }else{
     queryString="SELECT DateStart FROM vwCustTimeSheetList WHERE EmpId = "+empId+" AND TimeSheetId = '"+timeSheetID+"'";   
     }

     try{
     statement =connection.createStatement();
     resultSet =statement.executeQuery(queryString);
     while(resultSet.next()){
     startDate = DateUtility.getInstance().convertDateToView(resultSet.getDate("DateStart"));
     }
     }catch (Exception ex) {
     throw new ServiceLocatorException(ex);
     }finally {
            
     try{
     if(resultSet != null){
     resultSet.close();
     resultSet = null;
     }
     if(statement != null){
     statement.close();
     statement = null;
     }
     if(connection != null){
     connection.close();
     connection = null;
     }
     }catch(SQLException sqle){
     throw new ServiceLocatorException(sqle);
     }
     }
     return startDate;
     }*/

    public String getTimeSheetStartDate(String empId, String timeSheetID, String empType, String resourceType) throws ServiceLocatorException {
        String startDate = "";
        Statement statement = null;
        ResultSet resultSet = null;
        Statement statement1 = null;
        ResultSet resultSet1 = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        Connection connection1 = ConnectionProvider.getInstance().getConnection();
        String queryString = "";
        String queryString1 = "";


        try {
            if (empType.equalsIgnoreCase("e")) {
                if (resourceType.equalsIgnoreCase("e")) {
                    //queryString="SELECT DateStart FROM vwTimeSheetList WHERE EmpId = "+empId+" AND TimeSheetId = '"+timeSheetID+"'";
                    queryString1 = "select * from tblEmployee where Id=" + empId;

                    statement1 = connection1.createStatement();
                    resultSet1 = statement1.executeQuery(queryString1);
                    if (resultSet1.next()) {
                        queryString = "SELECT DateStart FROM vwTimeSheetList WHERE EmpId = '" + empId + "' AND TimeSheetId = '" + timeSheetID + "'";
                    } else {
                        queryString = "SELECT DateStart FROM vwCustTimeSheetList WHERE EmpId = '" + empId + "' AND TimeSheetId = '" + timeSheetID + "'";
                    }
                } else {
                    queryString = "SELECT DateStart FROM vwCustTimeSheetList WHERE EmpId = " + empId + " AND TimeSheetId = '" + timeSheetID + "'";
                }
            } else {
                queryString = "SELECT DateStart FROM vwCustTimeSheetList WHERE EmpId = " + empId + " AND TimeSheetId = '" + timeSheetID + "'";
            }



            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                startDate = DateUtility.getInstance().convertDateToView(resultSet.getDate("DateStart"));
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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
                if (resultSet1 != null) {
                    resultSet1.close();
                    resultSet1 = null;
                }
                if (statement1 != null) {
                    statement1.close();
                    statement1 = null;
                }
                if (connection1 != null) {
                    connection1.close();
                    connection1 = null;
                }

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return startDate;
    }

    /* public String getTimeSheetEndDate(String empId, String timeSheetID,String empType,String resourceType) throws ServiceLocatorException {
     String endDate = "";
     Statement statement = null;
     ResultSet resultSet = null;
     //  Statement statement1 = null;
     // ResultSet resultSet1 = null;
     Connection connection = ConnectionProvider.getInstance().getConnection();
     //Connection connection1 = ConnectionProvider.getInstance().getConnection();
     String queryString = "";
     // String queryString1 = "";
     if(empType.equalsIgnoreCase("e")){
     if(resourceType.equalsIgnoreCase("e")){
     queryString ="SELECT DateEnd FROM vwTimeSheetList WHERE EmpId = "+empId+" AND TimeSheetId = '"+timeSheetID+"'"; 
     }else{
     queryString ="SELECT DateEnd FROM vwCustTimeSheetList WHERE EmpId = "+empId+" AND TimeSheetId = '"+timeSheetID+"'"; 
     }
     }
     else{
     queryString ="SELECT DateEnd FROM vwCustTimeSheetList WHERE EmpId = "+empId+" AND TimeSheetId = '"+timeSheetID+"'"; 
     }
       
        
     try{
              
     statement =connection.createStatement();
     resultSet =statement.executeQuery(queryString);
     while(resultSet.next()){
     endDate = DateUtility.getInstance().convertDateToView(resultSet.getDate("DateEnd"));
     }
     }catch (Exception ex) {
     throw new ServiceLocatorException(ex);
     }finally {
            
     try{
     if(resultSet != null){
     resultSet.close();
     resultSet = null;
     }
     if(statement != null){
     statement.close();
     statement = null;
     }
     if(connection != null){
     connection.close();
     connection = null;
     }
               
                
     }catch(SQLException sqle){
     throw new ServiceLocatorException(sqle);
     }
     }
     return endDate;
     } */
    public String getTimeSheetEndDate(String empId, String timeSheetID, String empType, String resourceType) throws ServiceLocatorException {
        String endDate = "";
        Statement statement = null;
        ResultSet resultSet = null;
        Statement statement1 = null;
        ResultSet resultSet1 = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        Connection connection1 = ConnectionProvider.getInstance().getConnection();
        String queryString = "";
        String queryString1 = "";



        try {
            if (empType.equalsIgnoreCase("e")) {
                if (resourceType.equalsIgnoreCase("e")) {
                    // queryString ="SELECT DateEnd FROM vwTimeSheetList WHERE EmpId = "+empId+" AND TimeSheetId = '"+timeSheetID+"'"; 
                    queryString1 = "select * from tblEmployee where Id=" + empId;

                    statement1 = connection1.createStatement();
                    resultSet1 = statement1.executeQuery(queryString1);
                    if (resultSet1.next()) {
                        queryString = "SELECT DateEnd FROM vwTimeSheetList WHERE EmpId = '" + empId + "' AND TimeSheetId = '" + timeSheetID + "'";
                    } else {
                        queryString = "SELECT DateEnd FROM vwCustTimeSheetList WHERE EmpId = '" + empId + "' AND TimeSheetId = '" + timeSheetID + "'";
                    }
                } else {
                    queryString = "SELECT DateEnd FROM vwCustTimeSheetList WHERE EmpId = " + empId + " AND TimeSheetId = '" + timeSheetID + "'";
                }
            } else {
                queryString = "SELECT DateEnd FROM vwCustTimeSheetList WHERE EmpId = " + empId + " AND TimeSheetId = '" + timeSheetID + "'";
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                endDate = DateUtility.getInstance().convertDateToView(resultSet.getDate("DateEnd"));
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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
                if (resultSet1 != null) {
                    resultSet1.close();
                    resultSet1 = null;
                }
                if (statement1 != null) {
                    statement1.close();
                    statement1 = null;
                }
                if (connection1 != null) {
                    connection1.close();
                    connection1 = null;
                }

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return endDate;
    }

    public String getContactOfficeMail(int contactId) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String contactOfficeMailId = null;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            //queryString = "select  concat(FirstName,'.',LastName) as Name from tblCatagory where CatagoryId = '" + empName + "'";;
            queryString = "select Email1 from tblCrmContact where Id = " + contactId;
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                contactOfficeMailId = resultSet.getString("Email1") + "^-";
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return contactOfficeMailId;
    }

    public String getContactPersonalMail(int contactId) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String contactPersonalMailId = null;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            //queryString = "select  concat(FirstName,'.',LastName) as Name from tblCatagory where CatagoryId = '" + empName + "'";;
            queryString = "select Email2 from tblCrmContact where Id = " + contactId;
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                contactPersonalMailId = resultSet.getString("Email2");
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return contactPersonalMailId;
    }

    public Map getAllSalesTeamExceptAccountTeam() throws ServiceLocatorException {
        Map salesTeamExceptAccountTeamMap = new TreeMap();
        salesTeamExceptAccountTeamMap.clear();

        ResultSet resultSet = null;
        Connection connection = null;
        Statement statement = null;

        connection = ConnectionProvider.getInstance().getConnection();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT LoginId,CONCAT(TRIM(FName),'.',TRIM(LName)) AS EmployeeName FROM tblEmployee WHERE DepartmentId='Sales' AND CurStatus='Active' ORDER BY FName");
            while (resultSet.next()) {
                salesTeamExceptAccountTeamMap.put(resultSet.getString("LoginId"), resultSet.getString("EmployeeName"));
            }


        } catch (SQLException ex) {
            throw new ServiceLocatorException(ex);

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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }

        return salesTeamExceptAccountTeamMap;
    }

    //Ended Here.
    public String getRequirementTitle(String id) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String requirementTitle = null;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            //queryString = "select  concat(FirstName,'.',LastName) as Name from tblCatagory where CatagoryId = '" + empName + "'";;
            queryString = "select JobTitle from tblRecRequirement where Id = " + id;
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                requirementTitle = resultSet.getString("JobTitle");
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return requirementTitle;
    }

    public String getLoginIdByEmpId(int empId) throws ServiceLocatorException {

        //String projectType = "";
        String empLoginID = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        //queryString = "SELECT concat(FirstName,'.',LastName) as Name, LoginId FROM tblCatagory";
        String queryString = "Select Id,LoginId from tblEmployee where Id=" + empId;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                empLoginID = resultSet.getString("LoginId");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return empLoginID;
    }

    public int getEmpIdByLoginId(String loginId) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";
        int empId = 0;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            //queryString = "select  concat(FirstName,'.',LastName) as Name from tblCatagory where CatagoryId = '" + empName + "'";;
            queryString = "select Id from tblEmployee where LoginId='" + loginId + "'";
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                empId = resultSet.getInt("Id");
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return empId;
    }

    public String getConsultantEmail(String consultId) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String consultEmail = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT Email2 FROM tblRecConsultant where Id=" + consultId;
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                consultEmail = resultSet.getString("Email2");
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return consultEmail;
    }

    public String getConsultantName(int consultantId) throws ServiceLocatorException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String queryString = null;
        ResultSet resultSet = null;
        String consultantName = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT Id,FName,LName,MName FROM tblRecConsultant WHERE Id = ?";
            preparedStatement = connection.prepareStatement(queryString);

            preparedStatement.setInt(1, consultantId);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                consultantName = resultSet.getString("FName") + " " + resultSet.getString("MName")
                        + "." + resultSet.getString("LName");
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return consultantName;
    }

    public String getResumeName(String resumeId) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resumeName = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT AttachmentName FROM tblRecAttachments where Id=" + resumeId;
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                resumeName = resultSet.getString("AttachmentName");
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return resumeName;
    }

    public String getEmployeeOrganisation(int empNo) throws ServiceLocatorException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String queryString = null;
        ResultSet resultSet = null;
        String organisationName = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT Id,OrgId FROM tblEmployee WHERE Id = ?";
            preparedStatement = connection.prepareStatement(queryString);

            preparedStatement.setInt(1, empNo);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                organisationName = resultSet.getString("OrgId");
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return organisationName;
    } //closing the method.

    public List getManagerUserIdOfRecruitment() throws ServiceLocatorException {

        List managersList = new ArrayList();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String queryString = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "select LoginId from tblEmployee where DepartmentId like 'recruiting' and IsManager = 1 and CurStatus='Active'";
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                managersList.add(resultSet.getString("LoginId"));
            }
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
        return managersList;
    }

    public String getCreatedByRequirementId(String id) throws ServiceLocatorException {
        String createdBy = "";
        String queryString = "";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT CreatedBy from tblRecRequirement where Id='" + id + "'";
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                createdBy = resultSet.getString("CreatedBy");
            }
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
        return createdBy;
    }

    public String getWorkPhNoByLoginId(String loginId) throws ServiceLocatorException {
        String workPh = "";
        String queryString = "";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT WorkPhoneNo from tblEmployee where LoginId='" + loginId + "'";
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                workPh = resultSet.getString("WorkPhoneNo");
            }
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
        return workPh;
    }

    public HashMap getTeamcalendar(String ReportsTo) throws Exception {
        // int num2=0;  //k
        int num1 = 0;  //i
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        // ArrayList  arrayList;
        teamCalendarMap = new HashMap();
        try {
            String dept = "Sales";
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("select Id,concat(FName,'.',LName) as empName,ReportsTo,LoginId from tblEmployee where ReportsTo='" + ReportsTo + "' and DepartmentId='" + dept + "'");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ReportsTo = resultSet.getString("LoginId");
                int id = resultSet.getInt("Id");
                String empName = resultSet.getString("empName");
                teamCalendarMap.put(id, empName);
                str[num1] = ReportsTo;
                num1++;
                num2 = num1;
            }
            for (num1 = 0; num1 < num2; num1++) {
                getHierarchy(str[num1], connection);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
                ex.printStackTrace();
            }
        }
        //System.err.println("teamCalendarMap length is"+teamCalendarMap.size());
        return (HashMap) teamCalendarMap;

    }

    private void getHierarchy(String ReportsTo, Connection connection) {
        // Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            int p = 0;
            int q = 0;
            int s = 0;
            // Class.forName("com.mysql.jdbc.Driver");
            // connection=DriverManager.getConnection("jdbc:mysql://172.17.4.60:3306/mirageteam","mirage","miragemysql");
            //connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("select Id,concat(FName,'.',LName) as empName,ReportsTo,LoginId from tblEmployee where ReportsTo='" + ReportsTo + "'");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ReportsTo = resultSet.getString("LoginId");
                int id = resultSet.getInt("Id");
                String empName = resultSet.getString("empName");
                teamCalendarMap.put(id, empName);
                str[num2] = ReportsTo;
                num2++;
                // s=num2;
            }

        } catch (Exception ex1) {
            ex1.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }

                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }

//                if(connection != null) {
//                    connection.close();
//                    connection = null;
//                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public List getProjectsList(String empId) throws ServiceLocatorException {
        List project = new ArrayList();
        ResultSet resultSet = null;
        Connection connection = null;
        Statement statement = null;
        String queryString = null;
        connection = ConnectionProvider.getInstance().getConnection();

        try {
            statement = connection.createStatement();
            //queryString = "Select Id,ProjectName FROM tblProjects WHERE Id in (select ProjectId from vwProjectTeamEmployees where EmpId="+empId+" ORDER BY ProjectName DESC)";
            queryString = "Select  Id,ProjectName,ProjectStartDate,ProjectEndDate,TotalResources,CustomerId,ProjectManagerUID FROM tblProjects WHERE ProjectManagerUID='" + empId + "'";
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                project.add(resultSet.getString("ProjectName"));
            }
        } catch (SQLException ex) {
            throw new ServiceLocatorException(ex);
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return project;
    }

    public Map getPrjTypeAndId() throws ServiceLocatorException {
        Map prjNameAndId = new TreeMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        queryString = "select Description,Id from tblLKProjectType";
        connection = ConnectionProvider.getInstance().getConnection();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                prjNameAndId.put(resultSet.getInt("Id"), resultSet.getString("Description"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }

        return prjNameAndId;
    }

    public Map getAccNameAndId() throws ServiceLocatorException {
        Map accNameAndId = new TreeMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        queryString = "select Id,Name from tblCrmAccount";
        connection = ConnectionProvider.getInstance().getConnection();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                accNameAndId.put(resultSet.getInt("Id"), resultSet.getString("Name"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }

        return accNameAndId;
    }

    public String getPrjDescById(int Id) throws ServiceLocatorException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String queryString = null;
        ResultSet resultSet = null;
        String prjDesc = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "select Id,Description from tblLKProjectType where Id = ?";
            preparedStatement = connection.prepareStatement(queryString);

            preparedStatement.setInt(1, Id);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                prjDesc = resultSet.getString("Description");
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return prjDesc;
    } //closing the method.

    public String getLeaveApprovalList(String userId) throws ServiceLocatorException {
        ArrayList list = new ArrayList();
        StringBuffer stringBuffer = new StringBuffer();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;
        try {
            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "select LoginId from tblEmployee where reportsTo  ='" + userId + "'";
            preparedStatement = connection.prepareStatement(queryString);

            //preparedStatement.setString(1,userId);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                stringBuffer.append("'");
                stringBuffer.append(resultSet.getString("LoginId"));
                stringBuffer.append("'");
                stringBuffer.append(",");
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
        return stringBuffer.substring(0, stringBuffer.length() - 1).toString();
    }

    public Map getCrmManagers() throws ServiceLocatorException {

        Map assignedMangerMap = new TreeMap(); // Key-Description
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;

        connection = ConnectionProvider.getInstance().getConnection();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT id,name from vwSalesPersonsList WHERE IsManager=1");
            while (resultSet.next()) {
                assignedMangerMap.put(resultSet.getString("id"), resultSet.getString("name"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return assignedMangerMap; // returning the object.
    }//closing the method.

    public String getEmailId(int empId) throws ServiceLocatorException {

        //String projectType = "";
        String emailId = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        //queryString = "SELECT concat(FirstName,'.',LastName) as Name, LoginId FROM tblCatagory";
        String queryString = "Select Id,Email1 from tblEmployee where Id=" + empId;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                emailId = resultSet.getString("Email1");
                int temp = emailId.indexOf("@");
                emailId = emailId.substring(0, temp);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return emailId;
    }

//    public String getDetailsByRequirementId(String id) throws ServiceLocatorException {
//        String createdBy = "";
//        String practice = "";
//        String queryString = "";
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        
//        try {
//            connection = ConnectionProvider.getInstance().getConnection();
//            queryString = "SELECT CreatedBy,Practice from tblRecRequirement where Id='"+id+"'";
//            preparedStatement = connection.prepareStatement(queryString);
//            resultSet = preparedStatement.executeQuery();
//            while(resultSet.next()) {
//                createdBy = resultSet.getString("CreatedBy");
//                practice = resultSet.getString("Practice");
//            }
//        }catch (SQLException sql){
//            throw new ServiceLocatorException(sql);
//        }finally{
//            try {
//                // resultSet Object Checking if it's null then close and set null
//                if(resultSet != null) {
//                    resultSet.close();
//                    resultSet = null;
//                }
//                
//                if(preparedStatement != null) {
//                    preparedStatement.close();
//                    preparedStatement = null;
//                }
//                
//                if(connection != null) {
//                    connection.close();
//                    connection = null;
//                }
//            } catch (SQLException ex) {
//                throw new ServiceLocatorException(ex);
//            }
//        }
//        return createdBy+"#"+practice;
//    }
    public String getDetailsByRequirementId(String id) throws ServiceLocatorException {
        String createdBy = "";
        String practice = "";
        String AssignedTo = "";
        String SecondaryRecruiter = "_";
        String AssignedBy = "";
        String queryString = "";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT CreatedBy,Practice,AssignedBy,AssignedTo,SecondaryRecruiter from tblRecRequirement where Id='" + id + "'";
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                createdBy = resultSet.getString("CreatedBy");
                practice = resultSet.getString("Practice");
                if (resultSet.getString("AssignedBy") != null) {
                    AssignedBy = resultSet.getString("AssignedBy");
                }
                if (resultSet.getString("AssignedTo") != null) {
                    AssignedTo = resultSet.getString("AssignedTo");
                }
                //System.out.println("resultSet.getString(\"SecondaryRecruiter\")--"+resultSet.getString("SecondaryRecruiter")+"ASD");
                if (resultSet.getString("SecondaryRecruiter") != null && !resultSet.getString("SecondaryRecruiter").equals("")) {
                    SecondaryRecruiter = resultSet.getString("SecondaryRecruiter");
                }
            }
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
        return createdBy + "#" + practice + "#" + AssignedBy + "#" + AssignedTo + "#" + SecondaryRecruiter;
    }

    public Map getEmployeeNamesByID(String workingCountry) throws ServiceLocatorException {


        Map employeeMap = new LinkedHashMap();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT Id,CONCAT(TRIM(FName),'.',TRIM(LName)) AS EmployeeName,isManager,isTeamLead FROM"
                    + " tblEmployee WHERE CurStatus='Active' AND DeletedFlag != 1 AND WorkingCountry LIKE '" + workingCountry + "'  ORDER BY EmployeeName";
            preparedStatement = connection.prepareStatement(queryString);

            //System.out.println("query-"+queryString);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employeeMap.put(resultSet.getString("Id"), resultSet.getString("EmployeeName"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return employeeMap; // returning the object.
    }

//New Methods For getting Primary TeamMember 
    public String getPrimaryTeamMember(int accountId) throws ServiceLocatorException {

        String primaryTeamMemberUserId = "";

        ResultSet resultSet = null;
        Connection connection = null;
        Statement statement = null;

        connection = ConnectionProvider.getInstance().getConnection();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT TeamMemberId FROM tblCrmAccountTeam WHERE AccountId=" + accountId + " AND PrimaryFlag=1");
            while (resultSet.next()) {
                primaryTeamMemberUserId = resultSet.getString("TeamMemberId");
            }
        } catch (SQLException ex) {
            throw new ServiceLocatorException(ex);
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }

        return primaryTeamMemberUserId;
    }

    /**
     * To get Employee First Name Based on login id.
     */
    public String getFname(String loginId) throws ServiceLocatorException {
        String FirstName = "";
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "select FName from tblEmployee where loginId = '" + loginId + "'";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                FirstName = resultSet.getString("FName");
            }


        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }

        return FirstName;
    }

    public int getMaxCrmActivityId() throws ServiceLocatorException {
        int MaxId = 0;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "select Max(Id) as MaxId from tblCrmActivity";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                MaxId = resultSet.getInt("MaxId");
            }


        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }

        return MaxId;

    }

    public int getMaxRecActivityId() throws ServiceLocatorException {
        int MaxId = 0;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "select Max(Id) as MaxId from tblRecActivity";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                MaxId = resultSet.getInt("MaxId");
            }


        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }

        return MaxId;
    }

//TO  getThe Consultant Name Based on the Task Id
    public String getConsultantNameByActivityId(int ActivityId) throws ServiceLocatorException {

        String ConsultantName = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT CONCAT(Fname,MName,LName) AS ConsultantName FROM tblRecConsultant WHERE Id = (SELECT ConsultantId FROM tblRecActivity WHERE Id =" + ActivityId + ")";
        // System.out.print("ConsultantNameByActivity-->"+queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                ConsultantName = resultSet.getString("ConsultantName");
            }


        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return ConsultantName;
    }

//method for month start date
    public String monthStratDate() throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String monStartDate = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT CONCAT(YEAR(CURRENT_DATE),'-',MONTH(CURRENT_DATE),'-','01') AS startdate";
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                monStartDate = resultSet.getString("startdate");
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }

        return monStartDate;
    }
//method for month end date

    public String monthEndDate() throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String monEndDate = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT (LAST_DAY(CURRENT_DATE)) AS enddate";
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                monEndDate = resultSet.getString("startdate");
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }

        return monEndDate;
    }

    /**
     * To get the green sheet created by name using green sheet id
     */
    public String getGreensheetCreatedByName(int id) throws ServiceLocatorException {
        String createdbyName = "";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT CreatedBy,ModifiedBy,Id FROM tblGreensheets WHERE Id = " + id;
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                createdbyName = resultSet.getString("CreatedBy");

            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return createdbyName;
    }

    public int getMaxRecAttachMentId() throws ServiceLocatorException {
        int MaxId = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT MAX(Id) AS Id FROM tblRecAttachments";
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                MaxId = resultSet.getInt("Id");

            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return MaxId;
    }
    //New method for getting requirement assigned date 

    public Timestamp getAssignedDate(String id) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";
        Timestamp assignedDate = null;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT AssignedDate FROM tblRecRequirement WHERE Id = " + Integer.parseInt(id);
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                assignedDate = resultSet.getTimestamp("AssignedDate");
                //System.out.println("Assigned date in DSDP-->"+assignedDate);
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }

        return assignedDate;
    }

//new method for getting states list based on account team members
    public List getStatesListForTeam(String loginId, HttpServletRequest httpServletRequest) throws ServiceLocatorException {

        List StatesListForTeam = new ArrayList();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;
        setMyTeamMembers((Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
        //Map m =(Map)(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
        //System.out.println("map values-->"+m);
        String myTeamMembers = getKeys(getMyTeamMembers(), ",");


        //queryString = "SELECT DISTINCT (tblCrmAccount.state) as state FROM  tblCrmAccount LEFT JOIN tblCrmAccountTeam ON tblCrmAccount.Id = tblCrmAccountTeam.AccountId WHERE tblCrmAccountTeam.TeammemberId  IN ('"+loginId+"',"+myTeamMembers+") " ;
        queryString = "SELECT DISTINCT tblCrmAddress.State as state FROM ((tblCrmAccount LEFT JOIN tblCrmAccountTeam ON (tblCrmAccount.Id =tblCrmAccountTeam.AccountId)) LEFT JOIN tblCrmAddress ON (tblCrmAddress.Id = tblCrmAccount.PrimaryAddressId) LEFT JOIN tblCrmAccountDetails ON ( tblCrmAccountDetails.AccountId = tblCrmAccount.Id)) WHERE tblCrmAccountTeam.TeamMemberId IN ('" + loginId + "'," + myTeamMembers + " ) AND tblCrmAddress.ObjectType LIKE 'Account' ";
        //System.out.println("Query String -->"+queryString);
        connection = ConnectionProvider.getInstance().getConnection();

        try {
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StatesListForTeam.add(resultSet.getString("state"));
            }

        } catch (SQLException sqlex) {
            throw new ServiceLocatorException(sqlex);
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
        //System.out.println("States list--->"+StatesListForTeam);
        return StatesListForTeam;

    }

//new method for getting tertory list based on account team members on 09292012
    public List getTeritoryList(String loginId, HttpServletRequest httpServletRequest) throws ServiceLocatorException {

        List TeritoryList = new ArrayList();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;
        //queryString = "SELECT DISTINCT (tblCrmAccount.state) as state FROM  tblCrmAccount LEFT JOIN tblCrmAccountTeam ON tblCrmAccount.Id = tblCrmAccountTeam.AccountId WHERE tblCrmAccountTeam.TeammemberId  IN ('"+loginId+"',"+myTeamMembers+") " ;
        queryString = "SELECT DISTINCT `tblCrmAccount`.`Teritory` as teritory FROM ((`tblCrmAccount` LEFT JOIN `tblCrmAccountTeam` ON (`tblCrmAccount`.`Id` =`tblCrmAccountTeam`.`AccountId`)) LEFT JOIN `tblCrmAddress` ON (`tblCrmAddress`.`Id` = `tblCrmAccount`.`PrimaryAddressId`) LEFT JOIN `tblCrmAccountDetails`ON ( tblCrmAccountDetails.AccountId = tblCrmAccount.Id)) WHERE tblCrmAccountTeam.TeamMemberId = '" + loginId + "' AND tblCrmAddress.Country LIKE '%' AND tblCrmAddress.ObjectType LIKE 'Account' ORDER BY `tblCrmAccount`.`Name` LIMIT 150 ";
        //System.out.println("Query String -->"+queryString);
        connection = ConnectionProvider.getInstance().getConnection();

        try {
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TeritoryList.add(resultSet.getString("teritory"));
            }

        } catch (SQLException sqlex) {
            throw new ServiceLocatorException(sqlex);
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
        // System.out.println("States list--->"+TeritoryList);
        return TeritoryList;

    }

    public List getStatesListByLoginId(String loginId, HttpServletRequest httpServletRequest) throws ServiceLocatorException {

        List StatesListForTeam = new ArrayList();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;
        setMyTeamMembers((Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
        //Map m =(Map)(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
        //System.out.println("map values-->"+m);
        String myTeamMembers = getKeys(getMyTeamMembers(), ",");


        //queryString = "SELECT DISTINCT (tblCrmAccount.state) as state FROM  tblCrmAccount LEFT JOIN tblCrmAccountTeam ON tblCrmAccount.Id = tblCrmAccountTeam.AccountId WHERE tblCrmAccountTeam.TeammemberId  IN ('"+loginId+"',"+myTeamMembers+") " ;
        queryString = "SELECT DISTINCT tblCrmAddress.State as state FROM ((tblCrmAccount LEFT JOIN tblCrmAccountTeam ON (tblCrmAccount.Id =tblCrmAccountTeam.AccountId)) LEFT JOIN tblCrmAddress ON (tblCrmAddress.Id = tblCrmAccount.PrimaryAddressId) LEFT JOIN tblCrmAccountDetails ON ( tblCrmAccountDetails.AccountId = tblCrmAccount.Id)) WHERE tblCrmAccountTeam.TeamMemberId IN ('" + loginId + "') AND tblCrmAddress.ObjectType LIKE 'Account' ";
        //System.out.println("Query String -->"+queryString);
        connection = ConnectionProvider.getInstance().getConnection();

        try {
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StatesListForTeam.add(resultSet.getString("state"));
            }

        } catch (SQLException sqlex) {
            throw new ServiceLocatorException(sqlex);
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
        //System.out.println("States list--->"+StatesListForTeam);
        return StatesListForTeam;

    }

    private String getKeys(Map map, String delimiter) {
        Iterator tempIterator = map.entrySet().iterator();
        String keys = "";
        String key = "";
        int cnt = 0;

        while (tempIterator.hasNext()) {
            Map.Entry entry = (Map.Entry) tempIterator.next();
            key = entry.getKey().toString();
            entry = null;

            //  Add the Delimiter to the Keys Field for the Second Key onwards
            if (cnt > 0) {
                keys = keys + delimiter;
            }

            keys = keys + "'" + key + "'";
            //System.out.println("keyssss------"+ keys);
            cnt++;
        }
        tempIterator = null;
        return (keys);
    }

    /**
     * @ returns Map for Team getMyTeamForLeaveSection.
     * @ throws New method for getting team list in employee leaves applied
     * section
     */
    public Map getMyTeamForLeaveSection(String reportsTo, String departmentId) throws ServiceLocatorException {

        //System.out.println("reportsTo"+reportsTo);
        // System.out.println("departmentId"+departmentId);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;
        connection = ConnectionProvider.getInstance().getConnection();
        Map getMyTeamForLeaveSection = new TreeMap();

        try {

            queryString = "SELECT LoginId,FName,MName,LName,IsManager,IsTeamLead FROM tblEmployee WHERE ReportsTo= ? AND CurStatus='Active' AND DeletedFlag !=1 ORDER BY FName";
            preparedStatement = connection.prepareStatement(queryString);
            getMyTeamForLeaveSection = getMyTeamMembersUpTo(reportsTo, preparedStatement);

        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }


        // System.out.println("myTeamMembers"+myTeamMembers);
        // System.out.println("I am Out of Data Source Provider");
        return getMyTeamForLeaveSection; // returning the object.
    }

    //new on 10162012 for assign Accounts for BDM
    /**
     * Author :Nagireddy Seerapu DESC : Return States List Based on his
     * Territory.
     */
    public String getStatesByTeritory(String teritory) throws ServiceLocatorException {
        //List statesList = new ArrayList();
        String statesList = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT State1,State2,State3,State4,State5 FROM tblLKTerritory WHERE Description='" + teritory + "'";
            statement = connection.createStatement();

            resultSet = statement.executeQuery(queryString);

            if (resultSet.next()) {
                //while(resultSet.next()) {
                statesList = resultSet.getString("State1") + "," + resultSet.getString("State2") + "," + resultSet.getString("State3") + "," + resultSet.getString("State4") + "," + resultSet.getString("State5");

            } else {
                //statesList.add("No");
                statesList = "no";
            }

        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }

        return statesList; // returning the object.

    }

    //new on 10162012 to restrict leave approval in the hierachy only
    public String getReportsToByLeaveId(int leaveId) throws ServiceLocatorException {
        Connection connection = ConnectionProvider.getInstance().getConnection();
        Statement statement = null;
        String queryString = "";
        ResultSet resultSet = null;
        String reportsTo = "";
        try {
            statement = connection.createStatement();
            queryString = "select reportsTo from tblEmpLeaves where Id = ";
            resultSet = statement.executeQuery(queryString);

        } catch (SQLException ex) {
            ex.printStackTrace();
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
            } catch (SQLException sql) {
                throw new ServiceLocatorException(sql);
            }
        }

        return reportsTo;

    }

    /*
     * Method for getting reports to list based on leave id.
     * Date : 10/16/2013
     * Author : Santosh Kola
     */
    public String getReportsToChainByleaveId(int leaveId) throws ServiceLocatorException {
        String userId = "";
        int empId;
        String reportsToChain = "";
        Statement statement = null;
        String queryString = "";
        ResultSet resultSet = null;
        String oldReportsTo = "";
        Connection connection = ConnectionProvider.getInstance().getConnection();
        //  queryString = "SELECT ReportsTo FROM tblEmpLeaves WHERE Id="+leaveId;
        queryString = "SELECT tblEmployee.ReportsTo AS ReportsTo,tblEmpLeaves.reportsTo AS OldReportsTo FROM tblEmployee INNER JOIN tblEmpLeaves ON (tblEmployee.Id = tblEmpLeaves.EmpId) WHERE tblEmpLeaves.Id=" + leaveId;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            resultSet.next();
            userId = resultSet.getString("ReportsTo");
            oldReportsTo = resultSet.getString("OldReportsTo");
            reportsToChain = getReportsToChain(userId);
            if (!"".equals(reportsToChain)) {
                reportsToChain = reportsToChain + ";" + userId + ";" + oldReportsTo;
            } else {
                reportsToChain = userId + ";" + oldReportsTo;
            }
            //System.out.println("reports to chain in dataprovider-->"+reportsToChain);
        } catch (SQLException ex) {
            throw new ServiceLocatorException(ex);
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
            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }

        return reportsToChain;
    }

    public String getReportsToChain(String userId) throws ServiceLocatorException {

        //System.out.println("userId--->"+userId);
        String reportsId = "";
        String levelReport = "";
        Connection connection = ConnectionProvider.getInstance().getConnection();
        Statement statement = null;
        String queryString = "";
        ResultSet resultSet = null;

        StringBuffer levelReportToId = new StringBuffer();

        try {

            for (int i = 1; i < 4; i++) {
                statement = connection.createStatement();
                queryString = "select ReportsTo from tblEmployee where curStatus='Active' and loginId = '" + userId + "'";
                resultSet = statement.executeQuery(queryString);

                while (resultSet.next()) {
                    reportsId = resultSet.getString("Reportsto");
                }
                //levelReportToId =  levelReportToId +  reportsId+";";
                userId = reportsId;

                if ((userId == "") || (userId.equalsIgnoreCase(null)) || (userId.equalsIgnoreCase(""))) {
                    i = 5;
                    levelReportToId.append(reportsId);
                } else {
                    levelReportToId.append(reportsId);
                    levelReportToId.append(";");
                }
                //System.out.println("reportsId----->"+reportsId);
                // resultSet  = null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
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
            } catch (SQLException sql) {
                throw new ServiceLocatorException(sql);
            }
        }

        levelReport = levelReportToId.toString();

        if (!"".equals(levelReport)) {

            levelReport = levelReport.substring(0, levelReport.length() - 1);
        }
        //System.out.println("levelReport---111------->"+levelReport);
        return levelReport;
    }

    /**
     * Return List territories in the given practice id.. New Practice Names
     * List list
     */
    public List getSubPracticeByPracticeId(int practiceId) throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        List lKSubPracticeListByPractice = new ArrayList();//Description
        queryString = "SELECT Description FROM tblLKSubPractice where practiceId=" + practiceId;

        connection = ConnectionProvider.getInstance().getConnection();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                lKSubPracticeListByPractice.add(resultSet.getString("Description"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return lKSubPracticeListByPractice; // returning the object.
    } //closing the method.

    /**
     * Desc: Return the List of reporting persons Login Id.
     */
    /*public String getReportsToChainByleaveId(int leaveId) throws ServiceLocatorException{
     String userId ="";
     int empId;
     String reportsToChain="";
     Statement statement=null;
     String queryString="";
     ResultSet resultSet=null;
     Connection connection = ConnectionProvider.getInstance().getConnection();
     queryString = "SELECT ReportsTo FROM tblEmpLeaves WHERE Id="+leaveId;
     try {   
     statement = connection.createStatement();
     resultSet = statement.executeQuery(queryString);
     resultSet.next();
     userId = resultSet.getString("ReportsTo");
     reportsToChain = getReportsTo(userId);
     reportsToChain = reportsToChain +";"+userId;
     //System.out.println("reports to chain in dataprovider-->"+reportsToChain);
     }catch (SQLException ex) {
     throw new ServiceLocatorException(ex);
     }finally {
     try{
     if(resultSet!=null){
     resultSet.close();
     resultSet = null;
     }
     if(statement!=null){
     statement.close();
     statement = null;
     }
     if(connection!=null){
     connection.close();
     connection = null;
     }
     }catch(SQLException sqle){
     throw new ServiceLocatorException(sqle);
     }
     }
     return reportsToChain;
     }
     */
    /*  Desc : Returns loginIds of the employees who are repoting to given loginId -->BEGIN
     *  Date : Nov 01 2012
     *
     */
    public String getEmpNameUsingReportsTo(String userId) throws ServiceLocatorException {
        StringBuffer stringBuffer = new StringBuffer();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;
        try {
            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT CONCAT(TRIM(tblEmployee.FName),'.',TRIM(tblEmployee.LName)) AS NAME FROM tblEmployee where tblEmployee.ReportsTo ='" + userId + "'";
            preparedStatement = connection.prepareStatement(queryString);

            //preparedStatement.setString(1,userId);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                stringBuffer.append("'");
                stringBuffer.append(resultSet.getString("NAME"));
                stringBuffer.append("'");
                stringBuffer.append(",");
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
        return stringBuffer.substring(0, stringBuffer.length() - 1).toString();
    }

    /**
     * Desc : To get the employee Name by Leave Id. Method Name :
     * getEmpNameByLeaveId()
     *
     */
    public String getEmpNameByLeaveId(int leaveId) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;
        String empName = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT EmpId FROM tblEmpLeaves WHERE Id=" + leaveId;
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            empName = getFname_Lname1(resultSet.getInt("EmpId"));


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return empName;
    }

    /**
     *
     * Desc : for getting presales list for the employees having pre sales role
     *
     */
    public List getTechLead() throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        List preSalesList = new ArrayList();//Description
        queryString = "SELECT CONCAT(fName,' ',mName,'.',lName) AS Name FROM (tblEmployee JOIN tblEmpRoles ON(tblEmployee.Id = tblEmpRoles.EmpId)) WHERE tblEmpRoles.RoleId = 10 AND tblEmployee.CurStatus = 'Active'";

        connection = ConnectionProvider.getInstance().getConnection();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                preSalesList.add(resultSet.getString("Name"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return preSalesList; // returning the object.
    }

    /**
     * Author:: tummapala DESC: Map sorting based Comparator; Date : 14 nov 2012
     */
    public < K, V extends Comparable< ? super V>> Map< K, V> sortMapByValues(final Map< K, V> mapToSort) {
        List< Map.Entry< K, V>> entries =
                new ArrayList< Map.Entry< K, V>>(mapToSort.size());

        entries.addAll(mapToSort.entrySet());

        Collections.sort(entries,
                new Comparator< Map.Entry< K, V>>() {
                    public int compare(
                            final Map.Entry< K, V> entry1,
                            final Map.Entry< K, V> entry2) {
                        return entry1.getValue().compareTo(entry2.getValue());
                    }
                });

        Map< K, V> sortedMap = new LinkedHashMap< K, V>();

        for (Map.Entry< K, V> entry : entries) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    /*
     *Geting Project Names by AccountId
     *Date : 06/11/2013
     *Author : Santosh
     */
    public Map getProjectsByAccountId(String accountId) throws ServiceLocatorException {

        Map projectMap = new TreeMap();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            if (accountId != null) {
                if (!"".equals(accountId)) {
                    queryString = "Select  Id,ProjectName,ProjectStartDate,ProjectEndDate,TotalResources,CustomerId,ProjectManagerUID  FROM tblProjects";
                    queryString = queryString + " WHERE CustomerId=" + accountId + " ORDER BY ProjectName DESC";
                    preparedStatement = connection.prepareStatement(queryString);
                    //preparedStatement = connection.prepareStatement(queryString);

                    //preparedStatement.setString(1,department);

                    //resultSet = preparedStatement.executeQuery();
                    resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        projectMap.put(resultSet.getString("Id"),
                                resultSet.getString("ProjectName"));
                    }
                } else {
                    projectMap.put("", "");
                }
            } else {
                projectMap.put("", "");
            }

        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return projectMap; // returning the object.
    } //closing the method.

    public String getRegionNameFromAccountId(int accountId) throws ServiceLocatorException {
        String region = "";
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT Region FROM tblCrmAccount WHERE Id=" + accountId;
        //System.out.print("QS:----->"+queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                region = resultSet.getString("Region");
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return region;
    }

    //new method for getting issuedetails to send issue remainder on 06122013 by vkandregula@miraclesoft.com
    public List getIssueDetails(String issueId) throws ServiceLocatorException {
        List issueDetails = new ArrayList();
        ResultSet resultSet = null;
        Connection connection = null;
        Statement statement = null;
        String queryString = null;
        connection = ConnectionProvider.getInstance().getConnection();

        try {
            statement = connection.createStatement();
            //queryString = "Select Id,ProjectName FROM tblProjects WHERE Id in (select ProjectId from vwProjectTeamEmployees where EmpId="+empId+" ORDER BY ProjectName DESC)";
            queryString = "Select CreatedBy,TaskId,IssueTitle,DateAssigned,DateClosed,AssignedTo,SecAssignTO,PercentageComplted FROM tblEmpIssues WHERE Id='" + issueId + "'";
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                issueDetails.add(resultSet.getString("CreatedBy"));
                issueDetails.add(resultSet.getInt("TaskId"));
                issueDetails.add(resultSet.getString("IssueTitle"));
                issueDetails.add(resultSet.getTimestamp("DateAssigned"));
                issueDetails.add(resultSet.getTimestamp("DateClosed"));

                issueDetails.add(resultSet.getString("AssignedTo"));
                issueDetails.add(resultSet.getString("SecAssignTO"));
                issueDetails.add(resultSet.getString("PercentageComplted"));
                //issueDetails.add(resultSet.getString("CreatedBy"));


            }
        } catch (SQLException ex) {
            throw new ServiceLocatorException(ex);
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return issueDetails;
    }

    /**
     * New method to get Team list as string
     */
    /**
     * @ returns Map for Team Members.
     * @ throws
     */
    public String getMyTeamMembersForIssue(String reportsTo, String departmentId) throws ServiceLocatorException {
        StringBuffer stringBuffer = new StringBuffer();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;
        connection = ConnectionProvider.getInstance().getConnection();
        Map myTeamMembersforIssue = new TreeMap();
        String key = null;
        String value = null;

        try {

            if (reportsTo.equalsIgnoreCase(Properties.getProperty("CEO"))) {
                queryString = "SELECT LoginId,FName,MName,LName,IsManager,IsTeamLead FROM tblEmployee WHERE ReportsTo= ? AND CurStatus='Active' AND DeletedFlag !=1 ORDER BY FName";
            } //new 
            else if (reportsTo.equalsIgnoreCase(Properties.getProperty("ExecutiveBoard"))) {
                queryString = "SELECT LoginId,FName,MName,LName,IsManager,IsTeamLead FROM tblEmployee WHERE ReportsTo= ? AND CurStatus='Active' AND DeletedFlag !=1 ORDER BY FName";
            } else {
                if ("GDC".equals(departmentId) || "SSG".equals(departmentId) || "Marketing".equals(departmentId)) {
                    queryString = "SELECT LoginId,FName,MName,LName,IsManager,IsTeamLead FROM tblEmployee WHERE  DSDP -->  and ReportsTo= ? AND CurStatus='Active' AND DeletedFlag !=1 ORDER BY FName";
                } else {
                    queryString = "SELECT LoginId,FName,MName,LName,IsManager,IsTeamLead FROM tblEmployee WHERE  DepartmentId='" + departmentId + "' and ReportsTo= ? AND CurStatus='Active' AND DeletedFlag !=1 ORDER BY FName";
                }
            }

            preparedStatement = connection.prepareStatement(queryString);
            myTeamMembersforIssue = getMyTeamMembersUpTo(reportsTo, preparedStatement);

            int teamsize = myTeamMembersforIssue.size();


            int i = 0;
            if (myTeamMembersforIssue.size() > 0) {

                Iterator tempIterator = myTeamMembersforIssue.entrySet().iterator();
                while (tempIterator.hasNext()) {
                    i++;
                    Map.Entry entry = (Map.Entry) tempIterator.next();
                    key = entry.getKey().toString();
                    value = entry.getValue().toString();

                    stringBuffer.append("'");
                    stringBuffer.append(key);
                    stringBuffer.append("'");


                    if (i <= (teamsize - 1)) {

                        stringBuffer.append(",");
                    }


                    entry = null;
                }
            }

            // }//Closing Cache Checking
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }

        // System.out.println("I am Out of Data Source Provider");
        return stringBuffer.toString(); // returning the object.
    }

    public String getProjectName(int projectId) throws ServiceLocatorException {

        String projectType = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "Select Id,ProjectName from tblProjects where Id =" + projectId;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                projectType = resultSet.getString("ProjectName");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return projectType;
    }

    public List getTaskDetails(String taskId) throws ServiceLocatorException {
        List taskDetails = new ArrayList();
        ResultSet resultSet = null;
        Connection connection = null;
        Statement statement = null;
        String queryString = null;
        connection = ConnectionProvider.getInstance().getConnection();

        try {
            statement = connection.createStatement();
            //queryString = "Select Id,ProjectName FROM tblProjects WHERE Id in (select ProjectId from vwProjectTeamEmployees where EmpId="+empId+" ORDER BY ProjectName DESC)";
            queryString = "Select CreatedBy,CustomerId,Title,Startdate,DueDate,PriAssignTO,SecAssignTo,percentageCompleted,PriAssignToType,SecAssignedToType FROM tblEmpTasks WHERE Id='" + taskId + "'";
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                taskDetails.add(resultSet.getString("CreatedBy"));
                taskDetails.add(resultSet.getInt("CustomerId"));
                taskDetails.add(resultSet.getString("Title"));
                taskDetails.add(resultSet.getTimestamp("Startdate"));
                taskDetails.add(resultSet.getTimestamp("DueDate"));

                taskDetails.add(resultSet.getString("PriAssignTO"));
                taskDetails.add(resultSet.getString("SecAssignTo"));
                taskDetails.add(resultSet.getString("percentageCompleted"));
                taskDetails.add(resultSet.getString("PriAssignToType"));
                taskDetails.add(resultSet.getString("SecAssignedToType"));

                //issueDetails.add(resultSet.getString("CreatedBy"));


            }
        } catch (SQLException ex) {
            throw new ServiceLocatorException(ex);
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return taskDetails;
    }

    /**
     * Author : Prasad Kandregula New Method for
     * getResumeSubmissionConsultantDetails Date : 07172013
     */
    public List getResumeSubmissionConsultantDetails(int consultantId) throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        List consultantDetails = new ArrayList();//Description

        queryString = "SELECT concat(FName,'.',LName) as name,Email2,CellPhoneNo FROM tblRecConsultant where Id=" + consultantId;

        connection = ConnectionProvider.getInstance().getConnection();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                consultantDetails.add(resultSet.getString("name"));
                consultantDetails.add(resultSet.getString("Email2"));
                consultantDetails.add(resultSet.getString("CellPhoneNo"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return consultantDetails; // returning the object.
    } //closing the method.

    /**
     * Author : Prasad Kandregula New Method for getTimeDifferenceInHours Date :
     * 07172013
     */
    public long getTimeDifferenceInHours(Timestamp downloadTime) throws ServiceLocatorException {
        long hoursdiff = 0;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT  (TIMESTAMPDIFF(HOUR, '" + downloadTime + "', NOW())) AS HoursDifference";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                hoursdiff = resultSet.getInt("HoursDifference");
            }


        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }

        return hoursdiff;
    }

    /**
     * *
     * New Methods for ecertification
     *
     */
    public Map getExamDomainsMap() throws ServiceLocatorException {

        Map examDomainMap = new TreeMap();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT Id,DomainName FROM tblEcertDomain WHERE status='Active' AND Id != " + Properties.getProperty("CRE.DomainId") + " ORDER BY DomainName";
            preparedStatement = connection.prepareStatement(queryString);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                examDomainMap.put(new Integer(resultSet.getInt("Id")), resultSet.getString("DomainName"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return examDomainMap; // returning the object.
    } //closing the method.

    public Map getDomainTopicsMap(int domainId) throws ServiceLocatorException {

        Map examDomainMap = new TreeMap();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT Id,TopicName FROM tblEcertTopics where DomainId = " + domainId + " AND status='Active' ORDER BY TopicName";
            preparedStatement = connection.prepareStatement(queryString);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                examDomainMap.put(new Integer(resultSet.getInt("Id")), resultSet.getString("TopicName"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return examDomainMap; // returning the object.
    } //closing the method.

    /*
     *Method to check wheather the Question Attempt or not
     *Date : 07/23/2013
     */
    public int isQuestionAttempt(int examKeyId, int questionId) throws ServiceLocatorException {
        int count = 0;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT COUNT(id) as id FROM tblEcertSummary WHERE ExamKeyId = " + examKeyId + " AND QuestionId = " + questionId;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                count = resultSet.getInt("id");
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return count;
    }

    public int getAnswer(int examKeyId, int questionId, int empId) throws ServiceLocatorException {
        int answer = 0;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT EmpAns as answer FROM tblEcertSummary WHERE ExamKeyId = " + examKeyId + " AND QuestionId = " + questionId + " AND EmpId = " + empId;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                answer = resultSet.getInt("answer");
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return answer;
    }

    /*
     *Method to get SubTopic Min Marks
     *Date : 07/23/2013
     */
    public int getExamMinMarks(String validationKey) throws ServiceLocatorException {
        int minMarks = 0;

        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT MinMarks FROM tblEcertValidatorKeys WHERE VKey = '" + validationKey + "'";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                minMarks = resultSet.getInt("MinMarks");
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }

        return minMarks;
    }

    /*New method for getting max subtopic id based exam creation
     *Author:Prasad kandregula 
     */
    public int getMaxSubTopicId() throws ServiceLocatorException {
        int maxSubTopicId = 0;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT MAX(ID)as Id  FROM tblLKSubTopics";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                maxSubTopicId = resultSet.getInt("Id");
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return maxSubTopicId;
    }

    /*Method to get Subtopic Names in the form of Map
     *Date : 08/02/2013
     *
     */
    public Map getSubTopicNamesMap(int topicId) throws ServiceLocatorException {

        Map subtopicsMap = new TreeMap();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT Id,SubTopicName FROM tblEcertSubTopics WHERE Status = 'Active' AND TopicId = ? ORDER BY SubTopicName";
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, topicId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                subtopicsMap.put(new Integer(resultSet.getInt("Id")),
                        resultSet.getString("SubTopicName"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return subtopicsMap; // returning the object.
    } //closing the method.

    public boolean checkSubTopicName(String subTopicName, int topicId) throws ServiceLocatorException {
        Statement statement = null;
        ResultSet resultSet = null;
        boolean flag = false;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT * FROM tblEcertSubTopics WHERE SubTopicName = '" + subTopicName + "' AND topicId = " + topicId;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            if (resultSet.next()) {
                //  count = resultSet.getInt("id");
                flag = true;
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return flag;
    }

    /**
     * To get Consultant name based on Consultant Id
     */
    public String getCreConsultantNameById(int creConsultantId) throws ServiceLocatorException {
        Statement statement = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        ResultSet resultSet = null;
        String queryString = "SELECT CONCAT(FName, '.', LName) AS ConsultantName FROM tblCreConsultentDetails WHERE Id = " + creConsultantId;
        String consultantName = "";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            if (resultSet.next()) {
                consultantName = resultSet.getString("ConsultantName");
            }

        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return consultantName;
    }

    /**
     * Author : Ajay Tummapala To delet from temp table inserted by procedure.
     *
     */
    /* public void deleteQuestionsFromTempTable(int maxKeyId) throws ServiceLocatorException {
     Statement statement = null;
     Connection connection = ConnectionProvider.getInstance().getConnection();
     String queryString = "DELETE FROM tblCretemp_EQ WHERE ExmKeyId="+maxKeyId;
    
     try{
     statement =connection.createStatement();
     statement.execute(queryString);
           
     }catch (Exception ex) {
     throw new ServiceLocatorException(ex);
     }finally {     
     try{
     if(statement != null){
     statement.close();
     statement = null;
     }
     if(connection != null){
     connection.close();
     connection = null;
     }
                
     }catch(SQLException sqle){
     throw new ServiceLocatorException(sqle);
     }
     }   
     }
     */
    /**
     *
     * Insert and update methods for employee answers in table ::
     * tblEcertSummary
     *
     */
    /* public void insertAnswer(int questionNo,int selectedAns,int empId,int examKeyId,int subTopicId) throws ServiceLocatorException {
     Connection connection = null;
     PreparedStatement preparedStatement = null;
     int i=0;
     try {
     connection = ConnectionProvider.getInstance().getConnection();
              
     preparedStatement = connection.prepareStatement("INSERT INTO tblEcertSummary (EmpId,ExamKeyId,QuestionId,EmpAns,DateSubmitted,SubtopicId) VALUES(?,?,?,?,?,? )");
     preparedStatement.setInt(1,empId);
     preparedStatement.setInt(2,examKeyId);
     preparedStatement.setInt(3,questionNo);
     preparedStatement.setInt(4,selectedAns);
     preparedStatement.setTimestamp(5,DateUtility.getInstance().getCurrentMySqlDateTime());
     preparedStatement.setInt(6,subTopicId);
     i = preparedStatement.executeUpdate();
     }catch (Exception e){
     System.err.println("Exception is-->"+e.getMessage());
     }finally {
     try {
     if(preparedStatement!=null){
     preparedStatement.close();
     preparedStatement = null;
     }if(connection!=null){
     connection.close();
     connection = null;
     } 
     }catch(Exception sqle){
     System.err.println("SQL Exception is-->"+sqle.getMessage());
     }
     }
          
     }*/
    /* public void updateAnswer(int questionNo,int selectedAns,int empId,int examKeyId) throws ServiceLocatorException {
         
     Connection connection = null;
     PreparedStatement preparedStatement = null;
     int i=0;
     try {
     connection = ConnectionProvider.getInstance().getConnection();
     preparedStatement = connection.prepareStatement("UPDATE tblEcertSummary SET EmpAns=?  WHERE EmpId = ? AND ExamKeyId = ? AND QuestionId = ?");
     preparedStatement.setInt(1,selectedAns);
     preparedStatement.setInt(2,empId);
     preparedStatement.setInt(3,examKeyId);
     preparedStatement.setInt(4,questionNo);
              
     i = preparedStatement.executeUpdate();
     }catch (Exception e){
     System.err.println("Exception is-->"+e.getMessage());
     }finally {
     try {
     if(preparedStatement!=null){
     preparedStatement.close();
     preparedStatement = null;
     }if(connection!=null){
     connection.close();
     connection = null;
     } 
     }catch(Exception sqle){
     System.err.println("SQL Exception is-->"+sqle.getMessage());
     }
     }
          
     }*/
    /**
     * Update Consultant status based on consultant id in
     * tblCreConsultentDetails
     *
     * Stauts,modifiedby,modified date,level
     *
     */
    /*public void updateCREConsultantStatus(int creConsultantId,String status,String level,String loginId) throws ServiceLocatorException {
     Connection connection = null;
     PreparedStatement preparedStatement = null;
     int i=0;
     try {
     connection = ConnectionProvider.getInstance().getConnection();
     preparedStatement = connection.prepareStatement("UPDATE tblCreConsultentDetails SET Status=?,Level=?,ModifiedBy=?,ModifiedDate=?  WHERE Id = ?");
     preparedStatement.setString(1,status);
     preparedStatement.setString(2,level);
     preparedStatement.setString(3,loginId);
     preparedStatement.setTimestamp(4,DateUtility.getInstance().getCurrentMySqlDateTime());
     preparedStatement.setInt(5,creConsultantId);
              
     i = preparedStatement.executeUpdate();
     }catch (Exception e){
     System.err.println("Exception is-->"+e.getMessage());
     }finally {
     try {
     if(preparedStatement!=null){
     preparedStatement.close();
     preparedStatement = null;
     }if(connection!=null){
     connection.close();
     connection = null;
     } 
     }catch(Exception sqle){
     System.err.println("SQL Exception is-->"+sqle.getMessage());
     }
     }
          
     }*/
    /**
     * To Insert subtopic for consultant
     *
     */
    /*   public void insertCREConsultantSubTopics(int ConsultantId,String SubtopicList) throws ServiceLocatorException {
     Connection connection = null;
     PreparedStatement preparedStatement = null;
     int i=0;
     try {
     connection = ConnectionProvider.getInstance().getConnection();
     preparedStatement = connection.prepareStatement("INSERT INTO tblCreConsExamTopics(CreId,SubtopicId,Status) VALUES(?,?,?)");
     preparedStatement.setInt(1,ConsultantId);
     preparedStatement.setString(2,SubtopicList);
     preparedStatement.setString(3,"Active");
     i = preparedStatement.executeUpdate();
     }catch (Exception e){
     System.err.println("Exception is-->"+e.getMessage());
     }finally {
     try {
     if(preparedStatement!=null){
     preparedStatement.close();
     preparedStatement = null;
     }if(connection!=null){
     connection.close();
     connection = null;
     } 
     }catch(Exception sqle){
     System.err.println("SQL Exception is-->"+sqle.getMessage());
     }
     }
          
     }*/
    /*   public void inActiveCreSuTopicsListByConsultantId(int Id) throws ServiceLocatorException {
     PreparedStatement preparedStatement = null;
     Connection connection = ConnectionProvider.getInstance().getConnection();
     ResultSet resultSet = null;
     String subtopicslist = null;
     int i=0;
     try{
     preparedStatement = connection.prepareStatement("update tblCreConsExamTopics set Status='InActive' WHERE CreId = "+Id);
     i = preparedStatement.executeUpdate();
           
           
     }catch (Exception ex) {
     throw new ServiceLocatorException(ex);
     }finally {     
     try{
     if(resultSet != null){
     resultSet.close();
     resultSet = null;
     }
     if(preparedStatement != null){
     preparedStatement.close();
     preparedStatement = null;
     }
     if(connection != null){
     connection.close();
     connection = null;
     }
                
     }catch(SQLException sqle){
     throw new ServiceLocatorException(sqle);
     }
     }
     }*/
    /*
     *Get Task Name by Task Id
     */
    public String getTaskNameById(int taskId) throws ServiceLocatorException {
        Statement statement = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        ResultSet resultSet = null;
        String queryString = "SELECT Title FROM tblEmpTasks WHERE Id = " + taskId;
        String taskName = "";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            if (resultSet.next()) {
                taskName = resultSet.getString("Title");
            }

        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return taskName;
    }

    public Map getEmployeeNamesByRecruitingRole() throws ServiceLocatorException {

        Map employeeMap = new TreeMap();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            // queryString = "SELECT LoginId,FName,LName,MName FROM tblEmployee WHERE curStatus='Active' and DepartmentId LIKE '"+department+"%' AND CurStatus='Active' ORDER BY FName";
            queryString = "SELECT tblEmployee.LoginId,tblEmployee.FName,tblEmployee.LName,tblEmployee.MName "
                    + " FROM tblEmployee LEFT OUTER JOIN tblEmpRoles ON(tblEmployee.Id = tblEmpRoles.EmpId) "
                    + " WHERE tblEmpRoles.RoleId = 6  AND CurStatus='Active' ORDER BY FName";
            preparedStatement = connection.prepareStatement(queryString);
            //preparedStatement = connection.prepareStatement(queryString);

            //preparedStatement.setString(1,department);

            //resultSet = preparedStatement.executeQuery();
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employeeMap.put(resultSet.getString("LoginId"),
                        resultSet.getString("FName") + " " + resultSet.getString("MName")
                        + "." + resultSet.getString("LName"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return employeeMap; // returning the object.
    }

    /*
     public Map getEmployeeNamesByRecruitingRole(HttpServletRequest httpServletRequest)throws ServiceLocatorException{
        
     Map employeeMap = new TreeMap();//key-Description
     PreparedStatement preparedStatement = null;
     Connection connection = null;
     ResultSet resultSet = null;
     String queryString = null;
     // new For consultantSerch changes
     int isUserManager;
     int isUserTeamLead;
        
     try{
     connection = ConnectionProvider.getInstance().getConnection();
     // userRoleName= httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
     String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
     isUserManager = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString());
     isUserTeamLead = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_TEAM_LEAD).toString());
            
     if(isUserManager == 1 || isUserTeamLead==1) 
     {  
     // queryString = "SELECT LoginId,FName,LName,MName FROM tblEmployee WHERE curStatus='Active' and DepartmentId LIKE '"+department+"%' AND CurStatus='Active' ORDER BY FName";
     queryString = "SELECT tblEmployee.LoginId,tblEmployee.FName,tblEmployee.LName,tblEmployee.MName "
     + " FROM tblEmployee LEFT OUTER JOIN tblEmpRoles ON(tblEmployee.Id = tblEmpRoles.EmpId) "
     + " WHERE tblEmpRoles.RoleId = 6  AND CurStatus='Active' ORDER BY FName";
     }
     else
     {
     // queryString = "SELECT LoginId,FName,LName,MName FROM tblEmployee WHERE curStatus='Active' and DepartmentId LIKE '"+department+"%' AND CurStatus='Active' ORDER BY FName";
     queryString = "SELECT tblEmployee.LoginId,tblEmployee.FName,tblEmployee.LName,tblEmployee.MName "
     + " FROM tblEmployee where LoginId='"+loginId+"'";            
     }
     preparedStatement = connection.prepareStatement(queryString);
     //preparedStatement = connection.prepareStatement(queryString);
            
     //preparedStatement.setString(1,department);
            
     //resultSet = preparedStatement.executeQuery();
     resultSet = preparedStatement.executeQuery();
     while(resultSet.next()) {
     employeeMap.put(resultSet.getString("LoginId"),
     resultSet.getString("FName")+" "+resultSet.getString("MName")
     +"."+resultSet.getString("LName"));
     }
            
            
     }catch (SQLException sql){
     throw new ServiceLocatorException(sql);
     }finally{
     try {
     // resultSet Object Checking if it's null then close and set null
     if(resultSet != null) {
     resultSet.close();
     resultSet = null;
     }
                
     if(preparedStatement != null) {
     preparedStatement.close();
     preparedStatement = null;
     }
                
     if(connection != null) {
     connection.close();
     connection = null;
     }
     } catch (SQLException ex) {
     throw new ServiceLocatorException(ex);
     }
     }
        
     return employeeMap; // returning the object.
     }
     */
    /*
     *Get Topic Name by Topic Id
     */
    public String getTopicNameById(int topicId) throws ServiceLocatorException {
        Statement statement = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        ResultSet resultSet = null;
        String queryString = "SELECT DomainName,TopicName,tblEcertTopics.ID AS TopicId FROM tblEcertDomain LEFT OUTER JOIN tblEcertTopics ON(tblEcertDomain.Id= tblEcertTopics.DomainId) WHERE TopicName IS NOT NULL AND tblEcertTopics.ID = " + topicId;
        String topicName = "";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            if (resultSet.next()) {
                topicName = resultSet.getString("DomainName") + "@" + resultSet.getString("TopicName");
            }

        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return topicName;
    }
    /*
     * 
     * Getting Author list based on Topic Id
     */

    public List getAuthorsByTopicId(int topicId) throws ServiceLocatorException {

        List authorList = new ArrayList();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;

        queryString = "SELECT AuthorId FROM tblEcertTopicAuthors WHERE TopicId= " + topicId;
        connection = ConnectionProvider.getInstance().getConnection();

        try {
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                authorList.add(resultSet.getString("AuthorId"));
            }

        } catch (SQLException sqlex) {
            throw new ServiceLocatorException(sqlex);
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
        return authorList;
    }//closing the method.

    /*  
     public List getSubTopicNamesByCreId(int creId) throws ServiceLocatorException{
        
     List subTopicList = new ArrayList();
        
     Connection connection = null;
     PreparedStatement preparedStatement = null;
     ResultSet resultSet = null;
     String queryString = null;
        
     queryString = " SELECT SubTopicName FROM tblCreConsExamTopics LEFT OUTER JOIN tblEcertSubTopics "
     + "ON (tblCreConsExamTopics.SubtopicId = tblEcertSubTopics.ID) "
     + "where tblCreConsExamTopics.Status = 'Active' AND CreId = "+ creId ;
     
     
     connection = ConnectionProvider.getInstance().getConnection();
        
     try {
     preparedStatement = connection.prepareStatement(queryString);
     resultSet = preparedStatement.executeQuery();
     while(resultSet.next()){
     subTopicList.add(resultSet.getString("SubTopicName"));
     }
            
     } catch (SQLException sqlex) {
     throw new ServiceLocatorException(sqlex);
     }finally {
            
     try {
     // resultSet Object Checking if it's null then close and set null
     if(resultSet != null) {
     resultSet.close();
     resultSet = null;
     }
                
     if(preparedStatement != null) {
     preparedStatement.close();
     preparedStatement = null;
     }
                
     if(connection != null) {
     connection.close();
     connection = null;
     }
     } catch (SQLException ex) {
     throw new ServiceLocatorException(ex);
     }
            
     }
     return subTopicList;
     }//closing the method.
     */
    public String getForwarderByName(int id) throws ServiceLocatorException {

        String name = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;

        queryString = "SELECT ForwardedBy FROM tblRecConsultantActivity WHERE Id= " + id;
        connection = ConnectionProvider.getInstance().getConnection();

        try {
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                name = resultSet.getString("ForwardedBy");
            }

        } catch (SQLException sqlex) {
            throw new ServiceLocatorException(sqlex);
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
        return name;
    }

    public String getConsultantLinkStatus(int id) throws ServiceLocatorException {

        String status = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;

        queryString = "SELECT LinkStatus FROM tblRecConsultantActivity WHERE Id= " + id;
        connection = ConnectionProvider.getInstance().getConnection();

        try {
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                status = resultSet.getString("LinkStatus");
            }

        } catch (SQLException sqlex) {
            throw new ServiceLocatorException(sqlex);
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
        return status;
    }

    /**
     * Method : getOpsContactId DESC : To get operations contact team map
     *
     */
    public Map getOpsContactId(String country, String isAdmin) throws ServiceLocatorException {
        Map employeeMap = new TreeMap();//key-Description
        Connection connection = null;

        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        String queryString = null;


        try {
            connection = ConnectionProvider.getInstance().getConnection();
            if (isAdmin.equals("YES")) {
                queryString = "SELECT Id,CONCAT(LName,'.',FName,' ',MName) AS EmpName FROM tblEmployee WHERE IsOperationContactTeam=1";
            } else {
                queryString = "SELECT Id,CONCAT(LName,'.',FName,' ',MName) AS EmpName FROM tblEmployee WHERE IsOperationContactTeam=1 AND country LIKE '" + country + "'";
            }


            preparedStatement = connection.prepareStatement(queryString);

            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                employeeMap.put(resultSet.getString("Id"),
                        resultSet.getString("EmpName"));
            }
            employeeMap = DataUtility.getInstance().getMapSortByValue(employeeMap);
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return employeeMap;

    }
    //new team name using loginId

    public String getTeamNameByUserId(String userId) throws ServiceLocatorException {
        String teamName = "";
        ResultSet resultSet = null;
        Statement statement = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "select teamId from tblEmployee where loginId = '" + userId + "'";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                teamName = resultSet.getString("teamId");
            }


        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }



        return teamName;
    }
    /*
     * Author : Tummapala
     * Desc : to get Primarty person by account name
     */

    public String getPrimaryTeamMemberByName(String accountName) throws ServiceLocatorException {
        String TeamMemberId = null;
        ResultSet resultSet = null;
        Statement statement = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT TeamMemberId FROM tblCrmAccount LEFT OUTER JOIN tblCrmAccountTeam "
                + "ON(tblCrmAccount.Id=tblCrmAccountTeam.AccountId) "
                + "WHERE tblCrmAccount.NAME LIKE '" + accountName + "' AND tblCrmAccountTeam.PrimaryFlag=1";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                TeamMemberId = resultSet.getString("TeamMemberId");
            }


        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }

        return TeamMemberId;
    }
    // New Cre

    public Map getExamTypeMap() throws ServiceLocatorException {

        Map subtopicsMap = new TreeMap();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT Id,ExamType FROM tblCreSetExam WHERE Status = 'Active'";
            preparedStatement = connection.prepareStatement(queryString);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                subtopicsMap.put(new Integer(resultSet.getInt("Id")),
                        resultSet.getString("ExamType"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return subtopicsMap; // returning the object.
    } //closing the method.

    public String getSubTopicIdString(List subTopicId) {
        String subTopicLists = "";
        for (int i = 0; i < subTopicId.size(); i++) {
            subTopicLists = subTopicLists + (String) subTopicId.get(i) + ",";

        }
        //  System.out.println("subTopicsList----------->"+subTopicLists);

        String subTopicLists1 = subTopicLists.substring(0, subTopicLists.length() - 1);
        return subTopicLists1;
    }

    /*Get Exams List By String 
     * Date : 02/26/2014
     * Author Santosh Kumar Kola
     */
    public List getExamsListByListString(String examsListDetails) throws ServiceLocatorException {
        List examsList = new ArrayList();
        Map subtopicsMap = null;//key-Description
        CreReviewVTO creReviewVTO = null;
        String examInfo[] = examsListDetails.split("\\^");
        for (int i = 0; i < examInfo.length; i++) {
            creReviewVTO = new CreReviewVTO();
            subtopicsMap = new TreeMap();
            String examDetail[] = examInfo[i].split("\\!");
            creReviewVTO.setExamTypeId(Integer.parseInt(examDetail[0]));
            creReviewVTO.setExamTypeName(examDetail[1]);
            creReviewVTO.setMinMarks(Integer.parseInt(examDetail[2]));
            creReviewVTO.setTotDuration(Integer.parseInt(examDetail[3]));
            creReviewVTO.setTotalQuestions(Integer.parseInt(examDetail[4]));
            String subTopicInfo[] = examDetail[5].split("\\*");
            for (int j = 0; j < subTopicInfo.length; j++) {
                String subtopicDetail[] = subTopicInfo[j].split("\\@");

                subtopicsMap.put(Integer.parseInt(subtopicDetail[1]), subtopicDetail[0]);
            }
            creReviewVTO.setSubTopicsMap(subtopicsMap);
            examsList.add(creReviewVTO);
        }
        return examsList;

    }

    public String getDesignationName(int contatId) throws ServiceLocatorException {

        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String queryString = null;
        String designation = null;
        String designationAct = null;
        queryString = "SELECT Designation FROM tblCrmContact WHERE Id=" + contatId;

        try {
            //System.out.println("conatcID------->"+contatId);
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                designation = resultSet.getString("Designation");
                if (designation.equalsIgnoreCase("CU")) {
                    designationAct = "Customer";
                }
                if (designation.equalsIgnoreCase("CN")) {
                    designationAct = "Consultant";
                }
                if (designation.equalsIgnoreCase("TL")) {
                    designationAct = "TeamLead";
                }
                if (designation.equalsIgnoreCase("MN")) {
                    designationAct = "Manager";
                }
                if (designation.equalsIgnoreCase("DR")) {
                    designationAct = "Director";
                }
                if (designation.equalsIgnoreCase("OR")) {
                    designationAct = "Operations";
                }
            }//Closing while loop

        } catch (SQLException sqle) {
            throw new ServiceLocatorException(sqle);
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
            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }

        return designationAct;
    }

    /**
     * @param map
     * @return
     */
    public static Map sortByValue(Map map) {
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getValue())
                        .compareTo(((Map.Entry) (o2)).getValue());
            }
        });

        Map result = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    /**
     * *
     *
     * to get Account contacts associated with specific project
     *
     * Returns :Map
     *
     */
    public Map getProjectTeamWithoutAccount(int projectId, int accountId) throws ServiceLocatorException {
        CallableStatement callableStatement = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String queryString = null;
        ResultSet rs = null;
        Map<String, String> projTeamMap = new TreeMap();

        StringBuilder genKey = new StringBuilder();
        StringBuilder genValue = new StringBuilder();

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            // SELECT Distinct tblCrmContact.Id,FirstName,LastName FROM tblCrmContact JOIN tblCrmContactProjects 
//ON (tblCrmContact.Id=tblCrmContactProjects.CrmContactId) WHERE tblCrmContact.AccountId=116331
            queryString = "select tblCrmContact.id,concat(FirstName,' ',LastName) as empName from tblCrmContactProjects left outer join tblCrmContact on (tblCrmContactProjects.CrmContactId=tblCrmContact.id) where contactStatus='Active' and ProjectId=" + projectId + " and tblCrmContact.id not in (select empid from tblProjectTeam where projectId=" + projectId + ")";


            // System.out.println("queryString---->avilable project team---->"+queryString);

            preparedStatement = connection.prepareStatement(queryString);


            rs = preparedStatement.executeQuery();



            while (rs.next()) {


                genKey.append(rs.getInt("Id"));

                genValue.append(rs.getString("empName"));

                //genValue.toString();

                projTeamMap.put(genKey.toString(), genValue.toString());

                genKey.setLength(0);
                genValue.setLength(0);

            }

        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {

                if (callableStatement != null) {
                    callableStatement.close();
                    callableStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        //System.out.println("ProjectTeam---->"+projTeamMap);
        return sortByValue(projTeamMap);
    }

    /**
     * *
     *
     * To get email form the customer
     *
     *
     */
    /*
     public String getReportsEmailIdByContactId(int reportsToId,String reportsToType) throws ServiceLocatorException {
     PreparedStatement preparedStatement = null;
     Connection connection = null;
     ResultSet resultSet = null;
     String queryString = null;
     String queryString1 = null;
     String reportsToEmail = null;
     try{
            
            
     connection = ConnectionProvider.getInstance().getConnection();
     if(reportsToType.equalsIgnoreCase("V") || reportsToType.equalsIgnoreCase("C")){
     queryString = "SELECT Email1 FROM tblCrmContact WHERE Id = "+reportsToId;
     }
     else{
     queryString1 = "SELECT Email1,Email3 FROM tblEmployee WHERE Id = "+reportsToId;
     } 
     // System.out.println("queryString-->"+queryString1); 
     //  System.out.println("queryString-->"+queryString); 
     if(queryString!=null)
     {
     preparedStatement = connection.prepareStatement(queryString);
     resultSet = preparedStatement.executeQuery();
     if(resultSet.next()) {
     reportsToEmail = resultSet.getString("Email1")+"^-";
     }
     }
     else 
     {
     preparedStatement = connection.prepareStatement(queryString1);
     resultSet = preparedStatement.executeQuery();
     if(resultSet.next()) {
     String reportsTo2=resultSet.getString("Email3");
     if(reportsTo2==null || reportsTo2=="" || "".equals(reportsTo2) || "null".equals(reportsTo2))
     {
     reportsTo2="-"; 
     }
     reportsToEmail = resultSet.getString("Email1")+"^"+reportsTo2;
     }
     }
            
            
            
     }catch (SQLException sql){
     throw new ServiceLocatorException(sql);
     }finally{
     try {
     // resultSet Object Checking if it's null then close and set null
     if(resultSet != null) {
     resultSet.close();
     resultSet = null;
     }
                
     if(preparedStatement != null) {
     preparedStatement.close();
     preparedStatement = null;
     }
                
     if(connection != null) {
     connection.close();
     connection = null;
     }
     } catch (SQLException ex) {
     throw new ServiceLocatorException(ex);
     }
            
     }
     return reportsToEmail;
     }
     */
    public String getReportsEmailIdByContactId(int reportsToId, String reportsToType) throws ServiceLocatorException {
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;
        Connection connection = null;
        Connection connection1 = null;
        ResultSet resultSet = null;
        ResultSet resultSet1 = null;
        String queryString = null;
        String queryString1 = null;
        String queryString2 = null;
        String reportsToEmail = null;
        String objectType = "";
        try {

            // System.out.println("reportsToId-->"+reportsToId);
            // System.out.println("reportsToType-->"+reportsToType);
            connection = ConnectionProvider.getInstance().getConnection();
            if (reportsToType.equalsIgnoreCase("V") || reportsToType.equalsIgnoreCase("C")) {
                connection1 = ConnectionProvider.getInstance().getConnection();
                queryString2 = "SELECT ObjectType FROM tblProjectContacts WHERE ObjectId = " + reportsToId;
                preparedStatement1 = connection1.prepareStatement(queryString2);
                resultSet1 = preparedStatement1.executeQuery();
                if (resultSet1.next()) {
                    objectType = resultSet1.getString("ObjectType");

                    //System.out.println("objectType-->"+objectType);
                    if (objectType.equalsIgnoreCase("c") || objectType.equalsIgnoreCase("v")) {
                        queryString = "SELECT Email FROM tblProjectContacts WHERE ObjectId = " + reportsToId;
                    } else {
                        queryString1 = "SELECT Email1,Email3 FROM tblEmployee WHERE Id = " + reportsToId;
                    }
                }
                //System.out.println("queryString-->"+queryString);
                //System.out.println("queryString1-->"+queryString1);
            } else {
                queryString1 = "SELECT Email1,Email3 FROM tblEmployee WHERE Id = " + reportsToId;
            }
            // System.out.println("queryString-->"+queryString1); 
            //  System.out.println("queryString-->"+queryString); 
            if (queryString != null) {
                preparedStatement = connection.prepareStatement(queryString);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    reportsToEmail = resultSet.getString("Email") + "^-";
                }
            } else {
                preparedStatement = connection.prepareStatement(queryString1);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    String reportsTo2 = resultSet.getString("Email3");
                    if (reportsTo2 == null || reportsTo2 == "" || "".equals(reportsTo2) || "null".equals(reportsTo2)) {
                        reportsTo2 = "-";
                    }
                    reportsToEmail = resultSet.getString("Email1") + "^" + reportsTo2;
                }
            }



        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
                if (resultSet1 != null) {
                    resultSet1.close();
                    resultSet1 = null;
                }

                if (preparedStatement1 != null) {
                    preparedStatement1.close();
                    preparedStatement1 = null;
                }

                if (connection1 != null) {
                    connection1.close();
                    connection1 = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }

        }
//        System.out.prinln("reportsToEmail-->"+reportsToEmail);
        return reportsToEmail;
    }

    /**
     * *
     *
     * To get customer email id
     *
     *
     */
    /*public String getEmailIdByContactId(int contactId) throws ServiceLocatorException {
     PreparedStatement preparedStatement = null;
     Connection connection = null;
     ResultSet resultSet = null;
     String queryString = null;
     String reportsToEmail = null;
     try{
     connection = ConnectionProvider.getInstance().getConnection();
     queryString = "SELECT Email1 FROM tblProjectContacts inner join tblCrmContact on (tblCrmContact.Id = tblProjectContacts.ObjectId) WHERE ReportsTo!=0 and tblProjectContacts.ObjectId = "+contactId;
     //System.out.println("getEmailIdByContactId-->"+queryString);
     preparedStatement = connection.prepareStatement(queryString);
           
     resultSet = preparedStatement.executeQuery();
     if(resultSet.next()) {
     reportsToEmail = resultSet.getString("Email1");
     }
            
            
     }catch (SQLException sql){
     throw new ServiceLocatorException(sql);
     }finally{
     try {
     // resultSet Object Checking if it's null then close and set null
     if(resultSet != null) {
     resultSet.close();
     resultSet = null;
     }
                
     if(preparedStatement != null) {
     preparedStatement.close();
     preparedStatement = null;
     }
                
     if(connection != null) {
     connection.close();
     connection = null;
     }
     } catch (SQLException ex) {
     throw new ServiceLocatorException(ex);
     }
            
     }
     return reportsToEmail;
     }
     */
    public String getEmailIdByContactId(int contactId) throws ServiceLocatorException {
        PreparedStatement preparedStatement1 = null;
        Connection connection1 = null;
        ResultSet resultSet1 = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;
        String queryString1 = null;
        String queryString2 = null;
        String objectType = "";

        String reportsToEmail = null;
        try {
            connection1 = ConnectionProvider.getInstance().getConnection();
            //queryString = "SELECT Email1 FROM tblProjectContacts inner join tblCrmContact on (tblCrmContact.Id = tblProjectContacts.ObjectId) WHERE ReportsTo!=0 and tblProjectContacts.ObjectId = "+contactId;
            queryString = "SELECT ObjectType FROM tblProjectContacts where ObjectId = " + contactId;

            //System.out.println("getEmailIdByContactId-->"+queryString);

            preparedStatement1 = connection1.prepareStatement(queryString);

            resultSet1 = preparedStatement1.executeQuery();
            if (resultSet1.next()) {
                objectType = resultSet1.getString("ObjectType");
            }


            //System.out.println("objectType-->"+objectType);

            if (objectType.equalsIgnoreCase("c") || objectType.equalsIgnoreCase("v")) {
                //    System.out.println("in if-->");
                queryString1 = "SELECT Email FROM tblProjectContacts WHERE ObjectId = " + contactId;
            } else {
                //System.out.println("in lese");
                queryString2 = "SELECT Email1,Email3 FROM tblEmployee WHERE Id = " + contactId;
            }
            //  System.out.println("queryString1-->"+queryString1);
            //System.out.println("queryString2-->"+queryString2);

            connection = ConnectionProvider.getInstance().getConnection();
            if (queryString1 != null) {

                preparedStatement = connection.prepareStatement(queryString1);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    reportsToEmail = resultSet.getString("Email") + "^-";
                }
            }
            if (queryString2 != null) {
                preparedStatement = connection.prepareStatement(queryString2);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    String reportsTo2 = resultSet.getString("Email3");
                    //  System.out.println("reportsTo2-->"+reportsTo2);
                    if (reportsTo2 == null || reportsTo2 == "" || "".equals(reportsTo2) || "null".equals(reportsTo2)) {
                        reportsTo2 = "-";
                    }
                    reportsToEmail = resultSet.getString("Email1") + "^" + reportsTo2;
                }
            }




        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
                if (resultSet1 != null) {
                    resultSet1.close();
                    resultSet1 = null;
                }

                if (preparedStatement1 != null) {
                    preparedStatement1.close();
                    preparedStatement1 = null;
                }

                if (connection1 != null) {
                    connection1.close();
                    connection1 = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }

        }
        return reportsToEmail;
    }

    /**
     *
     * Customer team base on his id
     *
     *
     */
    /*  
     public Map getCustomerTeamMap(int empId) throws ServiceLocatorException {
     PreparedStatement preparedStatement = null;
     Connection connection = null;
     ResultSet resultSet = null;
     String queryString = null;
     String contactEmail = null;
     Map teamMap = new TreeMap();
     try{
     connection = ConnectionProvider.getInstance().getConnection();
     queryString = " SELECT ObjectId,CONCAT(firstName,' ',LastName) AS Ename FROM tblProjectContacts LEFT OUTER JOIN tblCrmContact ON (tblProjectContacts.ObjectId=tblCrmContact.Id) WHERE ReportsTo="+empId;
     //System.out.println("getteampMapByContactId-->"+queryString);
     preparedStatement = connection.prepareStatement(queryString);
           
     resultSet = preparedStatement.executeQuery();
     while(resultSet.next()) {
     teamMap.put(resultSet.getInt("ObjectId"),resultSet.getString("Ename"));
     }
            
            
     }catch (SQLException sql){
     throw new ServiceLocatorException(sql);
     }finally{
     try {
     // resultSet Object Checking if it's null then close and set null
     if(resultSet != null) {
     resultSet.close();
     resultSet = null;
     }
                
     if(preparedStatement != null) {
     preparedStatement.close();
     preparedStatement = null;
     }
                
     if(connection != null) {
     connection.close();
     connection = null;
     }
     } catch (SQLException ex) {
     throw new ServiceLocatorException(ex);
     }
            
     }
     return teamMap;
     }    
     */

    /*Modified for Dual ReportsTo
     * Date : 01/19/2015
     * Modified By : Santosh Kola
     */

    /*    public Map getCustomerTeamMap(int empId) throws ServiceLocatorException {
     PreparedStatement preparedStatement = null;
     PreparedStatement preparedStatement1 = null;
     Connection connection = null;
     Connection connection1 = null;
     ResultSet resultSet = null;
     ResultSet resultSet1 = null;
     String queryString = null;
     String contactEmail = null;
     Map teamMap = new TreeMap();
     String query="";
     try{
     connection = ConnectionProvider.getInstance().getConnection();
            
     connection1 = ConnectionProvider.getInstance().getConnection();
            
           
     queryString = "SELECT ObjectId,ObjectType FROM tblProjectContacts WHERE ReportsTo="+empId+" AND STATUS = 'Active'";
            
     preparedStatement = connection.prepareStatement(queryString);
           
     resultSet = preparedStatement.executeQuery();
            
     while(resultSet.next()) { 
     String objectType=resultSet.getString("ObjectType");
     int objectId=resultSet.getInt("ObjectId");
     if(objectType.equalsIgnoreCase("v") || objectType.equalsIgnoreCase("c"))
     {
     query = "select Id,CONCAT(FirstName,' ',LastName) AS NAME from tblCrmContact where ContactStatus='Active' AND Id="+objectId;
     }
     else if(objectType.equalsIgnoreCase("e"))
     {
     query = "select Id,CONCAT(FName,' ',LName) AS NAME from tblEmployee where CurStatus='Active' AND Id="+objectId;
     }
             
     preparedStatement1 = connection1.prepareStatement(query);
           
     resultSet1 = preparedStatement1.executeQuery();
     while(resultSet1.next()) { 
     teamMap.put(objectId,resultSet1.getString("NAME"));
     }
     query=null;
     preparedStatement1=null;
     resultSet1=null;
              
     }
            
     }catch (SQLException sql){
     sql.printStackTrace();
     throw new ServiceLocatorException(sql);
     }finally{
     try {
     // resultSet Object Checking if it's null then close and set null
     if(resultSet != null) {
     resultSet.close();
     resultSet = null;
     }
                
     if(preparedStatement != null) {
     preparedStatement.close();
     preparedStatement = null;
     }
                
     if(connection != null) {
     connection.close();
     connection = null;
     }
                
     if(resultSet1 != null) {
     resultSet1.close();
     resultSet1 = null;
     }
                
     if(preparedStatement1 != null) {
     preparedStatement1.close();
     preparedStatement1 = null;
     }
                
     if(connection1 != null) {
     connection1.close();
     connection1 = null;
     }
     } catch (SQLException ex) {
     throw new ServiceLocatorException(ex);
     }
            
     }
     return teamMap;
     }   */
    /*
     * New ProjectHierarchy changes 12/01/2014
     * 
     */
    public Map getCustomerTeamMap(int empId) throws ServiceLocatorException {
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;
        Connection connection = null;
        Connection connection1 = null;
        ResultSet resultSet = null;
        ResultSet resultSet1 = null;
        String queryString = null;
        String contactEmail = null;
        Map teamMap = new TreeMap();
        String query = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT DISTINCT(ObjectId),ResourceName,ResourceTitle FROM tblProjectContacts WHERE ReportsTo = ? AND STATUS = 'Active' AND ResourceType = 1";
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, empId);
            //teamMap = getMyProjectTeamMembersUpTo(empId, preparedStatement);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                teamMap.put(resultSet.getInt("ObjectId"), resultSet.getString("ResourceName"));
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
        return teamMap;
    }

    /* public List getCustomerTeamList(int empId) throws ServiceLocatorException {
     PreparedStatement preparedStatement = null;
     Connection connection = null;
     ResultSet resultSet = null;
     String queryString = null;
     String contactEmail = null;
     // Map teamMap = new TreeMap();
     List teamList = new ArrayList();
     try{
     connection = ConnectionProvider.getInstance().getConnection();
     //queryString = " SELECT empId FROM tblProjectTeam LEFT OUTER JOIN tblCrmContact ON (tblProjectTeam.empId=tblCrmContact.Id) WHERE ReportsTo="+empId;
     if(empId!=-1)
     queryString = " SELECT empId FROM tblProjectTeam LEFT OUTER JOIN tblCrmContact ON (tblProjectTeam.empId=tblCrmContact.Id) WHERE ReportsTo="+empId;
     else
     //queryString = " SELECT empId FROM tblProjectTeam LEFT OUTER JOIN tblCrmContact ON (tblProjectTeam.empId=tblCrmContact.Id) WHERE ProjectId ="+198; 
     queryString = " SELECT empId FROM tblProjectTeam LEFT OUTER JOIN tblCrmContact ON (tblProjectTeam.empId=tblCrmContact.Id) WHERE ProjectId ="+Integer.parseInt(Properties.getProperty("Cust.ProjectId")); 
     System.out.println("getteampMapByContactId-->"+queryString);
     //System.out.println("getteampMapByContactId-->"+queryString);
     preparedStatement = connection.prepareStatement(queryString);
           
     resultSet = preparedStatement.executeQuery();
     while(resultSet.next()) {
     teamList.add(resultSet.getInt("empId"));
     }
            
            
     }catch (SQLException sql){
     throw new ServiceLocatorException(sql);
     }finally{
     try {
     // resultSet Object Checking if it's null then close and set null
     if(resultSet != null) {
     resultSet.close();
     resultSet = null;
     }
                
     if(preparedStatement != null) {
     preparedStatement.close();
     preparedStatement = null;
     }
                
     if(connection != null) {
     connection.close();
     connection = null;
     }
     } catch (SQLException ex) {
     throw new ServiceLocatorException(ex);
     }
            
     }
     return teamList;
     }    */
    public List getCustomerTeamList(int empId, List projectList) throws ServiceLocatorException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;
        String contactEmail = null;
        // Map teamMap = new TreeMap();
        List teamList = new ArrayList();
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            if (empId != -1) {
                // queryString = " SELECT Distinct(ObjectId) FROM tblProjectContacts LEFT OUTER JOIN tblCrmContact ON (tblProjectContacts.ObjectId=tblCrmContact.Id) WHERE ReportsTo="+empId;
                //  queryString = " SELECT Distinct(ObjectId) FROM tblProjectContacts WHERE ReportsTo="+empId;
                queryString = " SELECT Distinct(ObjectId) FROM tblProjectContacts WHERE ReportsTo=" + empId + " AND STATUS='Active'";
            } else {
                String projectIds = "";

                if (projectList.size() > 0) {
                    projectIds = projectList.toString().replace("[", "");
                    projectIds = projectIds.replace("]", "");
                }
                //  queryString = " SELECT Distinct(ObjectId) FROM tblProjectContacts  WHERE ProjectId IN ("+projectIds+") "; 
                queryString = " SELECT Distinct(ObjectId) FROM tblProjectContacts  WHERE ProjectId IN (" + projectIds + ") AND STATUS='Active'";
            }
            // System.out.println("getteampMapByContactId-->"+queryString);
            preparedStatement = connection.prepareStatement(queryString);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                teamList.add(resultSet.getInt("ObjectId"));
            }


        } catch (SQLException sql) {
            sql.printStackTrace();
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
        return teamList;
    }

    public String getCustomerNameById(int empId) throws ServiceLocatorException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;
        //  String contactEmail = null;
        // Map teamMap = new TreeMap();
        //  List teamList = new ArrayList();
        String empName = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = " SELECT concat(FirstName,' .',LastName) as Name FROM tblCrmContact where Id =" + empId;
            // System.out.println("getteampMapByContactId-->"+queryString);
            preparedStatement = connection.prepareStatement(queryString);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                //  teamList.add(resultSet.getInt("empId"));
                empName = resultSet.getString("Name");
            }
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
        return empName;
    }

    public String getReportsNameByContactId(int contactId, String projectAssoIds) throws ServiceLocatorException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;
        PreparedStatement preparedStatement1 = null;
        Connection connection1 = null;
        ResultSet resultSet1 = null;
        String queryString1 = null;
        PreparedStatement preparedStatement3 = null;
        Connection connection3 = null;
        ResultSet resultSet3 = null;
        String queryString3 = null;
        String reportsToName = null;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            connection1 = ConnectionProvider.getInstance().getConnection();
            connection3 = ConnectionProvider.getInstance().getConnection();
            //   queryString = "SELECT concat(FirstName,'.',LastName) as Name FROM tblProjectContacts inner join tblCrmContact on (tblCrmContact.Id = tblProjectContacts.ReportsTo) WHERE ReportsTo!=0 and tblProjectContacts.ObjectId = "+contactId;
            queryString = "SELECT ReportsTo,ObjectType FROM tblProjectContacts  WHERE ReportsTo!=0 AND ProjectId IN (" + projectAssoIds + ") AND tblProjectContacts.ObjectId = " + contactId;
            //   System.out.println("getReportsNameByContactId1111111111111111111111111-->"+queryString);
            preparedStatement = connection.prepareStatement(queryString);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                queryString3 = "select ObjectType from tblProjectContacts where ObjectId=" + resultSet.getInt("ReportsTo");
                preparedStatement3 = connection3.prepareStatement(queryString3);

                resultSet3 = preparedStatement3.executeQuery();
                if (resultSet3.next()) {
                    if (resultSet3.getString("ObjectType").equalsIgnoreCase("c") || resultSet3.getString("ObjectType").equalsIgnoreCase("v")) {
                        queryString1 = " SELECT concat(FirstName,' ',LastName) AS Name FROM tblCrmContact where Id =" + resultSet.getInt("ReportsTo");
                    } else {
                        queryString1 = " SELECT concat(FName,' ',LName) AS Name FROM tblEmployee where Id =" + resultSet.getInt("ReportsTo");

                    }
                    preparedStatement1 = connection1.prepareStatement(queryString1);
                    resultSet1 = preparedStatement1.executeQuery();
                    if (resultSet1.next()) {
                        reportsToName = resultSet1.getString("Name");
                    }
                }
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
                if (resultSet1 != null) {
                    resultSet1.close();
                    resultSet1 = null;
                }

                if (preparedStatement1 != null) {
                    preparedStatement1.close();
                    preparedStatement1 = null;
                }

                if (connection1 != null) {
                    connection1.close();
                    connection1 = null;
                }
                if (resultSet3 != null) {
                    resultSet3.close();
                    resultSet3 = null;
                }

                if (preparedStatement3 != null) {
                    preparedStatement3.close();
                    preparedStatement3 = null;
                }

                if (connection3 != null) {
                    connection3.close();
                    connection3 = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }

        }
        return reportsToName;
    }

//new method to get sub-practice names in search page
    public List getSubPracticeForSearch() throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        List subPracticeForSearch = new ArrayList();//Description

        queryString = "select * from tblLKSubPractice ";

        connection = ConnectionProvider.getInstance().getConnection();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                subPracticeForSearch.add(resultSet.getString("Description"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return subPracticeForSearch; // returning the object.
    } //closing the method.
    public List getProjectsListByContactId(int contactId) throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        List teamList = new ArrayList();//Description



        connection = ConnectionProvider.getInstance().getConnection();

        try {
            //System.out.println("In cahe else");
            //queryString = "SELECT ProjectId FROM tblCrmContactProjects WHERE CrmContactId = "+contactId;
            //   queryString = "SELECT ProjectId FROM tblProjectContacts WHERE ObjectId = "+contactId;
            queryString = "SELECT ProjectId FROM tblProjectContacts WHERE ObjectId = " + contactId + " AND STATUS='Active'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                teamList.add(resultSet.getInt("ProjectId"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }

        }


        return teamList; // returning the object.
    } //closing the method.

    public Map getCustProjectReportsToMap(List projectList) throws ServiceLocatorException {
        Map resourceReportsToMap = new TreeMap();
        ResultSet resultSet = null;
        ResultSet resultSet1 = null;
        Connection connection = null;
        Statement statement = null;
        Connection connection1 = null;
        Statement statement1 = null;

        connection = ConnectionProvider.getInstance().getConnection();
        connection1 = ConnectionProvider.getInstance().getConnection();
        String projectIds = "";
        if (projectList != null) {
            if (projectList.size() > 0) {
                projectIds = projectList.toString().replace("[", "");
                projectIds = projectIds.replace("]", "");
            }
        }
        try {
            statement = connection.createStatement();
            statement1 = connection.createStatement();
            //if(!("".equals(primaryTeamMember))){
            if (!"".equals(projectIds)) {
                //resultSet = statement.executeQuery("select EmpId,EmpType from tblProjectTeam where ProjectId="+projectId );
                //System.out.println("reports to quesry-->select EmpId,Concat(FirstName,'.',LastName) as Name from tblCrmContact left outer join tblProjectTeam on (tblCrmContact.Id = tblProjectTeam.EmpId) where ProjectId="+projectId+" AND Designation != 'CU' AND Designation != 'CN'");
                //resultSet = statement.executeQuery("select EmpId,Concat(FirstName,'.',LastName) as Name from tblCrmContact left outer join tblProjectTeam on (tblCrmContact.Id = tblProjectTeam.EmpId) where ProjectId="+projectId+" AND Designation != 'CU' AND Designation != 'CN'" );
                String queryString1 = "";
                //String queryString = "select Distinct ObjectId,ObjectType,Concat(FirstName,'.',LastName) as Name from tblProjectContacts left outer join tblCrmContact on (tblProjectContacts.ObjectId = tblCrmContact.Id) where ProjectId IN("+projectIds+") AND ResourceTitle != '1' ";
                String queryString = "select Distinct ObjectId,ObjectType,Concat(FirstName,'.',LastName) as Name from tblProjectContacts left outer join tblCrmContact on (tblProjectContacts.ObjectId = tblCrmContact.Id) where ProjectId IN(" + projectIds + ") AND ResourceTitle != '1' AND tblProjectContacts.STATUS='Active'";
                //System.out.println("getCustProjectReportsToMap queryString-->"+queryString);
                resultSet = statement.executeQuery(queryString);
                while (resultSet.next()) {
                    resourceReportsToMap.put(resultSet.getInt("ObjectId"), resultSet.getString("Name"));
                    if (resultSet.getString("ObjectType").equalsIgnoreCase("c") || resultSet.getString("ObjectType").equalsIgnoreCase("v")) {
                        resourceReportsToMap.put(resultSet.getInt("ObjectId"), resultSet.getString("name"));
                    } else {
                        queryString1 = "select Id,CONCAT(FName,' ',LName) AS NAME from tblEmployee where Id=" + resultSet.getInt("ObjectId");
                        resultSet1 = statement1.executeQuery(queryString1);

                        // resultSet1 = preparedStatement1.executeQuery();
                        while (resultSet1.next()) {
                            resourceReportsToMap.put(resultSet1.getInt("Id"), resultSet1.getString("NAME"));
                        }
                    }

                }

            }



        } catch (SQLException ex) {
            throw new ServiceLocatorException(ex);
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
                if (resultSet1 != null) {
                    resultSet1.close();
                    resultSet1 = null;
                }
                if (statement1 != null) {
                    statement1.close();
                    statement1 = null;
                }
                if (connection1 != null) {
                    connection1.close();
                    connection1 = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }

        return resourceReportsToMap;
    }

    public Map getCustomerMap(List projectIdsList) throws ServiceLocatorException {
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;
        Connection connection = null;
        Connection connection1 = null;
        ResultSet resultSet = null;
        ResultSet resultSet1 = null;
        String queryString = null;
        String queryString1 = null;
        String contactEmail = null;
        Map customerMap = new TreeMap();
        String projectIds = "";
        if (projectIdsList != null) {
            if (projectIdsList.size() > 0) {
                projectIds = projectIdsList.toString().replace("[", "");
                projectIds = projectIds.replace("]", "");
            }
        }
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            connection1 = ConnectionProvider.getInstance().getConnection();
            // queryString = " SELECT ObjectType,ObjectId,CONCAT(firstName,' ',LastName) AS Ename FROM tblProjectContacts LEFT OUTER JOIN tblCrmContact ON (tblProjectContacts.ObjectId=tblCrmContact.Id) where  ProjectId in ('"+projectIds+"')";
            queryString = " SELECT ObjectType,ObjectId,CONCAT(firstName,' ',LastName) AS Ename FROM tblProjectContacts LEFT OUTER JOIN tblCrmContact ON (tblProjectContacts.ObjectId=tblCrmContact.Id) where  ProjectId in ('" + projectIds + "') AND tblProjectContacts.Status='Active'";
            //System.out.println("customer map-->"+queryString);
            preparedStatement = connection.prepareStatement(queryString);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString("ObjectType").equalsIgnoreCase("c") || resultSet.getString("ObjectType").equalsIgnoreCase("v")) {
                    customerMap.put(resultSet.getInt("ObjectId"), resultSet.getString("Ename"));
                } else {
                    queryString1 = "select Id,CONCAT(FName,' ',LName) AS NAME from tblEmployee where Id=" + resultSet.getInt("ObjectId");
                    preparedStatement1 = connection1.prepareStatement(queryString1);

                    resultSet1 = preparedStatement1.executeQuery();
                    while (resultSet1.next()) {
                        customerMap.put(resultSet1.getInt("Id"), resultSet1.getString("NAME"));
                    }
                }
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
                if (resultSet1 != null) {
                    resultSet1.close();
                    resultSet1 = null;
                }

                if (preparedStatement1 != null) {
                    preparedStatement1.close();
                    preparedStatement1 = null;
                }

                if (connection1 != null) {
                    connection1.close();
                    connection1 = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }

        }
        return customerMap;
    }
    /*For geting customer empIds list by projectsIds List
     * Date : 04/04/2014
     * Author : santosh kola
     * 
     */

    public String getCustomerList(List projectIdsList) throws ServiceLocatorException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;
        String contactEmail = null;
        PreparedStatement preparedStatement1 = null;
        Connection connection1 = null;
        ResultSet resultSet1 = null;
        String queryString1 = null;
        //  Map customerMap = new TreeMap();
        List customerList = new ArrayList();
        String projectIds = "";
        String empIds = "";
        if (projectIdsList != null) {
            if (projectIdsList.size() > 0) {
                projectIds = projectIdsList.toString().replace("[", "");
                projectIds = projectIds.replace("]", "");
            }
        }
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            connection1 = ConnectionProvider.getInstance().getConnection();
            //queryString = " SELECT ObjectType,ObjectId FROM tblProjectContacts LEFT OUTER JOIN tblCrmContact ON (tblProjectContacts.ObjectId=tblCrmContact.Id) where (firstName != '' || LastName != '') and ProjectId in ('"+projectIds+"') AND ResourceTitle != '8'";
            // System.out.println("customer map-->"+queryString);
            queryString = " SELECT ObjectType,ObjectId FROM tblProjectContacts  where  ProjectId in ('" + projectIds + "') AND ResourceTitle != '8'";
            preparedStatement = connection.prepareStatement(queryString);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                //customerMap.put(resultSet.getInt("empId"),resultSet.getString("Ename"));
                //   if(resultSet.getString("ObjectType").equalsIgnoreCase("C") || resultSet.getString("ObjectType").equalsIgnoreCase("V"))
                //   {
                customerList.add(resultSet.getInt("ObjectId"));
                //  }else
                // {
                //     queryString1 = "select Id form tblEmployee where "

                // }
            }
            if (customerList != null) {
                if (customerList.size() > 0) {
                    empIds = customerList.toString().replace("[", "");
                    empIds = empIds.replace("]", "");
                }
            }

        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
                if (resultSet1 != null) {
                    resultSet1.close();
                    resultSet1 = null;
                }

                if (preparedStatement1 != null) {
                    preparedStatement1.close();
                    preparedStatement1 = null;
                }

                if (connection1 != null) {
                    connection1.close();
                    connection1 = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }

        }
        return empIds;
    }

    public int getProjectContactsIdFromAccoundId(int accountId, int projectId, String objectId) throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        int projectId1 = 0;//Description



        connection = ConnectionProvider.getInstance().getConnection();

        try {
            //System.out.println("In cahe else");
            queryString = "select Id from tblProjectContacts where ObjectId=" + objectId + " and AccountId=" + accountId + " and ProjectId=" + projectId;
            // System.out.println(queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                projectId1 = resultSet.getInt("Id");
            }


        } catch (SQLException sql) {
            sql.printStackTrace();
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }

        }


        return projectId1; // returning the object.
    } //closing the method.

    //new
    public String getEmailIdForEmployee(int empId) throws ServiceLocatorException {

        //String projectType = "";
        String emailId = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        //queryString = "SELECT concat(FirstName,'.',LastName) as Name, LoginId FROM tblCatagory";
        String queryString = "Select Id,Email1 from tblEmployee where Id=" + empId;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                emailId = resultSet.getString("Email1");
                // int temp = emailId.indexOf("@");
                // emailId = emailId.substring(0,temp);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return emailId;
    }

    //new
    public Map getMyProjectList(int objectId) throws ServiceLocatorException {

        Map myprojectsTeamMap = new TreeMap();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT tblProjectContacts.ProjectId AS projectId,ProjectName FROM tblProjectContacts LEFT OUTER JOIN tblProjects ON(tblProjectContacts.ProjectId=tblProjects.Id) WHERE objectId=" + objectId + " and tblProjects.Status='Active' and tblProjectContacts.Status='Active'";
            preparedStatement = connection.prepareStatement(queryString);
            //preparedStatement = connection.prepareStatement(queryString);

            //preparedStatement.setString(1,department);

            //resultSet = preparedStatement.executeQuery();
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                myprojectsTeamMap.put(resultSet.getInt("projectId"),
                        resultSet.getString("ProjectName"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return myprojectsTeamMap; // returning the object.
    } //closing the method. 

    //For project reports to 
    public Map getProjectReportsTo(int accountId, int projectId) throws ServiceLocatorException {

        Map employeeMap = new TreeMap();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT DISTINCT(ObjectId) AS EmpId,ResourceName FROM tblProjectContacts  WHERE ResourceTitle!='1' AND STATUS = 'Active' "
                    + "AND AccountId =  " + accountId + " AND ProjectId = " + projectId;
            preparedStatement = connection.prepareStatement(queryString);
            //preparedStatement = connection.prepareStatement(queryString);

            //preparedStatement.setString(1,department);

            //resultSet = preparedStatement.executeQuery();
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employeeMap.put(resultSet.getInt("EmpId"), resultSet.getString("ResourceName"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return employeeMap; // returning the object.
    } //closing the method.  

    public String getResourceTypeForProject(int projectContactId) throws ServiceLocatorException {

        //String projectType = "";
        String empType = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        //queryString = "SELECT concat(FirstName,'.',LastName) as Name, LoginId FROM tblCatagory";
        String queryString = "SELECT ObjectType FROM tblProjectContacts WHERE ObjectId = " + projectContactId;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                empType = resultSet.getString("ObjectType");
                // int temp = emailId.indexOf("@");
                // emailId = emailId.substring(0,temp);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return empType;
    }

    public boolean checkAssociatedProject(String objectId) throws ServiceLocatorException {

        //String projectType = "";
        boolean result = false;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        //queryString = "SELECT concat(FirstName,'.',LastName) as Name, LoginId FROM tblCatagory";
        String queryString = "SELECT AccountId,ProjectId FROM tblProjectContacts WHERE ObjectId = " + objectId;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                result = true;
                // int temp = emailId.indexOf("@");
                // emailId = emailId.substring(0,temp);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return result;
    }

    public Map getRolesForProjectTeam() throws ServiceLocatorException {

        Map rolesMap = new TreeMap();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT Id,Role FROM tblLKProjectTeamRoles order by Id";
            preparedStatement = connection.prepareStatement(queryString);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                rolesMap.put(new Integer(resultSet.getInt("Id")),
                        resultSet.getString("Role"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return rolesMap; // returning the object.
    } //closing the method.

    public String getRoleByProjectContactId(String contactId) throws ServiceLocatorException {
        String roleId = "";
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT ResourceTitle FROM tblProjectContacts WHERE ObjectId = " + contactId + "";

        //System.out.println("queryString--->"+queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                roleId = resultSet.getString("ResourceTitle");
            }
            // System.out.println("roleId--->"+roleId);
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return roleId;
    }

    public int getReportsToIdByContactId(int contactId, int projectId) throws ServiceLocatorException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;
        int reportsToId = 0;
        try {


            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT ReportsTo FROM tblProjectContacts where ObjectId = " + contactId + " AND ProjectId =" + projectId;

            // System.out.println("getReportsEmailIdByContactId-->"+queryString);
            preparedStatement = connection.prepareStatement(queryString);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                reportsToId = resultSet.getInt("ReportsTo");
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
        return reportsToId;
    }

    public String getReportsToTypeByReportsToId(int reportsToId, int projectId) throws ServiceLocatorException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;
        String reportsToType = "";
        try {


            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT ObjectType FROM tblProjectContacts where ObjectId = " + reportsToId + " AND ProjectId=" + projectId;

            // System.out.println("getReportsEmailIdByContactId-->"+queryString);
            preparedStatement = connection.prepareStatement(queryString);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                reportsToType = resultSet.getString("ObjectType");
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
        return reportsToType;
    }

    public String getAssociatedProjectsById(int id) throws ServiceLocatorException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;
        String reportsToType = "";
        try {

            connection = ConnectionProvider.getInstance().getConnection();

            // queryString = "SELECT ProjectId FROM tblProjectContacts where ObjectId = "+id;
            queryString = "SELECT ProjectId FROM tblProjectContacts where ObjectId = " + id + " AND STATUS='Active'";

            // System.out.println("getReportsEmailIdByContactId-->"+queryString);
            preparedStatement = connection.prepareStatement(queryString);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                reportsToType = reportsToType + resultSet.getString("ProjectId") + ",";
            }
            if (!"".equals(reportsToType)) {
                reportsToType = reportsToType.substring(0, reportsToType.length() - 1);
            } else {
                reportsToType = "";
            }
            //System.out.println()
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
        return reportsToType;
    }
    //new method for customer loginId check

    public int checkForCustLoginId(String custLoginId) throws ServiceLocatorException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;
        int count = 0;
        try {


            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT * FROM tblCrmContact where LoginId = '" + custLoginId + "'";


            preparedStatement = connection.prepareStatement(queryString);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                count = 1;
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
        return count;


    }

    /*
     * New Methods for Customer tasks
     * Date : 04242014
     * 
     */
    // For customet tasks
    public Map getMyCrmAccountList(int objectId) throws ServiceLocatorException {

        Map myAccounts = new TreeMap();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            //  System.out.println("objectId-->"+objectId);
            connection = ConnectionProvider.getInstance().getConnection();

            queryString = " SELECT DISTINCT tblProjectContacts.AccountId AS accountId,tblCrmAccount.Name AS acoountName FROM tblProjectContacts LEFT OUTER JOIN tblCrmAccount ON(tblProjectContacts.AccountId=tblCrmAccount.Id) WHERE objectId=" + objectId;
            //  System.out.println("queryString-->"+queryString);
            preparedStatement = connection.prepareStatement(queryString);
            //preparedStatement = connection.prepareStatement(queryString);

            //preparedStatement.setString(1,department);

            //resultSet = preparedStatement.executeQuery();
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                myAccounts.put(resultSet.getInt("accountId"),
                        resultSet.getString("acoountName"));
                // myAccounts.add(resultSet.getString("acoountName"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
        //System.out.print("myAccounts-->"+myAccounts);
        return myAccounts; // returning the object.
    }

//new
    public int getRoleIdByContactId(int contactId) throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        int roleId = 0;//Description
        int isTeamLead = 0;



        connection = ConnectionProvider.getInstance().getConnection();

        try {
            //  System.out.println("In try-->"+contactId);
            queryString = "SELECT ResourceTitle FROM tblProjectContacts WHERE ObjectId = " + contactId;
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                roleId = resultSet.getInt("ResourceTitle");
            }
            if (roleId != 1) {
                isTeamLead = 1;
            }
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }

        }


        return isTeamLead; // returning the object.
    } //closing the method.

//new
    public String getCust_Fname_Lname(String userId) throws ServiceLocatorException {
        String Name = "";
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "select concat(trim(FirstName),'.',trim(LastName)) as Name from tblCrmContact where LoginId = '" + userId + "'";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                Name = resultSet.getString("Name");
            }


        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }



        return Name;
    }

    public String getCust_Fname_LnameById(String userId) throws ServiceLocatorException {
        String name = "";
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        //  String queryString = "select ResourceName as Name from tblProjectContacts where ObjectId = " + userId ;
        String queryString = "SELECT CONCAT(FirstName,'.',LastName) as Name FROM tblCrmContact WHERE  LoginId='" + userId + "'";
        //System.out.println("in dsdp -->"+queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                name = resultSet.getString("Name");
            }


        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }


        //System.out.println("name-->"+name);
        return name;
    }
    // for projectMAp

    public Map getProjectsForAccountId(String accountId, String resourceType) throws ServiceLocatorException {
        //StringBuffer projects  = new StringBuffer();
        Map projectMap = new TreeMap();
        String projectName = null;
        int projectId = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;
        if (resourceType.equalsIgnoreCase("c") || resourceType.equalsIgnoreCase("v")) {
            queryString = "SELECT distinct tblProjectContacts.ProjectId AS projectId,ProjectName FROM tblProjectContacts LEFT OUTER JOIN tblProjects ON(tblProjectContacts.ProjectId=tblProjects.Id) WHERE AccountId=" + accountId;
        } else {
            queryString = "SELECT Id AS projectId,ProjectName FROM tblProjects where CustomerId = " + accountId;
        }
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
//            projects.append("<xml version=\"1.0\">");
//            projects.append("<PROJECTS>");
//            projects.append("<USER projectId=\"\">--Please Select--</USER>");
            while (resultSet.next()) {
                projectId = resultSet.getInt("projectId");
                projectName = resultSet.getString("ProjectName");

                // projects.append(projectName);
                projectMap.put(projectId, projectName);
//                      projects.append("<USER projectId=\""+projectId+"\">");
//                  projects.append(projectName);
//                  projects.append("</USER>");
//                
            }
//            projects.append("</PROJECTS>");
//            projects.append("</xml>");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ServiceLocatorException sle) {
            sle.printStackTrace();
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
                ex.printStackTrace();
            }
        }
        // System.out.println("projectMap: "+projectMap);
        return projectMap;
    }

    //new method for customer task issue
    public String getMyTeamMembersForCustIssue(String reportsTo) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;
        connection = ConnectionProvider.getInstance().getConnection();

        List teamList = new ArrayList();
        String teamIds = "";
        try {


            queryString = "SELECT ObjectId FROM tblProjectContacts WHERE ReportsTo=" + reportsTo;
            //          System.out.println("in dsdp query-->"+queryString);

            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                teamList.add("'" + getLoginIdByObjectId(resultSet.getString("ObjectId")).split("\\^")[0] + "'");

                //teamList.add(resultSet.getString("ObjectId"));
            }



            if (teamList.size() > 0) {
                teamIds = teamList.toString().replace("[", "");
                teamIds = teamIds.replace("]", "");
            }


            // }//Closing Cache Checking
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

        // System.out.println("I am Out of Data Source Provider");
        return teamIds; // returning the object.
    }

    public String getLoginIdByObjectId(String objectId) throws ServiceLocatorException {

        //System.out.println("objectId-->"+objectId);
        Connection connection = null;
        CallableStatement callableStatement = null;

        connection = ConnectionProvider.getInstance().getConnection();


        String loginId = "";
        String loginIdType = "";
        try {

            callableStatement = connection.prepareCall("{call spTaskAssignment(?,?,?)}");
            callableStatement.setString(1, objectId);
            callableStatement.registerOutParameter(2, Types.VARCHAR);
            callableStatement.registerOutParameter(3, Types.VARCHAR);
            callableStatement.execute();
            loginId = callableStatement.getString(2);
            loginIdType = callableStatement.getString(3);
            // System.out.println("loginId-->"+loginId);
            // System.out.println("loginIdType-->"+loginIdType);




            // }//Closing Cache Checking
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {

                if (callableStatement != null) {
                    callableStatement.close();
                    callableStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }

        // System.out.println("I am Out of Data Source Provider");
        return loginId + "^" + loginIdType; // returning the object.
    }

    public String getObjectIdByLoginId(String loginId, String loginIdType) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String objectId = null;

        String teamIds = "";
        try {
            if (loginIdType.equalsIgnoreCase("e")) {
                queryString = "SELECT Id FROM tblEmployee WHERE LoginId='" + loginId + "'";
            } else if (loginIdType.equalsIgnoreCase("v") || loginIdType.equalsIgnoreCase("c")) {
                queryString = "SELECT Id FROM tblCrmContact WHERE LoginId='" + loginId + "'";
            }
            //queryString = "SELECT ObjectId FROM tblProjectContacts WHERE ReportsTo="+reportsTo;
            //           System.out.println("in dsdp query-->"+queryString);
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                objectId = resultSet.getString("Id");
            }

            // }//Closing Cache Checking
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

        // System.out.println("I am Out of Data Source Provider");
        return objectId; // returning the object.
    }

    public String getLoginIdTypeByLoginId(String loginId) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;
        connection = ConnectionProvider.getInstance().getConnection();


        String loginType = "";
        try {
            queryString = "SELECT Id FROM tblEmployee WHERE LoginId='" + loginId + "'";
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                loginType = "e";
            } else {
                resultSet.close();
                preparedStatement.close();
                String objectId = getObjectIdByLoginId(loginId, "V");//V or C we will get object id from same table i.e tblCrmContact.
                queryString = "SELECT ObjectType FROM tblProjectContacts WHERE ObjectId=" + objectId;
                preparedStatement = connection.prepareStatement(queryString);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    loginType = resultSet.getString("ObjectType");
                }
            }


            // }//Closing Cache Checking
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

        // System.out.println("I am Out of Data Source Provider");
        return loginType; // returning the object.
    }

    public String getAccountTypeById(int accId) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;
        connection = ConnectionProvider.getInstance().getConnection();


        String type = "";
        try {
            queryString = "SELECT TYPE FROM tblCrmAccount WHERE Id =" + accId;
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                type = resultSet.getString("TYPE");
            }



            // }//Closing Cache Checking
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

        // System.out.println("I am Out of Data Source Provider");
        return type; // returning the object.
    }

    public boolean checkProjectContactsByProjectId(String projectId) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;
        connection = ConnectionProvider.getInstance().getConnection();


        boolean isExists = false;
        try {
            queryString = "SELECT * FROM tblProjectContacts WHERE ProjectId='" + projectId + "'";
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                isExists = true;
            }


            // }//Closing Cache Checking
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

        // System.out.println("I am Out of Data Source Provider");
        return isExists; // returning the object.
    }

    public boolean isInteger(String s) {
        boolean check = false;
        try {
            //  System.out.println("in try");
            Integer.parseInt(s);
            check = true;
        } catch (NumberFormatException e) {
            //      System.out.println("in catch");
            check = false;
        }
        // only got here if we didn't return false
        //  System.out.println("check in dsdp---->"+check);
        return check;
    }

    public String getEmpEmailIdbyId(String empId) throws ServiceLocatorException {
        String loginId = "";
        Statement statement = null;
        ResultSet resultSet = null;
        Statement statement1 = null;
        ResultSet resultSet1 = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String objectType = "";
        Connection connection1 = null;
        //System.out.println("Emp Full Names -->"+empName);

        String queryString1 = "SELECT ObjectType FROM tblProjectContacts WHERE ObjectId ='" + empId + "'";
        String queryString = null;
        String queryString2 = null;
        try {
            connection1 = ConnectionProvider.getInstance().getConnection();
            statement1 = connection1.createStatement();
            resultSet1 = statement1.executeQuery(queryString1);
            while (resultSet1.next()) {
                objectType = resultSet1.getString("ObjectType");
            }
            //  System.out.println("in objectTyep-->"+objectType);
            if (objectType.equalsIgnoreCase("c") || objectType.equalsIgnoreCase("v")) {
                queryString2 = "SELECT Email1 FROM tblCrmContact WHERE Id ='" + empId + "'";
            } else {
                queryString = "SELECT Email1,Email3 FROM tblEmployee WHERE Id ='" + empId + "'";
            }

            if (queryString != null) {
                //   System.out.println("in if");
                statement = connection.createStatement();
                resultSet = statement.executeQuery(queryString);
                while (resultSet.next()) {
                    String loginId2 = resultSet.getString("Email3");
                    if (loginId2 == null || loginId2 == "" || "".equals(loginId2) || "null".equals(loginId2)) {
                        loginId2 = "-";
                    }
                    loginId = resultSet.getString("Email1") + "^" + loginId2;
                }
            } else if (queryString2 != null) {
                //  System.out.println("in lese");
                statement = connection.createStatement();
                resultSet = statement.executeQuery(queryString2);
                while (resultSet.next()) {

                    loginId = resultSet.getString("Email1") + "^-";
                }
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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
                if (resultSet1 != null) {
                    resultSet1.close();
                    resultSet1 = null;
                }
                if (statement1 != null) {
                    statement1.close();
                    statement1 = null;
                }
                if (connection1 != null) {
                    connection1.close();
                    connection1 = null;
                }

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return loginId;
    }
//new
    public String getEmailIdByPrimaryAssignId(String assignedToId, String assignedToType) throws ServiceLocatorException {
        String emailId = "";
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = null;
        String queryString1 = null;

        //System.out.println("Emp Full Names -->"+empName);
        if (assignedToType.equalsIgnoreCase("c") || assignedToType.equalsIgnoreCase("v")) {
            queryString = "SELECT Email1 FROM tblCrmContact WHERE LoginId='" + assignedToId + "'";
        } else {
            queryString1 = "SELECT Email1,Email3 FROM tblEmployee where LoginId ='" + assignedToId + "'";
        }

        //String queryString ="SELECT LoginId FROM tblEmployee WHERE CONCAT(fName,' ',mName,'.',lName) ='"+empName+"'";
        // System.out.println("Query-->"+queryString);

        try {
            if (queryString != null) {
                statement = connection.createStatement();
                resultSet = statement.executeQuery(queryString);
                while (resultSet.next()) {

                    emailId = resultSet.getString("Email1") + "^-";
                }
            } else if (queryString1 != null) {
                statement = connection.createStatement();
                resultSet = statement.executeQuery(queryString1);
                while (resultSet.next()) {
                    String loginId2 = resultSet.getString("Email3");
                    if (loginId2 == null || loginId2 == "" || "".equals(loginId2) || "null".equals(loginId2)) {
                        loginId2 = "-";
                    }
                    emailId = resultSet.getString("Email1") + "^" + loginId2;
                }
            }

        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return emailId;
    }

    public String getCustomerNameByContactsId(int empId) throws ServiceLocatorException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        PreparedStatement preparedStatement1 = null;
        Connection connection1 = null;
        ResultSet resultSet1 = null;
        String queryString1 = null;
        //  String contactEmail = null;
        // Map teamMap = new TreeMap();
        //  List teamList = new ArrayList();
        String empName = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            connection1 = ConnectionProvider.getInstance().getConnection();
            queryString = " SELECT ObjectType,ObjectId FROM tblProjectContacts where ObjectId =" + empId;
            // System.out.println("getteampMapByContactId-->"+queryString);
            preparedStatement = connection.prepareStatement(queryString);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                //  teamList.add(resultSet.getInt("empId"));

                if (resultSet.getString("ObjectType").equalsIgnoreCase("c") || resultSet.getString("ObjectType").equalsIgnoreCase("v")) {
                    queryString1 = " SELECT concat(FirstName,' ',LastName) AS Name FROM tblCrmContact where Id =" + empId;
                } else {
                    queryString1 = " SELECT concat(FName,' ',LName) AS Name FROM tblEmployee where Id =" + empId;

                }
                preparedStatement1 = connection1.prepareStatement(queryString1);
                resultSet1 = preparedStatement1.executeQuery();
                if (resultSet1.next()) {
                    empName = resultSet1.getString("Name");
                }

                // empName = resultSet.getString("Name");
            }
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
                if (resultSet1 != null) {
                    resultSet1.close();
                    resultSet1 = null;
                }

                if (preparedStatement1 != null) {
                    preparedStatement1.close();
                    preparedStatement1 = null;
                }

                if (connection1 != null) {
                    connection1.close();
                    connection1 = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }

        }
        return empName;
    }

    public String getEmpLoginIdbyId(String empId) throws ServiceLocatorException {
        String loginId = "";
        Statement statement = null;
        ResultSet resultSet = null;
        Statement statement1 = null;
        ResultSet resultSet1 = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        Connection connection1 = null;
        String objectType = "";
        //System.out.println("Emp Full Names -->"+empName);

        String queryString1 = "SELECT ObjectType FROM tblProjectContacts WHERE ObjectId ='" + empId + "'";
        String queryString = null;
        String queryString2 = null;
        try {
            connection1 = ConnectionProvider.getInstance().getConnection();
            statement1 = connection1.createStatement();
            resultSet1 = statement1.executeQuery(queryString1);
            if (resultSet1.next()) {
                objectType = resultSet1.getString("ObjectType");

                //    System.out.println("in objectTyep-->"+objectType);
                if (objectType.equalsIgnoreCase("c") || objectType.equalsIgnoreCase("v")) {
                    queryString2 = "SELECT Email1 FROM tblCrmContact WHERE Id ='" + empId + "'";
                } else {
                    queryString = "SELECT Email1,Email3 FROM tblEmployee WHERE Id ='" + empId + "'";
                }
            } else {
                queryString = "SELECT Email1,Email3 FROM tblEmployee WHERE Id ='" + empId + "'";
            }
            if (queryString != null) {
                //  System.out.println("in if");
                statement = connection.createStatement();
                resultSet = statement.executeQuery(queryString);
                while (resultSet.next()) {
                    String loginId2 = resultSet.getString("Email3");
                    if (loginId2 == null || loginId2 == "" || "".equals(loginId2) || "null".equals(loginId2)) {
                        loginId2 = "-";
                    }
                    loginId = resultSet.getString("Email1") + "^" + loginId2;
                }
            } else if (queryString2 != null) {
                //  System.out.println("in lese");
                statement = connection.createStatement();
                resultSet = statement.executeQuery(queryString2);
                while (resultSet.next()) {

                    loginId = resultSet.getString("Email1") + "^-";
                }
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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
                if (resultSet1 != null) {
                    resultSet1.close();
                    resultSet1 = null;
                }
                if (statement1 != null) {
                    statement1.close();
                    statement1 = null;
                }
                if (connection1 != null) {
                    connection1.close();
                    connection1 = null;
                }
            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return loginId;
    }

    public String getTimeSheetResourceTypeByEmpId(int empId, int timesheetId) throws ServiceLocatorException {

        //String projectType = "";
        String resourceType = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        //queryString = "SELECT concat(FirstName,'.',LastName) as Name, LoginId FROM tblCatagory";
        String queryString = "SELECT ResourceType FROM tblTimeSheets WHERE EmpId = " + empId + " and TimeSheetId=" + timesheetId;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resourceType = resultSet.getString("ResourceType");
                // int temp = emailId.indexOf("@");
                // emailId = emailId.substring(0,temp);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return resourceType;
    }

    public String updateNoOfResources(int projectId) throws ServiceLocatorException {

        //String projectType = "";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection1 = null;
        Statement statement1 = null;
        ResultSet resultSet1 = null;
        int count = 0;
        connection = ConnectionProvider.getInstance().getConnection();
        //queryString = "SELECT concat(FirstName,'.',LastName) as Name, LoginId FROM tblCatagory";
        String queryString = "SELECT * FROM tblProjectContacts WHERE ProjectId=" + projectId;
        //  System.out.println("queryString-->"+queryString);

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                count++;
            }
            // System.out.println("count-->"+count);
            String queryString1 = "update tblProjects set TotalResources=" + count + " where Id=" + projectId;
            //  System.out.println("queryString1-->"+queryString1);
            connection1 = ConnectionProvider.getInstance().getConnection();
            statement1 = connection1.createStatement();
            statement1.executeUpdate(queryString1);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
                if (resultSet1 != null) {
                    resultSet1.close();
                    resultSet1 = null;
                }

                if (statement1 != null) {
                    statement1.close();
                    statement1 = null;
                }

                if (connection1 != null) {
                    connection1.close();
                    connection1 = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return null;
    }
    /*
     * LastACtivity date by TeamMemberId
     * Author : Santosh Polmarsetty
     * Date : 06/11/2014
     */

    public String getdateLastActivityById(String teamMemberId, int accountId) throws ServiceLocatorException {
        Statement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;
        String createDate = "";
        String ActivityType = "";
        String resultType = null;
        try {


            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT MAX(CreatedDate) as CreatedDate,ActivityType FROM tblCrmActivity where CreatedById = " + "'" + teamMemberId + "' AND AccountId = " + accountId;

            //System.out.println("getdateLastActivityById-->"+queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            if (resultSet.next()) {
                createDate = resultSet.getString("CreatedDate");
                ActivityType = resultSet.getString("ActivityType");

            }

            if (!"".equals(createDate) && createDate != null) {

                createDate = createDate.substring(0, 10);
            } else {
                createDate = "-";


            }
            if (ActivityType != null && !"".equals(ActivityType)) {
            } else {
                ActivityType = "-";
            }
            resultType = ActivityType + "|" + createDate + "^";
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }

        }


        return resultType;
    }
    /*
     * New Methods For Madhavi mam Req.
     * Date : 07/31/2014
     * 
     * 
     */

    public Map getProjectsMap() throws ServiceLocatorException {

        //List prjoectList = new ArrayList();
        Map prjoectMap = new TreeMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        //queryString = "select Id,ProjectName from tblProjects Where ProjectName != '' ORDER BY ProjectName";
        //  queryString = "SELECT DISTINCT tblProjects.Id as Id,tblProjects.ProjectName as ProjectName FROM tblProjectContacts,tblProjects WHERE tblProjects.Id=tblProjectContacts.ProjectId ORDER BY Id";
        queryString = "SELECT DISTINCT tblProjects.Id as Id,tblProjects.ProjectName as ProjectName FROM tblProjectContacts,tblProjects WHERE tblProjects.Id=tblProjectContacts.ProjectId and tblProjects.Status = 'Active' ORDER BY Id";
        connection = ConnectionProvider.getInstance().getConnection();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                //  prjNameAndId.put(resultSet.getInt("Id"),resultSet.getString("ProjectName"));
                // prjoectList.add(resultSet.getString("ProjectName"));
                //prjoectMap.put(resultSet.getInt("Id"),resultSet.getString("ProjectName"));
                prjoectMap.put(resultSet.getInt("Id"), resultSet.getString("ProjectName"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        //System.out.println(prjNameAndId);
        return prjoectMap;
    }

    public Map getEmployeesByProjectId(int projectId) throws ServiceLocatorException {


        Map employeeMap = new LinkedHashMap();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            //queryString = "SELECT tblProjectContacts.ObjectId as EmpId,tblProjectContacts.ResourceName as EmpName FROM tblProjectContacts LEFT OUTER JOIN tblProjects ON(tblProjectContacts.ProjectId=tblProjects.Id) WHERE  ProjectId="+projectId+" and tblProjects.Status='Active' and ObjectType = 'E'";
            queryString = "SELECT tblProjectContacts.ObjectId as EmpId,tblProjectContacts.ResourceName as EmpName FROM tblProjectContacts LEFT OUTER JOIN tblProjects ON(tblProjectContacts.ProjectId=tblProjects.Id) WHERE  ProjectId=" + projectId + " and tblProjects.Status='Active' ";
            // queryString = "SELECT Id,CONCAT(TRIM(FName),'.',TRIM(LName)) AS EmployeeName,isManager,isTeamLead FROM" +
            //" tblEmployee WHERE CurStatus='Active' AND DeletedFlag != 1 AND Country LIKE '"+workingCountry+"'  ORDER BY EmployeeName";
            preparedStatement = connection.prepareStatement(queryString);

            //System.out.println("query-"+queryString);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employeeMap.put(resultSet.getString("EmpId"), resultSet.getString("EmpName"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return employeeMap; // returning the object.
    }

    public String getLoginIdByProjectReportsToId(int reportsToId) throws ServiceLocatorException {
        Statement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;
        String reportToName = "";

        try {


            connection = ConnectionProvider.getInstance().getConnection();
            if (reportsToId > 200000) {
                queryString = "select CONCAT(FirstName,'.',LastName) as Name from tblCrmContact where Id= " + reportsToId;
            } else {
                queryString = "select CONCAT(FName,'.',Lname) as Name from tblEmployee where Id = " + reportsToId;
            }

            //System.out.println("getdateLastActivityById-->"+queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            if (resultSet.next()) {
                reportToName = resultSet.getString("Name");

            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }

        }


        return reportToName;
    }

    public int checkForPMOLoginId(String PMOId, int projectId) throws ServiceLocatorException {
        Statement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;
        int isExists = 0;
        try {


            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "select * from tblPmoAuthors where AuthorId= '" + PMOId + "' and ProjectId= " + projectId;


            //System.out.println("getdateLastActivityById-->"+queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            if (resultSet.next()) {
                isExists = 1;
            }



        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }

        }

        // System.out.println("finalResult-->"+finalResult);
        return isExists;
    }
    //new
 /* public StringTokenizer getReferredLoginIdsForGroups(String referredMail)throws ServiceLocatorException {
     String group="";
     // System.out.println("referredMail-->"+referredMail);
     group=referredMail.split("@")[0];
     String toEmailLoginId="";
     //  System.out.println("group-->"+group);
     if("j2eegroup".contains(group)){
     toEmailLoginId =  com.mss.mirage.util.Properties.getProperty("j2eegroup").toString();
                
     }
     else if("wpsgroup".contains(group)){
     toEmailLoginId =  com.mss.mirage.util.Properties.getProperty("wpsgroup").toString();
                
     }  
     else if("wcsgroup".contains(group)){
     toEmailLoginId =  com.mss.mirage.util.Properties.getProperty("wcsgroup").toString();
                
     }  
     else if("b2bgroup".contains(group)){
     toEmailLoginId =  com.mss.mirage.util.Properties.getProperty("b2bgroup").toString();
     }
     else
     {
     toEmailLoginId= group+",";
     }
     //System.out.println("group1-->"+group);
     StringTokenizer st=new StringTokenizer(toEmailLoginId, ",");
        
     return st;
     
     }*/

    public StringTokenizer getReferredLoginIdsForGroups(String referredMail) throws ServiceLocatorException {
        String group = "";
        // System.out.println("referredMail-->"+referredMail);
        group = referredMail.split("@")[0];
        String toEmailLoginId = "";
        //  System.out.println("group-->"+group);
        if ("eaigroup".contains(group)) {
            toEmailLoginId = com.mss.mirage.util.Properties.getProperty("eaigroup").toString();

        } /*else if("wpsgroup".contains(group)){
         toEmailLoginId =  com.mss.mirage.util.Properties.getProperty("wpsgroup").toString();
                
         }  
         else if("wcsgroup".contains(group)){
         toEmailLoginId =  com.mss.mirage.util.Properties.getProperty("wcsgroup").toString();
                
         }  
         else if("b2bgroup".contains(group)){
         toEmailLoginId =  com.mss.mirage.util.Properties.getProperty("b2bgroup").toString();
         }*/ else {
            toEmailLoginId = group + ",";
        }
        //System.out.println("group1-->"+group);
        StringTokenizer st = new StringTokenizer(toEmailLoginId, ",");

        return st;

    }

    //new
    public int getReviewIdbyEmail(String email, int conId) throws ServiceLocatorException {
        Statement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;
        int reviewId = 0;

        try {


            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "select max(Id) as ReviewId from tblRecConsultantActivity where ConsultantId= " + conId + " and ForwardedTo='" + email + "'";

            //System.out.println("getdateLastActivityById-->"+queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            if (resultSet.next()) {
                reviewId = resultSet.getInt("ReviewId");

            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }

        }


        return reviewId;
    }

    public String getForwardedByEmail(int id, int conId) throws ServiceLocatorException {
        Statement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;
        String recruiterId = "";

        try {


            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "select ForwardedBy,ForwardedTo,ForwardToName,Comments,Rateing from tblRecConsultantActivity where ConsultantId= " + conId + " and Id=" + id;

            //System.out.println("getdateLastActivityById-->"+queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            if (resultSet.next()) {
                recruiterId = resultSet.getString("ForwardedBy") + "," + resultSet.getString("ForwardToName") + "," + resultSet.getString("ForwardedTo") + "," + resultSet.getString("Comments") + "," + resultSet.getString("Rateing");

            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }

        }

        //System.out.println("recruiterId-->"+recruiterId);
        return recruiterId;

    }


    /*Reteriving Projects by employee ID
     * Date : 09/02/2014
     * Author : Santosh Kola
     */
    public Map getProjectsMapByEmpId(int empId) throws ServiceLocatorException {

        //List prjoectList = new ArrayList();
        Map prjoectMap = new TreeMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        //queryString = "select Id,ProjectName from tblProjects Where ProjectName != '' ORDER BY ProjectName";
        //  queryString = "SELECT DISTINCT tblProjects.Id as Id,tblProjects.ProjectName as ProjectName FROM tblProjectContacts,tblProjects WHERE tblProjects.Id=tblProjectContacts.ProjectId ORDER BY Id";
        queryString = "SELECT DISTINCT tblProjects.Id as Id,tblProjects.ProjectName as ProjectName FROM tblProjectContacts,tblProjects WHERE tblProjects.Id=tblProjectContacts.ProjectId and tblProjects.Status = 'Active' AND tblProjectContacts.ObjectId = " + empId + " ORDER BY Id";
        connection = ConnectionProvider.getInstance().getConnection();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                //  prjNameAndId.put(resultSet.getInt("Id"),resultSet.getString("ProjectName"));
                // prjoectList.add(resultSet.getString("ProjectName"));
                //prjoectMap.put(resultSet.getInt("Id"),resultSet.getString("ProjectName"));
                prjoectMap.put(resultSet.getInt("Id"), resultSet.getString("ProjectName"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        //System.out.println(prjNameAndId);
        return prjoectMap;
    }

    public Map getTeamMapByProjectId(int projectId, int reportsToId) throws ServiceLocatorException {

        //List prjoectList = new ArrayList();
        Map prjoectMap = new TreeMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        //queryString = "select Id,ProjectName from tblProjects Where ProjectName != '' ORDER BY ProjectName";
        //  queryString = "SELECT DISTINCT tblProjects.Id as Id,tblProjects.ProjectName as ProjectName FROM tblProjectContacts,tblProjects WHERE tblProjects.Id=tblProjectContacts.ProjectId ORDER BY Id";
        // queryString = "SELECT DISTINCT tblProjects.Id as Id,tblProjects.ProjectName as ProjectName FROM tblProjectContacts,tblProjects WHERE tblProjects.Id=tblProjectContacts.ProjectId and tblProjects.Status = 'Active' AND tblProjectContacts.ObjectId = "+empId+" ORDER BY Id";
        queryString = "SELECT ObjectId as EmpId,Resourcename as EmpName FROM tblProjectContacts WHERE ProjectId=" + projectId + " and Status='Active' and ReportsTo = " + reportsToId + "  ORDER BY EmpName";
        connection = ConnectionProvider.getInstance().getConnection();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                //  prjNameAndId.put(resultSet.getInt("Id"),resultSet.getString("ProjectName"));
                // prjoectList.add(resultSet.getString("ProjectName"));
                //prjoectMap.put(resultSet.getInt("Id"),resultSet.getString("ProjectName"));
                prjoectMap.put(resultSet.getInt("EmpId"), resultSet.getString("EmpName"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        //System.out.println(prjNameAndId);
        return prjoectMap;
    }
    //new method salesEmp list by role id

    public Map getEmployeeNamesBySalesRoleId() throws ServiceLocatorException {

        Map employeeMap = new TreeMap();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            // queryString = "SELECT LoginId,FName,LName,MName FROM tblEmployee WHERE curStatus='Active' and DepartmentId LIKE '"+department+"%' AND CurStatus='Active' ORDER BY FName";
            queryString = "SELECT tblEmployee.LoginId,tblEmployee.FName,tblEmployee.LName,tblEmployee.MName "
                    + " FROM tblEmployee LEFT OUTER JOIN tblEmpRoles ON(tblEmployee.Id = tblEmpRoles.EmpId) "
                    + " WHERE tblEmpRoles.RoleId = 4  AND CurStatus='Active' ORDER BY FName";
            preparedStatement = connection.prepareStatement(queryString);
            //preparedStatement = connection.prepareStatement(queryString);

            //preparedStatement.setString(1,department);

            //resultSet = preparedStatement.executeQuery();
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employeeMap.put(resultSet.getString("LoginId"),
                        resultSet.getString("FName") + " " + resultSet.getString("MName")
                        + "." + resultSet.getString("LName"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return employeeMap; // returning the object.
    }

    //new
    public Map getEmployeeNamesUserIdByTitle(String titleOne, String titleTwo, String titleThree, String titleFour) throws ServiceLocatorException {

        Statement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;
        Map employeeMap = new TreeMap();

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT Id,FName,LName,MName FROM tblEmployee WHERE curStatus='Active' and (TitleTypeId ='" + titleOne + "' OR  TitleTypeId ='" + titleTwo + "' OR  TitleTypeId ='" + titleThree + "' OR  TitleTypeId ='" + titleFour + "') ORDER BY FName";
            statement = connection.createStatement();
            //preparedStatement = connection.prepareStatement(queryString);

            //preparedStatement.setString(1,department);

            //resultSet = preparedStatement.executeQuery();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                employeeMap.put(resultSet.getString("Id"),
                        resultSet.getString("FName") + " " + resultSet.getString("MName")
                        + "." + resultSet.getString("LName"));
            }

        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }

        return employeeMap; // returning the object.
    } //closing the method.

    //new method for forgeot password
    public String getEmpNameAndLoginIdByEmailId(String emailId, String emptype) throws ServiceLocatorException {

        String result = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            if (emptype.equalsIgnoreCase("e")) {
                queryString = "SELECT LoginId,CONCAT(FName,'.',LName) AS NAME FROM tblEmployee WHERE Email1 LIKE '" + emailId + "'";
            } else {
                queryString = "SELECT LoginId,PASSWORD,CONCAT(FirstName,'.',LastName) AS NAME FROM tblCrmContact WHERE Email1 LIKE '" + emailId + "'and iflag=1";
                //queryString = "SELECT LoginId,PASSWORD,CONCAT(FirstName,'.',LastName) AS NAME FROM tblCrmContactAlias WHERE Email LIKE '"+emailId+"'";  
                //queryString = "SELECT LoginId,PASSWORD,CONCAT(FirstName,'.',LastName) AS NAME FROM tblCrmContact WHERE Email1 LIKE '"+emailId+"'";  
            }
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getString("LoginId") + "-" + resultSet.getString("Name");
            } else {
                result = "nodata";
            }
            // System.out.println("dsdp--->"+result);            
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
        return result; // returning the object.
    }

    public Map getProjectNamesListByEmpId(int empId) throws ServiceLocatorException {
        Map project = new TreeMap();
        ResultSet resultSet = null;
        Connection connection = null;
        Statement statement = null;
        String queryString = null;
        connection = ConnectionProvider.getInstance().getConnection();

        try {
            statement = connection.createStatement();
            //queryString = "Select Id,ProjectName FROM tblProjects WHERE Id in (select ProjectId from vwProjectTeamEmployees where EmpId="+empId+" ORDER BY ProjectName DESC)";
            queryString = "SELECT ProjectName,tblProjects.Id as ProjectId FROM tblProjects LEFT OUTER JOIN tblProjectContacts ON (tblProjects.Id=tblProjectContacts.ProjectId) WHERE tblProjectContacts.Status='Active' and tblProjectContacts.ObjectId=" + empId;
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                project.put(resultSet.getString("ProjectId"), resultSet.getString("ProjectName"));
            }
        } catch (SQLException ex) {
            throw new ServiceLocatorException(ex);
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return project;
    }

    public int getCustomerIdByProjectId(int projectId) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;
        connection = ConnectionProvider.getInstance().getConnection();


        int customerId = 0;
        try {


            queryString = "SELECT CustomerId FROM tblProjects WHERE Id=" + projectId;
            //          System.out.println("in dsdp query-->"+queryString);

            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                customerId = resultSet.getInt("CustomerId");
            }





            // }//Closing Cache Checking
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

        // System.out.println("I am Out of Data Source Provider");
        return customerId; // returning the object.
    }

    public String getemployeenamebyloginId(String loginId) throws ServiceLocatorException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String queryString = null;
        ResultSet resultSet = null;
        String employeeName = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT Id,FName,LName,MName FROM tblEmployee WHERE curStatus='Active' and LoginId = ?";
            preparedStatement = connection.prepareStatement(queryString);

            preparedStatement.setString(1, loginId);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employeeName = resultSet.getString("FName") + " " + resultSet.getString("MName")
                        + "." + resultSet.getString("LName");
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return employeeName;
    } //closing the method.

    public Map getTaskEmpDetailsBasedOnIssueRel(String issueRel) throws ServiceLocatorException {
        // StringBuffer projects  = new StringBuffer();
        Map employeeMap = new TreeMap();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;


        //queryString = "SELECT distinct tblProjectContacts.ProjectId AS projectId,ProjectName FROM tblProjectContacts LEFT OUTER JOIN tblProjects ON(tblProjectContacts.ProjectId=tblProjects.Id) WHERE AccountId="+accountId;
        if (issueRel.equals("0")) {
            queryString = "SELECT CONCAT(TRIM(FName),'.',TRIM(LName)) AS EmpName,LoginId FROM"
                    + " tblEmployee WHERE CurStatus='Active' AND IsOperationContactTeam = 1   ORDER BY EmpName";
        } else {
            queryString = "SELECT EmpName,LoginId FROM tblEmpTaskResources WHERE IssueReletedTo_Id=" + issueRel + " AND STATUS = 'Active'";
        }

        // System.out.println("queryString-->"+queryString);
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employeeMap.put(resultSet.getString("LoginId"),
                        resultSet.getString("EmpName"));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ServiceLocatorException sle) {
            sle.printStackTrace();
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
                ex.printStackTrace();
            }
        }

        return employeeMap;
    }

    public Map getTaskEmpDetailsBasedOnHubbleNetworkInfraIssue(String issueRel) throws ServiceLocatorException {
        // StringBuffer projects  = new StringBuffer();
        Map employeeMap = new TreeMap();
        //  String projectName = null;
        String empName = null;
        // int projectId=0;
        String loginId = "";
        String loginIds[] = null;

        //queryString = "SELECT distinct tblProjectContacts.ProjectId AS projectId,ProjectName FROM tblProjectContacts LEFT OUTER JOIN tblProjects ON(tblProjectContacts.ProjectId=tblProjects.Id) WHERE AccountId="+accountId;
        if (issueRel.equalsIgnoreCase("1")) {
            loginIds = Properties.getProperty("Hubble").split(",");
        } else if (issueRel.equalsIgnoreCase("3")) {
            loginIds = Properties.getProperty("Network").split(",");
        } else if (issueRel.equalsIgnoreCase("4")) {
            loginIds = Properties.getProperty("Infra").split(",");
        }

        // System.out.println("queryString-->"+queryString);
        try {




            for (int i = 0; i < loginIds.length; i++) {
                employeeMap.put(loginIds[i],
                        DataSourceDataProvider.getInstance().getemployeenamebyloginId(loginIds[i]));
            }
        } catch (ServiceLocatorException sle) {
            sle.printStackTrace();
        }


        return employeeMap;
    }

    public Map getTaskEmpDetailsBasedOnPrjIssue(String projectId) throws ServiceLocatorException {
        // StringBuffer projects  = new StringBuffer();
        Map employeeMap = new TreeMap();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;
        String loginId = "";
        //queryString = "SELECT distinct tblProjectContacts.ProjectId AS projectId,ProjectName FROM tblProjectContacts LEFT OUTER JOIN tblProjects ON(tblProjectContacts.ProjectId=tblProjects.Id) WHERE AccountId="+accountId;

        queryString = "SELECT ResourceName AS EmpName,ObjectId FROM"
                + " tblProjectContacts WHERE STATUS='Active' AND ProjectId = " + projectId + "   ORDER BY EmpName";


        // System.out.println("queryString-->"+queryString);
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();


            // projectId=resultSet.getInt("Id");



            while (resultSet.next()) {
                loginId = DataSourceDataProvider.getInstance().getLoginIdByObjectId(resultSet.getString("ObjectId")).split("\\^")[0];
                employeeMap.put(loginId,
                        resultSet.getString("EmpName"));

            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ServiceLocatorException sle) {
            sle.printStackTrace();
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
                ex.printStackTrace();
            }
        }

        return employeeMap;
    }

    public String getPrimaryAssignToMailByLoginId(int taskId, String loginid) throws ServiceLocatorException {

        String priAssignToType = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection1 = null;
        Statement statement1 = null;
        ResultSet resultSet1 = null;
        String email = "";
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "Select PriAssignToType from tblEmpTasks where Id =" + taskId + " and PriAssignTO='" + loginid + "'";
        String queryString1 = "";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                priAssignToType = resultSet.getString("PriAssignToType");
                if (priAssignToType.equalsIgnoreCase("e")) {
                    queryString1 = "Select Email1 from tblEmployee where LoginId = '" + loginid + "'";
                } else if (priAssignToType.equalsIgnoreCase("v") || priAssignToType.equalsIgnoreCase("c")) {
                    queryString1 = "SELECT Email1 FROM tblCrmContact WHERE LoginId='" + loginid + "'";
                }
                connection1 = ConnectionProvider.getInstance().getConnection();
                statement1 = connection1.createStatement();
                resultSet1 = statement1.executeQuery(queryString1);
                while (resultSet1.next()) {
                    email = resultSet1.getString("Email1");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
                if (resultSet1 != null) {
                    resultSet1.close();
                    resultSet1 = null;
                }

                if (statement1 != null) {
                    statement1.close();
                    statement1 = null;
                }

                if (connection1 != null) {
                    connection1.close();
                    connection1 = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        //  System.out.println("email p -->"+email);
        return email;
    }

    public String getSecondaryAssignToMailByLoginId(int taskId, String loginid) throws ServiceLocatorException {

        String secAssignToType = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection1 = null;
        Statement statement1 = null;
        ResultSet resultSet1 = null;
        String email = "";
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "Select SecAssignedToType from tblEmpTasks where Id =" + taskId + " and SecAssignTo='" + loginid + "'";
        String queryString1 = "";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                secAssignToType = resultSet.getString("SecAssignedToType");
                if (secAssignToType.equalsIgnoreCase("e")) {
                    queryString1 = "Select Email1 from tblEmployee where LoginId = '" + loginid + "'";
                } else if (secAssignToType.equalsIgnoreCase("v") || secAssignToType.equalsIgnoreCase("c")) {
                    queryString1 = "SELECT Email1 FROM tblCrmContact WHERE LoginId='" + loginid + "'";
                }
                connection1 = ConnectionProvider.getInstance().getConnection();
                statement1 = connection1.createStatement();
                if (!"".equals(queryString1)) {
                    resultSet1 = statement1.executeQuery(queryString1);
                    while (resultSet1.next()) {
                        email = resultSet1.getString("Email1");
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
                if (resultSet1 != null) {
                    resultSet1.close();
                    resultSet1 = null;
                }

                if (statement1 != null) {
                    statement1.close();
                    statement1 = null;
                }

                if (connection1 != null) {
                    connection1.close();
                    connection1 = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        //     System.out.println("email s -->"+email);
        return email;
    }

    public String getTasksCreatedBy(int taskId) throws ServiceLocatorException {

        String createdbyType = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection1 = null;
        Statement statement1 = null;
        ResultSet resultSet1 = null;
        String email = "";
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "Select CreatedBy,CreatedByType from tblEmpTasks where Id =" + taskId;
        String queryString1 = "";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                createdbyType = resultSet.getString("CreatedByType");
                if (createdbyType.equalsIgnoreCase("e")) {
                    queryString1 = "Select Email1 from tblEmployee where LoginId = '" + resultSet.getString("CreatedBy") + "'";
                } else if (createdbyType.equalsIgnoreCase("v") || createdbyType.equalsIgnoreCase("c")) {
                    queryString1 = "SELECT Email1 FROM tblCrmContact WHERE LoginId='" + resultSet.getString("CreatedBy") + "'";
                }
                connection1 = ConnectionProvider.getInstance().getConnection();
                statement1 = connection1.createStatement();
                resultSet1 = statement1.executeQuery(queryString1);
                while (resultSet1.next()) {
                    email = resultSet1.getString("Email1");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
                if (resultSet1 != null) {
                    resultSet1.close();
                    resultSet1 = null;
                }

                if (statement1 != null) {
                    statement1.close();
                    statement1 = null;
                }

                if (connection1 != null) {
                    connection1.close();
                    connection1 = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        //      System.out.println("email s -->"+email);
        return email;
    }

    public String getMyTeamMembersForIssue1(String reportsTo, String departmentId, int empId) throws ServiceLocatorException {
        StringBuffer stringBuffer = new StringBuffer();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection1 = null;
        PreparedStatement preparedStatement1 = null;
        ResultSet resultSet1 = null;
        String queryString = null;
        connection = ConnectionProvider.getInstance().getConnection();
        Map myTeamMembersforIssue = new TreeMap();
        String key = null;
        String value = null;
        boolean check = false;
        String finalTeam = "";
        try {

           /* if (reportsTo.equalsIgnoreCase(Properties.getProperty("CEO"))) {
                queryString = "SELECT LoginId,FName,MName,LName,IsManager,IsTeamLead FROM tblEmployee WHERE ReportsTo= ? AND CurStatus='Active' AND DeletedFlag !=1 ORDER BY FName";
            } //new 
            else if (reportsTo.equalsIgnoreCase(Properties.getProperty("ExecutiveBoard"))) {
                queryString = "SELECT LoginId,FName,MName,LName,IsManager,IsTeamLead FROM tblEmployee WHERE ReportsTo= ? AND CurStatus='Active' AND DeletedFlag !=1 ORDER BY FName";
            } else {
                if ("GDC".equals(departmentId) || "SSG".equals(departmentId) || "Marketing".equals(departmentId)) {
                    queryString = "SELECT LoginId,FName,MName,LName,IsManager,IsTeamLead FROM tblEmployee WHERE   DepartmentId IN ('GDC','SSG','Marketing')  and ReportsTo= ? AND CurStatus='Active' AND DeletedFlag !=1 ORDER BY FName";
                } else {
                    queryString = "SELECT LoginId,FName,MName,LName,IsManager,IsTeamLead FROM tblEmployee WHERE  DepartmentId='" + departmentId + "' and ReportsTo= ? AND CurStatus='Active' AND DeletedFlag !=1 ORDER BY FName";
                }
            }*/
 queryString = "SELECT LoginId,FName,MName,LName,IsManager,IsTeamLead FROM tblEmployee WHERE ReportsTo= ? AND CurStatus='Active' AND DeletedFlag !=1 ORDER BY FName";
            preparedStatement = connection.prepareStatement(queryString);
            myTeamMembersforIssue = getMyTeamMembersUpTo(reportsTo, preparedStatement);

            int teamsize = myTeamMembersforIssue.size();


            int i = 0;
            if (myTeamMembersforIssue.size() > 0) {

                Iterator tempIterator = myTeamMembersforIssue.entrySet().iterator();
                while (tempIterator.hasNext()) {
                    i++;
                    Map.Entry entry = (Map.Entry) tempIterator.next();
                    key = entry.getKey().toString();
                    value = entry.getValue().toString();

                    stringBuffer.append("'");
                    stringBuffer.append(key);
                    stringBuffer.append("'");


                    if (i <= (teamsize - 1)) {

                        stringBuffer.append(",");
                    }


                    entry = null;
                }
            }
            finalTeam = stringBuffer.toString();
            //  System.out.println("I am Out of Data Source Provider-->"+stringBuffer.toString());
            check = checkProjectContactsReportsById(empId);

            if (check) {
                String queryString1 = null;
                String loginId = "";
                queryString1 = "SELECT ObjectId,ObjectType FROM tblProjectContacts WHERE ReportsTo=" + empId;
                connection1 = ConnectionProvider.getInstance().getConnection();
                preparedStatement1 = connection1.prepareStatement(queryString1);
                resultSet1 = preparedStatement1.executeQuery();
                while (resultSet1.next()) {
                    if (resultSet1.getString("ObjectType").equalsIgnoreCase("e")) {
                        loginId = getLoginIdByEmpId(resultSet1.getInt("ObjectId"));
                    } else {
                        loginId = getCustLoginIdByCustId(resultSet1.getInt("ObjectId"));
                    }
                    if (teamsize > 0) {
                        stringBuffer.append(",");
                    }
                    stringBuffer.append("'");
                    stringBuffer.append(loginId);
                    stringBuffer.append("'");

                    // stringBuffer.append(",");
                }
                finalTeam = stringBuffer.substring(0, stringBuffer.length());
            }
            // System.out.println("I am Out of Data Source Provider after-->"+ finalTeam);
            // }//Closing Cache Checking
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }


        return finalTeam; // returning the object.
    }

    public boolean checkProjectContactsReportsById(int empId) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;
        connection = ConnectionProvider.getInstance().getConnection();


        boolean isExists = false;
        try {
            queryString = "SELECT * FROM tblProjectContacts WHERE ReportsTo=" + empId;
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                isExists = true;
            }


            // }//Closing Cache Checking
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

        // System.out.println("I am Out of Data Source Provider");
        return isExists; // returning the object.
    }

    public String getCustLoginIdByCustId(int empId) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;
        connection = ConnectionProvider.getInstance().getConnection();


        String loginid = "";
        try {
            queryString = "SELECT LoginId FROM tblCrmContact WHERE Id=" + empId;
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                loginid = resultSet.getString("LoginId");
            }


            // }//Closing Cache Checking
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

        // System.out.println("I am Out of Data Source Provider");
        return loginid; // returning the object.
    }

    public boolean checkEmployeeOrCustomer(int empId) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;
        connection = ConnectionProvider.getInstance().getConnection();


        boolean isExists = false;
        try {
            queryString = "SELECT * FROM tblEmployee WHERE Id=" + empId;
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                isExists = true;
            }


            // }//Closing Cache Checking
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

        // System.out.println("I am Out of Data Source Provider");
        return isExists; // returning the object.
    }

    //Account Team size
    public int getAccountTeamSizeById(int AccountId) throws ServiceLocatorException {
        int teamCount = 0;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        //String queryString = "SELECT * FROM tblProjects WHERE ProjectName = '" + projName +"'";

        //String queryString = "SELECT COUNT(DISTINCT TeamMemberId) as Id  FROM tblCrmAccountTeam WHERE AccountId="+AccountId;
        String queryString = "SELECT COUNT(DISTINCT TeamMemberId) as Id  FROM tblCrmAccountTeam WHERE AccountId='" + AccountId + "' AND TeamMemberId!=''";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                teamCount = resultSet.getInt("Id");
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        //System.out.println("teamCount-->"+teamCount);
        return teamCount;
    }

    /*Method For getting associated project count
     * Date : 09/26/2014
     * Author : Santosh Kola
     */
    //SELECT COUNT(tblProjectContacts.ProjectId) FROM tblProjectContacts LEFT OUTER JOIN tblProjects ON(tblProjectContacts.ProjectId=tblProjects.Id) WHERE objectId=1885 AND tblProjects.STATUS='Active' AND tblProjectContacts.STATUS='Active'
    public int getEmpAssociatedProjectsCount(int empId) throws ServiceLocatorException {

        Statement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;
        int associatedProjectCount = 0;

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT COUNT(tblProjectContacts.ProjectId) as ProjectsCount FROM tblProjectContacts LEFT OUTER JOIN tblProjects ON(tblProjectContacts.ProjectId=tblProjects.Id) WHERE objectId=" + empId + " AND tblProjects.STATUS='Active' AND tblProjectContacts.STATUS='Active' AND tblProjectContacts.EmpProjStatus<>'OverHead' ";
            statement = connection.createStatement();
            //preparedStatement = connection.prepareStatement(queryString);

            //preparedStatement.setString(1,department);

            //resultSet = preparedStatement.executeQuery();
            resultSet = statement.executeQuery(queryString);
            if (resultSet.next()) {
                associatedProjectCount = resultSet.getInt("ProjectsCount");
            }

        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }

        return associatedProjectCount; // returning the object.
    } //closing the method.

    public List getListFromMap(Map mapObj) throws ServiceLocatorException {
        List listObj = new ArrayList();

        try {
            Iterator entries = mapObj.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry entry = (Map.Entry) entries.next();
                Object key = (Object) entry.getKey();

                listObj.add(String.valueOf(key));

            }
        } catch (Exception sql) {
            throw new ServiceLocatorException(sql);
        } finally {
        }

        return listObj; // returning the object.
    } //closing the method.

    public int isCustLoginIdExist(String custLoginId) throws ServiceLocatorException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;
        int count = 0;
        try {


            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT ObjectId FROM tblCrmContactAlias where LoginId = '" + custLoginId + "'";


            preparedStatement = connection.prepareStatement(queryString);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                count = 1;
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
        return count;


    }

    public List getEmpIdsByProjectIds(String projectIds) throws ServiceLocatorException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;
        List empidsList = null;
        try {


            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT ObjectId FROM tblProjectContacts where ProjectId IN (" + projectIds + ") AND STATUS='Active' AND ResourceType=1 ";

            // System.out.println("queryString111--->"+queryString);
            preparedStatement = connection.prepareStatement(queryString);

            resultSet = preparedStatement.executeQuery();
            empidsList = new ArrayList();
            while (resultSet.next()) {
                empidsList.add(resultSet.getInt("ObjectId"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
        return empidsList;


    }

    public String getPrimaryProjectReportsToEmail(int objectId) throws ServiceLocatorException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;
        String reportsToEmail = null;
        try {


            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT Email1 FROM tblCrmContact WHERE Id IN (SELECT ReportsTo FROM tblProjectContacts where ObjectId=" + objectId + " AND ResourceType=1)";


            preparedStatement = connection.prepareStatement(queryString);

            //resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                reportsToEmail = resultSet.getString("Email1");
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
        return reportsToEmail;


    }

    public String getReportsToTypeById(int reportsToId) throws ServiceLocatorException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;
        String reportsToType = "";
        try {


            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT ObjectType FROM tblProjectContacts where ObjectId = " + reportsToId;

            // System.out.println("getReportsEmailIdByContactId-->"+queryString);
            preparedStatement = connection.prepareStatement(queryString);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                reportsToType = resultSet.getString("ObjectType");
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
        return reportsToType;
    }

    public String getReportsToDetails(String empId) throws ServiceLocatorException {
        StringBuffer htmlText = new StringBuffer();
        Connection connection = null;
        CallableStatement callableStatement = null;
        String queryString = null;
        String reportsToDetails = null;
        try {
            connection = ConnectionProvider.getInstance().getConnection();

            callableStatement = connection.prepareCall("{call spSubmitTimesheetDetails(?,?)}");
            callableStatement.setInt(1, Integer.parseInt(empId));
            callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
            callableStatement.execute();
            reportsToDetails = callableStatement.getString(2);


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null


                if (callableStatement != null) {
                    callableStatement.close();
                    callableStatement = null;
                }

                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }

        }
        return reportsToDetails;
    }

    public int getReportsToCheck(int empId) throws ServiceLocatorException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;
        int checkedValue = 0;
        try {


            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT ReportsToFlag FROM tblEmployee WHERE ID = " + empId;

            // System.out.println("getReportsEmailIdByContactId-->"+queryString);
            preparedStatement = connection.prepareStatement(queryString);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                checkedValue = resultSet.getInt("ReportsToFlag");
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
        return checkedValue;
    }

    public String getReportsToById(int empId) throws ServiceLocatorException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;
        //int checkedValue = 0;
        String reportsToLoginId = null;
        try {


            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT ReportsTo FROM tblEmployee WHERE ID = " + empId;

            // System.out.println("getReportsEmailIdByContactId-->"+queryString);
            preparedStatement = connection.prepareStatement(queryString);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                reportsToLoginId = resultSet.getString("ReportsTo");
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
        return reportsToLoginId;
    }

    public String getEmailIdByObjectId(int objectId, String objectType) throws ServiceLocatorException {
        PreparedStatement preparedStatement = null;

        Connection connection = null;

        ResultSet resultSet = null;

        String queryString = null;

        String toEmail = null;
        //  String objectType="";
        try {

            // System.out.println("reportsToId-->"+reportsToId);
            // System.out.println("reportsToType-->"+reportsToType);
            connection = ConnectionProvider.getInstance().getConnection();


            //System.out.println("objectType-->"+objectType);
            if (objectType.equalsIgnoreCase("c") || objectType.equalsIgnoreCase("v")) {
                queryString = "SELECT Email1 AS Email FROM tblCrmContact WHERE Id = " + objectId;
            } else {
                queryString = "SELECT Email1 AS Email FROM tblEmployee WHERE Id = " + objectId;
            }
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                toEmail = resultSet.getString("Email");
            }
            // if()


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
//        System.out.println("reportsToEmail-->"+reportsToEmail);
        return toEmail;
    }

    /*New method for Total Profiles By practice
     * Date : 07/16/2014
     * Author : Santosh Kola
     */
    public List getSourcingEmpList() throws ServiceLocatorException {

        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = null;
        List sourcingList = new ArrayList();

        queryString = "select LoginId from tblEmployee where TeamId = 'Sourcing' and CurStatus = 'Active'";


        //String queryString ="SELECT LoginId FROM tblEmployee WHERE CONCAT(fName,' ',mName,'.',lName) ='"+empName+"'";
        // System.out.println("Query-->"+queryString);

        try {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {

                sourcingList.add(resultSet.getString("LoginId"));
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return sourcingList;
    }

    public boolean checkEmployeeLoginIdExists(String loginId) throws ServiceLocatorException {
        Statement statement = null;
        ResultSet resultSet = null;
        boolean flag = false;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT * FROM tblEmployee WHERE LoginId = '" + loginId + "'";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            if (resultSet.next()) {
                //  count = resultSet.getInt("id");
                flag = true;
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return flag;
    }

    /*
     * New method for get ReportsTo Email by teamMemberID
     * Author : Santosh Kola
     * Date : 11/13/2014
     */
    public String getReportsToEmailByEmployeeId(int empId) throws ServiceLocatorException {
        Statement statement = null;
        ResultSet resultSet = null;
        boolean flag = false;
        String reportsToEmail = "";
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT Email1 FROM tblEmployee WHERE loginid =(SELECT ReportsTo FROM tblEmployee WHERE id = " + empId + ")";
        // System.out.println("queryString-->"+queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            if (resultSet.next()) {
                //  count = resultSet.getInt("id");
                reportsToEmail = resultSet.getString("Email1");
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return reportsToEmail;
    }

    //new method for performance reviews
    public boolean checkForTitlesForDeptAndRole(String metricId, String dep, String role) throws ServiceLocatorException {
        Statement statement = null;
        ResultSet resultSet = null;
        boolean flag = false;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT * FROM tblTitleMetrics WHERE MetricId = " + metricId + " and DepartmentId='" + dep + "' and TitleTypeId='" + role + "'";
        //  System.out.println(queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            if (resultSet.next()) {
                //  count = resultSet.getInt("id");
                flag = true;
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return flag;
    }

    public boolean checkForMetricName(String metricName) throws ServiceLocatorException {
        Statement statement = null;
        ResultSet resultSet = null;
        boolean flag = false;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT * FROM tblEmpMetrics WHERE MetricName like '" + metricName + "'";
        //  System.out.println(queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            if (resultSet.next()) {
                //  count = resultSet.getInt("id");
                flag = true;
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return flag;
    }

    public String getMetricDetailsByMetricId(int metricId) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;
        connection = ConnectionProvider.getInstance().getConnection();


        String details = "";
        try {

            queryString = " SELECT `MetricName`, `MinValue`, `MaxValue`  FROM `tblEmpMetrics` WHERE Id=" + metricId;
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                details = resultSet.getString("MetricName") + "," + resultSet.getInt("MinValue") + "," + resultSet.getInt("MaxValue");
            }


            // }//Closing Cache Checking
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

        // System.out.println("I am Out of Data Source Provider");
        return details; // returning the object.
    }

    public String getMetricDetailsAndWeightageByMetricId(int metricId, String dept, String title) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;
        connection = ConnectionProvider.getInstance().getConnection();


        String details = "";
        try {

            queryString = "SELECT tblTitleMetrics.Weightage,tblEmpMetrics.MaxValue,tblEmpMetrics.MinValue,tblEmpMetrics.MetricName FROM tblEmpMetrics LEFT OUTER JOIN tblTitleMetrics ON(tblEmpMetrics.Id=tblTitleMetrics.MetricId) WHERE tblEmpMetrics.Id=" + metricId + " AND DepartmentId='" + dept + "' AND TitleTypeId='" + title + "' ";
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                details = resultSet.getString("MetricName") + "," + resultSet.getInt("MinValue") + "," + resultSet.getInt("MaxValue") + "," + resultSet.getInt("Weightage");
            }


            // }//Closing Cache Checking
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

        // System.out.println("I am Out of Data Source Provider");
        return details; // returning the object.
    }

    public String getEmpActivityDetails(String loginId, String dept) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";
        connection = ConnectionProvider.getInstance().getConnection();


        String details = "";
        try {

            int callOutBound = 0;
            int appointment = 0;
            int conferenceCalls = 0;
            int meeting = 0;
            int oppurtunity = 0;
            int requirement = 0;
            if (dept.equalsIgnoreCase("Sales")) {
                queryString = "SELECT activitytype,COUNT(id) AS c FROM tblCrmActivity WHERE activityType IN ('call-outbound','Appointment','Conference Call','Meeting','Oppurtunity','Requirement') and CreatedById='" + loginId + "' GROUP BY activityType";
            } else if (dept.equalsIgnoreCase("Recruiting")) {
                queryString = "SELECT activitytype,COUNT(id) AS c FROM tblRecActivity WHERE activityType IN ('call-outbound','Appointment','Conference Call','Meeting','Oppurtunity','Requirement') and CreatedBy='" + loginId + "' GROUP BY activityType";
            }
            // System.out.println("-------->"+queryString);
            if (!"".equals(queryString)) {
                preparedStatement = connection.prepareStatement(queryString);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    if (resultSet.getString("activitytype").equalsIgnoreCase("call-outbound")) {
                        callOutBound = resultSet.getInt("c");
                    } else if (resultSet.getString("activitytype").equalsIgnoreCase("Appointment")) {
                        appointment = resultSet.getInt("c");
                    } else if (resultSet.getString("activitytype").equalsIgnoreCase("Conference Call")) {
                        conferenceCalls = resultSet.getInt("c");
                    } else if (resultSet.getString("activitytype").equalsIgnoreCase("Meeting")) {
                        meeting = resultSet.getInt("c");
                    } else if (resultSet.getString("activitytype").equalsIgnoreCase("Oppurtunity")) {
                        oppurtunity = resultSet.getInt("c");
                    } else if (resultSet.getString("activitytype").equalsIgnoreCase("Requirement")) {
                        requirement = resultSet.getInt("c");
                    }
                }
            }
            details = callOutBound + "," + appointment + "," + conferenceCalls + "," + meeting + "," + oppurtunity + "," + requirement;

            // }//Closing Cache Checking
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

        //   System.out.println("I am Out of Data Source Provider----->"+details);
        return details; // returning the object.
    }

    /*Dual reports To Changes
     * 
     * Date : 01/16.2015
     */
    public boolean getDualFlagByObjectId(String objectId) throws ServiceLocatorException {
        StringBuffer htmlText = new StringBuffer();
        Connection connection = null;
        Statement statement = null;
        String queryString = null;
        String reportsToDetails = null;
        ResultSet resultSet = null;
        boolean isDualReq = false;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT Dualreporting FROM tblProjects WHERE ID = (SELECT ProjectId FROM tblProjectContacts WHERE ObjectId =" + objectId + " AND STATUS ='Active' AND ResourceType = 1 LIMIT 1)");

            if (resultSet.next()) {
                isDualReq = resultSet.getBoolean("Dualreporting");
            }

        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }

        }
        return isDualReq;
    }

    public boolean isDualReportsRequired(int projectId) throws ServiceLocatorException {
        StringBuffer htmlText = new StringBuffer();
        Connection connection = null;
        Statement statement = null;
        String queryString = null;
        String reportsToDetails = null;
        ResultSet resultSet = null;
        boolean isDualReq = false;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT Dualreporting FROM tblProjects WHERE Id = " + projectId);

            if (resultSet.next()) {
                isDualReq = resultSet.getBoolean("Dualreporting");
            }

        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }

        }
        return isDualReq;
    }

    /*
     * Method : getReportsTypeByTeamMemberId
     * Author : Santosh Kola
     * Date : 12/29/2014
     * 
     */
    public int getReportsTypeByTeamMemberId(int teamMemberId, int reportsToId) throws ServiceLocatorException {
        PreparedStatement preparedStatement = null;

        Connection connection = null;

        ResultSet resultSet = null;

        String queryString1 = null;
        String queryString2 = null;
        int reportsToType = -1;

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            queryString1 = "SELECT DISTINCT (ObjectId),ResourceName ,ResourceTitle FROM"
                    + " tblProjectContacts WHERE Status='Active' AND ResourceType = 1 AND ReportsTo=? AND ObjectId = ?";
            queryString2 = "SELECT DISTINCT (ObjectId),ResourceName ,ResourceTitle FROM"
                    + " tblProjectContacts WHERE Status='Active' AND ResourceType = 1 AND SecondReportTo=? AND ObjectId = ?";
            preparedStatement = connection.prepareStatement(queryString1);
            preparedStatement.setInt(1, reportsToId);
            preparedStatement.setInt(2, teamMemberId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) // reportsToType = 0;
            {
                reportsToType = 1;
            }

            resultSet.close();
            preparedStatement.close();
            if (reportsToType == -1) {
                preparedStatement = connection.prepareStatement(queryString2);
                preparedStatement.setInt(1, reportsToId);
                preparedStatement.setInt(2, teamMemberId);
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) // reportsToType = 1;
                {
                    reportsToType = 2;
                }
            }



        } catch (SQLException sql) {
            sql.printStackTrace();
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
        return reportsToType;
    }

    /*
     * Method for secondary reportsTO complete hierarchy
     * Date : 12/03/2014
     * Author : Santosh Kola
     * 
     */
    public Map getCustomerSecondaryTeamMap(int empId) throws ServiceLocatorException {
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;
        Connection connection = null;
        Connection connection1 = null;
        ResultSet resultSet = null;
        ResultSet resultSet1 = null;
        String queryString = null;
        String contactEmail = null;
        Map teamMap = new TreeMap();
        String query = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            //objectid -- set of emp
       /*      connection1 = ConnectionProvider.getInstance().getConnection();
            
             //queryString = "SELECT ObjectId,CONCAT(firstName,' ',LastName) AS Ename FROM tblProjectContacts LEFT OUTER JOIN tblCrmContact ON (tblProjectContacts.ObjectId=tblCrmContact.Id) WHERE ReportsTo="+empId;
             //queryString = "SELECT ObjectId,ObjectType FROM tblProjectContacts WHERE ReportsTo="+empId;
             queryString = "SELECT ObjectId,ObjectType FROM tblProjectContacts WHERE ReportsTo="+empId;
             //System.out.println("getteampMapByContactId-->"+queryString);
             preparedStatement = connection.prepareStatement(queryString);
           
             resultSet = preparedStatement.executeQuery();
            
             while(resultSet.next()) { 
             String objectType=resultSet.getString("ObjectType");
             int objectId=resultSet.getInt("ObjectId");
             if(objectType.equalsIgnoreCase("v") || objectType.equalsIgnoreCase("c"))
             {
             query = "select Id,CONCAT(FirstName,' ',LastName) AS NAME from tblCrmContact where ContactStatus='Active' AND Id="+objectId;
             }
             else if(objectType.equalsIgnoreCase("e"))
             {
             query = "select Id,CONCAT(FName,' ',LName) AS NAME from tblEmployee where CurStatus='Active' AND Id="+objectId;
             }
              
             //  System.out.println("query customermap---->"+query);
             //  connection1 = ConnectionProvider.getInstance().getConnection();
             preparedStatement1 = connection1.prepareStatement(query);
           
             resultSet1 = preparedStatement1.executeQuery();
             while(resultSet1.next()) { 
             teamMap.put(objectId,resultSet1.getString("NAME"));
             }
             query=null;
             preparedStatement1=null;
             resultSet1=null;
              
             }
             */
            //   queryString= "SELECT ObjectId,ResourceName,ResourceTitle FROM tblProjectContacts WHERE ReportsTo = ? AND STATUS = 'Active' AND ResourceType = 1";
            // queryString= "SELECT DISTINCT(ObjectId),ResourceName,ResourceTitle FROM tblProjectContacts WHERE ReportsTo = ? AND STATUS = 'Active' AND ResourceType = 1";
            queryString = "SELECT DISTINCT (ObjectId),ResourceName ,ResourceTitle FROM"
                    + " tblProjectContacts WHERE Status='Active' AND ResourceType = 1 AND SecondReportTo=? ";
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, empId);
            // teamMap = getMyProjectTeamMembersUpTo(empId, preparedStatement);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                teamMap.put(resultSet.getInt("ObjectId"), resultSet.getString("ResourceName"));
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
        teamMap = sortMapByValues(teamMap);
        return teamMap;
    }
    /*
     * Method : getCustomerAllTeamMap
     * Author : Santosh Kola
     * Date : 29/12/2014
     */

    public Map getCustomerAllTeamMap(int empId) throws ServiceLocatorException {
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;
        Connection connection = null;
        Connection connection1 = null;
        ResultSet resultSet = null;
        ResultSet resultSet1 = null;
        String queryString = null;
        String contactEmail = null;
        Map teamMap = new TreeMap();
        String query = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT DISTINCT (ObjectId),ResourceName ,ResourceTitle FROM"
                    + " tblProjectContacts WHERE Status='Active' AND ResourceType = 1 AND (SecondReportTo=? OR ReportsTo = ?)";
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, empId);
            preparedStatement.setInt(2, empId);
            // teamMap = getMyProjectAllTeamMembersUpTo(empId, preparedStatement);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                teamMap.put(resultSet.getInt("ObjectId"), resultSet.getString("ResourceName"));
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
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
        teamMap = sortMapByValues(teamMap);
        return teamMap;
    }

    public String getSecReportsToDetails(String empId) throws ServiceLocatorException {
        StringBuffer htmlText = new StringBuffer();
        Connection connection = null;
        CallableStatement callableStatement = null;
        String queryString = null;
        String reportsToDetails = null;
        try {
            connection = ConnectionProvider.getInstance().getConnection();

            callableStatement = connection.prepareCall("{call spSecReportsToEmail(?,?)}");
            //  callableStatement = connection.prepareCall("{call spGetReportsToEmail(?,?)}");
            callableStatement.setInt(1, Integer.parseInt(empId));
            callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
            callableStatement.execute();
            reportsToDetails = callableStatement.getString(2);


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null


                if (callableStatement != null) {
                    callableStatement.close();
                    callableStatement = null;
                }

                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }

        }
        return reportsToDetails;
    }

    /*
     * Dual ReportsTo changes
     * Date : 10/22/2014
     * Author : Santosh Kola
     */
    public Map getSecondaryTeamMap(int empId) throws ServiceLocatorException {
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;
        Connection connection = null;
        Connection connection1 = null;
        ResultSet resultSet = null;
        ResultSet resultSet1 = null;
        String queryString = null;
        String contactEmail = null;
        Map teamMap = new TreeMap();
        String query = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            //objectid -- set of emp
         /*    connection1 = ConnectionProvider.getInstance().getConnection();
            
             //queryString = "SELECT ObjectId,CONCAT(firstName,' ',LastName) AS Ename FROM tblProjectContacts LEFT OUTER JOIN tblCrmContact ON (tblProjectContacts.ObjectId=tblCrmContact.Id) WHERE ReportsTo="+empId;
             queryString = "SELECT ObjectId,ObjectType FROM tblProjectContacts WHERE SecondReportTo="+empId+" AND Status='Active'";
             //System.out.println("getteampMapByContactId-->"+queryString);
             preparedStatement = connection.prepareStatement(queryString);
           
             resultSet = preparedStatement.executeQuery();
            
             while(resultSet.next()) { 
             String objectType=resultSet.getString("ObjectType");
             int objectId=resultSet.getInt("ObjectId");
             if(objectType.equalsIgnoreCase("v") || objectType.equalsIgnoreCase("c"))
             {
             query = "select Id,CONCAT(FirstName,' ',LastName) AS NAME from tblCrmContact where ContactStatus='Active' AND Id="+objectId;
             }
             else if(objectType.equalsIgnoreCase("e"))
             {
             query = "select Id,CONCAT(FName,' ',LName) AS NAME from tblEmployee where CurStatus='Active' AND Id="+objectId;
             }
              
             //  System.out.println("query customermap---->"+query);
             //  connection1 = ConnectionProvider.getInstance().getConnection();
             preparedStatement1 = connection1.prepareStatement(query);
           
             resultSet1 = preparedStatement1.executeQuery();
             while(resultSet1.next()) { 
             teamMap.put(objectId,resultSet1.getString("NAME"));
             }
             query=null;
             preparedStatement1=null;
             resultSet1=null;
              
             }
             */

            queryString = "SELECT DISTINCT(ObjectId),ResourceName,ResourceTitle FROM tblProjectContacts WHERE SecondReportTo = ? AND STATUS = 'Active' AND ResourceType = 1";
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, empId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                teamMap.put(resultSet.getInt("ObjectId"), resultSet.getString("ResourceName"));
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
        return teamMap;
    }

    public List getCustomerSecondaryTeamList(int empId, List projectList) throws ServiceLocatorException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;
        String contactEmail = null;
        // Map teamMap = new TreeMap();
        List teamList = new ArrayList();
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            if (empId != -1) {
                // queryString = " SELECT Distinct(ObjectId) FROM tblProjectContacts LEFT OUTER JOIN tblCrmContact ON (tblProjectContacts.ObjectId=tblCrmContact.Id) WHERE ReportsTo="+empId;
                //  queryString = " SELECT Distinct(ObjectId) FROM tblProjectContacts WHERE ReportsTo="+empId;
                queryString = " SELECT Distinct(ObjectId) FROM tblProjectContacts WHERE SecondReportTo=" + empId + " AND STATUS='Active'";
            } else {
                String projectIds = "";

                if (projectList.size() > 0) {
                    projectIds = projectList.toString().replace("[", "");
                    projectIds = projectIds.replace("]", "");
                }
                //  queryString = " SELECT Distinct(ObjectId) FROM tblProjectContacts  WHERE ProjectId IN ("+projectIds+") "; 
                queryString = " SELECT Distinct(ObjectId) FROM tblProjectContacts  WHERE ProjectId IN (" + projectIds + ") AND STATUS='Active'";
            }
            // System.out.println("getteampMapByContactId-->"+queryString);
            preparedStatement = connection.prepareStatement(queryString);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                teamList.add(resultSet.getInt("ObjectId"));
            }


        } catch (SQLException sql) {
            sql.printStackTrace();
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
        return teamList;
    }

    public Map getSecondaryTeamMapByProjectId(int projectId, int reportsToId) throws ServiceLocatorException {

        //List prjoectList = new ArrayList();
        Map prjoectMap = new TreeMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        //queryString = "select Id,ProjectName from tblProjects Where ProjectName != '' ORDER BY ProjectName";
        //  queryString = "SELECT DISTINCT tblProjects.Id as Id,tblProjects.ProjectName as ProjectName FROM tblProjectContacts,tblProjects WHERE tblProjects.Id=tblProjectContacts.ProjectId ORDER BY Id";
        // queryString = "SELECT DISTINCT tblProjects.Id as Id,tblProjects.ProjectName as ProjectName FROM tblProjectContacts,tblProjects WHERE tblProjects.Id=tblProjectContacts.ProjectId and tblProjects.Status = 'Active' AND tblProjectContacts.ObjectId = "+empId+" ORDER BY Id";
        queryString = "SELECT ObjectId as EmpId,Resourcename as EmpName FROM tblProjectContacts WHERE ProjectId=" + projectId + " and Status='Active' and SecondReportTo = " + reportsToId + "  ORDER BY EmpName";
        connection = ConnectionProvider.getInstance().getConnection();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                //  prjNameAndId.put(resultSet.getInt("Id"),resultSet.getString("ProjectName"));
                // prjoectList.add(resultSet.getString("ProjectName"));
                //prjoectMap.put(resultSet.getInt("Id"),resultSet.getString("ProjectName"));
                prjoectMap.put(resultSet.getInt("EmpId"), resultSet.getString("EmpName"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        //System.out.println(prjNameAndId);
        return prjoectMap;
    }

    /*Customer team map
     * Author : Santosh Kola
     * Date : 01/19/2015
     */
    public List getCustomerAllTeamList(int empId, List projectList) throws ServiceLocatorException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;
        String contactEmail = null;
        // Map teamMap = new TreeMap();
        List teamList = new ArrayList();
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            if (empId != -1) {
                // queryString = " SELECT Distinct(ObjectId) FROM tblProjectContacts LEFT OUTER JOIN tblCrmContact ON (tblProjectContacts.ObjectId=tblCrmContact.Id) WHERE ReportsTo="+empId;
                //  queryString = " SELECT Distinct(ObjectId) FROM tblProjectContacts WHERE ReportsTo="+empId;
                queryString = " SELECT Distinct(ObjectId) FROM tblProjectContacts WHERE (SecondReportTo=" + empId + " OR ReportsTo=" + empId + ") AND STATUS='Active'";
            } else {
                String projectIds = "";

                if (projectList.size() > 0) {
                    projectIds = projectList.toString().replace("[", "");
                    projectIds = projectIds.replace("]", "");
                }
                //  queryString = " SELECT Distinct(ObjectId) FROM tblProjectContacts  WHERE ProjectId IN ("+projectIds+") "; 
                queryString = " SELECT Distinct(ObjectId) FROM tblProjectContacts  WHERE ProjectId IN (" + projectIds + ") AND STATUS='Active'";
            }
            //System.out.println("getCustomerSecondaryTeamList-->"+queryString);
            preparedStatement = connection.prepareStatement(queryString);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                teamList.add(resultSet.getInt("ObjectId"));
            }


        } catch (SQLException sql) {
            sql.printStackTrace();
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
        return teamList;
    }

    //-------------------Review methods-----------
    //-------------------Review methods-----------
    public Map getMyReviews(int reviewTypes) throws ServiceLocatorException {
        //reviewTypes = 0 ---> Flag=1
        //reviewTypes = 1 ---> Flag=1 OR Flag=4

        // System.err.println("I am in getEmployeeNamesByReportingPerson");
        Map myReviewMap = new TreeMap();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            if (reviewTypes == 1) {
                queryString = "SELECT Id,ReviewType from tblLkReviews where status='Active' AND (Flag=1 OR Flag=4)";
            } else if (reviewTypes == 2) {
                queryString = "SELECT Id,ReviewType from tblLkReviews where status='Active' AND (Flag=1 OR Flag=5)";
            } else if (reviewTypes == 3) {
                queryString = "SELECT Id,ReviewType from tblLkReviews where status='Active' AND (Flag=1 OR Flag=5 OR Flag=4)";
            } else {
                queryString = "SELECT Id,ReviewType from tblLkReviews where status='Active' AND Flag=1";
            }

            preparedStatement = connection.prepareStatement(queryString);
            //preparedStatement = connection.prepareStatement(queryString);

            //preparedStatement.setString(1,department);

            //resultSet = preparedStatement.executeQuery();
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                myReviewMap.put(resultSet.getString("Id"), resultSet.getString("ReviewType"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
        myReviewMap = sortMapByValues(myReviewMap);
        return myReviewMap; // returning the object.
    } //closing the method.

    public String getTeamLoginIdList(Map teamMap) throws ServiceLocatorException {
        // List listObj = new ArrayList();
        String teamList = "";
        try {
            Iterator entries = teamMap.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry entry = (Map.Entry) entries.next();
                Object key = (Object) entry.getKey();
                teamList = teamList + "'" + String.valueOf(key) + "',";
                //listObj.add(String.valueOf(key));

            }
            if (!"".equals(teamList)) {
                teamList = teamList.substring(0, teamList.length() - 1);
            }

        } catch (Exception sql) {
            throw new ServiceLocatorException(sql);
        } finally {
        }
        //System.out.println("teamList-->"+teamList);
        return teamList; // returning the object.
    }

    public Map getTeamReviewTypes(int reviewFlag) throws ServiceLocatorException {
        // System.err.println("I am in getEmployeeNamesByReportingPerson");
        Map reviewMap = new TreeMap();//key-Description

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT Id,ReviewType from tblLkReviews where status='Active' AND Flag=" + reviewFlag;
            preparedStatement = connection.prepareStatement(queryString);
            //preparedStatement = connection.prepareStatement(queryString);

            //preparedStatement.setString(1,department);

            //resultSet = preparedStatement.executeQuery();
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                reviewMap.put(resultSet.getString("Id"), resultSet.getString("ReviewType"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
        reviewMap = sortMapByValues(reviewMap);
        return reviewMap; // returning the object.
    } //closing the method.

    public Map getEmployeeNamesByWorkingCountry(String workingCountry) throws ServiceLocatorException {


        Map employeeMap = new LinkedHashMap();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT LoginId,CONCAT(TRIM(FName),'.',TRIM(LName)) AS EmployeeName FROM"
                    + " tblEmployee WHERE CurStatus='Active' AND DeletedFlag != 1 AND WorkingCountry LIKE '" + workingCountry + "'  ORDER BY EmployeeName";
            preparedStatement = connection.prepareStatement(queryString);

            //System.out.println("query-"+queryString);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employeeMap.put(resultSet.getString("LoginId"), resultSet.getString("EmployeeName"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return employeeMap; // returning the object.
    }

    public Map getAllReviewTypes() throws ServiceLocatorException {
        // System.err.println("I am in getEmployeeNamesByReportingPerson");
        Map reviewMap = new TreeMap();//key-Description

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT Id,ReviewType from tblLkReviews where status='Active'";
            preparedStatement = connection.prepareStatement(queryString);
            //preparedStatement = connection.prepareStatement(queryString);

            //preparedStatement.setString(1,department);

            //resultSet = preparedStatement.executeQuery();
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                reviewMap.put(resultSet.getString("Id"), resultSet.getString("ReviewType"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
        reviewMap = sortMapByValues(reviewMap);
        return reviewMap; // returning the object.
    } //closing the method.

    public String getEmpTitleByLoginId(String loginId) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String employeeTitle = "";


        try {

            queryString = "SELECT TitleTypeId FROM tblEmployee WHERE LoginId = '" + loginId + "'";
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                employeeTitle = resultSet.getString("TitleTypeId");
            }


            // }//Closing Cache Checking
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

        // System.out.println("I am Out of Data Source Provider");
        return employeeTitle; // returning the object.
    }

    public int getConsultantDetails(String email) throws ServiceLocatorException {

        //System.out.println("This is ConsultantDetails");
        StringBuffer sb = new StringBuffer();
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String activityDetails = "";
        String queryString;
        int activity = 0;
        int i = 0;
        //System.err.println(days+"Diff in Dyas...");
        try {

            queryString = "SELECT tblRecAttachments.Id,CellPhoneNo,SkillSet,AttachmentName,AttachmentLocation,CONCAT(tblRecConsultant.FName,'.',tblRecConsultant.LName) as name,TitleTypeId from tblRecConsultant "
                    + "LEFT OUTER JOIN tblRecAttachments "
                    + "ON(tblRecConsultant.Id=tblRecAttachments. ObjectId)  WHERE Email2 like '" + email + "' ";

            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);

            // System.out.println("Starting while loop");
            while (resultSet.next()) {
                i++;

                activity = resultSet.getInt("Id");
                //activity= Integer.parseInt(activityDetails);
                //  System.out.println(" Query String");
            }

        } catch (Exception sqe) {
            sqe.printStackTrace();
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
            } catch (SQLException sqle) {
            }
        }

        return activity;

    }

    public Map getIssuedRelatedTo() throws ServiceLocatorException {
        // StringBuffer projects  = new StringBuffer();
        Map issuerelatedMap = new TreeMap();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;


        //queryString = "SELECT distinct tblProjectContacts.ProjectId AS projectId,ProjectName FROM tblProjectContacts LEFT OUTER JOIN tblProjects ON(tblProjectContacts.ProjectId=tblProjects.Id) WHERE AccountId="+accountId;

        queryString = "SELECT Id,Descrition from tblLKIssueRelatedTo WHERE Status='Active'";


        // System.out.println("queryString-->"+queryString);
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                issuerelatedMap.put(resultSet.getString("Id"),
                        resultSet.getString("Descrition"));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ServiceLocatorException sle) {
            sle.printStackTrace();
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
                ex.printStackTrace();
            }
        }

        return issuerelatedMap;
    }

    public Map getIssuedCustomerRelatedTo() throws ServiceLocatorException {
        // StringBuffer projects  = new StringBuffer();
        Map issuerelatedMap1 = new TreeMap();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;


        //queryString = "SELECT distinct tblProjectContacts.ProjectId AS projectId,ProjectName FROM tblProjectContacts LEFT OUTER JOIN tblProjects ON(tblProjectContacts.ProjectId=tblProjects.Id) WHERE AccountId="+accountId;

        {
            queryString = "SELECT Id,Descrition FROM tblLKIssueRelatedTo WHERE STATUS='Active' AND Descrition IN ('Hubble','Projects')";
        }


        // System.out.println("queryString-->"+queryString);
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                issuerelatedMap1.put(resultSet.getString("Id"),
                        resultSet.getString("Descrition"));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ServiceLocatorException sle) {
            sle.printStackTrace();
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
                ex.printStackTrace();
            }
        }

        return issuerelatedMap1;
    }
    /*
     * Method : getRecruitmentEmployeeMap()
     * Date : 03/12/2015
     * Author : Santosh Kola
     */

    public Map getRecruitmentEmployeeMap() throws ServiceLocatorException {
        // StringBuffer projects  = new StringBuffer();
        Map recruitmentMap = new TreeMap();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;


        //queryString = "SELECT distinct tblProjectContacts.ProjectId AS projectId,ProjectName FROM tblProjectContacts LEFT OUTER JOIN tblProjects ON(tblProjectContacts.ProjectId=tblProjects.Id) WHERE AccountId="+accountId;

        {
            queryString = "SELECT LoginId,FName,MName,LName FROM tblEmployee JOIN tblEmpRoles ON tblEmployee.Id = tblEmpRoles.EmpId WHERE RoleId =6 AND tblEmployee.CurStatus = 'Active'";
        }
        //resultSet.getString("FName")+" "+resultSet.getString("MName")+"."+resultSet.getString("LName")

        // System.out.println("queryString-->"+queryString);
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                recruitmentMap.put(resultSet.getString("LoginId"),
                        resultSet.getString("FName") + " " + resultSet.getString("MName") + "." + resultSet.getString("LName"));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ServiceLocatorException sle) {
            sle.printStackTrace();
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
                ex.printStackTrace();
            }
        }

        return recruitmentMap;
    }

    public String getCampaignNameByCampaignId(int Id) throws ServiceLocatorException {

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;
        //int checkedValue = 0;
        String campaignName = null;
        try {


            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT CampaignName FROM tblCrmCampaign WHERE Id =" + Id;

            // System.out.println("getCampaignNameByCampaignId-->"+queryString);
            preparedStatement = connection.prepareStatement(queryString);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                campaignName = resultSet.getString("CampaignName");
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
        //System.out.println("the campaign name from dsdp=" +campaignName);
        return campaignName;
    }

    public Map getAllEmployees() throws ServiceLocatorException {

        Map employeeMap = new LinkedHashMap();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT LoginId,CONCAT(TRIM(FName),'.',TRIM(LName)) AS EmployeeName FROM"
                    + " tblEmployee WHERE CurStatus='Active' AND DeletedFlag != 1 ORDER BY EmployeeName";
            preparedStatement = connection.prepareStatement(queryString);

            //System.out.println("query-"+queryString);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employeeMap.put(resultSet.getString("LoginId"), resultSet.getString("EmployeeName"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return employeeMap; // returning the object.
    }
    /*Method for Check whether same account name is existed or not
     * Date : 05/12/2015
     * Author : Santosh Kola
     * 
     */

    public boolean checkAccountNameExist(String accountName) throws ServiceLocatorException {
        Statement statement = null;
        ResultSet resultSet = null;
        boolean flag = false;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT Id FROM tblCrmAccount WHERE NAME = '" + accountName + "'";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            if (resultSet.next()) {
                flag = true;
            } else {
                flag = false;
            }


        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return flag;
    }

    public Map getEmployeeNamesByOperationsContactId(String opsContactId) throws ServiceLocatorException {

        //   System.out.println("getEmployeeNamesByOperationsContactId method");
        Map employeeMap = new LinkedHashMap();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT LoginId,CONCAT(TRIM(FName),'.',TRIM(LName)) AS EmployeeName FROM"
                    + " tblEmployee WHERE CurStatus='Active' AND DeletedFlag != 1 AND OpsContactId = " + opsContactId + "  ORDER BY EmployeeName";
            preparedStatement = connection.prepareStatement(queryString);

            //System.out.println("query-"+queryString);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employeeMap.put(resultSet.getString("LoginId"), resultSet.getString("EmployeeName"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return employeeMap; // returning the object.
    }

    /* Method for Team map names list 
     * Date : 05/14/2015
     * author : santosh Kola
     */
    public String getTeamNamesList(Map teamMap) throws ServiceLocatorException {
        // List listObj = new ArrayList();
        String teamList = "";
        try {
            Iterator entries = teamMap.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry entry = (Map.Entry) entries.next();
                Object key = (Object) entry.getKey();
                Object value = (Object) entry.getValue();
                teamList = teamList + "'" + String.valueOf(value) + "',";
                //listObj.add(String.valueOf(key));

            }
            if (!"".equals(teamList)) {
                teamList = teamList.substring(0, teamList.length() - 1);
            }

        } catch (Exception sql) {
            throw new ServiceLocatorException(sql);
        } finally {
        }
        //System.out.println("teamList-->"+teamList);
        return teamList; // returning the object.
    }

    public boolean isRequirementAdmin(int empId) throws ServiceLocatorException {
        Statement statement = null;
        ResultSet resultSet = null;
        boolean flag = false;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        // String queryString = "SELECT Id FROM tblCrmAccount WHERE NAME = '"+accountName+"'";
        String queryString = "SELECT CONCAT(fName,' ',mName,'.',lName) AS Name FROM (tblEmployee JOIN tblEmpRoles ON(tblEmployee.Id = tblEmpRoles.EmpId)) WHERE tblEmpRoles.RoleId = 14 AND tblEmployee.CurStatus = 'Active' AND tblEmployee.Id =" + empId;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            if (resultSet.next()) {
                flag = true;
            } else {
                flag = false;
            }


        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return flag;
    }

    public String getStatusByEmailId(String emailId, String emptype) throws ServiceLocatorException {

        String result = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            if (emptype.equalsIgnoreCase("e")) {
                queryString = "SELECT CurStatus AS Status FROM tblEmployee WHERE Email1 LIKE '" + emailId + "'";
            } else {
                queryString = "SELECT ContactStatus AS Status FROM tblCrmContact WHERE Email1 LIKE '" + emailId + "'and iflag=1";
                //queryString = "SELECT LoginId,PASSWORD,CONCAT(FirstName,'.',LastName) AS NAME FROM tblCrmContactAlias WHERE Email LIKE '"+emailId+"'";  
                //queryString = "SELECT LoginId,PASSWORD,CONCAT(FirstName,'.',LastName) AS NAME FROM tblCrmContact WHERE Email1 LIKE '"+emailId+"'";  
            }
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getString("Status");
            } else {
                result = "nodata";
            }
            // System.out.println("dsdp--->"+result);            
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
        return result; // returning the object.
    }

    public Map getProjectDashBoardAccountList(int objectId) throws ServiceLocatorException {

        Map myAccounts = new TreeMap();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            //  System.out.println("objectId-->"+objectId);
            connection = ConnectionProvider.getInstance().getConnection();

            //queryString = " SELECT DISTINCT tblProjectContacts.AccountId AS accountId,tblCrmAccount.Name AS acoountName FROM tblProjectContacts LEFT OUTER JOIN tblCrmAccount ON(tblProjectContacts.AccountId=tblCrmAccount.Id) WHERE objectId=" + objectId+" AND tblCrmAccount.STATUS='Active' AND tblProjectContacts.STATUS='Active'";
            queryString = " SELECT DISTINCT tblProjectContacts.AccountId AS accountId,tblCrmAccount.NAME AS acoountName FROM tblProjectContacts "
                    + "LEFT OUTER JOIN tblCrmAccount ON(tblProjectContacts.AccountId=tblCrmAccount.Id) "
                    + "LEFT OUTER JOIN tblProjects ON (tblProjects.Id=tblProjectContacts.ProjectId) "
                    + "WHERE objectId=" + objectId + "  AND "
                    + "tblProjectContacts.STATUS='Active' AND tblProjects.STATUS='Active';";
            //   System.out.println("queryString-->"+queryString);
            preparedStatement = connection.prepareStatement(queryString);
            //preparedStatement = connection.prepareStatement(queryString);

            //preparedStatement.setString(1,department);

            //resultSet = preparedStatement.executeQuery();
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                myAccounts.put(resultSet.getInt("accountId"),
                        resultSet.getString("acoountName"));
                // myAccounts.add(resultSet.getString("acoountName"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
        //System.out.print("myAccounts-->"+myAccounts);
        return myAccounts; // returning the object.
    }

    public int getProjectActiveResources(int projectId) throws ServiceLocatorException {
        Statement statement = null;
        ResultSet resultSet = null;
        int totalResources = 0;
        Connection connection = ConnectionProvider.getInstance().getConnection();
       // String queryString = "SELECT COUNT(Id) As Total FROM tblProjectContacts WHERE STATUS = 'Active' AND ProjectId = " + projectId;
        // String queryString = "SELECT COUNT(tblProjectContacts.Id) AS Total FROM tblProjectContacts LEFT JOIN tblEmployee ON (tblProjectContacts.ObjectId=tblEmployee.Id) WHERE STATUS = 'Active' AND CurStatus='Active' AND ProjectId = " + projectId;
String queryString = "SELECT COUNT(tblProjectContacts.Id) AS Total FROM tblProjectContacts LEFT JOIN tblEmployee ON (tblProjectContacts.ObjectId=tblEmployee.Id) WHERE  ObjectType='E' AND STATUS = 'Active' AND CurStatus='Active' AND ProjectId = " + projectId;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            if (resultSet.next()) {
                totalResources = resultSet.getInt("Total");
            }


        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return totalResources;
    }
    //Perf Dashboard start

    public int getScore(String reportsTo, String startDate, String endDate) throws ServiceLocatorException {
        int score = 0;
        PreparedStatement preStmt = null;
        ArrayList alist = null;
        alist = new ArrayList();
        Connection connection = ConnectionProvider.getInstance().getConnection();

        try {
            //String queryString="SELECT tblEmpReview.UserId AS UserId,(SUM(IF(tblEmpReview.STATUS = 'Approved', TLRating, 0))+SUM(IF(tblEmpReview.STATUS = 'Approved', HRRating, 0))) AS Score ,tblEmployee.isManager ,tblEmployee.isTeamLead FROM (tblEmployee LEFT JOIN tblEmpReview ON ((tblEmployee.LoginId = CONVERT(tblEmpReview.UserId USING utf8)))) WHERE ((tblEmpReview.ReviewDate >= '"+DateUtility.getInstance().convertStringToMySQLDate(startDate)+"') AND (tblEmpReview.ReviewDate <= '"+DateUtility.getInstance().convertStringToMySQLDate(endDate)+"')) AND tblEmpReview.STATUS!='Opened' AND tblEmployee.reportsTo=? GROUP BY tblEmployee.Id ORDER BY Score DESC";
            String queryString = "SELECT tblEmpReview.UserId AS UserId,(SUM(IF((tblEmpReview.STATUS = 'Approved' AND tblEmpReview.HRStatus = 'Approved'), TLRating, 0)) +SUM(IF((tblEmpReview.STATUS = 'Approved' AND tblEmpReview.HRStatus = 'Approved'), HRRating, 0))) AS Score ,tblEmployee.isManager ,tblEmployee.isTeamLead FROM (tblEmployee LEFT JOIN tblEmpReview ON ((tblEmployee.LoginId = CONVERT(tblEmpReview.UserId USING utf8)))) WHERE ((tblEmpReview.ReviewDate >= '" + DateUtility.getInstance().convertStringToMySQLDate(startDate) + "') AND (tblEmpReview.ReviewDate <= '" + DateUtility.getInstance().convertStringToMySQLDate(endDate) + "')) AND tblEmpReview.STATUS!='Opened' AND tblEmployee.reportsTo=? GROUP BY tblEmployee.Id ORDER BY Score DESC";
            java.sql.PreparedStatement theStatement = connection.prepareStatement(queryString);


            alist = (ArrayList) getMyTeamMembersScore(reportsTo, theStatement);
            int size = alist.size();
            //System.out.println("size-->"+size);

            for (int i = 0; i < size; i++) {
                score += (Integer) alist.get(i);
            }
            // System.out.println("total score-->"+score);
            if (size > 0) {
                score = score / size;
            }
            //  System.out.println("final score---> "+score);

        } catch (SQLException ex) {
            throw new ServiceLocatorException(ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                } catch (SQLException sqlex) {
                    throw new ServiceLocatorException(sqlex);
                }
            }

        }


        return score;
    }

    public List getMyTeamMembersScore(String reportsTo, java.sql.PreparedStatement theStatement) throws ServiceLocatorException {


        String[] keys = new String[100];
        String key = null;
        ResultSet resultSet = null;
        List myTeamScore = new ArrayList();
        List tempMap = new ArrayList();
        int keyCnt = 0;
        try {

            //  Pass the Reportsto Parameter to the Prepared Statement Query
            theStatement.setString(1, reportsTo);

            //  Execute the Prepared Statement to Extract the List of People reporting to
            //  The Specified Person.
            resultSet = theStatement.executeQuery();

            while (resultSet.next()) {
                key = resultSet.getString("UserId");
                myTeamScore.add(resultSet.getInt("Score") / 2);
                // System.out.println("inner score--->"+resultSet.getInt("Score"));
                if (resultSet.getInt("isManager") == 1 || resultSet.getInt("isTeamLead") == 1) {
                    keys[keyCnt] = key;
                    keyCnt++;

                }
            }


            for (int i = 0; i < keyCnt; i++) {
                key = keys[i];

                tempMap = getMyTeamMembersScore(key, theStatement);

                //========================================================================
                // Iterate over the List of Team Members arrived at for this Person and
                //  add them to the myTeamMembersMap
                //========================================================================
                if (tempMap.size() > 0) {
                    //  Iterator tempIterator = tempMap.entrySet().iterator();
                    // while(tempIterator.hasNext()) {
                    for (Object val : tempMap) {
                        int value = (Integer) val;
                        // Map.Entry entry = (Map.Entry) tempIterator.next();
                        // key = entry.getKey().toString();
                        //value = entry.getValue().toString();
                        myTeamScore.add(value);
                        // entry = null;
                    }
                }
                // System.out.println("keyCnt value"+keyCnt);
                // System.out.println("i value"+i);
            }




        } catch (SQLException sqlex) {
            throw new ServiceLocatorException(sqlex);
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }


            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return myTeamScore;
    }

    //Perf Dashboard End
    // JobPost Changes
    public int isAuthorizedForJobPost(String loginId) throws ServiceLocatorException {
        int isAuthorized = 0;
        try {
            String authorizedUsersList[] = SecurityProperties.getProperty("JOB_POSTING_RECRUITMENT").split("\\,");
            for (int i = 0; i < authorizedUsersList.length; i++) {
                if (authorizedUsersList[i].equals(loginId)) {
                    isAuthorized = 1;
                    break;
                }
            }


        } catch (Exception exception) {
            throw new ServiceLocatorException(exception);
        }
        return isAuthorized;
    }

    public boolean isConsultantEmailExist(String email) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String consultEmail = "";
        boolean isEmailExist = false;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT Email2 FROM tblRecConsultant where Email2='" + email + "'";
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                isEmailExist = true;
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return isEmailExist;
    }

    public String isSourcingTeam(int empId) throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        String isSourcing = "NO";
        queryString = "SELECT CONCAT(fName,' ',mName,'.',lName) AS Name FROM (tblEmployee JOIN tblEmpRoles ON(tblEmployee.Id = tblEmpRoles.EmpId)) WHERE tblEmpRoles.RoleId = 11 AND tblEmployee.CurStatus = 'Active' AND tblEmployee.Id=" + empId;

        connection = ConnectionProvider.getInstance().getConnection();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            if (resultSet.next()) {
                isSourcing = "YES";
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return isSourcing; // returning the object.
    }

//-- Aditya No Due Changes 07/16/2015 
    public String getMaxDateForNoDuesSettlement(String empId) throws ServiceLocatorException, ParseException {
        //System.out.println("addDuesSettlement method-->" + empId);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Statement statement = null;
        String resultDate = "";
        boolean isUpdated = false;
        String queryString = "";
        String query = "";
        String maxDate = "";
        java.util.Date maxiDate;
        String pattern = "MM/dd/yyyy";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {

            connection = ConnectionProvider.getInstance().getConnection();
            query = "SELECT CONCAT(MONTH(MAX(ToDate)),'/',DAY(MAX(ToDate)),'/',YEAR(MAX(ToDate))) as MaxDate FROM tblEmpDuesDetails WHERE EmpId=" + empId;
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                maxDate = resultSet.getString("MaxDate");
            }
            //System.out.println("maxDate-->" + maxDate);
            if (maxDate == null || maxDate.length() == 0) {
            } else {


                //System.out.println("maxDate-->" + maxDate);

                maxiDate = format.parse(maxDate);
                //System.out.println("maxiDate-->" + maxiDate);
                resultDate = format.format(maxiDate);
            }
        } catch (SQLException se) {
            se.printStackTrace();
            throw new ServiceLocatorException(se);
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
            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }

        return resultDate;

    }

    public Map getAllEmployeesListForPayroll(String EmpId) throws ServiceLocatorException {

        Map employeeMap = new LinkedHashMap();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT LoginId,CONCAT(TRIM(FName),'.',TRIM(LName)) AS EmployeeName FROM"
                    + " tblEmployee WHERE CurStatus='Active' and Country='India' AND DeletedFlag != 1 ORDER BY EmployeeName";
            preparedStatement = connection.prepareStatement(queryString);

            //System.out.println("query-"+queryString);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employeeMap.put(resultSet.getString("LoginId"), resultSet.getString("EmployeeName"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return employeeMap; // returning the object.
    }

    public int getRoleIdByEmpId(int empId) throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection1 = null;
        Statement statement1 = null;
        ResultSet resultSet1 = null;
        String queryString = null;
        String queryString1 = null;
        int roleId = 0;//Description
        int isOperationContact = 0;
        String result = "";




        try {
            //  //System.out.println("In try-->"+contactId);

            queryString1 = "SELECT IsOperationContactTeam FROM tblEmployee WHERE Id = " + empId;

            connection1 = ConnectionProvider.getInstance().getConnection();
            statement1 = connection1.createStatement();
            resultSet1 = statement1.executeQuery(queryString1);
            while (resultSet1.next()) {
                isOperationContact = resultSet1.getInt("IsOperationContactTeam");
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
                if (resultSet1 != null) {
                    resultSet1.close();
                    resultSet1 = null;
                }

                if (statement1 != null) {
                    statement1.close();
                    statement1 = null;
                }

                if (connection1 != null) {
                    connection1.close();
                    connection1 = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }

        }


        return isOperationContact; // returning the object.
    } //closing the method.

    public String checkRemainderDatesForNoDues(String startDate, String empIds, String dept) throws ServiceLocatorException {
        Statement statement = null;
        ResultSet resultSet = null;
        String result = "";
        String result1 = "";
        Connection connection = ConnectionProvider.getInstance().getConnection();
        int temp = 0;
        try {
            //System.out.println("empIds uin dsdp---" + empIds);
            String employees[] = empIds.split(Pattern.quote("!@#"));
            if (Integer.parseInt(employees[0]) == 0 && dept.equals("All")) {
                String queryString = "SELECT EndDate,Flag FROM tblEmpNoDues WHERE StartDate <= '" + DateUtility.getInstance().getMysqlDate(startDate) + "' AND EndDate >= '" + DateUtility.getInstance().getMysqlDate(startDate) + "' and (EmpList LIKE 'All' or EmpList LIKE '%$$%') and  DepartmentId='All' ";
                //System.out.println("queryString dsdp-->" + queryString);
                statement = connection.createStatement();
                resultSet = statement.executeQuery(queryString);

                if (resultSet.next()) {
                    result = "All$%$Nodept";
                    result1 = result;
                }
            } else if (Integer.parseInt(employees[0]) == 0 && !("All").equals(dept)) {
                String queryString = "SELECT EndDate,Flag FROM tblEmpNoDues WHERE StartDate <= '" + DateUtility.getInstance().getMysqlDate(startDate) + "' AND EndDate >= '" + DateUtility.getInstance().getMysqlDate(startDate) + "' and (EmpList LIKE 'All') and (DepartmentId = '" + dept + "'  or DepartmentId='All')";
                //System.out.println("queryString dsdp-->" + queryString);
                statement = connection.createStatement();
                resultSet = statement.executeQuery(queryString);

                if (resultSet.next()) {
                    result = "All$%$" + dept;
                    result1 = result;
                }
            } else {
                for (int i = 0; i < employees.length; i++) {
//                    if (checkRemainderTablesFlag(employees[i])) {
//                        result = result + employees[i] + "@@";
//                        result1 = result;
//                        temp = 1;//formNotSubmitted
//                    } else {
                    String queryString = "SELECT EndDate,Flag FROM tblEmpNoDues WHERE StartDate <= '" + DateUtility.getInstance().getMysqlDate(startDate) + "' AND EndDate >= '" + DateUtility.getInstance().getMysqlDate(startDate) + "' and (EmpList LIKE '%$$" + employees[i] + "$$%' or EmpList LIKE 'All' ) and (DepartmentId = '" + dept + "' or DepartmentId='All')";
                    //System.out.println("queryString dsdp-->" + queryString);
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery(queryString);

                    while (resultSet.next()) {
                        //  count = resultSet.getInt("id");
                        result = result + employees[i] + "@@";
                        result1 = resultSet.getString("EndDate") + "#@#" + result;//formSubmitted
                        // break;
                        temp = 2;
                    }
                    //}
                }
            }

            if (temp == 1) {
                result1 = "formNotSubmitted$%$" + result1;
            } else if (temp == 2) {
                result1 = "formSubmitted$%$" + result1;
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        //System.out.println("result-->" + result1);
        return result1;
    }

    public String getemployeenameAndDepbyEmpId(String empId) throws ServiceLocatorException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String queryString = null;
        ResultSet resultSet = null;
        String employeeName = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT Id,FName,LName,MName,DepartmentId FROM tblEmployee WHERE curStatus='Active' and Id = ?";
            preparedStatement = connection.prepareStatement(queryString);

            preparedStatement.setString(1, empId);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employeeName = resultSet.getString("FName") + "." + resultSet.getString("MName")
                        + "." + resultSet.getString("LName") + "#$#" + resultSet.getString("DepartmentId");
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return employeeName;
    }

    public String checkForEmpRecordInNoDueTable(String empId, String deptId) throws ServiceLocatorException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String queryString = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement1 = null;
        Connection connection1 = null;
        ResultSet resultSet1 = null;
        //  boolean exist = false;
        String resultString = "";
        int noDueId = 0;
        try {
            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT `Id`,  `StartDate`, `EndDate`, `Comments` FROM `mirage`.`tblEmpNoDues` WHERE Flag = 0 AND ( EmpList LIKE '%$$" + empId + "$$%' or EmpList='All' ) and (DepartmentId = '" + deptId + "' or DepartmentId='All') LIMIT 0, 100;";
            //System.out.println("queryString--->" + queryString);
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                //System.out.println("in if");
                noDueId = resultSet.getInt("Id");
                String query = "SELECT 	COUNT(*) as count FROM `mirage`.`tblEmpDuesDetails` WHERE NoDueId = ? AND EmpId = ?";
                connection1 = ConnectionProvider.getInstance().getConnection();
                preparedStatement1 = connection1.prepareStatement(query);
                preparedStatement1.setInt(1, noDueId);
                preparedStatement1.setString(2, empId);
                resultSet1 = preparedStatement1.executeQuery();

                while (resultSet1.next()) {
                    if (resultSet1.getInt("count") == 0) {
                        String fromDate = resultSet.getString("StartDate");
                        String toDate = resultSet.getString("EndDate");
                        resultString = fromDate + "$$" + toDate + "$$" + noDueId;
                        //  exist = false;
                    }
                }

            }

        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
                if (resultSet1 != null) {
                    resultSet1.close();
                    resultSet1 = null;
                }

                if (preparedStatement1 != null) {
                    preparedStatement1.close();
                    preparedStatement1 = null;
                }

                if (connection1 != null) {
                    connection1.close();
                    connection1 = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        //System.out.println("  resultString -->" + resultString);
        return resultString;
    }

    public boolean checkRemainderTablesFlag(String empId) throws ServiceLocatorException {
        Statement statement = null;
        ResultSet resultSet = null;
        boolean flag = false;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT * FROM tblEmpNoDues WHERE Flag = 0 and   EmpList LIKE '%$$" + empId + "$$%'";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            if (resultSet.next()) {
                //  count = resultSet.getInt("id");
                flag = true;
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return flag;
    }

    public int isAuthorizedForNoDueRemainders(String loginId) throws ServiceLocatorException {
        int isAuthorized = 0;
        try {
            String authorizedUsersList[] = SecurityProperties.getProperty("No_Dues_Remainder").split("\\,");
            for (int i = 0; i < authorizedUsersList.length; i++) {
                if (authorizedUsersList[i].equals(loginId)) {
                    isAuthorized = 1;
                    break;
                }
            }


        } catch (Exception exception) {
            throw new ServiceLocatorException(exception);
        }
        return isAuthorized;
    }

    public int isAuthorizedForNoDueApprovals(String loginId) throws ServiceLocatorException {
        int isAuthorized = 0;
        try {
            String authorizedUsersList[] = SecurityProperties.getProperty("No_Dues_Approvers").split("\\,");
            for (int i = 0; i < authorizedUsersList.length; i++) {
                if (authorizedUsersList[i].equals(loginId)) {
                    isAuthorized = 1;
                    break;
                }
            }


        } catch (Exception exception) {
            throw new ServiceLocatorException(exception);
        }
        return isAuthorized;
    }

    public List getTimeList() {

        List timeList = new ArrayList();
        String hours = "";
        String minutes = "";

        for (int i = 1; i <= 12; i++) {
            for (int j = 0; j <= 50; j += 10) {
                if (i < 10) {
                    hours = "0" + i;
                } else {
                    hours = "" + i;
                }
                if (j == 0) {
                    minutes = "00";
                } else {
                    minutes = "" + j;
                }
                String time = hours + ":" + minutes + ":00";
                //System.out.println(time);
                timeList.add(time);
            }
        }

        return timeList;
    }

    public int getMaxProjectContactsID() throws ServiceLocatorException {
        Statement statement = null;
        ResultSet resultSet = null;
        int Id = 0;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT MAX(Id)as Id FROM tblProjectContacts";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            if (resultSet.next()) {
                Id = resultSet.getInt("Id");
            }

            //System.out.println("maxIdqueryString : "+queryString);
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return Id;
    }

    public String getEmpIDsBySkill(String skill) throws ServiceLocatorException {
        String empIds = "";
        String result = "";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
         String TABLE_EMP_STATE_HISTORY = Properties.getProperty("TABLE_EMP_STATE_HISTORY");
        String queryString = "SELECT EmpId FROM "+TABLE_EMP_STATE_HISTORY+" WHERE SkillSet LIKE ? AND EmpId>0";
        // System.out.println("queryString---"+queryString);
        // System.out.println("skill--"+skill);
        try {
            statement = connection.prepareStatement(queryString);
            statement.setString(1, "%" + skill + "%");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                // count = resultSet.getInt("count");
                empIds += resultSet.getString("EmpId") + ',';
            }
            if (empIds.length() > 0) {
                empIds = empIds.substring(0, empIds.length() - 1);
            }


            //   System.out.println(empIds);
        } catch (SQLException sqle) {

            throw new ServiceLocatorException(sqle);
        } catch (Exception e) {
            System.out.println(e);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }

        return empIds;
    }

    public int getBillableResourcesCount(String country) throws ServiceLocatorException {

        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = null;
        int billingCount = 0;
        queryString = "SELECT COUNT(DISTINCT ObjectId) as total,Billable,Country FROM tblProjectContacts LEFT JOIN tblEmployee ON(tblEmployee.Id=tblProjectContacts.ObjectId) WHERE (Country='" + country + "') AND ObjectType='E' AND Billable=1 AND tblProjectContacts.STATUS='Active' GROUP BY Country ORDER BY Country DESC";


        //String queryString ="SELECT LoginId FROM tblEmployee WHERE CONCAT(fName,' ',mName,'.',lName) ='"+empName+"'";
        // System.out.println("Query-->"+queryString);

        try {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {

                billingCount = resultSet.getInt("total");
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return billingCount;
    }

    public Map getManagerEmailsOfRecruitment(String requirementCountry) throws ServiceLocatorException {

        //List managersList = new ArrayList();
        Map managersMap = new HashMap();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String queryString = null;
        ResultSet resultSet = null;

        try {//resultSet.getString("FName")+" "+resultSet.getString("MName")+"."+resultSet.getString("LName")
            connection = ConnectionProvider.getInstance().getConnection();
            //queryString = "select Email1,FName,MName,LName from tblEmployee where DepartmentId like 'recruiting' and IsManager = 1 and CurStatus='Active' AND (WorkingCountry='"+requirementCountry+"' OR WorkingCountry='All')";
            queryString = "select Email1,FName,MName,LName from tblEmployee where DepartmentId like 'recruiting' and IsManager = 1 and CurStatus='Active'";
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                managersMap.put(resultSet.getString("Email1"), resultSet.getString("FName") + " " + resultSet.getString("MName") + "." + resultSet.getString("LName"));
            }
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
        return managersMap;
    }

    public int getRequirementMaxId() throws ServiceLocatorException {
        int maxId = 0;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT MAX(Id) AS Id FROM tblRecRequirement";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                maxId = resultSet.getInt("Id");
            }


        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return maxId;
    }

    public Map getRequirementAdminMap() throws ServiceLocatorException {
        Statement statement = null;
        ResultSet resultSet = null;
        boolean flag = false;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        Map requirementAdminMap = new HashMap();
        // String queryString = "SELECT Id FROM tblCrmAccount WHERE NAME = '"+accountName+"'";
        String queryString = "SELECT CONCAT(FName,' ',MName,'.',LName) AS Name,LoginId FROM (tblEmployee JOIN tblEmpRoles ON(tblEmployee.Id = tblEmpRoles.EmpId)) WHERE tblEmpRoles.RoleId = 14 AND tblEmployee.CurStatus = 'Active'";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                requirementAdminMap.put(resultSet.getString("LoginId"), resultSet.getString("Name"));
            }



        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return requirementAdminMap;
    }

    public Map getRequirementAssignToMap(String id) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String recruiter1 = "";
        String recruiter2 = "";
        String preSales1 = "";
        String preSales2 = "";
        String assignedBy = "";
        Map assignedMap = new HashMap();
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            //  queryString = "SELECT AssignedBy FROM tblRecRequirement WHERE Id = "+Integer.parseInt(id);   

            queryString = "SELECT AssignedTo,SecondaryRecruiter,AssignToTechLead,SecondaryTechLead,AssignedBy,AssignedDate FROM tblRecRequirement WHERE Id = " + Integer.parseInt(id);
            // System.out.println("queryString-->"+queryString);
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                recruiter1 = resultSet.getString("AssignedTo");

                if (recruiter1 != null && !"".equals(recruiter1.trim())) {
                    assignedMap.put("Recruiter1", recruiter1);
                } else {
                    assignedMap.put("Recruiter1", "");
                }

                recruiter2 = resultSet.getString("SecondaryRecruiter");
                if (recruiter2 != null && !"".equals(recruiter2.trim())) {
                    assignedMap.put("Recruiter2", recruiter2);
                } else {
                    assignedMap.put("Recruiter2", "");
                }

                preSales1 = resultSet.getString("AssignToTechLead");
                if (preSales1 != null && !"".equals(preSales1.trim())) {
                    assignedMap.put("PreSales1", preSales1);
                } else {
                    assignedMap.put("PreSales1", "");
                }

                preSales2 = resultSet.getString("SecondaryTechLead");
                if (preSales2 != null && !"".equals(preSales2.trim())) {
                    assignedMap.put("PreSales2", preSales2);
                } else {
                    assignedMap.put("PreSales2", "");
                }
                assignedBy = resultSet.getString("AssignedBy");
                if (assignedBy != null && !"".equals(assignedBy.trim())) {
                    assignedMap.put("AssignedBy", assignedBy);
                } else {
                    assignedMap.put("AssignedBy", "");
                }
                assignedMap.put("AssignedDate", resultSet.getTimestamp("AssignedDate"));
                //System.out.println("Assigned date in DSDP-->"+assignedDate);
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }

        return assignedMap;
    }

    public String getAssignedTo(String id) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String assignedTo = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT AssignedTo FROM tblRecRequirement WHERE Id = " + Integer.parseInt(id);
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                assignedTo = resultSet.getString("AssignedTo");
                //System.out.println("Assigned date in DSDP-->"+assignedDate);
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }

        return assignedTo;
    }

    public String getLoginIdByEmpFullName(String empName) throws ServiceLocatorException {
        String loginId = "";
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        //System.out.println("Emp Full Names -->"+empName);
        String queryString = "SELECT loginId FROM tblEmployee WHERE CONCAT(fName,' ',mName,'.',lName) ='" + empName + "'";
        //System.out.println("Query-->"+queryString);

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                loginId = resultSet.getString("loginId");
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        //  System.out.println("loginId--"+loginId);
        return loginId;
    }

    public HashSet getTopLevelReportsLoginIds(String loginId) throws ServiceLocatorException {
        HashSet<String> hashSet = new HashSet<String>();

        // System.out.println("Query-->"+queryString);

        try {
            String reportsTo = getReportsTo(loginId);
            // System.out.println("reportsTo----------------------------"+reportsTo);
            // System.out.println("hash set");
            String reports[] = reportsTo.split(";");
            for (int i = 0; i < reports.length && i <= 1; i++) {
                hashSet.add(reports[i]);
                //System.out.println(reports[i]);
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
        }
        return hashSet;
    }

    public String getRequirementAssignedBy(String id) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String assignedBy = null;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT AssignedBy FROM tblRecRequirement WHERE Id = " + Integer.parseInt(id);
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                assignedBy = resultSet.getString("AssignedBy");
                //System.out.println("Assigned date in DSDP-->"+assignedDate);
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }

        return assignedBy;
    }

    public List getConsultantStatusList() throws ServiceLocatorException {
        int maxId = 0;
        List statusList = new ArrayList();
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT Id,NAME FROM tblLKConsultantStatus ORDER BY Id ASC";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                String name = resultSet.getString("NAME");
                statusList.add(name);
            }


        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return statusList;
    }

    public String getTblRecId() throws ServiceLocatorException {
        String maxId = "";
        ArrayList list = new ArrayList();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT MAX(Id) AS maxId FROM tblRec";
        //  System.out.println("queryString---"+queryString);
        try {
            statement = connection.prepareStatement(queryString);


            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                maxId = resultSet.getString("maxId");

            }




        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return maxId;
    }

    public String getRecConsultantStatus(int recConsultantId) throws ServiceLocatorException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String queryString = null;
        ResultSet resultSet = null;
        String consultantStatus = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT STATUS FROM tblRec WHERE Id=?";
            preparedStatement = connection.prepareStatement(queryString);

            preparedStatement.setInt(1, recConsultantId);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                consultantStatus = resultSet.getString("STATUS");

            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return consultantStatus;
    }

    public int getEmpIdByName(String name) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";
        int empId = 0;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            //queryString = "select  concat(FirstName,'.',LastName) as Name from tblCatagory where CatagoryId = '" + empName + "'";;
            queryString = "SELECT tblEmployee.Id,CONCAT(fName,' ',mName,'.',lName) AS NAME FROM (tblEmployee JOIN tblEmpRoles ON(tblEmployee.Id = tblEmpRoles.EmpId)) WHERE tblEmpRoles.RoleId = 10 AND tblEmployee.CurStatus = 'Active' AND CONCAT(fName,' ',mName,'.',lName) ='" + name + "' ORDER BY NAME";
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                empId = resultSet.getInt("Id");
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return empId;
    }

    public Map getPresalesMap() throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        Map preSalesMap = new HashMap();//Description
        queryString = "SELECT tblEmployee.Id,CONCAT(fName,' ',mName,'.',lName) AS NAME FROM (tblEmployee JOIN tblEmpRoles ON(tblEmployee.Id = tblEmpRoles.EmpId)) WHERE tblEmpRoles.RoleId = 10 AND tblEmployee.CurStatus = 'Active' ORDER BY NAME";

        connection = ConnectionProvider.getInstance().getConnection();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                preSalesMap.put(resultSet.getInt("Id"), resultSet.getString("Name"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return preSalesMap; // returning the object.
    }

    public String[] getSkillSet(int id) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String skillSet[] = new String[10];
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            //queryString = "select  concat(FirstName,'.',LastName) as Name from tblCatagory where CatagoryId = '" + empName + "'";;
            queryString = "SELECT Skills,SecondarySkills  FROM tblRecRequirement WHERE Id=" + id;
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            String skills = "";
            while (resultSet.next()) {
                if (resultSet.getString("Skills") != null) {
                    skills = resultSet.getString("Skills");
                }
                if (resultSet.getString("SecondarySkills") != null) {
                    skills += "," + resultSet.getString("SecondarySkills");
                }
            }
            LinkedHashSet<String> al = new LinkedHashSet<String>();


            String tempSkils[] = skills.split(Pattern.quote(","));
            for (int i = 0; i < tempSkils.length; i++) {
                al.add(tempSkils[i]);
            }
            Iterator<String> itr = al.iterator();
            for (int i = 0; i < 10; i++) {
                if (itr.hasNext()) {
                    skillSet[i] = itr.next();
                } else {
                    skillSet[i] = "";
                }
                // System.out.println("skillSet["+i+"]-->"+skillSet[i]);
            }

        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return skillSet;
    }

    public int getReviewIdbyEmail(int preSales1, int preSales2, int conId) throws ServiceLocatorException {
        Statement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;
        int reviewId = 0;

        try {


            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "select max(Id) as ReviewId from tblRecConsultantActivity where ConsultantId= " + conId + " and (Presales1=" + preSales1 + " OR Presales2=" + preSales2 + ")";

            //System.out.println("getdateLastActivityById-->"+queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            if (resultSet.next()) {
                reviewId = resultSet.getInt("ReviewId");

            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }

        }


        return reviewId;
    }

    public String getFullNameBYLoginId(String loginId) throws ServiceLocatorException {
        String NAME = "";
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT CONCAT (FName,'.',LName) AS NAME FROM tblEmployee where loginId = '" + loginId + "'";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                NAME = resultSet.getString("NAME");
            }


        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }

        return NAME;
    }

    public String getRecruitersPreSalesByReviewId(int id) throws ServiceLocatorException {
        String result = "";
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        //System.out.println("Emp Full Names -->"+empName);
        String queryString = "SELECT AssignedTo,SecondaryRecruiter,Presales1,Presales2 FROM tblRecConsultantActivity LEFT JOIN tblRecRequirement ON(tblRecConsultantActivity.RequirementId=tblRecRequirement.Id) WHERE tblRecConsultantActivity.Id=" + id;
        //System.out.println("Query-->"+queryString);

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                result = resultSet.getString("AssignedTo") + "#";
                result += resultSet.getString("SecondaryRecruiter") + "#";
                result += resultSet.getString("Presales1") + "#";
                result += resultSet.getString("Presales2");
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        //System.out.println("result--"+result);
        return result;
    }

    public HashMap getSkillsAndRatings(int id) throws ServiceLocatorException {
        HashMap skillSetMap = new LinkedHashMap();
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT Skill1,Rating1,Skill2,Rating2,Skill3,Rating3,Skill4,Rating4,Skill5,Rating5,Skill6,Rating6,Skill7,Rating7,Skill8,Rating8,Skill9,Rating9,Skill10,Rating10,TechnicalSkills,DomainSkills,CommunicationSkills FROM tblRecConsultantActivity where id = " + id;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                for (int i = 1; i <= 10; i++) {
                    String skill = "Skill" + i;
                    String rating = "Rating" + i;
                    if (!"".equals(resultSet.getString(skill))) {

                        skillSetMap.put(resultSet.getString(skill), resultSet.getInt(rating));
                    }

                }
                skillSetMap.put("Technical Skills", resultSet.getInt("TechnicalSkills"));
                skillSetMap.put("Domain Skills", resultSet.getInt("DomainSkills"));
                skillSetMap.put("Communication Skills", resultSet.getInt("CommunicationSkills"));

            }


        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }

        return skillSetMap;
    }

    public Map getClientMap() throws ServiceLocatorException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String queryString = null;
        ResultSet resultSet = null;
        String consultantStatus = "";
        Map clientMap = new LinkedHashMap();
        try {
            connection = ConnectionProvider.getInstance().getConnection();

          //  queryString = "SELECT tblCrmAccount.Id AS Id,tblCrmAccount.NAME AS CustomerName FROM tblCrmAccount  LEFT JOIN tblRecRequirement ON tblCrmAccount.Id = tblRecRequirement.CustomerId ORDER BY CustomerName";
            queryString = "SELECT DISTINCT tblCrmAccount.Id AS Id,tblCrmAccount.NAME AS CustomerName FROM tblCrmAccount INNER JOIN tblRecRequirement ON tblCrmAccount.Id = tblRecRequirement.CustomerId ORDER BY CustomerName";
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                clientMap.put(resultSet.getInt("Id"), resultSet.getString("CustomerName"));

            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return clientMap;
    }

    public Map getTeamLeadMapByDept(String department) throws ServiceLocatorException {

        //    List employeeMap = new LinkedList();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;
        Map employeeMap = new LinkedHashMap();
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT LoginId,FName,LName,MName FROM tblEmployee WHERE curstatus='Active' and DepartmentId = ? AND (IsTeamLead=1 OR IsManager=1) ORDER BY FName";
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, department);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employeeMap.put(resultSet.getString("LoginId"), resultSet.getString("FName") + " " + resultSet.getString("MName")
                        + "." + resultSet.getString("LName"));
            }

        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return employeeMap; // returning the object.
    }

    public String getRecConsultantCountByStatus(int requirementId, String status) throws ServiceLocatorException {

        //    List employeeMap = new LinkedList();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        String count = " ";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT COUNT(Id) AS total FROM tblRec WHERE RequirementId = " + requirementId + " AND STATUS = '" + status + "'";
            preparedStatement = connection.prepareStatement(queryString);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                if (resultSet.getInt("total") != 0) {
                    count = String.valueOf(resultSet.getInt("total"));
                }
            }
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return count; // returning the object.
    }

    public List getRoleIdAndName(String empId, String DepartmentId) throws ServiceLocatorException {
        String result = "";
        ArrayList list = new ArrayList();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT tblEmpRoles.RoleId,tblLKRoles.Description,departmentId FROM tblEmpRoles LEFT JOIN tblLKRoles ON (tblEmpRoles.RoleId=tblLKRoles.ID) LEFT JOIN tblEmployee ON(tblEmployee.Id=tblEmpRoles.EmpId) WHERE EmpId=" + empId + " AND (Description='Pre-Sales' OR Description='Recruitment' OR Description='Sales' OR Description='Admin')  ORDER BY Description='Admin',Description='Sales',Description='Recruitment',Description='Pre-Sales'";
        //System.out.println("queryString---"+queryString);
        try {
            statement = connection.prepareStatement(queryString);


            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                //System.out.println(resultSet.getString("departmentId")+" "+resultSet.getString("Description"));
                if ((resultSet.getString("departmentId").equals("SSG") || resultSet.getString("departmentId").equals("GDC")) && resultSet.getString("Description").equals("Pre-Sales")) {
                    list.add(0, resultSet.getString("RoleId"));
                    list.add(1, resultSet.getString("Description"));
                    break;
                } else if (resultSet.getString("departmentId").equals("Recruiting") && resultSet.getString("Description").equals("Recruitment")) {
                    list.add(0, resultSet.getString("RoleId"));
                    list.add(1, resultSet.getString("Description"));
                    break;
                } else if (resultSet.getString("departmentId").equals("Sales") && resultSet.getString("Description").equals("Sales")) {
                    list.add(0, resultSet.getString("RoleId"));
                    list.add(1, resultSet.getString("Description"));
                    break;
                } else if (resultSet.getString("Description").equals("Admin")) {
                    list.add(0, resultSet.getString("RoleId"));
                    list.add(1, resultSet.getString("Description"));
                    break;
                }

            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return list;
    }

    public Map getPracticesalesMap() throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        Map preSalesMap = new HashMap();//Description
        queryString = "SELECT tblEmployee.Id,CONCAT(fName,' ',mName,'.',lName) AS NAME FROM (tblEmployee JOIN tblEmpRoles ON(tblEmployee.Id = tblEmpRoles.EmpId)) WHERE tblEmpRoles.RoleId = 15 AND tblEmployee.CurStatus = 'Active' ORDER BY NAME";

        connection = ConnectionProvider.getInstance().getConnection();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                preSalesMap.put(resultSet.getInt("Id"), resultSet.getString("Name"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return preSalesMap; // returning the object.
    }
    //Appreciation email methods start

    public Map getEmpEmails() throws ServiceLocatorException {

        //List prjoectList = new ArrayList();
        Map empEmailMap = new TreeMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        //queryString = "select Id,ProjectName from tblProjects Where ProjectName != '' ORDER BY ProjectName";
        //  queryString = "SELECT DISTINCT tblProjects.Id as Id,tblProjects.ProjectName as ProjectName FROM tblProjectContacts,tblProjects WHERE tblProjects.Id=tblProjectContacts.ProjectId ORDER BY Id";
        queryString = "SELECT LoginId,Email1 FROM tblEmployee WHERE CurStatus='Active'";
        connection = ConnectionProvider.getInstance().getConnection();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                //  prjNameAndId.put(resultSet.getInt("Id"),resultSet.getString("ProjectName"));
                // prjoectList.add(resultSet.getString("ProjectName"));
                //prjoectMap.put(resultSet.getInt("Id"),resultSet.getString("ProjectName"));
                empEmailMap.put(resultSet.getString("LoginId"), resultSet.getString("Email1"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }

        return empEmailMap;
    }

    public String getEmailIdForLoginId(String LoginId) throws ServiceLocatorException {

        //  System.out.println("LoginId"+LoginId);
        //String projectType = "";
        String emailId = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        //queryString = "SELECT concat(FirstName,'.',LastName) as Name, LoginId FROM tblCatagory";
        String queryString = "Select Email1 from tblEmployee where LoginId='" + LoginId + "'";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                emailId = resultSet.getString("Email1");
                // int temp = emailId.indexOf("@");
                // emailId = emailId.substring(0,temp);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return emailId;
    }

    public String getDesignation(String loginId) throws ServiceLocatorException {

        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String queryString = null;
        String designation = null;
        String designationAct = null;
        queryString = "SELECT TitleTypeId FROM tblEmployee WHERE LoginId='" + loginId + "'";

        try {
            //System.out.println("conatcID------->"+contatId);
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                designationAct = resultSet.getString("TitleTypeId");

            }//Closing while loop

        } catch (SQLException sqle) {
            throw new ServiceLocatorException(sqle);
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
            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }

        return designationAct;
    }

    public String userName(String loginId) throws ServiceLocatorException {
        String userName = "";
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = null;
        queryString = "SELECT CONCAT(TRIM(FName),'.',TRIM(LName)) AS Name1 FROM tblEmployee where loginId = '" + loginId + "'";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                userName = resultSet.getString("Name1");
            }


        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }

        return userName;
    }

    public String getHierarchyEmail(String loginId) throws ServiceLocatorException {

        //System.out.println("objectId-->"+objectId);
        Connection connection = null;
        CallableStatement callableStatement = null;

        connection = ConnectionProvider.getInstance().getConnection();


        String hierarchyEmail = "";
        try {

            callableStatement = connection.prepareCall("{call spGetAppreciationEmails(?,?)}");
            callableStatement.setString(1, loginId);
            callableStatement.registerOutParameter(2, Types.VARCHAR);
            callableStatement.execute();
            hierarchyEmail = callableStatement.getString(2);
            //  System.out.println("hierarchyEmail"+hierarchyEmail);
            // }//Closing Cache Checking
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {

                if (callableStatement != null) {
                    callableStatement.close();
                    callableStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }

        // System.out.println("I am Out of Data Source Provider");
        return hierarchyEmail;  // returning the object.
    }

    public Map getEmployeeNamesByLoginId(String workingCountry) throws ServiceLocatorException {


        Map employeeMap = new LinkedHashMap();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT LoginId,CONCAT(TRIM(FName),'.',TRIM(LName)) AS EmployeeName,isManager,isTeamLead FROM"
                    + " tblEmployee WHERE CurStatus='Active' AND DeletedFlag != 1 AND WorkingCountry LIKE '" + workingCountry + "'  ORDER BY EmployeeName";
            preparedStatement = connection.prepareStatement(queryString);

            //System.out.println("query-"+queryString);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employeeMap.put(resultSet.getString("LoginId"), resultSet.getString("EmployeeName"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return employeeMap; // returning the object.
    }

    //appreciation email methods ends 
    public List getAccommodationList() throws ServiceLocatorException {
        List accommodationList = new ArrayList();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        try {
            queryString = "SELECT Id,NAME FROM tblLKAccommodation WHERE STATUS='Active' order by Name";
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                accommodationList.add(resultSet.getString("NAME"));
            }
            Collections.sort(accommodationList);
        } catch (SQLException sqlex) {
            throw new ServiceLocatorException(sqlex);
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }

        }
        return accommodationList;
    }

    public List getTransportLocationList() throws ServiceLocatorException {
        List transportLocationList = new ArrayList();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        try {
            queryString = "SELECT Id,NAME FROM tblLKTransportLocation WHERE STATUS='Active' order by Name";
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                transportLocationList.add(resultSet.getString("NAME"));
            }
            Collections.sort(transportLocationList);
        } catch (SQLException sqlex) {
            throw new ServiceLocatorException(sqlex);
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }

        }
        return transportLocationList;
    }

    public List getPracticeNamesList(int departmentId, int type) throws ServiceLocatorException {

        List crmPraticeNamesList = new ArrayList();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;

        queryString = "select Description from tblLKPractice where  DepartmentId = " + departmentId + " AND TYPE = " + type + " order by Description";
        connection = ConnectionProvider.getInstance().getConnection();


        try {
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                crmPraticeNamesList.add(resultSet.getString("Description"));
            }
            Collections.sort(crmPraticeNamesList);

        } catch (SQLException sqlex) {
            throw new ServiceLocatorException(sqlex);
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
        return crmPraticeNamesList;
    }

    public Map getEmployeeByDepartmentAndPractice(String department, String practice) throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        Map preSalesMap = new HashMap();//Description
        queryString = "SELECT tblEmployee.Id,CONCAT(fName,' ',mName,'.',lName) AS NAME FROM tblEmployee  WHERE DepartmentId='" + department + "' and PracticeId='" + practice + "' and tblEmployee.CurStatus = 'Active' ORDER BY NAME";

        connection = ConnectionProvider.getInstance().getConnection();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                preSalesMap.put(resultSet.getInt("Id"), resultSet.getString("Name"));
            }

            preSalesMap = DataUtility.getInstance().getMapSortByValue(preSalesMap);
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return preSalesMap; // returning the object.
    }

    public String getMapKeys(Map map, String delimiter) {
        Iterator tempIterator = map.entrySet().iterator();
        String keys = "";
        String key = "";
        int cnt = 0;

        while (tempIterator.hasNext()) {
            Map.Entry entry = (Map.Entry) tempIterator.next();
            key = entry.getKey().toString();
            entry = null;

            //  Add the Delimiter to the Keys Field for the Second Key onwards
            if (cnt > 0) {
                keys = keys + delimiter;
            }

            keys = keys + key;
            //System.out.println("keyssss------"+ keys);
            cnt++;
        }
        tempIterator = null;
        return (keys);
    }

    public List getOpportunityStateList() throws ServiceLocatorException {
        List opportunityStateList = new ArrayList();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        try {
            queryString = "SELECT Id,NAME FROM tblLKOpportunityState WHERE STATUS='Active' order by Id";
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                opportunityStateList.add(resultSet.getString("NAME"));
            }
            //  Collections.sort(opportunityStateList);
        } catch (SQLException sqlex) {
            throw new ServiceLocatorException(sqlex);
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }

        }
        return opportunityStateList;
    }

    public Map getPresalesListByLoginId() throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        Map preSalesMap = new HashMap();//Description
        queryString = "SELECT tblEmployee.LoginId,CONCAT(fName,' ',mName,'.',lName) AS NAME FROM (tblEmployee JOIN tblEmpRoles ON(tblEmployee.Id = tblEmpRoles.EmpId)) WHERE tblEmpRoles.RoleId = 10 AND tblEmployee.CurStatus = 'Active' ORDER BY NAME";

        connection = ConnectionProvider.getInstance().getConnection();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                preSalesMap.put(resultSet.getString("LoginId"), resultSet.getString("Name"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return preSalesMap; // returning the object.
    }

    public Map getAllSalesLeads() throws ServiceLocatorException {
        Map salesTeamExceptAccountTeamMap = new TreeMap();
        salesTeamExceptAccountTeamMap.clear();

        ResultSet resultSet = null;
        Connection connection = null;
        Statement statement = null;

        connection = ConnectionProvider.getInstance().getConnection();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT LoginId,CONCAT(TRIM(FName),'.',TRIM(LName)) AS EmployeeName FROM tblEmployee WHERE DepartmentId='Sales' AND CurStatus='Active' AND (IsManager=1 OR IsTeamLead=1) ORDER BY FName");
            while (resultSet.next()) {
                salesTeamExceptAccountTeamMap.put(resultSet.getString("LoginId"), resultSet.getString("EmployeeName"));
            }


        } catch (SQLException ex) {
            throw new ServiceLocatorException(ex);

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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }

        return salesTeamExceptAccountTeamMap;
    }

    public Map getPmoMap() throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        Map pmomanagerMap = new HashMap();//Description
        queryString = "SELECT tblEmployee.LoginId,CONCAT(fName,' ',mName,'.',lName) AS NAME FROM tblEmployee WHERE PracticeId='PMO' AND CurStatus = 'Active'";

        connection = ConnectionProvider.getInstance().getConnection();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                pmomanagerMap.put(resultSet.getString("LoginId"), resultSet.getString("Name"));
            }
pmomanagerMap = sortByValue(pmomanagerMap);

        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return pmomanagerMap; // returning the object.
    }

    public Map getProjectRiskAssignedTo(int accountId, int projectId) throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        Map pmomanagerMap = new HashMap();//Description
        queryString = "SELECT ResourceName,Email FROM tblProjectContacts WHERE ObjectType='E' AND AccountId=" + accountId + " AND ProjectId=" + projectId;

        connection = ConnectionProvider.getInstance().getConnection();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                pmomanagerMap.put(getLoginIdByEmailId(resultSet.getString("Email")), resultSet.getString("ResourceName"));
            }
            pmomanagerMap = DataUtility.getInstance().getMapSortByValue(pmomanagerMap);

        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return pmomanagerMap; // returning the object.
    }

    public String getLoginIdByEmailId(String email) throws ServiceLocatorException {

        String loginId = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        //queryString = "SELECT concat(FirstName,'.',LastName) as Name, LoginId FROM tblCatagory";
        String queryString = "Select LoginId from tblEmployee where Email1 LIKE '" + email + "' and curStatus='Active'";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                loginId = resultSet.getString("LoginId");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return loginId;
    }

    public String getPrimaryProjectNameByEmpId(int empId) throws ServiceLocatorException {
        String project = "";
        ResultSet resultSet = null;
        Connection connection = null;
        Statement statement = null;
        String queryString = null;
        connection = ConnectionProvider.getInstance().getConnection();

        try {
            statement = connection.createStatement();
            //queryString = "Select Id,ProjectName FROM tblProjects WHERE Id in (select ProjectId from vwProjectTeamEmployees where EmpId="+empId+" ORDER BY ProjectName DESC)";
            queryString = "SELECT tblProjects.Id FROM tblProjects LEFT OUTER JOIN tblProjectContacts ON (tblProjects.Id=tblProjectContacts.ProjectId) WHERE tblProjectContacts.Status='Active'  AND ResourceType=1 and tblProjectContacts.ObjectId=" + empId;
            resultSet = statement.executeQuery(queryString);
            if (resultSet.next()) {
                project = resultSet.getString("Id");

            }
        } catch (SQLException ex) {
            throw new ServiceLocatorException(ex);
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return project;
    }

    //methid to get the leads and managers under loginId
    public Map getMyTeamList(String LoginId) throws ServiceLocatorException {

        Map MyTeamMap = new TreeMap();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT LoginId,CONCAT(FName,'.',LName) AS employeeName FROM tblEmployee WHERE curStatus='Active' and ReportsTo = ? And (IsTeamLead=1 OR IsManager=1)  ORDER BY employeeName";
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, LoginId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                MyTeamMap.put(resultSet.getString("LoginId"), resultSet.getString("employeeName"));
            }

            //  System.out.println("Team "+MyTeamMap);
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return MyTeamMap; // returning the object.
    } //closing the method

//method to get the map with empId and EmpName us reportsTo,departmentId
    public Map getMyTeamMembersEmpId(String reportsTo, String departmentId) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;
        connection = ConnectionProvider.getInstance().getConnection();
        Map myTeamMembers = new TreeMap();

        try {
//            if (CacheManager.getCache().containsKey(reportsTo + "Team")) {
//                myTeamMembers = (Map)CacheManager.getCache().get(reportsTo + "Team");
//            } else {
            if (reportsTo.equalsIgnoreCase(Properties.getProperty("CEO"))) {
                queryString = "SELECT Id,FName,MName,LName,IsManager,IsTeamLead FROM tblEmployee WHERE  DepartmentId='Sales' and  ReportsTo= ? AND CurStatus='Active' AND DeletedFlag !=1 ORDER BY FName";
            } //new 
            else if (reportsTo.equalsIgnoreCase(Properties.getProperty("ExecutiveBoard"))) {
                queryString = "SELECT Id,FName,MName,LName,IsManager,IsTeamLead FROM tblEmployee WHERE ReportsTo= ? AND CurStatus='Active' AND DeletedFlag !=1 ORDER BY FName";
            } else {
                if ("GDC".equals(departmentId) || "SSG".equals(departmentId) || "Marketing".equals(departmentId)) {
                    queryString = "SELECT Id,FName,MName,LName,IsManager,IsTeamLead FROM tblEmployee WHERE  DepartmentId IN ('GDC','SSG','Marketing') and ReportsTo= ? AND CurStatus='Active' AND DeletedFlag !=1 ORDER BY FName";
                } else {
                    queryString = "SELECT Id,FName,MName,LName,IsManager,IsTeamLead FROM tblEmployee WHERE  DepartmentId='" + departmentId + "' and ReportsTo= ? AND CurStatus='Active' AND DeletedFlag !=1 ORDER BY FName";
                }
            }

            preparedStatement = connection.prepareStatement(queryString);
            myTeamMembers = getMyTeamMembersEmpIdUpTo(reportsTo, preparedStatement);
            CacheManager.getCache().put(reportsTo + "EmpIdTeam", myTeamMembers);
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return myTeamMembers; // returning the object.
    }

    public Map getMyTeamMembersEmpIdUpTo(String reportsTo, java.sql.PreparedStatement theStatement) throws ServiceLocatorException {
        ResultSet resultSet = null;
        Map myTeamManagersMap = new TreeMap();
        Map tempMap = new TreeMap();
        String[] keys = new String[100];
        int keyCnt = 0;
        String key = null;
        String value = null;

        try {
            theStatement.setString(1, reportsTo);
            resultSet = theStatement.executeQuery();

            while (resultSet.next()) {
                key = resultSet.getString("Id");
                value = resultSet.getString("FName") + " " + resultSet.getString("MName") + "." + resultSet.getString("LName");
                myTeamManagersMap.put(key, value);
                if ((resultSet.getBoolean("IsManager")) || (resultSet.getBoolean("IsTeamLead"))) {
                    keys[keyCnt] = key;
                    keyCnt++;
                }
            }

            for (int i = 0; i < keyCnt; i++) {
                key = keys[i];

                tempMap = getMyTeamMembersUpTo(key, theStatement);
                if (tempMap.size() > 0) {
                    Iterator tempIterator = tempMap.entrySet().iterator();
                    while (tempIterator.hasNext()) {
                        Map.Entry entry = (Map.Entry) tempIterator.next();
                        key = entry.getKey().toString();
                        value = entry.getValue().toString();
                        myTeamManagersMap.put(key, value);
                        entry = null;
                    }
                }
            }

        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        }
        myTeamManagersMap = sortMapByValues(myTeamManagersMap);
        return myTeamManagersMap; // returning the object.
    } //closing the method

    public Map getRecrutimentLeadsAndManagers() throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        Map RecmemebersMap = new HashMap();//Description
        Map teamMembers = new TreeMap();
        String myTeamMembers = "";


        connection = ConnectionProvider.getInstance().getConnection();

        try {



            queryString = "SELECT LoginId,CONCAT(TRIM(FName),'.',TRIM(LName)) AS EmployeeName FROM tblEmployee WHERE curstatus='Active' AND DepartmentId='Recruiting' AND (IsManager=1 or IsTeamLead=1) GROUP BY EmployeeName ORDER BY TRIM(FName) ";
            // System.out.println("queryString1--->" + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                RecmemebersMap.put(resultSet.getString("LoginId"), resultSet.getString("EmployeeName"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return RecmemebersMap;
    }

    public Map getIssueTypeList() throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        Map pmomanagerMap = new HashMap();//Description
        queryString = " SELECT IssueTypeId,Description FROM tblLKIssueType WHERE IsIssueRelatedToID=2;";

        connection = ConnectionProvider.getInstance().getConnection();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                pmomanagerMap.put(resultSet.getString("IssueTypeId"), resultSet.getString("Description"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return pmomanagerMap; // returning the object.
    }

    public int isExistedBridgeDates(String bridgeStartDate, String bridgeEndDate, String timezone, String bridgeCode) throws ServiceLocatorException {
        List opportunityStateList = new ArrayList();
        int count = 0;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        try {
            queryString = "SELECT BHoldDate,BStartTime,BMidDayFrom,BEndTime,BMidDayTo,BTimeZone FROM tblBridgeReservation WHERE STATUS='Created' and BCode='" + bridgeCode + "' order by Id";
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            if ("EST".equals(timezone)) {
                timezone = "America/New_York";
            }
            SimpleDateFormat parseFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
            parseFormat.setTimeZone(TimeZone.getTimeZone(timezone));

            java.util.Date givenStartDate = parseFormat.parse(bridgeStartDate);
            java.util.Date givenEndDate = parseFormat.parse(bridgeEndDate);
            //  System.out.println("givenDate---" + givenStartDate);
            while (resultSet.next()) {
                String dbBridgeDate = resultSet.getString("BHoldDate");
                String dbStartTime = resultSet.getString("BStartTime");
                String dbmidDayFrom = resultSet.getString("BMidDayFrom");
                String dbEndTime = resultSet.getString("BEndTime");
                String dbmidDayTo = resultSet.getString("BMidDayTo");
                String dbtimezone = resultSet.getString("BTimeZone");
                if ("EST".equals(dbtimezone)) {
                    timezone = "America/New_York";
                }
                parseFormat.setTimeZone(TimeZone.getTimeZone(timezone));
                java.util.Date dbStartDate = parseFormat.parse(dbBridgeDate + " " + dbStartTime + " " + dbmidDayFrom);
                java.util.Date dbEndDate = parseFormat.parse(dbBridgeDate + " " + dbEndTime + " " + dbmidDayTo);
                
                // Adding one hour buffer time start
                Calendar cl = Calendar. getInstance();
               cl.setTime(dbEndDate);
                cl. add(Calendar.HOUR_OF_DAY, 1);
                dbEndDate=cl.getTime();
                // Adding one hour buffer time end
                
                // System.out.println("start date----------------------"+dbStartDate + "  " + givenStartDate + "  " + dbEndDate);
                //System.out.println("End Date------------------------"+dbStartDate + "  " + givenEndDate + "  " + dbEndDate);
                //System.out.println("givenStartDate.compareTo(dbStartDate)-----> " + givenStartDate.compareTo(dbStartDate));
                // System.out.println("givenStartDate.compareTo(dbEndDate)-----> " + givenStartDate.compareTo(dbEndDate));
                if (givenStartDate.compareTo(dbStartDate) <= 0 || givenStartDate.compareTo(dbEndDate) >= 0) {
                    if (givenStartDate.compareTo(dbStartDate) <= 0) {

                        if (givenEndDate.compareTo(dbStartDate) <= 0) {
                            // System.out.println("both are less");
                        } else {
                            count = 1;
                            break;
                        }
                    } else {
                        if (givenEndDate.compareTo(dbEndDate) >= 0) {
                            //System.out.println("both are greater");
                        } else {
                            count = 1;
                            break;
                        }
                    }

                } else {
                    count = 1;
                    break;
                }

                //System.out.println("count------------------------" + count);
                //  break;


            }
            //  Collections.sort(opportunityStateList);
        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (SQLException sqlex) {
            throw new ServiceLocatorException(sqlex);
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }

        }
        return count;
    }

    public String getExistedBCodes(String BHoldDate, String BStartTime, String BMidDayFrom, String BEndTime, String BMidDayTo, String timezone) throws ServiceLocatorException {
        List opportunityStateList = new ArrayList();
        String bCodes = "";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        try {
            if ("EST".equals(timezone)) {
                timezone = "America/New_York";
            }
            SimpleDateFormat parseFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
            parseFormat.setTimeZone(TimeZone.getTimeZone(timezone));

            java.util.Date givenStartDate = parseFormat.parse(BHoldDate + " " + BStartTime + " " + BMidDayFrom);
            java.util.Date givenEndDate = parseFormat.parse(BHoldDate + " " + BEndTime + " " + BMidDayTo);
            queryString = "SELECT BCode,BHoldDate,BStartTime,BMidDayFrom,BEndTime,BMidDayTo,BTimeZone FROM tblBridgeReservation WHERE STATUS='Created' and BHoldDate=?";// AND BStartTime=? AND BMidDayFrom=? ";//AND BEndTime=? AND  BMidDayTo=?";
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.prepareStatement(queryString);
            statement.setString(1, BHoldDate);
//            statement.setString(2, BStartTime);
//            statement.setString(3, BMidDayFrom);
//            statement.setString(4, BEndTime);
//            statement.setString(5, BMidDayTo);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String dbBridgeDate = resultSet.getString("BHoldDate");
                String dbStartTime = resultSet.getString("BStartTime");
                String dbmidDayFrom = resultSet.getString("BMidDayFrom");
                String dbEndTime = resultSet.getString("BEndTime");
                String dbmidDayTo = resultSet.getString("BMidDayTo");
                String dbtimezone = resultSet.getString("BTimeZone");
                if ("EST".equals(dbtimezone)) {
                    timezone = "America/New_York";
                }
                parseFormat.setTimeZone(TimeZone.getTimeZone(timezone));
                java.util.Date dbStartDate = parseFormat.parse(dbBridgeDate + " " + dbStartTime + " " + dbmidDayFrom);
                java.util.Date dbEndDate = parseFormat.parse(dbBridgeDate + " " + dbEndTime + " " + dbmidDayTo);
                
                // Adding one hour buffer time start
                Calendar cl = Calendar. getInstance();
               cl.setTime(dbEndDate);
                cl. add(Calendar.HOUR_OF_DAY, 1);
                dbEndDate=cl.getTime();
                // Adding one hour buffer time end
                
                if (givenStartDate.compareTo(dbStartDate) <= 0 || givenStartDate.compareTo(dbEndDate) >= 0) {
                    if (givenStartDate.compareTo(dbStartDate) <= 0) {

                        if (givenEndDate.compareTo(dbStartDate) <= 0) {
                            //System.out.println("both are less");
                        } else {
                            bCodes += "'" + resultSet.getString("BCode") + "',";
                        }
                    } else {
                        if (givenEndDate.compareTo(dbEndDate) >= 0) {
                            //System.out.println("both are greater");
                        } else {
                            bCodes += "'" + resultSet.getString("BCode") + "',";
                        }
                    }

                } else {
                    bCodes += "'" + resultSet.getString("BCode") + "',";
                }

            }
            if (!bCodes.equals("")) {
                bCodes = bCodes.substring(0, bCodes.length() - 1);
            }
            //  Collections.sort(opportunityStateList);
        } catch (ParseException ex) {
            throw new ServiceLocatorException(ex);
        } catch (SQLException sqlex) {
            throw new ServiceLocatorException(sqlex);
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }

        }
        // System.out.println("bCodes---"+bCodes);
        return bCodes;
    }

    public int doUpdateBMSBridgeDetails(String bCode, String loginId) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement statement = null;
        String responseString = "";
        int count = 0;
        try {


            String queryString = "UPDATE tblBridges SET STATUS=?,ModifiedBy=?,ModifiedDate=? WHERE BCode ='" + bCode + "'";

            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.prepareStatement(queryString);

            statement.setString(1, "Issue");
            statement.setString(2, loginId);
            statement.setTimestamp(3, DateUtility.getInstance().getCurrentMySqlDateTime());
            count = statement.executeUpdate();


        } catch (Exception sqe) {
            sqe.printStackTrace();

        } finally {
            try {

                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
            }
        }
        return count;
    }

    public Map getLeadNamesByCountry(String country) throws ServiceLocatorException {

        Map employeeMap = new TreeMap();//key-Description
        Statement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT LoginId,FName,LName,MName FROM tblEmployee WHERE (IsManager=1 OR IsTeamLead=1) AND Country='" + country + "' AND curStatus='Active'  ORDER BY FName";
            statement = connection.createStatement();
            //preparedStatement = connection.prepareStatement(queryString);

            //preparedStatement.setString(1,department);

            //resultSet = preparedStatement.executeQuery();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                employeeMap.put(resultSet.getString("LoginId"),
                        resultSet.getString("FName") + " " + resultSet.getString("MName")
                        + "." + resultSet.getString("LName"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        employeeMap = DataUtility.getInstance().getMapSortByValue(employeeMap);
        return employeeMap; // returning the object.
    } //closing the method.

    public Map getDeliveryLeadsByCountry(String country) throws ServiceLocatorException {

        Map employeeMap = new TreeMap();//key-Description
        Statement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT LoginId,FName,LName,MName FROM tblEmployee WHERE  curStatus='Active' and TitleTypeId ='Delivery Manager' AND Country='" + country + "' ORDER BY FName";
            statement = connection.createStatement();
            //preparedStatement = connection.prepareStatement(queryString);

            //preparedStatement.setString(1,department);

            //resultSet = preparedStatement.executeQuery();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                employeeMap.put(resultSet.getString("LoginId"),
                        resultSet.getString("FName") + " " + resultSet.getString("MName")
                        + "." + resultSet.getString("LName"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        employeeMap = DataUtility.getInstance().getMapSortByValue(employeeMap);
        return employeeMap; // returning the object.
    } //closing the method.

    public Map getInvestmentsNamesById() throws ServiceLocatorException {
        // System.err.println("I am in getEmployeeNamesByReportingPerson");
        Map investmentMap = new TreeMap();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            //   queryString = "SELECT Inv_Id,Inv_Name FROM tblInvestments WHERE STATUS LIKE 'Active'";
            queryString = "SELECT Inv_Id,Inv_Name FROM tblInvestments WHERE STATUS LIKE 'Active' AND InvestmentType='P'";
            preparedStatement = connection.prepareStatement(queryString);
            //preparedStatement = connection.prepareStatement(queryString);

            //preparedStatement.setString(1,department);

            //resultSet = preparedStatement.executeQuery();
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                investmentMap.put(resultSet.getInt("Inv_Id"), resultSet.getString("Inv_Name"));
            }
            investmentMap = sortByValue(investmentMap);

        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return investmentMap; // returning the object.
    } //closing the method.

    /**
     * @ author phanindra kanuri (pkanuri@miraclesoft.com)
     * @ returns Map for contacts against the Account.
     * @ throws
     */
    public Map getContactsNamesAgainstAccount(int accId) throws ServiceLocatorException {
        // System.err.println("I am in getEmployeeNamesByReportingPerson");
        Map contactMap = new TreeMap();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT Id,CONCAT (FirstName,' ',MiddleName,'.',LastName) AS ContactsName FROM tblCrmContact WHERE AccountId =" + accId;
            preparedStatement = connection.prepareStatement(queryString);
            //preparedStatement = connection.prepareStatement(queryString);

            //preparedStatement.setString(1,department);

            //resultSet = preparedStatement.executeQuery();
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString("ContactsName") != null && !"".equals(resultSet.getString("ContactsName"))) {
                    contactMap.put(resultSet.getInt("Id"), resultSet.getString("ContactsName"));
                } else {
                    contactMap.put(resultSet.getInt("Id"), "-");
                }
            }
            //  contactMap = sortByValue(contactMap);
            contactMap = sortIntStringByValue(contactMap);


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return contactMap; // returning the object.
    } //closing the method.

    public List getListFromString(String inputString) {

        List<String> list1 = new ArrayList<String>();
        StringTokenizer str1 = new StringTokenizer(inputString, ",");
        while (str1.hasMoreTokens()) {
            list1.add(str1.nextToken());
        }
        return list1;
    }

    public Map getLeadsMapByAccountId(int accountid) throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        Map leadsMap = new HashMap();//Description
        //   queryString = "SELECT Id,Title FROM tblCrmLeads  WHERE AccountId=" + accountid + " ORDER BY Title";
        //queryString = "SELECT Id,Title FROM tblCrmLeads  WHERE (AccountId=" + accountid + " OR AccountId=0 ) AND DATE(ExpiryDate)>=CURDATE() ORDER BY Title";
        queryString = "SELECT Id,Title FROM tblCrmLeads LEFT OUTER JOIN tblInvestments ON(InvestmentId=Inv_Id) WHERE (AccountId=" + accountid + " OR InvestmentType='S') AND DATE(ExpiryDate)>=CURDATE() ORDER BY Title";

        connection = ConnectionProvider.getInstance().getConnection();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                leadsMap.put(resultSet.getInt("Id"), resultSet.getString("Title"));
            }

            leadsMap = DataUtility.getInstance().getMapSortByValue(leadsMap);
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return leadsMap; // returning the object.
    }

    public String isAssociatedWithOpportunity(int accountid, int leadSourceId) throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        String result = "No";
        int count = 0;
        queryString = "SELECT COUNT(Id) as val FROM tblCrmOpportunity WHERE AccountId=" + accountid + " AND LeadSourceId=" + leadSourceId;

        connection = ConnectionProvider.getInstance().getConnection();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            if (resultSet.next()) {
                count = resultSet.getInt("val");
            }
            if (count > 0) {
                result = "Yes";
            }
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return result; // returning the object.
    }

    public Map getCampaignNames() throws ServiceLocatorException {

        Map campaignMap = new TreeMap();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            String date = DateUtility.getInstance().convertStringToMySQLDate(DateUtility.getInstance().getCurrentMySqlDate().toString());
            //queryString =  "SELECT Id,CampaignName FROM tblCrmCampaign WHERE CampaignEndDate >= CURDATE() AND  STATUS='Active'";         
            queryString = "SELECT Id,CampaignName FROM tblCrmCampaign WHERE  STATUS!='InActive'";
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                campaignMap.put(new Integer(resultSet.getInt("Id")), resultSet.getString("CampaignName"));
            }

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
        //System.out.println("campaignMap in dsdp-->"+campaignMap);
        //return campaignMap; // returning the object.
        return sortIntStringByValue(campaignMap);
    } //closing the method.

    public Map getOppurtunitiesNames(int accountid) throws ServiceLocatorException {

        Map oppurtunitiesMap = new TreeMap();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT Id,Title FROM tblCrmOpportunity WHERE AccountId=" + accountid + " ORDER BY Title";
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                oppurtunitiesMap.put(new Integer(resultSet.getInt("Id")), resultSet.getString("Title"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
        oppurtunitiesMap = sortMapByValues(oppurtunitiesMap);
        return oppurtunitiesMap; // returning the object.
    } //closing the method.

    public List getContactIdListAgainstAccount(int accountId, int contactId) throws ServiceLocatorException {

        List contactList = new ArrayList();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        queryString = "SELECT Id,ContactId FROM tblCrmActivity WHERE AccountId =" + accountId + " ORDER BY CreatedDate DESC";
        connection = ConnectionProvider.getInstance().getConnection();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                String contacts[] = resultSet.getString("ContactId").split(Pattern.quote(","));
                for (int j = 0; j < contacts.length; j++) {
                    if (contacts[j].equals(contactId + "")) {
                        contactList.add(resultSet.getInt("Id"));
                    }
                }
            }

        } catch (SQLException sqlex) {
            throw new ServiceLocatorException(sqlex);
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
        return contactList;
    }//closing the method.
    public String getOpportunityTitle(int opportunityId) throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        String opportunityTitle = "";

        queryString = "SELECT Title FROM tblCrmOpportunity WHERE Id = " + opportunityId;

        connection = ConnectionProvider.getInstance().getConnection();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);


            if (resultSet.next()) {

                opportunityTitle = resultSet.getString("Title");

            }


            // System.out.println("opportunityTitle"+opportunityTitle);

        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return opportunityTitle; // returning the object.
    }

    public Map getAllCampaignNames() throws ServiceLocatorException {

        Map campaignMap = new TreeMap();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            String date = DateUtility.getInstance().convertStringToMySQLDate(DateUtility.getInstance().getCurrentMySqlDate().toString());
            queryString = "SELECT Id,CampaignName FROM tblCrmCampaign WHERE CampaignEndDate >= CURDATE() AND  STATUS!='Inactive'";
            //queryString =  "SELECT Id,CampaignName FROM tblCrmCampaign WHERE  STATUS='Active'";         
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                campaignMap.put(new Integer(resultSet.getInt("Id")), resultSet.getString("CampaignName"));
            }

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
        //System.out.println("campaignMap in dsdp-->"+campaignMap);
        return campaignMap; // returning the object.
    } //closing the method.

    public List getCurrencyList() throws ServiceLocatorException {

        List countryList = new ArrayList();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        queryString = "SELECT countrycurrency FROM tblLKCountry";
        connection = ConnectionProvider.getInstance().getConnection();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                // String contactList[]=resultSet.getString("countrycurrency"));
                countryList.add(resultSet.getString("countrycurrency"));
            }
        } catch (SQLException sqlex) {
            throw new ServiceLocatorException(sqlex);
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
        return countryList;
    }

    public int getMaxCrmOpportunityId() throws ServiceLocatorException {
        int MaxId = 0;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "select Max(Id) as MaxId from tblCrmOpportunity";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                MaxId = resultSet.getInt("MaxId");
            }


        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }

        return MaxId;

    }

  /*  public String getAccountIdsList(String loginId) throws ServiceLocatorException {
        List list1 = new ArrayList();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        String accountIds = "";

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT DISTINCT AccountId FROM tblCrmAccountTeam WHERE  FIND_IN_SET (TeamMemberId,'" + loginId + "')";
            statement = connection.createStatement();
            //System.out.println("the query in DSDP"+queryString);

            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
//            StringTokenizer str = new StringTokenizer(resultSet.getString("AccountId"), ",");
//                        while (str.hasMoreTokens()) {
//                            list1.add(str.nextToken());
//                           
//                        }
                accountIds += resultSet.getString("AccountId") + ",";
            }
            if (!"".equals(accountIds)) {
                accountIds = accountIds.substring(0, accountIds.length() - 1);
            }
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }

        return accountIds;
    }*/

    public String getAccountIdsList(String loginId,HttpServletRequest httpServletRequest) throws ServiceLocatorException {
        List list1 = new ArrayList();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        String accountIds = "";

        try {
       //     System.out.println("in getAccountIdsList() of DSDP");
            connection = ConnectionProvider.getInstance().getConnection();
             String titleType = (String) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_TITLE);
        //     System.out.println("titleType----->"+titleType);
                    if(titleType.equals("BDM")){
                        System.out.println("in BDM case");
                           queryString = "SELECT DISTINCT tblCrmAccountTeam.AccountId,tblRecRequirement.CreatedBy FROM tblRecRequirement LEFT JOIN tblCrmAccountTeam ON (tblRecRequirement.CustomerId=tblCrmAccountTeam.AccountId) WHERE  FIND_IN_SET (tblRecRequirement.CreatedBy,'" + loginId + "') AND tblCrmAccountTeam.AccountId IS NOT NULL";
                    }
                    else{
                            System.out.println("in non BDM case");
                           queryString = "SELECT DISTINCT AccountId FROM tblCrmAccountTeam WHERE  FIND_IN_SET (TeamMemberId,'" + loginId + "')";
                    }
         
            statement = connection.createStatement();
      //      System.out.println("the query in DSDP  " +queryString);

            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
//            StringTokenizer str = new StringTokenizer(resultSet.getString("AccountId"), ",");
//                        while (str.hasMoreTokens()) {
//                            list1.add(str.nextToken());
//                           
//                        }
                accountIds += resultSet.getString("AccountId") + ",";
            }
            if (!"".equals(accountIds)) {
                accountIds = accountIds.substring(0, accountIds.length() - 1);
            }
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }

        return accountIds;
    }

    
    public String getContactsNames(String contactIdsList) throws ServiceLocatorException {
        String email = "";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        //System.out.println("Emp Full Names -->"+empName);
        // StringTokenizer stkEmail = new StringTokenizer(contactsName, ",");  

        //System.out.println("Query-->"+queryString);
        String contactNames = "";
        try {
            String contacts[] = contactIdsList.split("\\,");

            String queryString = " SELECT CONCAT(FirstName,' ',MiddleName,' ',LastName) AS ContactName FROM tblCrmContact WHERE Id=?";
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);

            for (int i = 0; i < contacts.length; i++) {
                preparedStatement.setInt(1, Integer.parseInt(contacts[i]));
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    if (i < contacts.length - 1) {
                        contactNames = contactNames + resultSet.getString("ContactName") + ",";
                    } else {
                        contactNames = contactNames + resultSet.getString("ContactName");
                    }
                }

            }


        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return contactNames;
    }

    public String getAttachmentLocationByEmpId(String empId, String timeSheetID) throws ServiceLocatorException {
        String attachmentLocation = "";
        Statement statement = null;
        ResultSet resultSet = null;

        Connection connection = ConnectionProvider.getInstance().getConnection();
        // System.out.println("getEmpID dsdp--"+empId);
        String queryString = "SELECT FilePath FROM tblTimeSheets WHERE EmpId=" + empId + " AND TimeSheetId=" + timeSheetID;
        // System.out.println("queryString--"+queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                attachmentLocation = resultSet.getString("FilePath");
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return attachmentLocation;
    }

    /*
     * Emet Methods
     * Author : Phani Kanuri
     * Date : 01/29/2016
     */
    public Map getActiveEmployeesList() throws ServiceLocatorException {

        Map activeEmployeeMap = new TreeMap();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;
        String loginIdofAttendees = "";
        List attendeesList = new ArrayList();
        try {
            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT LoginId,Email1 FROM tblEmployee WHERE CurStatus='Active' AND LoginId NOT IN (SELECT LoginId FROM tblEAttendees)";
            // System.out.println("queryString---"+queryString);
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                activeEmployeeMap.put(resultSet.getString("LoginId"), resultSet.getString("Email1"));
            }

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
        //System.out.println("campaignMap in dsdp-->"+campaignMap);
        return activeEmployeeMap; // returning the object.
    } //closing the method.

    public Map getAttendeesForEMeetByType(String accessType) throws ServiceLocatorException {

        List attendeesList = new ArrayList();
        Map attendeesMap = new HashMap();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;
        String attendeesSting = "";
        int count = 0;

        queryString = "SELECT DISTINCT Email1,CONCAT(TRIM(FName),' ',TRIM(MName),'.',TRIM(LName)) AS AttendeeName FROM tblEmployee LEFT JOIN tblEAttendees ON (tblEmployee.LoginId=tblEAttendees.LoginId) WHERE CurrStatus = 'Active' AND CurStatus = 'Active' AND (AccessType ='" + accessType + "' OR AccessType='Both')";
        //System.out.println("queryString---"+queryString);
        connection = ConnectionProvider.getInstance().getConnection();

        try {
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                //attendeesList.add(resultSet.getString("LoginId"));
                if (resultSet.getString("Email1") != null && !"".equals(resultSet.getString("Email1"))) {
                    attendeesMap.put(resultSet.getString("Email1"), resultSet.getString("AttendeeName"));
                }
            }

        } catch (SQLException sqlex) {
            throw new ServiceLocatorException(sqlex);
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
        return attendeesMap;
    }//closing the method.
    public int isAuthorizedForEmeets(String loginId) throws ServiceLocatorException {
        int isAuthorized = 0;
        try {
            String authorizedUsersList[] = SecurityProperties.getProperty("EMEET_ACCESS_PEOPLE").split("\\,");
            for (int i = 0; i < authorizedUsersList.length; i++) {
                if (authorizedUsersList[i].equals(loginId)) {
                    isAuthorized = 1;
                    break;
                }
            }


        } catch (Exception exception) {
            throw new ServiceLocatorException(exception);
        }
        return isAuthorized;
    }

    public int getAvailableUtilization(String empId) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        int rcount = 0;
        try {


            String queryString = "SELECT SUM(Utilization) FROM tblProjectContacts WHERE Status='Active' and ObjectId = " + empId + " ";

            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.prepareStatement(queryString);
            //  System.out.println("count in dsdp"+queryString);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt("SUM(Utilization)");
            }

            rcount = 100 - count;
            //   System.out.println("count in dsdp"+count);
            //               System.out.println("count in dsdp"+rcount);

        } catch (Exception sqe) {
            sqe.printStackTrace();

        } finally {
            try {

                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
            }
        }
        return rcount;

    }

    /*Sorting by map value by ignoring space and case sensitive
     * Map<Integer,String>
     * Author : Santosh Kola
     * Date : 02/03/2016
     */
    public static Map sortIntStringByValue(Map<Integer, String> map) {
        Map result = new LinkedHashMap();
        Set<Entry< Integer, String>> set = map.entrySet();
        List<Entry< Integer, String>> list = new ArrayList<Entry< Integer, String>>(set);
        Collections.sort(list, new Comparator<Map.Entry< Integer, String>>() {
            public int compare(Map.Entry< Integer, String> o1, Map.Entry< Integer, String> o2) {
                return (o1.getValue().trim().toUpperCase()).compareTo(o2.getValue().trim().toUpperCase());
            }
        });
        for (Map.Entry< Integer, String> entry : list) {
            result.put(entry.getKey(), entry.getValue());
            // System.out.println(entry.getKey()+" ==== "+entry.getValue());
        }
        return result;
    }

    public int getStatusDateCheck(int contactId, java.sql.Date stateStartDate) throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        java.sql.Date result;
        java.sql.Date startDate = null;
        java.sql.Date endDate = null;
        int count = 0;
        // System.out.println("----->dsdp"+contactId);
        connection = ConnectionProvider.getInstance().getConnection();

        try {
            //System.out.println("In cahe else");
            //queryString = "SELECT ProjectId FROM tblCrmContactProjects WHERE CrmContactId = "+contactId;
            //   queryString = "SELECT ProjectId FROM tblProjectContacts WHERE ObjectId = "+contactId;
            String TABLE_EMP_STATE_HISTORY = Properties.getProperty("TABLE_EMP_STATE_HISTORY");
            queryString = "SELECT StartDate,EndDate FROM "+TABLE_EMP_STATE_HISTORY+" WHERE EmpId = " + contactId + " ORDER BY CreatedDate DESC LIMIT 1";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            // System.out.println("queryString"+queryString);
            if (resultSet.next()) {
                startDate = resultSet.getDate("StartDate");
                endDate = resultSet.getDate("EndDate");


                //  System.out.println("StartDate"+startDate);
                //  System.out.println("EndDate"+endDate);
                //System.out.println("--->"+"null".equals(endDate));
                if (endDate == null || "".equals(endDate)) {



                    if (startDate.compareTo(stateStartDate) >= 0) {
                        count = 1;
                    }

                } else {

                    if (endDate.compareTo(stateStartDate) >= 0) {
                        count = 2;
                    }
                }
            }
            //  System.out.println("result"+result);
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }

        }


        return count; // returning the object.
    } //closing the method.

    

    public List getProjectStartDate(int Id) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List list = new ArrayList();
        try {
            String queryString = "SELECT ProjectStartDate,ProjectEndDate FROM tblProjects WHERE Id=" + Id;

            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.prepareStatement(queryString);
            //System.out.println("getProjectStartDate in dsdp"+queryString);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString("ProjectStartDate"));
                list.add(resultSet.getString("ProjectEndDate"));

            }
        } catch (Exception sqe) {
            sqe.printStackTrace();

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
            } catch (SQLException sqle) {
            }
        }
        return list;

    }

   

    public List getCollegeList() throws ServiceLocatorException {
        // System.out.println("--getCollegeList--");
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List list = new ArrayList();
        try {
            String queryString = "SELECT CollegeName FROM tblLKCreColleges ORDER BY CollegeName ASC";

            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.prepareStatement(queryString);
            //System.out.println("getProjectStartDate in dsdp"+queryString);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {

                list.add(resultSet.getString("CollegeName"));
            }
        } catch (Exception sqe) {
            sqe.printStackTrace();

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
            } catch (SQLException sqle) {
            }
        }
        return list;

    }
//SELECT * FROM tblLKPositions WHERE STATUS='Active'
    public Map getCrePositions() throws ServiceLocatorException {
        // System.out.println("--getCollegeList--");
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map positionsMap = new HashMap();
        try {
            String queryString = "SELECT Id,PostApplied FROM tblLKPositions WHERE STATUS='Active' ORDER BY PostApplied ASC";

            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.prepareStatement(queryString);
            //System.out.println("getProjectStartDate in dsdp"+queryString);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {

                positionsMap.put(resultSet.getInt("Id"), resultSet.getString("PostApplied"));
            }
        } catch (Exception sqe) {
            sqe.printStackTrace();

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
            } catch (SQLException sqle) {
            }
        }
        return sortIntStringByValue(positionsMap);

    }

    public String getCourseAndStreamByCreId(int creid) throws ServiceLocatorException {
        // System.out.println("--getCollegeList--");
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String courseStream = "";
        try {
            String queryString = "SELECT stream,cource FROM tblCreAcademics WHERE CreId =" + creid + " ORDER BY Id DESC LIMIT 1";

            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.prepareStatement(queryString);
            //System.out.println("getProjectStartDate in dsdp"+queryString);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {

                //positionsMap.put(resultSet.getInt("Id"), resultSet.getString("PostApplied")) ;      
                if (resultSet.getString("cource") != null && !"".equals(resultSet.getString("cource").trim())) {
                    courseStream = resultSet.getString("cource");
                } else {
                    courseStream = "-";
                }
                if (resultSet.getString("stream") != null && !"".equals(resultSet.getString("stream").trim())) {
                    courseStream = courseStream + "#^$" + resultSet.getString("stream");
                } else {
                    courseStream = courseStream + "#^$" + "-";
                }

            }
        } catch (Exception sqe) {
            sqe.printStackTrace();

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
            } catch (SQLException sqle) {
            }
        }
        return courseStream;

    }

    public String getEmpNoByEmpId(int empId) throws ServiceLocatorException {
        String empNo = "0";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        try {
            queryString = "SELECT EmpNo FROM tblEmployee WHERE Id=" + empId;
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            if (resultSet.next()) {
                empNo = resultSet.getString("EmpNo");
                //System.out.println("Dsdp empNo-->"+empNo);
                if (empNo == null || "".equals(empNo)) {
                    empNo = "0";
                }
            }
            //  Collections.sort(opportunityStateList);
        } catch (SQLException sqlex) {
            throw new ServiceLocatorException(sqlex);
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }

        }
        return empNo;
    }

    public String getEmpLocationByEmpId(int empId) throws ServiceLocatorException {
        String empLocation = "";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        try {
            queryString = "SELECT Location FROM tblEmployee WHERE Id=" + empId;
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                empLocation = resultSet.getString("Location");
            }
            //  Collections.sort(opportunityStateList);
        } catch (SQLException sqlex) {
            throw new ServiceLocatorException(sqlex);
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }

        }
        return empLocation;
    }

    public Map getAllIndianEmployees() throws ServiceLocatorException {

        Map employeeMap = new LinkedHashMap();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT Id,CONCAT(TRIM(FName),'.',TRIM(LName)) AS EmployeeName FROM"
                    + " tblEmployee WHERE CurStatus='Active' AND DeletedFlag != 1 and Country='India'ORDER BY EmployeeName";
            preparedStatement = connection.prepareStatement(queryString);

            //System.out.println("query-"+queryString);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employeeMap.put(resultSet.getString("Id"), resultSet.getString("EmployeeName"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
        // System.out.println("employeeMap......"+employeeMap);
        return employeeMap; // returning the object.
    }

    public int isAuthorizedForPFExcelAccess(String loginId) throws
            ServiceLocatorException {
        int isAuthorized = 0;
        try {
            String authorizedUsersList[] =
                    SecurityProperties.getProperty("PF_EXCEL_ACCESS").split("\\,");
            for (int i = 0; i < authorizedUsersList.length; i++) {
                if (authorizedUsersList[i].equals(loginId)) {
                    isAuthorized = 1;
                    break;
                }
            }


        } catch (Exception exception) {
            throw new ServiceLocatorException(exception);
        }
        // System.out.println("the values returning"+isAuthorized);
        return isAuthorized;
    }

    public String getExamName(String examKey) throws ServiceLocatorException {
        // System.out.println("--getExamName in dsdp--");
        String examName = "";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        try {
            queryString = "  SELECT ExamType FROM tblCreSetExam WHERE Id=" + examKey;
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            if (resultSet.next()) {
                examName = resultSet.getString("ExamType");
            }
            //  Collections.sort(opportunityStateList);
        } catch (SQLException sqlex) {
            throw new ServiceLocatorException(sqlex);
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }

        }
        return examName;
    }

    public Map getRoleStatusMap() throws ServiceLocatorException {
        Map roleStatusMap = new TreeMap();//Description
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        connection = ConnectionProvider.getInstance().getConnection();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT  Id AS RoleId, tblLKRoles.description AS RoleName  FROM tblLKRoles ORDER BY RoleName");
            while (resultSet.next()) {
                roleStatusMap.put(resultSet.getString("RoleId"), resultSet.getString("RoleName"));

            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }

        return roleStatusMap; // returning the object.
    }

    public Map getEmployeeLocationsList(String country) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        connection = ConnectionProvider.getInstance().getConnection();
        Map locationList = new TreeMap();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT Location FROM tblLKEmpLocations WHERE Country LIKE '" + country + "' AND Status='Active'");
            while (resultSet.next()) {
                locationList.put(resultSet.getString("Location"), resultSet.getString("Location"));


            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }

        return locationList; // returning the object.
    }

    public int isAuthorizedForOppSoftware(String loginId) throws ServiceLocatorException {
        int isAuthorized = 0;
        try {
            String authorizedUsersList[] = SecurityProperties.getProperty("OPPORTUNITY_SOFTWARE_ACCESS").split(Pattern.quote(","));
            for (int i = 0; i < authorizedUsersList.length; i++) {
                if (authorizedUsersList[i].equals(loginId)) {
                    isAuthorized = 1;
                    break;
                }
            }


        } catch (Exception exception) {
            throw new ServiceLocatorException(exception);
        }
        //System.out.println("the values returning"+isAuthorized);
        return isAuthorized;
    }

    /*
     * Contact Exist or Not 
     * Date : 04/19/2016
     * Author : Santosh Kola
     */
    public boolean isConatctExsit(int accountId, String offEmail, String personalEmail, int contactId) throws ServiceLocatorException {
        String contactName = "";
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT CONCAT(FirstName,' ',MiddleName,' ',LastName) AS Name FROM tblCrmContact WHERE AccountId=" + accountId;
        if (contactId > 0) {
            queryString = queryString + " AND Id!=" + contactId + " ";
        }
        if (offEmail != null && !"".equals(offEmail) && personalEmail != null && !"".equals(personalEmail)) {
            queryString = queryString + " AND (Email1='" + offEmail + "' OR Email2='" + personalEmail + "')";
        } else {
            if (offEmail != null && !"".equals(offEmail)) {
                queryString = queryString + " AND Email1='" + offEmail + "' ";
            }
            if (personalEmail != null && !"".equals(personalEmail)) {
                queryString = queryString + " AND Email2='" + personalEmail + "' ";
            }
        }
        boolean isContactExist = false;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            if (resultSet.next()) {
                contactName = resultSet.getString("Name");
                isContactExist = true;
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return isContactExist;
    }

    public Map getIssueTypeMapByIssueID(String issueType) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        connection = ConnectionProvider.getInstance().getConnection();
        Map issueMap = new HashMap();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT IssueTypeId,Description FROM tblLKIssueType WHERE STATUS='Active' AND IsIssueRelatedToID ='" + issueType + "' ORDER BY IssueTypeId");
            while (resultSet.next()) {
                issueMap.put(resultSet.getString("IssueTypeId"), resultSet.getString("Description"));


            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }

        return issueMap; // returning the object.
    }

    public int getPayrollAuthenctication(int empId) throws ServiceLocatorException {

        int count = 0;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        //queryString = "SELECT concat(FirstName,'.',LastName) as Name, LoginId FROM tblCatagory";
        String queryString = "select     count(*) AS count from tblPayrollAuthCheck where empId=" + empId + " and Status='Active'";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            if (resultSet.next()) {
                count = resultSet.getInt("count");
            }
            // System.out.println("count --"+count);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return count;
    }

    public int getPrimaryRoleId(int empId) throws ServiceLocatorException {

        int roleId = 0;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        //queryString = "SELECT concat(FirstName,'.',LastName) as Name, LoginId FROM tblCatagory";
        String queryString = "SELECT RoleId FROM tblEmpRoles WHERE empId=" + empId + " AND PrimaryFlag=1;";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            if (resultSet.next()) {
                roleId = resultSet.getInt("RoleId");
            }
            //System.out.println("roleId --"+roleId);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return roleId;
    }

// payroll start
    public String getOrgNameById(int orgId) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;
        String ordNazme = "";
        String resultString = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT Description  FROM tblLKOrganization WHERE AccountId = " + orgId + "";
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                ordNazme = resultSet.getString("Description");

            }
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return ordNazme;
    }

    public String getBankNameById(int bankId) throws ServiceLocatorException {

        String bankName = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        //queryString = "SELECT concat(FirstName,'.',LastName) as Name, LoginId FROM tblCatagory";
        String queryString = "SELECT NAME FROM tblLKEmpBanks WHERE Id=" + bankId + " AND STATUS='Active'";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                bankName = resultSet.getString("NAME");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return bankName;
    }

    public String getAccNumberById(int Id) throws ServiceLocatorException {

        String bankName = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        //queryString = "SELECT concat(FirstName,'.',LastName) as Name, LoginId FROM tblCatagory";
        String queryString = "SELECT AccNum FROM tblEmployee WHERE Id=" + Id + " AND CURSTATUS='Active'";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                bankName = resultSet.getString("AccNum");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return bankName;
    }

    public String getNameAsPerAccById(int Id) throws ServiceLocatorException {

        String bankName = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        //queryString = "SELECT concat(FirstName,'.',LastName) as Name, LoginId FROM tblCatagory";
        String queryString = "SELECT nameAsPerAcc FROM tblEmployee WHERE Id=" + Id + " AND CURSTATUS='Active'";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                bankName = resultSet.getString("nameAsPerAcc");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return bankName;
    }

    public int getPayrollIdByEmpId(int empId) throws ServiceLocatorException {

        int payrollId = 0;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        //queryString = "SELECT concat(FirstName,'.',LastName) as Name, LoginId FROM tblCatagory";
        String queryString = "SELECT PayRollId FROM tblEmpPayRoll WHERE EmpId = " + empId;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                payrollId = resultSet.getInt("PayRollId");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        //  System.out.println("loc=" + payrollId);
        return payrollId;
    }

    public String getFileLocationByTefId(int Id) throws ServiceLocatorException {

        String loc = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        //queryString = "SELECT concat(FirstName,'.',LastName) as Name, LoginId FROM tblCatagory";
        String queryString = "SELECT AttachmentLocation FROM tblEmpTaxExemptionDetails WHERE Id=" + Id;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                loc = resultSet.getString("AttachmentLocation");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        //   System.out.println("loc=" + loc);
        return loc;
    }

    public boolean checkForIsBlock(int empId, int month, int year) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;
        int checkIsBlock = 0;
        boolean isBlock = false;
        String resultString = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT IsBlock FROM tblEmpWages WHERE EmpId=" + empId + " and month(PayPeriodStartDate)=" + month + " and year(PayPeriodStartDate)=" + year;
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                checkIsBlock = resultSet.getInt("IsBlock");
                if (checkIsBlock == 1) {
                    isBlock = true;
                }
            }
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return isBlock;
    }

   public Map getTaxExemptionDetails(int empId, int month,int year) throws ServiceLocatorException {
//        System.out.println("getTaxExemptionDetails method");
        Map employeeMap = new TreeMap();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;
        String financialYear="";
 //int month=Calendar.getInstance().get(Calendar.MONTH);
    //    int year=Calendar.getInstance().get(Calendar.YEAR);
        String sdate = "";
            String edate = "";
//            if(month>=3){
//                sdate = year+"-04-01";
//                edate = (year+1)+"-03-31";
//            } else{
//                sdate = (year-1)+"-04-01";
//                edate = year+"-03-31";
//            }
            if (month >= 3) {
            sdate = year + "-04-01";
            edate = (year + 1) + "-03-31";
              financialYear = "April" + year + "-March" + (year + 1);
        } else {
            sdate = (year - 1) + "-04-01";
            edate = year + "-03-31";
             financialYear = "April" + (year - 1) + "-March" + (year);
        }
        try {
            connection = ConnectionProvider.getInstance().getConnection();
//            queryString = "SELECT COUNT(EmpID),SUM(CASE WHEN (tblEmpTaxExemptionDetails.`ExemptionId` = 9 AND tblEmpTaxExemptionDetails.STATUS='Approved') "+
// "THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS LICFROMSalary ,"+
// "SUM(CASE WHEN (tblEmpTaxExemptionDetails.`ExemptionId` = 5 AND tblEmpTaxExemptionDetails.STATUS='Approved') "+
//  "THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS TutionFee,"+
// "SUM(CASE WHEN (tblEmpTaxExemptionDetails.`ExemptionId` = 4 AND tblEmpTaxExemptionDetails.STATUS='Approved')"+
//  "THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS ppf,"+
// "SUM(CASE WHEN (tblEmpTaxExemptionDetails.`ExemptionId` = 1 AND tblEmpTaxExemptionDetails.STATUS='Approved') "+
//" THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS lic, "+
//  "SUM(CASE WHEN ((tblEmpTaxExemptionDetails.`ExemptionId` = 14 AND tblEmpTaxExemptionDetails.STATUS='Approved') OR (tblEmpTaxExemptionDetails.`ExemptionId` = 19 AND tblEmpTaxExemptionDetails.STATUS='Approved')) "+
//" THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS HBLoan, "+
//                      "SUM(CASE WHEN (tblEmpTaxExemptionDetails.`ExemptionId` = 17 AND tblEmpTaxExemptionDetails.STATUS='Approved') "+
//" THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS eduloan, "+
//                    "SUM(CASE WHEN (tblEmpTaxExemptionDetails.`ExemptionId` = 8 AND tblEmpTaxExemptionDetails.STATUS='Approved') "+
//" THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS taxSavingFD ,"+
//                       "SUM(CASE WHEN (tblEmpTaxExemptionDetails.`ExemptionId` = 6 AND tblEmpTaxExemptionDetails.STATUS='Approved') "+
//" THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS ELSS ,"+
//                    
//                    "SUM(CASE WHEN (tblEmpTaxExemptionDetails.`ExemptionId` = 2 AND tblEmpTaxExemptionDetails.STATUS='Approved') "+
//" THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS HbLoanIns, "+
//                    "SUM(CASE WHEN ((tblEmpTaxExemptionDetails.`ExemptionId` = 15 AND tblEmpTaxExemptionDetails.STATUS='Approved') OR (tblEmpTaxExemptionDetails.`ExemptionId` = 16 AND tblEmpTaxExemptionDetails.STATUS='Approved')) "+ 
//" THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS HealthDirect, "+
//                     "SUM(CASE WHEN ((tblEmpTaxExemptionDetails.`ExemptionId` = 3 AND tblEmpTaxExemptionDetails.STATUS='Approved')) "+ 
//" THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS NSC, "+
//                    "SUM(CASE WHEN ((tblEmpTaxExemptionDetails.`ExemptionId` = 7 AND tblEmpTaxExemptionDetails.STATUS='Approved')) "+ 
//" THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS PostOfficeTermDeposit, "+
//                    "SUM(CASE WHEN ((tblEmpTaxExemptionDetails.`ExemptionId` = 10 AND tblEmpTaxExemptionDetails.STATUS='Approved')) "+ 
//" THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS Others, "+
//                    "SUM(CASE WHEN ((tblEmpTaxExemptionDetails.`ExemptionId` = 12 AND tblEmpTaxExemptionDetails.STATUS='Approved')) "+ 
//" THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS 80CCD_NPS "+
//" FROM tblEmpTaxExemptionDetails  WHERE EmpId= "+empId+" AND PaymentDate>='"+sdate+"' and PaymentDate<='"+edate+"' ";
             queryString = "SELECT COUNT(EmpID),SUM(CASE WHEN (tblEmpTaxExemptionDetails.`ExemptionId` = 9 AND tblEmpTaxExemptionDetails.STATUS='Approved') "
                    + "THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS LICFROMSalary ,"
                    + "SUM(CASE WHEN (tblEmpTaxExemptionDetails.`ExemptionId` = 5 AND tblEmpTaxExemptionDetails.STATUS='Approved') "
                    + "THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS TutionFee,"
                    + "SUM(CASE WHEN (tblEmpTaxExemptionDetails.`ExemptionId` = 4 AND tblEmpTaxExemptionDetails.STATUS='Approved')"
                    + "THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS ppf,"
                    + "SUM(CASE WHEN (tblEmpTaxExemptionDetails.`ExemptionId` = 1 AND tblEmpTaxExemptionDetails.STATUS='Approved') "
                    + " THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS lic, "
                    + "SUM(CASE WHEN ((tblEmpTaxExemptionDetails.`ExemptionId` = 14 AND tblEmpTaxExemptionDetails.STATUS='Approved') OR (tblEmpTaxExemptionDetails.`ExemptionId` = 19 AND tblEmpTaxExemptionDetails.STATUS='Approved')) "
                    + " THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS HBLoan, "
                    + "SUM(CASE WHEN (tblEmpTaxExemptionDetails.`ExemptionId` = 17 AND tblEmpTaxExemptionDetails.STATUS='Approved') "
                    + " THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS eduloan, "
                    + "SUM(CASE WHEN (tblEmpTaxExemptionDetails.`ExemptionId` = 8 AND tblEmpTaxExemptionDetails.STATUS='Approved') "
                    + " THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS taxSavingFD ,"
                    + "SUM(CASE WHEN (tblEmpTaxExemptionDetails.`ExemptionId` = 6 AND tblEmpTaxExemptionDetails.STATUS='Approved') "
                    + " THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS ELSS ,"
                    + "SUM(CASE WHEN (tblEmpTaxExemptionDetails.`ExemptionId` = 2 AND tblEmpTaxExemptionDetails.STATUS='Approved') "
                    + " THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS HbLoanIns, "
                    + "SUM(CASE WHEN ((tblEmpTaxExemptionDetails.`ExemptionId` = 15 AND tblEmpTaxExemptionDetails.STATUS='Approved') OR (tblEmpTaxExemptionDetails.`ExemptionId` = 16 AND tblEmpTaxExemptionDetails.STATUS='Approved')) "
                    + " THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS HealthDirect, "
                    + "SUM(CASE WHEN ((tblEmpTaxExemptionDetails.`ExemptionId` = 3 AND tblEmpTaxExemptionDetails.STATUS='Approved')) "
                    + " THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS NSC, "
                    + "SUM(CASE WHEN ((tblEmpTaxExemptionDetails.`ExemptionId` = 7 AND tblEmpTaxExemptionDetails.STATUS='Approved')) "
                    + " THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS PostOfficeTermDeposit, "
                    + "SUM(CASE WHEN ((tblEmpTaxExemptionDetails.`ExemptionId` = 10 AND tblEmpTaxExemptionDetails.STATUS='Approved')) "
                    + " THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS Others, "
                    + "SUM(CASE WHEN ((tblEmpTaxExemptionDetails.`ExemptionId` = 12 AND tblEmpTaxExemptionDetails.STATUS='Approved')) "
                    + " THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS 80CCD_NPS "
                  //  + " FROM tblEmpTaxExemptionDetails  WHERE EmpId= " + empId + " AND PaymentDate>='" + sdate + "' and PaymentDate<='" + edate + "' ";
                    + " FROM tblEmpTaxExemptionDetails  WHERE IsActive='Active' AND EmpId= " + empId + " AND FinancialYear='" + financialYear + "'";

//            System.out.println("queryString--"+queryString);
            preparedStatement = connection.prepareStatement(queryString);
//            preparedStatement.setInt(1, empId);
//            preparedStatement.setString(2, sdate);
//            preparedStatement.setString(3, edate);
//           
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if(resultSet.getString("LICFROMSalary")!=null && !"".equals(resultSet.getString("LICFROMSalary"))){
                employeeMap.put("LICFROMSalary",resultSet.getString("LICFROMSalary"));
                }
                else{
                    employeeMap.put("LICFROMSalary","0.0");
                }
               
                
                 if(resultSet.getString("TutionFee")!=null && !"".equals(resultSet.getString("TutionFee"))){
                employeeMap.put("TutionFee",resultSet.getString("TutionFee"));
                }
                else{
                    employeeMap.put("TutionFee","0.0");
                }
                  if(resultSet.getString("ppf")!=null && !"".equals(resultSet.getString("ppf"))){
                employeeMap.put("ppf",resultSet.getString("ppf"));
                }
                else{
                    employeeMap.put("ppf","0.0");
                }
                   if(resultSet.getString("lic")!=null && !"".equals(resultSet.getString("lic"))){
                employeeMap.put("lic",resultSet.getString("lic"));
                }
                else{
                    employeeMap.put("lic","0.0");
                }
                
                
                   if(resultSet.getString("HBLoan")!=null && !"".equals(resultSet.getString("HBLoan"))){
                employeeMap.put("HBLoan",resultSet.getString("HBLoan"));
                }
                else{
                    employeeMap.put("HBLoan","0.0");
                }
                   if(resultSet.getString("eduloan")!=null && !"".equals(resultSet.getString("eduloan"))){
                employeeMap.put("eduloan",resultSet.getString("eduloan"));
                }
                else{
                    employeeMap.put("eduloan","0.0");
                }
                   if(resultSet.getString("taxSavingFD")!=null && !"".equals(resultSet.getString("taxSavingFD"))){
                employeeMap.put("taxSavingFD",resultSet.getString("taxSavingFD"));
                }
                else{
                    employeeMap.put("taxSavingFD","0.0");
                }
                   
              
                   if(resultSet.getString("ELSS")!=null && !"".equals(resultSet.getString("ELSS"))){
                employeeMap.put("ELSS",resultSet.getString("ELSS"));
                }
                else{
                    employeeMap.put("ELSS","0.0");
                }
                   if(resultSet.getString("HbLoanIns")!=null && !"".equals(resultSet.getString("HbLoanIns"))){
                employeeMap.put("HbLoanIns",resultSet.getString("HbLoanIns"));
                }
                else{
                    employeeMap.put("HbLoanIns","0.0");
                }
                   if(resultSet.getString("HealthDirect")!=null && !"".equals(resultSet.getString("HealthDirect"))){
                employeeMap.put("HealthDirect",resultSet.getString("HealthDirect"));
                }
                else{
                    employeeMap.put("HealthDirect","0.0");
                }
                   if(resultSet.getString("NSC")!=null && !"".equals(resultSet.getString("NSC"))){
                employeeMap.put("NSC",resultSet.getString("NSC"));
                }
                else{
                    employeeMap.put("NSC","0.0");
                }
                   
                
                   if(resultSet.getString("PostOfficeTermDeposit")!=null && !"".equals(resultSet.getString("PostOfficeTermDeposit"))){
                employeeMap.put("PostOfficeTermDeposit",resultSet.getString("PostOfficeTermDeposit"));
                }
                else{
                    employeeMap.put("PostOfficeTermDeposit","0.0");
                }
                   if(resultSet.getString("Others")!=null && !"".equals(resultSet.getString("Others"))){
                employeeMap.put("Others",resultSet.getString("Others"));
                }
                else{
                    employeeMap.put("Others","0.0");
                }
                   if(resultSet.getString("80CCD_NPS")!=null && !"".equals(resultSet.getString("80CCD_NPS"))){
                employeeMap.put("80CCD_NPS",resultSet.getString("80CCD_NPS"));
                }
                else{
                    employeeMap.put("80CCD_NPS","0.0");
                }
                   
                   
              
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return employeeMap; 
    }


    public double getPreviousMonthsTDS(int empId, String sdate, String edate) throws ServiceLocatorException {
        // System.out.println("getTaxExemptionDetails method");
        Map employeeMap = new TreeMap();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;
        double tds = 0.00;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT  CASE WHEN SUM(tblEmpWages.Ded_TDS_Bonus) IS NULL THEN 0 ELSE SUM(tblEmpWages.Ded_TDS_Bonus) END  AS TDS "
                    + "FROM tblEmpWages WHERE tblEmpWages.EmpId= " + empId;
            queryString = queryString + " AND (tblEmpWages.PayPeriodStartDate>= '" + sdate + "' AND tblEmpWages.PayPeriodStartDate<='" + edate + "')";
            //  System.out.println("queryString--"+queryString);
            preparedStatement = connection.prepareStatement(queryString);


            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                tds = resultSet.getDouble("TDS");
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return tds;
    }

    public int getMonthsBetweenDates(String startDate, String endDate) throws ServiceLocatorException {

        int difference = 0;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        //queryString = "SELECT concat(FirstName,'.',LastName) as Name, LoginId FROM tblCatagory";
        String queryString = "SELECT TIMESTAMPDIFF(MONTH, '" + startDate + "', '" + endDate + "') AS difference";
        // System.out.println("queryString---" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                difference = resultSet.getInt("difference");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        // System.out.println("category=" + category);
        return difference;
    }

    public Map getExemptionTypes() throws ServiceLocatorException {
        //  System.out.println("getExemptionTypes()");


        Map exemptionTypes = new TreeMap();//key-Description
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;

        queryString = "SELECT Id,ExemptionType FROM tblLKTaxExemptions WHERE Status='Active' ORDER BY ExemptionType";
        connection = ConnectionProvider.getInstance().getConnection();

        try {
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                exemptionTypes.put(new Integer(resultSet.getInt("Id")),
                        resultSet.getString("ExemptionType"));
            }

        } catch (SQLException sqlex) {
            throw new ServiceLocatorException(sqlex);
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
        return exemptionTypes;
    }

    public Map getEmpShifts() throws ServiceLocatorException {

        Map empShiftsMap = new TreeMap();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT Id,Name FROM tblLKEmpShifts WHERE Status='Active' order by Id";
            preparedStatement = connection.prepareStatement(queryString);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                empShiftsMap.put(new Integer(resultSet.getInt("Id")),
                        resultSet.getString("Name"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return empShiftsMap; // returning the object.
    }

    public Map getEmpClassification() throws ServiceLocatorException {

        Map classificationMap = new TreeMap();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT Id,Name FROM tblLKEmpClassification WHERE Status='Active' order by Id";
            preparedStatement = connection.prepareStatement(queryString);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                classificationMap.put(new Integer(resultSet.getInt("Id")),
                        resultSet.getString("Name"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return classificationMap; // returning the object.
    }

    public Map getEmpRegion() throws ServiceLocatorException {

        Map empRegionMap = new TreeMap();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT Id,Name FROM tblLKEmpRegion WHERE Status='Active' ORDER BY Id";
            preparedStatement = connection.prepareStatement(queryString);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                empRegionMap.put(new Integer(resultSet.getInt("Id")),
                        resultSet.getString("Name"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return empRegionMap; // returning the object.
    }

    public Map getEmpBanks() throws ServiceLocatorException {

        Map empBanksMap = new TreeMap();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT Id,Name FROM tblLKEmpBanks WHERE Status='Active' order by Id";
            preparedStatement = connection.prepareStatement(queryString);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                empBanksMap.put(new Integer(resultSet.getInt("Id")),
                        resultSet.getString("Name"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return empBanksMap; // returning the object.
    }

    public Map getEmpLocations() throws ServiceLocatorException {

        Map empLocationsMap = new TreeMap();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT Id,Location FROM tblLKEmpLocations WHERE Status='Active' order by Id";
            preparedStatement = connection.prepareStatement(queryString);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                empLocationsMap.put(new Integer(resultSet.getInt("Id")),
                        resultSet.getString("Location"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return empLocationsMap; // returning the object.
    }

    public Map getPaymentType() throws ServiceLocatorException {

        Map paymentTypeMap = new TreeMap();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT Id,Name FROM tblLKPaymentType WHERE Status='Active' ORDER BY Id";
            preparedStatement = connection.prepareStatement(queryString);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                paymentTypeMap.put(new Integer(resultSet.getInt("Id")),
                        resultSet.getString("Name"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return paymentTypeMap; // returning the object.
    }

    public Map getOrganisationMapForPayrolls(String orgKey) throws ServiceLocatorException {

        Map orgMap = new TreeMap();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT `Description`, `AccountId` FROM 	`mirage`.`tblLKOrganization` ";
            preparedStatement = connection.prepareStatement(queryString);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orgMap.put(new Integer(resultSet.getInt("AccountId")),
                        resultSet.getString("Description"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return orgMap; // returning the object.
    }

    public int isWageExisted(int month, int year) throws ServiceLocatorException {
        int wageId = 0;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT Id FROM tblWagesReference WHERE Month=" + month + " AND Year=" + year;


        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                wageId = resultSet.getInt("Id");
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return wageId;
    }

    public int getOrgIdByName(String orgName) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;
        int ordId = 0;
        String resultString = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT Id FROM tblCrmAccount WHERE NAME = '" + orgName + "'";
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                ordId = resultSet.getInt("Id");

            }
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return ordId;
    }

    public String getEmpSavings1234and5Values(int Id) throws ServiceLocatorException {

        String result = "";
        double empSavings1 = 0;
        double empSavings2 = 0;
        double empSavings3_parents = 0;
        double empSavings3_self = 0;
        double empSavings4 = 0;
        double empSavings5 = 0;
        double empSavings6 = 0;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        connection = ConnectionProvider.getInstance().getConnection();

        int month = Calendar.getInstance().get(Calendar.MONTH);
        int year = Calendar.getInstance().get(Calendar.YEAR);
        String sdate = "";
        String edate = "";
        String financialYear="";
        if (month >= 3) {
            sdate = year + "-04-01";
            edate = (year + 1) + "-03-31";
             financialYear="April" + year + "-March" + (year + 1);
             
        } else {
            sdate = (year - 1) + "-04-01";
            edate = year + "-03-31";
              financialYear="April" + (year - 1) + "-March" + (year);
        }

        //String queryString = "SELECT ExemptionId,ApprovedAmount FROM tblEmpTaxExemptionDetails WHERE EmpId=" + Id + " and STATUS = 'Approved' AND PaymentDate>='" + sdate + "' and PaymentDate<='" + edate + "'";
        String queryString = "SELECT ExemptionId,ApprovedAmount FROM tblEmpTaxExemptionDetails WHERE EmpId=" + Id + " and STATUS = 'Approved' AND IsActive='Active' AND FinancialYear='" + financialYear + "'";
      //  System.out.println("queryString--" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                int category = getCategoryIdBasedOnExemptionId(resultSet.getInt("ExemptionId"));
                if (category == 1) {
                    empSavings1 = empSavings1 + resultSet.getDouble("ApprovedAmount");
                } else if (category == 2) {
                    empSavings2 = empSavings2 + resultSet.getDouble("ApprovedAmount");
                } else if (category == 3) {
                    if (resultSet.getInt("ExemptionId") == 15) {
                        empSavings3_parents = empSavings3_parents + resultSet.getDouble("ApprovedAmount");
                    } else {
                        empSavings3_self = empSavings3_self + resultSet.getDouble("ApprovedAmount");
                    }
                } else if (category == 4) {
                    empSavings4 = empSavings4 + resultSet.getDouble("ApprovedAmount");
                } else if (category == 5) {
                    empSavings5 = empSavings5 + resultSet.getDouble("ApprovedAmount");
                } else if (category == 6) {
                    empSavings6 = empSavings6 + resultSet.getDouble("ApprovedAmount");
                }

            }
            //getEmpHealthAmtByEmpId
            double heathAmt = getEmpHealthAmtByEmpId(Id);
            //empSavings3= empSavings3 + (heathAmt*12);

            result = empSavings1 + "#" + empSavings2 + "#" + empSavings3_parents + "#" + empSavings4 + "#" + empSavings5 + "#" + empSavings3_self + "#" + (heathAmt * 12) + "#" + empSavings6;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        //  System.out.println("loc=" + result);
        return result;
    }

    public int getCategoryIdBasedOnExemptionId(int exemptionId) throws ServiceLocatorException {

        int category = 0;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        //queryString = "SELECT concat(FirstName,'.',LastName) as Name, LoginId FROM tblCatagory";
        String queryString = "SELECT Category FROM tblLKTaxExemptions WHERE Id=" + exemptionId;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                category = resultSet.getInt("Category");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        //   System.out.println("category=" + category);
        return category;
    }

    public boolean checkPayRollDateLimit(int year, int month) throws ServiceLocatorException {
        Statement statement = null;
        ResultSet resultSet = null;
        boolean flag = false;
        int count = 0;
        Connection connection = ConnectionProvider.getInstance().getConnection();


        try {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month - 1, 1); //------>
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

            /*calendar.add(Calendar.MONTH, month);
             calendar.set(Calendar.DAY_OF_MONTH, 1);
             calendar.add(Calendar.DATE, -1);
             */
            java.util.Date lastDayOfMonth = calendar.getTime();

            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //   System.out.println("sdf.format(lastDayOfMonth)-->" + sdf.format(lastDayOfMonth));
            //  System.out.println("sdf.format(lastDayOfMonth)-->" + DateUtility.getInstance().getCurrentSQLDate());
            String queryString = "SELECT DATEDIFF('" + DateUtility.getInstance().getCurrentSQLDate() + "','" + sdf.format(lastDayOfMonth) + "') AS DiffDate FROM tblEmpWages ";

            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            if (resultSet.next()) {
                count = resultSet.getInt("DiffDate");
                // System.out.println("count-->" + count);
                if (count >= 30) {
                    flag = true;
                }
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        //  System.out.println("flag-->" + flag);
        return flag;
    }

    public String getBankNameByBankId(String bankId) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;
        String bankName = "";
        String resultString = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT `Name` FROM `mirage`.`tblLKEmpBanks` WHERE Id =" + bankId;
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                bankName = resultSet.getString("Name");

            }
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return bankName;
    }

    public boolean checkForPayrollDateForRunWages(int month, int year, int orgId) throws ServiceLocatorException {
        Statement statement = null;
        ResultSet resultSet = null;
        boolean flag = false;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT * FROM tblEmpWages WHERE MONTH(PayrollDate)=" + month + " AND YEAR(PayrollDate)= " + year + " AND OrgId=" + orgId;
//System.out.println("queryString-->"+queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            if (resultSet.next()) {
                //  count = resultSet.getInt("id");
                flag = true;
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return flag;
    }

   public boolean checkLoadLeaves(int month, int year,int ordId) throws ServiceLocatorException {

        String resultMessage = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
String organization="";
        boolean result = false;
        int count1 = 0;
        int count2 = 0;
        connection = ConnectionProvider.getInstance().getConnection();
        //queryString = "SELECT concat(FirstName,'.',LastName) as Name, LoginId FROM tblCatagory";
        if(ordId==117009){
           organization="ITL";
        }
        else  if(ordId==104124){
           organization="MSS";
        }
        else  if(ordId==116866){
           organization="CNE";
        }
        String queryString = "    SELECT * FROM tblEmpAttendanceLaod WHERE  MONTH(PayrollDate)=" + month + " AND YEAR(PayrollDate)=" + year +" AND OrgID='"+organization+"'";
       // System.out.println("queryString"+queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            if (resultSet.next()) {
                result = true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
      //  System.out.println("resultMessage=" + result);
        return result;
    }

    public void deleteWagesIfExceptionOccurs() throws ServiceLocatorException {
        Statement statement = null;
        ResultSet resultSet = null;
        boolean flag = false;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "delete FROM tblEmpWages WHERE Trans_Flag=0";

        try {
            statement = connection.createStatement();
            statement.execute(queryString);
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }

    }

    public boolean checkForWageExisteByEmpId(int month, int year, int empID) throws ServiceLocatorException {
        Statement statement = null;
        ResultSet resultSet = null;
        boolean flag = false;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT * FROM tblEmpWages WHERE MONTH(PayrollDate)=" + month + " AND YEAR(PayrollDate)= " + year + " AND EmpId=" + empID;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            if (resultSet.next()) {
                //  count = resultSet.getInt("id");
                flag = true;
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return flag;
    }

    public boolean isFreezedByEmpId(int month, int year, int empID) throws ServiceLocatorException {
        Statement statement = null;
        ResultSet resultSet = null;
        boolean flag = false;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT Freeze_Payroll FROM tblEmpWages WHERE MONTH(PayrollDate)=" + month + " AND YEAR(PayrollDate)= " + year + " AND EmpId=" + empID;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            if (resultSet.next()) {

                if (resultSet.getInt("Freeze_Payroll") == 1) {
                    flag = false;
                } else {
                    flag = true;
                }
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return flag;
    }

    public boolean checkLeavesExcelUploadByMonth(int month, int year, int orgId) throws ServiceLocatorException {
        Statement statement = null;
        ResultSet resultSet = null;
        boolean flag = false;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "";
        String organization = "";
        if (orgId == 104124) {
            organization = "MSS";
        } else if (orgId == 116866) {
            organization = "CNE";
        }
        if (month < 10) {
            queryString = "SELECT COUNT(Id) AS total FROM tblEmpAttendanceLaod WHERE PayrollDate = '" + year + "-0" + month + "-01' and OrgId='" + organization + "'";
        } else {
            queryString = "SELECT COUNT(Id) AS total FROM tblEmpAttendanceLaod WHERE PayrollDate = '" + year + "-" + month + "-01' and OrgId='" + organization + "'";
        }
//System.out.println("result queryString-->"+queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            if (resultSet.next()) {

                if (resultSet.getInt("total") > 0) {
                    flag = true;
                } else {
                    flag = false;
                }
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        //System.out.println("result flag-->"+flag);
        return flag;
    }

    public int getPayrollCount(int orgId) throws ServiceLocatorException {

        int rowsCount = 0;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        //queryString = "SELECT concat(FirstName,'.',LastName) as Name, LoginId FROM tblCatagory";
        //  String queryString = "SELECT COUNT(*) AS totalCount FROM tblEmpPayRoll LEFT OUTER JOIN tblEmployee ON (tblEmpPayRoll.EmpId = tblEmployee.Id) WHERE tblEmployee.Country = 'India' AND tblEmpPayRoll.orgAccId="+orgId;
        String queryString = "SELECT COUNT(*) AS totalCount FROM tblEmpPayRoll LEFT OUTER JOIN tblEmployee ON (tblEmpPayRoll.EmpId = tblEmployee.Id) WHERE tblEmpPayRoll.NoSalary=0 and tblEmpPayRoll.orgAccId=" + orgId;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                rowsCount = resultSet.getInt("totalCount");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        //System.out.println("rowsCount=" + rowsCount);
        return rowsCount;
    }

    public String getClassificationById(int Id) throws ServiceLocatorException {

        String classification = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        //queryString = "SELECT concat(FirstName,'.',LastName) as Name, LoginId FROM tblCatagory";
        String queryString = "SELECT Name FROM tblLKEmpClassification WHERE Id=" + Id + " AND STATUS='Active'";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                classification = resultSet.getString("Name");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return classification;
    }

    public String getPaymentTypeById(int Id) throws ServiceLocatorException {

        String paymentType = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        //queryString = "SELECT concat(FirstName,'.',LastName) as Name, LoginId FROM tblCatagory";
        String queryString = "SELECT Name FROM tblLKPaymentType WHERE Id=" + Id + " AND STATUS='Active'";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                paymentType = resultSet.getString("Name");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        //   System.out.println("paymentType=" + paymentType);
        return paymentType;
    }

    public String getShiftByShiftId(int Id) throws ServiceLocatorException {

        String shiftName = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        //queryString = "SELECT concat(FirstName,'.',LastName) as Name, LoginId FROM tblCatagory";
        String queryString = "SELECT Name FROM tblLKEmpShifts WHERE Id=" + Id + " AND STATUS='Active'";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                shiftName = resultSet.getString("Name");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return shiftName;
    }
    //payroll end

    public int getEmpIdByEmpNo(int empNo) throws ServiceLocatorException {
        int empId = 0;

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        try {
            queryString = "SELECT Id FROM tblEmployee WHERE EmpNo=" + empNo;
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            if (resultSet.next()) {
                empId = resultSet.getInt("Id");
                //System.out.println("Dsdp empNo-->"+empNo);

            }
            //  Collections.sort(opportunityStateList);
        } catch (SQLException sqlex) {
            throw new ServiceLocatorException(sqlex);
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }

        }
        return empId;
    }

    public String getEmpNameByEmpId(int empId) throws ServiceLocatorException {

        String employeeName = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        try {
            queryString = "SELECT CONCAT(FName,' ',MName,'.',LName) AS EmpName FROM tblEmployee WHERE Id=" + empId;
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            if (resultSet.next()) {
                employeeName = resultSet.getString("EmpName");
                //System.out.println("Dsdp empNo-->"+empNo);

            }
            //  Collections.sort(opportunityStateList);
        } catch (SQLException sqlex) {
            throw new ServiceLocatorException(sqlex);
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }

        }
        return employeeName;
    }

    public double getEmpHealthAmtByEmpId(int empId) throws ServiceLocatorException {
        double health = 0.0;
        String employeeName = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        try {
            queryString = "SELECT health FROM tblEmpPayRoll WHERE EmpId=" + empId;
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            if (resultSet.next()) {
                health = resultSet.getDouble("health");
                //System.out.println("Dsdp empNo-->"+empNo);

            }
            //  Collections.sort(opportunityStateList);
        } catch (SQLException sqlex) {
            throw new ServiceLocatorException(sqlex);
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }

        }
        return health;
    }

    public int isAuthorizedForHierarchyAccess(String loginId) throws
            ServiceLocatorException {
        int isAuthorized = 0;
        try {
            String authorizedUsersList[] =
                    SecurityProperties.getProperty("Employee.Hierarchy.Access").split("\\,");
            for (int i = 0; i < authorizedUsersList.length; i++) {
                if (authorizedUsersList[i].equals(loginId)) {
                    isAuthorized = 1;
                    break;
                }
            }


        } catch (Exception exception) {
            throw new ServiceLocatorException(exception);
        }
        // System.out.println("the values returning"+isAuthorized);
        return isAuthorized;
    }

    /*IMS methods
     * Date : 05/12/2016
     * Author : Santosh Kola
     * 
     */
    public Map getAllBDMs() throws ServiceLocatorException {


        Map bdmMap = new LinkedHashMap();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String message = "";
        boolean isUpdated = false;
        String queryString = "";
        //  String queryString ="";
        // System.out.println("first call");
        String data = "";
        //queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ModifiedBy=?,ModifiedDate=?,ReviewName=?,ReviewDate=?,Status=?,TLComments=?,TLRating=?,HRRating=?,HrComments=? where Id=?";
        //if(roleName.equalsIgnoreCase("Employee"))
        // queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ReviewName=?,ReviewDate=?,Status=? where Id=?";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT Id,CONCAT(TRIM(FName),'.',TRIM(LName)) AS EmployeeName FROM"
                    + " tblEmployee WHERE CurStatus='Active' AND TitleTypeId='BDM' ORDER BY EmployeeName";
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            //subJson.put(resultSet.getString("SpeakerId"), resultSet.getString("SpeakerName"));
            while (resultSet.next()) {
                //  if(!investmentBDMMap.containsKey(resultSet.getString("Id"))){
                bdmMap.put(resultSet.getString("Id"), resultSet.getString("EmployeeName"));
                //}
            }

        } catch (Exception exception) {
            exception.printStackTrace();
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        bdmMap = DataSourceDataProvider.getInstance().sortMapByValues(bdmMap);

        return bdmMap;
    }

    public String getInvestmentIdsByBdmId(String bdmId) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String bdmList = "";

        String queryString = "";

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT DISTINCT InvestmentId FROM tblInvestmentBDMTeam WHERE EmpId=" + bdmId;

            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            //subJson.put(resultSet.getString("SpeakerId"), resultSet.getString("SpeakerName"));
            int i = 0;
            while (resultSet.next()) {
                if (i == 0) {
                    bdmList = resultSet.getString("InvestmentId");
                } else {
                    bdmList = bdmList + "," + resultSet.getString("InvestmentId");
                }
                i++;
            }
            if (i == 0) {
                bdmList = "-1";
            }

        } catch (Exception exception) {
            exception.printStackTrace();
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return bdmList;
    }

    public Map getInvestmentsNamesFromDashboard() throws ServiceLocatorException {
        // System.err.println("I am in getEmployeeNamesByReportingPerson");
        Map investmentMap = new TreeMap();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            //   queryString = "SELECT Inv_Id,Inv_Name FROM tblInvestments WHERE STATUS LIKE 'Active'";
            queryString = "SELECT Inv_Id,Inv_Name FROM tblInvestments WHERE STATUS != 'InActive' ";
            preparedStatement = connection.prepareStatement(queryString);
            //preparedStatement = connection.prepareStatement(queryString);

            //preparedStatement.setString(1,department);

            //resultSet = preparedStatement.executeQuery();
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                investmentMap.put(resultSet.getInt("Inv_Id"), resultSet.getString("Inv_Name"));
            }
            investmentMap = sortByValue(investmentMap);

        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return investmentMap; // returning the object.
    } //closing the method.

    public List getStatesList() throws ServiceLocatorException {
        System.out.println("states list");
        List statesList = new ArrayList();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        queryString = "SELECT Description FROM tblLKStates LEFT JOIN tblLKCountry ON tblLKStates.CountryId = tblLKCountry.Id WHERE Country = 'USA' ORDER BY Name";
        connection = ConnectionProvider.getInstance().getConnection();
        // System.out.println("query of states list is---->"+queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                // String contactList[]=resultSet.getString("countrycurrency"));
                statesList.add(resultSet.getString("Description"));
            }
        } catch (SQLException sqlex) {
            throw new ServiceLocatorException(sqlex);
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
        return statesList;
    }

    public Map getAllSalesTeam() throws ServiceLocatorException {
        Map salesTeamMap = new TreeMap();
        // salesTeamExceptAccountTeamMap.clear();

        ResultSet resultSet = null;
        Connection connection = null;
        Statement statement = null;

        connection = ConnectionProvider.getInstance().getConnection();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT LoginId,CONCAT(fName,' ',mName,'.',lName) AS EmployeeName FROM tblEmployee WHERE Id IN (SELECT EmpId FROM tblEmpRoles LEFT OUTER JOIN tblLKRoles ON (tblEmpRoles.RoleId=tblLKRoles.Id) WHERE Description='Sales') AND CurStatus = 'Active' ORDER BY EmployeeName");
            while (resultSet.next()) {
                salesTeamMap.put(resultSet.getString("LoginId"), resultSet.getString("EmployeeName"));
            }


        } catch (SQLException ex) {
            throw new ServiceLocatorException(ex);

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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        salesTeamMap = sortByValue(salesTeamMap);
        return salesTeamMap;
    }

    public int getOpportunityCountByLeadSourceId(int leadSourceid) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString;
        int count = 0;


        try {
            connection = ConnectionProvider.getInstance().getConnection();
            //System.out.println("marketingAction.getLeadId()-->"+marketingAction.getLeadId());

            queryString = "SELECT COUNT(Id) AS total FROM tblCrmOpportunity WHERE State!='Lost' AND LeadSourceId=" + leadSourceid;
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt("total");
            }

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
        return count;
    }

    public String getLeadTitleByLeadId(int leadId) throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        String leadTitle = "";
        //   queryString = "SELECT Id,Title FROM tblCrmLeads  WHERE AccountId=" + accountid + " ORDER BY Title";
        queryString = "SELECT Title FROM tblCrmLeads  WHERE Id=" + leadId;

        connection = ConnectionProvider.getInstance().getConnection();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            if (resultSet.next()) {
                leadTitle = resultSet.getString("Title");
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return leadTitle; // returning the object.
    }

    public Map getActiveInvestmentsMap() throws ServiceLocatorException {
        // System.err.println("I am in getEmployeeNamesByReportingPerson");
        Map investmentMap = new TreeMap();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            //   queryString = "SELECT Inv_Id,Inv_Name FROM tblInvestments WHERE STATUS LIKE 'Active'";
            queryString = "SELECT Inv_Id,Inv_Name FROM tblInvestments WHERE STATUS LIKE 'Active' ";
            preparedStatement = connection.prepareStatement(queryString);
            //preparedStatement = connection.prepareStatement(queryString);

            //preparedStatement.setString(1,department);

            //resultSet = preparedStatement.executeQuery();
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                investmentMap.put(resultSet.getInt("Inv_Id"), resultSet.getString("Inv_Name"));
            }
            investmentMap = sortByValue(investmentMap);

        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return investmentMap; // returning the object.
    } //closing the method.

    public Map getMarketingAnalystMap() throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;

        Map marketingMap = new LinkedHashMap();
        queryString = "SELECT LoginId,CONCAT(fName,' ',mName,'.',lName) AS EmployeeName FROM tblEmployee WHERE Id IN (SELECT EmpId FROM tblEmpRoles LEFT OUTER JOIN tblLKRoles ON (tblEmpRoles.RoleId=tblLKRoles.Id) WHERE Description='Marketing') AND CurStatus = 'Active' ORDER BY EmployeeName ";

        connection = ConnectionProvider.getInstance().getConnection();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                marketingMap.put(resultSet.getString("LoginId"), resultSet.getString("EmployeeName"));

            }
            marketingMap = sortByValue(marketingMap);

        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return marketingMap; // returning the object.
    }

    public String getInvestmentsNameById(int investmentId) throws ServiceLocatorException {
        // System.err.println("I am in getEmployeeNamesByReportingPerson");
        Map investmentMap = new TreeMap();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;
        String investmentTitle = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();

            //   queryString = "SELECT Inv_Id,Inv_Name FROM tblInvestments WHERE STATUS LIKE 'Active'";
            queryString = "SELECT Inv_Id,Inv_Name FROM tblInvestments WHERE Inv_Id=" + investmentId;
            preparedStatement = connection.prepareStatement(queryString);
            //preparedStatement = connection.prepareStatement(queryString);

            //preparedStatement.setString(1,department);

            //resultSet = preparedStatement.executeQuery();
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                investmentTitle = resultSet.getString("Inv_Name");
                //investmentMap.put(resultSet.getInt("Inv_Id"),resultSet.getString("Inv_Name"));
            }
            //investmentMap = sortByValue(investmentMap);

        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return investmentTitle; // returning the object.
    } //closing the method.

    public List getEmpNameCurStatusByEmpId(int empId) throws ServiceLocatorException {

        String employeeName = "";
        String status = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        List list = new ArrayList();
        try {
            queryString = "SELECT CONCAT(FName,' ',MName,'.',LName) AS EmpName,CurStatus FROM tblEmployee WHERE Id=" + empId;
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            if (resultSet.next()) {
                employeeName = resultSet.getString("EmpName");
                status = resultSet.getString("CurStatus");
                list.add(employeeName);
                list.add(status);
                //System.out.println("Dsdp empNo-->"+empNo);

            }
            //  Collections.sort(opportunityStateList);
        } catch (SQLException sqlex) {
            throw new ServiceLocatorException(sqlex);
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }

        }
        return list;
    }

    public Map getEmployeeLocationsLst() throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        connection = ConnectionProvider.getInstance().getConnection();
        Map locationList = new TreeMap();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT DISTINCT Location FROM tblLKEmpLocations WHERE STATUS='Active'");
            while (resultSet.next()) {
                locationList.put(resultSet.getString("Location"), resultSet.getString("Location"));


            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }

        return locationList; // returning the object.
    }

    public Map getEmployeeLocationsListCount() throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        connection = ConnectionProvider.getInstance().getConnection();
        Map locationList = new TreeMap();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT DISTINCT tblLKEmpLocations.Location,COUNT(tblEmployee.Location) As count  FROM tblEmployee JOIN tblLKEmpLocations ON (tblEmployee.Location=tblLKEmpLocations.Location) WHERE CurStatus='Active' GROUP BY tblEmployee.Location");
            while (resultSet.next()) {
                locationList.put(resultSet.getString("Location"), resultSet.getInt("count"));


            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }

        return locationList; // returning the object.
    }

    public int isAuthorizedForSyncing(String loginId, Map rolesMap) throws
            ServiceLocatorException {
        int isAuthorized = 0;
        try {
            if (rolesMap.containsValue("Admin")) {
                isAuthorized = 1;
            } else {
                String authorizedUsersList[] = Properties.getProperty("Authorization_For_ConstatContact").split("\\,");

                for (int i = 0; i < authorizedUsersList.length; i++) {
                    //System.out.println("bbbb"+authorizedUsersList[i].trim());
                    if (authorizedUsersList[i].trim().equals(loginId)) {
                        isAuthorized = 1;
                        break;
                    }
                }

            }
        } catch (Exception exception) {
            throw new ServiceLocatorException(exception);
        }
        // System.out.println("the values returning"+isAuthorized);
        return isAuthorized;
    }
     public String getReleaseStatusByMonthAndYear(int month,int year) throws ServiceLocatorException {

       
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String status ="";
        connection = ConnectionProvider.getInstance().getConnection();
          try {
        //queryString = "SELECT concat(FirstName,'.',LastName) as Name, LoginId FROM tblCatagory";
         String queryString = "SELECT STATUS FROM tblReleasePayslip WHERE MONTH(ReleasedFor)=" + month + " AND YEAR(ReleasedFor)=" + year;         
         System.out.println("query"+queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            if(resultSet.next()){
               status = resultSet.getString("status");
            }
            System.out.println("status --"+status);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return status;
    }
public boolean checkForPayrollDateForReleases(int month, int year) throws ServiceLocatorException {
        Statement statement = null;
        ResultSet resultSet = null;
        boolean flag = false;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT Payroll_Id FROM tblEmpWages WHERE MONTH(PayrollDate)=" + month + " AND YEAR(PayrollDate)= " + year ;
//System.out.println("queryString-->"+queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            if (resultSet.next()) {
                //  count = resultSet.getInt("id");
              //  System.out.println("if block");
                flag = true;
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return flag;
    }

public boolean checkIfSameMonthsRecordExists(int month, int year,String status) throws ServiceLocatorException {
        Statement statement = null;
        ResultSet resultSet = null;
        boolean flag = false;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT STATUS FROM tblReleasePayslip WHERE MONTH(ReleasedFor)=" + month + " AND YEAR(ReleasedFor)= " + year;
//System.out.println("quer"+queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            if (resultSet.next()) {
                //  count = resultSet.getInt("id");
                if((status).equals(resultSet.getString("STATUS"))){
                      flag = true;
                }
                                 
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return flag;
    }        
public Map getPayrollOrganisationMap(String orgKey) throws ServiceLocatorException {

        Map orgMap = new TreeMap();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT `Description`, `AccountId` FROM 	`mirage`.`tbllkpayrollorganization`";
            preparedStatement = connection.prepareStatement(queryString);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orgMap.put(new Integer(resultSet.getInt("AccountId")),
                        resultSet.getString("Description"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return orgMap; // returning the object.
    }


  public int isMcertQuestionAttempt(int examKeyId, int questionId) throws ServiceLocatorException {
        int count = 0;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT COUNT(id) as id FROM tblMcertSummary WHERE ExamKeyId = " + examKeyId + " AND QuestionId = " + questionId;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                count = resultSet.getInt("id");
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return count;
    }
  
  public int getMcertAnswer(int examKeyId, int questionId, int empId) throws ServiceLocatorException {
        int answer = 0;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT EmpAns as answer FROM tblMcertSummary WHERE ExamKeyId = " + examKeyId + " AND QuestionId = " + questionId + " AND EmpId = " + empId;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                answer = resultSet.getInt("answer");
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return answer;
    }

  public String getMcertConsultantNameById(int creConsultantId) throws ServiceLocatorException {
        Statement statement = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        ResultSet resultSet = null;
        String queryString = "SELECT CONCAT(FName, '.', LName) AS ConsultantName FROM tblMcertConsultant WHERE Id = " + creConsultantId;
        String consultantName = "";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            if (resultSet.next()) {
                consultantName = resultSet.getString("ConsultantName");
            }

        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return consultantName;
    }
	
 public Map getManagerProjectDashBoardAccountList(String objectId) throws ServiceLocatorException {

        Map myAccounts = new TreeMap();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            //  System.out.println("objectId-->"+objectId);
            connection = ConnectionProvider.getInstance().getConnection();

            //queryString = " SELECT DISTINCT tblProjectContacts.AccountId AS accountId,tblCrmAccount.Name AS acoountName FROM tblProjectContacts LEFT OUTER JOIN tblCrmAccount ON(tblProjectContacts.AccountId=tblCrmAccount.Id) WHERE objectId=" + objectId+" AND tblCrmAccount.STATUS='Active' AND tblProjectContacts.STATUS='Active'";
            queryString = " SELECT DISTINCT tblProjectContacts.AccountId AS accountId,tblCrmAccount.NAME AS acoountName FROM tblProjectContacts "
                    + "LEFT OUTER JOIN tblCrmAccount ON(tblProjectContacts.AccountId=tblCrmAccount.Id) "
                    + "LEFT OUTER JOIN tblProjects ON (tblProjects.Id=tblProjectContacts.ProjectId) "
                    + "WHERE objectId IN(SELECT Id FROM tblEmployee WHERE LoginId IN(" + objectId + "))  AND "
                    + "tblProjectContacts.STATUS='Active' AND tblProjects.STATUS='Active';";
          // System.out.println("queryString-->"+queryString);
            preparedStatement = connection.prepareStatement(queryString);
            //preparedStatement = connection.prepareStatement(queryString);

            //preparedStatement.setString(1,department);

            //resultSet = preparedStatement.executeQuery();
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                myAccounts.put(resultSet.getInt("accountId"),
                        resultSet.getString("acoountName"));
                // myAccounts.add(resultSet.getString("acoountName"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
        //System.out.print("myAccounts-->"+myAccounts);
        return myAccounts; // returning the object.
    }
 
 public String checkIsTeamLead(String LoginId) throws ServiceLocatorException, ParseException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
     String responseString = "";
     String queryString="";
 
        try {

            queryString = "SELECT Id,IsTeamLead,IsManager FROM tblEmployee WHERE loginId='"+LoginId+"'";
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.prepareStatement(queryString);
            resultSet = statement.executeQuery();


            while (resultSet.next()) {
               if(resultSet.getInt("IsTeamLead")==1 || resultSet.getInt("IsManager")==1)
               {
                   responseString="YES";
               }
               else
               {
                    responseString="NO";
               }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ServiceLocatorException sle) {
            sle.printStackTrace();
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
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return responseString;
    }
 public int getEmpNoDueRemainderStatus() throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
     String responseString = "";
     String queryString="";
 int flag=0;
        try {

            queryString = "SELECT Id FROM tblEmpNoDues WHERE flag=0;";
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.prepareStatement(queryString);
            resultSet = statement.executeQuery();


            if (resultSet.next()) {
              flag=1;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ServiceLocatorException sle) {
            sle.printStackTrace();
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
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return flag;
    }
public List getPracticeByDept(String dept) throws ServiceLocatorException {

        String practice = "";
        int id = 0;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        List list = new ArrayList();
      //  System.out.println("--->getPracticeByDept"+dept);
        try {
             queryString = "Select Description from tblLKPractice where DepartmentId = "
                + "(select id from tblLKDepartment where Description='" + dept + "') ORDER BY Description";
        connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while(resultSet.next()) {
                practice = resultSet.getString("Description");
              
               // list.add(id);
                list.add(practice);
                //System.out.println("Dsdp empNo-->"+empNo);

            }
       //    System.out.println("Dsdp queryString-->"+queryString);
        } catch (SQLException sqlex) {
            throw new ServiceLocatorException(sqlex);
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }

        }
        return list;
    }

 public String getLocationNameById(String locationId) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        String location = "";
       // System.out.println("location----"+locationId);
        connection = ConnectionProvider.getInstance().getConnection();
          try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT Location FROM tblLKEmpLocations WHERE id="+locationId);
            while (resultSet.next()) {
                 location = resultSet.getString("Location");


            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }

        return location; // returning the object.
    }

public int getOrgAccountIdByEmpID(int empId) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        String location = "";
        int id=0;
       // System.out.println("location----"+locationId);
        connection = ConnectionProvider.getInstance().getConnection();
          try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT tbllkpayrollorganization.AccountId FROM tbllkpayrollorganization LEFT JOIN tblEmployee ON (tbllkpayrollorganization.Description=tblEmployee.OrgId) WHERE Id="+empId);
            if (resultSet.next()) {
                 id = resultSet.getInt("AccountId");


            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }

        return id; // returning the object.
    }
public String getStringByList(List listString) throws ServiceLocatorException {
String commaString ="";
      
          try {
        for(int i=0;i<listString.size();i++){
                
                commaString =commaString+ listString.get(i) +",";
            }



if(!"".equals(commaString))
    commaString = commaString.substring(0,commaString.length()-1);
    } catch (Exception ex) {
                
            }
     return commaString; // returning the object.
    }

public int isExistedTaxExemtion(int empId, String rentStartDate, String restEndDate, String exemtionId,String FinancialYear) throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        int count = 0;
        // System.out.println("location----"+locationId);
        connection = ConnectionProvider.getInstance().getConnection();
        try {
            SimpleDateFormat parseFormat = new SimpleDateFormat("yyyy-MM-dd");

            java.util.Date givenStartDate = parseFormat.parse(rentStartDate);
            java.util.Date givenEndDate = parseFormat.parse(restEndDate);
            statement = connection.createStatement();
            queryString = "SELECT MAX(RentStartDate) as RentStartDate, MAX(RentEndDate) as RentEndDate FROM tblEmpTaxExemptionDetails WHERE EmpId=" + empId + " AND ExemptionId=" + exemtionId + " And IsActive='Active' AND FinancialYear='"+FinancialYear+"' AND Status!='Rejected'";
           // System.out.println("queryString--" + queryString);
            resultSet = statement.executeQuery(queryString);
            if (resultSet.next()) {
               
                
               
                
                if (resultSet.getString("RentStartDate") != null && !"".equals(resultSet.getString("RentStartDate")) && resultSet.getString("RentEndDate") != null && !"".equals(resultSet.getString("RentEndDate"))) {
                    java.util.Date dbStartDate = parseFormat.parse(resultSet.getString("RentStartDate"));
                    java.util.Date dbEndDate = parseFormat.parse(resultSet.getString("RentEndDate"));
                    if (givenStartDate.compareTo(dbStartDate) > 0 && givenStartDate.compareTo(dbEndDate) > 0) {
                        if (givenEndDate.compareTo(dbStartDate) > 0 && givenEndDate.compareTo(dbEndDate) > 0) {
                            count = 0;
                        } else {
                            count = 1;
                        }
                    } else {
                        count = 1;
                    }
                }

            }


        } catch (ParseException ex) {
            throw new ServiceLocatorException(ex);
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }

        return count;
    }

public String getContactNamesByIds(String contactIds) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        String ContactNames = "";
        String ContactName="-";
        String Title ="-";
       // System.out.println("location----"+locationId);
        connection = ConnectionProvider.getInstance().getConnection();
          try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT CONCAT(FirstName,' ',MiddleName,'.',LastName) AS ContactName,Title FROM tblCrmContact WHERE Id IN ("+contactIds+") ORDER BY FirstName");
           int i=0;
            while (resultSet.next()) {
                 Title = resultSet.getString("Title");
                 if(Title==null)
                     Title="-";
                 ContactName = resultSet.getString("ContactName");
                 if(ContactName==null)
                     ContactName = "-";
                 
                if(i==0)
                 ContactNames = "<font color=white>"+ContactName+"</font>"+"-"+"<font color=yellow>"+Title+"</font>";
                else
                    ContactNames = ContactNames+",<br><font color=white>"+ContactName+"</font>"+"-<font color=yellow>"+Title+"</font>";
i++;

            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }

        return ContactNames; // returning the object.
    }
public String getEmployeeLeavesWeekends(String payrollId,String year) throws ServiceLocatorException{
     Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        String result = "";
        
        connection = ConnectionProvider.getInstance().getConnection();
          try {
            statement = connection.createStatement();
            queryString = "SELECT SUM(DaysVacation) AS DaysVacation,SUM(Daysweekends) AS Daysweekends,SUM(LeavesAvailable) AS LeavesAvailable,SUM(DaysHolidays) AS DaysHolidays FROM tblEmpWages WHERE YEAR(PayrollDate) ="+ year +" AND Payroll_Id = "+ payrollId;
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                String leaves = resultSet.getString("DaysVacation");
                String availWeekends = resultSet.getString("Daysweekends");
                 String availLeaves = resultSet.getString("LeavesAvailable");
                  String availHolidays = resultSet.getString("DaysHolidays");
                if(leaves == null || "".equals(leaves)){
                    leaves = "0";
                }
                if(availWeekends == null || "".equals(availWeekends)){
                    availWeekends = "0";
                }
                if(availLeaves == null || "".equals(availLeaves)){
                    availLeaves = "0";
                }
                if(availHolidays == null || "".equals(availHolidays)){
                    availHolidays = "0";
                }
                 result = leaves + "#" + availWeekends + "#" + availLeaves + "#" + availHolidays ;
System.out.println("result----"+result);

            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }

        return result; // returning the object.
}


 public String getEmailIdsForLoginIds(String LoginIds) throws ServiceLocatorException {

        //  System.out.println("LoginId"+LoginId);
        //String projectType = "";
        String emailId = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
   
     String queryString ="Select Email1 from tblEmployee WHERE FIND_IN_SET(LoginId,'"+LoginIds+"') ";
  
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            int i=0;
            while (resultSet.next()) {
                if(i==0)
                   emailId =  resultSet.getString("Email1");
                else
                emailId = emailId+","+resultSet.getString("Email1");
               i++;
            }
          //  System.out.println("emailId.trim().length()"+emailId.trim().length());
           // if(emailId.trim().length()<=0){
           // emailId=emailId.substring(0, emailId.length()-1);
          //  }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
       // System.out.println("getEmailIdsForLoginIds"+emailId);
        return emailId;
    }


 public Map getEmpDetailsLoginIds(String LoginIds) throws ServiceLocatorException {

        //  System.out.println("LoginId"+LoginId);
       Map Empdetails = new TreeMap();
      
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
 

     String queryString ="Select concat(FName,'.',LName) as Name,Email1 from tblEmployee WHERE FIND_IN_SET(LoginId,'"+LoginIds+"') ";
  
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
               Empdetails.put(resultSet.getString("Email1"), resultSet.getString("Name"));
               
            }
           
          
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
      // System.out.println("Empdetails"+Empdetails);
        return Empdetails;
    }
 
  /*Current status new changes start */
 public String getAvailableStartDates(String empId,java.sql.Date assignedStartDate) throws ServiceLocatorException{
       Connection connection = null;
        PreparedStatement statement = null;
         ResultSet resultSet = null;
       java.sql.Date  startDate = null;
       java.sql.Date  endDate = null;
       String response="";
        String TABLE_EMP_STATE_HISTORY = Properties.getProperty("TABLE_EMP_STATE_HISTORY");
       
       
      int rcount = 0;
        try {
 
             
           String  queryString = "SELECT StartDate,EndDate FROM "+TABLE_EMP_STATE_HISTORY+" WHERE EmpId='"+empId+"' AND State='Available' ORDER BY StartDate DESC LIMIT 1";

                connection = ConnectionProvider.getInstance().getConnection();
                statement = connection.prepareStatement(queryString);
              //  System.out.println("getAvailableStartDates in dsdp"+queryString);
                 resultSet = statement.executeQuery();
            while (resultSet.next()) {
                startDate = resultSet.getDate("StartDate");
                endDate=resultSet.getDate("EndDate");
            }
            if ((startDate != null && !"".equalsIgnoreCase(startDate.toString())) && (endDate != null && !"".equalsIgnoreCase(endDate.toString()))  ) {
                        if (assignedStartDate.compareTo(endDate) <= 0) {
                      
                       response= "<font size='2' color='red'> Start date should be greater than  Employee available end date'" + com.mss.mirage.util.DateUtility.getInstance().convertToviewFormat(endDate.toString()) + "' !</font>";
                           
                        }
                        }else if ((startDate != null && !"".equalsIgnoreCase(startDate.toString()))  ) {
                        if (assignedStartDate.compareTo(startDate) < 0) {
                      response="<font size='2' color='red'> Start date should be greater than or equal to Employee available start date'" + com.mss.mirage.util.DateUtility.getInstance().convertToviewFormat(startDate.toString()) + "' !</font>";
                              
                        }
                        }
            
            
           
           // System.out.println("getAvailableStartDates in dsdp"+startDate);
                        
            
        }catch (Exception sqe) {
            sqe.printStackTrace();
          
        } finally {
            try {

                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
            }
        }
        return response;
       
   } 
 
 
 public java.sql.Date getHireDateOfEmployeeBeforeAddingCurrentStatus(int empId)  throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
       java.sql.Date hireDate=null;
       
       
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            String QUERY_STRING = "SELECT HireDate FROM tblEmployee WHERE Id= " + empId+" ";
           // QUERY_STRING = QUERY_STRING + "  AND  EmpId = " + contactId;
            preparedStatement = connection.prepareStatement(QUERY_STRING);
            resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next()){
              if(resultSet.getDate("HireDate")!=null && !"".equals(resultSet.getDate("HireDate"))){
                hireDate=resultSet.getDate("HireDate");  
              } 
           }
            
            
          //  System.out.println("result"+result);
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        }finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if(resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                
                if(preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                
                if(connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        
        }
       
     // System.out.println("count---"+count);
        return hireDate; // returning the object.
    }

 public int getAvailableState(int contactId) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
         String TABLE_EMP_STATE_HISTORY = Properties.getProperty("TABLE_EMP_STATE_HISTORY");
               try {

                   connection = ConnectionProvider.getInstance().getConnection();
            String QUERY_STRING = "SELECT COUNT(*) AS AvlCount FROM "+TABLE_EMP_STATE_HISTORY+"  WHERE EmpId = " + contactId + "  AND ProjectStatus='Active' AND state='Available' ORDER BY StartDate DESC ";
             preparedStatement = connection.prepareStatement(QUERY_STRING);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
               System.out.println("  count....  "+resultSet.getInt("AvlCount"));
               
                    count = resultSet.getInt("AvlCount");
                }


            //  System.out.println("result"+result);
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        // System.out.println("count---"+count);
        return count; // returning the object.
    } //closing the method. 	
 
 /*Currentstatus new changes end */

 public double getHRExemptionYearly(int empId,int month,int year) throws ServiceLocatorException {

        Connection connection = null;

        CallableStatement callableStatement = null;

        double yearlyAmount=0.00;
       
        try {





            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{call spGetHRExemptionYearly(?,?,?,?) }");

            callableStatement.setInt(1, empId);
            callableStatement.setInt(2, month);
            callableStatement.setInt(3, year);
           
            callableStatement.registerOutParameter(4, Types.DOUBLE);

             callableStatement.executeUpdate();

            yearlyAmount = callableStatement.getDouble(4);

           



        } catch (SQLException ex) {
            ex.printStackTrace();
           yearlyAmount=0;

        } catch (ServiceLocatorException sle) {
            sle.printStackTrace();
        } finally {
            try {
            if (callableStatement != null) {
                    callableStatement.close();
                    callableStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
                
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return yearlyAmount;

    }
 
  public List getRequirementStatusList() throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        List lKRequirementStatus = new ArrayList();//Description

       
        queryString = "SELECT Description FROM tblLKRequirementStatus";

        connection = ConnectionProvider.getInstance().getConnection();
    // System.out.println("queryString----"+queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                lKRequirementStatus.add(resultSet.getString("Description"));
            }
            Collections.sort(lKRequirementStatus);

        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return lKRequirementStatus; // returning the object.
    }

 public String getEmployeeNamesByLoginIdList(PreparedStatement preparedStatement,String ResourceIds)  throws ServiceLocatorException {
        ResultSet resultSet = null;
       
       String names="";
        try {
           preparedStatement.setString(1, ResourceIds);
            resultSet = preparedStatement.executeQuery();
            int i=0;
            while(resultSet.next()){
                if(i==0)
                    names = resultSet.getString("NAME");
                else
          names=names+ ",\n"+resultSet.getString("NAME") ;
                i++;
           }
        
           // names = StringUtils.chop(names); 
          
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        }finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if(resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                
              
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        
        }
       
     // System.out.println("count---"+count);
        return names; // returning the object.
    }
 
public Map getAllBDMLoginIds() throws ServiceLocatorException {


        Map bdmMap = new LinkedHashMap();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
       
        String queryString = "";
       
       
         try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT LoginId,CONCAT(TRIM(FName),'.',TRIM(LName)) AS EmployeeName FROM"
                    + " tblEmployee WHERE CurStatus='Active' AND TitleTypeId='BDM' ORDER BY EmployeeName";
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bdmMap.put(resultSet.getString("LoginId"), resultSet.getString("EmployeeName"));
              
            }

        } catch (Exception exception) {
            exception.printStackTrace();
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        bdmMap = DataSourceDataProvider.getInstance().sortMapByValues(bdmMap);

        return bdmMap;
    }

	
	public String getContactNamesByIdsForBdm(String contactIds) throws ServiceLocatorException {
      // System.out.println("getContactNamesByIds.."+contactIds);
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String ContactNames = "";
        String ContactName="-";
      
       // System.out.println("location----"+locationId);
        connection = ConnectionProvider.getInstance().getConnection();
          try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT CONCAT(FirstName,' ',MiddleName,'.',LastName) AS ContactName FROM tblCrmContact WHERE Id IN ("+contactIds+") ORDER BY FirstName");
           int i=0;
            while (resultSet.next()) {
                  ContactName = resultSet.getString("ContactName");
                 if(ContactName==null)
                     ContactName = "-";
                 
                if(i==0)
                 ContactNames =ContactName;
                else
                    ContactNames = ContactNames+","+ContactName;
i++;

            }
             // System.out.println("ContactNames.."+ContactNames);

        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }

        return ContactNames; // returning the object.
    }
	public Map getActiveIndividualCustomerContacts(String customerIds) throws ServiceLocatorException {


        Map contactsMap = new LinkedHashMap();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
       
        String queryString = "";
       
       
         try {
            connection = ConnectionProvider.getInstance().getConnection();
            
            
            queryString = "select count(tblCrmContact.Id) As Count ,tblCrmAccount.Name As Name from  tblCrmContact left join tblCrmAccount on(tblCrmContact.AccountId=tblCrmAccount.Id)"
                    + " where tblCrmContact.AccountId in("+customerIds+") group by AccountId";
              //  System.out.println(queryString); 
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                contactsMap.put(resultSet.getString("Name"), resultSet.getString("Count"));
              
            }

        } catch (Exception exception) {
            exception.printStackTrace();
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

       

        return contactsMap;
    }
 public Map getInsideSalesBDEByLoginId() throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        Map preSalesMap = new HashMap();//Description
        queryString = "SELECT tblEmployee.LoginId,CONCAT(fName,' ',mName,'.',lName) AS NAME FROM tblEmployee WHERE PracticeId='Inside' AND TitleTypeId  IN ('BDE','Sales Trainee') AND tblEmployee.CurStatus = 'Active' ORDER BY NAME";

        connection = ConnectionProvider.getInstance().getConnection();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                preSalesMap.put(resultSet.getString("LoginId"), resultSet.getString("Name"));
            }

preSalesMap = sortByValue(preSalesMap);
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return preSalesMap; // returning the object.
    }
public String accountTeamMemberEmails(int  accountId) throws ServiceLocatorException {

        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String queryString = null;
        String responseString = "";
       
        queryString = " SELECT Email1 FROM tblEmployee WHERE LoginId IN (SELECT TeamMemberId FROM tblCrmAccountTeam WHERE AccountId=?)";

        try {
           
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, accountId);
            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                responseString = responseString+resultSet.getString("Email1")+",";

            }
        if(!"".equals(responseString)){
        responseString=responseString.substring(0, responseString.length()-1);
        }
            
        } catch (SQLException sqle) {
            throw new ServiceLocatorException(sqle);
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
            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }

        return responseString;
    }

   public String getInvestmentNamesById(int id) throws ServiceLocatorException {
        // System.err.println("I am in getEmployeeNamesByReportingPerson");

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;
String inv_Name="";
        try {
            connection = ConnectionProvider.getInstance().getConnection();

           
            queryString = "SELECT Inv_Name FROM tblInvestments WHERE Inv_Id=?";
            preparedStatement = connection.prepareStatement(queryString);
       

            preparedStatement.setInt(1,id);

         
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                inv_Name=resultSet.getString("Inv_Name");
               
            }
          

        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return inv_Name; // returning the object.
    } //closing the method.

     public int getProjectActiveResourcesByCustomer(int customerId,String status) throws ServiceLocatorException {
        Statement statement = null;
        ResultSet resultSet = null;
        int totalResources = 0;
        Connection connection = ConnectionProvider.getInstance().getConnection();
       // String queryString = "SELECT COUNT(Id) AS Total FROM tblProjectContacts  WHERE tblProjectContacts.STATUS = 'Active' AND AccountId= " + customerId;
         if("".equals(status)){
          status="%";  
        }
      //  String queryString = "SELECT COUNT(tblProjectContacts.Id) AS Total FROM tblProjectContacts LEFT JOIN tblProjects ON tblProjects.Id=tblProjectContacts.ProjectId  WHERE tblProjectContacts.STATUS = 'Active' AND AccountId="+customerId+"  AND tblProjects.STATUS LIKE '"+status+"'";
     //  String queryString = "SELECT COUNT(tblProjectContacts.Id) AS Total FROM tblProjectContacts LEFT JOIN tblProjects ON tblProjects.Id=tblProjectContacts.ProjectId  WHERE   ObjectType='E' AND tblProjectContacts.STATUS = 'Active' AND AccountId="+customerId+"  AND tblProjects.STATUS LIKE '"+status+"'";
 
  String queryString = "SELECT COUNT(tblProjectContacts.Id) AS Total FROM tblProjectContacts LEFT  JOIN tblEmployee ON tblEmployee.Id=tblProjectContacts.ObjectId  LEFT JOIN tblProjects ON tblProjects.Id=tblProjectContacts.ProjectId  WHERE   ObjectType='E' AND tblProjectContacts.STATUS = 'Active' AND AccountId="+customerId+"  AND tblProjects.STATUS LIKE '"+status+"' AND CurStatus='Active'";
         try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            if (resultSet.next()) {
                totalResources = resultSet.getInt("Total");
            }


        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return totalResources;
    }

  public Map getCustomerMap() throws ServiceLocatorException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String queryString = null;
        ResultSet resultSet = null;
        String consultantStatus = "";
        Map clientMap = new LinkedHashMap();
        try {
            connection = ConnectionProvider.getInstance().getConnection();

          //  queryString = "SELECT tblCrmAccount.Id AS Id,tblCrmAccount.NAME AS CustomerName FROM tblCrmAccount  LEFT JOIN tblRecRequirement ON tblCrmAccount.Id = tblRecRequirement.CustomerId ORDER BY CustomerName";
            queryString = "SELECT DISTINCT tblCrmAccount.Id AS Id,tblCrmAccount.NAME AS CustomerName FROM tblCrmAccount INNER JOIN tblProjects ON(tblCrmAccount.Id=tblProjects.CustomerId) ORDER BY tblCrmAccount.NAME";
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                clientMap.put(resultSet.getInt("Id"), resultSet.getString("CustomerName"));

            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return clientMap;
    }
  public String getCustometNameById(int id) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;
        String resultString = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT Name FROM tblCrmAccount WHERE Id = " + id;
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                resultString = resultSet.getString("Name");

            }
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return resultString;
    }
  
  
  public Map getOpsContactIdByCountry(String country) throws ServiceLocatorException {
        Map employeeMap = new TreeMap();//key-Description
        Connection connection = null;

        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        String queryString = null;


        try {
            connection = ConnectionProvider.getInstance().getConnection();
          
                queryString = "SELECT Id,CONCAT(LName,'.',FName,' ',MName) AS EmpName FROM tblEmployee WHERE IsOperationContactTeam=1 AND country LIKE '" + country + "'";
            preparedStatement = connection.prepareStatement(queryString);

            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                employeeMap.put(resultSet.getString("Id"),
                        resultSet.getString("EmpName"));
            }
            employeeMap = DataUtility.getInstance().getMapSortByValue(employeeMap);
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return employeeMap;

    }
public String getEmpLeavesForTheGivenWeek(String empId,String wsDate,String weDate) throws ServiceLocatorException, ParseException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        String response="";

        try {
            connection = ConnectionProvider.getInstance().getConnection();

        
          String query = "SELECT DATE(StartDate) AS dbStartDate,DATE(EndDate) AS dbEndDate,leaveType,STATUS FROM tblEmpLeaves WHERE EmpId='"+empId+"' AND STATUS NOT IN('Cancelled') AND leaveType='Vacation'";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
             while (resultSet.next()) {
                  int temp=0;
                if ((com.mss.mirage.util.DateUtility.getInstance().convertStringToMySQLDate(wsDate).compareTo(resultSet.getString("dbStartDate")) >= 0 && com.mss.mirage.util.DateUtility.getInstance().convertStringToMySQLDate(wsDate).compareTo(resultSet.getString("dbEndDate")) <= 0) || (com.mss.mirage.util.DateUtility.getInstance().convertStringToMySQLDate(weDate).compareTo(resultSet.getString("dbStartDate")) >= 0 && com.mss.mirage.util.DateUtility.getInstance().convertStringToMySQLDate(weDate).compareTo(resultSet.getString("dbEndDate")) <= 0)) {
                       response = response +com.mss.mirage.util.DateUtility.getInstance().convertToviewFormat(resultSet.getString("dbStartDate")) +"|"+com.mss.mirage.util.DateUtility.getInstance().convertToviewFormat(resultSet.getString("dbEndDate"))+"|"+resultSet.getString("leaveType") +"^";
                temp=1;    
                }
                
                 if ((com.mss.mirage.util.DateUtility.getInstance().convertStringToMySQLDate(wsDate).compareTo(resultSet.getString("dbStartDate")) <= 0 && com.mss.mirage.util.DateUtility.getInstance().convertStringToMySQLDate(wsDate).compareTo(resultSet.getString("dbEndDate")) <= 0) || (com.mss.mirage.util.DateUtility.getInstance().convertStringToMySQLDate(weDate).compareTo(resultSet.getString("dbStartDate")) >= 0 && com.mss.mirage.util.DateUtility.getInstance().convertStringToMySQLDate(weDate).compareTo(resultSet.getString("dbEndDate")) <= 0)) {
                    if(temp==0){
                     response = response +com.mss.mirage.util.DateUtility.getInstance().convertToviewFormat(resultSet.getString("dbStartDate")) +"|"+com.mss.mirage.util.DateUtility.getInstance().convertToviewFormat(resultSet.getString("dbEndDate"))+"|"+resultSet.getString("leaveType") +"^";
                    }
                    }
                 }
            
            if("".equals(response)){
          response="NotExist";
                  }

          } catch (SQLException sle) {
            sle.printStackTrace();
        } catch (ServiceLocatorException sle) {
            sle.printStackTrace();
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
                ex.printStackTrace();
            }
        }
        //  System.out.println("Team: "+reportsToBuffer.toString());
        return response;
    }
public Map getEmployeeNamesByCountry(String workingCountry) throws ServiceLocatorException {


        Map employeeMap = new LinkedHashMap();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT Id,CONCAT(TRIM(FName),'.',TRIM(LName)) AS EmployeeName,isManager,isTeamLead FROM"
                    + " tblEmployee WHERE CurStatus='Active' AND DeletedFlag != 1 AND Country LIKE '" + workingCountry + "'  ORDER BY EmployeeName";
            preparedStatement = connection.prepareStatement(queryString);

            //System.out.println("query-"+queryString);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employeeMap.put(resultSet.getString("Id"), resultSet.getString("EmployeeName"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return employeeMap; // returning the object.
    }



public String getAttachmentDetails(int exemptionId) throws ServiceLocatorException{
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;
        String fileLocation = "";
        String fileName = "";
        String filePath = "";
   
        try {
            connection = ConnectionProvider.getInstance().getConnection();

           
            queryString = "SELECT AttachmentName,AttachmentLocation FROM tblEmpTaxExemptionDetails WHERE Id =?";
            preparedStatement = connection.prepareStatement(queryString);
       

            preparedStatement.setInt(1,exemptionId);
             resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                fileName = resultSet.getString("AttachmentName");
                filePath = resultSet.getString("AttachmentLocation");
            }
            if(fileName != null && filePath != null && !"".equals(fileName) && !"".equals(filePath)){
                fileLocation = fileName + "#" + filePath;
            }
            else{
                fileLocation = null;
            }
         
           
       //    System.out.println("fileLocation--->" + fileLocation);
          

        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
        return fileLocation;
    }
    
      public String getAttachmentDetailsForm12BB(int exemptionId) throws ServiceLocatorException{
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;
        String fileLocation = "";
        String fileName = "";
        String filePath = "";
   
        try {
            connection = ConnectionProvider.getInstance().getConnection();

           
            queryString = "SELECT Form12BBAttachmentName,Form12BBAttachmentPath FROM tblEmpTaxExemptionDetails WHERE Id =?";
            preparedStatement = connection.prepareStatement(queryString);
       

            preparedStatement.setInt(1,exemptionId);
             resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                fileName = resultSet.getString("Form12BBAttachmentName");
                filePath = resultSet.getString("Form12BBAttachmentPath");
            }
 if(fileName != null && filePath != null && !"".equals(fileName) && !"".equals(filePath)){
                fileLocation = fileName + "#" + filePath;
            }
            else{
                fileLocation = null;
            }
           
       //    System.out.println("fileLocation--->" + fileLocation);
          

        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
        return fileLocation;
    }

public List getProjectRollsOfEmpoyee() throws ServiceLocatorException {
      //  System.out.println("states list");
        List projectRollsist = new ArrayList();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        queryString = "SELECT NAME FROM tblLKEmpRollInProject WHERE STATUS='Active'";
        connection = ConnectionProvider.getInstance().getConnection();
        // System.out.println("query of states list is---->"+queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                // String contactList[]=resultSet.getString("countrycurrency"));
                projectRollsist.add(resultSet.getString("NAME"));
            }
        } catch (SQLException sqlex) {
            throw new ServiceLocatorException(sqlex);
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
        return projectRollsist;
    }
 public List getEmployeeInfoById(String Id) throws ServiceLocatorException {
        List list=new ArrayList();
        Connection connection = null;

        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        String queryString = null;


        try {
            connection = ConnectionProvider.getInstance().getConnection();
          
                queryString = "SELECT CONCAT (FName,'.',LName) AS EmpName,Itgbatch,HireDate,IsLateral,practiceId,TitleTypeId FROM tblEmployee WHERE Id="+Id+"";
            preparedStatement = connection.prepareStatement(queryString);

            resultSet = preparedStatement.executeQuery();

String EmpName="";
String Itgbatch="";
String HireDate="";
String IsLateral="";
String practiceId="";
String TitleTypeId="";
            while (resultSet.next()) {
                if(resultSet.getString("EmpName")!=null && !"".equals(resultSet.getString("EmpName").trim())){
                    EmpName=resultSet.getString("EmpName");
                }
                if(resultSet.getString("Itgbatch")!=null && !"".equals(resultSet.getString("Itgbatch").trim())){
                    Itgbatch=resultSet.getString("Itgbatch");
                }
                if(resultSet.getString("HireDate")!=null && !"".equals(resultSet.getString("HireDate").trim())){
                    HireDate=resultSet.getString("HireDate");
                }
                if(resultSet.getString("IsLateral")!=null && !"".equals(resultSet.getString("IsLateral").trim())){
                    IsLateral=resultSet.getString("IsLateral");
                }
                if(resultSet.getString("practiceId")!=null && !"".equals(resultSet.getString("practiceId").trim())){
                    practiceId=resultSet.getString("practiceId");
                }
                if(resultSet.getString("TitleTypeId")!=null && !"".equals(resultSet.getString("TitleTypeId").trim())){
                    TitleTypeId=resultSet.getString("TitleTypeId");
                }
                
                list.add(EmpName);
                list.add(Itgbatch);
                list.add(HireDate);
                list.add(IsLateral);
                list.add(practiceId);
                list.add(TitleTypeId);
            }

        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return list;

    }
 
  public List getAppraisalIds(int empId) throws ServiceLocatorException {
        ArrayList list=new ArrayList();
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT MAX(id) AS id,MAX(AppraisalId) AS AppraisalId FROM tblQuarterlyAppraisals WHERE EmpId="+empId;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                list.add(resultSet.getInt("id"));
                list.add(resultSet.getInt("AppraisalId"));
            }


        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }

        return list;
    }
  
   public Map getAllEmployeesByCountry(String Country) throws ServiceLocatorException {

        Map employeeMap = new LinkedHashMap();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT LoginId,CONCAT(TRIM(FName),'.',TRIM(LName)) AS EmployeeName FROM"
                    + " tblEmployee WHERE CurStatus='Active' AND DeletedFlag != 1 AND Country=? ORDER BY EmployeeName";
            preparedStatement = connection.prepareStatement(queryString);
preparedStatement.setString(1, Country);
            //System.out.println("query-"+queryString);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employeeMap.put(resultSet.getString("LoginId"), resultSet.getString("EmployeeName"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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

        return employeeMap; // returning the object.
    }
   
  /*Method for getting contacts Map for not having emailIds
     * Date : 04/17/2017
     * Author : Santosh Kola
     */
    public Map getWotEmailContactsMap(int accId) throws ServiceLocatorException {

        
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;
        Map wotEmailContactsMap = new HashMap();
        try {
            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT Id,CONCAT (FirstName,' ',MiddleName,'.',LastName) AS ContactsName FROM tblCrmContact WHERE Email1 NOT LIKE '%@%' AND AccountId ="+accId;
                    
            preparedStatement = connection.prepareStatement(queryString);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                wotEmailContactsMap.put(resultSet.getInt("Id"),resultSet.getString("ContactsName"));
            }
// contactMap = sortByValue(contactMap);

           
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
// resultSet Object Checking if it's null then close and set null
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

        return wotEmailContactsMap; // returning the object.
    } //closing the method.
	public String getWorkPhNoById(String Id) throws ServiceLocatorException {
        String workPh = "";
        String queryString = "";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT WorkPhoneNo from tblEmployee where Id='" + Id + "'";
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                workPh = resultSet.getString("WorkPhoneNo");
            }
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
        return workPh;
    }
 public Map getBdmAssociateList(String empId)throws ServiceLocatorException {

       Map bdmAssociateTeamMap =new LinkedHashMap();
        bdmAssociateTeamMap.clear();
        //Map sortedMap = new LinkedHashMap();
        //sortedMap.clear();
        ResultSet resultSet = null;
        Connection connection = null;
        Statement statement = null;
        
        
         
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
//            resultSet = statement.executeQuery("SELECT Id,CONCAT(TRIM(FName),'.',TRIM(LName)) AS EmployeeName FROM tblEmployee WHERE CurStatus ='Active' AND DepartmentId='Sales' ORDER BY FName ");
            resultSet = statement.executeQuery(" SELECT CONCAT(TRIM(FName),'.',TRIM(LName)) AS EmployeeName,tblEmployee.LoginId FROM tblEmployee LEFT JOIN tblBDMAssociates ON(tblEmployee.Id=tblBDMAssociates.TeamMemberId) WHERE tblBDMAssociates.BdmId='"+empId+"' AND tblBDMAssociates.STATUS='Active'");
        
            while(resultSet.next()) {
             
                   bdmAssociateTeamMap.put(resultSet.getString("LoginId"),resultSet.getString("EmployeeName"));
                  // System.out.println("bdmAssociateTeamMap before iss is---->"+bdmAssociateTeamMap);
                 /*    List list = new LinkedList( bdmAssociateTeamMap.entrySet());
                     
                      Collections.sort(list, new Comparator() {
                       public int compare(Object o1, Object o2) {
	           return ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());
             }
	});
 
           
	for (Iterator it = list.iterator(); it.hasNext();) {
	     Map.Entry entry = (Map.Entry)it.next();
             bdmAssociateTeamMap.put(entry.getKey(), entry.getValue());         
                }
             */
        }
        
        // CacheManager.getCache().put("allSalesTeamExceptAccountTeam",salesTeamExceptAccountTeamMap);
        
        } catch (SQLException ex) {
            throw new ServiceLocatorException(ex);
        } finally {
            try{
                if(resultSet != null){
                    resultSet.close();
                    resultSet = null;
                }
                if(statement != null){
                    statement.close();
                    statement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
         
      //  System.out.println("bdmAssociateTeamMap is---->"+bdmAssociateTeamMap);
        return sortByValue(bdmAssociateTeamMap);// returning the object.
    }
public Map getBdmTeam(String bdmId) throws ServiceLocatorException {


        Map bdmMap = new LinkedHashMap();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String message = "";
        boolean isUpdated = false;
        String queryString = "";
        //  String queryString ="";
        // System.out.println("first call");
        String data = "";
        //queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ModifiedBy=?,ModifiedDate=?,ReviewName=?,ReviewDate=?,Status=?,TLComments=?,TLRating=?,HRRating=?,HrComments=? where Id=?";
        //if(roleName.equalsIgnoreCase("Employee"))
        // queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ReviewName=?,ReviewDate=?,Status=? where Id=?";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
//            queryString = "SELECT tblEmployee.Id,CONCAT(TRIM(FName),'.',TRIM(LName)) AS EmployeeName FROM tblBDMAssociates LEFT JOIN tblEmployee ON(tblBDMAssociates.BdmId=tblEmployee.Id)";
            queryString = "SELECT tblEmployee.Id,CONCAT(TRIM(FName),'.',TRIM(LName)) AS EmployeeName FROM tblEmployee LEFT JOIN tblBDMAssociates ON(tblEmployee.Id=tblBDMAssociates.TeamMemberId) WHERE tblBDMAssociates.BdmId='" + bdmId + "'";
//           SELECT tblEmployee.Id,CONCAT(TRIM(FName),'.',TRIM(LName)) AS EmployeeName FROM tblEmployee LEFT JOIN tblBDMAssociates ON(tblEmployee.Id=tblBDMAssociates.TeamMemberId) WHERE tblBDMAssociates.BdmId=4143
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
           // System.out.println("queryString--->"+queryString);
            //subJson.put(resultSet.getString("SpeakerId"), resultSet.getString("SpeakerName"));
            while (resultSet.next()) {
                //  if(!investmentBDMMap.containsKey(resultSet.getString("Id"))){
                bdmMap.put(resultSet.getString("Id"), resultSet.getString("EmployeeName"));
                //}
            }

        } catch (Exception exception) {
            exception.printStackTrace();
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        bdmMap = DataSourceDataProvider.getInstance().sortMapByValues(bdmMap);

        return bdmMap;
    }
public Map getBdmSalesAssignedTeam(Map bdmTeam) throws ServiceLocatorException {
       
           
        Map salesTeamExceptBdmTeamMap =new LinkedHashMap();
        salesTeamExceptBdmTeamMap.clear();
        //Map sortedMap = new LinkedHashMap();
        //sortedMap.clear();
        ResultSet resultSet = null;
        Connection connection = null;
        Statement statement = null;
        
        
         
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT Id,CONCAT(TRIM(FName),'.',TRIM(LName)) AS EmployeeName FROM tblEmployee WHERE CurStatus ='Active' AND DepartmentId='Sales' ORDER BY FName ");
            while(resultSet.next()) {
                if(!(bdmTeam.containsKey(resultSet.getString("Id")))){
                   salesTeamExceptBdmTeamMap.put(resultSet.getString("Id"),resultSet.getString("EmployeeName"));
                    /* List list = new LinkedList( salesTeamExceptBdmTeamMap.entrySet());
                     
                      Collections.sort(list, new Comparator() {
                       public int compare(Object o1, Object o2) {
	           return ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());
             }
	});
 
           
	for (Iterator it = list.iterator(); it.hasNext();) {
	     Map.Entry entry = (Map.Entry)it.next();
             salesTeamExceptBdmTeamMap.put(entry.getKey(), entry.getValue());         
                }
              }  
                     
                     */
                }
        }
        
        // CacheManager.getCache().put("allSalesTeamExceptAccountTeam",salesTeamExceptAccountTeamMap);
        
        } catch (SQLException ex) {
            throw new ServiceLocatorException(ex);
        } finally {
            try{
                if(resultSet != null){
                    resultSet.close();
                    resultSet = null;
                }
                if(statement != null){
                    statement.close();
                    statement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
         
        
        return sortByValue(salesTeamExceptBdmTeamMap);
    }

}