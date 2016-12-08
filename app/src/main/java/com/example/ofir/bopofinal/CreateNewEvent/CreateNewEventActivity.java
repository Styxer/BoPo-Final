package com.example.ofir.bopofinal.CreateNewEvent;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ofir.bopofinal.MainAppScreenActivity;
import com.example.ofir.bopofinal.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class CreateNewEventActivity extends Activity implements View.OnClickListener, Spinner.OnItemSelectedListener {


    private static EditText etTitle, etDescription, etDate, etTime, etLocation, etMaxParticipants;
    private static Button bCreate, bBack;
    private static Intent intent;
    private static DatePickerDialog datePickerDialog;
    private static TimePickerDialog timePickerDialog;
    private static Spinner categories_selector;


    private ArrayList<String> categories;

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

        bCreate = (Button) findViewById(R.id.bCreate);
        bBack = (Button) findViewById(R.id.bBack);


        categories = new ArrayList<>();

        categories_selector.setOnItemSelectedListener(this);

        fetch_categories fetch_categories = new fetch_categories();
        fetch_categories.getData(CreateNewEventActivity.this,categories,categories_selector);

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bBack:
                intent = new Intent(CreateNewEventActivity.this, MainAppScreenActivity.class);
                CreateNewEventActivity.this.startActivity(intent);
                break;

            case R.id.bCreate:

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
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        categories.remove(0);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        categories.add(0,"Choose category");

    }
}
