package com.example.xutianyi.testapplication.mvp.model;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.example.xutianyi.testapplication.entity.BaseEvent;
import com.example.xutianyi.testapplication.entity.PhoneAppInfo;
import com.example.xutianyi.testapplication.mvp.MvpCommonModelImpl;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xutianyi on 17-1-23.
 */

public class MediaModelImpl extends MvpCommonModelImpl implements MediaModel {

    public Context mContext;

    public MediaModelImpl(Context mContext) {
        super(mContext);
        this.mContext = mContext;
    }

    @Override
    public void fetchAllApps() {
        Intent appIntent = new Intent(Intent.ACTION_MAIN, null);
        appIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        PackageManager packageManager = mContext.getPackageManager();
        List<ResolveInfo> resolveInfos = packageManager.queryIntentActivities(appIntent, 0);
        List<PhoneAppInfo> phoneAppInfoList = new ArrayList<>();
        for (int i = 0; i < resolveInfos.size(); i++) {
            ResolveInfo resolveInfo = resolveInfos.get(i);
            String launchActivity = resolveInfo.activityInfo.name;
            String packageName = resolveInfo.activityInfo.packageName;
            Drawable appIcon = resolveInfo.loadIcon(packageManager);
            String appName = (String) resolveInfo.loadLabel(packageManager);
            PhoneAppInfo phoneAppInfo = new PhoneAppInfo();
            phoneAppInfo.setLaunchActivity(launchActivity);
            phoneAppInfo.setPackageName(packageName);
            phoneAppInfo.setAppIcon(appIcon);
            phoneAppInfo.setAppName(appName);
            phoneAppInfoList.add(phoneAppInfo);
        }
        ApplistEvent applistEvent = new ApplistEvent();
        applistEvent.setPhoneAppInfoList(phoneAppInfoList);
        EventBus.getDefault().post(applistEvent);
    }

    public static class ApplistEvent extends BaseEvent{

        List<PhoneAppInfo> phoneAppInfoList;

        public List<PhoneAppInfo> getPhoneAppInfoList() {
            return phoneAppInfoList;
        }

        public void setPhoneAppInfoList(List<PhoneAppInfo> phoneAppInfoList) {
            this.phoneAppInfoList = phoneAppInfoList;
        }
    }
}
