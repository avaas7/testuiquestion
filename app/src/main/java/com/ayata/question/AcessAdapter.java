package com.ayata.question;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class AcessAdapter extends ArrayAdapter<AccessItem> {

    public AcessAdapter(Context context, ArrayList<AccessItem> accessList) {
        super(context,0,accessList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    private View initView(int position, View convertView, ViewGroup parent)
    {
        if (convertView==null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.access_spinner_row,parent,false);
        }
        ImageView imageViewFlag = convertView.findViewById(R.id.spinner_image_view_name);
        TextView textView = convertView.findViewById(R.id.spinner_text_view_name);

        AccessItem currentItem = getItem(position);

        if (currentItem!=null) {
            imageViewFlag.setImageResource(currentItem.getImage());
            textView.setText(currentItem.getName());
        }

        return convertView;

    }
}
