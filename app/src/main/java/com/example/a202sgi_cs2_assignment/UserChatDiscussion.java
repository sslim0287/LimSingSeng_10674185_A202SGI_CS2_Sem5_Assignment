package com.example.a202sgi_cs2_assignment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UserChatDiscussion extends AppCompatActivity {

    Button btnSendMessage;
    EditText etMessage;
    ListView lvDiscuss;
    ArrayList<String> listMessage = new ArrayList<String>();
    ArrayAdapter mArrayAdapter;

    String UserName, SelectedTopic, user_msg_key;

    private FirebaseUser user;
    private String userID;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_chat_discussion);

        btnSendMessage = (Button)findViewById(R.id.btnSendMessages);
        etMessage = (EditText)findViewById(R.id.etMessage);

        lvDiscuss = (ListView)findViewById(R.id.lvMessage);
        mArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listMessage);
        lvDiscuss.setAdapter(mArrayAdapter);

        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
        reference = FirebaseDatabase.getInstance().getReference("Chat").child("INTI Library Staff");

        UserName = getIntent().getExtras().get("user_name").toString();
        SelectedTopic = getIntent().getExtras().get("selected_topic").toString();
        setTitle("Topic : " + SelectedTopic);


        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> map = new HashMap<String, Object>();
                user_msg_key = reference.push().getKey();
                reference.updateChildren(map);

                DatabaseReference reference2 = reference.child(user_msg_key);
                Map<String,Object> map2 = new HashMap<String, Object>();
                map2.put("msg", etMessage.getText().toString());
                map2.put("user",UserName);
                reference2.updateChildren(map2);

            }
        });

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                updateMessage(dataSnapshot);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                updateMessage(dataSnapshot);

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void updateMessage(DataSnapshot dataSnapshot) {
        String message, user,conversation;
        Iterator i = dataSnapshot.getChildren().iterator();
        while(i.hasNext()){
            message =(String) ((DataSnapshot)i.next()).getValue();
            user = (String)((DataSnapshot)i.next()).getValue();

            conversation = user + ": " + message;
            mArrayAdapter.insert(conversation,mArrayAdapter.getCount());
            mArrayAdapter.notifyDataSetChanged();
        }
    }
}