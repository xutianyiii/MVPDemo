package com.example.xutianyi.testapplication.mvp.model;

import com.example.xutianyi.testapplication.entity.PhoneAppInfo;

import java.util.List;


/**
 * Created by xutianyi on 17-1-23.
 */

public interface MediaModel {
    //try to get a app list which contains all apps in this phone , appinfo is stored in PhoneAppInfo
    void fetchAllApps();
}
