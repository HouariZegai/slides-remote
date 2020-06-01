package com.houarizegai.slidesremoteserver.controllers;

import com.houarizegai.slidesremoteserver.engine.QRCodeEngine;
import com.houarizegai.slidesremoteserver.engine.RemoteControlEngine;
import com.houarizegai.slidesremoteserver.engine.RemoteControlEngineImpl;
import com.houarizegai.slidesremoteserver.engine.RemoteControlServer;
import com.houarizegai.slidesremoteserver.utils.NetworkUtils;
import com.houarizegai.slidesremoteserver.utils.RegexChecker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private JFXTextField fieldIpAddress;

    @FXML
    private ImageView imgQRCode;

    @FXML
    private Label lblStatus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        onRefresh();
    }

    @FXML
    private void onRefresh() {
        String myIp = NetworkUtils.getMyIPAddress();
        if(myIp != null) {
            fieldIpAddress.setText(myIp);
            Image generatedQRCode = QRCodeEngine.encode(myIp, 250, 250);
            if(generatedQRCode != null) {
                imgQRCode.setImage(generatedQRCode);
                lblStatus.setText("Ready to start");
            } else {
                lblStatus.setText("Disconnected! - Generation QRCode problem");
            }
        } else {
            lblStatus.setText("Disconnected!");
        }
    }


    @FXML
    private void onStart() {
        if(RegexChecker.isIP(fieldIpAddress.getText())) {
            RemoteControlServer.start();
            lblStatus.setText("Connected");
        } else {
            lblStatus.setText("Disconnected, please press the refresh button!");
        }
    }

    @FXML
    private void onStop() {
        RemoteControlServer.stop();
        lblStatus.setText("Disconnected");
    }

}
