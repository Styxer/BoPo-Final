package com.example.ofir.bopofinal;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TimePicker;

import com.example.ofir.bopofinal.LoginRegister.LoginActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class CreateNewEventActivity extends Activity implements View.OnClickListener {


    private static EditText etTitle, etDescription, etDate, etTime, etLocation, etMaxParticipants, etCategory;
    private static Button bCreate, bBack;
    private static Intent intent;
    private static DatePickerDialog datePickerDialog;
    private static TimePickerDialog timePickerDialog;
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
        etCategory = (EditText) findViewById(R.id.etCategorey);

        bCreate = (Button) findViewById(R.id.bCreate);
        bBack = (Button) findViewById(R.id.bBack);


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
}
