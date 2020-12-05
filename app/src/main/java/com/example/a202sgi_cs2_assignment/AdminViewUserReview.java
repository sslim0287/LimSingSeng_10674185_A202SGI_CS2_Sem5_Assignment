package com.example.a202sgi_cs2_assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminViewUserReview extends AppCompatActivity {

    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_user_review);

        final TextView tvUser = (TextView)findViewById(R.id.tvReviewUser1);
        final TextView tvStudentID = (TextView)findViewById(R.id.tvReviewStudentID1);
        final TextView tvRatingStar = (TextView)findViewById(R.id.tvRatingStar1);
        final TextView tvComment = (TextView)findViewById(R.id.tvUserComment1);

        reference = FirebaseDatabase.getInstance().getReference("Login").child("Users").child("oQwzHVH2CXTg5AqyopJHH2Z9Oxt2");
        reference.child("Rating 1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Rating rate  = snapshot.getValue(Rating.class);
                User user = snapshot.getValue(User.class);

                if(user != null){
                    if(rate != null){
                        String username1 = rate.username;
                        String studentID1 = rate.studentID;
                        String rating = rate.rating;
                        String comment = rate.comment;

                        tvUser.setText("User Name : " + username1);
                        tvStudentID.setText("Student ID : " + studentID1);
                        tvRatingStar.setText("Rating Star : " + rating);
                        tvComment.setText("Comment : " + comment);

                    }

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}