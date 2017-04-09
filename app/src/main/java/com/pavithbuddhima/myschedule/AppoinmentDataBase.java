package com.pavithbuddhima.myschedule;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Pavith Buddhima on 4/7/2017.
 */

public class AppoinmentDataBase extends SQLiteOpenHelper {



    private static final int DATABASE_VERSION=1;

    private static final String DATABSE_NAME="appoinments.db";

    public static final String TABLE_MY_APPOINMENTS="myappoinments";

    public static final String COLOUMN_ID="_id";

    public static final String COLOUMN_DATE="appoinmentDate";

    public static final String COLOUMN_TIME ="appoinmentTime";

    public static final String COLOUMN_TITLE="appoinmentTitle";

    public static final String COLOUMN_DISCRIPTION ="appoinmentDiscription";

    public static final String COLOUMN_MATH_TIME ="appoinmentMathTime";









    public AppoinmentDataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

        super(context, DATABSE_NAME, factory, DATABASE_VERSION);

    }



    @Override
    public void onCreate(SQLiteDatabase db) {



        String query = "CREATE TABLE " + TABLE_MY_APPOINMENTS + "(" +

                COLOUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLOUMN_DATE + " TEXT, " +
                COLOUMN_TIME + " DATETIME, " +
                COLOUMN_TITLE + " TEXT, " +
                COLOUMN_DISCRIPTION + " TEXT, " +
                COLOUMN_MATH_TIME + " DATETIME " +

                ");";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS" + TABLE_MY_APPOINMENTS);

        onCreate(db);

    }


    public Boolean checkTitle(Appoinment appoinment){

        SQLiteDatabase mydb = getWritableDatabase();

        String query = " SELECT * FROM " + TABLE_MY_APPOINMENTS + " WHERE " +
                COLOUMN_TITLE + "=\'" + appoinment.getTitle() + "\'" + " AND " +
                COLOUMN_DATE + "=\'" + appoinment.getDate() + "\'";

//        String query = "SELECT * FROM " + TABLE_MY_APPOINMENTS + " WHERE " +
//                COLOUMN_TITLE + " = \"" + appoinment.getTitle() + " \"";



        Cursor pointer = mydb.rawQuery(query,null);

//        mydb.close();

        if ( pointer == null || !pointer.moveToFirst()){

            return true;

        }else{

            return false;
        }

    }





    public String createAppoinment(Appoinment appoinment){

        ContentValues values = new ContentValues();

        values.put(COLOUMN_DATE,appoinment.getDate());
        values.put(COLOUMN_TIME,appoinment.getTime());
        values.put(COLOUMN_TITLE,appoinment.getTitle());
        values.put(COLOUMN_DISCRIPTION,appoinment.getDiscription());
        values.put(COLOUMN_MATH_TIME,appoinment.getMathTime());


        SQLiteDatabase mydb = getWritableDatabase();
        mydb.insert(TABLE_MY_APPOINMENTS,null,values);
        mydb.close();

//     return "time: "+appoinment.getTime()+" title:" +appoinment.getTitle()+" dis:" +appoinment.getDiscription()+" date:" + appoinment.getDate() ;
        return appoinment.getTitle() + " added";

    }



    public void deleteAll(String date){

        SQLiteDatabase mydb = getWritableDatabase();

        mydb.execSQL("DELETE FROM " + TABLE_MY_APPOINMENTS + " WHERE " + COLOUMN_DATE + "=\"" + date +"\";"   );
        mydb.close();


    }
//    delete single appoinment goes here


public String deleteSelect(String date , int userSelected , boolean titleOnly){

    SQLiteDatabase mydb = getWritableDatabase();

//    mydb.execSQL("DELETE FROM " + TABLE_MY_APPOINMENTS + " WHERE "  +
//            COLOUMN_TITLE + "=\'" + title + "\'" + " AND " +
//            COLOUMN_DATE + "=\'" + date + "\';"  );


    int index=1;
    String title = "200" ;



    String query = "SELECT * FROM " + TABLE_MY_APPOINMENTS + " WHERE " +
            COLOUMN_DATE + " = \"" + date +  "\" ORDER BY " + COLOUMN_MATH_TIME +" ASC" ;


    Cursor pointer = mydb.rawQuery(query,null);
    pointer.moveToFirst();

    while(!pointer.isAfterLast()){

//            if(pointer.getString(pointer.getColumnIndex("appoinmentDate"))!=null){

        String tempTitle = pointer.getString(pointer.getColumnIndex("appoinmentTitle"));

        if(index == userSelected){

            if(titleOnly){
                title = tempTitle ;
            }else {

                mydb.execSQL("DELETE FROM " + TABLE_MY_APPOINMENTS + " WHERE " + COLOUMN_TITLE + "=\"" + tempTitle + "\";");

            }
            break;
            }else{
            index++;

        }


        pointer.moveToNext();

    }

    mydb.close();
    if(pointer.isAfterLast()){
        return "404" ;
    }else{
        return title;
    }


}



    public String viewAppoinment(String date){


        String viewString="";
        int index=0;

        SQLiteDatabase mydb = getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_MY_APPOINMENTS + " WHERE " +
                COLOUMN_DATE + " = \"" + date +  "\" ORDER BY " + COLOUMN_MATH_TIME +" ASC" ;

//        String query = "SELECT * FROM " + TABLE_MY_APPOINMENTS ;

        Cursor pointer = mydb.rawQuery(query,null);
        pointer.moveToFirst();

        while(!pointer.isAfterLast()){

//            if(pointer.getString(pointer.getColumnIndex("appoinmentDate"))!=null){

                index++;

                String tempTime = pointer.getString(pointer.getColumnIndex("appoinmentTime"));
                String tempTitle = pointer.getString(pointer.getColumnIndex("appoinmentTitle"));

                viewString += index + ".  " + tempTime + "  " + tempTitle;
                viewString +="\n";
//            }

            pointer.moveToNext();

        }

        mydb.close();

        return viewString;
//        return  "date" ;
    }



    public void retriveAppoinment(String date , String title , int option){

        SQLiteDatabase mydb = getWritableDatabase();


        String query = " SELECT * FROM " + TABLE_MY_APPOINMENTS + " WHERE " +
                COLOUMN_TITLE + "=\'" + title + "\'" + " AND " +
                COLOUMN_DATE + "=\'" + date + "\'";


        Cursor pointer = mydb.rawQuery(query,null);

        switch (option) {

            case 1:
            String tempTitle = pointer.getString(pointer.getColumnIndex("appoinmentTitle"));
                break;
            case 2:

                break;
            case 3;

                break;
        }


        }



}
