package com.lightgeneration.kid_locker.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ngoc Sang on 3/10/2017.
 */

public class FullQuestion {
    @SerializedName("_id")
    private String idQuestion;
    @SerializedName("category")
    private String category;
    @SerializedName("level")
    private int level;
    @SerializedName("question")
    private Question question;
}
