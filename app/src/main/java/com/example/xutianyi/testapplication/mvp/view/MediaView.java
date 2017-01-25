package com.example.xutianyi.testapplication.mvp.view;

import com.example.xutianyi.testapplication.entity.PhoneAppInfo;

import java.util.List;

/**
 * Created by xutianyi on 17-1-23.
 */

public interface MediaView extends MvpView {
    void appListDisplay(List<PhoneAppInfo>phoneAppInfoList);
}
