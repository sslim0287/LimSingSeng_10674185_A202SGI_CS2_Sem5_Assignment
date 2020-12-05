package com.example.a202sgi_cs2_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class UserSetting extends AppCompatActivity implements View.OnClickListener{

    private Button logout;
    private Button btnUpdateProfile;
    private Button btnRoute;
    private Button btnReview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);

        btnUpdateProfile = (Button)findViewById(R.id.btnUpdateProfile);
        btnUpdateProfile.setOnClickListener(this);

        btnRoute = (Button)findViewById(R.id.btnGoogleMap);
        btnRoute.setOnClickListener(this);

        btnReview = (Button)findViewById(R.id.btnINTILibraryReview);
        btnReview.setOnClickListener(this);

        logout = (Button)findViewById(R.id.btnLogout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(UserSetting.this,MainActivity.class));
                Toast.makeText(UserSetting.this, "Successfully Logout !", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnUpdateProfile:
                startActivity(new Intent(this, UserUpdateProfile.class));
                break;

            case R.id.btnGoogleMap:
                startActivity(new Intent(this,UserGoogleMapRouteInti.class));
                break;

            case R.id.btnINTILibraryReview:
                startActivity(new Intent(this,UserReview.class));
                break;

        }
    }
    }
