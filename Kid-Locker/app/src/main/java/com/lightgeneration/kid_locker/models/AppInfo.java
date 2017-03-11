package com.lightgeneration.kid_locker.models;

import android.graphics.drawable.Drawable;

/**
 * Created by Ngoc Sang on 3/10/2017.
 */

public class AppInfo {
    private String title;

    public AppInfo(String title, String packageName, Drawable icon) {
        this.title = title;
        this.packageName = packageName;
        this.icon = icon;
    }

    public String getPackageName() {

        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    private String packageName;
    private boolean click;
    private Drawable icon;

    public AppInfo(String title, Drawable icon) {
        this.title = title;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public boolean isClick() {
        return click;
    }

    public void setClick(boolean click) {
        this.click = click;
    }
}
