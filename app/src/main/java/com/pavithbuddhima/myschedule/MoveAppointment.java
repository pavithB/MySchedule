package com.pavithbuddhima.myschedule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

public class MoveAppointment extends AppCompatActivity {


    Button movebtn ;
    CalendarView moveCalendr;
    String preDate;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_appointment);

        Intent getDate = new Intent();
        preDate = getDate.getStringExtra("date");


        movebtn = (Button) findViewById(R.id.movebtn);
        moveCalendr =(CalendarView) findViewById(R.id.calendarUpdate);









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

    }
}
