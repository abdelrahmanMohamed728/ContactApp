package com.example.abdo.contactapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.support.annotation.NonNull;
import android.widget.TextView;

import java.util.List;

public class mainListAdapter extends ArrayAdapter<pair> {
    public mainListAdapter(@NonNull Context context, @NonNull List<pair> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        convertView  = LayoutInflater.from(getContext()).inflate(R.layout.customlist,parent,false);
        pair p = getItem(position);
        TextView name = convertView.findViewById(R.id.name1);
        TextView phone = convertView.findViewById(R.id.phone);
        name.setText(p.getName());
        phone.setText(p.getPhone());
        return convertView;
    }
}
