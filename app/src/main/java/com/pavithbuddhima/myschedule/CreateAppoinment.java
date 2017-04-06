package com.pavithbuddhima.myschedule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class CreateAppoinment extends AppCompatActivity {

    static int year;
    static int month;
    static int day;

    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_appoinment);
    }
}
