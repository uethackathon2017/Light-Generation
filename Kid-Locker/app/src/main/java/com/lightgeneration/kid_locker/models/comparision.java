package com.lightgeneration.kid_locker.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ngoc Sang on 3/12/2017.
 */

public class comparision {
    @SerializedName("prob")
    private float prob;
    @SerializedName("total")
    private int total;

    public comparision(long prob, int total) {
        this.prob = prob;
        this.total = total;
    }

    public float getProb() {
        return prob;
    }

    public void setProb(float prob) {
        this.prob = prob;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
