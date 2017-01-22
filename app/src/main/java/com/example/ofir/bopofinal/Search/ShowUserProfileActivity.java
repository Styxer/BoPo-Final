package com.example.ofir.bopofinal.Search;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.ofir.bopofinal.Profile.DownloadImageFromDB;
import com.example.ofir.bopofinal.R;


public class ShowUserProfileActivity extends AppCompatActivity implements View.OnClickListener{

        private String m_userID, m_name, m_email, m_birthday, m_phone, m_address, m_image;
        Bundle bundle;
        private Intent intent;
        Button btnCallUser;
        TextView etName, etEmail, etBirthday, etPhone, etAddress;
        ImageView ivImage;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_show_user_profile);
            bundle = getIntent().getExtras();
            m_name = bundle.getString("name");
            getSupportActionBar().setTitle(m_name + "'s profile");
            m_email = bundle.getString("email");
            m_birthday = bundle.getString("birthday");
            m_phone = bundle.getString("phone");
            m_address = bundle.getString("address");
            m_image = bundle.getString("photo_name");
            m_userID = bundle.getString("user_id");


            ivImage = (ImageView) findViewById(R.id.ivSearchedUserPhoto);
            if(m_image.equals("profilePictureOfUserId" + m_userID + ".JPG"))
                new DownloadImageFromDB(ivImage).execute("http://tower.site88.net/pictures/profilePictureOfUserId" + m_userID + ".JPG");
            else if(m_image.equals("DefaultPicture.JPG"))
                new DownloadImageFromDB(ivImage).execute("http://tower.site88.net/pictures/DefaultPicture.JPG");

            btnCallUser = (Button) findViewById(R.id.btnCallUser);
            if (!m_phone.equals(""))
                btnCallUser.setVisibility(View.VISIBLE);
            btnCallUser.setText("call " + m_name);
            etName = (EditText) findViewById(R.id.etSearchedUserName);
            etEmail = (EditText) findViewById(R.id.etSearchedUserEmail);
            etBirthday = (EditText) findViewById(R.id.etSearchedUserBirthday);
            etPhone = (EditText) findViewById(R.id.etSearchedUserPhone);
            etAddress = (EditText) findViewById(R.id.etSearchedUserAddress);


            etName.setText(m_name);
            etEmail.setText(m_email);
            etBirthday.setText(m_birthday);
            etPhone.setText(m_phone);
            etAddress.setText(m_address);

            etName.setFocusable(false);
            etEmail.setFocusable(false);
            etBirthday.setFocusable(false);
            etPhone.setFocusable(false);
            etAddress.setFocusable(false);
        }


        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnCallUser:
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + m_phone));


                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ShowUserProfileActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            1);

                }
                startActivity(intent);
                break;
            }
        }
}


