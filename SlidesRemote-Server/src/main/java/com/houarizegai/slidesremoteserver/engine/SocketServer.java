package com.houarizegai.slidesremoteserver.engine;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Handler;

public class SocketServer {
    private static ServerSocket serverSocket;
    private static Socket socket;
    private static DataInputStream inputStream;

    private static final int PORT = 7800;
    private static KeyboardSimulatingEngine keyboard;
    private static boolean isStarted;

    public SocketServer() {
        keyboard = new KeyboardSimulatingEngine();
    }

    public void start() {
        isStarted = true;
        (new Thread(() -> {
            try {
                serverSocket = new ServerSocket(SocketServer.PORT);
                while (isStarted) {
                    socket = serverSocket.accept();
                    inputStream = new DataInputStream(socket.getInputStream());

                    String receivedData = inputStream.readUTF();
                    keyboard.pressKey(receivedData);
                    System.out.println("Received data: " + receivedData);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        })).start();
    }

    public void stop() {
        isStarted = false;
    }
}
