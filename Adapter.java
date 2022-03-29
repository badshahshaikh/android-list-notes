package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Adapter extends AppCompatActivity {

    ListView textlist;
    ArrayList <String>listItem;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item);

        DBHandler dbHandler = new DBHandler(this);

        listItem = new ArrayList<>();
        textlist = findViewById(R.id.list_text);
        Cursor cursor = dbHandler.alldata();
        if (cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "No data", Toast.LENGTH_LONG).show();
        }
        else{
            while (cursor.moveToNext()){
                listItem.add(cursor.getString(1));
            }
            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_2,listItem);
            textlist.setAdapter(adapter);
        }

    }
}