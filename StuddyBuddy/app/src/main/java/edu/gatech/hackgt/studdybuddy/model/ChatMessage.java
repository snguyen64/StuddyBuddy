package edu.gatech.hackgt.studdybuddy.model;

import android.support.v7.widget.RecyclerView;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class ChatMessage implements Serializable{
    @SerializedName("message")
    private String message;

    @SerializedName("username")
    private String username;

    @SerializedName("timestamp")
    private Date timestamp;

    public ChatMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    public String toString() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
