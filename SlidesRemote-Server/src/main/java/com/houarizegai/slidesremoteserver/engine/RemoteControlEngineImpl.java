package com.houarizegai.slidesremoteserver.engine;

import java.awt.event.KeyEvent;
import java.rmi.RemoteException;

public class RemoteControlEngineImpl implements RemoteControlEngine {

    private KeyboardSimulatingEngine keyboard;

    public RemoteControlEngineImpl() {
        keyboard = new KeyboardSimulatingEngine();
    }

    @Override
    public void nextSlide() throws RemoteException {
        keyboard.pressKey(KeyEvent.VK_RIGHT);
    }

    @Override
    public void previousSlide() throws RemoteException {
        keyboard.pressKey(KeyEvent.VK_LEFT);
    }

    @Override
    public void playSlide() throws RemoteException {
        keyboard.pressKey(KeyEvent.VK_F5);
    }

    @Override
    public void pauseSlide() throws RemoteException {
        keyboard.pressKey(KeyEvent.VK_ESCAPE);
    }

    @Override
    public void volumeUp() throws RemoteException {
        keyboard.pressKey(KeyEvent.VK_F3);
    }

    @Override
    public void volumeDown() throws RemoteException {
        keyboard.pressKey(KeyEvent.VK_F2);
    }

    @Override
    public void volumeMute() throws RemoteException {
        keyboard.pressKey(KeyEvent.VK_F1);
    }
}
