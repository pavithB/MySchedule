package com.pavithbuddhima.myschedule;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Pavith Buddhima on 4/7/2017.
 */

public class AppoinmentDataBase extends SQLiteOpenHelper {



    private static final int DATABASE_VERSION=1;

    private static final String DATABSE_NAME="appoinments.db";

    public static final String TABLE_MY_APPOINMENTS="myappoinments";

    public static final String COLOUMN_ID="_id";

    public static final String COLOUMN_PRODUCTNAME="productname";

    public AppoinmentDataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

        super(context, DATABSE_NAME, factory, DATABASE_VERSION);

    }



    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
