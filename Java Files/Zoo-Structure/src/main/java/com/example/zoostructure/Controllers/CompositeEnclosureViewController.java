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

/**
 * The CompositeEnclosureViewController class manages the layout and presentation of
 * enclosure and composite enclosure cards in a grid pane. It is responsible for
 * dynamically creating and organizing styled cards for both Enclosure and
 * CompositeEnclosureCollection objects, ensuring they are displayed in a grid
 * according to predefined constraints.
 *
 * This controller initializes the view with a collection of enclosures and composites,
 * adjusts the grid layout dynamically, and provides functionality to open detailed
 * views for Enclosure objects.
 *
 * Features of this class include:
 *
 * - Creates stylized cards for Enclosure and CompositeEnclosureCollection objects.
 * - Dynamically arranges cards into a grid pane with consistent row and column alignment.
 * - Supports nested composite structures, displaying them alongside single enclosures.
 * - Provides a detailed view for individual Enclosure objects using a modal window.
 *
 * @author Serena
 */
public class CompositeEnclosureViewController {

    /**
     * Represents the main GridPane layout where the composite enclosure cards
     * and individual animal enclosure cards are displayed.
     * It serves as a visual container to organize the displayed content in
     * a grid-based layout for the CompositeEnclosureViewController.
     *
     * This variable is injected via FXML and is expected to be linked with the
     * corresponding UI component in the FXML file.
     */
    @FXML
    private GridPane compositeEnclosureGridPane;
    /**
     * Keeps track of the number of cards added to the grid pane.
     */
    private int cardCount = 0;
    /**
     * Represents the number of cards displayed in each row of the composite enclosure grid.
     * This constant is used to properly arrange the layout of cards in the user interface.
     */
    private final int cardPerRow = 3;

    /**
     * Creates and returns a styled VBox meant to serve as a UI component for displaying content.
     * The card features custom padding, alignment, background, border, and drop shadow for enhanced appearance.
     *
     * @return a styled VBox instance with predefined layout and visual properties
     */
    private VBox createStyledCard() {
        VBox card = new VBox(10);
        card.setPadding(new Insets(12));
        card.setAlignment(Pos.TOP_LEFT); // ? consistent internal alignment
        card.setBackground(new Background(new BackgroundFill(Color.web("#F5F5F5"), new CornerRadii(10), Insets.EMPTY)));
        card.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(2))));
        card.setEffect(new DropShadow(5, Color.gray(0.3)));
        return card;
    }

    /**
     * Adds a visual card representation of the provided enclosure to the user interface.
     * Each card contains the enclosure's name and a "View" button, which opens a dedicated window for the enclosure details.
     *
     * @param enclosure the enclosure whose details will be displayed on the card
     */
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
        GridPane.setValignment(card, VPos.TOP); // ? align to top

        cardCount++;
    }

    /**
     * Adds a composite card representation of the provided {@link CompositeEnclosureCollection}
     * to the user interface. Each composite card contains a title displaying the name of the
     * composite collection. It also includes buttons for each child enclosure in the collection,
     * which, when clicked, open a dedicated window for that enclosure.
     *
     * @param composite the composite enclosure collection whose details will be displayed on the card
     */
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
        GridPane.setValignment(card, VPos.TOP); // ? align to top
        cardCount++;
    }

    /**
     * Initializes the user interface for the composite enclosure view.
     * This method is invoked automatically after the FXML file has been loaded.
     *
     * The method performs the following steps:
     *
     * 1. Aligns the composite enclosure grid pane to the top center.
     * 2. Fetches a collection of enclosures using {@link ImportHelper#createAnimals()}.
     * 3. Calculates the total number of rows needed in the grid based on the number
     *    of collection items and the maximum cards allowed per row.
     * 4. Configures the row constraints for the grid pane, ensuring consistent row height
     *    and aligning content to the top.
     * 5. Iterates through each child in the fetched collection. If the child is an individual
     *    enclosure, it adds an enclosure card to the grid. If the child is a composite enclosure
     *    collection, it adds a composite card instead.
     *
     * This method dynamically populates the grid pane with appropriate visual components
     * for each enclosure, providing a hierarchical display of singular and composite enclosures.
     */
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

    /**
     * Opens a new modal window displaying the details of the specified enclosure.
     * This method initializes an FXML-based UI for the enclosure and sets the enclosure data on the controller.
     * It uses the enclosure's name as the title for the window.
     *
     * @param enclosure the enclosure whose details are to be displayed in the modal window. Must not be null.
     */
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