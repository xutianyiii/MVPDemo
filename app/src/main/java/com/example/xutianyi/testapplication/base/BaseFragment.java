package com.example.xutianyi.testapplication.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xutianyi.testapplication.R;
import com.example.xutianyi.testapplication.mvp.presenter.MvpPresenter;
import com.example.xutianyi.testapplication.mvp.view.MvpView;

import butterknife.ButterKnife;

/**
 * Created by xutianyi on 17-1-23.
 */

public abstract class BaseFragment<V extends MvpView,P extends MvpPresenter<V>> extends Fragment {
    public P presenter;
    public TextView tv_titleView;
    public View rootView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(null == rootView){
            View rootView = inflater.inflate(getLayoutRes(),container,false);
            this.rootView = rootView;
        }
        tv_titleView = (TextView) rootView.findViewById(R.id.tv_titleView);
        if(null == presenter){
            presenter = createPresenter();
        }
        ButterKnife.bind(this,rootView);
        presenter.attachView((V)this);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        presenter.detachView(null != getActivity() && getActivity().isChangingConfigurations() && getRetainInstance());
        if (rootView != null) {
            ((ViewGroup) rootView.getParent()).removeView(rootView);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public abstract int getLayoutRes();

    public abstract P createPresenter();

    public void setFragmentTitle(int StringId){
        tv_titleView.setText(getResources().getString(StringId));
    }

}