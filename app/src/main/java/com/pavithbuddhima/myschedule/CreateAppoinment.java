package com.pavithbuddhima.myschedule;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class CreateAppoinment extends AppCompatActivity {

    static double mathTime;
    static int hour;
    static int minute;
    static int day;
    static String date;

    //Thesaurus stuff

    Button thesaurusBtn , thesaurusBtn2;

    ThesaurusAdapter thesaurusAdapter;
    ListView synonymlist; //list view to store the synonyms
    PopupWindow popupWindow;

    //variables to store the input from the text box
    private String inputWord;
    //constant for the thesaurus service key
    public static final String THESAURUS_KEY = "UntLDHf7wjETU9pRAKzY";
    //variable to store the language
    private String lang = "en_US";

    EditText title;
    TimePicker time;
    EditText discription , thesaurusWord;

    Button save ;

    AppoinmentDataBase handleDB;

    String selTime;
    String selDate;
    String preTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_create_appoinment);





        Intent getdate = getIntent();
        selDate = getdate.getStringExtra("date");
        preTitle = getdate.getStringExtra("title");

        title = (EditText) findViewById(R.id.titleedit);
        time = (TimePicker) findViewById(R.id.timeedit);
        discription = (EditText) findViewById(R.id.editdetail);
        save = (Button) findViewById(R.id.savebtn);

        thesaurusWord =  (EditText) findViewById(R.id.thesaurusWord);

//        thesaurusbtn =(Button) findViewById(R.id.thesaurusbtn) ;

        //initialize the text box and 2 buttons in the thesaurus area
//        thesaurusWord = (EditText) findViewById(R.id.wordInput);
        thesaurusBtn = (Button) findViewById(R.id.thesaurusbtn);
//        thesaurusBtn.setOnClickListener(this);
        thesaurusBtn2 = (Button) findViewById(R.id.thesaurusbtn2);
//        thesaurusBtn2.setOnClickListener(this);


        handleDB = new AppoinmentDataBase(this, null, null, 1);



/*
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
*/


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Hides the virtual keyboard when the buttons are clicked
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);



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

                   /* hour = time.getCurrentHour();

                    minute = time.getCurrentMinute();

                    mathTime = (hour * 60) + minute;

                    selTime = "" + hour + ":" + minute;

                    boolean updateResult = handleDB.updateAppointment(date ,preTitle ,title.getText().toString() , selTime ,discription.getText().toString(),mathTime);

                    if(updateResult){
                        Toast.makeText(getApplicationContext(), "Update " + preTitle +" appoitment SUCCESSFULL", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(), " something went WRONG", Toast.LENGTH_LONG).show();
                    }
*/


                    hour = time.getCurrentHour();

                    minute = time.getCurrentMinute();

                    mathTime = (hour * 60) + minute;

                    selTime = "" + hour + ":" + minute;

                    boolean updateResult = handleDB.updateAppointment(selDate ,preTitle ,title.getText().toString() , selTime ,discription.getText().toString(),mathTime);
//                Toast.makeText(getApplicationContext(), preTitle, Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), selDate, Toast.LENGTH_LONG).show();
                    if(updateResult){
                        Toast.makeText(getApplicationContext(), "Update " + preTitle +" appoitment SUCCESSFULL", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(), " something went WRONG", Toast.LENGTH_LONG).show();
                    }


                }
        }
        });




        thesaurusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Hides the virtual keyboard when the buttons are clicked
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);



                inputWord = thesaurusWord.getText().toString();

                if(inputWord.equals(null) || inputWord.equals("")){
                    thesaurusWord.setError("Please enter a word and press the button");
                } else{
                    resultPopUp(v);
                }


                thesaurusWord.setText("");



            }
        });


        thesaurusBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Hides the virtual keyboard when the buttons are clicked
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);




                int startSelection=discription.getSelectionStart();
                int endSelection=discription.getSelectionEnd();

                String selectedText = discription.getText().toString().substring(startSelection, endSelection);
                Toast.makeText(getBaseContext(),"You selected the word \"" + selectedText + "\"",Toast.LENGTH_SHORT).show();

                inputWord = selectedText;
                resultPopUp(v);


            }
        });

    }


    //Helper method to determine if Internet connection is available.
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /**
     *
     * This function creates a popup window that will display all the results of the
     * returned XML
     *
     * @param v The current view instance is passed
     */
    public void resultPopUp (View v) {

        try {
            //get an instance of layout inflater
            LayoutInflater inflater = (LayoutInflater) CreateAppoinment.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //initiate the view
            View layout = inflater.inflate(R.layout.list_view_thesaurus,
                    (ViewGroup) findViewById(R.id.popUpList));

            //initialize a size for the popup
            popupWindow = new PopupWindow(layout, 1000, 1500 ,  true);
            // display the popup in the center
            popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);

            //Get reference to our ListView
            synonymlist = (ListView) layout.findViewById(R.id.synonymList);

		    /*
		     * If network is available download the xml from the Internet.
		     * If not toast internet error and close the popup
		    */
            if(isNetworkAvailable()){

                SitesDownloadTask download = new SitesDownloadTask();
                download.execute();
            }else{

                Toast.makeText(getBaseContext() , "No internet Connection. Please connect " +
                        "your device to the internet and try again" , Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * AsyncTask that will download the xml file for us and store it locally.
     * After the download is done we'll parse the local file.
     */
    private class SitesDownloadTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... arg0) {
            //Download the file
            try {
                DownloadFromUrl("http://thesaurus.altervista.org/thesaurus/v1?word=" + inputWord +
                                "&language="+ lang +"&%20key="+ THESAURUS_KEY +"&output=xml",
                        openFileOutput("synonyms.xml", Context.MODE_PRIVATE));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result){

            //setup our Adapter and set it to the ListView.
            thesaurusAdapter = new ThesaurusAdapter(CreateAppoinment.this, -1,
                    ThesaurusXMLPullParser.getSynonymsFromFile(CreateAppoinment.this));
            synonymlist.setAdapter(thesaurusAdapter);

        }
    }

    /**
     * This method will try to download the xml form the internet
     * @param URL URL to make the request
     * @param fos The name to store the XML file
     */
    public static void DownloadFromUrl(String URL, FileOutputStream fos) {
        try {

            java.net.URL url = new URL(URL); //URL of the file

			/* Open a connection to that URL. */
            URLConnection connection = url.openConnection();


            //input stream that'll read from the connection
            InputStream is = connection.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);

            //buffer output stream that'll write to the xml file
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            //write to the file while reading
            byte data[] = new byte[1024];
            int count;
            //loop and read the current chunk
            while ((count = bis.read(data)) != -1) {
                //write this chunk
                bos.write(data, 0, count);
            }

            bos.flush();
            bos.close();

        } catch (IOException e) {
        }
    }


}
