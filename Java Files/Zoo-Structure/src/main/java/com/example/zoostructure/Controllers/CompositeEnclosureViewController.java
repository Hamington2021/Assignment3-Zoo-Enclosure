package com.example.zoostructure.Controllers;

import java.io.IOException;
import java.util.Optional;

import com.example.zoostructure.Helpers.ImportHelper;
import com.example.zoostructure.Model.CompositeEnclosureCollection;
import com.example.zoostructure.Model.Enclosure;
import com.example.zoostructure.Model.EnclosureCollection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * A controller class for managing the composite enclosure view in the zoo application.
 * This controller is responsible for displaying a grid layout of composite enclosure
 * cards and individual enclosure cards, providing functionalities to interact with the
 * enclosures and manage the UI layout dynamically based on input data.
 */
public class CompositeEnclosureViewController {

    /**
     * Represents the main GridPane layout where the composite enclosure cards
     * and individual animal enclosure cards are displayed.
     * It serves as a visual container to organize the displayed content in
     * a grid-based layout for the CompositeEnclosureViewController.
     * <p>
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

    private VBox createStyledCard() {
        VBox card = new VBox(10);
        card.setPadding(new Insets(12));
        card.setAlignment(Pos.TOP_CENTER); // Center content inside the card
        card.setPrefWidth(240);            // Consistent width
        card.setPrefHeight(120);           // Optional: consistent height
        card.setBackground(new Background(new BackgroundFill(Color.web("#F5F5F5"), new CornerRadii(10), Insets.EMPTY)));
        card.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(2))));
        card.setEffect(new DropShadow(5, Color.gray(0.3)));
        return card;
    }

    /**
     * Defines a CSS style for buttons to standardize their visual appearance across the application.
     * The style string includes properties such as background color, border settings, text fill color,
     * font weight, padding, and a drop shadow effect. These properties enhance the visual consistency
     * and usability of the buttons in the graphical user interface.
     */
    private static final String BUTTON_STYLE = """
                -fx-background-color: #dddddd;
                -fx-border-color: #444;
                -fx-border-width: 1;
                -fx-border-radius: 6;
                -fx-text-fill: #222;
                -fx-font-weight: bold;
                -fx-background-radius: 6;
                -fx-padding: 6 12;
                -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 2, 0, 0, 1);
            """;

    /**
     * Adds a new enclosure card to the composite enclosure grid pane. The card includes
     * the enclosure's name and a button to view the enclosure details.
     *
     * @param enclosure the enclosure object to use for creating the card. It provides
     *                  the name of the enclosure and is used to open the corresponding
     *                  enclosure window when the "View" button is clicked.
     */
    public void addEnclosureCard(Enclosure enclosure) {
        VBox card = createStyledCard();

        Label nameLabel = new Label(enclosure.getName());
        nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        nameLabel.setAlignment(Pos.CENTER);

        Button viewButton = new Button("View");
        viewButton.setStyle(BUTTON_STYLE);
        viewButton.setOnAction(e -> openEnclosureWindow(enclosure));

        card.getChildren().addAll(nameLabel, viewButton);

        int col = cardCount % cardPerRow;
        int rowIndex = cardCount / cardPerRow;
        compositeEnclosureGridPane.add(card, col, rowIndex);
        GridPane.setMargin(card, new Insets(10));
        GridPane.setHalignment(card, HPos.CENTER);
        GridPane.setValignment(card, VPos.TOP);

        cardCount++;
    }

    /**
     * Adds a card representing a composite enclosure collection to the grid pane.
     * The card displays the name of the composite enclosure and includes a button
     * that opens a detailed view of the enclosure when clicked.
     *
     * @param composite the composite enclosure collection to be represented by the card.
     *                  It provides the name for display and is used to open the corresponding
     *                  enclosure view when the "View" button is clicked.
     */
    private void addCompositeCard(CompositeEnclosureCollection composite) {
        VBox card = createStyledCard();

        Label title = new Label(composite.getName());
        title.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        title.setAlignment(Pos.CENTER);

        Button viewButton = new Button("View");
        viewButton.setStyle(BUTTON_STYLE);
        viewButton.setOnAction(e -> openEnclosureWindow(composite));

        card.getChildren().addAll(title, viewButton);

        int col = cardCount % cardPerRow;
        int row = cardCount / cardPerRow;
        compositeEnclosureGridPane.add(card, col, row);
        GridPane.setMargin(card, new Insets(10));
        GridPane.setHalignment(card, HPos.CENTER);
        GridPane.setValignment(card, VPos.TOP);

        cardCount++;
    }

