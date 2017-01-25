package com.example.xutianyi.testapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xutianyi.testapplication.R;
import com.example.xutianyi.testapplication.entity.PhoneAppInfo;

import java.util.List;

/**
 * Created by xutianyi on 17-1-23.
 */

public class AppsGridAdapter extends BaseAdapter {

    public List<PhoneAppInfo> phoneAppInfoList;
    public Context mContext;

    public AppsGridAdapter(List<PhoneAppInfo> phoneAppInfoList,Context mContext){
        this.phoneAppInfoList = phoneAppInfoList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return null == phoneAppInfoList?0:phoneAppInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View contentView, ViewGroup viewGroup) {
        ViewHolder vh = null;
        if(null == contentView){
            vh = new ViewHolder();
            contentView = LayoutInflater.from(mContext).inflate(R.layout.app_gridview_item,viewGroup,false);
            vh.iv_appIcon = (ImageView) contentView.findViewById(R.id.app_icon);
            vh.tv_appName = (TextView) contentView.findViewById(R.id.app_name);
            contentView.setTag(vh);
        }else{
            vh = (ViewHolder) contentView.getTag();
        }
        vh.iv_appIcon.setImageDrawable(phoneAppInfoList.get(position).getAppIcon());
        vh.tv_appName.setText(phoneAppInfoList.get(position).getAppName());
        return contentView;
    }

    public static class ViewHolder{
        public ImageView iv_appIcon;
        public TextView tv_appName;
    }
}
