package com.pavithbuddhima.myschedule;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CalenderMain extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender_main);
    }


    public void onClick(View v){

        switch (v.getId()){

            case R.id.create :
                Intent createAppoint = new Intent(this,CreateAppoinment.class);
                startActivity(createAppoint);


        }

    }


}
