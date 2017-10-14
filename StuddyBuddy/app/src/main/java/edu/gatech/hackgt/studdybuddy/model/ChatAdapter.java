package edu.gatech.hackgt.studdybuddy.model;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import edu.gatech.hackgt.studdybuddy.R;


public class ChatAdapter extends RecyclerView.Adapter {
    private List<ChatMessage> messages;
    public ChatAdapter(List<ChatMessage> messages) { this.messages = messages; }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View v = li.inflate(R.layout.data_item, parent, false);
        return new ChatAdapter.DataViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ChatAdapter.DataViewHolder dvh = (ChatAdapter.DataViewHolder) holder;
        final ChatMessage message = messages.get(position);
        dvh.message.setText(message.toString());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    private static class DataViewHolder extends RecyclerView.ViewHolder {
        private TextView message;
        public DataViewHolder(View messageView) {
            super(messageView);
            message = messageView.findViewById(R.id.messagesView);
        }
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ChatMessage> messages) {
        this.messages = messages;
    }

    public void add(ChatMessage cMessage) {
        messages.add(cMessage);
    }
}
