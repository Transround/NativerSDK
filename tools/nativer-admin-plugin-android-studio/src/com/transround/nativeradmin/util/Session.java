package com.transround.nativeradmin.util;

import com.transround.nativeradmin.model.User;

/**
 * Created by szeibert on 2014.11.24..
 */
public class Session {

    private static Session instance;

    private String token;
    private String sessid;
    private User user;

    private Session() {
    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSessid() {
        return sessid;
    }

    public void setSessid(String sessid) {
        this.sessid = sessid;
    }
}
