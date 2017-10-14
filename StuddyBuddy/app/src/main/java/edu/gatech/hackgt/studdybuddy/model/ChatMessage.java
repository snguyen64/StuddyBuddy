package edu.gatech.hackgt.studdybuddy.model;

import android.support.v7.widget.RecyclerView;

public class ChatMessage extends RecyclerView.ViewHolder {
    public String message;
    public ChatMessage(String message) {
        this.message = message;
    }
}
