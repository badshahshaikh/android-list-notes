package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context c;
    ArrayList<String> name;
    ArrayList<String> age;

    public CustomAdapter(Context c, ArrayList<String> name) {
        this.c = c;
        this.name = name;
    }


    @Override
    public int getCount() {
        return name.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view =  inflater.inflate(R.layout.activity_listitem, parent,false);

        TextView txtName, txtAge;
        txtName = view.findViewById(R.id.text_name);

        txtName.setText(name.get(i));

        return view;
    }
}
