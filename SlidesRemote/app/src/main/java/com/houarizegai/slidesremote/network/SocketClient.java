package com.houarizegai.slidesremote.network;

import android.os.AsyncTask;
import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketClient extends AsyncTask<String, Void, Void> {

    private static Socket socket;
    private static DataOutputStream outputStream;

    private static int serverPort = 7800;
    private static String serverIP;

    public SocketClient(String serverIP) {
        SocketClient.serverIP = serverIP;
    }

    @Override
    protected Void doInBackground(String ...voids) {
        try {
            Log.d("SocketServer", "before init socket passed!");
            socket = new Socket(SocketClient.serverIP, SocketClient.serverPort);
            Log.d("SocketServer.isConnected", String.valueOf(socket.isConnected()));
            outputStream = new DataOutputStream(socket.getOutputStream());
            outputStream.writeUTF(voids[0]);

            outputStream.flush();
            outputStream.close();
            socket.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            Log.d("SocketServer.msg", ioe.getMessage());
        }

        return null;
    }
}
