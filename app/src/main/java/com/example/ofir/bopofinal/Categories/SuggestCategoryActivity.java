package com.example.ofir.bopofinal.Categories;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.ofir.bopofinal.R;

public class SuggestCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest_category);

        final EditText etCatName = (EditText) findViewById(R.id.etCategoryName);
        final Button btnSend = (Button) findViewById(R.id.btnSend);
        final Button btnBack = (Button) findViewById(R.id.btnBack);
    }
}
