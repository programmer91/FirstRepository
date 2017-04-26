/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.employee.expenses;

import com.mss.mirage.util.ServiceLocatorException;
import java.util.Collection;

/**
 *
 * @author miracle
 */
public interface ExpensesService {
    public int checkAction(int empId) throws ServiceLocatorException;
     public ExpensesVTO getExpenses(int empId,int expId)throws ServiceLocatorException;
    public int addExpenses(ExpensesAction expensesAction) throws ServiceLocatorException;
     public int editExpenses(ExpensesAction expensesAction) throws ServiceLocatorException;
      public Collection getOtherDetailExpenses(int empId,int noOfRecords) throws ServiceLocatorException;
       public ExpensesVTO getCopyOfLastRecord(int empId)throws ServiceLocatorException;
}
