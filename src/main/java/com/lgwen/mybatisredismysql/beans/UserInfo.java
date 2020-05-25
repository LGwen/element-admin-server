package com.lgwen.mybatisredismysql.beans;

import java.io.Serializable;
import java.util.List;

public class UserInfo implements Serializable {
    private User user;
    private List<String> roles;


    public User getUser() {
        return user;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public UserInfo(User user, List<String> roles) {
        this.user = user;
        this.roles = roles;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "user=" + user +
                ", roles='" + roles + '\'' +
                '}';
    }
}
