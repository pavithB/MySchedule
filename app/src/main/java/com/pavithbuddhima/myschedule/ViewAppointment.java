package com.pavithbuddhima.myschedule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;

public class ViewAppointment extends AppCompatActivity {

    AppoinmentDataBase handleDB;

    TextView  viewHead , inform;

    EditText viewAll , selectAppoinment;

    Button btnSelect;

    String date , header , footer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appointment);

        Intent getdata = getIntent();
        date = getdata.getStringExtra("date");
        header = getdata.getStringExtra("option");
//        footer = getdata.getStringExtra("footer");

        handleDB = new AppoinmentDataBase(this, null, null, 1);

        viewAll = (EditText) findViewById(R.id.viewAppointment);
        viewHead = (TextView) findViewById(R.id.viewHead);
        inform = (TextView) findViewById(R.id.informText) ;
        btnSelect = (Button) findViewById(R.id.btnSelectAppointment);
        selectAppoinment = (EditText) findViewById(R.id.selectAppoinment);



        showappointment();

        viewHead.setText("Appointments on \n" + date);

        switch (header){

            case "view":

                viewEdit();
                break;
            case "delete":

                deleteSelect();
                break;
            case "move":

                moveSelect();
                break;


        }

    }

    public void showappointment(){

        String text = (handleDB.viewAppoinment(date));

        viewAll.setText(text);


    }


    public void viewEdit(){
        inform.setText("select one appointment and enter the number of the appointment to VIEW or EDIT the appointment ");
        btnSelect.setText("View/Edit");

    }

    public void deleteSelect(){
        inform.setText("select one appointment and enter the number of the appointment to DELETE the appointment ");
        btnSelect.setText("Delete");

    }

    public void moveSelect(){
        inform.setText("select one appointment and enter the number of the appointment to MOVE the appointment ");
        btnSelect.setText("Move");


    }
}
