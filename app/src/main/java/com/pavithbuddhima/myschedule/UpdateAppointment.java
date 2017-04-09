package com.pavithbuddhima.myschedule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class UpdateAppoinyment extends AppCompatActivity {

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
        setContentView(R.layout.activity_create_appoinment);


        Intent getdate = getIntent();
        selDate = getdate.getStringExtra("date");
        preTitle = getdate.getStringExtra("title");

        title = (EditText) findViewById(R.id.titleUpdat);
        time = (TimePicker) findViewById(R.id.timeUpdate);
        discription = (EditText) findViewById(R.id.detailUpdate);
        save = (Button) findViewById(R.id.saveUpdatebtn);


        handleDB = new AppoinmentDataBase(this, null, null, 1);



        if((preTitle.equals("404"))) {
            Toast.makeText(getApplicationContext(), "create appointment", Toast.LENGTH_LONG).show();
        }else{

            Toast.makeText(getApplicationContext(), "update appointment", Toast.LENGTH_LONG).show();

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

        }


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (preTitle.equals("404")){

                    hour = time.getCurrentHour();

                    minute = time.getCurrentMinute();

                    mathTime = (hour * 60) + minute;

                    selTime = "" + hour + ":" + minute;

                    Appoinment newAppoinment = new Appoinment(title.getText().toString(), discription.getText().toString(), selDate, selTime, mathTime);

                    if (handleDB.checkTitle(newAppoinment)) {

                        String adedTittle = handleDB.createAppoinment(newAppoinment);


                        Toast.makeText(getApplicationContext(), adedTittle, Toast.LENGTH_SHORT).show();

                        Intent home = new Intent(CreateAppoinment.this, CalenderMain.class);
                        startActivity(home);

                    } else {
                        Toast.makeText(getApplicationContext(), title.getText().toString() + " already exists, please choose a diﬀerent event title", Toast.LENGTH_LONG).show();
                    }
                }else{

                    hour = time.getCurrentHour();

                    minute = time.getCurrentMinute();

                    mathTime = (hour * 60) + minute;

                    selTime = "" + hour + ":" + minute;

                    boolean updateResult = handleDB.updateAppointment(date ,preTitle ,title.getText().toString() , selTime ,discription.getText().toString(),mathTime);

                    if(updateResult){
                        Toast.makeText(getApplicationContext(), "Update " + preTitle +" appoitment SUCCESSFULL", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(), " something went WRONG", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });


    }


}
