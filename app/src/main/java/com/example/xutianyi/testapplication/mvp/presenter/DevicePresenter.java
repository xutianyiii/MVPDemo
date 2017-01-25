package com.example.xutianyi.testapplication.mvp.presenter;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.support.v4.app.Fragment;

import com.example.xutianyi.testapplication.mvp.view.DeviceView;

import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by xutianyi on 17-1-23.
 */

public interface DevicePresenter extends MvpPresenter<DeviceView> {
    //check the bluetooth whether it is able --> true:yes , false:no
    boolean checkBlueTooth();

    void openBlueTooth(Fragment fragment);

    void closeBlueTooth();

    void searchDeviceNearBy();

    void stopSearchDevice();

    void updateBlueToothList(BluetoothDevice device);

    void connectDevice(BluetoothDevice device);

    void dataReceive();
}
