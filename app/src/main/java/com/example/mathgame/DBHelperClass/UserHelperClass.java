package com.example.mathgame.DBHelperClass;

public class UserHelperClass {

    String fName , userName , email , phone , password;

    //An empty constructor to call it in the register class
    public UserHelperClass() {
    }

    public UserHelperClass(String fName, String userName, String email, String phone, String password) {
        this.fName = fName;
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
