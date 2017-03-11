package com.lightgeneration.kid_locker.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ngoc Sang on 3/11/2017.
 */

public class ResponceAccount {
    @SerializedName("status")
    private String status;

    public ResponceAccount(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
