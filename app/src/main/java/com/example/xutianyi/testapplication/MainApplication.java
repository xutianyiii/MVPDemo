package com.example.xutianyi.testapplication;

import android.app.Application;
import android.os.Environment;

/**
 * Created by xutianyi on 17-1-25.
 */

public class MainApplication extends Application {
    public static String SD_PATH;

    @Override
    public void onCreate() {
        super.onCreate();
        initDiskPath();
    }

    //inti disk path
    private void initDiskPath() {
        // sdcard is mounted
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //mnt/sdcard/
            SD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
        } else {
            //data/data/
            SD_PATH = Environment.getDataDirectory().getAbsolutePath();
        }
    }
}
