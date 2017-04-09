package com.pavithbuddhima.myschedule;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ViewAppointment extends AppCompatActivity {

    AppoinmentDataBase handleDB;

    TextView  viewHead , inform ;

    EditText viewAll , selectAppoinment;

    Button btnSelect ;

    String date , header , footer;

   static int selection = 99;



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

/*        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                selection = Integer.parseInt(selectAppoinment.getText().toString());
                if(selection == 99){

//                    Toast.makeText(getApplicationContext(), "PLEASE SELECT A APPOINTMENT BEFORE PRESS THE BUTTON " , Toast.LENGTH_LONG).show();

                }else {

                    switch (header) {

                        case "view":

                            Intent viewEditSelect = new Intent(ViewAppointment.this,CreateAppoinment.class);
//                            handleDB.deleteSelect(date, selection,true);
                            viewEditSelect.putExtra("title",handleDB.deleteSelect(date, selection,true));
                            viewEditSelect.putExtra("date",date);

                            startActivity(viewEditSelect);


                            break;
                        case "delete":


//                            Toast.makeText(getApplicationContext(),handleDB.deleteSelect(date, selection,true) , Toast.LENGTH_LONG).show();
                                    handleDB.deleteSelect(date, selection,false);
                                    showappointment();
                                    selectAppoinment.setText("");





                            break;
                        case "move":


                            break;


                    }



                }


            }

        });*/






    }



    public void selectAction(View view) {


        selection = Integer.parseInt(selectAppoinment.getText().toString());
        if(selection == 99){

                    Toast.makeText(getApplicationContext(), "PLEASE SELECT A APPOINTMENT BEFORE PRESS THE BUTTON " , Toast.LENGTH_LONG).show();

        }else {

            switch (header) {

                case "view":
                    String pretitle = handleDB.deleteSelect(date, selection,true) ;
                    Intent viewEditSelect = new Intent(this,CreateAppoinment.class);
//                  handleDB.deleteSelect(date, selection,true);
                    viewEditSelect.putExtra("title",pretitle);
                    viewEditSelect.putExtra("date",date);

                    startActivity(viewEditSelect);



                    break;
                case "delete":

                    AlertDialog.Builder mbuild = new AlertDialog.Builder(ViewAppointment.this);
                    View mView = getLayoutInflater().inflate(R.layout.confirm_delete, null);
                     Button confirmNo =(Button) mView.findViewById(R.id.confirmNo);
                     Button confirmYes =(Button) mView.findViewById(R.id.confirmYes);
                    TextView confirmMsg = (TextView) mView.findViewById(R.id.confirmDeleteText);

                    String confirmDel = handleDB.deleteSelect(date,selection,true);
//                    Toast.makeText(getApplicationContext(),"123", Toast.LENGTH_LONG).show();
                    confirmMsg.setText("Would you like to delete event \" "+ confirmDel + " \"" );
// set action listners for dialog box buttons
                    confirmYes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            handleDB.deleteSelect(date, selection,false);
                            showappointment();
                            selectAppoinment.setText("");
                            Intent cont = new Intent(ViewAppointment.this,ViewAppointment.class);
                            cont.putExtra("date",date);
                            cont.putExtra("option",header);

                        }
                    });


                    confirmNo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            showappointment();
                            selectAppoinment.setText("");
                            Intent cont = new Intent(ViewAppointment.this,ViewAppointment.class);
                            cont.putExtra("date",date);
                            cont.putExtra("option",header);



                        }
                    });
//show the dialog box
                    mbuild.setView(mView);
                    AlertDialog dialog = mbuild.create();
                    dialog.show();


                    break;
                case "move":


                    break;


            }



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
