package com.example.ofir.bopofinal.Profile;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.ofir.bopofinal.LoginRegister.LoggedInUserService;
import com.example.ofir.bopofinal.MainAppScreenActivity;
import com.example.ofir.bopofinal.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.Calendar;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener, android.text.TextWatcher {
    private static DatePickerDialog datePickerDialog;
    Calendar calendar = Calendar.getInstance();
    private String m_userID,m_name, m_email, m_birthday, m_phone, m_address, m_password;;
    private static Intent intent;
    Button btnSavechanges;
    String AfterChangeName, AfterChangeEmail, AfterChangeBirthday, AfterChangePhone, AfterChangeAddress, AfterChangePassword, AfterChangeImage;
    TextView etName, etEmail, etBirthday, etPhone, etAddress, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setTitle("My profile");
        m_userID = LoggedInUserService.getInstance().getM_id() + "";
        m_name = LoggedInUserService.getInstance().getM_name();
        m_email = LoggedInUserService.getInstance().getM_email();
        m_birthday = LoggedInUserService.getInstance().getM_birthday();
        m_phone = LoggedInUserService.getInstance().getM_phone_number();
        m_address = LoggedInUserService.getInstance().getM_address();



        btnSavechanges =(Button)findViewById(R.id.btnSaveChanges);
        etName = (EditText) findViewById(R.id.etProfileName);
        etEmail = (EditText) findViewById(R.id.etProfileEmail);
        etBirthday = (EditText) findViewById(R.id.etProfileBirthday);
        etPhone = (EditText) findViewById(R.id.etProfilePhone);
        etAddress = (EditText) findViewById(R.id.etProfileAddress);
        etPassword = (EditText) findViewById(R.id.etProfilePassword);

        etName.setText(m_name);
        etEmail.setText(m_email);
        etBirthday.setText(m_birthday);
        etPhone.setText(m_phone);
        etAddress.setText(m_address);

        etName.addTextChangedListener(this);
        etEmail.addTextChangedListener(this);
        etBirthday.addTextChangedListener(this);
        etPhone.addTextChangedListener(this);
        etAddress.addTextChangedListener(this);
        etPassword.addTextChangedListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSaveChanges:
                AfterChangeName = etName.getText().toString();
                AfterChangeEmail = etEmail.getText().toString();
                AfterChangeBirthday = etBirthday.getText().toString();
                AfterChangePhone = etPhone.getText().toString();
                AfterChangeAddress = etAddress.getText().toString();
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {

                                LoggedInUserService.getInstance().setM_name(AfterChangeName);
                                LoggedInUserService.getInstance().setM_email(AfterChangeEmail);
                                LoggedInUserService.getInstance().setM_birthday(AfterChangeBirthday);
                                LoggedInUserService.getInstance().setM_phone_number(AfterChangePhone);
                                LoggedInUserService.getInstance().setM_address(AfterChangeAddress);

                                Toast.makeText(ProfileActivity.this, "profile changes saved",
                                        Toast.LENGTH_LONG).show();
                                intent = new Intent(ProfileActivity.this, MainAppScreenActivity.class);
                                ProfileActivity.this.startActivity(intent);

                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
                                builder.setMessage("change profile data failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                ProfileRequest profileRequest = new ProfileRequest(m_userID,AfterChangeName, AfterChangeEmail, AfterChangeBirthday, AfterChangePhone, AfterChangeAddress, responseListener);
                RequestQueue queue = Volley.newRequestQueue(ProfileActivity.this);
                queue.add(profileRequest);
                break;
            case R.id.etProfileBirthday:
                datePickerDialog = new DatePickerDialog(this, onDateSetListener, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            break;

        }
    }
    DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {

            DecimalFormat df = new DecimalFormat("00");
            String show = String.valueOf(df.format(dayOfMonth) + "/" + String.valueOf(df.format(monthOfYear + 1)
                    + "/" + String.valueOf(df.format(year))));
            etBirthday.setText(show);


        }
    };
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        btnSavechanges.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onTextChanged(CharSequence s, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        btnSavechanges.setVisibility(View.VISIBLE);
    }
}
