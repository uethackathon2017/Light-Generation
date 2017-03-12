package com.lightgeneration.kid_locker.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ngoc Sang on 3/12/2017.
 */

public class ItemProgress {
    @SerializedName("attempts")
    private int attempts;
    @SerializedName("createdAt")
    private long createdAt;

    public ItemProgress(int attempts, long createdAt) {
        this.attempts = attempts;
        this.createdAt = createdAt;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }
}
