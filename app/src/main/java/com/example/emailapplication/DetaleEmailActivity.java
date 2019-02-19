package com.example.emailapplication;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Objects;


public class DetaleEmailActivity extends AppCompatActivity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detale_email);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                onBackPressed();
            }
        });

        EmailItem emailItem = getIntent().getParcelableExtra("email item");
        // Extract data
        String userImg = emailItem.getUserImg();
        String userName = emailItem.getTitle();
        String emailSubject = emailItem.getSubtitle();
        String emailCompose = emailItem.getContent();
        String emailDate = emailItem.getData();

        // Find needed views
        ImageView userImgIv = findViewById(R.id.iv_avatar);
        TextView userNameTv = findViewById(R.id.tv_second);
        TextView emailSubjectTv = findViewById(R.id.tv_email);
        TextView emailComposeTv = findViewById(R.id.tv_first);
        TextView emailDateTv = findViewById(R.id.tv_time);

        // Place data into views
        // Using Glide for load images
        if (userImg != null && !userImg.isEmpty()) {
            Glide.with(this)
                    .load(userImg)
                    .into(userImgIv);
        } else {
            String userImgDefault = "http://simpleicon.com/wp-content/uploads/user1.png";
            Glide.with(this)
                    .load(userImgDefault)
                    .into(userImgIv);
        }

        // load other data
        if (userName != null && !userName.isEmpty()) {
            userNameTv.setText(userName);
        } else {
            userNameTv.setText(R.string.user_name);
        }

        if (emailSubject != null && !emailSubject.isEmpty()) {
            emailSubjectTv.setText(emailSubject);
        } else {
            emailSubjectTv.setText(R.string.email_subject);
        }

        if (emailCompose != null && !emailCompose.isEmpty()) {
            emailComposeTv.setText(emailCompose);
        } else {
            emailComposeTv.setText(R.string.email_compose);
        }

        if (emailDate != null && !emailDate.isEmpty()) {
            emailDateTv.setText(emailDate);
        } else {
            emailDateTv.setText(R.string.email_date);
        }
    }



}
