package com.pavithbuddhima.myschedule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

public class MoveAppointment extends AppCompatActivity {


    Button movebtn ;
    CalendarView moveCalendr;
    static String preDate;
    static String title;
    String date = "un";


    AppoinmentDataBase handleDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_appointment);

        Intent getData = getIntent();

        preDate = getData.getStringExtra("date");
        title = getData.getStringExtra("title");


        movebtn = (Button) findViewById(R.id.movebtn);
        moveCalendr =(CalendarView) findViewById(R.id.calendarUpdate);


        handleDB = new AppoinmentDataBase(this, null, null, 1);


        moveCalendr.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override public void onSelectedDayChange(
                    CalendarView view,
                    int          year,
                    int          month,
                    int          dayOfMonth ) {
//                calDay =dayOfMonth;
//                calMonth = month ;
//                calYear = year;



                Toast.makeText(getApplicationContext(), ""+year+ " / " + (month+1) + " / " + dayOfMonth+" selected", Toast.LENGTH_SHORT).show();
                date = ""+year+ " / " + (month+1) + " / " + dayOfMonth ;
            }


        });


        movebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(date.equals("un")){

                    Toast.makeText(getApplicationContext(), "PLEASE SELECT A DATE FIRST", Toast.LENGTH_SHORT).show();

                }else {

                    boolean moveResult = handleDB.MoveAppointment(preDate, title, date);
                    Toast.makeText(getApplicationContext(), "title: " + title + " predata: " + preDate + " new date:" + date, Toast.LENGTH_LONG).show();

                    if (moveResult) {
                        Toast.makeText(getApplicationContext(), "Move " + title + " appointment from:" + preDate + " to:" + date, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), " something went wrong", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });


    }
}
