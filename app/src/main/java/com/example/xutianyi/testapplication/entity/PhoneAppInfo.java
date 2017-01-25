package com.example.xutianyi.testapplication.entity;

import android.graphics.drawable.Drawable;

/**
 * Created by xutianyi on 17-1-23.
 * 手机中app的实体类,icon,启动类,包名等
 */

public class PhoneAppInfo {

    public String packageName;
    public String launchActivity;
    public Drawable appIcon;
    public String appName;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getLaunchActivity() {
        return launchActivity;
    }

    public void setLaunchActivity(String launchActivity) {
        this.launchActivity = launchActivity;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
