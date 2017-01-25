package com.example.xutianyi.testapplication.mvp.presenter;

import com.example.xutianyi.testapplication.mvp.view.MediaView;

/**
 * Created by xutianyi on 17-1-23.
 */

public interface MediaPresenter extends MvpPresenter<MediaView> {
    void fetchAllApps();
}
