package com.example.ofir.bopofinal.LoginRegister;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.support.v7.app.AlertDialog;
import android.content.Intent;
//import android.icu.text.DecimalFormat;
import android.os.AsyncTask;
import android.os.Build;


//import android.icu.util.Calendar;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;
import  java.util.Calendar;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.ofir.bopofinal.R;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener,android.text.TextWatcher {

    private static EditText etAge;
    private static EditText etName;
    private static EditText etUsername;
    private static EditText etPassword;
    private static Button bRegister;
    private static Button bBack;
    private static EditText etEmail;

    private static  Intent intent;
    private static DatePickerDialog datePickerDialog;
    Calendar calendar = Calendar.getInstance();

    private String email;

    private static LoginActivity m_instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etAge = (EditText) findViewById(R.id.etAge);
        etName = (EditText) findViewById(R.id.etName);
        etUsername = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bRegister = (Button) findViewById(R.id.bRegister);
        bBack = (Button) findViewById(R.id.bBack);
        etEmail = (EditText) findViewById(R.id.etEmail);

        intent = new Intent(RegisterActivity.this, LoginActivity.class);

        etEmail.addTextChangedListener(this);

        m_instance = LoginActivity.getInstance();


    }

    public  boolean isValidEmail(CharSequence email) {
      return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bRegister: //Register
                final String name = etName.getText().toString();
                final String username = etUsername.getText().toString();


                final int age = Integer.parseInt(etAge.getText().toString());
                final String password = etPassword.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                Toast.makeText(RegisterActivity.this, "Thank you "+name+" for registering",
                                        Toast.LENGTH_LONG).show();
                                RegisterActivity.this.startActivity(intent);
                            } else {
                                Builder builder = new Builder(RegisterActivity.this);
                                builder.setMessage("Register Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(name, username, age, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
                break;//end Register

            case  R.id.bBack: //back
                RegisterActivity.this.startActivity(intent);
                break; //end back

            case R.id.etAge://select age
                datePickerDialog = new DatePickerDialog(this, onDateSetListener, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();

                break; //age
        }
    }
    DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {

            DecimalFormat df = new DecimalFormat("00");
            String show = String.valueOf(df.format(dayOfMonth) + "/" + String.valueOf(df.format(monthOfYear + 1)
                    + "/" + String.valueOf(df.format(year))));
            etAge.setText(show);


        }
    };

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(final Editable editable) {
        email = etEmail.getText().toString();
        new AsyncTask<Void, Void, Boolean>() {
            protected Boolean doInBackground(Void... params) {
                if (!isValidEmail(email))
                    return false;
                return true;
            }

            protected void onPostExecute(Boolean isEmailValid) {
                //isEmailValid is the variable received from the doInBackground() method
                if (!isEmailValid) {
                    LoginActivity.alertDialog(RegisterActivity.this,"test","test");
                }


            }
        }.execute();
    }




 }



