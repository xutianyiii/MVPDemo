package com.example.xutianyi.testapplication.fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.PermissionChecker;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xutianyi.testapplication.R;
import com.example.xutianyi.testapplication.base.BaseFragment;
import com.example.xutianyi.testapplication.common.Constant;
import com.example.xutianyi.testapplication.common.DialogUtils;
import com.example.xutianyi.testapplication.mvp.presenter.DevicePresenter;
import com.example.xutianyi.testapplication.mvp.presenter.DevicePresenterImpl;
import com.example.xutianyi.testapplication.mvp.view.DeviceView;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.OnCheckedChanged;

import static android.app.Activity.RESULT_CANCELED;

/**
 * Created by xutianyi on 17-1-23.
 */

public class DeviceConnectionFragment extends BaseFragment<DeviceView,DevicePresenter> implements DeviceView{

    @Bind(R.id.switch_button)
    Switch bluetoothSwitch;
    @Bind(R.id.device_container)
    LinearLayout lv_deviceContainer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerReceiverDynamic();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setFragmentTitle(R.string.fg_device_title);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fg_device;
    }

    @Override
    public DevicePresenter createPresenter() {
        return new DevicePresenterImpl(getActivity());
    }

    @OnCheckedChanged(R.id.switch_button)
    public void onCheckSwitch(boolean isChecked){
        if(isChecked){
            if(presenter.checkBlueTooth()){  //android 6.0
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                        PermissionChecker.checkSelfPermission(getActivity(), Manifest.permission.BLUETOOTH_ADMIN) != PackageManager.PERMISSION_GRANTED) {
                    if (!shouldShowRequestPermissionRationale(Manifest.permission.BLUETOOTH_ADMIN)) {
                        Toast.makeText(getActivity(), getResources().getString(R.string.bluetooth_must_allow), Toast.LENGTH_LONG).show();
                    }
                    DeviceConnectionFragment.this.requestPermissions(new String[]{Manifest.permission.BLUETOOTH_ADMIN}, Constant.PERMISSIONS_REQUESTCODE);
                }else{
                    presenter.openBlueTooth(DeviceConnectionFragment.this);
                }
            }else{
                bluetoothSwitch.setChecked(false);
                Toast.makeText(getActivity(),getResources().getString(R.string.bluetooth_disable),Toast.LENGTH_LONG);
            }
        }else{
            //close bluetooth
            presenter.closeBlueTooth();
        }
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                DialogUtils.dismissDial();
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                Log.e("Test",device.getName());
                presenter.updateBlueToothList(device);
            }else if(BluetoothDevice.ACTION_BOND_STATE_CHANGED.equals(action)){

            }
        }
    };

    @Override
    public void onDestroy() {
        presenter.stopSearchDevice();
        getActivity().unregisterReceiver(receiver);
        super.onDestroy();
    }

    private void registerReceiverDynamic(){
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
        intentFilter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        intentFilter.addAction(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED);
        intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        getActivity().registerReceiver(receiver, intentFilter);
    }

    @Override
    public void updateBlueToothList(final BluetoothDevice device) {
        TextView tv_deviceName = new TextView(getActivity());
        tv_deviceName.setPadding(10,10,0,10);
        tv_deviceName.setText(device.getName());
        lv_deviceContainer.addView(tv_deviceName);
        tv_deviceName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.connectDevice(device);
                //receive data
                presenter.dataReceive();
            }
        });
    }

    // Bluetooth Permission handle -->android 6.0
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
            } else {
                // Permission Denied
            }
    }

    //android api < 22
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Constant.PERMISSIONS_OPEN_BLUETOOTH){
            if(resultCode == RESULT_CANCELED){  //user deny the permission
                bluetoothSwitch.setChecked(false);
            }else{ //user allow the permission
                DialogUtils.showDialogWithoutButton(getResources().getString(R.string.bluetooth_waiting),getActivity());
                presenter.searchDeviceNearBy();
            }
        }
    }
}
