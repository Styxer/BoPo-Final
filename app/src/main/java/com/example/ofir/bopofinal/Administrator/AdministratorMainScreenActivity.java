package com.example.ofir.bopofinal.Administrator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.ofir.bopofinal.Events.ShowMyEventsActivity;
import com.example.ofir.bopofinal.LoginRegister.LoggedInUserService;
import com.example.ofir.bopofinal.LoginRegister.LoginActivity;
import com.example.ofir.bopofinal.MainAppScreenActivity;
import com.example.ofir.bopofinal.R;
import com.example.ofir.bopofinal.Search.SearchActivity;

public class AdministratorMainScreenActivity extends AppCompatActivity implements View.OnClickListener {

    private static ImageButton m_ibManageCategories;
    private static ImageButton m_ibLogout;
    private static Intent m_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator_main_screen);

        m_ibManageCategories = (ImageButton) findViewById(R.id.ibManageCategories);
        m_ibLogout = (ImageButton) findViewById(R.id.ibLogout);

        getSupportActionBar().setTitle("Welcome administrator!");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.ibManageCategories:
                m_intent = new Intent(AdministratorMainScreenActivity.this, ManageCategoriesActivity.class);
                AdministratorMainScreenActivity.this.startActivity(m_intent);
                break;
            case R.id.ibLogout:
                Toast.makeText(AdministratorMainScreenActivity.this, "bye bye" +" "+ LoggedInUserService.getInstance().getM_name(),
                        Toast.LENGTH_SHORT).show();
                LogOutUser();
                m_intent = new Intent(AdministratorMainScreenActivity.this, LoginActivity.class);
                AdministratorMainScreenActivity.this.startActivity(m_intent);
                break;

        }
    }

    public void LogOutUser()
    {
        LoggedInUserService.getInstance().reset();
    }
}
