package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "coursedb";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "mycourses";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our course name column
    private static final String TEXT_COL = "text";


    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
            String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + "INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TEXT_COL + "TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
    public void addNewCourse(String text) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(TEXT_COL, text);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public Cursor alldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+ TABLE_NAME, null);
        return cursor;
    }

    //---deletes a particular title---
    public boolean deleteTitle(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,  ID_COL+ "=" + id, null) > 0;
    }

    public Cursor getId(String text){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor get = db.rawQuery("select "+ ID_COL +" from "+ TABLE_NAME + " where "+TEXT_COL+" like + '" + text + "'", null);
        return get;
    }

    public boolean UpdateTitle(String id,String text){
        ContentValues cv = new ContentValues();
        cv.put( TEXT_COL , text); //These Fields should be your String values of actual column names
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_NAME, cv,  ID_COL + "= ?", new String[]{id});
        return true;
    }



    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_NAME);

    }


}