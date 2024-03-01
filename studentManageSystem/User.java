package com.example.first_expereience.studentManageSystem;

import com.example.first_expereience.RegisterActivity;

public class User {
    private static String userName;
    private String userPassword;
    private String userRePassword;
    private String idNumber;
    private String telephoneNumber;

    public User() {
    }

    public User(String userName, String userCode, String idNumber, String telephoneNumber) {
        this.userName = userName;
        this.userPassword = userCode;
        this.idNumber = idNumber;
        this.telephoneNumber = telephoneNumber;
    }

    /**
     * 获取
     * @return userName
     */
    public static String getUserName() {
        return userName;
    }

    /**
     * 设置
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取
     * @return userCode
     */
    public String getUserPassword() {
        return userPassword;
    }
    public String getUserRePassword() {
        return userRePassword;
    }

    /**
     * 设置
     * @param userPassword
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }public void setUserRePassword(String userRePassword) {
        this.userRePassword = userRePassword;
    }

    /**
     * 获取
     * @return idNumber
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * 设置
     * @param idNumber
     */
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    /**
     * 获取
     * @return telephoneNumber
     */
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    /**
     * 设置
     * @param telephoneNumber
     */
    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String toString() {
        return "User{userName = " + userName + ", userPassword = " + userPassword + ", idNumber = " + idNumber + ", telephoneNumber = " + telephoneNumber + "}";
    }

}
