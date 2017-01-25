package com.example.xutianyi.testapplication.mvp.presenter;

import android.content.Context;

import com.example.xutianyi.testapplication.mvp.MvpCommonPresenterImpl;
import com.example.xutianyi.testapplication.mvp.model.MediaModelImpl;
import com.example.xutianyi.testapplication.mvp.view.MessageView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by xutianyi on 17-1-23.
 */

public class MessagePresenterImpl extends MvpCommonPresenterImpl<MessageView> implements MessagePresenter {

    public MessagePresenterImpl(Context mContext) {
        super(mContext);
    }

    @Subscribe
    public void fetchAppListEvent(MediaModelImpl.ApplistEvent event){

    }
}
