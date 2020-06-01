package com.houarizegai.slidesremoteserver.engine;

import com.houarizegai.slidesremoteserver.utils.Config;

import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class RemoteControlServer {
    private static boolean isStarted = true;
    public static void start() {
        isStarted = true;

        (new Thread() {
            @Override
            public synchronized void start() {
                try {
                    RemoteControlEngineImpl remoteImpl = new RemoteControlEngineImpl();
                    RemoteControlEngine objDis = (RemoteControlEngine) UnicastRemoteObject.exportObject(remoteImpl, Config.PORT);
                    LocateRegistry.createRegistry(Config.PORT).bind("remoteControlObj", objDis);
                    while (isStarted) {
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).run();
    }

    public static void stop() {
        isStarted = false;
    }
}
