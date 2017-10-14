package edu.gatech.hackgt.studdybuddy.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Chatroom implements Serializable{
    @SerializedName("usersList")
    private List<ChatroomUser> users;
    @SerializedName("course")
    private Course course;
    @SerializedName("name")
    private String name;

    public Chatroom(String name, Course course) {
        this.name = name;
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setUsers(List<ChatroomUser> users) {
        this.users = users;
    }

    public List<ChatroomUser> getUsers() {
        return users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
