package com.example.xutianyi.testapplication.fragments;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.GridView;

import com.example.xutianyi.testapplication.R;
import com.example.xutianyi.testapplication.adapter.AppsGridAdapter;
import com.example.xutianyi.testapplication.base.BaseFragment;
import com.example.xutianyi.testapplication.entity.PhoneAppInfo;
import com.example.xutianyi.testapplication.mvp.presenter.MediaPresenter;
import com.example.xutianyi.testapplication.mvp.presenter.MediaPresenterImpl;
import com.example.xutianyi.testapplication.mvp.view.MediaView;

import java.util.List;

import butterknife.Bind;
import butterknife.OnItemClick;

/**
 * Created by xutianyi on 17-1-23.
 */

public class MultiMediaFragment extends BaseFragment<MediaView,MediaPresenter> implements MediaView {
    @Bind(R.id.gv_appsGridView)
    public GridView appsGridView;
    public AppsGridAdapter appsGridAdapter;
    public List<PhoneAppInfo>phoneAppInfoList;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setFragmentTitle(R.string.fg_media_title);
        presenter.fetchAllApps();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fg_media;
    }

    @Override
    public MediaPresenter createPresenter() {
        return new MediaPresenterImpl(getActivity());
    }

    @Override
    public void appListDisplay(List<PhoneAppInfo> phoneAppInfoList) {
        this.phoneAppInfoList = phoneAppInfoList;
        appsGridAdapter = new AppsGridAdapter(phoneAppInfoList,getActivity());
        appsGridView.setAdapter(appsGridAdapter);
    }

    @OnItemClick(R.id.gv_appsGridView)
    public void appGridViewItemClick(int position,View itemView){
        PackageManager packageManager = getActivity().getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage(phoneAppInfoList.get(position).packageName);
        startActivity(intent);
    }

}
