package com.example.a202sgi_cs2_assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UserBooking extends AppCompatActivity implements View.OnClickListener{

    private EditText date;
    private Button btnChooseTime1, btnChooseTime2, btnChooseTime3, btnChooseTime4, btnChooseTime5;
    private Button btnChooseRoom1, btnChooseRoom2, btnChooseRoom3, btnChooseRoom4, btnChooseRoom5;
    private TextView chosenTime, chosenRoom;
    private Button btnBook;
    long number = 0;

    Booking booking;
    DatabaseReference reff;

    String username1,studentID1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_booking);

        date = (EditText)findViewById(R.id.etDate);
        chosenTime = (TextView)findViewById(R.id.tvBookTime);
        chosenRoom = (TextView)findViewById(R.id.tvBookRoom);

        btnChooseTime1 = (Button)findViewById(R.id.btnTime1);
        btnChooseTime1.setOnClickListener(this);
        btnChooseTime2 = (Button)findViewById(R.id.btnTime2);
        btnChooseTime2.setOnClickListener(this);
        btnChooseTime3 = (Button)findViewById(R.id.btnTime3);
        btnChooseTime3.setOnClickListener(this);
        btnChooseTime4 = (Button)findViewById(R.id.btnTime4);
        btnChooseTime4.setOnClickListener(this);
        btnChooseTime5 = (Button)findViewById(R.id.btnTime5);
        btnChooseTime5.setOnClickListener(this);

        btnChooseRoom1 = (Button)findViewById(R.id.btnRoom1);
        btnChooseRoom1.setOnClickListener(this);
        btnChooseRoom2 = (Button)findViewById(R.id.btnRoom2);
        btnChooseRoom2.setOnClickListener(this);
        btnChooseRoom3 = (Button)findViewById(R.id.btnRoom3);
        btnChooseRoom3.setOnClickListener(this);
        btnChooseRoom4 = (Button)findViewById(R.id.btnRoom4);
        btnChooseRoom4.setOnClickListener(this);
        btnChooseRoom5 = (Button)findViewById(R.id.btnRoom5);
        btnChooseRoom5.setOnClickListener(this);

        btnBook = (Button)findViewById(R.id.btnBookNow);

        booking = new Booking();
        User user = new User();


        reff = FirebaseDatabase.getInstance().getReference("Login").child("Users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());



        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    number = number +1 ;
                }

                User userProfile = snapshot.getValue(User.class);

                if(userProfile != null) {
                    String fullName = userProfile.fullName;
                    String studentID = userProfile.studentID;
                    username1 = fullName;
                    studentID1 = studentID;


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //Empty
            }
        });

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                booking.setUsername(username1);
                booking.setStudentID(studentID1);
                booking.setDate(date.getText().toString());
                booking.setTime(chosenTime.getText().toString());
                booking.setRoom(chosenRoom.getText().toString());

                reff.child("Booking " + number).setValue(booking);

                startActivity(new Intent(UserBooking.this,UserMainPage.class));
                Toast.makeText(UserBooking.this, "Booking Created Successfully!! ", Toast.LENGTH_SHORT).show();

            }
        });


        date.setInputType(InputType.TYPE_NULL);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(date);
            }
        });




    }

    private void showDateDialog(final EditText date) {
        final Calendar calendar=Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yy");
                date.setText(simpleDateFormat.format(calendar.getTime()));

            }
        };

        new DatePickerDialog(this,dateSetListener,calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnTime1:
                chosenTime.setText("8:00 - 10:00 AM");
                break;

            case R.id.btnTime2:
                chosenTime.setText("10:00 - 12:00 PM");
                break;

            case R.id.btnTime3:
                chosenTime.setText("12:00 - 2:00 PM");
                break;

            case R.id.btnTime4:
                chosenTime.setText("2:00 - 4:00 PM");
                break;

            case R.id.btnTime5:
                chosenTime.setText("4:00 - 6:00 PM");
                break;

            case R.id.btnRoom1:
                chosenRoom.setText("Discussion Room 1");
                break;

            case R.id.btnRoom2:
                chosenRoom.setText("Discussion Room 2");
                break;

            case R.id.btnRoom3:
                chosenRoom.setText("Discussion Room 3");
                break;

            case R.id.btnRoom4:
                chosenRoom.setText("Discussion Room 4");
                break;

            case R.id.btnRoom5:
                chosenRoom.setText("Discussion Room 5");
                break;
        }
    }
}
