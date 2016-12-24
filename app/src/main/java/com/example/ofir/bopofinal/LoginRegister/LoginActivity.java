package com.example.ofir.bopofinal.LoginRegister;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.ofir.bopofinal.Administrator.AdministratorMainScreenActivity;
import com.example.ofir.bopofinal.MainAppScreenActivity;
import com.example.ofir.bopofinal.R;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static EditText etEmail;
    private static EditText etPassword;
    private static Button bRegister;
    private static Button bLogin;

    static private LoginActivity instance = null;

    private static ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bRegister = (Button) findViewById(R.id.bRegister);
        bLogin = (Button) findViewById(R.id.bLogin);

        getSupportActionBar().setTitle("Login");

        progressDialog = new ProgressDialog(this);

    }




    public static LoginActivity getInstance() {
        if (instance == null)
            instance = new LoginActivity();
        return instance;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bLogin: //login

                progressDialog.setMessage("Signing...");
                progressDialog.setTitle("");
                progressDialog.show();
                final String email = etEmail.getText().toString();
                final String password = etPassword.getText().toString();

                EditText[] FirstList = {etEmail, etPassword};
               boolean bool =  userValidation.emptyFields(FirstList,"please fill in this field");
                if (bool){
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");

                                if (success) {
                                    progressDialog.dismiss();
                                    //get
                                    int user_id = jsonResponse.getInt("user_id");
                                    String user_role = jsonResponse.getString("role");
                                    String user_name = jsonResponse.getString("name");
                                    String user_email = jsonResponse.getString("email");
                                    String user_birthday = jsonResponse.getString("birthday");
                                    // int user_rating = jsonResponse.getInt("rating");
                                    String user_phone_number = jsonResponse.getString("phone_number");
                                    String user_address = jsonResponse.getString("address");

                                    //set
                                    LoggedInUserService loggedInUserService = LoggedInUserService.getInstance();
                                    loggedInUserService.setM_id(user_id);
                                    loggedInUserService.setM_role(user_role);
                                    loggedInUserService.setM_name(user_name);
                                    loggedInUserService.setM_email(user_email);
                                    loggedInUserService.setM_birthday(user_birthday);
                                    loggedInUserService.setM_phone_number(user_phone_number);
                                    loggedInUserService.setAddress(user_address);
//start changes by alona 24.12.16
                                    if (user_role.equals ("regular"))
                                        {
                                        Intent intent = new Intent(LoginActivity.this, MainAppScreenActivity.class);
                                        LoginActivity.this.startActivity(intent);
                                        }
                                    else if (user_role.equals("admin"))
                                       {
                                        Intent intent = new Intent(LoginActivity.this, AdministratorMainScreenActivity.class);
                                        LoginActivity.this.startActivity(intent);
                                       }
//end changes by alona 24.12.16
                                } else if(!TextUtils.isEmpty(password) && !TextUtils.isEmpty(email)){
                                    userValidation.alertDialog(LoginActivity.this,"Wrong user name or password", "Retry");
                                    progressDialog.dismiss();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    LoginRequest loginRequest = new LoginRequest(email, password, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                    queue.add(loginRequest);
                }
                break;//end login

            case R.id.bRegister://Register
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
              break;//end Register
        }
    }


}


