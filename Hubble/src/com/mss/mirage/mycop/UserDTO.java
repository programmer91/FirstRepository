/*
 * UserDTO.java
 *
 * Created on March 10, 2008, 7:37 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.mycop;

import java.sql.Date;
import java.sql.Time;


/**
 *
 * @author miracle
 */
public class UserDTO {
    
    private String EmployeeName;
    private String LoginTime;
    private String LogoutTime;
    private String TotalBreakTime;
    private String TotalWorkedTime;
    private String TotalMeetingTime;
    private String IPAddress;
    private String HostName;
    private String TotalAttendanceTime;
    private String Department;
    
    public String getEmployeeName() {
        return EmployeeName;
    }
    
    public void setEmployeeName(String EmployeeName) {
        this.EmployeeName = EmployeeName;
    }
    
    public String getLoginTime() {
        return LoginTime;
    }
    
    public void setLoginTime(String LoginTime) {
        this.LoginTime = LoginTime;
    }
    
    public String getLogoutTime() {
        return LogoutTime;
    }
    
    public void setLogoutTime(String LogoutTime) {
        this.LogoutTime = LogoutTime;
    }
    
    public String getTotalBreakTime() {
        return TotalBreakTime;
    }
    
    public void setTotalBreakTime(String TotalBreakTime) {
        this.TotalBreakTime = TotalBreakTime;
    }
    
    public String getTotalWorkedTime() {
        return TotalWorkedTime;
    }
    
    public void setTotalWorkedTime(String TotalWorkedTime) {
        this.TotalWorkedTime = TotalWorkedTime;
    }
    
    public String getTotalMeetingTime() {
        return TotalMeetingTime;
    }
    
    public void setTotalMeetingTime(String TotalMeetingTime) {
        this.TotalMeetingTime = TotalMeetingTime;
    }
    
    public String getIPAddress() {
        return IPAddress;
    }
    
    public void setIPAddress(String IPAddress) {
        this.IPAddress = IPAddress;
    }
    
    public String getHostName() {
        return HostName;
    }
    
    public void setHostName(String HostName) {
        this.HostName = HostName;
    }

    public String getTotalAttendanceTime() {
        return TotalAttendanceTime;
    }

    public void setTotalAttendanceTime(String TotalAttendanceTime) {
        this.TotalAttendanceTime = TotalAttendanceTime;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String Department) {
        this.Department = Department;
    }
    
    
    
}
