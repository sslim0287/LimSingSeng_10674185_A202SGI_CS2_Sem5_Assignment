package com.example.a202sgi_cs2_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserReview extends AppCompatActivity {

    private RatingBar rating;
    private EditText comment;
    private Button btnSubmit;

    Rating rate;
    DatabaseReference reference;
    String username1,studentID1;
    long maxID = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_review);

        rating = (RatingBar)findViewById(R.id.ratingBar);
        comment = (EditText)findViewById(R.id.commentReview);
        btnSubmit = (Button)findViewById(R.id.btnSubmitRating);

        rate = new Rating();
        reference = FirebaseDatabase.getInstance().getReference("Login").child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    maxID = maxID + 1;
                }

                User userProfile = dataSnapshot.getValue(User.class);

                if(userProfile != null) {
                    String fullName = userProfile.fullName;
                    String studentID = userProfile.studentID;
                    username1 = fullName;
                    studentID1 = studentID;


                }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rate.setUsername(username1);
                rate.setStudentID(studentID1);
                rate.setRating(String.valueOf(rating.getRating()));
                rate.setComment(comment.getText().toString());


                reference.child("Rating " + maxID).setValue(rate);

                startActivity(new Intent(UserReview.this,UserMainPage.class));
                Toast.makeText(UserReview.this, "Review Created Successfully", Toast.LENGTH_SHORT).show();
            }
        });

    }
}