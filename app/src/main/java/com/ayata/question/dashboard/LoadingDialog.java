package com.ayata.question.dashboard;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import com.ayata.question.R;

public class LoadingDialog {

    Activity activity;
    AlertDialog dialog;

    LoadingDialog(Activity myActivity) {
        activity = myActivity;
    }

    AlertDialog createLoadingDialog() {
       AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_dialog,null));
        builder.setCancelable(false);
        dialog = builder.create();
        return dialog;
    }

    void dismissDialog()
    {
        dialog.dismiss();
    }

}
