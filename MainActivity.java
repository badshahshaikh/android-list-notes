package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public EditText text,del_text;
    public Button buttonOne, get_data, del_btn,show_btn, add_text;
    public DBHandler dbHandler;
    public ListView listView;
    public Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHandler = new DBHandler(MainActivity.this);
        text = (EditText)findViewById(R.id.plain_text_input);
        add_text = (Button) findViewById(R.id.submit);
        get_data = (Button) findViewById(R.id.get_data);
        listView = findViewById(R.id.listview);

        //  Button delete_submit = (Button) findViewById(R.id.delete_submit);
        print_data();

        add_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String value = text.getText().toString();
                if ( value != null && value.length() > 0 ){
                    dbHandler.addNewCourse(value);
                    // after adding the data we are displaying a toast message.
                    print_data();

                }else{
                    Toast.makeText(MainActivity.this, "Please type some text.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        get_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "fetch ", Toast.LENGTH_SHORT).show();
                a();
            }
        });

    }

    public void a(){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    public void print_data(){

        cursor = dbHandler.alldata();
        ArrayList<String> name = new ArrayList<>();
        if (cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "No data", Toast.LENGTH_LONG).show();
        }
        else{
            while (cursor.moveToNext()){
                name.add(cursor.getString(1));
            }
        }

        CustomAdapter adapter = new CustomAdapter(MainActivity.this, name);
        listView.setAdapter(adapter);
        Toast.makeText(MainActivity.this, "Course has been added.", Toast.LENGTH_SHORT).show();
        text.setText("");

    }





}