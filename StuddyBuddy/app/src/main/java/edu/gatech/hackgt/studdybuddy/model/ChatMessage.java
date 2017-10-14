package edu.gatech.hackgt.studdybuddy.model;

import android.support.v7.widget.RecyclerView;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ChatMessage implements Serializable{
    @SerializedName("message")
    private String message;
    boolean side;
    public ChatMessage(String message, boolean side) {
        this.message = message;
        this.side = side;
    }
    public String getMessage() {
        return message;
    }
    public String toString() {
        return message;
    }
}
