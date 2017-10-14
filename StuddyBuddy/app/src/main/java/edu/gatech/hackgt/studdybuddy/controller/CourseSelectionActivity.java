package edu.gatech.hackgt.studdybuddy.controller;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
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
import edu.gatech.hackgt.studdybuddy.model.Course;
import edu.gatech.hackgt.studdybuddy.model.CourseAdapter;
import edu.gatech.hackgt.studdybuddy.model.CourseType;
import edu.gatech.hackgt.studdybuddy.util.APIClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseSelectionActivity extends AppCompatActivity {
    private RecyclerView courseAddView;
    private LinearLayoutManager courseAddViewManager;
    private CourseAdapter courseAdapter;

    private Spinner courseTypeSpinner;
    private Spinner courseNumberSpinner;

    private Button addButton;
    private Button goToDashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_selection);
        courseAddView = (RecyclerView) findViewById(R.id.addedCourseView);
        courseAddViewManager = new LinearLayoutManager(this);
        courseAdapter = new CourseAdapter(new ArrayList<Course>());
        courseAddView.setLayoutManager(courseAddViewManager);
        courseAddView.setAdapter(courseAdapter);

        APIClient.getInstance().getCourses(MainActivity.userId).enqueue(new Callback<List<Course>>() {
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(CourseSelectionActivity.this, "Unable to get your courses.", Toast.LENGTH_SHORT).show();
                } else {
                    courseAdapter.setCourseList(response.body());
                    courseAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {
                Toast.makeText(CourseSelectionActivity.this, "Unable to get your courses.", Toast.LENGTH_SHORT).show();
            }
        });

        ItemTouchHelper ith = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                Toast.makeText(CourseSelectionActivity.this, "On Swiped", Toast.LENGTH_LONG).show();
                int index = viewHolder.getAdapterPosition();
                Course c = courseAdapter.getCourseList().remove(index);
                courseAdapter.notifyItemRemoved(index);
                APIClient.getInstance().deleteCourse(MainActivity.userId,
                        String.valueOf(c.getCourseType()).toLowerCase(), 
                        c.getCourseNumber()).enqueue(new Callback<APIMessage>() {
                    @Override
                    public void onResponse(Call<APIMessage> call, Response<APIMessage> response) {
                        Toast.makeText(CourseSelectionActivity.this, "Deleted course.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<APIMessage> call, Throwable t) {
                        Toast.makeText(CourseSelectionActivity.this, "Unable to delete course.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        ith.attachToRecyclerView(courseAddView);

        courseTypeSpinner = (Spinner) findViewById(R.id.courseType);
        ArrayAdapter<CourseType> courseTypeArrayAdapter = new ArrayAdapter<CourseType>(this,
                android.R.layout.simple_spinner_item, CourseType.values());
        courseTypeSpinner.setAdapter(courseTypeArrayAdapter);

        courseNumberSpinner = (Spinner) findViewById(R.id.courseNumber);

        courseTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CourseType ct = (CourseType) adapterView.getItemAtPosition(i);
                String coty = String.valueOf(ct).toLowerCase();
                APIClient.getInstance().getCoursesByType(coty).enqueue(new Callback<List<Integer>>() {
                    @Override
                    public void onResponse(Call<List<Integer>> call, Response<List<Integer>> response) {
                        ArrayAdapter<Integer> courseNumberArrayAdapter = new ArrayAdapter<Integer>(CourseSelectionActivity.this,
                                android.R.layout.simple_spinner_item, response.body());
                        courseNumberSpinner.setAdapter(courseNumberArrayAdapter);
                    }

                    @Override
                    public void onFailure(Call<List<Integer>> call, Throwable t) {
                        Toast.makeText(CourseSelectionActivity.this,
                                "Unable to get course numbers.", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Course course = new Course((CourseType) courseTypeSpinner.getSelectedItem(),
                        (Integer) courseNumberSpinner.getSelectedItem());
                if (!courseAdapter.getCourseList().contains(course)) {
                    courseAdapter.getCourseList().add(course);
                    courseAdapter.notifyItemInserted(courseAdapter.getCourseList().size() - 1);
                    APIClient.getInstance().storeCourse(MainActivity.userId, course).enqueue(new Callback<APIMessage>() {
                        @Override
                        public void onResponse(Call<APIMessage> call, Response<APIMessage> response) {
                            if (response.isSuccessful() && response.body().isSuccess()) {
                                Toast.makeText(CourseSelectionActivity.this, "Stored course.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<APIMessage> call, Throwable t) {
                            Toast.makeText(CourseSelectionActivity.this, "Unable to store course.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        goToDashboard = (Button) findViewById(R.id.goToDashboard);
        goToDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CourseSelectionActivity.this, ProfileCourseActivity.class);
                startActivity(intent);
            }
        });
    }
}
