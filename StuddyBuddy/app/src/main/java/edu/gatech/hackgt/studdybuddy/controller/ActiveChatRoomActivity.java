package edu.gatech.hackgt.studdybuddy.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import edu.gatech.hackgt.studdybuddy.R;
import edu.gatech.hackgt.studdybuddy.model.ChatAdapter;
import edu.gatech.hackgt.studdybuddy.model.ChatMessage;
import edu.gatech.hackgt.studdybuddy.model.Chatroom;
import edu.gatech.hackgt.studdybuddy.model.Course;
import edu.gatech.hackgt.studdybuddy.util.APIClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActiveChatRoomActivity extends AppCompatActivity {
    private Chatroom thisRoom;
    private TextView chatInfo;
    private TextView chatroomCourse;
    private RecyclerView messages;
    private ChatAdapter chatAdapter;
    private EditText textMessage;
    private LinearLayoutManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_chat_room);
        thisRoom = (Chatroom) getIntent().getSerializableExtra("room");
        chatInfo = (TextView) findViewById(R.id.chatroomInfo);
        chatInfo.setText(thisRoom.getName());
        chatroomCourse = (TextView) findViewById(R.id.chatroomCourse);
        chatroomCourse.setText(thisRoom.getCourse().toString());
        textMessage = (EditText) findViewById(R.id.textMessage);
        messages = (RecyclerView) findViewById(R.id.messagesView);
        chatAdapter = new ChatAdapter(new ArrayList<ChatMessage>());
        messages.setAdapter(chatAdapter);
        manager = new LinearLayoutManager(this);
        manager.setReverseLayout(false);
        manager.setStackFromEnd(true);
        messages.setLayoutManager(manager);
    }

    public void goBack(View view) {
        Intent intent = new Intent(this, ChatRoomListActivity.class);
        startActivity(intent);
    }

    public void sendMessage(View view) {
        String mess = textMessage.getText().toString();
        if (TextUtils.isEmpty(mess) || mess == null) {
            return;
        } else {
            chatAdapter.add(new ChatMessage(mess));
            chatAdapter.setMessages(chatAdapter.getMessages());
            chatAdapter.notifyDataSetChanged();
            messages.scrollToPosition(chatAdapter.getItemCount()-1);
            textMessage.setText("");
            //textMessage.setText("");
            //change.getCourses to getMessages
//            APIClient.getInstance().getCourses(ActiveChatRoomActivity.textMessage).enqueue(new Callback<List<ChatMessage>>() {
//                @Override
//                public void onResponse(Call<List<ChatMessage>> call, Response<List<ChatMessage>> response) {
//                    if (!response.isSuccessful()) {
//                        return;
//                    } else {
//                        chatAdapter.setMessages(response.body());
//                        chatAdapter.notifyDataSetChanged();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<List<ChatMessage>> call, Throwable t) {
//                    return;
//                }
//            });
//
        }
        //android.gravity = right will set right align
        //you add this message to the recycler view
        //then call scrollToBottom
    }
}
