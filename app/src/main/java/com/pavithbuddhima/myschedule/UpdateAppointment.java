package com.pavithbuddhima.myschedule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class UpdateAppointment extends AppCompatActivity {

    static double mathTime;
    static int hour;
    static int minute;
    static int day;
    static String date;


    EditText title;
    TimePicker time;
    EditText discription;

    Button save;

    AppoinmentDataBase handleDB;

    String selTime;
    String selDate;
    String preTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_appointment);


        Intent getdate = getIntent();
        date = getdate.getStringExtra("date");
        preTitle = getdate.getStringExtra("title");

        title = (EditText) findViewById(R.id.titleUpdat);
        time = (TimePicker) findViewById(R.id.timeUpdate);
        discription = (EditText) findViewById(R.id.detailUpdate);
        save = (Button) findViewById(R.id.saveUpdatebtn);


        handleDB = new AppoinmentDataBase(this, null, null, 1);




        Toast.makeText(getApplicationContext(), "update appointment", Toast.LENGTH_LONG).show();


//        retriveData();
/*
//            int retriveHour ;
//            int retriveMinute ;
            String retriveTitle = handleDB.retriveAppoinment(date,preTitle,1) ;
            String retriveDis = handleDB.retriveAppoinment(date,preTitle,2);


            title.setText(retriveTitle);
            discription.setText(retriveDis);

            int retriveHour = handleDB.retriveTime(date,preTitle,1);
            int retriveMinute = handleDB.retriveTime(date,preTitle,2);

            time.setCurrentHour(retriveHour);
            time.setCurrentMinute(retriveMinute);
*/




        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                hour = time.getCurrentHour();

                minute = time.getCurrentMinute();

                mathTime = (hour * 60) + minute;

                selTime = "" + hour + ":" + minute;

                boolean updateResult = handleDB.updateAppointment(date ,preTitle ,title.getText().toString() , selTime ,discription.getText().toString(),mathTime);
//                Toast.makeText(getApplicationContext(), preTitle, Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), date, Toast.LENGTH_LONG).show();
                if(updateResult){
                    Toast.makeText(getApplicationContext(), "Update " + preTitle +" appoitment SUCCESSFULL", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), " something went WRONG", Toast.LENGTH_LONG).show();
                }


            }
        });


    }


    public void retriveData(){



        //    int retriveHour ;
//            int retriveMinute ;

        String retriveTitle = handleDB.retriveAppoinment(date,preTitle,1) ;
        String retriveDis = handleDB.retriveAppoinment(date,preTitle,2);


        title.setText(retriveTitle);
        discription.setText(retriveDis);

        int retriveHour = handleDB.retriveTime(date,preTitle,1);
        int retriveMinute = handleDB.retriveTime(date,preTitle,2);

        time.setCurrentHour(retriveHour);
        time.setCurrentMinute(retriveMinute);


    }


}
