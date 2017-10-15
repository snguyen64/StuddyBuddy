package edu.gatech.hackgt.studdybuddy.model;

import android.app.Application;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import java.net.URISyntaxException;

public class ChatApp extends Application{
    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://10:0:2:2:3000");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public Socket getSocket() {
        return mSocket;
    }
}
