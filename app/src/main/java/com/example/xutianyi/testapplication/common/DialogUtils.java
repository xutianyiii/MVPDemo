package com.example.xutianyi.testapplication.common;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.xutianyi.testapplication.R;

/**
 * Created by xutianyi on 17-1-24.
 */

public class DialogUtils {
    private static AlertDialog.Builder dialogBuilder;
    private static Dialog dialog;

    public static void showDialog(String message, String positiveStr, String negativeStr, Context mContext) {
        dialogBuilder = null;
        dialogBuilder = new AlertDialog.Builder(mContext);
        dialogBuilder.setMessage(message);
        dialogBuilder.setPositiveButton(positiveStr, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        dialogBuilder.setNegativeButton(negativeStr, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        dialog = dialogBuilder.create();
        dialog.show();
    }

    public static void showDialogWithoutButton(String message,Context mContext) {
        dialogBuilder = null;
        dialogBuilder = new AlertDialog.Builder(mContext);
        dialogBuilder.setMessage(message);
        dialog = dialogBuilder.create();
        dialog.show();
    }

    public static void dismissDial(){
        if(null != dialog){
           dialog.dismiss();
        }
    }
}
