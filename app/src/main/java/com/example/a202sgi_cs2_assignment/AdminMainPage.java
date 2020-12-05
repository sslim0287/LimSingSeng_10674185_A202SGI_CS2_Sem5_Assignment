package com.example.a202sgi_cs2_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class AdminMainPage extends AppCompatActivity implements View.OnClickListener{

    private ImageButton ibViewUserBooking,ibChatAdmin,ibReview,ibLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main_page);

        ibViewUserBooking = (ImageButton)findViewById(R.id.ibViewUserBooking);
        ibViewUserBooking.setOnClickListener(this);

        ibChatAdmin = (ImageButton)findViewById(R.id.ibChatAdmin);
        ibChatAdmin.setOnClickListener(this);

        ibReview = (ImageButton)findViewById(R.id.ibReview);
        ibReview.setOnClickListener(this);

        ibLogout = (ImageButton)findViewById(R.id.ibLogoutAdmin);
        ibLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(AdminMainPage.this,MainActivity.class));
                Toast.makeText(AdminMainPage.this, "Successfully Logout !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.ibViewUserBooking:
                startActivity(new Intent(this, AdminViewUserBooking.class));
                break;

            case R.id.ibChatAdmin:
                startActivity(new Intent(this, UserChat.class));
                break;

            case R.id.ibReview:
                startActivity(new Intent(this, AdminViewUserReview.class));
                break;


        }
    }
}
