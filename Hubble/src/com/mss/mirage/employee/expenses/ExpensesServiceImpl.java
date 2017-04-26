/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.employee.expenses;

import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.ServiceLocatorException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author miracle
 */
public class ExpensesServiceImpl implements ExpensesService {
    
      public int checkAction(int empId) throws ServiceLocatorException {
        int count=0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        String query="SELECT id FROM tblEmpDeductions WHERE EmpId ="+empId;
        try{
            connection =ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
              count++;
            }
            
           
        }catch(Exception e){
            throw new ServiceLocatorException(e);
        }finally{
            try {
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
            } catch (SQLException ex) {
               throw new ServiceLocatorException(ex);
            }
        }
        
        return count;
    }
       public ExpensesVTO getExpenses(int empId,int expId)throws ServiceLocatorException{        
        ExpensesVTO expensesVTO = new ExpensesVTO();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int updatedRows=0;               
        try{
            connection = ConnectionProvider.getInstance().getConnection();
         
        String queryString ="select Accommodation,RoomNo,RoomFee,Cafeteria,CafeteriaFee,Transportation,TransLocation,TransFee,OccupancyType,DateOfOccupancy,ElecrricalCharges from tblEmpDeductions where Id="+expId+" and EmpId="+empId ;
           
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            expensesVTO.setEmpId(empId);
            if(resultSet.next()){
                expensesVTO.setAccommodation(resultSet.getString("Accommodation"));
                expensesVTO.setRoomNo(resultSet.getString("RoomNo"));
                expensesVTO.setRoomFee(resultSet.getDouble("RoomFee"));
                expensesVTO.setCafeteria(resultSet.getString("Cafeteria"));
                expensesVTO.setCafeteriaFee(resultSet.getDouble("CafeteriaFee"));
                 expensesVTO.setTransportation(resultSet.getString("Transportation"));
                 expensesVTO.setTransportLocation(resultSet.getString("TransLocation"));
                       expensesVTO.setTransportFee(resultSet.getDouble("TransFee"));
                        if(resultSet.getString("DateOfOccupancy")!=null&&!"".equals(resultSet.getString("DateOfOccupancy")))
                        expensesVTO.setDateOfOccupancy(DateUtility.getInstance().convertToviewFormat(resultSet.getString("DateOfOccupancy")));
                       expensesVTO.setOccupancyType(resultSet.getString("OccupancyType"));
                       expensesVTO.setElectricalCharges(resultSet.getDouble("ElecrricalCharges"));

               
            }
            
          
            
        }catch(SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
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
            } catch(SQLException se){
                se.printStackTrace();
            }
        }
        
        return expensesVTO;
    }

    public int addExpenses(ExpensesAction expensesAction) throws ServiceLocatorException {
        int count=0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
      // String query="INSERT INTO tblEmpDeductions(EmpId,Accommodation, RoomNo, RoomFee, Cafeteria, CafeteriaFee, Transportation, TransLocation, TransFee, CreatedBy, CreatedDate) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
       //  String query="INSERT INTO tblEmpDeductions(EmpId,Accommodation, RoomNo, RoomFee, Cafeteria, CafeteriaFee, Transportation, TransLocation, TransFee, CreatedBy, CreatedDate,OccupancyType,DateOfOccupancy,ElecrricalCharges) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        String query="INSERT INTO tblEmpDeductions(EmpId,Accommodation, RoomNo, RoomFee, Cafeteria, CafeteriaFee, Transportation, TransLocation, TransFee, CreatedBy, CreatedDate,OccupancyType,DateOfOccupancy,ElecrricalCharges,IsActive) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
  inActiveExpenses(expensesAction.getId());
 
        try{
            connection =ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, expensesAction.getId());
            preparedStatement.setString(2, expensesAction.getAccommodation());
            preparedStatement.setString(3, expensesAction.getRoomNo());
            preparedStatement.setDouble(4, expensesAction.getRoomFee());
            preparedStatement.setString(5, expensesAction.getCafeteria());
            preparedStatement.setDouble(6, expensesAction.getCafeteriaFee());
            preparedStatement.setString(7, expensesAction.getTransportation());
            preparedStatement.setString(8, expensesAction.getTransportLocation());
            preparedStatement.setDouble(9, expensesAction.getTransportFee());
           preparedStatement.setString(10, expensesAction.getCreatedBy());
           preparedStatement.setTimestamp(11, DateUtility.getInstance().getCurrentMySqlDateTime());
             preparedStatement.setString(12, expensesAction.getOccupancyType());
          if(expensesAction.getDateOfOccupancy()!=null&&!"".equals(expensesAction.getDateOfOccupancy()))
           preparedStatement.setDate(13, DateUtility.getInstance().getMysqlDate(expensesAction.getDateOfOccupancy()));
           else
            preparedStatement.setTimestamp(13,null);   

            preparedStatement.setDouble(14, expensesAction.getElectricalCharges());
preparedStatement.setInt(15, 1);
            count = preparedStatement.executeUpdate();
           
          //  System.out.println("count--->"+count);
           
        }catch(Exception e){
            throw new ServiceLocatorException(e);
        }finally{
            try {
              
                if(preparedStatement != null){
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
               throw new ServiceLocatorException(ex);
            }
        }
        
        return count;
    }
     public int editExpenses(ExpensesAction expensesAction) throws ServiceLocatorException {
        int count=0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
       // String query="UPDATE tblEmpDeductions	SET Accommodation= ? , RoomNo= ? , RoomFee= ? , Cafeteria= ? , CafeteriaFee= ? , Transportation= ? , TransLocation= ? , TransFee= ? , ModifiedBy= ? , ModifiedDate= ? WHERE EmpId="+expensesAction.getId();
        String query="UPDATE tblEmpDeductions	SET Accommodation= ? , RoomNo= ? , RoomFee= ? , Cafeteria= ? , CafeteriaFee= ? , Transportation= ? , TransLocation= ? , TransFee= ? , ModifiedBy= ? , ModifiedDate= ?,OccupancyType=?,DateOfOccupancy=?,ElecrricalCharges=? WHERE Id="+expensesAction.getExpId()+" and EmpId="+expensesAction.getId();

        try{
            //inActiveExpenses(expensesAction.getId());
           // System.out.println("query-->"+query);
            connection =ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(query);
           
            preparedStatement.setString(1, expensesAction.getAccommodation());
            preparedStatement.setString(2, expensesAction.getRoomNo());
            preparedStatement.setDouble(3, expensesAction.getRoomFee());
            preparedStatement.setString(4, expensesAction.getCafeteria());
            preparedStatement.setDouble(5, expensesAction.getCafeteriaFee());
            preparedStatement.setString(6, expensesAction.getTransportation());
            preparedStatement.setString(7, expensesAction.getTransportLocation());
            preparedStatement.setDouble(8, expensesAction.getTransportFee());
             preparedStatement.setString(9, expensesAction.getCreatedBy());
           preparedStatement.setTimestamp(10, DateUtility.getInstance().getCurrentMySqlDateTime());
           preparedStatement.setString(11, expensesAction.getOccupancyType());
            if(expensesAction.getDateOfOccupancy()!=null&&!"".equals(expensesAction.getDateOfOccupancy()))
           preparedStatement.setDate(12, DateUtility.getInstance().getMysqlDate(expensesAction.getDateOfOccupancy()));
            else
                 preparedStatement.setTimestamp(12,null);

            preparedStatement.setDouble(13, expensesAction.getElectricalCharges());
          
            count = preparedStatement.executeUpdate();
           
            //System.out.println("count--->"+count);
           
        }catch(Exception e){
            throw new ServiceLocatorException(e);
        }finally{
            try {
              
                if(preparedStatement != null){
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
               throw new ServiceLocatorException(ex);
            }
        }
        
        return count;
    }

     public Collection getOtherDetailExpenses(int empId,int noOfRecords) throws ServiceLocatorException{
          //System.out.println("--in expenses impl--"+empId);
        Map OtherDetailExpenses = new TreeMap();
        int counter = 1;
        String tempCreatedDate ="";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            String queryString = "SELECT * FROM tblEmpDeductions WHERE EmpId='"+empId+"' ORDER BY CreatedDate DESC ";
            queryString=queryString+"LIMIT 15";
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while(resultSet.next()){
                if(counter <= noOfRecords){
                   // StateVTO stateVTO = new StateVTO();
                    ExpensesVTO expensesVTO=new ExpensesVTO();
                    expensesVTO.setId(resultSet.getInt("Id"));
                    expensesVTO.setEmpId(resultSet.getInt("EmpId"));
                    expensesVTO.setAccommodation(resultSet.getString("Accommodation"));
                    expensesVTO.setRoomNo(resultSet.getString("RoomNo"));
                   // System.out.println("roomnumber-->"+resultSet.getInt("RoomNo"));
                    expensesVTO.setRoomFee(resultSet.getDouble("RoomFee"));
                    expensesVTO.setCreatedDate(resultSet.getString("CreatedDate").substring(0, 10));
                   expensesVTO.setCafeteria(resultSet.getString("Cafeteria"));
                expensesVTO.setCafeteriaFee(resultSet.getDouble("CafeteriaFee"));
                 expensesVTO.setTransportation(resultSet.getString("Transportation"));
                 expensesVTO.setTransportLocation(resultSet.getString("TransLocation"));
                       expensesVTO.setTransportFee(resultSet.getDouble("TransFee"));
                        if(resultSet.getString("DateOfOccupancy")!=null&&!"".equals(resultSet.getString("DateOfOccupancy")))
                        expensesVTO.setDateOfOccupancy(DateUtility.getInstance().convertToviewFormat(resultSet.getString("DateOfOccupancy")));
                       expensesVTO.setOccupancyType(resultSet.getString("OccupancyType"));
                       expensesVTO.setElectricalCharges(resultSet.getDouble("ElecrricalCharges"));

                    OtherDetailExpenses.put("otherDetailsExpenses-->"+counter,expensesVTO);
                    counter++;
                
                }
            }
            
        }catch (SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
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
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        return OtherDetailExpenses.values();
    }
public int inActiveExpenses(int empId) throws ServiceLocatorException {
        int count=0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
       // String query="UPDATE tblEmpDeductions	SET Accommodation= ? , RoomNo= ? , RoomFee= ? , Cafeteria= ? , CafeteriaFee= ? , Transportation= ? , TransLocation= ? , TransFee= ? , ModifiedBy= ? , ModifiedDate= ? WHERE EmpId="+expensesAction.getId();
        String query="UPDATE tblEmpDeductions SET IsActive= 0 WHERE EmpId="+empId;

        try{
           // System.out.println("query-->"+query);
            connection =ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(query);
           
            

            count = preparedStatement.executeUpdate();
           
            //System.out.println("count--->"+count);
           
        }catch(Exception e){
            throw new ServiceLocatorException(e);
        }finally{
            try {
              
                if(preparedStatement != null){
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
               throw new ServiceLocatorException(ex);
            }
        }
        
        return count;
    }
      
      
      public ExpensesVTO getCopyOfLastRecord(int empId)throws ServiceLocatorException{
        System.out.println("getCopyOfLastRecord");
        ExpensesVTO expensesVTO = new ExpensesVTO();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int updatedRows=0;
    
    
        
        try{
            connection = ConnectionProvider.getInstance().getConnection();
          //  String queryString ="select Accommodation,RoomNo,RoomFee,Cafeteria,CafeteriaFee,Transportation,TransLocation,TransFee from tblEmpDeductions where EmpId="+empId;
        String queryString ="select Accommodation,RoomNo,RoomFee,Cafeteria,CafeteriaFee,Transportation,TransLocation,TransFee,OccupancyType,DateOfOccupancy,ElecrricalCharges from tblEmpDeductions where IsActive=1 and  EmpId="+empId ;

             System.out.println("queryString-->"+queryString);
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            expensesVTO.setEmpId(empId);
            if(resultSet.next()){
                expensesVTO.setAccommodation(resultSet.getString("Accommodation"));
                expensesVTO.setRoomNo(resultSet.getString("RoomNo"));
                expensesVTO.setRoomFee(resultSet.getDouble("RoomFee"));
                expensesVTO.setCafeteria(resultSet.getString("Cafeteria"));
                expensesVTO.setCafeteriaFee(resultSet.getDouble("CafeteriaFee"));
                 expensesVTO.setTransportation(resultSet.getString("Transportation"));
                 expensesVTO.setTransportLocation(resultSet.getString("TransLocation"));
                       expensesVTO.setTransportFee(resultSet.getDouble("TransFee"));
                        if(resultSet.getString("DateOfOccupancy")!=null&&!"".equals(resultSet.getString("DateOfOccupancy")))
                        expensesVTO.setDateOfOccupancy(DateUtility.getInstance().convertToviewFormat(resultSet.getString("DateOfOccupancy")));
                       expensesVTO.setOccupancyType(resultSet.getString("OccupancyType"));
                       expensesVTO.setElectricalCharges(resultSet.getDouble("ElecrricalCharges"));

               
            }
            
          
            
        }catch(SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
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
            } catch(SQLException se){
                se.printStackTrace();
            }
        }
        
        return expensesVTO;
    }
}


