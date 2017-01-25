package com.example.xutianyi.testapplication;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.example.xutianyi.testapplication.adapter.TabFragmentAdapter;
import com.example.xutianyi.testapplication.fragments.DeviceConnectionFragment;
import com.example.xutianyi.testapplication.fragments.MessageTransferFragment;
import com.example.xutianyi.testapplication.fragments.MultiMediaFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    public ViewPager viewPager;
    public MultiMediaFragment multiMediaFragment;
    public DeviceConnectionFragment deviceConnectionFragment;
    public MessageTransferFragment messageTransferFragment;
    public List<Fragment> fragmentList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragments(savedInstanceState);
        initViewPager();
    }

    private void initViewPager(){
        viewPager = (ViewPager) findViewById(R.id.viewpager);
//        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(new TabFragmentAdapter(getSupportFragmentManager(), fragmentList));
    }

    private void initFragments(Bundle savedInstanceState){
        fragmentList = new ArrayList<>();
        if(null == savedInstanceState){
            multiMediaFragment = new MultiMediaFragment();
            deviceConnectionFragment = new DeviceConnectionFragment();
            messageTransferFragment = new MessageTransferFragment();
        }else{
            List<Fragment> list = getSupportFragmentManager().getFragments();
            // restore fragment by the add order
            for (Fragment fragment : list){
                if(fragment instanceof MultiMediaFragment){
                    multiMediaFragment = (MultiMediaFragment)fragment;
                }else if(fragment instanceof DeviceConnectionFragment){
                    deviceConnectionFragment = (DeviceConnectionFragment)fragment;
                }else if(fragment instanceof MessageTransferFragment){
                    messageTransferFragment = (MessageTransferFragment)fragment;
                }
            }
            if(null == multiMediaFragment) multiMediaFragment = new MultiMediaFragment();
            if(null == deviceConnectionFragment) deviceConnectionFragment = new DeviceConnectionFragment();
            if(null == messageTransferFragment) messageTransferFragment = new MessageTransferFragment();
        }
        fragmentList.add(multiMediaFragment);
        fragmentList.add(deviceConnectionFragment);
        fragmentList.add(messageTransferFragment);
    }
}
