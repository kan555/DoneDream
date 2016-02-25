package com.donedream.sillapajarn.chawametha.donedream;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by WINDOWS7 on 25/2/2559.
 */
public class MyOpenHelper extends SQLiteOpenHelper{

    //Explicit
    public static final String database_name = "dream.db";
    private static final int database_version = 1;

    private static final String create_table_user = "create table userTABLE (" +
            "_id integer primary key, " +
            "User text," +
            "Password text," +
            "Name text," +
            "Email text," +
            "Birth text," +
            "Country text);";

    public MyOpenHelper(Context context) {
        super(context, database_name, null, database_version);
    }   //constructor

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(create_table_user);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}   //main class
