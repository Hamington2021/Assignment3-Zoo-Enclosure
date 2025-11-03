package com.example.zoostructure.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

public class AnimalViewC {
    @FXML
    private ChoiceBox<String> aTypeChoiceBox;

    @FXML
    private Spinner<Double> aAgeSpinner;

    @FXML
    private TextField aNameTextField;

    @FXML
    protected void onSaveButtonClick() {

    }

    @FXML
    protected void onBackButtonClick() {

    }
}
