package edu.gatech.hackgt.studdybuddy.model;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import edu.gatech.hackgt.studdybuddy.R;

public class ChatroomAdapter extends RecyclerView.Adapter {
    private List<Chatroom> chatrooms;

    public ChatroomAdapter(List<Chatroom> chatrooms) {
        this.chatrooms = chatrooms;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chatroom_card,
                parent, false);
        return new ChatroomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Chatroom chatroom = chatrooms.get(position);
        ((ChatroomViewHolder) holder).groupName.setText(chatroom.getName());
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

    private class ChatroomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView groupName;

        public ChatroomViewHolder(View itemView) {
            super(itemView);
            groupName = itemView.findViewById(R.id.username);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
