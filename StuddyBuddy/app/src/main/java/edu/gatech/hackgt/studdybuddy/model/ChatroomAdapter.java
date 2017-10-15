package edu.gatech.hackgt.studdybuddy.model;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import edu.gatech.hackgt.studdybuddy.R;
import edu.gatech.hackgt.studdybuddy.controller.ActiveChatRoomActivity;
import edu.gatech.hackgt.studdybuddy.controller.ChatRoomListActivity;

public class ChatroomAdapter extends RecyclerView.Adapter {

    public interface OnItemClickListener {
        void onItemClick(Chatroom room);
    }

    private List<Chatroom> chatrooms;

    private final OnItemClickListener listener;
    public ChatroomAdapter(List<Chatroom> data, OnItemClickListener listener) {
        this.chatrooms = data;
        this.listener = listener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chatroom_card,
                parent, false);
        return new ChatroomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Chatroom chatroom = chatrooms.get(position);
        ((ChatroomViewHolder) holder).groupName.setText(chatroom.getName());
        ((ChatroomViewHolder) holder).subject.setText(chatroom.getCourse().toString());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                listener.onItemClick(chatroom);
            }
        });
    }

    @Override
    public int getItemCount() {
        return chatrooms.size();
    }

    public List<Chatroom> getChatrooms() {
        return chatrooms;
    }

    public void setChatrooms(List<Chatroom> chatrooms) {
        this.chatrooms = chatrooms;
    }

    private class ChatroomViewHolder extends RecyclerView.ViewHolder{
        private TextView groupName;
        private TextView subject;

        public ChatroomViewHolder(View itemView) {
            super(itemView);
            groupName = itemView.findViewById(R.id.username);
            subject = itemView.findViewById(R.id.message);
        }
    }
}
