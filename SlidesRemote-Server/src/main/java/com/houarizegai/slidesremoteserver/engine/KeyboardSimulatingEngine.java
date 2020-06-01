package com.houarizegai.slidesremoteserver.engine;

import java.awt.*;

public class KeyboardSimulatingEngine {

    private Robot robot;

    public KeyboardSimulatingEngine() {
        try {
            robot = new Robot();
        } catch(AWTException e) {
            e.printStackTrace();
        }
    }


    public void pressKey(int key) { // Simulate a key press
        robot.keyPress(key);
        robot.keyRelease(key);
    }
}
