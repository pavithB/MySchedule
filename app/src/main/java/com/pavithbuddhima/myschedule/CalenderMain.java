package com.pavithbuddhima.myschedule;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

public class CalenderMain extends AppCompatActivity implements View.OnClickListener {


    Button createNew ;
    Button viewEdit ;
    Button move ;
    Button delete ;
    CalendarView datePicker;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender_main);

        createNew = (Button) findViewById(R.id.create);
        viewEdit = (Button) findViewById(R.id.viewedit);
        move = (Button) findViewById(R.id.move);
        delete = (Button) findViewById(R.id.delete);
        datePicker = (CalendarView) findViewById(R.id.pickdate);


        createNew.setOnClickListener(this);
        viewEdit.setOnClickListener(this);
        move.setOnClickListener(this);
        delete.setOnClickListener(this);






        datePicker.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override public void onSelectedDayChange(
                    CalendarView view,
                    int          year,
                    int          month,
                    int          dayOfMonth ) {

                Toast.makeText(getApplicationContext(), ""+year+ " / " + (month+1) + " / " + dayOfMonth+" selected", Toast.LENGTH_SHORT).show();

            }


    });

    }


    public void onClick(View v){

        switch (v.getId()){

            case R.id.create :
                Intent createAppoint = new Intent(this,CreateAppoinment.class);
                startActivity(createAppoint);

                break;
            case R.id.move :

                break;

            case R.id.viewedit :

                break;

            case R.id.delete :

                break;

            case R.id.searchView :

                break;

        }

    }






}
