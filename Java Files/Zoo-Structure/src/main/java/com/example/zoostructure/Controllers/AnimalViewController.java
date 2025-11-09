package com.example.zoostructure.Controllers;

import com.example.zoostructure.Model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AnimalViewController {

    private Animal aAnimal;

    private boolean isNew = true;

    private Enclosure aEnclosure;

    @FXML
    private ChoiceBox<String> aTypeChoiceBox;

    @FXML
    private TextField aNameTextField;

    @FXML
    private Spinner<Double> aAgeSpinner;

    @FXML
    public void initialize() {
        this.aTypeChoiceBox.getItems().addAll("Lion", "Tiger", "Cougar");
        this.aAgeSpinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 200, 1));
    }

    @FXML
    protected void onSaveButtonClick() {
        try {
            String animalName = this.aNameTextField.getText();
            double animalAge = this.aAgeSpinner.getValue();
            if (this.isNew) {
                switch(aTypeChoiceBox.getSelectionModel().getSelectedItem()) {
                    case "Lion":
                        this.aAnimal = new Lion(animalName, animalAge);
                        break;
                    case "Tiger":
                        this.aAnimal = new Tiger(animalName, animalAge);
                        break;
                    case "Cougar":
                        this.aAnimal = new Cougar(animalName, animalAge);
                        break;
                    default:
                        this.aAnimal = new Animal(animalName, animalAge);
                        System.err.println("No animal type selected. This should not happen.");
                }
            } else {
                this.aAnimal.setName(animalName);
                this.aAnimal.setAge(animalAge);
            }
        } catch (IllegalArgumentException exception) {
            Alert alert = new Alert(Alert.AlertType.WARNING, exception.getMessage());
            alert.showAndWait();
            return;
        }

        if (this.isNew) {
            this.aEnclosure.addAnimal(this.aAnimal);
        }

        this.onBackButtonClick();
    }

    @FXML
    protected void onBackButtonClick() {
        Stage stage = (Stage) aNameTextField.getScene().getWindow();
        stage.close();
    }

    public void setAnimal(Animal pAnimal) {
        this.aAnimal = pAnimal;
        this.isNew = false;
        this.aTypeChoiceBox.setValue(pAnimal.getClass().getSimpleName());
        this.aTypeChoiceBox.setDisable(true);
        this.aNameTextField.setText(pAnimal.getName());
        this.aAgeSpinner.getValueFactory().setValue(pAnimal.getAge());
        Stage currentStage = (Stage) this.aNameTextField.getScene().getWindow();
        currentStage.setTitle("Edit Animal: " + pAnimal.getName());
    }

    public void setEnclosure(Enclosure pEnclosure) {
        this.aEnclosure = pEnclosure;
    }
}
