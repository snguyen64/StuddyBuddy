package edu.gatech.hackgt.studdybuddy.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import edu.gatech.hackgt.studdybuddy.R;
import edu.gatech.hackgt.studdybuddy.model.CourseAdapter;

public class CourseSelectionActivity extends AppCompatActivity {
    private RecyclerView courseAddView;
    private LinearLayoutManager courseAddViewManager;
    private CourseAdapter courseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_selection);

    }
}
