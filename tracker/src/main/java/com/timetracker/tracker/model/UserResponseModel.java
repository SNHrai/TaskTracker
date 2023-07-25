package com.timetracker.tracker.model;

import javax.persistence.Column;
import javax.persistence.Id;

public class UserResponseModel {
    private long id;
    private String userName;
    private String employeeRole;
    private Long mobileNumber;
    private long verticleHeadId;
    private String password;

    public UserResponseModel() {
    }

    public UserResponseModel(String userName, String employeeRole, Long mobileNumber, long verticleHeadId,
            String password) {
        this.userName = userName;
        this.employeeRole = employeeRole;
        this.mobileNumber = mobileNumber;
        this.verticleHeadId = verticleHeadId;
        this.password = password;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmployeeRole(String employeeRole) {
         this.employeeRole = employeeRole;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setVerticleHeadId(long verticleHeadId) {
        this.verticleHeadId = verticleHeadId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public long getVerticleHeadId() {
        return verticleHeadId;
    }

    public String getPassword() {
        return password;
    }

}
