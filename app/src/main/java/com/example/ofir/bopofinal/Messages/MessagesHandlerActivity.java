package com.example.ofir.bopofinal.Messages;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.ofir.bopofinal.LoginRegister.LoggedInUserService;
import com.example.ofir.bopofinal.MainAppScreenActivity;
import com.example.ofir.bopofinal.R;


public class MessagesHandlerActivity extends AppCompatActivity implements View.OnClickListener{

    private static Intent m_intent;
    private String m_userID;
    TextView noMessages;
    Button ExitMessages;
    RecyclerView MessagesRecyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages_handler);
        m_userID = LoggedInUserService.getInstance().getM_id() + "";
        getSupportActionBar().setTitle("My messages");

        MessagesRecyclerView = (RecyclerView) findViewById(R.id.MessagesRecyclerView);
        noMessages = (TextView)findViewById(R.id.tvNoMessages);
        ExitMessages = (Button) findViewById(R.id.btnExitMessages);

        if (MainAppScreenActivity.noMessagesFlag == true)
        {
            MessagesRecyclerView.setVisibility(View.INVISIBLE);
            noMessages.setVisibility(View.VISIBLE);
        }
        else if (MainAppScreenActivity.noMessagesFlag == false)
        {
            MessagesRecyclerView.setVisibility(View.VISIBLE);
            noMessages.setVisibility(View.INVISIBLE);

            layoutManager = new LinearLayoutManager(this);
            MessagesRecyclerView.setLayoutManager(layoutManager);
            MessagesRecyclerView.setHasFixedSize(true);

            adapter = new MessagesRecyclerAdapter(MessageResultService.getInstance().getArray(),this);
            MessageResultService.getInstance().reset();
            MessagesRecyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onClick(View view) {
        m_intent = new Intent(MessagesHandlerActivity.this, MainAppScreenActivity.class);
        MessagesHandlerActivity.this.startActivity(m_intent);
    }
}
