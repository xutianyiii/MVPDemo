package com.example.xutianyi.testapplication.mvp;

import android.content.Context;

import com.example.xutianyi.testapplication.mvp.presenter.MvpPresenter;
import com.example.xutianyi.testapplication.mvp.view.MvpView;

import org.greenrobot.eventbus.EventBus;

public abstract class MvpCommonPresenterImpl<V extends MvpView> implements MvpPresenter<V> {
    protected Context mContext;
    private V attachedView;

    public MvpCommonPresenterImpl(Context mContext) {
        this.mContext = mContext.getApplicationContext();
    }

    @Override
    public void attachView(V view) {
        attachedView = view;
        EventBus.getDefault().register(this);
    }

    @Override
    public void detachView(boolean retainInstance) {
        if(null != attachedView) attachedView = null;
        EventBus.getDefault().unregister(this);
    }

    protected V getView(){
        return attachedView;
    }
}