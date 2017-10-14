package edu.gatech.hackgt.studdybuddy.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.hackgt.studdybuddy.R;
import edu.gatech.hackgt.studdybuddy.model.APIMessage;
import edu.gatech.hackgt.studdybuddy.model.Chatroom;
import edu.gatech.hackgt.studdybuddy.model.ChatroomAdapter;
import edu.gatech.hackgt.studdybuddy.model.Course;
import edu.gatech.hackgt.studdybuddy.util.APIClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatRoomListActivity extends AppCompatActivity {
    private Button chatroomCreate;
    private RecyclerView chatroomView;
    private LinearLayoutManager chatroomLayoutManager;
    private ChatroomAdapter chatroomAdapter;
    private Spinner chatroomCourseSelector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room_list);
        chatroomCreate = (Button) findViewById(R.id.createChatRoom);

        chatroomView = (RecyclerView) findViewById(R.id.onlineChatroom);
        chatroomLayoutManager = new LinearLayoutManager(this);
        chatroomAdapter = new ChatroomAdapter(new ArrayList<Chatroom>());

        chatroomView.setAdapter(chatroomAdapter);
        chatroomView.setLayoutManager(chatroomLayoutManager);

        chatroomCourseSelector = (Spinner) findViewById(R.id.chatroomCourseSelector);
        APIClient.getInstance().getCourses(MainActivity.userId).enqueue(new Callback<List<Course>>() {
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                if (response.isSuccessful()) {
                    ArrayAdapter<Course> courseArrayAdapter = new ArrayAdapter<>(ChatRoomListActivity.this,
                            android.R.layout.simple_spinner_item, response.body());
                    chatroomCourseSelector.setAdapter(courseArrayAdapter);
                } else {
                    Toast.makeText(ChatRoomListActivity.this, "Unable to get your courses.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {
                Toast.makeText(ChatRoomListActivity.this, "Unable to get your courses.", Toast.LENGTH_SHORT).show();
            }
        });

        chatroomCourseSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Course course = (Course) adapterView.getItemAtPosition(i);
                APIClient.getInstance().getListofChatroomsByCourse(course).enqueue(new Callback<List<Chatroom>>() {
                    @Override
                    public void onResponse(Call<List<Chatroom>> call, Response<List<Chatroom>> response) {
                        if (response.isSuccessful()) {
                            chatroomAdapter.setChatrooms(response.body());
                            chatroomAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(ChatRoomListActivity.this, "Unable to fetch chatroomsa.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Chatroom>> call, Throwable t) {
                        Log.e("ChatRoomListActivity", t.getMessage());
                        Toast.makeText(ChatRoomListActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void createRoom(View view) {
        Intent intent = new Intent(this, ChatRoomCreateActivity.class);
        startActivity(intent);
    }
}
