package com.houarizegai.slidesremoteserver.controllers;

import com.houarizegai.slidesremoteserver.engine.QRCodeEngine;
import com.houarizegai.slidesremoteserver.engine.SocketServer;
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

    private SocketServer socketServer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        onRefresh();

        fieldIpAddress.setOnKeyReleased(e -> changeQRCodeImg());
    }

    @FXML
    private void onRefresh() {
        String myIp = NetworkUtils.getMyIPAddress();
        fieldIpAddress.setText(myIp);
        changeQRCodeImg();
    }

    @FXML
    private void onStart() {
        socketServer = new SocketServer();
        if(RegexChecker.isIP(fieldIpAddress.getText())) {
            socketServer.start();
            lblStatus.setText("Connected");
        } else {
            lblStatus.setText("Disconnected, please press the refresh button!");
        }
    }

    @FXML
    private void onStop() {
        socketServer.stop();
        lblStatus.setText("Disconnected");
    }


    private void changeQRCodeImg() {
        String myIP = fieldIpAddress.getText();
        if(RegexChecker.isIP(myIP)) {
            Image generatedQRCode = QRCodeEngine.encode(myIP, 250, 250);
            if(generatedQRCode != null) {
                imgQRCode.setImage(generatedQRCode);
                lblStatus.setText("Ready to start");
            } else {
                lblStatus.setText("Disconnected! - Generation QRCode problem");
            }
        }

    }

}
