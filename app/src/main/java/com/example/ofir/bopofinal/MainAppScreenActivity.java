package com.example.ofir.bopofinal;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.ofir.bopofinal.Categories.SuggestCategoryActivity;
import com.example.ofir.bopofinal.LoginRegister.LoginActivity;
import com.example.ofir.bopofinal.LoginRegister.RegisterActivity;
import com.example.ofir.bopofinal.LoginRegister.RegisterRequest;
import com.example.ofir.bopofinal.MainActivity;
import com.example.ofir.bopofinal.R;

import org.json.JSONException;
import org.json.JSONObject;

public class MainAppScreenActivity extends AppCompatActivity implements View.OnClickListener {

    private static ImageButton btnMyEvents;
    private static ImageButton btnSuggestCategoty;
    private static Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app_screen);

          btnMyEvents = (ImageButton) findViewById(R.id.ibMyEvents);
      //  btnAddEvent = (EditText) findViewById(R.id.etName);
      //  btnSearch = (EditText) findViewById(R.id.etUserName);
     //   btnProfile = (EditText) findViewById(R.id.etPassword);
     //   btnSettings = (Button) findViewById(R.id.bRegister);
          btnSuggestCategoty = (ImageButton) findViewById(R.id.ibSuggestCategory);
     //   btnLogout = (Button) findViewById(R.id.bBack);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ibMyEvents:
                intent = new Intent(MainAppScreenActivity.this, LoginActivity.class);
                MainAppScreenActivity.this.startActivity(intent);
                break;
            case R.id.ibSuggestCategory:
                intent = new Intent(MainAppScreenActivity.this, SuggestCategoryActivity.class);
                MainAppScreenActivity.this.startActivity(intent);
                break;
        }
    }
}
