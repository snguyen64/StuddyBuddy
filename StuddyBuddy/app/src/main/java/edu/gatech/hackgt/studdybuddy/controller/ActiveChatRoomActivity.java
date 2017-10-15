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
    private WebSocket ws;

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

        OkHttpClient client = new OkHttpClient();
        Request req = new Request.Builder().url("ws://10.0.2.2:8888").build();
        ChatSocket cs = new ChatSocket();
        ws = client.newWebSocket(req, cs);

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
            return;
        } else {
            ChatMessage cm = new ChatMessage(mess);
            chatAdapter.add(cm);
            chatAdapter.notifyItemInserted(chatAdapter.getMessages().size() - 1);
            messages.scrollToPosition(chatAdapter.getItemCount()-1);
            textMessage.setText("");
<<<<<<< HEAD
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
=======
            ws.send(new Gson().toJson(cm));
        }
    }

    private final class ChatSocket extends WebSocketListener {
        @Override
        public void onMessage(WebSocket webSocket, String text) {
            ChatMessage cm = new Gson().fromJson(text, ChatMessage.class);
            chatAdapter.getMessages().add(cm);
            chatAdapter.notifyItemInserted(chatAdapter.getMessages().size() - 1);
            messages.scrollToPosition(chatAdapter.getItemCount() - 1);
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, okhttp3.Response response) {
            super.onFailure(webSocket, t, response);
            Toast.makeText(ActiveChatRoomActivity.this, "Unable to communicate with server.", Toast.LENGTH_LONG).show();
>>>>>>> ee19375adec6c2cfcbedc255484f6b38839105b1
        }
    }
}
