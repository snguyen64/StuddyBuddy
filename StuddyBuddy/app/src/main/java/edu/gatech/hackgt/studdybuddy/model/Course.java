package edu.gatech.hackgt.studdybuddy.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Course implements Serializable {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        return courseNumber == course.courseNumber && courseType == course.courseType;

    }

}
