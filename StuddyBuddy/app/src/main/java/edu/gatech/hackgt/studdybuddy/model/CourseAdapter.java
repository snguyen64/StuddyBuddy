package edu.gatech.hackgt.studdybuddy.model;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import edu.gatech.hackgt.studdybuddy.R;

public class CourseAdapter extends RecyclerView.Adapter {
    private List<Course> courseList;
    private int page = 0;
    private int lastId = 0;

    public CourseAdapter(List<Course> list) {
        courseList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View v = li.inflate(R.layout.course_card, parent, false);
        return new DataViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DataViewHolder dvh = (DataViewHolder) holder;
        final Course rd = courseList.get(position);
        dvh.course.setText(rd.toString());
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    private static class DataViewHolder extends RecyclerView.ViewHolder {
        private TextView course;
        public DataViewHolder(View itemView) {
            super(itemView);
            course = itemView.findViewById(R.id.courseID);
        }
    }
}
