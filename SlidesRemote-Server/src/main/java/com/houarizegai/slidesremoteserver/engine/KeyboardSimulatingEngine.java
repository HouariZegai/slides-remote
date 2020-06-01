package com.houarizegai.slidesremoteserver.engine;

import java.awt.*;
import java.awt.event.KeyEvent;

public class KeyboardSimulatingEngine {

    private Robot robot;

    public KeyboardSimulatingEngine() {
        try {
            robot = new Robot();
        } catch(AWTException e) {
            e.printStackTrace();
        }
    }

    public void pressKey(String key) { // Simulate a key press

        switch(key) {
            case "NEXT":
                robot.keyPress(KeyEvent.VK_RIGHT);
                break;
            case "PREVIOUS":
                robot.keyPress(KeyEvent.VK_LEFT);
                break;
            case "START":
                robot.keyPress(KeyEvent.VK_F5);
                break;
            case "STOP":
                robot.keyPress(KeyEvent.VK_ESCAPE);
                break;
            case "VOLUME_UP":
                robot.keyPress(KeyEvent.VK_F3);
                break;
            case "VOLUME_DOWN":
                robot.keyPress(KeyEvent.VK_F2);
                break;
            case "VOLUME_MUTE":
                robot.keyPress(KeyEvent.VK_F1);
                break;
        }

    }
}
