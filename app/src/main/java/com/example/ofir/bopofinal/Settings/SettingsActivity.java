package com.example.ofir.bopofinal.Settings;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.ofir.bopofinal.LoginRegister.LoggedInUserService;
import com.example.ofir.bopofinal.LoginRegister.LoginActivity;
import com.example.ofir.bopofinal.R;

import org.json.JSONException;
import org.json.JSONObject;


public class SettingsActivity extends AppCompatActivity implements View.OnClickListener{

    private static Intent intent;
    private String m_userID;
    Button btnDeleteAccount, btnChangePassword;
    boolean deleteFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setTitle("Settings");

        m_userID = LoggedInUserService.getInstance().getM_id() + "";
        btnDeleteAccount =(Button)findViewById(R.id.btnDeleteAccount);
        btnChangePassword = (Button)findViewById(R.id.btnChangePassword);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnDeleteAccount:
                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
                dlgAlert.setMessage("are you sure you want to delete your account?").setCancelable(true);;
                dlgAlert.setTitle("Delete account");
                dlgAlert.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                deleteFlag = true;
                                dialog.dismiss();
                                MakeDelete();
                            }
                        });
                dlgAlert.setNegativeButton("no",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                deleteFlag = false;
                                dialog.cancel();
                            }
                        });
                dlgAlert.create().show();
                break;
            case R.id.btnChangePassword:
                intent = new Intent(SettingsActivity.this, ChangePasswordActivity.class);
                SettingsActivity.this.startActivity(intent);

                break;
        }
    }

    private void MakeDelete()
    {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        Toast.makeText(SettingsActivity.this, "account deleted",
                                Toast.LENGTH_LONG).show();
                        intent = new Intent(SettingsActivity.this, LoginActivity.class);
                        SettingsActivity.this.startActivity(intent);

                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
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

            DeleteAccountRequest deleteAccountRequest = new DeleteAccountRequest(m_userID, responseListener);
            RequestQueue queue = Volley.newRequestQueue(SettingsActivity.this);
            queue.add(deleteAccountRequest);
    }
}
