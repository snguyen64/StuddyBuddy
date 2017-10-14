package edu.gatech.hackgt.studdybuddy.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import edu.gatech.hackgt.studdybuddy.R;
import edu.gatech.hackgt.studdybuddy.model.CourseType;
import edu.gatech.hackgt.studdybuddy.util.APIClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatRoomCreateActivity extends AppCompatActivity {

    private EditText chatroomName;
    private Spinner courseType;
    private Spinner course;
    private Button submit;
    private Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room_create);
        chatroomName = (EditText) findViewById(R.id.chatroomName);
        courseType = (Spinner) findViewById(R.id.courseType);
        ArrayAdapter<CourseType> courseTypeArrayAdapter = new ArrayAdapter<CourseType>(this,
                android.R.layout.simple_spinner_item, CourseType.values());
        courseType.setAdapter(courseTypeArrayAdapter);
        course = (Spinner) findViewById(R.id.course);
        courseType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CourseType ct = (CourseType) adapterView.getItemAtPosition(i);
                String coty = String.valueOf(ct).toLowerCase();
                APIClient.getInstance().getCoursesByType(coty).enqueue(new Callback<List<Integer>>() {
                    @Override
                    public void onResponse(Call<List<Integer>> call, Response<List<Integer>> response) {
                        ArrayAdapter<Integer> courseNumberArrayAdapter = new ArrayAdapter<Integer>(ChatRoomCreateActivity.this,
                                android.R.layout.simple_spinner_item, response.body());
                        course.setAdapter(courseNumberArrayAdapter);
                    }

                    @Override
                    public void onFailure(Call<List<Integer>> call, Throwable t) {
                        Toast.makeText(ChatRoomCreateActivity.this,
                                "Unable to get course numbers.", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        submit = (Button) findViewById(R.id.submit);
        cancel = (Button) findViewById(R.id.cancel);
    }

    public void submit(View view) {

    }
    public void cancel(View view) {
        Intent intent = new Intent(this, ChatRoomListActivity.class);
        startActivity(intent);
    }
}
