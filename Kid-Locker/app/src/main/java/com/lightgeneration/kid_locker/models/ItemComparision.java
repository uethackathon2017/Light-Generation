package com.lightgeneration.kid_locker.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ngoc Sang on 3/12/2017.
 */

public class ItemComparision {
    @SerializedName("Nhận biết")
    private comparision recognize;
    @SerializedName("Trái phải")
    private comparision leftRigh;
    @SerializedName("Trên dưới")
    private comparision upDown;
    @SerializedName("Tập Đếm")
    private comparision count;

    public ItemComparision(comparision recognize, comparision leftRigh, comparision upDown, comparision count) {
        this.recognize = recognize;
        this.leftRigh = leftRigh;
        this.upDown = upDown;
        this.count = count;
    }

    public comparision getRecognize() {
        return recognize;
    }

    public void setRecognize(comparision recognize) {
        this.recognize = recognize;
    }

    public comparision getLeftRigh() {
        return leftRigh;
    }

    public void setLeftRigh(comparision leftRigh) {
        this.leftRigh = leftRigh;
    }

    public comparision getUpDown() {
        return upDown;
    }

    public void setUpDown(comparision upDown) {
        this.upDown = upDown;
    }

    public comparision getCount() {
        return count;
    }

    public void setCount(comparision count) {
        this.count = count;
    }
}
