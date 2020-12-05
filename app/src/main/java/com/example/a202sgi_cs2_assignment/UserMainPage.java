package com.example.a202sgi_cs2_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserMainPage extends AppCompatActivity implements View.OnClickListener{

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    private ImageButton ibBooking,ibChat,ibView,ibSetting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main_page);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Login").child("Users");
        userID = user.getUid();

        final TextView tvGreeting = (TextView)findViewById(R.id.tvWelcome);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User userProfile = dataSnapshot.getValue(User.class);

                if(userProfile != null){
                    String fullName = userProfile.fullName;

                    tvGreeting.setText("Welcome, " + fullName);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(UserMainPage.this, "Something wrong happened!", Toast.LENGTH_SHORT).show();
            }
        });


        ibBooking = (ImageButton)findViewById(R.id.ibBooking);
        ibBooking.setOnClickListener(this);

        ibChat = (ImageButton)findViewById(R.id.ibChat);
        ibChat.setOnClickListener(this);

        ibView = (ImageButton)findViewById(R.id.ibView);
        ibView.setOnClickListener(this);

        ibSetting = (ImageButton)findViewById(R.id.ibSetting);
        ibSetting.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.ibBooking:
                startActivity(new Intent(this, UserBooking.class));
                break;

            case R.id.ibChat:
                startActivity(new Intent(this, UserChat.class));
                break;

            case R.id.ibView:
                startActivity(new Intent(this, UserViewHistory.class));
                break;

            case R.id.ibSetting:
                startActivity(new Intent(this,UserSetting.class));
                break;


        }
    }
}