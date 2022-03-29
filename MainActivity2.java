package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    public ListView TextList;
    ArrayList <String>listItem;
    ArrayAdapter adapter;
    public DBHandler dbHandler;
    public Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        dbHandler = new DBHandler(this);

        listItem = new ArrayList<>();
        TextList = findViewById(R.id.list_text);



        get_list();

        TextList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {

                String name = listItem.get(pos);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
                builder.setTitle("Confirmation!!!");
                builder.setMessage("Are you wants to edit ?");
                builder.setIcon(android.R.drawable.ic_menu_edit);
                builder.setCancelable(false);

                //for getting id by name
                Cursor cursor1 = dbHandler.getId(name);
                StringBuffer id = new StringBuffer();
                while(cursor1.moveToNext()){
                    id.append(cursor1.getString(0));
                }

                builder.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity2.this);
                        final EditText edittext = new EditText(MainActivity2.this);
                        alert.setMessage("Edit and click on update for updating the value");
                        alert.setTitle("Choose action!");
                        alert.setView(edittext);
                        edittext.setText(name);

                        alert.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                //"name" is old value // "edittext.gettext" will be new value
                                String updated_val = edittext.getText().toString();
                                dbHandler.UpdateTitle(id.toString(),updated_val);
                                Toast.makeText(MainActivity2.this, id +" Updated.", Toast.LENGTH_SHORT).show();

                                get_list();
                            }
                        });
                        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                // what ever you want to do with No option.
                                Toast.makeText(MainActivity2.this, " Cancel ", Toast.LENGTH_SHORT).show();
                            }
                        });
                        alert.show();

                    }
                });
                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int value = Integer.parseInt( id.toString() );
                        if (dbHandler.deleteTitle(value)){
                            Toast.makeText(MainActivity2.this, "id = "+value+" deleted", Toast.LENGTH_SHORT).show();
                            get_list();
                        }
                    }
                });

                builder.show();

            }
        });

    }

    public void get_list(){

        listItem.clear();
        cursor = dbHandler.alldata();
        if (cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "No data", Toast.LENGTH_LONG).show();
        }
        else{
            while (cursor.moveToNext()){
                listItem.add(cursor.getString(1));
            }
            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listItem);
            TextList.setAdapter(adapter);
        }

    }


}