package com.example.ofir.bopofinal.CreateNewEvent;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Html;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.ofir.bopofinal.LoginRegister.LoggedInUserService;
import com.example.ofir.bopofinal.LoginRegister.userValidation;
import com.example.ofir.bopofinal.MainAppScreenActivity;
import com.example.ofir.bopofinal.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;


import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.Timer;
import java.util.TimerTask;


public class CreateNewEventActivity extends AppCompatActivity implements
        View.OnClickListener, Spinner.OnItemSelectedListener, CompoundButton.OnCheckedChangeListener ,TextWatcher, TextView.OnEditorActionListener {


    private static EditText etTitle, etDescription, etDate, etTime, etLocation, etMaxParticipants;
    private static TextView mTvCreateRole, mTvCreateCurrentPeople;
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
    private static RelativeLayout relativeLayout;


    String title,description, date, time, location, maxParticipants,currentarticipants, category,ack, uerId;
    String afterTitle,afterDescription, afterDate, afterTime, afterLocation ,afterMaxParticipants, afterCurrentarticipants,
    afterCategory,afterAck;

    EditText[] mEditTexts = new EditText[]{};



    Calendar calendar = Calendar.getInstance();

    ComponentName mComponentName;

    private final int CREATE_EVENT  = 1;
    private final int EDIT_EVENT  = 2;


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

        categories_selector = (Spinner)  findViewById(R.id.spinner);

        mTvCreateRole = (TextView) findViewById(R.id.tvCreateRole);
        mTvCreateCurrentPeople = (TextView) findViewById(R.id.tvCurrentPeople);

        approveSwitch = (Switch) findViewById(R.id.sApprove);
        approveSwitch.setText("no");

        bCreate = (Button) findViewById(R.id.bCreate);

        loggedInUserService = LoggedInUserService.getInstance();


        categories = new ArrayList<>();

        categories_selector.setOnItemSelectedListener(this);
        approveSwitch.setOnCheckedChangeListener(this);

        progressDialog = new ProgressDialog(this);

        mComponentName =  this.getCallingActivity();

        relativeLayout = (RelativeLayout) findViewById(R.id.rel);

    }

    @Override
    protected void onStart() {
        super.onStart();
        fetch_categories fetch = new fetch_categories();
        fetch.getData(CreateNewEventActivity.this,categories,categories_selector,"");



        if(mComponentName != null){//edit event

            //get spinner value
            Intent intent = getIntent();
            afterCategory  = intent.getStringExtra("category");
            fetch.getData(CreateNewEventActivity.this,categories,categories_selector,afterCategory );

            mEditTexts = new EditText[]{etTitle, etDescription, etDate, etTime, etLocation};

            for(EditText editText: mEditTexts){
                editText.addTextChangedListener(this);
            }

            retrieveData();


        }else{
            getSupportActionBar().setTitle("Create new event");
        }


    }

    private void retrieveData(  ) {
        relativeLayout.setAlpha(0);
        setProgressDialogText("Fetching data...","");
        //String title = null, description = null, time = null, date = null, location = null, role = null, maxPeople = null,currentPeople = null;
        Intent intent = getIntent();

        //get edit text values
        String title = intent.getStringExtra("title");

        setButtonState(View.INVISIBLE);

        etTitle.setText(title);
        etDescription.setText(intent.getStringExtra("description"));
        etDate.setText(intent.getStringExtra("time"));
        etTime.setText(intent.getStringExtra("date"));
        etLocation.setText(intent.getStringExtra("location"));
        etMaxParticipants.setText(Html.fromHtml("maximum people "+intent.getStringExtra("maxPeople").replaceAll("[^0-9,<b>,</b>]", "")));




        getSupportActionBar().setTitle("Edit event" + title);

        EditText[] editTexts = {etTitle, etDescription, etDate, etTime, etLocation, etMaxParticipants};

        for (EditText editText: editTexts)//set text watcher to all edit text
            editText.addTextChangedListener(this);

        //set role

        mTvCreateRole.setVisibility(View.VISIBLE);
        mTvCreateRole.setText(Html.fromHtml("you are the "+intent.getStringExtra("role")+  " of the event"));

        //set current people

        mTvCreateCurrentPeople.setVisibility(View.VISIBLE);
        mTvCreateCurrentPeople.setText(Html.fromHtml("current people: "+ intent.getStringExtra("currentPeople")));

        //


        //get ACK value
        String ACK   = intent.getStringExtra("ACK");
        if (ACK.equals("true")) {
            approveSwitch.setText("yes");
            approveSwitch.setChecked(true);
        }else{
            approveSwitch.setText("no");
            approveSwitch.setChecked(false);
        }

        //set button text
        bCreate.setText("Edit event");

        progressDialog.hide();
        relativeLayout.setAlpha(1);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.bCreate:

                  title = etTitle.getText().toString().trim();
                  description  = etDescription.getText().toString().trim();
                  date = etDate.getText().toString().trim();
                  time = etTime.getText().toString().trim();
                  location = etLocation.getText().toString().trim();
                  maxParticipants = etMaxParticipants.getText().toString().trim();
                  category = categories_selector.getSelectedItem().toString();
                  ack = check;
                  uerId = Integer.toString(loggedInUserService.getM_id());



                if(category.equals("Choose category")){
                    userValidation.alertDialog(CreateNewEventActivity.this,"Please choose a category", "Retry");
                }else{
                   progressDialog.show();
                    EditText[] FirstList = {etTitle, etDescription,etDate,etTime,etLocation,etMaxParticipants};
                    boolean bool =  userValidation.emptyFields(FirstList,"please fill in this field");
                    if (bool){
                        if(mComponentName != null ) {// we came from to edit
                            setProgressDialogText("Editing event details...","");
                            event( EDIT_EVENT,  afterTitle, afterDescription,  afterDate,  afterTime,
                                    afterLocation,  maxParticipants,  afterCategory, ack, uerId,
                                    " edited successfully","editing the event failed");


                        }
                        else{
                            setProgressDialogText("Creating new event...","");
                            event(CREATE_EVENT,  title, description,  date,  time,
                                    location,  maxParticipants,  category, ack, uerId,
                                    " created successfully","Creating new event failed");
                        }

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
               // Toolbar toolbar =  (Toolbar) findViewById(R.uerId.toolbar);

                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                setProgressDialogText("Loading map...","");
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

    private void setProgressDialogText( String text , String title ) {
        progressDialog.setMessage(text);
        progressDialog.setTitle(title);
    }

    private void event(final int choice, final String title, String description, String date, String time,
                       String location, String maxParticipants, String category, String ack,
                       final String userID, final CharSequence textOkay, final CharSequence textFail ) {

          Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("JSONLog",response);
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success) {
                        progressDialog.dismiss();


                        Toast.makeText(CreateNewEventActivity.this, title + textOkay,
                                Toast.LENGTH_LONG).show();
                        backToMainIntent = new Intent(CreateNewEventActivity.this, MainAppScreenActivity.class);
                        CreateNewEventActivity.this.startActivity(backToMainIntent);

                    }  else {
                        progressDialog.dismiss();
                        userValidation.alertDialog(CreateNewEventActivity.this,textFail, "Retry");
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        };
        sendToServer( choice,   title,  description,  date,  time,
                location,  maxParticipants,  category,  ack,userID,responseListener );


    }


    private void sendToServer( int choice,  String title, String description, String date, String time,
                              String location, String maxParticipants, String category, String ack,
                               String id, Response.Listener<String> responseListener) {

        CreateNewEventRequest createNewEventRequest = new CreateNewEventRequest(choice ,title, description,
                date, time, location, maxParticipants,
                category, ack, id,responseListener);
        RequestQueue queue = Volley.newRequestQueue(CreateNewEventActivity.this);

        queue.add(createNewEventRequest);
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

    @Override
    public void beforeTextChanged( CharSequence charSequence, int i, int i1, int i2 ) {
       // setButtonState(View.INVISIBLE);
    }

    @Override
    public void onTextChanged( CharSequence charSequence, int i, int i1, int i2 ) {
        setButtonState(View.VISIBLE);
    }

    @Override
    public void afterTextChanged( Editable editable ) {
        setButtonState(View.VISIBLE);

        afterTitle = etTitle.getText().toString();
        afterDescription = etDescription.getText().toString();
        afterDate = etDate.getText().toString();
        afterTime = etTime.getText().toString();
        afterLocation = etLocation.getText().toString();
        afterMaxParticipants  = etMaxParticipants.getText().toString();



     /*   if (Integer.parseInt(afterMaxParticipants) > Integer.parseInt(maxParticipants) ){
            userValidation.alertDialog(this,"maximum number of peoples cannot be more then current amount","retry");
        }*/




        for(EditText editText: mEditTexts){
            editText.setSingleLine();
            editText.setMaxLines(1);
            editText.setInputType(InputType.TYPE_CLASS_TEXT);
            editText.setOnEditorActionListener(this);


        }



    }
    private void setButtonState(int state) {
        bCreate.setVisibility(state);
    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                actionId == EditorInfo.IME_ACTION_DONE ||
                event.getAction() == KeyEvent.ACTION_DOWN &&
                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
            if (!event.isShiftPressed()) {
              Log.i("textView",textView.getText().toString());

                return true; // consume.
            }
        }
        return false; // pass on to other listeners.
    }

 }

