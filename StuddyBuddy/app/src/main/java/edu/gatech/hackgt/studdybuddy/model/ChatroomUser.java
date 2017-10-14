package edu.gatech.hackgt.studdybuddy.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ChatroomUser implements Serializable {
    @SerializedName("id")
    private int id;

    @SerializedName("username")
    private String username;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
