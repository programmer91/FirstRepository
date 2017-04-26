 /*
  * AddressServiceImpl.java
  *
  * Created on November 26, 2007, 3:26 PM
  *
  * To change this template, choose Tools | Template Manager
  * and open the template in the editor.
  */

package com.mss.mirage.employee.address;

import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.HibernateServiceLocator;
import com.mss.mirage.util.ServiceLocatorException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import org.apache.poi.hssf.record.formula.functions.True;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author __RajaReddy.Andra
 *
 */
public class AddressServiceImpl implements AddressService{
   
    /** Creates a new instance of AddressServiceImpl */
    public AddressServiceImpl() {
    }
    
    /**
     * The getAddress(int addressId) is used for retrieving activity.
     * @throws ServiceLocatorException.
     * @return AddressVTO variable.
     * {@link com.mss.mirage.util.ServiceLocatorException}
     * {@link com.mss.mirage.crm.activities.ActivityVTO}
     */
    public AddressVTO getAddress(int empId)throws ServiceLocatorException{
        AddressVTO addressVTO = new AddressVTO();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int updatedRows=0;
    
     int offshoreAddress=0;
     int payrollAddress=0;
     int curProjAddress=0;
     int curHotelAddress=0;
     int homeAddress=0;
     int otherAddress=0;
        
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            String queryString ="select OffShoreAddressId,PayrollAddressId,CurProjectAddressId,CurHotelAddressId,HomeAddressId,OtherAddressId from tblEmployee where Id="+empId;
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            addressVTO.setEmpId(empId);
            if(resultSet.next()){
                offshoreAddress = resultSet.getInt(1);
                payrollAddress = resultSet.getInt(2);
                curProjAddress = resultSet.getInt(3);
                curHotelAddress = resultSet.getInt(4);
                homeAddress = resultSet.getInt(5);
                otherAddress = resultSet.getInt(6);
            }
            
            if(offshoreAddress > 0){
                preparedStatement = connection.prepareStatement("select * from tblEmpAddress where Id="+offshoreAddress);
                resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    addressVTO.setOffAddressId(resultSet.getInt("Id"));
                    addressVTO.setOffShoreAddLine1(resultSet.getString("AddressLine1"));
                    addressVTO.setOffShoreAddLine2(resultSet.getString("AddressLine2"));
                    addressVTO.setOffShoreCity(resultSet.getString("City"));
                    addressVTO.setOffShoreState(resultSet.getString("State"));
                    addressVTO.setOffShoreZip(resultSet.getString("Zip"));
                    addressVTO.setOffShoreCountry(resultSet.getString("Country"));
                }
                
            }
            if(payrollAddress > 0){
                preparedStatement = connection.prepareStatement("select * from tblEmpAddress where Id="+payrollAddress);
                resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    addressVTO.setPayAddressId(resultSet.getInt("Id"));
                    addressVTO.setPayrollAddLine1(resultSet.getString("AddressLine1"));
                    addressVTO.setPayrollAddLine2(resultSet.getString("AddressLine2"));
                    addressVTO.setPayrollCity(resultSet.getString("City"));
                    addressVTO.setPayrollState(resultSet.getString("State"));
                    addressVTO.setPayrollZip(resultSet.getString("Zip"));
                    addressVTO.setPayrollCountry(resultSet.getString("Country"));
                }
                
            }
            
            if(curProjAddress > 0){
                preparedStatement = connection.prepareStatement("select * from tblEmpAddress where Id="+curProjAddress);
                resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    addressVTO.setCProAddressId(resultSet.getInt("Id"));
                    addressVTO.setCprojectAddLine1(resultSet.getString("AddressLine1"));
                    addressVTO.setCprojectAddLine2(resultSet.getString("AddressLine2"));
                    addressVTO.setCprojectCity(resultSet.getString("City"));
                    addressVTO.setCprojectState(resultSet.getString("State"));
                    addressVTO.setCprojectZip(resultSet.getString("Zip"));
                    addressVTO.setCprojectCountry(resultSet.getString("Country"));
                }
                
            }
            
            if(curHotelAddress > 0){
                preparedStatement = connection.prepareStatement("select * from tblEmpAddress where Id="+curHotelAddress);
                resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    addressVTO.setHotelAddressId(resultSet.getInt("Id"));
                    addressVTO.setHotelAddLine1(resultSet.getString("AddressLine1"));
                    addressVTO.setHotelAddLine2(resultSet.getString("AddressLine2"));
                    addressVTO.setHotelCity(resultSet.getString("City"));
                    addressVTO.setHotelState(resultSet.getString("State"));
                    addressVTO.setHotelZip(resultSet.getString("Zip"));
                    addressVTO.setHotelCountry(resultSet.getString("Country"));
                }
                
            }
            
            if( homeAddress > 0){
                preparedStatement = connection.prepareStatement("select * from tblEmpAddress where Id="+ homeAddress);
                resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    addressVTO.setHomeAddressId(resultSet.getInt("Id"));
                    addressVTO.setHomeAddLine1(resultSet.getString("AddressLine1"));
                    addressVTO.setHomeAddLine2(resultSet.getString("AddressLine2"));
                    addressVTO.setHomeCity(resultSet.getString("City"));
                    addressVTO.setHomeState(resultSet.getString("State"));
                    addressVTO.setHomeZip(resultSet.getString("Zip"));
                    addressVTO.setHomeCountry(resultSet.getString("Country"));
                }
                
            }
            
            if( otherAddress > 0){
                preparedStatement = connection.prepareStatement("select * from tblEmpAddress where Id="+ otherAddress);
                resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    addressVTO.setOtherAddressId(resultSet.getInt("Id"));
                    addressVTO.setOtherAddLine1(resultSet.getString("AddressLine1"));
                    addressVTO.setOtherAddLine2(resultSet.getString("AddressLine2"));
                    addressVTO.setOtherCity(resultSet.getString("City"));
                    addressVTO.setOtherState(resultSet.getString("State"));
                    addressVTO.setOtherZip(resultSet.getString("Zip"));
                    addressVTO.setOtherCountry(resultSet.getString("Country"));
                }
                
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
        
        return addressVTO;
    }//end of the get method
    
    public int addOrUpdateAddress(AddressAction addressPojo) throws ServiceLocatorException {
        Connection connection = null;
        CallableStatement callableStatement = null;
        int updatedRows=0;
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{call spEmpAddress(?,?,?,?,?,?," +
                    "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            callableStatement.setInt(1,addressPojo.getId());
            
            callableStatement.setString(2,addressPojo.getOffShoreAddLine1());
            callableStatement.setString(3,addressPojo.getOffShoreAddLine2());
            callableStatement.setString(4,addressPojo.getOffShoreCity());
            callableStatement.setString(5,addressPojo.getOffShoreState());
            callableStatement.setString(6,addressPojo.getOffShoreCountry());
            callableStatement.setString(7,addressPojo.getOffShoreZip());
            
            callableStatement.setString(8,addressPojo.getPayrollAddLine1());
            callableStatement.setString(9,addressPojo.getPayrollAddLine2());
            callableStatement.setString(10,addressPojo.getPayrollCity());
            callableStatement.setString(11,addressPojo.getPayrollState());
            callableStatement.setString(12,addressPojo.getPayrollCountry());
            callableStatement.setString(13,addressPojo.getPayrollZip());
            
            callableStatement.setString(14,addressPojo.getCprojectAddLine1());
            callableStatement.setString(15,addressPojo.getCprojectAddLine2());
            callableStatement.setString(16,addressPojo.getCprojectCity());
            callableStatement.setString(17,addressPojo.getCprojectState());
            callableStatement.setString(18,addressPojo.getCprojectCountry());
            callableStatement.setString(19,addressPojo.getCprojectZip());
            
            callableStatement.setString(20,addressPojo.getHomeAddLine1());
            callableStatement.setString(21,addressPojo.getHomeAddLine2());
            callableStatement.setString(22,addressPojo.getHomeCity());
            callableStatement.setString(23,addressPojo.getHomeState());
            callableStatement.setString(24,addressPojo.getHomeCountry());
            callableStatement.setString(25,addressPojo.getHomeZip());
            
            callableStatement.setString(26,addressPojo.getHotelAddLine1());
            callableStatement.setString(27,addressPojo.getHotelAddLine2());
            callableStatement.setString(28,addressPojo.getHotelCity());
            callableStatement.setString(29,addressPojo.getHotelState());
            callableStatement.setString(30,addressPojo.getHotelCountry());
            callableStatement.setString(31,addressPojo.getHotelZip());
            
            callableStatement.setString(32,addressPojo.getOtherAddLine1());
            callableStatement.setString(33,addressPojo.getOtherAddLine2());
            callableStatement.setString(34,addressPojo.getOtherCity());
            callableStatement.setString(35,addressPojo.getOtherState());
            callableStatement.setString(36,addressPojo.getOtherCountry());
            callableStatement.setString(37,addressPojo.getOtherZip());
            
            //callableStatement.setString(38,addressPojo.getOperation());
            
            callableStatement.setInt(38,addressPojo.getHomeAddressId());
            callableStatement.setInt(39,addressPojo.getPayAddressId());
            callableStatement.setInt(40,addressPojo.getCProAddressId());
            callableStatement.setInt(41,addressPojo.getOffAddressId());
            callableStatement.setInt(42,addressPojo.getHotelAddressId());
            callableStatement.setInt(43,addressPojo.getOtherAddressId());
            
            updatedRows=callableStatement.executeUpdate();
           
        }catch(SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if(callableStatement!=null){
                    callableStatement.close();
                    callableStatement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            } catch(SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        return updatedRows;
    }
    
    public int checkAction(int empId) throws ServiceLocatorException {
        int count=0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        String query="select OffShoreAddressId,PayrollAddressId,CurProjectAddressId,CurHotelAddressId,HomeAddressId,OtherAddressId from tblEmployee where Id="+empId;
        try{
            connection =ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                if(resultSet.getInt("OffShoreAddressId")>0)count++;
                if(resultSet.getInt("PayrollAddressId")>0)count++;
                if(resultSet.getInt("CurProjectAddressId")>0)count++;
                if(resultSet.getInt("CurHotelAddressId")>0)count++;
                if(resultSet.getInt("HomeAddressId")>0)count++;
                if(resultSet.getInt("OtherAddressId")>0)count++;
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
    
}//end of the class

