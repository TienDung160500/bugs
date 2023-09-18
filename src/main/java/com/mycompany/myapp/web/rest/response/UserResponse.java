package com.mycompany.myapp.web.rest.response;


import java.util.Date;


public class UserResponse {
    private Integer id;
    private String userName;
    private String password;
    private Date createAt;
    private Date lastLogin;

    public UserResponse(Integer id, String userName, String password, Date createAt, Date lastLogin) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.createAt = createAt;
        this.lastLogin = lastLogin;
    }

    public UserResponse() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
}
