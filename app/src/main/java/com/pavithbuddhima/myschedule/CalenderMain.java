package com.pavithbuddhima.myschedule;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;
import android.widget.EditText;

public class CalenderMain extends AppCompatActivity implements View.OnClickListener {


    static int calDay;
    static int calMonth;
    static int calYear;

    static String date = "un";


    Button createNew, search;
    Button viewEdit;
    Button move;
    Button delete;
    Button delAll;
    Button delSel;
    CalendarView datePicker;
    EditText searchWord;

    AppoinmentDataBase handleDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_calender_main);


        createNew = (Button) findViewById(R.id.create);
        viewEdit = (Button) findViewById(R.id.viewedit);
        move = (Button) findViewById(R.id.move);
        delete = (Button) findViewById(R.id.delete);
        datePicker = (CalendarView) findViewById(R.id.pickdate);
        delAll = (Button) findViewById(R.id.delAll);
        delSel = (Button) findViewById(R.id.delSel);
        search = (Button) findViewById(R.id.searchBtn);
        searchWord = (EditText) findViewById(R.id.searchET);


        createNew.setOnClickListener(this);
        viewEdit.setOnClickListener(this);
        move.setOnClickListener(this);
        delete.setOnClickListener(this);
        search.setOnClickListener(this);


        handleDB = new AppoinmentDataBase(this, null, null, 1);


        datePicker.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(
                    CalendarView view,
                    int year,
                    int month,
                    int dayOfMonth) {
                calDay = dayOfMonth;
                calMonth = month;
                calYear = year;


                Toast.makeText(getApplicationContext(), "" + year + " / " + (month + 1) + " / " + dayOfMonth + " selected", Toast.LENGTH_SHORT).show();
                date = "" + year + " / " + (month + 1) + " / " + dayOfMonth;
            }


        });

    }


    public void onClick(View v) {
        if (date.equals("un")) {

            Toast.makeText(getApplicationContext(), "PLEASE SELECT A DATE FIRST", Toast.LENGTH_SHORT).show();

        } else {

            switch (v.getId()) {

                case R.id.create:
                    Intent createAppoint = new Intent(this, CreateAppoinment.class);
                    createAppoint.putExtra("year", calYear);
                    createAppoint.putExtra("month", calMonth);
                    createAppoint.putExtra("day", calDay);
                    createAppoint.putExtra("date", date);
                    createAppoint.putExtra("title", "404");


                    startActivity(createAppoint);

                    break;
                case R.id.move:
                    Intent move = new Intent(this, ViewAppointment.class);
                    move.putExtra("date", date);
                    move.putExtra("option", "move");
                    startActivity(move);

                    break;

                case R.id.viewedit:
                    Intent viewEdit = new Intent(this, ViewAppointment.class);
                    viewEdit.putExtra("date", date);
                    viewEdit.putExtra("option", "view");
                    startActivity(viewEdit);


                    break;

                case R.id.delete:


                    AlertDialog.Builder mbuild = new AlertDialog.Builder(CalenderMain.this);
                    View mView = getLayoutInflater().inflate(R.layout.delete_option, null);
                    final Button delAll = (Button) mView.findViewById(R.id.delAll);
                    final Button delSel = (Button) mView.findViewById(R.id.delSel);
// set action listners for dialog box buttons
                    delAll.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Toast.makeText(getApplicationContext(), "Appointments deleted", Toast.LENGTH_SHORT).show();


                            handleDB.deleteAll(date);

                        }
                    });


                    delSel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Toast.makeText(getApplicationContext(), "Select appointment to delete", Toast.LENGTH_SHORT).show();

                            Intent deleteOne = new Intent(CalenderMain.this, ViewAppointment.class);
                            deleteOne.putExtra("date", date);
                            deleteOne.putExtra("option", "delete");
                            startActivity(deleteOne);


                        }
                    });
//show the dialog box
                    mbuild.setView(mView);
                    AlertDialog dialog = mbuild.create();
                    dialog.show();


                    break;


//                case R.id.searchView:
//
//                    break;


                case R.id.searchBtn:

                    Intent searchView = new Intent(CalenderMain.this, SearchAppointment.class);
                    searchView.putExtra("keyword", searchWord.getText().toString());
                    startActivity(searchView);

                    break;

            }

        }


    }


}
