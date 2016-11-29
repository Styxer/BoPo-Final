package com.example.ofir.bopofinal.LoginRegister;

import android.app.DatePickerDialog;
import android.content.Intent;
//import android.icu.text.DecimalFormat;


//import android.icu.util.Calendar;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import  java.util.Calendar;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.ofir.bopofinal.R;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener,android.text.TextWatcher {

    private static EditText etDateOfBirth;
    private static EditText etName;
    private static EditText etAddress;
    private static EditText etPassword;
    private static Button bRegister;
    private static Button bBack;
    private static EditText etEmail;
    private static EditText etPhoneNumber;

    private static  Intent intent;
    private static DatePickerDialog datePickerDialog;
    Calendar calendar = Calendar.getInstance();

    private String email;
    private  String cuurentDate;

    private static LoginActivity m_instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etDateOfBirth = (EditText) findViewById(R.id.etDateOfBirth);
        etName = (EditText) findViewById(R.id.etName);
        etAddress = (EditText) findViewById(R.id.etAdress);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bRegister = (Button) findViewById(R.id.bRegister);
        bBack = (Button) findViewById(R.id.bBack);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPhoneNumber = (EditText) findViewById(R.id.etPhoneNumber);

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
                final String email = etEmail.getText().toString();
                final String dateOfBirth = etDateOfBirth.getText().toString();
                final String address = etAddress.getText().toString();
                final String phoneNumber = etPhoneNumber.getText().toString();
                final String password = etPassword.getText().toString();
                final String role = "regular";

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

                RegisterRequest registerRequest = new RegisterRequest(role, name,email, dateOfBirth, password, phoneNumber, address ,responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
                break;//end Register

            case  R.id.bBack: //back
                RegisterActivity.this.startActivity(intent);
                break; //end back

            case R.id.etDateOfBirth://select age
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
            etDateOfBirth.setText(show);


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
        etEmail.setSingleLine();
        etEmail.setMaxLines(1);
        etEmail.setInputType(InputType.TYPE_CLASS_TEXT);

        etEmail.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                        actionId == EditorInfo.IME_ACTION_DONE ||
                        event.getAction() == KeyEvent.ACTION_DOWN &&
                                event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    if (!event.isShiftPressed()) {
                        if (!isValidEmail(email)) {
                            LoginActivity.alertDialog(RegisterActivity.this,"Invalid email","Retry");
                        }

                        return true; // consume.
                    }
                }
                return false; // pass on to other listeners.
            }
        });
    }




 }



