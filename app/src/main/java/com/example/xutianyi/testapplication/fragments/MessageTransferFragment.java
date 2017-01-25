package com.example.xutianyi.testapplication.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.example.xutianyi.testapplication.R;
import com.example.xutianyi.testapplication.base.BaseFragment;
import com.example.xutianyi.testapplication.mvp.presenter.MediaPresenter;
import com.example.xutianyi.testapplication.mvp.presenter.MessagePresenter;
import com.example.xutianyi.testapplication.mvp.presenter.MessagePresenterImpl;
import com.example.xutianyi.testapplication.mvp.view.MediaView;
import com.example.xutianyi.testapplication.mvp.view.MessageView;

/**
 * Created by xutianyi on 17-1-23.
 */

public class MessageTransferFragment extends BaseFragment<MessageView,MessagePresenter> implements MessageView{

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setFragmentTitle(R.string.fg_message_title);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fg_message;
    }

    @Override
    public MessagePresenter createPresenter() {
        return new MessagePresenterImpl(getActivity());
    }
}
