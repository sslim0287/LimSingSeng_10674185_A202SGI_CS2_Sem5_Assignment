package com.example.a202sgi_cs2_assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminViewUserBooking extends AppCompatActivity {

    private DatabaseReference reference;
    private Button btnDeleteBooking1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_user_booking);



        final TextView tvName = (TextView)findViewById(R.id.tvBookingUser1);
        final TextView tvStudentID = (TextView)findViewById(R.id.tvBookingStudentID1);
        final TextView tvDate = (TextView)findViewById(R.id.tvBookingDate1);
        final TextView tvTime = (TextView)findViewById(R.id.tvBookingTime1);
        final TextView tvRoom = (TextView)findViewById(R.id.tvBookingRoom1);

        reference = FirebaseDatabase.getInstance().getReference("Login").child("Users").child("oQwzHVH2CXTg5AqyopJHH2Z9Oxt2");
        reference.child("Booking 1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Booking booking  = snapshot.getValue(Booking.class);
                User user = snapshot.getValue(User.class);

                if(user != null){
                    if(booking != null){
                        String username1 = booking.username;
                        String studentID1 = booking.studentID;
                        String date = booking.date;
                        String time = booking.time;
                        String room = booking.room;

                        tvName.setText("User Name : " + username1);
                        tvStudentID.setText("Student ID : " + studentID1);
                        tvDate.setText("Date : " + date);
                        tvTime.setText("Time : " + time);
                        tvRoom.setText("Room : " + room);

                    }

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnDeleteBooking1 = (Button)findViewById(R.id.btnDelete1);

        btnDeleteBooking1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Login").child("Users").child("oQwzHVH2CXTg5AqyopJHH2Z9Oxt2").child("Booking 1");
                reference.removeValue();

                tvName.setText("User Name : ");
                tvStudentID.setText("Student ID : ");
                tvDate.setText("Date : ");
                tvTime.setText("Time : ");
                tvRoom.setText("Room : ");


                Toast.makeText(AdminViewUserBooking.this, "Booking 1 has been deleted !", Toast.LENGTH_SHORT).show();
            }
        });

    }
}