    /**
     * Initializes the composite enclosure view by setting up the grid pane layout
     * and populating it with the necessary enclosure cards. The method ensures that
     * the grid pane is aligned properly and dynamically adjusts its row constraints
     * based on the number of items to display.
     *
     * It processes the enclosures in the composite collection, creating individual
     * cards for both direct {@link Enclosure} objects and nested
     * {@link CompositeEnclosureCollection} objects, and adds them to the grid pane.
     *
     * The layout is configured such that each row is constrained with a minimum height
     * and aligns its contents at the top to maintain uniformity.
     */
    @FXML
    private void initialize() {
        compositeEnclosureGridPane.setAlignment(Pos.TOP_CENTER);

        CompositeEnclosureCollection bigCats = ImportHelper.createAnimals();

        int totalItems = bigCats.getCollections().size();
        int totalRows = (int) Math.ceil((double) totalItems / cardPerRow);

        for (int i = 0; i < totalRows; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setMinHeight(150);
            rowConstraints.setValignment(VPos.TOP);
            compositeEnclosureGridPane.getRowConstraints().add(rowConstraints);
        }

        for (EnclosureCollection child : bigCats.getCollections()) {
            // ✅ Only add direct children
            if (child instanceof Enclosure enclosure) {
                addEnclosureCard(enclosure);
            } else if (child instanceof CompositeEnclosureCollection composite) {
                addCompositeCard(composite);
            }
        }
    }

    /**
     * Updates the composite enclosure grid pane by clearing its current content and rebuilding it
     * based on the provided composite collection. This method dynamically calculates and adjusts
     * the grid layout, including rows, and populates it with enclosure cards or nested composite
     * cards as necessary.
     *
     * @param composite the composite enclosure collection containing a list of {@link EnclosureCollection}
     *                  objects to be displayed. It includes both individual enclosures and nested
     *                  composite collections.
     */
    public void setCompositeCollection(CompositeEnclosureCollection composite) {
        compositeEnclosureGridPane.getChildren().clear();
        compositeEnclosureGridPane.getRowConstraints().clear();
        cardCount = 0;

        int totalItems = composite.getCollections().size();
        int totalRows = (int) Math.ceil((double) totalItems / cardPerRow);

        for (int i = 0; i < totalRows; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setMinHeight(150);
            rowConstraints.setValignment(VPos.TOP);
            compositeEnclosureGridPane.getRowConstraints().add(rowConstraints);
        }

        for (EnclosureCollection child : composite.getCollections()) {
            if (child instanceof Enclosure enclosure) {
                addEnclosureCard(enclosure);
            } else if (child instanceof CompositeEnclosureCollection nestedComposite) {
                addCompositeCard(nestedComposite);
            }
        }
    }

    /**
     * Indicates whether the current view is nested within a composite enclosure structure.
     * This flag is used to distinguish between standalone and nested views when managing
     * or displaying composite enclosure collections in the grid pane.
     */
    private boolean isNestedView = false;

    /**
     * Sets the nested view state for the composite enclosure view.
     *
     * @param nested a boolean indicating whether the view should display nested
     *               composite enclosure collections. If true, nested collections
     *               will be shown; otherwise, only top-level elements will be displayed.
     */
    public void setNestedView(boolean nested) {
        this.isNestedView = nested;
    }

    /**
     * Opens a new window to display the details of the specified enclosure. The method
     * dynamically determines whether the provided enclosure is a simple enclosure or
     * a composite enclosure collection, and loads the appropriate view accordingly.
     *
     * @param enclosure the enclosure to be displayed. It can be either a simple
     *                  {@code Enclosure} or a {@code CompositeEnclosureCollection}.
     *                  The window title will be derived from the enclosure's name.
     */
    private void openEnclosureWindow(EnclosureCollection enclosure) {
        try {
            FXMLLoader loader;
            Parent root = null;
            Stage stage = new Stage();
            stage.setWidth(600);
            stage.setMinHeight(250);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(enclosure.getName());

            if (enclosure instanceof CompositeEnclosureCollection composite) {
                loader = new FXMLLoader(getClass().getResource("/com/example/zoostructure/CompositeEnclosureView.fxml"));
                root = loader.load();

                CompositeEnclosureViewController controller = loader.getController();
                controller.setCompositeCollection(composite);
                controller.setNestedView(true); // ✅ mark as nested
            } else if (enclosure instanceof Enclosure simpleEnclosure) {
                loader = new FXMLLoader(getClass().getResource("/com/example/zoostructure/Enclosure-view.fxml"));
                root = loader.load();

                EnclosureViewController controller = loader.getController();
                controller.setEnclosure(simpleEnclosure);
            }

            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the action triggered by a back button click in the composite enclosure view.
     *
     * Depending on the view state, the method performs the following actions:
     * - If the view is nested (`isNestedView` is true), it closes the current window.
     * - If the view is not nested, it prompts the user with a confirmation dialog to exit
     *   the application. If the user confirms, the window is closed; otherwise, no action is taken.
     *
     * This method ensures proper user interaction when navigating back or exiting the interface.
     */
    @FXML
    private void onBackButtonClick() {
        if (isNestedView) {
            Stage stage = (Stage) compositeEnclosureGridPane.getScene().getWindow();
            stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exit Zoo View");
            alert.setHeaderText("You're already at the top-level view.");
            alert.setContentText("Would you like to exit the zoo interface?");

            ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(yesButton, noButton);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == yesButton) {
                Stage stage = (Stage) compositeEnclosureGridPane.getScene().getWindow();
                stage.close();
            }
            // If "No", do nothing
        }
    }

}