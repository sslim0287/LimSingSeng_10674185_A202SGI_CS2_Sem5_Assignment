package com.example.a202sgi_cs2_assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserViewHistory extends AppCompatActivity {

    private DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_history);

        final TextView tvDate = (TextView)findViewById(R.id.bookingDate);
        final TextView tvTime = (TextView)findViewById(R.id.bookingTime);
        final TextView tvRoom = (TextView)findViewById(R.id.bookingRoom);

        reff = FirebaseDatabase.getInstance().getReference("Login").child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        reff.child("Booking 1").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Booking booking = dataSnapshot.getValue(Booking.class);

                if(booking != null){
                    String date = booking.date;
                    String time = booking.time;
                    String room = booking.room;


                    tvDate.setText("Date: " + date);
                    tvTime.setText("Time: " + time );
                    tvRoom.setText("Room: " + room);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(UserViewHistory.this, "Error Occured!!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}