package com.example.ofir.bopofinal.Messages;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.ofir.bopofinal.R;
import android.view.View;
import android.widget.TextView;

public class SingleMessageActivity extends AppCompatActivity implements View.OnClickListener{

    Bundle bundle;
    String description,title;
    TextView titl, desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_message);
        getSupportActionBar().setTitle("Message");

        desc = (TextView) findViewById(R.id.tvDescription);
        titl = (TextView) findViewById(R.id.tvTitle);
        bundle = getIntent().getExtras();
        description = bundle.getString("description");
        title = bundle.getString("title");
        desc.setText(description);
        titl.setText(title);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnOK:
                new ShowMessages(this);
            break;
        }
    }
}
