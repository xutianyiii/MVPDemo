package com.example.xutianyi.testapplication.mvp;

import android.content.Context;

import com.example.xutianyi.testapplication.mvp.model.MvpModel;

/**
 * Created by xutianyi on 17-1-23.
 */

public abstract class MvpCommonModelImpl implements MvpModel {

    protected Context mContext;

    public MvpCommonModelImpl(Context mContext) {
        this.mContext = mContext;
    }
}
