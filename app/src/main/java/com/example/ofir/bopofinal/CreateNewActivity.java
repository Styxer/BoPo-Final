package com.example.ofir.bopofinal;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class CreateNewActivity extends Activity {


    private static EditText etTitle, etDescription, etDate, etTime, etLocation, etMaxParticipants, etCategory;
    private static Button bCreate, bBack;




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

}
