package com.lightgeneration.kid_locker.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ngoc Sang on 3/11/2017.
 */

public class ResponceLogin {
    @SerializedName("success")
    private boolean success;
    @SerializedName("username")
    private String username;
    @SerializedName("token")
    private String token;

    public ResponceLogin(boolean success, String username, String token) {
        this.success = success;
        this.username = username;
        this.token = token;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
