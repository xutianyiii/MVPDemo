package com.example.xutianyi.testapplication.mvp.presenter;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDialogFragment;
import android.widget.BaseAdapter;

import com.example.xutianyi.testapplication.common.Constant;
import com.example.xutianyi.testapplication.mvp.MvpCommonPresenterImpl;
import com.example.xutianyi.testapplication.mvp.model.MediaModelImpl;
import com.example.xutianyi.testapplication.mvp.view.DeviceView;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * Created by xutianyi on 17-1-23.
 */

public class DevicePresenterImpl extends MvpCommonPresenterImpl<DeviceView> implements DevicePresenter {
    private BluetoothAdapter bluetoothAdapter;
    private Context mContext;

    public DevicePresenterImpl(Context mContext) {
        super(mContext);
        this.mContext = mContext;
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    @Override
    public boolean checkBlueTooth() {
        if(null == bluetoothAdapter){
            // bluetooth is disable
            return false;
        }
        return true;
    }

    @Override
    public void openBlueTooth(Fragment fragment) {
        if(checkBlueTooth() && !bluetoothAdapter.isEnabled()){
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            // set the discoverable duration of the bluetooth.max value:300s
            intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
            fragment.startActivityForResult(intent, Constant.PERMISSIONS_OPEN_BLUETOOTH);
        }
    }

    @Override
    public void closeBlueTooth() {
        bluetoothAdapter.disable();
    }

    @Override
    public void searchDeviceNearBy() {
        bluetoothAdapter.startDiscovery();
    }

    @Override
    public void stopSearchDevice() {
        bluetoothAdapter.cancelDiscovery();
    }

    @Override
    public void updateBlueToothList(String deviceName) {
        if(null != getView()){
            getView().updateBlueToothList(deviceName);
        }
    }

    @Subscribe
    public void fetchAppListEvent(MediaModelImpl.ApplistEvent event){

    }
}
