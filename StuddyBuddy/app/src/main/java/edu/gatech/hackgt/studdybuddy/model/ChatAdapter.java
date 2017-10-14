package edu.gatech.hackgt.studdybuddy.model;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import edu.gatech.hackgt.studdybuddy.R;
import edu.gatech.hackgt.studdybuddy.controller.MainActivity;


public class ChatAdapter extends RecyclerView.Adapter {

    private List<ChatMessage> messages;
    private int page = 0;
    private int lastId = 0;

    public ChatAdapter(List<ChatMessage> messages) { this.messages = messages; }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View v = li.inflate(R.layout.message_card, parent, false);
        return new DataViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DataViewHolder dvh = (DataViewHolder) holder;
        final ChatMessage cm = messages.get(position);
        dvh.message.setText(cm.getMessage());
        dvh.user.setText(String.valueOf(MainActivity.userId));
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLastId() {
        return lastId;
    }

    public void setLastId(int lastId) {
        this.lastId = lastId;
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

    private static class DataViewHolder extends RecyclerView.ViewHolder {
        private TextView message;
        private TextView user;
        public DataViewHolder(View itemView) {
            super(itemView);
            message = itemView.findViewById(R.id.message);
            user = itemView.findViewById(R.id.username);
        }
    }

}
