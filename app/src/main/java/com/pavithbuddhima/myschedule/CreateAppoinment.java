package com.pavithbuddhima.myschedule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class CreateAppoinment extends AppCompatActivity {

    static int year;
    static int month;
    static int day;
    static String date;


    EditText title;
    TimePicker time;
    EditText discription;

    Button save;

    AppoinmentDataBase handleDB;

    String selTime;
    String selDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_appoinment);


        Intent getdate = getIntent();
        selDate = getdate.getStringExtra("date");


        title = (EditText) findViewById(R.id.titleedit);
        time = (TimePicker) findViewById(R.id.timeedit);
        discription = (EditText) findViewById(R.id.editdetail);
        save = (Button) findViewById(R.id.savebtn);


        handleDB = new AppoinmentDataBase(this, null, null, 1);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                selTime = "" + time.getCurrentHour() + ":" + time.getCurrentMinute();

                Appoinment newAppoinment = new Appoinment(title.getText().toString(), discription.getText().toString(), selDate, selTime);

                if(handleDB.checkTitle(newAppoinment)) {

                    String x = handleDB.createAppoinment(newAppoinment);


                    Toast.makeText(getApplicationContext(), x, Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


}
