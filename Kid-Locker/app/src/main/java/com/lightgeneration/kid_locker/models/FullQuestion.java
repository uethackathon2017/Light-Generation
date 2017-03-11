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

    public String getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(String idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
