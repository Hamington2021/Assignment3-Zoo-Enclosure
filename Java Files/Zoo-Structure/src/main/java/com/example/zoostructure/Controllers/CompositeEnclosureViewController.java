package com.example.zoostructure.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

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
    protected void onLionViewButtonClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/zoostructure/Enclosure-view.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL); // 🔒 Makes it modal
        stage.setTitle("Lion Enclosure");
        stage.setScene(new Scene(root));
        stage.showAndWait(); // ⏳ Blocks until the window is closed
    }

    @FXML
    protected void onTigerViewButtonClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/zoostructure/Enclosure-view.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL); // 🔒 Makes it modal
        stage.setTitle("Tiger Enclosure");
        stage.setScene(new Scene(root));
        stage.showAndWait(); // ⏳ Blocks until the window is closed
    }


    @FXML
    protected void onCougarViewButtonClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/zoostructure/Enclosure-view.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL); // 🔒 Makes it modal
        stage.setTitle("Cougar Enclosure");
        stage.setScene(new Scene(root));
        stage.showAndWait(); // ⏳ Blocks until the window is closed
    }
}

