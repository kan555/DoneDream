package com.donedream.sillapajarn.chawametha.donedream;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by WINDOWS7 on 25/2/2559.
 */
public class MyManage {

    //explicit
    private MyOpenHelper myOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase, readSqLiteDatabase;

    //about userTABLE
    public static final String table_user = "userTABLE";
    public static final String column_id = "_id";
    public static final String column_User = "User";
    public static final String column_Password = "Password";
    public static final String column_Name = "Name";
    public static final String column_Email = "Email";
    public static final String column_Birth = "Birth";
    public static final String column_Country = "Country";

    public MyManage(Context context) {

        //create & connected
        myOpenHelper = new MyOpenHelper(context);
        writeSqLiteDatabase = myOpenHelper.getWritableDatabase();
        readSqLiteDatabase = myOpenHelper.getReadableDatabase();

    }   //constructor

}   //main class
