package edu.gatech.hackgt.studdybuddy.model;

import com.google.gson.annotations.SerializedName;

public class Course {
    @SerializedName("courseType")
    private CourseType courseType;

    @SerializedName("courseNumber")
    private int courseNumber;

    public Course(CourseType courseType, int courseNumber) {
        this.courseType = courseType;
        this.courseNumber = courseNumber;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public int getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(int courseNumber) {
        this.courseNumber = courseNumber;
    }

    @Override
    public String toString() {
        return String.valueOf(courseType) + " " + courseNumber;
    }
}
