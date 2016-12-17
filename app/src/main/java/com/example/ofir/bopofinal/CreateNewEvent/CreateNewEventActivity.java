package com.example.ofir.bopofinal.CreateNewEvent;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.Toolbar;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ofir.bopofinal.LoginRegister.LoggedInUserService;
import com.example.ofir.bopofinal.LoginRegister.LoginRequest;
import com.example.ofir.bopofinal.LoginRegister.userValidation;
import com.example.ofir.bopofinal.MainAppScreenActivity;
import com.example.ofir.bopofinal.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class CreateNewEventActivity extends Activity implements View.OnClickListener, Spinner.OnItemSelectedListener, CompoundButton.OnCheckedChangeListener {


    private static EditText etTitle, etDescription, etDate, etTime, etLocation, etMaxParticipants;
    private static Button bCreate;
    private static Switch approveSwitch;
    private static Intent backToMainIntent;
    private static DatePickerDialog datePickerDialog;
    private static TimePickerDialog timePickerDialog;
    private static Spinner categories_selector;
    final int PLACE_PICKER_REQUEST = 1;
    private  Intent searchIntent;
    private String check = "false";
    private ArrayList<String> categories;
    private static ProgressDialog progressDialog;
    private static LoggedInUserService loggedInUserService;

    Calendar calendar = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new);

        etTitle = (EditText) findViewById(R.id.etTitle);
        etDescription = (EditText) findViewById(R.id.etDescription);
        etDate = (EditText) findViewById(R.id.etDate);
        etTime = (EditText) findViewById(R.id.etTime);
        etLocation = (EditText) findViewById(R.id.etLocation);
        etMaxParticipants = (EditText) findViewById(R.id.etMaxParticipants);
      //  etCategory = (EditText) findViewById(R.id.etCategorey);
        categories_selector = (Spinner)  findViewById(R.id.spinner);

        approveSwitch = (Switch) findViewById(R.id.sApprove);
        approveSwitch.setText("no");

        bCreate = (Button) findViewById(R.id.bCreate);
       // bBack = (Button) findViewById(R.id.bBack);

        loggedInUserService = LoggedInUserService.getInstance();


        categories = new ArrayList<>();

        categories_selector.setOnItemSelectedListener(this);
        approveSwitch.setOnCheckedChangeListener(this);

        progressDialog = new ProgressDialog(this);





    }

    @Override
    protected void onStart() {
        super.onStart();
        fetch_categories fetch_categories = new fetch_categories();
        fetch_categories.getData(CreateNewEventActivity.this,categories,categories_selector);
    }

    @Override
    protected void onStop() {
        super.onStop();
        progressDialog.dismiss();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
          /*  case R.id.bBack:

                CreateNewEventActivity.this.startActivity(backToMainIntent);
                break;*/

            case R.id.bCreate:

                progressDialog.setMessage("Creating new event...");
                progressDialog.setTitle("");


                final String title = etTitle.getText().toString().trim();
                final String description  = etDescription.getText().toString().trim();
                final String date = etDate.getText().toString().trim();
                final String time = etTime.getText().toString().trim();
                final String location = etLocation.getText().toString().trim();
                final String maxParticipants = etMaxParticipants.getText().toString().trim();
                final String category = categories_selector.getSelectedItem().toString();
                final String ack = check;
                final String id = Integer.toString(loggedInUserService.getM_id());



                if(category.equals("Choose category")){
                    userValidation.alertDialog(CreateNewEventActivity.this,"Please choose a category", "Retry");
                }else{
           //         progressDialog.show();
                    EditText[] FirstList = {etTitle, etDescription,etDate,etTime,etLocation,etMaxParticipants};
                    boolean bool =  userValidation.emptyFields(FirstList,"please fill in this field");
                    if (bool){
                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonResponse = new JSONObject(response);
                                    boolean success = jsonResponse.getBoolean("success");

                                    if (success) {
                                        Toast.makeText(CreateNewEventActivity.this,title + " created successfully",
                                                Toast.LENGTH_LONG).show();
                                        backToMainIntent = new Intent(CreateNewEventActivity.this, MainAppScreenActivity.class);
                                        CreateNewEventActivity.this.startActivity(backToMainIntent);

                                    }  else {
                                        userValidation.alertDialog(CreateNewEventActivity.this,"Creating new event failed", "Retry");
                                    }


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }

                        };
                        CreateNewEventRequest createNewEventRequest = new CreateNewEventRequest(title, description, date, time, location, maxParticipants,
                                                      category, ack, id,responseListener);
                        RequestQueue queue = Volley.newRequestQueue(CreateNewEventActivity.this);
                        queue.add(createNewEventRequest);
                    }
                }



                break;

            case R.id.etDate:
                datePickerDialog = new DatePickerDialog(this, onDateSetListener, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
                break;

            case R.id.etTime:
                timePickerDialog =  new TimePickerDialog(this, onTimeSetListener, calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE), true);
                timePickerDialog.show();
                break;

            case R.id.etLocation:
               // intent = new Intent(CreateNewEventActivity.this, MapsActivity.class);
               // CreateNewEventActivity.this.startActivity(intent);
               // Toolbar toolbar =  (Toolbar) findViewById(R.id.toolbar);

                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                progressDialog.setMessage("Loading map...");
                progressDialog.setTitle("");
                progressDialog.show();
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                    }
                },3000);


                try {
                    searchIntent = builder.build(this);

                   startActivityForResult(searchIntent,PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }

                break;

        }
    }

    protected void onActivityResult(int requestCode,int resultCode, Intent data){
        if(requestCode ==  PLACE_PICKER_REQUEST){
            if(requestCode == 1){
                Place place = PlacePicker.getPlace(this,data);
                String address = String.format("%s",place.getAddress());
                etLocation.setText(address);
            }
        }
    }

    DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {

            DecimalFormat df = new DecimalFormat("00");
            String show = String.valueOf(df.format(dayOfMonth) + "/" + String.valueOf(df.format(monthOfYear + 1)
                    + "/" + String.valueOf(df.format(year))));
            etDate.setText(show);
        }
    };

    TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
            DecimalFormat df = new DecimalFormat("00");
            String show = String.valueOf(df.format(hourOfDay) + ":" + String.valueOf(df.format(minute)));
            etTime.setText(show);
        }
    };



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id){
       if(position > 0) categories.remove(0);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        categories.add(0,"Choose category");

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
         check = "false";
        if(isChecked) {
            approveSwitch.setText("yes");
            check = "true";
        }else {
            approveSwitch.setText("no");
            check = "false";
        }


    }
}
