package com.houarizegai.slidesremoteserver.controllers;

import com.houarizegai.slidesremoteserver.App;
import com.houarizegai.slidesremoteserver.engine.QRCodeEngine;
import com.houarizegai.slidesremoteserver.engine.SocketServer;
import com.houarizegai.slidesremoteserver.utils.NetworkUtils;
import com.houarizegai.slidesremoteserver.utils.RegexChecker;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private StackPane root;

    @FXML
    private JFXComboBox<String> comboNetworkName;

    @FXML
    private Label lblIpAddress;

    @FXML
    private ImageView imgQRCode;

    @FXML
    private Label lblStatus;

    public static JFXDialog aboutDialog;

    private SocketServer socketServer;

    // Available network addresses in my PC
    private static Map<String, String> networkAddresses;

    // Used to make stage draggable
    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Init dialog
        try {
            Region aboutView = FXMLLoader.load(getClass().getResource("/fxml/about.fxml"));

            aboutDialog = new JFXDialog(root, aboutView, JFXDialog.DialogTransition.CENTER);
        } catch (IOException e) {
            e.printStackTrace();
        }


        comboNetworkName.valueProperty().addListener(e -> {
            updateIpLbl();
            changeQRCodeImg();
        });

        /* Make stage draggable */
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            App.stage.setX(event.getScreenX() - xOffset);
            App.stage.setY(event.getScreenY() - yOffset);
            App.stage.setOpacity(0.7f);
        });
        root.setOnDragDone(e -> App.stage.setOpacity(1.0f));
        root.setOnMouseReleased(e -> App.stage.setOpacity(1.0f));

        onRefresh();
    }

    @FXML
    private void onRefresh() {
        comboNetworkName.getItems().clear();
        networkAddresses = NetworkUtils.getMyIPv4Addresses();

        if(!networkAddresses.isEmpty()) {

            for(String key: networkAddresses.keySet()) {
                comboNetworkName.getItems().add(key);
            }
            changeQRCodeImg();
        }
    }

    @FXML
    private void onStart() {
        socketServer = new SocketServer();
        if(RegexChecker.isIP(lblIpAddress.getText())) {
            socketServer.start();
            lblStatus.setText("Connected");
        } else {
            lblStatus.setText("Disconnected");
        }
    }

    @FXML
    private void onStop() {
        socketServer.stop();
        lblStatus.setText("Disconnected");
    }

    @FXML
    private void onClose() {
        Platform.exit();
    }

    @FXML
    private void onHide() {
        App.stage.setIconified(true);
    }

    @FXML
    void onAbout() {
        aboutDialog.show();
    }

    private void changeQRCodeImg() {
        String myIP = lblIpAddress.getText();
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

    private void updateIpLbl() {
        String selectedNetworkName = networkAddresses.get(comboNetworkName.getSelectionModel().getSelectedItem());
        lblIpAddress.setText(selectedNetworkName);
    }
}
