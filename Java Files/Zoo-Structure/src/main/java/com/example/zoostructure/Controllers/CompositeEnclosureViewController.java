package com.example.zoostructure.Controllers;

import java.io.IOException;

import com.example.zoostructure.Helpers.ImportHelper;
import com.example.zoostructure.Model.CompositeEnclosureCollection;
import com.example.zoostructure.Model.Enclosure;
import com.example.zoostructure.Model.EnclosureCollection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CompositeEnclosureViewController {

    @FXML
    private GridPane compositeEnclosureGridPane;

    private int cardCount = 0;
    private final int cardPerRow = 3;

    private VBox createStyledCard() {
        VBox card = new VBox(10);
        card.setPadding(new Insets(12));
        card.setAlignment(Pos.TOP_LEFT); // ✅ consistent internal alignment
        card.setBackground(new Background(new BackgroundFill(Color.web("#F5F5F5"), new CornerRadii(10), Insets.EMPTY)));
        card.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(2))));
        card.setEffect(new DropShadow(5, Color.gray(0.3)));
        return card;
    }

    public void addEnclosureCard(Enclosure enclosure) {
        VBox card = createStyledCard();

        Label nameLabel = new Label(enclosure.getName());
        nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        Button viewButton = new Button("View");
        viewButton.setStyle("""
                    -fx-background-color: #dddddd;
                    -fx-border-color: #444;
                    -fx-border-width: 1;
                    -fx-border-radius: 6;
                    -fx-text-fill: #222;
                    -fx-font-weight: bold;
                    -fx-background-radius: 6;
                    -fx-padding: 6 12;
                    -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 2, 0, 0, 1);
                """);
        viewButton.setOnAction(e -> openEnclosureWindow(enclosure));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        spacer.setMinWidth(0);

        HBox row = new HBox(10, nameLabel, spacer, viewButton);
        row.setAlignment(Pos.CENTER_LEFT);
        card.getChildren().add(row);

        int col = cardCount % cardPerRow;
        int rowIndex = cardCount / cardPerRow;
        compositeEnclosureGridPane.add(card, col, rowIndex);
        GridPane.setMargin(card, new Insets(10));
        GridPane.setValignment(card, VPos.TOP); // ✅ align to top

        cardCount++;
    }

    private void addCompositeCard(CompositeEnclosureCollection composite) {
        VBox card = createStyledCard();

        Label title = new Label(composite.getName());
        title.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        card.getChildren().add(title);

        for (EnclosureCollection child : composite.getCollections()) {
            if (child instanceof Enclosure enclosure) {
                Button subsectionButton = new Button(enclosure.getName());
                subsectionButton.setStyle("""
                        -fx-background-color: #dddddd;
                        -fx-border-color: #444;
                        -fx-border-width: 1;
                        -fx-border-radius: 6;
                        -fx-text-fill: #222;
                        -fx-font-weight: bold;
                        -fx-background-radius: 6;
                        -fx-padding: 6 12;
                        -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 2, 0, 0, 1);
                        """);
                subsectionButton.setOnAction(e -> openEnclosureWindow(enclosure));
                card.getChildren().add(subsectionButton);
            }
        }

        int col = cardCount % cardPerRow;
        int row = cardCount / cardPerRow;
        compositeEnclosureGridPane.add(card, col, row);
        compositeEnclosureGridPane.setAlignment(Pos.TOP_CENTER);
        GridPane.setMargin(card, new Insets(10));
        GridPane.setValignment(card, VPos.TOP); // ✅ align to top
        cardCount++;
    }

    @FXML
    private void initialize() {
        compositeEnclosureGridPane.setAlignment(Pos.TOP_CENTER); // ensures grid aligns to top

        CompositeEnclosureCollection bigCats = ImportHelper.createAnimals();

        int totalItems = bigCats.getCollections().size();
        int totalRows = (int) Math.ceil((double) totalItems / cardPerRow);

        for (int i = 0; i < totalRows; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setMinHeight(150); // consistent row height
            rowConstraints.setValignment(VPos.TOP); // align content to top
            compositeEnclosureGridPane.getRowConstraints().add(rowConstraints);
        }

        for (EnclosureCollection child : bigCats.getCollections()) {
            if (child instanceof Enclosure enclosure) {
                addEnclosureCard(enclosure);
            } else if (child instanceof CompositeEnclosureCollection composite) {
                addCompositeCard(composite);
            }
        }
    }

    private void openEnclosureWindow(Enclosure enclosure) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/zoostructure/Enclosure-view.fxml"));
            Parent root = loader.load();

            EnclosureViewController controller = loader.getController();
            controller.setEnclosure(enclosure);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(enclosure.getName());
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}