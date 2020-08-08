package com.houarizegai.slidesremoteserver.controllers;

import javafx.fxml.FXML;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class AboutController {
    private static final String WEBSITE = "https://softbrainsdz.github.io/";
    private static final String FACEBOOK = "https://facebook.com/softbrainsdz";
    private static final String INSTAGRAM = "https://instagram.com/softbrainsdz";
    private static final String TWITTER = "https://twitter.com/softbrainsdz";
    private static final String LINKEDIN = "https://linkedin.com/company/softbrainsdz";

    @FXML
    void onClose() {
        MainController.aboutDialog.close();
    }

    @FXML
    void goFacebook() {
        visit(FACEBOOK);
    }

    @FXML
    void goInstagram() {
        visit(INSTAGRAM);
    }

    @FXML
    void goLinkedin() {
        visit(LINKEDIN);
    }

    @FXML
    void goTwitter() {
        visit(TWITTER);
    }

    @FXML
    void goWebsite() {
        visit(WEBSITE);
    }

    private void visit(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
