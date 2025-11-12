package com.example.zoostructure.Controllers;

import com.example.zoostructure.Model.Animal;
import com.example.zoostructure.Model.Enclosure;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EnclosureViewController {

    @FXML
    private ListView<Animal> animalList;

    @FXML
    private Label animalLabel;

    @FXML
    private Button deleteButton;

    @FXML
    private Button addButton;

    @FXML
    private Button editButton;

    private Enclosure enclosure;

    public void setEnclosure(Enclosure enclosure) {
        this.enclosure = enclosure;

        animalLabel.setText("Animals in " + enclosure.getName());
        animalList.getItems().setAll(enclosure.getAnimals());

        animalList.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Animal animal, boolean empty) {
                super.updateItem(animal, empty);
                setText(empty || animal == null ? null : animal.getName() + " (" + animal.getAge() + " yrs)");
            }
        });
    }

    @FXML
    protected void onBackButtonClick() {
        Stage stage = (Stage) deleteButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void onDeleteButtonClick() {
        Animal selected = animalList.getSelectionModel().getSelectedItem();
        if (selected != null) {
            enclosure.removeAnimal(selected.getName());
            animalList.getItems().remove(selected);
        }
    }

    @FXML
    protected void onAddButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("animal-view.fxml"));
            Parent root = loader.load();

            AnimalViewController controller = loader.getController();
            controller.setEnclosure(enclosure);

            Stage stage = new Stage();
            stage.setTitle("Add Animal");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();

        } catch (Exception e) {
            System.err.println("Error loading Add Animal View:");
            e.printStackTrace();
        }
    }

    @FXML
    protected void onEditButtonClick() {
        Animal selected = animalList.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("animal-view.fxml"));
                Parent root = loader.load();

                AnimalViewController controller = loader.getController();

                Stage stage = new Stage();
                stage.setTitle("Edit/View Animal");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(root));
                stage.show();

                controller.setAnimal(selected);
                controller.setEnclosure(enclosure);

                stage.setOnHidden(event -> refreshAnimalList());
            } catch (Exception e){
                System.err.println("Error loading Edit/View Animal:");
                e.printStackTrace();
            }
        }
    }
    public void refreshAnimalList() {
        animalList.getItems().setAll(enclosure.getAnimals());
    }
}
