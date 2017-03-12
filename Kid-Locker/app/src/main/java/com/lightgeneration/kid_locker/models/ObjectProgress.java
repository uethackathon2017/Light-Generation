package com.lightgeneration.kid_locker.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ngoc Sang on 3/12/2017.
 */

public class ObjectProgress {

    @SerializedName("Nhận biết")
    private ItemProgressCategory recognize;
    @SerializedName("Trái phải")
    private ItemProgressCategory leftRigh;
    @SerializedName("Trên dưới")
    private ItemProgressCategory upDown;
    @SerializedName("Tập Đếm")
    private ItemProgressCategory count;

    public ObjectProgress(ItemProgressCategory recognize, ItemProgressCategory leftRigh, ItemProgressCategory upDown, ItemProgressCategory count) {
        this.recognize = recognize;
        this.leftRigh = leftRigh;
        this.upDown = upDown;
        this.count = count;
    }

    public ItemProgressCategory getRecognize() {
        return recognize;
    }

    public void setRecognize(ItemProgressCategory recognize) {
        this.recognize = recognize;
    }

    public ItemProgressCategory getLeftRigh() {
        return leftRigh;
    }

    public void setLeftRigh(ItemProgressCategory leftRigh) {
        this.leftRigh = leftRigh;
    }

    public ItemProgressCategory getUpDown() {
        return upDown;
    }

    public void setUpDown(ItemProgressCategory upDown) {
        this.upDown = upDown;
    }

    public ItemProgressCategory getCount() {
        return count;
    }

    public void setCount(ItemProgressCategory count) {
        this.count = count;
    }
}
