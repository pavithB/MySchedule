package com.pavithbuddhima.myschedule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.EditText;

public class ViewAppointment extends AppCompatActivity {

    AppoinmentDataBase handleDB;

    TextView  viewHead;

    EditText viewAll ;

    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appointment);

        Intent getdate = getIntent();
       date = getdate.getStringExtra("date");

        handleDB = new AppoinmentDataBase(this, null, null, 1);

        viewAll = (EditText) findViewById(R.id.viewAppointment);
        viewHead = (TextView) findViewById(R.id.viewHead);
        showappointment();

        viewHead.setText("Appointments on \n" + date);



    }

    public void showappointment(){





        String text = (handleDB.viewAppoinment(date));


        viewAll.setText(text);


    }
}
