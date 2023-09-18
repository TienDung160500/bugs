package com.mycompany.myapp.web.rest.request;



public class UserPutRequest {
    private String userName;
    private String password;

    public UserPutRequest() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
