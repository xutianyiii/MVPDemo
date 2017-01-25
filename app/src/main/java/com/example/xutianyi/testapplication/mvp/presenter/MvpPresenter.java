package com.example.xutianyi.testapplication.mvp.presenter;

import com.example.xutianyi.testapplication.mvp.view.MvpView;

/**
 * Created by xutianyi on 17-1-23.
 * presenter is just like a controller , which detaches view and model
 */

public interface MvpPresenter<V extends MvpView> {
    //connect with view
    public void attachView(V view);

    public void detachView(boolean retainInstance);
}
