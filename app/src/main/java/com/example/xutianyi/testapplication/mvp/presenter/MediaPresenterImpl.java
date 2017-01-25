package com.example.xutianyi.testapplication.mvp.presenter;

import android.content.Context;

import com.example.xutianyi.testapplication.mvp.MvpCommonPresenterImpl;
import com.example.xutianyi.testapplication.mvp.model.MediaModel;
import com.example.xutianyi.testapplication.mvp.model.MediaModelImpl;
import com.example.xutianyi.testapplication.mvp.model.MessageModel;
import com.example.xutianyi.testapplication.mvp.model.MessageModelImpl;
import com.example.xutianyi.testapplication.mvp.view.MediaView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by xutianyi on 17-1-23.
 */

public class MediaPresenterImpl extends MvpCommonPresenterImpl<MediaView> implements MediaPresenter {

    public MediaModel mediaModel;

    public MediaPresenterImpl(Context mContext) {
        super(mContext);
        mediaModel = new MediaModelImpl(mContext);
    }

    @Override
    public void fetchAllApps() {
        mediaModel.fetchAllApps();
    }

    @Subscribe
    public void fetchAppListEvent(MediaModelImpl.ApplistEvent event){
        if(null != getView() && null != event){
            getView().appListDisplay(event.getPhoneAppInfoList());
        }
    }
}
