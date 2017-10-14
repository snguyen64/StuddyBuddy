package edu.gatech.hackgt.studdybuddy.model;

import java.util.List;

public class Chatroom {
    private List<User> users;
    private CourseType courseType;
    private Course course;
    private String name;

    public Chatroom(String name, CourseType courseType, Course course) {
        this.name = name;
        this.courseType = courseType;
        this.course = course;
    }

    public void addUser(User user) {
        users.add(user);
    }
    public void removeUser(User user) {
        users.remove(user);
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
