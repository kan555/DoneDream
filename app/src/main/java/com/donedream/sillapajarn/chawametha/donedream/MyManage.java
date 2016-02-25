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

    public MyManage(Context context) {

        //create & connected
        myOpenHelper = new MyOpenHelper(context);
        writeSqLiteDatabase = myOpenHelper.getWritableDatabase();
        readSqLiteDatabase = myOpenHelper.getReadableDatabase();

    }   //constructor

}   //main class
