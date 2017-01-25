package com.example.xutianyi.testapplication.mvp.presenter;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.widget.BaseAdapter;

import com.example.xutianyi.testapplication.MainApplication;
import com.example.xutianyi.testapplication.common.Constant;
import com.example.xutianyi.testapplication.mvp.MvpCommonPresenterImpl;
import com.example.xutianyi.testapplication.mvp.model.MediaModelImpl;
import com.example.xutianyi.testapplication.mvp.view.DeviceView;

import org.greenrobot.eventbus.Subscribe;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.PrivilegedAction;
import java.util.List;
import java.util.UUID;

/**
 * Created by xutianyi on 17-1-23.
 */

public class DevicePresenterImpl extends MvpCommonPresenterImpl<DeviceView> implements DevicePresenter {
    private BluetoothAdapter bluetoothAdapter;
    private Context mContext;
    private UUID uuid;
    private final String SPP_UUID = "00001101-0000-1000-8000-00805F9B34FB";

    public DevicePresenterImpl(Context mContext) {
        super(mContext);
        this.mContext = mContext;
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        uuid = UUID.fromString(SPP_UUID);
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
    public void updateBlueToothList(BluetoothDevice device) {
        if(null != getView()){
            getView().updateBlueToothList(device);
        }
    }

    @Override
    public void connectDevice(final BluetoothDevice device) {
        int connectState = device.getBondState();
        switch (connectState){
            case BluetoothDevice.BOND_NONE:
                //try to match first
                Method createBondMethod = null;
                try {
                    createBondMethod =BluetoothDevice.class.getMethod("createBond");
                    createBondMethod.invoke(device);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                //then connect
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        BluetoothSocket socket = null;
                        try {
                            socket = device.createRfcommSocketToServiceRecord(uuid);
                            socket.connect();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
            case BluetoothDevice.BOND_BONDED:
                //then connect
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        BluetoothSocket socket = null;
                        try {
                            socket = device.createRfcommSocketToServiceRecord(uuid);
                            socket.connect();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
        }
    }

    @Override
    public void dataReceive() {
        new Thread().start();
    }

    @Subscribe
    public void fetchAppListEvent(MediaModelImpl.ApplistEvent event){

    }

    //data receive thread
    private class DataReceiveThread implements Runnable{
        private BluetoothServerSocket serverSocket;
        private BluetoothSocket socket;
        private BufferedInputStream bis;

        public DataReceiveThread(){
            try {
                serverSocket = bluetoothAdapter.listenUsingRfcommWithServiceRecord("Bluetooth_Socket", uuid);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        @Override
        public void run() {
            File bltReceiveFile = new File(MainApplication.SD_PATH + Constant.BLUETOOTH_RECEIVE_PATH+ "/" + System.currentTimeMillis() );
            FileOutputStream fos = null;
            if(!bltReceiveFile.exists()){
                bltReceiveFile.mkdirs();
            }
            try {
                fos = new FileOutputStream(bltReceiveFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            receive(fos);
        }

        private void receive(FileOutputStream fos){
            try {
                socket = serverSocket.accept();
                bis = new BufferedInputStream(socket.getInputStream());
                byte[] buffer = new byte[1024];
                int len;
                while ((len = bis.read(buffer)) != -1){
                    fos.write(buffer,0,len);
                    Log.e("Test",bis.available()+"");
                }
                fos.flush();
                //transform bos into local file
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
