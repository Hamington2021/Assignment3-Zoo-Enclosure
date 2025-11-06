package com.example.zoostructure.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CompositeEnclosureViewController {

    @FXML
    private Label sectionNameLabel;
    @FXML
    private Button newSectionButton;
    @FXML
    private Button lionViewButton;
    @FXML
    private Button tigerViewButton;
    @FXML
    private Button cougarViewButton;

    @FXML
    protected void onNewSectionButtonClick() {

    }

    @FXML
    protected void onLionViewButtonClick() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LionView.fxml"));
        try {
            fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    protected void onTigerViewButtonClick() {

    }

    @FXML
    protected void onCougarViewButtonClick() {

    }

}
