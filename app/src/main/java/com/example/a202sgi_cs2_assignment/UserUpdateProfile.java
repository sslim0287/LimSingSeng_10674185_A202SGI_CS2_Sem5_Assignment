package com.example.a202sgi_cs2_assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserUpdateProfile extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    private Button btnUpdateProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_update_profile);

        btnUpdateProfile = (Button)findViewById(R.id.btnUpdate);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Login").child("Users");
        userID = user.getUid();

        final EditText etName = (EditText)findViewById(R.id.etFullName);
        final EditText etPHNum = (EditText)findViewById(R.id.etPhNum);
        final EditText etStudentID = (EditText)findViewById(R.id.etStudentID);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User userUpdate = dataSnapshot.getValue(User.class);

                if(userUpdate != null){
                    String fullname = userUpdate.fullName;
                    String phNum = userUpdate.phoneNum;
                    String studentID = userUpdate.studentID;

                    etName.setText(fullname);
                    etPHNum.setText(phNum);
                    etStudentID.setText(studentID);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(UserUpdateProfile.this, "Something wrong happened!", Toast.LENGTH_SHORT).show();

            }
        });

        btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.child(userID).child("fullName").setValue(etName.getText().toString());
                reference.child(userID).child("phoneNum").setValue(etPHNum.getText().toString());
                reference.child(userID).child("studentID").setValue(etStudentID.getText().toString());

                startActivity(new Intent(UserUpdateProfile.this,UserMainPage.class));
                Toast.makeText(UserUpdateProfile.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}