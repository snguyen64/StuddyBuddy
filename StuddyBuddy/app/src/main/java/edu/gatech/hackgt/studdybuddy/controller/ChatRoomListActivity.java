package edu.gatech.hackgt.studdybuddy.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.gatech.hackgt.studdybuddy.R;

public class ChatRoomListActivity extends AppCompatActivity {
    private Button chatroomCreate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room_list);
        chatroomCreate = (Button) findViewById(R.id.createChatRoom);
    }

    public void createRoom(View view) {
        Intent intent = new Intent(this, ChatRoomCreateActivity.class);
        startActivity(intent);
    }
}
