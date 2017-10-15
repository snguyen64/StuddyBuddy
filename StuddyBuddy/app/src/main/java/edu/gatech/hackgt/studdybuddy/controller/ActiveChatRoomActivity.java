package edu.gatech.hackgt.studdybuddy.controller;

import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import edu.gatech.hackgt.studdybuddy.R;
import edu.gatech.hackgt.studdybuddy.model.ChatAdapter;
import edu.gatech.hackgt.studdybuddy.model.ChatMessage;
import edu.gatech.hackgt.studdybuddy.model.Chatroom;
import edu.gatech.hackgt.studdybuddy.model.Course;
import edu.gatech.hackgt.studdybuddy.util.APIClient;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
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

        APIClient.getInstance().getChatMessages(thisRoom.getName()).enqueue(new Callback<List<ChatMessage>>() {
            @Override
            public void onResponse(Call<List<ChatMessage>> call, Response<List<ChatMessage>> response) {
                chatAdapter.setMessages(response.body());
                chatAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ChatMessage>> call, Throwable t) {

            }
        });
    }

    public void goBack(View view) {
        Intent intent = new Intent(this, ChatRoomListActivity.class);
        startActivity(intent);
    }

    public void sendMessage(View view) {
        String mess = textMessage.getText().toString();
        if (TextUtils.isEmpty(mess)) {
        } else {
            ChatMessage cm = new ChatMessage(mess);
            chatAdapter.add(cm);
            chatAdapter.notifyItemInserted(chatAdapter.getMessages().size() - 1);
            messages.scrollToPosition(chatAdapter.getItemCount()-1);
            textMessage.setText("");
        }
    }
}
