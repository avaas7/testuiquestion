package com.ayata.question.dashboard;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;

import com.ayata.question.R;

public class DialogHelper {
    Context context;
    AlertDialog alertDialog;

    public DialogHelper(Context context)
    {
        this.context = context;
    }

    public void showDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        alertDialog= builder.create();
        builder.setView(mInflater.inflate(R.layout.custom_dialog, null));

        alertDialog.setCancelable(false);
        alertDialog.show();

    }


}
