package com.lightgeneration.kid_locker.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Ngoc Sang on 3/10/2017.
 */

public class Question {
    @SerializedName("images")
    private ArrayList<String> images;
    @SerializedName("audio")
    private String audio;
    @SerializedName("text")
    private String text;
    @SerializedName("answer")
    private int answer;
    @SerializedName("horizontal")
    private boolean horizontal;

    public Question(ArrayList<String> images, String audio, String text, int answer, boolean horizontal) {
        this.images = images;
        this.audio = audio;
        this.text = text;
        this.answer = answer;
        this.horizontal = horizontal;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }
}
