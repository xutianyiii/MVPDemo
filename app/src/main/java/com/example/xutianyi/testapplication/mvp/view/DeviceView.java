package com.example.xutianyi.testapplication.mvp.view;

import com.example.xutianyi.testapplication.mvp.presenter.MvpPresenter;

import java.util.List;

/**
 * Created by xutianyi on 17-1-23.
 */

public interface DeviceView extends MvpView {

    void updateBlueToothList(String deviceName);
}
