package com.example.ofir.bopofinal.Settings;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.ofir.bopofinal.LoginRegister.LoggedInUserService;
import com.example.ofir.bopofinal.R;

import org.json.JSONException;
import org.json.JSONObject;

public class ChangePasswordActivity extends AppCompatActivity implements View.OnClickListener{
    EditText etOldPass,etNewPass, etConfirmPass;
    String oldPass, newPass, confirmPass, m_userID, m_password;
    Button btnSaveChanges;
    private static Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        getSupportActionBar().setTitle("Change password");

        etOldPass = (EditText) findViewById(R.id.etOldPassword);
        etNewPass = (EditText) findViewById(R.id.etNewPassword);
        etConfirmPass = (EditText) findViewById(R.id.etConfirmPassword);

        btnSaveChanges = (Button)findViewById(R.id.btnSavePassword);

        m_userID = LoggedInUserService.getInstance().getM_id() + "";
        m_password = LoggedInUserService.getInstance().getM_password();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSavePassword:
                oldPass = etOldPass.getText().toString();
                newPass = etNewPass.getText().toString();
                confirmPass = etConfirmPass.getText().toString();

                if((oldPass.equals(m_password))&& (newPass.equals(confirmPass)))
                {
                    ChangePass();
                }
                else if(!(oldPass.equals(m_password)))
                {
                    AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
                    dlgAlert.setMessage("wrong old password").setCancelable(false);;
                    dlgAlert.setTitle("error");
                    dlgAlert.setPositiveButton("try again",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    etOldPass.setText("");
                                }
                            });
                    dlgAlert.create().show();
                }
                else if(!(newPass.equals(confirmPass)))
                {
                    AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
                    dlgAlert.setMessage("new password does not match confirm password").setCancelable(false);;
                    dlgAlert.setTitle("error");
                    dlgAlert.setPositiveButton("try again",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    etNewPass.setText("");
                                    etConfirmPass.setText("");
                                }
                            });
                    dlgAlert.create().show();
                }
        }
    }

    private void ChangePass()
    {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        LoggedInUserService.getInstance().setM_password(newPass);
                        Toast.makeText(ChangePasswordActivity.this, "password changed",
                                Toast.LENGTH_LONG).show();
                        intent = new Intent(ChangePasswordActivity.this, SettingsActivity.class);
                        ChangePasswordActivity.this.startActivity(intent);

                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ChangePasswordActivity.this);
                        builder.setMessage("change password failed")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest(m_userID, newPass, responseListener);
        RequestQueue queue = Volley.newRequestQueue(ChangePasswordActivity.this);
        queue.add(changePasswordRequest);
    }
}
