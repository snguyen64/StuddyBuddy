package edu.gatech.hackgt.studdybuddy.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import edu.gatech.hackgt.studdybuddy.R;
import edu.gatech.hackgt.studdybuddy.model.Chatroom;

public class ActiveChatRoomActivity extends AppCompatActivity {
    private Chatroom thisRoom;
    private TextView chatInfo;
    private TextView chatroomCourse;
    private RecyclerView chatroomList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_chat_room);
        thisRoom = (Chatroom) getIntent().getSerializableExtra("room");
        chatInfo = (TextView) findViewById(R.id.chatroomInfo);
        chatInfo.setText(thisRoom.getName());
        chatroomCourse = (TextView) findViewById(R.id.chatroomCourse);
        chatroomCourse.setText(thisRoom.getCourse().toString());

    }
}
