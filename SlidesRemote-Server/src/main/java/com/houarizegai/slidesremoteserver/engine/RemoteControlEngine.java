package com.houarizegai.slidesremoteserver.engine;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteControlEngine extends Remote {
    void nextSlide() throws RemoteException;

    void previousSlide() throws RemoteException;

    void playSlide() throws RemoteException;

    void pauseSlide() throws RemoteException;

    void volumeUp() throws RemoteException;

    void volumeDown() throws RemoteException;

    void volumeMute() throws RemoteException;
}
