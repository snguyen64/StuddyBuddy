package edu.gatech.hackgt.studdybuddy.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.hackgt.studdybuddy.R;
import edu.gatech.hackgt.studdybuddy.model.Course;
import edu.gatech.hackgt.studdybuddy.model.CourseAdapter;
import edu.gatech.hackgt.studdybuddy.util.APIClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileCourseActivity extends AppCompatActivity {
    private Button findBuddies;
    private Button addCourse;
    private TextView userName;
    private RecyclerView enrolledCourses;
    private LinearLayoutManager enrolledLlm;
    private CourseAdapter enrolledAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_course);
        findBuddies = (Button) findViewById(R.id.findBuddiesButton);
        userName = (TextView) findViewById(R.id.user_name);
        userName.setText(getIntent().getStringExtra("username"));
        addCourse = (Button) findViewById(R.id.addCourse);
        enrolledCourses = (RecyclerView) findViewById(R.id.enrolledCourses);
        enrolledLlm = new LinearLayoutManager(this);
        enrolledAdapter = new CourseAdapter(new ArrayList<Course>());
        enrolledCourses.setAdapter(enrolledAdapter);
        enrolledCourses.setLayoutManager(enrolledLlm);
        APIClient.getInstance().getCourses(MainActivity.userId).enqueue(new Callback<List<Course>>() {
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                enrolledAdapter.setCourseList(response.body());
                enrolledAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {

            }
        });

    }

    public void findBuddies(View view) {
        Intent intent = new Intent(this, ChatRoomListActivity.class);
        startActivity(intent);
    }

    public void addCourse(View view) {
        Intent intent = new Intent(this, CourseSelectionActivity.class);
        startActivity(intent);
    }

}
