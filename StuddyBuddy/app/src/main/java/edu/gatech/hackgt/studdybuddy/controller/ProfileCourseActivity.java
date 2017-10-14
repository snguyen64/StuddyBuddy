package edu.gatech.hackgt.studdybuddy.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.gatech.hackgt.studdybuddy.R;

public class ProfileCourseActivity extends AppCompatActivity {
    private Button findBuddies;
    private Button addCourse;
    private Button removeCourse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_course);
        findBuddies = (Button) findViewById(R.id.findBuddiesButton);
        addCourse = (Button) findViewById(R.id.addCourse);
        removeCourse = (Button) findViewById(R.id.removeCourse);
    }
    
    public void findBuddies(View view) {
        Intent intent = new Intent(this, ChatRoomListActivity.class);
        startActivity(intent);
    }

    public void addCourse(View view) {
        Intent intent = new Intent(this, CourseSelectionActivity.class);
        startActivity(intent);
    }

    public void removeCourse(View view) {
        Intent intent = new Intent(this, CourseSelectionActivity.class);
        startActivity(intent);
    }

}
