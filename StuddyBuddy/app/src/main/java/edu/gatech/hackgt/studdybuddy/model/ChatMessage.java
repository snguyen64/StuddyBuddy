package edu.gatech.hackgt.studdybuddy.model;

import android.support.v7.widget.RecyclerView;

public class ChatMessage {
    public String message;
    boolean side;
    public ChatMessage(String message, boolean side) {
        this.message = message;
        this.side = side;
    }
}
