package com.transround.nativeradmin.model;

/**
 * Created by szeibert on 2014.11.24..
 */
public class LoginResponse extends ServiceResult {
    private String token;
    private String sessid;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSessid() {
        return sessid;
    }

    public void setSessid(String sessid) {
        this.sessid = sessid;
    }
}
